package net.datenwerke.rs.base.client.reportengines.table.columnfilter.hookers;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.menu.SeparatorMenuItem;

import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.base.client.reportengines.table.TableReportUiService;
import net.datenwerke.rs.base.client.reportengines.table.columnfilter.locale.FilterMessages;
import net.datenwerke.rs.base.client.reportengines.table.dto.AggregateFunctionDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.NullHandlingDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.OrderDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.TableReportDtoPA;
import net.datenwerke.rs.base.client.reportengines.table.helpers.ColumnFilterWindow;
import net.datenwerke.rs.base.client.reportengines.table.helpers.ColumnFormatWindow;
import net.datenwerke.rs.base.client.reportengines.table.hooks.TableReportPreviewCellEnhancerHook;
import net.datenwerke.rs.base.client.reportengines.table.locale.EnumMessages;
import net.datenwerke.rs.base.client.reportengines.table.ui.TableReportPreviewView;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class PreviewEnhancerHook implements TableReportPreviewCellEnhancerHook {

   final protected TableReportUiService tableReportService;

   @Inject
   public PreviewEnhancerHook(TableReportUiService tableReportService) {
      this.tableReportService = tableReportService;
   }

   @Override
   public boolean consumes(TableReportDto report, ColumnDto column, String value, String rawValue, String[] rowValues) {
      return true;
   }

   @Override
   public boolean enhanceMenu(final TableReportPreviewView view, Menu menu, TableReportDto report,
         final ColumnDto column, final String value, String rawValue, String[] rowValues) {

      addRefreshMenu(view, menu);

      if (!report.isConfigurationProtected()) {
         /* sorting */
         addSortMenu(view, menu, column);

         /* filter */
         if (!report.isEnableSubtotals())
            addFilterMenu(view, menu, column, rawValue);

         /* format */
         addFormatMenu(view, menu, column);

         /* aggregation */
         if (!report.isEnableSubtotals())
            addAggregationMenu(view, menu, column);

         return true;
      }

      return false;
   }

   private void addAggregationMenu(final TableReportPreviewView view, Menu menu, final ColumnDto column) {
      Menu aggMenu = new DwMenu();
      MenuItem aggMenuItem = new DwMenuItem(FilterMessages.INSTANCE.aggregate());
      aggMenuItem.setSubMenu(aggMenu);
      menu.add(aggMenuItem);

      DwMenuItem noFuncMenu = new DwMenuItem(EnumMessages.INSTANCE.none());
      if (null == column.getAggregateFunction())
         noFuncMenu.setIcon(BaseIcon.CHECK);
      aggMenu.add(noFuncMenu);
      noFuncMenu.addSelectionHandler(new SelectionHandler<Item>() {
         @Override
         public void onSelection(SelectionEvent<Item> event) {
            if (null == column.getAggregateFunction())
               return;

            column.setAggregateFunction(null);
            fireChangeAndReload(column, view);
         }
      });

      for (final AggregateFunctionDto aggFunc : tableReportService.getAvailableAggregateFunctionsFor(column)) {
         DwMenuItem aggFuncMenu = new DwMenuItem(aggFunc.toString());
         if (column.getAggregateFunction() == aggFunc)
            aggFuncMenu.setIcon(BaseIcon.CHECK);
         aggMenu.add(aggFuncMenu);

         aggFuncMenu.addSelectionHandler(new SelectionHandler<Item>() {
            @Override
            public void onSelection(SelectionEvent<Item> event) {
               if (aggFunc == column.getAggregateFunction())
                  return;

               column.setAggregateFunction(aggFunc);

               fireChangeAndReload(column, view);
            }
         });
      }

   }

   protected void fireChangeAndReload(ColumnDto column, TableReportPreviewView view) {
      column.fireObjectChangedEvent();
      view.getReport().fireObjectChangedEvent(TableReportDtoPA.INSTANCE.columns());
      view.reload();
   }

   private void addFormatMenu(final TableReportPreviewView view, Menu menu, final ColumnDto column) {
      Menu formatMenu = new DwMenu();
      MenuItem formatMenuItem = new DwMenuItem(FilterMessages.INSTANCE.format());
      formatMenuItem.setSubMenu(formatMenu);
      menu.add(formatMenuItem);

      MenuItem showFormat = new DwMenuItem(FilterMessages.INSTANCE.editColumnFormat());
      formatMenu.add(showFormat);
      showFormat.addSelectionHandler(new SelectionHandler<Item>() {

         @Override
         public void onSelection(SelectionEvent<Item> event) {
            final ColumnFormatWindow window = new ColumnFormatWindow((TableReportDto) view.getReport(), column) {
               @Override
               protected void onColumnFormatChange() {
                  super.onColumnFormatChange();
                  column.fireObjectChangedEvent();
                  view.getReport().fireObjectChangedEvent(TableReportDtoPA.INSTANCE.columns());

                  Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                     @Override
                     public void execute() {
                        view.reload();
                     }
                  });
               }
            };
            window.show();
         }
      });

      formatMenu.add(new SeparatorMenuItem());

      MenuItem removeFilters = new DwMenuItem(FilterMessages.INSTANCE.removeFormat());
      formatMenu.add(removeFilters);
      removeFilters.addSelectionHandler(new SelectionHandler<Item>() {
         @Override
         public void onSelection(SelectionEvent<Item> event) {
            ((ColumnDtoDec) column).removeAllFormats();
            fireChangeAndReload(column, view);
         }
      });
   }

   private void addFilterMenu(final TableReportPreviewView view, final Menu menu, final ColumnDto column,
         final String value) {
      Menu filterMenu = new DwMenu();
      MenuItem filterMenuItem = new DwMenuItem(FilterMessages.INSTANCE.previewEnhancerMenuFilterHeading());
      filterMenuItem.setSubMenu(filterMenu);
      menu.add(filterMenuItem);

      MenuItem showFilter = new DwMenuItem(FilterMessages.INSTANCE.editColumnFilter());
      filterMenu.add(showFilter);
      final String token = view.getExecuteReportToken() + ":" + String.valueOf(Math.random());
      showFilter.addSelectionHandler(new SelectionHandler<Item>() {
         @Override
         public void onSelection(SelectionEvent<Item> event) {
            final ColumnFilterWindow filter = new ColumnFilterWindow((TableReportDto) view.getReport(), column, token) {
               @Override
               protected void onHide() {
                  super.onHide();
                  column.fireObjectChangedEvent();
                  view.getReport().fireObjectChangedEvent(TableReportDtoPA.INSTANCE.columns());

                  Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                     @Override
                     public void execute() {
                        view.reload();
                     }
                  });

               }
            };
            filter.show();
         }
      });

      MenuItem excludeValueFilter = new DwMenuItem(FilterMessages.INSTANCE.excludeValue());
      filterMenu.add(excludeValueFilter);
      excludeValueFilter.addSelectionHandler(new SelectionHandler<Item>() {
         @Override
         public void onSelection(SelectionEvent<Item> event) {
            FilterDto filter = column.getFilter();
            if (null == filter) {
               filter = new FilterDtoDec();
               column.setFilter(filter);
            }
            List<String> excludeValues = filter.getExcludeValues();
            if (null == excludeValues) {
               excludeValues = new ArrayList<String>();
               filter.setExcludeValues(excludeValues);
            }
            if (null == value)
               column.setNullHandling(NullHandlingDto.Exlude);
            else
               excludeValues.add(value);

            /* set filter to refresh column */
            fireChangeAndReload(column, view);
         }
      });

      MenuItem includeValueFilter = new DwMenuItem(FilterMessages.INSTANCE.includeValue());
      filterMenu.add(includeValueFilter);
      includeValueFilter.addSelectionHandler(new SelectionHandler<Item>() {

         @Override
         public void onSelection(SelectionEvent<Item> event) {
            FilterDto filter = column.getFilter();
            if (null == filter) {
               filter = new FilterDtoDec();
               column.setFilter(filter);
            }
            List<String> includeValues = filter.getIncludeValues();
            if (null == includeValues) {
               includeValues = new ArrayList<String>();
               filter.setIncludeValues(includeValues);
            }
            if (null == value)
               column.setNullHandling(NullHandlingDto.Include);
            else
               includeValues.add(value);

            /* set filter to refresh column */
            fireChangeAndReload(column, view);
         }
      });

      filterMenu.add(new SeparatorMenuItem());

      MenuItem removeFilters = new DwMenuItem(FilterMessages.INSTANCE.removeAllFilters());
      filterMenu.add(removeFilters);
      removeFilters.addSelectionHandler(new SelectionHandler<Item>() {

         @Override
         public void onSelection(SelectionEvent<Item> event) {
            ((ColumnDtoDec) column).removeAllFilters();
            fireChangeAndReload(column, view);
         }
      });
   }

   private void addSortMenu(final TableReportPreviewView view, final Menu menu, final ColumnDto column) {
      Menu sortMenu = new DwMenu();
      MenuItem sortMenuItem = new DwMenuItem(FilterMessages.INSTANCE.orderHeading());
      sortMenuItem.setSubMenu(sortMenu);
      menu.add(sortMenuItem);

      MenuItem sortAscending = new DwMenuItem(FilterMessages.INSTANCE.orderAsc(), BaseIcon.SORT_ALPHA_ASC);
      sortMenu.add(sortAscending);
      sortAscending.addSelectionHandler(new SelectionHandler<Item>() {
         @Override
         public void onSelection(SelectionEvent<Item> event) {
            column.setOrder(OrderDto.ASC);

            fireChangeAndReload(column, view);
         }
      });

      MenuItem sortDescending = new DwMenuItem(FilterMessages.INSTANCE.orderDesc(), BaseIcon.SORT_ALPHA_DESC);
      sortMenu.add(sortDescending);
      sortDescending.addSelectionHandler(new SelectionHandler<Item>() {
         @Override
         public void onSelection(SelectionEvent<Item> event) {
            column.setOrder(OrderDto.DESC);

            fireChangeAndReload(column, view);
         }
      });

      MenuItem sortNo = new DwMenuItem(FilterMessages.INSTANCE.orderNone());
      sortMenu.add(sortNo);
      sortNo.addSelectionHandler(new SelectionHandler<Item>() {
         @Override
         public void onSelection(SelectionEvent<Item> event) {
            column.setOrder(null);
            fireChangeAndReload(column, view);
         }
      });
   }

   private void addRefreshMenu(final TableReportPreviewView view, Menu menu) {
      MenuItem refreshMenuItem = new DwMenuItem(BaseMessages.INSTANCE.refresh());
      refreshMenuItem.setIcon(BaseIcon.REFRESH.toImageResource());
      menu.add(refreshMenuItem);

      refreshMenuItem.addSelectionHandler(event -> view.reload());

   }

}
