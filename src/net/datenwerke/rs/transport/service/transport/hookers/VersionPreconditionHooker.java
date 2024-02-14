package net.datenwerke.rs.transport.service.transport.hookers;

import java.util.Optional;

import com.google.inject.Inject;

import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.GeneralInfoService;
import net.datenwerke.rs.transport.service.transport.PreconditionResult;
import net.datenwerke.rs.transport.service.transport.entities.Transport;
import net.datenwerke.rs.transport.service.transport.hooks.ApplyPreconditionHook;

public class VersionPreconditionHooker implements ApplyPreconditionHook {

   private final GeneralInfoService generalInfoService;
   
   @Inject
   public VersionPreconditionHooker(
         GeneralInfoService generalInfoService
         ) {
      this.generalInfoService = generalInfoService;
   }
   
   @Override
   public String getKey() {
      return "RS_VERSION_CHECK";
   }

   @Override
   public PreconditionResult analyze(Transport transport) {
      String rsVersion = generalInfoService.getRsVersion();
      String transportRsVersion = transport.getRsVersion();
      if (!rsVersion.equals(transportRsVersion))
         return new PreconditionResult(Optional.of("ReportServer versions do not match. Local: '" + rsVersion
               + "', transport: '" + transportRsVersion + "'"));

      return new PreconditionResult(Optional.empty());
   }

}
