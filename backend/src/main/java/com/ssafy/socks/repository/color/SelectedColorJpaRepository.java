package com.ssafy.socks.repository.color;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.socks.entity.color.SelectedColor;

@Repository
public interface SelectedColorJpaRepository extends JpaRepository<SelectedColor,Long> {
}
