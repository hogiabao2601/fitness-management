package bao.ho.build.profiles

import bao.ho.build.constants.ModuleConstants.*
import sbt.ModuleID
import bao.ho.build.dep.{ DefaultDependencies, DependenciesCompanion }
import bao.ho.build.versions.{ DefaultVersion, ModuleLevelVersion }
object DefaultProfile extends Profile {
  override val moduleLevelVersion: ModuleLevelVersion = DefaultVersion
  override val dependencies: DependenciesCompanion    = DefaultDependencies

  override def commonModuleDependencies: Seq[ModuleID] = {
    val dv = dependencies.apply(moduleLevelVersion.commonDependenciesVersion)
    import dv.*
    prepareDependencies(
      Common,
      Seq(
        log4j2Dependencies,
        catsDependencies,
        catsEffectDependencies,
        pureconfigDependencies,
        enumeratumDependencies
      ).flatten,
      Seq.empty,
      Seq.empty
    )
  }

  override def dbCommonModuleDependencies: Seq[ModuleID] = Seq()

  override def mysqlCommonModuleDependencies: Seq[ModuleID] = {
    val dv = dependencies.apply(moduleLevelVersion.scalatraCommonDependenciesVersion)
    import dv.*
    prepareDependencies(
      ScalatraApi,
      Seq(doobieDependencies, sqlDependencies).flatten,
      Seq.empty,
      Seq.empty
    )
  }

  override def hbaseCommonModuleDependencies: Seq[ModuleID] = Seq()

  override def modelModuleDependencies: Seq[ModuleID] = Seq()

  override def scalatraApiModuleDependencies: Seq[ModuleID] = {
    val dv = dependencies.apply(moduleLevelVersion.scalatraCommonDependenciesVersion)
    import dv.*
    prepareDependencies(
      ScalatraApi,
      scalatraDependencies,
      Seq.empty,
      Seq.empty
    )
  }

  override def playApiModuleDependencies: Seq[ModuleID] = Seq()

  override def controllerModuleDependencies: Seq[ModuleID] = Seq()

  override def serviceModuleDependencies: Seq[ModuleID] = Seq()

  override def handlerModuleDependencies: Seq[ModuleID] = Seq()

  override def hadoopCommonModuleDependencies: Seq[ModuleID] = Seq()

  override def sparkCommonModuleDependencies: Seq[ModuleID] = Seq()

  override def flinkCommonModuleDependencies: Seq[ModuleID] = Seq()

  override def sparkBatchModuleDependencies: Seq[ModuleID] = Seq()

  override def sparkStreamingModuleDependencies: Seq[ModuleID] = Seq()

  override def schedulerModuleDependencies: Seq[ModuleID] = Seq()

  override def apiCommonModuleDependencies: Seq[ModuleID] = {
    val dv = dependencies.apply(moduleLevelVersion.apiCommonDependenciesVersion)
    import dv.*
    prepareDependencies(
      ApiCommon,
      circeDependencies,
      Seq.empty,
      Seq.empty
    )
  }
}
