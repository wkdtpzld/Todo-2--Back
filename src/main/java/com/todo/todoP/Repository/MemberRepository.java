package com.todo.todoP.Repository;

import com.todo.todoP.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {

    List<Member> findByName(String name);
    List<Member> findAll();

    Boolean existsByEmail(String email);

    Member findMemberByName(String name);
    Member findMemberById(Long id);
    Member findMemberByEmail(String email);

}
