package pl.java.scalatech.rest;

import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Sławomir Borowiec
 *         Module name : poc
 *         Creating time : 22 kwi 2014 13:45:21
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
@NoArgsConstructor
public class DomainException extends RuntimeException {

    private static final long serialVersionUID = -7211084186666693740L;

    @Getter
    protected Long id;

    protected String name;

    public DomainException(Long id, String name) {
        super(name + "# " + id + " was not found");

        this.id = id;
        this.name = name;
    }

}
