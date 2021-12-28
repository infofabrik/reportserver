package net.datenwerke.rs.incubator.client.scriptdatasource.ui;

import com.google.inject.Inject;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.simpleform.SFFCGenericTreeNode;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.provider.annotations.FileServerTreeBasic;
import net.datenwerke.rs.incubator.client.scriptdatasource.dto.pa.ScriptDatasourceDtoPA;
import net.datenwerke.rs.incubator.client.scriptdatasource.locale.ScriptDatasourceMessages;

/**
 * 
 *
 */
public class ScriptDatasourceForm extends SimpleFormView{

	@Inject @FileServerTreeBasic UITree tree;
	
	@Override
	protected void configureSimpleForm(SimpleForm form) {
		/* configure form */
		form.setHeading(ScriptDatasourceMessages.INSTANCE.editDataSource() + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")")); 
		
		/* name name */
		form.addField(String.class, ScriptDatasourceDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.propertyName()); 
		
		form.addField(String.class, ScriptDatasourceDtoPA.INSTANCE.description(), BaseMessages.INSTANCE.propertyDescription(), new SFFCTextAreaImpl());
		
		form.setFieldWidth(0.4);
		form.addField(FileServerFileDto.class, ScriptDatasourceDtoPA.INSTANCE.script(), ScriptDatasourceMessages.INSTANCE.scriptLabel(), new SFFCGenericTreeNode(){
			@Override
			public UITree getTreeForPopup() {
				return tree;
			}
		});
		
		form.addField(Boolean.class, ScriptDatasourceDtoPA.INSTANCE.defineAtTarget(), ScriptDatasourceMessages.INSTANCE.defineAtTargetLabel());
		
		form.setFieldWidth(0.4);
		form.addField(Integer.class, ScriptDatasourceDtoPA.INSTANCE.databaseCache(), ScriptDatasourceMessages.INSTANCE.databaseCacheLabel());
		form.setFieldWidth(1);
	}



	
}
