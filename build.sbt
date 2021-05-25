ThisBuild / scalaVersion     := "2.13.5"
ThisBuild / version          := "0.1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .settings(
    name := "Scala Snippets",
    libraryDependencies ++= Seq("com.typesafe.akka" %% "akka-stream" % "2.6.14" )
)
