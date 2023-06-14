package net.datenwerke.rs.transport.client.transport.ui.forms;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.transport.client.transport.dto.pa.TransportFolderDtoPA;

/**
 * 
 *
 */
public class FolderForm extends SimpleFormView {

   @Override
   public void configureSimpleForm(SimpleForm form) {
      form.setHeading("Folder"
            + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")"));

      form.addField(String.class, TransportFolderDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.name());
      
      form.addField(String.class, TransportFolderDtoPA.INSTANCE.description(), BaseMessages.INSTANCE.description(),
            new SFFCTextAreaImpl());
   }

}