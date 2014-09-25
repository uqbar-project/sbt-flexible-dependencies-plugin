name := "sbt-flexible-dependencies"

description := "An SBT plugin that allows the creation of projects that automatically handles library dependencies as project dependencies if they are cloned on the workspace"

sbtPlugin := true

///////////////////////////////////////////////////////////////////////////////////////////////////

unmanagedSourceDirectories in Compile := Seq((scalaSource in Compile).value)

unmanagedSourceDirectories in Test := Seq()

scalacOptions += "-feature"