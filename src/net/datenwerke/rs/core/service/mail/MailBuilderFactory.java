package net.datenwerke.rs.core.service.mail;

import java.util.List;

import com.google.inject.assistedinject.Assisted;

import net.datenwerke.security.service.usermanager.entities.User;

public interface MailBuilderFactory {
   public MailBuilder create(@Assisted("subject") String subject, @Assisted("body") String body, List<User> recipients);
}
