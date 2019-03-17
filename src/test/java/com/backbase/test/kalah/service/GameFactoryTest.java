package com.backbase.test.kalah.service;

import com.backbase.test.kalah.model.Game;
import com.backbase.test.kalah.model.Player;
import com.backbase.test.kalah.respository.Games;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class GameFactoryTest {

    @Mock
    Games games;

    @Test
    public void initGame_createGame_GameStateCorrect() {
        GameFactory gameFactory = new GameFactory();

        Game game = gameFactory.initGame();

        assertThat(game.getId()).isEqualTo("0");
        assertThat(game.getCurrentPlayer()).isEqualTo(game.getPlayer1());
        assertThat(game.getGameCompletionState()).isNull();

        Player player1 = game.getPlayer1();
        assertThat(player1.getKalah()).isEqualTo(7);
        assertThat(player1.getFirstPit()).isEqualTo(1);
        assertThat(player1.getLastPit()).isEqualTo(6);

        Player player2 = game.getPlayer2();
        assertThat(player2.getKalah()).isEqualTo(14);
        assertThat(player2.getFirstPit()).isEqualTo(8);
        assertThat(player2.getLastPit()).isEqualTo(13);

        assertThat(game.getState().size()).isEqualTo(14);
        assertThat(game.getState().keySet()).contains(1,2,3,4,5,6,7,8,9,10,11,12,13,14);
        assertThat(game.getState().values()).contains(6,6,6,6,6,6,0,6,6,6,6,6,6,0);
    }

}