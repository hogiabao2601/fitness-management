package bao.ho.repos

import bao.ho.models.nosql.Alert

import scala.language.higherKinds

trait AlertRepo[F[_]] {
  def findAll(limit: Int, offset: Int): F[List[Alert]]

  def findById(id: Int): F[Option[Alert]]

}
