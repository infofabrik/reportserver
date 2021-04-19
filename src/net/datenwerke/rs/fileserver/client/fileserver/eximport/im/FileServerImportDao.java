package net.datenwerke.rs.fileserver.client.fileserver.eximport.im;

import java.util.List;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.fileserver.client.fileserver.eximport.im.rpc.FileServerImportRpcServiceAsync;
import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;


public class FileServerImportDao extends Dao {

	private final FileServerImportRpcServiceAsync rpcService;

	@Inject
	public FileServerImportDao(FileServerImportRpcServiceAsync rpcService) {
		this.rpcService = rpcService;
	}
	
	public void loadTree(AsyncCallback<List<ImportTreeModel>> callback){
		rpcService.loadTree(transformAndKeepCallback(callback));
	}
	
}
