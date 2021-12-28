package net.datenwerke.security.service.authenticator;

import net.datenwerke.security.service.usermanager.entities.User;

public interface RequestUserCache {

   public User getUser(Long id);
}
