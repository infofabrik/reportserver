package net.datenwerke.gf.client.managerhelper.mainpanel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel.TabPanelAppearance;
import com.sencha.gxt.widget.core.client.TabPanel.TabPanelBottomAppearance;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewProviderHook;
import net.datenwerke.gf.client.managerhelper.hooks.TreePostSelectAsyncHook;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.DwTabPanel;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormSubmissionListener;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

/*
 * View containing embedded MainPanelViews in Tabs. 
 */
public abstract class AbstractEmbeddingTabbedComponentsView extends MainPanelView {

   private final Map<MainPanelView, Widget> viewComponents = new HashMap<MainPanelView, Widget>();
   private final Map<MainPanelView, VerticalLayoutContainer> tabbedPanelsLookup = new HashMap<MainPanelView, VerticalLayoutContainer>();

   @Inject
   private HookHandlerService hookHandler;

   private DwTabPanel tabsetPanel;
   private String showTabOnSelection;

   private DwContentPanel tabsetContentPanel = new DwContentPanel();
   protected VerticalLayoutContainer tabsetContainer = new VerticalLayoutContainer();

   protected void createTabsetWidget(AbstractNodeDto nodeDto) {

      tabsetContainer.clear();
      tabsetContentPanel.clear();
      tabsetContentPanel.setHeight(550);

      /* clear maps */
      viewComponents.clear();
      tabbedPanelsLookup.clear();

      tabsetContentPanel.setBodyStyle("border: solid 1px");

      tabsetContainer.add(tabsetContentPanel, new VerticalLayoutData(-1, 1));

      setHeadingText(nodeDto);

      Collection<MainPanelViewProviderHook> viewProviders = hookHandler.getHookers(MainPanelViewProviderHook.class);
      final List<MainPanelView> views = new ArrayList<MainPanelView>();
      for (MainPanelViewProviderHook viewProvider : viewProviders) {
         List<MainPanelView> newViews = viewProvider.mainPanelViewProviderHook_getView(nodeDto);
         if (null != newViews)
            for (MainPanelView view : newViews)
               if (view.appliesTo(nodeDto))
                  views.add(view);

      }

      /* execute chain */
      TreePostSelectAsyncHook me = new TreePostSelectAsyncHook() {
         @Override
         public void postprocessNode(final AbstractNodeDto selectedNode, List<TreePostSelectAsyncHook> next) {
            if (!views.isEmpty()) {
               tabsetPanel = new DwTabPanel(GWT.<TabPanelAppearance>create(TabPanelBottomAppearance.class));

               tabsetPanel.setAutoSelect(false);
               tabsetPanel.setBodyBorder(false);
               tabsetPanel.setBorders(false);

               /* add module's widget */
               boolean bFirst = true;
               for (final MainPanelView view : views) {
                  /* create inner tab panel */
                  final VerticalLayoutContainer viewWrapper = new VerticalLayoutContainer();
                  viewWrapper.setBorders(false);

                  /* add to lookup table */
                  tabbedPanelsLookup.put(view, viewWrapper);

                  /* add tab to panel */
                  TabItemConfig tabItemConfig = new TabItemConfig(view.getComponentHeader());
                  if (null != view.getIcon())
                     tabItemConfig.setIcon(view.getIcon());
                  tabsetPanel.add(viewWrapper, tabItemConfig);

                  /* initialize view */
                  if (bFirst) {
                     bFirst = false;
                     if (null == showTabOnSelection || !viewsContain(showTabOnSelection, views)) {
                        display(view, selectedNode, tree, false);
                        tabsetPanel.setActiveWidget(viewWrapper);
                     }
                  }

                  if (null != showTabOnSelection && showTabOnSelection.equals(view.getViewId())) {
                     display(view, selectedNode, tree, false);
                     tabsetPanel.setActiveWidget(viewWrapper);
                  }

                  tabsetContentPanel.setWidget(tabsetPanel);

               }

               /* add listener to tab selection */
               tabsetPanel.addSelectionHandler(new SelectionHandler<Widget>() {
                  @Override
                  public void onSelection(SelectionEvent<Widget> event) {
                     Widget viewWrapper = event.getSelectedItem();
                     for (Entry<MainPanelView, VerticalLayoutContainer> e : tabbedPanelsLookup.entrySet()) {
                        if (e.getValue() == viewWrapper) {
                           display(e.getKey(), selectedNode, tree, false);
                           break;
                        }
                     }
                  }
               });
            }
         }

         @Override
         public boolean consumes(AbstractNodeDto node) {
            return true;
         }
      };

      List<TreePostSelectAsyncHook> postSelectHookers = hookHandler.getHookers(TreePostSelectAsyncHook.class);
      Iterator<TreePostSelectAsyncHook> it = postSelectHookers.iterator();
      while (it.hasNext()) {
         if (!it.next().consumes(nodeDto))
            it.remove();
      }
      postSelectHookers.add(me);

      postSelectHookers.get(0).postprocessNode(nodeDto, postSelectHookers.subList(1, postSelectHookers.size()));

   }

   private void setHeadingText(AbstractNodeDto nodeDto) {
      String headingText = nodeDto.toDisplayTitle();
      if (null != nodeDto.getId())
         headingText = headingText + " (" + nodeDto.getId() + ")";

      tabsetContentPanel.setHeading(headingText);
   }

   private boolean viewsContain(String viewId, List<MainPanelView> views) {
      for (MainPanelView view : views)
         if (null != view.getViewId() && view.getViewId().equals(viewId))
            return true;

      return false;
   }

   protected abstract void recordUpdated(Object boundModel);

   protected void display(MainPanelView view, AbstractNodeDto selectedNode, UITree tree, boolean select) {
      VerticalLayoutContainer viewWrapper = tabbedPanelsLookup.get(view);
      if (null == viewWrapper)
         return;

      if (!viewComponents.containsKey(view)) {
         view.initializeView(selectedNode, tree, treeManager, mainPanel);
         Widget viewComponent = view.getViewComponent(selectedNode);
         viewComponents.put(view, viewComponent);

         viewWrapper.add(viewComponent, new VerticalLayoutData(1, 1));
         view.viewAdded(viewWrapper);

         if (view instanceof SimpleFormView) {
            SimpleFormView sfv = (SimpleFormView) view;
            sfv.addSubmissionListener(new SimpleFormSubmissionListener() {

               @Override
               public void formSubmitted(SimpleForm simpleForm) {
                  recordUpdated(simpleForm.getBoundModel());
                  setHeadingText((AbstractNodeDto) simpleForm.getBoundModel());
               }
            });
         }
      }

      if (select)
         tabsetPanel.setActiveWidget(viewWrapper, false);

      if (view.isSticky())
         showTabOnSelection = view.getViewId();
      else
         showTabOnSelection = null;

   }
}
