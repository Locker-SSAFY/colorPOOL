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
import com.ssafy.socks.entity.magazine.Bookmark;
import com.ssafy.socks.entity.magazine.Contents;
import com.ssafy.socks.entity.magazine.Likes;
import com.ssafy.socks.entity.magazine.Magazine;
import com.ssafy.socks.entity.user.User;
import com.ssafy.socks.model.magazine.ContentsModel;
import com.ssafy.socks.model.magazine.MagazineModel;
import com.ssafy.socks.repository.color.ColorHistoryJpaRepository;
import com.ssafy.socks.repository.color.SelectedColorJpaRepository;
import com.ssafy.socks.repository.magazine.BookmarkRepository;
import com.ssafy.socks.repository.magazine.ContentsJpaRepository;
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
	private final ContentsJpaRepository contentsJpaRepository;

	public void saveMagazine(MagazineModel magazineModel) {
		Logger logger = LoggerFactory.getLogger(this.getClass());

		List<Contents> contentsList = new ArrayList<>();
		for (int i = 0; i < magazineModel.getContents().size(); i++) {
			Contents contents = Contents.builder()
				.url(magazineModel.getContents().get(i).getUrl())
				.answer(magazineModel.getContents().get(i).getAnswer())
				.mainText(magazineModel.getContents().get(i).getMainText())
				.subText(magazineModel.getContents().get(i).getSubText())
				.template(magazineModel.getContents().get(i).getTemplate())
				.question(magazineModel.getContents().get(i).getQuestion())
				.build();
			contentsList.add(contents);
		}

		LocalDateTime currDate = LocalDateTime.now();
		User user = userJpaRepository.findByEmail(magazineModel.getEmail()).orElseThrow(CUserNotFoundException::new);

		Magazine magazine = Magazine.builder()
			.user(user)
			.contents(contentsList)
			.magazineName(magazineModel.getMagazineName())
			.themeId(magazineModel.getThemeId())
			.selectedId(magazineModel.getSelectedColorId())
			.createdDate(currDate)
			.build();

		logger.info("----------------- magazine -----------------");
		logger.info("user : " + magazine.getUser().getEmail());
		for (Contents contents : contentsList) logger.info(contents.getMainText());
		logger.info("themeId : " + magazine.getThemeId());
		logger.info("current Date : " + magazine.getCreatedDate());
		logger.info("----------------- magazine -----------------");

		Likes likes = new Likes();
		likes.setMagazine(magazine);
		likes.setUser(user);
		likesJpaRepository.save(likes);

		Bookmark bookmark = new Bookmark();
		bookmark.setMagazineBookmark(magazine);
		bookmark.setUser(user);

		magazine.setLikes(likesJpaRepository.findAllByMagazine(magazine));
		magazine.setBookmarks(bookmarkRepository.findBookmarkByMagazine(magazine));

		ColorHistory colorHistory = new ColorHistory();
		colorHistory.setSelectedColor(selectedColorJpaRepository.findById(magazineModel.getSelectedColorId()).orElseThrow(CCommunicationException::new));
		colorHistory.setUser(userJpaRepository.findByEmail(magazineModel.getEmail()).orElseThrow(CUserNotFoundException::new));
		colorHistoryJpaRepository.save(colorHistory);

		magazineJpaRepository.save(magazine);

/*		magazine = magazineJpaRepository.findFirstByUser(user).orElseThrow(CCommunicationException::new);
		for (Contents contents : contentsList) {
			contents.setMagazine(magazine);
			contentsJpaRepository.save(contents);
		}*/
	}

	public List<MagazineModel> getMagazinesByUser(String userEmail) {
		List<Magazine> magazineList = magazineJpaRepository.findByUser(userJpaRepository.findByEmail(userEmail).orElseThrow(CUserNotFoundException::new));
		List<MagazineModel> magazineModels = new ArrayList<>();

		for(Magazine magazine : magazineList) {
			List<Contents> contentsList = magazine.getContents();
			List<ContentsModel> contentsModels = new ArrayList<>();

			for(Contents contents : contentsList) {
				ContentsModel contentsModel = ContentsModel.builder()
					.answer(contents.getAnswer())
					.mainText(contents.getMainText())
					.question(contents.getQuestion())
					.subText(contents.getSubText())
					.template(contents.getTemplate())
					.url(contents.getUrl())
					.build();

				contentsModels.add(contentsModel);
			}

			MagazineModel magazineModel = MagazineModel.builder()
				.email(magazine.getUser().getEmail())
				.magazineName(magazine.getMagazineName())
				.themeId(magazine.getThemeId())
				.selectedColorId(magazine.getSelectedId())
				.userNickname(magazine.getUser().getNickname())
				.contents(contentsModels)
				.build();

			magazineModels.add(magazineModel);
		}

		return magazineModels;
	}

	public List<MagazineModel> getMagazines() {
		List<Magazine> magazineList = magazineJpaRepository.findAll();
		List<MagazineModel> magazineModels = new ArrayList<>();

		for(Magazine magazine : magazineList) {
			List<Contents> contentsList = magazine.getContents();
			List<ContentsModel> contentsModels = new ArrayList<>();

			for(Contents contents : contentsList) {
				ContentsModel contentsModel = ContentsModel.builder()
					.answer(contents.getAnswer())
					.mainText(contents.getMainText())
					.question(contents.getQuestion())
					.subText(contents.getSubText())
					.template(contents.getTemplate())
					.url(contents.getUrl())
					.build();

				contentsModels.add(contentsModel);
			}

			MagazineModel magazineModel = MagazineModel.builder()
				.email(magazine.getUser().getEmail())
				.magazineName(magazine.getMagazineName())
				.themeId(magazine.getThemeId())
				.selectedColorId(magazine.getSelectedId())
				.userNickname(magazine.getUser().getNickname())
				.contents(contentsModels)
				.build();

			magazineModels.add(magazineModel);
		}

		return magazineModels;
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
