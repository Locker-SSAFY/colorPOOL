package com.ssafy.socks.repository.magazine;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ssafy.socks.entity.magazine.Likes;
import com.ssafy.socks.entity.magazine.Magazine;

import lombok.RequiredArgsConstructor;

@Repository @RequiredArgsConstructor @Transactional
public class MagazineRepository {
	private final EntityManager em;

	public List<Magazine> findMagazineOrderByLikes() {
		List<Likes> likes = em.createQuery("select l from Likes l order by count(l.magazine)", Likes.class).getResultList();

		return em.createQuery("select m from Magazine m where m.likes = :likes", Magazine.class)
			.setParameter("likes", likes)
			.getResultList();
	}
}
