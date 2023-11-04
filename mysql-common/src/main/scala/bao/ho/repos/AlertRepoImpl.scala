package bao.ho.repos

import bao.ho.converters.DoobieTypeConverter.UUIDConverter
import bao.ho.models.nosql.Alert
import bao.ho.models.sql.User
import cats.effect.kernel.MonadCancel
import doobie.Transactor
import doobie.implicits._

import scala.language.higherKinds

class AlertRepoImpl[F[_]](xa: Transactor[F])(implicit F: MonadCancel[F, Throwable])
    extends AlertRepo[F]
    with UUIDConverter {
  def findAll(limit: Int, offset: Int): F[List[Alert]] =
    sql"select * from Alert"
      .query[Alert] // Query0[String]
      .to[List]
      .transact(xa) // IO[List[String]]

  override def findById(id: Int): F[Option[Alert]] =
    sql"select * from Alert where id=$id"
      .query[Alert] // Query0[String]
      .option
      .transact(xa) // IO[List[String]]

}
