plugins {
    id("com.hivemq.extension")
    id("org.asciidoctor.jvm.convert")
}

group = "com.hivemq"
description = "HiveMQ Allow All Extension - all MQTT clients are authorized"

hivemqExtension {
    name = "Allow All Extension"
    author = "HiveMQ"
    priority = 0
    startPriority = 0
    mainClass = "com.hivemq.extensions.allowall.AllowAllMain"
    sdkVersion = "${property("hivemq-extension-sdk.version")}"
}

tasks.hivemqExtensionResources {
    from("LICENSE")
    from("README.adoc") { rename { "README.txt" } }
    from(tasks.asciidoctor)
}

val prepareAsciidocTask = tasks.register<Sync>("prepareAsciidoc") {
    from("README.adoc").into(buildDir.resolve("tmp/asciidoc"))
}
tasks.asciidoctor {
    dependsOn(prepareAsciidocTask)
    sourceDir(prepareAsciidocTask.get().outputs.files.asPath)
}

tasks.prepareHivemqHome {
    hivemqFolder.set("/Users/sgiebl/Projects/hivemq-4/test-hivemq/hivemq-4.4.1")
}

tasks.runHivemqWithExtension {
    debugOptions {
        enabled.set(true)
    }
}
