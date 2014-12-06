package model;

import java.util.HashMap;

public class Map {
	private HashMap<Integer,Case> _map;
	private int _caseNb;
	private MapType _type;

	/**
	 * @param _map
	 */
	public Map(MapType t) {
		_type = t;
		_caseNb = (_type == MapType.SMALL) ? 31 : 50;
		this._map = new HashMap<Integer, Case>(_caseNb);
		for (int i=0; i<=_caseNb;i++) {
			this._map.put(i, new Case(i));
		}
		if (this._type == MapType.SMALL) {
			System.out.print("Building a small map... ");
			this._map.get(0).setType(CaseType.WATER);
			this._map.get(1).setType(CaseType.FOREST);
			this._map.get(2).setType(CaseType.MOUNTAIN);
			this._map.get(3).setType(CaseType.FIELD);
			this._map.get(4).setType(CaseType.MARSH);
			this._map.get(5).setType(CaseType.FOREST);
			this._map.get(6).setType(CaseType.MARSH);
			this._map.get(7).setType(CaseType.FIELD);
			this._map.get(8).setType(CaseType.MARSH);
			this._map.get(9).setType(CaseType.HILL);
			this._map.get(10).setType(CaseType.MOUNTAIN);
			this._map.get(11).setType(CaseType.FIELD);
			this._map.get(12).setType(CaseType.MOUNTAIN);
			this._map.get(13).setType(CaseType.MARSH);
			this._map.get(14).setType(CaseType.WATER);
			this._map.get(15).setType(CaseType.MOUNTAIN);
			this._map.get(16).setType(CaseType.HILL);
			this._map.get(17).setType(CaseType.MOUNTAIN);
			this._map.get(18).setType(CaseType.FIELD);
			this._map.get(19).setType(CaseType.FOREST);
			this._map.get(20).setType(CaseType.HILL);
			this._map.get(21).setType(CaseType.MARSH);
			this._map.get(22).setType(CaseType.FIELD);
			this._map.get(23).setType(CaseType.HILL);
			this._map.get(24).setType(CaseType.MOUNTAIN);
			this._map.get(25).setType(CaseType.MOUNTAIN);
			this._map.get(26).setType(CaseType.FOREST);
			this._map.get(27).setType(CaseType.FOREST);
			this._map.get(28).setType(CaseType.WATER);
			this._map.get(29).setType(CaseType.HILL); 
			this._map.get(30).setType(CaseType.BORDER); 
			this.newEdge(0, 1);
			this.newEdge(0, 7);
			this.newEdge(0, 12);
			this.newEdge(0, 30);
			this.newEdge(1, 2);
			this.newEdge(1, 7);
			this.newEdge(1, 8);
			this.newEdge(1, 30);
			this.newEdge(2, 3);
			this.newEdge(2, 8);
			this.newEdge(2, 9);
			this.newEdge(2, 30);
			this.newEdge(3, 4);
			this.newEdge(3, 9);
			this.newEdge(3, 10);
			this.newEdge(3, 30);
			this.newEdge(4, 5);
			this.newEdge(4, 10);
			this.newEdge(4, 11);
			this.newEdge(4, 30);
			this.newEdge(5, 6);
			this.newEdge(5, 30);
			this.newEdge(6, 11);
			this.newEdge(6, 16);
			this.newEdge(6, 17);
			this.newEdge(6, 30);
			this.newEdge(7, 8);
			this.newEdge(7, 12);
			this.newEdge(8, 9);
			this.newEdge(8, 12);
			this.newEdge(8, 13);
			this.newEdge(9, 10);
			this.newEdge(9, 13);
			this.newEdge(9, 14);
			this.newEdge(10, 11);
			this.newEdge(10, 14);
			this.newEdge(10, 15);
			this.newEdge(11, 15);
			this.newEdge(11, 16);
			this.newEdge(12, 13);
			this.newEdge(12, 18);
			this.newEdge(12, 30);
			this.newEdge(13, 14);
			this.newEdge(13, 18);
			this.newEdge(13, 19);
			this.newEdge(14, 15);
			this.newEdge(14, 19);
			this.newEdge(14, 20);
			this.newEdge(14, 21);
			this.newEdge(15, 16);
			this.newEdge(15, 21);
			this.newEdge(15, 22);
			this.newEdge(16, 17);
			this.newEdge(16, 21);
			this.newEdge(16, 22);
			this.newEdge(17, 23);
			this.newEdge(17, 28);
			this.newEdge(17, 30);
			this.newEdge(18, 19);
			this.newEdge(18, 24);
			this.newEdge(18, 29);
			this.newEdge(18, 30);
			this.newEdge(19, 20);
			this.newEdge(19, 24);
			this.newEdge(19, 25);
			this.newEdge(20, 21);
			this.newEdge(20, 25);
			this.newEdge(20, 26);
			this.newEdge(21, 22);
			this.newEdge(21, 26);
			this.newEdge(21, 27);
			this.newEdge(22, 23);
			this.newEdge(22, 27);
			this.newEdge(23, 27);
			this.newEdge(23, 28);
			this.newEdge(24, 25);
			this.newEdge(24, 29);
			this.newEdge(24, 30);
			this.newEdge(25, 30);
			this.newEdge(26, 27);
			this.newEdge(26, 30);
			this.newEdge(27, 28);
			this.newEdge(27, 30);
			this.newEdge(28, 30);
			this.newEdge(29, 30);
			System.out.println("OK");
		}
	}
	
	private void newEdge(int a, int b) {
		this._map.get(a).getNeighbours().put(b, this._map.get(b));
		this._map.get(b).getNeighbours().put(a, this._map.get(a));
	}
}
