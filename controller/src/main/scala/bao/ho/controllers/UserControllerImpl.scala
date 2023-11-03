package bao.ho.controllers

import bao.ho.models.sql.User
import bao.ho.services.UserService

import scala.language.higherKinds

class UserControllerImpl[F[_]](userService: UserService[F]) extends UserController[F] {
  def findUserById(id: Int): F[User] = userService.getUserById(id)

}
