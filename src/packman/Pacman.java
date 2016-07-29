package packman;

import java.awt.CardLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Pacman extends JFrame {
	
	private JPanel content = new JPanel();
	private CardLayout cl = new CardLayout();
	private static Pacman pacWin ;
	
    public Pacman() {
        
        initUI();
    }
    
    private void initUI() {
    	        
    	Pacman.pacWin = this;
    	
    	setTitle("Pacman");
        this.setIconImage(new ImageIcon(this.getClass().getResource("/right1.png")).getImage());
        this.setAlwaysOnTop(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(380, 420);
        setLocationRelativeTo(null);
        this.setResizable(false);
        setVisible(true);   
    	
    	content.setLayout(cl);
    	Menu menu = new Menu();
    	content.add(menu, "Menu");
    	this.getContentPane().add(content);
             
    }
    
    public  void start(){
    	
    	Board board = new Board(1);
    	content.add(board, "Board");
    	cl.show(content, "Board");
    } 
    
    public  void select(){
    	
    	Board board = new Board(4);
    	content.add(board, "Board");
    	cl.show(content, "Board");
    } 
    
    public static Pacman getFrame(){
    	return pacWin;
    }
}
