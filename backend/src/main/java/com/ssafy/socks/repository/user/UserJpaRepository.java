package com.ssafy.socks.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.socks.entity.user.User;

public interface UserJpaRepository extends JpaRepository<User,Long> {
	Optional<User> findById(Long id);
	Optional<User> findByEmail(String email);
}
