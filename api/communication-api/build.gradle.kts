/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
  id("com.cognifide.aet.java-conventions")
}

dependencies {
  testImplementation("org.hamcrest:hamcrest-all:1.3")
  testImplementation("com.googlecode.zohhak:zohhak:1.1.1")

  compileOnly("com.google.guava:guava:25.1-jre")
  compileOnly("org.apache.activemq:activemq-osgi:5.15.2")
  compileOnly("org.apache.commons:commons-lang3:3.7")
  compileOnly("javax.validation:validation-api:1.1.0.Final")
  compileOnly("org.hibernate:hibernate-validator:4.3.2.Final")
  compileOnly("org.jboss.logging:jboss-logging:3.3.2.Final")
  compileOnly("commons-io:commons-io:2.6")
  compileOnly("com.google.code.gson:gson:2.8.5")
}

description = "AET :: API :: Communication API"
