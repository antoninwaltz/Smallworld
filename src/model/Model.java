package model;

import java.util.ArrayList;
import java.util.LinkedList;

import modelExceptions.TooFewToken;
import modelExceptions.Unreachable;


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
	private int _playerIndex;
	private Player _activePlayer;
	private ArrayList<Folk> _folkQueue;
	private ArrayList<Power> _powerQueue;
	private LinkedList<Folk> _availableFolk;

	/**
	 * <b>Constructor of the model</b> It only build the attributes
	 */
	public Model() {
		this._map = null;
		this._players = new ArrayList<Player>();
		this._folkQueue = new ArrayList<Folk>();
		this._powerQueue = new ArrayList<Power>();
		this._availableFolk = new LinkedList<Folk>();
		this.initFolk();
		this.initPower();
		this.initAvailableFolk();
	}

	public Map getMap() {
		return _map;
	}

	public Player getCurrentPlayer() {
		return _activePlayer;
	}

	public void initGame() {
		_activePlayer = _players.get(0);
		initMap();
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
	
	public boolean hasActivePlayerAnActiveFolk() {
		return _activePlayer.getCurrentFolk() != null;
	}

	public void selectActivePlayerFolk(int nb) {
		for (int i = 0; i < nb; i++)
			_availableFolk.get(i).incValue();
		Folk f = _availableFolk.remove(nb);
		Folk n = _folkQueue.remove(0);
		n.setPower(_powerQueue.remove(0));
		_availableFolk.addLast(n);
		_activePlayer.selectFolk(f, nb);
	}

	public void nextPlayer() {
		_playerIndex++;
		_playerIndex %= _players.size();
		_activePlayer = _players.get(_playerIndex);
	}

	public void attackCase(Case target) throws TooFewToken, Unreachable {
		if(!_activePlayer.canReach(target)) throw new Unreachable(target.getId());
		if(_activePlayer.getNbFreeToken() <= target.getTokenNb()) throw new TooFewToken();
		int tNb = target.getTokenNb();
		target.flushToken();
		for(int i=0;i<=tNb;i++){ // Set enough token on the case to make it colonized
			Token t = _activePlayer.getOneFreeToken();
			t.setCurrentCase(target);
			target.addToken(t);
		}
		_activePlayer.addControledCase(target);
		System.out.println("SUCCESS: target " + target.getId() + 
				" captured! Remaining tokens: " + _activePlayer.getNbFreeToken());
	}
}
