package packman;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class Options extends JPanel{
	/*
	 * This class let the player choose the color of the board in game
	 * with 3 slider for the color RGB
	 * In the Board, this class is called to get the color set by the player 
	 * There is a button to go back to the menu
	 */

	private static int red = 5;
	private static int green = 100;
	private static int blue = 5;
	private JSlider slideRed = new JSlider();  	
	private JSlider slideBlue = new JSlider();  
	private JSlider slideGreen = new JSlider();  
	private static Color writing = new Color(red,green,blue);
	private PacButton back ;
	private JLabel choosecolor =new JLabel("Choose your Board Color"); 
	
	public Options(){
		this.setBackground(Color.black);
		this.setLayout(new GridLayout(0,1,20,20));
		
		
		FontPacman.setMyFont(choosecolor, 18, writing);
		choosecolor.setHorizontalAlignment(getWidth()/2);
		this.add(choosecolor);
					
	    this.add(slideRed);
	    InitScroll(slideRed);
	    this.add(slideGreen);
	    InitScroll(slideGreen);
	    this.add(slideBlue);
	    InitScroll(slideBlue);

		back = new PacButton("Back to menu", writing);
		this.add(back);	
		back.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Pacman.getFrame().menu();							
			}
			
		});
	}
	
	
	/*
	 *InitScroll init different slider. Because the color is in RGB and his caped a 255 on each value, the slider can go
	 *from 0 to 255 with step of 1
	 *The code is inspired by the one on
	 *https://openclassrooms.com/courses/apprenez-a-programmer-en-java/conteneurs-sliders-et-barres-de-progression
	 */
	
	public void InitScroll(JSlider slider){
		slider.setMaximum(255);
		slider.setMinimum(0);
		slider.setBackground(Color.black);
		slider.setForeground(writing);
		
		if(slider == slideRed ){
			slider.setValue(red);
		}
		else if(slider == slideGreen ){
			slider.setValue(green);
		}
		else if(slider == slideBlue ){
			slider.setValue(blue);
		}

		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMinorTickSpacing(5);
		slider.setMajorTickSpacing(25);
		slider.addChangeListener(new ChangeListener(){
	        public void stateChanged(ChangeEvent event){
	        	if( (JSlider)event.getSource() == slideRed){
	        		red =((JSlider)event.getSource()).getValue();
	        	}
	        	if( (JSlider)event.getSource() == slideGreen){
	        		green =((JSlider)event.getSource()).getValue();
	        	}
	        	if( (JSlider)event.getSource() == slideBlue){
	        		blue =((JSlider)event.getSource()).getValue();
	        	}
	        	setcolor();
	        }
	      }); 
		
	}
	
	/*
	 * setcolor will set every component on the panel with the one choose by the player
	 */
	public void setcolor(){
		writing = new Color(red,green,blue);
		back.setForeground(writing);
		slideRed.setForeground(writing);
		slideBlue.setForeground(writing);
		slideGreen.setForeground(writing);
		FontPacman.setMyFont(choosecolor, 18, writing);
	}
	
	
	/*
	 * getcolor will return the color chosen by the player
	 */
	public Color getColorboard(){
		
		return writing ;
	}
	
}
