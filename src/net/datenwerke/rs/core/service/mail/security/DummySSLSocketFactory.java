package net.datenwerke.rs.core.service.mail.security;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http://java.sun.com/products/javamail/SSLNOTES.txt
 * 
 *
 */
public class DummySSLSocketFactory extends SSLSocketFactory {
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	private SSLSocketFactory factory;
	
	public DummySSLSocketFactory() {
		try {
		    SSLContext sslcontext = SSLContext.getInstance("TLS"); //$NON-NLS-1$
		    sslcontext.init(null,
					 new TrustManager[] { new DummyManager()},
					 null);
		    factory = (SSLSocketFactory)sslcontext.getSocketFactory();
		} catch(Exception e) {
			logger.warn( e.getMessage(), e);
		}
    }

	@Override
    public Socket createSocket() throws IOException {
		return factory.createSocket();
    }

	@Override
    public Socket createSocket(Socket socket, String s, int i, boolean flag)
				throws IOException {
		return factory.createSocket(socket, s, i, flag);
    }

	@Override
    public Socket createSocket(InetAddress inaddr, int i,
				InetAddress inaddr1, int j) throws IOException {
		return factory.createSocket(inaddr, i, inaddr1, j);
    }

	@Override
    public Socket createSocket(InetAddress inaddr, int i)
				throws IOException {
		return factory.createSocket(inaddr, i);
    }

	@Override
    public Socket createSocket(String s, int i, InetAddress inaddr, int j)
				throws IOException {
		return factory.createSocket(s, i, inaddr, j);
    }

	@Override
    public Socket createSocket(String s, int i) throws IOException {
		return factory.createSocket(s, i);
    }

	@Override
    public String[] getDefaultCipherSuites() {
		return factory.getDefaultCipherSuites();
    }

	@Override
    public String[] getSupportedCipherSuites() {
		return factory.getSupportedCipherSuites();
    }

	

}
