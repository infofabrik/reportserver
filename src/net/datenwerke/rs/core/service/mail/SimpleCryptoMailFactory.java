package net.datenwerke.rs.core.service.mail;

import javax.mail.Session;

public interface SimpleCryptoMailFactory {
   public SimpleCryptoMail create(Session session);
}
