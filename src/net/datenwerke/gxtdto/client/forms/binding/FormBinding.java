package net.datenwerke.gxtdto.client.forms.binding;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.widget.core.client.form.Field;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.FormPanelHelper;
import com.sencha.gxt.widget.core.client.form.IsField;

import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.utils.StringEscapeUtils;

public class FormBinding {

   private final FormPanel form;
   private Map<HasValueChangeHandlers, HasValueFieldBinding> bindingMap = new HashMap<HasValueChangeHandlers, HasValueFieldBinding>();

   private Set<String> ignorePathsForAutoBinding = new HashSet<String>();
   private Set<HasValueChangeHandlers> ignoreFieldsForAutoBinding = new HashSet<HasValueChangeHandlers>();

   public FormBinding(FormPanel form) {
      this.form = form;
   }

   public void autoBind(Dto dto) {
      for (IsField<?> f : FormPanelHelper.getFields(form)) {
         if (!(f instanceof Field))
            continue;

         Field<?> field = (Field<?>) f;
         if (ignoreFieldsForAutoBinding.contains(field))
            continue;
         if (ignorePathsForAutoBinding.contains(field.getName()))
            continue;

         ValueProvider<?, ?> vp = dto.getValueProviderByPath(field.getName());
         if (null == vp)
            continue;

         HasValueFieldBinding binding = new HasValueFieldBinding(field, dto, vp);
         bindingMap.put(field, binding);
      }
   }

   public void unbind() {
      for (HasValueFieldBinding binding : bindingMap.values())
         binding.unbind();
      bindingMap.clear();
   }

   public void unbind(HasValueChangeHandlers<?> field) {
      if (bindingMap.containsKey(field))
         bindingMap.remove(field).unbind();
   }

   public void clearIgnorePathsForAutoBinding() {
      ignorePathsForAutoBinding.clear();
   }

   public void addIgnorePathsForAutoBinding(String... paths) {
      for (String path : paths)
         ignorePathsForAutoBinding.add(path);
   }

   public void addIgnoreFieldsForAutoBinding(HasValueChangeHandlers... fields) {
      for (HasValueChangeHandlers field : fields)
         ignoreFieldsForAutoBinding.add(field);
   }

   public void clearIgnoreFieldsForAutoBinding() {
      ignoreFieldsForAutoBinding.clear();
   }

   public void addBinding(HasValueChangeHandlers<?> field, HasValueFieldBinding binding) {
      unbind(field);
      bindingMap.put(field, binding);
   }

   public void addBinding(HasValueChangeHandlers<?> field, Object object, ValueProvider vp) {
      addBinding(field, new HasValueFieldBinding(field, object, vp));
   }

   public void addHtmlSafeStringBinding(HasValueChangeHandlers<?> field, Object object, ValueProvider vp) {
      addBinding(field, new HasValueFieldBinding(field, object, vp) {
         @Override
         protected Object convertModelValue(Object value) {
            if (value instanceof String)
               return StringEscapeUtils.unescapeHTML((String) value);
            return value;
         }
      });
   }

}
