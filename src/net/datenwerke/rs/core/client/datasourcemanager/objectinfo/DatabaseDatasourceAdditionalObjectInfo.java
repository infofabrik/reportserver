package net.datenwerke.rs.core.client.datasourcemanager.objectinfo;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.objectinformation.hooks.ObjectInfoAdditionalInfoProvider;
import net.datenwerke.gxtdto.client.ui.helper.info.InfoWindow;
import net.datenwerke.rs.adminutils.client.datasourcetester.DatasourceTesterDao;
import net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceDto;
import net.datenwerke.rs.core.client.datasourcemanager.DatasourceDao;
import net.datenwerke.rs.core.client.datasourcemanager.locale.DatasourcesMessages;
import net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleDto;

public class DatabaseDatasourceAdditionalObjectInfo implements ObjectInfoAdditionalInfoProvider{
   
   private final DatasourceDao datasourceDao;
   private final DatasourceTesterDao datasourceTesterDao;
   
   
   @Inject
   public DatabaseDatasourceAdditionalObjectInfo(DatasourceDao datasourceDao, DatasourceTesterDao datasourceTesterDao) {
      this.datasourceDao = datasourceDao;
      this.datasourceTesterDao = datasourceTesterDao;
   }

   @Override
   public boolean consumes(Object object) {
      return object instanceof DatabaseDatasourceDto && !(object instanceof DatabaseBundleDto);
   }

   @Override
   public void addInfoFor(Object object, InfoWindow window) {
      Map<String, DwContentPanel> panels = new HashMap<>();
      getConfigMap().keySet().forEach(key -> panels.put(key, window.addDelayedSimpelInfoPanel(key)));
      panels.keySet().forEach(key -> {
         DwContentPanel dwContentPanel = panels.get(key);
         dwContentPanel.clear();
         dwContentPanel.enableScrollContainer();
         dwContentPanel.forceLayout();
      });
      
      datasourceTesterDao.testConnection((DatabaseDatasourceDto)object,
            new AsyncCallback<Boolean>() {
               
               @Override
               public void onSuccess(Boolean result) {                 
                  
                  if(result) {
                     datasourceDao.getDatasourceInfoDetailsAsHtml((DatabaseDatasourceDto) object, new RsAsyncCallback<Map<String, SafeHtml>>() {
                        
                        @Override
                        public void onSuccess(Map<String, SafeHtml> result) {
                           panels.keySet().forEach(key -> {
                              DwContentPanel dwContentPanel = panels.get(key);;
                              dwContentPanel.add(new HTML(result.get(getConfigMap().get(key))));
                           });            
                        }
                        });
                     } 
                  else {
                     panels.keySet().forEach(key -> {
                        DwContentPanel dwContentPanel = panels.get(key);
                        dwContentPanel.add(new HTML("Could not retrieve datasource metadata"));
                     });
                  }
                  
               }
               @Override
               public void onFailure(Throwable caught) {
                  // TODO Auto-generated method stub
                  
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
