package model;

import java.util.HashMap;


public class Case {
	private int _id;
	private HashMap<Integer,Case> _neighbours;
	private Player _owner;
	private CaseType _type;
	
	public Case(int id) {
		_id = id;
		this._neighbours = new HashMap<>();
	}
	
	/* === START (GET/SET)ERS === */

	public int getId() {
		return _id;
	}

	public HashMap<Integer, Case> getNeighbours() {
		return _neighbours;
	}

	public void setNeighbours(HashMap<Integer, Case> _neighbours) {
		this._neighbours = _neighbours;
	}

	public Player getOwner() {
		return _owner;
	}

	public void setOwner(Player _owner) {
		this._owner = _owner;
	}

	public CaseType getType() {
		return _type;
	}

	public void setType(CaseType _type) {
		this._type = _type;
	}
	/* === END (GET/SET)ERS === */

}
