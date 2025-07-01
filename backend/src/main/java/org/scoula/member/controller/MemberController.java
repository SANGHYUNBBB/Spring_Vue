package org.scoula.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.scoula.common.util.UploadFiles;
import org.scoula.member.dto.MemberDTO;
import org.scoula.member.dto.MemberJoinDTO;
import org.scoula.member.dto.MemberUpdateDTO;
import org.scoula.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    final MemberService service;

    // ID 중복 체크 API
    @GetMapping("/checkusername/{username}")
    public ResponseEntity<Boolean> checkUsername(@PathVariable String username) {
        return ResponseEntity.ok().body(service.checkDuplicate(username));
    }

    // 회원가입 API
    // 매개변수에 @RequestBody 생략됨
    @PostMapping("")
    public ResponseEntity<MemberDTO> join(MemberJoinDTO member) {
        // @RequestBody MemberJoinDTO
        //json 형태로 온 응답을 dto형태로 바꾸는게 requestbody였지만
        // dto를 이미지로도 받아야되는데, json은 이미지로 못넣음.
        // 이미지를 인코딩해서 넣어줘야하는데, 효율이 안나오니까
        // 폼 데이터(formdata) 형태로 넘겨주려고 저렇게 하는 것임!!
        // 그래서 @RequestBody대신  @Modelattribute를 사용하는것임. 그리고 그것은 생략 가능!!!!!
        return ResponseEntity.ok(service.join(member));
    }

    // MemberController.java
    @GetMapping("/{username}/avatar")
    public void getAvatar(@PathVariable String username, HttpServletResponse response) {
        String avatarPath = "c:/upload/avatar/" + username + ".png";
        File file = new File(avatarPath);

        if (!file.exists()) {
            // 아바타가 없는 경우 기본 이미지 사용
            file = new File("C:/upload/avatar/unknown.png");
        }

        UploadFiles.downloadImage(response, file);
    }

    @PutMapping("/{username}") // PUT 메서드 : 기존 리소스의 완전한 업데이트를 의미
    public ResponseEntity<MemberDTO> changeProfile(MemberUpdateDTO member) {
        return ResponseEntity.ok(service.update(member));
    }
}