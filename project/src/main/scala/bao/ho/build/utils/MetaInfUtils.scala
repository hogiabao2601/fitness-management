package bao.ho.build.utils

import java.io.PrintWriter

import sbt.*

object MetaInfUtils {

  def addDependencyTreeToMetaInf(
    moduleName: String,
    target: File,
    scalaMajorVer: String,
    depTreeContent: String,
    warInfModule: String
  ): String = {
    val fileName         = s"${moduleName}_dep.txt"
    val jarMetaInfDir    = s"$target/scala-$scalaMajorVer/classes/META-INF"
    val webAppMetaInfDir = s"$target/webapp/META-INF"
    if (warInfModule == moduleName) {
      addDependencyTreeToMetaInf(depTreeContent, fileName, webAppMetaInfDir)
    }

    addDependencyTreeToMetaInf(depTreeContent, fileName, jarMetaInfDir)
  }

  private def addDependencyTreeToMetaInf(
    depTreeContent: String,
    fileName: String,
    jarMetaInfDir: String
  ): String = {
    val dependencyTreePath = s"$jarMetaInfDir/$fileName"
    println(s"Writing dependency tree to file $dependencyTreePath")
    addContentToFile(depTreeContent, dependencyTreePath)
    val jarFilePath        = s"META-INF/$fileName"
    jarFilePath
  }

  def addContentToFile(content: String, targetPath: String): String = {
    val targetFile = new File(targetPath)
    val parentDir  = targetFile.getParentFile
    if (!parentDir.exists()) {
      parentDir.mkdirs()
    }
    new PrintWriter(targetFile) { write(content); close() }
    targetFile.getName
  }
}
