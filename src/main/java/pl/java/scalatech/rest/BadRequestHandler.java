package pl.java.scalatech.rest;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import pl.java.scalatech.exception.BadRequestException;
import pl.java.scalatech.rest.dto.ErrorHolder;

/**
 * @author Sławomir Borowiec
 *         Module name : poc
 *         Creating time : 23 kwi 2014 11:53:43
 */
@ControllerAdvice
@Slf4j
public class BadRequestHandler extends ErrorHandler<BadRequestException>{

	@Autowired
	public BadRequestHandler(MessageSource messageSource) {
		super(messageSource);
		log.error("++                  " + "BadRequestHandler  {}  ");
	}


	@Override
	@ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class, BadRequestException.class, NullPointerException.class })
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorHolder> concreteExceptionHandler(BadRequestException ex, WebRequest webRequest) {
		log.error("++  				" + "BadRequestHandler  {}  ", ex);
		List<MediaType> acceptableMediaTypes = new ArrayList<>();
		acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(acceptableMediaTypes);
		ErrorHolder eHolder = new ErrorHolder(ex.getMessage(), HttpStatus.BAD_REQUEST);
		setErrorCode(eHolder, ex);
		setUrl(eHolder,webRequest);
		return new ResponseEntity<>(eHolder, headers, HttpStatus.BAD_REQUEST);
	}
}
