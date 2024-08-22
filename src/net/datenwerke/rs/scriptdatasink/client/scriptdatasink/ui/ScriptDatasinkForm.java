package net.datenwerke.rs.scriptdatasink.client.scriptdatasink.ui;

import com.google.inject.Inject;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.simpleform.SFFCGenericTreeNode;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.rs.core.client.datasinkmanager.ui.forms.DatasinkSimpleForm;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.provider.annotations.FileServerTreeBasic;
import net.datenwerke.rs.scriptdatasink.client.scriptdatasink.dto.pa.ScriptDatasinkDtoPA;
import net.datenwerke.rs.scriptdatasource.client.scriptdatasource.locale.ScriptDatasourceMessages;

public class ScriptDatasinkForm extends DatasinkSimpleForm {

   @Inject
   @FileServerTreeBasic
   UITree tree;
   
   @Override
   protected void configureSimpleFormCustomFields(SimpleForm form) {
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