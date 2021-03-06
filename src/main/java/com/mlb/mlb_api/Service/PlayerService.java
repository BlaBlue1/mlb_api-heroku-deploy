package com.mlb.mlb_api.Service;

import com.mlb.mlb_api.controllers.dto.PlayerDTO;
import com.mlb.mlb_api.repositories.entities.Player;

public interface PlayerService {

        Player save(PlayerDTO playerDTO);
        Player update(PlayerDTO playerDTO, Integer id);

        void delete(Integer playerId);

        Iterable<Player> findAll();
        Player findById(Integer playerId);
        //Query method



}
