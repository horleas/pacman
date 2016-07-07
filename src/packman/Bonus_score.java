package packman;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Bonus_score {
	private Image img;
	private int bonus_score ;
	private int PosX;
	private int PosY;
	
	public Bonus_score(){
		
		setBonus();
		
	}

	public Image getImg() {
		return this.img;
	}

	public void setBonus() {
		 this.img = new ImageIcon("images/bonus1.png").getImage();
		 this.bonus_score = 50;			
	}

	public int getBonus_score() {
		return this.bonus_score;
	}

	public int getPosX() {
		return PosX;
	}

	public void setPosX(int posX) {
		PosX = posX;
	}

	public int getPosY() {
		return PosY;
	}

	public void setPosY(int posY) {
		PosY = posY;
	}

}
