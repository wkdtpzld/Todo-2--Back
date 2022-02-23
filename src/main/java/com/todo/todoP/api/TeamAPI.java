package com.todo.todoP.api;

import com.todo.todoP.DTO.Basic.ResponseDTO;
import com.todo.todoP.DTO.Team.TeamCreateDTO;
import com.todo.todoP.DTO.Team.TeamDTO;
import com.todo.todoP.Entity.Team;
import com.todo.todoP.Service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TeamAPI {

    private final TeamService teamService;

    @GetMapping("/auth/teams")
    public ResponseEntity<?> TeamList(){
        List<TeamDTO> all = teamService.findAllNotPaging();
        ResponseDTO<TeamDTO> response = ResponseDTO.<TeamDTO>builder().data(all).build();

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/team")
    public ResponseEntity<?> CreateTeam(@Valid @RequestBody TeamCreateDTO createDTO){
        try {
            Team team = TeamCreateDTO.toEntity(createDTO);

            Team saveTeam = teamService.save(team);
            List<Team> response = List.of(saveTeam);
            List<TeamDTO> collect = response.stream().map(TeamDTO::new).collect(Collectors.toList());
            ResponseDTO<TeamDTO> build = ResponseDTO.<TeamDTO>builder().data(collect).build();

            return ResponseEntity.ok().body(build);

        } catch (Exception e){
            String error = e.getMessage();
            ResponseDTO<TeamDTO> response = ResponseDTO.<TeamDTO>builder().etc(error).build();

            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/team/{title}")
    public ResponseEntity<?> UpdateTeam(@Valid @RequestBody TeamCreateDTO createDTO,
                                        @AuthenticationPrincipal Long userID,
                                        @PathVariable String title){
        try {
            Team team = teamService.FindByTitle(title);
            if (userID == Long.parseLong(team.getCreator())){
                List<TeamDTO> update = teamService.update(team, createDTO);
                ResponseDTO<TeamDTO> response = ResponseDTO.<TeamDTO>builder().data(update).build();
                return ResponseEntity.ok().body(response);
            } else {
                return ResponseEntity.badRequest().body("It doesn't match the creator.");
            }
        } catch (Exception e){
            String error = e.getMessage();
            ResponseDTO<TeamDTO> response = ResponseDTO.<TeamDTO>builder().etc(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }
}
