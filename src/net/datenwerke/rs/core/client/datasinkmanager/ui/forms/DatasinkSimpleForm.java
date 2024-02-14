package net.datenwerke.rs.core.client.datasinkmanager.ui.forms;
import com.sencha.gxt.core.client.ValueProvider;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCAllowBlank;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStringValidatorRegex;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.datasinkmanager.dto.pa.DatasinkDefinitionDtoPA;
import net.datenwerke.rs.core.client.datasinkmanager.locale.DatasinksMessages;
import net.datenwerke.rs.utils.validator.shared.SharedRegex;

public abstract class DatasinkSimpleForm extends SimpleFormView {
   
   private final ValueProvider<DatasinkDefinitionDto, String> nameVp;
   private final ValueProvider<DatasinkDefinitionDto, String> keyVp;
   private final ValueProvider<DatasinkDefinitionDto, String> descriptionVp;
   
   
   public DatasinkSimpleForm() {
      super();
      this.nameVp = DatasinkDefinitionDtoPA.INSTANCE.name();
      this.keyVp = DatasinkDefinitionDtoPA.INSTANCE.key();
      this.descriptionVp = DatasinkDefinitionDtoPA.INSTANCE.description();
   }

   @Override
   protected void configureSimpleForm(SimpleForm form) {
      /* configure form */
      form.setHeading(DatasinksMessages.INSTANCE.editDatasink()
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
