package com.todo.todoP.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.todo.todoP.Entity.Basic.BasicUserEntity;
import com.todo.todoP.Entity.Embedded.Address;
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
public class Member extends BasicUserEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;
    private String email;
    private String password;

    @Embedded
    private Address address;

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

    public Member(String name, String password, String email, Address address) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.address = address;
    }
}
