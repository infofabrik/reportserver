package net.datenwerke.rs.grideditor.service.grideditor.definition;

import java.math.BigDecimal;
import java.util.Date;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gxtdto.client.utils.SqlTypes;

@GenerateDto(
	dtoPackage="net.datenwerke.rs.grideditor.client.grideditor.dto",
	generateDto2Poso=false,
	createDecorator=true
)
public class GridEditorRecordEntry {
	
	@ExposeToClient
	private Boolean entryNull = false;
	
	@ExposeToClient
	private Integer type;
	
	@ExposeToClient(disableHtmlEncode=true)
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

	public GridEditorRecordEntry(){
		
	}
	
	public GridEditorRecordEntry(Integer value) {
		setValue(SqlTypes.INTEGER, value);
	}
	
	public GridEditorRecordEntry(String value) {
		setValue(SqlTypes.VARCHAR, value);
	}
	
	public GridEditorRecordEntry(Boolean value) {
		setValue(SqlTypes.BOOLEAN, value);
	}
	
	public GridEditorRecordEntry(Double value) {
		setValue(SqlTypes.DOUBLE, value);
	}
	
	public GridEditorRecordEntry(Float value) {
		setValue(SqlTypes.FLOAT, value);
	}

	public GridEditorRecordEntry(BigDecimal value) {
		setValue(SqlTypes.DECIMAL, value);
	}
	
	public GridEditorRecordEntry(Date value) {
		setValue(SqlTypes.DATE, value);
	}

	public GridEditorRecordEntry(Integer type, Object value) {
		setValue(type, value);
	}
	
	public void setValue(Integer value) {
		setValue(SqlTypes.INTEGER, value);
	}
	
	public void setValue(String value) {
		setValue(SqlTypes.VARCHAR, value);
	}
	
	public void setValue(Boolean value) {
		setValue(SqlTypes.BOOLEAN, value);
	}
	
	public void setValue(Double value) {
		setValue(SqlTypes.DOUBLE, value);
	}
	
	public void setValue(Float value) {
		setValue(SqlTypes.FLOAT, value);
	}

	public void setValue(BigDecimal value) {
		setValue(SqlTypes.DECIMAL, value);
	}
	
	public void setValue(Date value) {
		setValue(SqlTypes.DATE, value);
	}

	public void setValue(Integer type, Object value) {
		this.type = type;
		
		if(null == value){
			setEntryNull(true);
			return;
		}
			
		switch(type){
		case SqlTypes.BIT:
		case SqlTypes.BOOLEAN:
			booleanValue = Boolean.TRUE.equals(value); 	
			break;
		case SqlTypes.CHAR:
    	case SqlTypes.CLOB:
    	case SqlTypes.LONGVARCHAR:
    	case SqlTypes.LONGNVARCHAR:
    	case SqlTypes.VARCHAR:
    	case SqlTypes.ROWID:
    	case SqlTypes.NVARCHAR:
    	case SqlTypes.NCHAR:
    	case SqlTypes.NCLOB:
    	case SqlTypes.SQLXML:
    	case SqlTypes.OTHER:
    		stringValue = String.valueOf(value);
    		break;
    		
    	case SqlTypes.BIGINT:
    		longValue = (long) value;
    		break;
    	case SqlTypes.INTEGER:
    		intValue = (Integer) value;
    		break;
    	case SqlTypes.SMALLINT:
    	case SqlTypes.TINYINT:
    		intValue = new Integer((Short)value);
    		break;
    	case SqlTypes.NUMERIC:
    	case SqlTypes.DECIMAL:
    		decimalValue = (BigDecimal) value;
    		break;
    		
    	case SqlTypes.DATE:
    	case  SqlTypes.TIME:
    	case  SqlTypes.TIMESTAMP:
    		dateValue = (Date) value;
    		break;
    		
    	case SqlTypes.DOUBLE:
    		doubleValue = (double) value;
    		break;
    	case SqlTypes.FLOAT:
    		floatValue = (float) value;
    		break;
    		    		
    	case SqlTypes.BLOB:
    	case SqlTypes.LONGVARBINARY:
    		break;
		}
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
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
