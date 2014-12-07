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
	
	public Player(String name) {
		this._name = name;
		this._money = 5;
		this._currentFolk = null;
		this._decliningFolk = null;
		this._ownedCases = new HashMap<>();
	}
	
	/* === START (GET/SET)ERS === */

	public Folk getCurrentFolk() {
		return _currentFolk;
	}
	
	public void setCurrentFolk(Folk _currentFolk) {
		this._currentFolk = _currentFolk;
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
	
	public void earnGold(int q) {
		this._money += q;
	}
	
	public void payGold(int q) throws TooPoor {
		if(this._money < q)
			throw new TooPoor();
		this._money -= q;
	}
	
	public void attackCase(int id) throws TooFewToken {
		// TODO
	}
}
