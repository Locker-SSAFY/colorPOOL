package com.ssafy.socks.service.magazine;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
import com.ssafy.socks.model.magazine.LikesModel;
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
	Logger logger = LoggerFactory.getLogger(this.getClass());

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
		LocalDateTime currDate = LocalDateTime.now();
		User user = userJpaRepository.findByEmail(magazineModel.getEmail()).orElseThrow(CUserNotFoundException::new);

		Magazine magazine = Magazine.builder()
			.userId(user.getId())
			.magazineName(magazineModel.getMagazineName())
			.themeId(magazineModel.getThemeId())
			.selectedId(magazineModel.getSelectedColorId())
			.createdDate(currDate)
			.build();

		logger.info("----------------- magazine -----------------");
		logger.info("user : " + magazine.getUser().getEmail());
		logger.info("themeId : " + magazine.getThemeId());
		logger.info("current Date : " + magazine.getCreatedDate());
		logger.info("----------------- magazine -----------------");

		ColorHistory colorHistory = new ColorHistory();
		colorHistory.setSelectedColor(selectedColorJpaRepository.findById(magazineModel.getSelectedColorId()).orElseThrow(CCommunicationException::new));
		colorHistory.setUser(user);
		colorHistoryJpaRepository.save(colorHistory);

		magazineJpaRepository.save(magazine);

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

		Magazine findMagazine = magazineJpaRepository.findByMagazineName(magazineModel.getMagazineName()).orElseThrow(CCommunicationException::new);
		for (Contents contents : contentsList) {
			contents.setMagazineId(findMagazine.getId());
			contentsJpaRepository.save(contents);
		}
	}

	public List<MagazineModel> getMagazinesByUser(String userEmail) {
		List<Magazine> magazineList = magazineJpaRepository.findByUser(userJpaRepository.findByEmail(userEmail).orElseThrow(CUserNotFoundException::new));
		List<MagazineModel> magazineModels = new ArrayList<>();

		for(Magazine magazine : magazineList) {
			List<Contents> contentsList = contentsJpaRepository.findByMagazineId(magazine.getId());
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

	/**
	 * 인기도 순으로 잡지 조회
	 * @return
	 */
	public List<MagazineModel> getMagazines() {
		List<Magazine> magazineList = magazineRepository.findMagazineOrderByLikes();
		List<MagazineModel> magazineModels = new ArrayList<>();

		for(Magazine magazine : magazineList) {
			List<Contents> contentsList = contentsJpaRepository.findByMagazineId(magazine.getId());
			List<ContentsModel> contentsModelList = new ArrayList<>();

			for(Contents contents : contentsList) {
				ContentsModel contentsModel = ContentsModel.builder()
					.url(contents.getUrl())
					.template(contents.getTemplate())
					.subText(contents.getSubText())
					.question(contents.getQuestion())
					.mainText(contents.getMainText())
					.answer(contents.getAnswer())
					.build();

				contentsModelList.add(contentsModel);
			}

			MagazineModel magazineModel = MagazineModel.builder()
				.email(magazine.getUser().getEmail())
				.contents(contentsModelList)
				.userNickname(magazine.getUser().getNickname())
				.selectedColorId(magazine.getSelectedId())
				.themeId(magazine.getThemeId())
				.magazineName(magazine.getMagazineName())
				.build();

			magazineModels.add(magazineModel);
		}

		return magazineModels;
	}

	public Magazine getMagazine(Long magazineId) {
		return magazineJpaRepository.findById(magazineId).orElseThrow(CCommunicationException::new);
	}

	public LikesModel setLikes(Long magazineId, String userEmail) {
		User user = userJpaRepository.findByEmail(userEmail).orElseThrow(CUserNotFoundException::new);
		Optional<Likes> likesOptional = likesJpaRepository.findByUserIdAAndMagazineId(user.getId(),magazineId);

		LikesModel likesModel = new LikesModel();
		if(likesOptional.isPresent()) {
			likesJpaRepository.save(
				Likes.builder()
					.magazineId(magazineId)
					.userId(user.getId())
					.build()
			);
			likesModel.setMagazineId(magazineId);
			likesModel.setUserId(user.getId());
			likesModel.setClicked(false);
		} else {
			likesJpaRepository.deleteByUserIdAndMagazineId(user.getId(), magazineId);
			likesModel.setMagazineId(magazineId);
			likesModel.setUserId(user.getId());
			likesModel.setClicked(true);
		}
		return likesModel;
	}

	public List<Magazine> getBookmarkMagazines(String userEmail) {
		User user = userJpaRepository.findByEmail(userEmail).orElseThrow(CUserNotFoundException::new);
		return bookmarkRepository.findBookmarkRepository(user);
	}
}
