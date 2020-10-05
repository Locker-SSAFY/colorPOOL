package com.ssafy.socks.repository.magazine;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.socks.entity.magazine.Likes;
import com.ssafy.socks.entity.magazine.Magazine;
import com.ssafy.socks.entity.user.User;

@Repository
public interface LikesJpaRepository extends JpaRepository<Likes,Long> {
	Boolean existsByUserAndMagazine(User user, Magazine magazine);
	void deleteByUserAndMagazine(User user, Magazine magazine);
}
