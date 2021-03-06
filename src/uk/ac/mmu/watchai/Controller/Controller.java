package uk.ac.mmu.watchai.Controller;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.json.JSONArray;
import org.json.JSONObject;

import com.phidgets.*;
import com.phidgets.event.*;
import uk.ac.*;
import uk.ac.mmu.watchai.Model.UserUtils;
import uk.ac.mmu.watchai.Model.Thing;
import uk.ac.mmu.watchai.Model.User;
import uk.ac.mmu.watchai.Registration.Broadcast;
import uk.ac.mmu.watchai.Sensors.Light;
import uk.ac.mmu.watchai.Things.Lights;
import uk.ac.mmu.watchai.Things.Lock;
import uk.ac.mmu.watchai.Things.Music;
import uk.ac.mmu.watchai.Things.Store;
import uk.ac.mmu.watchai.GAE.GetThings;
import uk.ac.mmu.watchai.MQTT.MQTT;
import uk.ac.mmu.watchai.Controller.*;

/**
 * 
 * @author Samuel Orgill 15118305
 * NW5 Smartwatch Control of Environment
 * September 2016
 * 
 * A controller class to MQTT broker
 *
 */

public class Controller {
	
  public static void main(String[] args) throws IOException, InterruptedException, PhidgetException {
	  
	  //Gets all the things stored in the DB&  adds them to an arraylist
	  GetThings gt = new GetThings();
	  gt.getDB();
	  
	  //Starts MQTT Client
	  MQTT mqtt = new MQTT();
	  mqtt.startMQTT(args);
	
	  
	/*	  Series of tests during development
	 * 	
	 * 	  //Broadcasts the IP to locally connected devices
		  Broadcast bc = new Broadcast();
		  bc.broadcast();
		  
		  UserUtils uu = new UserUtils();
	
		  final String username = uu.getUser();
		  System.out.println("From file: " + username);
		  
		  
		  //Method to start the MQTT broker
		  MQTT mqtt = new MQTT();
		  mqtt.startMQTT(args);
		
		  //Turns on lights depending if it gets dark
		  Lights li = new Lights();
		  li.turnOn(args);
		 
		  
		  //Servo tester
		  Lock lo = new Lock();
		  lo.locks(args);
		  
		  //Music Tester
		  Music mu = new Music();
		  
		  String choice;
		  System.out.println("What would you like to play?");
		  choice = EasyScanner.nextString();
				  mu.playSound(choice);
		  System.out.println("Would you like to stop?");
		  choice = EasyScanner.nextString();
		  		mu.playSound(choice);
		 
	  	  //Test loks
	  	  Lock lo = new Lock();
		  lo.locks(args);
		  System.out.println("Would you like to Lock or Unlock the door?");
		  String ans = EasyScanner.nextString();
		  AdvancedServoPhidget servo = new AdvancedServoPhidget(); 
		  if(ans.equalsIgnoreCase("L")){
			  lo.lock(servo);
		  }else{
			  lo.unlock(servo);
		  }
		 */
		

	  
	  
	  
	  
	  
}
  
  
 	  
}