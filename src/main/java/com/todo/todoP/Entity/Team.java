package com.todo.todoP.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Team extends BasicEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_ID")
    private Long id;

    private String title;
    private String category;

    @JsonIgnore
    @OneToMany(mappedBy = "team")
    private List<Member_Team_Parent> parent = new ArrayList<>();

    public Team(String title) {
        this.title = title;
    }
}
