package com.mlb.mlb_api.Service;

import com.mlb.mlb_api.controllers.dto.PlayerDTO;
import com.mlb.mlb_api.repositories.PlayerRepository;
import com.mlb.mlb_api.repositories.entities.Player;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player save(PlayerDTO playerDTO) {
        //converting a playerDTO to Player entity
        Player player = new Player(playerDTO);
        return playerRepository.save(player); //player is the returned player entity from the DB
    }

    @Override
    public Player update(@RequestBody PlayerDTO playerDTO, Integer id) {
        //find the player to update
        Player playerToUpdate = findById(id);

        //update the players information
        if(playerDTO.getName() == null) {
            playerToUpdate.setName(playerToUpdate.getName());
        }else if(playerDTO.getName().isEmpty()) {
            playerToUpdate.setName(playerToUpdate.getName());
        }else {
            playerToUpdate.setName(playerDTO.getName());
        }
        playerToUpdate.setAge(playerDTO.getAge() != null ? playerDTO.getAge() : playerToUpdate.getAge());
        playerToUpdate.setRating(playerDTO.getRating() != null ? playerDTO.getRating() : playerToUpdate.getRating());
        playerToUpdate.setYearsOfExperience(playerDTO.getYearsOfExperience() != null ? playerDTO.getYearsOfExperience() : playerToUpdate.getYearsOfExperience());

        return playerRepository.save(playerToUpdate);
    }

    @Override
    public void delete(Integer id) {
        //find player to delete
        Optional<Player> playerToDelete = playerRepository.findById(id);
        //delete player
        if(playerToDelete.isPresent()) {
            playerRepository.deleteById(id);
            throw new ResponseStatusException(HttpStatus.OK, "The player has been successfully deleted");
        }
        throw new ResponseStatusException(HttpStatus.OK, "The player was not found");

    }

    @Override
    public Iterable<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public Player findById(Integer id) {
        Optional<Player> optionalPlayer = playerRepository.findById(id);
        if(optionalPlayer.isEmpty()) {
            return null;
        }

        return optionalPlayer.get();
    }


}
