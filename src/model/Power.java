package model;

import java.io.Serializable;

public class Power implements Serializable {
	private PowerType _type;
	private int _initialTokenNb;

	
	public  Power(PowerType t, int tn) {
		this._type = t;
		this._initialTokenNb = tn;
	}
	
	@Override
	public String toString() {
		return _type.toString();
	}

	public int getInitialToken() {
		return this._initialTokenNb;
	}

}
