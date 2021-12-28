package net.datenwerke.rs.adminutils.client.systemconsole.connpool.ui;

import java.util.List;
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
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;
import com.sencha.gxt.widget.core.client.info.Info;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCAllowBlank;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCBoolean;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCCustomComponentImpl;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.lists.SFFCSimpleDynamicList;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.dummy.CustomComponent;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.adminutils.client.systemconsole.connpool.ConnectionPoolConsoleDao;
import net.datenwerke.rs.adminutils.client.systemconsole.connpool.dto.ConnectionPoolDatasourceDto;
import net.datenwerke.rs.adminutils.client.systemconsole.connpool.dto.ConnectionPoolDatasourceDtoPA;
import net.datenwerke.rs.adminutils.client.systemconsole.connpool.dto.ConnectionPoolInfoDto;
import net.datenwerke.rs.adminutils.client.systemconsole.connpool.dto.ConnectionPoolInfoDtoPA;
import net.datenwerke.rs.adminutils.client.systemconsole.locale.SystemConsoleMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
@Singleton
public class ConnectionPoolInfoPanel extends DwContentPanel {
   private final ConnectionPoolConsoleDao connPoolConsoleDao;

   private VerticalLayoutContainer wrapper;

   private Timer update;

   private int maxNumberOfUnits = 60;
   private boolean animationStopped = false;
   private boolean showMaxPoolSize = true;
   private String showMaxKey;
   private String unitIntervalKey;
   private String numberOfUnitsKey;
   private double maxPoolSize;

   // we call the rpc refresh every second by default
   private int unitInterval = 1000;

   private final Chart<ConnectionPoolInfoDto> chart = new Chart<>();
   private final NumericAxis<ConnectionPoolInfoDto> axis = new NumericAxis<>();
   private LineSeries<ConnectionPoolInfoDto> seriesMax = new LineSeries<>();

   private HorizontalLayoutContainer buttonsLayout = new HorizontalLayoutContainer();

   private VerticalLayoutContainer layout = new VerticalLayoutContainer();
   private final ConnectionPoolInfoDtoPA props = ConnectionPoolInfoDtoPA.INSTANCE;
   private final ListStore<ConnectionPoolInfoDto> store = new ListStore<>(props.key());

   private SimpleForm chartAdditionalInfoForm;
   private String threadsAwaitingConnectionKey;
   private String orphanedConnectionsKey;

   private SimpleForm configurationForm;
   private final ConnectionPoolDatasourceDtoPA datasourcesProps = ConnectionPoolDatasourceDtoPA.INSTANCE;
   private final ListStore<ConnectionPoolDatasourceDto> datasourcesStore = new ListStore<>(datasourcesProps.key());
   private String datasourcesKey;

   @Inject
   public ConnectionPoolInfoPanel(ConnectionPoolConsoleDao connPoolConsoleDao) {

      this.connPoolConsoleDao = connPoolConsoleDao;

      /* initialize ui */
      initializeUI();
   }

   private void initializeUI() {
      setHeading(SystemConsoleMessages.INSTANCE.connectionPool());
      addStyleName("rs-memoryconsole");

      wrapper = new VerticalLayoutContainer();
      wrapper.setScrollMode(ScrollMode.AUTOY);
      add(wrapper);

      createChart();
      createConfigurationForm();
   }

   private void createChart() {
      TextSprite title = new TextSprite(SystemConsoleMessages.INSTANCE.connections());
      title.setFontSize(18);

      axis.setPosition(Position.LEFT);
      axis.addField(props.busyConnections());
      axis.setTitleConfig(title);
      axis.setMinorTickSteps(1);
      axis.setDisplayGrid(true);
      axis.setMinimum(0);

      title = new TextSprite(BaseMessages.INSTANCE.time() + " (s)");
      title.setFontSize(18);

      CategoryAxis<ConnectionPoolInfoDto, Long> categoriesAxis = new CategoryAxis<>();
      categoriesAxis.setPosition(Position.BOTTOM);
      categoriesAxis.setField(props.timestamp());
      categoriesAxis.setTitleConfig(title);
      categoriesAxis.setLabelProvider(item -> "");

      Sprite marker = Primitives.diamond(0, 0, 2);
      marker.setFill(new RGB(32, 68, 186));

      final LineSeries<ConnectionPoolInfoDto> seriesBusyConns = new LineSeries<>();
      seriesBusyConns.setYAxisPosition(Position.LEFT);
      seriesBusyConns.setLegendTitle(SystemConsoleMessages.INSTANCE.busyConnections());
      seriesBusyConns.setYField(props.busyConnections());
      seriesBusyConns.setStroke(new RGB(0, 23, 165));
      seriesBusyConns.setShowMarkers(true);
      seriesBusyConns.setSmooth(false);
      seriesBusyConns.setFill(new RGB(0, 23, 165));
      seriesBusyConns.setMarkerConfig(marker);
      seriesBusyConns.setHighlighting(true);

      final LineSeries<ConnectionPoolInfoDto> seriesNumberConns = new LineSeries<>();
      seriesNumberConns.setYAxisPosition(Position.LEFT);
      seriesNumberConns.setLegendTitle(SystemConsoleMessages.INSTANCE.numberOfConnections());
      seriesNumberConns.setYField(props.noOfConnections());
      seriesNumberConns.setStroke(new RGB(23, 168, 35));
      seriesNumberConns.setShowMarkers(true);
      seriesNumberConns.setSmooth(false);
      seriesNumberConns.setFill(new RGB(23, 168, 35));
      seriesNumberConns.setMarkerConfig(marker);
      seriesNumberConns.setHighlighting(true);

      seriesMax.setYAxisPosition(Position.LEFT);
      seriesMax.setLegendTitle(SystemConsoleMessages.INSTANCE.maximumPoolSize());
      seriesMax.setYField(props.maxPoolSize());
      seriesMax.setStroke(new RGB(194, 0, 36));
      seriesMax.setShowMarkers(true);
      seriesMax.setSmooth(false);
      seriesMax.setMarkerConfig(marker);
      seriesMax.setHighlighting(true);

      final Legend<ConnectionPoolInfoDto> legend = new Legend<>();
      legend.setItemHighlighting(true);
      legend.setItemHiding(true);
      legend.getBorderConfig().setStrokeWidth(0);

      chart.setStore(store);
      chart.setShadowChart(false);
      chart.addAxis(axis);
      chart.addAxis(categoriesAxis);
      chart.addSeries(seriesNumberConns);
      chart.addSeries(seriesBusyConns);
      chart.addSeries(seriesMax);
      chart.setLegend(legend);
      chart.setDefaultInsets(30);

      createAdditionalInfoForm();

      update = new Timer() {
         @Override
         public void run() {
            ConnectionPoolDatasourceDto selectedDatasource = ((SimpleComboBox<ConnectionPoolDatasourceDto>) configurationForm
                  .getField(datasourcesKey)).getCurrentValue();
            if (null != selectedDatasource) {
               connPoolConsoleDao.getDatasourceById(selectedDatasource.getDsId(),
                     new RsAsyncCallback<ConnectionPoolInfoDto>() {
                        @Override
                        public void onSuccess(ConnectionPoolInfoDto result) {
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
         }
      };

      wrapper.add(chart, new VerticalLayoutData(1000, 550, new Margins(10)));
      wrapper.add(chartAdditionalInfoForm, new VerticalLayoutData(1000, 20, new Margins(10)));
      update.scheduleRepeating(unitInterval);
   }

   private void createAdditionalInfoForm() {
      chartAdditionalInfoForm = SimpleForm.getNewInstance();
      chartAdditionalInfoForm.setHeaderVisible(false);
      chartAdditionalInfoForm.setLightDarkStyle();
      chartAdditionalInfoForm.setLabelAlign(LabelAlign.LEFT);

      chartAdditionalInfoForm.setLabelWidth(250);
      chartAdditionalInfoForm.beginFloatRow();

      threadsAwaitingConnectionKey = chartAdditionalInfoForm.addField(Integer.class,
            SystemConsoleMessages.INSTANCE.threadsAwaitingCheckout());
      orphanedConnectionsKey = chartAdditionalInfoForm.addField(Integer.class,
            SystemConsoleMessages.INSTANCE.unclosedOrphanedConnections());
      chartAdditionalInfoForm.endRow();
      chartAdditionalInfoForm.loadFields();

      chartAdditionalInfoForm.clearButtonBar();

      NumberField<Integer> threadsAwaitingConnectionField = (NumberField<Integer>) chartAdditionalInfoForm
            .getField(threadsAwaitingConnectionKey);
      threadsAwaitingConnectionField.setReadOnly(true);
      NumberField<Integer> orphanedConnectionsField = (NumberField<Integer>) chartAdditionalInfoForm
            .getField(orphanedConnectionsKey);
      orphanedConnectionsField.setReadOnly(true);
   }

   private void createConfigurationForm() {
      wrapper.add(layout);
      wrapper.add(buttonsLayout);

      DwTextButton playStopBtn = new DwTextButton(SystemConsoleMessages.INSTANCE.stop(), BaseIcon.STOP);
      playStopBtn.addSelectHandler(event -> {
         Info.display(BaseMessages.INSTANCE.changesApplied(), BaseMessages.INSTANCE.changesApplied());
         if (!animationStopped)
            stopAnimation(playStopBtn);
         else
            startAnimation(playStopBtn);
      });

      configurationForm = SimpleForm.getNewInstance();
      configurationForm.setHeading(BaseMessages.INSTANCE.configuration());

      configurationForm.beginFloatRow();
      configurationForm.setFieldWidth(600);

      datasourcesKey = configurationForm.addField(List.class, new SFFCSimpleDynamicList<ConnectionPoolDatasourceDto>() {

         @Override
         public Boolean isMultiselect() {
            return false;
         }

         @Override
         public ListStore<ConnectionPoolDatasourceDto> getStore() {
            return datasourcesStore;
         }

         @Override
         public ValueProvider<?, String> getStoreKeyForDisplay() {
            return new ValueProvider<ConnectionPoolDatasourceDto, String>() {

               @Override
               public String getValue(ConnectionPoolDatasourceDto object) {
                  return object.getDsName() + " (" + object.getDsId() + ")";
               }

               @Override
               public void setValue(ConnectionPoolDatasourceDto object, String value) {
               }

               @Override
               public String getPath() {
                  return "datasource";
               }
            };
         }

      });

      configurationForm.setFieldWidth(150);

      DwTextButton refreshDatasourcesBtn = new DwTextButton(SystemConsoleMessages.INSTANCE.refreshDatasources(),
            BaseIcon.REFRESH);
      configurationForm.addField(CustomComponent.class, new SFFCCustomComponentImpl(refreshDatasourcesBtn));
      refreshDatasourcesBtn.addSelectHandler(
            event -> connPoolConsoleDao.loadDatasources(new RsAsyncCallback<List<ConnectionPoolDatasourceDto>>() {
               public void onSuccess(List<ConnectionPoolDatasourceDto> result) {
                  updateDatasourcesCombobox(result);
                  Info.display(BaseMessages.INSTANCE.changesApplied(), BaseMessages.INSTANCE.changesApplied());
               };
            }));

      configurationForm.setFieldWidth(200);
      configurationForm.beginFloatRow();

      numberOfUnitsKey = configurationForm.addField(Integer.class, SystemConsoleMessages.INSTANCE.numberUnits(),
            new SFFCAllowBlank() {
               @Override
               public boolean allowBlank() {
                  return false;
               }
            });
      configurationForm.setValue(numberOfUnitsKey, 60);

      unitIntervalKey = configurationForm.addField(Integer.class, SystemConsoleMessages.INSTANCE.unitInterval(),
            new SFFCAllowBlank() {
               @Override
               public boolean allowBlank() {
                  return false;
               }
            });
      configurationForm.setValue(unitIntervalKey, 1);

      showMaxKey = configurationForm.addField(Boolean.class, "", new SFFCBoolean() {
         @Override
         public String getBoxLabel() {
            return SystemConsoleMessages.INSTANCE.showMax();
         }
      });
      configurationForm.setValue(showMaxKey, true);

      configurationForm.setValidateOnSubmit(true);
      configurationForm.addSubmissionListener(event -> {
         Info.display(BaseMessages.INSTANCE.changesApplied(), BaseMessages.INSTANCE.changesApplied());
         maxNumberOfUnits = (Integer) (configurationForm.getValue(numberOfUnitsKey));
         unitInterval = (Integer) (configurationForm.getValue(unitIntervalKey)) * 1000;
         if (!animationStopped) {
            update.cancel();
            update.scheduleRepeating(unitInterval);
         }
         if (showMaxPoolSize) {
            axis.setMaximum(maxPoolSize);
            showMaxPoolSize = (boolean) configurationForm.getValue(showMaxKey);
         } else {
            axis.setMaximum(Double.NaN);
            showMaxPoolSize = (boolean) configurationForm.getValue(showMaxKey);
         }
      });

      configurationForm.loadFields();

      configurationForm.clearButtonBar();

      configurationForm.addButton(playStopBtn);
      configurationForm.addSubmitButton();

      connPoolConsoleDao.loadDatasources(new RsAsyncCallback<List<ConnectionPoolDatasourceDto>>() {
         public void onSuccess(List<ConnectionPoolDatasourceDto> result) {
            updateDatasourcesCombobox(result);
         };
      });

      buttonsLayout.add(configurationForm, new HorizontalLayoutData(1000, 20, new Margins(10)));

      // switch datasources
      ((SimpleComboBox<ConnectionPoolDatasourceDto>) configurationForm.getField(datasourcesKey))
            .addSelectionHandler(event -> store.clear());
   }

   private void updateDatasourcesCombobox(List<ConnectionPoolDatasourceDto> datasources) {
      datasourcesStore.replaceAll(datasources);
   }

   private void updateChart(final ConnectionPoolInfoDto connectionPoolInfo) {
      chartAdditionalInfoForm.setValue(threadsAwaitingConnectionKey, connectionPoolInfo.getThreadsAwaitingConnection());
      chartAdditionalInfoForm.setValue(orphanedConnectionsKey, connectionPoolInfo.getUnclosedOrphanedConnections());

      store.add(connectionPoolInfo);

      maxPoolSize = connectionPoolInfo.getMaxPoolSize();

      if (!showMaxPoolSize) {
         axis.setMaximum(connectionPoolInfo.getNoOfConnections() + 10);
         if (containsSeriesMax())
            chart.removeSeries(seriesMax);
      } else {
         axis.setMaximum(maxPoolSize + 5);
         if (!containsSeriesMax())
            chart.addSeries(seriesMax);
      }

      int numberOfUnitsToDelete = store.size() - maxNumberOfUnits;
      if (numberOfUnitsToDelete > 0)
         IntStream.range(0, numberOfUnitsToDelete).forEach(store::remove);

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
