package uk.ac.mmu.watchai.Registration;

import java.io.IOException;

/**
 * 
 * @author Samuel Orgill 15118305
 * NW5 Smartwatch Control of Environment
 * September 2016
 * 
 * A class for registering
 *
 */

public class Register {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void broadcast() throws InterruptedException{
		 Broadcast bc = new Broadcast();
		  try {
			bc.broadcast();
			System.out.println("Running");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
