package com.backbase.test.kalah.model;

import java.util.HashMap;
import java.util.Map;

public class Game {
    private String id;

    private Map<Integer, Integer> state = new HashMap<>();

    private Player currentPlayer;

    private Player player1;

    private Player player2;

    private GameCompletionState finalState;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<Integer, Integer> getState() {
        return state;
    }

    public void setState(Map<Integer, Integer> state) {
        this.state = state;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public GameCompletionState getGameCompletionState() {
        return this.finalState;
    }

    public void setGameCompletionState(GameCompletionState gameCompletionState) {
        this.finalState = gameCompletionState;
    }
}
