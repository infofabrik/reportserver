package net.datenwerke.rs.core.client.reportmanager.ui.forms;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;

import net.datenwerke.gf.client.managerhelper.mainpanel.FormView;
import net.datenwerke.gf.client.managerhelper.mainpanel.FormViewUtil;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gxtdto.client.forms.binding.FormBinding;
import net.datenwerke.rs.core.client.datasourcemanager.DatasourceUIService;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerProviderDto;
import net.datenwerke.rs.core.client.datasourcemanager.helper.forms.DatasourceSelectionField;
import net.datenwerke.rs.core.client.datasourcemanager.provider.annotations.DatasourceTreeBasic;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.pa.ReportDtoPA;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public abstract class AbstractReportForm extends FormView {

   protected final DatasourceUIService datasourceService;

   protected TextField nameField;
   protected TextField keyField;
   protected TextArea descriptionField;

   protected DatasourceSelectionField datasourceFieldCreator;

   private final Provider<UITree> datasourceTreeProvider;
   
   @Inject
   private FormViewUtil helper;

   public AbstractReportForm(DatasourceUIService datasourceService,
         @DatasourceTreeBasic Provider<UITree> datasourceTreeProvider) {

      /* store objects */
      this.datasourceService = datasourceService;
      this.datasourceTreeProvider = datasourceTreeProvider;
   }

   @Override
   protected void initializeForm(FormPanel form, VerticalLayoutContainer fieldWrapper) {
      form.setLabelWidth(120);

      nameField = helper.createNameField();
      keyField = helper.createKeyField();
      descriptionField = helper.createDescriptionField();

      helper.addNameAndKey(fieldWrapper, nameField, keyField);
      helper.addDescription(fieldWrapper, descriptionField);

      /* datasource */
      addDatasourceField(fieldWrapper, isDisplayOptionalAdditionalConfigFieldsForDatasource());
   }

   protected boolean isDisplayOptionalAdditionalConfigFieldsForDatasource() {
      return true;
   }

   @Override
   protected void doInitFormBinding(FormBinding binding, AbstractNodeDto model) {
      datasourceFieldCreator.initFormBinding((DatasourceContainerProviderDto) model);

      binding.addHtmlSafeStringBinding(nameField, model, ReportDtoPA.INSTANCE.name());
      binding.addHtmlSafeStringBinding(keyField, model, ReportDtoPA.INSTANCE.key());
      binding.addHtmlSafeStringBinding(descriptionField, model, ReportDtoPA.INSTANCE.description());
   }

   protected void addDatasourceField(Container container, boolean displayOptionalConfigFields) {
      datasourceFieldCreator = datasourceService.getSelectionField(container, displayOptionalConfigFields,
            datasourceTreeProvider);
      datasourceFieldCreator.addSelectionField();
      datasourceFieldCreator.addDisplayDefaultButton();
   }

}
