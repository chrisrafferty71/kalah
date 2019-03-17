package com.backbase.test.kalah.respository;

import com.backbase.test.kalah.model.Game;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class Games {

    private Map<String, Game> games = new HashMap<>();

    public Game getGame(String gameId) {
        return games.get(gameId);
    }

    public void addGame(Game toAdd) {
        games.put(toAdd.getId(), toAdd);
    }
}
