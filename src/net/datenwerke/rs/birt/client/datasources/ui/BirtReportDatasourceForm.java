package net.datenwerke.rs.birt.client.datasources.ui;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.base.client.datasources.dto.pa.DatabaseDatasourceDtoPA;
import net.datenwerke.rs.base.client.datasources.locale.BaseDatasourceMessages;
import net.datenwerke.rs.birt.client.datasources.dto.pa.BirtReportDatasourceDefinitionDtoPA;

public class BirtReportDatasourceForm extends SimpleFormView {

	@Override
	protected void configureSimpleForm(SimpleForm form) {
		/* configure form */
		form.setHeading(BaseDatasourceMessages.INSTANCE.editDataSource() + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")")); 
		
		/* name name */
		form.addField(String.class, DatabaseDatasourceDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.propertyName()); 
		
		form.addField(String.class, DatabaseDatasourceDtoPA.INSTANCE.description(), BaseMessages.INSTANCE.propertyDescription(), new SFFCTextAreaImpl());
		
		form.setFieldWidth(0.3);
		form.addField(Integer.class, BirtReportDatasourceDefinitionDtoPA.INSTANCE.databaseCache(), BaseDatasourceMessages.INSTANCE.csvDatabaseCacheLabel());
		form.setFieldWidth(1);
	}

}
