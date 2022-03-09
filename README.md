# xml-to-skolloble
simple library to convert .xml to .skolloble

## build.gradle.kts
```kotlin
repositories {
    mavenCentral()
}

dependencies {
    implementation("io.github.muqhc:xml-to-skolloble:1.0.0") //Version 1 (with dom4j)
    implementation("io.github.muqhc:xml-to-skolloble:2.0.0") //Version 2 (without dom4j) (is not implemented yet)
}
```

## [Example](src/test/kotlin/Test.kt)

# Notice

Version 1 is depend on [dom4j](https://github.com/dom4j/dom4j).

If you are going to use Version 1, read [dom4j license](https://github.com/dom4j/dom4j/blob/master/LICENSE).

Or you can use Version 2 future if you want to use without [dom4j](https://github.com/dom4j/dom4j).
