package bao.ho.models.nosql

import io.circe.generic.semiauto.{ deriveDecoder, deriveEncoder }
import io.circe.{ Decoder, Encoder }

object AlertCirce {
  implicit val decoder: Decoder[Alert] = deriveDecoder[Alert]
  implicit val encoder: Encoder[Alert] = deriveEncoder[Alert]
}
