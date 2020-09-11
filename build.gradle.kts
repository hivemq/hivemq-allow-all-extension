plugins {
    id("com.hivemq.hivemq-extension-plugin")
    id("org.asciidoctor.jvm.convert")
}

group = "com.hivemq"
description = "HiveMQ Allow All Extension - all MQTT clients are authorized"

hivemqExtension {
    name = "Allow All Extension"
    author = "HiveMQ"
    mainClass = "com.hivemq.extensions.allowall.AllowAllMain"
    sdkVersion = "${property("hivemq-extension-sdk.version")}"
    priority = 0
    startPriority = 0
}

tasks.hivemqExtensionResources {
    from("LICENSE")
    from("README.adoc") { rename { "README.txt" } }
    from(tasks.asciidoctor)
}

val prepareAsciidocTask = tasks.register<Copy>("prepareAsciidoc") {
    from("README.adoc").into(buildDir.resolve("tmp/asciidoc"))
}
tasks.asciidoctor {
    dependsOn(prepareAsciidocTask)
    sourceDir(prepareAsciidocTask.get().outputs.files.asPath)
}
