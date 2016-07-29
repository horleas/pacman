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
    
    public  void loadlevel(int level){
    	//System.out.println("the level is : "+ level);
    	Board board = new Board(level);
    	content.add(board, "loader");
    	cl.show(content, "loader");
    } 
    
    public  void select(){
    	
    	SelectLevel selectLevel = new SelectLevel();
    	content.add(selectLevel, "SelectLevel");
    	cl.show(content, "SelectLevel");
    } 
    
    public  void menu(){
    	
    	Menu menu = new Menu();
    	content.add(menu, "Menu");
    	cl.show(content, "Menu");
    } 
    
    public static Pacman getFrame(){
    	return pacWin;
    }
}
