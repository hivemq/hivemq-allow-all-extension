plugins {
    id("com.hivemq.extension")
    id("com.github.hierynomus.license")
    id("io.github.sgtsilvio.gradle.defaults")
    id("org.asciidoctor.jvm.convert")
}

group = "com.hivemq.extensions"
description = "HiveMQ Allow All Extension - all MQTT clients are authorized"

hivemqExtension {
    name.set("Allow All Extension")
    author.set("HiveMQ")
    priority.set(0)
    startPriority.set(0)
    sdkVersion.set("${property("hivemq-extension-sdk.version")}")
}

val prepareAsciidoc by tasks.registering(Sync::class) {
    from("README.adoc").into({ temporaryDir })
}

tasks.asciidoctor {
    dependsOn(prepareAsciidoc)
    sourceDir(prepareAsciidoc.map { it.destinationDir })
}

hivemqExtension.resources {
    from("LICENSE")
    from("README.adoc") { rename { "README.txt" } }
    from(tasks.asciidoctor)
}

license {
    header = rootDir.resolve("HEADER")
    mapping("java", "SLASHSTAR_STYLE")
}
