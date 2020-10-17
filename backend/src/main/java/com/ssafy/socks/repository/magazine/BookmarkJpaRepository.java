package com.ssafy.socks.repository.magazine;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.socks.entity.magazine.Bookmark;

public interface BookmarkJpaRepository extends JpaRepository<Bookmark, Long> {
	Optional<Bookmark> findByUserIdAndMagazineId(Long userId, Long magazineId);
	List<Bookmark> findByUserId(Long userId);
}
