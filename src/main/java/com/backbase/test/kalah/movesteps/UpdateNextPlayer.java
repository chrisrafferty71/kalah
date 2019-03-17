package com.backbase.test.kalah.movesteps;

import com.backbase.test.kalah.model.Game;
import org.springframework.stereotype.Component;

@Component
public class UpdateNextPlayer {

    /**
     * Updates who the next player is based on the location of the last stone sowed
     * @param game game being played
     * @param lastPitIdSowed position of the last stone sowed
     */
    public void updateNextPlayer(Game game, Integer lastPitIdSowed) {
        if (game.getCurrentPlayer().getKalah() != lastPitIdSowed) {
            if (game.getCurrentPlayer().equals(game.getPlayer1())) {
                game.setCurrentPlayer(game.getPlayer2());
            } else {
                game.setCurrentPlayer(game.getPlayer1());
            }
        }
    }
}
