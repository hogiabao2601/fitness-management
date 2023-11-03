//import profiles.ProfileResolver.profile._
//import rule.ExclusionRules
//import sbt.Package.ManifestAttributes
//import utils.MetaInfUtils._
//import utils.DateUtils._
//import java.time.Instant

name := "fitness-management"
//initialCommands := "import com.tookitaki.aip.common._; import com.tookitaki.aip.common.wrapper._; import com.tookitaki.aip.common.util._"

//val apiProjectName   = "api"

//val modulePrefix     = "tdss"
//val commonModuleName = "common"

//ThisBuild / useCoursier := false

//ThisBuild / libraryDependencySchemes ++= Seq(
//  "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always
//)

//lazy val publishOpts = Seq(
//  credentials += Credentials(Path.userHome / ".ivy2" / "credentials"),
//  publishConfiguration      := publishConfiguration.value.withOverwrite(true),
////  pushRemoteCacheConfiguration := pushRemoteCacheConfiguration.value.withOverwrite(true),
//  publishLocalConfiguration := publishLocalConfiguration.value.withOverwrite(true),
//  publishMavenStyle         := true,
//  Test / publishArtifact    := false,
//  pomIncludeRepository      := { _ =>
//    false
//  },
//  ThisBuild / publishTo     := Some(
//    "Artifactory Realm"
//      .at("http://tookitaki-artifacts.tookitaki.com/artifactory/tookitaki-releases")
//      .withAllowInsecureProtocol(true)
//  )
//)

//lazy val noPublishSettings = Seq(
//  publish / skip := true
//)

val ProjectVersion = "1.1.0"
val ScalaVersion   = "2.12.17"

lazy val commonSettings = Seq(
  organization  := "bao.ho",
  version       := ProjectVersion,
  scalaVersion  := ScalaVersion,
  scalacOptions := Seq("-deprecation", "-feature", "-encoding", "utf8", "-Ypartial-unification")
//  assembly / test := {}
//  Test / coverageEnabled := true,
//  Test / envVars         := Map(
//    "SOD_ENV"                  -> "test",
//    "LOG4J_LEVEL"              -> "ERROR",
//    "LOG4J_CONFIGURATION_FILE" -> "src/test/resources/log4j.properties"
//  ),
//  Test / fork            := true,
//  Test / exportJars      := false,
//  credentials += Credentials(Path.userHome / ".ivy2" / "credentials"),
//  resolvers ++= Resolvers.*,
//  excludeDependencies ++= Seq(
//    ExclusionRules.orgSlf4jLog4j12
//  ),
//  dependencyOverrides ++= Seq(
//    "org.scala-lang.modules" % "scala-parser-combinators_2.12" % "2.1.0"
//     "org.scala-lang.modules" % "scala-xml_2.12" % "2.1.0"
//  ),
//  packageOptions         := Seq(
//    ManifestAttributes(
//      ("Vendor", "Tookitaki"),
//      ("Title", "TT Analytics (TDSS)"),
//      ("Build-Timestamp", toDateString(Instant.now())),
//      ("version", version.value),
//      ("scala-version", scalaVersion.value),
//      (s"${moduleName.value}-dependency-tree", (compile / writeDependencyTree).value),
//      ("Library-Dependencies", libraryDependencies.value.mkString(", "))
//    )
//  ),
//  writeDependencyTree    := addDependencyTreeToMetaInf(
//    moduleName.value,
//    target.value,
//    scalaBinaryVersion.value,
//    (Compile / dependencyTree / asString).value,
//    apiProjectName
//  )
)

//lazy val scalaAssemblySetting = assembly / assemblyOption ~= {
//  _.withIncludeScala(false)
//}

//lazy val scalafmtSettings =
//  Seq(
//    scalafmtOnCompile     := true,
//    scalafmtTestOnCompile := true,
//    scalafmtVersion       := "1.2.0"
//  )

lazy val root = (project in file("."))
//  .settings(compile / unmanagedSourceDirectories := Nil)
  .aggregate(common)
  .aggregate(dbCommon)
  .aggregate(scalatraCommon)
  .aggregate(scalatraApi)
  .aggregate(playCommon)
  .aggregate(playApi)
  .aggregate(controller)
  .aggregate(service)
  .aggregate(handler)
  .aggregate(sparkCommon)
  .aggregate(flinkCommon)
  .aggregate(sparkBatch)
  .aggregate(sparkStreaming)
  .aggregate(scheduler)

lazy val common = (project in file("common"))
  .settings(commonSettings *)

lazy val dbCommon = (project in file("db-common"))
  .settings(commonSettings *)

lazy val scalatraCommon = (project in file("scalatra-common"))
  .settings(commonSettings *)

lazy val scalatraApi = (project in file("scalatra-api"))
  .settings(commonSettings *)

lazy val playCommon = (project in file("play-common"))
  .settings(commonSettings *)

lazy val playApi = (project in file("play-api"))
  .settings(commonSettings *)

lazy val controller = (project in file("controller"))
  .settings(commonSettings *)

lazy val service = (project in file("service"))
  .settings(commonSettings *)

lazy val handler = (project in file("handler"))
  .settings(commonSettings *)

lazy val sparkCommon = (project in file("spark-common"))
  .settings(commonSettings *)

lazy val flinkCommon = (project in file("flink-common"))
  .settings(commonSettings *)

lazy val sparkBatch = (project in file("spark-batch"))
  .settings(commonSettings *)

lazy val sparkStreaming = (project in file("spark-streaming"))
  .settings(commonSettings *)
//  .settings(update / aggregate := false)
//  .settings(libraryDependencies ++= scalatraCommonModuleDependencies)

lazy val scheduler = (project in file("scheduler"))
  .settings(commonSettings *)

//lazy val scalatraCommon = (project in file("scalatra-common"))
//  .settings(commonSettings: _*)
//  .settings(version := aipVersion)
//  .settings(update / aggregate := false)
//  .settings(libraryDependencies ++= scalatraCommonModuleDependencies)
//
//lazy val dbCommon = (project in file("db_common"))
//  .settings(commonSettings: _*)
//  .settings(version := aipVersion)
//  .settings(update / aggregate := false)
//  .settings(libraryDependencies ++= dbCommonModuleDependencies)
//
//lazy val api = (project in file(apiProjectName))
//  .settings(commonSettings *)
//  .settings(version := aipVersion)
//  .settings(coverageMinimumStmtTotal := 7.40)
//  .dependsOn(common % "compile->compile;test->test", scalatraCommon % "compile->compile;test->test")
//  .settings(update / aggregate := false)
//  .settings(libraryDependencies ++= apiModuleDependencies)
//  .settings(dependencyOverrides ++= Seq("org.scala-lang.modules" %% "scala-java8-compat" % "1.0.0"))
//  .settings(
//    excludeDependencies ++= Seq(
//      ExclusionRules.mortbayJetty,
//      ExclusionRules.eclipseJettyOrbit,
//      ExclusionRules.eclipseJettyAggregate,
//      ExclusionRules.activeMq,
//      ExclusionRules.leveldbjni
//    )
//  )
//  .enablePlugins(SbtWeb)
//  .enablePlugins(JettyPlugin)
//
//lazy val jobs = (project in file("jobs"))
//  .settings(commonSettings: _*)
//  .settings(version := aipVersion)
//  .settings(coverageMinimumStmtTotal := 20.23)
//  .dependsOn(common % "compile->compile;test->test")
//  .settings(update / aggregate := false)
//  .settings(exportJars := true)
//  .settings(libraryDependencies ++= jobModuleDependencies)
//
//lazy val common = (project in file(commonModuleName))
//  .settings(name := s"$modulePrefix-$commonModuleName")
//  .settings(commonSettings: _*)
//  .settings(version := aipVersion)
//  .settings(coverageMinimumStmtTotal := 17.94)
//  .settings(exportJars := true)
//  .settings(libraryDependencies ++= commonModuleDependencies)
//  .settings(publishOpts)
//  .dependsOn(dbCommon % "compile->compile;test->test")
//
//lazy val analyzers = (project in file("analyzers"))
//  .settings(commonSettings)
//  .settings(version := aipVersion)
//  .settings(coverageMinimumStmtTotal := 17.00)
//  .settings(libraryDependencies ++= analyzerModuleDependencies)
//  .settings(scalaAssemblySetting)
//  .dependsOn(common % "compile->compile;test->test")
//  .settings(update / aggregate := false)
//  .settings(exportJars := true)
//
//lazy val transformation = (project in file("transformation"))
//  .settings(commonSettings)
//  .settings(assembly / mainClass := Some("com.tookitaki.aip.transformation.Main"))
//  .settings(version := aipVersion)
//  .settings(coverageMinimumStmtTotal := 3.79)
//  .settings(libraryDependencies ++= transformationModuleDependencies)
//  .settings(scalaAssemblySetting)
//  .dependsOn(common % "compile->compile;test->test")
//  .settings(update / aggregate := false)
//  .settings(exportJars := true)
//
//lazy val connectors = (project in file("connectors"))
//  .settings(commonSettings: _*)
//  .settings(version := aipVersion)
//  .settings(coverageMinimumStmtTotal := 12.29)
//  .settings(libraryDependencies ++= connectorModuleDependencies)
//  .settings(scalaAssemblySetting)
//  .dependsOn(common % "compile->compile;test->test")
//  .settings(update / aggregate := false)
//  .settings(exportJars := true)
//
//lazy val persister = (project in file("persister"))
//  .settings(commonSettings: _*)
//  .settings(version := aipVersion)
//  .settings(coverageMinimumStmtTotal := 37.90)
//  .settings(libraryDependencies ++= persisterModuleDependencies)
//  .settings(scalaAssemblySetting)
//  .dependsOn(common % "compile->compile;test->test")
//  .settings(update / aggregate := false)
//  .settings(exportJars := true)
//
//lazy val streaming = (project in file("streaming"))
//  .settings(commonSettings: _*)
//  .settings(version := aipVersion)
//  .dependsOn(connectors % "compile->compile;test->test")
//  .dependsOn(persister % "compile->compile;test->test")
//  .dependsOn(transformation % "compile->compile;test->test")
//  .dependsOn(analyzers % "compile->compile;test->test")
//  .settings(coverageMinimumStmtTotal := 0)
//  .settings(libraryDependencies ++= streamingModuleDependencies)
//
//lazy val flink = (project in file("flink"))
//  .settings(commonSettings: _*)
//  .settings(version := aipVersion)
//  .settings(update / aggregate := false)
//  .dependsOn(common % "compile->compile;test->test")
//
//lazy val confmanager = (project in file("confmanager"))
//  .settings(commonSettings: _*)
//  .settings(version := aipVersion)
//  .settings(coverageMinimumStmtTotal := 37.90)
//  .settings(libraryDependencies ++= confmanagerModuleDependencies)
//  .settings(update / aggregate := false)
//  .settings(exportJars := true)
//  .settings(publishOpts)
//
//val writeDependencyTree = taskKey[String](
//  "This sbt task writes the dependency tree to the target directory of module and later it get included in jar"
//)
//
//addCommandAlias("publish-local", "; +clean; +compile; +publishLocal")
//addCommandAlias("publish-remote", "; +clean; +compile; +publish")
