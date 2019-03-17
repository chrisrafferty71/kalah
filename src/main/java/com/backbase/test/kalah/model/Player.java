package com.backbase.test.kalah.model;

public class Player {

    private int first_pit;

    private int last_pit;

    private int kalah;

    public Player(int first_pit, int last_pit, int kalah) {
        this.first_pit = first_pit;
        this.last_pit = last_pit;
        this.kalah = kalah;
    }

    public int getFirstPit() {
        return first_pit;
    }

    public int getLastPit() {
        return last_pit;
    }

    public int getKalah() {
        return kalah;
    }
}
