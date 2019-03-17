package com.backbase.test.kalah.service;

import com.backbase.test.kalah.exception.GameWonException;
import com.backbase.test.kalah.exception.InvalidPitException;
import com.backbase.test.kalah.exception.NotFoundException;
import com.backbase.test.kalah.model.Game;
import com.backbase.test.kalah.responses.GameCreation;
import com.backbase.test.kalah.responses.GameState;
import com.backbase.test.kalah.respository.Games;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class KalahService {

    @Value("${server.host}")
    private String serverHost;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private Games games;

    @Autowired
    private GameFactory gameFactory;

    @Autowired
    private MoveProcessor moveProcessor;

    /**
     * Creates an initialised game with all the correct state
     * @return details of the game, including id
     */
    public ResponseEntity<GameCreation> createGame() {
        Game game = gameFactory.initGame();
        games.addGame(game);
        GameCreation gameCreation = new GameCreation(game, getGameUrl(game));
        return new ResponseEntity<>(gameCreation, HttpStatus.CREATED);
    }

    /**
     * Updates the game state based on the requested move
     * @param gameId identifies the game being played
     * @param pitId identifies the pit selected in the players move
     * @return an updated game state including the distribution of stones across all locations
     * @throws NotFoundException if the supplied game id does not match an existing game
     * @throws GameWonException if the requested move cannot be performed because the game is finished
     * @throws InvalidPitException if the pit supplied is invalid, i.e. not a number or doesn't belong to the current player
     */
    public ResponseEntity<GameState> gameMove(String gameId, String pitId) {
        Game game = games.getGame(gameId);
        if (game != null) {
            if (checkNotWon(game)) {
                try {
                    moveProcessor.processMove(game, Integer.parseInt(pitId));
                } catch (NumberFormatException e) {
                    throw new InvalidPitException(pitId, "it is not an integer");
                }
                GameState gameState = new GameState(game, getGameUrl(game));
                return new ResponseEntity<>(gameState, HttpStatus.OK);
            }
        }
        throw new NotFoundException(gameId);
    }

    private boolean checkNotWon(Game game) {
        if (game.getGameCompletionState() != null) {
            throw new GameWonException(game.getGameCompletionState());
        }
        return true;
    }

    private String getGameUrl(Game game) {
        return "http://" + serverHost + ":" + serverPort + "/games/" + game.getId();
    }

}
