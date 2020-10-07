package com.ssafy.socks.repository.color;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.socks.entity.color.ColorHistory;
import com.ssafy.socks.entity.user.User;

@Repository
public interface ColorHistoryJpaRepository extends JpaRepository<ColorHistory,Long> {
	Optional<ColorHistory> findTop1ByUserId(Long userId);
}
