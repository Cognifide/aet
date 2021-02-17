plugins {
    id("com.cognifide.aet.java-conventions")
	id("net.idlestate.gradle-duplicate-classes-check")
    id("biz.aQute.bnd.builder")
}

val seleniumVersion = "3.8.1"
dependencies {
    implementation("org.seleniumhq.selenium:jetty-repacked:7.6.1.1")
    implementation("org.seleniumhq.selenium:selenium-java:${seleniumVersion}")
    implementation("org.seleniumhq.selenium:selenium-api:${seleniumVersion}")
    implementation("org.seleniumhq.selenium:selenium-chrome-driver:${seleniumVersion}")
    implementation("org.seleniumhq.selenium:selenium-firefox-driver:${seleniumVersion}")
    implementation("org.seleniumhq.selenium:selenium-htmlunit-driver:2.52.0")
    implementation("org.seleniumhq.selenium:selenium-ie-driver:${seleniumVersion}")
    implementation("org.seleniumhq.selenium:selenium-remote-driver:${seleniumVersion}")
    implementation("org.seleniumhq.selenium:selenium-support:${seleniumVersion}")
    implementation("org.seleniumhq.selenium:selenium-server:${seleniumVersion}")
    implementation("org.apache.commons:commons-exec:1.3")
    implementation("com.sun.jna:jna:3.0.9")
    implementation("net.java.dev.jna:jna:3.3.0")
    implementation("net.java.dev.jna:platform:3.5.2")
}

tasks.jar {
    manifest {
        attributes(
            Pair("Bundle-Vendor", "Cognifide Ltd."),
            Pair("Export-Package", "org.openqa.*;version=\"${seleniumVersion}\""),
            Pair(
                "Import-Package",
                "com.beust.jcommander;resolution:=optional;version='[1.48,2)',com.beust.jcommander.converters;resolution:=optional;version='[1.48,2)',com.gargoylesoftware.htmlunit.javascript.host;resolution:=optional,com.gargoylesoftware.htmlunit.javascript.host.dom;resolution:=optional,com.gargoylesoftware.htmlunit.javascript.host.event;resolution:=optional,com.gargoylesoftware.htmlunit.javascript.host.html;resolution:=optional,javax.servlet;resolution:=optional;version='[3.1,4)',javax.servlet.http;resolution:=optional;version='[3.1,4)',org.eclipse.jetty.client;resolution:=optional;version='[9.4,10)',org.eclipse.jetty.client.security;resolution:=optional,org.eclipse.jetty.client.webdav;resolution:=optional,org.eclipse.jetty.continuation;resolution:=optional,org.eclipse.jetty.http;resolution:=optional;version='[9.4,10)',org.eclipse.jetty.http.gzip;resolution:=optional,org.eclipse.jetty.io;resolution:=optional;version='[9.4,10)',org.eclipse.jetty.io.bio;resolution:=optional,org.eclipse.jetty.io.nio;resolution:=optional,org.eclipse.jetty.jmx;resolution:=optional,org.eclipse.jetty.security;resolution:=optional,org.eclipse.jetty.security.authentication;resolution:=optional,org.eclipse.jetty.server;resolution:=optional,org.eclipse.jetty.server.bio;resolution:=optional,org.eclipse.jetty.server.handler;resolution:=optional,org.eclipse.jetty.server.handler.jmx;resolution:=optional,org.eclipse.jetty.server.jmx;resolution:=optional,org.eclipse.jetty.server.nio;resolution:=optional,org.eclipse.jetty.server.session;resolution:=optional,org.eclipse.jetty.server.session.jmx;resolution:=optional,org.eclipse.jetty.server.ssl;resolution:=optional,org.eclipse.jetty.servlet;resolution:=optional,org.eclipse.jetty.servlet.api;resolution:=optional,org.eclipse.jetty.servlet.jmx;resolution:=optional,org.eclipse.jetty.servlet.listener;resolution:=optional,org.eclipse.jetty.servlets;resolution:=optional,org.eclipse.jetty.util;resolution:=optional;version='[9.4,10)',org.eclipse.jetty.util.ajax;resolution:=optional,org.eclipse.jetty.util.component;resolution:=optional;version='[9.4,10)',org.eclipse.jetty.util.log;resolution:=optional;version='[9.4,10)',org.eclipse.jetty.util.resource;resolution:=optional;version='[9.4,10)',org.eclipse.jetty.util.security;resolution:=optional;version='[9.4,10)',org.eclipse.jetty.util.ssl;resolution:=optional;version='[9.4,10)',org.eclipse.jetty.util.statistic;resolution:=optional;version='[9.4,10)',org.eclipse.jetty.util.thread;resolution:=optional;version='[9.4,10)',org.eclipse.jetty.webapp;resolution:=optional,org.eclipse.jetty.xml;resolution:=optional;version='[9.4,10)',org.mortbay.log;resolution:=optional,org.mortbay.util.ajax;resolution:=optional,org.seleniumhq.jetty7.jmx;resolution:=optional,org.seleniumhq.jetty9.security;resolution:=optional,org.seleniumhq.jetty9.server;resolution:=optional,org.seleniumhq.jetty9.servlet;resolution:=optional,org.seleniumhq.jetty9.util.security;resolution:=optional,org.seleniumhq.jetty9.util.thread;resolution:=optional,net.bytebuddy;resolution:=optional;version='[1.7,2)',net.bytebuddy.description.annotation;resolution:=optional;version='[1.7,2)',net.bytebuddy.dynamic;resolution:=optional;version='[1.7,2)',net.bytebuddy.implementation;resolution:=optional;version='[1.7,2)',net.bytebuddy.matcher;resolution:=optional;version='[1.7,2)',org.openqa.selenium.safari;resolution:=optional,org.openqa.selenium.security;resolution:=optional,com.gargoylesoftware.htmlunit,com.gargoylesoftware.htmlunit.html,com.gargoylesoftware.htmlunit.javascript,com.gargoylesoftware.htmlunit.util,com.google.common.base;version='[25.1,26)',com.google.common.cache;version='[25.1,26)',com.google.common.collect;version='[25.1,26)',com.google.common.io;version='[25.1,26)',com.google.common.net;version='[25.1,26)',com.google.common.primitives;version='[25.1,26)',com.google.common.reflect;version='[25.1,26)',com.google.common.util.concurrent;version='[25.1,26)',com.google.gson;version='[2.8,3)',com.google.gson.annotations;version='[2.8,3)',com.google.gson.reflect;version='[2.8,3)',com.google.gson.stream;version='[2.8,3)',javax.imageio,javax.management,javax.naming,javax.net,javax.net.ssl,javax.security.auth,javax.security.cert,javax.sql,javax.swing,javax.swing.text,javax.xml.namespace,javax.xml.parsers,javax.xml.xpath,net.jcip.annotations,net.sourceforge.htmlunit.corejs.javascript,org.apache.http,org.apache.http.auth,org.apache.http.client,org.apache.http.client.config,org.apache.http.client.methods,org.apache.http.config,org.apache.http.conn,org.apache.http.conn.routing,org.apache.http.conn.socket,org.apache.http.conn.ssl,org.apache.http.entity,org.apache.http.impl.client,org.apache.http.impl.conn,org.apache.http.message,org.apache.http.protocol,org.apache.http.util,org.ietf.jgss,org.openqa.grid.common;version='[3.8,4)',org.openqa.grid.common.exception;version='[3.8,4)',org.openqa.grid.internal;version='[3.8,4)',org.openqa.grid.internal.exception;version='[3.8,4)',org.openqa.grid.internal.listeners;version='[3.8,4)',org.openqa.grid.internal.utils;version='[3.8,4)',org.openqa.grid.internal.utils.configuration;version='[3.8,4)',org.openqa.grid.internal.utils.configuration.converters;version='[3.8,4)',org.openqa.grid.internal.utils.configuration.validators;version='[3.8,4)',org.openqa.grid.selenium.node;version='[3.8,4)',org.openqa.grid.shared;version='[3.8,4)',org.openqa.grid.web;version='[3.8,4)',org.openqa.grid.web.servlet;version='[3.8,4)',org.openqa.grid.web.servlet.beta;version='[3.8,4)',org.openqa.grid.web.servlet.handler;version='[3.8,4)',org.openqa.grid.web.utils;version='[3.8,4)',org.openqa.selenium;version='[3.8,4)',org.openqa.selenium.firefox;version='[3.8,4)',org.openqa.selenium.firefox.internal;version='[3.8,4)',org.openqa.selenium.html5;version='[3.8,4)',org.openqa.selenium.interactions;version='[3.8,4)',org.openqa.selenium.interactions.internal;version='[3.8,4)',org.openqa.selenium.internal;version='[3.8,4)',org.openqa.selenium.io;version='[3.8,4)',org.openqa.selenium.json;version='[3.8,4)',org.openqa.selenium.logging;version='[3.8,4)',org.openqa.selenium.logging.profiler;version='[3.8,4)',org.openqa.selenium.mobile;version='[3.8,4)',org.openqa.selenium.net;version='[3.8,4)',org.openqa.selenium.remote;version='[3.8,4)',org.openqa.selenium.remote.html5;version='[3.8,4)',org.openqa.selenium.remote.http;version='[3.8,4)',org.openqa.selenium.remote.internal;version='[3.8,4)',org.openqa.selenium.remote.mobile;version='[3.8,4)',org.openqa.selenium.remote.server;version='[3.8,4)',org.openqa.selenium.remote.server.commandhandler;version='[3.8,4)',org.openqa.selenium.remote.server.handler;version='[3.8,4)',org.openqa.selenium.remote.server.handler.html5;version='[3.8,4)',org.openqa.selenium.remote.server.handler.interactions;version='[3.8,4)',org.openqa.selenium.remote.server.handler.interactions.touch;version='[3.8,4)',org.openqa.selenium.remote.server.handler.internal;version='[3.8,4)',org.openqa.selenium.remote.server.handler.mobile;version='[3.8,4)',org.openqa.selenium.remote.server.jmx;version='[3.8,4)',org.openqa.selenium.remote.server.log;version='[3.8,4)',org.openqa.selenium.remote.server.rest;version='[3.8,4)',org.openqa.selenium.remote.server.xdrpc;version='[3.8,4)',org.openqa.selenium.remote.service;version='[3.8,4)',org.openqa.selenium.remote.session;version='[3.8,4)',org.openqa.selenium.support;version='[3.8,4)',org.openqa.selenium.support.events;version='[3.8,4)',org.openqa.selenium.support.events.internal;version='[3.8,4)',org.openqa.selenium.support.pagefactory;version='[3.8,4)',org.openqa.selenium.support.pagefactory.internal;version='[3.8,4)',org.openqa.selenium.support.ui;version='[3.8,4)',org.slf4j,org.slf4j.helpers,org.slf4j.spi,org.w3c.css.sac,org.w3c.dom,org.xml.sax,org.xml.sax.helpers"
            ),
            Pair("Embed-Directory", "OSGI-INF/lib"),
            Pair("Embed-Transitive", "false")
        )
    }
}

description = "AET :: OSGi Dependencies :: Selenium"
