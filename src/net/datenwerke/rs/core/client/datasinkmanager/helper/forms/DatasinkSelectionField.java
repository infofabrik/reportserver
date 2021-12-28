package net.datenwerke.rs.core.client.datasinkmanager.helper.forms;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;
import com.sencha.gxt.widget.core.client.box.MessageBox;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.LayoutData;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.Validator;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.selection.SingleTreeSelectionField;
import net.datenwerke.gxtdto.client.clipboard.ClipboardUiService;
import net.datenwerke.gxtdto.client.eventbus.EventBusHelper;
import net.datenwerke.gxtdto.client.forms.binding.HasValueFieldBinding;
import net.datenwerke.gxtdto.client.servercommunication.callback.NotamCallback;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkUIService;
import net.datenwerke.rs.core.client.datasinkmanager.HasDefaultDatasink;
import net.datenwerke.rs.core.client.datasinkmanager.config.DatasinkDefinitionConfigConfigurator;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkContainerDto;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkContainerProviderDto;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.datasinkmanager.dto.pa.DatasinkContainerDtoPA;
import net.datenwerke.rs.core.client.datasinkmanager.locale.DatasinksMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

/**
 * 
 *
 */
public class DatasinkSelectionField implements HasValueChangeHandlers<DatasinkContainerDto> {

   private static int FIELDNAME_INDEX = 1;
   private final String FIELDNAME = "__internal_datasink_" + FIELDNAME_INDEX++; //$NON-NLS-1$

   private final DatasinkUIService datasinkService;
   private final Container container;
   private final UITree datasinkTree;
   private final Provider<? extends HasDefaultDatasink> datasinkDaoProvider;

   private DatasinkDefinitionDto oldDatasink = null;
   private SingleTreeSelectionField dsField;

   private DatasinkContainerDto datasinkContainer;
   private final BaseIcon defaultDatasinkIcon;

   private FieldLabel dsFieldLabel;
   private LayoutData fieldLayoutData;
   private Class<? extends DatasinkDefinitionDto>[] types;

   @Inject
   public DatasinkSelectionField(ClipboardUiService clipboardService, DatasinkUIService datasinkService,
         @Assisted Provider<? extends HasDefaultDatasink> datasinkDaoProvider, @Assisted BaseIcon defaultDatasinkIcon,
         @Assisted Container container, @Assisted UITree datasinkTree,
         @Assisted Class<? extends DatasinkDefinitionDto>... types) {

      /* store objects */
      this.datasinkService = datasinkService;
      this.container = container;
      this.datasinkTree = datasinkTree;
      this.datasinkDaoProvider = datasinkDaoProvider;
      this.types = types;
      this.defaultDatasinkIcon = defaultDatasinkIcon;

      if (null == this.types || this.types.length == 0) {
         this.types = new Class[] { DatasinkDefinitionDto.class };
      }
   }

   public SingleTreeSelectionField getSelectionField() {
      return dsField;
   }

   public DatasinkDefinitionDto getDatasink() {
      return (DatasinkDefinitionDto) dsField.getValue();
   }

   public DatasinkContainerDto getDatasinkContainer() {
      if (null != datasinkContainer)
         return datasinkContainer;
      else {
         DatasinkContainerDto container = new DatasinkContainerDto();
         container.setDatasink(getDatasink());
         return container;
      }
   }

   public void setFieldLabel(FieldLabel fieldLabel) {
      if (null != dsField) {
         dsFieldLabel.setText(fieldLabel.getText());
      } else
         this.dsFieldLabel = fieldLabel;
   }

   public void setLabel(String text) {
      if (null == text)
         return;

      if (null == dsFieldLabel)
         this.dsFieldLabel = new FieldLabel();

      this.dsFieldLabel.setText(text);
   }

   public void addSelectionField() {
      dsField = new SingleTreeSelectionField(types);
      dsField.setTriggerIcon(BaseIcon.SEARCH);
      dsField.setWidth(210);
      dsField.setTreePanel(datasinkTree);
      dsField.setName(FIELDNAME);
      addDisplayDefaultDatasinkButton();

      if (null == dsFieldLabel)
         dsFieldLabel = new FieldLabel(dsField, DatasinksMessages.INSTANCE.datasink());
      else
         dsFieldLabel.setWidget(dsField);

      if (null != fieldLayoutData)
         dsFieldLabel.setLayoutData(fieldLayoutData);

      container.add(dsFieldLabel);

   }

   private void showFailureMessageBox() {
      MessageBox msg = new MessageBox(DatasinksMessages.INSTANCE.useDefaultFailureTitle(),
            DatasinksMessages.INSTANCE.useDefaultFailureMessage());
      msg.show();
   }

   private void addDisplayDefaultDatasinkButton() {
      dsField.setTwinTriggerIcon(defaultDatasinkIcon);
      dsField.setHideTwinTrigger(false);
      dsField.redraw();
      dsField.addTwinTriggerClickHandler(event -> {
         datasinkDaoProvider.get().getDefaultDatasink(
               new NotamCallback<DatasinkDefinitionDto>(DatasinksMessages.INSTANCE.useDefaultSuccessMessage(),
                     DatasinksMessages.INSTANCE.useDefaultFailureMessage()) {
                  @Override
                  public void doOnSuccess(DatasinkDefinitionDto result) {
                     if (result == null)
                        showFailureMessageBox();
                     else
                        dsField.setValue(result, true);
                  }
               });
      });
   }

   public HasValueFieldBinding initFormBinding(final DatasinkContainerProviderDto datasinkContainerProvider) {
      datasinkContainer = datasinkContainerProvider.getDatasinkContainer();
      if (null == datasinkContainer) {
         datasinkContainer = new DatasinkContainerDto();
         datasinkContainerProvider.setDatasinkContainer(datasinkContainer);
      }

      HasValueFieldBinding binding = new HasValueFieldBinding(dsField, datasinkContainer,
            DatasinkContainerDtoPA.INSTANCE.datasink()) {
         @Override
         public void updateField() {
            DatasinkSelectionField.this.datasinkContainer = datasinkContainerProvider.getDatasinkContainer();
            if (null != datasinkContainer)
               dsField.setValue(datasinkContainer.getDatasink(), true);
            else
               dsField.setValue(null, true);

         }

         @Override
         public void updateModel(Object value) {
            DatasinkSelectionField.this.datasinkContainer = datasinkContainerProvider.getDatasinkContainer();

            /* get datasink */
            DatasinkDefinitionDto datasink = (DatasinkDefinitionDto) dsField.getValue();

            /* update fields */
            DatasinkSelectionField.this.updateFieldsAndModel(datasink, datasinkContainerProvider);

            ValueChangeEvent.fire(DatasinkSelectionField.this, datasinkContainer);
         }
      };

      return binding;
   }

   public void addValidation(Validator<AbstractNodeDto> validator) {
      dsField.addValidator(validator);
   }

   public boolean validate() {
      return dsField.validate();
   }

   protected void updateFieldsAndModel(DatasinkDefinitionDto datasink,
         final DatasinkContainerProviderDto datasinkContainerProvider) {
      /* if datasink is null remove config and return */
      if (null == datasink) {
         /* update model */
         oldDatasink = null;
         datasinkContainer.setDatasink(null);

         return;
      }

      /* we have a new datasink */

      /* update model with new datasink */
      datasinkContainer.setDatasink(datasink);

      /* reset the datasink container, so that a proxy knows that something changed */
      datasinkContainerProvider.setDatasinkContainer(datasinkContainer);

      /*
       * if the new datasink equals the old one (type) and configurator does not want
       * to reload then do nothing
       */
      DatasinkDefinitionConfigConfigurator tempConfigurator = datasinkService.getConfigurator(datasink.getClass());
      if (null != oldDatasink && datasink.getClass().equals(oldDatasink.getClass())
            && !tempConfigurator.isReloadOnDatasinkChange())
         return;
      else
         oldDatasink = datasink;

   }

   public void setFieldLayoutData(LayoutData fieldLayoutData) {
      this.fieldLayoutData = fieldLayoutData;
   }

   @Override
   public void fireEvent(GwtEvent<?> event) {
      EventBusHelper.EVENT_BUS.fireEventFromSource(event, this);
   }

   @Override
   public HandlerRegistration addValueChangeHandler(ValueChangeHandler<DatasinkContainerDto> handler) {
      return EventBusHelper.EVENT_BUS.addHandlerToSource(ValueChangeEvent.getType(), this, handler);
   }

}
