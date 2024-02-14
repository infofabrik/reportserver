package net.datenwerke.rs.transport.client.transport.ui.forms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.tree.Tree;
import com.sencha.gxt.widget.core.client.tree.Tree.CheckCascade;
import com.sencha.gxt.widget.core.client.tree.Tree.CheckState;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwConfirmMessageBox;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.theme.CssClassConstant;
import net.datenwerke.gxtdto.client.ui.helper.nav.NavItemSelectionCallback;
import net.datenwerke.gxtdto.client.ui.helper.nav.NavigationModelData;
import net.datenwerke.gxtdto.client.ui.helper.nav.NavigationPanelHelper;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.BasicObjectModelKeyProvider;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.eximport.client.eximport.im.hooks.ImporterConfiguratorHook;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.transport.client.transport.TransportDao;
import net.datenwerke.rs.transport.client.transport.dto.TransportDto;
import net.datenwerke.rs.transport.client.transport.locale.TransportMessages;
import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;

public class ObjectTreeForm extends SimpleFormView {

   @CssClassConstant
   public static final String CSS_IMPORT_MAIN_TREE = "rs-transport-items-tree";

   private final TransportDao transportDao;
   private final HookHandlerService hookHandler;
   private final NavigationPanelHelper navPanelHelper;

   private DwContentPanel importerNavPanel;
   private DwContentPanel importerMainPanel;
   private DwContentPanel buttonMainPanel;
   private VerticalLayoutContainer container;
   private TreeStore<NavigationModelData<ImporterConfiguratorHook>> store;
   protected TreeStore<NavigationModelData<ImportTreeModel>> objectStore;
   private Set<ImporterConfiguratorHook> importers;
   private Tree<NavigationModelData<ImporterConfiguratorHook>, String> tree;   
   private Map<String, String> exporterMap = new HashMap<String, String>();
   private Tree<NavigationModelData<ImportTreeModel>, String> objectTree;
   
   @Inject
   public ObjectTreeForm(
         HookHandlerService hookHandler,
         NavigationPanelHelper navPanelHelper,
         TransportDao transportDao
         ) {
      super();

      /* store objects */
      this.hookHandler = hookHandler;
      this.navPanelHelper = navPanelHelper;
      this.transportDao = transportDao;
   }

   @Override
   public String getComponentHeader() {
      return TransportMessages.INSTANCE.objects();
   }

   private void init(final List<ImportTreeModel> importTreeModelList) {
      importerNavPanel.unmask();
      importerMainPanel.unmask();
      buttonMainPanel.unmask();
      loadImporters(importTreeModelList);

      /* display modules in tree */
      store = new TreeStore<>(new BasicObjectModelKeyProvider<>());

      /* add modules */
      for (ImporterConfiguratorHook importer : importers) {
         store.add(new NavigationModelData<>(
               importer.getImporterName() + " (" + exporterMap.get(importer.getImporterId()) + ")", importer.getImporterIcon(),
               importer));
      }

      /* build tree */
      tree = navPanelHelper.createNavigationTreePanel(store, (model) -> showImporter(model, importTreeModelList));

      importerMainPanel.setWidget(tree);
      container.add(importerMainPanel);
      container.add(importerNavPanel);
      form.add(container);
   };

   private void showImporter(ImporterConfiguratorHook model, List<ImportTreeModel> importTreeModelList) {
      form.clear();
      
      objectStore = new TreeStore<NavigationModelData<ImportTreeModel>>(
            new BasicObjectModelKeyProvider<NavigationModelData<ImportTreeModel>>());

      ImportTreeModel rootImportTreeModel = new ImportTreeModel();
      rootImportTreeModel.setType("folder");
      
      NavigationModelData<ImportTreeModel> root = new NavigationModelData<ImportTreeModel>("Root",
            BaseIcon.FOLDER.toImageResource(), rootImportTreeModel);
      objectStore.add(root);

      List<ImportTreeModel> importTreeModelChildrenList = new ArrayList<>();
      for (ImportTreeModel importTreeModel : importTreeModelList) {
         if (importTreeModel.getName().contains(model.getImporterId().replace("Importer", ""))) {
            importTreeModelChildrenList = importTreeModel.getChildren();
            break;
         }
      }

      setChildrenTree(root, importTreeModelChildrenList);

      objectTree = navPanelHelper
            .createNavigationTreePanel(objectStore, new NavItemSelectionCallback<ImportTreeModel>() {
               @Override
               public void selected(ImportTreeModel model) {
               }
            });
      
      if (!((TransportDto) getSelectedNode()).isClosed()) {
         objectTree.setCheckable(true);
         objectTree.setCheckStyle(CheckCascade.CHILDREN);      
         objectTree.addCheckChangeHandler(event -> {
            if (null == event.getChecked())
               return;

            final NavigationModelData<ImportTreeModel> contextMenuItem = event.getItem();

            for (NavigationModelData<ImportTreeModel> child : objectStore.getChildren(contextMenuItem)) {
               final CheckState newCheckState;
               if (CheckState.CHECKED.name().equals(event.getChecked().name())) {
                  newCheckState = CheckState.UNCHECKED;
               } else {
                  newCheckState = CheckState.CHECKED;
               }

               objectTree.setChecked(child, newCheckState);
            }
         });         
      }
          
      importerNavPanel.add(objectTree);
      importerMainPanel.add(tree);

      container.add(importerMainPanel);
      container.add(importerNavPanel);
      
      if (!((TransportDto) getSelectedNode()).isClosed()) {
         final DwTextButton bSubmit = new DwTextButton(TransportMessages.INSTANCE.deleteAllSelectedObjects(), BaseIcon.CHECK);
         
         /* listen to selection events */
         bSubmit.addSelectHandler(event -> {
            final ConfirmMessageBox cmb = new DwConfirmMessageBox(BaseMessages.INSTANCE.confirmDeleteTitle(),
                  TransportMessages.INSTANCE.confirmDeleteAllMsg());
            cmb.addDialogHideHandler(dialogHideEvent -> {
               if (dialogHideEvent.getHideButton() == PredefinedButton.YES) {              
                     /* perform server call */
                  removeElements();
               }
            });
            cmb.show();
         });
         
         buttonMainPanel.add(bSubmit);
         container.add(buttonMainPanel);         
      }

      form.add(container);
      form.forceLayout();
   }

   private void setChildrenTree(NavigationModelData<ImportTreeModel> parent,
         List<ImportTreeModel> importTreeModelList) {
      for (ImportTreeModel importTreeModel : importTreeModelList) {
         if (importTreeModel.getType().equals("folder")) {
            NavigationModelData<ImportTreeModel> subTreeParent = new NavigationModelData<ImportTreeModel>(
                  importTreeModel.getName(), BaseIcon.FOLDER.toImageResource(), importTreeModel);
            objectStore.add(parent, subTreeParent);
            setChildrenTree(subTreeParent, importTreeModel.getChildren());
         } else {
            objectStore.add(parent, new NavigationModelData<ImportTreeModel>(importTreeModel.getName(),
                  BaseIcon.from(importTreeModel.getType()).toImageResource(), importTreeModel));
         }
      }
   }

   private void loadImporters(List<ImportTreeModel> importTreeModelList) {
      importers = new HashSet<>();
      for (ImporterConfiguratorHook importer : hookHandler.getHookers(ImporterConfiguratorHook.class)) {
         if (null != importer.getSupportedExporters()) {
            for (String supportedExporter : importer.getSupportedExporters()) {
               for (ImportTreeModel importTreeModel : importTreeModelList) {
                  if (importTreeModel.getName().contains(supportedExporter)) {
                     exporterMap.put(importer.getImporterId(), importTreeModel.getName());
                     importers.add(importer);
                  }

               }
            }
         }
      }
   }

   @Override
   protected void configureSimpleForm(SimpleForm form) {
      instatiateContainer();
      instantiateImporterNavPanel();
      instantiateImporterMainPanel();
      instantiateButtonMainPanel();

      transportDao.extractTreeModel((TransportDto) getSelectedNode(), new RsAsyncCallback<List<ImportTreeModel>>() {

         public void onSuccess(List<ImportTreeModel> importTreeModelList) {
            form.setHeading(TransportMessages.INSTANCE.objects());
            init(importTreeModelList);
         }

         public void onFailure(Throwable caught) {
            form.unmask();
         };
      });
   }

   private void instatiateContainer() {
      container = new VerticalLayoutContainer();
      container.addStyleName("rs-import-view-nav");
      container.setVisible(true);
   }

   private void instantiateImporterNavPanel() {
      importerNavPanel = DwContentPanel.newInlineInstance();
      importerNavPanel.addClassName("rs-import-view-nav");
      container.add(importerNavPanel);
   }

   private void instantiateImporterMainPanel() {
      importerMainPanel = DwContentPanel.newInlineInstance();
      importerMainPanel.addClassName("rs-import-view-nav");
      container.add(importerMainPanel);
   }
   
   private void instantiateButtonMainPanel() {
      buttonMainPanel = DwContentPanel.newInlineInstance();
      buttonMainPanel.addClassName("rs-import-view-nav");
      container.add(buttonMainPanel);
   }
   
   protected void removeElements() {
      TransportDto transportDto = (TransportDto) getSelectedNode();
      List<ImportTreeModel> elementsToRemove = new ArrayList<>();
      
      for (NavigationModelData<ImportTreeModel> selectedItem : objectTree.getCheckedSelection()) {
         final ImportTreeModel item = selectedItem.getModel();

         if (!"folder".equals(item.getType()) && elementsToRemove.contains(item)) {
            elementsToRemove.remove(item);
         } else if (!"folder".equals(item.getType()) && !elementsToRemove.contains(item)) {
            elementsToRemove.add(item);
         }
         
         item.setType(tree.getSelectionModel().getSelectedItem().getName());
      }
      
      elementsToRemove.stream().forEach(c -> c.setType(exporterMap.get(tree.getSelectionModel().getSelectedItem().getModel().getImporterId())));
      mask(BaseMessages.INSTANCE.storingMsg());
      if (elementsToRemove.size() > 0) {
         transportDao.removeElements(transportDto, elementsToRemove, new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
               unmask();
            }
            
            @Override
            public void onSuccess(Void result) {
               Info.display(TransportMessages.INSTANCE.success(), TransportMessages.INSTANCE.deleteSuccess());
               unmask();
               reloadNodeAndView();
            };
         });
      } 
   }
}