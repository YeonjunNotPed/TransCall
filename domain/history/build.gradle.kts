plugins {
    alias(libs.plugins.youhajun.jvm.library)
}

dependencies {
    implementation(projects.core.model)
    implementation(libs.javax.inject)
}