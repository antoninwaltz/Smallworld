package view;

import javax.swing.JButton;
import javax.swing.JLabel; 

import model.Model;

/**
 * <b>The number of players page</b>
 * 
 * It's on this page that we choose the number of players
 * 
 * @author Antonin WALTZ
 */

public class NbPlayersView extends ViewMenu {
	
	JLabel playernbLabel;
	private JButton twoButton, threeButton, fourButton, fiveButton;
	
	public NbPlayersView(){
	
		playernbLabel = new JLabel("Select the number of players :");
		playernbLabel.setSize(300, 30);
		playernbLabel.setLocation(frameWidth/2-100,150);
		
		twoButton = new JButton("2");
		twoButton.setSize(200,30);
		twoButton.setLocation(frameWidth/2-100, 225);
		
		threeButton = new JButton("3");
		threeButton.setSize(200,30);
		threeButton.setLocation(frameWidth/2-100, 300);
		
		fourButton = new JButton("4");
		fourButton.setSize(200,30);
		fourButton.setLocation(frameWidth/2-100, 375);
		
		fiveButton = new JButton("5");
		fiveButton.setSize(200,30);
		fiveButton.setLocation(frameWidth/2-100, 450);
		
		this.add(playernbLabel,new Integer(1));
		this.add(twoButton,new Integer(1));
		this.add(threeButton,new Integer(1));
		this.add(fourButton,new Integer(1));
		this.add(fiveButton,new Integer(1));

	}
	
	public JButton getTwoButton() {
		return twoButton;
	}
	
	public JButton getThreeButton() {
		return threeButton;
	}
	
	public JButton getFourButton(){
		return fourButton;
	}
	
	public JButton getFiveButton(){
		return fiveButton;
	}
}
