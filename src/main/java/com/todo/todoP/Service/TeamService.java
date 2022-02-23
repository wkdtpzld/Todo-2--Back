package com.todo.todoP.Service;

import com.todo.todoP.DTO.Team.TeamCreateDTO;
import com.todo.todoP.DTO.Team.TeamDTO;
import com.todo.todoP.Entity.Team;
import com.todo.todoP.Repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamService {

    private final TeamRepository teamRepository;

    public Team save(Team team){
        validation(team);
        return teamRepository.save(team);
    }

    public void delete(Team team){
        teamRepository.delete(team);
    }

    public List<TeamDTO> update(Team team, TeamCreateDTO dto){
        
        team.setTitle(dto.getTitle());
        team.setCategory(dto.getCategory());

        return List.of(team).stream().map(TeamDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TeamDTO> findAllNotPaging(){
        List<Team> all = teamRepository.findAll();
        return all.stream()
                .map(TeamDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Team FindByTitle(String title){
        validationExists(title);
        return teamRepository.findUpdateTeam(title);
    }

    @Transactional(readOnly = true)
    public Team FindById(Long id){
        Team team = teamRepository.findTeamById(id);
        if (team == null){
            throw new IllegalStateException("Team must exist");
        }
        return team;

    }

    //==증명 메서드==//
    private void validation(Team team){
        if (!teamRepository.findListByTitle(team.getTitle()).isEmpty()){
            throw new IllegalStateException("already exists");
        }
    }



    private void validationExists(String title){
        if (teamRepository.findOptionalByTitle(title).isEmpty()){
            throw new IllegalStateException("Team must exist.");
        }
    }

}
