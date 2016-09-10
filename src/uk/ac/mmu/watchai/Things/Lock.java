package uk.ac.mmu.watchai.Things;

import com.phidgets.AdvancedServoPhidget;
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
import com.phidgets.event.ServoPositionChangeEvent;
import com.phidgets.event.ServoPositionChangeListener;

public class Lock {

	public void locks(String[] args) throws PhidgetException {
		// TODO Auto-generated method stub

		final int MOTOR_PORT=0;
		AdvancedServoPhidget servo; 
		System.out.println(Phidget.getLibraryVersion());

		servo = new AdvancedServoPhidget();
		attachListener(servo);
		
		servo.addServoPositionChangeListener(new ServoPositionChangeListener()
		{
			public void servoPositionChanged(ServoPositionChangeEvent oe)
			{
				// 
				// System.out.println(oe);
			}
		});
		
		

		servo.openAny();
		System.out.println("waiting for AdvancedServo attachment...");
		servo.waitForAttachment();

		System.out.println("Serial: " + servo.getSerialNumber());
		System.out.println("Servos: " + servo.getMotorCount());
		
		System.out.println("Max speed: " + servo.getAccelerationMax(0));

		 servo.setEngaged(MOTOR_PORT, true);
		 
		 servo.setPosition(0, 50);
		 

			
			
	     }

	/**
	 * A method to lock the lock
	 * @param servo
	 * @param led
	 * @throws PhidgetException
	 */
	public void lock(AdvancedServoPhidget servo) throws PhidgetException{
		final int MOTOR_PORT=0;
		
			servo.setSpeedRampingOn(0, true);
			servo.setAcceleration(0,servo.getAccelerationMin(0));
			servo.setAcceleration(0, 100000);
			servo.setVelocityLimit(0, 200);
			servo.setPosition(0, 200);
     	  
	}
	
	/**
	 * A method to unlock the lock
	 * @param servo
	 * @throws PhidgetException
	 */
	public void unlock(AdvancedServoPhidget servo) throws PhidgetException{
		final int MOTOR_PORT=0;
			
		servo.setSpeedRampingOn(0, true);
        servo.setAcceleration(0,servo.getAccelerationMin(0));
        servo.setAcceleration(0, 100000);
        servo.setVelocityLimit(0, 200);
  	  	servo.setPosition(0, 100);
  	
	}
	
		
	

	public void attachListener(AdvancedServoPhidget servo) throws PhidgetException{
		servo.addAttachListener(new AttachListener() {
			public void attached(AttachEvent ae) {
				System.out.println("attachment of " + ae);
			}
		});
		servo.addDetachListener(new DetachListener() {
			public void detached(DetachEvent ae) {
				System.out.println("detachment of " + ae);
			}
		});
		servo.addErrorListener(new ErrorListener() {
			public void error(ErrorEvent ee) {
				System.out.println("error event for " + ee);
			}
		});
		servo.openAny();
		System.out.println("waiting for AdvancedServo attachment...");
		servo.waitForAttachment();
	}

}
