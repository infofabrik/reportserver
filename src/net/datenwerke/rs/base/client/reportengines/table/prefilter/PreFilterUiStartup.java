package net.datenwerke.rs.base.client.reportengines.table.prefilter;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.client.reportengines.table.prefilter.hookers.BinaryColumnFilterConfiguratorHooker;
import net.datenwerke.rs.base.client.reportengines.table.prefilter.hookers.ColumnFilterConfiguratorHooker;
import net.datenwerke.rs.base.client.reportengines.table.prefilter.hooks.PreFilterConfiguratorHook;
import net.datenwerke.rs.base.client.reportengines.table.prefilter.propertywidgets.PreFilterViewFactory;
import net.datenwerke.rs.core.client.reportexecutor.hooks.ReportViewHook;

public class PreFilterUiStartup {

   @Inject
   public PreFilterUiStartup(HookHandlerService hookHandler, PreFilterViewFactory preFilterViewFactory,

         Provider<ColumnFilterConfiguratorHooker> columnFilterConfig,
         Provider<BinaryColumnFilterConfiguratorHooker> binaryColumnFilterConfig) {

      /* attach hooks */
      hookHandler.attachHooker(ReportViewHook.class, new ReportViewHook(preFilterViewFactory),
            HookHandlerService.PRIORITY_MEDIUM - 5);

      hookHandler.attachHooker(PreFilterConfiguratorHook.class, columnFilterConfig);
      hookHandler.attachHooker(PreFilterConfiguratorHook.class, binaryColumnFilterConfig,
            HookHandlerService.PRIORITY_LOW);

   }
}
