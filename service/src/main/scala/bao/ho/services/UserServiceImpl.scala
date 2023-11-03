package bao.ho.services

import bao.ho.models.sql.User
import bao.ho.repos.UserRepo
import bao.ho.utils.Utils
import cats.effect.Async
import cats.implicits._

import scala.language.higherKinds

class UserServiceImpl[F[_]](userRepo: UserRepo[F])(implicit F: Async[F]) extends UserService[F] {
  def getUserById(id: Int): F[User] =
    for {
      userOpt <- userRepo.findById(id)
      user     = userOpt.getOrElse(Utils.fail(s"No user with id $id"))
    } yield user

}
