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
		return em.createQuery("select m "
			+ "from Magazine m "
			+ "join Likes l on m.id = l.magazineId "
			+ "group by m.id "
			+ "order by m.id", Magazine.class)
			.getResultList();
	}
}
