package view;


import model.Model;

import view.HomeView;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * <b>The View class</b>
 * 
 * It contains the model
 * 
 * Handles the graphical interface
 * 
 * @see Model
 * 
 * @author Skia
 */
public class View extends JFrame {
	/**
	 * The model attribute
	 * @see Model
	 */
	private Model m;
	
	private HomeView _HomeView;
	private NbPlayersView _NbPlayersView;
	private PseudoPlayersView _PseudoPlayersView;
	private RulesView _RulesView;
	
	/**
	 * <b>Constructor of the view</b>
	 * 
	 * @param m The model used by the view
	 */
	public View(Model m) {
		
		this.m = m;
		
		//Views
		_HomeView = new HomeView();
		_NbPlayersView = new NbPlayersView();
		_PseudoPlayersView = new PseudoPlayersView();
		_RulesView = new RulesView();
		
		this.setContentPane(_HomeView);
		
		this.setTitle("Smallworld UTBM");
		this.setSize((int)View.getFrameSize().getWidth(),(int)View.getFrameSize().getHeight());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		
	}
	
	public HomeView getHomeView() {
		return _HomeView;
	}
	
	public NbPlayersView getNbPlayersView() {
		return _NbPlayersView;
	}
	
	public PseudoPlayersView getPseudoPlayersView() {
		return _PseudoPlayersView;
	}
	
	public RulesView getRulesView() {
		return _RulesView;
	}
	
	static public Dimension getFrameSize() {
		Dimension frameSize = new Dimension(1200,800);
		return frameSize;
	}
}
