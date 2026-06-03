plugins {
    `java-library`
}

repositories {
    mavenCentral()
	maven("https://hub.spigotmc.org/nexus/content/groups/public/")
}

dependencies {
	compileOnly("org.spigotmc:spigot-api:1.16.5-R0.1-SNAPSHOT")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}
