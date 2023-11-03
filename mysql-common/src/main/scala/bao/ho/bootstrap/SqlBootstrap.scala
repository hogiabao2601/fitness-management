package bao.ho.bootstrap

import bao.ho.config.{ ConfigReader, DatabaseDetails }
import bao.ho.db.SqlHikariConnection
import bao.ho.repos.{ UserRepo, UserRepoImpl }
import bao.ho.utils.Utils
import cats.effect.Async
import com.zaxxer.hikari.HikariDataSource
import doobie.util.transactor.Transactor

import scala.concurrent.ExecutionContext
import scala.language.higherKinds

trait SqlBootstrap[F[_]] extends ConfigBootstrap {

  implicit val F: Async[F]
  implicit val ec: ExecutionContext
  private val dbDetails: DatabaseDetails           =
    ConfigReader.dbDetails.fold(_ => Utils.fail(""), identity)
  lazy val xa: Transactor.Aux[F, HikariDataSource] = SqlHikariConnection.dbTransactor(dbDetails)
  lazy val userRepo: UserRepo[F]                   = new UserRepoImpl[F](xa)

}
