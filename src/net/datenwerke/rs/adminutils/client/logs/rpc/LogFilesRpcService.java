package net.datenwerke.rs.adminutils.client.logs.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;

@RemoteServiceRelativePath("logfiles")
public interface LogFilesRpcService extends RemoteService {
	
	List<String> readLastLines(String filename) throws ServerCallFailedException;
	
	void emailFile(String filename) throws ServerCallFailedException;

}