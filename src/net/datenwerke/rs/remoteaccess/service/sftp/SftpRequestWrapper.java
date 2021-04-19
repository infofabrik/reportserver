package net.datenwerke.rs.remoteaccess.service.sftp;

import java.io.IOException;
import java.util.concurrent.Callable;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.datenwerke.rs.remoteaccess.service.sftp.mockup.SftpServletRequest;
import net.datenwerke.rs.utils.mockrequest.MockServletResponse;

import org.apache.sshd.common.Session;
import org.apache.sshd.common.Session.AttributeKey;
import org.apache.sshd.server.session.ServerSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.servlet.GuiceFilter;

public class SftpRequestWrapper {

	private static final AttributeKey<ServletRequest> SERVLET_REQUEST_KEY = new AttributeKey<ServletRequest>();
	private static final AttributeKey<ServletResponse> SERVLET_RESPONSE_KEY = new AttributeKey<ServletResponse>();
	
	protected static final Logger logger = LoggerFactory.getLogger(SftpRequestWrapper.class.getName());
	
	private static ThreadLocal<Object> resultHolder = new ThreadLocal<Object>();
	
	public static <T> Callable<T> wrapRequest(final Callable<T> callable, final ServerSession session){
		return new Callable<T>() {
			@Override
			public T call() throws Exception {
				if(null == session.getAttribute(SERVLET_REQUEST_KEY))
					init(session);
				
				SftpServletRequest req = (SftpServletRequest) session.getAttribute(SERVLET_REQUEST_KEY);
				ServletResponse rep = session.getAttribute(SERVLET_RESPONSE_KEY);
				req.setSftpSession(session);
				
				Object previous = resultHolder.get();
				new GuiceFilter().doFilter(req, rep, new FilterChain() {
					@Override
					public void doFilter(ServletRequest req, ServletResponse resp)
							throws IOException, ServletException {
						try {
							resultHolder.set(callable.call());
						} catch (Exception e) {
							if(e instanceof IOException)
								throw (IOException)e;
							logger.warn( e.getMessage(), e);
						}
					}
				});
				T result = (T) resultHolder.get();
				resultHolder.set(previous);
				
				return result;
			}
		};
	}

	protected static void init(Session session) {
		SftpServletRequest req = new SftpServletRequest();
		MockServletResponse resp = new MockServletResponse();
		
		session.setAttribute(SERVLET_REQUEST_KEY, req);
		session.setAttribute(SERVLET_RESPONSE_KEY, resp);
	}
}
