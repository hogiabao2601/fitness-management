package bao.ho.config

case class DatabaseDetails(
  url: String,
  driverName: String,
  host: String,
  port: Int,
  username: String,
  password: String,
  extraConfigs: Map[String, String]
)
