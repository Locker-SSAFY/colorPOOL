package com.ssafy.socks.repository.theme;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.socks.entity.color.Theme;
import com.ssafy.socks.entity.images.ThemeImages;

public interface ThemeImagesJpaRepository extends JpaRepository<ThemeImages,Long> {
	List<ThemeImages> findAllByTheme(Theme theme);
}
