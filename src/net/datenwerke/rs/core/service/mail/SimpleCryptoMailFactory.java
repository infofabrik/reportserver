package net.datenwerke.rs.core.service.mail;

import javax.mail.Session;

import com.google.inject.assistedinject.Assisted;

public interface SimpleCryptoMailFactory {
   public SimpleCryptoMail create(Session session, @Assisted("from") String from, @Assisted("fromName") String fromName);
}
