package itat.zttc.user06;

public class UserException extends RuntimeException {

	private static final long serialVersionUID = -81189502336847297L;

	public UserException() {
		super();
	}

	public UserException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserException(String message) {
		super(message);
	}

	public UserException(Throwable cause) {
		super(cause);
	}
	
}
