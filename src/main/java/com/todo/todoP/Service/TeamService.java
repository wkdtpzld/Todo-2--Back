package com.todo.todoP.Service;

import com.todo.todoP.DTO.Team.TeamDTO;
import com.todo.todoP.Entity.Team;
import com.todo.todoP.Repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    public void save(Team team){
        validation(team);
        teamRepository.save(team);
    }

    public void delete(Team team){
        teamRepository.delete(team);
    }

    public List<TeamDTO> findAll(){
        List<Team> all = teamRepository.findAll();
        return all.stream()
                .map(TeamDTO::new)
                .collect(Collectors.toList());
    }

    //==증명 메서드==//
    private void validation(Team team){
        if (teamRepository.findByTitle(team.getTitle()).isEmpty()){
            throw new IllegalStateException("already exists");
        }
    }

}
