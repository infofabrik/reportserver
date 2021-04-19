package net.datenwerke.usermanager.ext.client.eximport.im.ui;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.simpleform.SFFCGenericTreeNode;
import net.datenwerke.rs.eximport.client.eximport.im.exceptions.NotProperlyConfiguredException;
import net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto;
import net.datenwerke.security.ext.client.usermanager.locale.UsermanagerMessages;
import net.datenwerke.security.ext.client.usermanager.provider.annotations.UserManagerTreeFolders;
import net.datenwerke.treedb.ext.client.eximport.im.ui.ImporterMainPropertiesPanel;
import net.datenwerke.usermanager.ext.client.eximport.im.dto.UserManagerImportConfigDto;

import com.google.inject.Inject;
import com.google.inject.Provider;


public class UserManagerImporterMainPropertiesPanel extends
		ImporterMainPropertiesPanel<UserManagerImportConfigDto> {

	private final Provider<UITree> treeProvider;
	
	protected String parentKey;
	protected String defaultDatasource;
	
	@Inject
	public UserManagerImporterMainPropertiesPanel(
		@UserManagerTreeFolders Provider<UITree> treeProvider
		) {

		/* store objects */
		this.treeProvider = treeProvider;
		
		/* init */
		initializeUI();
	}

	@Override
	public void populateConfig(UserManagerImportConfigDto config) throws NotProperlyConfiguredException {
		super.populateConfig(config);
		
		OrganisationalUnitDto parent = (OrganisationalUnitDto) form.getValue(parentKey);
		config.setParent(parent);
	}
	
	@Override
	public void validateConfig(UserManagerImportConfigDto config)
			throws NotProperlyConfiguredException {
		if(null == config.getParent() && ! config.getConfigs().isEmpty())
			throw new NotProperlyConfiguredException(UsermanagerMessages.INSTANCE.importConfigFailureNoParent());
	}

	@Override
	protected void configureForm() {
		super.configureForm();

		parentKey = form.addField(OrganisationalUnitDto.class, UsermanagerMessages.INSTANCE.importWhereTo(), new SFFCGenericTreeNode(){
			@Override
			public UITree getTreeForPopup() {
				return treeProvider.get();
			}
		});
	}

	@Override
	protected String getDescription() {
		return UsermanagerMessages.INSTANCE.importMainPropertiesDescription();
	}

	@Override
	protected String getHeadline() {
		return UsermanagerMessages.INSTANCE.importMainPropertiesHeadline();
	}
}
