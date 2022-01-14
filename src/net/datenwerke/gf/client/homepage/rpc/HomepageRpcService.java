package net.datenwerke.gf.client.homepage.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("homepage")
public interface HomepageRpcService extends RemoteService {

   public String getPageTitle();

   public boolean isStartupComplete();

   String getSessionRedirect();

   void assertAllowsRedirect(String redir);

}
