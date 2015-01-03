package view;

import view.View;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.Player;

/**
 * <b>The RulesView class</b>
 * 
 * It defines the rules page
 * 
 * @author WALTZ Antonin
 */

public class RulesView extends ViewMenu {
	
	final int frameWidth = View.getFrameSize().width;
	final int frameHeight = View.getFrameSize().height;
	
	private JLabel rulesLabel;
	private JLabel rulesImageLabel;
	/** TODO Vérifier l'image */
	private String rulesImagePath = "images/rules.png";
	private Image image;
	
	public RulesView(){
		
		ImageIcon imageicon = new ImageIcon(rulesImagePath);
		image = imageicon.getImage();
		rulesLabel = new JLabel("Rules :");
		rulesLabel.setSize(200, 30);
		rulesLabel.setLocation(frameWidth/2-200,150);
		
		
		//JLabel repeint avec l'image desiree
		rulesImageLabel= new JLabel() {
				public void paint(Graphics g) {
						/** TODO Vérifier les coordonnées */
						g.drawImage(image,frameWidth/2 - 345/2,frameHeight/2 - 245/2, 345,245, null);
				}
		};
		
		rulesImageLabel.setSize(new Dimension(frameWidth, frameHeight));
		setFocusable(true);
		setDoubleBuffered(true);
		
		//ajout des attributs avec l'index 1 pour etre en premier plan
		this.add(rulesLabel,new Integer(1));
		this.add(rulesImageLabel, new Integer(1));
		super.refresh(frameWidth, frameHeight);
	}
	
	public void refresh(int w, int h) {
		super.refresh(w, h);
	}

}
