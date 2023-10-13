package net.datenwerke.rs.dot.client.dot.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;

public interface DotRpcServiceAsync {

   void loadDotAsSVG(FileServerFileDto file, AsyncCallback<String> callback);
}
