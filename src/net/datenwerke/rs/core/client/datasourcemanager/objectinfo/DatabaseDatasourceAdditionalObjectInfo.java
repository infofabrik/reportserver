package net.datenwerke.rs.core.client.datasourcemanager.objectinfo;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.HTML;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.objectinformation.hooks.ObjectInfoAdditionalInfoProvider;
import net.datenwerke.gxtdto.client.ui.helper.info.InfoWindow;
import net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceDto;
import net.datenwerke.rs.core.client.datasourcemanager.DatasourceDao;
import net.datenwerke.rs.core.client.datasourcemanager.locale.DatasourcesMessages;

public class DatabaseDatasourceAdditionalObjectInfo implements ObjectInfoAdditionalInfoProvider{
   
   private final DatasourceDao datasourceDao;
   
   
   @Inject
   public DatabaseDatasourceAdditionalObjectInfo(DatasourceDao datasourceDao) {
      this.datasourceDao = datasourceDao;
   }

   @Override
   public boolean consumes(Object object) {
      return object instanceof DatabaseDatasourceDto;
   }

   @Override
   public void addInfoFor(Object object, InfoWindow window) {
      Map<String, DwContentPanel> allPanels = new HashMap<>();
      getConfigMap().keySet().forEach(key -> allPanels.put(key, window.addDelayedSimpelInfoPanel(key)));
      datasourceDao.getDatasourceInfoDetailsAsHtml((DatabaseDatasourceDto) object, new RsAsyncCallback<Map<String, SafeHtml>>() {

         @Override
         public void onSuccess(Map<String, SafeHtml> result) {
            allPanels.keySet().forEach(key -> {
               DwContentPanel dwContentPanel = allPanels.get(key);
               dwContentPanel.clear();
               dwContentPanel.enableScrollContainer();
               dwContentPanel.setScrollMode(ScrollMode.AUTO);
               dwContentPanel.add(new HTML(result.get(getConfigMap().get(key))));
               dwContentPanel.forceLayout();
            });            
         }
      });
      
   }
   
   private Map<String, String> getConfigMap() {
      Map<String, String> configMap = new HashMap<>();
      configMap.put(DatasourcesMessages.INSTANCE.objectInfoGeneralInfo(), "generalInfo");
      configMap.put(DatasourcesMessages.INSTANCE.objectInfoUrlInfo(), "urlInfo");
      configMap.put(DatasourcesMessages.INSTANCE.objectInfoFuncstionsSection(), "functionsSection");
      configMap.put(DatasourcesMessages.INSTANCE.objectInfoSupportsSection(), "supportsSection");
      return configMap;
   }

}
