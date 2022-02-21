package com.todo.todoP.DTO.Member;


import com.todo.todoP.Entity.Member;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MemberCreateDTO {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @NotBlank(message = "Check your name")
    @Length(max = 20)
    private String name;

    @NotBlank(message = "Check your Email")
    private String email;

    @NotBlank(message = "Check your Password")
    private String password;

    public static Member toEntity(final MemberCreateDTO dto){
        return new Member(dto.getName()
                ,dto.passwordEncoder.encode(dto.getPassword())
                ,dto.getEmail());
    }
}
