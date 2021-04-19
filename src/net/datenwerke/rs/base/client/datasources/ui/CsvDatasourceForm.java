package net.datenwerke.rs.base.client.datasources.ui;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.rs.base.client.datasources.dto.pa.CsvDatasourceDtoPA;
import net.datenwerke.rs.base.client.datasources.locale.BaseDatasourceMessages;

/**
 * 
 *
 */
public class CsvDatasourceForm extends FormatBasedDatasourceForm{

	protected void addSpecificFields(SimpleForm form) {
		form.beginRow();
		form.addField(String.class, CsvDatasourceDtoPA.INSTANCE.quote(), BaseDatasourceMessages.INSTANCE.csvQuoteLabel()); 

		form.addField(String.class, CsvDatasourceDtoPA.INSTANCE.separator(), BaseDatasourceMessages.INSTANCE.csvSeparatorLabel());
		form.endRow();
		
		form.setFieldWidth(0.3);
		form.addField(Integer.class, CsvDatasourceDtoPA.INSTANCE.databaseCache(), BaseDatasourceMessages.INSTANCE.csvDatabaseCacheLabel());
		form.setFieldWidth(1);
	}


	
}
