package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import view.HomeView;
import view.PseudoPlayersView;
import view.RulesView;
import view.View;
import model.Case;
import model.Model;
import modelExceptions.TooFewToken;
import modelExceptions.Unreachable;

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
		m.newPlayer("Gobelin");
		m.initGame();
		m.initMap();
		m.printAvailaibleFolk();
		m.selectActivePlayerFolk(5);
		System.out.println(m.getCurrentPlayer());
		m.nextPlayer();
		m.selectActivePlayerFolk(2);
		System.out.println(m.getCurrentPlayer());
		m.nextPlayer();
		m.selectActivePlayerFolk(3);
		System.out.println(m.getCurrentPlayer());
		m.nextPlayer();
		m.printAvailaibleFolk();
		
		
		int i = 0;
		while (true) {
			if (i > 3)
				break;
			else
				i++;

			System.out.println("\nActive player: " + m.getCurrentPlayer());
			if (!m.hasActivePlayerAnActiveFolk()) {
				m.selectActivePlayerFolk((int) (Math.random() * 10) % 6);
			} else {
				while (true) {
					Case r = m.getMap().getCase(0);
					while (!m.getCurrentPlayer().canAttack(r)) {
						r = m.getMap().getCase((r.getId() + 1));
					}
					System.out.println(m.getCurrentPlayer().getName()
							+ " attacks case " + r.getId() + " that contains " + 
							r.getTokenNb() + " tokens");
					try {
						m.attackCase(r);
					} catch (TooFewToken e) {
						System.out.println("FAIL - TooFewToken");
						e.printStackTrace();
						break;
					} catch (Unreachable e) {
						System.out.println("FAIL - Unreachable");
						e.printStackTrace();
					}
					if(m.getCurrentPlayer().getNbFreeToken() == 0)
						break;
				}
				System.out.println(m.getCurrentPlayer());
			}
			m.nextPlayer();
		}
		m.printAvailaibleFolk();
	}

}
