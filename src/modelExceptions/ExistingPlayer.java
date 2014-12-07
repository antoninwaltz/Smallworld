package modelExceptions;

public class ExistingPlayer extends Exception {
	/**
	 * Exception for already existing player
	 */
	public ExistingPlayer() {
		super("Player already exists!");
	}
}
