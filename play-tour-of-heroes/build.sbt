name := """play-tour-of-heroes"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"
resolvers += "typesafe" at "http://repo.typesafe.com/typesafe/releases/"
resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "5.1.35",
  "com.typesafe.play" %% "play-slick" % "2.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "2.0.0",
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
)

