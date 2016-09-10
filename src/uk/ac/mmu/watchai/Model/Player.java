package uk.ac.mmu.watchai.Model;

import java.io.File;

import jaco.mp3.player.MP3Player;

public class Player {

	private MP3Player mp3;
	
	
	public Player(MP3Player mplay){
		this.mp3 = mplay;
	
		
	}

	public MP3Player getMp3() {
		return mp3;
	}

	public void setMp3(MP3Player mp3) {
		this.mp3 = mp3;
	}
	
}
