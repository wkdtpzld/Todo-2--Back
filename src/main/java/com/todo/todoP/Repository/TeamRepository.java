package com.todo.todoP.Repository;

import com.todo.todoP.Entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team,Long> {

    List<Team> findAll();

    List<Team> findByTitle(String title);
}
