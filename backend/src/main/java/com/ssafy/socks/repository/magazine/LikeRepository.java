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
		em.createQuery("update Likes l set l.isChecked = false where l.magazineId = :magazineId and l.userId = :userId")
			.setParameter("magazineId",magazineId)
			.setParameter("userId",userId);
		em.flush();
		em.close();
	}

	public Likes findChecked(Long magazineId, Long userId) {
		Likes likes = em.createQuery("select l from Likes l where l.magazineId = :magazineId and l.userId = :userId", Likes.class)
			.setParameter("magazineId",magazineId)
			.setParameter("userId",userId)
			.getSingleResult();
		em.flush();
		em.close();
		return likes;
	}
}
