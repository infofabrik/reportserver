package net.datenwerke.rs.keyutils.client.keyutils.rpc;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("keyutils")
public interface KeyUtilsRpcService extends RemoteService{

   String generateDefaultKey();
}
