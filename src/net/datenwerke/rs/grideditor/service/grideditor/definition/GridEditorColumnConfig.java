package net.datenwerke.rs.grideditor.service.grideditor.definition;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gxtdto.client.utils.SqlTypes;
import net.datenwerke.rs.base.service.reportengines.table.entities.Order;
import net.datenwerke.rs.grideditor.service.grideditor.definition.editor.Editor;
import net.datenwerke.rs.grideditor.service.grideditor.definition.validator.Validator;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;

@GenerateDto(dtoPackage = "net.datenwerke.rs.grideditor.client.grideditor.dto", createDecorator = true, generateDto2Poso = false)
public class GridEditorColumnConfig {

   @ExposeToClient
   private String name;

   @ExposeToClient
   private String displayName;

   @ExposeToClient
   private int width = 200;

   @ExposeToClient
   private int type;

   @ExposeToClient
   private boolean editable = true;

   @ExposeToClient
   private boolean sortable = true;

   @ExposeToClient
   private boolean hidden = false;

   @ExposeToClient
   private boolean filterable = true;

   @ExposeToClient
   private boolean enforceCaseSensitivity = false;

   @ExposeToClient
   private boolean defaultCaseSensitive = true;

   @ExposeToClient
   @EnclosedEntity
   private List<Validator> validators = new ArrayList<Validator>();

   @ExposeToClient
   @EnclosedEntity
   private Editor editor;

   @ExposeToClient
   private Order order;

   @ExposeToClient
   private Boolean primaryKey;

   @ExposeToClient
   @EnclosedEntity
   private GridEditorRecordEntry defaultValue;

   private GridEditorForeignKeyRelationship foreignKey;

   public GridEditorColumnConfig() {
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getWidth() {
      return width;
   }

   public void setWidth(int width) {
      this.width = width;
   }

   public int getType() {
      return type;
   }

   public void setType(int type) {
      this.type = type;
   }

   public boolean isEditable() {
      return editable;
   }

   public void setEditable(boolean editable) {
      this.editable = editable;
   }

   public boolean isSortable() {
      return sortable;
   }

   public void setSortable(boolean sortable) {
      this.sortable = sortable;
   }

   public boolean isFilterable() {
      return filterable;
   }

   public void setFilterable(boolean filterable) {
      this.filterable = filterable;
   }

   public boolean isEnforceCaseSensitivity() {
      return enforceCaseSensitivity;
   }

   public void setEnforceCaseSensitivity(boolean enforceCaseSensitivity) {
      this.enforceCaseSensitivity = enforceCaseSensitivity;
   }

   public boolean isDefaultCaseSensitive() {
      return defaultCaseSensitive;
   }

   public void setDefaultCaseSensitive(boolean defaultCaseSensitive) {
      this.defaultCaseSensitive = defaultCaseSensitive;
   }

   public String getDisplayName() {
      return displayName;
   }

   public void setDisplayName(String displayName) {
      this.displayName = displayName;
   }

   public List<Validator> getValidators() {
      return validators;
   }

   public void setValidators(List<Validator> validators) {
      if (null == validators)
         validators = new ArrayList<Validator>();
      this.validators = validators;
   }

   public void addValidator(Validator validator) {
      this.validators.add(validator);
   }

   public Editor getEditor() {
      return editor;
   }

   public void setEditor(Editor editor) {
      this.editor = editor;
   }

   public void setOrder(String order) {
      if (null == order)
         setOrder((Order) null);

      if ("asc".equals(order.toLowerCase()))
         setOrder(Order.ASC);
      else if ("desc".equals(order.toLowerCase()))
         setOrder(Order.DESC);
   }

   public Order getOrder() {
      return order;
   }

   public void setOrder(Order order) {
      this.order = order;
   }

   public boolean isHidden() {
      return hidden;
   }

   public void setHidden(Boolean hidden) {
      if (null == hidden)
         hidden = false;
      this.hidden = hidden;
   }

   public GridEditorRecordEntry getDefaultValue() {
      return defaultValue;
   }

   public void setDefaultValue(Integer value) {
      setDefaultValue(new GridEditorRecordEntry(SqlTypes.INTEGER, value));
   }

   public void setDefaultValue(String value) {
      setDefaultValue(new GridEditorRecordEntry(SqlTypes.VARCHAR, value));
   }

   public void setDefaultValue(Boolean value) {
      setDefaultValue(new GridEditorRecordEntry(SqlTypes.BOOLEAN, value));
   }

   public void setDefaultValue(Double value) {
      setDefaultValue(new GridEditorRecordEntry(SqlTypes.DOUBLE, value));
   }

   public void setDefaultValue(Float value) {
      setDefaultValue(new GridEditorRecordEntry(SqlTypes.FLOAT, value));
   }

   public void setDefaultValue(BigDecimal value) {
      setDefaultValue(new GridEditorRecordEntry(SqlTypes.DECIMAL, value));
   }

   public void setDefaultValue(Date value) {
      setDefaultValue(new GridEditorRecordEntry(SqlTypes.DATE, value));
   }

   public void setDefaultValue(Integer sqltype, Object value) {
      setDefaultValue(new GridEditorRecordEntry(sqltype, value));
   }

   public void setDefaultValue(GridEditorRecordEntry defaultValue) {
      this.defaultValue = defaultValue;
   }

   public Boolean isPrimaryKey() {
      return primaryKey;
   }

   public void setPrimaryKey(Boolean primaryKey) {
      this.primaryKey = primaryKey;
   }

   public void setForeignKey(GridEditorForeignKeyRelationship foreignKey) {
      setHidden(true);
      setEditable(false);
      this.foreignKey = foreignKey;
   }

   public void setForeignKey(String tableName, String fkColumn, String displayExpression, String displayName) {
      setForeignKey(new GridEditorForeignKeyRelationship(tableName, fkColumn, displayExpression, displayName));
   }

   public void setForeignKey(String tableName, String fkColumn, String displayExpression,
         GridEditorColumnConfig fkConfig) {
      setForeignKey(new GridEditorForeignKeyRelationship(tableName, fkColumn, displayExpression, fkConfig));
   }

   public GridEditorForeignKeyRelationship getForeignKey() {
      return foreignKey;
   }

}
