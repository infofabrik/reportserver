package net.datenwerke.gf.client.managerhelper.mainpanel;

import com.google.inject.Singleton;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer.HBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;

import net.datenwerke.gf.client.validator.KeyValidator;
import net.datenwerke.gxtdto.client.locale.BaseMessages;

/**
 * This util class provides common elements used in the FormView classes
 * to ensure that we have the same design elements and formatting everywhere
 */
@Singleton
public class FormViewUtil {
   
   public TextField createNameField() {
      TextField nameField = new TextField();
      nameField.setWidth(-1);
      return nameField;
   }
   
   public TextField createKeyField() {
      TextField keyField = new TextField();
      keyField.setWidth(-1);
      keyField.addValidator(new KeyValidator(BaseMessages.INSTANCE.invalidKey()));
      keyField.setAllowBlank(false);
      keyField.setAutoValidate(true);
      return keyField;
   }
   
   public TextArea createDescriptionField() {
      TextArea descriptionField = new TextArea();
      descriptionField.setWidth(-1);
      return descriptionField;
   }
   
   /**
    * Adds Name and Key TextField in one row to the fieldWrapper with
    * dynamic sizing
    * @param fieldWrapper target container to which name and key is added
    * @param name the name TextField
    * @param key the key TextField
    */
   public void addNameAndKey(VerticalLayoutContainer fieldWrapper, TextField nameField, TextField keyField) {
      
      HBoxLayoutContainer row = new HBoxLayoutContainer();
      row.setHBoxLayoutAlign(HBoxLayoutAlign.STRETCHMAX);
      fieldWrapper.add(row, new VerticalLayoutData(1, -1));
      
      /* name */
      BoxLayoutData nameData = new BoxLayoutData(new Margins(0, 5, 0, 0));
      nameData.setFlex(2);
      nameData.setMinSize(54);
      row.add(new FieldLabel(nameField, BaseMessages.INSTANCE.name()), nameData);
      
      /* key */
      BoxLayoutData keyData = new BoxLayoutData(new Margins(0, 5, 0, 0));
      keyData.setFlex(2);
      keyData.setMinSize(54);
      row.add(new FieldLabel(keyField, BaseMessages.INSTANCE.key()), keyData);   
   }
   
   public void addDescription(VerticalLayoutContainer fieldWrapper, TextArea descriptionField) {
      fieldWrapper.add(
            new FieldLabel(descriptionField, BaseMessages.INSTANCE.propertyDescription()),
            new VerticalLayoutData(1, 150));
   }
}
