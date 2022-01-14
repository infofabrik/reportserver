package net.datenwerke.gf.service.juel;

import java.math.BigDecimal;
import java.util.Date;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.utils.juel.wrapper.SimpleDateWrapper;

@GenerateDto(dtoPackage = "net.datenwerke.gf.client.juel.dto", generateDto2Poso = false, createDecorator = true)
public class JuelResult {

   @ExposeToClient
   private Boolean entryNull = false;

   @ExposeToClient
   private JuelResultType type;

   @ExposeToClient(disableHtmlEncode = true)
   private String stringValue;

   @ExposeToClient
   private Integer intValue;

   @ExposeToClient
   private Long longValue;

   @ExposeToClient
   private BigDecimal decimalValue;

   @ExposeToClient
   private Double doubleValue;

   @ExposeToClient
   private Float floatValue;

   @ExposeToClient
   private Date dateValue;

   @ExposeToClient
   private Boolean booleanValue;

   public JuelResult() {
   }

   public JuelResult(Object object) {
      this.setValue(object);
   }

   public JuelResult(JuelResultType type, Object object) {
      this.setValue(type, object);
   }

   public void setValue(Object object) {
      if (null == object)
         setValue(JuelResultType.NULL, null);
      else if (object instanceof Boolean)
         setValue(JuelResultType.BOOLEAN, object);
      else if (object instanceof Date)
         setValue(JuelResultType.DATE, object);
      else if (object instanceof SimpleDateWrapper)
         setValue(JuelResultType.DATE, ((SimpleDateWrapper) object).getDate());
      else if (object instanceof BigDecimal)
         setValue(JuelResultType.DECIMAL, object);
      else if (object instanceof Double)
         setValue(JuelResultType.DOUBLE, object);
      else if (object instanceof Float)
         setValue(JuelResultType.FLOAT, object);
      else if (object instanceof Long)
         setValue(JuelResultType.LONG, object);
      else if (object instanceof Integer)
         setValue(JuelResultType.INTEGER, object);
      else
         setValue(JuelResultType.STRING, object);

   }

   public void setValue(JuelResultType type, Object object) {
      setType(type);

      if (null == object) {
         setEntryNull(true);
      } else {
         switch (type) {
         case BOOLEAN:
            setBooleanValue(Boolean.TRUE.equals(object));
            break;
         case DATE:
            setDateValue((Date) object);
            break;
         case DECIMAL:
            setDecimalValue((BigDecimal) object);
            break;
         case DOUBLE:
            setDoubleValue((Double) object);
            break;
         case FLOAT:
            setFloatValue((Float) object);
            break;
         case INTEGER:
            setIntValue((Integer) object);
            break;
         case LONG:
            setLongValue((Long) object);
            break;
         default:
            setStringValue(String.valueOf(object));
            break;
         }
      }
   }

   public JuelResultType getType() {
      return type;
   }

   public void setType(JuelResultType type) {
      this.type = type;
   }

   public String getStringValue() {
      return stringValue;
   }

   public void setStringValue(String stringValue) {
      this.stringValue = stringValue;
   }

   public Integer getIntValue() {
      return intValue;
   }

   public void setIntValue(int intValue) {
      this.intValue = intValue;
   }

   public Long getLongValue() {
      return longValue;
   }

   public void setLongValue(long longValue) {
      this.longValue = longValue;
   }

   public BigDecimal getDecimalValue() {
      return decimalValue;
   }

   public void setDecimalValue(BigDecimal decimalValue) {
      this.decimalValue = decimalValue;
   }

   public Double getDoubleValue() {
      return doubleValue;
   }

   public void setDoubleValue(double doubleValue) {
      this.doubleValue = doubleValue;
   }

   public Float getFloatValue() {
      return floatValue;
   }

   public void setFloatValue(float floatValue) {
      this.floatValue = floatValue;
   }

   public boolean isEntryNull() {
      return entryNull;
   }

   public void setEntryNull(boolean entryNull) {
      this.entryNull = entryNull;
   }

   public Date getDateValue() {
      return dateValue;
   }

   public void setDateValue(Date dateValue) {
      this.dateValue = dateValue;
   }

   public Boolean isBooleanValue() {
      return booleanValue;
   }

   public void setBooleanValue(Boolean booleanValue) {
      this.booleanValue = booleanValue;
   }

}
