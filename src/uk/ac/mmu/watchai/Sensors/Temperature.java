package uk.ac.mmu.watchai.Sensors;

import com.phidgets.InterfaceKitPhidget;
import com.phidgets.PhidgetException;
import uk.ac.mmu.watchai.MQTT.MQTTSend;

public class Temperature {

	
	public void getTemp(InterfaceKitPhidget ifk) throws PhidgetException{
		//ifk.setRatiometric(true);
		double sensVal = ifk.getSensorValue(7);
		double tempCels = (sensVal * 0.2222) -61.111;
		String cTemp = Double.toString(tempCels).substring(0, 4);
		System.out.println("Temp " + tempCels);
		MQTTSend.send(cTemp);
		ifk.close();
		
	}

	
	
}
