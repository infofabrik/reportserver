package net.datenwerke.gxtdto.client.ui.helper.nav;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Singleton;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.IconProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;
import com.sencha.gxt.widget.core.client.tree.Tree;
import com.sencha.gxt.widget.core.client.tree.Tree.TreeAppearance;
import com.sencha.gxt.widget.core.client.tree.TreeSelectionModel;

@Singleton
public class NavigationPanelHelper {

   public <M> Tree<NavigationModelData<M>, String> createNavigationTreePanel(
         TreeStore<NavigationModelData<M>> navigationStore, final NavItemSelectionCallback<M> callback) {
      // TODO: Theme
      // return createNavigationTreePanel(navigationStore, callback,
      // (RepserTreeMenuAppearance) GWT.create(RepserTreeMenuAppearance.class));
      return createNavigationTreePanel(navigationStore, callback, (TreeAppearance) GWT.create(TreeAppearance.class));
   }

   public <M> Tree<NavigationModelData<M>, String> createNavigationTreePanel(
         TreeStore<NavigationModelData<M>> navigationStore, final NavItemSelectionCallback<M> callback,
         TreeAppearance appearance) {
      Tree<NavigationModelData<M>, String> navigationTree = new Tree<NavigationModelData<M>, String>(navigationStore,
            new ValueProvider<NavigationModelData<M>, String>() {
               @Override
               public String getValue(NavigationModelData<M> object) {
                  return object.getName();
               }

               @Override
               public void setValue(NavigationModelData<M> object, String value) {
               }

               @Override
               public String getPath() {
                  return "name";
               }
            }, appearance);

      /* icons */
      navigationTree.setIconProvider(new IconProvider<NavigationModelData<M>>() {
         @Override
         public ImageResource getIcon(NavigationModelData<M> model) {
            return model.getIcon();
         }
      });

      TreeSelectionModel<NavigationModelData<M>> sModel = new TreeSelectionModel<NavigationModelData<M>>();
      sModel.setSelectionMode(SelectionMode.SINGLE);
      sModel.addSelectionChangedHandler(new SelectionChangedHandler<NavigationModelData<M>>() {
         @Override
         public void onSelectionChanged(SelectionChangedEvent<NavigationModelData<M>> event) {
            if (null != event.getSelection())
               if (event.getSelection().isEmpty())
                  callback.selected(null);
               else
                  callback.selected(event.getSelection().get(0).getModel());
         }
      });
      navigationTree.setSelectionModel(sModel);

      return navigationTree;
   }

}
