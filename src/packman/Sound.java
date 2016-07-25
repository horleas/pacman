package packman;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound{
	
	private Clip clip;
	
	public Sound(String name){
		
		try{
			clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResource(name));
			clip.open(inputStream);
			clip.start();
		}catch(Exception e){
			System.out.println("play sound error" + e.getMessage());
		}
		
	}
	
	public void stop(){
		
		clip.stop();
		
	}
	
	public void loop(){
		
		clip.loop(10);
		
	}
	
	public void start(){
		clip.start();
	}
	
	// Look at http://www.classicgaming.cc/classics/pac-man/sounds
	public static synchronized void play(final String name){
		
		new Thread(new Runnable(){
			
			public void run(){
				
				try{
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResource(name));
					clip.open(inputStream);
					clip.start();
				}catch(Exception e){
					System.out.println("play sound error" + e.getMessage());
				}
				
			}
			
		}).start();
		
	}
	
	}
