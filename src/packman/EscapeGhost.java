package packman;

import java.awt.Image;

import javax.swing.ImageIcon;

/*
 * Escape ghost is a special ghost always in a weak state, slightly faster than the player ( so must be block in a corner of a map or dash on)
 * He will try to stay at a 4 block distance of the player, if he is in the range of the player, he will try to escape
 * but if he is too far, he will bait the player to chase him
 */
public class EscapeGhost extends Ghost {
	private int distescape = 4*blocksize ;
	private Image imgreward = new ImageIcon(this.getClass().getResource("/lifeupscore.png")).getImage() ;
	
	public EscapeGhost(int pposX, int pposY, int type) {
		super(pposX, pposY, type);
		
		 ghostspeed = validspeeds[4];
		 setState("weak");
	}	
	
	/*
	 * if the player is in range of 4 block , the ghost will escape 
	 * else the ghost will bait the player
	 */
	protected void possibleMovement(){		
		int tile = Board.gettileinfo(getPosX(),getPosY());		
		int count = 0;
		int possibilityx[] = new int [4] ;
		int possibilityy[] = new int [4] ;
		int targetx = Board.getPacmanx();
		int targety = Board.getPacmany();
		

		if (getPosX() % blocksize == 0 && getPosY() % blocksize == 0) {
			//System.out.println(targetx-getPosX());
			if( (targetx-getPosX()< distescape && getPosX() - targetx < distescape ) && (getPosY()-targety <distescape  &&  targety - getPosY() <distescape) ){
				if ((tile & 1) == 0 && getGhostdx() != 1 && targetx >= getPosX() ) {
					possibilityx[count] = -1;
					possibilityy[count] = 0;
		            count++;
		        }
		
		        if ((tile & 2) == 0 && getGhostdy() != 1 && targety >= getPosY()) {
					possibilityx[count] = 0;
					possibilityy[count] = -1;
		            count++;
		        }
		
		        if ((tile & 4) == 0 && getGhostdx() != -1 && targetx <= getPosX() ) {
					possibilityx[count] = 1;
					possibilityy[count] = 0;
		            count++;
		        }
		
		        if ((tile & 8) == 0 && getGhostdy() != -1 && targety <= getPosY()) {
					possibilityx[count] = 0;
					possibilityy[count] = 1;
		            count++;
		        }
			}else {
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
	 * the reward for killing this ghost is to add a life to the player
	 */
	public Image getReward(){
		
		Board.addlife();
		return imgreward ;
	}
	
	/*
	 * keep the ghost in a weak state ( or dead state if eaten)
	 */
	public void setState(String state) {
		if(state == "alive" || state == "weak"){
			this.state = "weak";
		}else if(state == "dead" ){
			this.state = state ;
		}
		else{System.out.println("State not existing");}

	}
}