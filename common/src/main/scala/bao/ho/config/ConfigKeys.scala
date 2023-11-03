package bao.ho.config

import bao.ho.config.RunMode.{ Local, Production, Test }
import bao.ho.logger.AppLogger
import bao.ho.utils.Utils
import pureconfig.ConfigSource

object ConfigKeys extends AppLogger {

  val RunModeKey         = "RUN_MODE"
  val ConfFileKey        = "CONF_FILE"
  val LoggingConfFileKey = "LOGGING_CONF"

//  val AmlsVersionKey = "AMLS_VERSION"
//
//  val DbDriverKey                      = "driver"
//  val DbUrlKey                         = "url"
//  val DbUserKey                        = "user"
//  val DbPasswordKey                    = "password"
//  val CpPoolInitialSizeKey             = "poolInitialSize"
//  val CpPoolMaxSizeKey                 = "poolMaxSize"
//  val CpPoolConnectionTimeoutMillisKey = "poolConnectionTimeoutMillis"
//  val CacheTimeL                       = "misc.cacheTimeInSeconds"
//  val UseDBCache                       = "misc.useDBCache"
//  val HttpTimeoutInSeconds             = "http.timeoutInSeconds"
//  val MaxUploadSizeInMb                = "http.maxUploadSizeInMb"
//
//  val AuthEnableKey         = "authEnable"
//  val SecureKeyStoreTypeKey = "keystore.type"
//  val SecureKeys            = "keystore.keys"

  def getConfFiles: EnvironmentConfig = {
    val runMode: RunMode =
      sys.env
        .get(s"${ConfigKeys.RunModeKey}")
        .map(RunMode.withName)
        .getOrElse(RunMode.Local)

    logger.info(s"Application is running in mode: $runMode mode")

    val confFileLocationOpt: Option[String]      = sys.env.get(s"${ConfigKeys.ConfFileKey}")
    val logConfigFileLocationOpt: Option[String] = sys.env.get(s"${ConfigKeys.LoggingConfFileKey}")

    runMode match {
      case Local      =>
        val localConf             = getClass.getClassLoader.getResource("local.conf").getFile
        val confFileLocation      = confFileLocationOpt.getOrElse(localConf)
        val logConfigFileLocation = logConfigFileLocationOpt.getOrElse("log4j2.xml")
        EnvironmentConfig(
          confFileLocation,
          logConfigFileLocation
        )
      case Production =>
        val confFileLocation      = sys.env.getOrElse(
          s"${ConfigKeys.ConfFileKey}",
          Utils.fail[String](s"Can not find ${ConfigKeys.ConfFileKey} variable")
        )
        val logConfigFileLocation = sys.env.getOrElse(
          s"${ConfigKeys.LoggingConfFileKey}",
          Utils.fail[String](s"Can not find ${ConfigKeys.LoggingConfFileKey} variable")
        )
        EnvironmentConfig(confFileLocation, logConfigFileLocation)
      case Test       =>
        val localConf             = getClass.getClassLoader.getResource("test.conf").getFile
        val confFileLocation      = confFileLocationOpt.getOrElse(localConf)
        val logConfigFileLocation = logConfigFileLocationOpt.getOrElse("log4j2-test.xml")
        EnvironmentConfig(
          confFileLocation,
          logConfigFileLocation
        )
    }
  }
}
