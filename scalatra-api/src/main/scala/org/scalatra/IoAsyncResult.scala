package org.scalatra

import cats.effect.IO
import org.scalatra.ServletCompat.ServletContext
import org.scalatra.ServletCompat.http.{ HttpServletRequest, HttpServletResponse }

import scala.concurrent.duration.{ Duration, DurationInt }

abstract class IoAsyncResult(implicit override val scalatraContext: ScalatraContext)
    extends ScalatraContext {

  implicit val request: HttpServletRequest = scalatraContext.request

  implicit val response: HttpServletResponse = scalatraContext.response

  val servletContext: ServletContext = scalatraContext.servletContext

  // This is a Duration instead of a timeout because a duration has the concept of infinity
  implicit def timeout: Duration = 30.seconds

  val is: IO[_]

}
