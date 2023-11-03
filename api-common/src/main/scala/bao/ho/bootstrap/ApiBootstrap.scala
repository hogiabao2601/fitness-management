package bao.ho.bootstrap

import scala.language.higherKinds

trait ApiBootstrap[F[_]] extends HandlerBootstrap[F] {}
