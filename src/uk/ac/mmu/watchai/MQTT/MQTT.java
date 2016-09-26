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
		    String content      = "Hello from CloudMQTT";
		    int qos             = 1;
		    String broker       = "tcp://m21.cloudmqtt.com:17781";
		    String clientId     = "Watchai_Hub_Cloud";

		    
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
	            	 			
	            	 
	            	 String message = new String(msg.getPayload());
			
	            	if(topic.contains(msc) && message.contains("genius")){
	            		 music.playSound("genius");
	            	}if(topic.contains(msc) && message.contains("sleep")){
	            		 music.playSound("sleep");
	            	}if(topic.contains(msc) && message.contains("party")){
	            		 music.playSound("party");
	            	}if(topic.contains(msc) && message.contains("rhyme")){
	            		 music.playSound("rhyme");
	            	}if(topic.contains(msc) && message.contains("stop")){
	            		 music.stopMusic();
	            	}if(topic.contains("Blind") && message.equals(locked)){
                    	  System.out.println(locked);
                    	  lock.lock(servo);
                    	  li.turnOffAll(ifk);
                    }if(topic.contains("Blind") && message.equals(unlocked)){
                    	  System.out.println(unlocked);
                    	  lock.unlock(servo);
                    	  li.turnOnAll(ifk);
                    }if(topic.contains("Bedroom") && message.equals(locked)){
                    	li.turnOnWhite(ifk);
					}if(topic.contains("Bedroom") && message.equals(unlocked)){
						li.turnOffWhite(ifk);
					}if(topic.contains("Bathroom") && message.equals(locked)){
						li.turnOnGreen(ifk);
					}if(topic.contains("Bathroom") && message.equals(unlocked)){
						li.turnOffGreen(ifk);
					}if(topic.contains("Study") || topic.contains("Dining") && message.equals(locked)){
						li.turnOnRed(ifk);
					}if(topic.contains("Study") && message.equals(unlocked)){
						li.turnOffRed(ifk);
					}if(topic.contains(lightMon) &&  message.contains(on)){
		               	 lightSensor.turnOn(ifk);
                    }if(topic.contains(lightMon) &&  message.contains(off)){
                    	 lightSensor.turnOff(ifk);
                    }if(topic.contains(vibration) && message.contains(on)){
                    	 vibSensor.turnOn(ifk);
                    }if(topic.contains(vibration) && message.contains(off)){
                    	 vibSensor.turnOff(ifk);
                    	 mqttClient.publish(topic, new MqttMessage(off.getBytes()));
                     }if(topic.contains(tempMon) && message.contains(on)){
                    	 tempSensor.getTemp(ifk);       	
                     }if(topic.contains(recipe) && message.contains(sleep)){
				           	 music.playSound(sleep);
	                    	 li.soothingLights(ifk);
	                    	 lock.lock(servo);
	                 }if(topic.contains(recipe) && message.contains(wake)){
	                    	 music.playSound("genius");
	                    	 lock.unlock(servo);
                     }if(topic.contains(recipe) && message.contains(sooth)){
				           	 music.playSound("genius");
	                    	 li.soothingLights(ifk);
                     }if(topic.contains(recipe) && message.contains(entertain)){
	                    	 music.playSound("rhyme");
	                    	 li.partyLights(ifk);
                     }if(topic.contains(recipe) && message.contains(emergency)){
			               	 li.emergencyLight(ifk);  
                     }if(topic.contains(recipe) && message.contains("stop")){
							 music.stopMusic();
							 li.turnOffAll(ifk);
			         }	
				            	
	            	 System.out.println("Topic: " + topic + ", message: " + message);
	                 System.out.println("Topic recieved:" + topic);
	                 System.out.println("Message recieved:" + new String(msg.getPayload()));
	     
	                           
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
	          connOpts.setUserName("xcihlzki");
	           connOpts.setPassword(new char[]{'7', 'w', 'p', 'h', '1', 'k', 'J', 'E', 'R', '7', 'X', 'h'});
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
