package packman;

import java.awt.Image;

import javax.swing.ImageIcon;

/*
 * phase ghost is a ghost who will wander in the level through wall
 */
public class PhaseGhost extends Ghost {
	
	/*
	 * load image of the ghost
	 */
	private Image up1=new ImageIcon(this.getClass().getResource("/phaseup1.png")).getImage();
	private Image up2=new ImageIcon(this.getClass().getResource("/phaseup2.png")).getImage();
	private Image down1=new ImageIcon(this.getClass().getResource("/phasedown1.png")).getImage();
	private Image down2=new ImageIcon(this.getClass().getResource("/phasedown2.png")).getImage();
	private Image right1= new ImageIcon(this.getClass().getResource("/phaseright1.png")).getImage();
	private Image right2= new ImageIcon(this.getClass().getResource("/phaseright2.png")).getImage();
	private Image left1 = new ImageIcon(this.getClass().getResource("/phaseleft1.png")).getImage();
	private Image left2 = new ImageIcon(this.getClass().getResource("/phaseleft2.png")).getImage();
	private Image imgreward = new ImageIcon(this.getClass().getResource("/dashstring.png")).getImage() ;

	
	/*
	 * reduce the speed of the ghost
	 */
	public PhaseGhost(int pposX, int pposY, int type) {
		super(pposX, pposY, type);
		
		 ghostspeed = validspeeds[0];
	}
	
	protected void possibleMovement(){		
		int count = 0;
		int possibilityx[] = new int [4] ;
		int possibilityy[] = new int [4] ;
		
		
		if (getPosX() % blocksize == 0 && getPosY() % blocksize == 0) {
			if (getGhostdx() != 1) {
				possibilityx[count] = -1;
				possibilityy[count] = 0;
	            count++;
	        }
	
	        if ( getGhostdy() != 1) {
				possibilityx[count] = 0;
				possibilityy[count] = -1;
	            count++;
	        }
	
	        if (getGhostdx() != -1) {
				possibilityx[count] = 1;
				possibilityy[count] = 0;
	            count++;
	        }
	
	        if (getGhostdy() != -1) {
				possibilityx[count] = 0;
				possibilityy[count] = 1;
	            count++;
	        }	        
	
	            count = (int) (Math.random() * count);
	
	            if (count > 3) {
	                count = 3;
	            }	
	            setGhostdx(possibilityx[count]);
	            setGhostdy(possibilityy[count]); 	               
		}		
	}
	
	/*
	 * the reward to kill the ghost is 5 dash
	 */
	public Image getReward(){
		Board.addDash(5);
		return imgreward ;
	}
	
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
