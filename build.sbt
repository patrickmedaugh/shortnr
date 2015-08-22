name := "just-play-scala"

version := "1.0-SNAPSHOT"

lazy val root = project.in(file(".")).enablePlugins(PlayScala)

scalaVersion := "2.10.4"

fork in run := true
