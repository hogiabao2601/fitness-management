package bao.ho.bootstrap

import bao.ho.config.{ ConfigReader, DatabaseDetails }
import bao.ho.db.SqlHikariConnection
import bao.ho.repos.{ AlertRepo, AlertRepoImpl, UserRepo, UserRepoImpl }
import bao.ho.utils.Utils
import cats.effect.Async
import com.zaxxer.hikari.HikariDataSource
import doobie.util.transactor.Transactor

import scala.concurrent.ExecutionContext
import scala.language.higherKinds

trait NoSqlBootstrap[F[_]] extends ConfigBootstrap {

  implicit val F: Async[F]
  implicit val ec: ExecutionContext
  private val dbDetails: DatabaseDetails                =
    ConfigReader.hbaseDbDetails.fold(error => Utils.fail(error.toString()), identity)
  lazy val hbaseXa: Transactor.Aux[F, HikariDataSource] =
    SqlHikariConnection.dbTransactor(dbDetails)

  lazy val alertRepo: AlertRepo[F] = new AlertRepoImpl[F](hbaseXa)

}
