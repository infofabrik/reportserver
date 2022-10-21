package net.datenwerke.rs.authenticator.client.login.dto;

import net.datenwerke.security.client.login.AuthToken;

public interface NamedUserAuthToken extends AuthToken {

   public String getUsername();

}
