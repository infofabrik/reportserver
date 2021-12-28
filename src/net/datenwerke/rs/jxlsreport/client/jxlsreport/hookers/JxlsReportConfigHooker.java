package net.datenwerke.rs.jxlsreport.client.jxlsreport.hookers;

import java.util.Collection;
import java.util.Collections;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.hooks.ReportTypeConfigHook;
import net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportDto;
import net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportVariantDto;
import net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.decorator.JxlsReportDtoDec;
import net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.decorator.JxlsReportVariantDtoDec;
import net.datenwerke.rs.jxlsreport.client.jxlsreport.locale.JxlsReportMessages;
import net.datenwerke.rs.jxlsreport.client.jxlsreport.ui.JxlsReportForm;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class JxlsReportConfigHooker implements ReportTypeConfigHook {

   private final Provider<JxlsReportForm> adminFormProvider;

   @Inject
   public JxlsReportConfigHooker(Provider<JxlsReportForm> adminFormProvider) {
      this.adminFormProvider = adminFormProvider;
   }

   @Override
   public boolean consumes(ReportDto report) {
      return report instanceof JxlsReportDto;
   }

   @Override
   public Collection<? extends MainPanelView> getAdminViews(ReportDto report) {
      return Collections.singleton(adminFormProvider.get());
   }

   @Override
   public Class<? extends ReportDto> getReportClass() {
      return JxlsReportDto.class;
   }

   @Override
   public ImageResource getReportIcon() {
      return BaseIcon.REPORT_JXLS.toImageResource();
   }

   @Override
   public ImageResource getReportIconLarge() {
      return BaseIcon.REPORT_JXLS.toImageResource(1);
   }

   @Override
   public ImageResource getReportLinkIcon() {
      return BaseIcon.REPORT_JXLS_LINK.toImageResource();
   }

   @Override
   public ImageResource getReportLinkIconLarge() {
      return BaseIcon.REPORT_JXLS_LINK.toImageResource(1);
   }

   @Override
   public String getReportName() {
      return JxlsReportMessages.INSTANCE.reportTypeName();
   }

   @Override
   public Class<? extends ReportDto> getReportVariantClass() {
      return JxlsReportVariantDto.class;
   }

   @Override
   public ImageResource getReportVariantIcon() {
      return BaseIcon.REPORT_USER.toImageResource();
   }

   @Override
   public ImageResource getReportVariantIconLarge() {
      return BaseIcon.REPORT_USER.toImageResource(1);
   }

   @Override
   public ReportDto instantiateReport() {
      return new JxlsReportDtoDec();
   }

   @Override
   public ReportDto instantiateReportVariant() {
      return new JxlsReportVariantDtoDec();
   }

   @Override
   public boolean isAvailable() {
      return true;
   }
}
