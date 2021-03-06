package com.ssafy.socks.service.color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.ssafy.socks.advice.exception.CCommunicationException;
import com.ssafy.socks.advice.exception.CUserNotFoundException;
import com.ssafy.socks.entity.color.Color;
import com.ssafy.socks.entity.color.ColorHistory;
import com.ssafy.socks.entity.color.RecommendColor;
import com.ssafy.socks.entity.color.RecommendTheme;
import com.ssafy.socks.entity.color.SelectedColor;
import com.ssafy.socks.entity.color.Theme;
import com.ssafy.socks.entity.user.User;
import com.ssafy.socks.repository.color.ColorHistoryJpaRepository;
import com.ssafy.socks.repository.color.RecommendColorJpaRepository;
import com.ssafy.socks.repository.color.RecommendRepository;
import com.ssafy.socks.repository.color.SelectedColorJpaRepository;
import com.ssafy.socks.repository.theme.ThemeJpaRepository;
import com.ssafy.socks.repository.user.UserJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ColorService {
	private final ThemeJpaRepository themeJpaRepository;
	private final RecommendRepository recommendRepository;
	private final SelectedColorJpaRepository selectedColorJpaRepository;
	private final ColorHistoryJpaRepository colorHistoryJpaRepository;
	private final RecommendColorJpaRepository recommendColorJpaRepository;
	private final UserJpaRepository userJpaRepository;

	public List<Theme> getThemes(Long colorId) {
		RecommendTheme recommendTheme = recommendRepository.findById(colorId).orElseThrow(CCommunicationException::new);

		List<Theme> themes = new ArrayList<>();
		themes.add(themeJpaRepository.findById(recommendTheme.getThemeId1() + 1).orElseThrow(CCommunicationException::new));
		themes.add(themeJpaRepository.findById(recommendTheme.getThemeId2() + 1).orElseThrow(CCommunicationException::new));
		themes.add(themeJpaRepository.findById(recommendTheme.getThemeId3() + 1).orElseThrow(CCommunicationException::new));
		themes.add(themeJpaRepository.findById(recommendTheme.getThemeId4() + 1).orElseThrow(CCommunicationException::new));
		themes.add(themeJpaRepository.findById(recommendTheme.getThemeId5() + 1).orElseThrow(CCommunicationException::new));
		themes.add(themeJpaRepository.findById(recommendTheme.getThemeId6() + 1).orElseThrow(CCommunicationException::new));
		themes.add(themeJpaRepository.findById(recommendTheme.getThemeId7() + 1).orElseThrow(CCommunicationException::new));
		themes.add(themeJpaRepository.findById(recommendTheme.getThemeId8() + 1).orElseThrow(CCommunicationException::new));
		themes.add(themeJpaRepository.findById(recommendTheme.getThemeId9() + 1).orElseThrow(CCommunicationException::new));
		themes.add(themeJpaRepository.findById(recommendTheme.getThemeId10() +1).orElseThrow(CCommunicationException::new));

		return themes;
	}

	public Long getRecommend(String userEmail) {
		User user = userJpaRepository.findByEmail(userEmail).orElseThrow(CUserNotFoundException::new);
		ColorHistory colorHistory = colorHistoryJpaRepository.findTop1ByUserId(user.getId()).orElseThrow(CCommunicationException::new);
		RecommendColor recommendColor = recommendColorJpaRepository.findById(colorHistory.getSelectedColorId()).orElseThrow(CCommunicationException::new);

		return getRecommendColor(recommendColor);
	}

	private Long getRecommendColor(RecommendColor recommendColor) {
		Random random = new Random();
		int recommendIdx = random.nextInt(4);

		switch (recommendIdx) {
			case 0:
				return recommendColor.getColorId1();
			case 1:
				return recommendColor.getColorId2();
			case 2:
				return recommendColor.getColorId3();
			case 3:
				return recommendColor.getColorId4();
			case 4:
				return recommendColor.getColorId5();
		}

		return null;
	}

	public Theme getColors(Long themeId) {
		return themeJpaRepository.findById(themeId).orElseThrow(CCommunicationException::new);
	}
}
