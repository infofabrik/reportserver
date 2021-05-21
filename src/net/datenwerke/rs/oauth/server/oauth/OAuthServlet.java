package net.datenwerke.rs.oauth.server.oauth;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.oauth.service.oauth.OAuthAuthenticatable;
import net.datenwerke.rs.oauth.service.oauth.OAuthAuthenticationService;

@Singleton
public class OAuthServlet extends HttpServlet {

   /**
    * 
    */
   private static final long serialVersionUID = 7216005763523739465L;

   private DatasinkService datasinkService;
   private OAuthAuthenticationService oAuthAuthenticationService;

   @Inject
   public OAuthServlet(DatasinkService datasinkService, OAuthAuthenticationService oAuthAuthenticationService) {
      this.datasinkService = datasinkService;
      this.oAuthAuthenticationService = oAuthAuthenticationService;
   }

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      Objects.requireNonNull(req.getParameter("code"));
      Objects.requireNonNull(req.getParameter("state"));

      String authenticationCode = null;
      String datasinkId = null;
      String path = null;
      String redirectUri = null;

      authenticationCode = req.getParameter("code");

      String state = req.getParameter("state");
      try {
         JSONObject jsonObject = new JSONObject(state);
         datasinkId = jsonObject.getString("datasinkId");
         path = jsonObject.getString("path");
         redirectUri = jsonObject.getString("redirect_uri");
         Objects.requireNonNull(datasinkId);
         Objects.requireNonNull(path);
         Objects.requireNonNull(redirectUri);
      } catch (JSONException e) {
         throw new ServletException("Error while reading json parameter state", e);
      }

      if (authenticationCode != null && datasinkId != null) {
         DatasinkDefinition datasinkDefinition = datasinkService.getDatasinkById(Long.parseLong(datasinkId));
         Objects.requireNonNull(datasinkDefinition);
         if (!(datasinkDefinition instanceof OAuthAuthenticatable))
            throw new IllegalArgumentException("Datasink must be of type " + OAuthAuthenticatable.class);

         OAuthAuthenticatable oauthDatasink = (OAuthAuthenticatable) datasinkDefinition;
         try {
            oAuthAuthenticationService.generateRefreshToken(authenticationCode, oauthDatasink, redirectUri);
            resp.sendRedirect(req.getContextPath() + "#" + path);
         } catch (InterruptedException | ExecutionException e) {
            throw new ServletException("Error while generating refresh token");
         }
      }

   }
}
