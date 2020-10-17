package com.ssafy.socks.repository.color;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.socks.entity.color.RecommendColor;

@Repository
public interface RecommendColorJpaRepository extends JpaRepository<RecommendColor,Long> {
}
