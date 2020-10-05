package com.ssafy.socks.service.magazine;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.socks.advice.exception.CCommunicationException;
import com.ssafy.socks.advice.exception.CUserNotFoundException;
import com.ssafy.socks.entity.magazine.Contents;
import com.ssafy.socks.entity.magazine.Magazine;
import com.ssafy.socks.model.magazine.MagazineModel;
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

	public void saveMagazine(MagazineModel magazineModel) {
		List<Contents> contentsList = new ArrayList<>();
		Magazine magazine = new Magazine();

		for (int i = 0; i < magazineModel.getContent().size(); i++) {
			contentsList.add(
				Contents.builder()
					.content(magazineModel.getContent().get(i))
					.page(i)
					.magazine(magazine)
					.build());
		}

		magazine = Magazine.builder()
			.user(userJpaRepository.findByEmail(magazineModel.getEmail()).orElseThrow(CUserNotFoundException::new))
			.contents(contentsList)
			.createdDate(magazineModel.getCreatedDate())
			.build();

		magazineJpaRepository.save(magazine);
	}

	public Magazine getMagazine(String userEmail) {
		return magazineJpaRepository.findByUser(userJpaRepository.findByEmail(userEmail).orElseThrow(CUserNotFoundException::new)).orElseThrow(
			CCommunicationException::new);
	}

	public List<Magazine> getMagazines() {
		return magazineRepository.findMagazineOrderByLikes();
	}
}
