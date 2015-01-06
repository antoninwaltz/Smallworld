package view;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Model;
import controller.Controller;

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
	private Controller _c;


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
	private JButton _newButton, _loadButton, _saveButton, _rulesButton, _exitButton, _addButton, _removeButton ;

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
		
		_saveButton = _MapGameView.getSaveButton();
		_saveButton.addActionListener(this);

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
			return; // Dont go to sleep
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
			if (getContentPane() == _MapGameView)
				setContentPane(_menu);
			_menu.seeMenu();
			refresh();
			return; // Dont go to sleep
		}
		else if (e.getSource().equals(_newButton))
		{
			if(m.getPlayers().size() < 2){
				JOptionPane.showMessageDialog(this, "Not enough players");

			} else {
				Event _newGame = new Event(EventType.NEWGAME);
				this._eventStack.add(_newGame);
			}
		}
		else if (e.getSource().equals(_addButton))
		{
			if(m.getPlayers().size() >= 6) {
				JOptionPane.showMessageDialog(this, "Too much players");
			} else {
				String  name = (String)javax.swing.JOptionPane.showInputDialog(this,
						"Enter your name:",
						"Name",
						JOptionPane.PLAIN_MESSAGE);
				if (name != null && !name.equals("")) {
					Event ev = new Event(EventType.NEWPLAYER, name);
					this._eventStack.add(ev);
				}
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
				}
			}
		} else if (e.getSource() == _saveButton) {
			String  filename = (String)javax.swing.JOptionPane.showInputDialog(this,
					"Nom du fichier:",
					"Fichier",
					JOptionPane.PLAIN_MESSAGE);
			if (filename == null || filename == "")
				filename = "game.save";
			Event ev = new Event(EventType.SAVE, filename);
			this._eventStack.add(ev);
		} else if (e.getSource() == _loadButton) {
			String  filename = (String)javax.swing.JOptionPane.showInputDialog(this,
					"Nom du fichier:",
					"Fichier",
					JOptionPane.PLAIN_MESSAGE);
			if (filename == null || filename == "")
				filename = "game.save";
			Event ev = new Event(EventType.LOAD, filename);
			this._eventStack.add(ev);
		}
		synchronized (_c) {
			_c.notify();
		}
		try {
			wait();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	public synchronized Event popEvent() {
		if(_eventStack.size()>0)
			return _eventStack.remove(0);
		else
			return null;
	}
	
	public void setModel(Model mod) {
		m = mod;
		_menu.setModel(m);
		_MapGameView.setModel(m);
	}

	public void initBoard() {
		this._MapGameView.drawBoard();
		this.setContentPane(_MapGameView);
	}

	public void refresh() {
		System.out.print("Refreshing ");
		if(this.getContentPane() == _menu) {
			System.out.println("menu...");
			_menu.refresh(getWidth(), getHeight());
		}
		else if (this.getContentPane() == _MapGameView) {
			System.out.println("game map...");
			_MapGameView.refresh(this.getWidth(), this.getHeight()-20);
		}
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

	public void setController(Controller c) {
		_c = c;
		_MapGameView.setController(c);
	}
}


