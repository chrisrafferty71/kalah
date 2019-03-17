package com.backbase.test.kalah.exception;

import com.backbase.test.kalah.model.Player;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidPitException extends RuntimeException {
    public InvalidPitException(String pitId, String reason) {
        super("Pit " + pitId +" is invalid because " + reason);
    }
}
