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
public class StoneCaptureTest {

    @Mock
    private Game game;

    @Mock
    private Player player;

    private Map<Integer, Integer> state;

    private StoneCapture stoneCapture;

    @Before
    public void setup() {
        stoneCapture = new StoneCapture();
        state = new HashMap<>();
        when(game.getCurrentPlayer()).thenReturn(player);
        when(game.getState()).thenReturn(state);
        when(player.getFirstPit()).thenReturn(1);
        when(player.getLastPit()).thenReturn(6);
        when(player.getKalah()).thenReturn(7);
        for (int i = 1; i <= 14; i++) {
            state.put(i, 0);
        }
    }

    @Test
    public void stoneCapture_lastStoneOwnPitEmpty_stonesCaptured() {
        state.put(1, 1);
        state.put(13, 2);
        stoneCapture.stoneCapture(game, 1);
        assertThat(state.get(7)).isEqualTo(3);
        assertThat(state.get(1)).isEqualTo(0);
        assertThat(state.get(13)).isEqualTo(0);

        state.put(6, 1);
        state.put(8, 5);
        stoneCapture.stoneCapture(game, 6);
        assertThat(state.get(7)).isEqualTo(9);
        assertThat(state.get(6)).isEqualTo(0);
        assertThat(state.get(8)).isEqualTo(0);
    }

    @Test
    public void stoneCapture_lastStoneOwnPitNotEmpty_stonesNotCaptured() {
        state.put(1, 2);
        state.put(13, 2);
        stoneCapture.stoneCapture(game, 1);
        assertThat(state.get(7)).isEqualTo(0);
        assertThat(state.get(1)).isEqualTo(2);
        assertThat(state.get(13)).isEqualTo(2);
    }

    @Test
    public void stoneCapture_lastStoneOpponentsPit_stonesNotCaptured() {
        state.put(8, 2);
        state.put(6, 2);
        stoneCapture.stoneCapture(game, 8);
        assertThat(state.get(7)).isEqualTo(0);
        assertThat(state.get(8)).isEqualTo(2);
        assertThat(state.get(6)).isEqualTo(2);
    }

    @Test
    public void stoneCapture_lastStoneOwnKalah_stonesNotCaptured() {
        state.put(7, 2);
        state.put(14, 1);
        stoneCapture.stoneCapture(game, 7);
        assertThat(state.get(7)).isEqualTo(2);
        assertThat(state.get(14)).isEqualTo(1);
    }

    @Test
    public void stoneCapture_lastStoneOpponentsKalah_stonesNotCaptured() {
        state.put(7, 2);
        state.put(14, 1);
        stoneCapture.stoneCapture(game, 14);
        assertThat(state.get(7)).isEqualTo(2);
        assertThat(state.get(14)).isEqualTo(1);
    }

}