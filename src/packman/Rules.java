package packman;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class Rules extends JPanel{
	
	private PacButton back = new PacButton("Back to menu", Color.green);
	private JLabel intro = new JLabel("Rules");
	private JTabbedPane onglet ;
	private BasicRules br = new BasicRules();
	private Controls ct = new Controls();
	private Bestiary bestiary = new Bestiary();
	private BonusShow bonshow = new BonusShow();

	
	private JPanel content = new JPanel();

	
	public Rules(){
		this.setBackground(Color.BLACK);
		this.setLayout(new BorderLayout());
		FontPacman.setMyFont(intro, 18, Color.green);
		intro.setHorizontalAlignment(getWidth()/2);
		intro.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.add(intro,BorderLayout.NORTH);
		
		
		content.setBackground(Color.BLACK);
		content.setLayout(new BorderLayout());		
	    
		UIManager.put("TabbedPane.contentOpaque", false);
	    onglet = new JTabbedPane();
		FontPacman.setMyFont(onglet, 10, Color.green);
	    onglet.setBackground(Color.BLACK);
	    onglet.setOpaque(false);
	    onglet.add("Basic Rules", br);
	    onglet.add("Controls", ct);
	    onglet.add("Bestiary", bestiary);
	    onglet.add("Bonus", bonshow);
	    

	    
	    content.add(onglet);
		
		this.add(content, BorderLayout.CENTER);
		
		
		this.add(back, BorderLayout.SOUTH);
		back.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {


				Pacman.getFrame().menu();			
				
			}
			
		});
		
	}

}
