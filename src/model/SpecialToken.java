package model;

public class SpecialToken extends Token {
	private SpecialTokenType _type;

	public SpecialToken(int id, SpecialTokenType t) {
		super(id);
		_type = t;
	}
}
