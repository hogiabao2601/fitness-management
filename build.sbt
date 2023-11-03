import bao.ho.build.profiles.ProfileResolver.profile.*
name := "fitness-management"

val ProjectVersion = "1.0.0"
val ScalaVersion   = "2.12.17"

lazy val commonSettings = Seq(
  organization  := "bao.ho",
  version       := ProjectVersion,
  scalaVersion  := ScalaVersion,
  scalacOptions := Seq("-deprecation", "-feature", "-encoding", "utf8", "-Ypartial-unification")
)

lazy val root = (project in file("."))
  .aggregate(common)
  .aggregate(dbCommon)
  .aggregate(mysqlCommon)
  .aggregate(hbaseCommon)
  .aggregate(model)
  .aggregate(apiCommon)
  .aggregate(scalatraApi)
  .aggregate(playApi)
  .aggregate(controller)
  .aggregate(service)
  .aggregate(handler)
  .aggregate(hadoopCommon)
  .aggregate(sparkCommon)
  .aggregate(flinkCommon)
  .aggregate(sparkBatch)
  .aggregate(sparkStreaming)
  .aggregate(scheduler)

lazy val common = (project in file("common"))
  .settings(commonSettings *)
  .settings(update / aggregate := false)
  .settings(libraryDependencies ++= commonModuleDependencies)

lazy val model = (project in file("model"))
  .settings(commonSettings *)
  .settings(libraryDependencies ++= modelModuleDependencies)

lazy val dbCommon = (project in file("db-common"))
  .settings(commonSettings *)
  .dependsOn(common % "compile->compile;test->test")
  .dependsOn(model % "compile->compile;test->test")
  .settings(libraryDependencies ++= dbCommonModuleDependencies)

lazy val scalatraApi = (project in file("scalatra-api"))
  .settings(commonSettings *)
  .dependsOn(apiCommon % "compile->compile;test->test")
  .settings(update / aggregate := false)
  .settings(libraryDependencies ++= scalatraApiModuleDependencies)
  .enablePlugins(SbtWeb)
  .enablePlugins(JettyPlugin)

lazy val playApi = (project in file("play-api"))
  .settings(commonSettings *)
  .dependsOn(apiCommon % "compile->compile;test->test")
  .settings(libraryDependencies ++= playApiModuleDependencies)

lazy val controller = (project in file("controller"))
  .settings(commonSettings *)
  .dependsOn(handler % "compile->compile;test->test")
  .settings(libraryDependencies ++= controllerModuleDependencies)

lazy val service = (project in file("service"))
  .settings(commonSettings *)
  .dependsOn(mysqlCommon % "compile->compile;test->test")
  .dependsOn(hbaseCommon % "compile->compile;test->test")
  .settings(libraryDependencies ++= serviceModuleDependencies)

lazy val handler = (project in file("handler"))
  .settings(commonSettings *)
  .dependsOn(service % "compile->compile;test->test")
  .settings(libraryDependencies ++= handlerModuleDependencies)

lazy val hadoopCommon = (project in file("hadoop-common"))
  .settings(commonSettings *)
  .dependsOn(mysqlCommon % "compile->compile;test->test")
  .settings(libraryDependencies ++= hadoopCommonModuleDependencies)

lazy val sparkCommon = (project in file("spark-common"))
  .settings(commonSettings *)
  .dependsOn(hadoopCommon % "compile->compile;test->test")
  .settings(libraryDependencies ++= sparkCommonModuleDependencies)

lazy val flinkCommon = (project in file("flink-common"))
  .settings(commonSettings *)
  .dependsOn(hadoopCommon % "compile->compile;test->test")
  .settings(libraryDependencies ++= flinkCommonModuleDependencies)

lazy val sparkBatch = (project in file("spark-batch"))
  .settings(commonSettings *)
  .dependsOn(sparkCommon % "compile->compile;test->test")
  .settings(libraryDependencies ++= sparkBatchModuleDependencies)

lazy val sparkStreaming = (project in file("spark-streaming"))
  .settings(commonSettings *)
  .dependsOn(sparkCommon % "compile->compile;test->test")
  .settings(libraryDependencies ++= sparkStreamingModuleDependencies)

lazy val scheduler = (project in file("scheduler"))
  .settings(commonSettings *)
  .dependsOn(mysqlCommon % "compile->compile;test->test")
  .settings(libraryDependencies ++= schedulerModuleDependencies)

lazy val mysqlCommon = (project in file("mysql-common"))
  .settings(commonSettings *)
  .dependsOn(dbCommon % "compile->compile;test->test")
  .settings(libraryDependencies ++= mysqlCommonModuleDependencies)

lazy val hbaseCommon = (project in file("hbase-common"))
  .settings(commonSettings *)
  .dependsOn(dbCommon % "compile->compile;test->test")
  .settings(libraryDependencies ++= hbaseCommonModuleDependencies)

lazy val apiCommon = (project in file("api-common"))
  .settings(commonSettings *)
  .dependsOn(controller % "compile->compile;test->test")
  .settings(libraryDependencies ++= apiCommonModuleDependencies)
