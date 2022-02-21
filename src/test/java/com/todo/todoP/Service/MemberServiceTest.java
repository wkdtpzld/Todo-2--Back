package com.todo.todoP.Service;

import com.todo.todoP.DTO.Basic.ResponseDTO;
import com.todo.todoP.DTO.Member.MemberDTO;
import com.todo.todoP.Entity.Member;
import com.todo.todoP.Repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;


}