package org.isf.generaldata;

import static org.assertj.core.api.Assertions.assertThat;

import org.isf.medicalstock.service.MedicalStockIoOperations;
import org.junit.Ignore;
import org.junit.Test;

public class GeneralDataTest {
	
	

	@Test
	public void testGetGeneralData() {
		MedicalStockIoOperations medicalStockIoOperations1 = new MedicalStockIoOperations();
		medicalStockIoOperations1.logGeneralDataProperties();
		
		
		GeneralData generalData =  GeneralData.getGeneralData();
		
		assertThat(generalData).isNotNull();
		
		assertThat(GeneralData.LANGUAGE).isEqualTo("es");
		
		MedicalStockIoOperations medicalStockIoOperations2 = new MedicalStockIoOperations();
		medicalStockIoOperations2.logGeneralDataProperties();
		

	}

}
