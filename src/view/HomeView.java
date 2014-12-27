package view;

import javax.swing.JButton;

import model.Model;

/**
 * <b>Home Page</b>
 * 
 * It is the first page of the game where we can choose to start a new game ect.
 * 
 * @author Antonin WALTZ
 */

public class HomeView extends ViewMenu {
	
	private JButton newButton,loadButton,rulesButton,exitButton;
	
	public HomeView() {
		
		newButton = new JButton("NEW GAME");
		newButton.setSize(200,30);
		newButton.setLocation(frameWidth/2-100, 150);
		
		loadButton = new JButton("LOAD GAME");
		loadButton.setSize(200,30);
		loadButton.setLocation(frameWidth/2-100, 225);
		
		rulesButton = new JButton("RULES");
		rulesButton.setSize(200,30);
		rulesButton.setLocation(frameWidth/2-100, 300);
		
		exitButton = new JButton("EXIT GAME");
		exitButton.setSize(200,30);
		exitButton.setLocation(frameWidth/2-100, 375);
		
		backButton.setVisible(false); //pas de bouton de retour sur la page principale
		
		//ajout des attributs avec l'index 1 pour etre en premier plan
		this.add(newButton,new Integer(1));
		this.add(loadButton,new Integer(1));
		this.add(rulesButton,new Integer(1));
		this.add(exitButton,new Integer(1));

	}
	
	public JButton getNewButton() {
		return newButton;
	}
	
	public JButton getLoadButton() {
		return loadButton;
	}
	
	public JButton getRulesButton(){
		return rulesButton;
	}
	
	public JButton getExitButton(){
		return exitButton;
	}

}
