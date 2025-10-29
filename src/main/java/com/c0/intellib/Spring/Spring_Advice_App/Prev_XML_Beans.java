package com.c0.intellib.Spring.Spring_Advice_App;

/*
1. Old-Style (XML Configuration) In older Spring apps, everything lived in an XML file:

<bean id="jpaDao" class="com.spring.repository.JpaDao"/>
<bean id="customService" class="com.spring.service.CustomServiceImpl">
    <property name="repository" ref="jpaDao"/>
</bean>

Beans defined manually
Dependencies wired with <property> and <ref>
Tedious, error-prone, and not type-safe
✅ Still works
❌ Verbose and outdated for modern projects

🚀 2. Modern-Style (Annotation / Java Config) Now we use annotations or Java configuration classes — no XML needed.

Example using @Component and @Autowired
@Repository
public class JpaDao { }
@Service
public class CustomServiceImpl {
    private final JpaDao repository;
    @Autowired
    public CustomServiceImpl(JpaDao repository) {
        this.repository = repository;
    }
}

Then a simple configuration class (or just Spring Boot):
@Configuration
@ComponentScan("com.spring")
public class AppConfig { }

And in your main class:
@SpringBootApplication
public class MyApp {
    public static void main(String[] args) {
        SpringApplication.run(MyApp.class, args);
    }
}

✅ No XML
✅ Automatic bean scanning
✅ Type-safe dependency injection
✅ Cleaner, IDE-friendly, and more maintainable
 */
public class Prev_XML_Beans {
}
