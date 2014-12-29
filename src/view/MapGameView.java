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
import javax.swing.JFrame;
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
	private JPanel _backPanel,p,_jPanel;
	
	private ArrayList<Polygon> _arrayListPolygon;
	private ArrayList<JPanel> _arrayListJPanel;
	
	private Polygon _polygon1, _polygon2;
	
	/**
	 * Constructor
	 * 
	 */
	@SuppressWarnings("serial")
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

		int x[][] = {	{0,93,124,113,70,60,72,0},
						{0,60,93,147,200,285,235,158,81,0},
					};
		
		int y[][] = {	{125,125,176,224,241,266,324,352},
						{350,332,349,339,312,425,472,481,536,525},
					};
		
		//-----------------------------------------------------------------------
		
		_arrayListPolygon = new ArrayList<Polygon>();
		_arrayListJPanel = new ArrayList<JPanel>();
		_polygon1 = new Polygon(x[0], y[0], x[0].length);
		_polygon2 = new Polygon(x[1], y[1], x[1].length);
		
		_jPanel = new JPanel(){
				public void paint(Graphics g) {
					g.setColor(Color.RED);
					g.fillPolygon(_polygon1);
					g.setColor(Color.GREEN);
					g.fillPolygon(_polygon2);
			}
		};
		
		MouseAdapter ma = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				super.mouseClicked(me);

				if (_polygon1.contains(me.getPoint())) {
					System.out.println("Clicked polygon");
				}

			};
		};
		
		MouseAdapter ma2 = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				super.mouseClicked(me);

				if (_polygon2.contains(me.getPoint())) {
					System.out.println("Clicked polygon 2");
					
				}

			};
		};
		
		_jPanel.addMouseListener(ma);
		_jPanel.addMouseListener(ma2);
		
		_jPanel.setSize(1000,1000);
		this.add(_jPanel,new Integer(1));
		
		


		//----------------------------------------------------------------------

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
		// TODO Auto-generated method stub
		
	
	
		//-------------------------------------------------------------------------------

	
/*	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (_polygon.contains(e.getPoint())) {
            System.out.println("LOOOL");
        }
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
	
	/*
		for(int i = 0; i < 2; i++){
			
			_polygon[i] = new Polygon(x[i], y[i], x[i].length);
			_arrayListPolygon.add(_polygon[i]);
			System.out.println("POLYGON i : "+_polygon[i]);
			
			_arrayListJPanel.add(new JPanel(){
				public void paintComponent(Graphics g) {
					
					g.setColor(Color.RED);
					System.out.println("ARRAYLISTPOLYGON GET DANS LA METHODE PAINT : "+ (_arrayListPolygon.size() - 1));
					g.fillPolygon(_arrayListPolygon.get(_arrayListPolygon.size()-1));
				}
				
				public Dimension getPreferredSize() {
					return new Dimension(1000, 1000);
				}	
			});
			System.out.println("ARRAYLISTJPANEL SIZE : "+_arrayListJPanel.get(i));
			
		}

		MouseAdapter ma = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				super.mouseClicked(me);

				if (_arrayListPolygon.get(0).contains(me.getPoint())) {
					System.out.println("Clicked polygon");
				}

			};
		};
		
		MouseAdapter ma2 = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				super.mouseClicked(me);

				if (_arrayListPolygon.get(1).contains(me.getPoint())) {
					System.out.println("Clicked polygon 2");
					
				}

			};
		};
			_arrayListJPanel.get(0).addMouseListener(ma);
			_arrayListJPanel.get(1).addMouseListener(ma2);
			
			_arrayListJPanel.get(0).setSize(1000,1000);
			_arrayListJPanel.get(1).setSize(1000,500);
			
			//_arrayListJPanel.get(i).addMouseListener(this);
			
			//System.out.print("Start..."+i);
			//this.add(_arrayListJPanel.get(i),new Integer(1));
			//System.out.println(_arrayListJPanel.get(i)+"OK");
			//this.add(_arrayListJPanel.get(0),new Integer(2));
			//this.add(_arrayListJPanel.get(1),new Integer(3));
			
		
       */


}
