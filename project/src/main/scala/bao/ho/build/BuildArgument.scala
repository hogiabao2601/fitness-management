package src.main.scala.bao.ho.build

import bao.ho.build.constants.ModuleKeyConstants

object BuildArgument {
  val buildModule: String = System.getProperties.getProperty(ModuleKeyConstants.buildModuleKey)
  val buildEnv: String    = System.getProperties.getProperty(ModuleKeyConstants.buildEnvKey)
}
