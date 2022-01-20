package net.datenwerke.rs.adminutils.client.systemconsole.memory.ui;

import java.util.stream.IntStream;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.Timer;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.sencha.gxt.chart.client.chart.Chart;
import com.sencha.gxt.chart.client.chart.Chart.Position;
import com.sencha.gxt.chart.client.chart.Legend;
import com.sencha.gxt.chart.client.chart.axis.CategoryAxis;
import com.sencha.gxt.chart.client.chart.axis.NumericAxis;
import com.sencha.gxt.chart.client.chart.series.LineSeries;
import com.sencha.gxt.chart.client.chart.series.Primitives;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.chart.client.draw.sprite.Sprite;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.info.DefaultInfoConfig;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.info.InfoConfig;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCAllowBlank;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCBoolean;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.adminutils.client.systemconsole.locale.SystemConsoleMessages;
import net.datenwerke.rs.adminutils.client.systemconsole.memory.MemoryConsoleDao;
import net.datenwerke.rs.adminutils.client.systemconsole.memory.dto.MemoryInfoDto;
import net.datenwerke.rs.adminutils.client.systemconsole.memory.dto.MemoryInfoDtoPA;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
@Singleton
public class MemoryInfoPanel extends DwContentPanel {

   private final MemoryConsoleDao memoryConsoleDao;

   private VerticalLayoutContainer wrapper;

   private Timer update;

   private int maxNumberOfUnits = 60;
   private boolean animationStopped = false;
   private boolean showMaxMb = true;
   private String showMaxKey;
   private String unitIntervalKey;
   private String numberOfUnitsKey;
   private double maxMb;

   // we call the rpc refresh every second by default
   private int unitInterval = 1000;

   private final Chart<MemoryInfoDto> chart = new Chart<>();
   private final NumericAxis<MemoryInfoDto> axis = new NumericAxis<>();
   LineSeries<MemoryInfoDto> seriesMax = new LineSeries<>();

   HorizontalLayoutContainer buttonsLayout = new HorizontalLayoutContainer();

   final MemoryInfoDtoPA props = MemoryInfoDtoPA.INSTANCE;
   final ListStore<MemoryInfoDto> store = new ListStore<>(props.key());

   @Inject
   public MemoryInfoPanel(MemoryConsoleDao memoryConsoleDao) {

      this.memoryConsoleDao = memoryConsoleDao;

      /* initialize ui */
      initializeUI();
   }

   private void initializeUI() {
      setHeading(SystemConsoleMessages.INSTANCE.jvmLiveMemory());
      addStyleName("rs-memoryconsole");

      wrapper = new VerticalLayoutContainer();
      wrapper.setScrollMode(ScrollMode.AUTOY);
      add(wrapper);

      createChart();
      createForm();
   }

   private void createChart() {
      TextSprite title = new TextSprite(SystemConsoleMessages.INSTANCE.jvmMemory() + " (MB)");
      title.setFontSize(18);

      axis.setPosition(Position.LEFT);
      axis.addField(props.usedMb());
      axis.setTitleConfig(title);
      axis.setMinorTickSteps(1);
      axis.setDisplayGrid(true);
      axis.setMinimum(0);

      title = new TextSprite(BaseMessages.INSTANCE.time() + " (s)");
      title.setFontSize(18);

      CategoryAxis<MemoryInfoDto, Long> categoriesAxis = new CategoryAxis<>();
      categoriesAxis.setPosition(Position.BOTTOM);
      categoriesAxis.setField(props.timestamp());
      categoriesAxis.setTitleConfig(title);
      categoriesAxis.setLabelProvider(item -> "");

      Sprite marker = Primitives.diamond(0, 0, 2);
      marker.setFill(new RGB(80, 89, 134));

      final LineSeries<MemoryInfoDto> seriesUsed = new LineSeries<>();
      seriesUsed.setYAxisPosition(Position.LEFT);
      seriesUsed.setLegendTitle(SystemConsoleMessages.INSTANCE.used() + " (MB)");
      seriesUsed.setYField(props.usedMb());
      seriesUsed.setStroke(new RGB(32, 38, 58));
      seriesUsed.setShowMarkers(true);
      seriesUsed.setSmooth(false);
      seriesUsed.setFill(new RGB(32, 38, 58));
      seriesUsed.setMarkerConfig(marker);
      seriesUsed.setHighlighting(true);

      final LineSeries<MemoryInfoDto> seriesTotal = new LineSeries<>();
      seriesTotal.setYAxisPosition(Position.LEFT);
      seriesTotal.setLegendTitle(SystemConsoleMessages.INSTANCE.total() + " (MB)");
      seriesTotal.setYField(props.totalMb());
      seriesTotal.setStroke(new RGB(209, 211, 228));
      seriesTotal.setShowMarkers(true);
      seriesTotal.setSmooth(false);
      seriesTotal.setFill(new RGB(209, 211, 228));
      seriesTotal.setMarkerConfig(marker);
      seriesTotal.setHighlighting(true);

      seriesMax.setYAxisPosition(Position.LEFT);
      seriesMax.setYField(props.maxMb());
      seriesMax.setLegendTitle(SystemConsoleMessages.INSTANCE.maximum() + " (MB)");
      seriesMax.setStroke(new RGB(151, 11, 48));
      seriesMax.setShowMarkers(true);
      seriesMax.setSmooth(false);
      seriesMax.setMarkerConfig(marker);
      seriesMax.setHighlighting(true);

      final Legend<MemoryInfoDto> legend = new Legend<>();
      legend.setItemHighlighting(true);
      legend.setItemHiding(true);
      legend.getBorderConfig().setStrokeWidth(0);

      chart.setStore(store);
      chart.setShadowChart(false);
      chart.addAxis(axis);
      chart.addAxis(categoriesAxis);
      chart.addSeries(seriesTotal);
      chart.addSeries(seriesUsed);
      chart.addSeries(seriesMax);
      chart.setLegend(legend);
      chart.setDefaultInsets(30);

      update = new Timer() {
         @Override
         public void run() {

            memoryConsoleDao.loadMemoryInfo(new RsAsyncCallback<MemoryInfoDto>() {
               @Override
               public void onSuccess(MemoryInfoDto result) {
                  super.onSuccess(result);
                  updateChart(result);
               }

               @Override
               public void onFailure(Throwable caught) {
                  super.onFailure(caught);
                  unmask();
               }
            });
         }
      };

      wrapper.add(chart, new VerticalLayoutData(1000, 550, new Margins(10)));
      update.scheduleRepeating(unitInterval);
   }

   private void createForm() {
      wrapper.add(buttonsLayout);

      DwTextButton playStopBtn = new DwTextButton(SystemConsoleMessages.INSTANCE.stop(), BaseIcon.STOP);
      playStopBtn.addSelectHandler(event -> {
         Info.display(BaseMessages.INSTANCE.changesApplied(), BaseMessages.INSTANCE.changesApplied());
         if (!animationStopped)
            stopAnimation(playStopBtn);
         else
            startAnimation(playStopBtn);
      });

      final SimpleForm form = SimpleForm.getNewInstance();
      form.setHeading(BaseMessages.INSTANCE.configuration());

      form.setFieldWidth(200);
      form.beginFloatRow();
      numberOfUnitsKey = form.addField(Integer.class, SystemConsoleMessages.INSTANCE.numberUnits(),
            new SFFCAllowBlank() {
               @Override
               public boolean allowBlank() {
                  return false;
               }
            });
      form.setValue(numberOfUnitsKey, 60);

      unitIntervalKey = form.addField(Integer.class, SystemConsoleMessages.INSTANCE.unitInterval(),
            new SFFCAllowBlank() {
               @Override
               public boolean allowBlank() {
                  return false;
               }
            });
      form.setValue(unitIntervalKey, 1);

      form.endRow();

      showMaxKey = form.addField(Boolean.class, "", new SFFCBoolean() {
         @Override
         public String getBoxLabel() {
            return SystemConsoleMessages.INSTANCE.showMax();
         }
      });
      form.setValue(showMaxKey, true);

      form.setValidateOnSubmit(true);
      form.addSubmissionListener(event -> {
         Info.display(BaseMessages.INSTANCE.changesApplied(), BaseMessages.INSTANCE.changesApplied());
         maxNumberOfUnits = (Integer) (form.getValue(numberOfUnitsKey));
         unitInterval = (Integer) (form.getValue(unitIntervalKey)) * 1000;
         if (!animationStopped) {
            update.cancel();
            update.scheduleRepeating(unitInterval);
         }
         if (showMaxMb) {
            axis.setMaximum(maxMb);
            showMaxMb = (boolean) form.getValue(showMaxKey);
         } else {
            axis.setMaximum(Double.NaN);
            showMaxMb = (boolean) form.getValue(showMaxKey);
         }
      });

      form.loadFields();

      /* garbage collector button */
      DwTextButton garbageCollectorBtn = new DwTextButton(SystemConsoleMessages.INSTANCE.jvmGC());
      garbageCollectorBtn.addSelectHandler(event -> memoryConsoleDao.callGarbageCollector(new RsAsyncCallback<Void>() {
         public void onSuccess(Void result) {
            InfoConfig infoConfig = new DefaultInfoConfig(BaseMessages.INSTANCE.changesApplied(),
                  SystemConsoleMessages.INSTANCE.jvmGCCalled());
            infoConfig.setWidth(350);
            infoConfig.setDisplay(3500);
            Info.display(infoConfig);
         };
      }));

      form.clearButtonBar();
      form.addButton(garbageCollectorBtn);
      form.addButton(playStopBtn);
      form.addSubmitButton();

      buttonsLayout.add(form, new HorizontalLayoutData(1000, 20, new Margins(10)));
   }

   protected void updateChart(final MemoryInfoDto memoryInfo) {
      store.add(memoryInfo);

      maxMb = memoryInfo.getMaxMb() + 100;

      if (!showMaxMb) {
         axis.setMaximum(Double.NaN);
         if (containsSeriesMax())
            chart.removeSeries(seriesMax);
      } else {
         axis.setMaximum(maxMb);
         if (!containsSeriesMax())
            chart.addSeries(seriesMax);
      }

      int numberOfUnitsToDelete = store.size() - maxNumberOfUnits;
      if (numberOfUnitsToDelete > 0) {
         IntStream.range(0, numberOfUnitsToDelete).forEach(store::remove);
      }

      chart.redrawChart();
      Scheduler.get().scheduleDeferred(forceLayoutCommand);
   }

   private boolean containsSeriesMax() {
      return chart.getSeries().stream().anyMatch(s -> s == seriesMax);
   }

   private void stopAnimation(DwTextButton stopBtn) {
      if (null == update)
         return;

      if (!animationStopped) {
         update.cancel();
         stopBtn.setText(SystemConsoleMessages.INSTANCE.start());
         stopBtn.setIcon(BaseIcon.PLAY);
      }

      animationStopped = true;
   }

   private void startAnimation(DwTextButton stopBtn) {
      if (null == update)
         return;

      if (animationStopped) {
         update.scheduleRepeating(unitInterval);
         stopBtn.setText(SystemConsoleMessages.INSTANCE.stop());
         stopBtn.setIcon(BaseIcon.STOP);
      }

      animationStopped = false;
   }

}