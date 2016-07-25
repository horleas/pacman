package packman;

import java.awt.Image;

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
 
	
	
	public Ghost(int pposX, int pposY, int type){
		
		setPosX(pposX);
		setPosY(pposY);
		
		setType(type);
		
		setGhostdx(0);
		setGhostdy(0);
		
		ghostwaittobealive=0;
		
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
		checkRevive();
		updateframe();
		
	}


	protected void updateframe() {
		tmp++;
		if(tmp%8==0){
			setPosframe(getPosframe()+1);
			}	
	}



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

	
	public void checkRevive(){
    //Revive Ghost
	    if(getState()=="weak"){
	    	ghostwaittobealive ++ ;
	    	if(ghostwaittobealive%200 == 0){
	    		setState("alive");
	    	}
	    }
	    
	    if(getState()=="dead"){
	    	ghostwaittobealive ++ ;
	    	if(ghostwaittobealive%1000 == 0)
	    		setState("alive");
	    }
	}
	
	public int getDirection(){
		
		if(getGhostdy()==-1){return 0 ;}
		else if(getGhostdy()==1){return 1 ;}
		else if(getGhostdx()==1){return 2 ;}
		else if(getGhostdx()==-1){return 3 ;}
		else if(getGhostdx()==0 && getGhostdy()==0 ){return 1 ;}
		else{System.out.println("probleme with direction");
		return 5;}
		
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
