package net.datenwerke.rs.scheduler.service.scheduler.mail;

import static java.util.stream.Collectors.toSet;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Set;

import javax.inject.Inject;
import javax.mail.internet.InternetAddress;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datenwerke.rs.core.service.mail.MailService;
import net.datenwerke.rs.core.service.mail.SimpleMail;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.EmailDatasink;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.security.service.usermanager.entities.User;

public class SchedulerMailHelper {

   @Transient
   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   private MailService mailService;

   @Inject
   public SchedulerMailHelper(
         MailService mailService
         ) {
      this.mailService = mailService;
   }

   public SimpleMail prepareSimpleMail(ReportExecuteJob job) {
      /* create new object */
      SimpleMail mail = mailService.newSimpleMail();

      /* set from */
      InternetAddress mailFrom = getMailFrom(job);
      mail.setFrom(mailFrom, true);

      /* set recipients */
      mail.setToRecipients(new ArrayList<>(getRecipientEmailList(job)));

      return mail;
   }

   public InternetAddress getMailFrom(ReportExecuteJob job) {
      User sender = job.getExecutor();
      String mailFrom = ((User) sender).getEmail();

      EmailDatasink defaultEmailDatasink = mailService.loadDefaultEmailDatasink();
      
      if (null == mailFrom || "".equals(mailFrom)) 
         mailFrom = defaultEmailDatasink.getSender();

      if (null == mailFrom || "".equals(mailFrom)) {
         IllegalArgumentException ex = new IllegalArgumentException(
               "Sender does not have email address: " + sender.getId());
         logger.warn(ex.getMessage(), ex);
         throw ex;
      }
      try {
         return new InternetAddress(mailFrom, sender.getFirstname() + " " + sender.getLastname());
      } catch (UnsupportedEncodingException e) {
         logger.warn(e.getMessage(), e);
         throw new RuntimeException("Failed to set mail sender address ", e);
      }
   }

   /**
    * Gets all non-empty, unique email addresses of recipients in the given
    * {@link ReportExecuteJob}.
    * 
    * @param job the job containing the recipients
    * @return all non-empty unique email addresses
    */
   public Set<String> getRecipientEmailList(ReportExecuteJob job) {
      return job.getRecipients().stream().filter(user -> null != user.getEmail() && !"".equals(user.getEmail().trim()))
            .map(User::getEmail).collect(toSet()); /* email address should not contain duplicates */
   }

}
