package com.todo.todoP.Repository;

import com.todo.todoP.DTO.Member.MemberDTO;
import com.todo.todoP.DTO.Member.MemberSearchCondition;
import com.todo.todoP.Entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface MemberRepositoryCustom {
    Page<MemberDTO> findAllPageSort(Pageable pageable, MemberSearchCondition condition);

    Slice<MemberDTO> findAllSliceSort(Pageable pageable, MemberSearchCondition condition);

}
