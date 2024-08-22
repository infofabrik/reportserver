package net.datenwerke.gf.client.treedb.helper.menu;

import java.util.ArrayList;
import java.util.List;

import com.sencha.gxt.data.shared.TreeStore;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.UiTreeFactory;
import net.datenwerke.gf.client.treedb.selection.TreeSelectionPopup;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.selection.SelectionMode;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.servercommunication.callback.NotamCallback;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.BasicObjectModelKeyProvider;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.birt.client.reportengines.dto.BirtReportDto;
import net.datenwerke.rs.core.client.reportmanager.ReportManagerTreeLoaderDao;
import net.datenwerke.rs.core.client.reportmanager.ReportManagerTreeManagerDao;
import net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportVariantDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorReportDto;
import net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportDto;
import net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportDto;
import net.datenwerke.rs.scriptreport.client.scriptreport.dto.ScriptReportDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.dto.WriteDto;
import net.datenwerke.security.client.treedb.dto.decorator.SecuredAbstractNodeDtoDec;
import net.datenwerke.treedb.client.treedb.TreeDbManagerDao;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.client.treedb.locale.TreedbMessages;

public class DuplicateMenuItem extends TreeMenuItem {

   public DuplicateMenuItem(final TreeDbManagerDao treeManager) {
      super();
      
      setText(TreedbMessages.INSTANCE.duplicateText());
      addMenuSelectionListener((tree, node) -> treeManager.duplicateNode(node,
            new NotamCallback<AbstractNodeDto>(TreedbMessages.INSTANCE.duplicated())));
   }
   
   public DuplicateMenuItem(final ReportManagerTreeManagerDao treeHandler, 
         final ReportManagerTreeLoaderDao treeLoader, final UiTreeFactory uiTreeFactory) {
      super();

      setText(TreedbMessages.INSTANCE.duplicateText());
      addMenuSelectionListener((tree, node) -> {
         if (null != node)
            duplicateReportWithVariants(node, treeLoader, uiTreeFactory, treeHandler);
      });
   }

   @Override
   public void toBeDisplayed(AbstractNodeDto selectedItem) {
      disable();

      /* try to get parent */
      AbstractNodeDto parent = tree.getParentNode(selectedItem);
      if (null != parent && (!(parent instanceof SecuredAbstractNodeDtoDec)
            || !((SecuredAbstractNodeDtoDec) parent).isAccessRightsLoaded()
            || (((SecuredAbstractNodeDtoDec) parent).hasAccessRight(WriteDto.class)
                  && ((SecuredAbstractNodeDtoDec) parent).hasInheritedAccessRight(WriteDto.class)))) {
         enable();
      }
   }
   
   protected void duplicateReportWithVariants(AbstractNodeDto node, ReportManagerTreeLoaderDao treeLoader,
         UiTreeFactory uiTreeFactory, ReportManagerTreeManagerDao treeManager) {
      
      /* prepare store */
      TreeStore<AbstractNodeDto> store = new TreeStore<AbstractNodeDto>(new BasicObjectModelKeyProvider<>());
      store.setAutoCommit(true);
      mask(BaseMessages.INSTANCE.loadingMsg());
      treeLoader.getChildren(node, false, null, null, new RsAsyncCallback<List<AbstractNodeDto>>() {
         @Override
         public void onSuccess(List<AbstractNodeDto> result) {
            unmask();
            // if the report has variants
            if (null != result && result.size() > 0) {
               for (AbstractNodeDto node : result) 
                  if (node instanceof ReportVariantDto)
                     store.add((ReportDto) node);
                  
               UITree tree = uiTreeFactory.create(store);
               tree.setIconProvider(model -> {
                  if (model instanceof TableReportDto)
                     return BaseIcon.REPORT_DL.toImageResource();
                  if (model instanceof JasperReportDto)
                     return BaseIcon.REPORT_JASPER.toImageResource();
                  if (model instanceof BirtReportDto)
                     return BaseIcon.REPORT_BIRT.toImageResource();
                  if (model instanceof CrystalReportDto)
                     return BaseIcon.REPORT_CRYSTAL.toImageResource();
                  if (model instanceof GridEditorReportDto)
                     return BaseIcon.REPORT_GE.toImageResource();
                  if (model instanceof JxlsReportDto)
                     return BaseIcon.REPORT_JXLS.toImageResource();
                  if (model instanceof SaikuReportDto)
                     return BaseIcon.REPORT_SAIKU.toImageResource();
                  if (model instanceof ScriptReportDto)
                     return BaseIcon.SCRIPT.toImageResource();
                  
                  return BaseIcon.REPORT.toImageResource();
               });
               TreeSelectionPopup popup = new TreeSelectionPopup(tree, ReportDto.class) {
                  @Override
                  protected void itemsSelected(List<AbstractNodeDto> selectedItems) {
                     // if we have selected variants
                     if (selectedItems.size() > 0) {
                        List<AbstractNodeDto> selectedVariants = new ArrayList<>(selectedItems);
                        treeManager.duplicateReportWithVariants(node, selectedVariants,
                              new NotamCallback<AbstractNodeDto>(TreedbMessages.INSTANCE.duplicated()));
                     } else {
                        // if we have no selected variants
                        treeManager.duplicateNode(node,
                              new NotamCallback<AbstractNodeDto>(TreedbMessages.INSTANCE.duplicated()));
                     }
                  }
               };
               
               popup.setHeading(TreedbMessages.INSTANCE.selectVariants());
               popup.setSelectionMode(SelectionMode.MULTI);
               popup.setHeaderIcon(BaseIcon.REPORT);
               popup.show();
            } else {
               // if the report has no variants
               treeManager.duplicateNode(node, new NotamCallback<AbstractNodeDto>(TreedbMessages.INSTANCE.duplicated()));
            }
         }
      });
      
   }
   
}
