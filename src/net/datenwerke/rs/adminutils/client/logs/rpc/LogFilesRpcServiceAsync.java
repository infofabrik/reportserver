package net.datenwerke.rs.adminutils.client.logs.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LogFilesRpcServiceAsync {

   public void readLastLines(String filename, AsyncCallback<List<String>> callback);

   void emailFile(String filename, AsyncCallback<Void> callback);

}
