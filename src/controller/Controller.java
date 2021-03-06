package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.Model;
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
public class Controller extends Thread {
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
	public synchronized void run() {
		Event ev = null;
		m.newPlayer("Skia");
		m.newPlayer("Gobelin");
		v.refresh();
		while(true) {
			ev = v.popEvent();
			if (ev==null) {
				try {
					this.wait();
					continue;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
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
			case SAVE:
				try {
					save(ev.getString());
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
				break;
			case LOAD:
				try {
					load(ev.getString());
					v.setModel(m);
					System.out.println("Game loaded");
					v.initBoard();
				} catch (ClassNotFoundException | IOException e) {
					System.err.println(e.getMessage());
				}
				break;
			default:
				break;
			}
			synchronized (v) {
				v.notify();
			}
			v.refresh();
		}
	}
	
	//===================================================================
	public void save(String string) throws IOException {
		FileOutputStream f = null;
		ObjectOutputStream s = null;
		f = new FileOutputStream(string);
		s = new ObjectOutputStream(f);
		s.writeObject(m);
		f.close();
		s.close();
	}

	public void load(String string) throws IOException, ClassNotFoundException {
		FileInputStream f = null;
		ObjectInputStream s = null;
		f = new FileInputStream(string);
		s = new ObjectInputStream(f);
		m = (Model) s.readObject();
		f.close();
		s.close();

	}
}
