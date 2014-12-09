package view;

import model.Model;


/**
 * <b>The View class</b>
 * 
 * It contains the model
 * 
 * Handles the graphical interface
 * 
 * @see Model
 * 
 * @author Skia
 */
public class View {
	/**
	 * The model attribute
	 * @see Model
	 */
	private Model m;
	
	/**
	 * <b>Constructor of the view</b>
	 * 
	 * @param m The model used by the view
	 */
	public View(Model m) {
		this.m = m;
	}
}
