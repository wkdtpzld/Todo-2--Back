package com.todo.todoP.Service;

import com.todo.todoP.DTO.Member.MemberDTO;
import com.todo.todoP.DTO.Basic.ResponseDTO;
import com.todo.todoP.Entity.Member;
import com.todo.todoP.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public List<Member> save(Member member){
        existsMember(member);
        memberRepository.save(member);
        return memberRepository.findByName(member.getName());
    }

    public List<MemberDTO> update(Member member){
        validation(member);
        Optional<Member> original = memberRepository.findById(member.getId());

        original.ifPresent(m ->{
            if (member.getName() != null){
                m.setName(member.getName());
            }
            if (member.getEmail() != null){
                m.setEmail(member.getEmail());
            }
            if (member.getPassword() != null){
                m.setPassword(member.getPassword());
            }

            memberRepository.save(m);
        });

        return original.stream().map(MemberDTO::new).collect(Collectors.toList());

    }

    public void remove(Member member){
        memberRepository.delete(member);
    }

    @Transactional(readOnly = true)
    public ResponseDTO<MemberDTO> findAll(){
        List<MemberDTO> members = findAllDTO();
        return ResponseDTO.<MemberDTO>builder().data(members).build();
    }

    //편의 메서드
    public List<MemberDTO> findAllDTO(){
        List<Member> allMembers = memberRepository.findAll();
        return allMembers.stream()
                .map(MemberDTO::new)
                .collect(Collectors.toList());
    }

    //==증명 메서드==//
    private void validation(Member member){
        if (member == null){
            throw new RuntimeException("Entity cannot be null");
        }

        if (member.getName() == null){
            throw new RuntimeException("Unknown user");
        }
    }

    private void existsMember(Member member) {
        if(member == null || member.getEmail() == null){
            throw new RuntimeException("Invalid arguments");
        }

        if (memberRepository.existsByEmail(member.getEmail())){
            throw new RuntimeException("Email already exists");
        }
    }

    public Member getByCredentials(String email, String password, PasswordEncoder encoder){
        Member entity = memberRepository.findMemberByEmail(email);

        if (entity != null && encoder.matches(password, entity.getPassword())){
            return entity;
        }
        return null;
    }


    //사용할 수도?
//    public MemberDTO findById(Long id){
//        Member member = memberRepository.findById(id).orElse(null);
//        if (member != null){
//            return new MemberDTO(member);
//        } else{
//            return null;
//        }
//    }
}
