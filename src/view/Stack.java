package view;

import java.util.ArrayList;

public class Stack {

	private ArrayList<Event> _stack;
	
	public Stack(){
		
		this._stack = new ArrayList<Event>();
		
	}
	
	
	public ArrayList getStack(){
		return this._stack;
	}
	
	
	public void addStack(Event e){
		this._stack.add(e);
	}
	
	public Event getItemOfStack(int i){
		return this._stack.get(i);
	}
	
}
