package packman;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class BonusShow extends JPanel{
	
	private JTextArea bonusdescribe[];
	private JLabel imgbonus[];
	private int totalbonus ;

	/*
	 * panel to show every bonus in the game and put on the right a text area with the description of the bonus
	 */
	public BonusShow(){
		this.setBackground(Color.BLACK);
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,5,5);
		BonusCreator bontoshow = new BonusCreator();

		totalbonus = bontoshow.getTotalbonus();	
		imgbonus = new JLabel[totalbonus];		
		bonusdescribe = new JTextArea[totalbonus];
		
		for(int i = 1;i<totalbonus;i++){			
			bontoshow = new BonusCreator(0,0,i);
			
			imgbonus[i] = new  JLabel() ;
			imgbonus[i].setIcon(new ImageIcon(bontoshow.getImg()));
			imgbonus[i].setHorizontalAlignment(this.getWidth()/8);
		    gbc.gridx = 0;
		    gbc.gridy = i-1;
			this.add(imgbonus[i],gbc);
			
			
			bonusdescribe[i] = new JTextArea();
			bonusdescribe[i].setFont(new Font("Arial",Font.CENTER_BASELINE,12));
			bonusdescribe[i].setForeground(Color.GREEN);
			bonusdescribe[i].setBackground(Color.BLACK);
			bonusdescribe[i].setText(bontoshow.getDescribe());
		    gbc.gridx = 1;
		    gbc.gridy = i-1;
		    gbc.anchor = GridBagConstraints.WEST;
			this.add(bonusdescribe[i],gbc);
			
			
		}
		
		

		
	}
}


