package controler;

import view.View;
import model.Model;
import modelExceptions.ExistingPlayer;

/**
 * <b>The Controller class</b>
 * 
 * It contains the model and the view
 * 
 * @see Model
 * @see View
 * 
 * @author Skia
 */
public class Controller {
	/**
	 * The Model attribute
	 * 
	 * @see Model
	 */
	private Model m;
	/**
	 * The View attribute
	 * 
	 * @see View
	 */
	private View v;

	/**
	 * Constructor of the {@link Controller}
	 * 
	 * @param m
	 *            The {@link Model}
	 * @param v
	 *            The {@link View}
	 */
	public Controller(Model m, View v) {
		this.m = m;
		this.v = v;
	}

	/**
	 * The main function to play
	 * 
	 * It synchronizes the view and model in order to have a playable game
	 */
	public void play() {
		m.newPlayer("Skia");
		m.newPlayer("Troll");
		m.newPlayer("Blah");
		m.initMap();
		m.printAvailaibleFolk();
		m.selectPlayerFolk(5);
		System.out.println(m.getCurrentPlayer());
		m.nextPlayer();
		m.selectPlayerFolk(2);
		System.out.println(m.getCurrentPlayer());
		m.nextPlayer();
		m.selectPlayerFolk(3);
		System.out.println(m.getCurrentPlayer());
		m.nextPlayer();
		m.printAvailaibleFolk();

		while (true) {
			break;
			/*
			 * e = view.getEvent()
			 * 
			 * switch (e) { }
			 */
		}
	}
}
