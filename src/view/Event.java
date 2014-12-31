package view;


public class Event {
	
	private EventType _type;
	private String _str;
	private int _int;
	
	public Event(EventType type){
		this._type = type ;
	}
	
	public Event(EventType type, String s){
		this._type = type ;
		this._str = s;
	}
	
	public Event(EventType event, int id) {
		this._type = event ;
		this._int = id;
	}

	public EventType getEventType(){
		return this._type;
	}

	public String getString() {
		return _str;
	}

	public int getInteger() {
		return _int;
	}
	
	

}
