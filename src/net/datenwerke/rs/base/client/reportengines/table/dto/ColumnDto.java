package net.datenwerke.rs.base.client.reportengines.table.dto;

import com.google.gwt.core.client.GWT;
import java.lang.Boolean;
import java.lang.IllegalStateException;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.IdedDto;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;
import net.datenwerke.rs.base.client.reportengines.table.dto.AggregateFunctionDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.NullHandlingDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.OrderDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.ColumnDtoPA;
import net.datenwerke.rs.base.client.reportengines.table.dto.posomap.ColumnDto2PosoMap;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;

/**
 * Dto for {@link Column}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class ColumnDto extends RsDto implements IdedDto {


	private static final long serialVersionUID = 1;

	private Long dtoId;


	/* Fields */
	private AggregateFunctionDto aggregateFunction;
	private  boolean aggregateFunction_m;
	public static final String PROPERTY_AGGREGATE_FUNCTION = "dpi-column-aggregatefunction";

	private transient static PropertyAccessor<ColumnDto, AggregateFunctionDto> aggregateFunction_pa = new PropertyAccessor<ColumnDto, AggregateFunctionDto>() {
		@Override
		public void setValue(ColumnDto container, AggregateFunctionDto object) {
			container.setAggregateFunction(object);
		}

		@Override
		public AggregateFunctionDto getValue(ColumnDto container) {
			return container.getAggregateFunction();
		}

		@Override
		public Class<?> getType() {
			return AggregateFunctionDto.class;
		}

		@Override
		public String getPath() {
			return "aggregateFunction";
		}

		@Override
		public void setModified(ColumnDto container, boolean modified) {
			container.aggregateFunction_m = modified;
		}

		@Override
		public boolean isModified(ColumnDto container) {
			return container.isAggregateFunctionModified();
		}
	};

	private String alias;
	private  boolean alias_m;
	public static final String PROPERTY_ALIAS = "dpi-column-alias";

	private transient static PropertyAccessor<ColumnDto, String> alias_pa = new PropertyAccessor<ColumnDto, String>() {
		@Override
		public void setValue(ColumnDto container, String object) {
			container.setAlias(object);
		}

		@Override
		public String getValue(ColumnDto container) {
			return container.getAlias();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "alias";
		}

		@Override
		public void setModified(ColumnDto container, boolean modified) {
			container.alias_m = modified;
		}

		@Override
		public boolean isModified(ColumnDto container) {
			return container.isAliasModified();
		}
	};

	private String defaultAlias;
	private  boolean defaultAlias_m;
	public static final String PROPERTY_DEFAULT_ALIAS = "dpi-column-defaultalias";

	private transient static PropertyAccessor<ColumnDto, String> defaultAlias_pa = new PropertyAccessor<ColumnDto, String>() {
		@Override
		public void setValue(ColumnDto container, String object) {
			container.setDefaultAlias(object);
		}

		@Override
		public String getValue(ColumnDto container) {
			return container.getDefaultAlias();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "defaultAlias";
		}

		@Override
		public void setModified(ColumnDto container, boolean modified) {
			container.defaultAlias_m = modified;
		}

		@Override
		public boolean isModified(ColumnDto container) {
			return container.isDefaultAliasModified();
		}
	};

	private String defaultPreviewWidth;
	private  boolean defaultPreviewWidth_m;
	public static final String PROPERTY_DEFAULT_PREVIEW_WIDTH = "dpi-column-defaultpreviewwidth";

	private transient static PropertyAccessor<ColumnDto, String> defaultPreviewWidth_pa = new PropertyAccessor<ColumnDto, String>() {
		@Override
		public void setValue(ColumnDto container, String object) {
			container.setDefaultPreviewWidth(object);
		}

		@Override
		public String getValue(ColumnDto container) {
			return container.getDefaultPreviewWidth();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "defaultPreviewWidth";
		}

		@Override
		public void setModified(ColumnDto container, boolean modified) {
			container.defaultPreviewWidth_m = modified;
		}

		@Override
		public boolean isModified(ColumnDto container) {
			return container.isDefaultPreviewWidthModified();
		}
	};

	private String description;
	private  boolean description_m;
	public static final String PROPERTY_DESCRIPTION = "dpi-column-description";

	private transient static PropertyAccessor<ColumnDto, String> description_pa = new PropertyAccessor<ColumnDto, String>() {
		@Override
		public void setValue(ColumnDto container, String object) {
			container.setDescription(object);
		}

		@Override
		public String getValue(ColumnDto container) {
			return container.getDescription();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "description";
		}

		@Override
		public void setModified(ColumnDto container, boolean modified) {
			container.description_m = modified;
		}

		@Override
		public boolean isModified(ColumnDto container) {
			return container.isDescriptionModified();
		}
	};

	private String dimension;
	private  boolean dimension_m;
	public static final String PROPERTY_DIMENSION = "dpi-column-dimension";

	private transient static PropertyAccessor<ColumnDto, String> dimension_pa = new PropertyAccessor<ColumnDto, String>() {
		@Override
		public void setValue(ColumnDto container, String object) {
			container.setDimension(object);
		}

		@Override
		public String getValue(ColumnDto container) {
			return container.getDimension();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "dimension";
		}

		@Override
		public void setModified(ColumnDto container, boolean modified) {
			container.dimension_m = modified;
		}

		@Override
		public boolean isModified(ColumnDto container) {
			return container.isDimensionModified();
		}
	};

	private boolean exportNullAsString;
	private  boolean exportNullAsString_m;
	public static final String PROPERTY_EXPORT_NULL_AS_STRING = "dpi-column-exportnullasstring";

	private transient static PropertyAccessor<ColumnDto, Boolean> exportNullAsString_pa = new PropertyAccessor<ColumnDto, Boolean>() {
		@Override
		public void setValue(ColumnDto container, Boolean object) {
			container.setExportNullAsString(object);
		}

		@Override
		public Boolean getValue(ColumnDto container) {
			return container.isExportNullAsString();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "exportNullAsString";
		}

		@Override
		public void setModified(ColumnDto container, boolean modified) {
			container.exportNullAsString_m = modified;
		}

		@Override
		public boolean isModified(ColumnDto container) {
			return container.isExportNullAsStringModified();
		}
	};

	private FilterDto filter;
	private  boolean filter_m;
	public static final String PROPERTY_FILTER = "dpi-column-filter";

	private transient static PropertyAccessor<ColumnDto, FilterDto> filter_pa = new PropertyAccessor<ColumnDto, FilterDto>() {
		@Override
		public void setValue(ColumnDto container, FilterDto object) {
			container.setFilter(object);
		}

		@Override
		public FilterDto getValue(ColumnDto container) {
			return container.getFilter();
		}

		@Override
		public Class<?> getType() {
			return FilterDto.class;
		}

		@Override
		public String getPath() {
			return "filter";
		}

		@Override
		public void setModified(ColumnDto container, boolean modified) {
			container.filter_m = modified;
		}

		@Override
		public boolean isModified(ColumnDto container) {
			return container.isFilterModified();
		}
	};

	private ColumnFormatDto format;
	private  boolean format_m;
	public static final String PROPERTY_FORMAT = "dpi-column-format";

	private transient static PropertyAccessor<ColumnDto, ColumnFormatDto> format_pa = new PropertyAccessor<ColumnDto, ColumnFormatDto>() {
		@Override
		public void setValue(ColumnDto container, ColumnFormatDto object) {
			container.setFormat(object);
		}

		@Override
		public ColumnFormatDto getValue(ColumnDto container) {
			return container.getFormat();
		}

		@Override
		public Class<?> getType() {
			return ColumnFormatDto.class;
		}

		@Override
		public String getPath() {
			return "format";
		}

		@Override
		public void setModified(ColumnDto container, boolean modified) {
			container.format_m = modified;
		}

		@Override
		public boolean isModified(ColumnDto container) {
			return container.isFormatModified();
		}
	};

	private Boolean hidden;
	private  boolean hidden_m;
	public static final String PROPERTY_HIDDEN = "dpi-column-hidden";

	private transient static PropertyAccessor<ColumnDto, Boolean> hidden_pa = new PropertyAccessor<ColumnDto, Boolean>() {
		@Override
		public void setValue(ColumnDto container, Boolean object) {
			container.setHidden(object);
		}

		@Override
		public Boolean getValue(ColumnDto container) {
			return container.isHidden();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "hidden";
		}

		@Override
		public void setModified(ColumnDto container, boolean modified) {
			container.hidden_m = modified;
		}

		@Override
		public boolean isModified(ColumnDto container) {
			return container.isHiddenModified();
		}
	};

	private Long id;
	private  boolean id_m;
	public static final String PROPERTY_ID = "dpi-column-id";

	private transient static PropertyAccessor<ColumnDto, Long> id_pa = new PropertyAccessor<ColumnDto, Long>() {
		@Override
		public void setValue(ColumnDto container, Long object) {
			// id field
		}

		@Override
		public Long getValue(ColumnDto container) {
			return container.getId();
		}

		@Override
		public Class<?> getType() {
			return Long.class;
		}

		@Override
		public String getPath() {
			return "id";
		}

		@Override
		public void setModified(ColumnDto container, boolean modified) {
			container.id_m = modified;
		}

		@Override
		public boolean isModified(ColumnDto container) {
			return container.isIdModified();
		}
	};

	private boolean indexColumn;
	private  boolean indexColumn_m;
	public static final String PROPERTY_INDEX_COLUMN = "dpi-column-indexcolumn";

	private transient static PropertyAccessor<ColumnDto, Boolean> indexColumn_pa = new PropertyAccessor<ColumnDto, Boolean>() {
		@Override
		public void setValue(ColumnDto container, Boolean object) {
			container.setIndexColumn(object);
		}

		@Override
		public Boolean getValue(ColumnDto container) {
			return container.isIndexColumn();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "indexColumn";
		}

		@Override
		public void setModified(ColumnDto container, boolean modified) {
			container.indexColumn_m = modified;
		}

		@Override
		public boolean isModified(ColumnDto container) {
			return container.isIndexColumnModified();
		}
	};

	private String name;
	private  boolean name_m;
	public static final String PROPERTY_NAME = "dpi-column-name";

	private transient static PropertyAccessor<ColumnDto, String> name_pa = new PropertyAccessor<ColumnDto, String>() {
		@Override
		public void setValue(ColumnDto container, String object) {
			container.setName(object);
		}

		@Override
		public String getValue(ColumnDto container) {
			return container.getName();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "name";
		}

		@Override
		public void setModified(ColumnDto container, boolean modified) {
			container.name_m = modified;
		}

		@Override
		public boolean isModified(ColumnDto container) {
			return container.isNameModified();
		}
	};

	private NullHandlingDto nullHandling;
	private  boolean nullHandling_m;
	public static final String PROPERTY_NULL_HANDLING = "dpi-column-nullhandling";

	private transient static PropertyAccessor<ColumnDto, NullHandlingDto> nullHandling_pa = new PropertyAccessor<ColumnDto, NullHandlingDto>() {
		@Override
		public void setValue(ColumnDto container, NullHandlingDto object) {
			container.setNullHandling(object);
		}

		@Override
		public NullHandlingDto getValue(ColumnDto container) {
			return container.getNullHandling();
		}

		@Override
		public Class<?> getType() {
			return NullHandlingDto.class;
		}

		@Override
		public String getPath() {
			return "nullHandling";
		}

		@Override
		public void setModified(ColumnDto container, boolean modified) {
			container.nullHandling_m = modified;
		}

		@Override
		public boolean isModified(ColumnDto container) {
			return container.isNullHandlingModified();
		}
	};

	private String nullReplacementFormat;
	private  boolean nullReplacementFormat_m;
	public static final String PROPERTY_NULL_REPLACEMENT_FORMAT = "dpi-column-nullreplacementformat";

	private transient static PropertyAccessor<ColumnDto, String> nullReplacementFormat_pa = new PropertyAccessor<ColumnDto, String>() {
		@Override
		public void setValue(ColumnDto container, String object) {
			container.setNullReplacementFormat(object);
		}

		@Override
		public String getValue(ColumnDto container) {
			return container.getNullReplacementFormat();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "nullReplacementFormat";
		}

		@Override
		public void setModified(ColumnDto container, boolean modified) {
			container.nullReplacementFormat_m = modified;
		}

		@Override
		public boolean isModified(ColumnDto container) {
			return container.isNullReplacementFormatModified();
		}
	};

	private OrderDto order;
	private  boolean order_m;
	public static final String PROPERTY_ORDER = "dpi-column-order";

	private transient static PropertyAccessor<ColumnDto, OrderDto> order_pa = new PropertyAccessor<ColumnDto, OrderDto>() {
		@Override
		public void setValue(ColumnDto container, OrderDto object) {
			container.setOrder(object);
		}

		@Override
		public OrderDto getValue(ColumnDto container) {
			return container.getOrder();
		}

		@Override
		public Class<?> getType() {
			return OrderDto.class;
		}

		@Override
		public String getPath() {
			return "order";
		}

		@Override
		public void setModified(ColumnDto container, boolean modified) {
			container.order_m = modified;
		}

		@Override
		public boolean isModified(ColumnDto container) {
			return container.isOrderModified();
		}
	};

	private Integer previewWidth;
	private  boolean previewWidth_m;
	public static final String PROPERTY_PREVIEW_WIDTH = "dpi-column-previewwidth";

	private transient static PropertyAccessor<ColumnDto, Integer> previewWidth_pa = new PropertyAccessor<ColumnDto, Integer>() {
		@Override
		public void setValue(ColumnDto container, Integer object) {
			container.setPreviewWidth(object);
		}

		@Override
		public Integer getValue(ColumnDto container) {
			return container.getPreviewWidth();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "previewWidth";
		}

		@Override
		public void setModified(ColumnDto container, boolean modified) {
			container.previewWidth_m = modified;
		}

		@Override
		public boolean isModified(ColumnDto container) {
			return container.isPreviewWidthModified();
		}
	};

	private String semanticType;
	private  boolean semanticType_m;
	public static final String PROPERTY_SEMANTIC_TYPE = "dpi-column-semantictype";

	private transient static PropertyAccessor<ColumnDto, String> semanticType_pa = new PropertyAccessor<ColumnDto, String>() {
		@Override
		public void setValue(ColumnDto container, String object) {
			container.setSemanticType(object);
		}

		@Override
		public String getValue(ColumnDto container) {
			return container.getSemanticType();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "semanticType";
		}

		@Override
		public void setModified(ColumnDto container, boolean modified) {
			container.semanticType_m = modified;
		}

		@Override
		public boolean isModified(ColumnDto container) {
			return container.isSemanticTypeModified();
		}
	};

	private Boolean subtotalGroup;
	private  boolean subtotalGroup_m;
	public static final String PROPERTY_SUBTOTAL_GROUP = "dpi-column-subtotalgroup";

	private transient static PropertyAccessor<ColumnDto, Boolean> subtotalGroup_pa = new PropertyAccessor<ColumnDto, Boolean>() {
		@Override
		public void setValue(ColumnDto container, Boolean object) {
			container.setSubtotalGroup(object);
		}

		@Override
		public Boolean getValue(ColumnDto container) {
			return container.isSubtotalGroup();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "subtotalGroup";
		}

		@Override
		public void setModified(ColumnDto container, boolean modified) {
			container.subtotalGroup_m = modified;
		}

		@Override
		public boolean isModified(ColumnDto container) {
			return container.isSubtotalGroupModified();
		}
	};

	private Integer type;
	private  boolean type_m;
	public static final String PROPERTY_TYPE = "dpi-column-type";

	private transient static PropertyAccessor<ColumnDto, Integer> type_pa = new PropertyAccessor<ColumnDto, Integer>() {
		@Override
		public void setValue(ColumnDto container, Integer object) {
			container.setType(object);
		}

		@Override
		public Integer getValue(ColumnDto container) {
			return container.getType();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "type";
		}

		@Override
		public void setModified(ColumnDto container, boolean modified) {
			container.type_m = modified;
		}

		@Override
		public boolean isModified(ColumnDto container) {
			return container.isTypeModified();
		}
	};


	public ColumnDto() {
		super();
	}

	public AggregateFunctionDto getAggregateFunction()  {
		if(! isDtoProxy()){
			return this.aggregateFunction;
		}

		if(isAggregateFunctionModified())
			return this.aggregateFunction;

		if(! GWT.isClient())
			return null;

		AggregateFunctionDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().aggregateFunction());

		return _value;
	}


	public void setAggregateFunction(AggregateFunctionDto aggregateFunction)  {
		/* old value */
		AggregateFunctionDto oldValue = null;
		if(GWT.isClient())
			oldValue = getAggregateFunction();

		/* set new value */
		this.aggregateFunction = aggregateFunction;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(aggregateFunction_pa, oldValue, aggregateFunction, this.aggregateFunction_m));

		/* set indicator */
		this.aggregateFunction_m = true;

		this.fireObjectChangedEvent(ColumnDtoPA.INSTANCE.aggregateFunction(), oldValue);
	}


	public boolean isAggregateFunctionModified()  {
		return aggregateFunction_m;
	}


	public static PropertyAccessor<ColumnDto, AggregateFunctionDto> getAggregateFunctionPropertyAccessor()  {
		return aggregateFunction_pa;
	}


	public String getAlias()  {
		if(! isDtoProxy()){
			return this.alias;
		}

		if(isAliasModified())
			return this.alias;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().alias());

		return _value;
	}


	public void setAlias(String alias)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getAlias();

		/* set new value */
		this.alias = alias;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(alias_pa, oldValue, alias, this.alias_m));

		/* set indicator */
		this.alias_m = true;

		this.fireObjectChangedEvent(ColumnDtoPA.INSTANCE.alias(), oldValue);
	}


	public boolean isAliasModified()  {
		return alias_m;
	}


	public static PropertyAccessor<ColumnDto, String> getAliasPropertyAccessor()  {
		return alias_pa;
	}


	public String getDefaultAlias()  {
		if(! isDtoProxy()){
			return this.defaultAlias;
		}

		if(isDefaultAliasModified())
			return this.defaultAlias;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().defaultAlias());

		return _value;
	}


	public void setDefaultAlias(String defaultAlias)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getDefaultAlias();

		/* set new value */
		this.defaultAlias = defaultAlias;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(defaultAlias_pa, oldValue, defaultAlias, this.defaultAlias_m));

		/* set indicator */
		this.defaultAlias_m = true;

		this.fireObjectChangedEvent(ColumnDtoPA.INSTANCE.defaultAlias(), oldValue);
	}


	public boolean isDefaultAliasModified()  {
		return defaultAlias_m;
	}


	public static PropertyAccessor<ColumnDto, String> getDefaultAliasPropertyAccessor()  {
		return defaultAlias_pa;
	}


	public String getDefaultPreviewWidth()  {
		if(! isDtoProxy()){
			return this.defaultPreviewWidth;
		}

		if(isDefaultPreviewWidthModified())
			return this.defaultPreviewWidth;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().defaultPreviewWidth());

		return _value;
	}


	public void setDefaultPreviewWidth(String defaultPreviewWidth)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getDefaultPreviewWidth();

		/* set new value */
		this.defaultPreviewWidth = defaultPreviewWidth;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(defaultPreviewWidth_pa, oldValue, defaultPreviewWidth, this.defaultPreviewWidth_m));

		/* set indicator */
		this.defaultPreviewWidth_m = true;

		this.fireObjectChangedEvent(ColumnDtoPA.INSTANCE.defaultPreviewWidth(), oldValue);
	}


	public boolean isDefaultPreviewWidthModified()  {
		return defaultPreviewWidth_m;
	}


	public static PropertyAccessor<ColumnDto, String> getDefaultPreviewWidthPropertyAccessor()  {
		return defaultPreviewWidth_pa;
	}


	public String getDescription()  {
		if(! isDtoProxy()){
			return this.description;
		}

		if(isDescriptionModified())
			return this.description;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().description());

		return _value;
	}


	public void setDescription(String description)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getDescription();

		/* set new value */
		this.description = description;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(description_pa, oldValue, description, this.description_m));

		/* set indicator */
		this.description_m = true;

		this.fireObjectChangedEvent(ColumnDtoPA.INSTANCE.description(), oldValue);
	}


	public boolean isDescriptionModified()  {
		return description_m;
	}


	public static PropertyAccessor<ColumnDto, String> getDescriptionPropertyAccessor()  {
		return description_pa;
	}


	public String getDimension()  {
		if(! isDtoProxy()){
			return this.dimension;
		}

		if(isDimensionModified())
			return this.dimension;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().dimension());

		return _value;
	}


	public void setDimension(String dimension)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getDimension();

		/* set new value */
		this.dimension = dimension;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(dimension_pa, oldValue, dimension, this.dimension_m));

		/* set indicator */
		this.dimension_m = true;

		this.fireObjectChangedEvent(ColumnDtoPA.INSTANCE.dimension(), oldValue);
	}


	public boolean isDimensionModified()  {
		return dimension_m;
	}


	public static PropertyAccessor<ColumnDto, String> getDimensionPropertyAccessor()  {
		return dimension_pa;
	}


	public boolean isExportNullAsString()  {
		if(! isDtoProxy()){
			return this.exportNullAsString;
		}

		if(isExportNullAsStringModified())
			return this.exportNullAsString;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().exportNullAsString());

		return _value;
	}


	public void setExportNullAsString(boolean exportNullAsString)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isExportNullAsString();

		/* set new value */
		this.exportNullAsString = exportNullAsString;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(exportNullAsString_pa, oldValue, exportNullAsString, this.exportNullAsString_m));

		/* set indicator */
		this.exportNullAsString_m = true;

		this.fireObjectChangedEvent(ColumnDtoPA.INSTANCE.exportNullAsString(), oldValue);
	}


	public boolean isExportNullAsStringModified()  {
		return exportNullAsString_m;
	}


	public static PropertyAccessor<ColumnDto, Boolean> getExportNullAsStringPropertyAccessor()  {
		return exportNullAsString_pa;
	}


	public FilterDto getFilter()  {
		if(! isDtoProxy()){
			return this.filter;
		}

		if(isFilterModified())
			return this.filter;

		if(! GWT.isClient())
			return null;

		FilterDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().filter());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isFilterModified())
						setFilter((FilterDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setFilter(FilterDto filter)  {
		/* old value */
		FilterDto oldValue = null;
		if(GWT.isClient())
			oldValue = getFilter();

		/* set new value */
		this.filter = filter;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(filter_pa, oldValue, filter, this.filter_m));

		/* set indicator */
		this.filter_m = true;

		this.fireObjectChangedEvent(ColumnDtoPA.INSTANCE.filter(), oldValue);
	}


	public boolean isFilterModified()  {
		return filter_m;
	}


	public static PropertyAccessor<ColumnDto, FilterDto> getFilterPropertyAccessor()  {
		return filter_pa;
	}


	public ColumnFormatDto getFormat()  {
		if(! isDtoProxy()){
			return this.format;
		}

		if(isFormatModified())
			return this.format;

		if(! GWT.isClient())
			return null;

		ColumnFormatDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().format());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isFormatModified())
						setFormat((ColumnFormatDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setFormat(ColumnFormatDto format)  {
		/* old value */
		ColumnFormatDto oldValue = null;
		if(GWT.isClient())
			oldValue = getFormat();

		/* set new value */
		this.format = format;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(format_pa, oldValue, format, this.format_m));

		/* set indicator */
		this.format_m = true;

		this.fireObjectChangedEvent(ColumnDtoPA.INSTANCE.format(), oldValue);
	}


	public boolean isFormatModified()  {
		return format_m;
	}


	public static PropertyAccessor<ColumnDto, ColumnFormatDto> getFormatPropertyAccessor()  {
		return format_pa;
	}


	public Boolean isHidden()  {
		if(! isDtoProxy()){
			return this.hidden;
		}

		if(isHiddenModified())
			return this.hidden;

		if(! GWT.isClient())
			return null;

		Boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().hidden());

		return _value;
	}


	public void setHidden(Boolean hidden)  {
		/* old value */
		Boolean oldValue = null;
		if(GWT.isClient())
			oldValue = isHidden();

		/* set new value */
		this.hidden = hidden;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(hidden_pa, oldValue, hidden, this.hidden_m));

		/* set indicator */
		this.hidden_m = true;

		this.fireObjectChangedEvent(ColumnDtoPA.INSTANCE.hidden(), oldValue);
	}


	public boolean isHiddenModified()  {
		return hidden_m;
	}


	public static PropertyAccessor<ColumnDto, Boolean> getHiddenPropertyAccessor()  {
		return hidden_pa;
	}


	public final Long getId()  {
		return dtoId;
	}

	public final void setId(Long id)  {
		if (null != dtoId)
			throw new IllegalStateException("Id already set!");
		this.dtoId = id;
	}

	public boolean isIdModified()  {
		return id_m;
	}


	public static PropertyAccessor<ColumnDto, Long> getIdPropertyAccessor()  {
		return id_pa;
	}


	public boolean isIndexColumn()  {
		if(! isDtoProxy()){
			return this.indexColumn;
		}

		if(isIndexColumnModified())
			return this.indexColumn;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().indexColumn());

		return _value;
	}


	public void setIndexColumn(boolean indexColumn)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isIndexColumn();

		/* set new value */
		this.indexColumn = indexColumn;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(indexColumn_pa, oldValue, indexColumn, this.indexColumn_m));

		/* set indicator */
		this.indexColumn_m = true;

		this.fireObjectChangedEvent(ColumnDtoPA.INSTANCE.indexColumn(), oldValue);
	}


	public boolean isIndexColumnModified()  {
		return indexColumn_m;
	}


	public static PropertyAccessor<ColumnDto, Boolean> getIndexColumnPropertyAccessor()  {
		return indexColumn_pa;
	}


	public String getName()  {
		if(! isDtoProxy()){
			return this.name;
		}

		if(isNameModified())
			return this.name;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().name());

		return _value;
	}


	public void setName(String name)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getName();

		/* set new value */
		this.name = name;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(name_pa, oldValue, name, this.name_m));

		/* set indicator */
		this.name_m = true;

		this.fireObjectChangedEvent(ColumnDtoPA.INSTANCE.name(), oldValue);
	}


	public boolean isNameModified()  {
		return name_m;
	}


	public static PropertyAccessor<ColumnDto, String> getNamePropertyAccessor()  {
		return name_pa;
	}


	public NullHandlingDto getNullHandling()  {
		if(! isDtoProxy()){
			return this.nullHandling;
		}

		if(isNullHandlingModified())
			return this.nullHandling;

		if(! GWT.isClient())
			return null;

		NullHandlingDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().nullHandling());

		return _value;
	}


	public void setNullHandling(NullHandlingDto nullHandling)  {
		/* old value */
		NullHandlingDto oldValue = null;
		if(GWT.isClient())
			oldValue = getNullHandling();

		/* set new value */
		this.nullHandling = nullHandling;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(nullHandling_pa, oldValue, nullHandling, this.nullHandling_m));

		/* set indicator */
		this.nullHandling_m = true;

		this.fireObjectChangedEvent(ColumnDtoPA.INSTANCE.nullHandling(), oldValue);
	}


	public boolean isNullHandlingModified()  {
		return nullHandling_m;
	}


	public static PropertyAccessor<ColumnDto, NullHandlingDto> getNullHandlingPropertyAccessor()  {
		return nullHandling_pa;
	}


	public String getNullReplacementFormat()  {
		if(! isDtoProxy()){
			return this.nullReplacementFormat;
		}

		if(isNullReplacementFormatModified())
			return this.nullReplacementFormat;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().nullReplacementFormat());

		return _value;
	}


	public void setNullReplacementFormat(String nullReplacementFormat)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getNullReplacementFormat();

		/* set new value */
		this.nullReplacementFormat = nullReplacementFormat;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(nullReplacementFormat_pa, oldValue, nullReplacementFormat, this.nullReplacementFormat_m));

		/* set indicator */
		this.nullReplacementFormat_m = true;

		this.fireObjectChangedEvent(ColumnDtoPA.INSTANCE.nullReplacementFormat(), oldValue);
	}


	public boolean isNullReplacementFormatModified()  {
		return nullReplacementFormat_m;
	}


	public static PropertyAccessor<ColumnDto, String> getNullReplacementFormatPropertyAccessor()  {
		return nullReplacementFormat_pa;
	}


	public OrderDto getOrder()  {
		if(! isDtoProxy()){
			return this.order;
		}

		if(isOrderModified())
			return this.order;

		if(! GWT.isClient())
			return null;

		OrderDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().order());

		return _value;
	}


	public void setOrder(OrderDto order)  {
		/* old value */
		OrderDto oldValue = null;
		if(GWT.isClient())
			oldValue = getOrder();

		/* set new value */
		this.order = order;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(order_pa, oldValue, order, this.order_m));

		/* set indicator */
		this.order_m = true;

		this.fireObjectChangedEvent(ColumnDtoPA.INSTANCE.order(), oldValue);
	}


	public boolean isOrderModified()  {
		return order_m;
	}


	public static PropertyAccessor<ColumnDto, OrderDto> getOrderPropertyAccessor()  {
		return order_pa;
	}


	public Integer getPreviewWidth()  {
		if(! isDtoProxy()){
			return this.previewWidth;
		}

		if(isPreviewWidthModified())
			return this.previewWidth;

		if(! GWT.isClient())
			return null;

		Integer _value = dtoManager.getProperty(this, instantiatePropertyAccess().previewWidth());

		return _value;
	}


	public void setPreviewWidth(Integer previewWidth)  {
		/* old value */
		Integer oldValue = null;
		if(GWT.isClient())
			oldValue = getPreviewWidth();

		/* set new value */
		this.previewWidth = previewWidth;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(previewWidth_pa, oldValue, previewWidth, this.previewWidth_m));

		/* set indicator */
		this.previewWidth_m = true;

		this.fireObjectChangedEvent(ColumnDtoPA.INSTANCE.previewWidth(), oldValue);
	}


	public boolean isPreviewWidthModified()  {
		return previewWidth_m;
	}


	public static PropertyAccessor<ColumnDto, Integer> getPreviewWidthPropertyAccessor()  {
		return previewWidth_pa;
	}


	public String getSemanticType()  {
		if(! isDtoProxy()){
			return this.semanticType;
		}

		if(isSemanticTypeModified())
			return this.semanticType;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().semanticType());

		return _value;
	}


	public void setSemanticType(String semanticType)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getSemanticType();

		/* set new value */
		this.semanticType = semanticType;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(semanticType_pa, oldValue, semanticType, this.semanticType_m));

		/* set indicator */
		this.semanticType_m = true;

		this.fireObjectChangedEvent(ColumnDtoPA.INSTANCE.semanticType(), oldValue);
	}


	public boolean isSemanticTypeModified()  {
		return semanticType_m;
	}


	public static PropertyAccessor<ColumnDto, String> getSemanticTypePropertyAccessor()  {
		return semanticType_pa;
	}


	public Boolean isSubtotalGroup()  {
		if(! isDtoProxy()){
			return this.subtotalGroup;
		}

		if(isSubtotalGroupModified())
			return this.subtotalGroup;

		if(! GWT.isClient())
			return null;

		Boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().subtotalGroup());

		return _value;
	}


	public void setSubtotalGroup(Boolean subtotalGroup)  {
		/* old value */
		Boolean oldValue = null;
		if(GWT.isClient())
			oldValue = isSubtotalGroup();

		/* set new value */
		this.subtotalGroup = subtotalGroup;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(subtotalGroup_pa, oldValue, subtotalGroup, this.subtotalGroup_m));

		/* set indicator */
		this.subtotalGroup_m = true;

		this.fireObjectChangedEvent(ColumnDtoPA.INSTANCE.subtotalGroup(), oldValue);
	}


	public boolean isSubtotalGroupModified()  {
		return subtotalGroup_m;
	}


	public static PropertyAccessor<ColumnDto, Boolean> getSubtotalGroupPropertyAccessor()  {
		return subtotalGroup_pa;
	}


	public Integer getType()  {
		if(! isDtoProxy()){
			return this.type;
		}

		if(isTypeModified())
			return this.type;

		if(! GWT.isClient())
			return null;

		Integer _value = dtoManager.getProperty(this, instantiatePropertyAccess().type());

		return _value;
	}


	public void setType(Integer type)  {
		/* old value */
		Integer oldValue = null;
		if(GWT.isClient())
			oldValue = getType();

		/* set new value */
		this.type = type;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(type_pa, oldValue, type, this.type_m));

		/* set indicator */
		this.type_m = true;

		this.fireObjectChangedEvent(ColumnDtoPA.INSTANCE.type(), oldValue);
	}


	public boolean isTypeModified()  {
		return type_m;
	}


	public static PropertyAccessor<ColumnDto, Integer> getTypePropertyAccessor()  {
		return type_pa;
	}


	@Override
	public void setDtoId(Object id)  {
		setId((Long) id);
	}

	@Override
	public Object getDtoId()  {
		return getId();
	}

	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof ColumnDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((ColumnDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new ColumnDto2PosoMap();
	}

	public ColumnDtoPA instantiatePropertyAccess()  {
		return GWT.create(ColumnDtoPA.class);
	}

	public void clearModified()  {
		this.aggregateFunction = null;
		this.aggregateFunction_m = false;
		this.alias = null;
		this.alias_m = false;
		this.defaultAlias = null;
		this.defaultAlias_m = false;
		this.defaultPreviewWidth = null;
		this.defaultPreviewWidth_m = false;
		this.description = null;
		this.description_m = false;
		this.dimension = null;
		this.dimension_m = false;
		this.exportNullAsString = false;
		this.exportNullAsString_m = false;
		this.filter = null;
		this.filter_m = false;
		this.format = null;
		this.format_m = false;
		this.hidden = null;
		this.hidden_m = false;
		this.id = null;
		this.id_m = false;
		this.indexColumn = false;
		this.indexColumn_m = false;
		this.name = null;
		this.name_m = false;
		this.nullHandling = null;
		this.nullHandling_m = false;
		this.nullReplacementFormat = null;
		this.nullReplacementFormat_m = false;
		this.order = null;
		this.order_m = false;
		this.previewWidth = null;
		this.previewWidth_m = false;
		this.semanticType = null;
		this.semanticType_m = false;
		this.subtotalGroup = null;
		this.subtotalGroup_m = false;
		this.type = null;
		this.type_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(aggregateFunction_m)
			return true;
		if(alias_m)
			return true;
		if(defaultAlias_m)
			return true;
		if(defaultPreviewWidth_m)
			return true;
		if(description_m)
			return true;
		if(dimension_m)
			return true;
		if(exportNullAsString_m)
			return true;
		if(filter_m)
			return true;
		if(format_m)
			return true;
		if(hidden_m)
			return true;
		if(id_m)
			return true;
		if(indexColumn_m)
			return true;
		if(name_m)
			return true;
		if(nullHandling_m)
			return true;
		if(nullReplacementFormat_m)
			return true;
		if(order_m)
			return true;
		if(previewWidth_m)
			return true;
		if(semanticType_m)
			return true;
		if(subtotalGroup_m)
			return true;
		if(type_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(aggregateFunction_pa);
		list.add(alias_pa);
		list.add(defaultAlias_pa);
		list.add(defaultPreviewWidth_pa);
		list.add(description_pa);
		list.add(dimension_pa);
		list.add(exportNullAsString_pa);
		list.add(filter_pa);
		list.add(format_pa);
		list.add(hidden_pa);
		list.add(id_pa);
		list.add(indexColumn_pa);
		list.add(name_pa);
		list.add(nullHandling_pa);
		list.add(nullReplacementFormat_pa);
		list.add(order_pa);
		list.add(previewWidth_pa);
		list.add(semanticType_pa);
		list.add(subtotalGroup_pa);
		list.add(type_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(aggregateFunction_m)
			list.add(aggregateFunction_pa);
		if(alias_m)
			list.add(alias_pa);
		if(defaultAlias_m)
			list.add(defaultAlias_pa);
		if(defaultPreviewWidth_m)
			list.add(defaultPreviewWidth_pa);
		if(description_m)
			list.add(description_pa);
		if(dimension_m)
			list.add(dimension_pa);
		if(exportNullAsString_m)
			list.add(exportNullAsString_pa);
		if(filter_m)
			list.add(filter_pa);
		if(format_m)
			list.add(format_pa);
		if(hidden_m)
			list.add(hidden_pa);
		if(id_m)
			list.add(id_pa);
		if(indexColumn_m)
			list.add(indexColumn_pa);
		if(name_m)
			list.add(name_pa);
		if(nullHandling_m)
			list.add(nullHandling_pa);
		if(nullReplacementFormat_m)
			list.add(nullReplacementFormat_pa);
		if(order_m)
			list.add(order_pa);
		if(previewWidth_m)
			list.add(previewWidth_pa);
		if(semanticType_m)
			list.add(semanticType_pa);
		if(subtotalGroup_m)
			list.add(subtotalGroup_pa);
		if(type_m)
			list.add(type_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(description_pa);
			list.add(id_pa);
			list.add(name_pa);
			list.add(type_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(aggregateFunction_pa);
			list.add(alias_pa);
			list.add(defaultAlias_pa);
			list.add(defaultPreviewWidth_pa);
			list.add(dimension_pa);
			list.add(exportNullAsString_pa);
			list.add(filter_pa);
			list.add(format_pa);
			list.add(hidden_pa);
			list.add(indexColumn_pa);
			list.add(nullHandling_pa);
			list.add(nullReplacementFormat_pa);
			list.add(order_pa);
			list.add(previewWidth_pa);
			list.add(semanticType_pa);
			list.add(subtotalGroup_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(filter_pa);
		list.add(format_pa);
		return list;
	}



	net.datenwerke.rs.base.client.reportengines.table.dto.NullHandlingDto wl_0;
	net.datenwerke.rs.base.client.reportengines.table.dto.AggregateFunctionDto wl_1;
	net.datenwerke.rs.base.client.reportengines.table.dto.FilterDto wl_2;
	net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatDto wl_3;
	net.datenwerke.rs.base.client.reportengines.table.dto.OrderDto wl_4;

}
