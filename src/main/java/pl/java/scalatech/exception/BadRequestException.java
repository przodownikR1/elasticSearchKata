package pl.java.scalatech.exception;

import lombok.ToString;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Sławomir Borowiec
 *         Module name : poc
 *         Creating time : 23 kwi 2014 11:52:58
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
@ToString
public class BadRequestException extends RestException {
    private static final long serialVersionUID = -6245854960880201367L;

}
