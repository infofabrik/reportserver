package net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.ToggleGroup;
import com.sencha.gxt.data.shared.StringLabelProvider;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.CardLayoutContainer;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer.HBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.DateTimePropertyEditor;
import com.sencha.gxt.widget.core.client.form.Field;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor;
import com.sencha.gxt.widget.core.client.form.Radio;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.TimeField;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.form.DwNumberField;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwCardContainer;
import net.datenwerke.gxtdto.client.forms.wizard.Validatable;
import net.datenwerke.gxtdto.client.forms.wizard.WizardResizer;
import net.datenwerke.rs.core.client.i18tools.FormatUiHelper;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.client.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.utils.misc.Nullable;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.DailyConfigDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.DailyPatternDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.DailyRepeatTypeDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.DateTriggerConfigDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.DaysDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.EndTypesDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthlyNthDayConfigDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthlyNthDayOfWeekConfigDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthsDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.NthDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.TimeDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.TimeUnitsDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.WeeklyConfigDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.YearlyAtDateConfigDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.YearlyNthDayOfWeekConfigDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.decorator.TimeDtoDec;

public class SeriesConfigurationForm extends DwContentPanel implements Validatable, WizardResizer {

   private static final String ASSOCIATED_PAGE = "associatedPage";

   public enum SeriesPattern {
      DAILY, WEEKLY, MONTHLY, YEARLY
   }

   public enum SeriesSubPattern {
      DAILY_EVERY_Nth_DAY, DAILY_WORKDAY, MONTHLY_Nth_DAY_Mth_MONTH, MONTHLY_Nth_DAY_OF_WEEK_Mth_MONTH, NOT_SPECIFIED,
      YEARLY_AT_DATE, YEARLY_Nth_DAY_OF_WEEK_IN_MONTH
   }

   private final static String ENUM_VALUE = "enum_value"; //$NON-NLS-1$

   private static final long MILLISECONDS_IN_HOUR = 1000 * 60 * 60;

   private DwCardContainer repeatConfigPanel;

   private VerticalLayoutContainer dailyPanel;
   private VerticalLayoutContainer monthlyPanel;
   private VerticalLayoutContainer weeklyPanel;
   private VerticalLayoutContainer yearlyPanel;

   private NumberField dailyN;

   private ToggleGroup endType;

   private DateField firstExecution;

   private DateField lastExecution;

   private NumberField monthlyM;

   private NumberField monthlyN;

   private SimpleComboBox<NthDto> monthlyNth;

   private NumberField monthlyO;

   private SimpleComboBox<DaysDto> monthlyDay;

   private NumberField numberOfExecutions;

   private ToggleGroup seriesType;

   private HashMap<SeriesPattern, ToggleGroup> seriesSupType = new HashMap<SeriesPattern, ToggleGroup>();

   private NumberField weeklyN;

//	private ToggleGroup weeklyDays;
   private ArrayList<HasValue<Boolean>> weeklyDays;

   private NumberField yearlyN;

   private SimpleComboBox<MonthsDto> yearlyMonth;

   private SimpleComboBox<NthDto> yearlyNth;

   private SimpleComboBox<DaysDto> yearlyDay;

   private SimpleComboBox<MonthsDto> yearlyMonth2;

   private TimeField atTime;

   private TimeField timeRangeStart;

   private TimeField timeRangeEnd;

   private NumberField timeRangeInterval;

   private SimpleComboBox<TimeUnitsDto> timeRangeUnit;

   private ToggleGroup dailyRepeatType;

   private List<Object> allFields = new ArrayList<Object>();

   final private Date now = new Date();

   private FormatUiHelper formatUiHelper;

   @Inject
   public SeriesConfigurationForm(
         FormatUiHelper formatUiHelper,
         @Nullable @Assisted ReportScheduleDefinition definition
         ) {
      this.formatUiHelper = formatUiHelper;

      setHeaderVisible(false);
      addClassName("rs-scheduler-series");

      repeatConfigPanel = new DwCardContainer();

      VerticalLayoutContainer wrapper = new VerticalLayoutContainer();
      setWidget(wrapper);

      wrapper.add(createTimeFieldSet(definition), new VerticalLayoutData(1, -1, new Margins(10, 10, 0, 10)));
      wrapper.add(createPatternFieldSet(definition), new VerticalLayoutData(1, -1, new Margins(10, 10, 0, 10)));
      wrapper.add(createEndFieldSet(definition), new VerticalLayoutData(1, -1, new Margins(10)));

      allFields.addAll(Arrays.asList(new Object[] { dailyN, endType, firstExecution, lastExecution, monthlyM, monthlyN,
            monthlyNth, monthlyO, monthlyDay, numberOfExecutions, seriesType, weeklyN, weeklyDays, yearlyN, yearlyMonth,
            yearlyNth, yearlyDay, yearlyMonth2, atTime, timeRangeStart, timeRangeEnd, timeRangeInterval, timeRangeUnit,
            dailyRepeatType }));

      /* disallow blank as a valid value for all fields */
      allFields
         .stream()
         .filter(f -> f instanceof TextField)
         .forEach(f -> ((TextField) f).setAllowBlank(false));

      attachFieldConfigurator();

      if (null != definition)
         configureFromDefinition(definition.getSchedulerConfig());
   }

   /**
    * Disables or enables fields reflecting the radiogroup configuration
    */
   private void enabledFields() {
      enableFields(false, allFields);

      if (getDailyRepeatType().equals(DailyRepeatTypeDto.ONCE))
         enableFields(true, timeRangeStart, timeRangeEnd, timeRangeInterval, timeRangeUnit);
      else
         enableFields(true, atTime);

      switch (getSeriesType()) {
      case DAILY:
         if (getSeriesSubType().equals(SeriesSubPattern.DAILY_WORKDAY))
            enableFields(true, dailyN);

         enableFields(true, weeklyN);
         enableFields(true, monthlyM, monthlyN);
         enableFields(true, monthlyNth, monthlyDay, monthlyO);
         enableFields(true, yearlyN, yearlyMonth2);
         enableFields(true, yearlyNth, yearlyDay, yearlyMonth);
         break;

      case WEEKLY:
         enableFields(true, dailyN);
         enableFields(true, monthlyM, monthlyN);
         enableFields(true, monthlyNth, monthlyDay, monthlyO);
         enableFields(true, yearlyN, yearlyMonth2);
         enableFields(true, yearlyNth, yearlyDay, yearlyMonth);
         break;

      case MONTHLY:
         enableFields(true, dailyN);
         enableFields(true, weeklyN);

         if (getSeriesSubType().equals(SeriesSubPattern.MONTHLY_Nth_DAY_Mth_MONTH))
            enableFields(true, monthlyNth, monthlyDay, monthlyO);
         else
            enableFields(true, monthlyM, monthlyN);

         enableFields(true, yearlyN, yearlyMonth2);
         enableFields(true, yearlyNth, yearlyDay, yearlyMonth);
         break;

      case YEARLY:
         enableFields(true, dailyN);
         enableFields(true, weeklyN);
         enableFields(true, monthlyM, monthlyN);
         enableFields(true, monthlyNth, monthlyDay, monthlyO);

         if (getSeriesSubType().equals(SeriesSubPattern.YEARLY_AT_DATE))
            enableFields(true, yearlyNth, yearlyDay, yearlyMonth2);
         else
            enableFields(true, yearlyN, yearlyMonth);

         break;
      }

      switch (getEndType()) {
      case COUNT:
         enableFields(true, lastExecution);
         break;
      case DATE:
         enableFields(true, numberOfExecutions);
         break;
      case INFINITE:
         enableFields(true, lastExecution, numberOfExecutions);
         break;
      }

   }

   private void enableFields(boolean allow, Object... fields) {
      enableFields(allow, Arrays.asList(fields));
   }

   private void enableFields(boolean allow, List<Object> fields) {
      for (Object f : fields) {
         if (f instanceof Component)
            ((Component) f).setEnabled(!allow);
         if (f instanceof ToggleGroup)
            for (HasValue<Boolean> b : (ToggleGroup) f)
               ((Field) b).setEnabled(!allow);
      }
   }

   /**
    * attaches a listener to all RadioGroups which enables field according to the
    * radio.config
    */
   private void attachFieldConfigurator() {

      enabledFields();

      ValueChangeHandler<HasValue<Boolean>> handler = new ValueChangeHandler<HasValue<Boolean>>() {
         public void onValueChange(ValueChangeEvent<HasValue<Boolean>> event) {
            /*
             * In gxt change events on radiogroups are just the union of their childrens
             * change events, so it is possible to receive a change event from a radio group
             * currently in an invalid state. ignore these
             */

            if (null == getDailyRepeatType() || null == getSeriesType() || null == getSeriesSubType()
                  || null == getEndType())
               return;

            enabledFields();
         }
      };

      dailyRepeatType.addValueChangeHandler(handler);
      seriesType.addValueChangeHandler(handler);
      ;
      seriesSupType.get(SeriesPattern.DAILY).addValueChangeHandler(handler);
      seriesSupType.get(SeriesPattern.MONTHLY).addValueChangeHandler(handler);
      seriesSupType.get(SeriesPattern.YEARLY).addValueChangeHandler(handler);
      endType.addValueChangeHandler(handler);
   }

   private Widget createTimeFieldSet(ReportScheduleDefinition definition) {
      /* 15:30 */
      atTime = new TimeField();
      atTime.setWidth(90);
      atTime.setFormat(formatUiHelper.getShortTimeFormat()); // $NON-NLS-1$
      atTime.setTriggerAction(TriggerAction.ALL);
      atTime.setValue(new Date(), true);

      /* Between 16:30 and 17:45 all 15 Minuten */
      timeRangeStart = new TimeField();
      timeRangeStart.setWidth(90);
      timeRangeStart.setFormat(formatUiHelper.getShortTimeFormat()); // $NON-NLS-1$
      timeRangeStart.setTriggerAction(TriggerAction.ALL);
      timeRangeStart.setValue(new Date(), true);

      timeRangeEnd = new TimeField();
      timeRangeEnd.setWidth(90);
      timeRangeEnd.setFormat(formatUiHelper.getShortTimeFormat()); // $NON-NLS-1$
      timeRangeEnd.setTriggerAction(TriggerAction.ALL);
      timeRangeEnd.setValue(new Date(new Date().getTime() + MILLISECONDS_IN_HOUR), true);

      timeRangeInterval = new DwNumberField(new NumberPropertyEditor.IntegerPropertyEditor());
      timeRangeInterval.addStyleName("rs-scheduler-dialog-f-nf");
      timeRangeInterval.setWidth(25);
      timeRangeInterval.getCell().setWidth(27);
      timeRangeInterval.setValue(2);

      timeRangeUnit = new SimpleComboBox<TimeUnitsDto>(new StringLabelProvider<TimeUnitsDto>());
      timeRangeUnit.add(Arrays.asList(TimeUnitsDto.values()));
      timeRangeUnit.setWidth(120);
      timeRangeUnit.setAllowBlank(false);
      timeRangeUnit.setEditable(false);
      timeRangeUnit.setForceSelection(true);
      timeRangeUnit.setTriggerAction(TriggerAction.ALL);
      timeRangeUnit.setValue(timeRangeUnit.getStore().get(0), true);

      dailyRepeatType = new ToggleGroup();

      Radio once = new Radio();
      once.setBoxLabel(SchedulerMessages.INSTANCE.at()); // $NON-NLS-1$
      once.setData(ENUM_VALUE, DailyRepeatTypeDto.ONCE);

      Radio interval = new Radio();
      interval.setBoxLabel(SchedulerMessages.INSTANCE.between()); // $NON-NLS-1$
      interval.setData(ENUM_VALUE, DailyRepeatTypeDto.BOUNDED_INTERVAL);

      dailyRepeatType.add(once);
      dailyRepeatType.add(interval);

      /*
       * set default value for radio group - must be done after adding it to
       * togglegroup
       */
      once.setValue(true, true);

      DwContentPanel fieldSet = new DwContentPanel();
      fieldSet.setLightDarkStyle();
      fieldSet.setHeading(SchedulerMessages.INSTANCE.time()); // $NON-NLS-1$

      HBoxLayoutContainer cont = new HBoxLayoutContainer();
      cont.setHBoxLayoutAlign(HBoxLayoutAlign.TOP);
      cont.setPack(BoxLayoutPack.START);
      cont.setAdjustForFlexRemainder(true);
      cont.setHeight(55);
      fieldSet.setWidget(cont);

      FlowLayoutContainer col1 = new FlowLayoutContainer();
      col1.add(once, new VerticalLayoutData(-1, -1, new Margins(3, 0, 10, 0)));
      col1.add(interval, new VerticalLayoutData(-1, -1));

      HorizontalLayoutContainer range = new HorizontalLayoutContainer();
      range.add(timeRangeStart);
      Label andLabel = createLabel(SchedulerMessages.INSTANCE.and());
      andLabel.addStyleName("rs-scheduler-dialog-f-pl5 rs-scheduler-dialog-f-pr5");
      range.add(andLabel);
      range.add(timeRangeEnd);
      Label allLabel = createLabel(SchedulerMessages.INSTANCE.all());
      allLabel.addStyleName("rs-scheduler-dialog-f-pl5 rs-scheduler-dialog-f-pr5");
      range.add(allLabel);
      range.add(timeRangeInterval);
      range.add(timeRangeUnit);

      VerticalLayoutContainer col2 = new VerticalLayoutContainer();
      col2.add(atTime, new VerticalLayoutData(-1, -1, new Margins(0, 0, 5, 0)));
      col2.add(range, new VerticalLayoutData(1, -1));
      col2.setHeight(55);

      BoxLayoutData flexData = new BoxLayoutData();
      flexData.setFlex(1);

      col1.setHeight(55);
      col2.setHeight(55);
      cont.add(col1, new BoxLayoutData(new Margins(0, 5, 0, 0)));
      cont.add(col2, flexData);

      return fieldSet;
   }

   private Widget createDailyPanel() {
      if (null == dailyPanel) {
         dailyPanel = new VerticalLayoutContainer();

         Radio ndays = new Radio();
         ndays.setData(ENUM_VALUE, SeriesSubPattern.DAILY_EVERY_Nth_DAY);

         Label txtBefore = createLabel(SchedulerMessages.INSTANCE.All());
         txtBefore.addStyleName("rs-scheduler-dialog-f-pr5");
         Label txtAfter = createLabel(SchedulerMessages.INSTANCE.days());
         txtAfter.addStyleName("rs-scheduler-dialog-f-pl5");

         dailyN = new DwNumberField(new NumberPropertyEditor.IntegerPropertyEditor());
         dailyN.addStyleName("rs-scheduler-dialog-f-nf");
         dailyN.setValue(1, true);
         dailyN.getCell().setWidth(27);
         dailyN.setWidth(25);

         Radio workingDays = new Radio();
         workingDays.setData(ENUM_VALUE, SeriesSubPattern.DAILY_WORKDAY);
         Label txtWorkingDays = createLabel(
               SchedulerMessages.INSTANCE.every() + " " + SchedulerMessages.INSTANCE.workingday()); //$NON-NLS-1$ //$NON-NLS-2$
                                                                                                    // //$NON-NLS-3$

         ToggleGroup dailySeriesSubType = new ToggleGroup();
         seriesSupType.put(SeriesPattern.DAILY, dailySeriesSubType);

         dailySeriesSubType.add(ndays);
         dailySeriesSubType.add(workingDays);

         /* default */
         ndays.setValue(true, true);

         HorizontalLayoutContainer r1 = new HorizontalLayoutContainer();
         r1.add(ndays);
         r1.add(txtBefore);
         r1.add(dailyN);
         r1.add(txtAfter);

         HorizontalLayoutContainer r2 = new HorizontalLayoutContainer();
         r2.add(workingDays);
         r2.add(txtWorkingDays);

         dailyPanel.add(r1, new VerticalLayoutData(1, 30));
         dailyPanel.add(r2, new VerticalLayoutData(1, 30));

         repeatConfigPanel.add(dailyPanel);
      }
      return dailyPanel;
   }

   private Widget createWeeklyPanel() {
      if (null == weeklyPanel) {
         weeklyPanel = new VerticalLayoutContainer();

         HBoxLayoutContainer lc1 = new HBoxLayoutContainer();
         lc1.setHBoxLayoutAlign(HBoxLayoutAlign.STRETCHMAX);
         lc1.setPack(BoxLayoutPack.START);
         lc1.setHeight(35);
         Label txtBefore = createLabel(SchedulerMessages.INSTANCE.All());
         weeklyN = new DwNumberField(new NumberPropertyEditor.IntegerPropertyEditor());
         weeklyN.addStyleName("rs-scheduler-dialog-f-nf");
         weeklyN.setValue(1, true);
         weeklyN.getCell().setWidth(27);
         weeklyN.setWidth(25);
         Label txtAfter = createLabel(SchedulerMessages.INSTANCE.weeksAt());

         lc1.add(txtBefore, new BoxLayoutData(new Margins(0, 5, 0, 0)));
         lc1.add(weeklyN);
         lc1.add(txtAfter, new BoxLayoutData(new Margins(0, 0, 0, 5)));

         weeklyDays = new ArrayList<HasValue<Boolean>>();// new ToggleGroup();
         HorizontalLayoutContainer lc2 = new HorizontalLayoutContainer();
         HorizontalLayoutContainer lc3 = new HorizontalLayoutContainer();

         for (DaysDto d : DaysDto.values()) {
            if (d.ordinal() > 6)
               break;
            CheckBox cb = new CheckBox();
            weeklyDays.add(cb);

            cb.setBoxLabel(d.toString());
            cb.setData(ENUM_VALUE, d);

            if (d.ordinal() < 4)
               lc2.add(cb);
            else
               lc3.add(cb);
         }

         weeklyPanel.add(lc1, new VerticalLayoutData(1, 35));
         weeklyPanel.add(lc2, new VerticalLayoutData(1, 25));
         weeklyPanel.add(lc3, new VerticalLayoutData(1, 25));

         repeatConfigPanel.add(weeklyPanel);

      }
      return weeklyPanel;
   }

   private Widget createMonthlyPanel() {
      if (null == monthlyPanel) {
         monthlyPanel = new VerticalLayoutContainer();

         Radio nthday = new Radio();
         nthday.setData(ENUM_VALUE, SeriesSubPattern.MONTHLY_Nth_DAY_Mth_MONTH);

         HBoxLayoutContainer lc1 = new HBoxLayoutContainer();
         lc1.setHBoxLayoutAlign(HBoxLayoutAlign.STRETCHMAX);
         lc1.setPack(BoxLayoutPack.START);
         lc1.setHeight(35);

         Label txtBefore = createLabel(SchedulerMessages.INSTANCE.At());
         monthlyN = new DwNumberField(new NumberPropertyEditor.IntegerPropertyEditor());
         monthlyN.addStyleName("rs-scheduler-dialog-f-nf");
         monthlyN.setValue(1, true);
         monthlyN.getCell().setWidth(27);
         monthlyN.setWidth(25);

         Label txtBetween = createLabel(". " + SchedulerMessages.INSTANCE.dayEvery());

         monthlyM = new DwNumberField(new NumberPropertyEditor.IntegerPropertyEditor());
         monthlyM.addStyleName("rs-scheduler-dialog-f-nf");
         monthlyM.setValue(1, true);
         monthlyM.getCell().setWidth(27);
         monthlyM.setWidth(25);

         Label txtAfter = createLabel(". " + SchedulerMessages.INSTANCE.month()); //$NON-NLS-1$ //$NON-NLS-2$
         lc1.add(nthday);
         lc1.add(txtBefore, new BoxLayoutData(new Margins(0, 5, 0, 0)));
         lc1.add(monthlyN);
         lc1.add(txtBetween, new BoxLayoutData(new Margins(0, 5, 0, 5)));
         lc1.add(monthlyM);
         lc1.add(txtAfter, new BoxLayoutData(new Margins(0, 0, 0, 5)));

         Radio namedDay = new Radio();
         namedDay.setData(ENUM_VALUE, SeriesSubPattern.MONTHLY_Nth_DAY_OF_WEEK_Mth_MONTH);

         HBoxLayoutContainer lc2 = new HBoxLayoutContainer();
         lc2.setHBoxLayoutAlign(HBoxLayoutAlign.STRETCHMAX);
         lc2.setPack(BoxLayoutPack.START);
         lc2.setHeight(35);

         Label txtNdBefore = createLabel(SchedulerMessages.INSTANCE.At());

         monthlyNth = new SimpleComboBox<NthDto>(new StringLabelProvider<NthDto>());
         monthlyNth.add(Arrays.asList(NthDto.values()));
         monthlyNth.setWidth(100);
         monthlyNth.setAllowBlank(false);
         monthlyNth.setEditable(false);
         monthlyNth.setForceSelection(true);
         monthlyNth.setTriggerAction(TriggerAction.ALL);
         monthlyNth.setValue(monthlyNth.getStore().get(0), true);

         monthlyDay = new SimpleComboBox<DaysDto>(new StringLabelProvider<DaysDto>());
         monthlyDay.add(Arrays.asList(DaysDto.values()));
         monthlyDay.setWidth(100);
         monthlyDay.setAllowBlank(false);
         monthlyDay.setEditable(false);
         monthlyDay.setForceSelection(true);
         monthlyDay.setTriggerAction(TriggerAction.ALL);
         monthlyDay.setValue(monthlyDay.getStore().get(0), true);

         Label txtNdBetween = createLabel(SchedulerMessages.INSTANCE.everyOf());

         monthlyO = new DwNumberField(new NumberPropertyEditor.IntegerPropertyEditor());
         monthlyO.addStyleName("rs-scheduler-dialog-f-nf");
         monthlyO.setValue(1, true);
         monthlyO.getCell().setWidth(27);
         monthlyO.setWidth(25);

         Label txtNdAfter = createLabel(SchedulerMessages.INSTANCE.thMonth()); // $NON-NLS-1$
         lc2.add(namedDay);
         lc2.add(txtNdBefore, new BoxLayoutData(new Margins(0, 5, 0, 0)));
         lc2.add(monthlyNth);
         lc2.add(monthlyDay);
         lc2.add(txtNdBetween, new BoxLayoutData(new Margins(0, 5, 0, 0)));
         lc2.add(monthlyO);
         lc2.add(txtNdAfter, new BoxLayoutData(new Margins(0, 0, 0, 5)));

         ToggleGroup monthlySeriesSubtype = new ToggleGroup();
         monthlySeriesSubtype.add(nthday);
         monthlySeriesSubtype.add(namedDay);

         /* add */
         nthday.setValue(true, true);

         seriesSupType.put(SeriesPattern.MONTHLY, monthlySeriesSubtype);

         monthlyPanel.add(lc1, new VerticalLayoutData(1, 35));
         monthlyPanel.add(lc2, new VerticalLayoutData(1, 35));

         repeatConfigPanel.add(monthlyPanel);
      }
      return monthlyPanel;
   }

   private Widget createYearlyPanel() {
      if (null == yearlyPanel) {
         yearlyPanel = new VerticalLayoutContainer();

         Radio at = new Radio();
         at.setData(ENUM_VALUE, SeriesSubPattern.YEARLY_AT_DATE);
         at.setHeight(30);

         Label txtAtBefore = createLabel(SchedulerMessages.INSTANCE.At()); // $NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         txtAtBefore.addStyleName("rs-scheduler-dialog-f-pr5");
         yearlyN = new DwNumberField(new NumberPropertyEditor.IntegerPropertyEditor());
         yearlyN.addStyleName("rs-scheduler-dialog-f-nf");
         yearlyN.setValue(1, true);
         yearlyN.getCell().setWidth(27);
         yearlyN.setWidth(25);
         Label txtAtBetween = createLabel(". "); //$NON-NLS-1$
         txtAtBetween.addStyleName("rs-scheduler-dialog-f-pr5");
         yearlyMonth = new SimpleComboBox<MonthsDto>(new StringLabelProvider<MonthsDto>());
         yearlyMonth.add(Arrays.asList(MonthsDto.values()));
         yearlyMonth.setWidth(100);
         yearlyMonth.setAllowBlank(false);
         yearlyMonth.setEditable(false);
         yearlyMonth.setForceSelection(true);
         yearlyMonth.setTriggerAction(TriggerAction.ALL);
         yearlyMonth.setValue(yearlyMonth.getStore().get(0), true);

         HorizontalLayoutContainer lc2 = new HorizontalLayoutContainer();
         lc2.add(at);
         lc2.add(txtAtBefore);
         lc2.add(yearlyN);
         lc2.add(txtAtBetween);
         lc2.add(yearlyMonth);

         Radio every = new Radio();
         every.setData(ENUM_VALUE, SeriesSubPattern.YEARLY_Nth_DAY_OF_WEEK_IN_MONTH);

         Label txtEveryBefore = createLabel(SchedulerMessages.INSTANCE.every()); // $NON-NLS-1$ //$NON-NLS-2$
         txtEveryBefore.addStyleName("rs-scheduler-dialog-f-pr5");
         yearlyNth = new SimpleComboBox<NthDto>(new StringLabelProvider<NthDto>());
         yearlyNth.add(Arrays.asList(NthDto.values()));
         yearlyNth.setWidth(100);
         yearlyNth.setAllowBlank(false);
         yearlyNth.setEditable(false);
         yearlyNth.setForceSelection(true);
         yearlyNth.setTriggerAction(TriggerAction.ALL);
         yearlyNth.setValue(yearlyNth.getStore().get(0), true);

         yearlyDay = new SimpleComboBox<DaysDto>(new StringLabelProvider<DaysDto>());
         yearlyDay.add(Arrays.asList(DaysDto.values()));
         yearlyDay.setWidth(100);
         yearlyDay.setAllowBlank(false);
         yearlyDay.setEditable(false);
         yearlyDay.setForceSelection(true);
         yearlyDay.setTriggerAction(TriggerAction.ALL);
         yearlyDay.setValue(yearlyDay.getStore().get(0), true);

         Label txtEveryBetween = createLabel(SchedulerMessages.INSTANCE.in());
         txtEveryBetween.addStyleName("rs-scheduler-dialog-f-pl5 rs-scheduler-dialog-f-pr5");
         yearlyMonth2 = new SimpleComboBox<MonthsDto>(new StringLabelProvider<MonthsDto>());
         yearlyMonth2.add(Arrays.asList(MonthsDto.values()));
         yearlyMonth2.setWidth(100);
         yearlyMonth2.setAllowBlank(false);
         yearlyMonth2.setEditable(false);
         yearlyMonth2.setForceSelection(true);
         yearlyMonth2.setTriggerAction(TriggerAction.ALL);
         yearlyMonth2.setValue(yearlyMonth2.getStore().get(0), true);

         HorizontalLayoutContainer lc3 = new HorizontalLayoutContainer();
         lc3.add(every);
         lc3.add(txtEveryBefore);
         lc3.add(yearlyNth);
         lc3.add(yearlyDay);
         lc3.add(txtEveryBetween);
         lc3.add(yearlyMonth2);

         ToggleGroup yearlySeriesSubtype = new ToggleGroup();
         yearlySeriesSubtype.add(at);
         yearlySeriesSubtype.add(every);

         /* default */
         at.setValue(true, true);

         seriesSupType.put(SeriesPattern.YEARLY, yearlySeriesSubtype);

         yearlyPanel.add(lc2, new VerticalLayoutData(1, 30));
         yearlyPanel.add(lc3, new VerticalLayoutData(1, 30));

         repeatConfigPanel.add(yearlyPanel);
      }
      return yearlyPanel;
   }

   private Component createPatternFieldSet(ReportScheduleDefinition definition) {
      Radio daily = new Radio();
      daily.setBoxLabel(SchedulerMessages.INSTANCE.daily());
      daily.setData(ASSOCIATED_PAGE, createDailyPanel());
      daily.setData(ENUM_VALUE, SeriesPattern.DAILY);

      Radio weekly = new Radio();
      weekly.setBoxLabel(SchedulerMessages.INSTANCE.weekly());
      weekly.setData(ASSOCIATED_PAGE, createWeeklyPanel());
      weekly.setData(ENUM_VALUE, SeriesPattern.WEEKLY);

      Radio monthly = new Radio();
      monthly.setBoxLabel(SchedulerMessages.INSTANCE.monthly());
      monthly.setData(ASSOCIATED_PAGE, createMonthlyPanel());
      monthly.setData(ENUM_VALUE, SeriesPattern.MONTHLY);

      Radio yearly = new Radio();
      yearly.setBoxLabel(SchedulerMessages.INSTANCE.yearly());
      yearly.setData(ASSOCIATED_PAGE, createYearlyPanel());
      yearly.setData(ENUM_VALUE, SeriesPattern.YEARLY);

      seriesType = new ToggleGroup();

      seriesType.add(daily);
      seriesType.add(weekly);
      seriesType.add(monthly);
      seriesType.add(yearly);

      /* default */
      daily.setValue(true, true);

      seriesType.addValueChangeHandler(new ValueChangeHandler<HasValue<Boolean>>() {
         @Override
         public void onValueChange(ValueChangeEvent<HasValue<Boolean>> event) {
            Radio r = (Radio) event.getValue();

            /*
             * In gxt change events on radiogroups are just the union of their childrens
             * change events, so it is possible to receive a change event from a radio group
             * currently in an invalid state. ignore these
             */
            if (null == r) {
               return;
            }
            Object o = r.getData(ASSOCIATED_PAGE); // $NON-NLS-1$
            if (null != o && o instanceof Container) {
               ((CardLayoutContainer) repeatConfigPanel).setActiveWidget((Component) o);
            }
         }
      });

      VerticalLayoutContainer schemaRadios = new VerticalLayoutContainer();
      schemaRadios.add(daily, new VerticalLayoutData(-1, -1, new Margins(0, 0, 0, 0)));
      schemaRadios.add(weekly, new VerticalLayoutData(-1, -1, new Margins(0, 0, 0, 0)));
      schemaRadios.add(monthly, new VerticalLayoutData(-1, -1, new Margins(0, 0, 0, 0)));
      schemaRadios.add(yearly, new VerticalLayoutData(-1, -1, new Margins(0, 0, 0, 0)));
      schemaRadios.setHeight(100);

      DwContentPanel repeatSchema = new DwContentPanel();
      repeatSchema.setLightDarkStyle();
      repeatSchema.setHeading(SchedulerMessages.INSTANCE.schema()); // $NON-NLS-1$

      HBoxLayoutContainer cont = new HBoxLayoutContainer();
      cont.setAdjustForFlexRemainder(true);
      cont.setHBoxLayoutAlign(HBoxLayoutAlign.STRETCHMAX);
      cont.setPack(BoxLayoutPack.START);
      cont.setHeight(100);

      BoxLayoutData flexData = new BoxLayoutData();
      flexData.setFlex(1);

      cont.add(schemaRadios, new BoxLayoutData(new Margins(0, 10, 0, 0)));
      repeatConfigPanel.setHeight(100);
      cont.add(repeatConfigPanel, flexData);

      repeatSchema.add(cont);

      return repeatSchema;
   }

   private Component createEndFieldSet(ReportScheduleDefinition definition) {
      firstExecution = new DateField(new DateTimePropertyEditor(formatUiHelper.getShortDateFormat()));
      firstExecution.setValue(now, true);

      Radio infinite = new Radio();
      infinite.setBoxLabel(SchedulerMessages.INSTANCE.noEnd());
      infinite.setData(ENUM_VALUE, EndTypesDto.INFINITE);

      HBoxLayoutContainer nTimes = new HBoxLayoutContainer();
      nTimes.setPack(BoxLayoutPack.START);
      nTimes.setHBoxLayoutAlign(HBoxLayoutAlign.STRETCHMAX);
      nTimes.setHeight(30);

      Radio nTime = new Radio();
      nTime.setData(ENUM_VALUE, EndTypesDto.COUNT);
      nTime.setBoxLabel(SchedulerMessages.INSTANCE.endsAfter());

      nTimes.add(nTime);

      numberOfExecutions = new DwNumberField<Integer>(new NumberPropertyEditor.IntegerPropertyEditor());
      numberOfExecutions.getCell().setWidth(27);
      numberOfExecutions.setWidth(25);
      numberOfExecutions.addStyleName("rs-scheduler-dialog-f-nf");
      numberOfExecutions.setValue(1, true);

      nTimes.add(numberOfExecutions);
      Label execLabel = createLabel(SchedulerMessages.INSTANCE.dates());
      execLabel.addStyleName("rs-scheduler-dialog-f-pl5");
      nTimes.add(execLabel);

      HBoxLayoutContainer atDate = new HBoxLayoutContainer();
      atDate.setPack(BoxLayoutPack.START);
      atDate.setHBoxLayoutAlign(HBoxLayoutAlign.STRETCHMAX);
      atDate.setHeight(35);

      Radio endAtdate = new Radio();
      endAtdate.setData(ENUM_VALUE, EndTypesDto.DATE);
      endAtdate.setBoxLabel(SchedulerMessages.INSTANCE.endsAt());

      atDate.add(endAtdate);

      lastExecution = new DateField(new DateTimePropertyEditor(formatUiHelper.getShortDateFormat()));
      lastExecution.setValue(now, true);
      atDate.add(lastExecution);

      endType = new ToggleGroup();
      endType.add(infinite);
      endType.add(nTime);
      endType.add(endAtdate);

      /* default */
      nTime.setValue(true, true);

      VerticalLayoutContainer end = new VerticalLayoutContainer();
      end.add(infinite, new VerticalLayoutData(1, -1, new Margins(0, 0, 10, 0)));
      end.add(nTimes, new VerticalLayoutData(1, -1, new Margins(0, 0, 0, 0)));
      end.add(atDate, new VerticalLayoutData(1, -1));

      DwContentPanel seriesEnd = new DwContentPanel();
      seriesEnd.setLightDarkStyle();
      seriesEnd.setHeading(SchedulerMessages.INSTANCE.duration());

      HBoxLayoutContainer cont = new HBoxLayoutContainer();
      cont.setHBoxLayoutAlign(HBoxLayoutAlign.STRETCHMAX);
      cont.setPack(BoxLayoutPack.START);
      cont.setAdjustForFlexRemainder(true);
      cont.setHeight(100);

      BoxLayoutData flexData = new BoxLayoutData();
//		flexData.setFlex(1);

      HBoxLayoutContainer begin = new HBoxLayoutContainer();
      begin.setHBoxLayoutAlign(HBoxLayoutAlign.STRETCHMAX);
      begin.setPack(BoxLayoutPack.START);
//		begin.setAdjustForFlexRemainder(true);
      begin.setHeight(30);
      begin.add(createLabel(SchedulerMessages.INSTANCE.begin() + ": "), new BoxLayoutData(new Margins(0, 5, 0, 0)));
      begin.add(firstExecution, flexData);

      BoxLayoutData flexData2 = new BoxLayoutData(new Margins(0, 0, 0, 10));
//		flexData2.setFlex(2);

      begin.setHeight(100);
      cont.add(begin, flexData);
      cont.add(end, flexData2);

      seriesEnd.add(cont);

      return seriesEnd;
   }

   protected Label createLabel(String string) {
      Label l = new Label(string);
      l.addStyleName("rs-scheduler-dialog-label");
      return l;
   }

   /**
    * Returns whether all required fields have been filled with reasonable values
    * 
    */
   public boolean isValid() {
      for (Object f : allFields) {
         if (f instanceof Field) {
            Field field = (Field) f;
            if (!field.isEnabled())
               continue;

            if (!field.isValid()) {
               field.markInvalid("");
               return false;
            }

            if (null == field.getValue()) {
               field.markInvalid("");
               return false;
            }
         }
      }

      return true;
   }

   public void configureFromDefinition(DateTriggerConfigDto config) {
      /* base config */

      setEndType(config.getEndType());

      if (null != config.getFirstExecution()) {
         firstExecution.enable();
         firstExecution.setValue(config.getFirstExecution(), true);
      }

      if (null != config.getLastExecution()) {
         lastExecution.enable();
         lastExecution.setValue(config.getLastExecution(), true);
      }

      if (null != config.getNumberOfExecutions()) {
         numberOfExecutions.enable();
         numberOfExecutions.setValue(config.getNumberOfExecutions(), true);
      }

      if (null != config.getAtTime()) {
         atTime.enable();
         atTime.setValue(((TimeDtoDec) config.getAtTime()).toTime(), true);
      }

      if (null != config.getTimeRangeStart()) {
         timeRangeStart.enable();
         timeRangeStart.setValue(((TimeDtoDec) config.getTimeRangeStart()).toTime(), true);
      }

      if (null != config.getTimeRangeEnd()) {
         timeRangeEnd.enable();
         timeRangeEnd.setValue(((TimeDtoDec) config.getTimeRangeEnd()).toTime(), true);
      }

      if (null != config.getTimeRangeInterval())
         timeRangeInterval.setValue(config.getTimeRangeInterval(), true);

      if (null != config.getTimeRangeUnit())
         setTimeRangeUnit(config.getTimeRangeUnit());

      if (null != config.getDailyRepeatType())
         setDailyRepeatType(config.getDailyRepeatType());

      config.setDailyRepeatType(getDailyRepeatType());

      if (config instanceof DailyConfigDto) {
         setSeriesType(SeriesPattern.DAILY);
         switch (((DailyConfigDto) config).getPattern()) {
         case DAILY_EVERY_Nth_DAY:
            setSeriesSubType(SeriesPattern.DAILY, SeriesSubPattern.DAILY_EVERY_Nth_DAY);
            break;
         case DAILY_WORKDAY:
            setSeriesSubType(SeriesPattern.DAILY, SeriesSubPattern.DAILY_WORKDAY);
            break;
         }
         if (null != ((DailyConfigDto) config).getDailyN())
            dailyN.setValue(((DailyConfigDto) config).getDailyN(), true);

      } else if (config instanceof WeeklyConfigDto) {
         setSeriesType(SeriesPattern.WEEKLY);

         if (null != ((WeeklyConfigDto) config).getWeeklyDays())
            setWeeklyDays(((WeeklyConfigDto) config).getWeeklyDays());

         if (null != ((WeeklyConfigDto) config).getWeeklyN())
            weeklyN.setValue(((WeeklyConfigDto) config).getWeeklyN(), true);

      } else if (config instanceof MonthlyNthDayConfigDto || config instanceof MonthlyNthDayOfWeekConfigDto) {
         setSeriesType(SeriesPattern.MONTHLY);

         if (config instanceof MonthlyNthDayConfigDto) {
            setSeriesSubType(SeriesPattern.MONTHLY, SeriesSubPattern.MONTHLY_Nth_DAY_Mth_MONTH);
            if (null != ((MonthlyNthDayConfigDto) config).getMonth()) {
               monthlyM.enable();
               monthlyM.setValue(((MonthlyNthDayConfigDto) config).getMonth(), true);
            }

            if (null != ((MonthlyNthDayConfigDto) config).getDayInMonth()) {
               monthlyN.enable();
               monthlyN.setValue(((MonthlyNthDayConfigDto) config).getDayInMonth(), true);
            }
         } else {
            setSeriesSubType(SeriesPattern.MONTHLY, SeriesSubPattern.MONTHLY_Nth_DAY_OF_WEEK_Mth_MONTH);
            if (null != ((MonthlyNthDayOfWeekConfigDto) config).getMonthlyDay()) {
               monthlyDay.enable();
               setMonthlyDay(((MonthlyNthDayOfWeekConfigDto) config).getMonthlyDay());
            }

            if (null != ((MonthlyNthDayOfWeekConfigDto) config).getMonthlyNth()) {
               monthlyNth.enable();
               setMonthlyNth(((MonthlyNthDayOfWeekConfigDto) config).getMonthlyNth());
            }

            if (null != ((MonthlyNthDayOfWeekConfigDto) config).getMonth()) {
               monthlyO.enable();
               monthlyO.setValue(((MonthlyNthDayOfWeekConfigDto) config).getMonth(), true);
            }
         }

      } else if (config instanceof YearlyAtDateConfigDto || config instanceof YearlyNthDayOfWeekConfigDto) {
         setSeriesType(SeriesPattern.YEARLY);

         if (config instanceof YearlyAtDateConfigDto) {
            if (null != ((YearlyAtDateConfigDto) config).getYearlyMonth()) {
               yearlyMonth.setEnabled(true);
               setYearlyMonth(((YearlyAtDateConfigDto) config).getYearlyMonth());
            }

            if (null != ((YearlyAtDateConfigDto) config).getYearlyNDay()) {
               yearlyN.setEnabled(true);
               yearlyN.setValue(((YearlyAtDateConfigDto) config).getYearlyNDay(), true);
            }
         } else {
            if (null != ((YearlyNthDayOfWeekConfigDto) config).getYearlyDay()) {
               yearlyDay.setEnabled(true);
               setYearlyDay(((YearlyNthDayOfWeekConfigDto) config).getYearlyDay());
            }

            if (null != ((YearlyNthDayOfWeekConfigDto) config).getYearlyMonth()) {
               yearlyMonth2.setEnabled(true);
               setYearlyMonth2(((YearlyNthDayOfWeekConfigDto) config).getYearlyMonth());
            }

            if (null != ((YearlyNthDayOfWeekConfigDto) config).getYearlyNth()) {
               yearlyNth.setEnabled(true);
               setYearlyNth(((YearlyNthDayOfWeekConfigDto) config).getYearlyNth());
            }
         }
      }
   }

   public DateTriggerConfigDto createDto() {
      DateTriggerConfigDto config = null;
      switch (getSeriesType()) {
      case DAILY:
         config = new DailyConfigDto();
         switch (getSeriesSubType()) {
         case DAILY_EVERY_Nth_DAY:
            ((DailyConfigDto) config).setPattern(DailyPatternDto.DAILY_EVERY_Nth_DAY);
            break;
         case DAILY_WORKDAY:
            ((DailyConfigDto) config).setPattern(DailyPatternDto.DAILY_WORKDAY);
            break;
         default:
            throw new IllegalArgumentException();
         }

         if (dailyN.isEnabled())
            ((DailyConfigDto) config).setDailyN(getDailyN());
         break;
      case WEEKLY:
         config = new WeeklyConfigDto();

         if (((Component) weeklyDays.iterator().next()).isEnabled())
            ((WeeklyConfigDto) config).setWeeklyDays(getWeeklyDays());

         if (weeklyN.isEnabled())
            ((WeeklyConfigDto) config).setWeeklyN(getWeeklyN());

         break;
      case MONTHLY:
         switch (getSeriesSubType()) {
         case MONTHLY_Nth_DAY_Mth_MONTH:
            config = new MonthlyNthDayConfigDto();

            if (monthlyM.isEnabled())
               ((MonthlyNthDayConfigDto) config).setMonth(getMonthlyM());

            if (monthlyN.isEnabled())
               ((MonthlyNthDayConfigDto) config).setDayInMonth(getMonthlyN());

            break;
         case MONTHLY_Nth_DAY_OF_WEEK_Mth_MONTH:
            config = new MonthlyNthDayOfWeekConfigDto();

            if (monthlyDay.isEnabled())
               ((MonthlyNthDayOfWeekConfigDto) config).setMonthlyDay(getMonthlyDay());

            if (monthlyNth.isEnabled())
               ((MonthlyNthDayOfWeekConfigDto) config).setMonthlyNth(getMonthlyNth());

            if (monthlyO.isEnabled())
               ((MonthlyNthDayOfWeekConfigDto) config).setMonth(getMonthlyO());

            break;
         default:
            throw new IllegalArgumentException();
         }
         break;
      case YEARLY:
         switch (getSeriesSubType()) {
         case YEARLY_AT_DATE:
            config = new YearlyAtDateConfigDto();

            if (yearlyMonth.isEnabled())
               ((YearlyAtDateConfigDto) config).setYearlyMonth(getYearlyMonth());

            if (yearlyN.isEnabled())
               ((YearlyAtDateConfigDto) config).setYearlyNDay(getYearlyN());

            break;
         case YEARLY_Nth_DAY_OF_WEEK_IN_MONTH:
            config = new YearlyNthDayOfWeekConfigDto();

            if (yearlyDay.isEnabled())
               ((YearlyNthDayOfWeekConfigDto) config).setYearlyDay(getYearlyDay());

            if (yearlyMonth2.isEnabled())
               ((YearlyNthDayOfWeekConfigDto) config).setYearlyMonth(getYearlyMonth2());

            if (yearlyNth.isEnabled())
               ((YearlyNthDayOfWeekConfigDto) config).setYearlyNth(getYearlyNth());

            break;
         default:
            throw new IllegalArgumentException();
         }
         break;
      }

      /* base config */

      config.setEndType(getEndType());

      if (firstExecution.isEnabled())
         config.setFirstExecution(getFirstExecution());

      if (lastExecution.isEnabled())
         config.setLastExecution(getLastExecution());

      if (numberOfExecutions.isEnabled())
         config.setNumberOfExecutions(getNumberOfExecutions());

      if (atTime.isEnabled()) {
         TimeDto time = new TimeDtoDec();
         time.setHour(getAtTime().getHour());
         time.setMinutes(getAtTime().getMinutes());

         config.setAtTime(time);
      }

      if (timeRangeStart.isEnabled()) {
         TimeDto time = new TimeDtoDec();
         time.setHour(getTimeRangeStart().getHour());
         time.setMinutes(getTimeRangeStart().getMinutes());

         config.setTimeRangeStart(time);
      }

      if (timeRangeEnd.isEnabled()) {
         TimeDto time = new TimeDtoDec();
         time.setHour(getTimeRangeEnd().getHour());
         time.setMinutes(getTimeRangeEnd().getMinutes());

         config.setTimeRangeEnd(time);
      }

      if (timeRangeInterval.isEnabled())
         config.setTimeRangeInterval(getTimeRangeInterval());

      if (timeRangeUnit.isEnabled())
         config.setTimeRangeUnit(getTimeRangeUnit());

      config.setDailyRepeatType(getDailyRepeatType());

      return config;
   }

   /* getter */
   public Integer getDailyN() {
      return safeIntegerReturn(dailyN.getValue());
   }

   public void setEndType(EndTypesDto type) {
      for (HasValue<Boolean> radio : endType) {
         if (((Radio) radio).getData(ENUM_VALUE) == type) {
            ((Radio) radio).setValue(true, true);
            return;
         }
      }
   }

   public EndTypesDto getEndType() {
      Radio v = (Radio) endType.getValue();
      if (null == v)
         return null;
      return v.getData(ENUM_VALUE);
   }

   public Date getFirstExecution() {
      return firstExecution.getValue();
   }

   public Date getLastExecution() {
      return lastExecution.getValue();
   }

   public void setMonthlyDay(DaysDto day) {
      monthlyDay.setValue(day, true);
   }

   public DaysDto getMonthlyDay() {
      return monthlyDay.getValue();
   }

   public Integer getMonthlyM() {
      return safeIntegerReturn(monthlyM.getValue());
   }

   public Integer getMonthlyN() {
      return safeIntegerReturn(monthlyN.getValue());
   }

   public void setMonthlyNth(NthDto nth) {
      monthlyNth.setValue(nth, true);
   }

   public NthDto getMonthlyNth() {
      return monthlyNth.getValue();
   }

   public Integer getMonthlyO() {
      return safeIntegerReturn(monthlyO.getValue());
   }

   public Integer getNumberOfExecutions() {
      return safeIntegerReturn(numberOfExecutions.getValue());
   }

   public void setSeriesSubType(SeriesPattern sPattern, SeriesSubPattern pattern) {
      ToggleGroup r = seriesSupType.get(sPattern);
      for (HasValue<Boolean> radio : r) {
         if (((Radio) radio).getData(ENUM_VALUE) == pattern) {
            ((Radio) radio).setValue(true, true);
            break;
         }
      }
   }

   public SeriesSubPattern getSeriesSubType() {
      if (null == getSeriesType())
         return null;

      ToggleGroup r = seriesSupType.get(getSeriesType());
      if (null == r)
         return SeriesSubPattern.NOT_SPECIFIED;

      HasValue<Boolean> v = r.getValue();
      if (null == v)
         return null;

      return ((Component) v).getData(ENUM_VALUE);
   }

   public void setSeriesType(SeriesPattern pattern) {
      for (HasValue<Boolean> radio : seriesType) {
         if (((Radio) radio).getData(ENUM_VALUE) == pattern) {
            ((Radio) radio).setValue(true, true);
            break;
         }
      }
   }

   public SeriesPattern getSeriesType() {
      HasValue<Boolean> v = seriesType.getValue();
      if (null == v)
         return null;
      return ((Component) v).getData(ENUM_VALUE);
   }

   public void setWeeklyDays(Set<DaysDto> days) {
      for (HasValue<Boolean> box : weeklyDays) {
         if (days.contains(((Component) box).getData(ENUM_VALUE)))
            box.setValue(true, true);
      }
   }

   public Set<DaysDto> getWeeklyDays() {
      Set<DaysDto> res = new HashSet<DaysDto>();
      for (HasValue<Boolean> box : weeklyDays) {
         if (box.getValue())
            res.add((DaysDto) ((Component) box).getData(ENUM_VALUE));
      }
      return res;
   }

   public Integer getWeeklyN() {
      return safeIntegerReturn(weeklyN.getValue());
   }

   public void setYearlyDay(DaysDto day) {
      yearlyDay.setValue(day, true);
   }

   public DaysDto getYearlyDay() {
      return yearlyDay.getValue();
   }

   public void setYearlyMonth(MonthsDto month) {
      yearlyMonth.setValue(month, true);
   }

   public MonthsDto getYearlyMonth() {
      return yearlyMonth.getValue();
   }

   public void setYearlyMonth2(MonthsDto month) {
      yearlyMonth2.setValue(month, true);
   }

   public MonthsDto getYearlyMonth2() {
      return yearlyMonth2.getValue();
   }

   public Integer getYearlyN() {
      return safeIntegerReturn(yearlyN.getValue());
   }

   public void setYearlyNth(NthDto nth) {
      yearlyNth.setValue(nth, true);
   }

   public NthDto getYearlyNth() {
      return yearlyNth.getValue();
   }

   public TimeDto getAtTime() {
      return new TimeDtoDec(atTime.getValue());
   }

   public TimeDto getTimeRangeStart() {
      return new TimeDtoDec(timeRangeStart.getValue());
   }

   public TimeDto getTimeRangeEnd() {
      return new TimeDtoDec(timeRangeEnd.getValue());
   }

   public Integer getTimeRangeInterval() {
      return safeIntegerReturn(timeRangeInterval.getValue());
   }

   public void setTimeRangeUnit(TimeUnitsDto unit) {
      timeRangeUnit.setValue(unit, true);
   }

   public TimeUnitsDto getTimeRangeUnit() {
      return timeRangeUnit.getValue();
   }

   public void setDailyRepeatType(final DailyRepeatTypeDto type) {
      dailyRepeatType
         .stream()
         .filter(radio -> ((Radio) radio).getData(ENUM_VALUE) == type)
         .findFirst()
         .ifPresent(radio -> ((Radio) radio).setValue(true, true));
   }

   public DailyRepeatTypeDto getDailyRepeatType() {
      HasValue<Boolean> r = dailyRepeatType.getValue();
      if (null == r)
         return null;
      return ((Component) r).getData(ENUM_VALUE);
   }

   private Integer safeIntegerReturn(Object num) {
      if (num instanceof Number)
         return ((Number) num).intValue();
      return null;
   }

   @Override
   public int getPageHeight() {
      return 540;
   }

}
