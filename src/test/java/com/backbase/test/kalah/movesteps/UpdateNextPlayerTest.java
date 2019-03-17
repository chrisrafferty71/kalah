package com.backbase.test.kalah.movesteps;

import com.backbase.test.kalah.model.Game;
import com.backbase.test.kalah.model.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class UpdateNextPlayerTest {

    @Mock
    private Game game;

    @Mock
    private Player player1;

    @Mock
    private Player player2;

    private UpdateNextPlayer updateNextPlayer;

    @Before
    public void setup() {
        updateNextPlayer = new UpdateNextPlayer();
        when(game.getCurrentPlayer()).thenReturn(player1);
        when(game.getPlayer1()).thenReturn(player1);
        when(game.getPlayer2()).thenReturn(player2);
        when(player1.getKalah()).thenReturn(7);
    }

    @Test
    public void updateNextPlayer_lastSowedOwnKalah_hasNextMove() {
        updateNextPlayer.updateNextPlayer(game, 7);
        assertThat(game.getCurrentPlayer()).isEqualTo(player1);
    }

    @Test
    public void updateNextPlayer_lastSowedNotOwnKalah_playerHasNextMove() {
        for (int i = 1; i <= 6; i++) {
            updateNextPlayer.updateNextPlayer(game, i);
            verify(game, times(i)).setCurrentPlayer(player2);
        }
        for (int i = 8; i <= 14; i++) {
            updateNextPlayer.updateNextPlayer(game, i);
            verify(game, times(i-1)).setCurrentPlayer(player2);
        }
    }

}