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
	
	JLabel player1Label;
	JLabel player2Label;
	JLabel player3Label;
	JLabel player4Label;
	JLabel player5Label;
	
	JTextField player1nameField;
	JTextField player2nameField;
	JTextField player3nameField;
	JTextField player4nameField;
	JTextField player5nameField;
	
	JButton okButton;
	
	public PseudoPlayersView(){
		
		//Label
		player1Label = new JLabel("Player 1, enter your name :");
		player1Label.setSize(200, 30);
		player1Label.setLocation(frameWidth/2-100,150);
		
		player2Label = new JLabel("Player 2, enter your name :");
		player2Label.setSize(200, 30);
		player2Label.setLocation(frameWidth/2-100,250);
		
		player3Label = new JLabel("Player 3, enter your name :");
		player3Label.setSize(200, 30);
		player3Label.setLocation(frameWidth/2-100,350);
		
		player4Label = new JLabel("Player 4, enter your name :");
		player4Label.setSize(200, 30);
		player4Label.setLocation(frameWidth/2-100,450);
		
		player5Label = new JLabel("Player 5, enter your name :");
		player5Label.setSize(200, 30);
		player5Label.setLocation(frameWidth/2-100,550);
		
		
		//TextField
		player1nameField = new JTextField();
		player1nameField.setSize(200,30);
		player1nameField.setLocation(frameWidth/2-100,200);
		
		player2nameField = new JTextField();
		player2nameField.setSize(200,30);
		player2nameField.setLocation(frameWidth/2-100,300);
		
		player3nameField = new JTextField();
		player3nameField.setSize(200,30);
		player3nameField.setLocation(frameWidth/2-100,400);
		
		player4nameField = new JTextField();
		player4nameField.setSize(200,30);
		player4nameField.setLocation(frameWidth/2-100,500);
		
		player5nameField = new JTextField();
		player5nameField.setSize(200,30);
		player5nameField.setLocation(frameWidth/2-100,600);
		
		//OK Button
		okButton = new JButton("OK");
		okButton.setSize(200,30);
		okButton.setLocation(frameWidth/2-100, 700);
		
		//Add
		this.add(player1Label,new Integer(1));
		this.add(player2Label,new Integer(1));
		this.add(player3Label,new Integer(1));
		this.add(player4Label,new Integer(1));
		this.add(player5Label,new Integer(1));
		
		this.add(player1nameField,new Integer(1));
		this.add(player2nameField,new Integer(1));
		this.add(player3nameField,new Integer(1));
		this.add(player4nameField,new Integer(1));
		this.add(player5nameField,new Integer(1));
		
		this.add(okButton,new Integer(1));
	}
	
	public JTextField getPlayer1NameField() {
		return player1nameField;
	}
	
	public JTextField getPlayer2NameField() {
		return player2nameField;
	}
	
	public JTextField getPlayer3NameField() {
		return player3nameField;
	}
	
	public JTextField getPlayer4NameField() {
		return player4nameField;
	}
	
	public JTextField getPlayer5NameField() {
		return player5nameField;
	}
	
	public JButton getOkButton() {
		return okButton;
	}

}
