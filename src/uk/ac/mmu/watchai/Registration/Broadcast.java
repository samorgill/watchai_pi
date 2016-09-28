package uk.ac.mmu.watchai.Registration;

import java.net.InetAddress;
import java.util.Formatter;
import java.io.IOException;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

/**
 * 
 * @author Samuel Orgill 15118305
 * NW5 Smartwatch Control of Environment
 * September 2016
 * 
 * JmDNS broadcast of service to other devices
 *
 */

public class Broadcast {

	
	public void broadcast() throws IOException, InterruptedException{
	String SERVICE_TYPE = "MQTT";
    String SERVICE_NAME = "Laptop";
    
    InetAddress addr = InetAddress.getLocalHost();
     
    String hostname = InetAddress.getByName(addr.getHostName()).toString();
    
    InetAddress inet = null;
   
    //Set IP address of your machine
    String ipAddress = "192.168.0.30";
    inet = InetAddress.getByName(ipAddress);
    String hName = "DESKTOP-N8PUL6V";
   
    System.out.println("Inet: " + inet.toString().substring(1));
    
    //JmDNS
    
	JmDNS jmdns; 
	System.out.println("Out " + inet + " " + hName);
	
	jmdns = JmDNS.create(inet, hName);
	
	ServiceInfo mqttService = ServiceInfo.create(SERVICE_TYPE, SERVICE_NAME, 1883, 0, 0, "Laptop MQTT Broker Service");
	System.out.println("Pro Names: " + mqttService.getNiceTextString());
	jmdns.registerService(mqttService);
	
	System.out.println("Service: " + mqttService.toString());
	close(jmdns);

	}
	
	/*
	 * Close JmDNS connection
	 */
	public void close(JmDNS jmdns) throws IOException{
		
		jmdns.close();
	}
	
}
