package net.datenwerke.rs.core.client.datasinkmanager.ui.forms;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.datasinkmanager.dto.pa.DatasinkFolderDtoPA;
import net.datenwerke.rs.core.client.datasinkmanager.locale.DatasinksMessages;

/**
 * 
 *
 */
public class FolderForm extends SimpleFormView {
	
	@Override
	public void configureSimpleForm(SimpleForm form) {
		form.setHeading(DatasinksMessages.INSTANCE.editFolder() + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")"));
		
		form.addField(String.class, DatasinkFolderDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.name()); 
		
		form.addField(String.class, DatasinkFolderDtoPA.INSTANCE.description(), BaseMessages.INSTANCE.description(), new SFFCTextAreaImpl());
	}

}