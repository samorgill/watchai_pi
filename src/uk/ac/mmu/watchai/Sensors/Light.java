package uk.ac.mmu.watchai.Sensors;

import com.phidgets.InterfaceKitPhidget;
import com.phidgets.Phidget;
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

import uk.ac.mmu.watchai.Things.Lights;

public class Light {

	
	public void turnOn(InterfaceKitPhidget led) throws PhidgetException {
	    Lights lights = new Lights();
		
		 //InterfaceKitPhidget led = new InterfaceKitPhidget();
		 System.out.println(Phidget.getLibraryVersion());
		 attachListener(led);
		 
		 //Set the change listener to monitor the light levels
		 led.addInputChangeListener(new InputChangeListener(){

			@Override
			public void inputChanged(InputChangeEvent arg0) {
	
			}
			 
		 });
			led.addSensorChangeListener(new SensorChangeListener(){

				@Override
				public void sensorChanged(SensorChangeEvent arg0) {
					
					int sensVal = arg0.getValue();
					
					System.out.println("Arg " + arg0.getValue());
					
					//If the light levels fall below 30, the lights turn on
					if(sensVal < 30){
						try {
							lights.turnOnWhite(led);
							lights.turnOnRed(led);
							lights.turnOnGreen(led);
							
						} catch (PhidgetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
					}else if(sensVal > 50){
						try {
							
							lights.turnOffWhite(led);
							lights.turnOffRed(led);
							lights.turnOffGreen(led);
							String str = "Light off";
						      
						} catch (PhidgetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
			});
			
			 }
	

	/**
	 * Method for turning off the event listener & no longer monitor the light
	 * @param led
	 * @throws PhidgetException
	 */
	
	public void turnOff(InterfaceKitPhidget led) throws PhidgetException{
		
		SensorChangeListener sn = null;
		
	
		led.removeSensorChangeListener(sn);
		led.close();
		
	}
	
	/**
	 * Listener methods to listen for changes in the light variables
	 * @param led
	 * @throws PhidgetException
	 */
	public void attachListener(InterfaceKitPhidget led) throws PhidgetException{
  		led.addAttachListener(new AttachListener(){
  			public void attached(AttachEvent ae) {
  				System.out.println("attachment of " + ae);
  			}
  		});
  		
  		led.addDetachListener(new DetachListener() {
  			public void detached(DetachEvent ae) {
  				System.out.println("detachment of " + ae);
  			}
  		});
  		led.addErrorListener(new ErrorListener() {
  			public void error(ErrorEvent ee) {
  				System.out.println("error event for " + ee);
  			}
  		});
  		
  		led.openAny();
		led.waitForAttachment(); 
	 	

  	}

	
}
