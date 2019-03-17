package com.backbase.test.kalah.service;

import com.backbase.test.kalah.model.Game;
import com.backbase.test.kalah.movesteps.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MoveProcessorTest {
    @Mock
    private Game game;

    @Mock
    private ValidPitForMove validPitForMove;

    @Mock
    private SowStones sowStones;

    @Mock
    private StoneCapture stoneCapture;

    @Mock
    private UpdateNextPlayer updateNextPlayer;

    @Mock
    private GameOverCheck gameOverCheck;

    private Integer pitId = 1;
    private Integer lastPitSowed = 10;
    private MoveProcessor moveProcessor;

    @Before
    public void setup() {
        when(sowStones.sow(game, pitId)).thenReturn(lastPitSowed);
        moveProcessor = new MoveProcessor();
        ReflectionTestUtils.setField(moveProcessor, "validPitForMove", validPitForMove);
        ReflectionTestUtils.setField(moveProcessor, "sowStones", sowStones);
        ReflectionTestUtils.setField(moveProcessor, "stoneCapture", stoneCapture);
        ReflectionTestUtils.setField(moveProcessor, "updateNextPlayer", updateNextPlayer);
        ReflectionTestUtils.setField(moveProcessor, "gameOverCheck", gameOverCheck);
    }

    @Test
    public void processMove_called_allStepsCalled() {

        moveProcessor.processMove(game, pitId);

        verify(validPitForMove).checkValidPit(game, pitId);
        verify(sowStones).sow(game, pitId);
        verify(stoneCapture).stoneCapture(game, lastPitSowed);
        verify(updateNextPlayer).updateNextPlayer(game, lastPitSowed);
        verify(gameOverCheck).checkGameOver(game);
    }


}