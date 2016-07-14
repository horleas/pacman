package packman;

import java.util.ArrayList;

public class Maze {

    private final int blocksize = 24;
    private final int nrofblocks = 15;
	
	private int numlevel = 30;
	private int nbrGhost = 30 ;
	private String name = "Default";
    private short map[] = new short [nrofblocks*nrofblocks];
    private int dashlevel = 0;
	private ArrayList<BonusCreator> bonusfixelist = new ArrayList<BonusCreator>() ;
	private BonusCreator bonus;
    
	/* 1 = Left Border
	 * 2 = Top Border
	 * 4 = Right Border
	 * 8 = Bottom Border
	 * 16 = Point
	 * 32 = EntreePoint for Player
	 * 64 = EntreePoint for Ghost
	 * 
	 * 
	 *  private final short base[] = {
		        19, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22,
		        17, 48, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,		// EntreePoint for Player [ 2 : 2 ]
		        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
		        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
		        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
		        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
		        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
		        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
		        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
		        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
		        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
		        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
		        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
		        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 80, 20,   // Ghost Entry in [14 : 14]
		        9, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 28
		    };
	 */
	
	//Basic
	 private final short leveldata1[] = {
		        19, 26, 26, 26, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22,
		        21,  3,  2,  6, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
		        21,  1, 64,  4, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,				// Ghost Entry in [3 : 3]
		        21,  9,  0, 12, 17, 16, 16, 24, 16, 16, 16, 16, 16, 16, 20,
		        17, 18, 16, 18, 16, 16, 20,  0, 17, 16, 16, 16, 16, 16, 20,
		        17, 16, 16, 16, 16, 16, 20,  0, 17, 16, 16, 16, 16, 24, 20,
		        25, 16, 16, 16, 24, 24, 28,  0, 25, 24, 24, 16, 20,  0, 21,
		         1, 17, 16, 20,  0,  0,  0,  0,  0,  0,  0, 17, 20,  0, 21,
		         1, 17, 16, 16, 18, 18, 22,  0, 19, 18, 18, 16, 20,  0, 21,
		         1, 17, 16, 16, 16, 16, 20,  0, 17, 16, 16, 16, 20,  0, 21,
		         1, 17, 16, 16, 16, 16, 20,  0, 17, 16, 16, 16, 20,  0, 21,
		         1, 17, 16, 16, 16, 16, 16, 18, 16, 16, 16, 16, 20,  0, 21,
		         1, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 52,  0, 21,				// Player Entry in [13 : 13]
		         1, 25, 24, 24, 24, 24, 24, 24, 24, 24, 16, 16, 16, 18, 20,
		         9,  8,  8,  8,  8,  8,  8,  8,  8,  8, 25, 24, 24, 24, 28
		    };
	 
	 //Old School
	 private final short leveldata2[] = {
		        19, 26, 26, 18, 26, 26, 22,  0, 19, 26, 26, 18, 26, 26, 22,
		        21,  0,  0, 21,  0,  0, 21,  0, 21,  0,  0, 21,  0,  0, 21,
		        17, 18, 18, 20,  0, 19, 24, 26, 24, 22,  0, 17, 18, 18, 20,
		        17, 24, 16, 16, 26, 20,  3,  2,  6, 17, 26, 16, 16, 24, 20,
		        21,  0, 17, 28,  0, 21,  1, 64,  4, 21,  0, 25, 20,  0, 21,     		// Ghost Entry in [8 : 5]
		        21,  0, 21,  0,  0, 21,  9,  0, 12, 21,  0,  0, 21,  0, 21,
		        17, 18, 16, 22,  0, 17, 26, 24, 26, 20,  0, 19, 16, 18, 20,
		        17, 24, 16, 16, 26, 20,  0,  0,  0, 17, 26, 16, 16, 24, 20,
		        21,  0, 17, 20,  0, 17, 18, 58, 18, 20,  0, 17, 20,  0, 21,				// Player Entry in [8 : 8]
		        21,  0, 17, 20,  0, 17, 20,  0, 17, 20,  0, 17, 20,  0, 21,
		        21,  0, 25, 16, 18, 16, 28,  0, 25, 16, 18, 16, 28,  0, 21,
		        21,  0,  0, 17, 24, 20,  0,  0,  0, 17, 24, 20,  0,  0, 21,
		        17, 26, 26, 20,  0, 25, 18, 26, 18, 28,  0, 17, 26, 26, 20,
		        21,  0,  0, 21,  0,  0, 21,  0, 21,  0,  0, 21,  0,  0, 21,
		        25, 26, 26, 24, 26, 26, 28,  0, 25, 26, 26, 24, 26, 26, 28
		    };
	  
	 //Arena
	 private final short leveldata3[] = {
			     3, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10,  6,
		         5, 51,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  6,  5,				// Player Entry in [2 : 2]
		         5,  1, 19, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22,  4,  5,
		         5,  1, 17, 16, 16, 16, 16, 16, 16, 16, 16, 80, 20,  4,  5,				// Ghost Entry 1 in [12 : 4]
		         5,  1, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,  4,  5,
		         5,  1, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,  4,  5,
		         5,  1, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,  4,  5,
		         5,  1, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,  4,  5,				
		         5,  1, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,  4,  5,
		         5,  1, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,  4,  5,
		         5,  1, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,  4,  5,
		         5,  1, 17, 80, 16, 16, 16, 16, 16, 16, 16, 16, 20,  4,  5,				// Ghost Entry 2 in [4 : 12]
		         5,  1, 25, 24, 24, 24, 24, 24, 24, 24, 24, 24, 28,  4,  5,
		         5,  9,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8, 12,  5,
		         9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 12
		    };
	 
	//Maze1 
	 private final short leveldata4[] = {
			 	 3, 50, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10,  6,
		         5, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16,  5,
		         5, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16,  5,
		         1,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  6, 16, 16,  5,
		         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4, 16, 16,  5,
		         1,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8, 12, 16, 16,  5,
		         5, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16,  5,
		         5, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16,  5,
		         5, 16, 16,  3, 10, 10, 10, 10, 10,  2, 10, 10, 10, 10,  4,
		         5, 16, 16,  5, 16, 16, 16, 16, 16,  5, 16, 16, 16, 16,  5,
		         5, 16, 16,  5, 16, 16, 16, 16, 16,  5, 16, 11, 10, 10,  4,
		         5, 16, 16, 13, 16, 16,  7, 16, 16, 13, 16, 16, 16, 16,  5,
		         5, 16, 16, 16, 16, 16,  5, 16, 16, 16, 16, 11, 10, 10,  4,
		         5, 16, 16, 16, 16, 16,  5, 16, 16, 16, 16, 16, 16, 84,  4,
		         9, 10, 10, 10, 10, 10,  8, 10, 10, 10, 10, 10, 10, 10, 12
		    };
	 
	 private final short leveldata5[] = {
	         3,  2,  6,  7,  3,  2, 10,  2, 10,  2,  6,  7,  3,  2,  6,
	         1, 16,  4,  1,  0,  4,  7,  5,  7,  1,  0,  4,  1, 16,  4,
	         9,  0, 12,  5,  1,  4,  5, 29,  5,  1,  4,  5,  9,  0, 12,
	        11,  8, 10, 12,  1,  4,  9, 10, 12,  1,  4,  9, 10,  8, 14,
	         3, 10, 10, 10,  0,  0, 10,  2, 10,  0,  0, 10, 10, 10,  6,
	         5, 27, 10,  6,  1,  4,  3,  0,  6,  1,  4,  3, 10, 30,  5,
	         1, 10, 10,  4,  1,  4,  1,  0,  4,  1,  4,  1, 10, 10,  4,
	         5, 27, 10,  4,  1,  4,  9, 72, 12,  1,  4,  1, 10, 30,  5,
	         1, 10, 10,  4,  1,  4,  3, 50,  6,  1,  4,  1, 10, 10,  4,
	         5, 27, 10, 12,  1,  4,  9,  0, 12,  1,  4,  9, 10, 30,  5,
	         9, 10, 10, 10,  0,  0, 10,  8, 10,  0,  0, 10, 10, 10, 12,
	        11,  2, 10,  6,  1,  4,  3, 10,  6,  1,  4,  3, 10,  2, 14,
	         3,  0,  6,  5,  1,  4,  5, 23,  5,  1,  4,  5,  3,  0,  6,
	         1, 16,  4,  1,  0,  4, 13,  5, 13,  1,  0,  4,  1, 16,  4,
	         9,  8, 12, 13,  9,  8, 10,  8,  10,  8, 12, 13,  9,  8, 12
		    };
	 
	 private final short test[] = {
		        19, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22,
		        17, 48, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
		        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
		        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
		        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
		        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
		        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
		        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
		        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
		        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
		        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
		        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
		        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
		        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 80, 20,
		        25, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 28
		    };
	 
	 private final short test2[] = {
		         3,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  6,
		         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,
		         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,
		         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,
		         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,
		         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,
		         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,
		         1,  0,  0,  0,  0, 48, 16, 16, 16, 16,  0,  0,  0,  0,  4,
		         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 16,  0,  4,
		         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 16,  0,  4,
		         1,  0,  0,  0,  0, 16, 16, 16, 16, 16,  0,  0,  0,  0,  4,
		         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,
		         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,
		         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,
		         9,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8, 12
		    };
	 
	 private final short TutoTroughMap[] = {
	         3,  2,  2,  2,  2,  2,  6, 21,  3,  2,  2,  2,  2,  2,  6,
	         1,  0,  0,  0,  0,  0,  4, 21,  1,  0,  0,  0,  0,  0,  4,
	         1,  0,  0,  0,  0,  0,  4, 13,  1,  0,  0,  0,  0,  0,  4,
	         1,  0,  0,  0,  0,  0,  0,  2,  0,  0,  0,  0,  0,  0,  4,
	         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,
	         1,  0,  0,  0,  0,  0,  0, 16,  0,  0,  0,  0,  0,  0,  4,
	         9,  8,  8,  0,  0,  0,  0, 16,  0,  0,  0,  0,  8,  8, 12,
	        26, 26, 14,  1,  0, 48, 16, 16, 16, 16,  0,  0, 10, 26, 26,
	         3,  2,  2,  0,  0,  0,  0, 16,  0,  0,  0,  0,  2,  2,  6,
	         1,  0,  0,  0,  0,  0,  0, 16,  0,  0,  0,  0,  0,  0,  4,
	         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,
	         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,
	         1,  0,  0,  0,  0,  0,  4,  5,  1,  0,  0,  0,  0,  0,  4,
	         1,  0,  0,  0,  0,  0,  4, 21,  1,  0,  0,  0,  0,  0,  4,
	         9,  8,  8,  8,  8,  8, 12, 21,  9,  8,  8,  8,  8,  8, 12
	    };
	 
	 private final short Jumptuto[] = {
	         3,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  6,  3,  2,  6,
	         1,  0,  0,  8,  8,  8,  8,  8,  8,  8,  8, 12,  1,  0,  4,
	         1,  0,  4, 59, 10, 10, 10, 10, 10, 10, 10, 78,  1,  0,  4,
	         1,  0,  0,  2,  2,  2,  2,  2,  2,  2,  2,  6,  9,  0, 12,
	         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  6,  5,  7,
	         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,  5,  5,
	         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4, 13,  5,
	         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4, 13,  5,				// Must add a refill dash in 2nd case
	         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4, 79,  5,				// Block a Ghost in a tile to prove you can dash 2 tile long ( Multi Ghsot Location)
	         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4, 13,  5,
	         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,  5,  5,
	         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4, 29,  5,
	         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,
	         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,
	         9,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8, 12
	    };
	
	
	public Maze(int pnumlevel){

		this.numlevel = pnumlevel;
		this.setMap(numlevel);		
		System.out.println("Level "+ this.numlevel + " : " + this.name + "\t with "+ this .nbrGhost+" ghosts");
		
		
	}

	public int getNbrGhost() {
		return this.nbrGhost;
	}
	

	public short[] getMap() {
		return this.map;
	}

	public String getName() {
		return this.name;
	}

	public void setMap(int pnumlevel) {
    	switch(pnumlevel){
    	case 1 : this.map = leveldata1;
    	 		 this.name = "Level 1";
    	 		 this.nbrGhost = 4;
    	 		 this.dashlevel=0;
    			break ;		
    	
    	case 2 : this.map = leveldata2;
    			 this.name = "Classic";
    			 this.nbrGhost = 4;
    			 this.dashlevel=0;
				break ;	
    	
    	case 3 : this.map = leveldata3;
    			 this.name = "Arena";
    			 this.nbrGhost = 2;
    			 this.dashlevel=2;
				break ;	
    	
    	case 4 : this.map = leveldata4;
    	 		 this.name = "Don't Fall";
    	 		 this.nbrGhost = 0;
    	 		 this.dashlevel=1;
				break ;	
				
    	case 5 : this.map = leveldata5;
		 		 this.name = "The Box";
		 		 this.nbrGhost = 4;
		 		this.dashlevel=0;
		 		 break ;
		 		 
    	case 6 : this.map = Jumptuto;
		 		 this.name = "Press Space to Dash";
		 		 this.nbrGhost = 2;
		 		 this.dashlevel=5;
		 		 break ;
		 		 
    	case 7 : this.map = TutoTroughMap;
    			 this.name = "Through Map Tutorial";
    			 this.nbrGhost = 0;
    			 this.dashlevel=0;
    			 break ;
    			 
    	default : this.map = test2;
		 		  this.name = "Test";
		 		  this.nbrGhost = 0;
		 		  this.dashlevel=10;
				  this.bonusfixelist.add(new BonusCreator(2,2,1));
		 		  break ;
    	}
	}

	public int getDashlevel() {
		return dashlevel;
	}

	public void setDashlevel(int dashlevel) {
		this.dashlevel = dashlevel;
	}
	
	public ArrayList<BonusCreator> getBonusfixelist() {
		return bonusfixelist;
	}

	public void setBonusfixelist(ArrayList<BonusCreator> bonusfixelist) {
		this.bonusfixelist = bonusfixelist;
	}
	
}
