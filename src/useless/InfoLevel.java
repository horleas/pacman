package packman;

import java.awt.Image;

public class InfoLevel {
	private String nameLevel ;
	private Image levelimg ;
	
	public InfoLevel(int numlevel){
		
		Maze info = new Maze(numlevel);
		
		setNameLevel(info.getName());
		setLevelimg(info.getImglevel(numlevel));
		
		
	}

	public Image getLevelimg() {
		return levelimg;
	}

	public void setLevelimg(Image levelimg) {
		this.levelimg = levelimg;
	}

	public String getNameLevel() {
		return nameLevel;
	}

	public void setNameLevel(String nameLevel) {
		this.nameLevel = nameLevel;
	}
	
}
