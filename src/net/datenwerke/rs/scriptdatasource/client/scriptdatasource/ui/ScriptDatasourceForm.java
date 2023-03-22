package net.datenwerke.rs.scriptdatasource.client.scriptdatasource.ui;

import com.google.inject.Inject;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.simpleform.SFFCGenericTreeNode;
import net.datenwerke.gf.client.validator.KeyValidator;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStringValidatorRegex;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.provider.annotations.FileServerTreeBasic;
import net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto.pa.ScriptDatasourceDtoPA;
import net.datenwerke.rs.scriptdatasource.client.scriptdatasource.locale.ScriptDatasourceMessages;

/**
 * 
 *
 */
public class ScriptDatasourceForm extends SimpleFormView {

   @Inject
   @FileServerTreeBasic
   UITree tree;

   @Override
   protected void configureSimpleForm(SimpleForm form) {
      /* configure form */
      form.setHeading(ScriptDatasourceMessages.INSTANCE.editDataSource()
            + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")"));

      /* name */
      form.beginFloatRow();
      form.setFieldWidth(600);
      form.addField(String.class, ScriptDatasourceDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.propertyName());
      form.setFieldWidth(500);
      
      /* key */
      form.addField(String.class, ScriptDatasourceDtoPA.INSTANCE.key(),
            BaseMessages.INSTANCE.key(), new SFFCStringValidatorRegex(KeyValidator.KEY_REGEX, BaseMessages.INSTANCE.invalidKey()));
      form.endRow();
      
      form.setFieldWidth(1);

      form.addField(String.class, ScriptDatasourceDtoPA.INSTANCE.description(),
            BaseMessages.INSTANCE.propertyDescription(), new SFFCTextAreaImpl());

      form.setFieldWidth(0.4);
      form.addField(FileServerFileDto.class, ScriptDatasourceDtoPA.INSTANCE.script(),
            ScriptDatasourceMessages.INSTANCE.scriptLabel(), new SFFCGenericTreeNode() {
               @Override
               public UITree getTreeForPopup() {
                  return tree;
               }
            });

      form.addField(Boolean.class, ScriptDatasourceDtoPA.INSTANCE.defineAtTarget(),
            ScriptDatasourceMessages.INSTANCE.defineAtTargetLabel());

      form.setFieldWidth(0.4);
      form.addField(Integer.class, ScriptDatasourceDtoPA.INSTANCE.databaseCache(),
            ScriptDatasourceMessages.INSTANCE.databaseCacheLabel());
      form.setFieldWidth(1);
   }

}
