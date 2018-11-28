package bs.controller.user;

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

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler
	public ResponseEntity<UserErrorReponse> handleException(Exception exc)
	{
		UserErrorReponse error = new UserErrorReponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler
	public ResponseEntity<UserErrorReponse> handleException(UserNotUniqueNameOrEmailException exc)
	{
		UserErrorReponse error = new UserErrorReponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		
	}
	
}
