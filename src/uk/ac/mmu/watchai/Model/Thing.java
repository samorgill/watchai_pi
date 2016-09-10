package uk.ac.mmu.watchai.Model;

public class Thing {

	private String thing;
	private String state;
	private String serial;
	private String type;
	private String zone;
	private String room;
	private String topic;
	
	public Thing(String th, String st, String se, String ty, String zo, String ro, String to){
		this.thing = th;
		this.state = st;
		this.serial = se;
		this.type = ty;
		this.zone = zo;
		this.room = ro;
		this.topic = to;
		
	}

	public String getThing() {
		return thing;
	}

	public void setThing(String thing) {
		this.thing = thing;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	public String toString(){
		return "Thing: " + thing + " "+ state + " "+ serial + " " + type + " "+ zone + " " + room + " " + topic + " ";
	}
	
	
}
