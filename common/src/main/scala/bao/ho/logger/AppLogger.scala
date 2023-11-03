package bao.ho.logger

import org.slf4j.{ Logger, LoggerFactory }

trait AppLogger {
  val logger: Logger = LoggerFactory.getLogger(getClass.getName)
}
