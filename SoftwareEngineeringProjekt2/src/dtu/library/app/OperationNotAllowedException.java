package dtu.library.app;

public class OperationNotAllowedException extends Exception {
	public OperationNotAllowedException(String ErrorMessage) {
		super(ErrorMessage);
	}
}
