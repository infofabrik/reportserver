package net.datenwerke.gf.client.managerhelper.mainpanel;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.event.SubmitCompleteEvent;
import com.sencha.gxt.widget.core.client.event.SubmitCompleteEvent.SubmitCompleteHandler;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.FormPanel.Encoding;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.form.FormPanel.Method;
import com.sencha.gxt.widget.core.client.form.FormPanelHelper;
import com.sencha.gxt.widget.core.client.form.IsField;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;

import net.datenwerke.gf.client.managerhelper.locale.ManagerhelperMessages;
import net.datenwerke.gf.client.upload.FileUploadUIModule;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.dialog.error.DetailErrorDialog;
import net.datenwerke.gxtdto.client.forms.binding.FormBinding;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.servercommunication.callback.NotamCallback;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwButtonBar;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.dto.WriteDto;
import net.datenwerke.security.client.treedb.dto.decorator.SecuredAbstractNodeDtoDec;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

/**
 * A {@link MainPanelView} that displays a FormPanel.
 * 
 *
 */
public abstract class FormView extends MainPanelView {

   public static interface FormViewDataProcessedClearance {
      void finishUp();

      void onError(Throwable t);
   }

   protected FormPanel form;

   protected VerticalLayoutContainer scrollWrapper;

   @Override
   public ImageResource getIcon() {
      return BaseIcon.LIST.toImageResource();
   }

   @Override
   public Component getViewComponent(AbstractNodeDto selectedNode) {
      scrollWrapper = new VerticalLayoutContainer();
      scrollWrapper.setScrollMode(ScrollMode.AUTOY);

      /* create form wrapper */
      DwContentPanel formWrapper = new DwContentPanel();
      formWrapper.setLightDarkStyle();
      formWrapper.setBodyBorder(false);
      formWrapper.setBorders(false);

      String header = getHeader();
      if (null != header)
         formWrapper.setHeading(header);
      else
         formWrapper.setHeaderVisible(false);

      /* create widget instance */
      form = new FormPanel();
      form.setLabelAlign(LabelAlign.TOP);
      formWrapper.add(form);
      scrollWrapper.add(formWrapper, new VerticalLayoutData(1, -1, new Margins(10)));

      /* create wrapper for fields */
      VerticalLayoutContainer fieldWrapper = new VerticalLayoutContainer();
      form.setWidget(fieldWrapper);

      /* init form */
      initializeForm(form, fieldWrapper);
      initFormBinding(form, getSelectedNode());

      /* buttons */
      if (!(getSelectedNode() instanceof SecuredAbstractNodeDtoDec)
            || ((SecuredAbstractNodeDtoDec) getSelectedNode()).hasAccessRight(WriteDto.class)) {
         /* button bar */
         DwButtonBar bbar = new DwButtonBar();
         bbar.setMinButtonWidth(75);
         bbar.setPadding(new Padding(0, 10, 0, 0));
         bbar.setPack(BoxLayoutPack.END);
         fieldWrapper.add(bbar, new VerticalLayoutData(1, -1, new Margins(0, 0, 30, 0)));

         /* make buttons align to the right */
         bbar.add(new FillToolItem());

         final DwTextButton bSubmit = new DwTextButton(BaseMessages.INSTANCE.apply(), BaseIcon.CHECK);
         bbar.add(bSubmit);

         /* listen to selection events */
         bSubmit.addSelectHandler(new SelectHandler() {
            @SuppressWarnings("unchecked")
            @Override
            public void onSelect(SelectEvent event) {
               mask(BaseMessages.INSTANCE.storingMsg());

               /* ask widget to finalize the node */
               doPreSubmit(getSelectedNode());

               /* perform server call */
               doSaveNode(getSelectedNode());
            }
         });

         /* add listener if we handle upload events */
         if (isUploadForm()) {
            form.addSubmitCompleteHandler(new SubmitCompleteHandler() {

               @Override
               public void onSubmitComplete(SubmitCompleteEvent event) {
                  if (!event.getResults().startsWith(FileUploadUIModule.UPLOAD_SUCCESSFUL_PREFIX))
                     new DetailErrorDialog(BaseMessages.INSTANCE.error(), BaseMessages.INSTANCE.uploadError(),
                           ManagerhelperMessages.INSTANCE.upoadError() + event.getResults()).show();

                  /* reload selected node */
                  reloadNodeAndView();
               }
            });
         }

         /* button binding */
         ValueChangeHandler changeHandler = new ValueChangeHandler() {
            @Override
            public void onValueChange(ValueChangeEvent event) {
               bSubmit.setEnabled(FormPanelHelper.isValid(form, true));
            }
         };
         for (IsField<?> f : FormPanelHelper.getFields(form)) {
            if (f instanceof HasValueChangeHandlers) {
               HasValueChangeHandlers field = (HasValueChangeHandlers) f;
               field.addValueChangeHandler(changeHandler);
            }
         }

         if (null != getFormAction())
            form.setAction(getFormAction());

         /* file upload */
         if (isUploadForm()) {
            form.setMethod(Method.POST);
            form.setEncoding(Encoding.MULTIPART);
         }
      }

      return scrollWrapper;
   }

   /**
    * Allows to perform processing after the standard ajax call is finished but
    * before the form submit or unmask is done.
    * 
    * @param clearance
    */
   protected void dataProcessed(FormViewDataProcessedClearance clearance) {
      clearance.finishUp();
   }

   protected int getWidth() {
      return -1;
   }

   protected String getHeader() {
      return null;
   }

   protected boolean isUploadForm() {
      return false;
   }

   protected String getFormAction() {
      return null;
   }

   protected abstract void initializeForm(FormPanel form, VerticalLayoutContainer fieldWrapper);

   @Override
   public String getComponentHeader() {
      return BaseMessages.INSTANCE.properties();
   }

   protected final void initFormBinding(FormPanel form, AbstractNodeDto selectedNode) {
      if (!isFormBinding())
         return;

      FormBinding binding = new FormBinding(form);
      if (isAutoBinding()) {
         configureAutoBinding(binding);
         binding.autoBind(selectedNode);
      }

      doInitFormBinding(binding, selectedNode);
   }

   protected void configureAutoBinding(FormBinding binding) {

   }

   protected FormPanel getForm() {
      return form;
   }

   /**
    * To be overriden
    * 
    * @param selectedNode
    */
   protected void doPreSubmit(AbstractNodeDto selectedNode) {
   }

   /*
    * To be overriden
    * 
    * @param selectedNode
    */
   @SuppressWarnings("unchecked")
   protected void doSaveNode(AbstractNodeDto selectedNode) {
      treeManager.updateNode(selectedNode,
            new NotamCallback<AbstractNodeDto>(ManagerhelperMessages.INSTANCE.updated()) {
               @Override
               public void doOnSuccess(final AbstractNodeDto result) {
                  dataProcessed(new FormViewDataProcessedClearance() {
                     @Override
                     public void finishUp() {
                        /* file upload ? */
                        if (null != getFormAction() && isUploadForm()) {
                           /* ask widget to finalize the node */
                           ((FormPanel) form).submit();
                        } else
                           unmask();
                     }

                     @Override
                     public void onError(Throwable t) {
                        new DetailErrorDialog(t).show();
                        unmask();
                     }
                  });
               }

               @Override
               public void doOnFailure(Throwable caught) {
                  unmask();
               }
            });
   }

   /**
    * To be overriden
    * 
    * @param binding
    * 
    * @param binding
    * @param selectedNode
    */
   protected void doInitFormBinding(FormBinding binding, AbstractNodeDto selectedNode) {
   }

   protected boolean isFormBinding() {
      return true;
   }

   protected boolean isAutoBinding() {
      return true;
   }

}
