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
	private boolean _redeploying;
	private boolean _canDecline;

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
	
	public boolean isOwner(int cId) {
		return _activePlayer.isOwner(_map.getCase(cId));
	}
	
	public boolean canAttack(int cId) {
		return _activePlayer.canAttack(_map.getCase(cId));
	}
	
	public ArrayList<Player> getPlayers() {
		return _players;
	}

	public String[] getPlayerList() {
		String[] plist = new String[_players.size()];
		for(int i=0;i<_players.size();i++){
			plist[i] = i+": "+_players.get(i).getName();
		}
		return plist;
	}

	public LinkedList<Folk> getAvailableFolks() {
		return _availableFolk;
	}

	public boolean isRedeploying() {
		return _redeploying;
	}
	
	public boolean canDeline() {
		return hasActivePlayerAnActiveFolk() && _canDecline;
	}

	public boolean canRedeploy() {
		return hasActivePlayerAnActiveFolk() && _activePlayer.getControlledCaseNumber()>0;
	}
//===========================================================================//
	public void initGame() {
		_activePlayer = _players.get(0);
		initMap();
	}
	
	public void initFolk() {
		System.out.print("Initializing folks... ");
		_folkQueue.add(new Folk("INFO", 5));
		_folkQueue.add(new Folk("MC", 4));
		_folkQueue.add(new Folk("EDIM", 5));
		_folkQueue.add(new Folk("EE", 6));
		_folkQueue.add(new Folk("IMSI", 7));
		_folkQueue.add(new Folk("PROFESSEURS", 5));
		_folkQueue.add(new Folk("CHERCHEURS", 5));
		_folkQueue.add(new Folk("DOCTORANTS", 5));
		_folkQueue.add(new Folk("ADMINISTRATIFS", 5));
		_folkQueue.add(new Folk("TC", 4));
		System.out.println("OK");
	}

	public void initPower() {
		System.out.print("Initializing powers... ");
		_powerQueue.add(new Power(PowerType.CHIMISTES, 4));
		_powerQueue.add(new Power(PowerType.ANCIENS, 4));
		_powerQueue.add(new Power(PowerType.FESTIFS, 5));
		_powerQueue.add(new Power(PowerType.BLOUSEUX, 5));
		_powerQueue.add(new Power(PowerType.BARMANS, 3));
		_powerQueue.add(new Power(PowerType.MATHEUX, 4));
		_powerQueue.add(new Power(PowerType.BOSSEURS, 5));
		_powerQueue.add(new Power(PowerType.SPORTIFS, 3));
		_powerQueue.add(new Power(PowerType.LITTERAIRES, 4));
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

	public void delPlayer(int id) {
		System.out.print("Deletting player "+_players.remove(id).getName()+"... ");
		System.out.println("OK");
	}
	
	public boolean hasActivePlayerAnActiveFolk() {
		return _activePlayer.getCurrentFolk() != null;
	}

	
	//============================================================================//
	public void selectActivePlayerFolk(int nb) {
		System.out.print("Selecting folk "+_availableFolk.get(nb)+"... ");
		_canDecline = false;
		for (int i = 0; i < nb; i++)
			_availableFolk.get(i).incValue();
		Folk f = _availableFolk.remove(nb);
		Folk n = _folkQueue.remove(0);
		n.setPower(_powerQueue.remove(0));
		_availableFolk.addLast(n);
		_activePlayer.selectFolk(f, nb);
		System.out.println("OK");
	}
	
	public void attack(int cId) {
		_canDecline = false;
		try {
			_activePlayer.attackCase(_map.getCase(cId));
		} catch (TooFewToken | Unreachable e) {
			e.printStackTrace();
		}
	}

	public void nextPlayer() {
		_redeploying = false;
		_canDecline = true;
		_playerIndex++;
		_playerIndex %= _players.size();
		_activePlayer = _players.get(_playerIndex);
	}
	
	public void playerToDeline() {
		_activePlayer.folkToDecline();
	}

	public void setRedeploying() {
		System.out.print("Redeploying... ");
		_canDecline = false;
		_redeploying = true;
		_activePlayer.setRedeploying();
		System.out.println("OK");
	}


}
