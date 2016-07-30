package packman;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class Controls extends JPanel{
	
	private JTextArea rules = new JTextArea();

	
	public Controls(){
		this.setBackground(Color.BLACK);
		this.setLayout(new BorderLayout());
		
		
		rules.setFont(new Font("Arial",Font.CENTER_BASELINE,12));
		rules.setForeground(Color.GREEN);
		rules.setBackground(Color.BLACK);
		rules.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		rules.setText(
				"  \n Move Pacman : \n" +
				"   Up \t \t \t Arrow up\n"+
				"   Down \t \t \t Arrow down\n"+
				"   Left \t \t \t Arrow left\n"+
				"   Right \t \t \t Arrow right\n"+
				"   Dash \t \t \t Space \n \n"+
				
				
				"Special key :\n"+
				"   Reset the Level \t \t 'R'\n"+
				"   Pause \t \t \t 'P'\n"+
				"   Change the color of the board \t 'C' \n \n"+
				
				"Cheat HotKey : \n"+
				
				"   Change ghosts state\t \t 'T'\n"+
				"   Add 1 Life and 10 Dash \t \t 'Y'\n"+
				"   Suicide \t \t \t 'U'\n"+
				"   Previous level \t \t 'I'\n"+
				"   Next level\t \t \t 'O'\n"
				);
				
		this.add(rules, BorderLayout.CENTER);


		
	}

}