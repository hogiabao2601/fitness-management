package bao.ho.build.profiles

import sbt.ModuleID
import sbt.librarymanagement.InclExclRule
import bao.ho.build.dep.DependenciesCompanion
import bao.ho.build.versions.ModuleLevelVersion

trait Profile {
  val moduleLevelVersion: ModuleLevelVersion
  val dependencies: DependenciesCompanion
  def commonModuleDependencies: Seq[ModuleID]
  def dbCommonModuleDependencies: Seq[ModuleID]
  def mysqlCommonModuleDependencies: Seq[ModuleID]
  def hbaseCommonModuleDependencies: Seq[ModuleID]
  def modelModuleDependencies: Seq[ModuleID]
  def scalatraApiModuleDependencies: Seq[ModuleID]
  def playApiModuleDependencies: Seq[ModuleID]
  def controllerModuleDependencies: Seq[ModuleID]
  def serviceModuleDependencies: Seq[ModuleID]
  def handlerModuleDependencies: Seq[ModuleID]
  def hadoopCommonModuleDependencies: Seq[ModuleID]
  def sparkCommonModuleDependencies: Seq[ModuleID]
  def flinkCommonModuleDependencies: Seq[ModuleID]
  def sparkBatchModuleDependencies: Seq[ModuleID]
  def sparkStreamingModuleDependencies: Seq[ModuleID]
  def schedulerModuleDependencies: Seq[ModuleID]
  def apiCommonModuleDependencies: Seq[ModuleID]

  protected def prepareDependencies(
    module: String,
    dep: Seq[ModuleID],
    exclude: Seq[InclExclRule],
    include: Seq[ModuleID]
  ): Seq[sbt.ModuleID] = {
    print(s"\n============= Building $module with dependencies: \n $dep ============= ")
    dep.map(_.excludeAll(exclude *)) ++ include
  }
}
