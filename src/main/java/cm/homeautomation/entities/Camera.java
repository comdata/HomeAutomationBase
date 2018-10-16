package cm.homeautomation.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmIgnore;

@Entity
public class Camera {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonBackReference("room")
	@ManyToOne
	@JoinColumn(name = "ROOM_ID", nullable = false)
	@EdmIgnore
	private Room room;

	@Column(nullable=false, unique=true)
	private String cameraName;
	
	private String icon;
	private String stream;
	@XmlTransient
	@JsonIgnore
	private byte[] imageSnapshot;
	
	private boolean enabled=true;

	public String getCameraName() {
		return cameraName;
	}

	public void setCameraName(String cameraName) {
		this.cameraName = cameraName;
	}

	@XmlTransient
	@JsonIgnore
	@JsonBackReference("room")
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonIgnore
	@XmlTransient
	public byte[] getImageSnapshot() {
		return imageSnapshot;
	}

	public void setImageSnapshot(byte[] imageSnapshot) {
		this.imageSnapshot = imageSnapshot;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
