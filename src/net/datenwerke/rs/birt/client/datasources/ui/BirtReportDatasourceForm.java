package net.datenwerke.rs.birt.client.datasources.ui;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.rs.base.client.datasources.locale.BaseDatasourceMessages;
import net.datenwerke.rs.birt.client.datasources.dto.pa.BirtReportDatasourceDefinitionDtoPA;
import net.datenwerke.rs.core.client.datasourcemanager.ui.forms.DatasourceSimpleForm;

public class BirtReportDatasourceForm extends DatasourceSimpleForm {

   @Override
   protected void configureSimpleFormCustomFields(SimpleForm form) {
      /* */
      form.setFieldWidth(0.3);
      form.addField(Integer.class, BirtReportDatasourceDefinitionDtoPA.INSTANCE.databaseCache(),
            BaseDatasourceMessages.INSTANCE.csvDatabaseCacheLabel());
      form.setFieldWidth(1);
   }

}
