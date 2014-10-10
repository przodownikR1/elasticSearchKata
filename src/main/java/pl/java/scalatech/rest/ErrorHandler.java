package pl.java.scalatech.rest;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import pl.java.scalatech.rest.dto.ErrorHolder;

/**
 * @author Sławomir Borowiec
 *         Module name : poc
 *         Creating time : 23 kwi 2014 11:45:47 @param <Ex>
 */
@Slf4j
public abstract class ErrorHandler<Ex> {

	protected MessageSource messageSource;

	public ErrorHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	protected HttpHeaders createJsonHeader() {
		List<MediaType> acceptableMediaTypes = new ArrayList<>();
		acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(acceptableMediaTypes);
		return headers;
	}

	protected void setUrl(ErrorHolder eHolder, WebRequest webRequest) {
		log.info("+++       create link    {}  , eholder    {}  ",webRequest,eHolder);
		String location = ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}").buildAndExpand(eHolder.getEntityId()).toUriString();
		// TODO
		eHolder.setUrl(location);
	}

	public abstract ResponseEntity<ErrorHolder> concreteExceptionHandler(Ex ex, WebRequest webRequest);

	protected void setErrorCode(ErrorHolder eHolder, Ex ex) {
		// TODO
		eHolder.setErrorCode("30031");
	}

}
