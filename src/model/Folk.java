package model;

import java.util.HashMap;

public class Folk {
	private boolean _inDecline;
	private int _value;
	private int _initialTokenNb;
	private HashMap<Integer, FolkToken> _token;
	private Power _power;
	private String _name;
	
	public Folk (String name, int tNb) {
		this._name = name;
		this._token = new HashMap<Integer, FolkToken>();
		this._initialTokenNb = tNb;
	}

	@Override
	public String toString() {
		return _name + "-" + _power;
	}

	public HashMap<Integer, FolkToken> getToken() {
		return _token;
	}
	
	public void setPower(Power p) {
		this._power = p;
		this._initialTokenNb += p.getInitialToken();
		for(int i=0;i<_initialTokenNb;i++) {
			_token.put(i, new FolkToken(i, _name));
		}
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

	
}
