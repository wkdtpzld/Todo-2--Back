package com.todo.todoP.Entity;

import lombok.*;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "TODO_ID")
    private Long id;

    private String title;

    private String content;

    private Boolean done;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

}
