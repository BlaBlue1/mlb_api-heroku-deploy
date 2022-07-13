package com.mlb.mlb_api.controllers;
import com.mlb.mlb_api.Service.PlayerService;
import com.mlb.mlb_api.controllers.dto.PlayerDTO;
import com.mlb.mlb_api.repositories.PlayerRepository;
import com.mlb.mlb_api.repositories.entities.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/addPlayer")
    public Player createPlayer(@RequestBody PlayerDTO newPlayerDTO) {
        return playerService.save(newPlayerDTO);
    }

    @GetMapping
    public Iterable<Player> getPlayer() {
        return playerService.findAll();
    }

    //Query methods
    //find by name and age
    //SELECT * FROM Player WHERE name="player name" AND age="player age";
    @Autowired
    PlayerRepository playerRepo;

    @GetMapping("/name")
    public ResponseEntity<List<Player>> getPlayersByName(@RequestParam String name) {
        return new ResponseEntity<>(playerRepo.findByName(name), HttpStatus.OK);

    }

    @GetMapping("/nameAndAge")
    public ResponseEntity<List<Player>> getPlayersByNameANDAge(@RequestParam String name, @RequestParam Integer age) {
        return new ResponseEntity<>(playerRepo.findByNameAndAge(name, age), HttpStatus.OK);

    }

    //    //find by name and Rating
//    //SELECT * FROM Player WHERE name="player name" AND rating="player rating";
    @GetMapping("/nameAndRating")
    public ResponseEntity<List<Player>> getPlayersByNameAndRating(@RequestParam String name, @RequestParam Double rating) {
        return new ResponseEntity<>(playerRepo.findByNameAndRating(name, rating), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable("id") Integer id) {

        return playerService.findById(id);
    }

    @PutMapping("/{id}")
    public Player updatePlayer(@RequestBody PlayerDTO playerDTO, @PathVariable("id") Integer id) {

        return playerService.update(playerDTO, id);
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable("id") Integer id, Player incomingPlayer) {
        playerService.delete(id);
    }


    //    @GetMapping
//    public Iterable<Player> getAllPlayers(){
//        return playerRepository.findAll();
//    }

    //mine
//        @PutMapping("{id}")
//        public ResponseEntity<Player> updatePlayer(@PathVariable Integer id, @RequestBody Player incomingPlayer) {
//            Player playerToUpdate = playerRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Player doesn't exist with id: " + id));
//
//            playerToUpdate.setName(incomingPlayer.getName() != null && incomingPlayer.getName().isEmpty() ? playerToUpdate.getName() : incomingPlayer.getName());
//            playerToUpdate.setAge(incomingPlayer.getAge() != null ? incomingPlayer.getAge() : playerToUpdate.getAge());
//            playerToUpdate.setRating(incomingPlayer.getRating() !=null ? incomingPlayer.getRating() : playerToUpdate.getRating());
//            playerToUpdate.setYearsOfExperience(incomingPlayer.getYearsOfExperience() !=null ? incomingPlayer.getYearsOfExperience() : playerToUpdate.getYearsOfExperience());
////        save the player back to the DB
////        return the player to the client
//            playerRepository.save(incomingPlayer);
//            return ResponseEntity.ok(playerToUpdate);}

//    //Rob's
//
//    @DeleteMapping("/{id}")
//    public void deletePlayer(@PathVariable Integer id) {
//        //find player to delete
//        Optional<Player> playerToDelete = playerRepository.findById(id);
//        //delete player
//        if(playerToDelete.isPresent()) {
//            playerRepository.deleteById(id);
//            throw new ResponseStatusException(HttpStatus.OK, "The player has been successfully deleted");
//        }
//        throw new ResponseStatusException(HttpStatus.OK, "The player was not found");
//
//    }

}
