package bao.ho

import bao.ho.bootstrap.ApiBootstrap
import bao.ho.controllers.{ UserControllerImpl, UserControllerServlet }
import cats.effect.IO

import scala.language.higherKinds

trait ControllerBootstrap extends ApiBootstrap[IO] {
  lazy val userController = new UserControllerImpl[IO](userService)

  lazy val myScalatraServlet = new UserControllerServlet[IO](userController)(ec, F)
}
