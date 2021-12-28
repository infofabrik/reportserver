package net.datenwerke.gxtdto.client.forms.simpleform;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.Style.HideMode;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.dom.HasScrollSupport;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.button.ButtonBar;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer.HBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.container.HasMargins;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.Field;
import com.sencha.gxt.widget.core.client.form.FieldSet;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.FormPanel.Encoding;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.form.FormPanel.Method;
import com.sencha.gxt.widget.core.client.form.IsField;
import com.sencha.gxt.widget.core.client.menu.Menu;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwHorizontalFlowLayoutContainer;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwAlertMessageBox;
import net.datenwerke.gxtdto.client.dtomanager.HasValueProviderByPath;
import net.datenwerke.gxtdto.client.forms.layout.FormFieldLayoutConfiguration;
import net.datenwerke.gxtdto.client.forms.locale.FormsMessages;
import net.datenwerke.gxtdto.client.forms.simpleform.actions.SimpleFormAction;
import net.datenwerke.gxtdto.client.forms.simpleform.conditions.ComplexCondition;
import net.datenwerke.gxtdto.client.forms.simpleform.conditions.SimpleFormCondition;
import net.datenwerke.gxtdto.client.forms.simpleform.conditions.registrar.ComplexConditionRegistrar;
import net.datenwerke.gxtdto.client.forms.simpleform.conditions.registrar.ConditionRegistrar;
import net.datenwerke.gxtdto.client.forms.simpleform.conditions.registrar.SimpleConditionRegistrar;
import net.datenwerke.gxtdto.client.forms.simpleform.decorators.field.ContextMenuDecorator;
import net.datenwerke.gxtdto.client.forms.simpleform.decorators.field.FieldInfoDecorator;
import net.datenwerke.gxtdto.client.forms.simpleform.decorators.field.FieldInfoDecorator.DelayedInfoMessage;
import net.datenwerke.gxtdto.client.forms.simpleform.decorators.field.LabelDecorator;
import net.datenwerke.gxtdto.client.forms.simpleform.decorators.field.SimpleFormFieldDecorator;
import net.datenwerke.gxtdto.client.forms.simpleform.dependency.DependencyRegistrar;
import net.datenwerke.gxtdto.client.forms.simpleform.dependency.SimpleDependencyRegistrar;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHook;
import net.datenwerke.gxtdto.client.forms.simpleform.json.SimpleFormFieldJson;
import net.datenwerke.gxtdto.client.forms.simpleform.json.SimpleFormFieldsJson;
import net.datenwerke.gxtdto.client.forms.simpleform.json.SimpleFormJsonConfig;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.theme.CssClassConstant;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwButtonBar;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class SimpleForm extends DwContentPanel {

   @CssClassConstant
   public static final String CSS_NAME = "rs-sf";

   @Inject
   protected static HookHandlerService hookService;

   enum LAYOUT_MARKER {
      BEGIN_ROW, BEGIN_FLOAT_ROW, BEGIN_COLUMN, BEGIN_FS
   }

   public class SField {
      private Widget widget;
      private Widget displayField;
      private Class<?> type;
      private SimpleFormFieldConfiguration[] configs;
      private FormFieldProviderHook responsibleHook;
      private ValueProvider valueProvider;
      private Object preLoadValue;
      private Container container;

      private final String name;
      private FormFieldLayoutConfiguration fieldLayoutConfig;

      public SField(String name) {
         this.name = name;
      }

      public Widget getWidget() {
         return widget;
      }

      public void setWidget(Widget widget) {
         this.widget = widget;
      }

      public Widget getDisplayField() {
         return displayField;
      }

      public void setDisplayField(Widget displayField) {
         this.displayField = displayField;
      }

      public Class<?> getType() {
         return type;
      }

      public void setType(Class<?> type) {
         this.type = type;
      }

      public SimpleFormFieldConfiguration[] getConfigs() {
         return configs;
      }

      public void setConfigs(SimpleFormFieldConfiguration[] configs) {
         this.configs = configs;
      }

      public FormFieldProviderHook getResponsibleHook() {
         return responsibleHook;
      }

      public void setResponsibleHook(FormFieldProviderHook responsibleHook) {
         this.responsibleHook = responsibleHook;
      }

      public ValueProvider getValueProvider() {
         return valueProvider;
      }

      public void setValueProvider(ValueProvider valueProvider) {
         this.valueProvider = valueProvider;
      }

      public Object getPreLoadValue() {
         return preLoadValue;
      }

      public void setPreLoadValue(Object preLoadValue) {
         this.preLoadValue = preLoadValue;
      }

      public String getName() {
         return name;
      }

      public void setFieldLayoutConfig(FormFieldLayoutConfiguration fieldLayoutConfig) {
         this.fieldLayoutConfig = fieldLayoutConfig;
      }

      public FormFieldLayoutConfiguration getFieldLayoutConfig() {
         return fieldLayoutConfig;
      }

      public void setContainer(Container container) {
         this.container = container;
      }

      public Container getContainer() {
         return container;
      }
   }

   protected Object model;

   protected List<String> fieldKeyList = new ArrayList<>();
   protected Map<String, SField> fieldLookup = new HashMap<>();

   protected Map<String, List<LAYOUT_MARKER>> fieldBeginLayoutLookup = new HashMap<String, List<LAYOUT_MARKER>>();
   protected Map<String, List<FormFieldLayoutConfiguration>> fieldBeginFormLayoutLookup = new HashMap<String, List<FormFieldLayoutConfiguration>>();
   protected Map<String, List<MarginData>> fieldBeginLayoutMarginLookup = new HashMap<String, List<MarginData>>();
   protected Map<String, List<String>> fieldBeginFieldsetLabelLookup = new HashMap<String, List<String>>();
   protected Map<String, List<Double>> fieldBeginContainerWidthLookup = new HashMap<String, List<Double>>();
   protected Map<String, List<Double>> fieldBeginContainerHeightLookup = new HashMap<String, List<Double>>();
   protected Map<String, Integer> fieldEndLayoutLookup = new HashMap<String, Integer>();

   protected List<ConditionRegistrar> conditionRegistrars = new ArrayList<ConditionRegistrar>();
   protected List<DependencyRegistrar> dependencyRegistrars = new ArrayList<DependencyRegistrar>();

   protected List<SimpleFormSubmissionListener> submissionListeners = new ArrayList<SimpleFormSubmissionListener>();
   protected List<SimpleFormSubmissionCallback> submissionCallbacks = new ArrayList<SimpleFormSubmissionCallback>();

   protected boolean loadFieldsOnBinding = true;
   protected boolean fieldsLoaded = false;

   protected boolean isAfterInitialLayout = false;

   protected int fieldNameCounter = 0;

   protected int labelPad = 0;

   private boolean validateOnSubmit = false;

   protected List<SimpleFormFieldDecorator> fieldDecorators = new ArrayList<SimpleFormFieldDecorator>();

   protected List<LAYOUT_MARKER> beginLayout = new ArrayList<SimpleForm.LAYOUT_MARKER>();
   protected int isEndLayout;
   protected List<MarginData> beginMarginData = new ArrayList<MarginData>();
   protected List<String> beginFsLabel = new ArrayList<String>();
   protected List<Double> beginContainerWidth = new ArrayList<Double>();
   protected List<Double> beginContainerHeight = new ArrayList<Double>();

   protected Stack<Container> layoutStack;

   protected FormPanel mainForm = new FormPanel();
   protected VerticalLayoutContainer fieldWrapper = new VerticalLayoutContainer();

   private DwButtonBar buttonBar;
   private DwTextButton submitButton;

   private FormFieldLayoutConfiguration fieldLayoutConfig = new FormFieldLayoutConfiguration();

   private boolean focusOnShow = true;

   private Widget oldWidget;

   public SimpleForm() {
      super();
      setBodyBorder(false);
      setBorders(false);

      VerticalLayoutContainer vCont = new VerticalLayoutContainer();
      add(vCont);
      vCont.add(mainForm, new VerticalLayoutData(1, -1));

      mainForm.add(fieldWrapper);

      buttonBar = new DwButtonBar();
      buttonBar.setMinButtonWidth(75);
      buttonBar.setPadding(new Padding(0, 10, 0, 0));
      buttonBar.setPack(BoxLayoutPack.END);
      vCont.add(buttonBar, new VerticalLayoutData(1, -1));

      addSubmitButton();

      /* init layout stack and add layout */
      layoutStack = new Stack<Container>();

      /* add default decorators */
      addFieldDecorator(new LabelDecorator());
      addFieldDecorator(new ContextMenuDecorator());
      addFieldDecorator(new FieldInfoDecorator());

      /* init ui */
      initializeUI();
   }

   public void setAutoHeight() {
      mainForm.setLayoutData(new VerticalLayoutData(1, 1));
   }

   @Override
   public String getCssName() {
      return super.getCssName() + " " + CSS_NAME;
   }

   public static SimpleForm getNewInstance() {
      return new SimpleForm();
   }

   public static SimpleForm getInlineInstance() {
      SimpleForm form = new SimpleForm();

      form.setHeaderVisible(false);
      form.setBodyBorder(false);
      form.setBorders(false);

      form.getButtonBar().clear();

      return form;
   }

   public static SimpleForm getInlineLabelessInstance() {
      SimpleForm form = getInlineInstance();

      form.setDiscardLabels(true);

      form.getButtonBar().clear();

      return form;
   }

   public void clearButtonBar() {
      submitButton = null;
      buttonBar.clear();
   }

   public static Widget createFormlessField(Class<?> type, SimpleFormFieldConfiguration... configs) {
      FormFieldProviderHook hooker = getResponsibleHooker(type, configs);
      if (null == hooker)
         throw new IllegalArgumentException("Could not find provider for " + type);

      return hooker.createFormField();
   }

   public Container getFieldWrapper() {
      return fieldWrapper;
   }

   public void setDiscardLabels(boolean b) {
      fieldLayoutConfig.setHasLabel(!b);
   }

   public void setFieldMarginData(MarginData marginData) {
      fieldLayoutConfig.setMarginData(marginData);
   }

   /**
    * Sets the width of all form fields added after this method has been called.
    * 
    * @param width the width of all fields added after this method call
    */
   public void setFieldWidth(double width) {
      fieldLayoutConfig.setFieldWidth(width);
   }

   public void setFieldHeight(double height) {
      fieldLayoutConfig.setFieldHeight(height);
   }

   public void setLabelAlign(LabelAlign al) {
      fieldLayoutConfig.setLabelAlign(al);
   }

   public void addSubmissionCallback(SimpleFormSubmissionCallback cb) {
      submissionCallbacks.add(cb);
   }

   public List<SimpleFormSubmissionCallback> getSubmissionCallbacks() {
      return submissionCallbacks;
   }

   public SimpleFormSubmissionCallback getCompositeSubmissionCallback() {
      if (submissionCallbacks.size() == 1)
         return submissionCallbacks.get(0);

      return new ChainedCallbackWrapper(submissionCallbacks, this);
   }

   public SimpleFormFieldDecorator getFieldDecorator(String id) {
      for (SimpleFormFieldDecorator dec : fieldDecorators)
         if (id.equals(dec.getDecoratorId()))
            return dec;
      return null;
   }

   public <T extends SimpleFormFieldDecorator> T getFieldDecorator(Class<? extends T> type, String id) {
      for (SimpleFormFieldDecorator dec : fieldDecorators)
         if (id.equals(dec.getDecoratorId()))
            return (T) dec;
      return null;
   }

   public void addFieldDecorator(SimpleFormFieldDecorator fieldDecorator) {
      fieldDecorators.add(fieldDecorator);
   }

   public void setFieldDecorators(List<SimpleFormFieldDecorator> fieldDecorators) {
      this.fieldDecorators = fieldDecorators;
   }

   public List<SimpleFormFieldDecorator> getFieldDecorators() {
      return this.fieldDecorators;
   }

   public void setLabelPad(int pad) {
      this.labelPad = pad;
   }

   public void setLabelWidth(int i) {
      this.fieldLayoutConfig.setLabelWidth(i);
   }

   public FormPanel getFormPanel() {
      return mainForm;
   }

   /**
    * Called once after the form has been rendered for the first time and loads
    * dependencies.
    * 
    * <p>
    * Dependencies are loaded
    * </p>
    */
   @Override
   protected void onAfterFirstAttach() {
      super.onAfterFirstAttach();

      if (isAfterInitialLayout)
         return;

      isAfterInitialLayout = true;

      /* load conditions */
      loadConditions();

      /* do main thing */
      loadDependancies();

      /* call decorators */
      for (String fieldKey : fieldKeyList)
         for (SimpleFormFieldDecorator dec : fieldDecorators)
            dec.configureFieldAfterLayout(this, fieldLookup.get(fieldKey).getWidget(), fieldKey);

      if (focusOnShow && !fieldKeyList.isEmpty()) {
         Widget widget = fieldLookup.get(fieldKeyList.get(0)).getWidget();
         if (widget instanceof Field)
            ((Field) widget).focus();
      }
   }

   public void setFocusOnShow(boolean focusOnShow) {
      this.focusOnShow = focusOnShow;
   }

   protected void initializeUI() {
      setLabelWidth(120);
      setLabelPad(20);
   }

   protected void enableFormScrollSupport() {
      /* basic properties */
      if (fieldWrapper instanceof HasScrollSupport)
         ((HasScrollSupport) fieldWrapper).getScrollSupport().setScrollMode(ScrollMode.AUTO);
   }

   @Override
   public void addButton(Widget widget) {
      buttonBar.add(widget);
   }

   @Override
   public ButtonBar getButtonBar() {
      return buttonBar;
   }

   public DwTextButton addSubmitButton() {
      return addSubmitButton(BaseMessages.INSTANCE.apply());
   }

   public DwTextButton addSubmitButton(String txt) {
      if (null != submitButton) {
         submitButton.setText(txt);
         submitButton.setIcon(BaseIcon.CHECK);
         return submitButton;
      }

      /* add ok button */
      submitButton = new DwTextButton(txt, BaseIcon.CHECK);
      submitButton.addSelectHandler(event -> formSubmitted());

      addButton(submitButton);

      return submitButton;
   }

   public DwTextButton getSubmitButton() {
      return submitButton;
   }

   /**
    * Triggers a reload whenever the selection of dependsOn changes.
    * 
    * @param dependant
    * @param dependsOn
    */
   public void addDependency(final String dependant, String dependsOn) {
      if (isFieldsLoaded())
         throw new IllegalStateException("Fields have already been loaded"); //$NON-NLS-1$

      DependencyRegistrar registrar = new SimpleDependencyRegistrar(dependant, dependsOn, this);
      this.dependencyRegistrars.add(registrar);
   }

   /**
    * Reloads the field
    */
   public void reloadField(String fieldKey) {
      SField sfield = fieldLookup.get(fieldKey);

      if (null == sfield)
         return;

      Widget formField = sfield.getWidget();
      Widget displayedField = sfield.getDisplayField();

      if (!displayedField.isAttached())
         return;

      FormFieldProviderHook responsibleHook = sfield.getResponsibleHook();

      if (isBindingActive())
         responsibleHook.removeFieldBindings(model, formField);

      Widget newField = responsibleHook.reload(formField);

      if (null != newField) {
         if (!(displayedField.getParent() instanceof Container))
            throw new IllegalArgumentException("parent of field needs to be a container"); //$NON-NLS-1$

         /* find index */
         Container parent = (Container) displayedField.getParent();
         int index = 0;
         for (Widget child : parent) {
            if (child.equals(displayedField))
               break;
            index++;
         }

         /* get layout data */
         Object layoutData = displayedField.getLayoutData();

         /* remove from parent */
         displayedField.removeFromParent();

         /* put into lookup table */
         sfield.setWidget(newField);

         /* configure field */
         if (responsibleHook.isDecorateable())
            newField = configureField(newField, fieldKey);
         sfield.setDisplayField(newField);

         /* add new field */
         if (parent instanceof FlowLayoutContainer)
            ((FlowLayoutContainer) parent).insert(newField, index);
         else if (parent instanceof VerticalLayoutContainer)
            if (layoutData instanceof VerticalLayoutData)
               ((VerticalLayoutContainer) parent).insert(newField, index, (VerticalLayoutData) layoutData);
            else
               ((VerticalLayoutContainer) parent).insert(newField, index);
         else
            parent.add(newField);

         if (newField instanceof Component)
            ((Component) newField).setHideMode(HideMode.OFFSETS);

         /* binding */
         if (isBindingActive() && null != sfield.getValueProvider())
            responsibleHook.addFieldBindings(model, sfield.getValueProvider(), sfield.getWidget());
      }

      forceLayout();
   }

   /**
    * Adds a simple condition to the form.
    * 
    * @param fieldKey
    * @param condition
    * @param action
    */
   public void addCondition(String fieldKey, final SimpleFormCondition condition, final SimpleFormAction action) {
      if (isFieldsLoaded())
         throw new IllegalStateException("Fields have already been loaded"); //$NON-NLS-1$

      ConditionRegistrar registrar = new SimpleConditionRegistrar(fieldKey, condition, action, this);
      conditionRegistrars.add(registrar);
   }

   /**
    * Adds a complex condition to the form.
    * 
    * @param action
    * @param conditions
    */
   public void addCondition(final SimpleFormAction action, final ComplexCondition... conditions) {
      if (isFieldsLoaded())
         throw new IllegalStateException("Fields have already been loaded"); //$NON-NLS-1$

      ConditionRegistrar registrar = new ComplexConditionRegistrar(action, conditions, this);
      conditionRegistrars.add(registrar);

   }

   public <D> void addHidden(String name, D value) {
      Hidden field = new Hidden();
      field.setValue(String.valueOf(value));
      field.setName(name);
      fieldWrapper.add(field);

   }

   public void beginColumn() {
      beginColumn(1, -1, null);
   }

   public void beginColumn(double width, double height) {
      beginColumn(width, height, null);
   }

   public void beginColumn(double width, MarginData marginData) {
      beginColumn(width, -1, marginData);
   }

   public void beginColumn(MarginData marginData) {
      beginColumn(1, -1, marginData);
   }

   public void beginColumn(double width, double height, MarginData marginData) {
      beginLayout.add(LAYOUT_MARKER.BEGIN_COLUMN);
      this.beginMarginData.add(marginData);
      this.beginFsLabel.add("");
      this.beginContainerWidth.add(width);
      this.beginContainerHeight.add(height);
   }

   public void endColumn() {
      endGroup();
   }

   public void beginRow() {
      beginRow(1, -1);
   }

   public void beginRow(double width, double height) {
      beginRow(width, height, null);
   }

   public void beginRow(MarginData marginData) {
      beginRow(1, -1, marginData);
   }

   public void beginRow(double width, double height, MarginData marginData) {
      beginLayout.add(LAYOUT_MARKER.BEGIN_ROW);
      this.beginMarginData.add(marginData);
      this.beginFsLabel.add("");
      this.beginContainerWidth.add(width);
      this.beginContainerHeight.add(height);
   }

   public void beginFloatRow() {
      beginFloatRow(1, -1);
   }

   public void beginFloatRow(double width, double height) {
      beginFloatRow(width, height, null);
   }

   public void beginFloatRow(MarginData marginData) {
      beginFloatRow(1, -1, marginData);
   }

   public void beginFloatRow(double width, double height, MarginData marginData) {
      beginLayout.add(LAYOUT_MARKER.BEGIN_FLOAT_ROW);
      this.beginMarginData.add(marginData);
      this.beginFsLabel.add("");
      this.beginContainerWidth.add(width);
      this.beginContainerHeight.add(height);
   }

   public void endRow() {
      endGroup();
   }

   /**
    * Starts a new field group
    */
   public void beginFieldset(String label) {
      beginFieldset(1, -1, label, null);
   }

   public void beginFieldset(double width, double height, String label, MarginData marginData) {
      beginLayout.add(LAYOUT_MARKER.BEGIN_FS);
      this.beginFsLabel.add(label);
      this.beginMarginData.add(marginData);
      this.beginContainerWidth.add(width);
      this.beginContainerHeight.add(height);
   }

   /**
    * Ends the current group.
    * 
    * <p>
    * If no group was previously started. method does nothing
    * </p>
    */
   public void endGroup() {
      isEndLayout++;
   }

   protected String nextName() {
      String name = "__simpleForm_internal_" + (++fieldNameCounter);
      return name;
   }

   public String addField(Class<?> type, SimpleFormFieldConfiguration... configs) {
      return addField(type, nextName(), "", configs);
   }

   public String addField(Class<?> type, String label, SimpleFormFieldConfiguration... configs) {
      return addField(type, nextName(), label, configs);
   }

   public String addField(Class<?> type, ValueProvider vp, SimpleFormFieldConfiguration... configs) {
      return addField(type, vp, "", configs);
   }

   public String addField(Class<?> type, ValueProvider vp, String label, SimpleFormFieldConfiguration... configs) {
      return addField(type, vp, label, null, configs);
   }

   public String addField(Class<?> type, String name, String label, SimpleFormFieldConfiguration... configs) {
      return addField(type, name, label, null, configs);
   }

   public String addField(Class<?> type, ValueProvider vp, String label, Object value,
         SimpleFormFieldConfiguration... configs) {
      return addField(type, vp.getPath(), FormFieldLayoutConfiguration.from(label, fieldLayoutConfig), value, vp,
            configs);
   }

   public String addField(Class<?> type, String name, String label, Object value,
         SimpleFormFieldConfiguration... configs) {
      return addField(type, name, FormFieldLayoutConfiguration.from(label, fieldLayoutConfig), value, null, configs);
   }

   /**
    * Adds a new field to the form.
    */
   public String addField(Class<?> type, String name, FormFieldLayoutConfiguration fieldLayoutConfig, Object value,
         ValueProvider vp, SimpleFormFieldConfiguration... configs) {
      /* get hook */
      FormFieldProviderHook responsibleHook = SimpleForm.getResponsibleHooker(type, configs);

      if (null == responsibleHook)
         throw new IllegalArgumentException("Could not find a provider for " + type.getName()); //$NON-NLS-1$

      return addField(responsibleHook, name, fieldLayoutConfig, value, vp);
   }

   public String addField(FormFieldProviderHook responsibleHook, String name,
         FormFieldLayoutConfiguration fieldLayoutConfig, Object value, ValueProvider vp) {
      /* validate name */
      if (fieldKeyList.contains(name))
         throw new IllegalArgumentException("Duplicate field name: " + name);

      /* init hook */
      responsibleHook.init(name, this);

      /* add to lookup maps */
      fieldKeyList.add(name);
      SField sfield = new SField(name);
      fieldLookup.put(name, sfield);

      sfield.setResponsibleHook(responsibleHook);
      sfield.setFieldLayoutConfig(fieldLayoutConfig);
      sfield.setValueProvider(vp);

      /* label */
      if (null != fieldLayoutConfig)
         getFieldDecorator(LabelDecorator.class, LabelDecorator.DECORATOR_ID).addLabel(name, fieldLayoutConfig);

      /* set value */
      if (null != value)
         setValue(name, value);

      /* layout */
      if (!beginLayout.isEmpty()) {
         fieldBeginLayoutLookup.put(name, new ArrayList<SimpleForm.LAYOUT_MARKER>(beginLayout));
         fieldBeginLayoutMarginLookup.put(name, new ArrayList<MarginData>(beginMarginData));
         fieldBeginFieldsetLabelLookup.put(name, new ArrayList<String>(beginFsLabel));
         fieldBeginContainerWidthLookup.put(name, new ArrayList<Double>(beginContainerWidth));
         fieldBeginContainerHeightLookup.put(name, new ArrayList<Double>(beginContainerHeight));
      }

      if (isEndLayout > 0)
         fieldEndLayoutLookup.put(name, isEndLayout);

      beginLayout.clear();
      beginMarginData.clear();
      beginContainerWidth.clear();
      beginContainerHeight.clear();
      beginFsLabel.clear();
      this.isEndLayout = 0;

      return name;
   }

   public static FormFieldProviderHook getResponsibleHooker(final Class<?> type,
         final SimpleFormFieldConfiguration... configs) {

      return hookService.getHookers(FormFieldProviderHook.class).stream()
            .filter(provider -> provider.consumes(type, configs)).findAny().orElse(null);
   }

   public void addFieldMenu(String key, Menu menu) {
      getFieldDecorator(ContextMenuDecorator.class, ContextMenuDecorator.DECORATOR_ID).addMenu(key, menu);
   }

   public void addFieldInfo(String key, String data) {
      getFieldDecorator(FieldInfoDecorator.class, FieldInfoDecorator.DECORATOR_ID).addInfo(key, data);
   }

   public void addFieldInfo(String key, DelayedInfoMessage data) {
      getFieldDecorator(FieldInfoDecorator.class, FieldInfoDecorator.DECORATOR_ID).addInfo(key, data);
   }

   public FormFieldLayoutConfiguration getFieldLayoutConfigFor(String key) {
      return getFieldDecorator(LabelDecorator.class, LabelDecorator.DECORATOR_ID).getConfigFor(key);
   }

   /**
    * Load all fields.
    */
   public void loadFields() {
      if (fieldsLoaded)
         return;
      fieldsLoaded = true;
      fieldKeyList.forEach(this::loadField);

      forceLayout();
   }

   /**
    * Loads all dependancies
    */
   protected void loadDependancies() {
      for (DependencyRegistrar registrar : dependencyRegistrars)
         registrar.registerDependency();
   }

   /**
    * Loads all conditions
    */
   protected void loadConditions() {
      conditionRegistrars.forEach(ConditionRegistrar::registerCondition);
   }

   /**
    * Load the field identified by fieldKey.
    * 
    * @param fieldKey
    */
   protected void loadField(String fieldKey) {
      SField sfield = fieldLookup.get(fieldKey);
      FormFieldProviderHook responsibleHook = sfield.getResponsibleHook();

      /* create field */
      Widget widget = responsibleHook.createFormField();

      /* store field for later lookup */
      sfield.setWidget(widget);

      /* set label if field */
      if (responsibleHook.isDecorateable())
         widget = configureField(widget, fieldKey);
      sfield.setDisplayField(widget);

      if (widget instanceof Component)
         ((Component) widget).setHideMode(HideMode.OFFSETS);

      if (!isBindingActive() && null != sfield.getPreLoadValue())
         responsibleHook.setValue(sfield.getWidget(), sfield.getPreLoadValue());

      /* get container */
      if (fieldEndLayoutLookup.containsKey(fieldKey))
         for (int i = fieldEndLayoutLookup.get(fieldKey); i > 0; i--)
            layoutStack.pop();
      if (fieldBeginLayoutLookup.containsKey(fieldKey))
         addLayoutToStack(fieldKey);

      Container lc = layoutStack.isEmpty() ? fieldWrapper : layoutStack.peek();

      addToContainer(lc, widget, sfield.getFieldLayoutConfig().getFieldWidth(),
            sfield.getFieldLayoutConfig().getFieldHeight(), null, sfield.getFieldLayoutConfig());
      sfield.setContainer(lc);
   }

   private void addToContainer(Container lc, Widget widget, double width, double height, MarginData margin,
         FormFieldLayoutConfiguration config) {
      if (lc instanceof VerticalLayoutContainer)
         ((VerticalLayoutContainer) lc).add(widget,
               new VerticalLayoutData(width, height, null == margin
                     ? widget.getLayoutData() instanceof HasMargins ? ((HasMargins) widget.getLayoutData()).getMargins()
                           : null != config ? config.getMarginData().getMargins() : new Margins(0)
                     : margin.getMargins()));
      else if (lc instanceof HorizontalLayoutContainer)
         ((HorizontalLayoutContainer) lc).add(widget,
               new HorizontalLayoutData(width, height, null == margin
                     ? widget.getLayoutData() instanceof HasMargins ? ((HasMargins) widget.getLayoutData()).getMargins()
                           : null != config ? config.getMarginData().getMargins() : new Margins(0, 10, 0, 00)
                     : margin.getMargins()));
      else if (lc instanceof HBoxLayoutContainer) {
         BoxLayoutData data = new BoxLayoutData(null == margin
               ? widget.getLayoutData() instanceof HasMargins ? ((HasMargins) widget.getLayoutData()).getMargins()
                     : null != config ? config.getMarginData().getMargins() : new Margins(0, 10, 0, 0)
               : margin.getMargins());
         data.setFlex(width);
         ((HBoxLayoutContainer) lc).add(widget, data);
      } else if (lc instanceof DwHorizontalFlowLayoutContainer) {
         MarginData data = new MarginData(0, 10, 0, 0);
         if (width > 1)
            widget.setWidth(width + "px");
         if (widget.getLayoutData() instanceof HasMargins)
            data.setMargins(((HasMargins) widget.getLayoutData()).getMargins());
         ((DwHorizontalFlowLayoutContainer) lc).add(widget, data);
      } else
         lc.add(widget);
   }

   private void addLayoutToStack(final String fieldKey) {
      for (int i = 0; i < fieldBeginLayoutLookup.get(fieldKey).size(); i++) {
         LAYOUT_MARKER type = fieldBeginLayoutLookup.get(fieldKey).get(i);
         double width = fieldBeginContainerWidthLookup.get(fieldKey).get(i);
         double height = fieldBeginContainerHeightLookup.get(fieldKey).get(i);
         MarginData margin = fieldBeginLayoutMarginLookup.get(fieldKey).get(i);

         Container currentLc = layoutStack.isEmpty() ? fieldWrapper : layoutStack.peek();

         switch (type) {
         case BEGIN_FS: {
            String label = fieldBeginFieldsetLabelLookup.get(fieldKey).get(i);

            VerticalLayoutContainer container = new VerticalLayoutContainer();

            FieldSet fieldSet = new FieldSet();
            fieldSet.setHeading(label);
            fieldSet.setWidget(container);

            layoutStack.push(container);

            addToContainer(currentLc, fieldSet, width, height, margin, null);
            break;
         }
         case BEGIN_FLOAT_ROW: {
            DwHorizontalFlowLayoutContainer container = new DwHorizontalFlowLayoutContainer();
            layoutStack.push(container);

            addToContainer(currentLc, layoutStack.peek(), width, height, margin, null);
            break;
         }
         case BEGIN_ROW:
            HBoxLayoutContainer container = new HBoxLayoutContainer();
            container.setHBoxLayoutAlign(HBoxLayoutAlign.STRETCHMAX);
            container.setPack(BoxLayoutPack.START);
            container.setEnableOverflow(false);
            layoutStack.push(container);

            addToContainer(currentLc, layoutStack.peek(), width, height, margin, null);
            break;
         case BEGIN_COLUMN:
            layoutStack.push(new VerticalLayoutContainer());
            currentLc.add(layoutStack.peek());
            addToContainer(currentLc, layoutStack.peek(), width, height, margin, null);
            break;
         }
      }
   }

   protected Widget configureField(Widget formField, String fieldKey) {
      for (SimpleFormFieldDecorator dec : fieldDecorators)
         dec.configureFieldOnLoad(this, formField, fieldKey);

      for (SimpleFormFieldDecorator dec : fieldDecorators) {
         Widget newField = dec.adjustFieldForDisplay(this, formField, fieldKey);
         formField = (newField == null) ? formField : newField;
      }

      return formField;
   }

   /**
    * Returns the field identified by fieldKey.
    * 
    * @param fieldKey
    */
   public Widget getField(String fieldKey) {
      if (!fieldLookup.containsKey(fieldKey))
         return null;
      return fieldLookup.get(fieldKey).getWidget();
   }

   public Widget getDisplayedField(String fieldKey) {
      if (!fieldLookup.containsKey(fieldKey))
         return null;
      return fieldLookup.get(fieldKey).getDisplayField();
   }

   public Collection<String> getFieldKeys() {
      return fieldKeyList;
   }

   public Class<?> getFieldType(String fieldKey) {
      if (!fieldLookup.containsKey(fieldKey))
         return null;

      return fieldLookup.get(fieldKey).getType();
   }

   public FormFieldProviderHook getResponsibleHook(String fieldKey) {
      if (!fieldLookup.containsKey(fieldKey))
         return null;

      return fieldLookup.get(fieldKey).getResponsibleHook();
   }

   public Object getValue(String fieldKey) {
      if (!fieldLookup.containsKey(fieldKey))
         throw new IllegalArgumentException("no such field: " + fieldKey);

      FormFieldProviderHook provider = getResponsibleHook(fieldKey);
      Widget field = getField(fieldKey);

      return provider.getValue(field);
   }

   public void setValue(String key, Object value) {
      if (!fieldLookup.containsKey(key))
         throw new IllegalArgumentException("no such field: " + key);

      if (!isFieldsLoaded()) {
         getSField(key).setPreLoadValue(value);
         return;
      }

      FormFieldProviderHook provider = getResponsibleHook(key);

      Widget field = getField(key);
      provider.setValue(field, value);
   }

   public List<SField> getAllSFields() {
      return fieldLookup.keySet().stream().map(this::getSField).collect(toList());
   }

   public SField getSField(String key) {
      return fieldLookup.get(key);
   }

   public boolean isField(String key) {
      return fieldLookup.containsKey(key);
   }

   /**
    * Allows to issue a form binding
    * 
    * @param model
    */
   public void bind(Object model) {
      /* store model */
      this.model = model;

      /* specialized binding */
      doFieldBindings(model);
   }

   public Object getBoundModel() {
      return this.model;
   }

   public boolean isBindingActive() {
      return model != null;
   }

   protected void doFieldBindings(Object model) {
      /* find lists */
      for (String key : fieldKeyList) {
         if (isLoadFieldsOnBinding())
            loadField(key);

         SField sfield = getSField(key);

         ValueProvider vp = sfield.getValueProvider();
         if (null == vp && model instanceof HasValueProviderByPath) {
            vp = ((HasValueProviderByPath) model).getValueProviderByPath(key);
            sfield.setValueProvider(vp);
         }

         /* bind object if we have a value provider specified for this field */
         FormFieldProviderHook responsibleHook = sfield.getResponsibleHook();
         if (null != vp) {
            Widget field = sfield.getWidget();
            responsibleHook.addFieldBindings(model, vp, field);
         }

         /* set initial value if necessary */
         if (null != sfield.getPreLoadValue())
            responsibleHook.setValue(sfield.getWidget(), sfield.getPreLoadValue());
      }

      if (isLoadFieldsOnBinding() && !isFieldsLoaded()) {
         /* load conditions - dependencies are loaded after rendering */
         loadConditions();

         setFieldsLoaded(true);
         forceLayout();
      }
   }

   private void formSubmitted() {
      if (isValidateOnSubmit() && !mainForm.isValid()) {
         new DwAlertMessageBox(FormsMessages.INSTANCE.validationFailedTitle(),
               FormsMessages.INSTANCE.validationFailedMessage()).show();
         return;
      }

      getCompositeSubmissionCallback().formSubmitted();

      for (SimpleFormSubmissionListener listener : submissionListeners) {
         listener.formSubmitted(this);
      }
   }

   public void addSubmissionListener(SimpleFormSubmissionListener submissionListener) {
      submissionListeners.add(submissionListener);
   }

   public boolean isLoadFieldsOnBinding() {
      return loadFieldsOnBinding;
   }

   public void setLoadFieldsOnBinding(boolean isLoadFieldsOnBinding) {
      this.loadFieldsOnBinding = isLoadFieldsOnBinding;
   }

   public boolean isFieldsLoaded() {
      return fieldsLoaded;
   }

   protected void setFieldsLoaded(boolean fieldsLoaded) {
      this.fieldsLoaded = fieldsLoaded;
   }

   public boolean isValid() {
      return mainForm.isValid();
   }

   public void clearInvalid() {
      mainForm.getFields().forEach(IsField::clearInvalid);
   }

   public void setMethod(Method method) {
      mainForm.setMethod(method);
   }

   public void setEncoding(Encoding encoding) {
      mainForm.setEncoding(encoding);
   }

   public void setAction(String action) {
      mainForm.setAction(action);
   }

   public void submit() {
      mainForm.submit();
   }

   public void setSize(int width, int height) {
      setWidth(width);
      setHeight(height);
   }

   @Override
   public void setHeight(int height) {
      super.setHeight(height);
      mainForm.setHeight(height);
   }

   public HandlerRegistration addValueChangeHandler(String key, ValueChangeHandler handler) {
      if (!isField(key))
         return null;
      return getSField(key).getResponsibleHook().addValueChangeHandler(handler);
   }

   public boolean isValidateOnSubmit() {
      return validateOnSubmit;
   }

   public void setValidateOnSubmit(boolean validateOnSubmit) {
      this.validateOnSubmit = validateOnSubmit;
   }

   public void updateFormLayout() {
      Scheduler.get().scheduleDeferred(new ScheduledCommand() {

         @Override
         public void execute() {
            forceLayout();
            Widget w = getWidget();
            if (null == w)
               w = getOldWidget();
            if (w instanceof VerticalLayoutContainer) {
               VerticalLayoutContainer c = (VerticalLayoutContainer) w;
               c.forceLayout();
            }
         }
      });
   }

   public void setOldWidget(Widget container) {
      this.oldWidget = container;
   }

   public Widget getOldWidget() {
      return oldWidget;
   }

   public static SimpleForm fromJson(String formConfig) {
      return fromJson((SimpleFormJsonConfig) JsonUtils.safeEval(formConfig).cast());
   }

   public static SimpleForm fromJson(SimpleFormJsonConfig config) {
      SimpleForm form = getInlineInstance();

      if ("top".equals(config.getLabelAlign()))
         form.setLabelAlign(LabelAlign.TOP);
      else
         form.setLabelAlign(LabelAlign.LEFT);

      SimpleFormFieldsJson fieldsConfig = config.getFields();
      for (int i = 0; i < fieldsConfig.length(); i++) {
         SimpleFormFieldJson fieldConfig = fieldsConfig.get(i);
         form.addField(fieldConfig);
      }

      return form;
   }

   protected String addField(SimpleFormFieldJson fieldConfig) {
      String type = fieldConfig.getType().toLowerCase();

      /* get hook */
      FormFieldProviderHook responsibleHook = SimpleForm.getResponsibleHooker(type, fieldConfig);

      if (null == responsibleHook)
         throw new IllegalArgumentException("Could not find a provider for " + type); //$NON-NLS-1$

      String name = addField(responsibleHook, fieldConfig.getId(),
            FormFieldLayoutConfiguration.from(fieldConfig.getLabel(), fieldLayoutConfig), null, null);

      /* try set value */
      String value = fieldConfig.getValue();
      if (null != value)
         setValue(name, value);

      return name;
   }

   public static FormFieldProviderHook getResponsibleHooker(final String type, final SimpleFormFieldJson config) {
      return hookService.getHookers(FormFieldProviderHook.class).stream()
            .filter(provider -> provider.consumes(type, config)).findAny().orElse(null);
   }

   public String getStringValue(String fieldKey) {
      if (!fieldLookup.containsKey(fieldKey))
         throw new IllegalArgumentException("no such field: " + fieldKey);

      FormFieldProviderHook provider = getResponsibleHook(fieldKey);
      Widget field = getField(fieldKey);

      return provider.getStringValue(field);
   }

   public Map<String, String> getStringValueMap() {
      return getFieldKeys().stream().collect(toMap(identity(), this::getStringValue));
   }

}
