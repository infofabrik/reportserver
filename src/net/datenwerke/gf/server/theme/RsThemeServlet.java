package net.datenwerke.gf.server.theme;

import java.io.IOException;

import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.theme.ThemeService;
import net.datenwerke.security.server.SecuredHttpServlet;
import net.datenwerke.security.service.security.annotation.SecurityChecked;

@Singleton
public class RsThemeServlet  extends SecuredHttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -541050931207667866L;

	public static final String PROPERTY_LOGO = "logo";
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	private final Provider<ThemeService> themeServiceProvider;
	
	@Inject
	public RsThemeServlet(
		Provider<ThemeService> themeServiceProvider
		){
		
		/* store objects */
		this.themeServiceProvider = themeServiceProvider;
	}
	
	@Override
	@SecurityChecked(bypass=true)
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		if("true".equals(request.getParameter(PROPERTY_LOGO))){
			getLogo(response);
			return;
		}
		
		ThemeService themeService = themeServiceProvider.get();
		String theme = themeService.getTheme();
		
		response.setContentType("text/css");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Disposition", "inline; filename=\"rstheme.css\"");
		response.setHeader("Content-Length", theme.getBytes().length + "");
		
		response.getOutputStream().print(theme);
	}

	protected void getLogo(HttpServletResponse response) throws IOException {
		ThemeService themeService = themeServiceProvider.get();
		byte[] logo = themeService.getLogo();
		
		response.setHeader("Content-Disposition", "inline; filename=\"logo\"");
		response.setHeader("Content-type", "image");
		response.setHeader("Content-Length", logo.length + "");
				
		response.getOutputStream().write(logo);
	}
}
