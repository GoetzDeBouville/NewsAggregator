pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "NewsAggregator"
include(":app")
include(":uikit")
include(":core:data:dto")
include(":core:domain:models")
include(":core:data:network")
include(":feature:article:presentation")
include(":feature:newslist:presentation")
include(":feature:newslist:data")
include(":core:resources")
include(":core:utils")
include(":feature:newslist:di")
include(":core:data:di")
include(":core:presentation:nav")
include(":feature:newslist:domain:api")
include(":feature:newslist:domain:impl")
include(":feature:newslist:domain:di")
include(":core:presentation:utils")
include(":core:data:db")
