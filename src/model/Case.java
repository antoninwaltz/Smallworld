package model;

import java.util.HashMap;


public class Case {
	private int _id;
	private HashMap<Integer,Case> _neighbours;
	private Player _owner;
	private CaseType _type; 
	private HashMap<Integer,Token> _tokens;
	private int _tokenNb;

	public Case(int id, CaseType t) {
		_id = id;
		_type = t;
		this._neighbours = new HashMap<>();
		this._tokens = new HashMap<>();
		
	}
	
	/* === START (GET/SET)ERS === */

	public int getId() {
		return _id;
	}

	public HashMap<Integer, Case> getNeighbours() {
		return _neighbours;
	}

	public void setNeighbours(HashMap<Integer, Case> neighbours) {
		this._neighbours = neighbours;
	}

	public Player getOwner() {
		return _owner;
	}

	public void setOwner(Player owner) {
		this._owner = owner;
	}

	public CaseType getType() {
		return _type;
	}

	public void setType(CaseType type) {
		this._type = type;
	}
	
	public int getTokenNb() {
		return _tokenNb;
	}
	
	/* === END (GET/SET)ERS === */
	public void addToken(Token t) {
		_tokens.put(t.getId(), t);
		_tokenNb++;
	}
	
	public boolean isOnBorder() {
		for(Case c : _neighbours.values()) {
			if(c._id == 4000)
				return true;
		}
		return false;
	}

	public void flushToken() {
		_tokens.clear();
		_tokenNb = 0;
	}

	
}
