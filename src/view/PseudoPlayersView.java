package view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * <b>Pseudo Players Page</b>
 * 
 * It's on this page that we enter the pseudo of the players
 * 
 * @author Antonin WALTZ
 */

public class PseudoPlayersView extends ViewMenu {
	
	JLabel playerLabel[] = new JLabel[6];
	JTextField playerTextField[] = new JTextField[6];
	JButton okButton, addButton, removeButton;
	
	public PseudoPlayersView(){
		
		//Label
		for(int i = 0; i<6 ; i++){
			JLabel temp = new JLabel("Player "+(i+1)+", enter your name :");
			playerLabel[i] = temp;
			playerLabel[i].setSize(200, 30);
			playerLabel[i].setLocation(frameWidth/2-100,150+i*100);
			
			this.add(playerLabel[i],new Integer(1));
		}
		playerLabel[2].setVisible(false);
		playerLabel[3].setVisible(false);
		playerLabel[4].setVisible(false);
		playerLabel[5].setVisible(false);	
		
		
		//TextField
		for(int i = 0; i<6 ; i++){
			JTextField temp = new JTextField();
			playerTextField[i] = temp;
			playerTextField[i].setSize(200, 30);
			playerTextField[i].setLocation(frameWidth/2-100,200+i*100);
			this.add(playerTextField[i],new Integer(1));
		}
		playerTextField[2].setVisible(false);
		playerTextField[3].setVisible(false);
		playerTextField[4].setVisible(false);
		playerTextField[5].setVisible(false);
		
		//OK Button
		okButton = new JButton("OK");
		okButton.setSize(200,30);
		okButton.setLocation(150, 700);
		this.add(okButton,new Integer(1));
		
		//Add Button
		addButton = new JButton("ADD PLAYER");
		addButton.setSize(200,30);
		addButton.setLocation(frameWidth/2-100, 800);
		this.add(addButton,new Integer(1));
		
		//REMOVE Button
		removeButton = new JButton("REMOVE PLAYER");
		removeButton.setSize(200,30);
		removeButton.setLocation(frameWidth/2-100, 850);
		this.add(removeButton,new Integer(1));
		
	}
	
	
	public JButton getOkButton() {
		return okButton;
	}
	
	public JButton getAddButton() {
		return addButton;
	}
	
	public JButton getRemoveButton() {
		return removeButton;
	}
	
	public JTextField getPlayerTextField(int i) {
		if(i>0 && i<=6){
			return playerTextField[i-1];
		}
		else{
			return playerTextField[0]; /** TODO try catch ? */
		}
	}
	
	

}
