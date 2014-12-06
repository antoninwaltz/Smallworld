package mvc;

import java.util.HashMap;

import model.Map;
import model.MapType;
import model.Player;

/**
 * <b>The Model class</b>
 * 
 * It contains the map, the players and everything to play!
 * 
 * @author Skia
 */
public class Model {
	private Map _map;
	private HashMap<String, Player> _players;
	/**
	 * <b>Constructor of the model</b>
	 * It only build the attributes
	 */
	public Model() {
		this._map = null;
		this._players = new HashMap<>();
	}
	
	public Map getMap() {
		return _map;
	}
	
	public Player getPlayer(String name) {
		return _players.get(name);
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
		System.out.print("New player: "+name+"... ");
		_players.put(name, new Player(name));
		System.out.println("OK");
	}
}
