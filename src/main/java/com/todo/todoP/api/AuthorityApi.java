package com.todo.todoP.api;

import com.todo.todoP.DTO.Basic.ResponseDTO;
import com.todo.todoP.DTO.Member.MemberCreateDTO;
import com.todo.todoP.DTO.Member.MemberDTO;
import com.todo.todoP.DTO.Member.MemberSignInDTO;
import com.todo.todoP.Entity.Member;
import com.todo.todoP.Security.TokenProvider;
import com.todo.todoP.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthorityApi {

    private final MemberService memberService;
    private final TokenProvider tokenProvider;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/signup")
    public ResponseEntity<?> createMember(@Valid @RequestBody MemberCreateDTO memberDTO){
        try {
            Member entity = MemberCreateDTO.toEntity(memberDTO);
            List<Member> member = memberService.save(entity);

            List<MemberDTO> dto = member.stream().map(MemberDTO::new)
                    .collect(Collectors.toList());

            ResponseDTO<MemberDTO> response = ResponseDTO.<MemberDTO>builder()
                    .data(dto).build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e){

            String error = e.getMessage();
            ResponseDTO<MemberDTO> response = ResponseDTO.<MemberDTO>builder().etc(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }


    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@Valid @RequestBody MemberSignInDTO memberDTO){
        Member member = memberService.getByCredentials(memberDTO.getName(), memberDTO.getPassword(), passwordEncoder);

        if (member != null){

            String token = tokenProvider.create(member);
            MemberDTO responseMemberDTO = MemberDTO.builder()
                    .email(member.getEmail())
                    .id(member.getId())
                    .token(token)
                    .name(member.getName())
                    .build();
            return ResponseEntity.ok().body(responseMemberDTO);
        } else {
            ResponseDTO<Object> responseDTO = ResponseDTO.builder()
                    .etc("Login Failed")
                    .build();

            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

}
