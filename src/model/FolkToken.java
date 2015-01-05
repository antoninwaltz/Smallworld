package model;

public class FolkToken extends Token {
	private FolkType _type;
	private boolean _declining;

	public FolkToken(int id, FolkType t) {
		super(id);
		this._declining = false;
		this._type = t;
	}
	
	public FolkType getType() {
		return _type;
	}
	
	public boolean isDeclining() {
		return _declining;
	}

	public void toDecline() {
		this._declining = true;
	}
}
