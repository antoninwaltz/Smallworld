package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import model.Model;

import java.util.ArrayList;


/**
 * <b>Game page</b>
 * 
 * It is the page where we play
 * 
 * @author Antonin WALTZ
 */

public class MapGameView extends JLayeredPane implements MouseListener {
	private static final long serialVersionUID = 1L;
	//Dimension
	final int frameWidth = View.getFrameSize().width;
	final int frameHeight = View.getFrameSize().height;

    private Model _m;
	
	//Background
	private String _backgroundImagePath = "images/Plateau_V1.png";
	private Image _background;
	private JPanel _backPanel, _jPanel;
	
	private ArrayList<Polygon> _polygonList;
	private ArrayList<Event> _queue;
	
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
	
	/**
	 * Constructor
	 * @param id 
	 * 
	 */
	MapGameView(Model m, ArrayList<Event> queue){
		this._m = m;
		this._queue = queue;
		
		//setFocusable(true);
		setDoubleBuffered(true);
		//Background-----------------------------------------------------------
		ImageIcon _iconimage = new ImageIcon(_backgroundImagePath);
		_background = _iconimage.getImage();
		
		_backPanel = new JPanel(){
			private static final long serialVersionUID = 1L;
			public void paint(Graphics g) {
				g.drawImage(_background, 0, 0,frameWidth, frameHeight, null);
			}
		};
		
		_backPanel.setSize(new Dimension(frameWidth, frameHeight));
		//-----------------------------------------------------------------------
		
		
		//Coordonnées des polygones pour la petite map---------------------------

		int x[][] = {	//0
						{0,87,104,112,117,120,120,117,113,111,98,87,78,69,63,60,58,59,60,64,68,70,0},
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
						//{0,84,100,122,150,176,205,230,228,238,249,261,276,290,315,297,297,303,251,208,204,199},
						
					};
		
		int y[][] = {	//0
						{125,125,141,152,163,175,190,201,213,221,224,227,233,239,248,257,269,281,294,307,319,326,350},
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
						//{528,542,527,509,491,481,475,474,483,486,491,498,509,532,564,570,573,594,573,570,571,665},
						
					};
		
		//Création des polygones pour la petite map ------------------------------
		
		_polygonList = new ArrayList<Polygon>();
		
		for(int i=0; i<x.length; i++){
			_polygonList.add(new Polygon(computeX(x[i]), computeY(y[i]), x[i].length));
		}
		
		//Ajout des polygones au JPanel -------------------------------------------
		_jPanel = new JPanel(){
			private static final long serialVersionUID = 1L;
			public void paint(Graphics g) {
				for(int i = 0; i < _polygonList.size(); i++){
					if(_m.getCurrentPlayer().canAttack(_m.getMap().getCase(i)))
						g.setColor(new Color(0, 255, 0, 150));
					else
						g.setColor(new Color(255, 0, 0, 150));
					g.fillPolygon(_polygonList.get(i));
				}
			}
		};
		
		//LISTENER-------------------------------------------------------------------------------------------
		
		ArrayList<MouseAdapter> ma = new ArrayList<>();
		for(int i=0; i<x.length; i++){
			final int polId = i;
			ma.add(new MouseAdapter() {
				public void mouseClicked(MouseEvent me) {
					super.mouseClicked(me);
					if (_polygonList.get(polId).contains(me.getPoint())) {
						_queue.add(new Event(EventType.CLICKPOLY, polId));
					}
				};
			});
		}
		for(MouseAdapter m1 : ma) {
			_jPanel.addMouseListener(m1);
		}
		
	//--------------------------------------------------------------------------------------------------------	
		_jPanel.setSize(frameWidth,frameHeight);
		this.add(_backPanel, 1);
		this.add(_jPanel, 2);
	}

	//--------------------------------------------------------------------------------------------------------

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		// forcement utile
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		// peut etre utile pour afficher la case en vert/rouge au survol,
		// et pas en permanence
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		// idem
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		// useless
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		// idem
	}
	
	public void boardInit() { // TODO could handle huge part of the constructor
		_jPanel.revalidate();
	}
	
	public void repaint() {
		_backPanel.repaint();
		_jPanel.repaint();
	}
}
