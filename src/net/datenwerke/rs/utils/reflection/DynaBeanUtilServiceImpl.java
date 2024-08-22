package net.datenwerke.rs.utils.reflection;

import static java.util.stream.Collectors.toList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtils;

public class DynaBeanUtilServiceImpl implements DynaBeanUtilService {

   @Override
   public Map<String, Object> convertDynaBeanToMap(DynaBean dynaBean) {
      Map<String, Object> resultMap = new HashMap<>();
      DynaProperty[] dynaProperties = dynaBean.getDynaClass().getDynaProperties();

      try {
         for (DynaProperty dynaProperty : dynaProperties) {
             String propertyName = dynaProperty.getName();
             Object propertyValue = PropertyUtils.getProperty(dynaBean, propertyName);
             resultMap.put(propertyName, propertyValue);
         }
      } catch (Exception e) {
         throw new IllegalStateException("Could not convert DynaBean into a map", e);
      }

      return resultMap;
   }

   @Override
   public List<Map<String, Object>> convertDynaBeansToMap(List<DynaBean> dynaBeans) {
      return dynaBeans
         .stream()
         .map(this::convertDynaBeanToMap)
         .collect(toList());
   }
   
}
