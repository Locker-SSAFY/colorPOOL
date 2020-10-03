package com.ssafy.socks.repository.theme;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.socks.entity.images.ThemeImages;

public interface ThemeImagesJpaRepository extends JpaRepository<ThemeImages,Long> {
}
