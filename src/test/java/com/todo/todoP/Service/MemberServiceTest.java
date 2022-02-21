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
    @Autowired MemberRepository memberRepository;

    @Test
    public void UpdateTest(){
//        memberService.update(12L,"updateMember");
    }

    @Test
    public void findAll(){
        ResponseDTO<MemberDTO> all = memberService.findAll();
//        assertThat(all.size()).isEqualTo(2);
//        for (MemberDTO memberDTO : all) {
//            System.out.println(memberDTO.getTeam()+"------");
//        }
    }

    @Test
    @Rollback(value = false)
    public void updateTest(){
        Member member = new Member("updateName");
        member.setId(3L);
        List<MemberDTO> memberDTO = memberService.update(member);
        for (MemberDTO dto : memberDTO) {
            System.out.println(dto);
        }
    }
}