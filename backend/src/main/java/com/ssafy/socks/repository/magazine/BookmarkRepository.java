package com.ssafy.socks.repository.magazine;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ssafy.socks.entity.magazine.Bookmark;
import com.ssafy.socks.entity.magazine.Magazine;
import com.ssafy.socks.entity.user.User;

import lombok.RequiredArgsConstructor;

@Repository @RequiredArgsConstructor @Transactional
public class BookmarkRepository {
	private final EntityManager em;

	public List<Magazine> findBookmarkRepository(User user) {
		List<Bookmark> bookmarks = em.createQuery("select b from Bookmark b where b.user = :user", Bookmark.class)
			.setParameter("user",user)
			.getResultList();

		return em.createQuery("select m from Magazine m where m.bookmarks = :bookmarks", Magazine.class)
			.setParameter("bookmarks", bookmarks)
			.getResultList();
	}

	public List<Bookmark> findBookmarkByMagazine(Magazine magazine) {
		return em.createQuery("select b from Bookmark b where b.magazineBookmark = :magazine", Bookmark.class)
			.setParameter("magazine",magazine)
			.getResultList();
	}
}
