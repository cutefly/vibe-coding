package com.club012.vibe.service;

import com.club012.vibe.domain.User;
import java.util.List;
import java.util.Optional;

/**
 * 사용자 관련 비즈니스 로직을 정의하는 서비스 인터페이스입니다.
 */
public interface UserService {
    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    User saveUser(User user);

    void deleteUser(Long id);
}
