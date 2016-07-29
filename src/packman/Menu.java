package packman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Menu extends JPanel{
	
	private JLabel pacmanTitle = new JLabel("PACMAN");
	private JLabel pacmanUnderTitle = new JLabel("Remixed by Horleas");
	
	private PacButton newGame = new PacButton("New Game" , Color.cyan);
	private PacButton selectLevel = new PacButton("Select Level",Color.green);
	private PacButton selectTuto = new PacButton("Select Tutorial",Color.green);
	private PacButton exit = new PacButton("EXIT",Color.red);
	private PacButton rules = new PacButton("Rules",Color.green);
	private PacButton score = new PacButton("HighScore",Color.green);
	private PacButton personaliser = new PacButton("Options",Color.green);
	private Dimension dimWin = new Dimension(380,420);
	
	public Menu (){
		
		this.setPreferredSize(dimWin);
		this.setSize(380, 420);
		this.setBackground(Color.BLACK);
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(4,4,4,4);
		
		
		gbc.gridx=0;
		gbc.gridy=0;
		FontPacman.setMyFont(pacmanTitle, 24, Color.YELLOW);
		this.add(pacmanTitle,gbc);		

		
		gbc.gridx=0;
		gbc.gridy=1;
		gbc.anchor = GridBagConstraints.EAST;
		FontPacman.setMyFont(pacmanUnderTitle, 10, Color.orange);
		this.add(pacmanUnderTitle,gbc);


		gbc.gridx=0;
		gbc.gridy=2;
		URL urlimage = this.getClass().getResource("/menuimg.png");
		JLabel img = new JLabel(new ImageIcon(urlimage));
		this.add(img, gbc);
		
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx=0;
		gbc.gridy=3;
		newGame.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
		
				Pacman.getFrame().start();				
			}
		
		});
		this.add(newGame,gbc);
		
		
		gbc.gridx=0;
		gbc.gridy=4;
		selectTuto.setEnabled(false);
		this.add(selectTuto,gbc);
		
		gbc.gridx=0;
		gbc.gridy=5;	
		selectLevel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
		
				Pacman.getFrame().select();				
			}
		
		});
		
		this.add(selectLevel,gbc);
		
		gbc.gridx=0;
		gbc.gridy=6;
		this.add(score,gbc);
		
		gbc.gridx=0;
		gbc.gridy=7;
		this.add(rules,gbc);
		
		gbc.gridx=0;
		gbc.gridy=8;
		this.add(personaliser,gbc);
		
		gbc.gridx=0;
		gbc.gridy=9;
		exit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
			
		});
		this.add(exit,gbc);
		
		
	}
	

}
