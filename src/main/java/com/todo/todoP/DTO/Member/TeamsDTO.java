package com.todo.todoP.DTO.Member;

import com.todo.todoP.Entity.Member_Team_Parent;
import lombok.Data;

@Data
public class TeamsDTO {

    private String title;
    private Long team_id;

    public TeamsDTO(Member_Team_Parent parent){
        title = parent.getTeam().getTitle();
        team_id = parent.getTeam().getId();
    }

}
