package controller;

import model.Case;
import model.Model;
import modelExceptions.TooFewToken;
import modelExceptions.Unreachable;
import view.Event;
import view.EventType;
import view.View;

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
	public void run() {
		Event ev = null;
		m.newPlayer("Skia");
		m.newPlayer("Gobelin");
		v.refresh();
		while(true) {
			ev = v.popEvent();
			if(ev==null) continue;
			switch (ev.getEventType()) {
			case NEWGAME:
				m.initGame();
				v.refresh();
				break;
			case NEWPLAYER:
				m.newPlayer(ev.getString());
				v.refresh();
				break;
			case REMOVEPLAYER:
				m.delPlayer(ev.getInteger());
				v.refresh();
				break;
			case CLICKPOLY:
				System.out.println("Clicked poly "+ev.getInteger());
				if (!m.hasActivePlayerAnActiveFolk())
					m.selectActivePlayerFolk((int) (Math.random() * 10) % 6);
				try {
					m.getCurrentPlayer().attackCase(
							m.getMap().getCase(ev.getInteger()));
				} catch (TooFewToken | Unreachable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				m.nextPlayer();
				v.refresh();
				break;
			default:
				break;
			}
		}
		
		
		/*
		m.initGame();
		m.newPlayer("Skia");
		m.newPlayer("Gobelin");m.printAvailaibleFolk();
		
		int i = 0;
		while (true) {
			if (i > 7)
				break;
			else
				i++;

			System.out.println("\nActive player: " + m.getCurrentPlayer());
			if (!m.hasActivePlayerAnActiveFolk()) {
				m.selectActivePlayerFolk((int) (Math.random() * 10) % 6);
			} else if (m.getCurrentPlayer().getNbFreeToken() > 0){
				while (true) {
					Case r = m.getMap().getCase(0);
					while (r.getId() < 29 && !m.getCurrentPlayer().canAttack(r)) {
						r = m.getMap().getCase((r.getId() + 1));
					}
					System.out.println(m.getCurrentPlayer().getName()
							+ " attacks case " + r.getId() + " that contains " + 
							r.getTokenNb() + " tokens");
					try {
						m.getCurrentPlayer().attackCase(r);
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
			} else {
				m.getCurrentPlayer().folkToDecline();
			}
			m.getCurrentPlayer().harvestMoney();
			System.out.println("End of turn: "+m.getCurrentPlayer());
			m.nextPlayer();
		}
		m.printAvailaibleFolk();
		*/
	}

}
