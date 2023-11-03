package bao.ho.bootstrap

import scala.language.higherKinds

trait HandlerBootstrap[F[_]] extends ServiceBootstrap[F] {}
