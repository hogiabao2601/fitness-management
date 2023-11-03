package bao.ho.build.profiles

import bao.ho.build.constants.ModuleKeyConstants

object ProfileResolver {

  private val buildEnv: String = System.getProperties.getProperty(ModuleKeyConstants.buildEnvKey)

  lazy val profile: Profile = {
    val profile = Option(buildEnv) match {
      case None | Some("") => DefaultProfile
      case _               => throw new Exception(s"Requested build environment: $buildEnv is not supported")
    }
    profile
  }
}
