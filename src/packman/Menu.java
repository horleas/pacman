package packman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Menu extends JPanel{
	
	private JLabel Pacmantitle = new JLabel("PACMAN");
	private JLabel Pacmanundertitle = new JLabel("Remixed by Horleas");
	
	private PacButton newGame = new PacButton("New Game");
	private PacButton selectLevel = new PacButton("Select the Level");
	private PacButton seletTuto = new PacButton("Select Tutorial Map");
	private PacButton exit = new PacButton("EXIT");
	private PacButton rules = new PacButton("Rules");
	private PacButton score = new PacButton("HighScore");
	private PacButton personaliser = new PacButton("Personalise your Board");
	private Dimension dimWin = new Dimension(380,420);
	
	public Menu (){
		
		this.setPreferredSize(dimWin);
		this.setSize(380, 420);
		this.setBackground(Color.BLACK);
		

		FontPacman.setMyFont(Pacmantitle, 24, Color.YELLOW);
		this.add(Pacmantitle);
		
		FontPacman.setMyFont(Pacmanundertitle, 10, Color.orange);
		this.add(Pacmanundertitle);
		
		FontPacman.setMyFont(newGame, 12, Color.BLUE);
		newGame.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
		
				Pacman.getFrame().start();				
			}
		
		});
		this.add(newGame);
		
		
		
		FontPacman.setMyFont(seletTuto, 12, Color.green);
		this.add(seletTuto);
		FontPacman.setMyFont(selectLevel, 12, Color.green);
		
		selectLevel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
		
				Pacman.getFrame().select();				
			}
		
		});
		
		this.add(selectLevel);
		FontPacman.setMyFont(score, 12, Color.green);
		this.add(score);
		FontPacman.setMyFont(rules, 12, Color.green);
		this.add(rules);
		FontPacman.setMyFont(personaliser, 12, Color.green);
		this.add(personaliser);
		
		FontPacman.setMyFont(exit, 12, Color.red);
		exit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
			
		});
		this.add(exit);
		
	}
	

}
