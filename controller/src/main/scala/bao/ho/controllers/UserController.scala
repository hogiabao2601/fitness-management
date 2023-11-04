package bao.ho.controllers

import bao.ho.responses.UserAlert

import scala.language.higherKinds

trait UserController[F[_]] {
  def findUserById(id: Int): F[UserAlert]

}
