package view;


import model.Model;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowListener;
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
public class View extends JFrame implements ActionListener, ComponentListener {
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
	private ViewMenu _menu;
	private MapGameView _MapGameView;

	/**
	 * Attributes for the buttons of the menus
	 *
	 * @see View
	 */
	private JButton _backButton;
	private JButton _newButton, _loadButton, _rulesButton, _exitButton, _addButton, _removeButton ;

	private JButton _quitGame;



	/**
	 * <b>Constructor of the view</b>
	 *
	 * @param m The model used by the view
	 */
	public View(Model m) {
		this.m = m;
		this._eventStack = new ArrayList<>();

		//Paramètre de base
		this.setTitle("Smallworld UTBM");
		//this.setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(600, 480));
		this.setResizable(true);
		this.setVisible(true);
		
		//Views
		_menu = new ViewMenu(getWidth(), getHeight(), m);
		_menu.addComponentListener(this);
		_MapGameView = new MapGameView(getWidth(), getHeight(), m, _eventStack);
		_MapGameView.addComponentListener(this);

		//Récupération des boutons de retour
		_backButton = _menu.getBackButton();
		_backButton.addActionListener(this);

		//Récupération du bouton de nouvelle partie
		_newButton = _menu.getNewButton();
		_newButton.addActionListener(this);

		//Récupération du bouton de chargement d'une partie
		_loadButton = _menu.getLoadButton();
		_loadButton.addActionListener(this);

		//Récupération du bouton des règles
		_rulesButton = _menu.getRulesButton();
		_rulesButton.addActionListener(this);

		//Récupération du bouton exit
		_exitButton = _menu.getExitButton();
		_exitButton.addActionListener(this);

		//Récupération du bouton exit
		_exitButton = _menu.getExitButton();
		_exitButton.addActionListener(this);

		_addButton = _menu.getAddButton();
		_addButton.addActionListener(this);

		_removeButton = _menu.getDelButton();
		_removeButton.addActionListener(this);

		_quitGame = _MapGameView.getQuitButton();
		_quitGame.addActionListener(this);
		
		//Page de départ
		this.setContentPane(_menu);
		_menu.refresh(getWidth(), getHeight());
		_menu.seeMenu();

		this.pack();

	}

	@Override
	public synchronized void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(_rulesButton))
		{
			_menu.seeRules();
			refresh();
		}
		else if (e.getSource().equals(_exitButton))
		{
			int option = javax.swing.JOptionPane.showConfirmDialog(null, "Are you sure ?", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(option == JOptionPane.OK_OPTION) {
				System.exit(0);
			}
		}
		else if (e.getSource() == _backButton || e.getSource() == _quitGame)
		{
			System.out.println("TROLL");
			if (getContentPane() == _MapGameView)
				setContentPane(_menu);
			_menu.seeMenu();
			refresh();
		}
		else if (e.getSource().equals(_newButton))
		{
			if(m.getPlayers().size() < 2){
				JOptionPane.showMessageDialog(this, "Not enough players");

			} else {
				Event _newGame = new Event(EventType.NEWGAME);
				this._eventStack.add(_newGame);

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

	public void initBoard() {
		this._MapGameView.drawBoard();
		this.setContentPane(_MapGameView);
	}

	public void refresh() {
		System.out.println("Refreshing...");
		if(this.getContentPane() == _menu)
			_menu.refresh(getWidth(), getHeight());
		else if (this.getContentPane() == _MapGameView)
			_MapGameView.refresh(this.getWidth(), this.getHeight()-20);
	}

	@Override
	public void componentHidden(ComponentEvent e) {		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		refresh();
	}

	@Override
	public void componentResized(ComponentEvent e) {
		refresh();		
	}

	@Override
	public void componentShown(ComponentEvent e) {		
		refresh();		
	}
}


