package net.datenwerke.rs.base.client.reportengines.table.helpers;

import static java.util.stream.Collectors.toList;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.http.client.Request;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.ListLoadConfig;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoader;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;

import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.forms.selection.SelectionPopup;
import net.datenwerke.gxtdto.client.servercommunication.callback.ModalAsyncCallback;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.gxtdto.client.utils.SqlTypes;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.BasicObjectModelKeyProvider;
import net.datenwerke.rs.base.client.datasources.statementmanager.StatementManagerDao;
import net.datenwerke.rs.base.client.reportengines.table.TableReportUtilityDao;
import net.datenwerke.rs.base.client.reportengines.table.columnfilter.locale.FilterMessages;
import net.datenwerke.rs.base.client.reportengines.table.dto.AdditionalColumnSpecDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.ColumnDtoPA;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class ColumnSelector extends SelectionPopup<ColumnDto> {

   @Inject
   private static StatementManagerDao statementManager;

   private final TableReportDto report;

   private boolean displayAdditionalColumns = true;
   private ModalAsyncCallback<ListLoadResult<ColumnDto>> mac;

   private boolean hasError = false;

   public ColumnSelector(final TableReportUtilityDao tableReportUtilityDao, final TableReportDto report,
         final String executeToken) {
      super(new ListStore<ColumnDto>(new BasicObjectModelKeyProvider<ColumnDto>()), getDisplayPropertiesLeft(),
            getDisplayPropertiesRight());

      final String execToken = executeToken + ":" + String.valueOf(Math.random());

      setLoader(new ListLoader<ListLoadConfig, ListLoadResult<ColumnDto>>(
            new RpcProxy<ListLoadConfig, ListLoadResult<ColumnDto>>() {

               @Override
               public void load(ListLoadConfig loadConfig,
                     final com.google.gwt.user.client.rpc.AsyncCallback<ListLoadResult<ColumnDto>> callback) {

                  mac = new ModalAsyncCallback<ListLoadResult<ColumnDto>>(3000) {

                     @Override
                     protected void doOnCancel() {
                        statementManager.cancelStatement(execToken);
                     };

                     @Override
                     public void doOnSuccess(com.sencha.gxt.data.shared.loader.ListLoadResult<ColumnDto> result) {
                        callback.onSuccess(result);
                     };

                     @Override
                     public void doOnFailure(Throwable caught) {
                        hasError = true;
                        callback.onFailure(caught);
                     }
                  };

                  Request request = tableReportUtilityDao.getReturnedColumns(report, execToken, mac);
                  mac.setRequest(request);
               }
            }));

      setSize(900, 600);
      setHeading(FilterMessages.INSTANCE.columnSelectionTitle());

      this.report = report;
   }

   @Override
   protected void initSelectionToolbar(DwToolBar toolbar) {
      DwTextButton refresh = new DwTextButton(BaseIcon.REFRESH);
      refresh.addSelectHandler(event -> loadData());
      toolbar.add(refresh);
      toolbar.add(new SeparatorToolItem());
   }

   @Override
   public void show() {
      super.show();

      /* update additional columns */
      if (!displayAdditionalColumns)
         return;
      
      allItemsStore.getAll()
         .stream()
         .filter(column -> column instanceof AdditionalColumnSpecDto)
         .forEach(allItemsStore::remove);

      allItemsStore.addAll(report.getAdditionalColumns());
   }

   private static Map<ValueProvider<ColumnDto, String>, String> getDisplayPropertiesLeft() {
      /*
       * note .. this cannot be put into a static variable since the messages are not
       * loaded directly at startup
       */

      HashMap<ValueProvider<ColumnDto, String>, String> displayPropertiesLeft = new LinkedHashMap<ValueProvider<ColumnDto, String>, String>();

      displayPropertiesLeft.put(ColumnDtoPA.INSTANCE.name(), FilterMessages.INSTANCE.column());
      displayPropertiesLeft.put(ColumnDtoPA.INSTANCE.defaultAlias(), FilterMessages.INSTANCE.defaultAlias());
      displayPropertiesLeft.put(ColumnDtoPA.INSTANCE.description(), FilterMessages.INSTANCE.description());
      displayPropertiesLeft.put(ColumnDtoPA.INSTANCE.defaultPreviewWidth(), FilterMessages.INSTANCE.defaultWidth());
      displayPropertiesLeft.put(new ValueProvider<ColumnDto, String>() {
         @Override
         public void setValue(ColumnDto object, String value) {
         }

         @Override
         public String getValue(ColumnDto object) {
            if (null != object.getType())
               return SqlTypes.getName(object.getType());
            return "";
         }

         @Override
         public String getPath() {
            return "type";
         }
      }, FilterMessages.INSTANCE.type());

      return displayPropertiesLeft;
   }

   private static Map<ValueProvider<ColumnDto, String>, String> getDisplayPropertiesRight() {
      /*
       * note .. this cannot be put into a static variable since the messages are not
       * loaded directly at startup
       */
      Map<ValueProvider<ColumnDto, String>, String> displayPropertiesRight = new LinkedHashMap<>();

      displayPropertiesRight.put(ColumnDtoPA.INSTANCE.name(), FilterMessages.INSTANCE.column());

      return displayPropertiesRight;
   }

   @Override
   public void initTools() {
      super.initTools();

      if (null != closeBtn) {
         closeBtn.addSelectHandler(event -> cancelSelected());
      }
   }

   @Override
   protected int getDefaultEastSize() {
      return 250;
   }

   protected void adaptColumnInGridForSelection(ColumnConfig<ColumnDto, ?> cc) {
      cc.setWidth(230);
   }

   @Override
   protected ColumnConfig<ColumnDto, ?> getAutoExpandColumn(List<ColumnConfig<ColumnDto, ?>> columns) {
      return columns.get(3);
   }

   @Override
   protected void dataLoaded() {
      if (displayAdditionalColumns) {
         List<AdditionalColumnSpecDto> additionalColumns = report.getAdditionalColumns();
         if (null != additionalColumns && !additionalColumns.isEmpty())
            allItemsStore.addAll(additionalColumns);
      }
   }

   public void displayAdditionalColumns(boolean b) {
      displayAdditionalColumns = b;
   }

   @Override
   protected List<ColumnConfig<ColumnDto, ?>> getColumnConfigForSelectionGrid() {
      List<ColumnConfig<ColumnDto, ?>> conf = super.getColumnConfigForSelectionGrid();

      ColumnConfig<ColumnDto, ColumnDto> colIcon = new ColumnConfig<ColumnDto, ColumnDto>(
            new IdentityValueProvider<ColumnDto>(), 35);
      colIcon.setCell(new AbstractCell<ColumnDto>() {
         @Override
         public void render(com.google.gwt.cell.client.Cell.Context context, ColumnDto value, SafeHtmlBuilder sb) {
            if (value instanceof AdditionalColumnSpecDto)
               sb.append(BaseIcon.CALCULATOR.toSafeHtml());
            if (value.isIndexColumn())
               sb.append(BaseIcon.INDEX.toSafeHtml());
         }
      });
      colIcon.setMenuDisabled(true);
      colIcon.setSortable(false);
      conf.add(0, colIcon);

      conf.get(1).setWidth(180);
      conf.get(2).setWidth(100);

      return conf;
   }

   @Override
   protected ColumnDto convertModelForSelection(ColumnDto model) {
      return ((ColumnDtoDec) model).cloneColumnForSelection();
   }

   @Override
   protected List<? extends ColumnDto> convertModelsForSelection(final List<ColumnDto> selection) {
      return selection
         .stream()
         .map(col -> (ColumnDtoDec) col)
         .map(ColumnDtoDec::cloneColumnForSelection)
         .collect(toList());
   }

   @Override
   protected void cancelSelected() {
      if (null != mac) {
         mac.cancelRequest();
      }
   }

   public boolean hasError() {
      return hasError;
   }

}
