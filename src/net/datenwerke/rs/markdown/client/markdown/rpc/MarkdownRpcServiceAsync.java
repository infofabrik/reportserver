package net.datenwerke.rs.markdown.client.markdown.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;

public interface MarkdownRpcServiceAsync {

   void loadMarkdownAsHtml(FileServerFileDto file, AsyncCallback<String> callback);
}
