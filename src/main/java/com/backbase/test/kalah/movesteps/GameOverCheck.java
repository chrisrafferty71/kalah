package com.backbase.test.kalah.movesteps;

import com.backbase.test.kalah.model.Game;
import com.backbase.test.kalah.model.GameCompletionState;
import com.backbase.test.kalah.model.Player;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GameOverCheck {

    /**
     * Checks if the games has been finished, if so sweeps stones remaining into the Kalahs to work out the winner
     * and updates the game state
     * @param game the game after a move has completed
     */
    public void checkGameOver(Game game) {
        Map<Integer, Integer> state = game.getState();
        boolean player1Finished = playersPitsEmpty(state, game.getPlayer1());
        boolean player2Finished = playersPitsEmpty(state, game.getPlayer2());

        if (player1Finished || player2Finished) {
            if (player1Finished) accumulatePlayer(state, game.getPlayer2());
            if (player2Finished) accumulatePlayer(state, game.getPlayer1());

            if (state.get(game.getPlayer1().getKalah()) >
                    state.get(game.getPlayer2().getKalah())) {
                game.setGameCompletionState(GameCompletionState.PLAYER1);
            } else if (state.get(game.getPlayer2().getKalah()) >
                    state.get(game.getPlayer1().getKalah())) {
                game.setGameCompletionState(GameCompletionState.PLAYER2);
            } else {
                game.setGameCompletionState(GameCompletionState.DRAW);
            }
        }
    }

    private void accumulatePlayer(Map<Integer, Integer> state, Player player) {
        Integer total = 0;
        for (int i = player.getFirstPit(); i <= player.getLastPit(); i++) {
            total += state.get(i);
            state.put(i, 0);
        }
        state.put(player.getKalah(), state.get(player.getKalah())+total);
    }

    public boolean playersPitsEmpty(Map<Integer, Integer> state, Player player) {
        return pitsEmpty(state, player.getFirstPit(), player.getLastPit());
    }

    public boolean pitsEmpty(Map<Integer, Integer> state, Integer startPit, Integer lastPit) {
        for (int i = startPit; i <= lastPit; i++) {
            if (state.get(i) > 0) {
                return false;
            }
        }
        return true;
    }
}
