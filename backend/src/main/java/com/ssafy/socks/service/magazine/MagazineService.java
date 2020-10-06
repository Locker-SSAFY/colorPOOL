package com.ssafy.socks.service.magazine;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.socks.advice.exception.CCommunicationException;
import com.ssafy.socks.advice.exception.CUserNotFoundException;
import com.ssafy.socks.entity.magazine.Contents;
import com.ssafy.socks.entity.magazine.Likes;
import com.ssafy.socks.entity.magazine.Magazine;
import com.ssafy.socks.entity.user.User;
import com.ssafy.socks.model.magazine.MagazineModel;
import com.ssafy.socks.repository.color.SelectedColorJpaRepository;
import com.ssafy.socks.repository.magazine.BookmarkRepository;
import com.ssafy.socks.repository.magazine.LikesJpaRepository;
import com.ssafy.socks.repository.magazine.MagazineJpaRepository;
import com.ssafy.socks.repository.magazine.MagazineRepository;
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
	private final SelectedColorJpaRepository selectedColorJpaRepository;

	public void saveMagazine(MagazineModel magazineModel) {
		List<Contents> contentsList = new ArrayList<>();
		Magazine magazine = new Magazine();

		for (int i = 0; i < magazineModel.getContent().size(); i++) {
			contentsList.add(
				Contents.builder()
					.url(magazineModel.getContent().get(i).getUrl())
					.answer(magazineModel.getContent().get(i).getAnswer())
					.mainText(magazineModel.getContent().get(i).getMainText())
					.subText(magazineModel.getContent().get(i).getSubText())
					.template(magazineModel.getContent().get(i).getTemplate())
					.question(magazineModel.getContent().get(i).getQuestion())
					.magazine(magazine)
					.build());
		}

		magazine = Magazine.builder()
			.user(userJpaRepository.findByEmail(magazineModel.getEmail()).orElseThrow(CUserNotFoundException::new))
			.contents(contentsList)
			.selectedColor(selectedColorJpaRepository.findById(magazineModel.getColorId()).orElseThrow(CCommunicationException::new))
			.createdDate(magazineModel.getCreatedDate())
			.build();

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
