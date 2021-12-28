package net.datenwerke.rs.core.client.datasourcemanager.ui.forms;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.datasourcemanager.dto.pa.DatasourceFolderDtoPA;
import net.datenwerke.rs.core.client.datasourcemanager.locale.DatasourcesMessages;

/**
 * 
 *
 */
public class FolderForm extends SimpleFormView {

   @Override
   public void configureSimpleForm(SimpleForm form) {
      form.setHeading(DatasourcesMessages.INSTANCE.editFolder()
            + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")"));

      form.addField(String.class, DatasourceFolderDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.name());

      form.addField(String.class, DatasourceFolderDtoPA.INSTANCE.description(), BaseMessages.INSTANCE.description(),
            new SFFCTextAreaImpl());
   }

}