package com.ssafy.socks.repository.theme;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.socks.entity.color.SelectedColor;
import com.ssafy.socks.entity.color.Theme;

public interface ThemeJpaRepository extends JpaRepository<Theme,Long> {
	List<Theme> findBySelectedColor(SelectedColor selectedColor);
}
