package com.backbase.test.kalah.controller;

import com.backbase.test.kalah.responses.GameCreation;
import com.backbase.test.kalah.responses.GameState;
import com.backbase.test.kalah.service.KalahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class KalahController {

    @Autowired
    KalahService kalahService;

    @PostMapping(value = "/games", produces = "application/json")
    public ResponseEntity<GameCreation> createGame() {
        return kalahService.createGame();
    }

    @PutMapping(value = "/games/{gameId}/pits/{pitId}", produces = "application/json")
    public ResponseEntity<GameState> gameMove(@PathVariable("gameId") String gameId, @PathVariable("pitId") String pitId) {
        return kalahService.gameMove(gameId, pitId);
    }

}
