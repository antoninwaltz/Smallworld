package model;

abstract public class Token {
	private int _id;
	private Case _currentCase;
	private Player _owner;
	private boolean _declining;
	
	public Token(int id) {
		this._id = id;
		this._declining = false;
		this._currentCase = null;
		this._owner = null;
	}
	
	/* === START (GET/SET)ERS === */

	public int getId() {
		return _id;
	}
	public Case getCurrentCase() {
		return _currentCase;
	}
	public void setCurrentCase(Case _currentCase) {
		this._currentCase = _currentCase;
	}
	public Player getOwner() {
		return _owner;
	}
	public void setOwner(Player _owner) {
		this._owner = _owner;
	}
	public boolean isDeclining() {
		return _declining;
	}

	public void toDecline() {
		this._declining = true;
	}
	
	/* === END (GET/SET)ERS === */
}
