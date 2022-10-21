package net.datenwerke.rs.base.client.reportengines.table.prefilter.hooks;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterSpecDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.base.client.reportengines.table.prefilter.propertywidgets.PreFilterView.EditPreFilterCallback;
import net.datenwerke.rs.base.client.reportengines.table.prefilter.propertywidgets.PreFilterView.InstantiatePreFilterCallback;

public interface PreFilterConfiguratorHook extends Hook {

   String getHeadline();

   ImageResource getIcon();

   void instantiateFilter(TableReportDto report, String executeToken,
         InstantiatePreFilterCallback instantiatePreFilterCallback);

   boolean consumes(FilterSpecDto filter);

   void displayFilter(TableReportDto report, FilterSpecDto filter, String executeToken, EditPreFilterCallback callback);

   void filterInstantiated(TableReportDto report, FilterSpecDto filter, String executeToken,
         final EditPreFilterCallback callback);

}
