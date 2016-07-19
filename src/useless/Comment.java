package packman;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

public class Comment {

	/*
	 *  private void drawGhost(Graphics2D g2d, int x, int y, int numghost , int type) {
    	//(TODO) to improve ( reduce/clean code)
    	delayghost ++;
    	if(delayghost%6==0){ghostpos ++;}
    	
    	
    	if(ghoststate[numghost]=="alive"){    		//ALIVE STATE	
	    	switch(type){    	
	    	case 0: // RED HOTCHILIPEPPER
		    	// Load Left
		    	if(ghostdx[numghost]<0){ 
		    		switch(ghostpos%2){
		    		case 0 :
		    			ghost = ghostredleft1;
		    			break;
		    		default :
		    			ghost = ghostredleft2;
		    			break;   		
		    		}	
		    	}
		    	// Load Right
		    	if(ghostdx[numghost]>0){ 
		    		switch(ghostpos%2){
		    		case 0 :
		    			ghost = ghostredright1;
		    			break;
		    		default :
		    			ghost = ghostredright2;
		    			break;   		
		    		}	
		    	}
		    	// Load up
		    	if(ghostdy[numghost]<0){ 
		    		switch(ghostpos%2){
		    		case 0 :
		    			ghost = ghostredup1;
		    			break;
		    		default :
		    			ghost = ghostredup2;
		    			break;   		
		    		}	
		    	}
		    	// Load Down
		    	if(ghostdy[numghost]>0){ 
		    		switch(ghostpos%2){
		    		case 0 :
		    			ghost = ghostreddown1;
		    			break;
		    		default :
		    			ghost = ghostreddown2;
		    			break;   		
		    		}	
		    	}
		    	break;
		    	
	    	case 1: // PINK FLOYD
		    	// Load Left
		    	if(ghostdx[numghost]<0){ 
		    		switch(ghostpos%2){
		    		case 0 :
		    			ghost = ghostpinkleft1;
		    			break;
		    		default :
		    			ghost = ghostpinkleft2;
		    			break;   		
		    		}	
		    	}
		    	// Load Right
		    	if(ghostdx[numghost]>0){ 
		    		switch(ghostpos%2){
		    		case 0 :
		    			ghost = ghostpinkright1;
		    			break;
		    		default :
		    			ghost = ghostpinkright2;
		    			break;   		
		    		}	
		    	}
		    	// Load up
		    	if(ghostdy[numghost]<0){ 
		    		switch(ghostpos%2){
		    		case 0 :
		    			ghost = ghostpinkup1;
		    			break;
		    		default :
		    			ghost = ghostpinkup2;
		    			break;   		
		    		}	
		    	}
		    	// Load Down
		    	if(ghostdy[numghost]>0){ 
		    		switch(ghostpos%2){
		    		case 0 :
		    			ghost = ghostpinkdown1;
		    			break;
		    		default :
		    			ghost = ghostpinkdown2;
		    			break;   		
		    		}	
		    	}
		    	break;
		    	
	    	case 2: // TURQUOISE DAYS GREYSKIES
		    	// Load Left
		    	if(ghostdx[numghost]<0){ 
		    		switch(ghostpos%2){
		    		case 0 :
		    			ghost = ghostturquoiseleft1;
		    			break;
		    		default :
		    			ghost = ghostturquoiseleft2;
		    			break;   		
		    		}	
		    	}
		    	// Load Right
		    	if(ghostdx[numghost]>0){ 
		    		switch(ghostpos%2){
		    		case 0 :
		    			ghost = ghostturquoiseright1;
		    			break;
		    		default :
		    			ghost = ghostturquoiseright2;
		    			break;   		
		    		}	
		    	}
		    	// Load up
		    	if(ghostdy[numghost]<0){ 
		    		switch(ghostpos%2){
		    		case 0 :
		    			ghost = ghostturquoiseup1;
		    			break;
		    		default :
		    			ghost = ghostturquoiseup2;
		    			break;   		
		    		}	
		    	}
		    	// Load Down
		    	if(ghostdy[numghost]>0){ 
		    		switch(ghostpos%2){
		    		case 0 :
		    			ghost = ghostturquoisedown1;
		    			break;
		    		default :
		    			ghost = ghostturquoisedown2;
		    			break;   		
		    		}	
		    	}
		    	break;
		    	
	    	case 3: // YELLOW SUBMARINE
		    	// Load Left
		    	if(ghostdx[numghost]<0){ 
		    		switch(ghostpos%2){
		    		case 0 :
		    			ghost = ghostyellowleft1;
		    			break;
		    		default :
		    			ghost = ghostyellowleft2;
		    			break;   		
		    		}	
		    	}
		    	// Load Right
		    	if(ghostdx[numghost]>0){ 
		    		switch(ghostpos%2){
		    		case 0 :
		    			ghost = ghostyellowright1;
		    			break;
		    		default :
		    			ghost = ghostyellowright2;
		    			break;   		
		    		}	
		    	}
		    	// Load up
		    	if(ghostdy[numghost]<0){ 
		    		switch(ghostpos%2){
		    		case 0 :
		    			ghost = ghostyellowup1;
		    			break;
		    		default :
		    			ghost = ghostyellowup2;
		    			break;   		
		    		}	
		    	}
		    	// Load Down
		    	if(ghostdy[numghost]>0){ 
		    		switch(ghostpos%2){
		    		case 0 :
		    			ghost = ghostyellowdown1;
		    			break;
		    		default :
		    			ghost = ghostyellowdown2;
		    			break;   		
		    		}	
		    	}
		    	break;
		    	
	    	default: // DEFAULT
		    	// Load Left
		    	if(ghostdx[numghost]<0){ 
		    		switch(ghostpos%2){
		    		case 0 :
		    			ghost = ghostyellowleft1;
		    			break;
		    		default :
		    			ghost = ghostyellowleft2;
		    			break;   		
		    		}	
		    	}
		    	// Load Right
		    	if(ghostdx[numghost]>0){ 
		    		switch(ghostpos%2){
		    		case 0 :
		    			ghost = ghostyellowright1;
		    			break;
		    		default :
		    			ghost = ghostyellowright2;
		    			break;   		
		    		}	
		    	}
		    	// Load up
		    	if(ghostdy[numghost]<0){ 
		    		switch(ghostpos%2){
		    		case 0 :
		    			ghost = ghostyellowup1;
		    			break;
		    		default :
		    			ghost = ghostyellowup2;
		    			break;   		
		    		}	
		    	}
		    	// Load Down
		    	if(ghostdy[numghost]>0){ 
		    		switch(ghostpos%2){
		    		case 0 :
		    			ghost = ghostyellowdown1;
		    			break;
		    		default :
		    			ghost = ghostyellowdown2;
		    			break;   		
		    		}	
		    	}
		    	break;
	    	}
    	}
    	
    	

    	
    	// WEAK STATE
    	if(ghoststate[numghost]=="weak"){
	    		switch(ghostpos%2){
	    		case 0 :
	    			ghost = ghostweakblue1;
	    			break;
	    		default :
	    			ghost = ghostweakwhite1;
	    			break;   		
	    		}	
    	}
    	
    	
    	//DEAD STATE
    	if(ghoststate[numghost]=="dead"){
    		if(ghostdx[numghost]<0){ 
	    		ghost = ghostdeadleft;
	    	}
    		if(ghostdx[numghost]>0){ 
    			ghost = ghostdeadright;
    		}
    		if(ghostdy[numghost]<0){ 
    			ghost = ghostdeadup;
    		}
    		if(ghostdy[numghost]>0){ 
    			ghost = ghostdeaddown;
    		}
    	}
    	
        g2d.drawImage(ghost, x, y, this);
    }
	 * 
	 * 
	 * 
	 *****************************************************************************************************************************************************
	 *
	 *
	 *    	ghostyellow = new ImageIcon(this.getClass().getResource("/submarine.png")).getImage();
    	ghostyellowup1 = new ImageIcon(this.getClass().getResource("/yellowup1.png")).getImage();
    	ghostyellowup2 = new ImageIcon(this.getClass().getResource("/yellowup2.png")).getImage();
    	ghostyellowdown1 = new ImageIcon(this.getClass().getResource("/yellowdown1.png")).getImage();
    	ghostyellowdown2 = new ImageIcon(this.getClass().getResource("/yellowdown2.png")).getImage();
    	ghostyellowright1 = new ImageIcon(this.getClass().getResource("/yellowright1.png")).getImage();
    	ghostyellowright2 = new ImageIcon(this.getClass().getResource("/yellowright2.png")).getImage();
    	ghostyellowleft1 = new ImageIcon(this.getClass().getResource("/yellowleft1.png")).getImage();
    	ghostyellowleft2 = new ImageIcon(this.getClass().getResource("/yellowleft2.png")).getImage();
	 *
	 * 
	 * ****************************************************************************************************************************************************
	 *   for (i = 0; i < nrofghosts; i++) {
            if (ghostx[i] % blocksize == 0 && ghosty[i] % blocksize == 0) {
                pos = ghostx[i] / blocksize + nrofblocks * (int) (ghosty[i] / blocksize);

                count = 0;

                if ((screendata[pos] & 1) == 0 && ghostdx[i] != 1) {
                    dx[count] = -1;
                    dy[count] = 0;
                    count++;
                }

                if ((screendata[pos] & 2) == 0 && ghostdy[i] != 1) {
                    dx[count] = 0;
                    dy[count] = -1;
                    count++;
                }

                if ((screendata[pos] & 4) == 0 && ghostdx[i] != -1) {
                    dx[count] = 1;
                    dy[count] = 0;
                    count++;
                }

                if ((screendata[pos] & 8) == 0 && ghostdy[i] != -1) {
                    dx[count] = 0;
                    dy[count] = 1;
                    count++;
                }

                if (count == 0) {

                    if ((screendata[pos] & 15) == 15) {
                        ghostdx[i] = 0;
                        ghostdy[i] = 0;
                    } else {
                        ghostdx[i] = -ghostdx[i];
                        ghostdy[i] = -ghostdy[i];
                    }

                } else {

                    count = (int) (Math.random() * count);

                    if (count > 3) {
                        count = 3;
                    }

                    ghostdx[i] = dx[count];
                    ghostdy[i] = dy[count];
                }

            }
 
            //Warping of the Ghost
            ghostx[i] = ghostx[i] + (ghostdx[i] * ghostspeed[i]);	
    		if(ghostx[i]<0){ghostx[i] = 14*blocksize;}
    		if(ghostx[i]>14*blocksize){ghostx[i] = 0*blocksize;}
            ghosty[i] = ghosty[i] + (ghostdy[i] * ghostspeed[i]);
    		if(ghosty[i]<0){ghosty[i] = 14*blocksize;}
    		if(ghosty[i]>14*blocksize){ghosty[i] = 0*blocksize;}
    		
    		
            drawGhost(g2d, ghostx[i] + 1, ghosty[i] + 1, i, ghosttype[i]);
            

            //Revive Ghost
            if(ghoststate[i]=="weak"){
            	ghostwaittobealive[i] ++ ;
            	if(ghostwaittobealive[i]%200 == 0){
            		ghoststate[i]="alive";
            		nbreaten = 0 ;
            	}
            }
            
            if(ghoststate[i]=="dead"){
            	ghostwaittobealive[i] ++ ;
            	if(ghostwaittobealive[i]%1000 == 0)
            		ghoststate[i]="alive";
            }
            

            //Check Collision with Pacman
            if (pacmanx > (ghostx[i] - 12) && pacmanx < (ghostx[i] + 12)
                    && pacmany > (ghosty[i] - 12) && pacmany < (ghosty[i] + 12)
                    && ingame) {
            	
            	if(ghoststate[i]=="alive"){
            		dying = true;
            	}
            	
            	if(ghoststate[i]=="weak"){
            		ghoststate[i]="dead";
            		nbreaten += 1  ;
            		System.out.println(nbreaten);
            		switch(nbreaten){
            		case 1 :
                		eaten = ghosteat200;
                		score += 200; 
                		break;
            		case 2 :
                		eaten = ghosteat400;
                		score += 400;
                		break;
            		case 3 :
                		eaten = ghosteat800;
                		score += 800;
                		break;
            		case 4 :
                		eaten = ghosteat1600;
                		score += 1600;
                		nbreaten = 0;
                		break;            		
            		default :
            			nbreaten = 0;
            			break;            		
            		}
            		eateninX=ghostx[i]/blocksize;
            		eateninY=ghosty[i]/blocksize;
            		drawBonuscore(g2d);
            	}
            	
            	
            	if(ghoststate[i]=="dead"){   	}
            }
        }
	 * 
	 * ***************************************************************************************************************************************************
	 * 
	 * 
	 * 
	 *             ghosty[i] = entryGhostX[locpop] * blocksize;
            ghostx[i] = entryGhostY[locpop] * blocksize;				//put a ghost at each location and if all location has been use and there is still some ghost to place, remake the loop 
            ghostdy[i] = 0;
            ghostdx[i] = dx;
            ghosttype[i]= type;
            ghoststate[i] = "alive";
            ghostwaittobealive[i] = 0;
            
            
            
            
            
	 */
	
}
