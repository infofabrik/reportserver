package net.datenwerke.rs.tabledatasink.client.tabledatasink.ui;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer.HBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;

import net.datenwerke.gf.client.managerhelper.mainpanel.FormView;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gxtdto.client.forms.binding.FormBinding;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.datasinkmanager.locale.DatasinksMessages;
import net.datenwerke.rs.core.client.datasourcemanager.DatasourceUIService;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerProviderDto;
import net.datenwerke.rs.core.client.datasourcemanager.helper.forms.DatasourceSelectionField;
import net.datenwerke.rs.core.client.datasourcemanager.provider.annotations.DatasourceTreeBasic;
import net.datenwerke.rs.tabledatasink.client.tabledatasink.dto.pa.TableDatasinkDtoPA;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class TableDatasinkForm extends FormView {
   
   private DatasourceSelectionField datasourceFieldCreator;
   
   private final Provider<UITree> datasourceTreeProvider;

   private final DatasourceUIService datasourceService;
   
   private TextField nameField;
   private TextArea descriptionField;
   private TextField tableNameField;
   
   @Inject
   public TableDatasinkForm(
         DatasourceUIService datasourceService,
         @DatasourceTreeBasic Provider<UITree> datasourceTreeProvider
         ) {

      /* store objects */
      this.datasourceService = datasourceService;
      this.datasourceTreeProvider = datasourceTreeProvider;
   }
   
   @Override
   protected void initializeForm(FormPanel form, VerticalLayoutContainer fieldWrapper) {
      form.setLabelWidth(120);

      HBoxLayoutContainer firstRow = new HBoxLayoutContainer();
      firstRow.setHBoxLayoutAlign(HBoxLayoutAlign.STRETCHMAX);
      fieldWrapper.add(firstRow, new VerticalLayoutData(1, -1));
      
      /* name */
      nameField = new TextField();
      nameField.setWidth(-1);
      BoxLayoutData nameData = new BoxLayoutData(new Margins(0, 5, 0, 0));
      nameData.setFlex(2);
      nameData.setMinSize(54);
      firstRow.add(new FieldLabel(nameField, BaseMessages.INSTANCE.name()), nameData);
      
      /* description */
      descriptionField = new TextArea();
      descriptionField.setWidth(-1);
      fieldWrapper.add(new FieldLabel(descriptionField, BaseMessages.INSTANCE.propertyDescription()),
            new VerticalLayoutData(1, 150));
      
      /* datasource */
      addDatasourceField(fieldWrapper, false);
      
      /* table name */
      tableNameField = new TextField();
      tableNameField.setWidth(300);
      BoxLayoutData tableNameData = new BoxLayoutData(new Margins(0, 5, 0, 0));
      tableNameData.setFlex(2);
      tableNameData.setMinSize(54);
      fieldWrapper.add(new FieldLabel(tableNameField, "Table name"));
      
      form.add(fieldWrapper);
   }
   
   @Override
   protected void doInitFormBinding(FormBinding binding, AbstractNodeDto model) {
      datasourceFieldCreator.initFormBinding((DatasourceContainerProviderDto) model);

      binding.addHtmlSafeStringBinding(nameField, model, TableDatasinkDtoPA.INSTANCE.name());
      binding.addHtmlSafeStringBinding(descriptionField, model, TableDatasinkDtoPA.INSTANCE.description());
      binding.addHtmlSafeStringBinding(tableNameField, model, TableDatasinkDtoPA.INSTANCE.tableName());
   }
   
   @Override
   protected String getHeader() {
      return DatasinksMessages.INSTANCE.editDatasink()
          + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")");
   }
   
   protected void addDatasourceField(Container container, boolean displayOptionalConfigFields) {
      datasourceFieldCreator = datasourceService.getSelectionField(container, displayOptionalConfigFields,
            datasourceTreeProvider);
      datasourceFieldCreator.addSelectionField();
      datasourceFieldCreator.addDisplayDefaultButton();
   }

}
