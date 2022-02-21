package com.todo.todoP.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Setter @Getter
public class Member_Team_Parent extends BasicEntity{

    @Id @GeneratedValue
    @Column(name = "Parent_ID")
    private Long id;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    //=생성 메서드=//
    public static Member_Team_Parent joinMember(Member member, Team team, LocalDateTime date){
        Member_Team_Parent x = new Member_Team_Parent();
        x.setMember(member);
        x.setTeam(team);

        return x;
    }
}
