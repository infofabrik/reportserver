package net.datenwerke.rs.core.client.reportmanager.objectinfo;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.objectinformation.hooks.GeneralObjectInfoImpl;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportVariantDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.hooks.ReportTypeConfigHook;
import net.datenwerke.rs.core.client.reportmanager.locale.ReportmanagerMessages;

public final class ReportObjectInfo extends GeneralObjectInfoImpl<ReportDto> {

   private final HookHandlerService hookHandler;

   @Inject
   public ReportObjectInfo(HookHandlerService hookHandler) {
      this.hookHandler = hookHandler;
   }

   @Override
   protected String doGetName(ReportDto report) {
      return report.getName();
   }

   @Override
   public boolean consumes(Object object) {
      return object instanceof ReportDto;
   }

   @Override
   protected String doGetType(ReportDto report) {
      return getConfigHook(report).getReportName()
            + ((report instanceof ReportVariantDto) ? " (" + ReportmanagerMessages.INSTANCE.variant() + ")" : "");
   }

   @Override
   protected ImageResource doGetIconSmall(ReportDto report) {
      return (report instanceof ReportVariantDto) ? getConfigHook(report).getReportVariantIcon()
            : getConfigHook(report).getReportIcon();
   }

   protected ReportTypeConfigHook getConfigHook(ReportDto report) {
      for (ReportTypeConfigHook hooker : hookHandler.getHookers(ReportTypeConfigHook.class))
         if (hooker.consumes(report))
            return hooker;
      throw new IllegalArgumentException("Could not find config provider for: " + report.getClass());
   }

}
