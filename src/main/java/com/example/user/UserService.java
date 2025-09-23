// src/main/java/com/example/user/UserService.java
package com.example.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

  private final UserRepository repo;

  public UserService(UserRepository repo) {
    this.repo = repo;
  }

  public User getUser(Long id) {
    return repo.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("User not found: " + id));
  }

  public User createUser(String name) {
    return repo.save(new User(name));
  }
}
