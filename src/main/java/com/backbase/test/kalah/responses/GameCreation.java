package com.backbase.test.kalah.responses;

import com.backbase.test.kalah.model.Game;

public class GameCreation {

    private String id;

    private String url;

    public GameCreation(Game game, String url) {
        this.id = game.getId();
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
