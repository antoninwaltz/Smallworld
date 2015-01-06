package smallworld;

import model.Model;
import view.View;
import controller.Controller;

/**
 * The Main class
 * @author Skia
 */
public class Main {
	public static void main(String[] args) {
		Model m = new Model();
		View v = new View(m);
		Controller c = new Controller(m, v);
		v.setController(c);
		c.start();
	}
}

