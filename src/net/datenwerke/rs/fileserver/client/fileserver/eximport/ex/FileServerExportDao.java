package net.datenwerke.rs.fileserver.client.fileserver.eximport.ex;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.fileserver.client.fileserver.eximport.ex.rpc.FileServerExportRpcServiceAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class FileServerExportDao extends Dao  {

	private final FileServerExportRpcServiceAsync rpcService;

	@Inject
	public FileServerExportDao(FileServerExportRpcServiceAsync rpcService) {
		this.rpcService = rpcService;
	}
	
	public void quickExport(AbstractFileServerNodeDto dto, AsyncCallback<Void> callback){
		rpcService.quickExport(dto, transformAndKeepCallback(callback));
	}
	
	public void loadResult(AsyncCallback<String> callback){
		rpcService.loadResult(transformAndKeepCallback(callback));
	}
	
}
