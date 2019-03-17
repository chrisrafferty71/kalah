package com.backbase.test.kalah.service;

import com.backbase.test.kalah.model.Game;
import com.backbase.test.kalah.model.Player;
import com.backbase.test.kalah.respository.Games;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class GameFactory {

    public static final int PLAYER_1_FIRST_PIT = 1;
    public static final int PLAYER_1_LAST_PIT = 6;
    public static final int PLAYER1_KALAH = 7;
    public static final int PLAYER_2_FIRST_PIT = 8;
    public static final int PLAYER_2_LAST_PIT = 13;
    public static final int PLAYER_2_KALAH = 14;

    private static final int STONES = 6;

    private AtomicInteger gameId = new AtomicInteger();

    /**
     * Builds an initialised game
     * @return the created game
     */
    public Game initGame() {
        Player player1 = new Player(PLAYER_1_FIRST_PIT, PLAYER_1_LAST_PIT, PLAYER1_KALAH);
        Player player2 = new Player(PLAYER_2_FIRST_PIT, PLAYER_2_LAST_PIT, PLAYER_2_KALAH);
        Game game = new Game(player1, player2);
        game.setId(String.valueOf(gameId.getAndIncrement()));
        game.setCurrentPlayer(player1);
        game.setState(getInitialState());
        return game;
    }

    private Map<Integer, Integer> getInitialState() {
        Map<Integer, Integer> state = new HashMap<>();
        addPits(PLAYER_1_FIRST_PIT, PLAYER_1_LAST_PIT, STONES, state);
        addPits(PLAYER1_KALAH, PLAYER1_KALAH, 0, state);
        addPits(PLAYER_2_FIRST_PIT, PLAYER_2_LAST_PIT, STONES, state);
        addPits(PLAYER_2_KALAH, PLAYER_2_KALAH, 0, state);
        return state;
    }

    private void addPits(int startId, int lastId, int stones, Map<Integer, Integer> state) {
        for (int i = startId; i <= lastId; i++) {
            state.put(i, stones);
        }
    }
}
