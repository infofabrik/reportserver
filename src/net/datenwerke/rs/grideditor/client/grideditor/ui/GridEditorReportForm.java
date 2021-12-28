package net.datenwerke.rs.grideditor.client.grideditor.ui;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.simpleform.SFFCGenericTreeNode;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.rs.core.client.reportmanager.ui.forms.AbstractReportSimpleForm;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.provider.annotations.FileServerTreeBasic;
import net.datenwerke.rs.grideditor.client.grideditor.dto.pa.GridEditorReportDtoPA;
import net.datenwerke.rs.grideditor.client.grideditor.locale.GridEditorMessages;

public class GridEditorReportForm extends AbstractReportSimpleForm {

   private final Provider<UITree> fileTreeProvider;

   @Inject
   public GridEditorReportForm(@FileServerTreeBasic Provider<UITree> fileTreeProvider) {
      this.fileTreeProvider = fileTreeProvider;

   }

   @Override
   protected void configureSimpleForm(SimpleForm form) {
      super.configureSimpleForm(form);

      form.setHeading(GridEditorMessages.INSTANCE.editReport() + " (" + getSelectedNode().getId() + ")"); //$NON-NLS-1$ //$NON-NLS-2$

      form.setFieldWidth(200);

      form.addField(FileServerFileDto.class, GridEditorReportDtoPA.INSTANCE.script(),
            GridEditorMessages.INSTANCE.script(), new SFFCGenericTreeNode() {

               @Override
               public UITree getTreeForPopup() {
                  return fileTreeProvider.get();
               }
            });

      form.setFieldWidth(1);

      form.addField(String.class, GridEditorReportDtoPA.INSTANCE.arguments(), GridEditorMessages.INSTANCE.arguments(),
            new SFFCTextAreaImpl() {
               @Override
               public int getHeight() {
                  return 75;
               }
            });
   }

   @Override
   protected boolean isDisplayConfigFieldsForDatasource() {
      return false;
   }

}
