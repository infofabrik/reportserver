package net.datenwerke.rs.remoteaccess.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.lateinit.LateInitHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.hooks.GeneralInfoCategoryProviderHook;
import net.datenwerke.rs.remoteaccess.service.sftp.SftpService;
import net.datenwerke.rs.remoteaccess.service.sftp.genrights.SftpSecurityTarget;
import net.datenwerke.rs.remoteaccess.service.sftp.hookers.SftpServerCategoryProviderHooker;
import net.datenwerke.security.service.security.SecurityService;

public class RemoteAccessStartup {

   protected final Logger logger = LoggerFactory.getLogger(getClass().getName());

   @Inject
   public RemoteAccessStartup(
         final SecurityService securityService, 
         final HookHandlerService hookHandlerService, 
         final SftpService sftpService,
         final Provider<SftpServerCategoryProviderHooker> generalInfoSftpServerCategoryProviderHooker
         ) {

      securityService.registerSecurityTarget(SftpSecurityTarget.class);
      
      hookHandlerService.attachHooker(GeneralInfoCategoryProviderHook.class, generalInfoSftpServerCategoryProviderHooker,
            HookHandlerService.PRIORITY_LOW + 40);

      hookHandlerService.attachHooker(LateInitHook.class, () -> {
         try {
            sftpService.start();
         } catch (Exception e) {
            logger.warn("Failed to start SFTP Server", e);
         }
      });

   }
}
