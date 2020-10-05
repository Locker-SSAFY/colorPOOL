package com.ssafy.socks.repository.color;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.socks.entity.color.RecommendTheme;

@Repository
public interface RecommendRepository extends JpaRepository<RecommendTheme,Long> {
}
