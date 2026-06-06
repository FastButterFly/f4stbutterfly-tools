plugins {
    `java-library`
}

repositories {
    mavenCentral()
	maven("https://hub.spigotmc.org/nexus/content/groups/public/")
	maven("https://repo.extendedclip.com/releases/")
}

dependencies {
	compileOnly("org.spigotmc:spigot-api:1.16.5-R0.1-SNAPSHOT")
	compileOnly("me.clip:placeholderapi:2.12.2")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}
