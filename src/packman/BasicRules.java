package packman;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class BasicRules extends JPanel{
	

	private JLabel intro = new JLabel("Pacman remixed : ");
	private JTextArea rules = new JTextArea();

	
	public BasicRules(){
		this.setBackground(Color.BLACK);
		this.setLayout(new BorderLayout());
		
		FontPacman.setMyFont(intro, 18, Color.green);
		intro.setHorizontalAlignment(0);
		intro.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		this.add(intro,BorderLayout.NORTH);
		
		rules.setFont(new Font("Arial",Font.CENTER_BASELINE,12));
		rules.setForeground(Color.GREEN);
		rules.setBackground(Color.BLACK);
		rules.setText(
				"  You must eat every pacgum \n" +
				"  or save your PacWoman from ghosts \n"+
				"  in order to go to the next level.\n"+
				"  However, stay alert for ghosts.\n"+
				"  Some are just poor souls wandering \n"+
				"  but the others may attack you, surprise you across walls \n"+
				"  and some are scared to death and will avoid you. \n \n"+
				"  For more informations, go to the bestiary tab ! \n" +
				"  Check the controls !\n" +
				"  Check the differents bonuses ! \n" +
				"  Enjoy !\n" 
				);
				
		this.add(rules, BorderLayout.CENTER);


		
	}

}
