package com.todo.todoP.Repository;

import com.todo.todoP.Entity.Member;
import com.todo.todoP.Entity.Member_Team_Parent;
import com.todo.todoP.Entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberParentRepository extends JpaRepository<Member_Team_Parent, Long> {

    Member_Team_Parent findByTeamAndMember(Team team, Member member);

    boolean existsByTeamAndMember(Team team, Member member);

}
