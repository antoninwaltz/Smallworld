package model;

public class FolkToken extends Token {
	private String _name;

	public FolkToken(int id, String n) {
		super(id);
		this._name = n+id;
	}

}
