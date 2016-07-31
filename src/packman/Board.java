package packman;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener {

    private Dimension d;
    private final Font smallfont = new Font("Helvetica", Font.BOLD, 14);

    private Image ii;
    private final Color dotcolor = new Color(192, 192, 0);
    private Color mazecolor;

    private boolean ingame = false;
    private boolean dying = false;

    private final static int blocksize = 24;
    private final static int nrofblocks = 15;
    private final int scrsize = nrofblocks * blocksize;
    private final int pacanimdelay = 2;
    private final int pacmananimcount = 4;
    private final int pacmanspeed = 6;


    private int pacanimcount = pacanimdelay;
    private int pacanimdir = 1;
    private int pacmananimpos = 0;
    private int nrofghosts = 6;
    private int entryGhostX[]=new int[nrofblocks] ;
    private int entryGhostY[]=new int[nrofblocks] ;
    private int tmpstateghost;
    
    private static int pacsleft;
	private static int score; 
    private ArrayList<Ghost> ghostlist;
    private ArrayList<Ghost> ghostlisttmp ;
    private int nbrpopghost;

    
    private Image ghostimage[][][] ;
    @SuppressWarnings("unused")
	private Image ghost , ghostred , ghostpink, ghostturquoise, ghostyellow , dashimg;
    private Image ghostdeadup, ghostdeadleft, ghostdeaddown, ghostdeadright;
    private Image ghostweakblue1,ghostweakblue2,ghostweakwhite1,ghostweakwhite2;
    
    private Image pacman1, pacman2up, pacman2left, pacman2right, pacman2down;
    private Image pacman3up, pacman3down, pacman3left, pacman3right;
    private Image pacman4up, pacman4down, pacman4left, pacman4right;
    private Image pacdeath[] ;

    private static int pacmanx;
	private static int pacmany;
	private static int pacmandx;
	private static int pacmandy;
    private int reqdx, reqdy, viewdx, viewdy;
    private int entryPacmanX, entryPacmanY;
    private static int jumpcount = 0;
	private int lengthjump = 1;
	private boolean deathanim= false ;
	private int deathdelay = 0;
    
    private int numlevel = 1;
    private short leveldata[] = new short [nrofblocks*nrofblocks];
    private Image lava = new ImageIcon(this.getClass().getResource("/Lava.png")).getImage(); ;
    private String namelevel;

    @SuppressWarnings("unused")
	private int currentspeed = 3;
    private static short[] screendata;
    private Timer timer;
    
    private ArrayList<Color> swapColorList;			//Color(Red,Green,Blue)
    private Color orange = new Color(205, 100, 5);
    private Color green = new Color(5, 100, 5);
    private Color flashygreen = new Color(0, 255, 0);
    private int numcolor;
    
    private boolean boodrawbon = false;
    private boolean boobonusexist = false;
    private boolean eatenbonuscore = false;
    private int ptseatingbonus ;
    private int ptseatinglife ;
    
	private int eateninX ;
	private int eateninY ;
	private Image eaten ;
	private int eatendelay;
    
    private ArrayList<BonusCreator> bonuslist;
	private ArrayList<BonusCreator> currentbonusfixelist;
    
    private BonusCreator bonscore ;
	private BonusCreator bon;
	

    public Board(int level) {

    	this.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorRemoved(AncestorEvent pEvent) {
            }

            @Override
            public void ancestorMoved(AncestorEvent pEvent) {
            }

            @Override
            public void ancestorAdded(AncestorEvent pEvent) {
                // TextField is added to its parent => request focus in Event Dispatch Thread
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        requestFocusInWindow();
                    }
                });
            }
        });
    	
        loadImages();
        this.numlevel = level ;
        initVariables();
        
        addKeyListener(new TAdapter());

        setFocusable(true);

        setBackground(Color.black);
        setDoubleBuffered(true);
    }

    private void initVariables() {

    	//backsound = new Sound("pacmanintro.wav");
    	
        screendata = new short[nrofblocks * nrofblocks];
        Options color = new Options();
        mazecolor = color.getColorboard();
        d = new Dimension(400, 400);
        ghostlist = new ArrayList<Ghost>();
        ghostlisttmp = new ArrayList<Ghost>();
              
        tmpstateghost = 0 ;
        //numlevel = 12;
        
        currentbonusfixelist = new ArrayList<BonusCreator>();
        bonuslist = new ArrayList<BonusCreator>();
        bon = new BonusCreator();
        initColorBoard();
        
        timer = new Timer(40, this);
        timer.start();
    }

    @Override
    public void addNotify() {
        super.addNotify();

        initGame();
    }

    private void doAnim() {

        pacanimcount--;

        if (pacanimcount <= 0) {
            pacanimcount = pacanimdelay;
            pacmananimpos = pacmananimpos + pacanimdir;

            if (pacmananimpos == (pacmananimcount - 1) || pacmananimpos == 0) {
                pacanimdir = -pacanimdir;
            }
        }
    }

    private void playGame(Graphics2D g2d) {

        if (dying) {
            death();

        } else if(deathanim){		           
            	drawDeathAnim( g2d) ;
        } else{


            movePacman();
            drawPacman(g2d);
            moveGhosts(g2d);
            checkMaze();
        }
    }

    private void showIntroScreen(Graphics2D g2d) {

        g2d.setColor(new Color(0, 32, 48));
        g2d.fillRect(50, scrsize / 2 - 30, scrsize - 100, 50);
        g2d.setColor(Color.white);
        g2d.drawRect(50, scrsize / 2 - 30, scrsize - 100, 50);

        String s = "Press s to start.";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g2d.setColor(Color.white);
        g2d.setFont(small);
        g2d.drawString(s, (scrsize - metr.stringWidth(s)) / 2, scrsize / 2);
    }

    private void drawScore(Graphics2D g) {

        int i;
        String s;

        g.setFont(smallfont);
        g.setColor(new Color(96, 128, 255));
        s = "Score: " + score;
        g.drawString(s, scrsize / 2 + 96, scrsize + 16);
        
        s = "Level "+ numlevel +" : " + namelevel;
        g.drawString(s, (scrsize / 2) - 50,16);

        if(pacsleft<=5){
	        for (i = 0; i < pacsleft; i++) {
	            g.drawImage(pacman3left, i * 28 + 8, scrsize + 1, this);
	        }
        }else{
        	g.drawImage(pacman3left,8, scrsize + 1, this);
        	g.drawString("x " + pacsleft,28 + 8, scrsize + 14);
        }
        
        if(jumpcount <=0){
        	g.drawImage(dashimg,168, scrsize + 1, this);
        	g.setColor(Color.red);
        	g.drawLine(168, scrsize + 1, 180, scrsize + 15);
        	g.drawLine(168, scrsize + 15, 180, scrsize + 1);
        }else if(jumpcount<=4){
	        for (i = 0; i < jumpcount; i++) {
	            g.drawImage(dashimg,160 + i * 28 + 8, scrsize + 1, this);
	        }
        }else{
        	g.drawImage(dashimg, 160, scrsize + 1, this);
        	g.drawString("x " + jumpcount,160 + 28 + 8, scrsize + 14);
        }
    }

    private void checkMaze() {

        short i = 0;
        boolean finished = true;

        while (i < nrofblocks * nrofblocks && finished) {

            if ((screendata[i] & 16) != 0) {
                finished = false;
            }

            i++;
        }

        if (finished) {
        	nextLevel();
        }
    }
    
    private void nextLevel(){
    	lengthjump = 1;
    	currentbonusfixelist.removeAll(currentbonusfixelist);
    	ghostlist.removeAll(ghostlist);
    	ghostlisttmp.removeAll(ghostlist);
    	bonuslist.removeAll(bonuslist);
        numlevel ++ ;
        initLevel();
    }

    private void death() {
    	Sound.play("pacmandeath.wav");
    	pacsleft--;
    	dying = false ;
        deathanim = true ;
    }

    private void moveGhosts(Graphics2D g2d) {

        for(Ghost currentghost : ghostlist){
        	currentghost.movement();

        	//Check Collision
            if (pacmanx > (currentghost.getPosX()- 12) && pacmanx < (currentghost.getPosX() + 12)
                    && pacmany > (currentghost.getPosY() - 12) && pacmany < (currentghost.getPosY() + 12)
                    && ingame) {
            	
            	if(currentghost.getState()=="alive"){
            		dying = true;
            	}
            	
            	if(currentghost.getState()=="weak"){
            		currentghost.setState("dead");
            		Sound.play("pacman_eatghost.wav");
            		
            		eaten = currentghost.getReward();
            		eateninX=currentghost.getPosX()/blocksize;
            		eateninY=currentghost.getPosY()/blocksize;
            		drawBonuscore(g2d);
            	}
            	
            	
            	if(currentghost.getState()=="dead"){   	}
            }
            
            if(currentghost.getState()=="alive"){
            	currentghost.setImg(ghostimage[currentghost.getType()][currentghost.getDirection()][currentghost.getPosframe()%2]);
            }
            
            else if(currentghost.getState()=="weak"){
	    		switch(currentghost.getPosframe()% currentghost.weakState()){
	    		case 0 :
	    			currentghost.setImg(ghostweakblue1);
	    			break;
	    		case 1 :
	    			currentghost.setImg(ghostweakblue2);
	    			break;
	    		case 2 :	
	    			currentghost.setImg(ghostweakwhite1);
	    			break;
	    		default :
	    			currentghost.setImg(ghostweakwhite2);
	    			break;   		
	    		}
            }
            
            else if(currentghost.getState()=="dead"){
            		if(currentghost.getGhostdx()<0){ 
        	    		currentghost.setImg(ghostdeadleft);
        	    	}
            		if(currentghost.getGhostdx()>0){ 
            			currentghost.setImg(ghostdeadright);
            		}
            		if(currentghost.getGhostdy()<0){ 
            			currentghost.setImg(ghostdeadup);
            		}
            		if(currentghost.getGhostdy()>0){ 
            			currentghost.setImg(ghostdeaddown);
            		}
            		else{currentghost.setImg(ghostdeaddown);}
            	}
            
            
        	drawGhost2(g2d,currentghost.getPosX()+1,currentghost.getPosY(),currentghost.getImg());
        }
    }

    private void drawGhost2(Graphics2D g2d, int x, int y, Image ghost) {
        g2d.drawImage(ghost, x, y, this);
    }
    
    private void movePacman() {

        int pos;
        short ch;

        if (reqdx == -pacmandx && reqdy == -pacmandy) {
            pacmandx = reqdx;
            pacmandy = reqdy;
            viewdx = pacmandx;
            viewdy = pacmandy;
        }

        if (pacmanx % blocksize == 0 && pacmany % blocksize == 0) {
            pos = pacmanx / blocksize + nrofblocks * (int) (pacmany / blocksize);
            ch = screendata[pos];

            if ((ch & 16) != 0) {
                screendata[pos] = (short) (ch & 15);
                Sound.play("pacman_chomp2.wav");
                score++;
                ptseatingbonus ++ ;
                ptseatinglife ++;
               // System.out.println("["+pacmanx +":"+ pacmany+"]");
                
                if(boobonusexist){

                	boolean modif = false;
                	for(BonusCreator bonus : bonuslist){
                	
                		if((bonus.getPosX()*blocksize ==  pacmanx ) &&  (bonus.getPosY()*blocksize==  pacmany )){
                			
                			if(bonus.getName()=="lifeup"){
                				addlife();
                				}
                			
                			else if(bonus.getName()=="jumprefill"){
                				System.out.println("+5 dash");
                				addDash(5);
                				}
                			else if(bonus.getName()=="ghosteater"){
                				Sound.play("ghostbusterbonus.wav");
                				System.out.println("you can eat ghost");
                                for(Ghost currentghost : ghostlist){
                                	if(currentghost.getState()=="alive"){
                                		currentghost.setState("weak");
                                	}
                                }
            				}
                			else{
                				score += bonus.getBonus_score();
                				Sound.play("pacman_eatfruit.wav");
                			}
                			/*
                			else if(bonus.getName()=="jumpupgrade"){
                				lengthjump = lengthjump+1;
	                				if(lengthjump>=2){
	                					lengthjump=2;
	                				}
                				System.out.println("Dash Upgraded : "+ lengthjump);
                				}
                			
                			else if(bonus.getName()=="jumpdowngrade"){
                				lengthjump = lengthjump-1;
	                				if(lengthjump<=1){
	                					lengthjump=1;
	                				}
                				System.out.println("Dash Downgraded : "+ lengthjump);
                				}

                			*/
                			bon = bonus;
                			modif = true;
                		}
                    }
                	
                	if(modif)
                		eatenbonuscore = true;
                	eateninX = bon.getPosX();
                	eateninY = bon.getPosY();
                	eaten = bon.getImgScore();
                		bonuslist.remove(bon);
                	
                }

                
                if (ptseatinglife == 100 ){ 
                	addlife(); 
                }

                if(ptseatingbonus == 50){		// 50 ingame but 5 for test
                    ptseatingbonus = 0;
                	boobonusexist = false;
                	checkMaze();				//prevent blocking game if the number of pts to eat in the map is a multiple of the ptseatingbonus
                	addBonus(); 
                }
                
            }
            if ((ch & 32) != 0) {
            	dying = true ;				// kill by Lava
            }

            if (reqdx != 0 || reqdy != 0) {
                if (!((reqdx == -1 && reqdy == 0 && (ch & 1) != 0)
                        || (reqdx == 1 && reqdy == 0 && (ch & 4) != 0)
                        || (reqdx == 0 && reqdy == -1 && (ch & 2) != 0)
                        || (reqdx == 0 && reqdy == 1 && (ch & 8) != 0))) {
                    pacmandx = reqdx;
                    pacmandy = reqdy;
                    viewdx = pacmandx;
                    viewdy = pacmandy;
                }
            }

            // Check for standstill
            if ((pacmandx == -1 && pacmandy == 0 && (ch & 1) != 0)
                    || (pacmandx == 1 && pacmandy == 0 && (ch & 4) != 0)
                    || (pacmandx == 0 && pacmandy == -1 && (ch & 2) != 0)
                    || (pacmandx == 0 && pacmandy == 1 && (ch & 8) != 0)) {
                pacmandx = 0;
                pacmandy = 0;
            }
        }
        
        if(!currentbonusfixelist.isEmpty()){					// Eat Fix Bonus
        	boolean modif = false;
        	boolean finish = false ;
        	for(BonusCreator bonus : currentbonusfixelist){
        	
        		if((bonus.getPosX()*blocksize ==  pacmanx ) &&  (bonus.getPosY()*blocksize==  pacmany )){
        			if(bonus.getName()=="lifeup"){
        				addlife();
        				}
        			else if(bonus.getName()=="jumprefill"){
        				System.out.println("+5 dash");
        				addDash(5);
        				}
        			else if(bonus.getName()=="jumpupgrade"){
        				Sound.play("dashsound2.wav");
        				lengthjump = lengthjump+1;
        				if(lengthjump>2){
        					lengthjump=2;
        				}
        				System.out.println("Dash Upgraded : "+ lengthjump);
        				}
        			else if(bonus.getName()=="jumpdowngrade"){
        				Sound.play("pacman_downgradedash.wav");
        				lengthjump = lengthjump-1;
            				if(lengthjump<1){
            					lengthjump=1;
            				}
        				System.out.println("Dash Downgraded : "+ lengthjump);
        				}	
        			else if(bonus.getName()=="ghosteater"){
        				Sound.play("ghostbusterbonus.wav");
        				System.out.println("you can eat ghost");
                        for(Ghost currentghost : ghostlist){
                        	if(currentghost.getState()=="alive"){
                        		currentghost.setState("weak");
                        	}
                        }
    				}
        			else if(bonus.getName()=="finishline"){	// to avoid java.util.ConcurrentModificationException for reset         				
        				finish = true ;						//the current bonus list while working on it, move the call to nextLevel ouside the loop 
        				}
        			else{
        				score += bonus.getBonus_score();
        				Sound.play("pacman_eatfruit.wav");
        			}
        			
        			bon = bonus;
        			modif = true;
        		}
            }
        	
        	if(modif){
        		eatenbonuscore = true;
	        	eateninX = bon.getPosX();
	        	eateninY = bon.getPosY();
	        	eaten = bon.getImgScore();
	        	if(!currentbonusfixelist.isEmpty()){
	        		currentbonusfixelist.remove(bon);
	        	}
        	}
        	if(finish){
        		nextLevel();
        	}
        	
        }
        	
        
        pacmanx = pacmanx + pacmanspeed * pacmandx;				//Move through map
		if(pacmanx<0){pacmanx = 14*blocksize;}
		if(pacmanx>14*blocksize){pacmanx = 0*blocksize;}
		
        pacmany = pacmany + pacmanspeed * pacmandy;
		if(pacmany<0){pacmany = 14*blocksize;}
		if(pacmany>14*blocksize){pacmany = 0*blocksize;}
    }



	private void drawPacman(Graphics2D g2d) {

        if (viewdx == -1) {
            drawPacmanLeft(g2d);
        } else if (viewdx == 1) {
            drawPacmanRight(g2d);
        } else if (viewdy == -1) {
            drawPacmanUp(g2d);
        } else {
            drawPacmanDown(g2d);
        }
    }

    private void drawPacmanUp(Graphics2D g2d) {

        switch (pacmananimpos) {
            case 1:
                g2d.drawImage(pacman2up, pacmanx + 1, pacmany + 1, this);
                break;
            case 2:
                g2d.drawImage(pacman3up, pacmanx + 1, pacmany + 1, this);
                break;
            case 3:
                g2d.drawImage(pacman4up, pacmanx + 1, pacmany + 1, this);
                break;
            default:
                g2d.drawImage(pacman1, pacmanx + 1, pacmany + 1, this);
                break;
        }
    }

    private void drawPacmanDown(Graphics2D g2d) {

        switch (pacmananimpos) {
            case 1:
                g2d.drawImage(pacman2down, pacmanx + 1, pacmany + 1, this);
                break;
            case 2:
                g2d.drawImage(pacman3down, pacmanx + 1, pacmany + 1, this);
                break;
            case 3:
                g2d.drawImage(pacman4down, pacmanx + 1, pacmany + 1, this);
                break;
            default:
                g2d.drawImage(pacman1, pacmanx + 1, pacmany + 1, this);
                break;
        }
    }

    private void drawPacmanLeft(Graphics2D g2d) {

        switch (pacmananimpos) {
            case 1:
                g2d.drawImage(pacman2left, pacmanx + 1, pacmany + 1, this);
                break;
            case 2:
                g2d.drawImage(pacman3left, pacmanx + 1, pacmany + 1, this);
                break;
            case 3:
                g2d.drawImage(pacman4left, pacmanx + 1, pacmany + 1, this);
                break;
            default:
                g2d.drawImage(pacman1, pacmanx + 1, pacmany + 1, this);
                break;
        }
    }

    private void drawPacmanRight(Graphics2D g2d) {

        switch (pacmananimpos) {
            case 1:
                g2d.drawImage(pacman2right, pacmanx + 1, pacmany + 1, this);
                break;
            case 2:
                g2d.drawImage(pacman3right, pacmanx + 1, pacmany + 1, this);
                break;
            case 3:
                g2d.drawImage(pacman4right, pacmanx + 1, pacmany + 1, this);
                break;
            default:
                g2d.drawImage(pacman1, pacmanx + 1, pacmany + 1, this);
                break;
        }
    }

    private void drawMaze(Graphics2D g2d) {

        short i = 0;
        int x, y;

        for (y = 0; y < scrsize; y += blocksize) {
            for (x = 0; x < scrsize; x += blocksize) {

                g2d.setColor(mazecolor);
                g2d.setStroke(new BasicStroke(2));
                
                if ((screendata[i] & 32) != 0) { 		//draw the lava under the wall 
                	
                	g2d.drawImage(lava, x, y, this);
                }
                

                if ((screendata[i] & 1) != 0) { 
                    g2d.drawLine(x, y, x, y + blocksize - 1);
                }

                if ((screendata[i] & 2) != 0) { 
                    g2d.drawLine(x, y, x + blocksize - 1, y);
                }

                if ((screendata[i] & 4) != 0) { 
                    g2d.drawLine(x + blocksize - 1, y, x + blocksize - 1,
                            y + blocksize - 1);
                }

                if ((screendata[i] & 8) != 0) { 
                    g2d.drawLine(x, y + blocksize - 1, x + blocksize - 1,
                            y + blocksize - 1);
                }

                if ((screendata[i] & 16) != 0) { 
                    g2d.setColor(dotcolor);
                    g2d.fillRect(x + 11, y + 11, 2, 2);
                }
                


                i++;
            }
        }
    }
    
    private void drawBonus(Graphics2D g2d) {
        for(BonusCreator bonus : bonuslist){
        	g2d.drawImage(bonus.getImg(), bonus.getPosX()*blocksize,  bonus.getPosY()*blocksize, this);
        }
    }
    
    private void drawBonusFixe(Graphics2D g2d) {
    	
    	for(BonusCreator bonus : currentbonusfixelist){
        	g2d.drawImage(bonus.getImg(), bonus.getPosX()*blocksize +5 ,  bonus.getPosY()*blocksize + 3, this);
        }

    }
    
    private void drawBonuscore(Graphics2D g2d) {
    	eatendelay++;
    	
    	if (eatendelay <= 15){
	        	g2d.drawImage(eaten, eateninX*blocksize,  eateninY*blocksize - eatendelay, this);
    	}else{
    		eatendelay=0;
    		eatenbonuscore = false ;
    	}
        
    }
    
    private void drawDeathAnim(Graphics2D g2d) {
    	deathdelay++;
    	if (deathdelay <= 20){
	        	g2d.drawImage(pacdeath[deathdelay/2], pacmanx, pacmany, this);
    	}else{
    		deathdelay=0;
    		deathanim  = false ;   		
            if (pacsleft == 0) {
                ingame = false;
            }            
            continueLevel();
    	}        
    }
    
    

    private void initGame() { 	
    	//backsound.loop();
    	jumpcount = 0;
        pacsleft = 3;
        score = 0;
        initLevel();
        currentspeed = 3;
    }

    private void initLevel() {

        int i;
        nbrpopghost = 0 ;
        ptseatingbonus = 0;
        
        if(ingame){
	    	if(numlevel == 1){
	    		Sound.play("pacman_beginning.wav");
	    	}
	    	else {
	    		Sound.play("pacman_intermission.wav");
	    	}
        }

        Maze currentlevel = new Maze(numlevel);
        System.out.println(currentlevel.getName());
        lengthjump = 1;
        leveldata = currentlevel.getMap();
        nrofghosts = currentlevel.getNbrGhost();
        namelevel = currentlevel.getName();
        entryPacmanX = currentlevel.getEntryPointX();
        entryPacmanY = currentlevel.getEntryPointY();
        addDash(currentlevel.getDashlevel());
        currentbonusfixelist = currentlevel.getBonusfixelist();
        ghostlisttmp = currentlevel.getSpecialghostlist();
        
        boobonusexist = false ;
        bonuslist.removeAll(bonuslist);
        
        for (i = 0; i < nrofblocks * nrofblocks; i++) {
            screendata[i] = leveldata[i];
            if((leveldata[i] & 64) != 0 ){			// Get Every Location for poping Ghost and have the number of Poping
            	entryGhostX[nbrpopghost] = i % nrofblocks ;
            	entryGhostY[nbrpopghost] = i / nrofblocks ;
            	
            	nbrpopghost++;
            }            
        }
        continueLevel();
    }

    private void continueLevel() {

        short i;
        int locpop = 0;
        int type = 0 ;
        

        
        // Pop the Special Ghost
        ghostlist.removeAll(ghostlist);
        getSpecialghostlist();
        for(Ghost tmpghost : ghostlisttmp){			
        	ghostlist.add(tmpghost);
        }
        
        //Pop normal Ghost
        for (i = 0; i < nrofghosts; i++) {
       	
        	ghostlist.add(new Ghost(entryGhostX[locpop]* blocksize,entryGhostY[locpop]* blocksize, type));
        	
            type ++;
            locpop ++ ;
        	
            if(type >= 4){ type = 0;}
            if(locpop>=nbrpopghost){ locpop = 0;} 
                       
        }
        // TODO new Ghost
        //ghostlist.add(new PhaseGhost(entryGhostX[locpop]* blocksize,entryGhostY[locpop]* blocksize, type));
        //ghostlist.add(new ChaserGhost(entryGhostX[locpop]* blocksize,entryGhostY[locpop]* blocksize, type));
        //ghostlist.add(new EscapeGhost(entryGhostX[locpop]* blocksize,entryGhostY[locpop]* blocksize, type));
        //ghostlist.add(new BlockGhost(entryGhostX[locpop]* blocksize,entryGhostY[locpop]* blocksize, type));
        //ghostlist.add(new PhaseChaserGhost(entryGhostX[locpop]* blocksize,entryGhostY[locpop]* blocksize, type));
        //ghostlist.add(new PhaseEscapeGhost(entryGhostX[locpop]* blocksize,entryGhostY[locpop]* blocksize, type));
        //ghostlist.add(new LivingArmor(4* blocksize,4* blocksize, 0));
        //ghostlist.add(new LivingArmor(12* blocksize,12* blocksize, 1));
        

        pacmanx = entryPacmanX * blocksize;
        pacmany = entryPacmanY * blocksize;
        pacmandx = 0;
        pacmandy = 0;
        reqdx = 0;
        reqdy = 0;
        viewdx = -1;
        viewdy = 0;
        dying = false;
    }

    private void loadImages() {
    	
    	ghostimage=new Image [4][4][2];

    	// LOAD RED GHOST SPRITE
    	ghostred = new ImageIcon(this.getClass().getResource("/hotchilipepper.png")).getImage();
    	
    	ghostimage[0][0][0]=new ImageIcon(this.getClass().getResource("/redup1.png")).getImage();
    	ghostimage[0][0][1]=new ImageIcon(this.getClass().getResource("/redup2.png")).getImage();
    	ghostimage[0][1][0]=new ImageIcon(this.getClass().getResource("/reddown1.png")).getImage();
    	ghostimage[0][1][1]=new ImageIcon(this.getClass().getResource("/reddown2.png")).getImage();
    	ghostimage[0][2][0]= new ImageIcon(this.getClass().getResource("/redright1.png")).getImage();
    	ghostimage[0][2][1]= new ImageIcon(this.getClass().getResource("/redright2.png")).getImage();
    	ghostimage[0][3][0] = new ImageIcon(this.getClass().getResource("/redleft1.png")).getImage();
    	ghostimage[0][3][1] = new ImageIcon(this.getClass().getResource("/redleft2.png")).getImage();
    	
    	
    	
    	// LOAD PINK GHOST SPRITE
    	ghostpink = new ImageIcon(this.getClass().getResource("/floyd.png")).getImage();
    	
    	ghostimage[1][0][0]=new ImageIcon(this.getClass().getResource("/pinkup1.png")).getImage();
    	ghostimage[1][0][1]=new ImageIcon(this.getClass().getResource("/pinkup2.png")).getImage();
    	ghostimage[1][1][0]=new ImageIcon(this.getClass().getResource("/pinkdown1.png")).getImage();
    	ghostimage[1][1][1]=new ImageIcon(this.getClass().getResource("/pinkdown2.png")).getImage();
    	ghostimage[1][2][0]= new ImageIcon(this.getClass().getResource("/pinkright1.png")).getImage();
    	ghostimage[1][2][1]= new ImageIcon(this.getClass().getResource("/pinkright2.png")).getImage();
    	ghostimage[1][3][0] = new ImageIcon(this.getClass().getResource("/pinkleft1.png")).getImage();
    	ghostimage[1][3][1] = new ImageIcon(this.getClass().getResource("/pinkleft2.png")).getImage();
    	
    	
    	// LOAD TURQUOISE GHOST SPRITE
    	ghostturquoise = new ImageIcon(this.getClass().getResource("/Greyskies.png")).getImage();
    	
    	ghostimage[2][0][0]=new ImageIcon(this.getClass().getResource("/turquoiseup1.png")).getImage();
    	ghostimage[2][0][1]=new ImageIcon(this.getClass().getResource("/turquoiseup2.png")).getImage();
    	ghostimage[2][1][0]=new ImageIcon(this.getClass().getResource("/turquoisedown1.png")).getImage();
    	ghostimage[2][1][1]=new ImageIcon(this.getClass().getResource("/turquoisedown2.png")).getImage();
    	ghostimage[2][2][0]= new ImageIcon(this.getClass().getResource("/turquoiseright1.png")).getImage();
    	ghostimage[2][2][1]= new ImageIcon(this.getClass().getResource("/turquoiseright2.png")).getImage();
    	ghostimage[2][3][0] = new ImageIcon(this.getClass().getResource("/turquoiseleft1.png")).getImage();
    	ghostimage[2][3][1] = new ImageIcon(this.getClass().getResource("/turquoiseleft2.png")).getImage();
    	
    	
    	// LOAD YELLOW GHOST SPRITE
    	ghostyellow = new ImageIcon(this.getClass().getResource("/submarine.png")).getImage(); 	
    	
    	ghostimage[3][0][0]=new ImageIcon(this.getClass().getResource("/yellowup1.png")).getImage();
    	ghostimage[3][0][1]=new ImageIcon(this.getClass().getResource("/yellowup2.png")).getImage();
    	ghostimage[3][1][0]=new ImageIcon(this.getClass().getResource("/yellowdown1.png")).getImage();
    	ghostimage[3][1][1]=new ImageIcon(this.getClass().getResource("/yellowdown2.png")).getImage();
    	ghostimage[3][2][0]= new ImageIcon(this.getClass().getResource("/yellowright1.png")).getImage();
    	ghostimage[3][2][1]= new ImageIcon(this.getClass().getResource("/yellowright2.png")).getImage();
    	ghostimage[3][3][0] = new ImageIcon(this.getClass().getResource("/yellowleft1.png")).getImage();
    	ghostimage[3][3][1] = new ImageIcon(this.getClass().getResource("/yellowleft2.png")).getImage();
    	
    	//LOAD DEADGHOST
    	ghostdeadup = new ImageIcon(this.getClass().getResource("/deadup.png")).getImage();
    	ghostdeaddown = new ImageIcon(this.getClass().getResource("/deaddown.png")).getImage();
    	ghostdeadleft = new ImageIcon(this.getClass().getResource("/deadleft.png")).getImage();
    	ghostdeadright = new ImageIcon(this.getClass().getResource("/deadright.png")).getImage();
    	
    	//LOAD WEAKGHOST 
    	ghostweakblue1 = new ImageIcon(this.getClass().getResource("/weakblue1.png")).getImage();
    	ghostweakblue2 = new ImageIcon(this.getClass().getResource("/weakblue2.png")).getImage();
    	ghostweakwhite1 = new ImageIcon(this.getClass().getResource("/weakwhite1.png")).getImage();
    	ghostweakwhite2 = new ImageIcon(this.getClass().getResource("/weakwhite2.png")).getImage();
    	  	
    	
    	// LOAD PACMAN BOY
    	pacman1 = new ImageIcon(this.getClass().getResource("/pacman.png")).getImage();
        pacman2up = new ImageIcon(this.getClass().getResource("/up1.png")).getImage();
        pacman3up = new ImageIcon(this.getClass().getResource("/up2.png")).getImage();
        pacman4up = new ImageIcon(this.getClass().getResource("/up3.png")).getImage();
        pacman2down = new ImageIcon(this.getClass().getResource("/down1.png")).getImage();
        pacman3down = new ImageIcon(this.getClass().getResource("/down2.png")).getImage();
        pacman4down = new ImageIcon(this.getClass().getResource("/down3.png")).getImage();
        pacman2left = new ImageIcon(this.getClass().getResource("/left1.png")).getImage();
        pacman3left = new ImageIcon(this.getClass().getResource("/left2.png")).getImage();
        pacman4left = new ImageIcon(this.getClass().getResource("/left3.png")).getImage();
        pacman2right = new ImageIcon(this.getClass().getResource("/right1.png")).getImage();
        pacman3right = new ImageIcon(this.getClass().getResource("/right2.png")).getImage();
        pacman4right = new ImageIcon(this.getClass().getResource("/right3.png")).getImage();
        
        //LOAD DEATH ANIM PACMAN
        pacdeath = new Image [11];
        
        pacdeath[0] = new ImageIcon(this.getClass().getResource("/death1.png")).getImage();
        pacdeath[1] = new ImageIcon(this.getClass().getResource("/death2.png")).getImage();
        pacdeath[2] = new ImageIcon(this.getClass().getResource("/death3.png")).getImage();
        pacdeath[3] = new ImageIcon(this.getClass().getResource("/death4.png")).getImage();
        pacdeath[4] = new ImageIcon(this.getClass().getResource("/death5.png")).getImage();
        pacdeath[5] = new ImageIcon(this.getClass().getResource("/death6.png")).getImage();
        pacdeath[6] = new ImageIcon(this.getClass().getResource("/death7.png")).getImage();
        pacdeath[7] = new ImageIcon(this.getClass().getResource("/death8.png")).getImage();
        pacdeath[8] = new ImageIcon(this.getClass().getResource("/death9.png")).getImage();
        pacdeath[9] = new ImageIcon(this.getClass().getResource("/death10.png")).getImage();
        pacdeath[10] = new ImageIcon(this.getClass().getResource("/death11.png")).getImage();

        
        dashimg = new ImageIcon(this.getClass().getResource("/dash.png")).getImage();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, d.width, d.height);

        drawMaze(g2d);
        drawScore(g2d);
        if(!currentbonusfixelist.isEmpty()){
        	drawBonusFixe(g2d);
        }
        doAnim();
        if(boodrawbon){
        	drawBonus(g2d);
        }
        if(eatenbonuscore){
        	drawBonuscore(g2d);
        }
        
        if (ingame) {
            playGame(g2d);
        } else {
            showIntroScreen(g2d);
        }

        g2d.drawImage(ii, 5, 5, this);
        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (ingame) {
                if (key == KeyEvent.VK_LEFT) {
                    reqdx = -1;
                    reqdy = 0;
                } else if (key == KeyEvent.VK_RIGHT) {
                    reqdx = 1;
                    reqdy = 0;
                } else if (key == KeyEvent.VK_UP) {
                    reqdx = 0;
                    reqdy = -1;
                } else if (key == KeyEvent.VK_DOWN) {
                    reqdx = 0;
                    reqdy = 1;
                } else if (key == KeyEvent.VK_T) {
                	tmpstateghost ++ ;
                	switch(tmpstateghost){

                            
                		case 1 :
                            for(Ghost currentghost : ghostlist){
                            		currentghost.setState("weak");
                            }
                            break;
                            
                		case 2 :
                            for(Ghost currentghost : ghostlist){
                            		currentghost.setState("dead");
                            }
                            break;
                		case 3 :
                            for(Ghost currentghost : ghostlist){
                            		currentghost.setState("alive");
                            }
                            tmpstateghost = 0;
                            break;
                            
                        default : 
                            tmpstateghost = 0;
                            break;
                	}
                	
                } else if (key == KeyEvent.VK_C) {							// Swap Board Color
                   numcolor += 1; 
                   if(numcolor>swapColorList.size()-1){
                	   numcolor=0;
                   }
                	mazecolor=swapColorList.get(numcolor);
                	
                }else if (key == KeyEvent.VK_R) {							//Restart Level
                	initGame();
                } 
                else if (key == KeyEvent.VK_O ) {							//Next Level Cheat
                	nextLevel();
                }
                else if (key == KeyEvent.VK_I ) {							//Previous Level Cheat
                	numlevel--;
                    initLevel();
                }
                else if (key == KeyEvent.VK_U ) {							//Pacman Death
                	dying = true ;
                	death();
                	
                }
                else if (key == KeyEvent.VK_Y ) {							//Next Level Cheat
                	addlife();
                	addDash(10);
                } 
                
                else if (key == KeyEvent.VK_SPACE) {						// warping dash jump
                	if(jumpcount>=0 && !dying && !deathanim){
                		if(jumpcount==0){
                			Sound.play("false1.wav");
                		}
                		else{
		                	if(reqdx<0){
		                		//pacmanx = pacmanx - blocksize*lengthjump;
		                		if(pacmanx - blocksize*lengthjump <0){
		                			pacmanx = 14*blocksize + (pacmanx - blocksize*(lengthjump-1));}           
		                		else{
		                		pacmanx = pacmanx - blocksize*lengthjump;
		                		}
		                	}
		                	if(reqdx>0){               		
		                		if(pacmanx + blocksize*lengthjump >14*blocksize){
		                			pacmanx = 0 + (pacmanx + blocksize*(lengthjump-1) - 14*blocksize) ;
		                		}else{
		                			pacmanx = pacmanx + blocksize*lengthjump;
		                		}               		
		                	}                	
		                	if(reqdy<0){
		                		
		                		if(pacmany - blocksize*lengthjump <0){
		                			pacmany = 14*blocksize + (pacmany - blocksize*(lengthjump-1));}           
		                		else{
		                			pacmany = pacmany - blocksize*lengthjump;
		                		}
		                	}
		                	if(reqdy>0){
		                		if(pacmany + blocksize*lengthjump >14*blocksize){
		                			pacmany = 0 + (pacmany + blocksize*(lengthjump-1) - 14*blocksize) ;
		                		}else{
		                			pacmany = pacmany + blocksize*lengthjump;
		                		}
		                	}
		                    jumpcount--;
		                    Sound.play("dashsound.wav");
		                    //System.out.println("Jump : "+ jumpcount + " \t lengthjump :"+ lengthjump);
	                	}
                	}
                }else if (key == KeyEvent.VK_ESCAPE && timer.isRunning()) {
                    ingame = false;
                } else if (key == KeyEvent.VK_P) {
                    if (timer.isRunning()) {
                        timer.stop();
                    } else {
                        timer.start();
                    }
                }
            } else {
                if (key == 's' || key == 'S') {
                    ingame = true;
                    initGame();
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

            int key = e.getKeyCode();

            if (key == Event.LEFT || key == Event.RIGHT
                    || key == Event.UP || key == Event.DOWN) {
                reqdx = 0;
                reqdy = 0;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        repaint();
    }
    
    public static void addlife(){
    	Sound.play("pacman_extrapac2.wav");
    	pacsleft = pacsleft+1;	
    	if (pacsleft>= 10){
    		pacsleft = 10 ;
    	}

    }
    
    public static void addDash(int newdash){
    	Sound.play("pacman_refilldash.wav");
    	jumpcount = jumpcount + newdash;
    }
    
    private void addBonus() {
		bonscore = new BonusCreator();
		boodrawbon = true;
		boolean alreadybonusinplace = false;
		int trytoplace=0;
		
		while(!boobonusexist){
			
			bonscore.setPosX((int) (Math.random()*nrofblocks));
	        bonscore.setPosY((int) (Math.random()*nrofblocks));
	        
	        if ((screendata[bonscore.getPosX() +nrofblocks * bonscore.getPosY()] & 16) != 0 ){
	        	trytoplace++;
	    		alreadybonusinplace = false;
		        for(BonusCreator bonus : bonuslist){
		        	if (bonus.getPosX() == bonscore.getPosX() && bonus.getPosY() == bonscore.getPosY() ) { 
		        		alreadybonusinplace = true;
		        		break; //if already a bonus at this place, no need to check the rest of the list 
		        	}
		        }
		        
	        	if(alreadybonusinplace){
	        		System.out.println("There is already a bonus on [" +bonscore.getPosX()+":" +bonscore.getPosY()+"] !!! try number : " + trytoplace );
	        	}
		        
		        if(!alreadybonusinplace){
		        	bonuslist.add(bonscore);
					boobonusexist = true;
		        }
	        }
	        if(trytoplace >=20){
	        	System.out.println("No place left for a bonus" );
	        	bonscore = null;
	        	break;
	        } 
		}	
	}
    
    static int gettileinfo(int x, int y){    
    	
        int pos = x / blocksize + nrofblocks * (int) (y / blocksize);        
    	return screendata[pos];
    }
    
    static int getBlockSize(){
    	return blocksize;
    }
    
    static int getPacmanx(){
    	return pacmanx;
    }
    
    static int getPacmany(){
    	return pacmany;
    }
    
    static int getPacmandx(){
    	return pacmandx;
    }
    
    static int getPacmandy(){
    	return pacmandy;
    }
    
    public static void updateScore(int newpts){
    	score = score + newpts ;
    }
    
    
    private void initColorBoard(){
        swapColorList = new ArrayList<Color>();
        
        swapColorList.add(green);
        swapColorList.add(orange);
        swapColorList.add(flashygreen);
        
        swapColorList.add(Color.cyan);
        swapColorList.add(Color.PINK);
        swapColorList.add(Color.BLUE);
        swapColorList.add(Color.GREEN);
        swapColorList.add(Color.MAGENTA);
        swapColorList.add(Color.YELLOW);

        
        numcolor=0;
    }
    
    private void getSpecialghostlist(){
        Maze currentlevel = new Maze(numlevel);
        ghostlisttmp = currentlevel.getSpecialghostlist();       
    }
    
    
}