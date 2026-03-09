package com.club012.vibe.controller;

import com.club012.vibe.domain.Group;
import com.club012.vibe.service.GroupService;
import com.club012.vibe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 그룹 정보 관리를 위한 웹 요청을 처리하는 컨트롤러 클래스입니다.
 */
@Controller
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {
    //원격 푸시를 위한 임시 주석
    private final GroupService groupService;
    private final UserService userService;

    /**
     * 모든 그룹 목록을 조회하여 뷰(View)에 전달합니다.
     *
     * @param model 뷰에 데이터를 전달하기 위한 Model 객체
     * @return 그룹 목록 페이지 템플릿 이름 ("groups")
     */
    @GetMapping
    public String listGroups(Model model) {
        model.addAttribute("groups", groupService.getAllGroups());
        model.addAttribute("group", new Group()); // For the form
        return "groups";
    }

    /**
     * 폼에서 전달받은 그룹 정보를 저장합니다.
     *
     * @param group 저장할 그룹 정보 모델 객체
     * @return 그룹 목록 페이지로의 리다이렉트 경로 ("redirect:/groups")
     */
    @PostMapping
    public String addGroup(@ModelAttribute Group group) {
        groupService.saveGroup(group);
        return "redirect:/groups";
    }

    /**
     * 특정 그룹 정보를 수정하기 위한 폼 페이지를 반환합니다.
     *
     * @param id    수정할 그룹의 고유 ID
     * @param model 뷰에 데이터를 전달하기 위한 Model 객체
     * @return 그룹 수정 페이지 템플릿 이름 ("update-group")
     */
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Group group = groupService.getGroupById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid group Id:" + id));
        model.addAttribute("group", group);
        model.addAttribute("allUsers", userService.getAllUsers());
        return "update-group";
    }

    /**
     * 폼에서 전달받은 정보로 특정 그룹 정보를 업데이트합니다.
     *
     * @param id    수정 대상 그룹의 고유 ID
     * @param group 수정할 그룹 정보가 담긴 모델 객체
     * @return 그룹 목록 페이지로의 리다이렉트 경로 ("redirect:/groups")
     */
    @PostMapping("/update/{id}")
    public String updateGroup(@PathVariable("id") long id, @ModelAttribute Group group) {
        group.setId(id);
        groupService.saveGroup(group);
        return "redirect:/groups";
    }

    /**
     * 특정 그룹 정보를 삭제합니다.
     *
     * @param id 삭제할 그룹의 고유 ID
     * @return 그룹 목록 페이지로의 리다이렉트 경로 ("redirect:/groups")
     */
    @GetMapping("/delete/{id}")
    public String deleteGroup(@PathVariable("id") long id) {
        groupService.deleteGroup(id);
        return "redirect:/groups";
    }

    /**
     * 특정 사용자를 그룹에 추가합니다.
     */
    @PostMapping("/{groupId}/users/{userId}")
    public String addUserToGroup(@PathVariable Long groupId, @PathVariable Long userId) {
        groupService.addUserToGroup(userId, groupId);
        return "redirect:/groups/edit/" + groupId;
    }

    /**
     * 특정 사용자를 그룹에서 제거합니다.
     */
    @PostMapping("/{groupId}/users/{userId}/remove")
    public String removeUserFromGroup(@PathVariable Long groupId, @PathVariable Long userId) {
        groupService.removeUserFromGroup(userId, groupId);
        return "redirect:/groups/edit/" + groupId;
    }
}
