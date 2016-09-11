package uk.ac.mmu.watchai.Sensors;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.phidgets.InterfaceKitPhidget;
import com.phidgets.PhidgetException;
import com.phidgets.event.AttachEvent;
import com.phidgets.event.AttachListener;
import com.phidgets.event.DetachEvent;
import com.phidgets.event.DetachListener;
import com.phidgets.event.ErrorEvent;
import com.phidgets.event.ErrorListener;
import com.phidgets.event.InputChangeEvent;
import com.phidgets.event.InputChangeListener;
import com.phidgets.event.SensorChangeEvent;
import com.phidgets.event.SensorChangeListener;

import uk.ac.mmu.watchai.Things.Music;

public class Vibration {

	Music music = new Music();
	
	/**
	 * Method to turn on the vibration sensor.
	 * This tests that there is normal movement on a bed
	 * Anything < 100 is considered restless and triggers soft music
	 * @param ifk
	 * @throws PhidgetException
	 * @throws InterruptedException 
	 */
	
	public void turnOn(InterfaceKitPhidget ifk) throws PhidgetException, InterruptedException{
		
		attachListener(ifk);
		
		
		ifk.addInputChangeListener(new InputChangeListener(){

			@Override
			public void inputChanged(InputChangeEvent arg0) {
	
			}
			 
		 });
			ifk.addSensorChangeListener(new SensorChangeListener(){

				@Override
				public void sensorChanged(SensorChangeEvent arg0){
					
					int sensVal = arg0.getValue();
					
					System.out.println("Arg " + arg0.getValue());
					
					//If the light levels fall below 30, the lights turn on
					if(sensVal < 100){
							//play();
						System.out.println("Your child is restless");
						
						
					}if(sensVal > 100 && sensVal < 500){
						System.out.println("Everythings just fine");
						
					}else if(sensVal > 500){
						System.out.println("Check your child");
						
					}
				}
				
			});

		
	}
	

	/**
	 * Method for turning off the event listener & no longer monitor the light
	 * @param led
	 * @throws PhidgetException
	 * @throws InterruptedException 
	 */
	
	public void turnOff(InterfaceKitPhidget ifk) throws PhidgetException{
		
		SensorChangeListener sn = null;
		
	
		ifk.removeSensorChangeListener(sn);
		ifk.close();
		
	}
	
	
	/**
	 * Listener methods to listen for changes in the light variables
	 * @param ifk
	 * @throws PhidgetException
	 */
	public void attachListener(InterfaceKitPhidget ifk) throws PhidgetException{
  		ifk.addAttachListener(new AttachListener(){
  			public void attached(AttachEvent ae) {
  				System.out.println("attachment of " + ae);
  			}
  		});
  		
  		ifk.addDetachListener(new DetachListener() {
  			public void detached(DetachEvent ae) {
  				System.out.println("detachment of " + ae);
  			}
  		});
  		ifk.addErrorListener(new ErrorListener() {
  			public void error(ErrorEvent ee) {
  				System.out.println("error event for " + ee);
  			}
  		});
  		
  		ifk.openAny();
		ifk.waitForAttachment(); 
	 	

  	}

	
}
