package net.datenwerke.security.ext.client.usermanager.ui.forms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.container.MarginData;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCAllowBlank;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCPasswordField;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCStaticDropdownList;
import net.datenwerke.security.client.usermanager.dto.SexDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.client.usermanager.dto.pa.UserDtoPA;
import net.datenwerke.security.ext.client.usermanager.locale.UsermanagerMessages;

public class UserForm extends SimpleFormView {

   @Inject
   public UserForm() {
      super();
   }

   public void configureSimpleForm(SimpleForm form) {
      /* build form */
      form.setHeading(UsermanagerMessages.INSTANCE.editUser()
            + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")"));

      form.beginRow();

      /* personal fields */
      form.beginColumn(new MarginData(0, 5, 0, 0));

      /* sex */
      form.setFieldWidth(200);
      form.addField(List.class, UserDtoPA.INSTANCE.sex(), UsermanagerMessages.INSTANCE.gender(), // $NON-NLS-1$
            new SFFCStaticDropdownList<SexDto>() {
               public Map<String, SexDto> getValues() {
                  Map<String, SexDto> map = new HashMap<String, SexDto>();

                  map.put(UsermanagerMessages.INSTANCE.genderMale(), SexDto.Male);
                  map.put(UsermanagerMessages.INSTANCE.genderFemale(), SexDto.Female);

                  return map;
               }

               @Override
               public boolean allowBlank() {
                  return true;
               }
            });
      form.setFieldWidth(1);

      /* first name */
      form.addField(String.class, UserDtoPA.INSTANCE.firstname(), UsermanagerMessages.INSTANCE.firstname()); // $NON-NLS-1$

      /* last name */
      form.addField(String.class, UserDtoPA.INSTANCE.lastname(), UsermanagerMessages.INSTANCE.lastname(),
            new SFFCAllowBlank() {

               @Override
               public boolean allowBlank() {
                  return false;
               }
            }); // $NON-NLS-1$

      /* end personal */
      form.endColumn();

      /* security */
      form.beginColumn();

      /* user name */
      form.addField(String.class, UserDtoPA.INSTANCE.username(), UsermanagerMessages.INSTANCE.username(),
            new SFFCAllowBlank() {

               @Override
               public boolean allowBlank() {
                  return false;
               }
            }); // $NON-NLS-1$

      /* password field */
      form.addField(String.class, UserDtoPA.INSTANCE.password(), UsermanagerMessages.INSTANCE.password(),
            new SFFCPasswordField() {
               @Override
               public Boolean isPasswordSet() {
                  return ((UserDto) getSelectedNode()).isHasPassword();
               }
            }); // $NON-NLS-1$

      /* email */
      form.addField(String.class, UserDtoPA.INSTANCE.email(), UsermanagerMessages.INSTANCE.email()); // $NON-NLS-1$

      form.endColumn();

      form.endRow();

      form.setValidateOnSubmit(true);
   }

}
