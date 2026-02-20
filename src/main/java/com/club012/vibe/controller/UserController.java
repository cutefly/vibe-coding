package com.club012.vibe.controller;

import com.club012.vibe.domain.User;
import com.club012.vibe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 사용자 정보 관리를 위한 웹 요청을 처리하는 컨트롤러 클래스입니다.
 */
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 모든 사용자 목록을 조회하여 뷰(View)에 전달합니다.
     *
     * @param model 뷰에 데이터를 전달하기 위한 Model 객체
     * @return 사용자 목록 페이지 템플릿 이름 ("users")
     */
    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", new User()); // For the form
        return "users";
    }

    /**
     * 폼에서 전달받은 사용자 정보를 저장합니다.
     *
     * @param user 저장할 사용자 정보 모델 객체
     * @return 사용자 목록 페이지로의 리다이렉트 경로 ("redirect:/users")
     */
    @PostMapping
    public String addUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    /**
     * 특정 사용자 정보를 수정하기 위한 폼 페이지를 반환합니다.
     *
     * @param id    수정할 사용자의 고유 ID
     * @param model 뷰에 데이터를 전달하기 위한 Model 객체
     * @return 사용자 수정 페이지 템플릿 이름 ("update-user")
     * @throws IllegalArgumentException 유효하지 않은 사용자 ID일 경우 발생
     */
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userService.getUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "update-user";
    }

    /**
     * 폼에서 전달받은 정보로 특정 사용자 정보를 업데이트합니다.
     *
     * @param id   수정 대상 사용자의 고유 ID
     * @param user 수정할 사용자 정보가 담긴 모델 객체
     * @return 사용자 목록 페이지로의 리다이렉트 경로 ("redirect:/users")
     */
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @ModelAttribute User user) {
        user.setId(id); // Ensure the ID is set for update
        userService.saveUser(user);
        return "redirect:/users";
    }

    /**
     * 특정 사용자 정보를 삭제합니다.
     *
     * @param id 삭제할 사용자의 고유 ID
     * @return 사용자 목록 페이지로의 리다이렉트 경로 ("redirect:/users")
     */
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
