package bao.ho.build.utils

import java.time.format.DateTimeFormatter
import java.time.{ Instant, ZoneOffset }

object DateUtils {

  val DateFormat = "uuuu-MM-dd HH:mm:ss Z"

  def toDateString(inst: Instant): String =
    inst
      .atOffset(ZoneOffset.UTC)
      .format(
        DateTimeFormatter
          .ofPattern(DateFormat)
      )
}
