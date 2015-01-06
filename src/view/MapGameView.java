package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.Folk;
import model.Model;


/**
 * <b>Game page</b>
 *
 * It is the page where we play
 *
 * @author Antonin WALTZ
 */

public class MapGameView extends JPanel {
	private static final long serialVersionUID = 1L;
	//Dimension
	int frameWidth;
	int frameHeight;

    private Model _m;

	//Background
	private String _backgroundImagePath = "images/gameview.png";

	private ArrayList<Polygon> _polygonList;
	private ArrayList<Event> _queue;
	private ArrayList<Rectangle> _folkList;
	private Rectangle _nextButton;
	private Rectangle _redeploy;
	private Rectangle _toDeline;

	private ArrayList<MouseListener> mouseEvent = new ArrayList<>();

	private JButton _quitGame;

	// Workaround for non constant window size
	private int[] computeX(int[] x) {
		for (int i=0;i<x.length;i++){
			x[i] = (int) ((x[i]/1440.0)*frameWidth);
		}
		return x;
	}
	private int[] computeY(int[] y) {
		for (int i=0;i<y.length;i++){
			y[i] = (int) ((y[i]/900.0)*frameHeight);
		}
		return y;
	}
	private int getBarycenterX(int id) {
		int x=0;
		for(int i=0;i<_polygonList.get(id).npoints;i++) {
			x += _polygonList.get(id).xpoints[i];
		}
		x /= _polygonList.get(id).npoints;
		return x;
	}
	private int getBarycenterY(int id) {
		int y=0;
		for(int i=0;i<_polygonList.get(id).npoints;i++) {
			y += _polygonList.get(id).ypoints[i];
		}
		y /= _polygonList.get(id).npoints;
		return y;
	}

	/**
	 * Constructor
	 * @param id
	 *
	 */
	MapGameView(int w, int h, Model m, ArrayList<Event> queue){
		this.frameWidth = w;
		this.frameHeight = h;
		this._m = m;
		this._queue = queue;
		this.setSize(frameWidth, frameHeight);
		_polygonList = new ArrayList<>();
		_folkList = new ArrayList<>();
		setFocusable(true);
		setDoubleBuffered(true);
		_quitGame = new JButton("Stopper la partie");
		_quitGame.setSize(200, 30);
		this.add(_quitGame);
	}
	
	public JButton getQuitButton() {
		return _quitGame;
	}

	//===========Add every shape here, index ordered===========================
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int x, y, w, h;
		//====BACKGROUND====//
		g.drawImage(new ImageIcon(_backgroundImagePath).getImage(), 0, 0,frameWidth, frameHeight, null);
		//====POLYGONES====//
		for(int i = 0; i < _polygonList.size(); i++){
			// ---BACKGROUND--- //
			if(_m.isOwner(i))
				g.setColor(new Color(0, 150, 0, 150));
			else if(_m.canAttack(i) && !_m.isRedeploying())
				g.setColor(new Color(0, 255, 0, 150));
			else
				g.setColor(new Color(255, 0, 0, 0));
			g.fillPolygon(_polygonList.get(i));
			// ---TokenNumber--- //
			g.setColor(new Color(0, 0, 0));
			if (_m.getMap().getCase(i).getTokenNb()!=0)
				g.drawString(_m.getMap().getCase(i).getTokenNb()+"",
					getBarycenterX(i),
					getBarycenterY(i));
		}
		//====FOLK-STACK====//
		g.setFont(new Font("Helvetica", Font.BOLD, (int)(0.015*frameWidth)));
		for(int i = 0; i < 5; i++){
			//BACKGROUND
			x = (int)_folkList.get(i).getX();
			y = (int)_folkList.get(i).getY();
			w = (int)_folkList.get(i).getWidth();
			h = (int)_folkList.get(i).getHeight();
			g.setColor(new Color(0, 0, 255));
			g.fillRect(x, y, w, h);
			//TEXT
			g.setColor(Color.BLACK);
			x += 10; 
			y += 20;
			g.drawString(_m.getAvailableFolks().get(i).getName()+" "+
						 _m.getAvailableFolks().get(i).getInitialTokNb(), x, y);
			g.drawString(_m.getAvailableFolks().get(i).getPower()+" "+
						 _m.getAvailableFolks().get(i).getPower().getInitialToken(), x, y+20);
		}
		//====PLAYER-INFO====//
		// ---Name--- //
		g.setFont(new Font("Helvetica", Font.BOLD, (int)(0.03*frameWidth)));
		g.setColor(Color.BLACK);
		g.drawString(_m.getCurrentPlayer().getName(),
				(int)(0.04*frameWidth), 
				(int)(0.9*frameHeight));
		// ---Money--- //
		g.setColor(Color.YELLOW);
		g.drawString(_m.getCurrentPlayer().getMoney()+"",
				(int)(0.14*frameWidth), 
				(int)(0.96*frameHeight));
		// ---Folk--- //
		g.setColor(Color.GREEN);
		g.setFont(new Font("Helvetica", Font.BOLD, (int)(0.02*frameWidth)));
		String f = (_m.getCurrentPlayer().getCurrentFolk() != null) ? 
				_m.getCurrentPlayer().getCurrentFolk().toString() : 
				"Pas de peuple";
		g.drawString(f,
				(int)(0.2*frameWidth), 
				(int)(0.9*frameHeight));
		// ---FreeFolkToken--- //
		g.setColor(new Color(0, 150, 0));
		g.drawString(_m.getCurrentPlayer().getNbFreeToken()+"",
				(int)(0.2*frameWidth), 
				(int)(0.96*frameHeight));
		//====ACTION-BUTTONS====//
		// ---NextPlayer--- //
		x = (int)_nextButton.getX();
		y = (int)_nextButton.getY();
		w = (int)_nextButton.getWidth();
		h = (int)_nextButton.getHeight();
		g.setColor(new Color(150, 150, 150));
		g.fillRect(x, y, w, h);
		x += (int)(0.005*frameWidth);
		y += (int)(0.04*frameHeight);
		if(_m.getCurrentPlayer().getNbFreeToken() == 0)
			g.setColor(Color.BLACK);
		else
			g.setColor(Color.GRAY);
		g.setFont(new Font("Helvetica", Font.BOLD, (int)(0.02*frameWidth)));
		g.drawString("Suivant", x, y);
		// ---Redeploying--- //
		x = (int)_redeploy.getX();
		y = (int)_redeploy.getY();
		w = (int)_redeploy.getWidth();
		h = (int)_redeploy.getHeight();
		g.setColor(new Color(150, 150, 150));
		g.fillRect(x, y, w, h);
		x += (int)(0.005*frameWidth);
		y += (int)(0.04*frameHeight);
		if(_m.canRedeploy())
			g.setColor(Color.BLACK);
		else
			g.setColor(Color.GRAY);
		g.drawString("Redeployer", x, y);
		// ---ToDeline--- //
		x = (int)_toDeline.getX();
		y = (int)_toDeline.getY();
		w = (int)_toDeline.getWidth();
		h = (int)_toDeline.getHeight();
		g.setColor(new Color(150, 150, 150));
		g.fillRect(x, y, w, h);
		x += (int)(0.005*frameWidth);
		y += (int)(0.04*frameHeight);
		if(_m.canDeline())
			g.setColor(Color.BLACK);
		else
			g.setColor(Color.GRAY);
		g.drawString("Passer en declin", x, y);
		
	}

	public void drawBoard() {
		//Coordonnées des polygones pour la petite map---------------------------
		int x[][] = {	//0
						{0,0,0,87,104,112,117,120,120,117,113,111,98,87,78,69,63,60,58,59,60,64,68,70,0},
						//1
						{99,325,312,300,293,287,283,280,278,279,280,165,163,163,117,122,126,127,127,124,120,115,108},
						//2
						{333,325,310,302,295,290,287,286,285,286,287,362,425,424,419,412,403},
						//3
						{410,426,433,431,441,450,464,478,556,553,558,529,593},
						//4
						{605,538,568,560,615,710,698,695,694,699,710,725,740,769},
						//5
						{776,744,717,702,701,703,715,718,716,775,851,860,901,876,877,987},
						//6
						{1000,884,912,865,782,861,876,894,911,936,954,972,989,1010,1010},
						//7
						{110,90,79,71,67,66,67,70,77,75,68,82,93,109,124,141,162,195,169,163},
						//8
						{281,171,249,380,343,342,355,362,366,366,365},
						//9
						{424,371,373,374,372,367,350,351,381,393,407,420,429,495,485,478,465,450,440,431},
						//10
						{559,491,503,519,537,556,569,621,611},
						//11
						{709,618,628,638,650,660,672,688,708,734,777,773,844,772},
						//12
						{0,62,72,83,101,118,146,175,200,234,234,282,236,214,191,174,152,133,119,106,82,0},
						//13
						{250,291,236,248,263,279,294,311,326,377,373,373,384,395,405,411,416,418,419,415,418,423,414,401,389,385},
						//14
						{423,427,427,424,418,409,397,382,394,411,428,439,451,457,550,605,606,604,563,549,539,524,507},
						//15
						{625,572,610,612,670,681,694,712,738,709,690,674,655,641},
						//16
						{719,743,750,833,835,835,914,881,874,864,786},
						//17
						{884,947,1010,1010,992,972,949,933,909},
						//18
						{0,84,100,119,137,161,187,214,230,228,230,243,252,265,279,295,315,298,298,303,250,207,202,196,0},
						//19
						{319,305,324,344,366,391,424,462,455,450,442,431,417,403,392,329},
						//20
						{463,469,468,464,541,588,546},
						//21
						{612,612,552,598,738,710,678,664,678,668},
						//22
						{677,685,686,672,683,715,740,797,810,828,750,735,718,706,699,689},
						//23
						{833,815,888,902,916,928,935,938,940,915},
						//24
						{311,257,210,222,347,377,364,346,331},
						//25
						{384,353,532,454,430,404},
						//26
						{592,548,707,659,601,597},
						//27
						{667,716,751,754,760,783,803,824,848,869,881,810,800,745,745,740},
						//28
						{945,942,937,927,915,894,876,852,823,792,761,1010,1010,1010,1010,1010},
						//29
						{0,5,197,203,245,202,213,0}
					};

		int y[][] = {	//0
						{125,125,125,125,141,152,163,175,190,201,213,221,224,227,233,239,248,257,269,281,294,307,319,326,350},
						//1
						{125,125,133,143,149,156,164,173,186,203,215,256,260,263,226,213,199,191,172,159,151,143,135},
						//2
						{125,133,143,152,159,168,177,186,198,208,217,237,200,196,160,144,125},
						//3
						{125,158,179,207,226,240,252,260,211,208,201,169,125},
						//4
						{125,170,200,208,259,216,206,199,190,184,178,173,169,125},
						//5
						{125,174,183,191,195,199,210,216,220,250,255,244,226,195,190,125},
						//6
						{125,193,228,250,349,358,347,335,328,320,318,321,327,339,125},
						//7
						{229,234,241,248,258,271,284,302,325,330,334,342,345,346,342,337,328,309,273,272},
						//8
						{223,262,362,350,315,295,282,274,264,255,246},
						//9
						{209,241,246,263,272,283,299,310,339,348,359,369,383,361,266,268,264,253,241,226},
						//10
						{217,261,359,358,359,362,366,324,266},
						//11
						{224,266,322,334,345,351,356,360,363,360,353,348,262,257},
						//12
						{357,335,346,351,353,352,343,329,314,356,362,424,469,468,470,475,483,493,503,513,534,521},
						//13
						{369,425,478,479,484,492,503,518,532,510,509,502,493,480,466,454,438,425,407,391,386,386,376,365,353,357},
						//14
						{392,408,428,444,461,478,491,504,503,506,513,520,529,535,535,497,482,474,370,366,365,364,364},
						//15
						{330,373,470,478,478,463,446,435,421,371,368,364,356,347},
						//16
						{370,417,413,448,452,458,435,356,356,364,356},
						//17
						{350,501,495,346,336,329,325,327,337},
						//18
						{528,542,528,512,499,487,478,475,474,478,485,487,487,493,501,514,532,565,571,575,593,573,571,571,666},
						//19
						{539,566,576,586,594,602,609,615,541,540,531,523,516,512,512,539},
						//20
						{541,616,620,622,699,615,543},
						//21
						{486,499,541,615,567,546,541,515,490,485},
						//22
						{480,485,492,514,534,540,559,543,544,455,421,428,437,445,453,462},
						//23
						{465,547,612,594,571,542,518,507,503,443},
						//24
						{578,598,644,701,701,606,602,596,588},
						//25
						{606,701,701,621,617,611,607},
						//26
						{620,701,701,602,622,624},
						//27
						{600,701,701,697,693,683,673,660,644,629,617,551,549,564,572,575},
						//28
						{508,524,542,567,589,615,633,651,670,688,701,701,701,701,502,502},
						//29
						{673,671,579,579,600,642,701,701}
					};

		//Création des polygones pour la petite map ------------------------------
		_polygonList.clear();
		for(int i=0; i<x.length; i++){
			_polygonList.add(new Polygon(computeX(x[i]), computeY(y[i]), x[i].length));
		}
		_folkList.clear();
		for(int i=0;i<6;i++){
			int X = (int)(0.75*frameWidth);
			int Y = (int)(0.3*frameHeight+0.11*frameHeight*i);
			int w = (int)(0.2*frameWidth);
			int h = (int)(0.1*frameHeight);
			_folkList.add(new Rectangle(X, Y, w, h));
		}
		_nextButton = new Rectangle((int)(0.61*frameWidth),
				(int)(0.92*frameHeight),
				(int)(0.1*frameWidth),
				(int)(0.05*frameHeight));
		_redeploy = new Rectangle((int)(0.56*frameWidth),
				(int)(0.865*frameHeight),
				(int)(0.15*frameWidth),
				(int)(0.05*frameHeight));
		_toDeline = new Rectangle((int)(0.38*frameWidth),
				(int)(0.92*frameHeight),
				(int)(0.22*frameWidth),
				(int)(0.05*frameHeight));

		//LISTENERS------------------------------------------------------------------------------------------

		for(MouseListener ml : mouseEvent) {
			removeMouseListener(ml);
		}
		mouseEvent.clear();
		//====Polys====//
		for(int i=0; i<x.length; i++){
			final int polId = i;
			final Model mod = _m;
			mouseEvent.add(new MouseAdapter() {
				public synchronized void mouseClicked(MouseEvent me) {
					if (_polygonList.get(polId).contains(me.getPoint())) {
						if(mod.isOwner(polId) && 
								mod.isRedeploying() && 
								mod.getCurrentPlayer().getNbFreeToken()>0) {
							_queue.add(new Event(EventType.CLICKPOLY, polId));
						}
						else if(mod.getCurrentPlayer().canAttack(mod.getMap().getCase(polId)) &&
								!mod.isRedeploying()) {
							_queue.add(new Event(EventType.ATTACKCASE, polId));
						}
					}
					
				};
			});
		}
		//====FolkStack====//
		for(int i=0; i<6; i++){
			final int folkId = i;
			final Model mod = _m;
			mouseEvent.add(new MouseAdapter() {
				public synchronized void mouseClicked(MouseEvent me) {
					if (_folkList.get(folkId).contains(me.getPoint()) &&
							!mod.hasActivePlayerAnActiveFolk()) {
						_queue.add(new Event(EventType.SELECTFOLKPOWER, folkId));
					}
				};
			});
		}
		//====ActionButtons====//
		mouseEvent.add(new MouseAdapter() {
			public synchronized void mouseClicked(MouseEvent me) {
				if (_nextButton.contains(me.getPoint()) &&
						_m.getCurrentPlayer().getNbFreeToken() == 0) {
					_queue.add(new Event(EventType.NEXTPLAYER));
				}
			};
		});
		mouseEvent.add(new MouseAdapter() {
			public synchronized void mouseClicked(MouseEvent me) {
				if (_redeploy.contains(me.getPoint())) {
					_queue.add(new Event(EventType.REDEPLOY));
				}
			};
		});
		mouseEvent.add(new MouseAdapter() {
			public synchronized void mouseClicked(MouseEvent me) {
				if (_toDeline.contains(me.getPoint()) && _m.canDeline()) {
					_queue.add(new Event(EventType.FOLKTODECLINE));
				}
			};
		});
		for(MouseListener m1 : mouseEvent) {
			this.addMouseListener(m1);
		}
	}

	public void refresh(int w, int h) {
		if (w != frameWidth || h != frameHeight) {
			frameWidth = w;
			frameHeight = h;
			drawBoard();
		}
		_quitGame.setLocation(frameWidth-210, 10);
		repaint();
	}
}
