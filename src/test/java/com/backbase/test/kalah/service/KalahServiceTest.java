package com.backbase.test.kalah.service;

import com.backbase.test.kalah.exception.GameWonException;
import com.backbase.test.kalah.exception.InvalidPitException;
import com.backbase.test.kalah.exception.NotFoundException;
import com.backbase.test.kalah.model.Game;
import com.backbase.test.kalah.model.GameCompletionState;
import com.backbase.test.kalah.responses.GameCreation;
import com.backbase.test.kalah.responses.GameState;
import com.backbase.test.kalah.respository.Games;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class KalahServiceTest {

    @org.junit.Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private Games games;

    @Mock
    private GameFactory gameFactory;

    @Mock
    private MoveProcessor moveProcessor;

    @Mock
    private Game game;

    private Map<Integer, Integer> state;

    private KalahService kalahService;


    @Before
    public void setup() {
        kalahService = new KalahService();
        state = new HashMap<>();
        ReflectionTestUtils.setField(kalahService, "moveProcessor", moveProcessor);
        ReflectionTestUtils.setField(kalahService, "gameFactory", gameFactory);
        ReflectionTestUtils.setField(kalahService, "games", games);
        ReflectionTestUtils.setField(kalahService, "serverHost", "testhost");
        ReflectionTestUtils.setField(kalahService, "serverPort", "4321");

        when(gameFactory.initGame()).thenReturn(game);
        when(game.getId()).thenReturn("1234");
    }

    @Test
    public void createGame_responseReturned_createdSuccessfully() {

        ResponseEntity<GameCreation> response = kalahService.createGame();

        verify(games).addGame(game);
        assertThat(response.getBody().getId()).isEqualTo(game.getId());
        assertThat(response.getBody().getUrl()).isEqualTo("http://testhost:4321/games/1234");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void gameMove_happyPath_responseReturned() {
        when(game.getState()).thenReturn(state);
        when(games.getGame("1")).thenReturn(game);

        ResponseEntity<GameState> response = kalahService.gameMove("1", "1");

        verify(moveProcessor).processMove(game, 1);
        assertThat(response.getBody().getId()).isEqualTo(game.getId());
        assertThat(response.getBody().getUrl()).isEqualTo("http://testhost:4321/games/1234");
        assertThat(response.getBody().getState()).isEqualTo(state);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void gameMove_gameDoesNotExist_exception() {
        when(games.getGame("1")).thenReturn(null);

        expectedException.expect(NotFoundException.class);
        expectedException.expectMessage("Game 1 not found");

        ResponseEntity<GameState> response = kalahService.gameMove("1", "1");
    }

    @Test
    public void gameMove_gameComplete_exception() {
        when(games.getGame("1")).thenReturn(game);
        when(game.getGameCompletionState()).thenReturn(GameCompletionState.PLAYER1);

        expectedException.expect(GameWonException.class);
        expectedException.expectMessage("Game has already been won by PLAYER1" );

        ResponseEntity<GameState> response = kalahService.gameMove("1", "1");
    }

    @Test
    public void gameMove_InvalidPitId_exception() {
        when(games.getGame("1")).thenReturn(game);

        expectedException.expect(InvalidPitException.class);
        expectedException.expectMessage("Pit not a number is invalid because it is not an integer" );

        ResponseEntity<GameState> response = kalahService.gameMove("1", "not a number");
    }

}