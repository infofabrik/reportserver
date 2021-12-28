package net.datenwerke.gxtdto.client.forms.simpleform;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class SimpleMultiForm extends SimpleForm {

   private final List<SimpleForm> subFormList = new ArrayList<>();

   public void addSubForm(SimpleForm form) {
      subFormList.add(form);

      Widget container = form.getWidget();

      VerticalLayoutContainer.VerticalLayoutData layoutData = new VerticalLayoutContainer.VerticalLayoutData();
      layoutData.setMargins(new Margins(0));
      fieldWrapper.add(container, layoutData);

      form.setOldWidget(container);
   }

   @Override
   public SimpleFormSubmissionCallback getCompositeSubmissionCallback() {
      List<SimpleFormSubmissionCallback> callbacks = new ArrayList<SimpleFormSubmissionCallback>();
      callbacks.add(super.getCompositeSubmissionCallback());
      for (SimpleForm sf : subFormList) {
         callbacks.add(sf.getCompositeSubmissionCallback());
      }

      if (callbacks.size() == 1)
         return callbacks.get(0);

      return new ChainedCallbackWrapper(callbacks, this);
   }

   @Override
   protected void onAfterFirstAttach() {
      boolean afterFirst = isAfterInitialLayout;

      super.onAfterFirstAttach();

      if (afterFirst)
         return;

      /* method is not called on subforms */
      for (SimpleForm subForm : subFormList) {
         subForm.onAfterFirstAttach();
      }
   }

   public static SimpleMultiForm createInlineInstance() {
      SimpleMultiForm form = new SimpleMultiForm();

      form.getButtonBar().clear();
      form.setHeaderVisible(false);
      form.setBodyBorder(false);
      form.setBorders(false);

      return form;
   }

}
