package net.datenwerke.rs.dashboard.client.dashboard.ui.helper;

import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.DtoModelProvider;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCBaseModel;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCDashboard;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.dashboard.client.dashboard.DashboardUiService;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.pa.DashboardDtoPA;

public class DashboardProvider extends DtoModelProvider {

   private static DashboardDtoPA dashboardPa = GWT.create(DashboardDtoPA.class);

   private final DashboardUiService dashboardService;

   @Inject
   public DashboardProvider(DashboardUiService dashboardService) {

      /* store objects */
      this.dashboardService = dashboardService;
   }

   @Override
   public boolean doConsumes(Class<?> type, SimpleFormFieldConfiguration... configs) {
      return type.equals(DashboardDto.class);
   }

   @Override
   public Widget createFormField() {
      final Map<ValueProvider<DashboardDto, String>, String> displayProperties = new LinkedHashMap<ValueProvider<DashboardDto, String>, String>();
      displayProperties.put(dashboardPa.name(), BaseMessages.INSTANCE.name());
      displayProperties.put(new ValueProvider<DashboardDto, String>() {

         @Override
         public void setValue(DashboardDto object, String value) {
         }

         @Override
         public String getValue(DashboardDto object) {
            return String.valueOf(object.getId());
         }

         @Override
         public String getPath() {
            return "";
         }
      }, BaseMessages.INSTANCE.id());

      final SFFCDashboard dashboardConfig = getDashboardConfig();

      configs = new SimpleFormFieldConfiguration[] { new SFFCBaseModel<DashboardDto>() {
         @Override
         public ListStore<DashboardDto> getAllItemsStore() {
            if (dashboardConfig.isLoadAll())
               return DashboardProvider.this.dashboardService.getAllDashboardsStore();
            return DashboardProvider.this.dashboardService.getDashboardStore();
         }

         @Override
         public Map<ValueProvider<DashboardDto, String>, String> getDisplayProperties() {
            return displayProperties;
         }

         @Override
         public boolean isMultiSelect() {
            return dashboardConfig.isMulti();
         }

      } };

      return super.createFormField();
   }

   private SFFCDashboard getDashboardConfig() {
      for (SimpleFormFieldConfiguration config : configs)
         if (config instanceof SFFCDashboard)
            return (SFFCDashboard) config;
      return new SFFCDashboard() {
         @Override
         public boolean isMulti() {
            return false;
         }

         @Override
         public boolean isLoadAll() {
            return false;
         }
      };
   }
}
