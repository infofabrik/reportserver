package net.datenwerke.rs.legacysaiku.server.rest.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.security.service.authenticator.AuthenticatorService;

@Singleton
@Path("/legacysaiku/{username}/session")
public class SessionResource  {
	
	private final Provider<AuthenticatorService> authenticatorService;
	private final LocalizationServiceImpl localizationService;


	@Inject
	public SessionResource(
			Provider<AuthenticatorService> authenticatorService, 
			LocalizationServiceImpl localizationService) {
		this.authenticatorService = authenticatorService;
		this.localizationService = localizationService;
	}

	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Response login(
			@Context HttpServletRequest req,
			@FormParam("username") String username, 
			@FormParam("password") String password) 
	{
		try {
			return Response.ok().build();
		}
		catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String,Object> getSession(@Context HttpServletRequest req, @PathParam("username") String username) {
		Map<String, Object> session = new HashMap<String, Object>();

		session.put("language", localizationService.getUserLocal());
		session.put("username", username); // username identifies a saiku session -> ReportExecuteToken
		session.put("sessionid", UUID.randomUUID().toString());
		
		List<String> roles = new ArrayList<String>();
		roles.add("ROLE_USER");
		session.put("roles", roles);

		return session;
	}
	
	@DELETE
	public Response logout(@Context HttpServletRequest req) 
	{
//		sessionService.logout(req);
		//		NewCookie terminate = new NewCookie(TokenBasedRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY, null);

		return Response.ok().build();

	}
}
