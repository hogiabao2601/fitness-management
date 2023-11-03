package bao.ho.config

import enumeratum._

import scala.collection.immutable

sealed trait RunMode extends EnumEntry

object RunMode extends Enum[RunMode] {

  /*
   `findValues` is a protected method that invokes a macro to find all `Greeting` object declarations inside an `Enum`

   You use it to implement the `val values` member
   */
  val values: immutable.IndexedSeq[RunMode] = findValues

  case object Test       extends RunMode
  case object Local      extends RunMode
  case object Production extends RunMode

}
