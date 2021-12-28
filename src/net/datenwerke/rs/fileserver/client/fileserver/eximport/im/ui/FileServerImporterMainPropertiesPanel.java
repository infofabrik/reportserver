package net.datenwerke.rs.fileserver.client.fileserver.eximport.im.ui;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.simpleform.SFFCGenericTreeNode;
import net.datenwerke.rs.eximport.client.eximport.im.exceptions.NotProperlyConfiguredException;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto;
import net.datenwerke.rs.fileserver.client.fileserver.eximport.im.dto.FileServerImportConfigDto;
import net.datenwerke.rs.fileserver.client.fileserver.locale.FileServerMessages;
import net.datenwerke.rs.fileserver.client.fileserver.provider.annotations.FileServerTreeFolders;
import net.datenwerke.treedb.ext.client.eximport.im.ui.ImporterMainPropertiesPanel;


public class FileServerImporterMainPropertiesPanel extends
		ImporterMainPropertiesPanel<FileServerImportConfigDto> {

	private final Provider<UITree> treeProvider;
	
	protected String parentKey;
	protected String defaultDatasource;
	
	@Inject
	public FileServerImporterMainPropertiesPanel(
		@FileServerTreeFolders Provider<UITree> treeProvider
		) {

		/* store objects */
		this.treeProvider = treeProvider;
		
		/* init */
		initializeUI();
	}

	@Override
	public void populateConfig(FileServerImportConfigDto config) throws NotProperlyConfiguredException {
		super.populateConfig(config);
		
		FileServerFolderDto parent = (FileServerFolderDto) form.getValue(parentKey);
		
		config.setParent(parent);
	}
	
	@Override
	public void validateConfig(FileServerImportConfigDto config)
			throws NotProperlyConfiguredException {
		if(null == config.getParent() && ! config.getConfigs().isEmpty())
			throw new NotProperlyConfiguredException(FileServerMessages.INSTANCE.importConfigFailureNoParent());
	}

	@Override
	protected void configureForm() {
		super.configureForm();

		parentKey = form.addField(FileServerFolderDto.class, FileServerMessages.INSTANCE.importWhereTo(), new SFFCGenericTreeNode(){
			@Override
			public UITree getTreeForPopup() {
				return treeProvider.get();
			}
		});
	}

	@Override
	protected String getDescription() {
		return FileServerMessages.INSTANCE.importMainPropertiesDescription();
	}

	@Override
	protected String getHeadline() {
		return FileServerMessages.INSTANCE.importMainPropertiesHeadline();
	}
}
