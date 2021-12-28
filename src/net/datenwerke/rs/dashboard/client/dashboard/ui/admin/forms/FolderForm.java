package net.datenwerke.rs.dashboard.client.dashboard.ui.admin.forms;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.dashboard.client.dashboard.dto.pa.DashboardFolderDtoPA;
import net.datenwerke.rs.dashboard.client.dashboard.locale.DashboardMessages;

/**
 * 
 *
 */
public class FolderForm extends SimpleFormView {

   @Override
   public void configureSimpleForm(SimpleForm form) {
      form.setHeading(DashboardMessages.INSTANCE.editFolder()
            + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")"));

      form.addField(String.class, DashboardFolderDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.propertyName());

      form.addField(String.class, DashboardFolderDtoPA.INSTANCE.description(),
            BaseMessages.INSTANCE.propertyDescription(), new SFFCTextAreaImpl());

   }

}