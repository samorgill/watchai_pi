package uk.ac.mmu.watchai.Things;

import com.phidgets.AccelerometerPhidget;
import com.phidgets.AdvancedServoPhidget;
import com.phidgets.InterfaceKitPhidget;
import com.phidgets.Phidget;
import com.phidgets.PhidgetException;

/**
 * 
 * @author Samuel Orgill 15118305
 * NW5 Smartwatch Control of Environment
 * September 2016
 * 
 * A class for a new thing attached
 *
 */

public class NewThing {
	
	
	public void accelerometer() throws PhidgetException{
	AccelerometerPhidget device = new AccelerometerPhidget();
	deviceOpen(device);
	}
	
	public void servo() throws PhidgetException{
		final int MOTOR_PORT=0;
		AdvancedServoPhidget servo = new AdvancedServoPhidget();
		deviceOpen(servo);

	}
	
	public void led()throws PhidgetException{
		InterfaceKitPhidget led;
		
		 led = new InterfaceKitPhidget();
		 deviceOpen(led);
	}
	
	public void deviceOpen(Phidget device) throws PhidgetException{
		device.openAny();
		
	}
	

}
