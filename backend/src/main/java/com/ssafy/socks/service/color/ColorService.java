package com.ssafy.socks.service.color;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.socks.advice.exception.CCommunicationException;
import com.ssafy.socks.entity.color.Theme;
import com.ssafy.socks.repository.color.SelectedColorJpaRepository;
import com.ssafy.socks.repository.theme.ThemeJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ColorService {
	private final ThemeJpaRepository themeJpaRepository;
	private final SelectedColorJpaRepository selectedColorJpaRepository;

	public List<Theme> getThemes(Long colorId) {
		return themeJpaRepository.findBySelectedColor(selectedColorJpaRepository.findById(colorId).orElseThrow(
			CCommunicationException::new));
	}
}
