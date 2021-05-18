package net.datenwerke.rs.core.service.mail;

import javax.mail.Session;

public interface SimpleMailFactory {
   public SimpleMail create(Session session);
}
