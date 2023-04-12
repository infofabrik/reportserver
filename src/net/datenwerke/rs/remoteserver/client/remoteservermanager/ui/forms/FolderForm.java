package net.datenwerke.rs.remoteserver.client.remoteservermanager.ui.forms;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.pa.RemoteServerFolderDtoPA;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.locale.RemoteServerMessages;

/**
 * 
 *
 */
public class FolderForm extends SimpleFormView {

   @Override
   public void configureSimpleForm(SimpleForm form) {
      form.setHeading(RemoteServerMessages.INSTANCE.editFolder()
            + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")"));

      form.addField(String.class, RemoteServerFolderDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.name());

      form.addField(String.class, RemoteServerFolderDtoPA.INSTANCE.description(), BaseMessages.INSTANCE.description(),
            new SFFCTextAreaImpl());
   }

}