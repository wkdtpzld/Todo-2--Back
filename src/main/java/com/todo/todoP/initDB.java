package com.todo.todoP;

import com.todo.todoP.Entity.Member;
import com.todo.todoP.Entity.Member_Team_Parent;
import com.todo.todoP.Entity.Team;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class initDB {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.DBinit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{

        private final EntityManager em;

        public void DBinit(){

            for (int i=0 ; i<1000; i++){
                Member member = new Member("member" + i, "password" + i);
                em.persist(member);

                Team team = new Team("team"+(1000-i));
                em.persist(team);

                Member_Team_Parent join =
                        Member_Team_Parent.joinMember(member,team);
                em.persist(join);
            }
        }

    }

}
