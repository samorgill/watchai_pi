package uk.ac.mmu.watchai.Registration;

import java.io.IOException;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;
import javax.jmdns.ServiceTypeListener;

import uk.ac.mmu.watchai.Model.User;
import uk.ac.mmu.watchai.Model.UserUtils;

public class Listen {

	static JmDNS jmdns;
	static InetAddress inet = null;
	static InetAddress ine = null;
	
	// TODO Auto-generated method stub

	static class SampleListener implements ServiceListener {

		String brokerIP;
		boolean ready;
		JmDNS jmdns;
		
		User user = new User("");
		
		String SERVICE_TYPE = "MQTT";
	    String SERVICE_NAME = "sample_jmdns_service";
		
	    public void serviceAdded(ServiceEvent event) {
	    System.out.println("Service found : " + event.getName());
	   
	    System.out.println("Resolving Service...");

	    user.setUserName(event.getName());
	    System.out.println("setting un" + event.getName());
	    
	    jmdns.requestServiceInfo(SERVICE_TYPE, event.getName());
	   
	    
	    }
	    public void serviceRemoved(ServiceEvent event) {
	    System.out.println("Service removed : " + event.getName() + "." + event.getType());
	    }
	    public void serviceResolved(ServiceEvent event) {
	    System.out.println("Service resolved!");
	    String[] serviceUrl = event.getInfo().getURLs();
	    brokerIP = "tc"+serviceUrl[0].substring(3);
	    System.out.println("Broker IP: " + "tc"+serviceUrl[0].substring(3));
	    System.out.println(serviceUrl[0].substring(7, 19));
	    
	    user.setUserName(event.getName().toString());
	    System.out.println("Resolved device: " + event.getName());
	    
	    ready=true;
	    
	    
			UserUtils.saveUser(event.getName());
		
	    
	    
	    }
		
	   

					
    }

    /**
     * @param args
     *            the command line arguments
     * @throws UnknownHostException 
     */
    public static void main(String[] args) throws UnknownHostException {
        /*
         * Activate these lines to see log messages of JmDNS Logger logger = Logger.getLogger(JmDNS.class.getName()); ConsoleHandler handler = new ConsoleHandler(); logger.addHandler(handler); logger.setLevel(Level.FINER);
         * handler.setLevel(Level.FINER);
         */

    	String ipAddress = "192.168.0.30";
        inet = InetAddress.getByName(ipAddress);
        
        String hName = "Raspberry_Pi";
    	System.out.println("jmdns create" + inet.toString() + " " + hName );
        
    	
        try {
        	jmdns = JmDNS.create(inet, hName);
        	
            jmdns.addServiceListener("_MQTT._tcp.local.", new SampleListener());

            System.out.println("Press q and Enter, to quit");
            int b;
            while ((b = System.in.read()) != -1 && (char) b != 'q') {
                /* Stub */
            }
           // jmdns.close();
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	
}

