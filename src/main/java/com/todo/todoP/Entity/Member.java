package com.todo.todoP.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member extends BasicEntity{

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;
    private String email;
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Todo> todos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Member_Team_Parent> parent = new ArrayList<>();

    //=생성 메서드=//
    public Member(String name) {
        this.name = name;
    }


    public Member(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Member(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }
}
