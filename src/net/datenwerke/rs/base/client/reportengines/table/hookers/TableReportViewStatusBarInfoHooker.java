package net.datenwerke.rs.base.client.reportengines.table.hookers;

import com.google.gwt.core.client.Duration;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.WhiteSpace;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.Status;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar.PagingToolBarAppearance;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.form.DwNumberField;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwAlertMessageBox;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.model.DwModel;
import net.datenwerke.rs.base.client.AvailableReportProperties;
import net.datenwerke.rs.base.client.datasources.statementmanager.StatementManagerDao;
import net.datenwerke.rs.base.client.reportengines.table.TableReportUtilityDao;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportInformation;
import net.datenwerke.rs.core.client.i18tools.FormatUiHelper;
import net.datenwerke.rs.core.client.reportexecutor.hooks.ReportPreviewViewStatusbarHook;
import net.datenwerke.rs.core.client.reportexecutor.locale.ReportexecutorMessages;
import net.datenwerke.rs.core.client.reportexecutor.ui.preview.AbstractReportPreviewView;
import net.datenwerke.rs.core.client.reportexecutor.ui.preview.AbstractReportPreviewView.ExecutionDoneCallback;
import net.datenwerke.rs.core.client.reportexecutor.ui.preview.PageablePreviewView;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.decorator.ReportDtoDec;

/**
 * 
 *
 */
public class TableReportViewStatusBarInfoHooker implements ReportPreviewViewStatusbarHook {

   private final static PagingToolBarAppearance pagingAppearance = GWT
         .<PagingToolBarAppearance>create(PagingToolBarAppearance.class);

   private static final String BUTTON_STYLE_INFORM = "rs-btn-inform";

   private final TableReportUtilityDao tableReportUtilityDao;
   private final StatementManagerDao statementManager;
   private final Provider<FormatUiHelper> formatHelperProvider;

   private Status countDataSet;
   private Status executeDuration;
   private Status countColumns;
   private Status countPages;

   private int currentPage = 1;
   private int numberOfPages = -1;

   private NumberField<Integer> pageField;
   private boolean countAutomatic;

   private ToolBar toolbar;
   private String tokenLocalPart = "";

   private TextButton countRowsBtn;

   private SeparatorToolItem countColumnsSep;

   private SeparatorToolItem countDataSetSep;

   @Inject
   public TableReportViewStatusBarInfoHooker(
         TableReportUtilityDao tableReportUtilityService,
         StatementManagerDao statementManager, 
         Provider<FormatUiHelper> formatHelperProvider
         ) {

      this.tableReportUtilityDao = tableReportUtilityService;
      this.statementManager = statementManager;
      this.formatHelperProvider = formatHelperProvider;
   }

   @Override
   public void reportPreviewViewStatusbarHook_addLeft(final AbstractReportPreviewView reportPreviewView,
         ToolBar toolbar, final ReportDto report) {
      if (!(report instanceof TableReportDto))
         return;

      countAutomatic = "auto".equals(((ReportDtoDec) report).getEffectiveReportProperty(
            AvailableReportProperties.PROPERTY_DL_PREVIEW_COUNT_DEFAULT.getValue(), "auto"));

      /* paging buttons */
      if (reportPreviewView instanceof PageablePreviewView) {
         DwTextButton pagePrefBtn = new DwTextButton();
         pagePrefBtn.addSelectHandler(event -> {
            if (currentPage > 1)
               setPageCount(--currentPage, (PageablePreviewView) reportPreviewView);
         });
         pagePrefBtn.setIcon(pagingAppearance.prev());
         pagePrefBtn.addStyleName(BUTTON_STYLE_INFORM);
         toolbar.add(pagePrefBtn);

         pageField = new DwNumberField<Integer>(new NumberPropertyEditor.IntegerPropertyEditor());
         pageField.setWidth(30);
         pageField.setValue(currentPage);
         pageField.addValueChangeHandler(event -> {
            if (null != pageField.getValue() && currentPage != pageField.getValue().intValue())
               setPageCount(Math.max(0, Math.min(numberOfPages, pageField.getValue().intValue())),
                     (PageablePreviewView) reportPreviewView);
         });

         toolbar.add(pageField);

         DwTextButton pageNextBtn = new DwTextButton();
         pageNextBtn.addSelectHandler(event -> {
            if (numberOfPages == -1 || currentPage < numberOfPages)
               setPageCount(++currentPage, (PageablePreviewView) reportPreviewView); 
            });
         pageNextBtn.setIcon(pagingAppearance.next());
         pageNextBtn.addStyleName(BUTTON_STYLE_INFORM);
         toolbar.add(pageNextBtn);
      }

      countPages = new Status();
      countPages.setBorders(false);
      toolbar.add(countPages);

      this.toolbar = toolbar;
   }

   protected void setPageCount(int page, PageablePreviewView reportPreviewView) {
      pageField.setValue(page);
      reportPreviewView.setPageNumber(page);
      currentPage = reportPreviewView.getPageNumber();
   }

   @Override
   public void reportPreviewViewStatusbarHook_addRight(final AbstractReportPreviewView reportPreviewView,
         ToolBar toolbar, final ReportDto report) {
      /* count rows btn */
      countRowsBtn = new TextButton(ReportexecutorMessages.INSTANCE.countRecords());
      countRowsBtn.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            if (countAutomatic)
               return;

            countRecords((TableReportDto) report, reportPreviewView);
         }
      });
      toolbar.add(countRowsBtn);

      /* count the dataset */
      countDataSet = new Status();
      countDataSet.setBorders(false);
      countDataSet.getElement().getStyle().setWhiteSpace(WhiteSpace.NOWRAP);
      toolbar.add(countDataSet);
      countDataSetSep = new SeparatorToolItem();
      toolbar.add(countDataSetSep);

      /* count columns */
      countColumns = new Status();
      countColumns.setBorders(false);
      countColumns.getElement().getStyle().setWhiteSpace(WhiteSpace.NOWRAP);
      toolbar.add(countColumns);
      countColumnsSep = new SeparatorToolItem();

      toolbar.add(countColumnsSep);

      /* duration */
      executeDuration = new Status();
      executeDuration.setBorders(false);
      executeDuration.getElement().getStyle().setWhiteSpace(WhiteSpace.NOWRAP);
      toolbar.add(executeDuration);
   }

   @Override
   public void reportPreviewViewStatusbarHook_reportToBeReloaded(ReportDto report,
         AbstractReportPreviewView abstractReportPreviewView) {
      if (!(report instanceof TableReportDto) || ((TableReportDto) report).isCubeFlag())
         return;

      currentPage = 1;
      pageField.setValue(currentPage);
      reportPreviewViewStatusbarHook_reportUpdated(report, abstractReportPreviewView, true);
   }

   @Override
   public void reportPreviewViewStatusbarHook_reportUpdated(ReportDto report,
         final AbstractReportPreviewView abstractReportPreviewView, boolean configChanged) {
      if (!(report instanceof TableReportDto) || ((TableReportDto) report).isCubeFlag())
         return;

      if (!configChanged)
         return;

      final TableReportDto tReport = (TableReportDto) report;

      /* only go further if report has columns */
      if (((TableReportDto) report).getColumns().isEmpty()) {
         return;
      }

      if (abstractReportPreviewView instanceof PageablePreviewView) {
         currentPage = ((PageablePreviewView) abstractReportPreviewView).getPageNumber();
         pageField.setValue(currentPage);
      }

      if (countAutomatic) {
         countRowsBtn.hide();
         countRecords(tReport, abstractReportPreviewView);
      } else {
         countRowsBtn.show();
         countDataSet.hide();
         countColumns.hide();
         countPages.hide();
         executeDuration.hide();

         countDataSetSep.hide();
         countColumnsSep.hide();
      }

   }

   protected void countRecords(final TableReportDto tReport,
         final AbstractReportPreviewView abstractReportPreviewView) {
      countRowsBtn.hide();

      countDataSet.show();
      countColumns.show();
      countPages.show();
      executeDuration.show();

      countDataSetSep.show();
      countColumnsSep.show();

      /* set busy */
      countDataSet.setBusy(ReportexecutorMessages.INSTANCE.loadingNumRecords());
      countColumns.setBusy(ReportexecutorMessages.INSTANCE.loadingNumColumns());
      countPages.setBusy(ReportexecutorMessages.INSTANCE.loadingNumPages());
      executeDuration.setBusy(ReportexecutorMessages.INSTANCE.loadingExecuteDuration());

      /* server call */
      final double start = Duration.currentTimeMillis();
      tokenLocalPart = String.valueOf(Math.random());
      String token = abstractReportPreviewView.getExecuteReportToken() + ":" + tokenLocalPart;
      tableReportUtilityDao.getReportInformation(tReport, token, new RsAsyncCallback<TableReportInformation>() {
         @Override
         public void onSuccess(TableReportInformation information) {
            int nrOfRows = information.getDataCount();
            String formattedNumberOfRows = "";
            try {
               formattedNumberOfRows = formatHelperProvider.get().getNumberFormat().format(nrOfRows);
            } catch (Exception e) {
               formattedNumberOfRows = "" + nrOfRows;
            }
            countDataSet.clearStatus(ReportexecutorMessages.INSTANCE.records() + formattedNumberOfRows);

            String duration = ReportexecutorMessages.INSTANCE.executeDuration(
                  information.getExecuteDuration() / (double) 1000,
                  (Duration.currentTimeMillis() - start) / (double) 1000);
            executeDuration.clearStatus(duration);

            numberOfPages = (int) Math.ceil( (((double)information.getDataCount()
                  / (double)((PageablePreviewView) abstractReportPreviewView).getPageSize())));
            countPages.clearStatus(ReportexecutorMessages.INSTANCE.pages() + numberOfPages);

            countColumns.clearStatus(ReportexecutorMessages.INSTANCE.columns() + information.getVisibleCount() + "/"
                  + tReport.getColumns().size() + "/" + information.getColumnCount());

            toolbar.forceLayout();
         }

         @Override
         public void onFailure(final Throwable caught) {
            abstractReportPreviewView.callbackOnExecutionDone(new ExecutionDoneCallback() {
               @Override
               public void executionDone() {
                  if (!abstractReportPreviewView.isReportExecutionFailed())
                     new DwAlertMessageBox(BaseMessages.INSTANCE.error(), caught.getMessage()).show();
               }
            });
         }
      });
   }

   @Override
   public void reportPreviewViewStatusbarHook_reportLoaded(ReportDto report, AsyncCallback<DwModel> modalAsyncCallback,
         DwModel result, boolean configChanged) {
      // TODO Auto-generated method stub

   }

   @Override
   public void reportPreviewViewStatusbarHook_cancel(ReportDto report,
         AbstractReportPreviewView abstractReportPreviewView) {
      if (!(report instanceof TableReportDto) || ((TableReportDto) report).isCubeFlag())
         return;

      String token = abstractReportPreviewView.getExecuteReportToken() + ":" + tokenLocalPart;
      statementManager.cancelStatement(token);
   }

}
