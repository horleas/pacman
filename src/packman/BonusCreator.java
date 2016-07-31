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
	private int totalbonus = 11;
	private String describe ;
	
	
	/*
	 * create a random bonus ( called when pacman eat 50 pacgums)
	 */
	public BonusCreator(){
		
		setBonus();
		
	}
	
	/*
	 * create a specific bonus ( with position and type of bonus
	 */
	public BonusCreator(int posX, int posY ,int type){
		
		setBonus(posX, posY,type);
		
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

	public Image getImg() {
		return this.img;
	}
	
	public Image getImgScore() {
		return this.imgscore;
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

	/*
	 * the typebonus parametre define the bonus with a img, the imgscore is the explanation of the bonus
	 * a bonus_score to add to score
	 * and a little description for the panel rules 
	 * and a name to be easily recognize in the board checking when eating bonus
	 */
	public void setType(int typebonus) {
		switch(typebonus){
		case 1 :
			 this.img = new ImageIcon(this.getClass().getResource("/bonus2.png")).getImage();
			 this.imgscore = new ImageIcon(this.getClass().getResource("/bonuspts20.png")).getImage();
			 this.bonus_score = 20;	
			 this.setName("bonus_score" + bonus_score);
			 this.setDescribe(" Strawberry fields forever (50pts)");
			 
			 break;
		case 2 :
			 this.img = new ImageIcon(this.getClass().getResource("/bonus3.png")).getImage();
			 this.imgscore = new ImageIcon(this.getClass().getResource("/bonuspts50.png")).getImage();
			 this.bonus_score = 50;	
			 this.setDescribe(" Fire melon, Earth Melon, Air Melon ...(100pts)");
			 this.setName("bonus_score" + bonus_score);
			
			 break;
		case 3 :
			 this.img = new ImageIcon(this.getClass().getResource("/bonus1.png")).getImage();
			 this.imgscore = new ImageIcon(this.getClass().getResource("/bonuspts100.png")).getImage();
			 this.bonus_score = 100;
			 this.setName("bonus_score" + bonus_score);
			 this.setDescribe("Cherry blossoms, color and scent fall with them ...(200pts)");
			 break;
			 
		case 4 :
			 this.img = new ImageIcon(this.getClass().getResource("/lifeup2.png")).getImage();
			 this.imgscore = new ImageIcon(this.getClass().getResource("/lifeupscore.png")).getImage();
			 this.setName("lifeup");
			 this.setDescribe("Even a cat envies you ! Add a life ( max : 10)");
			
			 break;
			 
		case 5 :
			 this.img = new ImageIcon(this.getClass().getResource("/dash.png")).getImage();
			 this.imgscore = new ImageIcon(this.getClass().getResource("/dashstring.png")).getImage();
			 this.setName("jumprefill");
			 this.setDescribe(" You have the ability to dash through wall and more.");
			 break;
		
			 //(TODO) Verify there is ghost in the level for random pop
		case 6 :
			 this.img = new ImageIcon(this.getClass().getResource("/ghostbusters.png")).getImage();
			 this.imgscore = new ImageIcon(this.getClass().getResource("/ghosteater.png")).getImage();
			 this.setName("ghosteater");
			 this.setDescribe(" Who you gonna call ? (You can eat the ghost now)");
			 break;
			 
		case 8 :
			 this.img = new ImageIcon(this.getClass().getResource("/dashdecrease.png")).getImage();
			 this.imgscore = new ImageIcon(this.getClass().getResource("/decreasedashstring.png")).getImage();
			 this.setName("jumpdowngrade");
			 this.setDescribe(" Reduce your dash");
			 break;
			 
		case 9 :
			 this.img = new ImageIcon(this.getClass().getResource("/dashupgrade.png")).getImage();
			 this.imgscore = new ImageIcon(this.getClass().getResource("/upgradedashstring.png")).getImage();
			 this.setName("jumpupgrade");
			 this.setDescribe(" Improve your dash");
			 break;
			 
		case 10 :
			 this.img = new ImageIcon(this.getClass().getResource("/finishline.png")).getImage();
			 this.imgscore = new ImageIcon(this.getClass().getResource("/finishline.png")).getImage();
			 this.setName("finishline");
			 this.setDescribe("Save her and go to the next level !");
			 break;
			
			 
		default : 
			this.img = new ImageIcon(this.getClass().getResource("/bonus4.png")).getImage();
			this.imgscore = new ImageIcon(this.getClass().getResource("/bonuspts500.png")).getImage();
			this.bonus_score = 500;	
			 this.setName("bonus_score" + bonus_score);
			 this.setDescribe(" Eat an apple a day, keeps the ghost away (500pts)");
			break;
		}
	}

	public int getTotalbonus() {
		return totalbonus;
	}

	public void setTotalbonus(int totalbonus) {
		this.totalbonus = totalbonus;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
}
