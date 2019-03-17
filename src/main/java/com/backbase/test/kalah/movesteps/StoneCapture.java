package com.backbase.test.kalah.movesteps;

import com.backbase.test.kalah.model.Game;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.backbase.test.kalah.movesteps.utils.MoveStepsUtils.isPitIdOwnedByCurrentPlayer;

@Component
public class StoneCapture {

    /**
     * Checks if the current player can capture the opposite pit based on the position of the last stone sowed
     * @param game game being played
     * @param lastPitIdSowed position of the last stone sowed
     */
    public void stoneCapture(Game game, Integer lastPitIdSowed) {
        Map<Integer, Integer> state = game.getState();
        if (isPitIdOwnedByCurrentPlayer(lastPitIdSowed, game) &&
                state.get(lastPitIdSowed) == 1 &&
                lastPitIdSowed != game.getCurrentPlayer().getKalah()) {
            state.put(lastPitIdSowed, 0);
            Integer opposite = calcOpposite(state, lastPitIdSowed);
            Integer captured = state.get(opposite) + 1;
            state.put(opposite, 0);
            Integer kalahId = game.getCurrentPlayer().getKalah();
            Integer kalahValue = state.get(kalahId);
            state.put(kalahId, kalahValue+captured);
        }
    }

    private Integer calcOpposite(Map<Integer, Integer> state, Integer lastPitIdSowed) {
        return state.size() - lastPitIdSowed;
    }
}
