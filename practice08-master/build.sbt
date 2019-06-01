import sbt.CrossVersion
import scala.languageFeature.experimental.macros

name := "practice"

version := "0.1"

scalaVersion := "2.12.8"

lazy val root = (project in file("."))
  .enablePlugins(RunAcceptance)
  .settings(
    inConfig(AcceptanceTest)(
      Seq(acceptanceTestsPath := "exercises")
    )
  )
  .settings(libraryDependencies ++= Seq(kindProjector, macroParaside))

lazy val commonSettings = Seq(
  name := "practice",
  version := "0.1",
  scalaVersion := "2.12.8",
  scalacOptions in Compile ++= Seq(
    "-encoding",
    "UTF-8",
    "-target:jvm-1.8",
    "-deprecation",
    "-feature",
    "-unchecked",
    "-Xlog-reflective-calls",
    "-Xlint",
    "-Yrangepos",
    "-language:higherKinds",
    "-language:implicitConversions",
    "-language:experimental.macros"
  ),
  javacOptions in Compile ++= Seq(
    "-source",
    "1.8",
    "-target",
    "1.8",
    "-Xlint:unchecked",
    "-Xlint:deprecation"
  ),
  javaOptions in Test ++= Seq("-Xms256m", "-Xmx2g"),
  javaOptions in run ++= Seq("-Xms256m", "-Xmx2g")
)

val macroParaside = compilerPlugin(
  "org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
val kindProjector = compilerPlugin(
  "org.spire-math" % "kind-projector" % "0.9.4" cross CrossVersion.binary)

libraryDependencies ++= Seq(kindProjector, macroParaside)
libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.14.0"
libraryDependencies += "com.github.mpilquist" %% "simulacrum" % "0.10.0"
libraryDependencies += "org.scalameta" %% "scalameta" % "4.1.0"