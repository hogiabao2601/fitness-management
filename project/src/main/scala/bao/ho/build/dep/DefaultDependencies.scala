package bao.ho.build.dep

import bao.ho.build.versions.Version
import src.main.scala.bao.ho.build.dep.BaseDependencies
class DefaultDependencies(version: Version) extends BaseDependencies(version) {}

object DefaultDependencies extends DependenciesCompanion {
  def apply(version: Version): DefaultDependencies = new DefaultDependencies(version)
}
