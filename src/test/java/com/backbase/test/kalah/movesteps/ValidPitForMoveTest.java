package com.backbase.test.kalah.movesteps;

import com.backbase.test.kalah.exception.InvalidPitException;
import com.backbase.test.kalah.model.Game;
import com.backbase.test.kalah.model.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ValidPitForMoveTest {
    @org.junit.Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private Game game;

    @Mock
    private Player player;

    private ValidPitForMove validPitForMove;

    @Before
    public void setup() {
        validPitForMove = new ValidPitForMove();
        when(game.getCurrentPlayer()).thenReturn(player);
        when(player.getFirstPit()).thenReturn(1);
        when(player.getLastPit()).thenReturn(6);
    }

    @Test
    public void checkValidPit_emptyPit_exception() {
        Map<Integer, Integer> state = new HashMap<>();
        state.put(1, 0);
        when(game.getState()).thenReturn(state);

        expectedException.expect(InvalidPitException.class);
        expectedException.expectMessage("Pit 1 is invalid because it is empty");

        validPitForMove.checkValidPit(game, 1);
    }

    @Test
    public void checkValidPit_notOwnedByPlayer_exception() {
        Map<Integer, Integer> state = new HashMap<>();
        when(game.getCurrentPlayer()).thenReturn(player);

        expectedException.expect(InvalidPitException.class);
        expectedException.expectMessage("Pit 8 is invalid because it does not belong to current player");

        validPitForMove.checkValidPit(game, 8);
    }

    @Test
    public void checkValidPit_pitIdTolarge_exception() {
        Map<Integer, Integer> state = new HashMap<>();
        when(game.getCurrentPlayer()).thenReturn(player);

        expectedException.expect(InvalidPitException.class);
        expectedException.expectMessage("Pit 200 is invalid because it does not belong to current player");

        validPitForMove.checkValidPit(game, 200);
    }

    @Test
    public void checkValidPit_pitIdNegative_exception() {
        Map<Integer, Integer> state = new HashMap<>();
        when(game.getCurrentPlayer()).thenReturn(player);

        expectedException.expect(InvalidPitException.class);
        expectedException.expectMessage("Pit -1 is invalid because it does not belong to current player");

        validPitForMove.checkValidPit(game, -1);
    }

}