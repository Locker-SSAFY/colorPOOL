package com.ssafy.socks.repository.magazine;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.socks.entity.magazine.Likes;
import com.ssafy.socks.entity.magazine.Magazine;
import com.ssafy.socks.entity.user.User;

@Repository
public interface LikesJpaRepository extends JpaRepository<Likes,Long> {
	Boolean existsByUserIdAndMagazineId(Long userId, Long magazineId);
	List<Likes> findByMagazineId(Long magazineId);
	void deleteByUserIdAndMagazineId(Long userId, Long magazineId);
}
