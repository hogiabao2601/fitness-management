package bao.ho.responses

import bao.ho.models.nosql.Alert
import bao.ho.models.sql.User

case class UserAlert(user: User, alert: Option[Alert])
