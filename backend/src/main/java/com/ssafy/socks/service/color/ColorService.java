package com.ssafy.socks.service.color;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.socks.advice.exception.CCommunicationException;
import com.ssafy.socks.entity.color.RecommendTheme;
import com.ssafy.socks.entity.color.SelectedColor;
import com.ssafy.socks.entity.color.Theme;
import com.ssafy.socks.repository.color.RecommendRepository;
import com.ssafy.socks.repository.color.SelectedColorJpaRepository;
import com.ssafy.socks.repository.theme.ThemeJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ColorService {
	private final ThemeJpaRepository themeJpaRepository;
	private final RecommendRepository recommendRepository;
	private final SelectedColorJpaRepository selectedColorJpaRepository;

	public List<Theme> getThemes(Long colorId) {
		SelectedColor selectedColor = selectedColorJpaRepository.findById(colorId).orElseThrow(CCommunicationException::new);
		RecommendTheme recommendTheme = recommendRepository.findById(selectedColor.getId()).orElseThrow(CCommunicationException::new);

		List<Theme> themes = new ArrayList<>();
		themes.add(themeJpaRepository.findById(recommendTheme.getThemeId1()).orElseThrow(CCommunicationException::new));
		themes.add(themeJpaRepository.findById(recommendTheme.getThemeId2()).orElseThrow(CCommunicationException::new));
		themes.add(themeJpaRepository.findById(recommendTheme.getThemeId3()).orElseThrow(CCommunicationException::new));
		themes.add(themeJpaRepository.findById(recommendTheme.getThemeId4()).orElseThrow(CCommunicationException::new));
		themes.add(themeJpaRepository.findById(recommendTheme.getThemeId5()).orElseThrow(CCommunicationException::new));
		themes.add(themeJpaRepository.findById(recommendTheme.getThemeId6()).orElseThrow(CCommunicationException::new));
		themes.add(themeJpaRepository.findById(recommendTheme.getThemeId7()).orElseThrow(CCommunicationException::new));
		themes.add(themeJpaRepository.findById(recommendTheme.getThemeId8()).orElseThrow(CCommunicationException::new));
		themes.add(themeJpaRepository.findById(recommendTheme.getThemeId9()).orElseThrow(CCommunicationException::new));
		themes.add(themeJpaRepository.findById(recommendTheme.getThemeId10()).orElseThrow(CCommunicationException::new));

		return themes;
	}
}
