package packman;

public class PhaseEscapeGhost extends Ghost  {
	private int distescape = 3*blocksize ;
	
	public PhaseEscapeGhost(int pposX, int pposY, int type) {
		super(pposX, pposY, type);
		
		 ghostspeed = validspeeds[3];
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

			//TODO
			// work in map but bug with border escape  
			if( ((targetx-getPosX()< distescape && getPosX() - targetx < distescape ) && (getPosY()-targety <distescape  ||  targety - getPosY() <distescape))
					||(getPosX() < targetx + distescape -14*blocksize ||    getPosX() > targetx - distescape + 14*blocksize )
					||(getPosY() < targety + distescape -14*blocksize ||    getPosY() > targety - distescape + 14*blocksize) ){
				//Escape mode
				System.out.println("Escape Mode");
				if (( targetx >= getPosX() || getPosX() > 14*blocksize - distescape + targetx) ) {
					if(!( getPosX() > targetx - distescape + 14*blocksize )){
						possibilityx[count] = -1;
						possibilityy[count] = 0;
			            count++;
					}
		        }
		
		        if (( targety <= getPosY()|| getPosY() > 14*blocksize - distescape + targety) ) {
		        	if(!(getPosY() < targety + distescape -14*blocksize )){
						possibilityx[count] = 0;
						possibilityy[count] = -1;
			            count++;
		        	}
		        }
		
		        if ((  targetx <= getPosX() || getPosX() <  targetx - 14*blocksize + distescape ) ) {
		        	if(!(getPosX() > 14*blocksize - distescape + targetx)){
						possibilityx[count] = 1;
						possibilityy[count] = 0;
			            count++;
		           
		        	}
		        }
		
		        if (( targety >= getPosY() || getPosY() <  targety - 14*blocksize + distescape )) {
		        	if(!( getPosY() > targety - distescape + 14*blocksize )){
						possibilityx[count] = 0;
						possibilityy[count] = 1;
			            count++;
		        	}
		        }
			}else {
				//Chase mode
				System.out.println("Chase Mode");
				if ( getGhostdx() != 1 && (targetx < getPosX() || (targetx - getPosX() > 14 * blocksize -targetx + getPosX()) ) ) {
					possibilityx[count] = -1;
					possibilityy[count] = 0;
		            count++;
		        }
		
		        if (getGhostdy() != 1 && (targety < getPosY() || (targety - getPosY() > 14 * blocksize -targety + getPosY()) ) ) {
					possibilityx[count] = 0;
					possibilityy[count] = -1;
		            count++;
		        }
		
		        if (getGhostdx() != -1 &&(targetx > getPosX() || (getPosX() - targetx > 14 * blocksize - getPosX() + targetx ) ) )  {
					possibilityx[count] = 1;
					possibilityy[count] = 0;
		            count++;
		        }
		
		        if (getGhostdy() != -1 && (targety > getPosY() || (getPosY() - targety > 14 * blocksize - getPosY() + targety ) ) ) {
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