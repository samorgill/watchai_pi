package uk.ac.mmu.watchai.GAE;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.json.JSONArray;
import org.json.JSONObject;

import uk.ac.mmu.watchai.Model.Thing;
import uk.ac.mmu.watchai.Model.UserUtils;
import uk.ac.mmu.watchai.Things.Store;


public class GetThings {

	public static String serverURL =
            "http://3-dot-projectbabywatch.appspot.com/";
	
	
	 public void getDB() throws IOException{
		  

		  String serverURL =
		            "http://3-dot-projectbabywatch.appspot.com/";
			
			UserUtils uu = new UserUtils();
			
			String usrName = uu.getUser();
		  
		  Store st = new Store();
		   
			//usrName = "SamHome";
				
			System.out.println("Gettings things...");
			
			JSONObject jObject = new JSONObject();

	      String fullURLStr = serverURL + "GetAllThings?user3=" + usrName;
	      System.out.println("GetAll url" + fullURLStr);
	      JSONArray jArray = getFromServer(fullURLStr);

	     String thing, state, serial, type, zone, room, topic;

	      try {
	          for (int i = 0; i < jArray.length(); i++) {
	              jObject = jArray.getJSONObject(i);
	              
	              

	                  thing = jObject.get("thing").toString();
	                  state = jObject.get("state").toString();
	                  serial = jObject.get("serial").toString();
	                  type = jObject.get("type").toString();
	                  zone = jObject.get("zone").toString();
	                  room = jObject.get("room").toString();

	                  
	                  topic = usrName+"/"+type+"/"+zone+"/"+room+"/"+thing;
	                 
	                 Thing thin = new Thing(thing, state, serial, type, zone, room, topic);
	                 
	                  st.add(thin);
	            
	          }
	      } catch (Exception e){
	          e.printStackTrace();
	      }
		  
	  }
	
		public JSONArray getFromServer(String urlStr){
	        URL url;
	        HttpURLConnection conn;
	        BufferedReader rd;
	        String line = "";
	        String result = "";
	        String stat ="";
	        JSONObject jObject = new JSONObject();
	        JSONObject jOb = new JSONObject();
	        JSONArray jArray = new JSONArray();
	        JSONArray jAr = new JSONArray();
	        JSONArray jArr = new JSONArray();
	        ArrayList<Object> aList = new ArrayList<Object>();

	        try {
	            url = new URL(urlStr);
	            conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestMethod("GET");
	            // Issue the GET to send the data
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            // read the result to process response and ensure data sent

	            StringBuilder sb = new StringBuilder();
	            while ((line = rd.readLine()) != null) {

	                sb.append(line);


	            }
	            rd.close();

	            result = sb.toString();

	            jArray = new JSONArray(result);
	            jObject = new JSONObject();
	            for(int i = 0; i < jArray.length(); i++){
	                jObject = jArray.getJSONObject(i);

	                jAr.put(jObject.get("propertyMap"));


	            }


	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        try {
	            for (int j = 0; j < jAr.length(); j++) {
	                jOb = jAr.getJSONObject(j);
	                jArr.put(jOb);
	                // System.out.println(jOb.get("doorName") + " is " + jOb.get("state"));
	            }
	        }catch (Exception e){
	            e.printStackTrace();
	        }

	        return jArr;
	    }
    
	
}
