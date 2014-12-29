package view;

import view.View;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;


/**
 * <b>The ViewMenu class</b>
 * 
 * It defines the menus page
 * 
 * @author WALTZ Antonin
 */

public abstract class ViewMenu extends JLayeredPane {
	
	final int frameWidth = View.getFrameSize().width;
	final int frameHeight = View.getFrameSize().height;
	
	//background of the menu pages
	/** TODO Vérifier les images */
	private String titleImagePath = "images/title.png";
	private String backgroundImagePath = "images/background.png";
	private Image image,title;
	private JPanel backPanel;
	
	//back button
	protected JButton backButton;
	
	public ViewMenu(){
		
		
		ImageIcon _iconimage = new ImageIcon(backgroundImagePath);
		image = _iconimage.getImage();
		
		ImageIcon _titleimage = new ImageIcon(titleImagePath);
		title = _titleimage.getImage();
		
		backPanel = new JPanel(){
			
			public void paint(Graphics g) {
				g.drawImage(image, 0, 0,frameWidth, frameHeight, null);
				g.drawImage(title,frameWidth/2-295, 20, null);
			}
			
		};
		
		backPanel.setSize(new Dimension(frameWidth, frameHeight));
		setFocusable(true);
		setDoubleBuffered(true);

		backButton = new JButton("RETURN HOME");
		backButton.setSize(150,30);
		backButton.setLocation(700, 500);
		
		this.add(backPanel,new Integer(0)); //le backPanel a un index de 0 pour etre en arriere plan de tous les autres objets qui auront un index de 1
		this.add(backButton,new Integer(1));
		
		this.setVisible(true);
		
	}

	public JButton getBackButton(){
		
		return backButton;
		
	}
				
}
