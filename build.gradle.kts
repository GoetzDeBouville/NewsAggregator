import io.gitlab.arturbosch.detekt.Detekt

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath(libs.hilt.android.gradle.plugin)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.detekt)
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
}

tasks.withType<Detekt>().configureEach {
    parallel = true
    autoCorrect = false
    disableDefaultRuleSets = false
    buildUponDefaultConfig = true

    jvmTarget = JavaVersion.valueOf(libs.versions.java.get()).toString()

    setSource(files(projectDir))
    include("**/*.kt")
    include("**/*.kts")
    exclude("**/resources/**")
    exclude("**/build/**")

    reports {
        xml.required.set(false)
        html.required.set(true)
        txt.required.set(true)
        sarif.required.set(false)
        md.required.set(false)
    }

    config.setFrom(files(project.rootDir.resolve("conf/custom-detekt.yml")))
}

tasks.register<Detekt>("detektAll") {
    description = "Runs detekt over the whole code base"
    group = "verification"

    parallel = true
    autoCorrect = false
    disableDefaultRuleSets = false
    buildUponDefaultConfig = false

    jvmTarget = JavaVersion.valueOf(libs.versions.java.get()).toString()
    setSource(files(projectDir))
    include("**/*.kt", "**/*.kts")
    exclude("**/resources/**", "**/build/**")

    config.setFrom(files(rootProject.file("conf/detekt.yml")))

    reports {
        xml.required.set(true)
        html.required.set(true)
        txt.required.set(true)
    }
}

tasks.register<Detekt>("detektFormat") {
    description = "Auto-corrects the code base using Detekt formatting rules"
    group = "formatting"

    parallel = true
    autoCorrect = true
    disableDefaultRuleSets = false
    buildUponDefaultConfig = false

    jvmTarget = JavaVersion.valueOf(libs.versions.java.get()).toString()
    setSource(files(projectDir))
    include("**/*.kt", "**/*.kts")
    exclude("**/resources/**", "**/build/**")

    config.setFrom(files(rootProject.file("conf/detekt.yml")))

    reports {
        xml.required.set(false)
        html.required.set(false)
        txt.required.set(true)
    }
}

dependencies {
    add("detekt", libs.staticAnalysis.detektCli)
    add("detektPlugins", libs.staticAnalysis.detektFormatting)
    add("detektPlugins", libs.staticAnalysis.detektLibraries)
}