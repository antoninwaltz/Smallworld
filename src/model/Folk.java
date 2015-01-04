package model;

import java.util.ArrayList;

public class Folk {
	private boolean _inDecline;
	private int _value;
	private int _initialTokenNb;
	private ArrayList<FolkToken> _token;
	private Power _power;
	private String _name;
	
	public Folk (String name, int tNb) {
		this._name = name;
		this._token = new ArrayList<FolkToken>();
		this._initialTokenNb = tNb;
	}

	@Override
	public String toString() {
		return _name + "-" + _power;
	}
	
	public String getName() {
		return _name;
	}
	
	public Power getPower() {
		return _power;
	}

	public int getInitialTokNb() {
		return _initialTokenNb;
	}

	public ArrayList<FolkToken> getToken() {
		return _token;
	}
	
	public void setPower(Power p) {
		this._power = p;
		this._initialTokenNb += p.getInitialToken();
	}
	
	public int getValue() {
		return this._value;
	}
	
	public void incValue() {
		this._value++;
	}

	public void toDecline() {
		this._inDecline = true;
	}

	public void generateFolkTokens() {
		for(int i=0;i<_initialTokenNb;i++) {
			_token.add(new FolkToken(i, _name+i));
		}
	}

	
}
