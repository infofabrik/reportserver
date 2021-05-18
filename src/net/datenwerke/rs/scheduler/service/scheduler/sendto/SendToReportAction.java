package net.datenwerke.rs.scheduler.service.scheduler.sendto;

import static java.util.stream.Collectors.toMap;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.sendto.hooks.SendToTargetProviderHook;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractAction;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.exceptions.ActionExecutionException;
import net.datenwerke.security.service.authenticator.AuthenticatorService;

@Entity
@Table(name = "SCHED_ACTION_SEND_TO")
@Inheritance(strategy = InheritanceType.JOINED)
public class SendToReportAction extends AbstractAction {

   @Transient
   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   @Transient
   @Inject
   private HookHandlerService hookHandlerService;
   @Inject
   @Transient
   private Provider<AuthenticatorService> authenticatorServiceProvider;

   private String sendToId;

   @JoinColumn(name = "SEND_TO")
   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
   private Set<SendToReportActionValue> configValues;

   public String getSendToId() {
      return sendToId;
   }

   public void setSendToId(String sendToId) {
      this.sendToId = sendToId;
   }

   public Set<SendToReportActionValue> getConfigValues() {
      return configValues;
   }

   public void setConfigValues(Set<SendToReportActionValue> values) {
      this.configValues = values;
   }

   @Transient
   public void setConfigValues(Map<String, String> values) {
      if (null == this.configValues)
         this.configValues = new HashSet<SendToReportActionValue>();
      this.configValues.clear();

      if (null != values) {
         for (String key : values.keySet()) {
            String value = values.get(key);
            this.configValues.add(new SendToReportActionValue(key, value));
         }
      }
   }

   public Map<String, String> getValueMap() {
      if (null == configValues || configValues.isEmpty())
         return null;

      return configValues
         .stream()
         .collect(toMap(SendToReportActionValue::getValueId, SendToReportActionValue::getTheValue));
   }

   public Logger getLogger() {
      return logger;
   }

   @Override
   public void doExecute(AbstractJob abstractJob) throws ActionExecutionException {
      if (!(abstractJob instanceof ReportExecuteJob) || null == sendToId)
         return;

      final ReportExecuteJob reportJob = (ReportExecuteJob) abstractJob;

      try {
         try {
            authenticatorServiceProvider.get().setAuthenticatedInThread(reportJob.getExecutor().getId());

            Optional<SendToTargetProviderHook> sendToHooker = hookHandlerService.getHookers(SendToTargetProviderHook.class)
               .stream()
               .filter(hooker -> sendToId.equals(hooker.getId()))
               .findAny();
            
            if (sendToHooker.isPresent()) {
               sendToHooker.get().scheduledSendTo(reportJob.getExecutedReport(), reportJob.getReport(), reportJob,
                     reportJob.getOutputFormat(), getValueMap());
               return;
            } else
               throw new ActionExecutionException("Could not find target handler for id: " + sendToId);
            
         } finally {
            authenticatorServiceProvider.get().logoffUserInThread();
         }
      } catch (RuntimeException e) {
         throw new ActionExecutionException(e);
      }
   }

}
