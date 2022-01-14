package net.datenwerke.security.service.authenticator;

import net.datenwerke.security.client.login.AuthToken;

public interface ReportServerPAM {

   public AuthenticationResult authenticate(AuthToken[] tokens);

   public String getClientModuleName();

}
