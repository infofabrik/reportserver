package net.datenwerke.rs.utils.reflection;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.DynaBean;

import com.google.inject.ImplementedBy;

@ImplementedBy(DynaBeanUtilServiceImpl.class)
public interface DynaBeanUtilService {

   Map<String, Object> convertDynaBeanToMap(DynaBean dynaBean);

   List<Map<String, Object>> convertDynaBeansToMap(List<DynaBean> dynaBeans);

}
