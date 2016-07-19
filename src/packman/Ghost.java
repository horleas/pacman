package packman;

import java.awt.Image;

public class Ghost {
	
 private int posX,posY;
 private Image img ;
 private String state = "alive";
 private int type = 3;
 private int posframe = 0;
 private int ghostspeed ;
 private int ghostdx , ghostdy ;
 private int currentspeed = 3;
 private final int validspeeds[] = {1, 2, 3, 4, 6, 8};
 private int random;
 private int blocksize;
 
	
	
	public Ghost(int pposX, int pposY){
		
		setPosX(pposX);
		setPosY(pposY);
		
		setGhostdx(0);
		setGhostdy(0);
		
		blocksize = Board.getBlockSize();
		
        random = (int) (Math.random() * (currentspeed + 1));

        if (random > currentspeed) {
            random = currentspeed;
        }

        ghostspeed = validspeeds[random];
		
	}
	
	
	
	public void movement(){
		possibleMovement();
		moveGhost();
		//check collision()
		//check revive()
		
	}


	private void possibleMovement(){		
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
	
	private void moveGhost() {

        setPosX(getPosX() + (getGhostdx() * getGhostspeed()) );	
		if(getPosX()<0){setPosX( 14*blocksize);}
		if(getPosX()>14*blocksize){setPosX(0*blocksize);}
		setPosY(getPosY()+ (getGhostdy() * getGhostspeed()));
		if(getPosY()<0){setPosY(14*blocksize);}
		if(getPosY()>14*blocksize){setPosY(0*blocksize);}
		
	}


	private void checkCollision(){
		
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
