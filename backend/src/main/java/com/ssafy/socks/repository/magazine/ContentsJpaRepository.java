package com.ssafy.socks.repository.magazine;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.socks.entity.magazine.Contents;

@Repository
public interface ContentsJpaRepository extends JpaRepository<Contents,Long> {
	List<Contents> findByMagazineId(Long id);
}
