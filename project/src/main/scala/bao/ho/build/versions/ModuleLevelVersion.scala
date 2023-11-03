package bao.ho.build.versions

import bao.ho.build.constants.ModuleConstants.*
import bao.ho.build.profiles.DefaultProfile.moduleLevelVersion
import src.main.scala.bao.ho.build.BuildArgument.buildModule

trait ModuleLevelVersion {

  protected val envSpecificVersion: Version

  def commonDependenciesVersion: Version         = envSpecificVersion
  def dbCommonDependenciesVersion: Version       = envSpecificVersion
  def mysqlCommonDependenciesVersion: Version    = envSpecificVersion
  def hbaseCommonDependenciesVersion: Version    = envSpecificVersion
  def modelDependenciesVersion: Version          = envSpecificVersion
  def scalatraCommonDependenciesVersion: Version = envSpecificVersion
  def scalatraApiDependenciesVersion: Version    = envSpecificVersion
  def playCommonDependenciesVersion: Version     = envSpecificVersion
  def playApiDependenciesVersion: Version        = envSpecificVersion
  def controllerDependenciesVersion: Version     = envSpecificVersion
  def serviceDependenciesVersion: Version        = envSpecificVersion
  def handlerDependenciesVersion: Version        = envSpecificVersion
  def hadoopCommonDependenciesVersion: Version   = envSpecificVersion
  def sparkCommonDependenciesVersion: Version    = envSpecificVersion
  def flinkCommonDependenciesVersion: Version    = envSpecificVersion
  def sparkBatchDependenciesVersion: Version     = envSpecificVersion
  def sparkStreamingDependenciesVersion: Version = envSpecificVersion
  def schedulerDependenciesVersion: Version      = envSpecificVersion
  def apiCommonDependenciesVersion: Version      = envSpecificVersion

  // scalastyle:off
  def dependenciesVersion: Version =
    Option(buildModule) match {
      case Some(Common)         => moduleLevelVersion.commonDependenciesVersion
      case Some(DbCommon)       => moduleLevelVersion.dbCommonDependenciesVersion
      case Some(MysqlCommon)    => moduleLevelVersion.mysqlCommonDependenciesVersion
      case Some(HbaseCommon)    => moduleLevelVersion.hbaseCommonDependenciesVersion
      case Some(Model)          => moduleLevelVersion.modelDependenciesVersion
      case Some(ScalatraApi)    => moduleLevelVersion.scalatraApiDependenciesVersion
      case Some(PlayApi)        => moduleLevelVersion.playApiDependenciesVersion
      case Some(Controller)     => moduleLevelVersion.controllerDependenciesVersion
      case Some(Service)        => moduleLevelVersion.serviceDependenciesVersion
      case Some(Handler)        => moduleLevelVersion.handlerDependenciesVersion
      case Some(HadoopCommon)   => moduleLevelVersion.hadoopCommonDependenciesVersion
      case Some(SparkCommon)    => moduleLevelVersion.sparkCommonDependenciesVersion
      case Some(FlinkCommon)    => moduleLevelVersion.flinkCommonDependenciesVersion
      case Some(SparkBatch)     => moduleLevelVersion.sparkBatchDependenciesVersion
      case Some(SparkStreaming) => moduleLevelVersion.sparkStreamingDependenciesVersion
      case Some(Scheduler)      => moduleLevelVersion.schedulerDependenciesVersion
      case _                    => moduleLevelVersion.commonDependenciesVersion
    }
}
