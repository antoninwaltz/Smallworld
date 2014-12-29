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

import java.util.ArrayList;


/**
 * <b>Game page</b>
 * 
 * It is the page where we play
 * 
 * @author Antonin WALTZ
 */

public class MapGameView extends JLayeredPane implements MouseListener {
	
	//Dimension
	final int frameWidth = View.getFrameSize().width;
	final int frameHeight = View.getFrameSize().height;
	
	//Background
	private String _backgroundImagePath = "images/Plateau_V1.png";
	private Image _background;
	private JPanel _backPanel,_jPanel;
	
	private ArrayList<Polygon> _arrayListPolygon;
	private ArrayList<MouseAdapter> _arrayListMouseAdapter;
	
	private Polygon _polygon1, _polygon2;
	
	/**
	 * Constructor
	 * 
	 */
	MapGameView(){
		
		//Background-----------------------------------------------------------
		ImageIcon _iconimage = new ImageIcon(_backgroundImagePath);
		_background = _iconimage.getImage();
		
		_backPanel = new JPanel(){
			
			public void paint(Graphics g) {
				g.drawImage(_background, 0, 0,frameWidth, frameHeight, null);
				
			}
			
		};
		
		_backPanel.setSize(new Dimension(frameWidth, frameHeight));
		setFocusable(true);
		setDoubleBuffered(true);
		this.add(_backPanel,new Integer(0));
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
					};
		
		//Création des polygones pour la petite map ------------------------------
		
		_arrayListPolygon = new ArrayList<Polygon>();
		
		for(int i=0; i<x.length; i++){
			
			_arrayListPolygon.add(new Polygon(x[i], y[i], x[i].length));
			
		}
		
		//Ajout des polygones au JPanel -------------------------------------------
		_jPanel = new JPanel(){
				public void paint(Graphics g) {
					
					for(int i = 0; i < 8; i++){
						g.setColor(Color.BLACK);
						g.fillPolygon(_arrayListPolygon.get(i));
					}
				}
		};
		
		//LISTENER-------------------------------------------------------------------------------------------
		
		//0
		MouseAdapter ma0 = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				super.mouseClicked(me);

				if (_arrayListPolygon.get(0).contains(me.getPoint())) {
					System.out.println("Clicked polygon 0");
				}

			};
		};
		_jPanel.addMouseListener(ma0);
		
		//1
		MouseAdapter ma1 = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				super.mouseClicked(me);

				if (_arrayListPolygon.get(1).contains(me.getPoint())) {
					System.out.println("Clicked polygon 1");
					
				}

			};
		};
		_jPanel.addMouseListener(ma1);
		
		//2
		MouseAdapter ma2 = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				super.mouseClicked(me);

				if (_arrayListPolygon.get(2).contains(me.getPoint())) {
					System.out.println("Clicked polygon 2");
							
				}

			};
		};
		_jPanel.addMouseListener(ma2);
		
		//3
		MouseAdapter ma3 = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				super.mouseClicked(me);
				if (_arrayListPolygon.get(3).contains(me.getPoint())) {
					System.out.println("Clicked polygon 3");
							
				}

			};
		};
		_jPanel.addMouseListener(ma3);
		
		//4
		MouseAdapter ma4 = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				super.mouseClicked(me);

				if (_arrayListPolygon.get(4).contains(me.getPoint())) {
					System.out.println("Clicked polygon 4");
				}

			};
		};
		_jPanel.addMouseListener(ma4);
		
		//5
		MouseAdapter ma5 = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				super.mouseClicked(me);

				if (_arrayListPolygon.get(5).contains(me.getPoint())) {
					System.out.println("Clicked polygon 5");
				}

			};
		};
		_jPanel.addMouseListener(ma5);
		
		//6
		MouseAdapter ma6 = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				super.mouseClicked(me);

				if (_arrayListPolygon.get(6).contains(me.getPoint())) {
					System.out.println("Clicked polygon 6");
				}

			};
		};
		_jPanel.addMouseListener(ma6);
		
		//7
		MouseAdapter ma7 = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				super.mouseClicked(me);

				if (_arrayListPolygon.get(7).contains(me.getPoint())) {
					System.out.println("Clicked polygon 7");
				}

			};
		};
		_jPanel.addMouseListener(ma7);
		
		
	//--------------------------------------------------------------------------------------------------------	
		_jPanel.setSize(1400,800);
		this.add(_jPanel,new Integer(1));
		
		
	}

	//--------------------------------------------------------------------------------------------------------

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
