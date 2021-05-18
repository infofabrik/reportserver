package net.datenwerke.rs.emaildatasink.service.emaildatasink;

import java.util.Objects;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;

import net.datenwerke.rs.core.service.mail.security.DummySSLSocketFactory;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.EmailDatasink;

public class EmailDatasinkSessionProvider implements Provider<Session> {

   private final EmailDatasink emailDatasink;

   @Inject
   public EmailDatasinkSessionProvider(@Assisted EmailDatasink emailDatasink) {
      this.emailDatasink = emailDatasink;
   }

   @Override
   public Session get() {
      final String host = Objects.requireNonNull(emailDatasink.getHost());
      final int port = Objects.requireNonNull(emailDatasink.getPort());
      final String username = emailDatasink.getUsername();
      final boolean ssl = emailDatasink.isSslEnable();
      final boolean enableTLS = emailDatasink.isTlsEnable();
      final boolean requireTLS = emailDatasink.isTlsRequire();

      final String password = emailDatasink.getPassword();
      
      /* prepare properties */
      Properties props = new Properties();
      props.setProperty("mail.smtp.host", String.valueOf(host)); //$NON-NLS-1$
      props.setProperty("mail.smtp.port", String.valueOf(port)); //$NON-NLS-1$
      props.setProperty("mail.smtp.user", null == username ? "" : username); //$NON-NLS-1$
      props.setProperty("mail.smtp.auth", null != password ? "true" : "false"); //$NON-NLS-1$ //$NON-NLS-2$
      if (ssl) {
         props.setProperty("mail.smtp.ssl.enable", "true"); //$NON-NLS-1$ //$NON-NLS-2$
         props.put("mail.smtp.ssl.socketFactory", new DummySSLSocketFactory()); //$NON-NLS-1$
      }
      if (enableTLS)
         props.setProperty("mail.smtp.starttls.enable", "true"); //$NON-NLS-1$ //$NON-NLS-2$
      if (requireTLS)
         props.setProperty("mail.smtp.starttls.required", "true"); //$NON-NLS-1$ //$NON-NLS-2$

      /* create authenticator */
      Authenticator auth = new Authenticator() {

         @Override
         protected PasswordAuthentication getPasswordAuthentication() {
            PasswordAuthentication authenticator = new PasswordAuthentication(null == username ? "" : username,
                  null == password ? "" : password);
            return authenticator;
         }
      };

      /* create session */
      Session session = Session.getInstance(props, auth);
      return session;
   }

}
