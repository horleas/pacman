package packman;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SelectLevel extends JPanel{
	
	private PacButton back = new PacButton("Back to menu", Color.green);
	private PacButton load = new PacButton ("Load Level" , Color.YELLOW);
	private JLabel intro = new JLabel("Select Your Level !");
	private int level = 1;
	private String nameLevel ;
	private PacButton previous = new PacButton ( "<=" ,Color.red , 60 , 60 );
	private PacButton next = new PacButton ( "=>" ,Color.blue ,60 , 60 );
	private JLabel name ;
	private JLabel img ;
	
	private JPanel content = new JPanel();

	
	public SelectLevel(){
		this.setBackground(Color.BLACK);
		this.setLayout(new BorderLayout());
		FontPacman.setMyFont(intro, 18, Color.green);
		intro.setHorizontalAlignment(getWidth()/2);
		this.add(intro,BorderLayout.NORTH);
		
		
		content.setBackground(Color.BLACK);
		content.setLayout(new BorderLayout());		
		infomap(level);		
		

		content.add(load, BorderLayout.SOUTH);
		
		this.add(content, BorderLayout.CENTER);
		
		this.add(previous, BorderLayout.WEST);
		previous.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				content.removeAll();
				level --;
				infomap(level);		
				
				load.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {

				    	//System.out.println("the level is : "+ level);
						Pacman.getFrame().loadlevel(level);			
						
					}
					
				});
				content.add(load, BorderLayout.SOUTH);
				content.repaint();
				revalidate();
				
			}
			
		});
		
		this.add(next, BorderLayout.EAST);
		next.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				content.removeAll();
				level ++;
				infomap(level);	
				load.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						Pacman.getFrame().loadlevel(level);									
					}
					
				});
				content.add(load, BorderLayout.SOUTH);
				content.repaint();
				revalidate();
				
			}
			
		});
		
		this.add(back, BorderLayout.SOUTH);
		back.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {


				Pacman.getFrame().menu();			
				
			}
			
		});
		
	}
	
	public void infomap( int level){
		Maze infoname = new Maze(level);
		

		nameLevel = infoname.getName();
		name = new JLabel(nameLevel);
		FontPacman.setMyFont(name, 14, Color.green);
		//name.setHorizontalAlignment(getWidth()/2);
		content.add(name, BorderLayout.NORTH);
		
		if(level >= 12 || level <= 0){
			if(level <= 0){previous.setEnabled(false);}
			if(level >= 12){next.setEnabled(false);}
			URL urlimage = this.getClass().getResource("/test.png");
			img = new JLabel(new ImageIcon(urlimage));
		}
		else{
			
		previous.setEnabled(true);
		next.setEnabled(true);
		URL urlimage = this.getClass().getResource("/level"+level+".png");
		img = new JLabel(new ImageIcon(urlimage));
		}
		content.add(img,BorderLayout.CENTER);
		System.out.println("the level is : "+ level);
		

		
		
	}
	

}
