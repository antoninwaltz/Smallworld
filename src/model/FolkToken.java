package model;

public class FolkToken extends Token {
	private String _name;
	private boolean _declining;


	public FolkToken(int id, String n) {
		super(id);
		this._declining = false;
		this._name = n+id;
	}
	
	public boolean isDeclining() {
		return _declining;
	}

	public void toDecline() {
		this._declining = true;
	}
}
