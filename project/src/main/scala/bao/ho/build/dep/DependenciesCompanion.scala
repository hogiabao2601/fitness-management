package bao.ho.build.dep

import bao.ho.build.versions.Version
import src.main.scala.bao.ho.build.dep.BaseDependencies

trait DependenciesCompanion {
  def apply(version: Version): BaseDependencies
}
