package bao.ho.services

import bao.ho.models.sql.User

import scala.language.higherKinds

trait UserService[F[_]] {
  def getUserById(id: Int): F[User]

}
