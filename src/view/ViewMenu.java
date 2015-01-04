package view;

import view.View;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import model.Model;
import model.Player;


/**
 * <b>The ViewMenu class</b>
 * 
 * It defines the menus page
 * 
 * @author WALTZ Antonin
 */

public class ViewMenu extends JLayeredPane {
	private static final long serialVersionUID = 1L;
	int frameWidth;
	int frameHeight;
	
	//background of the menu pages
	/** TODO Vérifier les images */
	private String titleImagePath = "images/title.png";
	private String backgroundImagePath = "images/background.png";

	private JLabel rulesLabel;
	private JLabel rulesImageLabel;
	/** TODO Vérifier l'image */
	private String rulesImagePath = "images/rules.png";
	//back button
	private JButton backButton;
	private JButton newButton,loadButton,rulesButton,exitButton,addPlayer,delPlayer;
	private ArrayList<JLabel> playerList;
	private Model m;
	
	public ViewMenu(int w, int h, Model mod){
		frameWidth = w;
		frameHeight = h;
		m = mod;
		playerList = new ArrayList<>();
		
		newButton = new JButton("NEW GAME");
		newButton.setSize(200,30);
		
		loadButton = new JButton("LOAD GAME");
		loadButton.setSize(200,30);
		
		rulesButton = new JButton("RULES");
		rulesButton.setSize(200,30);
		
		exitButton = new JButton("EXIT GAME");
		exitButton.setSize(200,30);
		
		addPlayer = new JButton("Add new player");
		addPlayer.setSize(200,30);
		
		delPlayer = new JButton("Remove player");
		delPlayer.setSize(200,30);

		backButton = new JButton("RETURN HOME");
		backButton.setSize(150,30);
		
		rulesLabel = new JLabel("Rules :");
		rulesLabel.setSize(200, 30);
		rulesLabel.setLocation(frameWidth/2-200,150);
		
		setFocusable(true);
		setDoubleBuffered(true);

		this.add(backButton,new Integer(1));
		this.setSize(frameWidth, frameHeight);
		
		this.setVisible(true);
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(new ImageIcon(backgroundImagePath).getImage(), 0, 0,frameWidth, frameHeight, null);
		g.drawImage(new ImageIcon(titleImagePath).getImage(),frameWidth/2-295, 20, null);
	}
	
	public JButton getBackButton(){
		return backButton;
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
	
	public JButton getAddButton() {
		return addPlayer;
	}
	
	public JButton getDelButton() {
		return delPlayer;
	}
	
	public void refresh(int w, int h) {
		frameWidth = w;
		frameHeight = h;
		newButton.setLocation(frameWidth/2-100, 150);
		loadButton.setLocation(frameWidth/2-100, 225);
		rulesButton.setLocation(frameWidth/2-100, 300);
		exitButton.setLocation(frameWidth/2-100, 375);
		addPlayer.setLocation(frameWidth-250, 10);
		delPlayer.setLocation(frameWidth-250, 50);
		backButton.setLocation(frameWidth-200, frameHeight-50);
		if(newButton.isShowing())
			refreshPlayer();
		this.revalidate();
		this.repaint();
	}
	
	private void refreshPlayer() {
		for(int i=0;i<playerList.size();i++){
			this.remove(playerList.get(i));
		}
		playerList.clear();
		for(int i=0;i<m.getPlayers().size();i++) {
			playerList.add(new JLabel(m.getPlayers().get(i).getName()));
			playerList.get(i).setSize(200, 300);
			playerList.get(i).setLocation((int)(0.8*frameWidth), 10+20*i);
			this.add(playerList.get(i), new Integer(1));
		}
	}
	
	public void seeMenu() {
		this.removeAll();
		this.add(newButton);
		this.add(loadButton);
		this.add(rulesButton);
		this.add(exitButton);
		this.add(addPlayer);
		this.add(delPlayer);
		this.refresh(frameWidth, frameHeight);
	}

	public void seeRules() {
		this.removeAll();
		this.add(rulesLabel);
		//this.add(rulesImageLabel, 1);
		this.add(backButton); 
		this.refresh(frameWidth, frameHeight);
	}
}
