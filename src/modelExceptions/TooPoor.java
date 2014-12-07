package modelExceptions;

public class TooPoor extends Exception {
	public TooPoor () {
		super("Not enough money");
	}
}
