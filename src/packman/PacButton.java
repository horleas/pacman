package packman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class PacButton extends JButton implements MouseListener{

	private Color background = Color.BLACK;
	private Color writing ;
	
	public PacButton(String str, Color color){
		super(str);
		this.setBackground(background);
		this.setForeground(color);
		writing = color;
		FontPacman.setMyFont(this, 12, color);
		this.addMouseListener(this);
		//this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.green, 2),BorderFactory.createLineBorder(background,5)));
		this.setBorderPainted(false);
		this.setFocusable(false);
		this.setPreferredSize(new Dimension(200, 30));
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.setForeground(Color.white); 
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.setForeground(writing); 
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
