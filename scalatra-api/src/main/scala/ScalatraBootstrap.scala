import bao.ho.ControllerBootstrap
import bao.ho.logger.AppLogger
import cats.effect.{ Async, IO }
import jakarta.servlet.ServletContext
import org.scalatra._

import scala.concurrent.ExecutionContext

class ScalatraBootstrap extends LifeCycle with ControllerBootstrap with AppLogger {
  override def init(context: ServletContext): Unit = {
    logger.info("Mounting controller")
    context.mount(myScalatraServlet, "/*")
  }

  override implicit val F: Async[IO]         = IO.asyncForIO
  override implicit val ec: ExecutionContext = ExecutionContext.Implicits.global
}
