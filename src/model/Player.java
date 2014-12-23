package model;

import java.util.HashMap;

import modelExceptions.TooFewToken;
import modelExceptions.TooPoor;

public class Player {
	private String _name;
	private int _money;
	private Folk _currentFolk;
	private Folk _decliningFolk;
	private HashMap<Integer, Case> _ownedCases;
	private HashMap<Integer, FolkToken> _freeFolkTokens;

	public Player(String name) {
		this._name = name;
		this._money = 5;
		this._currentFolk = null;
		this._decliningFolk = null;
		this._ownedCases = new HashMap<>();
		this._freeFolkTokens = new HashMap<>();
	}
	
	

	/* === START (GET/SET)ERS === */

	@Override
	public String toString() {
		return "Player " + _name + ", " + _currentFolk + ", " + _money;
	}

	public Folk getCurrentFolk() {
		return _currentFolk;
	}

	public void setCurrentFolk(Folk _currentFolk) {
		this._currentFolk = _currentFolk;
		// this._tokens = listoffolktoken
	}

	public Folk getDecliningFolk() {
		return _decliningFolk;
	}

	public void setDecliningFolk(Folk _decliningFolk) {
		this._decliningFolk = _decliningFolk;
	}

	public String getName() {
		return _name;
	}

	public int getMoney() {
		return _money;
	}

	/* === END (GET/SET)ERS === */

	public void selectFolk(Folk f, int cost) {
		try {
			earnGold(f.getValue());
			payGold(cost);
			_currentFolk = f;
			this._freeFolkTokens = new HashMap<>(this._currentFolk.getToken());
		} catch (TooPoor e) {
			System.out.println(e.getMessage());
		}
	}

	public void earnGold(int q) {
		this._money += q;
	}

	public void payGold(int q) throws TooPoor {
		if (this._money < q)
			throw new TooPoor();
		this._money -= q;
	}

	public void attackCase(Case c) throws TooFewToken {
		// TODO
	}
}
