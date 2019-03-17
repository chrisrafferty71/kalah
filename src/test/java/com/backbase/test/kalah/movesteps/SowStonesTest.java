package com.backbase.test.kalah.movesteps;

import com.backbase.test.kalah.model.Game;
import com.backbase.test.kalah.model.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SowStonesTest {

    @Mock
    private Game game;

    @Mock
    private Player player1;

    @Mock
    private Player player2;

    private Map<Integer, Integer> state;

    private SowStones sowStones;

    @Before
    public void setup() {
        sowStones = new SowStones();
        state = new HashMap<>();
        when(game.getCurrentPlayer()).thenReturn(player1);
        when(game.getPlayer1()).thenReturn(player1);
        when(game.getPlayer2()).thenReturn(player2);
        when(game.getState()).thenReturn(state);
        when(player2.getKalah()).thenReturn(14);
        for (int i = 1; i <= 14; i++) {
            state.put(i, 0);
        }
    }

    @Test
    public void sow_addsToPits_rightPitsUpdated() {
        state.put(1, 3);
        state.put(2, 1);

        Integer lastPositionSowed = sowStones.sow(game, 1);

        assertThat(lastPositionSowed).isEqualTo(4);
        assertThat(state.get(1)).isEqualTo(0);
        assertThat(state.get(2)).isEqualTo(2);
        assertThat(state.get(3)).isEqualTo(1);
        assertThat(state.get(4)).isEqualTo(1);
    }

    @Test
    public void sow_pitsSpanOwnKalah_kalahUpdated() {
        state.put(6, 3);
        state.put(7, 1);

        Integer lastPositionSowed = sowStones.sow(game, 6);

        assertThat(lastPositionSowed).isEqualTo(9);
        assertThat(state.get(6)).isEqualTo(0);
        assertThat(state.get(7)).isEqualTo(2);
        assertThat(state.get(8)).isEqualTo(1);
        assertThat(state.get(9)).isEqualTo(1);
    }

    @Test
    public void soq_pitsSpanOpponentsKalah_opponents_Kalah() {
        state.put(13, 3);
        state.put(14, 5);
        state.put(1, 2);

        Integer lastPositionSowed = sowStones.sow(game, 13);

        assertThat(lastPositionSowed).isEqualTo(3);
        assertThat(state.get(13)).isEqualTo(0);
        assertThat(state.get(14)).isEqualTo(5);
        assertThat(state.get(1)).isEqualTo(3);
        assertThat(state.get(2)).isEqualTo(1);
        assertThat(state.get(3)).isEqualTo(1);
    }

}