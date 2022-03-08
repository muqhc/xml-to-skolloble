import io.github.muqhc.xmltoskolloble.xmlToSkolloble
import org.dom4j.io.SAXReader

fun main() {
    val _xml = """
        |<?xml version="1.0" encoding="UTF-8"?>
        |<project xmlns="http://maven.apache.org/POM/4.0.0"
        |         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        |         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
        |    <modelVersion>4.0.0</modelVersion>
        |
        |    <groupId>io.github.muqhc</groupId>
        |    <artifactId>sample</artifactId>
        |    <version>1.0.0</version>
        |
        |    <properties>
        |        <kotlin.version>1.6.10</kotlin.version>
        |        <main.class>io.github.muqhc.sample.Sample</main.class>
        |    </properties>
        |
        |    <dependencies>
        |        <dependency>
        |            <groupId>org.jetbrains.kotlin</groupId>
        |            <artifactId>kotlin-stdlib</artifactId>
        |            <version>${"$"}{kotlin.version}</version>
        |        </dependency>
        |        <dependency>
        |            <groupId>org.jetbrains.kotlin</groupId>
        |            <artifactId>kotlin-test-junit</artifactId>
        |            <version>${"$"}{kotlin.version}</version>
        |            <scope>test</scope>
        |        </dependency>
        |    </dependencies>
        |
        |    <build>
        |        <sourceDirectory>${"$"}{project.basedir}/src/main/kotlin</sourceDirectory>
        |        <testSourceDirectory>${"$"}{project.basedir}/src/test/kotlin</testSourceDirectory>
        |        <plugins>
        |            <plugin>
        |                <groupId>org.jetbrains.kotlin</groupId>
        |                <artifactId>kotlin-maven-plugin</artifactId>
        |                <version>${"$"}{kotlin.version}</version>
        |
        |                <executions>
        |                    <execution>
        |                        <id>compile</id>
        |                        <goals>
        |                            <goal>compile</goal>
        |                        </goals>
        |                    </execution>
        |
        |                    <execution>
        |                        <id>test-compile</id>
        |                        <goals>
        |                            <goal>test-compile</goal>
        |                        </goals>
        |                    </execution>
        |                </executions>
        |            </plugin>
        |            <plugin>
        |                <groupId>org.apache.maven.plugins</groupId>
        |                <artifactId>maven-jar-plugin</artifactId>
        |                <version>2.6</version>
        |                <configuration>
        |                    <archive>
        |                        <manifest>
        |                            <addClasspath>true</addClasspath>
        |                            <mainClass>${"$"}{main.class}</mainClass>
        |                        </manifest>
        |                    </archive>
        |                </configuration>
        |            </plugin>
        |        </plugins>
        |    </build>
        |</project>
        |""".trimMargin()
    xmlToSkolloble(_xml).let(::println)
}