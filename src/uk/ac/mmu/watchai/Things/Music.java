package uk.ac.mmu.watchai.Things;

import java.io.File;
import jaco.mp3.player.MP3Player;
import uk.ac.mmu.watchai.Controller.EasyScanner;
import uk.ac.mmu.watchai.MQTT.MQTT;
import uk.ac.mmu.watchai.Model.Player;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import com.phidgets.PhidgetException;

public class Music {

	String path = "music/";
	Player play = new Player(null);
	
	public void playSound(String choice) throws InterruptedException{
		// TODO Auto-generated method stub

		MP3Player mplay = null;
		
		String myChoice = choice;
		
		playMusic(mplay, myChoice);
		
		/*if(myChoice != "stop"){
		playMusic(mplay, myChoice);
		} else if(myChoice.equals("stop")){
			stopMusic(mplay);
		}*/
			
	}
	
	public void playMusic(MP3Player mp3, String myChoice) throws InterruptedException {
	
		File file = new File (path + myChoice + ".mp3");
		
		MP3Player mplay = new MP3Player(file);
		
		mplay.play();
		
		
		//play.setFile(file);
		play.setMp3(mplay);
		
	}
	
	/**
	 * Method to stop the music
	 * @throws PhidgetException
	 * @throws InterruptedException
	 */
	
	public void stopMusic() throws PhidgetException, InterruptedException {
		
		
		MP3Player mp3 = play.getMp3();
		
		mp3.stop();
		
	}
	
	
}
