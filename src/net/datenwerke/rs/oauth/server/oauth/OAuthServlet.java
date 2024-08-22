package net.datenwerke.rs.oauth.server.oauth;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Provider;

import net.datenwerke.gf.service.history.HistoryLink;
import net.datenwerke.gf.service.history.HistoryService;
import net.datenwerke.rs.core.service.datasinkmanager.DatasinkTreeService;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.oauth.service.oauth.OAuthAuthenticatable;
import net.datenwerke.rs.oauth.service.oauth.OAuthAuthenticationService;

@Singleton
public class OAuthServlet extends HttpServlet {

   /**
    * 
    */
   private static final long serialVersionUID = 7216005763523739465L;

   private final Provider<DatasinkTreeService> datasinkServiceProvider;
   private final Provider<OAuthAuthenticationService> oAuthAuthenticationServiceProvider;
   private final Provider<HistoryService> historyServiceProvider;

   @Inject
   public OAuthServlet(Provider<DatasinkTreeService> datasinkServiceProvider,
         Provider<OAuthAuthenticationService> oAuthAuthenticationServiceProvider,
         Provider<HistoryService> historyServiceProvider) {
      this.datasinkServiceProvider = datasinkServiceProvider;
      this.oAuthAuthenticationServiceProvider = oAuthAuthenticationServiceProvider;
      this.historyServiceProvider = historyServiceProvider;
   }

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      Objects.requireNonNull(req.getParameter("code"));
      Objects.requireNonNull(req.getParameter("state"));

      String authenticationCode = null;
      String datasinkId = null;
      String path = null;
      final String redirectUri = oAuthAuthenticationServiceProvider.get().getRedirectUri();
      DatasinkDefinition datasinkDefinition = null;

      authenticationCode = req.getParameter("code");

      String state = req.getParameter("state");
      final ObjectNode jsonObject = new ObjectMapper().readValue(state, ObjectNode.class);
      final String datasinkIdField = "datasinkId";
      if (jsonObject.has(datasinkIdField))
         datasinkId = jsonObject.get(datasinkIdField).asText();
      else
         throw new IllegalArgumentException("No datasink id sent");

      datasinkDefinition = datasinkServiceProvider.get().getDatasinkById(Long.parseLong(datasinkId));
      Objects.requireNonNull(datasinkDefinition);
      if (!(datasinkDefinition instanceof OAuthAuthenticatable))
         throw new IllegalArgumentException("Datasink must be of type " + OAuthAuthenticatable.class);

      List<HistoryLink> historyLinks = historyServiceProvider.get().buildLinksFor(datasinkDefinition);
      if (historyLinks.isEmpty())
         throw new IllegalArgumentException("Datasink does not contain history links");

      path = historyLinks.get(0).getHistoryToken();

      Objects.requireNonNull(path);
      Objects.requireNonNull(redirectUri);

      if (authenticationCode != null && datasinkId != null) {
         OAuthAuthenticatable oauthDatasink = (OAuthAuthenticatable) datasinkDefinition;
         try {
            oAuthAuthenticationServiceProvider.get().generateRefreshToken(authenticationCode, oauthDatasink,
                  redirectUri);
            resp.sendRedirect(req.getContextPath() + "#" + path);
         } catch (Exception e) {
            throw new ServletException("Error while generating refresh token", e);
         }
      } else {
         throw new IllegalStateException("No authentication code or datasinkId");
      }

   }
}
