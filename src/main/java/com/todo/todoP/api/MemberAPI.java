package com.todo.todoP.api;

import com.todo.todoP.DTO.Member.MemberCreateDTO;
import com.todo.todoP.DTO.Member.MemberDTO;
import com.todo.todoP.DTO.Basic.ResponseDTO;
import com.todo.todoP.Entity.Member;
import com.todo.todoP.Entity.Team;
import com.todo.todoP.Service.MemberService;
import com.todo.todoP.Service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class MemberAPI {

    private final MemberService memberService;
    private final TeamService teamService;

    @GetMapping("/member/{name}")
    public ResponseEntity<?> OneMember(@PathVariable String name){
        try {
            List<MemberDTO> DTO = memberService.GetOneMember(name);
            ResponseDTO<MemberDTO> response = ResponseDTO.<MemberDTO>builder().data(DTO).build();

            return ResponseEntity.ok().body(response);
        } catch (Exception e){
            String error = e.getMessage();
            ResponseDTO<MemberDTO> response = ResponseDTO.<MemberDTO>builder().etc(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/member")
    public ResponseEntity<?> updateMember(@Valid @RequestBody MemberCreateDTO memberDTO, @AuthenticationPrincipal Long userId){

        Member entity = MemberCreateDTO.toEntity(memberDTO);
        entity.setId(userId);
        List<MemberDTO> dto = memberService.update(entity);
        ResponseDTO<MemberDTO> response = ResponseDTO.<MemberDTO>builder().data(dto).build();

        return ResponseEntity.ok().body(response);

    }

    @DeleteMapping("/member")
    public ResponseEntity<?> deleteMember(@AuthenticationPrincipal Long userId){
        Member member = memberService.findByUserId(userId);
        memberService.remove(member);
        ResponseDTO<Object> response = ResponseDTO.builder().etc("Delete Success").build();

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/page/members")
    public Page<MemberDTO> list(@PageableDefault(size = 10, sort = "name") Pageable pageable){
        return memberService.findAll(pageable);
    }

    @GetMapping("/slice/members")
    public Slice<MemberDTO> list2(@PageableDefault(size = 10) Pageable pageable){
        return memberService.GetMembersBySlice(pageable);
    }

    @GetMapping("/members")
    public ResponseEntity<?> AllMembers(){
        ResponseDTO<MemberDTO> response = memberService.findAllNotPaging();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/member/team/{id}")
    public ResponseEntity<?> JoinTeam(@AuthenticationPrincipal Long userId,@PathVariable Long id){
        try {

            Member member = memberService.findByUserId(userId);
            Team team = teamService.FindById(id);
            memberService.JoinTeam(member,team);
            ResponseDTO<Object> response = ResponseDTO.builder().etc("Member Join Team").build();

            return ResponseEntity.ok().body(response);

        } catch (Exception e){
            String error = e.getMessage();
            ResponseDTO<Object> response = ResponseDTO.builder().etc(error).build();

            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/member/team/{id}")
    public ResponseEntity<?> deleteTeamMember(@AuthenticationPrincipal Long userId, @PathVariable Long id){
        try {
            Member member = memberService.findByUserId(userId);
            Team team = teamService.FindById(id);
            memberService.DeleteTeamMember(member,team);
            ResponseDTO<Object> response = ResponseDTO.builder().etc("Member Delete Success").build();

            return ResponseEntity.ok().body(response);

        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO<Object> response = ResponseDTO.builder().etc(error).build();

            return ResponseEntity.badRequest().body(response);
        }


    }



}
