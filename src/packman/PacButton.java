package packman;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class PacButton extends JButton {

	private String name ;
	private Color background = Color.BLACK;
	private Color writing = Color.green;
	
	public PacButton(String str){
		super(str);
		this.name = str;
		
		this.setBackground(background);
		this.setForeground(writing);
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.green, 2),BorderFactory.createLineBorder(background,5)));
		this.setFocusable(false);
	}
	
	
	
}
