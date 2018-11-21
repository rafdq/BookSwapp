package bs.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler
{
	@ExceptionHandler
	public ResponseEntity<UserErrorReponse> handleException(UserNotFoundException exc)
	{
		UserErrorReponse error = new UserErrorReponse(HttpStatus.NOT_FOUND.value(), exc.getMessage());

		return new ResponseEntity<UserErrorReponse>(error, HttpStatus.NOT_FOUND);

	}
}
