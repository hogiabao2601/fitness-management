package src.main.scala.bao.ho.build

import sbt.*
object Resolvers {

  /** This will resolve cdh specific hadoop jars */
  val cdhResolver   =
    "Cloudera Maven Repository"
      .at("https://repository.cloudera.com/artifactory/cloudera-repos")
  val jBossResolver =
    "JBoss"
      .at("https://maven.repository.redhat.com/ga/")
  val mavenResolver =
    "Maven"
      .at("https://repo1.maven.org/maven2/")
  val CdpResolver   =
    "CDP7_1_8"
      .at("https://archive.cloudera.com/cdh7/7.1.8.0/maven-repository")

  val SparkPackageResolver =
    "bintray-spark-packages"
      .at("https://repos.spark-packages.org")

  val resolvers: Seq[MavenRepository] = Seq(
    mavenResolver,
    cdhResolver,
    jBossResolver,
    CdpResolver,
    SparkPackageResolver
  )
}
