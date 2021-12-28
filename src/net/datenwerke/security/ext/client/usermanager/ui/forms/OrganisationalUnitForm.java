package net.datenwerke.security.ext.client.usermanager.ui.forms;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.security.client.usermanager.dto.pa.OrganisationalUnitDtoPA;
import net.datenwerke.security.ext.client.usermanager.locale.UsermanagerMessages;

public class OrganisationalUnitForm extends SimpleFormView {

   public void configureSimpleForm(SimpleForm form) {
      /* build form */
      form.setHeading(UsermanagerMessages.INSTANCE.editOU()
            + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")"));

      /* name name */
      form.addField(String.class, OrganisationalUnitDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.name()); // $NON-NLS-1$

      form.addField(String.class, OrganisationalUnitDtoPA.INSTANCE.description(),
            BaseMessages.INSTANCE.propertyDescription(), new SFFCTextAreaImpl());
   }

}
