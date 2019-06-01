import scala.languageFeature.experimental.macros

name := "practice10"

version := "0.1"

scalaVersion := "2.12.8"

lazy val root = (project in file("."))
  .enablePlugins(RunAcceptance)
  .settings(
    inConfig(AcceptanceTest)(
      Seq(acceptanceTestsPath := "exercises")
    )
  )

libraryDependencies += "io.monix" %% "monix" % "2.3.3"