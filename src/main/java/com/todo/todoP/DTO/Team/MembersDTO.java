package com.todo.todoP.DTO.Team;

import com.todo.todoP.Entity.Member_Team_Parent;
import lombok.Data;

@Data
public class MembersDTO {

    private String name;
    private Long member_id;

    public MembersDTO(Member_Team_Parent parent){
        name = parent.getMember().getName();
        member_id = parent.getMember().getId();
    }

}
