package net.datenwerke.rs.utils.misc;

import javax.inject.Inject;
import javax.servlet.ServletContext;

public class MimeUtils {

	private ServletContext servletContext;

	@Inject
	public MimeUtils(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	public String getMimeTypeByExtension(String filename) {
		String mime = servletContext.getMimeType(filename);
		if(null == mime){
			if(filename.endsWith(".groovy")){
				return "text/groovy";
			}else if(filename.endsWith(".rs")){
				return "text/groovy";
			}else if(filename.endsWith(".cf")){
				return "text/xml";
			}else if(filename.endsWith(".cf.disabled")){
				return "text/xml";
			}
		}
		
		return mime;
	}
}
