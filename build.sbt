name := "CreditCardValidator"

version := "0.1"

scalaVersion := "2.12.7"

triggeredMessage := Watched.clearWhenTriggered

resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"

libraryDependencies ++= Seq(
  "org.scalactic" %% "scalactic" % "3.0.5" ,
  "org.scalatest" %% "scalatest" % "3.0.5" % "test" ,
  "org.pegdown"    % "pegdown"   % "1.6.0" % "test"
)

testOptions in Test ++=
  Seq(
    Tests.Argument(TestFrameworks.ScalaTest, "-oSD"),
    Tests.Argument(TestFrameworks.ScalaTest, "-h", "target/test-reports")
  )

addCommandAlias("testc", ";clean;coverage;test;coverageReport")