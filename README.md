﻿SBT: Flexible Dependencies Plugin [![Build Status](https://travis-ci.org/uqbar-project/sbt-flexible-dependencies-plugin.svg?branch=master)](https://travis-ci.org/uqbar-project/sbt-flexible-dependencies-plugin)
=================================

Sometimes you want to work on a project and its dependencies at the same time, but they are not related closely enough
as to be considered part of the same super-project. Of course, you could define these as *project dependencies*, but
then maybe not all the developers want to have *all* these projects checked-out in order to be able to work, specially
in open-source projects, where many people is involved.

This *SBT* plugin allows the creation of projects that automatically treats *library dependencies* as
*project dependencies* if they are cloned on the same workspace.

Setup
-----

To include this plugin in your *SBT* build, just add the following line to your `project/plugins.sbt` file, or your
global *SBT* configuration:

```scala
addSbtPlugin("org.uqbar" % "sbt-flexible-dependencies" % "latest.integration")
```

Usage
-----

To create a project with flexible dependencies just define it in your `build.sbt` as follows:

```scala
lazy val myProject = FDProject(
	"some.dependency" %% "example1" % "1.2.3",
    "some.other.dependency" % "example2" % "[2.2,)" % "test"
)
```

The project created will handle the given dependencies as *Project Dependencies* if there is a project of the
same name cloned on the workspace (`../` folder); otherwise the dependency will be handled as *Library Dependencies*.

So, with the following folder structure, `"some.dependency" %% "example1" % "1.2.3"` would be treated as a
*Project Dependency*, while `"some.other.dependency" % "example2" % "[2.2,)" % "test"` would be treated as a
*Library Dependency*.

```
myWorkspace
 + myProject
 |  + src
 |  |  + ...
 |  + build.sbt
 |  + project
 |     + plugins.sbt
 + example1
 	+ ...
```

The generated project will be equivalent as the generated by the following code:

```scala
lazy val example1 = file("../example1")
lazy val myProject = project in file(".") dependsOn example1

libraryDependencies += "some.other.dependency" % "example2" % "[2.2,)" % "test"
```

Contributions
-------------

Yes, please! Pull requests are always welcome, just try to keep it small and clean.

License
-------

This code is open source software licensed under the [LGPL v3 License](https://www.gnu.org/licenses/lgpl.html) by [The Uqbar Foundation](http://www.uqbar-project.org/). Feel free to use it accordingly.
