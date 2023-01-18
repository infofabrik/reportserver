package net.datenwerke.rs.base.client.reportengines.table.dto.decorator;

import net.datenwerke.gxtdto.client.dtomanager.IdedDto;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.base.client.reportengines.table.dto.AggregateFunctionDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterDto;

/**
 * Dto Decorator for {@link ColumnDto}
 *
 */
public class ColumnDtoDec extends ColumnDto implements IdedDto {

   private static final String DEFAULT_NULL_REPLACEMENT = "NULL";
   private static final long serialVersionUID = 1L;

   public ColumnDtoDec() {
      super();
      setNullReplacementFormat(DEFAULT_NULL_REPLACEMENT);
   }

   public void removeAllFilters() {
      setFilter(null);
      setNullHandling(null);
   }

   public void removeAllFormats() {
      setFormat(null);
      setNullReplacementFormat(DEFAULT_NULL_REPLACEMENT);
   }

   @Override
   public void setSubtotalGroup(Boolean subtotalGroup) {
      if (Boolean.TRUE.equals(subtotalGroup))
         setAggregateFunction(null);
      super.setSubtotalGroup(subtotalGroup);
   }

   @Override
   public Boolean isSubtotalGroup() {
      return Boolean.TRUE.equals(super.isSubtotalGroup());
   }

   @Override
   public void setAggregateFunction(AggregateFunctionDto aggregateFunction) {
      if (null != aggregateFunction)
         setSubtotalGroup(false);
      super.setAggregateFunction(aggregateFunction);
   }

   public boolean hasFilters() {
      if (null == getFilter())
         return false;

      FilterDto filter = getFilter();

      if (!(filter.getIncludeValues().size() == 0 && filter.getIncludeRanges().size() == 0
            && filter.getExcludeValues().size() == 0 && filter.getExcludeRanges().size() == 0)) {

         return true;
      }

      return false;
   }

   public ColumnDto cloneColumnForSelection() {
      ColumnDto clone = new ColumnDtoDec();

      copyPropertiesTo(clone);

      return clone;
   }

   public void copyPropertiesTo(ColumnDto clone) {
      clone.setName(this.getName());
      clone.setDescription(this.getDescription());
      clone.setDefaultAlias(this.getDefaultAlias());
      clone.setAlias(this.getAlias());
      clone.setHidden(this.isHidden());
      clone.setAggregateFunction(this.getAggregateFunction());
      clone.setNullHandling(this.getNullHandling());
      clone.setFilter(FilterDtoDec.clone(this.getFilter()));
      clone.setOrder(this.getOrder());
      clone.setSemanticType(this.getSemanticType());
      clone.setIndexColumn(this.isIndexColumn());
      clone.setType(this.getType());
      clone.setFormat(ColumnFormatDtoDec.clone(this.getFormat()));
      clone.setNullReplacementFormat(this.getNullReplacementFormat());
      clone.setSubtotalGroup(this.isSubtotalGroup());
      clone.setDimension(this.getDimension());
      clone.setDefaultPreviewWidth(this.getDefaultPreviewWidth());
      clone.setPreviewWidth(this.getPreviewWidth());
   }

   public boolean hasSomeFormat() {
      return null != getFormat() || !DEFAULT_NULL_REPLACEMENT.equals(getNullReplacementFormat());
   }

   @Override
   public String toDisplayTitle() {
      String name = getName();
      if (null == name)
         return BaseMessages.INSTANCE.unnamed();
      return name;
   }

}
