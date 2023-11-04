package bao.ho.bootstrap

import bao.ho.config.ConfigReader
import bao.ho.logger.AppLogger

trait ConfigBootstrap extends AppLogger {
  logger.info(s"${ConfigReader.mysqlDbDetails}")

}
