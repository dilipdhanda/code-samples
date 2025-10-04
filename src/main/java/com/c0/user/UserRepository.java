// src/main/java/com/example/user/UserRepository.java
package com.c0.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
