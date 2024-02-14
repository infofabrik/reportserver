package net.datenwerke.rs.transport.client.transport.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.dialog.error.DetailErrorDialog;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.servercommunication.callback.ModalAsyncCallback;
import net.datenwerke.gxtdto.client.servercommunication.callback.NotamCallback;
import net.datenwerke.gxtdto.client.ui.helper.wrapper.ToolbarWrapperPanel;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.transport.client.transport.TransportDao;
import net.datenwerke.rs.transport.client.transport.TransportUiService;
import net.datenwerke.rs.transport.client.transport.dto.TransportCheckEntryDto;
import net.datenwerke.rs.transport.client.transport.dto.TransportDto;
import net.datenwerke.rs.transport.client.transport.dto.pa.TransportDtoPA;
import net.datenwerke.rs.transport.client.transport.locale.TransportMessages;

/**
 * 
 *
 */
@Singleton
public class TransportManagerAdminPanel extends DwContentPanel {

   private final TransportDao transportDao;
   private ListStore<TransportDto> transportStore;
   private TransportDtoPA transportManagerProps;
   private final Provider<TransportUiService> transportUiServiceProvider;

   @Inject
   public TransportManagerAdminPanel(
         TransportDao transportDao, 
         ToolbarService toolbarService,
         Provider<TransportUiService> transportUiServiceProvider
         ) {

      /* store objects */
      this.transportDao = transportDao;
      this.transportUiServiceProvider = transportUiServiceProvider;
      
      /* initialize ui */
      initializeUI();
   }

   private void initializeUI() {
      setHeading(TransportMessages.INSTANCE.viewNavigationTitle());

      /* create props */
      transportManagerProps = GWT.create(TransportDtoPA.class);

      /* create store */
      transportStore = new ListStore<>(transportManagerProps.dtoId());

      /* create grid */
      Component gridComponent = createGrid();

      DwContentPanel wrapper = new DwContentPanel();
      wrapper.setLightHeader();
      wrapper.setHeading(TransportMessages.INSTANCE.viewNavigationTitle());
      wrapper.setInfoText(TransportMessages.INSTANCE.dialogDescription());
      wrapper.add(gridComponent);

      VerticalLayoutContainer outerWrapper = new VerticalLayoutContainer();
      outerWrapper.add(wrapper, new VerticalLayoutData(1, 1, new Margins(10)));

      add(outerWrapper);
   }

   private void loadTransports() {
      transportDao.loadImportedTransports(new RsAsyncCallback<List<TransportDto>>() {
         @Override
         public void onSuccess(List<TransportDto> result) {
            /* clear store */
            transportStore.clear();
            transportStore.addAll(result);
            
            enable();
            unmask();
         }
      });
   }

   private Component createGrid() {
      /* configure columns */
      List<ColumnConfig<TransportDto, ?>> columns = new ArrayList<>();
      
      /* short key column */
      ColumnConfig<TransportDto, String> shortKeyConfig = new ColumnConfig<>(
            transportManagerProps.shortKey(), 200, TransportMessages.INSTANCE.shortKeyLabel());
      shortKeyConfig.setMenuDisabled(true);
      columns.add(shortKeyConfig);

      /* created on column */
      ColumnConfig<TransportDto, Date> createdOnConfig = new ColumnConfig<>(
            transportManagerProps.createdOn(), 200, TransportMessages.INSTANCE.createdOnLabel());
      createdOnConfig.setMenuDisabled(true);
      columns.add(createdOnConfig);
      
      /* description column */
      ColumnConfig<TransportDto, String> descriptionConfig = new ColumnConfig<>(
            transportManagerProps.description(), 200, TransportMessages.INSTANCE.descriptionLabel());
      descriptionConfig.setMenuDisabled(true);
      columns.add(descriptionConfig);

      /* create grid */
      final Grid<TransportDto> grid = new Grid<>(transportStore,
            new ColumnModel<TransportDto>(columns));
      grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
      grid.getView().setAutoExpandColumn(shortKeyConfig);
      grid.getView().setAutoExpandColumn(createdOnConfig);
      grid.getView().setAutoExpandColumn(descriptionConfig);
      grid.getView().setShowDirtyCells(false);

      ToolbarWrapperPanel wrapper = new ToolbarWrapperPanel(grid) {};
      
      /* transport rpull button */
      DwTextButton rpullBtn = new DwTextButton(TransportMessages.INSTANCE.rpull());
      rpullBtn.setIcon(BaseIcon.DOWNLOAD);
      rpullBtn.addSelectHandler(event -> {
         mask(BaseMessages.INSTANCE.busyMsg());
         transportDao.rpull(new NotamCallback<Void>(TransportMessages.INSTANCE.rpullOk()) {
            @Override
            public void doOnSuccess(Void result) {
               unmask();
            }

            @Override
            public void doOnFailure(Throwable caught) {
               unmask();
               throw new IllegalStateException(caught);
            }
         });
      });
      wrapper.getToolbar().add(rpullBtn);
      
      /* check preconditions */
      DwTextButton checkBtn = new DwTextButton(TransportMessages.INSTANCE.checkApplyPreconditions(), BaseIcon.BOOKMARK);
      checkBtn.addSelectHandler(event -> {
         mask(BaseMessages.INSTANCE.busyMsg());
         if (null != grid.getSelectionModel().getSelectedItem()) {
            transportDao.checkPreconditions((TransportDto) grid.getSelectionModel().getSelectedItem(),
                  new RsAsyncCallback<List<TransportCheckEntryDto>>() {
                     @Override
                     public void onSuccess(List<TransportCheckEntryDto> result) {
                        unmask();
                        transportUiServiceProvider.get().displayAnalysisResult(result);
                     }

                     @Override
                     public void onFailure(Throwable caught) {
                        unmask();
                        new DetailErrorDialog(caught).show();
                     }
                  });
         }
      });
      wrapper.getToolbar().add(checkBtn);
      
      /* apply */
      DwTextButton applyBtn = new DwTextButton(TransportMessages.INSTANCE.applyTransport());
      applyBtn.setIcon(BaseIcon.ADJUST);
      applyBtn.addSelectHandler(event -> {
         if (null != grid.getSelectionModel().getSelectedItem()) {
            ModalAsyncCallback<Void> callback = new ModalAsyncCallback<Void>(null,
                  null, TransportMessages.INSTANCE.success(),
                  TransportMessages.INSTANCE.applySuccess(), TransportMessages.INSTANCE.pleaseWait(),
                  TransportMessages.INSTANCE.applyingTitle(), TransportMessages.INSTANCE.applyingProgressMessage()) {
            };
            Request request = transportDao.apply((TransportDto) grid.getSelectionModel().getSelectedItem(), callback);
            callback.setRequest(request);
            
            
         }
      });
      
      wrapper.getToolbar().add(applyBtn);
      
      return wrapper;
   }
   
   public void notifyOfSelection() {
      disable();
      mask(BaseMessages.INSTANCE.loadingMsg());
      loadTransports();
   }

}
