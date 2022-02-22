package com.todo.todoP.DTO.Team;

import com.todo.todoP.Entity.Enum.Category;
import com.todo.todoP.Entity.Team;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class TeamDTO {

    private String title;
    private Category category;
    private List<MembersDTO> members;

    public TeamDTO(Team team){
        category = team.getCategory();
        title = team.getTitle();
        members = team.getParent().stream()
                .map(MembersDTO::new)
                .collect(Collectors.toList());
    }
}
