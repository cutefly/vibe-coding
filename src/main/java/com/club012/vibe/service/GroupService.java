package com.club012.vibe.service;

import com.club012.vibe.domain.Group;
import java.util.List;
import java.util.Optional;

/**
 * 그룹 관리 비즈니스 로직을 위한 서비스 인터페이스입니다.
 */
public interface GroupService {
    List<Group> getAllGroups();

    Optional<Group> getGroupById(Long id);

    Group saveGroup(Group group);

    void deleteGroup(Long id);

    void addUserToGroup(Long userId, Long groupId);

    void removeUserFromGroup(Long userId, Long groupId);
}
