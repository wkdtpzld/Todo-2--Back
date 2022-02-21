package com.todo.todoP.DTO.Team;

import com.todo.todoP.Entity.Team;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;


@Data
public class TeamDTO {

    private String title;
    private String category;
    private Long teamId;
    private List<MembersDTO> members;

    public TeamDTO(Team team){
        teamId = team.getId();
        category = team.getCategory();
        title = team.getTitle();
        members = team.getParent().stream()
                .map(MembersDTO::new)
                .collect(Collectors.toList());
    }

}
