package view;


import model.Model;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
	private static final long serialVersionUID = 1L;

	/**
	 * The model attribute
	 * @see Model
	 */
	private Model m;
	
	/**
	 * The stack attribute
	 * 
	 * it contains the stack of events
	 * 
	 * @see Stack
	 */
	private ArrayList<Event> _eventStack;
	
	/**
	 * The different pages of the view
	 * 
	 * @see View
	 */
	private HomeView _HomeView;;
	//private PseudoPlayersView _PseudoPlayersView;
	private RulesView _RulesView;
	private MapGameView _MapGameView;
	
	/**
	 * Attributes for the buttons of the menus
	 * 
	 * @see View
	 */
	private JButton _backfromRulesButton;
	private JButton _newButton, _loadButton, _rulesButton, _exitButton, _addButton, _removeButton ;
		
	/**
	 * <b>Constructor of the view</b>
	 * 
	 * @param m The model used by the view
	 */
	public View(Model m) {
		
		this.m = m;
		this._eventStack = new ArrayList<>();
		//Views
		_HomeView = new HomeView(m);
		//_PseudoPlayersView = new PseudoPlayersView();
		_RulesView = new RulesView();
		_MapGameView = new MapGameView(_eventStack);
		
		//Page de départ
		_HomeView.refresh(m.getPlayers());
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
		
		_addButton = _HomeView.getAddButton();
		_addButton.addActionListener(this);
		
		_removeButton = _HomeView.getDelButton();
		_removeButton.addActionListener(this);
				
		//Récupération du bouton OK dans le menu de creation des profils joueurs
		//_okButton = _PseudoPlayersView.getOkButton();
		//_okButton.addActionListener(this);
		
		//Récupération du bouton Add dans le menu de creation des profils joueurs
		//_addButton = _PseudoPlayersView.getAddButton();
		//_addButton.addActionListener(this);
		
		//Récupération du bouton Add dans le menu de creation des profils joueurs
		//_removeButton = _PseudoPlayersView.getRemoveButton();
		//_removeButton.addActionListener(this);
		
		
		//Paramètre de base
		this.setTitle("Smallworld UTBM");
		this.setSize(getFrameSize().width, getFrameSize().height);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		
	}
	
	public HomeView getHomeView() {
		return _HomeView;
	}
	
	//public PseudoPlayersView getPseudoPlayersView() {
	//	return _PseudoPlayersView;
	//}
	
	public RulesView getRulesView() {
		return _RulesView;
	}
	
	static public Dimension getFrameSize() {
		Dimension frameSize = new Dimension(1440,900);
		return frameSize;
	}

	@Override
	public synchronized void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(_rulesButton)) 
		{
			this.setContentPane(_RulesView);
			_RulesView.requestFocus();
			this.setVisible(true);
		} 
		else if (e.getSource().equals(_exitButton)) 
		{
			int option = javax.swing.JOptionPane.showConfirmDialog(null, "Are you sure ?", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(option == JOptionPane.OK_OPTION) {
				System.exit(0);
			}
		} 
		else if (e.getSource().equals(_backfromRulesButton)) 
		{
			this.setContentPane(_HomeView);
		} 
		else if (e.getSource().equals(_newButton)) 
		{
			if(m.getPlayers().size() < 2){
				JOptionPane.showMessageDialog(this, "Not enough players");

			} else {
				Event _newGame = new Event(EventType.NEWGAME);
				this._eventStack.add(_newGame);
				this.setContentPane(_MapGameView);
				this.getContentPane().revalidate();
				this.getContentPane().setVisible(true);

				System.out.println(this._eventStack.get(0).getEventType());
			}
		}
		else if (e.getSource().equals(_addButton)) 
		{
			if(m.getPlayers().size() >= 6) {
				JOptionPane.showMessageDialog(this, "Too much players");
			} else {
				String  name = null;
				while(name==null)
					name = (String)javax.swing.JOptionPane.showInputDialog(this,
						"Enter your name:",
						"Name",
						JOptionPane.PLAIN_MESSAGE);
				Event ev = new Event(EventType.NEWPLAYER, name);
				this._eventStack.add(ev);
				
				System.out.println(this._eventStack.get(0).getEventType() + " "+name);
			}
		}
		else if (e.getSource().equals(_removeButton)) 
		{
			if(m.getPlayerList().length==0){
				JOptionPane.showMessageDialog(this, "No player");
			} else {
				String s = (String)JOptionPane.showInputDialog(this,
						"Which player?",
						"Who",
						JOptionPane.PLAIN_MESSAGE,
						null,
						m.getPlayerList(),
						null);
				if(s != null){
					int id = s.toCharArray()[0]-'0';
					Event ev = new Event(EventType.REMOVEPLAYER, id);
					this._eventStack.add(ev);

					System.out.println(this._eventStack.get(0).getEventType() + " "+id);
				}
			}
		}
	}

	public synchronized Event popEvent() {
		if(_eventStack.size()>0)
			return _eventStack.remove(0);
		else
			return null;
	}
	
	public void refresh() {
		System.out.println("Refreshing...");
		this.getContentPane().revalidate();
		this.getContentPane().requestFocus();

	}
}
	

