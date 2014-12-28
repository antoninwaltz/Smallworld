package model;

import java.util.ArrayList;
import java.util.HashMap;

import modelExceptions.TooFewToken;
import modelExceptions.TooPoor;
import modelExceptions.Unreachable;

public class Player {
	private String _name;
	private int _money;
	private Folk _currentFolk;
	private Folk _decliningFolk;
	private HashMap<Integer, Case> _ownedCases;
	private HashMap<Integer, Case> _declinedCases;
	private ArrayList<FolkToken> _freeFolkTokens;

	public Player(String name) {
		this._name = name;
		this._money = 5;
		this._currentFolk = null;
		this._decliningFolk = null;
		this._ownedCases = new HashMap<Integer, Case>();
		this._declinedCases = new HashMap<Integer, Case>();
		this._freeFolkTokens = new ArrayList<FolkToken>();
	}

	/* === START (GET/SET)ERS === */

	@Override
	public String toString() {
		return "Player " + _name + ", Folk: " + _currentFolk + ", FreeTokens: "
				+ getNbFreeToken() + ", Money: " + _money;
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
			f.generateFolkTokens();
			_currentFolk = f;
			this._freeFolkTokens = new ArrayList<FolkToken>(
					this._currentFolk.getToken());
		} catch (TooPoor e) {
			System.out.println(e.getMessage());
		}
	}

	public void attackCase(Case target) throws TooFewToken, Unreachable {
		if (!canReach(target))
			throw new Unreachable(target.getId());
		if (getNbFreeToken() <= target.getTokenNb())
			throw new TooFewToken();
		int tNb = target.getTokenNb();
		target.flushToken();
		for (int i = 0; i <= tNb; i++) { // Set enough token on the case to make
											// it colonized
			Token t = getOneFreeToken();
			t.setCurrentCase(target);
			target.addToken(t);
		}
		addControledCase(target);
		System.out.println("SUCCESS: target " + target.getId()
				+ " captured! Remaining tokens: " + getNbFreeToken());
	}

	public void freeTroops() {
		for (Case c : _ownedCases.values()) {
			FolkToken t = c.remOneFolkToken();
			while (t != null) {
				_freeFolkTokens.add(t);
				t = c.remOneFolkToken();
			}
		}
	}

	public void harvestMoney() {
		int count = _ownedCases.size()+_declinedCases.size();
		// TODO implement special rules
		earnGold(count);
	}

	public void folkToDecline() {
		System.out.print(_currentFolk + " getting into decline...");
		this._currentFolk.toDecline();
		this._decliningFolk = _currentFolk;
		_currentFolk = null;
		for (Case c : _declinedCases.values()) { // flush old declining folk
			c.flushToken();
		}
		for (Case c : _ownedCases.values()) { // only one declining folk token
												// remains
			FolkToken t = c.remOneFolkToken();
			t.toDecline();
			c.flushToken();
			c.addToken(t);
		}
		_declinedCases = _ownedCases;
		_ownedCases = new HashMap<Integer, Case>();
		System.out.println("OK");
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
		if (_ownedCases.isEmpty() && target.isOnBorder())
			return true;
		for (Case c : _ownedCases.values()) {
			if (c.getNeighbours().containsValue(target))
				return true;
		}
		return false;
	}

	public boolean canAttack(Case target) {
		if (_ownedCases.containsKey(target.getId())) // don't attack yourself!
			return false;
		if (canReach(target) && target.getTokenNb() < getNbFreeToken())
			return true;
		return false;
	}

	public void addControledCase(Case c) {
		if (c.getOwner() != null) { // delete last owner's ownership
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
