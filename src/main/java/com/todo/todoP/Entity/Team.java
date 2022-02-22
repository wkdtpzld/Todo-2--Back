package com.todo.todoP.Entity;

import com.todo.todoP.Entity.Basic.BasicByEntity;
import com.todo.todoP.Entity.Basic.BasicUserEntity;
import com.todo.todoP.Entity.Enum.Category;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Team extends BasicByEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_ID")
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToMany(mappedBy = "team")
    private List<Member_Team_Parent> parent = new ArrayList<>();

    public Team(String title) {
        this.title = title;
    }

    public Team(String title, Category category){
        this.title = title;
        this.category = category;
    }
}
