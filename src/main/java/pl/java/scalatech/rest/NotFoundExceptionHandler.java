package pl.java.scalatech.rest;

import java.util.Locale;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import pl.java.scalatech.rest.dto.ApiError;


@ControllerAdvice
@Slf4j
public class NotFoundExceptionHandler {
    private final static String CATEGORY_NOT_FOUND_ID = "category_404_id_error";
    private final static String CATEGORY_NOT_FOUND_NAME = "category_404_name_error";
    private MessageSource messageSource;

    protected Locale locale = Locale.getDefault();

    @Autowired
    public NotFoundExceptionHandler(MessageSource messageSource) {
        log.info("+++              NotFoundExceptionHandler()");  
        this.messageSource = messageSource;
    }

    @ExceptionHandler({ DomainException.class })
    public ResponseEntity<?> handlePersonNotFound(DomainException ce) {

        return new ResponseEntity<>(new ApiError(HttpStatus.NOT_FOUND.value(), messageSource.getMessage(CATEGORY_NOT_FOUND_ID, new Object[] { ce.getId() },
                locale)), HttpStatus.NOT_FOUND);

    }

}
