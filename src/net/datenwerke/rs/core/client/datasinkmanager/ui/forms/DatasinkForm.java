package net.datenwerke.rs.core.client.datasinkmanager.ui.forms;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;

import net.datenwerke.gf.client.managerhelper.mainpanel.FormView;
import net.datenwerke.gf.client.managerhelper.mainpanel.FormViewUtil;
import net.datenwerke.gxtdto.client.forms.binding.FormBinding;
import net.datenwerke.rs.core.client.datasinkmanager.dto.pa.DatasinkDefinitionDtoPA;
import net.datenwerke.rs.core.client.datasinkmanager.locale.DatasinksMessages;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public abstract class DatasinkForm extends FormView {
   
   protected TextField nameField;
   protected TextField keyField;
   protected TextArea descriptionField;
   protected FormViewUtil helper;
   
   @Inject
   public DatasinkForm(Provider<FormViewUtil> helper) {
      this.helper = helper.get();
   }
   
   @Override
   protected void initializeForm(FormPanel form, VerticalLayoutContainer fieldWrapper) {
      form.setLabelWidth(120);
      form.add(fieldWrapper);

      nameField = helper.createNameField();
      keyField = helper.createKeyField();
      descriptionField = helper.createDescriptionField();

      helper.addNameAndKey(fieldWrapper, nameField, keyField);
      helper.addDescription(fieldWrapper, descriptionField);
      
   }
   
   @Override
   protected String getHeader() {
      return DatasinksMessages.INSTANCE.editDatasink()
          + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")");
   }
   
   @Override
   protected void doInitFormBinding(FormBinding binding, AbstractNodeDto model) {
      binding.addHtmlSafeStringBinding(nameField, model, DatasinkDefinitionDtoPA.INSTANCE.name());
      binding.addHtmlSafeStringBinding(keyField, model, DatasinkDefinitionDtoPA.INSTANCE.key());
      binding.addHtmlSafeStringBinding(descriptionField, model, DatasinkDefinitionDtoPA.INSTANCE.description());
   }
}
