
package cm.homeautomation.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlIDREF;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	
	@Column(name="ROOMNAME", nullable=false)
	private String roomName;
	
	@OneToMany(mappedBy="room", cascade=CascadeType.ALL)
	@JsonManagedReference("room")
	private List<Sensor> sensors;

	@OneToMany(mappedBy="room", cascade=CascadeType.ALL)
	private List<Switch> switches;
	
	@OneToMany(mappedBy="room", cascade=CascadeType.ALL)
	private List<Device> devices;

	@OneToMany(mappedBy="room", cascade=CascadeType.ALL)
	private List<Light> lights;
	
	@Column(name="VISIBLE")
	private Boolean visible;
	
	@Column(name="SORT_ORDER")
	private int sortOrder=0;
	
	@ElementCollection 
	@CollectionTable(
	        name="ROOM_PROPERTIES")
	private Map<String, String> roomProperties;
	
	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public List<Sensor> getSensors() {
		if (sensors==null) {
			sensors=new ArrayList<>();
		}
		return sensors;
	}

	@XmlIDREF
	public void setSensors(List<Sensor> sensors) {
		this.sensors = sensors;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Switch> getSwitches() {
		return switches;
	}

	public void setSwitches(List<Switch> switches) {
		this.switches = switches;
	}

	public List<Device> getDevices() {
		if (devices==null) {
			devices=new ArrayList<Device>();
		}
		
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	@XmlIDREF
	public List<Light> getLights() {
		if (lights==null) {
			lights=new ArrayList<Light>();
		}
		
		return lights;
	}

	public void setLights(List<Light> lights) {
		this.lights = lights;
	}

	public boolean isVisible() {
		return getVisible();
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Map<String, String> getRoomProperties() {
		return roomProperties;
	}

	public void setRoomProperties(Map<String, String> roomProperties) {
		this.roomProperties = roomProperties;
	}
}
