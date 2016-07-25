package packman;

import java.awt.Image;

import javax.swing.ImageIcon;

public class PhaseChaserGhost extends Ghost {
	
	private Image up1=new ImageIcon(this.getClass().getResource("/phasechaserup1.png")).getImage();
	private Image up2=new ImageIcon(this.getClass().getResource("/phasechaserup2.png")).getImage();
	private Image down1=new ImageIcon(this.getClass().getResource("/phasechaserdown1.png")).getImage();
	private Image down2=new ImageIcon(this.getClass().getResource("/phasechaserdown2.png")).getImage();
	private Image right1= new ImageIcon(this.getClass().getResource("/phasechaserright1.png")).getImage();
	private Image right2= new ImageIcon(this.getClass().getResource("/phasechaserright2.png")).getImage();
	private Image left1 = new ImageIcon(this.getClass().getResource("/phasechaserleft1.png")).getImage();
	private Image left2 = new ImageIcon(this.getClass().getResource("/phasechaserleft2.png")).getImage();

	public PhaseChaserGhost(int pposX, int pposY, int type) {
		super(pposX, pposY, type);
		
		 ghostspeed = validspeeds[2];
	}
	
	
	protected void possibleMovement(){		
		int tile = Board.gettileinfo(getPosX(),getPosY());		
		int count = 0;
		int possibilityx[] = new int [4] ;
		int possibilityy[] = new int [4] ;
		int targetx = Board.getPacmanx();
		int targety = Board.getPacmany();
		
		
		if (getPosX() % blocksize == 0 && getPosY() % blocksize == 0) {
			//Chase mode
			System.out.println("Chase Mode");
			if ( getGhostdx() != 1 && (targetx < getPosX() || (targetx - getPosX() > 14 * blocksize -targetx + getPosX()) ) ) {	//if target is on left or target is on the right and warp across the map is faster  
				possibilityx[count] = -1;
				possibilityy[count] = 0;
	            count++;
	        }
	
	        if ( getGhostdy() != 1 && (targety < getPosY() || (targety - getPosY() > 14 * blocksize -targety + getPosY()) ) ) {
				possibilityx[count] = 0;
				possibilityy[count] = -1;
	            count++;
	        }
	
	        if ( getGhostdx() != -1 && (targetx > getPosX() || (getPosX() - targetx > 14 * blocksize - getPosX() + targetx ) ) ) {
				possibilityx[count] = 1;
				possibilityy[count] = 0;
	            count++;
	        }
	
	        if ( getGhostdy() != -1 && (targety > getPosY() || (getPosY() - targety > 14 * blocksize - getPosY() + targety ) ) ) {
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
				else if(getGhostdx()==0 && getGhostdy()==0 ){this.img = down2 ;}
							
			}
		}else{
			this.img = img;
		}
	
	}
}