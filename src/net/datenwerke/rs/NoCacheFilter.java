package net.datenwerke.rs;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Singleton;

@Singleton
public class NoCacheFilter implements Filter{

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		httpResponse.setDateHeader("Expires", 0);
		httpResponse.setHeader("Pragma", "no-cache");
		httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

		filterChain.doFilter(request, response);	
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
			
	}

}
