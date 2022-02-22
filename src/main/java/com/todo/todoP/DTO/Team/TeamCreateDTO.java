package com.todo.todoP.DTO.Team;

import com.todo.todoP.Entity.Enum.Category;
import com.todo.todoP.Entity.Team;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TeamCreateDTO {

    @NotBlank
    private String title;

    private Category category;

    public static Team toEntity(final TeamCreateDTO dto){
        return new Team(dto.getTitle(),dto.getCategory());
    }


}
