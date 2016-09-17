package uk.ac.mmu.watchai.MQTT;

import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.phidgets.AdvancedServoPhidget;
import com.phidgets.InterfaceKitPhidget;
import com.phidgets.PhidgetException;

import uk.ac.mmu.watchai.Model.Thing;
import uk.ac.mmu.watchai.Model.UserUtils;
import uk.ac.mmu.watchai.Sensors.Light;
import uk.ac.mmu.watchai.Sensors.Temperature;
import uk.ac.mmu.watchai.Sensors.Vibration;
import uk.ac.mmu.watchai.Things.Lights;
import uk.ac.mmu.watchai.Things.Lock;
import uk.ac.mmu.watchai.Things.Music;
import uk.ac.mmu.watchai.Things.Store;

public class MQTT {

	 public void startMQTT(String[] args) throws PhidgetException {
		 
		 UserUtils uu = new UserUtils();
		 

		  String username = null;
		try {
			username = uu.getUser();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  System.out.println("MQTT un: " + username);
		 	
		    String topic        = username + "/#"; 
		    String content      = "Hello there from CloudMQTT";
		    int qos             = 1;
		    String broker       = "tcp://192.168.0.19:1883";
		// String broker       = "tcp://m21.cloudmqtt.com:17781";
		    //MQTT client id to use for the device. "" will generate a client id automatically
		    String clientId     = "CloudSample";

		    
		    
		    Lock lock = new Lock();
		    
		    AdvancedServoPhidget servo = new AdvancedServoPhidget();
		    
		    lock.attachListener(servo);
		    
		    Lights li = new Lights();
			InterfaceKitPhidget ifk = new InterfaceKitPhidget();
			li.attachListener(ifk);
			
			Music music = new Music();
			
			Light lightSensor = new Light();
			Temperature tempSensor = new Temperature();
			Vibration vibSensor = new Vibration();
			
			String vibration = "Vibration";
			String lightMon = "Luminosity";
			String tempMon = "Temperature";
			String recipe = "Recipe";
			String msc = "Music";
			String on = "On";
			String off = "Off";
			String locked = "Locked";
			String unlocked = "Unlocked";
			String sleep = "sleep";
			String wake = "wake";
			String sooth = "sooth";
			String entertain = "entertain";
			String emergency = "emergency";
         	  
			
		    
	         MemoryPersistence persistence = new MemoryPersistence();
	         try {
	           MqttClient mqttClient = new MqttClient(broker, clientId, persistence);
	           mqttClient.setCallback(new MqttCallback() {
	             public void messageArrived(String topic, MqttMessage msg)
	                       throws Exception {
	            	 			
	            	/**
	            	 * If topic contains music: 
	            	 */
	            	 
	            	 
	            	 
	            	//Light.main(args); 
	            	 
	            	 String message = new String(msg.getPayload());
	            	 
	            	 
	            	if(topic.contains(msc) && message.contains("Genius")){
	            		 music.playSound("genius");
	            	 }if(topic.contains(msc) && message.contains("Sleep")){
	            		 music.playSound("sleep");
	            	 }if(topic.contains(msc) && message.contains("Party")){
	            		 music.playSound("party");
	            	 }if(topic.contains(msc) && message.contains("Rhyme")){
	            		 music.playSound("rhyme");
	            	 }if(topic.contains(msc) && message.contains("Stop")){
	            		 music.stopMusic();
	            	 }if(message.equals(locked)){
                    	  System.out.println(locked);
                    	  lock.lock(servo);
                    	  li.turnOffAll(ifk);
                     }if(message.equals(unlocked)){
                    	  System.out.println(unlocked);
                    	  lock.unlock(servo);
                    	  li.turnOnAll(ifk);
                     }if(topic.contains(lightMon) &&  message.contains(on)){
                    	 lightSensor.turnOn(ifk);
                     }if(topic.contains(lightMon) &&  message.contains(off)){
                    	 lightSensor.turnOff(ifk);
                     }if(topic.contains(vibration) && message.contains(on)){
                    	 vibSensor.turnOn(ifk);
                     }if(topic.contains(vibration) && message.contains(off)){
                    	 vibSensor.turnOff(ifk);
                    	// mqttClient.publish(topic, new MqttMessage(off.getBytes()));
                     }if(topic.contains(tempMon) && message.contains(on)){
                    	 tempSensor.getTemp(ifk);       	
                     }if(topic.contains(recipe) && message.contains(sleep)){
                    	 music.playSound(sleep);
                    	 li.soothingLights();
                    	 lock.lock(servo);
                     }if(topic.contains(recipe) && message.contains(wake)){
                    	 music.playSound("genius");
                    	 lock.unlock(servo);
                     }if(topic.contains(recipe) && message.contains(sooth)){
                    	 music.playSound("genius");
                    	 li.soothingLights();
                     }if(topic.contains(recipe) && message.contains(entertain)){
                    	 music.playSound("rhyme");
                    	 li.partyLights();
                     }if(topic.contains(recipe) && message.contains(emergency)){
                    	 li.emergencyLight();
              
                     }
	            	
	            	 System.out.println(topic + " this is the msg: " + message);
	            	 
	            	 
	                           System.out.println("Recived:" + topic);
	                           System.out.println("Recived:" + new String(msg.getPayload()));
	                           
	                          // String mssg = new String(msg.getPayload());
	                           
	                           }

	             public void deliveryComplete(IMqttDeliveryToken arg0) {
	                         System.out.println("Delivary complete");
	                     }

	             public void connectionLost(Throwable arg0) {
	                         // TODO Auto-generated method stub
	                     }
	           });

	           
	           
	           MqttConnectOptions connOpts = new MqttConnectOptions();
	           connOpts.setCleanSession(true);
	           /*connOpts.setUserName("xcihlzki");
	           connOpts.setPassword(new char[]{'7', 'w', 'p', 'h', '1', 'k', 'J', 'E', 'R', '7', 'X', 'h'});*/
	          /* connOpts.setUserName("samo");
	           connOpts.setPassword(new char[]{'a', 't', 'h', 'c', 'l', 'i', 'a', 't', 'h', '8'});*/
	           mqttClient.connect(connOpts);
	           MqttMessage message = new MqttMessage(content.getBytes());
	           message.setQos(qos); 
	           System.out.println("Publish message: " + message);
	           mqttClient.subscribe(topic, qos);
	           //mqttClient.publish(topic, message);
	           /*mqttClient.disconnect();
	           System.exit(0);*/
	           
	           
	           
	         } catch(MqttException me) {
	           System.out.println("reason "+me.getReasonCode());
	           System.out.println("msg "+me.getMessage());
	           System.out.println("loc "+me.getLocalizedMessage());
	           System.out.println("cause "+me.getCause());
	           System.out.println("excep "+me);
	           me.printStackTrace();
	         }
	      
	    	 
	    	 
	         
}
	 

}
