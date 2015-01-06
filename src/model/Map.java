package model;

import java.io.Serializable;
import java.util.HashMap;

public class Map implements Serializable {
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
		if (this._type == MapType.SMALL) {
			System.out.print("Building a small map... ");
			this._map.put(0, new Case(0, CaseType.QUESTIONNER_CREER));
			this._map.put(1, new Case(1, CaseType.EXPRESSION_COMMUNICATION));
			this._map.put(2, new Case(2, CaseType.CONNAISSANCE_SCIENTIFIQUE));
			this._map.put(3, new Case(3, CaseType.STAGE));
			this._map.put(4, new Case(4, CaseType.ORGANISER_MANAGER));
			this._map.put(5, new Case(5, CaseType.EXPRESSION_COMMUNICATION));
			this._map.put(6, new Case(6, CaseType.ORGANISER_MANAGER));
			this._map.put(7, new Case(7, CaseType.STAGE));
			this._map.put(8, new Case(8, CaseType.ORGANISER_MANAGER));
			this._map.put(9, new Case(9, CaseType.TECHNIQUE_ET_METHODE));
			this._map.put(10, new Case(10, CaseType.CONNAISSANCE_SCIENTIFIQUE));
			this._map.put(11, new Case(11, CaseType.STAGE));
			this._map.put(12, new Case(12, CaseType.CONNAISSANCE_SCIENTIFIQUE));
			this._map.put(13, new Case(13, CaseType.ORGANISER_MANAGER));
			this._map.put(14, new Case(14, CaseType.QUESTIONNER_CREER));
			this._map.put(15, new Case(15, CaseType.CONNAISSANCE_SCIENTIFIQUE));
			this._map.put(16, new Case(16, CaseType.TECHNIQUE_ET_METHODE));
			this._map.put(17, new Case(17, CaseType.CONNAISSANCE_SCIENTIFIQUE));
			this._map.put(18, new Case(18, CaseType.STAGE));
			this._map.put(19, new Case(19, CaseType.EXPRESSION_COMMUNICATION));
			this._map.put(20, new Case(20, CaseType.TECHNIQUE_ET_METHODE));
			this._map.put(21, new Case(21, CaseType.ORGANISER_MANAGER));
			this._map.put(22, new Case(22, CaseType.STAGE));
			this._map.put(23, new Case(23, CaseType.TECHNIQUE_ET_METHODE));
			this._map.put(24, new Case(24, CaseType.CONNAISSANCE_SCIENTIFIQUE));
			this._map.put(25, new Case(25, CaseType.CONNAISSANCE_SCIENTIFIQUE));
			this._map.put(26, new Case(26, CaseType.EXPRESSION_COMMUNICATION));
			this._map.put(27, new Case(27, CaseType.EXPRESSION_COMMUNICATION));
			this._map.put(28, new Case(28, CaseType.QUESTIONNER_CREER));
			this._map.put(29, new Case(29, CaseType.TECHNIQUE_ET_METHODE)); 
			this._map.put(4000, new Case(4000, CaseType.BORDER));
			this.newEdge(0, 1);
			this.newEdge(0, 7);
			this.newEdge(0, 12);
			this.newEdge(0, 4000);
			this.newEdge(1, 2);
			this.newEdge(1, 7);
			this.newEdge(1, 8);
			this.newEdge(1, 4000);
			this.newEdge(2, 3);
			this.newEdge(2, 8);
			this.newEdge(2, 9);
			this.newEdge(2, 4000);
			this.newEdge(3, 4);
			this.newEdge(3, 9);
			this.newEdge(3, 10);
			this.newEdge(3, 4000);
			this.newEdge(4, 5);
			this.newEdge(4, 10);
			this.newEdge(4, 11);
			this.newEdge(4, 4000);
			this.newEdge(5, 6);
			this.newEdge(5, 11);
			this.newEdge(5, 4000);
			this.newEdge(6, 11);
			this.newEdge(6, 16);
			this.newEdge(6, 17);
			this.newEdge(6, 4000);
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
			this.newEdge(12, 4000);
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
			this.newEdge(17, 4000);
			this.newEdge(18, 19);
			this.newEdge(18, 24);
			this.newEdge(18, 29);
			this.newEdge(18, 4000);
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
			this.newEdge(24, 4000);
			this.newEdge(25, 4000);
			this.newEdge(26, 27);
			this.newEdge(26, 4000);
			this.newEdge(27, 28);
			this.newEdge(27, 4000);
			this.newEdge(28, 4000);
			this.newEdge(29, 4000);
			System.out.println("OK");
		}
	}
	
	private void newEdge(int a, int b) {
		this._map.get(a).getNeighbours().put(b, this._map.get(b));
		this._map.get(b).getNeighbours().put(a, this._map.get(a));
	}
	
	public Case getCase(int caseId) {
		return this._map.get(caseId);
	}

	public void clear() {
		for (Case c : _map.values())
			c.free();
	}
}
