package com.club012.vibe.service;

import com.club012.vibe.domain.Group;
import com.club012.vibe.domain.User;
import com.club012.vibe.repository.GroupRepository;
import com.club012.vibe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * GroupService 인터페이스의 구현체로, 그룹 관련 비즈니스 로직을 처리합니다.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public Optional<Group> getGroupById(Long id) {
        return groupRepository.findById(id);
    }

    @Override
    @Transactional
    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }

    @Override
    @Transactional
    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addUserToGroup(Long userId, Long groupId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + userId));
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid group Id:" + groupId));

        user.getGroups().add(group);
        group.getUsers().add(user);

        userRepository.save(user);
    }

    @Override
    @Transactional
    public void removeUserFromGroup(Long userId, Long groupId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + userId));
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid group Id:" + groupId));

        user.getGroups().remove(group);
        group.getUsers().remove(user);

        userRepository.save(user);
    }
}
