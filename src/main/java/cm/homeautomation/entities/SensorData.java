package cm.homeautomation.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmIgnore;

@Entity
@Table(indexes = { @Index(name = "sensorId", columnList = "SENSOR_ID, VALIDTHRU") })
public class SensorData {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	private String value;

	@ManyToOne
	@JoinColumn(name = "SENSOR_ID", nullable = false)
	@EdmIgnore
	private Sensor sensor;

	private Date dateTime = new Date();

	private Date validThru = new Date();

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public Date getValidThru() {
		return validThru;
	}

	public void setValidThru(Date validThru) {
		this.validThru = validThru;
	}
}
