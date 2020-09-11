pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }

    plugins {
        id("com.hivemq.hivemq-extension-plugin") version "${extra["plugin.hivemq-extension.version"]}"
        id("org.asciidoctor.jvm.convert") version "${extra["plugin.asciidoctor.version"]}"
    }
}

rootProject.name = "hivemq-allow-all-extension"
