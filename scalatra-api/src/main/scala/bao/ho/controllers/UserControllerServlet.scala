package bao.ho.controllers

import bao.ho.logger.AppLogger
import bao.ho.responses.UserAlertCirce._
import cats.effect.Async
import cats.syntax.functor._
import cats.syntax.applicativeError._
import io.circe.syntax.EncoderOps
import org.scalatra.{ BadRequest, Ok }

import scala.concurrent.ExecutionContext
import scala.language.higherKinds

class UserControllerServlet[F[_]](userController: UserController[F])(implicit
  ec: ExecutionContext,
  F: Async[F]
) extends BaseScalatraServlet
    with AppLogger {
  get("/") {
    val id = params("id")
    userController
      .findUserById(id.toInt)
      .map(_.asJson)
      .map(Ok(_))
      .recover { case ex: Throwable =>
        ex.printStackTrace()
        BadRequest(ex.getMessage)
      }

  }

//  post("/") {
//    val result = (1 to 100000000).sum
//    logger.info(s"result: $result")
//
//    "I am a post request $result"
//  }
//
//  put("/") {
//    val result = (1 to 100000000).sum
//    logger.info(s"result: $result")
//
//    "I am a put request $result"
//  }

}
