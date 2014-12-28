package view;


public class Event {
	
	private EventType _event;
	
	public Event(EventType event){
		
		this._event = event ;

	}
	
	
	public EventType getEvent(){
		
		return this._event;
		
	}

}
