package net.datenwerke.rs.base.service.reportengines.table.output.object;

import java.sql.Clob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datenwerke.rs.base.service.reportengines.locale.ReportEnginesMessages;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorRuntimeException;

public class RSSimpleTableBean extends CompiledTableReport implements CompiledReport {

   /**
    * 
    */
   private static final long serialVersionUID = 6857312595785000533L;

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   private List<String> attributes = new ArrayList<String>();

   private BasicDynaClass dynaClass;

   private DynaProperty[] props;

   private HashMap<Integer, List<String>> idxToAttributeMap = new HashMap<Integer, List<String>>();

   private List<DynaBean> rows = new ArrayList<DynaBean>();
   private DynaBean row;
   private int fieldIndex = 0;

   public RSSimpleTableBean() {
   }

   private void addToIdxToAttributeMap(Integer idx, String attribute) {
      List<String> list;
      if (!idxToAttributeMap.containsKey(idx)) {
         list = new ArrayList<String>();
         idxToAttributeMap.put(idx, list);
      } else {
         list = idxToAttributeMap.get(idx);
      }

      list.add(attribute);
   }
   
   private Class<?> mapTypeClass(Class<?> clazz) {
      if (Clob.class.equals(clazz))
         return String.class;
      if (Short.class.equals(clazz) || Byte.class.equals(clazz))
         return Integer.class;
      
      return clazz;
   }

   public void setDefinition(TableDefinition td, TableReport report) {
      this.attributes = new ArrayList<String>();

      ArrayList<DynaProperty> props = new ArrayList<DynaProperty>();

      int i = 0;
      for (String colName : td.getColumnNames()) {
         if (!attributes.contains(colName)) {
            attributes.add(colName);

            props.add(new DynaProperty(colName, mapTypeClass(td.getColumnTypes().get(i))));
            addToIdxToAttributeMap(i, colName);
         } else
            throw new ReportExecutorRuntimeException(
                  ReportEnginesMessages.INSTANCE.exceptionOutputFormatNotSupportsDuplicateNames(colName));
         i++;
      }

      i = 0;
      for (String colName : td.getOriginalColumnNames()) {
         if (!attributes.contains(colName)) {
            attributes.add(colName);
            props.add(new DynaProperty(colName, mapTypeClass(td.getColumnTypes().get(i))));
            addToIdxToAttributeMap(i, colName);
         }
         i++;
      }

      this.props = props.toArray(new DynaProperty[0]);
      dynaClass = new BasicDynaClass("rssimpletable", null, this.props);

      /* init */
      nextRow();
   }

   public List<String> getAttributes() {
      return this.attributes;
   }

   @Override
   public Object getReport() {
      return null;
   }

   @Override
   public String getMimeType() {
      return null;
   }

   @Override
   public String getFileExtension() {
      return null;
   }

   public void addField(final Object value) {
      idxToAttributeMap.get(fieldIndex).forEach(attr -> row.set(attr, value));

      fieldIndex++;
   }

   public List<DynaBean> getTable() {
      return rows;
   }

   public void nextRow() {
      fieldIndex = 0;

      try {
         if (null != row)
            rows.add(row);
         row = dynaClass.newInstance();
      } catch (Exception e) {
         logger.warn(e.getMessage(), e);
      }
   }

   @Override
   public boolean isStringReport() {
      return false;
   }

   public void close() {
      if (null != row)
         rows.add(row);

      fieldIndex = 0;
      row = null;

   }

}
