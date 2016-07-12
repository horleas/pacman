package packman;

import java.awt.Image;

import javax.swing.ImageIcon;


public class BonusCreator {
	private Image img;
	private Image imgscore;
	private int bonus_score = 0;
	private int PosX;
	private int PosY;
	private String name;
	private static boolean upgradedashmax = false;	
	
	public BonusCreator(){
		
		setBonus();
		
	}

	public Image getImg() {
		return this.img;
	}
	
	public Image getImgScore() {
		return this.imgscore;
	}

	public void setBonus() {
		int randombonus = (int) (Math.random()*7);	

		if(upgradedashmax && randombonus == 6){randombonus=5; }
		
		switch(randombonus){
		case 1 :
			 this.img = new ImageIcon("images/bonus2.png").getImage();
			 this.imgscore = new ImageIcon("images/bonuspts20.png").getImage();
			 this.bonus_score = 20;	
			 this.setName("bonus_score" + bonus_score);
			 break;
		case 2 :
			 this.img = new ImageIcon("images/bonus3.png").getImage();
			 this.imgscore = new ImageIcon("images/bonuspts50.png").getImage();
			 this.bonus_score = 50;	
			 this.setName("bonus_score" + bonus_score);
			 break;
		case 3 :
			 this.img = new ImageIcon("images/bonus1.png").getImage();
			 this.imgscore = new ImageIcon("images/bonuspts100.png").getImage();
			 this.bonus_score = 100;
			 this.setName("bonus_score" + bonus_score);
			 break;
			 
		case 4 :
			 this.img = new ImageIcon("images/lifeup.png").getImage();
			 this.imgscore = new ImageIcon("images/lifeupscore.png").getImage();
			 this.setName("lifeup");
			 break;
			 
		case 5 :
			 this.img = new ImageIcon("images/dash.png").getImage();
			 this.imgscore = new ImageIcon("images/dashstring.png").getImage();
			 this.setName("jumprefill");
			 break;
			 
		case 6 :
			 this.img = new ImageIcon("images/dashupgrade.png").getImage();
			 this.imgscore = new ImageIcon("images/upgradedashstring.png").getImage();
			 this.setName("jumpupgrade");
			 upgradedashmax = true;
			 break;
			 
		default : 
			this.img = new ImageIcon("images/bonus4.png").getImage();
			this.imgscore = new ImageIcon("images/bonuspts500.png").getImage();
			this.bonus_score = 500;	
			 this.setName("bonus_score" + bonus_score);
			break;
		}
		
		
		
		
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
