plugins {
    id("java")
    id("fabric-loom") version "1.1-SNAPSHOT"
}

group = "me.eumel"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    minecraft("com.mojang:minecraft:1.19.4")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:0.14.19")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}
