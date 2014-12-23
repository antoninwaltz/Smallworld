package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


/**
 * <b>The Model class</b>
 * 
 * It contains the map, the players and everything to play!
 * 
 * @author Skia
 */
public class Model {
	private Map _map;
	private ArrayList<Player> _players;
	private int _activePlayer;
	private ArrayList<Folk> _folkQueue;
	private ArrayList<Power> _powerQueue;
	private LinkedList<Folk> _availableFolk;
	private int[] _folkMoney;

	/**
	 * <b>Constructor of the model</b> It only build the attributes
	 */
	public Model() {
		this._map = null;
		this._players = new ArrayList<>();
		this._folkQueue = new ArrayList<>();
		this._powerQueue = new ArrayList<>();
		this._availableFolk = new LinkedList<>();
		this._folkMoney = new int[6];
		this.initFolk();
		this.initPower();
		this.initAvailableFolk();
	}

	public Map getMap() {
		return _map;
	}

	public Player getCurrentPlayer() {
		return _players.get(_activePlayer);
	}

	public void initFolk() {
		System.out.print("Initializing folks... ");
		_folkQueue.add(new Folk("Elves", 5));
		_folkQueue.add(new Folk("Dwarves", 4));
		_folkQueue.add(new Folk("Orks", 5));
		_folkQueue.add(new Folk("Halfelins", 6));
		_folkQueue.add(new Folk("Goblins", 7));
		_folkQueue.add(new Folk("Giants", 5));
		_folkQueue.add(new Folk("Trolls", 5));
		_folkQueue.add(new Folk("Amazones", 5));
		_folkQueue.add(new Folk("Wizards", 5));
		System.out.println("OK");
	}

	public void initPower() {
		System.out.print("Initializing powers... ");
		_powerQueue.add(new Power(PowerType.BLOUSEUX, 4));
		_powerQueue.add(new Power(PowerType.FROM_THE_FOREST, 5));
		_powerQueue.add(new Power(PowerType.SWAG, 5));
		_powerQueue.add(new Power(PowerType.SUPER_SWAG, 6));
		_powerQueue.add(new Power(PowerType.CHELOUS, 6));
		_powerQueue.add(new Power(PowerType.TROP_NULS, 6));
		_powerQueue.add(new Power(PowerType.FESTIFS, 3));
		_powerQueue.add(new Power(PowerType.NIAIS, 4));
		_powerQueue.add(new Power(PowerType.STRESSES, 5));
		System.out.println("OK");
	}

	public void initAvailableFolk() {
		System.out.print("Generating avalaible folks...");
		for (int i = 0; i < 6; i++) {
			Folk n = _folkQueue.remove(0);
			n.setPower(_powerQueue.remove(0));
			_availableFolk.addLast(n);
		}
		System.out.println("OK");
	}

	public void printAvailaibleFolk() {
		System.out.println(_availableFolk);
	}

	public void initMap() {
		System.out.println("Initializing the map...");
		if (_players.size() == 2 || _players.size() == 3) {
			this._map = new Map(MapType.SMALL);
		} else {
			this._map = new Map(MapType.BIG);
		}
		System.out.println("Map initialized");
	}

	public void newPlayer(String name) {
		System.out.print("New player: " + name + "... ");
		_players.add(new Player(name));
		System.out.println("OK");
	}

	public void selectPlayerFolk(int nb) {
		for (int i = 0; i < nb; i++)
			_availableFolk.get(i).incValue();
		Folk f = _availableFolk.remove(nb);
		Folk n = _folkQueue.remove(0);
		n.setPower(_powerQueue.remove(0));
		_availableFolk.addLast(n);
		_players.get(_activePlayer).selectFolk(f, nb);
	}

	public void nextPlayer() {
		_activePlayer++;
		_activePlayer %= _players.size();
	}

}
