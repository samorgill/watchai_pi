package uk.ac.mmu.watchai.Model;

import java.io.File;

import jaco.mp3.player.MP3Player;

/**
*@author Samuel Orgill 15118305
* NW5 Smartwatch Control of Environment
* September 2016
* 
* Music player
*/

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
