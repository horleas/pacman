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
	private int type;
	
	
	public BonusCreator(){
		
		setBonus();
		
	}
	
	public BonusCreator(int posX, int posY ,int type){
		
		setBonus(posX, posY,type);
		
	}

	public Image getImg() {
		return this.img;
	}
	
	public Image getImgScore() {
		return this.imgscore;
	}

	public void setBonus() {
		int randombonus = (int) (Math.random()*7);	
		this.setType(randombonus);
	}	
		
		
		
	public void setBonus(int posX,int posY,int typebonus) {
		
		this.setType(typebonus);
		this.setPosX(posX);
		this.setPosY(posY);

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

	public int getType() {
		return type;
	}

	public void setType(int typebonus) {
		//if(upgradedashmax && typebonus == 6){typebonus=5; }
		switch(typebonus){
		case 1 :
			 this.img = new ImageIcon(this.getClass().getResource("/bonus2.png")).getImage();
			 this.imgscore = new ImageIcon(this.getClass().getResource("/bonuspts20.png")).getImage();
			 this.bonus_score = 20;	
			 this.setName("bonus_score" + bonus_score);
			 break;
		case 2 :
			 this.img = new ImageIcon(this.getClass().getResource("/bonus3.png")).getImage();
			 this.imgscore = new ImageIcon(this.getClass().getResource("/bonuspts50.png")).getImage();
			 this.bonus_score = 50;	
			 this.setName("bonus_score" + bonus_score);
			 break;
		case 3 :
			 this.img = new ImageIcon(this.getClass().getResource("/bonus1.png")).getImage();
			 this.imgscore = new ImageIcon(this.getClass().getResource("/bonuspts100.png")).getImage();
			 this.bonus_score = 100;
			 this.setName("bonus_score" + bonus_score);
			 break;
			 
		case 4 :
			 this.img = new ImageIcon(this.getClass().getResource("/lifeup.png")).getImage();
			 this.imgscore = new ImageIcon(this.getClass().getResource("/lifeupscore.png")).getImage();
			 this.setName("lifeup");
			 break;
			 
		case 5 :
			 this.img = new ImageIcon(this.getClass().getResource("/dash.png")).getImage();
			 this.imgscore = new ImageIcon(this.getClass().getResource("/dashstring.png")).getImage();
			 this.setName("jumprefill");
			 break;
		
			 //(TODO) Verify there is ghost in the level for random pop
		case 6 :
			 this.img = new ImageIcon(this.getClass().getResource("/ghostbusters.png")).getImage();
			 this.imgscore = new ImageIcon(this.getClass().getResource("/ghosteater.png")).getImage();
			 this.setName("ghosteater");
			 break;
			 
		case 8 :
			 this.img = new ImageIcon(this.getClass().getResource("/dashdecrease.png")).getImage();
			 this.imgscore = new ImageIcon(this.getClass().getResource("/decreasedashstring.png")).getImage();
			 this.setName("jumpdowngrade");
			 break;
			 
		case 9 :
			 this.img = new ImageIcon(this.getClass().getResource("/dashupgrade.png")).getImage();
			 this.imgscore = new ImageIcon(this.getClass().getResource("/upgradedashstring.png")).getImage();
			 this.setName("jumpupgrade");
			 break;
			
			 
		default : 
			this.img = new ImageIcon(this.getClass().getResource("/bonus4.png")).getImage();
			this.imgscore = new ImageIcon(this.getClass().getResource("/bonuspts500.png")).getImage();
			this.bonus_score = 500;	

			 this.setName("bonus_score" + bonus_score);
			break;
		}
	}
}
