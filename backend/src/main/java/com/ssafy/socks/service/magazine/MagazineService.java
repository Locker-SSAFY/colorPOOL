package com.ssafy.socks.service.magazine;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ssafy.socks.advice.exception.CCommunicationException;
import com.ssafy.socks.advice.exception.CUserNotFoundException;
import com.ssafy.socks.entity.color.ColorHistory;
import com.ssafy.socks.entity.magazine.Contents;
import com.ssafy.socks.entity.magazine.Likes;
import com.ssafy.socks.entity.magazine.Magazine;
import com.ssafy.socks.entity.user.User;
import com.ssafy.socks.model.magazine.MagazineModel;
import com.ssafy.socks.repository.color.ColorHistoryJpaRepository;
import com.ssafy.socks.repository.color.SelectedColorJpaRepository;
import com.ssafy.socks.repository.magazine.BookmarkRepository;
import com.ssafy.socks.repository.magazine.LikesJpaRepository;
import com.ssafy.socks.repository.magazine.MagazineJpaRepository;
import com.ssafy.socks.repository.magazine.MagazineRepository;
import com.ssafy.socks.repository.theme.ThemeJpaRepository;
import com.ssafy.socks.repository.user.UserJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MagazineService {
	private final MagazineJpaRepository magazineJpaRepository;
	private final UserJpaRepository userJpaRepository;
	private final MagazineRepository magazineRepository;
	private final LikesJpaRepository likesJpaRepository;
	private final BookmarkRepository bookmarkRepository;
	private final ThemeJpaRepository themeJpaRepository;
	private final ColorHistoryJpaRepository colorHistoryJpaRepository;
	private final SelectedColorJpaRepository selectedColorJpaRepository;

	public void saveMagazine(MagazineModel magazineModel) {
		Logger logger = LoggerFactory.getLogger(this.getClass());

		List<Contents> contentsList = new ArrayList<>();
		Magazine magazine = new Magazine();

		for (int i = 0; i < magazineModel.getContents().size(); i++) {
			contentsList.add(
				Contents.builder()
					.url(magazineModel.getContents().get(i).getUrl())
					.answer(magazineModel.getContents().get(i).getAnswer())
					.mainText(magazineModel.getContents().get(i).getMainText())
					.subText(magazineModel.getContents().get(i).getSubText())
					.template(magazineModel.getContents().get(i).getTemplate())
					.question(magazineModel.getContents().get(i).getQuestion())
					.magazine(magazine)
					.build());
		}

		logger.info("----------------- contents -----------------");
		for (Contents contents : contentsList) {
			logger.info(contents.getMainText());
		}
		logger.info("----------------- contents -----------------");

		LocalDateTime currDate = LocalDateTime.now();

		magazine = Magazine.builder()
			.user(userJpaRepository.findByEmail(magazineModel.getEmail()).orElseThrow(CUserNotFoundException::new))
			.contents(contentsList)
			.themeId(magazineModel.getThemeId())
			.createdDate(currDate)
			.build();

		ColorHistory colorHistory = new ColorHistory();
		colorHistory.setSelectedColor(selectedColorJpaRepository.findById(magazineModel.getSelectedColorId()).orElseThrow(CCommunicationException::new));
		colorHistory.setUser(userJpaRepository.findByEmail(magazineModel.getEmail()).orElseThrow(CUserNotFoundException::new));
		colorHistoryJpaRepository.save(colorHistory);

		magazineJpaRepository.save(magazine);
	}

	public List<Magazine> getMagazinesByUser(String userEmail) {
		return magazineJpaRepository.findByUser(userJpaRepository.findByEmail(userEmail).orElseThrow(CUserNotFoundException::new));
	}

	public List<Magazine> getMagazines() {
		return magazineRepository.findMagazineOrderByLikes();
	}

	public Magazine getMagazine(Long magazineId) {
		return magazineJpaRepository.findById(magazineId).orElseThrow(CCommunicationException::new);
	}

	public void setLikes(Long magazineId, String userEmail) {
		User user = userJpaRepository.findByEmail(userEmail).orElseThrow(CUserNotFoundException::new);
		Magazine magazine = magazineJpaRepository.findById(magazineId).orElseThrow(CCommunicationException::new);
		boolean isSetLike = likesJpaRepository.existsByUserAndMagazine(user,magazine);
		if(isSetLike) likesJpaRepository.deleteByUserAndMagazine(user,magazine);
		else likesJpaRepository.save(
			Likes.builder()
				.magazine(magazine)
				.user(user)
				.build()
		);
	}

	public List<Magazine> getBookmarkMagazines(String userEmail) {
		User user = userJpaRepository.findByEmail(userEmail).orElseThrow(CUserNotFoundException::new);
		return bookmarkRepository.findBookmarkRepository(user);
	}
}
