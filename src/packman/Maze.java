package packman;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/*
 * Maze contains the different level created
 * a Maze is a map 15*15 tile with each tile is 24 pixels by 24 pixels
 * with some dash given to the player
 * some bonus to placed and specialghost
 * with a name and an image to show on the menu select
 * the number of ghost in the maze and the entry point of pacman
 * 
 */
public class Maze {

    private final int blocksize = 24;
    private final int nrofblocks = 15;
	
	private int numlevel = 30;
	private int nbrGhost = 30 ;
	private String name = "Default";
    private short map[] = new short [nrofblocks*nrofblocks];
    private int dashlevel = 0;
	private ArrayList<BonusCreator> bonusfixelist = new ArrayList<BonusCreator>() ;
	private int entryPointX =0 , entryPointY = 0;
	private ArrayList<Ghost> specialghostlist = new ArrayList<Ghost>();
	@SuppressWarnings("unused")
	private Image imglevel ;
	private int levelmax = 12 ;
    
	/* 1 = Left Border
	 * 2 = Top Border
	 * 4 = Right Border
	 * 8 = Bottom Border
	 * 16 = Point
	 * 32 = EntreePoint for Player
	 * 64 = EntreePoint for Ghost
	 * 128 = Trap Killer
	 * 
	 *  private final short base[] = {
		        19, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22,
		        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,		// EntreePoint for Player [ 2 : 2 ]
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
		        17, 18, 16, 18, 16, 16, 20,  7, 17, 16, 16, 16, 16, 16, 20,
		        17, 16, 16, 16, 16, 16, 20,  5, 17, 16, 16, 16, 16, 24, 20,
		        25, 16, 16, 16, 24, 24, 28,  5, 25, 24, 24, 16, 20,  7, 21,
		         7, 17, 16, 20, 11, 10, 10,  0, 10, 10, 14, 17, 20,  5, 21,
		         5, 17, 16, 16, 18, 18, 22,  5, 19, 18, 18, 16, 20,  5, 21,
		         5, 17, 16, 16, 16, 16, 20,  5, 17, 16, 16, 16, 20,  5, 21,
		         5, 17, 16, 16, 16, 16, 20, 13, 17, 16, 16, 16, 20,  5, 21,
		         5, 17, 16, 16, 16, 16, 16, 18, 16, 16, 16, 16, 20,  5, 21,
		         5, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20, 13, 21,				// Player Entry in [12 : 12]
		         5, 25, 24, 24, 24, 24, 24, 24, 24, 24, 16, 16, 16, 18, 20,
		         9, 10, 10, 10, 10, 10, 10, 10, 10, 14, 25, 24, 24, 24, 28
		    };
	 
	 //Old School
	 private final short leveldata2[] = {
		        19, 26, 26, 18, 26, 26, 22,  5, 19, 26, 26, 18, 26, 26, 22,
		        21, 11, 14, 21,  3, 14, 21, 13, 21, 11,  6, 21, 11, 14, 21,
		        17, 18, 18, 20, 13, 19, 24, 26, 24, 22, 13, 17, 18, 18, 20,
		        17, 24, 16, 16, 26, 20,  3,  2,  6, 17, 26, 16, 16, 24, 20,
		        21,  7, 17, 28,  7, 21,  1, 64,  4, 21,  7, 25, 20,  7, 21,     		// Ghost Entry in [8 : 5]
		        21, 13, 21, 11,  4, 21,  9,  0, 12, 21,  1, 14, 21, 13, 21,
		        17, 18, 16, 22, 13, 17, 26, 24, 26, 20, 13, 19, 16, 18, 20,
		        17, 24, 16, 16, 26, 20, 11, 10, 14, 17, 26, 16, 16, 24, 20,
		        21,  7, 17, 20,  7, 17, 18, 26, 18, 20,  7, 17, 20,  7, 21,				// Player Entry in [7 : 7]
		        21,  5, 17, 20, 13, 17, 20,  7, 17, 20, 13, 17, 20,  5, 21,
		        21,  5, 25, 16, 18, 16, 28,  5, 25, 16, 18, 16, 28,  5, 21,
		        21,  9, 14, 17, 24, 20, 11,  8, 14, 17, 24, 20, 11, 12, 21,
		        17, 26, 26, 20,  7, 25, 18, 26, 18, 28,  7, 17, 26, 26, 20,
		        21, 11, 14, 21,  9, 14, 21,  7, 21, 11, 12, 21, 11, 14, 21,
		        25, 26, 26, 24, 26, 26, 28,  5, 25, 26, 26, 24, 26, 26, 28
		    };
	  
	 //Arena
	 private final short leveldata3[] = {
			     3, 10, 10, 10, 10, 10, 10,  0, 10, 10, 10, 10, 10, 10,  6,
		         5, 19,  2,  2,  2,  2,  2,  0,  2,  2,  2,  2,  2,  6,  5,				// Player Entry in [1 : 1]
		         5,  1, 19, 26, 18, 18, 18, 18, 18, 18, 18, 26, 22,  4,  5,
		         5,  1, 21, 47, 17, 16, 16, 16, 16, 16, 20, 47, 85,  4,  5,				// Ghost Entry 1 in [12 : 4]
		         5,  1, 17, 18, 16, 16, 16, 16, 16, 16, 16, 18, 20,  4,  5,
		         5,  1, 17, 16, 16, 16, 24, 24, 24, 16, 16, 16, 20,  4,  5,
		         5,  1, 17, 16, 16, 20, 35, 34, 38, 17, 16, 16, 20,  4,  5,
		         0,  0, 17, 16, 16, 20, 33, 32, 36, 17, 16, 16, 20,  0,  0,				
		         5,  1, 17, 16, 16, 20, 41, 40, 44, 17, 16, 16, 20,  4,  5,
		         5,  1, 17, 16, 16, 16, 18, 18, 18, 16, 16, 16, 20,  4,  5,
		         5,  1, 17, 24, 16, 16, 16, 16, 16, 16, 16, 24, 20,  4,  5,
		         5,  1, 85, 47, 17, 16, 16, 16, 16, 16, 20, 47, 21,  4,  5,				// Ghost Entry 2 in [4 : 12]
		         5,  1, 25, 26, 24, 24, 24, 24, 24, 24, 24, 26, 28,  4,  5,
		         5,  9,  8,  8,  8,  8,  8,  0,  8,  8,  8,  8,  8, 12,  5,
		         9, 10, 10, 10, 10, 10, 10,  0, 10, 10, 10, 10, 10, 10, 12
		    };
	 
	//Maze1 
	 private final short leveldata4[] = {
			 	 3, 18, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10,  6,			// Player Entry in [1 : 0]
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
		         5, 16, 16, 16, 16, 16,  5, 16, 16, 16, 16, 16, 16, 84,  5,
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
	         1, 10, 10,  4,  1,  4,  3, 18,  6,  1,  4,  1, 10, 10,  4,					// Player Entry in [7 : 8]
	         5, 27, 10, 12,  1,  4,  9,  0, 12,  1,  4,  9, 10, 30,  5,
	         9, 10, 10, 10,  0,  0, 10,  8, 10,  0,  0, 10, 10, 10, 12,
	        11,  2, 10,  6,  1,  4,  3, 10,  6,  1,  4,  3, 10,  2, 14,
	         3,  0,  6,  5,  1,  4,  5, 23,  5,  1,  4,  5,  3,  0,  6,
	         1, 16,  4,  1,  0,  4, 13,  5, 13,  1,  0,  4,  1, 16,  4,
	         9,  8, 12, 13,  9,  8, 10,  8,  10,  8, 12, 13,  9,  8, 12
		    };
	 
	 private final short test[] = {
		        19, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18,  5, 18, 22,
		        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 13, 16, 20,				// Player Entry in [1 : 1]
		        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
		        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
		        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
		        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
		        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
		        17, 16, 16, 16, 16, 16, 31, 16, 16, 16, 16, 16, 16, 16, 20,
		        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
		        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
		        10, 14, 16, 16, 16, 16, 16, 16, 24, 16, 16, 16, 11, 10, 10,
		        17, 16, 16, 16, 16, 16, 16, 20, 47, 17, 16, 16, 16, 16, 20,
		        17, 16, 16, 16, 16, 16, 16, 16, 18, 16, 80, 16, 16, 15, 20,
		        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16,  7, 16, 20,
		        25, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24,  5, 24, 28
		    };
	 
	 @SuppressWarnings("unused")
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
	 

		private final short lavaMaze1[] = {
		        43, 42, 42, 42, 42, 42, 46, 23, 43, 42, 42, 42, 42, 42, 46,
		        31, 19, 18, 26, 18, 26, 18, 24, 18, 26, 18, 26, 18, 22, 70,
		        19, 28, 21, 47, 21, 19, 20, 16, 17, 22, 21, 47, 21, 25, 22,
		        17, 18, 16, 26, 16, 24, 20, 23, 17, 24, 16, 26, 16, 18, 20,
		        17, 28, 21, 23, 21, 23, 21, 85, 21, 23, 21, 23, 21, 25, 20,
		        21, 27, 24, 28, 21, 25, 24, 24, 24, 28, 21, 25, 24, 30, 21,
		        25, 26, 26, 26, 24, 26, 26, 26, 26, 26, 24, 26, 26, 26, 28,
		        43, 42, 42, 42, 42, 42, 46, 15, 43, 42, 42, 42, 42, 42, 46,
		        19, 26, 26, 26, 18, 18, 26, 26, 26, 18, 18, 26, 26, 26, 22,
		        17, 26, 26, 22, 21, 25, 26, 82, 26, 28, 21, 19, 26, 26, 20,
		        21, 19, 26, 24, 16, 26, 30, 29, 27, 26, 16, 24, 26, 22, 21,
		        21, 25, 26, 22, 21, 35, 34, 34, 34, 38, 21, 19, 26, 28, 21,
		        21, 19, 30, 21, 21, 33, 32, 40, 32, 36, 21, 21, 27, 22, 21,
		        21, 25, 26, 28, 21, 33, 36, 16, 33, 36, 21, 25, 26, 28, 21,
		        25, 26, 90, 26, 28, 41, 40, 42, 40, 44, 25, 26, 90, 26, 28,
		    };
	 
		private final short Finishline[] = {
		         3,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  6,
		         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,
		         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,
		         1,  0,  0,  8,  8,  8,  8,  8,  8,  8,  0,  0,  0,  0,  4,
		         1,  0,  4, 27, 26, 26, 26, 26, 26, 22,  1,  0,  0,  0,  4,  				// Player Entry in [3 : 4]
		         1,  0,  0,  2,  2,  2,  2,  2,  6, 21,  1,  0,  0,  0,  4,
		         1,  0,  8,  8,  8,  8,  8,  8, 12, 21,  1,  0,  0,  0,  4,
		         1,  4, 27, 18, 26, 26, 26, 26, 26, 28,  1,  0,  0,  0,  4,
		         1,  0,  6, 21,  3,  2,  6,  3,  6,  3,  0,  0,  0,  0,  4,
		         1,  4, 19, 16, 22,  9, 12,  1,  4,  1,  0,  0,  0,  0,  4,
		         1,  4, 17,  0, 16, 10, 10,  8, 12,  1,  0,  0,  0,  0,  4,
		         1,  4, 25, 24, 28,  3,  2,  2,  2,  0,  0,  0,  0,  0,  4,
		         1,  0,  2,  2,  2,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,
		         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,
		         9,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8, 12
		    };
		
		 private final short LavaCircuit[] = {
		        32, 32, 32, 32, 23, 32, 32, 32, 32, 32,  7, 32, 32, 32, 32,
		        32, 75, 74, 74, 26, 26, 26, 26, 26, 26, 26, 26, 26, 22, 32,
		        32, 37, 32, 32, 32, 32, 32, 13, 32, 32, 32, 32, 32, 21, 32,
		        32, 37, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 21, 32,
		        32, 21, 32, 15, 32, 32, 32, 32, 32, 32, 32, 32, 11, 21, 32,
		        32, 21, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 21, 32,
		        32, 25, 26, 22, 32, 32, 32, 32, 32, 32, 32, 32, 32, 21, 32,
		        32, 32, 32, 21, 32, 15, 32, 15, 32, 15, 32, 32, 19, 28, 32,
		        32, 19, 26, 28, 32, 32, 32, 32, 32, 32, 32, 32, 21, 32, 32,
		        32, 21, 32, 32, 32, 32, 32, 32, 32, 15, 32, 15, 21,  7, 32,
		        32, 25, 22, 32, 32, 15, 32, 32, 32, 32, 32, 32, 25, 20, 32,
		        32, 32, 21, 32, 32, 32, 32, 32, 19, 26, 22, 32, 32, 21, 32,
		        32, 19, 24, 22, 32, 19, 22, 32, 21, 32, 25, 26, 26, 20, 32,
		        32, 13, 32, 25, 26, 28, 37, 32, 21, 32, 32, 32, 32, 13, 32,
		        32, 32, 32, 32, 32, 32, 25, 26, 28, 32, 32, 32, 32, 32, 32
		    };
		
		
	 
	 private final short TutoTroughMap[] = {
	         3,  2,  2,  2,  2,  2,  6, 21,  3,  2,  2,  2,  2,  2,  6,
	         1,  0,  0,  0,  0,  0,  4, 21,  1,  0,  0,  0,  0,  0,  4,
	         1,  0,  0,  0,  0,  0,  4, 13,  1,  0,  0,  0,  0,  0,  4,
	         1,  0,  0,  0,  0,  0,  0,  2,  0,  0,  0,  0,  0,  0,  4,
	         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,
	         1,  0,  0,  0,  0,  0,  0, 16,  0,  0,  0,  0,  0,  0,  4,
	         9,  8,  8,  0,  0,  0,  0, 16,  0,  0,  0,  0,  8,  8, 12,
	        26, 26, 14,  1,  0, 16, 16, 16, 16, 16,  0,  0, 10, 26, 26,						// Player Entry in [8 : 8]
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
	         1,  0,  0, 40, 40, 40, 40, 40, 40, 40, 40, 44,  1,  0,  4,
	         1,  0, 36, 27, 10, 10, 10, 10, 10, 10, 10, 78,  1, 16,  4,				// Player Entry in [3 : 2]
	         1,  8,  8, 42, 34, 34, 34, 34, 34, 34, 34, 40,  9,  0, 12,
	         5,  3, 10,  6,  9,  8,  8,  8,  8,  0,  0,  0,  6,  5,  7,
	         5, 69, 31,  5, 10, 10, 14, 79, 31,  1,  0,  0,  4,  5,  5,
	         5,  9, 10, 12,  3,  2,  2,  2,  2,  0,  0,  0,  4,  5,  5,
	         1,  2,  6,  7,  1,  0,  0,  8,  8,  8,  0,  0,  4, 13,  5,				// Must add a refill dash in 2nd case
	         1,  0,  4,  5,  1,  0,  4,  3, 90,  6,  1,  0,  4, 47,  5,				// Block a Ghost in a tile to prove you can dash 2 tile long
	         1,  0,  4,  5,  1,  0,  4,  5, 15,  5,  1,  0,  4,  7,  5,
	         1,  0, 12, 13,  9,  0,  4,  9, 10, 12,  1,  0,  4,  5,  5,
	         1,  4,  3, 10,  6,  9, 12,  5,  7,  5,  9,  8, 12,  5,  5,
	         1,  4, 69, 31,  5, 11, 10, 12,  5,  9, 10, 10, 10, 28,  5,
	         1,  4,  9, 10, 12,  3,  2,  2,  0,  2,  2,  2,  2,  2,  4,
	         9,  8, 10, 10, 10,  8,  8,  8,  8,  8,  8,  8,  8,  8, 12
	    };
	 
		private final short Linerunner[] = {
		         7, 23,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7, 23,  7,
		         5,  5,  5,  5,  5,  5,  5, 21,  5,  5,  5,  5,  5,  5, 21,
		         5,  5, 21,  5,  5,  5,  5,  5,  5, 21,  5,  5,  5,  5,  5,
		        21,  5,  5, 21,  5,  5, 21,  5,  5,  5,  5,  5,  5,  5,  5,
		         5,  5,  5,  5,  5,  5,  5,  5,  5,  5,  5,  5, 21,  5,  5,
		         5,  5,  5,  5, 21,  5,  5,  5, 21,  5, 21,  5,  5,  5,  5,
		         5, 21,  5,  5,  5,  5, 21,  5,  5,  5,  5,  5,  5,  5, 21,
		         5,  5,  5,  5,  5,  5,  5,  5,  5,  5,  5,  5,  5,  5,  5,
		         5,  5,  5,  5,  5, 21,  5,  5,  5, 21,  5,  5,  5, 21,  5,
		         5,  5,  5, 21,  5,  5,  5,  5,  5,  5,  5,  5,  5,  5,  5,
		         5, 21,  5,  5,  5,  5, 21,  5,  5,  5,  5, 21,  5,  5,  5,
		         5,  5,  5,  5,  5,  5,  5,  5, 21,  5,  5,  5,  5,  5, 21,
		         5,  5, 21,  5,  5,  5,  5,  5,  5,  5,  5,  5,  5,  5,  5,
		         5,  5,  5,  5,  5, 21,  5,  5,  5, 21,  5,  5, 21,  5,  5,
		        13, 13, 13, 29, 13, 13, 13, 13, 29, 13, 29, 13, 13, 13, 13,				// Player Entry in [9 : 14]
		    };
		
		 private final short expomaze[] = {
		         3,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  6,
		         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,
		         1,  0,  1,  0,  2,  0,  4,  0,  8,  0,  0,  0,  0,  0,  4,
		         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,
		         1,  0,  3,  0,  6,  0,  9,  0, 12,  0,  0,  0,  0,  0,  4,
		         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,
		         1,  0,  7,  0, 13,  0, 11,  0, 14,  0,  0,  0,  0,  0,  4,
		         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,
		         1,  0, 15,  0, 16,  0, 32,  0,  0,  0,  0,  0,  0,  0,  4,
		         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,
		         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,
		         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,
		         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,
		         1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 16,  4,
		         9,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8, 12
		    };
	
	/*
	 * load the maze with the number given by the parameter
	 */
	public Maze(int pnumlevel){

		this.numlevel = pnumlevel;
		this.setMap(numlevel);		
		//System.out.println("Level "+ this.numlevel + " : " + this.name + "\t with "+ this .nbrGhost+" ghosts");
		
		
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

	/*
	 * initialize the map 
	 */
	public void setMap(int pnumlevel) {
		getImglevel(pnumlevel);
    	switch(pnumlevel){
    	case 1 : this.map = leveldata1;
    	 		 this.name = "From tutorial";
    	 		 this.nbrGhost = 4;
    	 		 this.dashlevel=0;
    	 		 this.setEntryPointX(12);
    	 		 this.setEntryPointY(12);
				 this.bonusfixelist.add(new BonusCreator(2,2,6));
    			break ;		
    	
    	case 2 : this.map = leveldata2;
    			 this.name = "Classic";
    			 this.nbrGhost = 4;
    			 this.dashlevel=0;
				 this.bonusfixelist.add(new BonusCreator(2,3,6));
				 this.bonusfixelist.add(new BonusCreator(11,11,6));
    	 		 this.setEntryPointX(7);
    	 		 this.setEntryPointY(8);
				break ;	
    	
    	case 3 : this.map = leveldata3;
    			 this.name = "Arena";
    			 this.nbrGhost = 2;
    			 this.dashlevel=2;
    	 		 this.setEntryPointX(1);
    	 		 this.setEntryPointY(1);
    	 		 this.specialghostlist.add(new ChaserGhost(10*blocksize,10*blocksize,0));
				break ;	
    	
    	case 4 : this.map = leveldata4;
    	 		 this.name = "Don't Fall";
    	 		 this.nbrGhost = 0;
    	 		 this.dashlevel=1;
    	 		 this.setEntryPointX(1);
    	 		 this.setEntryPointY(0);
    	 		this.bonusfixelist.add(new BonusCreator(13,11,3));
				break ;	
				
    	case 5 : this.map = leveldata5;
		 		 this.name = "The Box";
		 		 this.nbrGhost = 4;
		 		 this.dashlevel=1;
    	 		 this.setEntryPointX(7);
    	 		 this.setEntryPointY(8);
				 this.bonusfixelist.add(new BonusCreator(7,6,6));
		 		 break ;
		 		 
    	case 6 : this.map = Jumptuto;
		 		 this.name = "Press Space to Dash";
		 		 this.nbrGhost = 5;
		 		 this.dashlevel=5;
    	 		 this.setEntryPointX(3);
    	 		 this.setEntryPointY(2);
		 		 this.bonusfixelist.add(new BonusCreator(4,2,4));
		 		 this.bonusfixelist.add(new BonusCreator(13,7,9));
		 		 this.bonusfixelist.add(new BonusCreator(13,1,5));
		 		 this.bonusfixelist.add(new BonusCreator(8,9,3));
		 		 this.bonusfixelist.add(new BonusCreator(1,5,8));
			 	 this.bonusfixelist.add(new BonusCreator(2,4,5));
			 	 this.bonusfixelist.add(new BonusCreator(12,2,5));
				 this.bonusfixelist.add(new BonusCreator(6,5,6));
				 this.bonusfixelist.add(new BonusCreator(8,5,10));
		 		 break ;
		 		 
    	case 7 : this.map = TutoTroughMap;
    			 this.name = "Through Map Tutorial";
    			 this.nbrGhost = 0;
    			 this.dashlevel=0;
    	 		 this.setEntryPointX(7);
    	 		 this.setEntryPointY(7);
    			 break ;
    			 
    	case 8 : this.map = Linerunner;
		 		 this.name = "Line Runner";
		 		 this.nbrGhost = 0;
		 		 this.dashlevel=3;
    	 		 this.setEntryPointX(8);
    	 		 this.setEntryPointY(14);
		 		 for(int bo=0;bo<nrofblocks;bo++){
		 			this.specialghostlist.add(new LivingArmor(bo*blocksize,0*blocksize,1));
		 		 }
    	 		 
		 		 for(int bo=0;bo<nrofblocks;bo++){
		 			 this.bonusfixelist.add(new BonusCreator(bo,14,5));
		 		 }
 		 		 break ;
 		 		 
    	case 9 : this.map = Finishline;
    			 this.name = "Finishing Line";
    			 this.nbrGhost = 0;
    			 this.dashlevel=0;
    	 		 this.setEntryPointX(3);
    	 		 this.setEntryPointY(4);
    			 this.bonusfixelist.add(new BonusCreator(3, 8,10));
    			 this.bonusfixelist.add(new BonusCreator(3,10,15));
    			 this.bonusfixelist.add(new BonusCreator(2,7,5));
    			 break ;	
    			 
    			
    	case 10 : this.map = lavaMaze1 ;
		 		  this.name = "Lava Maze";
				  this.nbrGhost = 5 ;
				  this.dashlevel= 2 ;
				  this.bonusfixelist.add(new BonusCreator( 7,13,10));
				  this.bonusfixelist.add(new BonusCreator( 3, 4, 5));
				  this.bonusfixelist.add(new BonusCreator(11, 4, 5));
				  this.bonusfixelist.add(new BonusCreator( 7,10, 5));
				  this.bonusfixelist.add(new BonusCreator( 5, 4, 2));
				  this.bonusfixelist.add(new BonusCreator( 9, 4, 2));
				  this.bonusfixelist.add(new BonusCreator( 7, 7, 6));
				  this.bonusfixelist.add(new BonusCreator(12,12, 9));
				  this.bonusfixelist.add(new BonusCreator( 2,12, 4));
				  this.bonusfixelist.add(new BonusCreator(14, 1, 4));
				  this.bonusfixelist.add(new BonusCreator( 7, 2, 3));
				  this.setEntryPointX(0);
				  this.setEntryPointY(1);   		
    			  break ;
    			
    	case 11 : this.map = LavaCircuit ;
				  this.name = "Lava Circuit";
				  this.nbrGhost = 3 ;
				  this.dashlevel= 5 ;
				  this.bonusfixelist.add(new BonusCreator(  3,  4, 10));
				  this.bonusfixelist.add(new BonusCreator(  7,  2,  5));				  
				  this.bonusfixelist.add(new BonusCreator( 12,  4,  5));
				  this.bonusfixelist.add(new BonusCreator( 13,  9,  5));
				  this.bonusfixelist.add(new BonusCreator( 10,  0,  4));
				  this.bonusfixelist.add(new BonusCreator(  1, 13,  4));
				  this.bonusfixelist.add(new BonusCreator(  5, 10,  7));
				  this.bonusfixelist.add(new BonusCreator( 13, 13,  9));
				  this.bonusfixelist.add(new BonusCreator( 11,  9,  1));
				  this.bonusfixelist.add(new BonusCreator(  9,  9,  1));
				  this.bonusfixelist.add(new BonusCreator(  9,  7,  2));
				  this.bonusfixelist.add(new BonusCreator(  7,  7,  2));
				  this.bonusfixelist.add(new BonusCreator(  5,  7,  3));
				  this.setEntryPointX(4);
				  this.setEntryPointY(0);	
				  break ;
    			 
    	default : this.map = expomaze;
		 		  this.name = "Test";
		 		  this.nbrGhost = 0;
		 		  this.dashlevel=5;
	    	 	  this.setEntryPointX(1);
	    	 	  this.setEntryPointY(1);
				  //this.bonusfixelist.add(new BonusCreator(5,5,6));
				  //this.specialghostlist.add(new LivingArmor(12*blocksize,0*blocksize,1));
				  //this.specialghostlist.add(new LivingArmor(12*blocksize,10*blocksize,0));
				  //this.specialghostlist.add(new LivingArmor(13*blocksize,12*blocksize,0));
		 		  break ;
    		
    		
    		
    		/*this.map = test2;
		 		  this.name = "Test";
		 		  this.nbrGhost = 0;
		 		  this.dashlevel=10;
		 		 this.setEntryPointX(1);
    	 		 this.setEntryPointY(1);
				  this.bonusfixelist.add(new BonusCreator(2,2,1));
		 		  break ;*/
    	}
	}

	public int getDashlevel() {
		return dashlevel;
	}

	public void setDashlevel(int dashlevel) {
		this.dashlevel = dashlevel;
	}
	
	public int getEntryPointX() {
		return entryPointX;
	}

	public void setEntryPointX(int entryPointX) {
		this.entryPointX = entryPointX;
	}

	public ArrayList<BonusCreator> getBonusfixelist() {
		return bonusfixelist;
	}

	public void setBonusfixelist(ArrayList<BonusCreator> bonusfixelist) {
		this.bonusfixelist = bonusfixelist;
	}

	public int getEntryPointY() {
		return entryPointY;
	}

	public void setEntryPointY(int entryPointY) {
		this.entryPointY = entryPointY;
	}

	public ArrayList<Ghost> getSpecialghostlist() {
		return specialghostlist;
	}

	public void setSpecialghostlist(ArrayList<Ghost> specialghostlist) {
		this.specialghostlist = specialghostlist;
	}

	/*
	 * load the image for the level but if the level don't exist
	 * put the test image
	 */
	public Image getImglevel(int pnumlevel) {
		if(pnumlevel >=levelmax || pnumlevel <=0){
			return new ImageIcon(this.getClass().getResource("/test.png")).getImage() ;
		}
		return new ImageIcon(this.getClass().getResource("/level"+pnumlevel+".png")).getImage() ;
	}

	public void setImglevel(Image imglevel) {
		this.imglevel = imglevel;
	}
	
}
