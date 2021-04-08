package br.ce.wcaquino.yaskbackend.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

import br.ce.wcaquino.taskbackend.utils.DateUtils;

public class DateUtilsTest {
	
	@Test
	public void deveRetornarTrueParaDatasFuturas() {
		LocalDate date = LocalDate.of(2030, 12, 13);
		assertTrue(DateUtils.isEqualOrFutureDate(date));
	}
	
	@Test
	public void deveRetornarFalseParaDatasPassadas() {
		LocalDate date = LocalDate.of(2012, 12, 13);
		assertFalse(DateUtils.isEqualOrFutureDate(date));
	}
	
	@Test
	public void deveRetornarTrueeParaDatasPresente() {
		LocalDate date = LocalDate.now();
		assertTrue(DateUtils.isEqualOrFutureDate(date));
	}

}
