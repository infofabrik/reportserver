package net.datenwerke.rs.scriptdatasink.client.scriptdatasink.ui;

import com.google.inject.Inject;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.simpleform.SFFCGenericTreeNode;
import net.datenwerke.gf.client.validator.KeyValidator;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStringValidatorRegex;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.datasinkmanager.locale.DatasinksMessages;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.provider.annotations.FileServerTreeBasic;
import net.datenwerke.rs.scriptdatasink.client.scriptdatasink.dto.pa.ScriptDatasinkDtoPA;
import net.datenwerke.rs.scriptdatasource.client.scriptdatasource.locale.ScriptDatasourceMessages;

public class ScriptDatasinkForm extends SimpleFormView {

   @Inject
   @FileServerTreeBasic
   UITree tree;
   
   @Override
   protected void configureSimpleForm(SimpleForm form) {
      /* configure form */
      form.setHeading(DatasinksMessages.INSTANCE.editDatasink()
            + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")"));

      form.beginFloatRow();
      form.setFieldWidth(600);
      
      /* name */
      form.addField(String.class, ScriptDatasinkDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.name());
      
      form.setFieldWidth(500);
      /* key */
      form.addField(String.class, ScriptDatasinkDtoPA.INSTANCE.key(), BaseMessages.INSTANCE.key(),
            new SFFCStringValidatorRegex(KeyValidator.KEY_REGEX, BaseMessages.INSTANCE.invalidKey()));
      
      form.endRow();
      
      form.setFieldWidth(1);

      /* description */
      form.addField(String.class, ScriptDatasinkDtoPA.INSTANCE.description(), BaseMessages.INSTANCE.description(),
            new SFFCTextAreaImpl());
      
      form.setFieldWidth(0.4);
      form.addField(FileServerFileDto.class, ScriptDatasinkDtoPA.INSTANCE.script(),
            ScriptDatasourceMessages.INSTANCE.scriptLabel(), new SFFCGenericTreeNode() {
               @Override
               public UITree getTreeForPopup() {
                  return tree;
               }
            });
   }

}