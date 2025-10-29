package com.c0.intellib.Spring;

import com.c0.SekoPdfLabelServerApp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
<servlet>
<servlet-name>clothing</servlet-name>
<servlet-class>
org.springframework.web.servlet.DispatcherServlet
</servlet-class>
</servlet>
<servlet-mapping>
<servlet-name>clothing</servlet-name>
<url-pattern>*.htm</url-pattern>
</servlet-mapping>
Above is old legacy way. this is the pre–Spring Boot, XML-configured way. In modern apps you
  almost never touch web.xml or per-servlet XML files.
contextConfigLocation is correct for legacy XML setups—but today, most teams use Java config or
  Spring Boot and skip the XML entirely.

How it’s done now (typical patterns)
1) Spring Boot (most common)
- No web.xml.
- One DispatcherServlet auto-registered.
- Map routes with @RestController / @Controller.
- Configure extras via Java config or application.properties.
Optional: customize the DispatcherServlet mapping in application.properties:
# default is "/"
server.servlet.context-path=/clothing
spring.mvc.servlet.path=/api   # DispatcherServlet prefix (optional)

Evolution of Spring Web App Startup
Era	                                      Typical setup	               Example	    Status
🧓  Old school   (Spring 2.x – 3.x)	      web.xml + *-servlet.xml	     XML config	  ✅ Still works (legacy only)
🧱  Transitional (Spring 4.x pre-Boot)	  WebApplicationInitializer +  Pure Java,   🟡 Bridge era
                                          @Configuration		           no XML          used when migrating off XML
🚀  Modern       (Spring Boot 1.x – 3.x)	@SpringBootApplication 	     Auto-config, ✅ Standard now
                                          + application.yml/properties embedded Tomcat
If someone says “I’m using Spring MVC (not Boot)” or they’re migrating a legacy XML app, (2) is the recommended
Java-based approach in that specific non-Boot world.
But in your context — modern microservices, Boot apps, JSON APIs — you can safely ignore it.
If your project uses Spring Boot, you don’t need WebApplicationInitializer or web.xml
 */
@SpringBootApplication
public class Spring_App {
  public static void main(String[] args) {
    SpringApplication.run(SekoPdfLabelServerApp.class, args);
  }
}
