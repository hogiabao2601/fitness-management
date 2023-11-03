package bao.ho.controllers

import cats.effect.{ Async, IO, LiftIO }
import org.scalatra.{
  ActionResult,
  AsyncResult,
  FutureSupport,
  HttpMethod,
  IoSupport,
  Route,
  RouteTransformer,
  ScalatraServlet
}

import scala.concurrent.duration.Duration
import scala.concurrent.{ ExecutionContext, Future }
import scala.language.higherKinds

class BaseScalatraServlet[F[_]](implicit ec: ExecutionContext, F: Async[F])
    extends ScalatraServlet
    with IoSupport {

  import cats.syntax.option._
  import cats.syntax.apply._
  import cats.syntax.applicative._
  import cats.syntax.applicativeError._
//  import io.circe.syntax._
//  import io.circe.generic.auto._ // for automatic encoder derivation
  override protected implicit def executor: ExecutionContext = ec

  override protected def addRoute(
    method: HttpMethod,
    transformers: Seq[RouteTransformer],
    action: => Any
  ): Route =
    super.addRoute(
      method,
      transformers,
      action
    )

//  protected def async(
//    fa: => IO[ActionResult]
//  ): IoAsyncResult =
//    new IoAsyncResult {
//      override val is: IO[_] = fa
//    }

}
