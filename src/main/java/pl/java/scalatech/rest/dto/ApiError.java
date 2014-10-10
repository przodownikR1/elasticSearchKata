package pl.java.scalatech.rest.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author Sławomir Borowiec 
 * Module name : rest-neo4j-kata
 * Creating time :  20 mar 2014 12:01:06
 
 */
@RequiredArgsConstructor
public class ApiError {
    @Getter
    private @NonNull Integer code;
    @Getter
    private @NonNull String message;
    
    
}
