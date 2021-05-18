package net.datenwerke.rs.emaildatasink.service.emaildatasink;

import com.google.inject.assistedinject.Assisted;

import net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.EmailDatasink;

public interface EmailDatasinkSessionFactory {
   public EmailDatasinkSessionProvider create(@Assisted EmailDatasink emailDatasink);
}
