package org.uqbar.sbt.flexibledependencies

import sbt._
import Keys._

object FlexibleDependenciesPlugin extends Plugin {
	lazy val FDProject = FlexibleDependencyProject

	object FlexibleDependencyProject {
		def apply(dependencies: ModuleID*) = {
			val (projectDeps, libraryDeps) = dependencies.partition{ dependency => file(s"../${dependency.name}").exists }
			val baseProject = project
				.in(file("."))
				.copy(settings = Defaults.coreDefaultSettings ++ Seq(libraryDependencies ++= libraryDeps))

			(baseProject /: projectDeps){ (project, dependency) => project dependsOn RootProject(file(s"../${dependency.name}")) }
		}
	}
}