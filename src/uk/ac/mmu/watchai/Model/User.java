package uk.ac.mmu.watchai.Model;

/**
*@author Samuel Orgill 15118305
* NW5 Smartwatch Control of Environment
* September 2016
* 
* User object
*/

public class User {

	private String userName;
	
	//Constructor
	public User(String usr){
		setUserName(usr);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
