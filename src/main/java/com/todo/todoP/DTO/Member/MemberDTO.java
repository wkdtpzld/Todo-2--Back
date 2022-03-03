package com.todo.todoP.DTO.Member;

import com.querydsl.core.annotations.QueryProjection;
import com.todo.todoP.Entity.Embedded.Address;
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
    private String name;
    private String email;
    private List<TeamsDTO> team;
    private Address address;

    @QueryProjection
    public MemberDTO(Member member){
        name = member.getName();
        email = member.getEmail();
        team = member.getParent().stream()
                .map(TeamsDTO::new)
                .collect(Collectors.toList());
        address = member.getAddress();
    }

}
