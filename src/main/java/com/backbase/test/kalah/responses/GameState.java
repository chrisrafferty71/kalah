package com.backbase.test.kalah.responses;

import com.backbase.test.kalah.model.Game;

import java.util.HashMap;
import java.util.Map;

public class GameState extends GameCreation {

    private Map<Integer, Integer> state = new HashMap<>();

    public GameState(Game game, String url) {
        super(game, url);
        state = game.getState();
    }


    public Map<Integer, Integer> getState() {
        return state;
    }

    public void setState(Map<Integer, Integer> state) {
        this.state = state;
    }
}
