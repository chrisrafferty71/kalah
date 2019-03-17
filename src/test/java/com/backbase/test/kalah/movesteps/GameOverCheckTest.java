package com.backbase.test.kalah.movesteps;

import com.backbase.test.kalah.model.Game;
import com.backbase.test.kalah.model.GameCompletionState;
import com.backbase.test.kalah.model.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameOverCheckTest {
    @Mock
    private Game game;

    @Mock
    private Player player1;

    @Mock
    private Player player2;

    private Map<Integer, Integer> state;

    private GameOverCheck gameOverCheck;

    @Before
    public void setup() {
        gameOverCheck = new GameOverCheck();
        state = new HashMap<>();
        when(game.getPlayer1()).thenReturn(player1);
        when(game.getPlayer2()).thenReturn(player2);
        when(game.getState()).thenReturn(state);
        when(player1.getFirstPit()).thenReturn(1);
        when(player1.getLastPit()).thenReturn(6);
        when(player1.getKalah()).thenReturn(7);
        when(player2.getFirstPit()).thenReturn(8);
        when(player2.getLastPit()).thenReturn(13);
        when(player2.getKalah()).thenReturn(14);
        for (int i = 1; i <= 14; i++) {
            state.put(i, 0);
        }
    }

    @Test
    public void checkGameOver_nobodyFinished_stillPlaying() {
        state.put(1,1);
        state.put(8,1);
        gameOverCheck.checkGameOver(game);
        assertThat(game.getGameCompletionState()).isNull();
    }

    @Test
    public void checkGameOver_player1FinishedPlayerOneWins_statePlayer1Wins() {
        state.put(7,5);
        state.put(8,1);
        state.put(9,1);
        gameOverCheck.checkGameOver(game);
        verify(game).setGameCompletionState(GameCompletionState.PLAYER1);
        assertThat(state.get(8)).isEqualTo(0);
        assertThat(state.get(14)).isEqualTo(2);
    }

    @Test
    public void checkGameOver_player1FinishedPlayerTwoWins_statePlayer2Wins() {
        state.put(14,5);
        state.put(8,1);
        gameOverCheck.checkGameOver(game);
        verify(game).setGameCompletionState(GameCompletionState.PLAYER2);
        assertThat(state.get(8)).isEqualTo(0);
        assertThat(state.get(14)).isEqualTo(6);
        assertThat(state.get(7)).isEqualTo(0);
    }

    @Test
    public void checkGameOver_player2FinishedPlayer1Wins_statePlayer1Wins() {
        state.put(7,5);
        state.put(1,1);
        gameOverCheck.checkGameOver(game);
        verify(game).setGameCompletionState(GameCompletionState.PLAYER1);
        assertThat(state.get(1)).isEqualTo(0);
        assertThat(state.get(14)).isEqualTo(0);
        assertThat(state.get(7)).isEqualTo(6);
    }

    @Test
    public void checkGameOver_player2FinishedPlayer2Wins_statePlayer2Wins() {
        state.put(14,5);
        state.put(1,1);
        gameOverCheck.checkGameOver(game);
        verify(game).setGameCompletionState(GameCompletionState.PLAYER2);
        assertThat(state.get(1)).isEqualTo(0);
        assertThat(state.get(14)).isEqualTo(5);
        assertThat(state.get(7)).isEqualTo(1);
    }

    @Test
    public void checkGameOver_playerFinishedDraw_stateDraw() {
        state.put(14,5);
        state.put(1,5);
        gameOverCheck.checkGameOver(game);
        verify(game).setGameCompletionState(GameCompletionState.DRAW);
        assertThat(state.get(1)).isEqualTo(0);
        assertThat(state.get(14)).isEqualTo(5);
        assertThat(state.get(7)).isEqualTo(5);
    }

}