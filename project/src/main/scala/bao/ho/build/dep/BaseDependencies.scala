package src.main.scala.bao.ho.build.dep

import bao.ho.build.versions.Version
import sbt.*
class BaseDependencies(version: Version) {

  import version.*

  val scalatraDependencies: Seq[ModuleID] = Seq(
    "org.apache.tomcat.embed" % "tomcat-embed-core"          % tomcatEmbedVersion,
    "org.scalatra"           %% "scalatra-jakarta"           % scalatraVersion,
    "org.scalatra"           %% "scalatra-scalatest-jakarta" % scalatraVersion       % Test,
    "jakarta.servlet"         % "jakarta.servlet-api"        % jakartaServletVersion % Provided
  )

  val log4j2Dependencies: Seq[ModuleID] = Seq(
    "org.slf4j"                % "slf4j-api"         % slf4jVersion,
    "org.apache.logging.log4j" % "log4j-core"        % log4j2Version, // apache
    "org.apache.logging.log4j" % "log4j-api"         % log4j2Version,
    "org.apache.logging.log4j" % "log4j-slf4j2-impl" % log4j2Version  // apache
  )

  val doobieDependencies: Seq[ModuleID] = Seq(
    "org.tpolecat" %% "doobie-core"   % "1.0.0-RC1",
    "org.tpolecat" %% "doobie-hikari" % "1.0.0-RC1", // HikariCP transactor.
    "org.tpolecat" %% "doobie-quill"  % "1.0.0-RC1"
//    "io.getquill"  %% "quill-doobie"  % "4.8.0"
  )

  val catsDependencies: Seq[ModuleID] = Seq(
    "org.typelevel" %% "cats-core" % catVersion
  )

  val catsEffectDependencies: Seq[ModuleID] = Seq(
    "org.typelevel" %% "cats-effect" % catEffectVersion
  )

  val pureconfigDependencies: Seq[ModuleID] = Seq(
    "com.github.pureconfig" %% "pureconfig" % pureconfigVersion
  )

  val enumeratumDependencies: Seq[ModuleID] = Seq(
    "com.beachape" %% "enumeratum" % enumeratumVersion
  )

  val sqlDependencies: Seq[ModuleID] = Seq(
    "com.mysql" % "mysql-connector-j" % mysqlVersion // GPL 2.0
  )

  val phoenixDependencies: Seq[ModuleID] = Seq(
    "org.apache.phoenix" % "phoenix-client-hbase-2.4" % "5.1.3"
  )

  val circeDependencies: Seq[ModuleID] = Seq(
    "io.circe" %% "circe-core"    % circeVersion,
    "io.circe" %% "circe-parser"  % circeVersion,
    "io.circe" %% "circe-generic" % circeVersion
  )

}
