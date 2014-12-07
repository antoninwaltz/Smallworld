package mvc;

import modelExceptions.ExistingPlayer;


/**
 * <b>The Controller class</b>
 * 
 * It contains the model and the view
 * @see Model
 * @see View
 * 
 * @author Skia
 */
public class Controller {
	/**
	 * The Model attribute
	 * @see Model
	 */
	private Model m;
	/**
	 * The View attribute
	 * @see View
	 */
	private View v;
	
	/**
	 * Constructor of the {@link Controller}
	 * @param m The {@link Model}
	 * @param v The {@link View}
	 */
	public Controller(Model m, View v) {
		this.m = m;
		this.v = v;
	}
	
	/**
	 * The main function to play
	 * 
	 * It synchronize the view and model in order to have a playable game
	 */
	public void play() {
		try {
			m.newPlayer("Skia");
			m.newPlayer("Troll");
			m.newPlayer("Skia");
		} catch (ExistingPlayer e) {
			System.err.println(e.getMessage());
		}
		m.initMap();
		
		while(true) {
			break;
			/* e = view.getEvent()
			 * 
			 * switch (e) {
			 * }
			 */
		}
	}
}
