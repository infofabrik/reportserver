package net.datenwerke.rs.base.service.reportengines.table.entities;

import static java.util.stream.Collectors.toList;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.envers.Audited;

import com.google.common.base.MoreObjects;
import com.google.inject.Inject;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.utils.SqlTypes;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.Filter;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormat;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatCurrency;
import net.datenwerke.rs.base.service.reportengines.table.entities.post.ColumnDtoPost;
import net.datenwerke.rs.core.service.i18ntools.I18nToolsService;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.entitydiff.annotations.EntityDiffGuide;
import net.datenwerke.rs.utils.entitydiff.annotations.EntityDiffGuides;
import net.datenwerke.security.service.usermanager.entities.User;

@Entity
@Table(name = "COLUMN")
@Audited
@Inheritance(strategy = InheritanceType.JOINED)
@GenerateDto(dtoPackage = "net.datenwerke.rs.base.client.reportengines.table.dto", dto2PosoPostProcessors = ColumnDtoPost.class, poso2DtoPostProcessors = ColumnDtoPost.class, createDecorator = true)
@EntityDiffGuides(guides = {
      @EntityDiffGuide(name = Report.ENTITY_DIFF_IDENTITCAL_FOR_EXECUTION, ignoreId = true, ignoreVersion = true, blacklist = {
            "position" }) })
public class Column implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 4385836150734779462L;

   public interface CellFormatter {
      public String format(Object value);
   }

   public interface ColumnFormatCellFormatter extends CellFormatter {
      public ColumnFormat getColumnFormat();
   }

   public static final CellFormatter DUMMY_FORMATTER = new CellFormatter() {
      @Override
      public String format(Object value) {
         if (null == value)
            return "NULL";
         return String.valueOf(value);
      }
   };

   public enum OrderPrecedence {
      HIGH, NORMAL, LOW
   }

   @Inject
   protected static I18nToolsService i18nTools;

   @ExposeToClient(id = true)
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @Version
   private Long version;

   @ExposeToClient
   @EnclosedEntity
   @OneToOne(cascade = CascadeType.ALL)
   private Filter filter;

   /**
    * Stores the name of the column
    */
   @ExposeToClient(view = DtoView.MINIMAL)
   private String name;

   /**
    * Stores the column alias
    */
   @ExposeToClient
   private String alias;

   /**
    * Stores the column width
    */
   @ExposeToClient
   private Integer previewWidth;

   @Transient
   @ExposeToClient
   private String defaultAlias;

   @Transient
   @ExposeToClient
   private String defaultPreviewWidth;

   @Transient
   @ExposeToClient
   private String semanticType;

   @Transient
   @ExposeToClient
   private boolean indexColumn;

   @ExposeToClient
   private AggregateFunction aggregateFunction;

   @ExposeToClient
   @EnclosedEntity
   @OneToOne(cascade = CascadeType.ALL)
   private ColumnFormat format;

   @Transient
   @ExposeToClient(view = DtoView.MINIMAL)
   private String description;

   private int position;

   /**
    * Stores the visibility of the column
    */
   @ExposeToClient
   private Boolean hidden = false;

   /**
    * Stores the datattype of this columns. See {@link java.sql.Types}
    */
   @ExposeToClient(view = DtoView.MINIMAL)
   private Integer type;

   @ExposeToClient
   private Order order;

   @ExposeToClient
   private NullHandling nullHandling;

   @ExposeToClient
   private String nullReplacementFormat = "NULL";
   
   @ExposeToClient
   private boolean exportNullAsString = false; // per default, export NULLs as real NULLs

   @ExposeToClient
   private String dimension;

   @ExposeToClient
   private Boolean subtotalGroup = false;

   @Transient
   private String likeFilter;

   @Transient
   private OrderPrecedence orderPrecedence = OrderPrecedence.NORMAL;

   /**
    * Use B!oolean: null means do not care
    */
   @Transient
   private Boolean groupedBy = null;

   @Transient
   @TransientID
   private Long transientId;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getVersion() {
      return version;
   }

   public void setVersion(Long version) {
      this.version = version;
   }

   public void setTransientId(Long transientId) {
      this.transientId = transientId;
   }

   public Long getTransientId() {
      return transientId;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getNullReplacementFormat() {
      // Oracle... ""==NULL
      if (null == nullReplacementFormat)
         return "";
      return nullReplacementFormat;
   }

   public void setNullReplacementFormat(String nullReplacementFormat) {
      this.nullReplacementFormat = nullReplacementFormat;
   }

   public int getPosition() {
      return position;
   }

   public void setPosition(int position) {
      this.position = position;
   }

   public Boolean isHidden() {
      return hidden;
   }

   public void setHidden(Boolean hidden) {
      if (null == hidden)
         hidden = false;
      this.hidden = hidden;
   }

   public Integer getType() {
      return type;
   }

   public void setType(Integer type) {
      this.type = type;
   }

   public String getAlias() {
      return alias;
   }

   public void setAlias(String alias) {
      this.alias = alias;
   }

   public Integer getPreviewWidth() {
      return previewWidth;
   }

   public void setPreviewWidth(Integer previewWidth) {
      this.previewWidth = previewWidth;
   }

   public String getDefaultAlias() {
      return defaultAlias;
   }

   public void setDefaultAlias(String defaultAlias) {
      this.defaultAlias = defaultAlias;
   }

   public String getDefaultPreviewWidth() {
      return defaultPreviewWidth;
   }

   public void setDefaultPreviewWidth(String defaultPreviewWidth) {
      this.defaultPreviewWidth = defaultPreviewWidth;
   }

   public AggregateFunction getAggregateFunction() {
      return aggregateFunction;
   }

   public void setAggregateFunction(AggregateFunction aggregateFunction) {
      this.aggregateFunction = aggregateFunction;
   }

   public Filter getFilter() {
      return filter;
   }

   public void setFilter(Filter filter) {
      this.filter = filter;
   }

   public Order getOrder() {
      return order;
   }

   public void setOrder(Order order) {
      this.order = order;
   }

   public String getLikeFilter() {
      return likeFilter;
   }

   public void setLikeFilter(String likeFilter) {
      this.likeFilter = likeFilter;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getDimension() {
      return dimension;
   }

   public void setDimension(String dimension) {
      this.dimension = dimension;
   }

   public Boolean isGroupedBy() {
      if (null == groupedBy)
         return (null == aggregateFunction);
      else
         return groupedBy;
   }

   public void setGroupedBy(Boolean groupedBy) {
      this.groupedBy = groupedBy;
   }

   public NullHandling getNullHandling() {
      return nullHandling;
   }

   public void setNullHandling(NullHandling nullHandling) {
      this.nullHandling = nullHandling;
   }

   @Override
   public int hashCode() {
      if (null != getId())
         return getId().hashCode();
      return super.hashCode();
   }

   @Override
   public boolean equals(Object obj) {
      if (!(obj instanceof Column))
         return false;

      if (null != getId())
         return getId().equals(((Column) obj).getId());

      return super.equals(obj);
   }

   @Override
   public String toString() {
      return MoreObjects.toStringHelper(getClass())
            .add("id", id)
            .add("name", name)
            .toString();
   }

   public void setSemanticType(String semanticType) {
      this.semanticType = semanticType;
   }

   public String getSemanticType() {
      return semanticType;
   }

   public void setFormat(ColumnFormat format) {
      this.format = format;
   }

   public ColumnFormat getFormat() {
      return format;
   }

   public CellFormatter getCellFormatterForGroupRow(final User user) {
      if (format instanceof ColumnFormatCurrency && (AggregateFunction.COUNT == getAggregateFunction()
            || AggregateFunction.COUNT_DISTINCT == getAggregateFunction()
            || AggregateFunction.VARIANCE == getAggregateFunction())) {
         return new CellFormatter() {
            @Override
            public String format(Object value) {
               if (null == value)
                  return getNullReplacementFormat();
               return i18nTools.translateNumberFromSystemToUser(String.valueOf(value), user);
            }

         };
      }
      return getCellFormatter(user);
   }

   public CellFormatter getCellFormatter(final User user) {
      if (!isFormatted()) {
         if (null != getType() && SqlTypes.isNumerical(getType()) && null != user) {
            return new CellFormatter() {
               @Override
               public String format(Object value) {
                  if (null == value)
                     return getNullReplacementFormat();
                  return i18nTools.translateNumberFromSystemToUser(String.valueOf(value), user);
               }

            };
         } else {
            return new CellFormatter() {
               @Override
               public String format(Object value) {
                  if (null == value)
                     return getNullReplacementFormat();
                  return String.valueOf(value);
               }

            };
         }
      }

      return new ColumnFormatCellFormatter() {

         @Override
         public String format(Object value) {
            if (null == value)
               return getNullReplacementFormat();
            return getFormat().format(value);
         }

         @Override
         public ColumnFormat getColumnFormat() {
            return getFormat();
         }
      };
   }

   public boolean isFormatted() {
      return null != getFormat();
   }

   public void setSubtotalGroup(Boolean subtotalGroup) {
      if (null == subtotalGroup)
         subtotalGroup = false;
      this.subtotalGroup = subtotalGroup;
   }

   public Boolean isSubtotalGroup() {
      return null == subtotalGroup ? false : subtotalGroup;
   }
   
   public void setExportNullAsString(boolean exportNullAsString) {
      this.exportNullAsString = exportNullAsString;
   }

   public boolean isExportNullAsString() {
      return exportNullAsString;
   }

   public void setOrderPrecedence(OrderPrecedence orderPrecedence) {
      this.orderPrecedence = orderPrecedence;
   }

   public OrderPrecedence getOrderPrecedence() {
      return orderPrecedence;
   }

   public boolean isIndexColumn() {
      return indexColumn;
   }

   public void setIndexColumn(boolean indexColumn) {
      this.indexColumn = indexColumn;
   }

   /**
    * Take care of order and check that parent is set
    */
   @SuppressWarnings("unused")
   @PrePersist
   @PreUpdate
   final private void prePersist() {
      if (null != getType() && SqlTypes.isNumerical(getType())) {
         Filter filter = getFilter();
         if (null != filter) {
            try {
               filter.verifyNumberFormat();
            } catch (ParseException e) {
               throw new IllegalStateException(e);
            }
         }
      }
   }

   public boolean hasFilters() {
      Filter filter = getFilter();

      if (null != filter && !(filter.getIncludeValues().size() == 0 && filter.getIncludeRanges().size() == 0
            && filter.getExcludeValues().size() == 0 && filter.getExcludeRanges().size() == 0)) {
         return true;
      }

      if (null != getLikeFilter() && getLikeFilter().length() > 0)
         return true;

      if (null != getNullHandling())
         return true;

      return false;
   }

   public Map<String, Object> getFilterAsMap() {
      if (!hasFilters())
         return Collections.emptyMap();

      Map<String, Object> asMap = new HashMap<>();

      // include filters
      if (!getFilter().getIncludeValues().isEmpty())
         asMap.put("include", getFilter().getIncludeValues());

      // include range
      if (!getFilter().getIncludeRanges().isEmpty()) {
         Object val = getFilter().getIncludeRanges().stream()
               .map(filterRange -> Arrays.asList(filterRange.getRangeFrom(), filterRange.getRangeTo()))
               .collect(toList());
         asMap.put("include_range", val);
      }

      // exclude filters
      if (!getFilter().getExcludeValues().isEmpty())
         asMap.put("exclude", getFilter().getExcludeValues());

      // exclude range
      if (!getFilter().getExcludeRanges().isEmpty()) {
         Object val = getFilter().getExcludeRanges().stream()
               .map(filterRange -> Arrays.asList(filterRange.getRangeFrom(), filterRange.getRangeTo()))
               .collect(toList());
         asMap.put("exclude_range", val);
      }

      // null handling
      String nullHandlingKey = "null_handling";
      if (null == getNullHandling())
         asMap.put(nullHandlingKey, "--");
      else
         asMap.put(nullHandlingKey, getNullHandling());

      // case sensitive
      asMap.put("case_sensitive", getFilter().isCaseSensitive());

      return asMap;
   }

}
