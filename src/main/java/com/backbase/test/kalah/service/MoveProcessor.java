package com.backbase.test.kalah.service;

import com.backbase.test.kalah.model.Game;
import com.backbase.test.kalah.movesteps.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoveProcessor {

    @Autowired
    ValidPitForMove validPitForMove;

    @Autowired
    SowStones sowStones;

    @Autowired
    StoneCapture stoneCapture;

    @Autowired
    UpdateNextPlayer updateNextPlayer;

    @Autowired
    GameOverCheck gameOverCheck;

    /**
     * Processes the requested move and updates the game state
     * @param game the game being played
     * @param pitId identifies the pit for the move
     * @throws com.backbase.test.kalah.exception.InvalidPitException if the pit does not belong to the player or is empty
     */
    public void processMove(Game game, Integer pitId) {
        validPitForMove.checkValidPit(game, pitId);
        Integer lastPitIdSowed = sowStones.sow(game, pitId);
        stoneCapture.stoneCapture(game, lastPitIdSowed);
        updateNextPlayer.updateNextPlayer(game, lastPitIdSowed);
        gameOverCheck.checkGameOver(game);
    }
}
