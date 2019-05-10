package dtu.library.app;

public class MissingAuthenticity extends Exception {
	public MissingAuthenticity(String ErrorMessage) {
		super(ErrorMessage);
	}
}
