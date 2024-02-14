package net.datenwerke.rs.core.client.datasourcemanager.ui.forms;
import com.sencha.gxt.core.client.ValueProvider;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCAllowBlank;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStringValidatorRegex;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.pa.DatasourceDefinitionDtoPA;
import net.datenwerke.rs.core.client.datasourcemanager.locale.DatasourcesMessages;
import net.datenwerke.rs.utils.validator.shared.SharedRegex;

public abstract class DatasourceSimpleForm extends SimpleFormView {
   
   private final ValueProvider<DatasourceDefinitionDto, String> nameVp;
   private final ValueProvider<DatasourceDefinitionDto, String> keyVp;
   private final ValueProvider<DatasourceDefinitionDto, String> descriptionVp;
   
   
   public DatasourceSimpleForm() {
      super();
      this.nameVp = DatasourceDefinitionDtoPA.INSTANCE.name();
      this.keyVp = DatasourceDefinitionDtoPA.INSTANCE.key();
      this.descriptionVp = DatasourceDefinitionDtoPA.INSTANCE.description();
   }

   @Override
   protected void configureSimpleForm(SimpleForm form) {
      /* configure form */
      form.setHeading(DatasourcesMessages.INSTANCE.editDataSource()
            + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")"));

      form.beginFloatRow();
      form.setFieldWidth(600);

      /* name */
      form.addField(String.class, nameVp, BaseMessages.INSTANCE.name());

      form.setFieldWidth(500);
      /* key */
      form.addField(String.class, keyVp, BaseMessages.INSTANCE.key(),
            new SFFCStringValidatorRegex(SharedRegex.KEY_REGEX, BaseMessages.INSTANCE.invalidKey()),
            new SFFCAllowBlank() {
         @Override
         public boolean allowBlank() {
            return false;
         }
      }); // $NON-NLS-1$

      form.endRow();

      form.setFieldWidth(1);

      /* description */
      form.addField(String.class, descriptionVp, BaseMessages.INSTANCE.description(),
            new SFFCTextAreaImpl());
      /* do custom field from subclass */
      configureSimpleFormCustomFields(form);
   }

   protected abstract void configureSimpleFormCustomFields(SimpleForm form);
}
