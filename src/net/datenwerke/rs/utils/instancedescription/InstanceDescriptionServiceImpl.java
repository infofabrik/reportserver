package net.datenwerke.rs.utils.instancedescription;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.hibernate.proxy.HibernateProxy;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.utils.instancedescription.annotations.Description;
import net.datenwerke.rs.utils.instancedescription.annotations.IconProvider;
import net.datenwerke.rs.utils.instancedescription.annotations.Title;
import net.datenwerke.rs.utils.juel.SimpleJuel;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;
import net.datenwerke.rs.utils.reflection.ReflectionService;

public class InstanceDescriptionServiceImpl implements InstanceDescriptionService {

   private final Provider<SimpleJuel> juelProvider;
   private final ReflectionService reflectionService;

   @Inject
   public InstanceDescriptionServiceImpl(Provider<SimpleJuel> juelProvider, ReflectionService reflectionService) {

      /* store objects */
      this.juelProvider = juelProvider;
      this.reflectionService = reflectionService;
   }

   @Override
   public InstanceDescription getDescriptionFor(Object object) {
      if (object instanceof HibernateProxy)
         object = ((HibernateProxy) object).getHibernateLazyInitializer().getImplementation();

      net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription instanceDescriptor = object.getClass()
            .getAnnotation(net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription.class);

      String objectType = getObjectType(instanceDescriptor);
      String title = getTitle(object, instanceDescriptor);
      String description = getDescription(object, instanceDescriptor);

      String icon = getIcon(object, instanceDescriptor);

      return new InstanceDescription(objectType, title, description, icon);
   }

   private String getIcon(Object object,
         net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription instanceDescriptor) {
      String icon = "file";
      if (null != instanceDescriptor) {
         icon = instanceDescriptor.icon();
      }

      /* search for method with icon provider */
      Method iconProvider = reflectionService.getMethodByAnnotation(object, IconProvider.class);
      if (null != iconProvider) {
         try {
            iconProvider.setAccessible(true);
            Object result = iconProvider.invoke(object, (Object[]) null);
            if (result instanceof String)
               icon = (String) result;
         } catch (Exception e) {
         }
      }
      return icon;
   }

   private String getObjectType(
         net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription instanceDescriptor) {
      if (null == instanceDescriptor)
         return "unknown";

      Class<? extends Messages> msg = instanceDescriptor.msgLocation();
      String key = instanceDescriptor.objNameKey();
      if (null != msg && null != key && !"".equals(key))
         return LocalizationServiceImpl.getMessage(msg, key);

      return null;
   }

   private String getTitle(Object object,
         net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription instanceDescriptor) {
      String title = "";

      if (null == instanceDescriptor || "".equals(instanceDescriptor.title())) {
         Field titleField = reflectionService.getFieldByAnnotation(object, Title.class);
         if (null != titleField) {
            titleField.setAccessible(true);
            Object titleTmp;
            try {
               titleTmp = titleField.get(object);
               if (null != titleTmp)
                  title = titleTmp.toString();
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            }
         }
      } else {
         SimpleJuel juel = prepareJuel(object, instanceDescriptor);
         title = juel.parse(instanceDescriptor.title());
      }

      return title;
   }

   private String getDescription(Object object,
         net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription instanceDescriptor) {
      String description = "";

      if (null == instanceDescriptor || "".equals(instanceDescriptor.description())) {
         Field descField = reflectionService.getFieldByAnnotation(object, Description.class);
         if (null != descField) {
            descField.setAccessible(true);
            Object descTmp;
            try {
               descTmp = descField.get(object);
               if (null != descTmp)
                  description = descTmp.toString();
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            }
         }
      } else {
         SimpleJuel juel = prepareJuel(object, instanceDescriptor);
         description = juel.parse(instanceDescriptor.description());
      }

      return description;
   }

   private SimpleJuel prepareJuel(Object object,
         net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription instanceDescriptor) {
      SimpleJuel juel = juelProvider.get();
      for (String fieldName : instanceDescriptor.fields()) {
         Field field = reflectionService.getFieldByName(object, fieldName);
         if (null != field) {
            field.setAccessible(true);
            try {
               Object value = field.get(object);
               String strValue = null != value ? value.toString() : "";
               juel.addReplacement(fieldName, strValue);
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            }
         }
      }

      return juel;
   }

}
