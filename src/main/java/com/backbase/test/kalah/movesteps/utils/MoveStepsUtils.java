package com.backbase.test.kalah.movesteps.utils;

import com.backbase.test.kalah.model.Game;

public class MoveStepsUtils {

    public static boolean isPitIdOwnedByCurrentPlayer(Integer pitId, Game game) {
        return game.getCurrentPlayer().getFirstPit() <= pitId &&
                game.getCurrentPlayer().getLastPit() >= pitId;
    }

}
