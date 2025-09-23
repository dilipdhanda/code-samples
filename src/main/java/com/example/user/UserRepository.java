// src/main/java/com/example/user/UserRepository.java
package com.example.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
