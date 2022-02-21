package com.todo.todoP.DTO.Member;

import com.todo.todoP.Entity.Member;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MemberDTO {

    private String token;
    private Long id;
    private String name;
    private String email;
    private String password;
    private List<TeamsDTO> team;

    public MemberDTO(Member member){
        id = member.getId();
        name = member.getName();
        email = member.getEmail();
        password = member.getPassword();
        team = member.getParent().stream()
                .map(TeamsDTO::new)
                .collect(Collectors.toList());
    }

}
