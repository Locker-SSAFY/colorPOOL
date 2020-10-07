package com.ssafy.socks.repository.magazine;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ssafy.socks.entity.magazine.Likes;
import com.ssafy.socks.entity.magazine.Magazine;

import lombok.RequiredArgsConstructor;

@Repository @RequiredArgsConstructor @Transactional
public class LikeRepository {
	private EntityManager em;

	public void updateUnChecked(Long magazineId, Long userId) {
		em.createQuery("delete from Likes l where l.magazineId = :magazineId and l.userId = :userId")
			.setParameter("magazineId",magazineId)
			.setParameter("userId",userId);
	}
}
