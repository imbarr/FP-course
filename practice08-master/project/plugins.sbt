resolvers += Resolver.bintrayIvyRepo("mikkka", "sbt-plugins")

addSbtPlugin("name.mtkachev" %% "sbt-acceptance-plugin" % "0.1")
addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.10.0")
addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)