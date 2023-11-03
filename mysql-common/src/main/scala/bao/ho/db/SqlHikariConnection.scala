package bao.ho.db

import bao.ho.config.DatabaseDetails
import cats.effect.Async
import com.zaxxer.hikari.{ HikariConfig, HikariDataSource }
import doobie.Transactor
import doobie.hikari.HikariTransactor

import scala.concurrent.ExecutionContext
import scala.language.higherKinds

object SqlHikariConnection extends DbConnection[HikariDataSource] {

  override def dbTransactor[F[_]](dbConfig: DatabaseDetails)(implicit
    F: Async[F],
    ec: ExecutionContext
  ): Transactor.Aux[F, HikariDataSource] = {
    val config: HikariConfig = applyConfig(dbConfig)
    val hikariDataSource     = new HikariDataSource(config)
    HikariTransactor.apply[F](hikariDataSource, ec)
  }

  private def applyConfig(dbConfig: DatabaseDetails): HikariConfig = {
    val config: HikariConfig = new HikariConfig()
    config.setDriverClassName(dbConfig.driverName)
    config.setJdbcUrl(dbConfig.url)
    config.setUsername(dbConfig.username)
    config.setPassword(dbConfig.password)
//    config.setMaximumPoolSize(dbConfig.extraConfigs.get("MaximumPoolSize", "1000").toInt)
//    dbConfig.config.foreach { case (key, value) =>
//      config.addDataSourceProperty(key, value)
//    }
    config
  }
}
