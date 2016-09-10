package uk.ac.mmu.watchai.Registration;

import java.net.InetAddress;
import java.util.Formatter;
import java.io.IOException;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;


public class Broadcast {

	
	public void broadcast() throws IOException, InterruptedException{
	String SERVICE_TYPE = "MQTT";
    String SERVICE_NAME = "Laptop";
    
    InetAddress addr = InetAddress.getLocalHost();
     
    String hostname = InetAddress.getByName(addr.getHostName()).toString();
    
    //
    InetAddress inet = null;
   
    String ipAddress = "192.168.0.19";
    inet = InetAddress.getByName(ipAddress);
    String hName = "DESKTOP-N8PUL6V";
   
    System.out.println("Inet: " + inet.toString().substring(1));
    
    //
    
	JmDNS jmdns; 
	
	System.out.println("Out " + inet + " " + hName);
	
	jmdns = JmDNS.create(inet, hName);
	
	
	
	
	
ServiceInfo mqttService = ServiceInfo.create(SERVICE_TYPE, SERVICE_NAME, 1883, 0, 0, "Laptop MQTT Broker Service");

	System.out.println("Pro Names: " + mqttService.getNiceTextString());

	jmdns.registerService(mqttService);
	
	System.out.println("Service: " + mqttService.toString());
	
	close(jmdns);
	


	}
	
	public void close(JmDNS jmdns) throws IOException{
		
		jmdns.close();
	}
	
}
