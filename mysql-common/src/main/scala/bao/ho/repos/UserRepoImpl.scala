package bao.ho.repos

import bao.ho.converters.DoobieTypeConverter.UUIDConverter
import bao.ho.models.sql.User
import cats.effect.kernel.MonadCancel
import doobie.Transactor
import doobie.implicits._

import scala.language.higherKinds
class UserRepoImpl[F[_]](xa: Transactor[F])(implicit F: MonadCancel[F, Throwable])
    extends UserRepo[F]
    with UUIDConverter {
  def findAll(limit: Int, offset: Int): F[List[User]] =
    sql"select * from user"
      .query[User] // Query0[String]
      .to[List]
      .transact(xa) // IO[List[String]]

  override def findById(id: Int): F[Option[User]] =
    sql"select * from user where id=$id"
      .query[User]  // Query0[String]
      .option
      .transact(xa) // IO[List[String]]

}
