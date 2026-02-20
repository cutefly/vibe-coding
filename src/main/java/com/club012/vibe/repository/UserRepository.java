package com.club012.vibe.repository;

import com.club012.vibe.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 사용자(User) 엔티티에 대한 데이터 처리 및 데이터베이스 접근을 담당하는 리포지토리입니다.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
