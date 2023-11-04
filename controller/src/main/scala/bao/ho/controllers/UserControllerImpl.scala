package bao.ho.controllers

import bao.ho.responses.UserAlert
import bao.ho.services.{ AlertService, UserService }
import cats.effect.kernel.Async
import cats.syntax.flatMap._
import cats.syntax.functor._

import scala.language.higherKinds

class UserControllerImpl[F[_]](userService: UserService[F], alertService: AlertService[F])(implicit
  F: Async[F]
) extends UserController[F] {
  def findUserById(id: Int): F[UserAlert] =
    for {
      user  <- userService.getUserById(id)
      alert <- alertService.getAlertById(id)
    } yield UserAlert(user, alert)

}
