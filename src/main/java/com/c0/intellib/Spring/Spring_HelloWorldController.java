package com.c0.intellib.Spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/*
ModelAndView is mainly for MVC (server-side rendered web apps)
- ModelAndView belongs to the Spring Web MVC era (pre-Spring Boot REST dominance).
- It’s used when: The server returns HTML pages (e.g., JSP, Thymeleaf, Freemarker)
- You’re building a web app with controllers + views, not APIs
- This was common for monolithic web apps (Spring MVC, JSP, etc.).
- ModelAndView isn’t used in REST-style microservices anymore.
- It’s still around in legacy or hybrid apps (especially internal admin dashboards),
  but most modern Spring Boot APIs skip it entirely, one of the biggest architectural shifts in the Spring
  ecosystem (and backend development overall) over the past decade
- Old way (pre–microservices era): MVC model
  Apps were monolithic and server-rendered.
  The controller generated HTML views (via JSP, Thymeleaf, etc.).

= Modern way: JSON + API-first
- Now, even for UI communication:
  The backend exposes REST (or GraphQL) APIs returning JSON.
  The UI (React, Angular, Vue, iOS, Android) consumes the JSON and renders its own view.
✅ Backend is headless (view-agnostic)
✅ Frontend can be anything — web, mobile, desktop
✅ Easier versioning, testing, scaling
REST APIs don’t use ModelAndView
- In RESTful or microservice architectures, the backend doesn’t render views — it returns JSON or XML payloads.
- @RestController = @Controller + @ResponseBody
- It directly returns data, not a ModelAndView
- The front-end (React, Angular, Vue, etc.) or another microservice consumes that JSON
 */

@Controller
// I answered wrong: @RequestMapping can be used twice - the code below works fine
@RequestMapping("/welcome")     // class-level mapping
public class Spring_HelloWorldController {
  @RequestMapping(method = RequestMethod.GET)   // method-level mapping
  public ModelAndView helloWorld() {
    ModelAndView model = new ModelAndView("HelloWorldPage");
    model.addObject("msg", "hello world");
    return model;
  }
}

