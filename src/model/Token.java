package model;

abstract public class Token {
	private int _id;
	private Case _currentCase;
	private Player _owner;
	
	public Token(int id) {
		this._id = id;
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
	
	
	/* === END (GET/SET)ERS === */
}
