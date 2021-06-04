ThisBuild / scalaVersion     := "2.13.5"
ThisBuild / version          := "0.1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .settings(
    name := "Scala Snippets",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-stream" % "2.6.14",
      "org.apache.logging.log4j" % "log4j-slf4j-impl" % "2.14.1",
      "org.log4s" %% "log4s" % "1.8.2",
      "com.fasterxml.jackson.core" % "jackson-databind" % "2.12.3"
    )
)
