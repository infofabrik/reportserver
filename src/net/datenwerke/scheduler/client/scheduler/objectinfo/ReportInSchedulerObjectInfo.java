package net.datenwerke.scheduler.client.scheduler.objectinfo;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.objectinformation.hooks.ObjectInfoAdditionalInfoProvider;
import net.datenwerke.gxtdto.client.ui.helper.info.InfoWindow;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.scheduler.client.scheduler.SchedulerDao;
import net.datenwerke.rs.scheduler.client.scheduler.locale.SchedulerMessages;

/**
 * 
 *
 */
public class ReportInSchedulerObjectInfo implements ObjectInfoAdditionalInfoProvider {

   private final SchedulerDao schedulerDao;

   @Inject
   public ReportInSchedulerObjectInfo(SchedulerDao schedulerDao) {
      this.schedulerDao = schedulerDao;
   }

   @Override
   public boolean consumes(Object object) {
      return object instanceof ReportDto;
   }

   @Override
   public void addInfoFor(Object object, InfoWindow window) {
      final DwContentPanel panel = window.addDelayedSimpelInfoPanel(SchedulerMessages.INSTANCE.scheduler());

      schedulerDao.getReportJobListAsHtml((ReportDto) object, new RsAsyncCallback<SafeHtml>() {
         @Override
         public void onSuccess(SafeHtml result) {
            panel.clear();
            panel.enableScrollContainer();

            if (null == result)
               panel.add(new Label(SchedulerMessages.INSTANCE.reportNotInJobMessages()));
            else {
               SafeHtmlBuilder builder = new SafeHtmlBuilder();
               builder.appendHtmlConstant("<div class=\"rs-infopanel-reportinscheduler\">");
               builder.append(result);
               panel.add(new HTML(builder.toSafeHtml()));
            }

            panel.forceLayout();
         }
      });
   }

}
