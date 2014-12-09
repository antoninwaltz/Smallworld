package model;

abstract public class Token {
	private Case _currentCase;
	private Player _owner;
	
	/* === START (GET/SET)ERS === */

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