package net.datenwerke.rs.base.ext.client.datasourcemanager.eximport.im.ui;

import java.util.List;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.sencha.gxt.data.shared.IconProvider;

import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.rs.base.client.datasources.dto.CsvDatasourceDto;
import net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceDto;
import net.datenwerke.rs.base.ext.client.datasourcemanager.eximport.im.DatasourceManagerImportDao;
import net.datenwerke.rs.base.ext.client.datasourcemanager.eximport.im.dto.DatasourceManagerImportConfigDto;
import net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceDefinitionDto;
import net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleDto;
import net.datenwerke.rs.incubator.client.scriptdatasource.dto.ScriptDatasourceDto;
import net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;
import net.datenwerke.treedb.ext.client.eximport.im.ui.ImporterItemsPanel;

public class DatasourceImporterItemsPanel extends ImporterItemsPanel<DatasourceManagerImportConfigDto> {

   private final DatasourceManagerImportDao dsImportDao;

   @Inject
   public DatasourceImporterItemsPanel(DatasourceManagerImportDao dsImportDao) {
      super();

      /* store objects */
      this.dsImportDao = dsImportDao;

      /* load data */
      loadData();
   }

   private void loadData() {
      dsImportDao.loadTree(new RsAsyncCallback<List<ImportTreeModel>>() {
         @Override
         public void onSuccess(List<ImportTreeModel> roots) {
            buildTree(roots);
         }
      });
   }

   protected void configureTree() {
      super.configureTree();
      tree.setIconProvider(new IconProvider<ImportTreeModel>() {
         @Override
         public ImageResource getIcon(ImportTreeModel model) {
            if (DatabaseDatasourceDto.class.getName().equals(model.getType()))
               return BaseIcon.DATABASE.toImageResource();
            if (MondrianDatasourceDto.class.getName().equals(model.getType()))
               return BaseIcon.CUBES.toImageResource();
            if (CsvDatasourceDto.class.getName().equals(model.getType()))
               return BaseIcon.FILE_TEXT_O.toImageResource();
            if (ScriptDatasourceDto.class.getName().equals(model.getType()))
               return BaseIcon.FILE_CODE_O.toImageResource();
            if (DatabaseBundleDto.class.getName().equals(model.getType()))
               return BaseIcon.RANDOM.toImageResource();
            if (BirtReportDatasourceDefinitionDto.class.getName().equals(model.getType()))
               return BaseIcon.FILE.toImageResource();
            return BaseIcon.FOLDER_O.toImageResource();
         }
      });
   }
}
