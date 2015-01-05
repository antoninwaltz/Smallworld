package controller;

import model.Model;
import modelExceptions.TooFewToken;
import modelExceptions.Unreachable;
import view.Event;
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
				m.quitGame();
				m.initGame();
				v.initBoard();
				break;
			case NEWPLAYER:
				m.newPlayer(ev.getString());
				break;
			case REMOVEPLAYER:
				m.delPlayer(ev.getInteger());
				break;
			case SELECTFOLKPOWER:
				m.selectActivePlayerFolk(ev.getInteger());
				break;
			case ATTACKCASE:
				m.attack(ev.getInteger());
				break;
			case REDEPLOY:
				m.setRedeploying();
				break;
			case CLICKPOLY:
				m.getCurrentPlayer().addTokenToCase(ev.getInteger());
				break;
			case FOLKTODECLINE:
				m.playerToDeline();
				m.nextPlayer();
				break;
			case NEXTPLAYER:
				m.getCurrentPlayer().harvestMoney();
				m.nextPlayer();
				m.harvestTroups();
				break;
			default:
				break;
			}
			v.refresh();
		}
	}

}
