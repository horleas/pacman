package packman;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ChaserGhost extends Ghost {
	
	/*
	 * all the image used for the living sprite of the chaser
	 */
	private Image up1=new ImageIcon(this.getClass().getResource("/chaserup1.png")).getImage();
	private Image up2=new ImageIcon(this.getClass().getResource("/chaserup2.png")).getImage();
	private Image down1=new ImageIcon(this.getClass().getResource("/chaserdown1.png")).getImage();
	private Image down2=new ImageIcon(this.getClass().getResource("/chaserdown2.png")).getImage();
	private Image right1= new ImageIcon(this.getClass().getResource("/chaserright1.png")).getImage();
	private Image right2= new ImageIcon(this.getClass().getResource("/chaserright2.png")).getImage();
	private Image left1 = new ImageIcon(this.getClass().getResource("/chaserleft1.png")).getImage();
	private Image left2 = new ImageIcon(this.getClass().getResource("/chaserleft2.png")).getImage();
	private Image imgreward = new ImageIcon(this.getClass().getResource("/bonuspts300.png")).getImage() ;


	/*
	 * extends from ghost but reduce his speeds to be slower than the player
	 */
	public ChaserGhost(int pposX, int pposY, int type) {
		super(pposX, pposY, type);
		
		 ghostspeed = validspeeds[2];
		
	}
	
	/*
	 * will take the direction where the player/target is
	 */
	protected void possibleMovement(){		
		int tile = Board.gettileinfo(getPosX(),getPosY());		
		int count = 0;
		int possibilityx[] = new int [4] ;
		int possibilityy[] = new int [4] ;
		int targetx = Board.getPacmanx();
		int targety = Board.getPacmany();
		
		
		if (getPosX() % blocksize == 0 && getPosY() % blocksize == 0) {
			//Chase mode
			if ((tile & 1) == 0 && getGhostdx() != 1 && targetx < getPosX() ) {
				possibilityx[count] = -1;
				possibilityy[count] = 0;
	            count++;
	        }
	
	        if ((tile & 2) == 0 && getGhostdy() != 1 && targety < getPosY()) {
				possibilityx[count] = 0;
				possibilityy[count] = -1;
	            count++;
	        }
	
	        if ((tile & 4) == 0 && getGhostdx() != -1 && targetx > getPosX() ) {
				possibilityx[count] = 1;
				possibilityy[count] = 0;
	            count++;
	        }
	
	        if ((tile & 8) == 0 && getGhostdy() != -1 && targety > getPosY()) {
				possibilityx[count] = 0;
				possibilityy[count] = 1;
	            count++;
	        }
	        
	        if (count == 0) {
	
	            if ((tile & 15) == 15) {
	            	setGhostdx(0);
	                setGhostdy(0);
	            } else {
	            	setGhostdx(-getGhostdx());
	            	setGhostdy(-getGhostdy());
	            }
	
	        } else {
	
	            count = (int) (Math.random() * count);
	
	            if (count > 3) {
	                count = 3;
	            }
	
	            setGhostdx(possibilityx[count]);
	            setGhostdy(possibilityy[count]); 
	        }
        
		}
		
	}
	
	/*
	 * reward is 300 point
	 */
	public Image getReward(){
		
		Board.updateScore(300);
		return imgreward ;
	}
	
	
	/*
	 * set the sprite for the living chaser 
	 */
	public void setImg(Image img) {
		if(getState()=="alive"){
			if(getPosframe()%2==0){
				if(getGhostdy()==-1){ this.img = up1 ;}
				else if(getGhostdy()==1){this.img = down1 ;}
				else if(getGhostdx()==1){this.img = right1 ;}
				else if(getGhostdx()==-1){this.img = left1 ;}
				else if(getGhostdx()==0 && getGhostdy()==0 ){this.img = down1 ;}
			}else{
				if(getGhostdy()==-1){ this.img = up2;}
				else if(getGhostdy()==1){this.img = down2 ;}
				else if(getGhostdx()==1){this.img = right2 ;}
				else if(getGhostdx()==-1){this.img = left2 ;}
				else if(getGhostdx()==0 && getGhostdy()==0 ){this.img = down1 ;}
							
			}
		}else{
			this.img = img;
		}
	
	}
}