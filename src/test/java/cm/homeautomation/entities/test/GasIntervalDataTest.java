package cm.homeautomation.entities.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

import org.junit.Test;

import cm.homeautomation.entities.GasIntervalData;

public class GasIntervalDataTest {

	@Test
	public void testConstructor() {
		BigDecimal qm=BigDecimal.ONE;
		Timestamp timeslice=Timestamp.from(Instant.now());
		GasIntervalData gasIntervalData = new GasIntervalData(qm, timeslice);
	
		assertEquals(qm, gasIntervalData.getQm());
		assertEquals(timeslice, gasIntervalData.getTimeslice());
	}
	
	@Test
	public void testSetterQM() {
		BigDecimal qm=BigDecimal.ONE;
		Timestamp timeslice=Timestamp.from(Instant.now());
		GasIntervalData gasIntervalData = new GasIntervalData(qm, timeslice);
	
		gasIntervalData.setQm(BigDecimal.ZERO);
		
		assertEquals(BigDecimal.ZERO, gasIntervalData.getQm());
		assertEquals(timeslice, gasIntervalData.getTimeslice());
	}
	
	@Test
	public void testSetterTimeslice() {
		BigDecimal qm=BigDecimal.ONE;
		Timestamp timeslice=Timestamp.from(Instant.now());
		Timestamp timesliceLater=Timestamp.from(Instant.now().plusSeconds(10));
		GasIntervalData gasIntervalData = new GasIntervalData(qm, timeslice);
	
		gasIntervalData.setTimeslice(timesliceLater);
		
		assertEquals(qm, gasIntervalData.getQm());
		assertEquals(timesliceLater, gasIntervalData.getTimeslice());
	}

}
