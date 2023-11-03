package bao.ho.repos

import bao.ho.models.sql.User

import scala.language.higherKinds

trait UserRepo[F[_]] {
  def findAll(limit: Int, offset: Int): F[List[User]]

  def findById(id: Int): F[Option[User]]

}
