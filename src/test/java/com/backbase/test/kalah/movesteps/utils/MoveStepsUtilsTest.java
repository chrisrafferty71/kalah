package com.backbase.test.kalah.movesteps.utils;

import com.backbase.test.kalah.model.Game;
import com.backbase.test.kalah.model.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MoveStepsUtilsTest {

    @Mock
    Game game;

    @Mock
    Player player;

    @Before
    public void setup() {
        when(game.getCurrentPlayer()).thenReturn(player);
        when(player.getFirstPit()).thenReturn(1);
        when(player.getLastPit()).thenReturn(6);
    }

    @Test
    public void moveStepsUtils_playerOwnsId_trueReturned(){
        assertThat(MoveStepsUtils.isPitIdOwnedByCurrentPlayer(4, game)).isTrue();
        assertThat(MoveStepsUtils.isPitIdOwnedByCurrentPlayer(1, game)).isTrue();
        assertThat(MoveStepsUtils.isPitIdOwnedByCurrentPlayer(6, game)).isTrue();
    }

    @Test
    public void moveStepsUtils_doesNotOwnId_falseReturned(){
        assertThat(MoveStepsUtils.isPitIdOwnedByCurrentPlayer(14, game)).isFalse();
        assertThat(MoveStepsUtils.isPitIdOwnedByCurrentPlayer(7, game)).isFalse();
        assertThat(MoveStepsUtils.isPitIdOwnedByCurrentPlayer(0, game)).isFalse();
    }

}