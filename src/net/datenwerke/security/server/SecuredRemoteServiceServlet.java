package net.datenwerke.security.server;

import javax.servlet.http.HttpServletRequest;

import net.datenwerke.gf.base.server.DwRemoteServiceServlet;
import net.datenwerke.gf.base.server.RemoteRequest;
import net.datenwerke.gf.base.server.gwtstacktrace.annotations.GWTInitClientException;
import net.datenwerke.security.service.security.annotation.SecurityChecked;

import com.google.inject.Singleton;

@Singleton
@SecurityChecked(
	bypassInheritedMethods = true
)
@GWTInitClientException
public class SecuredRemoteServiceServlet extends DwRemoteServiceServlet implements RemoteRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5531482963721209584L;

	public HttpServletRequest getRequest() {
	    return super.getThreadLocalRequest();
	}
}
