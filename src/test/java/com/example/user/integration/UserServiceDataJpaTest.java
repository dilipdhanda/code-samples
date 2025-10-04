package com.example.user.integration;

import com.c0.user.User;
import com.c0.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.*;

// Option A — Slice test with @DataJpaTest + @Import(UserService)
// This keeps only JPA bits and your service in the context (fastest).
@DataJpaTest
@Import(UserService.class) // bring the service into this JPA slice
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // use H2
class UserServiceDataJpaTest {

  // TODO - AnnotationConfigContextLoaderUtils -- Could not detect default configuration classes for test class
//  [com.example.user.integration.UserServiceDataJpaTest]: UserServiceDataJpaTest does not declare any static, non-private,
//  non-final, nested classes annotated with @Configuration.
//java.lang.IllegalStateException: Unable to find a @SpringBootConfiguration, you need to use @ContextConfiguration
// or @SpringBootTest(classes=...) with your test

  @Autowired private UserService userService;

  @Test
  void createAndFetchUser() {
    User created = userService.createUser("Alice");

    assertThat(created.getId()).isNotNull();
    assertThat(created.getName()).isEqualTo("Alice");

    User fetched = userService.getUser(created.getId());
    assertThat(fetched.getName()).isEqualTo("Alice");
  }

  @Test
  void getUser_nonExisting_throws() {
    assertThatThrownBy(() -> userService.getUser(999L))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("User not found");
  }
}
