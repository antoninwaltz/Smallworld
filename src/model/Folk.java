package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Folk implements Serializable {
	private boolean _inDecline;
	private int _value;
	private int _initialTokenNb;
	private ArrayList<FolkToken> _token;
	private Power _power;
	private FolkType _type;
	
	public Folk (FolkType type, int tNb) {
		this._type = type;
		this._token = new ArrayList<FolkToken>();
		this._initialTokenNb = tNb;
	}

	@Override
	public String toString() {
		return _type + "-" + _power;
	}
	
	public FolkType getName() {
		return _type;
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
		for(int i=0;i<(_initialTokenNb+_power.getInitialToken());i++) {
			_token.add(new FolkToken(i, _type));
		}
	}

	
}
