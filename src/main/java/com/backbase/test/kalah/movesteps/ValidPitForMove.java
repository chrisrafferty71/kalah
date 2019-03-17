package com.backbase.test.kalah.movesteps;

import com.backbase.test.kalah.exception.InvalidPitException;
import com.backbase.test.kalah.model.Game;
import org.springframework.stereotype.Component;

import static com.backbase.test.kalah.movesteps.utils.MoveStepsUtils.isPitIdOwnedByCurrentPlayer;

@Component
public class ValidPitForMove {

    /**
     * Check if the select pit is valid
     * @param game the game being played
     * @param pitId the pit selected to move
     */
    public void checkValidPit(Game game, Integer pitId) {
        if (!isPitIdOwnedByCurrentPlayer(pitId, game)) {
            throw new InvalidPitException(pitId.toString(), "it does not belong to current player");
        }
        if (game.getState().get(pitId) == 0) {
            throw new InvalidPitException(pitId.toString(), "it is empty");
        }
    }

}
