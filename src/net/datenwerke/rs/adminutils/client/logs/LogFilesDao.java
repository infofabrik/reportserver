package net.datenwerke.rs.adminutils.client.logs;

import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.rs.adminutils.client.logs.rpc.LogFilesRpcServiceAsync;

public class LogFilesDao extends Dao {
	private final LogFilesRpcServiceAsync rpcService;

	@Inject
	public LogFilesDao(LogFilesRpcServiceAsync rpcService) {
		this.rpcService = rpcService;
	}

	public void readLastLines(String filePath, RsAsyncCallback<List<String>> callback) {
		rpcService.readLastLines(filePath, transformAndKeepCallback(callback));
	}
	
	public void emailFile(String filename, RsAsyncCallback<Void> callback) {
		rpcService.emailFile(filename, transformAndKeepCallback(callback));
	}

}
