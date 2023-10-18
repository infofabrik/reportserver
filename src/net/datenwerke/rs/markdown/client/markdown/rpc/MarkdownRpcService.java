package net.datenwerke.rs.markdown.client.markdown.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;

@RemoteServiceRelativePath("markdown")
public interface MarkdownRpcService extends RemoteService{

   public String loadMarkdownAsHtml(FileServerFileDto file);
}
