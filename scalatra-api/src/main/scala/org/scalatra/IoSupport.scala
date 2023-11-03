package org.scalatra

import cats.effect.IO
import jakarta.servlet.AsyncContext
import org.scalatra.ServletCompat.{ AsyncEvent, AsyncListener }
import org.scalatra.servlet.AsyncSupport

import java.util.concurrent.atomic.AtomicBoolean
import scala.concurrent.duration.Duration
import scala.concurrent.{ ExecutionContext, Future }
import scala.util.{ Failure, Success }

trait IoSupport extends AsyncSupport {
  implicit protected def executor: ExecutionContext

  override def asynchronously(f: => Any): Action = () => IO(f)

  override protected def isAsyncExecutable(result: Any): Boolean =
    classOf[IO[_]].isAssignableFrom(result.getClass) ||
      classOf[IoAsyncResult].isAssignableFrom(result.getClass)

  import cats.effect.unsafe.implicits._

  override protected def renderResponse(actionResult: Any): Unit =
    actionResult match {
      case r: IoAsyncResult => handleFuture(r.is.unsafeToFuture(), Some(r.timeout))
      case f: IO[_]         => handleFuture(f.unsafeToFuture(), None)
      case a                => super.renderResponse(a)
    }

  private[this] def handleFuture(f: Future[_], timeout: Option[Duration]): Unit = {
    val gotResponseAlready    = new AtomicBoolean(false)
    val context: AsyncContext = request.startAsync(request, response)
    timeout.foreach { timeout =>
      if (timeout.isFinite) context.setTimeout(timeout.toMillis) else context.setTimeout(-1)
    }

    def renderFutureResult(f: Future[_]): Unit =
      f.onComplete {
        // Loop until we have a non-future result
        case Success(f2: IO[_])        => renderFutureResult(f2.unsafeToFuture())
        case Success(r: IoAsyncResult) => renderFutureResult(r.is.unsafeToFuture())
        case t                         =>
          if (gotResponseAlready.compareAndSet(false, true)) {
            withinAsyncContext(context) {
              try
                t.map { result =>
                  renderResponse(result)
                }.recover {
                  case e: HaltException =>
                    renderHaltException(e)
                  case e                =>
                    try
                      renderResponse(errorHandler(e))
                    catch {
                      case e: HaltException =>
                        renderHaltException(e)
                      case e: Throwable     =>
                        ScalatraBase.runCallbacks(Failure(e))
                        renderUncaughtException(e)
                        ScalatraBase.runRenderCallbacks(Failure(e))
                    }
                }
              finally
                context.complete()
            }
          }
      }

    context.addListener(new AsyncListener {

      def onTimeout(event: AsyncEvent): Unit =
        onAsyncEvent(event) {
          if (gotResponseAlready.compareAndSet(false, true)) {
            renderHaltException(HaltException(Some(504), Map.empty, "Gateway timeout"))
            event.getAsyncContext.complete()
          }
        }

      def onComplete(event: AsyncEvent): Unit = {}

      def onError(event: AsyncEvent): Unit =
        onAsyncEvent(event) {
          if (gotResponseAlready.compareAndSet(false, true)) {
            event.getThrowable match {
              case e: HaltException => renderHaltException(e)
              case e                =>
                try
                  renderResponse(errorHandler(e))
                catch {
                  case e: Throwable =>
                    ScalatraBase.runCallbacks(Failure(e))
                    renderUncaughtException(e)
                    ScalatraBase.runRenderCallbacks(Failure(e))
                }
            }
          }
        }

      def onStartAsync(event: AsyncEvent): Unit = {}
    })

    renderFutureResult(f)
  }
}
