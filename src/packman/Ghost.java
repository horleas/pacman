package packman;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Ghost {
	
 protected int posX,posY;
 protected Image img ;
 protected String state = "alive";
 protected int type = 3;
 protected int posframe = 0;
 protected int ghostspeed ;
 protected int ghostdx , ghostdy ;
 protected int currentspeed = 3;
 protected final int validspeeds[] = {1, 2, 3, 4, 6, 8};
 protected int random;
 protected int blocksize;
 protected int ghostwaittobealive;
 protected int tmp = 0;
 protected static int nbreaten = 0 ;
 private Image ghosteat200, ghosteat400, ghosteat800, ghosteat1600;
 private boolean alreadysound = false ;
 private int ghostweakdelay = 200 ;
 private int ghostdeaddelay = 1000 ;
 
 
 
	
	/*
	 * Main class for the ghost
	 * need position and direction
	 * state (alive/weak/dead)
	 * type is for the color (red/turquoise/yellow/pink)
	 * some delay to repop of the ghost
	 * and a random speed
	 */
	public Ghost(int pposX, int pposY, int type){
		
		setPosX(pposX);
		setPosY(pposY);
		
		setType(type);
		
		setGhostdx(0);
		setGhostdy(0);
		
		ghostwaittobealive=0;
		
        //LOAD GHOST EAT POINT
    	
    	ghosteat200 = new ImageIcon(this.getClass().getResource("/ghosteat200.png")).getImage();
    	ghosteat400 = new ImageIcon(this.getClass().getResource("/ghosteat400.png")).getImage();
    	ghosteat800 = new ImageIcon(this.getClass().getResource("/ghosteat800.png")).getImage();
    	ghosteat1600 = new ImageIcon(this.getClass().getResource("/ghosteat1600.png")).getImage();
		
		blocksize = Board.getBlockSize();
		
        random = (int) (Math.random() * (currentspeed + 1));

        if (random > currentspeed) {
            random = currentspeed;
        }

        ghostspeed = validspeeds[random];
		
	}
	
	
	/*
	 * when ghost turn to move in the Board, movement is called which is constitute in 4 parts
	 * possible move is override for special ghost because it hold the algorithm to choose the direction to move
	 * move ghost (can pass from one side to another)
	 * check if the ghost is in weak or dead state, delay until revive
	 * and after, updtae the sprite to choose
	 * 
	 */
	public void movement(){
		possibleMovement();
		moveGhost();
		checkRevive();
		updateframe();
		
	}

	/*
	 * each 8 frame , the sprite will be update 
	 */
	
	protected void updateframe() {
		tmp++;
		if(tmp%8==0){
			setPosframe(getPosframe()+1);
			}	
	}


/*
 * possiblemovement will check each direction of the tile where the ghost is
 * to check if the path is free to go or if there is a wall in front
 * if possible , the direction will be added to a random lottery which will be chosen after all the 4 directions
 * has been tried.
 * check if the direction is not opposite from the last choice to maintain the direction if the ghost is in a corridor
 * if there is no direction in the lottery, the ghost will go back
 * if the ghost is trap inside 4 wall he will stop moving
 */
	protected void possibleMovement(){		
		int tile = Board.gettileinfo(getPosX(),getPosY());		
		int count = 0;
		int possibilityx[] = new int [4] ;
		int possibilityy[] = new int [4] ;
		
		
		if (getPosX() % blocksize == 0 && getPosY() % blocksize == 0) {
			if ((tile & 1) == 0 && getGhostdx() != 1) {
				possibilityx[count] = -1;
				possibilityy[count] = 0;
	            count++;
	        }
	
	        if ((tile & 2) == 0 && getGhostdy() != 1) {
				possibilityx[count] = 0;
				possibilityy[count] = -1;
	            count++;
	        }
	
	        if ((tile & 4) == 0 && getGhostdx() != -1) {
				possibilityx[count] = 1;
				possibilityy[count] = 0;
	            count++;
	        }
	
	        if ((tile & 8) == 0 && getGhostdy() != -1) {
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
	 * deplaced the ghost (warp through map)
	 * and some print to know where the ghost is going
	 */
	protected void moveGhost() {

        setPosX(getPosX() + (getGhostdx() * getGhostspeed()) );	
		if(getPosX()<0){setPosX( 14*blocksize);}
		if(getPosX()>14*blocksize){setPosX(0*blocksize);}
		setPosY(getPosY()+ (getGhostdy() * getGhostspeed()));
		if(getPosY()<0){setPosY(14*blocksize);}
		if(getPosY()>14*blocksize){setPosY(0*blocksize);}
		
		/*
		if(getGhostdx()<0){System.out.println("go left");}
		if(getGhostdx()>0){System.out.println("go right");}
		if(getGhostdy()<0){System.out.println("go up");}
		if(getGhostdy()>0){System.out.println("go down");}
		*/
	}

	/*
	 * when the ghost is eaten, a delay begin for when he will go back to "life"
	 * warning when there is only 50 frame from going back to life
	 */
	public void checkRevive(){
    //Revive Ghost
	    if(getState()=="weak"){
	    	ghostwaittobealive ++ ;
	    	if(ghostwaittobealive%ghostweakdelay == 0){
	    		setState("alive");
	    		ghostwaittobealive = 0;
	    		alreadysound = false ;
	    	}
	    }
	    
	    if(getState()=="dead"){	    	
	    	ghostwaittobealive ++ ;
	    	if(ghostwaittobealive%ghostdeaddelay == 0){
	    		setState("alive");
	    		ghostwaittobealive = 0;
	    		alreadysound = false ;
	    	}
	    }
	    
	    if(getState()=="dead" && ghostwaittobealive > (ghostdeaddelay-50) && !alreadysound){
	    	Sound.play("warning.wav");
	    	alreadysound = true ;
	    }else if(getState()=="weak" && ghostwaittobealive > (ghostweakdelay-50) && !alreadysound){
	    	Sound.play("warning.wav");
	    	alreadysound = true ;
	    } 
	}
	
	/*
	 * when there is still 50 frame from coming back to life, the ghost will alterne from blue weak and white weak
	 */
	public int weakState(){
		if(ghostwaittobealive > 150){
			return 4 ;
		}
		else{
			return 2 ;
		}
	}
	
	/*
	 * get direction for the sprite selection 
	 */
	public int getDirection(){
		
		if(getGhostdy()==-1){return 0 ;}
		else if(getGhostdy()==1){return 1 ;}
		else if(getGhostdx()==1){return 2 ;}
		else if(getGhostdx()==-1){return 3 ;}
		else if(getGhostdx()==0 && getGhostdy()==0 ){return 1 ;}
		else{System.out.println("probleme with direction");
		return 5;}
		
	}
	
	/*
	 * when the ghost is eaten, the player will get some pts depending
	 * of the number of ghost eaten in the current level
	 * (or the time to a ghost to come back to life)
	 */
	public Image getReward(){
		
		nbreaten += 1  ;
		System.out.println(nbreaten);
		switch(nbreaten){
		case 1 :
			Board.updateScore(200);
    		return ghosteat200;
		case 2 :
			Board.updateScore(400);
			return ghosteat400;
		case 3 :
			Board.updateScore(400);
			return ghosteat800;
		case 4 :
			Board.updateScore(1600);
    		nbreaten = 0;
    		return ghosteat1600;
		default :
			nbreaten = 0;
			break;            		
		}
				
		return ghosteat200;
	}

	
	public int getPosY() {
		return posY;
	}



	public void setPosY(int posY) {
		this.posY = posY;
	}



	public Image getImg() {
		return img;
	}



	public void setImg(Image img) {
		this.img = img;
	}



	public int getPosX() {
		return posX;
	}



	public void setPosX(int posX) {
		this.posX = posX;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		if(state == "alive" || state == "weak" || state == "dead"){
			this.state = state;
			alreadysound = false ;
		}
		else{System.out.println("State not existing");}

	}
	
	



	public int getType() {
		return type;
	}



	public void setType(int type) {
		this.type = type;
	}



	public int getGhostdx() {
		return ghostdx;
	}



	public void setGhostdx(int ghostdx) {
		this.ghostdx = ghostdx;
	}



	public int getGhostdy() {
		return ghostdy;
	}



	public void setGhostdy(int ghostdy) {
		this.ghostdy = ghostdy;
	}



	public int getGhostspeed() {
		return ghostspeed;
	}



	public void setGhostspeed(int ghostspeed) {
		this.ghostspeed = ghostspeed;
	}



	public int getPosframe() {
		return posframe;
	}



	public void setPosframe(int posframe) {
		this.posframe = posframe;
	}
	
	
}
