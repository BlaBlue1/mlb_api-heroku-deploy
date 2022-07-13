package com.mlb.mlb_api.repositories;

import com.mlb.mlb_api.repositories.entities.Player;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlayerRepository extends CrudRepository<Player, Integer> {
//    Player findByName(String name);
    //Creating Query methods
    List<Player> findByNameAndAge(String name, Integer age);
    List<Player> findByNameAndRating(String name, Double rating);
    List<Player> findPlayersByNameAndYearsOfExperience(String name, Double yearsOfExperience);
    List<Player> findByName(String name);
}