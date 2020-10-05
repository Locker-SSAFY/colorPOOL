package com.ssafy.socks.repository.magazine;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.socks.entity.magazine.Magazine;
import com.ssafy.socks.entity.user.User;

public interface MagazineJpaRepository extends JpaRepository<Magazine,Long> {
	Optional<Magazine> findByUser(User user);
}
