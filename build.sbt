ThisBuild / scalaVersion     := "2.13.5"
ThisBuild / version          := "0.1.0-SNAPSHOT"

lazy val log4jVersion = "2.14.1"

lazy val dependencies = new {
  val log4s            = "org.log4s" %% "log4s" % "1.8.2"
  val log4j_imps       = "org.apache.logging.log4j" % "log4j-slf4j-impl" % log4jVersion
  val log4jCore        = "org.apache.logging.log4j" % "log4j-core" % log4jVersion
  val log4jApi         = "org.apache.logging.log4j" % "log4j-api" % log4jVersion
  val log4jKube        = "org.apache.logging.log4j" % "log4j-kubernetes" % log4jVersion
  val akka             = "com.typesafe.akka" %% "akka-stream" % "2.6.14"
  val jackson_databind = "com.fasterxml.jackson.core" % "jackson-databind" % "2.12.3"
  val cats             = "org.typelevel" %% "cats-core" % "2.2.0"
}


lazy val akka = project
  .in(file("akka"))
  .settings(
    name :="Akka snippets",
    libraryDependencies ++=Seq(
      dependencies.akka
    )
  )

lazy val logging = project
  .in(file("logging"))
  .enablePlugins(DockerPlugin)
  .settings(
    name := "Logging",
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
  .in(file("cats"))
  .settings(
    name := "Cats snippets",
    libraryDependencies ++= Seq(
      dependencies.cats
    )
  )

lazy val logMDC = project
  .in(file("logMDC"))
  .settings(
    name :="Log4s MDC",
    libraryDependencies ++=Seq(
      dependencies.log4s,
      dependencies.log4j_imps,
      dependencies.jackson_databind
    )
  )


lazy val root = (project in file("general"))
  .settings(
    name := "Scala Snippets",
    libraryDependencies ++= Seq(

    )
  )


