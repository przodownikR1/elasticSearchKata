package pl.java.scalatech.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Sławomir Borowiec
 *         Module name : poc
 *         Creating time : 22 kwi 2014 12:14:34
 */

@NoArgsConstructor
@AllArgsConstructor
public class FieldErrorDTO {

    @Getter
    private String field;
    @Getter
    private String errorCode;
    @Getter
    private String message;
}
