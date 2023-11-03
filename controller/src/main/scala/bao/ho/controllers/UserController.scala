package bao.ho.controllers

import bao.ho.models.sql.User

import scala.language.higherKinds

trait UserController[F[_]] {
  def findUserById(id: Int): F[User]

}
