package com.club012.vibe.repository;

import com.club012.vibe.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Group 엔티티에 대한 데이터 액세스 처리를 담당하는 레포지토리 인터페이스입니다.
 */
@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}
