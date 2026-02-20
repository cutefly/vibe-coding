package com.club012.vibe.service;

import com.club012.vibe.domain.User;
import com.club012.vibe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * UserService 인터페이스의 구현체로, 사용자 관련 비즈니스 로직과 데이터 트랜잭션을 처리합니다.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * 데이터베이스에 저장된 모든 사용자를 조회합니다.
     *
     * @return User 엔티티의 전체 목록 (List)
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * 주어진 ID를 기반으로 특정 사용자를 데이터베이스에서 조회합니다.
     *
     * @param id 찾고자 하는 사용자의 식별자(ID)
     * @return 조회된 사용자를 감싼 Optional. 사용자가 존재하지 않을 경우 빈 Optional 반환
     */
    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * 신규 사용자를 추가하거나 기존 사용자를 데이터베이스에 갱신 저장합니다.
     * 처리 중 데이터 변경이 발생하므로 트랜잭션(@Transactional) 처리가 적용됩니다.
     *
     * @param user 저장(혹은 업데이트)할 사용자 엔티티 객체
     * @return 영속화(저장)가 완료된 참조 엔티티 객체
     */
    @Override
    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * 주어진 ID에 해당하는 사용자를 데이터베이스에서 삭제합니다.
     * 삭제 시 데이터 변경이 발생하므로 트랜잭션(@Transactional) 처리가 적용됩니다.
     *
     * @param id 삭제할 대상 사용자의 고유 식별자(ID)
     */
    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
