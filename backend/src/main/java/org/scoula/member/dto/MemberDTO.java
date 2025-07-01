package org.scoula.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.security.account.domain.MemberVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//응답용도로 사용됨. 패스워드가 없음!
public class MemberDTO {
    private String username;
    private String email;
    private Date regDate;              // 등록일
    private Date updateDate;           // 수정일
    private MultipartFile avatar;
    private List<String> authList;     // 권한 목록 (join 처리 필요)

    // MemberVO -> MemberDTO 변환 (정적 팩토리 메서드)
    public static MemberDTO of(MemberVO m) {
        return MemberDTO.builder()
                .username(m.getUsername())
                .email(m.getEmail())
                .regDate(m.getRegDate())
                .updateDate(m.getUpdateDate())
                .authList(m.getAuthList().stream()
                        .map(a -> a.getAuth())
                        .toList())
                .build();
    }
}