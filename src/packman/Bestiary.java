package packman;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class Bestiary extends JPanel{
	/*
	 * Bestiary present the differents opponents Pacman will find on his journey
	 * the central panel is the image of the ghost
	 * the north panel contains his name
	 * the south panel contains some informations about the ghost
	 * And on the West and East , previous and next PacButton let the player to pass 
	 * to another ghost and refresh the panel with the correct information 
	 */
	
private Color background = Color.BLACK;
private PacButton previous = new PacButton ( "<=" ,Color.red , 60 , 60 );
private PacButton next = new PacButton ( "=>" ,Color.cyan ,60 , 60 );
private JLabel nameghost = new  JLabel() ;
private JLabel imgghost = new  JLabel() ;
private JTextArea describeghost = new  JTextArea() ;
private URL urlimage;
private int numghost = 1 ;
private int maxghost = 9 ;
private JPanel contentimg = new JPanel();
		
	public Bestiary(){
		this.setBackground(background);
		this.setLayout(new BorderLayout());
		
		/*
		 * load the previous ghost 
		 */
		this.add(previous, BorderLayout.WEST);
		previous.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				contentimg.removeAll();
				numghost --;
				getGhostInfo(numghost);
				contentimg.repaint();
				contentimg.revalidate();
				
			}
			
		});
		
		/*
		 * load the next ghost 
		 */
		this.add(next, BorderLayout.EAST);
		next.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				contentimg.removeAll();			
				numghost ++;
				getGhostInfo(numghost);
				contentimg.repaint();
				contentimg.revalidate();
				
			}
			
		});
		
		FontPacman.setMyFont(nameghost, 16, Color.green);
		nameghost.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		nameghost.setHorizontalAlignment(getWidth()/2);
		this.add(nameghost, BorderLayout.NORTH);
	
		getGhostInfo(numghost);
		contentimg.setBackground(background);
		contentimg.add(imgghost);
		this.add(contentimg, BorderLayout.CENTER);
		
		
		describeghost.setFont(new Font("Arial",Font.CENTER_BASELINE,12));
		describeghost.setForeground(Color.GREEN);
		describeghost.setBackground(Color.BLACK);
		describeghost.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.add(describeghost, BorderLayout.SOUTH);


	}
	
	/*
	 * get the info of the ghost by the number in parameter
	 * and Enable or disable the next and previous button depending on the current ghost looked
	 */
	public void getGhostInfo(int numghost){
		
		if(numghost<=1){
			previous.setEnabled(false);
		}else{
			previous.setEnabled(true);
		}
		if(numghost>=9){
			next.setEnabled(false);
		}else{
			next.setEnabled(true);
		}
		
		switch(numghost){
		
		case 1 :
			nameghost.setText("Floyd");
			urlimage = this.getClass().getResource("/floyd.png");
			imgghost = new JLabel(new ImageIcon(urlimage));
			describeghost.setText("A pink ghost wandering without goals for eternity.");
			break;
			
		case 2 :
			nameghost.setText("Submarine");
			urlimage = this.getClass().getResource("/submarine.png");
			imgghost = new JLabel(new ImageIcon(urlimage));
			describeghost.setText("A yellow ghost ,who drown in the sea, \n now roaming to the surface.");
			break;
			
		case 3 :
			nameghost.setText("GreySkies");
			urlimage = this.getClass().getResource("/GreySkies.png");
			imgghost = new JLabel(new ImageIcon(urlimage));
			describeghost.setText("A turquoise ghost ,who died in a rainy day, \n looking for an umbrella.");
			break;
			
		case 4 :
			nameghost.setText("Hotchilipepper");
			urlimage = this.getClass().getResource("/hotchilipepper.png");
			imgghost = new JLabel(new ImageIcon(urlimage));
			describeghost.setText("A red ghost caused by eating an incredibly spicy pepper.");
			break;
			
		case 5 :
			nameghost.setText("Ad the Blocker");
			urlimage = this.getClass().getResource("/blocker.png");
			imgghost = new JLabel(new ImageIcon(urlimage));
			describeghost.setText("A stuborn ghost trying to be every time on your way.");
			break;
			
			
		case 6 :
			nameghost.setText("Hotoke, the Chaser");
			urlimage = this.getClass().getResource("/Chaser.png");
			imgghost = new JLabel(new ImageIcon(urlimage));
			describeghost.setText("Someone who try to kill you.\n "+
					"No one know why ! "+
					"Some Says his name is Yamada Taro");
			break;
			
			
		case 7 :
			nameghost.setText("Escaper");
			urlimage = this.getClass().getResource("/escape.png");
			imgghost = new JLabel(new ImageIcon(urlimage));
			describeghost.setText("The ghost with the smallest memory who will try to approach \n"+
			"you before runing away when he remembe ... \n"+
			"Every time he see you, he is scared to death.");
			break;
			
			
		case 8 :
			nameghost.setText("Phasers");
			urlimage = this.getClass().getResource("/phaseghost.png");
			imgghost = new JLabel(new ImageIcon(urlimage));
			describeghost.setText("Ghosts among ghosts.\n"+
					"Environment have not effect on them.\n"+
					"Theirs feeling aren't their own.\n"+
					"They take others comportements");
			break;
		
		case 9 :
			nameghost.setText("Unknown Living Armor");
			urlimage = this.getClass().getResource("/livingarmor.png");
			imgghost = new JLabel(new ImageIcon(urlimage));
			describeghost.setText("An old dead knight in his armor making round trip. \n"+
			"The helmet is too bruised to see anything");
			break;
		}
		nameghost.setText(nameghost.getText()+" ("+numghost+"/"+maxghost+")");
		contentimg.add(imgghost);
	}

}



