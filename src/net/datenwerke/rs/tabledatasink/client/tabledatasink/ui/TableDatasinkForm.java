package net.datenwerke.rs.tabledatasink.client.tabledatasink.ui;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.IntegerField;
import com.sencha.gxt.widget.core.client.form.TextField;

import net.datenwerke.gf.client.managerhelper.mainpanel.FormViewUtil;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gxtdto.client.forms.binding.FormBinding;
import net.datenwerke.rs.core.client.datasinkmanager.locale.DatasinksMessages;
import net.datenwerke.rs.core.client.datasinkmanager.ui.forms.DatasinkForm;
import net.datenwerke.rs.core.client.datasourcemanager.DatasourceUIService;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerProviderDto;
import net.datenwerke.rs.core.client.datasourcemanager.helper.forms.DatasourceSelectionField;
import net.datenwerke.rs.core.client.datasourcemanager.provider.annotations.DatasourceTreeOnlyDatabaseDatasources;
import net.datenwerke.rs.tabledatasink.client.tabledatasink.dto.pa.TableDatasinkDtoPA;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class TableDatasinkForm extends DatasinkForm {
   
   private DatasourceSelectionField datasourceFieldCreator;
   
   private final Provider<UITree> datasourceTreeProvider;

   private final DatasourceUIService datasourceService;
   

   private TextField tableNameField;
   private TextField primaryKeysField;
   private CheckBox copyPrimaryKeysField;
   private CheckBox truncateTableField;
   private IntegerField batchSizeField;
   
   @Inject
   public TableDatasinkForm(
         Provider<FormViewUtil> helper,
         DatasourceUIService datasourceService,
         @DatasourceTreeOnlyDatabaseDatasources Provider<UITree> datasourceTreeProvider
         ) {
      super(helper);
      /* store objects */
      this.datasourceService = datasourceService;
      this.datasourceTreeProvider = datasourceTreeProvider;
   }
   
   @Override
   protected void initializeForm(FormPanel form, VerticalLayoutContainer fieldWrapper) {
      super.initializeForm(form, fieldWrapper);

      /* datasource */
      addDatasourceField(fieldWrapper, false);

      /* table name */
      tableNameField = new TextField();
      tableNameField.setWidth(300);
      BoxLayoutData tableNameData = new BoxLayoutData(new Margins(0, 5, 0, 0));
      tableNameData.setFlex(2);
      tableNameData.setMinSize(54);
      fieldWrapper.add(new FieldLabel(tableNameField, DatasinksMessages.INSTANCE.destinationTableName()));

      /* primary keys */
      primaryKeysField = new TextField();
      primaryKeysField.setWidth(300);
      BoxLayoutData primaryKeysData = new BoxLayoutData(new Margins(0, 5, 0, 0));
      primaryKeysData.setFlex(2);
      primaryKeysData.setMinSize(54);
      fieldWrapper.add(new FieldLabel(primaryKeysField, DatasinksMessages.INSTANCE.dstTablePrimaryKeys()));

      /* copy primary keys */
      copyPrimaryKeysField = new CheckBox();
      BoxLayoutData copyPrimaryKeysData = new BoxLayoutData(new Margins(0, 5, 0, 0));
      copyPrimaryKeysData.setFlex(2);
      copyPrimaryKeysData.setMinSize(54);
      fieldWrapper.add(new FieldLabel(copyPrimaryKeysField, DatasinksMessages.INSTANCE.copyPrimaryKeys()));
      
      /* delete table before copying */
      truncateTableField = new CheckBox();
      fieldWrapper.add(new FieldLabel(truncateTableField, "Truncate"));

      /* batch size */
      batchSizeField = new IntegerField();
      batchSizeField.setWidth(300);
      BoxLayoutData batchSizeData = new BoxLayoutData(new Margins(0, 5, 0, 0));
      batchSizeData.setFlex(2);
      batchSizeData.setMinSize(54);
      fieldWrapper.add(new FieldLabel(batchSizeField, DatasinksMessages.INSTANCE.batchSize()));

      form.add(fieldWrapper);
   }

   @Override
   protected void doInitFormBinding(FormBinding binding, AbstractNodeDto model) {
      super.doInitFormBinding(binding, model);
      
      datasourceFieldCreator.initFormBinding((DatasourceContainerProviderDto) model);

      binding.addHtmlSafeStringBinding(tableNameField, model, TableDatasinkDtoPA.INSTANCE.tableName());
      binding.addHtmlSafeStringBinding(primaryKeysField, model, TableDatasinkDtoPA.INSTANCE.primaryKeys());
      binding.addHtmlSafeStringBinding(copyPrimaryKeysField, model, TableDatasinkDtoPA.INSTANCE.copyPrimaryKeys());
      binding.addHtmlSafeStringBinding(truncateTableField, model, TableDatasinkDtoPA.INSTANCE.truncateTable());
      binding.addHtmlSafeStringBinding(batchSizeField, model, TableDatasinkDtoPA.INSTANCE.batchSize());
   }
   
   protected void addDatasourceField(Container container, boolean displayOptionalConfigFields) {
      datasourceFieldCreator = datasourceService.getSelectionField(container, displayOptionalConfigFields,
            datasourceTreeProvider);
      datasourceFieldCreator.addSelectionField();
      datasourceFieldCreator.addDisplayDefaultButton();
   }

}
