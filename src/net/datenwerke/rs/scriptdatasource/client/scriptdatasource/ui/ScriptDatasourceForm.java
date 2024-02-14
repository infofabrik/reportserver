package net.datenwerke.rs.scriptdatasource.client.scriptdatasource.ui;

import com.google.inject.Inject;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.simpleform.SFFCGenericTreeNode;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.rs.core.client.datasourcemanager.ui.forms.DatasourceSimpleForm;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.provider.annotations.FileServerTreeBasic;
import net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto.pa.ScriptDatasourceDtoPA;
import net.datenwerke.rs.scriptdatasource.client.scriptdatasource.locale.ScriptDatasourceMessages;

/**
 * 
 *
 */
public class ScriptDatasourceForm extends DatasourceSimpleForm {

   @Inject
   @FileServerTreeBasic
   UITree tree;

   @Override
   protected void configureSimpleFormCustomFields(SimpleForm form) {
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
