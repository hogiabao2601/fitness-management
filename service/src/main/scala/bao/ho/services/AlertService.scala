package bao.ho.services

import bao.ho.models.nosql.Alert

import scala.language.higherKinds

trait AlertService[F[_]] {
//  def getAlertById(id: Int): F[Alert]
  def getAlertById(id: Int): F[Option[Alert]]

}
