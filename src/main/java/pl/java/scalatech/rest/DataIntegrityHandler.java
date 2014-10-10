package pl.java.scalatech.rest;

import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Sławomir Borowiec
 *         Module name : poc
 *         Creating time : 23 kwi 2014 16:10:17
 */
@ControllerAdvice
public class DataIntegrityHandler {
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleConflict() {
        // TODO
        return "409+++";
    }

    @ExceptionHandler({ SQLException.class, DataAccessException.class })
    public String databaseError() {
        // TODO
        return "databaseError";
    }

}
