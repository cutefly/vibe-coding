package com.club012.vibe.service;

import com.club012.vibe.domain.User;
import java.util.List;
import java.util.Optional;

/**
 * 사용자 관련 비즈니스 로직을 정의하는 서비스 인터페이스입니다.
 */
public interface UserService {
    /**
     * 등록된 모든 사용자의 목록을 조회합니다.
     *
     * @return 모든 사용자의 엔티티 리스트
     */
    List<User> getAllUsers();

    /**
     * 특정 ID를 가진 단일 사용자의 정보를 조회합니다.
     *
     * @param id 조회할 사용자의 고유 ID
     * @return 조회된 사용자 엔티티를 포함하는 Optional 객체. 사용자가 없으면 empty를 반환합니다.
     */
    Optional<User> getUserById(Long id);

    /**
     * 새로운 사용자 정보를 생성하거나 기존 사용자 정보를 갱신합니다.
     *
     * @param user 저장하거나 갱신할 사용자 모델 객체
     * @return 저장된 사용자 엔티티 객체
     */
    User saveUser(User user);

    /**
     * 특정 ID를 가진 사용자의 정보를 데이터베이스에서 삭제합니다.
     *
     * @param id 삭제할 사용자의 고유 ID
     */
    void deleteUser(Long id);
}
