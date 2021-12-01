package net.datenwerke.rs.saiku.client.datasource.config;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.ListLoadConfig;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoader;
import com.sencha.gxt.data.shared.loader.LoadEvent;
import com.sencha.gxt.data.shared.loader.LoadHandler;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.lists.SFFCSimpleDynamicList;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.BasicObjectModelKeyProvider;
import net.datenwerke.rs.core.client.datasourcemanager.config.DatasourceDefinitionConfigConfigurator;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerProviderDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.core.client.datasourcemanager.helper.forms.DatasourceSelectionField;
import net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceConfigDto;
import net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceDto;
import net.datenwerke.rs.saiku.client.saiku.SaikuDao;
import net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportDto;
import net.datenwerke.rs.saiku.client.saiku.locale.SaikuMessages;

public class MondrianDatasourceConfigConfigurator implements DatasourceDefinitionConfigConfigurator {

   private final SaikuDao saikuDao;

   private ComboBox<String> dropDown;

   private ListStore<String> store;

   @Inject
   public MondrianDatasourceConfigConfigurator(SaikuDao saikuDao) {
      this.saikuDao = saikuDao;
   }

   @Override
   public Iterable<Widget> getOptionalAdditionalFormfields(DatasourceDefinitionConfigDto config,
         final DatasourceDefinitionDto datasourceDefinitionDto, final DatasourceSelectionField datasourceSelectionField,
         final DatasourceContainerProviderDto datasourceContainerProvider) {
      RpcProxy<ListLoadConfig, ListLoadResult<String>> proxy = new RpcProxy<ListLoadConfig, ListLoadResult<String>>() {
         @Override
         public void load(ListLoadConfig loadConfig, final AsyncCallback<ListLoadResult<String>> callback) {
            saikuDao.loadCubesFor((MondrianDatasourceDto) datasourceDefinitionDto,
                  (SaikuReportDto) datasourceContainerProvider, callback);
         }
      };
      ListLoader<ListLoadConfig, ListLoadResult<String>> loader = new ListLoader<ListLoadConfig, ListLoadResult<String>>(
            proxy);
      store = new ListStore<String>(new BasicObjectModelKeyProvider<String>());

      loader.addLoadHandler(new LoadResultListStoreBinding<ListLoadConfig, String, ListLoadResult<String>>(store));
      loader.addLoadHandler(new LoadHandler<ListLoadConfig, ListLoadResult<String>>() {
         @Override
         public void onLoad(LoadEvent<ListLoadConfig, ListLoadResult<String>> event) {
            boolean found = false;
            if (null != event.getLoadResult()) {
               String currentValue = dropDown.getCurrentValue();
               if (null != currentValue && !"".equals(currentValue.trim())) {
                  for (String cube : event.getLoadResult().getData()) {
                     if (cube.equals(currentValue)) {
                        found = true;
                        break;
                     }
                  }
               }
            }
            if (!found)
               dropDown.clear();
         }
      });
      loader.load();

      dropDown = (ComboBox<String>) SimpleForm.createFormlessField(List.class, new SFFCSimpleDynamicList<String>() {

         @Override
         public Boolean isMultiselect() {
            return false;
         }

         @Override
         public ListStore<String> getStore() {
            return store;
         }

         @Override
         public ValueProvider<?, String> getStoreKeyForDisplay() {
            return new IdentityValueProvider<String>();
         }

      });

      dropDown.setWidth(227);

      if (null != ((MondrianDatasourceConfigDto) config).getCubeName())
         dropDown.setValue(((MondrianDatasourceConfigDto) config).getCubeName());

      dropDown.addValueChangeHandler(event -> datasourceSelectionField.updateDatasourceConfig());

      return Arrays.asList(new Widget[] { new FieldLabel(dropDown, SaikuMessages.INSTANCE.cubeLabel()) });
   }

   @Override
   public void inheritChanges(DatasourceDefinitionConfigDto config, DatasourceDefinitionDto datasourceDefinitionDto) {

      /* are we rendered */
      if (null == dropDown)
         ((MondrianDatasourceConfigDto) config).setCubeName(null);
      else
         ((MondrianDatasourceConfigDto) config).setCubeName(dropDown.getCurrentValue());
   }

   @Override
   public DatasourceDefinitionConfigDto createConfigObject(DatasourceDefinitionDto datasourceDefinitionDto,
         DatasourceContainerProviderDto datasourceContainerProvider) {

      return new MondrianDatasourceConfigDto();
   }

   @Override
   public boolean consumes(DatasourceDefinitionDto datasourceDefinitionDto,
         DatasourceDefinitionConfigDto datasourceConfig) {
      return null != datasourceConfig && MondrianDatasourceConfigDto.class.equals(datasourceConfig.getClass());
   }

   @Override
   public boolean isValid(DatasourceContainerDto datasourceContainer) {
      if (!consumes(datasourceContainer.getDatasource(), datasourceContainer.getDatasourceConfig()))
         return false;
      return true;
   }

   @Override
   public boolean isReloadOnDatasourceChange() {
      return true;
   }

   @Override
   public Iterable<Widget> getDefaultAdditionalFormfields(DatasourceDefinitionConfigDto config,
         DatasourceDefinitionDto datasourceDefinitionDto, DatasourceSelectionField datasourceSelectionField,
         DatasourceContainerProviderDto datasourceContainerProvider) {
      return null;
   }

}
