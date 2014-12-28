package view;


import model.Model;

import view.HomeView;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
public class View extends JFrame implements ActionListener {
	/**
	 * The model attribute
	 * @see Model
	 */
	private Model m;
	
	private HomeView _HomeView;;
	private PseudoPlayersView _PseudoPlayersView;
	private RulesView _RulesView;
	
	/**
	 * Attributes for the buttons of the menus
	 * 
	 * @see View
	 */
	private JButton _backfromPseudoButton, _backfromRulesButton;
	private JButton _newButton, _loadButton, _rulesButton, _exitButton, _addButton, _removeButton ;
	private JButton _okButton;
	
	
	/**
	 * <b>Constructor of the view</b>
	 * 
	 * @param m The model used by the view
	 */
	public View(Model m) {
		
		this.m = m;
		
		//Views
		_HomeView = new HomeView();
		_PseudoPlayersView = new PseudoPlayersView();
		_RulesView = new RulesView();
		
		//Page de départ
		this.setContentPane(_HomeView);
		//this.setContentPane(_PseudoPlayersView);
		
		//Récupération des boutons de retour
		_backfromRulesButton = _RulesView.getBackButton();
		_backfromRulesButton.addActionListener(this);
		
		//Récupération du bouton de nouvelle partie
		_newButton = _HomeView.getNewButton();
		_newButton.addActionListener(this);
				
		//Récupération du bouton de chargement d'une partie
		_loadButton = _HomeView.getLoadButton();
		_loadButton.addActionListener(this);
				
		//Récupération du bouton des règles
		_rulesButton = _HomeView.getRulesButton();
		_rulesButton.addActionListener(this);
				
		//Récupération du bouton exit
		_exitButton = _HomeView.getExitButton();
		_exitButton.addActionListener(this);
		
		//Récupération du bouton exit
		_exitButton = _HomeView.getExitButton();
		_exitButton.addActionListener(this);
				
		//Récupération du bouton OK dans le menu de creation des profils joueurs
		_okButton = _PseudoPlayersView.getOkButton();
		_okButton.addActionListener(this);
		
		//Récupération du bouton Add dans le menu de creation des profils joueurs
		_addButton = _PseudoPlayersView.getAddButton();
		_addButton.addActionListener(this);
		
		//Récupération du bouton Add dans le menu de creation des profils joueurs
		_removeButton = _PseudoPlayersView.getRemoveButton();
		_removeButton.addActionListener(this);
		
		
		//Paramètre de base
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
	
	public PseudoPlayersView getPseudoPlayersView() {
		return _PseudoPlayersView;
	}
	
	public RulesView getRulesView() {
		return _RulesView;
	}
	
	static public Dimension getFrameSize() {
		Dimension frameSize = new Dimension(900,600);
		return frameSize;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
				
		//Si on appuie sur le bouton des regles, on ouvre le menu des regles
		if (e.getSource().equals(_rulesButton)) {
			this.setContentPane(_RulesView);
			_RulesView.requestFocus();
			this.setVisible(true);
		}
				
		//Si on appuie sur le bouton exit, on vérifie que l'utilisateur veut bien quitter la partie, puis on quitte ou non
		if (e.getSource().equals(_exitButton)) {
			int option = javax.swing.JOptionPane.showConfirmDialog(null, "Are you sure ?", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(option == JOptionPane.OK_OPTION) {
				System.exit(0);
			}
		}
		
		//Si on appuie sur le bouton return home en étant sur la page des regles,
		//On retourne sur la page d'accueil
		if (e.getSource().equals(_backfromRulesButton)) {
			this.setContentPane(_HomeView);
			_HomeView.requestFocus();
			this.setVisible(true);
		}
	}
}
	

