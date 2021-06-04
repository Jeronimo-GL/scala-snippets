ThisBuild / scalaVersion     := "2.13.5"
ThisBuild / version          := "0.1.0-SNAPSHOT"

lazy val dependencies = new {
  val log4s            = "org.log4s" %% "log4s" % "1.8.2"
  val log4j_imps       = "org.apache.logging.log4j" % "log4j-slf4j-impl" % "2.14.1"
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
  .settings(
    name := "Logging",
    libraryDependencies ++= Seq(
      dependencies.log4s,
      dependencies.log4j_imps,
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

lazy val root = (project in file("general"))
  .settings(
    name := "Scala Snippets",
    libraryDependencies ++= Seq(

    )
)
