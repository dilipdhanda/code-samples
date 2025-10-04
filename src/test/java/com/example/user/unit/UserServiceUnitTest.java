package com.example.user.unit;

import com.c0.user.User;
import com.c0.user.UserRepository;
import com.c0.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/*
When to use which?
= Mockito unit test:
Fast, runs in milliseconds.
Great for pure logic, verifying repository interactions.
= Spring Boot integration test:
Validates wiring + DB behavior.
Slower, but catches schema/config issues.
 */

// You mock UserRepository so you test only the logic in UserService (no DB, no Spring context).
@ExtendWith(MockitoExtension.class)
class UserServiceUnitTest {

  @Mock
  private UserRepository repository;   // mock dependency

  @InjectMocks
  private UserService service;         // class under test

  @Test
  void getUser_existingId_returnsUser() {
    User mockUser = new User("Alice");
    when(repository.findById(1L)).thenReturn(Optional.of(mockUser));

    User result = service.getUser(1L);

    assertThat(result.getName()).isEqualTo("Alice");
    verify(repository).findById(1L);
  }

  @Test
  void getUser_nonExistingId_throws() {
    when(repository.findById(99L)).thenReturn(Optional.empty());

    assertThrows(IllegalArgumentException.class,
      () -> service.getUser(99L));
  }

  @Test
  void createUser_callsSaveAndReturnsUser() {
    User saved = new User("Bob");
    when(repository.save(any(User.class))).thenReturn(saved);

    User result = service.createUser("Bob");

    assertThat(result.getName()).isEqualTo("Bob");
    verify(repository).save(any(User.class));
  }
}

