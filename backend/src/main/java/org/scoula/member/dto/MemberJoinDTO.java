package org.scoula.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.security.account.domain.MemberVO;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// 회원가입 요청에 대한 DTO.
// 사용자가 적어서 우리에게 보낼 내용들임.
public class MemberJoinDTO {
    private String username;           // 사용자 ID
    private String password;           // 비밀번호
    private String email;              // 이메일

    private MultipartFile avatar;      // 아바타 이미지 파일

    // MemberJoinDTO -> MemberVO 변환 메서드
    // org.scoula.security.account.domain.MemberVO 클래스에 @Builder 추가
    public MemberVO toVO() {
        return MemberVO.builder()
                .username(username)
                .password(password)
                .email(email)
                .build();
    }
}