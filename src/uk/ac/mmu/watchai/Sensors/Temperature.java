package uk.ac.mmu.watchai.Sensors;

import com.phidgets.InterfaceKitPhidget;
import com.phidgets.PhidgetException;
import uk.ac.mmu.watchai.MQTT.MQTTSend;

/**
 * 
 * @author Samuel Orgill 15118305
 * NW5 Smartwatch Control of Environment
 * September 2016
 * 
 * Class for getting temperature
 *
 */

public class Temperature {

	/**
	 * Get the temperature
	 * @param ifk
	 * @throws PhidgetException
	 */
	public void getTemp(InterfaceKitPhidget ifk) throws PhidgetException{
		double sensVal = ifk.getSensorValue(7);
		double tempCels = (sensVal * 0.2222) -61.111;
		String cTemp = Double.toString(tempCels).substring(0, 4);
		System.out.println("Temp " + tempCels);
		MQTTSend.send(cTemp);
		ifk.close();
	}

	
}
