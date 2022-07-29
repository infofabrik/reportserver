package net.datenwerke.rs.scriptdatasink.client.scriptdatasink.ui;

import com.google.inject.Inject;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.simpleform.SFFCGenericTreeNode;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.datasinkmanager.locale.DatasinksMessages;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.provider.annotations.FileServerTreeBasic;
import net.datenwerke.rs.incubator.client.scriptdatasource.locale.ScriptDatasourceMessages;
import net.datenwerke.rs.scriptdatasink.client.scriptdatasink.dto.pa.ScriptDatasinkDtoPA;

public class ScriptDatasinkForm extends SimpleFormView {

   @Inject
   @FileServerTreeBasic
   UITree tree;
   
   @Override
   protected void configureSimpleForm(SimpleForm form) {
      /* configure form */
      form.setHeading(DatasinksMessages.INSTANCE.editDatasink()
            + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")"));

      /* name */
      form.addField(String.class, ScriptDatasinkDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.name());

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