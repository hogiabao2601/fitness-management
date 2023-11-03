package bao.ho.converters

import io.getquill.MappedEncoding

import java.util.UUID

object DoobieTypeConverter {
  trait UUIDConverter{
    implicit val encodeUuid: MappedEncoding[UUID, String] =
      MappedEncoding[UUID, String](_.toString)

    implicit val decodeUuid: MappedEncoding[String, UUID] =
      MappedEncoding[String, UUID](UUID.fromString)
  }

}
