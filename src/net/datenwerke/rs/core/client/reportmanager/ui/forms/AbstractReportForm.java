package net.datenwerke.rs.core.client.reportmanager.ui.forms;

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
import net.datenwerke.gf.client.validator.KeyValidator;
import net.datenwerke.gxtdto.client.forms.binding.FormBinding;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.datasourcemanager.DatasourceUIService;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerProviderDto;
import net.datenwerke.rs.core.client.datasourcemanager.helper.forms.DatasourceSelectionField;
import net.datenwerke.rs.core.client.datasourcemanager.provider.annotations.DatasourceTreeBasic;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.pa.ReportDtoPA;
import net.datenwerke.rs.core.client.reportmanager.locale.ReportmanagerMessages;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public abstract class AbstractReportForm extends FormView {

   protected final DatasourceUIService datasourceService;

   protected TextField nameField;
   protected TextField keyField;
   protected TextArea descriptionField;

   protected DatasourceSelectionField datasourceFieldCreator;

   private final Provider<UITree> datasourceTreeProvider;

   public AbstractReportForm(DatasourceUIService datasourceService,
         @DatasourceTreeBasic Provider<UITree> datasourceTreeProvider) {

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

      /* key */
      keyField = new TextField();
      keyField.setWidth(-1);
      keyField.addValidator(new KeyValidator(BaseMessages.INSTANCE.invalidKey()));
      BoxLayoutData keyData = new BoxLayoutData(new Margins(0, 0, 0, 5));
      keyData.setFlex(1);
      keyData.setMinSize(54);
      firstRow.add(new FieldLabel(keyField, ReportmanagerMessages.INSTANCE.key()), keyData);

      /* description */
      descriptionField = new TextArea();
      descriptionField.setWidth(-1);
      fieldWrapper.add(new FieldLabel(descriptionField, BaseMessages.INSTANCE.propertyDescription()),
            new VerticalLayoutData(1, 150));

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
