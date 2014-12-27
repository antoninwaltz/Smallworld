package smallworld;

import controller.Controller;
import view.View;
import model.Model;

/**
 * The Main class
 * @author Skia
 */
public class Main {
	public static void main(String[] args) {
		Model m = new Model();
		View v = new View(m);
		Controller c = new Controller(m, v);
		
		c.play();
	}
}

