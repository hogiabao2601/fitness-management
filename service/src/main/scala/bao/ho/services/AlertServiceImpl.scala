package bao.ho.services

import bao.ho.models.nosql.Alert
import bao.ho.models.sql.User
import bao.ho.repos.{ AlertRepo, UserRepo }
import bao.ho.utils.Utils
import cats.effect.Async
import cats.implicits._

import scala.language.higherKinds

class AlertServiceImpl[F[_]](alertRepo: AlertRepo[F])(implicit F: Async[F])
    extends AlertService[F] {
//  def getAlertById(id: Int): F[Alert] =
//    for {
//      alertOpt <- alertRepo.findById(id)
//      alert     = alertOpt.getOrElse(Utils.fail(s"No user with id $id"))
//    } yield alert

  def getAlertById(id: Int): F[Option[Alert]] =
    alertRepo.findById(id)

}
