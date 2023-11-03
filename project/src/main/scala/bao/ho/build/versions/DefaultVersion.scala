package bao.ho.build.versions

object DefaultVersion extends ModuleLevelVersion {
  class EnvSpecificVersion extends Version {
    override val hadoopV: String         = "3.3.5"
    override val sparkV: String          = "3.5.0"
    override val scalatraVersion: String = "3.0.0"
  }

  val envSpecificVersion: Version = new EnvSpecificVersion()

}
