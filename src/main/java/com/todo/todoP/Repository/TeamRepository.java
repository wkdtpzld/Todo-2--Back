package com.todo.todoP.Repository;

import com.todo.todoP.Entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team,Long> {

    List<Team> findAll();

    List<Team> findListByTitle(String title);

    Optional<Team> findOptionalByTitle(String title);

    @Query("select t from Team t where t.title = :title")
    Team findUpdateTeam(@Param("title") String title);

    Team findTeamById(Long id);
}
