package view;

import view.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
	//private String rulesImagePath = "images/rules.png";
	private JTextArea rulesTextArea;
	//back button
	private JButton backButton;
	private JButton newButton,loadButton,rulesButton,exitButton,addPlayer,delPlayer;
	private ArrayList<JLabel> playerList;
	private Model m;
	
	private JScrollPane scrollPanePlain; 
	
	
	
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
		
		rulesTextArea = new JTextArea(
				"Dans Small World, chaque joueur dirige la destinée de quelques peuples dans un monde médiéval fantastique. Ce monde imaginaire étant très petit, il n'y a pas de place pour tous les peuples, chacun doit donc lutter pour sa survie." +
				"\n\n" +
				"Le monde illustré sur le plateau de jeu est découpé en territoires: montagnes, marais, forêts, etc. Chaque peuple dirigé par un joueur est en réalité l'association d'un peuple de base et d'un pouvoir spécial. Les cartes se combinant aléatoirement, vous pourrez avoir d'une partie à l'autre des configurations très différentes: sorciers des forêts, squelettes diplomates, elfes et leur dragon, etc." +
				"\n\n" +
				"Pour chacun de ces peuples, vous obtenez un nombre variable de pions peuple (de l'ordre d'une dizaine), qui vont vous servir à envahir des cases terrain. Au début de votre tour, vous prenez dans la main ces pions peuple, en veillant toutefois à laisser (sur le plateau) un pion par case que vous possédez éventuellement déjà. Ensuite, en partant d'une zone du bord du plateau, vous pouvez conquérir un nouveau territoire à condition d'y placer suffisamment de pions peuple. Un territoire inoccupé coûte 2 pions peuple, auxquels vous devez ajouter un pion par éventuelle armée adverse en place, + 1 pion si vous visez une case montagne, et d'autres pions si la case est défendue par une construction ou un pouvoir spécial. Des combats sans hasard: le plus nombreux gagne !" +
				"\n\n" +
				"Bref, il n'y a pas de hasard dans le résultat des combats: si vous investissez suffisamment de pions, vous gagnez à coup sûr, sinon vous ne pouvez pas conquérir la case visée. Une exception toutefois: en fin de tour, lorsqu'il ne vous reste plus assez d'armées pour attaquer votre dernier terrain, vous lancez le dé de renforts qui peut vous apporter le soutien nécessaire." +
				"\n\n" +
				"A la fin du tour, vous marquez autant de points de victoire que de terrains que vous possédez." +
				"\n\n" +
				"L'une des astuces des règles réside dans la manière dont les joueurs choisissent un peuple. Au départ, six combinaisons peuple et pouvoir sont placées face visible l'une au-dessus de l'autre. A votre tour, vous pouvez choisir la première combinaison gratuitement. Mais si elle ne vous plaît pas et si vous voulez celle d'en-dessous, il vous en coûtera 1 point de victoire (la monnaie du jeu) à placer sur la combinaison non-souhaitée. Et ainsi de suite, si vous voulez le peuple qui est encore en-dessous. Ce qui fait qu'au bout d'un moment, un peuple dont personne n'a voulu disposera de plusieurs points de victoire qui reviendront au joueur qui le choisira. Déclarez votre déclin, pour mieux repartir avec un nouveau peuple" +
				"\n\n" +
				"Lorsque vous estimez que votre peuple s'est suffisamment étendu et que vous n'avez plus assez de pions pour conquérir de nouveaux territoires, vous pouvez déclarer votre peuple en déclin et en choisir un nouveau. Le peuple en déclin continuera de vous rapporter quelques points de victoire mais ne pourra plus s'étendre et devient plus faible face aux adversaires." +
				"\n\n" +
				"Enfin, chaque peuple de base et chaque pouvoir vous confère une capacité spéciale qui peut favoriser l'attaque, la défense, les mouvements sur le terrain, l'apport de points de victoire, etc. Par exemple, le pouvoir intitulé des forêts vous apporte un bonus d'un point de victoire pour chaque terrain de type forêt possédé en fin de tour.");
		rulesTextArea.setLineWrap(true);
		rulesTextArea.setWrapStyleWord(true);
		rulesTextArea.setEditable(false);
		;
		
		
		 
		scrollPanePlain = new JScrollPane(rulesTextArea); 
		//scrollPanePlain.setBounds(100, 30, 250, 100);  
		scrollPanePlain.setVisible(true);
		scrollPanePlain.setForeground(new Color(255,255,255,150));
		scrollPanePlain.setBackground(new Color(0,0,0,50));
		
		/*rulesLabel = new JLabel("Rules :");
		rulesLabel.setSize(200, 30);
		rulesLabel.setLocation(frameWidth/2-200,150);*/
		
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
		scrollPanePlain.setSize(frameWidth-100, frameHeight-150);
	
		
		
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
		//this.add(rulesImageLabel, 1);
		
		scrollPanePlain.setLocation(50, 50);
	
		//this.add(rulesTextArea);
		this.add(scrollPanePlain);  
		this.add(backButton); 
		this.refresh(frameWidth, frameHeight);
	}

	public void setModel(Model m2) {
		m = m2;
	}
}
