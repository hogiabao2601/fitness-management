package bao.ho.config

import pureconfig.ConfigReader.Result
import pureconfig.{ConfigObjectSource, ConfigSource}
import pureconfig.generic.auto._

object ConfigReader {
  private val appConfig: ConfigObjectSource = ConfigSource.file(ConfigKeys.getConfFiles.confFileLocation)

  val dbDetails: Result[DatabaseDetails] = appConfig.at("db.sql").load[DatabaseDetails]

}
