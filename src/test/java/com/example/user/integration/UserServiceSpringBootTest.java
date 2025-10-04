package com.example.user.integration;

import com.c0.user.User;
import com.c0.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

// Use this when your service needs more Spring beans than JPA (e.g., events, mappers).
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // forces H2
class UserServiceSpringBootTest {

  // TODO - AnnotationConfigContextLoaderUtils -- Could not detect default configuration classes for test class
//  [com.example.user.UserServiceDataJpaTest]: UserServiceDataJpaTest does not declare any static, non-private,
//  non-final, nested classes annotated with @Configuration.
//java.lang.IllegalStateException: Unable to find a @SpringBootConfiguration, you need to use @ContextConfiguration
// or @SpringBootTest(classes=...) with your test

  @Autowired private UserService userService;

  @Test
  void createAndFetchUser_fullContext() {
    User u = userService.createUser("Bob");
    assertThat(userService.getUser(u.getId()).getName()).isEqualTo("Bob");
  }
}

