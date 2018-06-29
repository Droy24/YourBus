package com.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Global exception handler class for all the HTTP type requests.
 * 
 * @author Pardeep
 * @version 1.0
 */
@ControllerAdvice
public class GlobalException {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Handler class for BadRequestException
	 *
	 * @param exception
	 *            the BadRequestException object as parameter
	 * 
	 * @return the response entity.
	 * 
	 * @throws JsonProcessingException
	 */
	@ExceptionHandler(value = BadRequestException.class)
	public ResponseEntity<?> handleBadRequest(BadRequestException exception) throws JsonProcessingException {
		HttpStatusCodeErrorMessage forbiddenResponse = new HttpStatusCodeErrorMessage();
		logger.error(exception.getMessage(), exception);
		if (exception.getMessage() != null && !exception.getMessage().isEmpty()) {
			forbiddenResponse.setMessage(exception.getMessage());
			return new ResponseEntity<>(forbiddenResponse, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Handler class for ForbiddenException
	 *
	 * @param exception
	 *            the ForbiddenException object as parameter
	 * 
	 * @return the response entity.
	 * 
	 * @throws JsonProcessingException
	 */
	@ExceptionHandler(value = ForbiddenException.class)
	public ResponseEntity<?> handleForbiddenException(ForbiddenException exception) throws JsonProcessingException {
		HttpStatusCodeErrorMessage forbiddenResponse = new HttpStatusCodeErrorMessage();
		logger.error(exception.getMessage(), exception);
		if (exception.getMessage() != null && !exception.getMessage().isEmpty()) {
			forbiddenResponse.setMessage(exception.getMessage());
			return new ResponseEntity<>(forbiddenResponse, HttpStatus.FORBIDDEN);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	/**
	 * Handler class for UnauthorizedException
	 *
	 * @param exception
	 *            the UnauthorizedException object as parameter
	 * 
	 * @return the response entity.
	 * 
	 * @throws JsonProcessingException
	 */
	@ExceptionHandler(value = UnauthorizedException.class)
	public ResponseEntity<?> handleUnauthorizedException(UnauthorizedException exception)
			throws JsonProcessingException {
		HttpStatusCodeErrorMessage forbiddenResponse = new HttpStatusCodeErrorMessage();
		logger.error(exception.getMessage(), exception);
		if (exception.getMessage() != null && !exception.getMessage().isEmpty()) {
			forbiddenResponse.setMessage(exception.getMessage());
			return new ResponseEntity<>(forbiddenResponse, HttpStatus.UNAUTHORIZED);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	/**
	 * Handler class for NotFoundException
	 *
	 * @param exception
	 *            the NotFoundException object as parameter
	 * 
	 * @return the response entity.
	 * 
	 * @throws JsonProcessingException
	 */
	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity<?> handleNotFoundException(NotFoundException exception) throws JsonProcessingException {
		HttpStatusCodeErrorMessage forbiddenResponse = new HttpStatusCodeErrorMessage();
		logger.error(exception.getMessage(), exception);
		if (exception.getMessage() != null && !exception.getMessage().isEmpty()) {
			forbiddenResponse.setMessage(exception.getMessage());
			return new ResponseEntity<>(forbiddenResponse, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Handler class for UnprocessableEntityException
	 *
	 * @param exception
	 *            the UnprocessableEntityException object as parameter
	 * 
	 * @return the response entity.
	 * 
	 * @throws JsonProcessingException
	 */
	@ExceptionHandler(value = UnprocessableEntityException.class)
	public ResponseEntity<?> handleUnprocessableEntityException(UnprocessableEntityException exception)
			throws JsonProcessingException {
		HttpStatusCodeErrorMessage forbiddenResponse = new HttpStatusCodeErrorMessage();
		logger.error(exception.getMessage(), exception);
		if (exception.getMessage() != null && !exception.getMessage().isEmpty()) {
			forbiddenResponse.setMessage(exception.getMessage());
			return new ResponseEntity<>(forbiddenResponse, HttpStatus.UNPROCESSABLE_ENTITY);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	/**
	 * Handler class for ForbiddenException
	 *
	 * @param exception
	 *            the ForbiddenException object as parameter
	 * 
	 * @return the response entity.
	 * 
	 * @throws JsonProcessingException
	 */
	@ExceptionHandler(value = UserDeletedException.class)
	public ResponseEntity<?> handleUserDeletedException(UserDeletedException exception) throws JsonProcessingException {
		HttpStatusCodeErrorMessage forbiddenResponse = new HttpStatusCodeErrorMessage();
		logger.error(exception.getMessage(), exception);
		if (exception.getMessage() != null && !exception.getMessage().isEmpty()) {
			forbiddenResponse.setMessage(exception.getMessage());
			return new ResponseEntity<>(forbiddenResponse, HttpStatus.FORBIDDEN);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	/**
	 * Handler class for all other unhandily Exception
	 *
	 * @param exception
	 *            the Exception object as parameter
	 * 
	 * @return the response entity.
	 * 
	 * @throws JsonProcessingException
	 */
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<?> handleGlobal(Exception exception) throws JsonProcessingException {
		HttpStatusCodeErrorMessage forbiddenResponse = new HttpStatusCodeErrorMessage();
		logger.error(exception.getMessage(), exception);
		forbiddenResponse.setMessage("Flash server is unable to process this request.");
		return new ResponseEntity<>(forbiddenResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * HTTP status code handler class for all error type messages
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	class HttpStatusCodeErrorMessage 
	{
		private String message;

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
}
