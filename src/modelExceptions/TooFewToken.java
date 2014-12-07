package modelExceptions;

public class TooFewToken extends Exception {

	public TooFewToken() {
		super("Too few token to attack");
	}

}
