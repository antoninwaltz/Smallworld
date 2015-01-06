package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


public class Case implements Serializable {
	private int _id;
	private HashMap<Integer,Case> _neighbours;
	private Player _owner;
	private CaseType _type; 
	private ArrayList<Token> _tokens;
	private int _tokenNb;

	public Case(int id, CaseType t) {
		_id = id;
		_type = t;
		this._neighbours = new HashMap<Integer, Case>();
		this._tokens = new ArrayList<Token>();
		
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
	
	public int getFolkTokenNb() {
		int i = 0;
		for(Token t : _tokens) {
			if(t instanceof FolkToken)
				i++;
		}
		return i;
	}
	
	/* === END (GET/SET)ERS === */
	public void addToken(Token t) {
		_tokens.add(t);
		_tokenNb++;
	}
	
	public FolkToken removeOneFolkToken() {
		for(int i=0;i<_tokenNb;i++){
			if(_tokens.get(i) instanceof FolkToken) {
				FolkToken ft = (FolkToken) _tokens.remove(i);
				_tokenNb--;
				return ft;
			}
		}
		return null;
	}
	
	public ArrayList<FolkToken> getGarnison() {
		ArrayList<FolkToken> tList = new ArrayList<>();
		for(int i=1;i<_tokenNb;i++) {
			if(_tokens.get(i) instanceof FolkToken) {
				FolkToken ft = (FolkToken) _tokens.get(i);
				tList.add(ft);
			}
		}
		_tokens.removeAll(tList);
		_tokenNb-=tList.size();
		return tList;
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
	
	public void free() {
		flushToken();
		_owner = null;
	}

	public FolkType getFolkType() {
		for (Token t : _tokens)
			if (t instanceof FolkToken)
				return ((FolkToken) t).getType();
		return null;
	}
}
