package com.backbase.test.kalah.movesteps;

import com.backbase.test.kalah.model.Game;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class SowStones {

    /**
     * Cycles through the pits sowing the stones from the pit being played
     * Starts with the next pit along, includes sowing into the players own kalah, excludes the opponents kalah
     * @param game game be played
     * @param pitId identifies the pit to sow stone from
     * @return position of the last stone sowed
     */
    public Integer sow(Game game, Integer pitId) {
        Map<Integer, Integer> state = game.getState();
        Integer stonesToSow = collectFromPit(state, pitId);
        Integer pitToUpdate = pitId;
        while (stonesToSow > 0) {
            pitToUpdate = pitToUpdate + 1 > state.size() ? 1 : pitToUpdate + 1;
            if (!isKalah(pitToUpdate, game) || game.getCurrentPlayer().getKalah() == pitToUpdate) {
                state.put(pitToUpdate, state.get(pitToUpdate) + 1);
                stonesToSow--;
            }
        }
        return pitToUpdate;
    }

    private Integer collectFromPit(Map<Integer, Integer> state, Integer pitId) {
        Integer pitStones = state.get(pitId);
        state.put(pitId, 0);
        return pitStones;
    }

    private boolean isKalah(Integer pitId, Game game) {
        return pitId == game.getPlayer1().getKalah() || pitId == game.getPlayer2().getKalah();
    }
}
