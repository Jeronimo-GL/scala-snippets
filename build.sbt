ThisBuild / version          := "0.1.0-SNAPSHOT"

lazy val log4jVersion = "2.14.1"
lazy val sparkVersion = "3.0.0"


lazy val dependencies = new {
  val log4s            = "org.log4s" %% "log4s" % "1.8.2"
  val log4j_imps       = "org.apache.logging.log4j" % "log4j-slf4j-impl" % log4jVersion
  val log4jCore        = "org.apache.logging.log4j" % "log4j-core" % log4jVersion
  val log4jApi         = "org.apache.logging.log4j" % "log4j-api" % log4jVersion
  val log4jKube        = "org.apache.logging.log4j" % "log4j-kubernetes" % log4jVersion
  val akka             = "com.typesafe.akka" %% "akka-stream" % "2.6.14"
  val jackson_databind = "com.fasterxml.jackson.core" % "jackson-databind" % "2.12.3"
  val cats             = "org.typelevel" %% "cats-core" % "2.2.0"
  val spark            = "org.apache.spark" %% "spark-core" % sparkVersion// % "provided"
  val sparkSql         = "org.apache.spark" %% "spark-sql" % sparkVersion// % "provided"
  val bigDL            ="com.intel.analytics.zoo" % "analytics-zoo-bigdl_0.12.1-spark_3.0.0"// % "0.10.0"// % "provided"
}


lazy val scopt = project
  .in(file("modules/scopt"))
  .settings(
    name:="Scopt sample",
    fork:=true,
    scalaVersion:="2.13.5",
    libraryDependencies ++= Seq(
      "com.github.scopt" %% "scopt" % "4.0.1"
    ),
    version:="0.0.1"
  )


lazy val spark  = project
  .in(file("modules/spark"))
  .settings(
    name:="Spark samples",
    fork:= true,
    run / javaOptions ++= Seq(
      "-Dlog4j.debug=false",
      "-Dlog4j.configuration=log4j.properties"),
    scalaVersion:="2.12.15",
    libraryDependencies ++=Seq(
      dependencies.spark,
      dependencies.sparkSql
    ),
    version:="0.0.1"
  )

lazy val bigdl = project
  .in(file("modules/bigDL"))
  .settings(
    name:="BigDL samples",
    fork:= true,
    run / javaOptions ++= Seq(
      "-Dlog4j.debug=false",
      "-Dlog4j.configuration=log4j.properties"),
    scalaVersion:="2.11.12",
    libraryDependencies ++=Seq(
      "com.intel.analytics.zoo" % "analytics-zoo-bigdl_0.12.2-spark_2.4.3" % "0.10.0",
      "org.apache.spark" %% "spark-sql" % "2.4.3"
//      "org.apache.spark" %% "spark-mllib" % "2.4.3" %  "provided"
    ),
    version:="0.0.1"
  )

lazy val akka = project
  .in(file("modules/akka"))
  .settings(
    name :="Akka snippets",
    fork := true,
    scalaVersion := "2.13.5",
    libraryDependencies ++=Seq(
      dependencies.akka
    )
  )

lazy val logging = project
  .in(file("modules/logging"))
  .enablePlugins(DockerPlugin)
  .settings(
    name := "Logging",
    scalaVersion := "2.13.5",
    fork := true,
    libraryDependencies ++= Seq(
      dependencies.log4s,
      dependencies.log4j_imps,
      dependencies.log4jCore,
      dependencies.log4jApi,
      dependencies.log4jKube,
      dependencies.jackson_databind
    )
  )

lazy val cats = project
  .in(file("modules/cats"))
  .settings(
    name := "Cats snippets",
    scalaVersion := "2.13.5",
    libraryDependencies ++= Seq(
      dependencies.cats
    )
  )

lazy val logMDC = project
  .in(file("modules/logMDC"))
  .settings(
    name :="Log4s MDC",
    scalaVersion := "2.13.5",
    fork := true,
    libraryDependencies ++=Seq(
      dependencies.log4s,
      dependencies.log4j_imps,
      dependencies.jackson_databind
    )
  )


lazy val general = project
  .in(file("modules/general"))
  .settings(
    name := "Scala Snippets",
    scalaVersion := "2.13.5",
    fork := true,
    libraryDependencies ++= Seq(

    )
  )


