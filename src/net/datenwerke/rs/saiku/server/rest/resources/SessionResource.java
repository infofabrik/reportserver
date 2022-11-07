/*  
 *   Copyright 2012 OSBI Ltd
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package net.datenwerke.rs.saiku.server.rest.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Provider;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.security.service.authenticator.AuthenticatorService;


/**
 * Saiku Session Endpoints
 */
//@Component
@Path("/saiku/{username}/session")
public class SessionResource  {

   private static final Logger log = LoggerFactory.getLogger(SessionResource.class);

   private final Provider<AuthenticatorService> authenticatorService;
   private final LocalizationServiceImpl localizationService;

   @Inject
   public SessionResource(Provider<AuthenticatorService> authenticatorService,
         LocalizationServiceImpl localizationService) {
      this.authenticatorService = authenticatorService;
      this.localizationService = localizationService;
   }
   
//	private static final Logger log = LoggerFactory.getLogger(SessionResource.class);
//
//	private ISessionService sessionService;
//    private UserService userService;
//
//  public ISessionService getSessionService() {
//	return sessionService;
//  }
//
//  public void setSessionService(ISessionService ss) {
//		this.sessionService = ss;
//	}
//
//    public void setUserService(UserService us) {
//        userService = us;
//    }

  /**
   * Login to Saiku
   * @summary Login
   * @param req Servlet request
   * @param username Username
   * @param password Password
   * @return A 200 response
   */
    @POST
	@Consumes("application/x-www-form-urlencoded")
	public Response login(
	      @Context HttpServletRequest req, @FormParam("username") String username,
	         @FormParam("password") String password) 
	{
		try {
//		  sessionService.login(req, username, password);
		  return Response.ok().build();
		}
		catch (Exception e) {
			log.debug("Error logging in:" + username, e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getLocalizedMessage()).build();
		}
	}

  /**
   * Clear logged in users session.
   * @summary Login
   * @param req Servlet request
   * @param username Username
   * @param password Password
   * @return A 200 response
   */
  @POST
  @Path("/clear")
  @Consumes("application/x-www-form-urlencoded")
  public Response clearSession(
        @Context HttpServletRequest req, @FormParam("username") String username,
        @FormParam("password") String password)
  {
     if (!authenticatorService.get().isAuthenticated())
        throw new IllegalStateException("not authenticated");
     
	try {
//	  sessionService.clearSessions(req, username, password);
	  return Response.ok("Session cleared").build();
	}
	catch (Exception e) {
	  log.debug("Error clearing sessions for:" + username, e);
	  return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getLocalizedMessage()).build();
	}
  }

  /**
   * Get the session in the request
   * @summary Get session
   * @param req The servlet request
   * @return A reponse with a session map
   */
	@GET
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.APPLICATION_JSON)
//    @ReturnType("java.util.Map<String, Object>")
    public Response getSession(@Context HttpServletRequest req, @PathParam("username") String username) {

//	  Map<String, Object> sess = null;
//	  try {
//		sess = sessionService.getSession();
//	  } catch (Exception e) {
//		return Response.serverError().entity(e.getLocalizedMessage()).build();
//	  }
//	  try {
//			String acceptLanguage = req.getLocale().getLanguage();
//			if (StringUtils.isNotBlank(acceptLanguage)) {
//				sess.put("language", acceptLanguage);
//			}
//		} catch (Exception e) {
//			log.debug("Cannot get language!", e);
//		}
//
//        try {
//            sess.put("isadmin", userService.isAdmin());
//        }
//        catch (Exception e){
//            //throw new UnsupportedOperationException();
//        }
//        try {
//            userService.checkFolders();
//        }
//        catch (Exception e){
//            //TODO detect if plugin or not.
//        }
//
//        return Response.ok().entity(sess).build();
	   if (!authenticatorService.get().isAuthenticated())
	         throw new IllegalStateException("not authenticated");

	      Map<String, Object> session = new HashMap<String, Object>();

	      session.put("language", localizationService.getUserLocal());
	      session.put("username", username); // username identifies a saiku session -> ReportExecuteToken
	      session.put("sessionid", UUID.randomUUID().toString());

	      List<String> roles = new ArrayList<String>();
	      roles.add("ROLE_USER");
	      session.put("roles", roles);

	      return Response.ok().entity(session).build();
	}

  /**
   * Logout of the Session
   * @summary Logout
   * @param req The servlet request
   * @return A 200 response.
   */
	@DELETE
	public Response logout(@Context HttpServletRequest req) 
	{
//		sessionService.logout(req);
//		//		NewCookie terminate = new NewCookie(TokenBasedRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY, null);

		return Response.ok().build();
	}


}
