package com.backbase.test.kalah.exception;

import com.backbase.test.kalah.model.GameCompletionState;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GameWonException extends RuntimeException {
    public GameWonException(GameCompletionState finalState) {
        super("Game has already been won by " + finalState.toString());
    }
}
