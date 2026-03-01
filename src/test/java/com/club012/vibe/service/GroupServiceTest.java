package com.club012.vibe.service;

import com.club012.vibe.domain.Group;
import com.club012.vibe.domain.User;
import com.club012.vibe.repository.GroupRepository;
import com.club012.vibe.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GroupServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GroupServiceImpl groupService;

    private User user;
    private Group group;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setName("Test User");

        group = new Group();
        group.setId(1L);
        group.setName("Test Group");
    }

    @Test
    void addUserToGroup_Success() {
        // given
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(groupRepository.findById(1L)).thenReturn(Optional.of(group));

        // when
        groupService.addUserToGroup(1L, 1L);

        // then
        assertTrue(user.getGroups().contains(group));
        assertTrue(group.getUsers().contains(user));
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void removeUserFromGroup_Success() {
        // given
        user.getGroups().add(group);
        group.getUsers().add(user);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(groupRepository.findById(1L)).thenReturn(Optional.of(group));

        // when
        groupService.removeUserFromGroup(1L, 1L);

        // then
        assertFalse(user.getGroups().contains(group));
        assertFalse(group.getUsers().contains(user));
        verify(userRepository, times(1)).save(user);
    }
}
