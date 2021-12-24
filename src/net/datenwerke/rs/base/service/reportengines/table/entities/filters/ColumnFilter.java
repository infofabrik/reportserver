package net.datenwerke.rs.base.service.reportengines.table.entities.filters;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;

@Entity
@Table(name = "COLUMN_FILTER")
@Audited
@GenerateDto(dtoPackage = "net.datenwerke.rs.base.client.reportengines.table.dto", createDecorator = true)
public class ColumnFilter extends FilterSpec {

   /**
    * 
    */
   private static final long serialVersionUID = -8779925486341041400L;

   @EnclosedEntity
   @ExposeToClient
   @OneToOne(cascade = CascadeType.ALL)
   private Column column;

   public Column getColumn() {
      return column;
   }

   public void setColumn(Column column) {
      this.column = column;
   }

   @Override
   public Collection<Column> getColumns() {
      if (null == column)
         return Collections.emptyList();
      
      return Arrays.asList(column);
   }

   @Override
   public Map<String, Object> asMap() {
      if (null == column)
         return Collections.emptyMap();
      
      Map<String, Object> asMap = new HashMap<>();
      asMap.put(column.getName(), column.getFilterAsMap());
      return asMap;
   }
}
