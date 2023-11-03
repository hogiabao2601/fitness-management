package bao.ho.models.sql

import io.circe.generic.semiauto.{ deriveDecoder, deriveEncoder }
import io.circe.{ Decoder, Encoder }

object UserCirce {
  implicit val decoder: Decoder[User] = deriveDecoder[User]
  implicit val encoder: Encoder[User] = deriveEncoder[User]
}
