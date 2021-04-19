package net.datenwerke.rs.core.client.reportmanager.ui.forms;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.reportmanager.dto.pa.ReportFolderDtoPA;
import net.datenwerke.rs.core.client.reportmanager.locale.ReportmanagerMessages;

/**
 * 
 *
 */
public class FolderForm extends SimpleFormView {
	
	public void configureSimpleForm(SimpleForm form) {
		form.setHeading(ReportmanagerMessages.INSTANCE.editFolder() + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")"));
		
		form.addField(String.class, ReportFolderDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.name()); 
		
		form.addField(String.class, ReportFolderDtoPA.INSTANCE.description(), BaseMessages.INSTANCE.propertyDescription(), new SFFCTextAreaImpl());
	}



}