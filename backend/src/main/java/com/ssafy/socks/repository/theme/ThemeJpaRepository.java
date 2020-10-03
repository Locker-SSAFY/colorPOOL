package com.ssafy.socks.repository.theme;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.socks.entity.color.Theme;

public interface ThemeJpaRepository extends JpaRepository<Theme,Long> {
}
