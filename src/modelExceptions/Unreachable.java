package modelExceptions;

public class Unreachable extends Exception {

	public Unreachable(int id) {
		super("Case "+id+" is unreachable for active player");
	}	
}
