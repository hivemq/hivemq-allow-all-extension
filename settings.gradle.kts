rootProject.name = "hivemq-allow-all-extension"

pluginManagement {
    plugins {
        id("com.hivemq.extension") version "${extra["plugin.hivemq-extension.version"]}"
        id("org.asciidoctor.jvm.convert") version "${extra["plugin.asciidoctor.version"]}"
    }
}