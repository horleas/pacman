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
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    private Dimension d;
    private final Font smallfont = new Font("Helvetica", Font.BOLD, 14);

    private Image ii;
    private final Color dotcolor = new Color(192, 192, 0);
    private Color mazecolor;

    private boolean ingame = false;
    private boolean dying = false;

    private final int blocksize = 24;
    private final int nrofblocks = 15;
    private final int scrsize = nrofblocks * blocksize;
    private final int pacanimdelay = 2;
    private final int pacmananimcount = 4;
    private final int maxghosts = 12;
    private final int pacmanspeed = 6;

    private int pacanimcount = pacanimdelay;
    private int pacanimdir = 1;
    private int pacmananimpos = 0;
    private int nrofghosts = 6;
    private int entryGhostX ;
    private int entryGhostY ;
    
    private int pacsleft, score;
    private int[] dx, dy;
    private int[] ghostx, ghosty, ghostdx, ghostdy, ghostspeed;

    private Image ghost;
    private Image pacman1, pacman2up, pacman2left, pacman2right, pacman2down;
    private Image pacman3up, pacman3down, pacman3left, pacman3right;
    private Image pacman4up, pacman4down, pacman4left, pacman4right;

    private int pacmanx, pacmany, pacmandx, pacmandy;
    private int reqdx, reqdy, viewdx, viewdy;
    private int entryPacmanX, entryPacmanY;

    
    private int numlevel;
    //private LoadMaze loader = new LoadMaze(numlevel);
    //private Maze currentlevel ;
    private short leveldata[] = new short [nrofblocks*nrofblocks];
    private String namelevel;

    private final int validspeeds[] = {1, 2, 3, 4, 6, 8};
    private final int maxspeed = 6;

    private int currentspeed = 3;
    private short[] screendata;
    private Timer timer;
    
    private Color orange = new Color(205, 100, 5);
    private Color green = new Color(5, 100, 5);
    
    private boolean boodrawbon = false;
    private boolean boobonusexist = false;
    private boolean eatenbonuscore = false;
    private int ptseatingbonus ;
    private int ptseatinglife ;
    
	private int eateninX ;
	private int eateninY ;
	private Image eaten ;
	private int eatendelay;
    
    private ArrayList<Bonus_score> bonuslist;
    
    private Bonus_score bonscore ;

    public Board() {

        loadImages();
        initVariables();
        
        addKeyListener(new TAdapter());

        setFocusable(true);

        setBackground(Color.black);
        setDoubleBuffered(true);
    }

    private void initVariables() {

        screendata = new short[nrofblocks * nrofblocks];
        mazecolor = green;
        d = new Dimension(400, 400);
        ghostx = new int[maxghosts];
        ghostdx = new int[maxghosts];
        ghosty = new int[maxghosts];
        ghostdy = new int[maxghosts];
        ghostspeed = new int[maxghosts];
        dx = new int[4];
        dy = new int[4];
        numlevel = 15;
        
        bonuslist = new ArrayList<Bonus_score>();
        
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

        } else {

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

        for (i = 0; i < pacsleft; i++) {
            g.drawImage(pacman3left, i * 28 + 8, scrsize + 1, this);
        }
    }

    private void checkMaze() {

        short i = 0;
        boolean finished = true;

        while (i < nrofblocks * nrofblocks && finished) {

            if ((screendata[i] & 48) != 0) {
                finished = false;
            }

            i++;
        }

        if (finished) {
            numlevel ++ ;
            initLevel();
        	/*
        	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	
            score += 50;
            if (nrofghosts < maxghosts) {
                nrofghosts++;
            }

            if (currentspeed < maxspeed) {
                currentspeed++;
            }
            */

        }
    }

    private void death() {

        pacsleft--;

        if (pacsleft == 0) {
            ingame = false;
        }

        continueLevel();
    }

    private void moveGhosts(Graphics2D g2d) {

        short i;
        int pos;
        int count;

        for (i = 0; i < nrofghosts; i++) {
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

            ghostx[i] = ghostx[i] + (ghostdx[i] * ghostspeed[i]);
            ghosty[i] = ghosty[i] + (ghostdy[i] * ghostspeed[i]);
            drawGhost(g2d, ghostx[i] + 1, ghosty[i] + 1);

            if (pacmanx > (ghostx[i] - 12) && pacmanx < (ghostx[i] + 12)
                    && pacmany > (ghosty[i] - 12) && pacmany < (ghosty[i] + 12)
                    && ingame) {

                dying = true;
            }
        }
    }

    private void drawGhost(Graphics2D g2d, int x, int y) {

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
                score++;
                ptseatingbonus ++ ;
                ptseatinglife ++;
                
                if(boobonusexist){
                	Bonus_score bon = new Bonus_score();
                	boolean modif = false;
                	for(Bonus_score bonus : bonuslist){
                	
                		if((bonus.getPosX()*blocksize ==  pacmanx ) &&  (bonus.getPosY()*blocksize==  pacmany )){
                			score += bonus.getBonus_score();
                			if(bonus.getName()=="lifeup"){addlife();}
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

                if(ptseatingbonus == 5){		// 50 ingame but 5 for test
                    ptseatingbonus = 0;
                	boobonusexist = false;
                	addBonus(); 
                }
                
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
        pacmanx = pacmanx + pacmanspeed * pacmandx;
        pacmany = pacmany + pacmanspeed * pacmandy;
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

        //boolean boobonus = true;
        //Image img = bonscore.getImg() ;
 
        /*if(!boobonusexist){
	        do{
		        bonscore.setPosX((int) (Math.random()*nrofblocks));
		        bonscore.setPosY((int) (Math.random()*nrofblocks));
		        if ((screendata[bonscore.getPosX() +nrofblocks * bonscore.getPosY()] & 16) != 0) { 

		        	g2d.drawImage(img, bonscore.getPosX()*blocksize, bonscore.getPosY()*blocksize, this);
			        //System.out.println("le bonus est en "+ bonscore.getPosX()+" : " + bonscore.getPosY() + "screendata : " + screendata[bonscore.getPosX() + bonscore.getPosY()*nrofblocks-1]);
		            boobonus = false ;
		            boobonusexist = true;
		        }
	        }while(boobonus);
        }
        else{
        	g2d.drawImage(img, bonscore.getPosX()*blocksize, bonscore.getPosY()*blocksize, this);
        }*/
        
        /*while(!boobonusexist){
	
		        bonscore.setPosX((int) (Math.random()*nrofblocks));
		        bonscore.setPosY((int) (Math.random()*nrofblocks));
		        if ((screendata[bonscore.getPosX() +nrofblocks * bonscore.getPosY()] & 16) != 0) { 
		        	g2d.drawImage(img, bonscore.getPosX()*blocksize, bonscore.getPosY()*blocksize, this);
		            boobonusexist = true;
		            
		        }
        }*/
        
        /*if(boobonusexist)
        	g2d.drawImage(img, bonscore.getPosX()*blocksize, bonscore.getPosY()*blocksize, this);*/
        
        
        
        for(Bonus_score bonus : bonuslist){
        	g2d.drawImage(bonus.getImg(), bonus.getPosX()*blocksize,  bonus.getPosY()*blocksize, this);
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
    
    

    private void initGame() {

        pacsleft = 3;
        score = 0;
        initLevel();
        currentspeed = 3;
    }

    private void initLevel() {

        int i;
        ptseatingbonus = 0;
        Maze currentlevel = new Maze(numlevel);
        System.out.println(currentlevel.getName());
        
        leveldata = currentlevel.getMap();
        nrofghosts = currentlevel.getNbrGhost();
        namelevel = currentlevel.getName();
        boobonusexist = false ;
        bonuslist.removeAll(bonuslist);
        
       // leveldata= loader.getLevelMaze(numlevel);
        
        for (i = 0; i < nrofblocks * nrofblocks; i++) {
            screendata[i] = leveldata[i];
            if((leveldata[i] & 32) != 0 ){
            	entryPacmanX = i % nrofblocks ;
            	entryPacmanY = i / nrofblocks ;
            }
            else if((leveldata[i] & 64) != 0 ){
            	entryGhostX = i / nrofblocks ;
            	entryGhostY = i % nrofblocks ;
            }
            
        }

        continueLevel();
    }

    private void continueLevel() {

        short i;
        int dx = 1;
        int random;

        for (i = 0; i < nrofghosts; i++) {

            ghosty[i] = entryGhostX * blocksize;
            ghostx[i] = entryGhostY * blocksize;
            ghostdy[i] = 0;
            ghostdx[i] = dx;
            dx = -dx;
            random = (int) (Math.random() * (currentspeed + 1));

            if (random > currentspeed) {
                random = currentspeed;
            }

            ghostspeed[i] = validspeeds[random];
        }

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

        ghost = new ImageIcon("images/floyd.png").getImage();
        pacman1 = new ImageIcon("images/pacman.png").getImage();
        pacman2up = new ImageIcon("images/up1.png").getImage();
        pacman3up = new ImageIcon("images/up2.png").getImage();
        pacman4up = new ImageIcon("images/up3.png").getImage();
        pacman2down = new ImageIcon("images/down1.png").getImage();
        pacman3down = new ImageIcon("images/down2.png").getImage();
        pacman4down = new ImageIcon("images/down3.png").getImage();
        pacman2left = new ImageIcon("images/left1.png").getImage();
        pacman3left = new ImageIcon("images/left2.png").getImage();
        pacman4left = new ImageIcon("images/left3.png").getImage();
        pacman2right = new ImageIcon("images/right1.png").getImage();
        pacman3right = new ImageIcon("images/right2.png").getImage();
        pacman4right = new ImageIcon("images/right3.png").getImage();

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
                } else if (key == KeyEvent.VK_ESCAPE && timer.isRunning()) {
                    ingame = false;
                } else if (key == KeyEvent.VK_PAUSE) {
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
    
    public void addlife(){
    	pacsleft = pacsleft+1;	
    	if (pacsleft>= 10){
    		pacsleft = 10 ;
    	}

    }
    
    private void addBonus() {
		bonscore = new Bonus_score();
		boodrawbon = true;
		boolean alreadybonusinplace = false;
		int trytoplace=0;
		
		while(!boobonusexist){
			
			bonscore.setPosX((int) (Math.random()*nrofblocks));
	        bonscore.setPosY((int) (Math.random()*nrofblocks));
	        
	        if ((screendata[bonscore.getPosX() +nrofblocks * bonscore.getPosY()] & 16) != 0 ){
	        	trytoplace++;
	    		alreadybonusinplace = false;
		        for(Bonus_score bonus : bonuslist){
		        	if (bonus.getPosX() == bonscore.getPosX() && bonus.getPosY() == bonscore.getPosY() ) { 
		        		alreadybonusinplace = true;
		        		break; //if already a bonus, no need to check the rest of the list 
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
}