package pl.java.scalatech.rest;

import lombok.extern.slf4j.Slf4j;

//import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;


/**
 * @author Sławomir Borowiec
 *         Module name : poc
 *         Creating time : 23 kwi 2014 11:50:02
 */
@ControllerAdvice
@Slf4j
public class AccessDeniedHandler{} /*extends ErrorHandler<AccessDeniedException> {

	@Autowired
	public AccessDeniedHandler(MessageSource messageSource) {
		super(messageSource);
	}

	@Override
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public ResponseEntity<ErrorHolder> concreteExceptionHandler(AccessDeniedException ex, WebRequest webRequest) {
		log.error("++ 			 " + "AccessDeniedHandler  {}  ", ex);
		ErrorHolder eHolder = new ErrorHolder(ex.getMessage(), HttpStatus.UNAUTHORIZED);
		setErrorCode(eHolder, ex);
		setUrl(eHolder, webRequest);
		return new ResponseEntity<>(eHolder, createJsonHeader(), HttpStatus.UNAUTHORIZED);
	}

}
*/