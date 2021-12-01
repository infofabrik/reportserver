package net.datenwerke.rs.utils.misc;

import java.io.IOException;
import java.util.Date;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Singleton
public class TracerServlet extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException {

		System.out.println("REQ: " + new Date() + getPM(req));
	}

	private String getPM(HttpServletRequest req) {
		StringBuilder b = new StringBuilder();
		
		for(Object k : req.getParameterMap().keySet()){
			b.append(k).append(": ").append(req.getParameter((String)k)).append(" ");
		}
	return b.toString();
	}

}
