package br.com.oma.intranet.util;

@SuppressWarnings("serial")
public class ADException extends Exception {

	public ADException() {
		super();
	}

	public ADException(String message, Throwable cause) {
		super(message, cause);
	}

	public ADException(String message) {
		super(message);
	}

	public ADException(Throwable cause) {
		super(cause);
	}
}
