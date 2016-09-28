package uk.ac.mmu.watchai.Things;

import java.util.ArrayList;


import uk.ac.mmu.watchai.Model.Thing;

/**
 * 
 * @author Samuel Orgill 15118305
 * NW5 Smartwatch Control of Environment
 * September 2016
 * 
 * Store class to store Things from the database and match them against
 * attached sensors & actuators.
 *
 */

public class Store {


	private static ArrayList<Thing> aList;
	private int total;
	
	
	// Constructor
	public Store(){
		aList = new ArrayList<Thing>();
		total = 0;
	}

	
	//Returns index of searched for thing
	private int search(String nameIn){
		for(int i = 0; i < total; i++){
			Thing tempThing = aList.get(i);
			String tempName = tempThing.getThing();
			if(tempName.equals(nameIn)){
				return i; 
			}
		}
		return -999;
	}
	
	//Gets total stored
	public int getTotal() {
		return total;
	}
	
	//Check if list is empty
	public boolean isEmpty(){
		if(total == 0){
			return true;
		}else{
			return false;
		}
	}
	
	//Check if list is full
	public boolean isFull(){
		if(total == aList.size()){
			return true;
		}else{
			return false;
		}
	}
	
	
	/**
	 * Add thing to list
	 * @param thingIn
	 */
	public void add(Thing thingIn){
			aList.add(thingIn);
			total++; //increment list	
	}
	
	/**
	 * Get all from list
	 */
	public void getAll(){
		for(int i = 0; i < aList.size(); i++){
			Thing th = aList.get(i);
			System.out.println("Sa: " + th);	
		}
	}
	
	/**
	 * Array of thing objects stored
	 * @return
	 */
	public static ArrayList<Thing> getAllList(){
		
		ArrayList<Thing> dList = new ArrayList<Thing>();
		for(int i = 0; i < aList.size(); i++){
			Thing th = aList.get(i);
			System.out.println("Ba: " + th);
			dList.add(th);
		}
		return dList;
	}
	
	
	
}
