package uk.ac.mmu.watchai.Things;

import java.util.ArrayList;


import uk.ac.mmu.watchai.Model.Thing;

/**
 * 
 * @author Samuel Orgill 15118305
 * Store class to store Things from the database and match them against
 * attached sensors & actuators.
 *
 */

public class Store {


	private static ArrayList<Thing> aList;
	private int total;
	
	
	// Constructor
	public Store(){
	//	list = new Thing[tot];
		aList = new ArrayList<Thing>();
		total = 0;
		
	}

	private int search(String nameIn){
		for(int i = 0; i < total; i++){
			Thing tempThing = aList.get(i);
			String tempName = tempThing.getThing();
			if(tempName.equals(nameIn)){
				return i; // returns the index no
			}
		}
		return -999;
	}
	
	public int getTotal() {
		return total;
	}
	
	public boolean isEmpty(){
		if(total == 0){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isFull(){
		if(total == aList.size()){
			return true;
		}else{
			return false;
		}
	}
	
	public void add(Thing thingIn){
		
		
			//System.out.println("Added in Store: " + thingIn);
			//list[total] = thingIn; //add item
			aList.add(thingIn);
			total++; //increment list
			
	}
	
	public void getAll(){
		for(int i = 0; i < aList.size(); i++){
			Thing th = aList.get(i);
			System.out.println("Sa: " + th);	
		}
	}
	
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
