package bao.ho.bootstrap

import bao.ho.logger.AppLogger
import bao.ho.services.{ UserService, UserServiceImpl }

import scala.language.higherKinds

trait ServiceBootstrap[F[_]] extends SqlBootstrap[F] with AppLogger {
  lazy val userService: UserService[F] = {
    logger.info("Creating new User service")
    new UserServiceImpl[F](userRepo)
  }
}
