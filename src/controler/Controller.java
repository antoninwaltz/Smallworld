package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import view.HomeView;
import view.NbPlayersView;
import view.PseudoPlayersView;
import view.RulesView;
import view.View;
import model.Case;
import model.Model;
import modelExceptions.TooFewToken;
import modelExceptions.Unreachable;

/**
 * <b>The Controller class</b>
 * 
 * It contains the model and the view
 * 
 * @see Model
 * @see View
 * 
 * @author Skia
 */
public class Controller implements ActionListener {
	/**
	 * The Model attribute
	 * 
	 * @see Model
	 */
	private Model m;
	/**
	 * The View attribute
	 * 
	 * @see View
	 */
	private View v;
	
	/**
	 * Attributes for the menus
	 * 
	 * @see View
	 */
	private HomeView _ControlHomeView;
	private NbPlayersView _ControlNbPlayersView;
	private PseudoPlayersView _ControlPseudoPlayersView;
	private RulesView _ControlRulesView;
	
	/**
	 * Attributes for the buttons of the menus
	 * 
	 * @see View
	 */
	private JButton _backfromNbPlayersButton, _backfromPseudoButton, _backfromRulesButton;
	private JButton _newButton, _loadButton, _rulesButton, _exitButton;
	private JButton _2PlayersButton, _3PlayersButton, _4PlayersButton, _5PlayersButton;
	private JButton _okButton;
	

	/**
	 * Constructor of the {@link Controller}
	 * 
	 * @param m
	 *            The {@link Model}
	 * @param v
	 *            The {@link View}
	 */
	public Controller(Model m, View v) {
		this.m = m;
		this.v = v;
	}

	/**
	 * The main function to play
	 * 
	 * It synchronizes the view and model in order to have a playable game
	 */
	public void play() {
		m.newPlayer("Skia");
		m.newPlayer("Gobelin");
		m.initGame();
		m.initMap();
		m.printAvailaibleFolk();
		m.selectPlayerFolk(5);
		System.out.println(m.getCurrentPlayer());
		m.nextPlayer();
		m.selectPlayerFolk(2);
		System.out.println(m.getCurrentPlayer());
		m.nextPlayer();
		m.selectPlayerFolk(3);
		System.out.println(m.getCurrentPlayer());
		m.nextPlayer();
		m.printAvailaibleFolk();
		
		//Récupération des vues du menu
		_ControlHomeView = v.getHomeView();
		_ControlNbPlayersView = v.getNbPlayersView();
		_ControlPseudoPlayersView = v.getPseudoPlayersView();
		_ControlRulesView = v.getRulesView();
		
		//Récupération des boutons de retour
		_backfromNbPlayersButton = _ControlNbPlayersView.getBackButton();
		_backfromNbPlayersButton.addActionListener(this);
		_backfromPseudoButton = _ControlPseudoPlayersView.getBackButton();
		_backfromPseudoButton.addActionListener(this);
		_backfromRulesButton = _ControlRulesView.getBackButton();
		_backfromRulesButton.addActionListener(this);
		
		//Récupération du bouton de nouvelle partie
		_newButton = _ControlHomeView.getNewButton();
		_newButton.addActionListener(this);
		
		//Récupération du bouton de chargement d'une partie
		_loadButton = _ControlHomeView.getLoadButton();
		_loadButton.addActionListener(this);
		
		//Récupération du bouton des règles
		_rulesButton = _ControlHomeView.getRulesButton();
		_rulesButton.addActionListener(this);
		
		//Récupération du bouton exit
		_exitButton = _ControlHomeView.getExitButton();
		_exitButton.addActionListener(this);
		
		//Récupération du nombre de joueurs
		_2PlayersButton = _ControlNbPlayersView.getTwoButton();
		_2PlayersButton.addActionListener(this);
		_3PlayersButton = _ControlNbPlayersView.getThreeButton();
		_3PlayersButton.addActionListener(this);
		_4PlayersButton = _ControlNbPlayersView.getFourButton();
		_4PlayersButton.addActionListener(this);
		_5PlayersButton = _ControlNbPlayersView.getFiveButton();
		_5PlayersButton.addActionListener(this);
		
		//Récupération du bouton OK dans le menu de pseudo des joueurs
		_okButton = _ControlPseudoPlayersView.getOkButton();
		_okButton.addActionListener(this);
		
		/** TODO Problème pour récupérer les pseudos des joueurs 
		 * 
		 * Boucle en fonction du nombre de joueurs ?
		 * Visibilité de chaque textfield en fonction du nombre de joueurs ?
		 * 
		 * */
		
		
		int i = 0;
		while (true) {
			if (i > 3)
				break;
			else
				i++;

			System.out.println("\nActive player: " + m.getCurrentPlayer());
			if (!m.hasActivePlayerAnActiveFolk()) {
				m.selectActivePlayerFolk((int) (Math.random() * 10) % 6);
			} else {
				while (true) {
					Case r = m.getMap().getCase(0);
					while (!m.getCurrentPlayer().canAttack(r)) {
						r = m.getMap().getCase((r.getId() + 1));
					}
					System.out.println(m.getCurrentPlayer().getName()
							+ " attacks case " + r.getId() + " that contains " + 
							r.getTokenNb() + " tokens");
					try {
						m.attackCase(r);
					} catch (TooFewToken e) {
						System.out.println("FAIL - TooFewToken");
						e.printStackTrace();
						break;
					} catch (Unreachable e) {
						System.out.println("FAIL - Unreachable");
						e.printStackTrace();
					}
					if(m.getCurrentPlayer().getNbFreeToken() == 0)
						break;
				}
				System.out.println(m.getCurrentPlayer());
			}
			m.nextPlayer();
		}
		m.printAvailaibleFolk();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Si on appuie sur le bouton de nouvelle partie, on ouvre le menu de sélection du nombre de joueurs
		if (e.getSource().equals(_newButton)) {
			v.setContentPane(_ControlNbPlayersView);
			_ControlNbPlayersView.requestFocus();
			v.setVisible(true);
		}
		
		//Si on appuie sur le bouton des regles, on ouvre le menu des regles
		if (e.getSource().equals(_rulesButton)) {
			v.setContentPane(_ControlRulesView);
			_ControlRulesView.requestFocus();
			v.setVisible(true);
		}
		
		//Si on appuie sur le bouton exit, on vérifie que l'utilisateur veut bien quitter la partie, puis on quitte ou non
		if (e.getSource().equals(_exitButton)) {
			int option = javax.swing.JOptionPane.showConfirmDialog(null, "Are you sure ?", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(option == JOptionPane.OK_OPTION) {
				System.exit(0);
			}
		}
		
		//Si on appuie sur les boutons de retour, on revient au menu d'accueil
		if(e.getSource().equals(_backfromNbPlayersButton)||e.getSource().equals(_backfromPseudoButton)||e.getSource().equals(_backfromRulesButton)) {
			v.setContentPane(_ControlHomeView);
			_ControlHomeView.requestFocus();
			v.setVisible(true);
		}
		
		
		
	}
}
