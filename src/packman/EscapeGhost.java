package packman;

public class EscapeGhost extends Ghost {
	private int distescape = 4*blocksize ;
	
	public EscapeGhost(int pposX, int pposY, int type) {
		super(pposX, pposY, type);
		
		 ghostspeed = validspeeds[4];
		 setState("weak");
	}	
	
	
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
	
	
	public void setState(String state) {
		if(state == "alive" || state == "weak"){
			this.state = "weak";
		}else if(state == "dead" ){
			this.state = state ;
		}
		else{System.out.println("State not existing");}

	}
}