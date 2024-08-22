package net.datenwerke.rs.dot.client.dot.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;

@RemoteServiceRelativePath("dot")
public interface DotRpcService extends RemoteService{

   public String loadDotAsSVG(FileServerFileDto file) throws ServerCallFailedException;
}
