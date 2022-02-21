package com.todo.todoP.DTO.Member;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberSignInDTO {

    @NotBlank(message = "Check your name")
    private String name;

    @NotBlank(message = "Check your password")
    private String password;

}
