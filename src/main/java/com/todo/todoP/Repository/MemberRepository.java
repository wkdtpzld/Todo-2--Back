package com.todo.todoP.Repository;

import com.todo.todoP.Entity.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {

    List<Member> findByName(String name);

    Boolean existsByEmail(String email);

    Member findMemberByName(String name);
    Member findMemberById(Long id);

    @Query("select m from Member m")
    Slice<Member> findSliceAll(Pageable pageable);

}
