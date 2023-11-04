package bao.ho.bootstrap

import bao.ho.logger.AppLogger
import bao.ho.services.{ AlertService, AlertServiceImpl, UserService, UserServiceImpl }

import scala.language.higherKinds

trait ServiceBootstrap[F[_]] extends SqlBootstrap[F] with NoSqlBootstrap[F] with AppLogger {
  lazy val userService: UserService[F] =
    new UserServiceImpl[F](userRepo)

  lazy val alertService: AlertService[F] =
    new AlertServiceImpl[F](alertRepo)
}
