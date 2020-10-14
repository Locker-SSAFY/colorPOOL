package com.ssafy.socks.repository.magazine;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.socks.entity.magazine.Likes;

@Repository
public interface LikesJpaRepository extends JpaRepository<Likes,Long> {
	Optional<Likes> findByUserIdAndMagazineId(Long userId, Long magazineId);
	List<Likes> findByMagazineId(Long magazineId);
	List<Likes> findByUserId(Long id);
}
