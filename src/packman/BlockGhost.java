package packman;

import java.awt.Image;

import javax.swing.ImageIcon;

/*
 * Block Ghost is a special ghost who have 2 comportement :
 * he have a target depending on your movement
 * If you are moving, he will try to go 3 block in front of you
 * if you stop moving, he will try to go directly on you
 * he will reset his target only when he has complete the last one ( so to be 3 block in front of your previous position)
 * this class of ghost is for the moment not so usefull because the pathfinding is not implemented.
 * The speed of this ghost is 4 which is similar to pacman speed
 * 
 * Block extends Ghost and will just modify the possible movement and the getreward, add the settarget
 * possibility to add other image and to used the function reducedist which reduce the distance between the player and the target 
 */
public class BlockGhost extends Ghost {
	private int targetx = 0;
	private int targety = 0;
	private int distblock = 2 * blocksize ;
	private Image imgreward = new ImageIcon(this.getClass().getResource("/bonuspts300.png")).getImage() ;

	public BlockGhost(int pposX, int pposY, int type) {
		super(pposX, pposY, type);
		
		 ghostspeed = validspeeds[4];
		 setTarget();
	}	
	
	/*
	 * if the ghost is on the target, he will set the new target
	 * else he will choose between the 4 directions
	 * first step is to get all the direction which help the ghost to go to the target
	 * second step is a random choice between the good direction
	 * if the ghost is blocked inside a tile ( tile & 15 ) he will stop
	 * if the ghost go on a dead end, he will go back 
	 */
	protected void possibleMovement(){		
		int tile = Board.gettileinfo(getPosX(),getPosY());		
		int count = 0;
		int possibilityx[] = new int [4] ;
		int possibilityy[] = new int [4] ;

		//System.out.println("Target is in the Tile : ["+targetx/blocksize+":"+targety/blocksize+"] and ghost is in the Tile["+getPosX()/blocksize+":"+getPosY()/blocksize+"] !" );
		if(targetx/blocksize == getPosX()/blocksize && targety/blocksize == getPosY()/blocksize){
			setTarget();
		}
		
		
		
		if (getPosX() % blocksize == 0 && getPosY() % blocksize == 0) {
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
	 * eat a block ghost will reward the player with 300 pts
	 */
	public Image getReward(){
		
		Board.updateScore(300);
		return imgreward ;
	}
	
	/*
	 * if pacman don't move ( getPacmandx and getPacmandy , the target is the location of pacman)
	 * else if pacman go on the right, target is on pacmanx , pacmany + distance of blocking
	 * else if etc
	 * 
	 * if pacman is on the edge of the map, the calcul will put the target ouside of the map so in order to avoid this comportement
	 * the target is set with the last position possible so near the edge
	 */
	private void setTarget(){
		//if target don't move , go on it
		if(Board.getPacmandx() == 0 && Board.getPacmandy() == 0 ) {
			targetx = Board.getPacmanx() ; 
			targety = Board.getPacmany() ;
			//System.out.println("Target don't move , go on it : "+Board.getPacmanx()+ "    " + Board.getPacmany());
			}
		// if target move, try to be on their path
		else{
			//System.out.println("Target move , block path : "+Board.getPacmandx()+ "    " + Board.getPacmandy());
			if(Board.getPacmandx()==-1){
				targetx = Board.getPacmanx() - distblock;
				targety =  Board.getPacmany() ;
				}
			if(Board.getPacmandx()== 1){
				targetx = Board.getPacmanx() + distblock;
				targety = Board.getPacmany() ;
				}
			if(Board.getPacmandy()==-1){
				targetx = Board.getPacmanx();
				targety = Board.getPacmany() - distblock;
				}
			if(Board.getPacmandy()== 1){
				targetx = Board.getPacmanx();
				targety = Board.getPacmany() + distblock;
				}				
		}
		
		// Check target no off map
		if(targetx/blocksize >= 14) { targetx = 14*blocksize ;}
		if(targetx/blocksize <= 0) { targetx = 0 ;}
		if(targety/blocksize >= 14) { targety = 14*blocksize ;}
		if(targety/blocksize <= 0) { targety = 0 ;}
		
		//System.out.println("New Target is in the Tile : ["+targetx/blocksize+":"+targety/blocksize+"] !" );
		
	}
	
	@SuppressWarnings("unused")
	private void reducedist(){
		distblock -= blocksize;		
	}
	
}