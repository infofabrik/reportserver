package net.datenwerke.rs.base.client.reportengines.table.helpers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwAlertMessageBox;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.lists.SFFCDynamicListInPopup;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.DtoIdModelKeyProvider;
import net.datenwerke.rs.base.client.reportengines.table.columnfilter.locale.FilterMessages;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.TableReportDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.ColumnDtoPA;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class EditSubtotalsWindow extends DwWindow {

   private final TableReportDto report;
   private SimpleForm form;
   private String groupColsKey;

   public EditSubtotalsWindow(TableReportDto report) {
      this.report = report;

      initUi();
   }

   private void initUi() {
      setSize(400, 400);
      setModal(true);
      setHeaderIcon(BaseIcon.SUBTOTALS);
      setHeading(FilterMessages.INSTANCE.editSubtotals());
      addStyleDependentName("rs-subtotal-edit");

      form = SimpleForm.getInlineInstance();

      final ListStore<ColumnDto> store = new ListStore<ColumnDto>(new DtoIdModelKeyProvider());
      for (ColumnDto col : report.getColumns())
         if (null == col.getAggregateFunction())
            store.add(col);

      groupColsKey = form.addField(List.class, FilterMessages.INSTANCE.subtotalGroupColumnsLabel(),
            new SFFCDynamicListInPopup<ColumnDto>() {
               @Override
               public Boolean isMultiselect() {
                  return true;
               }

               @Override
               public ListStore<ColumnDto> getStore() {
                  return store;
               }

               @Override
               public ValueProvider getStoreKeyForDisplay() {
                  return ColumnDtoPA.INSTANCE.name();
               }

               @Override
               public String getPropertyLabel() {
                  return FilterMessages.INSTANCE.selectedGroupingColumns();
               }
            });

      /* load values */
      List<ColumnDto> groupingCols = new ArrayList<ColumnDto>();
      if (report.isEnableSubtotals()) {
         for (ColumnDto col : report.getColumns())
            if (col.isSubtotalGroup())
               groupingCols.add(col);
      }
      form.setValue(groupColsKey, groupingCols);

      /* load fields */
      form.loadFields();

      VerticalLayoutContainer container = new VerticalLayoutContainer();

      DwToolBar tb = new DwToolBar();
      DwTextButton removeAll = new DwTextButton(BaseMessages.INSTANCE.removeAll(), BaseIcon.DELETE);
      tb.add(removeAll);
      removeAll.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            form.setValue(groupColsKey, new ArrayList<ColumnDto>());
         }
      });

      form.setScrollMode(ScrollMode.AUTO);

      container.add(tb, new VerticalLayoutData(1, -1));
      container.add(form, new VerticalLayoutData(1, 1));

      add(container, new MarginData(10));

      DwTextButton cancel = new DwTextButton(BaseMessages.INSTANCE.cancel());
      cancel.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            hide();
         }
      });
      addButton(cancel);

      /* submit */
      DwTextButton submitBtn = new DwTextButton(BaseMessages.INSTANCE.apply());
      submitBtn.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            for (ColumnDto col : report.getColumns()) {
               boolean hasFilters = ((ColumnDtoDec) col).hasFilters() || null != col.getAggregateFunction();
               if (hasFilters && null == col.getAggregateFunction()) {
                  /* check group columns */
                  List<ColumnDto> group = (List<ColumnDto>) form.getValue(groupColsKey);
                  if (group.size() > 0 && !group.contains(col)) {
                     DwAlertMessageBox mbox = new DwAlertMessageBox(BaseMessages.INSTANCE.warning(),
                           FilterMessages.INSTANCE.subTotalFilterWarningMsg());
                     mbox.show();
                     return;
                  }
               }
            }

            inheritSubtotals();
            hide();
         }
      });
      addButton(submitBtn);
   }

   protected void inheritSubtotals() {
      for (Iterator<ColumnDto> iterator = new ArrayList(report.getColumns()).iterator(); iterator.hasNext();)
         iterator.next().setSubtotalGroup(false);

      List<ColumnDto> groupingCols = (List<ColumnDto>) form.getValue(groupColsKey);
      boolean enable = (null != groupingCols && !groupingCols.isEmpty());

      if (enable) {
         for (ColumnDto col : groupingCols)
            col.setSubtotalGroup(true);

         if (((TableReportDtoDec) report).hasSubtotalGroupColumn())
            report.setEnableSubtotals(true);
         else
            report.setEnableSubtotals(false);
      } else {
         report.setEnableSubtotals(false);
      }
   }
}
