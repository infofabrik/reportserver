package net.datenwerke.rs.dashboard.client.dashboard.ui;

import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.BasicObjectModelKeyProvider;
import net.datenwerke.gxtdto.client.xtemplates.NullSafeFormatter;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardDto;
import net.datenwerke.rs.dashboard.client.dashboard.hooks.DadgetProcessorHook;
import net.datenwerke.rs.dashboard.client.dashboard.locale.DashboardMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Event;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.XTemplates.FormatterFactories;
import com.sencha.gxt.core.client.XTemplates.FormatterFactory;
import com.sencha.gxt.core.client.XTemplates.FormatterFactoryMethod;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public class DadgetCatalog extends DwWindow {

   @FormatterFactories(@FormatterFactory(factory = NullSafeFormatter.class, methods = @FormatterFactoryMethod(name = "nullsafe")))
   interface DashboardTemplates extends XTemplates {
      @XTemplate("<div class=\"rs-dadget-catalog-wrap\">" + "<div class=\"rs-dadget-icon\">{icon}</div>"
            + "<div class=\"x-editable rs-dadget-title\">{title:nullsafe}</div>"
            + "<div class=\"rs-dadget-description\">{description:nullsafe}</div>" + "</div>")
      public SafeHtml render(DadgetProcessorModel model);
   }

   private final HookHandlerService hookHandler;
   private DashboardContainer container;
   private DashboardDto dashboard;

   @Inject
   public DadgetCatalog(HookHandlerService hookHandler, @Assisted DashboardDto dashboard,
         @Assisted DashboardContainer container) {

      this.hookHandler = hookHandler;
      this.dashboard = dashboard;
      this.container = container;

      initializeUI();
   }

   private void initializeUI() {
      setHeading(DashboardMessages.INSTANCE.addDadget());
      setSize(640, 480);
      setHeaderIcon(BaseIcon.DADGET);

      /* load dadgets */
      ListStore<DadgetProcessorModel> dadgetStore = new ListStore<DadgetProcessorModel>(
            new BasicObjectModelKeyProvider<DadgetProcessorModel>());
      int dadgetCnt = 0;
      for (DadgetProcessorHook processor : hookHandler.getHookers(DadgetProcessorHook.class)) {
         DadgetProcessorModel model = new DadgetProcessorModel();
         model.setTitle(processor.getTitle());
         model.setDescription(processor.getDescription());
         model.setIcon(processor.getIcon());
         model.setProcessor(processor);
         dadgetStore.add(model);
      }

      final DashboardTemplates xtemplate = GWT.create(DashboardTemplates.class);

      final ListView<DadgetProcessorModel, DadgetProcessorModel> view = new ListView<DadgetProcessorModel, DadgetProcessorModel>(
            dadgetStore, new IdentityValueProvider<DadgetProcessorModel>()) {
         @Override
         public void onBrowserEvent(Event event) {
            super.onBrowserEvent(event);

            switch (event.getTypeInt()) {
            case Event.ONDBLCLICK:
               DadgetProcessorModel selection = getSelectionModel().getSelectedItem();
               if (null == selection)
                  return;

               DadgetCatalog.this.hide();

               DadgetDto dadget = (selection.getProcessor()).instantiateDadget();

               container.addDadget(dashboard, dadget);

               break;
            }
         }
      };
      view.setCell(new AbstractCell<DadgetProcessorModel>() {
         @Override
         public void render(com.google.gwt.cell.client.Cell.Context context, DadgetProcessorModel value,
               SafeHtmlBuilder sb) {
            sb.append(xtemplate.render(value));
         }
      });
      view.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

      add(view);

      addCancelButton();

      DwTextButton submitBtn = new DwTextButton(BaseMessages.INSTANCE.add());
      addButton(submitBtn);
      submitBtn.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            DadgetProcessorModel selection = view.getSelectionModel().getSelectedItem();
            if (null == selection)
               return;

            hide();

            DadgetDto dadget = (selection.getProcessor()).instantiateDadget();

            container.addDadget(dashboard, dadget);
         }
      });
   }
}
