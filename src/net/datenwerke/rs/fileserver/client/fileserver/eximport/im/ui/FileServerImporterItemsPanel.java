package net.datenwerke.rs.fileserver.client.fileserver.eximport.im.ui;

import java.util.List;

import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.resources.BaseResources;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto;
import net.datenwerke.rs.fileserver.client.fileserver.eximport.im.FileServerImportDao;
import net.datenwerke.rs.fileserver.client.fileserver.eximport.im.dto.FileServerImportConfigDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;
import net.datenwerke.treedb.ext.client.eximport.im.ui.ImporterItemsPanel;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.sencha.gxt.data.shared.IconProvider;


public class FileServerImporterItemsPanel extends ImporterItemsPanel<FileServerImportConfigDto> {

	private final FileServerImportDao importDao;
	
	@Inject
	public FileServerImporterItemsPanel(
		FileServerImportDao importDao
		) {
		super();
		
		/* store objects */
		this.importDao = importDao;
		
		/* load data */
		loadData();
	}


	private void loadData() {
		importDao.loadTree(new RsAsyncCallback<List<ImportTreeModel>>(){
			@Override
			public void onSuccess(List<ImportTreeModel> roots) {
				buildTree(roots);
			}
		});
	}
	
	protected void configureTree() {
		super.configureTree();
		
		tree.setIconProvider(new IconProvider<ImportTreeModel>() {
			@Override
			public ImageResource getIcon(ImportTreeModel model) {
				if(FileServerFolderDto.class.getName().equals(model.getType()))
					return BaseIcon.FOLDER_O.toImageResource();
				return BaseIcon.FILE.toImageResource();
			}
		});
	}
}
