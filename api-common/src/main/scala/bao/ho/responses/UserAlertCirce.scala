package bao.ho.responses

import bao.ho.models.nosql.AlertCirce
import bao.ho.models.sql.UserCirce
import io.circe.Decoder.Result
import io.circe.syntax.EncoderOps
import io.circe.{ Decoder, Encoder, HCursor, Json }
object UserAlertCirce {

  implicit val decoder: Decoder[UserAlert] = new Decoder[UserAlert] {
    override def apply(c: HCursor): Result[UserAlert] = ???
  }
  implicit val encoder: Encoder[UserAlert] = new Encoder[UserAlert] {
    override def apply(a: UserAlert): Json = Json.obj(
      ("user", a.user.asJson(UserCirce.encoder)),
      ("alert", a.alert.map(_.asJson(AlertCirce.encoder)).getOrElse(Json.Null))
    )
  }

}
