package bao.ho.db

import bao.ho.config.DatabaseDetails
import cats.effect.Async
import doobie.util.transactor.Transactor

import scala.concurrent.ExecutionContext
import scala.language.higherKinds

trait DbConnection[A] {

  def dbTransactor[F[_]](dbConfig: DatabaseDetails)(implicit
    F: Async[F],
    //    cs: ContextShift[F],
    ec: ExecutionContext
    //    blocker: Blocker
  ): Transactor.Aux[F, A]
}
