package net.datenwerke.gf.client.fileselection;

import java.util.ArrayList;

import net.datenwerke.gf.client.fileselection.dto.SelectedFileWrapper;
import net.datenwerke.gf.client.fileselection.rpc.FileSelectionRpcServiceAsync;
import net.datenwerke.gxtdto.client.dtomanager.Dao;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class FileSelectionDao extends Dao {

	private final FileSelectionRpcServiceAsync rpcService;

	@Inject
	public FileSelectionDao(FileSelectionRpcServiceAsync rpcService) {
		super();
		this.rpcService = rpcService;
	}

	public void submit(ArrayList<SelectedFileWrapper> data,	FileSelectionConfig config, AsyncCallback<Void> callback) {
		rpcService.submit(data, config, callback);
	}
	
	
}
