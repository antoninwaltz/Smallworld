package model;

import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import modelExceptions.TooFewToken;
import modelExceptions.TooPoor;

public class Player {
	private String _name;
	private int _money;
	private Folk _currentFolk;
	private Folk _decliningFolk;
	private HashMap<Integer, Case> _ownedCases;
	private ArrayList<FolkToken> _freeFolkTokens;

	public Player(String name) {
		this._name = name;
		this._money = 5;
		this._currentFolk = null;
		this._decliningFolk = null;
		this._ownedCases = new HashMap<>();
		this._freeFolkTokens = new ArrayList<>();
	}

	/* === START (GET/SET)ERS === */

	@Override
	public String toString() {
		return "Player " + _name + ", " + _currentFolk + ", " + getNbFreeToken();
	}

	public Folk getCurrentFolk() {
		return _currentFolk;
	}

	public void setCurrentFolk(Folk folk) {
		this._currentFolk = folk;
		// this._tokens = listoffolktoken
	}

	public Folk getDecliningFolk() {
		return _decliningFolk;
	}

	public String getName() {
		return _name;
	}

	public int getMoney() {
		return _money;
	}
	
	public int getNbFreeToken() {
		return _freeFolkTokens.size();
	}
	
	public Token getOneFreeToken() {
		return _freeFolkTokens.remove(0);
	}
	
	/* === END (GET/SET)ERS === */

	public void selectFolk(Folk f, int cost) {
		try {
			earnGold(f.getValue());
			payGold(cost);
			_currentFolk = f;
			this._freeFolkTokens = new ArrayList<>(this._currentFolk.getToken().values());
		} catch (TooPoor e) {
			System.out.println(e.getMessage());
		}
	}

	public void folkToDecline() {
		this._currentFolk.toDecline();
		this._decliningFolk = _currentFolk;
		_currentFolk = null;
	}

	public void earnGold(int q) {
		this._money += q;
	}

	public void payGold(int q) throws TooPoor {
		if (this._money < q)
			throw new TooPoor();
		this._money -= q;
	}

	public boolean canReach(Case target) {
		if(_ownedCases.isEmpty() && target.isOnBorder())
			return true;
		for(Case c : _ownedCases.values()) {
			if(c.getNeighbours().containsValue(target))
				return true;
		}
		return false;
	}
	
	public boolean canAttack(Case target) {
		if(_ownedCases.containsKey(target.getId()))
			return false;
		if(canReach(target) && target.getTokenNb()<getNbFreeToken())
			return true;
		return false;
	}

	public void addControledCase(Case c) {
		if(c.getOwner() != null) {
			c.getOwner().delControledCase(c);
		}
		c.setOwner(this);
		this._ownedCases.put(c.getId(), c);
	}

	private void delControledCase(Case c) {
		c.setOwner(null);
		this._ownedCases.remove(c.getId());
	}
}
