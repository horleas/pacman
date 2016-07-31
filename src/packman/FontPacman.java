package packman;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JComponent;

public class FontPacman {

	/*
	 * Personal font taken on 
	 * http://www.dafont.com/fr/04b-30.font
	 */
	public static void setMyFont(JComponent comp, int size, Color color){
		
		try{
			InputStream is = comp.getClass().getResourceAsStream("/PacmanFont.ttf" );
			Font myfont = Font.createFont(Font.TRUETYPE_FONT, is);
			comp.setFont(myfont.deriveFont(Font.PLAIN, size));
			comp.setForeground(color);
			
		}catch(FontFormatException e){
			
		}catch(IOException e){
			
		}
		
		
	}
}
