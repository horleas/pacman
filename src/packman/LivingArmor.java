package packman;

import java.awt.Image;

import javax.swing.ImageIcon;

public class LivingArmor extends Ghost{
	private Image up1=new ImageIcon(this.getClass().getResource("/livingarmorup1.png")).getImage();
	private Image up2=new ImageIcon(this.getClass().getResource("/livingarmorup2.png")).getImage();
	private Image down1=new ImageIcon(this.getClass().getResource("/livingarmordown1.png")).getImage();
	private Image down2=new ImageIcon(this.getClass().getResource("/livingarmordown2.png")).getImage();
	private Image right1= new ImageIcon(this.getClass().getResource("/livingarmorright1.png")).getImage();
	private Image right2= new ImageIcon(this.getClass().getResource("/livingarmorright2.png")).getImage();
	private Image left1 = new ImageIcon(this.getClass().getResource("/livingarmorleft1.png")).getImage();
	private Image left2 = new ImageIcon(this.getClass().getResource("/livingarmorleft2.png")).getImage();
	private Image still1 = new ImageIcon(this.getClass().getResource("/livingarmorbase1.png")).getImage();
	private Image still2 = new ImageIcon(this.getClass().getResource("/livingarmorbase2.png")).getImage();

	public LivingArmor(int pposX, int pposY, int type) {
		super(0, 0, 0);
		
		setPosX(pposX);
		setPosY(pposY);
		
		setType(type);
		
		if(type == 0){
			setGhostdx(1);
			setGhostdy(0);
		}
		else {
			setGhostdx(0);
			setGhostdy(1);			
		}
		

	}
	
	protected void possibleMovement(){		
		int tile = Board.gettileinfo(getPosX(),getPosY());		
		int count = 0;
		int possibilityx[] = new int [4] ;
		int possibilityy[] = new int [4] ;

		if (getPosX() % blocksize == 0 && getPosY() % blocksize == 0) {
			if ((tile & 1) == 0 && getGhostdx() != 1 && getType()== 0) {
				possibilityx[count] = -1;
				possibilityy[count] = 0;
	            count++;
	        }
	
	        if ((tile & 2) == 0 && getGhostdy() != 1 && getType()!= 0) {
				possibilityx[count] = 0;
				possibilityy[count] = -1;
	            count++;
	        }
	
	        if ((tile & 4) == 0 && getGhostdx() != -1 && getType()== 0) {
				possibilityx[count] = 1;
				possibilityy[count] = 0;
	            count++;
	        }
	
	        if ((tile & 8) == 0 && getGhostdy() != -1 && getType()!= 0) {
				possibilityx[count] = 0;
				possibilityy[count] = 1;
	            count++;
	        }
	        if (count == 0) {
	
	            if ((tile & 15) == 15) {
	            	setGhostdx(0);
	                setGhostdy(0);
	            }else if ((tile & 10 ) == 10 && type != 0 ){
	            	setGhostdx(0);
	                setGhostdy(0);
	            	
	            } else if ((tile & 5 ) == 5 && type == 0  ){
	            	setGhostdx(0);
	                setGhostdy(0);
	            	
	            }else {

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
	
	public void setImg(Image img) {
		if(getPosframe()%2==0){
			if(getGhostdy()==-1){ this.img = up1 ;}
			else if(getGhostdy()==1){this.img = down1 ;}
			else if(getGhostdx()==1){this.img = right1 ;}
			else if(getGhostdx()==-1){this.img = left1 ;}
			else if(getGhostdx()==0 && getGhostdy()==0 ){this.img = still1 ;}
		}else{
			if(getGhostdy()==-1){ this.img = up2;}
			else if(getGhostdy()==1){this.img = down2 ;}
			else if(getGhostdx()==1){this.img = right2 ;}
			else if(getGhostdx()==-1){this.img = left2 ;}
			else if(getGhostdx()==0 && getGhostdy()==0 ){this.img = still2 ;}						
		}	
	}
	
	

	public void setState(String str){
		state = "alive" ;
	}
}
