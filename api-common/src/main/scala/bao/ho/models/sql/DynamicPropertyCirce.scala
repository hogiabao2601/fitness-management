package bao.ho.models.sql

import io.circe.generic.semiauto.{ deriveDecoder, deriveEncoder }
import io.circe.{ Decoder, Encoder }

object DynamicPropertyCirce {
  implicit val decoder: Decoder[DynamicProperty] = deriveDecoder[DynamicProperty]
  implicit val encoder: Encoder[DynamicProperty] = deriveEncoder[DynamicProperty]
}
