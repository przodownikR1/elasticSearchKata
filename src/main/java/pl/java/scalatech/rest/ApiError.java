package pl.java.scalatech.rest;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class ApiError {
    @Getter
    private @NonNull Integer code;
    @Getter
    private @NonNull String message;
    
    
}
