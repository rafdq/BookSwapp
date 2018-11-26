package bs.controller.user;

public class UserNotUniqueNameOrEmailException extends RuntimeException
{

	private static final long serialVersionUID = 1L;

	public UserNotUniqueNameOrEmailException()
	{
	}

	public UserNotUniqueNameOrEmailException(String message)
	{
		super(message);
	}

	public UserNotUniqueNameOrEmailException(Throwable cause)
	{
		super(cause);
	}

	public UserNotUniqueNameOrEmailException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public UserNotUniqueNameOrEmailException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
