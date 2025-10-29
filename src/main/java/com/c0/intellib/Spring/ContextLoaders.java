package com.c0.intellib.Spring;

/*
Context Loader	                    Used For	            Typical Use Case
ClassPathXmlApplicationContext	    XML in classpath	    Legacy XML configuration
FileSystemXmlApplicationContext	    XML in file system	  Legacy, rarely used now
AnnotationConfigApplicationContext	Java config classes	  Modern Spring / Spring Boot
SpringApplication.run(...)	        Spring Boot	          Simplest, production-ready setup

SpringApplication.run(...) - Introduced in Spring Boot.
It does everything AnnotationConfigApplicationContext does plus a lot more:
- Starts the embedded web server (Tomcat/Jetty/Undertow)
- Sets up autoconfiguration
- Scans for components automatically
- Loads environment properties (application.yml, application.properties)
- Initializes logging, banners, etc.
So internally, SpringApplication.run(...) creates an ApplicationContext (often a subclass of
AnnotationConfigApplicationContext or AnnotationConfigServletWebServerApplicationContext),
but you don’t need to instantiate it yourself.
 */
public class ContextLoaders {
}
