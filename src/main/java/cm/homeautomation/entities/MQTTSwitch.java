package cm.homeautomation.entities;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
public class MQTTSwitch extends Switch {
	
	@Getter
	@Setter
	private String mqttPowerOnTopic;

	@Getter
	@Setter
	private String mqttPowerOffTopic;
	
	@Getter
	@Setter
	private String mqttPowerOnMessage;
	
	@Getter
	@Setter
	private String mqttPowerOffMessage;
	
}
