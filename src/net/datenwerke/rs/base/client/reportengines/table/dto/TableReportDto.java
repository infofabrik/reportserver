package net.datenwerke.rs.base.client.reportengines.table.dto;

import com.google.gwt.core.client.GWT;
import java.lang.Boolean;
import java.lang.NullPointerException;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.ChangeMonitoredList;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.MonitoredCollection;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.base.client.reportengines.table.dto.AdditionalColumnSpecDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.PreFilterDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.TableReportDtoPA;
import net.datenwerke.rs.base.client.reportengines.table.dto.posomap.TableReportDto2PosoMap;
import net.datenwerke.rs.base.client.reportengines.table.locale.TableMessages;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.decorator.ReportDtoDec;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * Dto for {@link TableReport}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class TableReportDto extends ReportDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private List<AdditionalColumnSpecDto> additionalColumns;
	private  boolean additionalColumns_m;
	public static final String PROPERTY_ADDITIONAL_COLUMNS = "dpi-tablereport-additionalcolumns";

	private transient static PropertyAccessor<TableReportDto, List<AdditionalColumnSpecDto>> additionalColumns_pa = new PropertyAccessor<TableReportDto, List<AdditionalColumnSpecDto>>() {
		@Override
		public void setValue(TableReportDto container, List<AdditionalColumnSpecDto> object) {
			container.setAdditionalColumns(object);
		}

		@Override
		public List<AdditionalColumnSpecDto> getValue(TableReportDto container) {
			return container.getAdditionalColumns();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "additionalColumns";
		}

		@Override
		public void setModified(TableReportDto container, boolean modified) {
			container.additionalColumns_m = modified;
		}

		@Override
		public boolean isModified(TableReportDto container) {
			return container.isAdditionalColumnsModified();
		}
	};

	private boolean allowCubification;
	private  boolean allowCubification_m;
	public static final String PROPERTY_ALLOW_CUBIFICATION = "dpi-tablereport-allowcubification";

	private transient static PropertyAccessor<TableReportDto, Boolean> allowCubification_pa = new PropertyAccessor<TableReportDto, Boolean>() {
		@Override
		public void setValue(TableReportDto container, Boolean object) {
			container.setAllowCubification(object);
		}

		@Override
		public Boolean getValue(TableReportDto container) {
			return container.isAllowCubification();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "allowCubification";
		}

		@Override
		public void setModified(TableReportDto container, boolean modified) {
			container.allowCubification_m = modified;
		}

		@Override
		public boolean isModified(TableReportDto container) {
			return container.isAllowCubificationModified();
		}
	};

	private boolean allowMdx;
	private  boolean allowMdx_m;
	public static final String PROPERTY_ALLOW_MDX = "dpi-tablereport-allowmdx";

	private transient static PropertyAccessor<TableReportDto, Boolean> allowMdx_pa = new PropertyAccessor<TableReportDto, Boolean>() {
		@Override
		public void setValue(TableReportDto container, Boolean object) {
			container.setAllowMdx(object);
		}

		@Override
		public Boolean getValue(TableReportDto container) {
			return container.isAllowMdx();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "allowMdx";
		}

		@Override
		public void setModified(TableReportDto container, boolean modified) {
			container.allowMdx_m = modified;
		}

		@Override
		public boolean isModified(TableReportDto container) {
			return container.isAllowMdxModified();
		}
	};

	private List<ColumnDto> columns;
	private  boolean columns_m;
	public static final String PROPERTY_COLUMNS = "dpi-tablereport-columns";

	private transient static PropertyAccessor<TableReportDto, List<ColumnDto>> columns_pa = new PropertyAccessor<TableReportDto, List<ColumnDto>>() {
		@Override
		public void setValue(TableReportDto container, List<ColumnDto> object) {
			container.setColumns(object);
		}

		@Override
		public List<ColumnDto> getValue(TableReportDto container) {
			return container.getColumns();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "columns";
		}

		@Override
		public void setModified(TableReportDto container, boolean modified) {
			container.columns_m = modified;
		}

		@Override
		public boolean isModified(TableReportDto container) {
			return container.isColumnsModified();
		}
	};

	private boolean cubeFlag;
	private  boolean cubeFlag_m;
	public static final String PROPERTY_CUBE_FLAG = "dpi-tablereport-cubeflag";

	private transient static PropertyAccessor<TableReportDto, Boolean> cubeFlag_pa = new PropertyAccessor<TableReportDto, Boolean>() {
		@Override
		public void setValue(TableReportDto container, Boolean object) {
			container.setCubeFlag(object);
		}

		@Override
		public Boolean getValue(TableReportDto container) {
			return container.isCubeFlag();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "cubeFlag";
		}

		@Override
		public void setModified(TableReportDto container, boolean modified) {
			container.cubeFlag_m = modified;
		}

		@Override
		public boolean isModified(TableReportDto container) {
			return container.isCubeFlagModified();
		}
	};

	private String cubeXml;
	private  boolean cubeXml_m;
	public static final String PROPERTY_CUBE_XML = "dpi-tablereport-cubexml";

	private transient static PropertyAccessor<TableReportDto, String> cubeXml_pa = new PropertyAccessor<TableReportDto, String>() {
		@Override
		public void setValue(TableReportDto container, String object) {
			container.setCubeXml(object);
		}

		@Override
		public String getValue(TableReportDto container) {
			return container.getCubeXml();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "cubeXml";
		}

		@Override
		public void setModified(TableReportDto container, boolean modified) {
			container.cubeXml_m = modified;
		}

		@Override
		public boolean isModified(TableReportDto container) {
			return container.isCubeXmlModified();
		}
	};

	private Boolean distinctFlag;
	private  boolean distinctFlag_m;
	public static final String PROPERTY_DISTINCT_FLAG = "dpi-tablereport-distinctflag";

	private transient static PropertyAccessor<TableReportDto, Boolean> distinctFlag_pa = new PropertyAccessor<TableReportDto, Boolean>() {
		@Override
		public void setValue(TableReportDto container, Boolean object) {
			container.setDistinctFlag(object);
		}

		@Override
		public Boolean getValue(TableReportDto container) {
			return container.isDistinctFlag();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "distinctFlag";
		}

		@Override
		public void setModified(TableReportDto container, boolean modified) {
			container.distinctFlag_m = modified;
		}

		@Override
		public boolean isModified(TableReportDto container) {
			return container.isDistinctFlagModified();
		}
	};

	private boolean enableSubtotals;
	private  boolean enableSubtotals_m;
	public static final String PROPERTY_ENABLE_SUBTOTALS = "dpi-tablereport-enablesubtotals";

	private transient static PropertyAccessor<TableReportDto, Boolean> enableSubtotals_pa = new PropertyAccessor<TableReportDto, Boolean>() {
		@Override
		public void setValue(TableReportDto container, Boolean object) {
			container.setEnableSubtotals(object);
		}

		@Override
		public Boolean getValue(TableReportDto container) {
			return container.isEnableSubtotals();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "enableSubtotals";
		}

		@Override
		public void setModified(TableReportDto container, boolean modified) {
			container.enableSubtotals_m = modified;
		}

		@Override
		public boolean isModified(TableReportDto container) {
			return container.isEnableSubtotalsModified();
		}
	};

	private boolean hideParents;
	private  boolean hideParents_m;
	public static final String PROPERTY_HIDE_PARENTS = "dpi-tablereport-hideparents";

	private transient static PropertyAccessor<TableReportDto, Boolean> hideParents_pa = new PropertyAccessor<TableReportDto, Boolean>() {
		@Override
		public void setValue(TableReportDto container, Boolean object) {
			container.setHideParents(object);
		}

		@Override
		public Boolean getValue(TableReportDto container) {
			return container.isHideParents();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "hideParents";
		}

		@Override
		public void setModified(TableReportDto container, boolean modified) {
			container.hideParents_m = modified;
		}

		@Override
		public boolean isModified(TableReportDto container) {
			return container.isHideParentsModified();
		}
	};

	private DatasourceContainerDto metadataDatasourceContainer;
	private  boolean metadataDatasourceContainer_m;
	public static final String PROPERTY_METADATA_DATASOURCE_CONTAINER = "dpi-tablereport-metadatadatasourcecontainer";

	private transient static PropertyAccessor<TableReportDto, DatasourceContainerDto> metadataDatasourceContainer_pa = new PropertyAccessor<TableReportDto, DatasourceContainerDto>() {
		@Override
		public void setValue(TableReportDto container, DatasourceContainerDto object) {
			container.setMetadataDatasourceContainer(object);
		}

		@Override
		public DatasourceContainerDto getValue(TableReportDto container) {
			return container.getMetadataDatasourceContainer();
		}

		@Override
		public Class<?> getType() {
			return DatasourceContainerDto.class;
		}

		@Override
		public String getPath() {
			return "metadataDatasourceContainer";
		}

		@Override
		public void setModified(TableReportDto container, boolean modified) {
			container.metadataDatasourceContainer_m = modified;
		}

		@Override
		public boolean isModified(TableReportDto container) {
			return container.isMetadataDatasourceContainerModified();
		}
	};

	private PreFilterDto preFilter;
	private  boolean preFilter_m;
	public static final String PROPERTY_PRE_FILTER = "dpi-tablereport-prefilter";

	private transient static PropertyAccessor<TableReportDto, PreFilterDto> preFilter_pa = new PropertyAccessor<TableReportDto, PreFilterDto>() {
		@Override
		public void setValue(TableReportDto container, PreFilterDto object) {
			container.setPreFilter(object);
		}

		@Override
		public PreFilterDto getValue(TableReportDto container) {
			return container.getPreFilter();
		}

		@Override
		public Class<?> getType() {
			return PreFilterDto.class;
		}

		@Override
		public String getPath() {
			return "preFilter";
		}

		@Override
		public void setModified(TableReportDto container, boolean modified) {
			container.preFilter_m = modified;
		}

		@Override
		public boolean isModified(TableReportDto container) {
			return container.isPreFilterModified();
		}
	};


	public TableReportDto() {
		super();
	}

	public List<AdditionalColumnSpecDto> getAdditionalColumns()  {
		if(! isDtoProxy()){
			List<AdditionalColumnSpecDto> _currentValue = this.additionalColumns;
			if(null == _currentValue)
				this.additionalColumns = new ArrayList<AdditionalColumnSpecDto>();

			return this.additionalColumns;
		}

		if(isAdditionalColumnsModified())
			return this.additionalColumns;

		if(! GWT.isClient())
			return null;

		List<AdditionalColumnSpecDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().additionalColumns());

		_value = new ChangeMonitoredList<AdditionalColumnSpecDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isAdditionalColumnsModified())
						setAdditionalColumns((List<AdditionalColumnSpecDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setAdditionalColumns(List<AdditionalColumnSpecDto> additionalColumns)  {
		/* old value */
		List<AdditionalColumnSpecDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getAdditionalColumns();

		/* set new value */
		this.additionalColumns = additionalColumns;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(additionalColumns_pa, oldValue, additionalColumns, this.additionalColumns_m));

		/* set indicator */
		this.additionalColumns_m = true;

		this.fireObjectChangedEvent(TableReportDtoPA.INSTANCE.additionalColumns(), oldValue);
	}


	public boolean isAdditionalColumnsModified()  {
		return additionalColumns_m;
	}


	public static PropertyAccessor<TableReportDto, List<AdditionalColumnSpecDto>> getAdditionalColumnsPropertyAccessor()  {
		return additionalColumns_pa;
	}


	public boolean isAllowCubification()  {
		if(! isDtoProxy()){
			return this.allowCubification;
		}

		if(isAllowCubificationModified())
			return this.allowCubification;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().allowCubification());

		return _value;
	}


	public void setAllowCubification(boolean allowCubification)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isAllowCubification();

		/* set new value */
		this.allowCubification = allowCubification;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(allowCubification_pa, oldValue, allowCubification, this.allowCubification_m));

		/* set indicator */
		this.allowCubification_m = true;

		this.fireObjectChangedEvent(TableReportDtoPA.INSTANCE.allowCubification(), oldValue);
	}


	public boolean isAllowCubificationModified()  {
		return allowCubification_m;
	}


	public static PropertyAccessor<TableReportDto, Boolean> getAllowCubificationPropertyAccessor()  {
		return allowCubification_pa;
	}


	public boolean isAllowMdx()  {
		if(! isDtoProxy()){
			return this.allowMdx;
		}

		if(isAllowMdxModified())
			return this.allowMdx;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().allowMdx());

		return _value;
	}


	public void setAllowMdx(boolean allowMdx)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isAllowMdx();

		/* set new value */
		this.allowMdx = allowMdx;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(allowMdx_pa, oldValue, allowMdx, this.allowMdx_m));

		/* set indicator */
		this.allowMdx_m = true;

		this.fireObjectChangedEvent(TableReportDtoPA.INSTANCE.allowMdx(), oldValue);
	}


	public boolean isAllowMdxModified()  {
		return allowMdx_m;
	}


	public static PropertyAccessor<TableReportDto, Boolean> getAllowMdxPropertyAccessor()  {
		return allowMdx_pa;
	}


	public List<ColumnDto> getColumns()  {
		if(! isDtoProxy()){
			List<ColumnDto> _currentValue = this.columns;
			if(null == _currentValue)
				this.columns = new ArrayList<ColumnDto>();

			return this.columns;
		}

		if(isColumnsModified())
			return this.columns;

		if(! GWT.isClient())
			return null;

		List<ColumnDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().columns());

		_value = new ChangeMonitoredList<ColumnDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isColumnsModified())
						setColumns((List<ColumnDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setColumns(List<ColumnDto> columns)  {
		/* old value */
		List<ColumnDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getColumns();

		/* set new value */
		this.columns = columns;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(columns_pa, oldValue, columns, this.columns_m));

		/* set indicator */
		this.columns_m = true;

		this.fireObjectChangedEvent(TableReportDtoPA.INSTANCE.columns(), oldValue);
	}


	public boolean isColumnsModified()  {
		return columns_m;
	}


	public static PropertyAccessor<TableReportDto, List<ColumnDto>> getColumnsPropertyAccessor()  {
		return columns_pa;
	}


	public boolean isCubeFlag()  {
		if(! isDtoProxy()){
			return this.cubeFlag;
		}

		if(isCubeFlagModified())
			return this.cubeFlag;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().cubeFlag());

		return _value;
	}


	public void setCubeFlag(boolean cubeFlag)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isCubeFlag();

		/* set new value */
		this.cubeFlag = cubeFlag;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(cubeFlag_pa, oldValue, cubeFlag, this.cubeFlag_m));

		/* set indicator */
		this.cubeFlag_m = true;

		this.fireObjectChangedEvent(TableReportDtoPA.INSTANCE.cubeFlag(), oldValue);
	}


	public boolean isCubeFlagModified()  {
		return cubeFlag_m;
	}


	public static PropertyAccessor<TableReportDto, Boolean> getCubeFlagPropertyAccessor()  {
		return cubeFlag_pa;
	}


	public String getCubeXml()  {
		if(! isDtoProxy()){
			return this.cubeXml;
		}

		if(isCubeXmlModified())
			return this.cubeXml;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().cubeXml());

		return _value;
	}


	public void setCubeXml(String cubeXml)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getCubeXml();

		/* set new value */
		this.cubeXml = cubeXml;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(cubeXml_pa, oldValue, cubeXml, this.cubeXml_m));

		/* set indicator */
		this.cubeXml_m = true;

		this.fireObjectChangedEvent(TableReportDtoPA.INSTANCE.cubeXml(), oldValue);
	}


	public boolean isCubeXmlModified()  {
		return cubeXml_m;
	}


	public static PropertyAccessor<TableReportDto, String> getCubeXmlPropertyAccessor()  {
		return cubeXml_pa;
	}


	public Boolean isDistinctFlag()  {
		if(! isDtoProxy()){
			return this.distinctFlag;
		}

		if(isDistinctFlagModified())
			return this.distinctFlag;

		if(! GWT.isClient())
			return null;

		Boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().distinctFlag());

		return _value;
	}


	public void setDistinctFlag(Boolean distinctFlag)  {
		/* old value */
		Boolean oldValue = null;
		if(GWT.isClient())
			oldValue = isDistinctFlag();

		/* set new value */
		this.distinctFlag = distinctFlag;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(distinctFlag_pa, oldValue, distinctFlag, this.distinctFlag_m));

		/* set indicator */
		this.distinctFlag_m = true;

		this.fireObjectChangedEvent(TableReportDtoPA.INSTANCE.distinctFlag(), oldValue);
	}


	public boolean isDistinctFlagModified()  {
		return distinctFlag_m;
	}


	public static PropertyAccessor<TableReportDto, Boolean> getDistinctFlagPropertyAccessor()  {
		return distinctFlag_pa;
	}


	public boolean isEnableSubtotals()  {
		if(! isDtoProxy()){
			return this.enableSubtotals;
		}

		if(isEnableSubtotalsModified())
			return this.enableSubtotals;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().enableSubtotals());

		return _value;
	}


	public void setEnableSubtotals(boolean enableSubtotals)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isEnableSubtotals();

		/* set new value */
		this.enableSubtotals = enableSubtotals;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(enableSubtotals_pa, oldValue, enableSubtotals, this.enableSubtotals_m));

		/* set indicator */
		this.enableSubtotals_m = true;

		this.fireObjectChangedEvent(TableReportDtoPA.INSTANCE.enableSubtotals(), oldValue);
	}


	public boolean isEnableSubtotalsModified()  {
		return enableSubtotals_m;
	}


	public static PropertyAccessor<TableReportDto, Boolean> getEnableSubtotalsPropertyAccessor()  {
		return enableSubtotals_pa;
	}


	public boolean isHideParents()  {
		if(! isDtoProxy()){
			return this.hideParents;
		}

		if(isHideParentsModified())
			return this.hideParents;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().hideParents());

		return _value;
	}


	public void setHideParents(boolean hideParents)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isHideParents();

		/* set new value */
		this.hideParents = hideParents;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(hideParents_pa, oldValue, hideParents, this.hideParents_m));

		/* set indicator */
		this.hideParents_m = true;

		this.fireObjectChangedEvent(TableReportDtoPA.INSTANCE.hideParents(), oldValue);
	}


	public boolean isHideParentsModified()  {
		return hideParents_m;
	}


	public static PropertyAccessor<TableReportDto, Boolean> getHideParentsPropertyAccessor()  {
		return hideParents_pa;
	}


	public DatasourceContainerDto getMetadataDatasourceContainer()  {
		if(! isDtoProxy()){
			return this.metadataDatasourceContainer;
		}

		if(isMetadataDatasourceContainerModified())
			return this.metadataDatasourceContainer;

		if(! GWT.isClient())
			return null;

		DatasourceContainerDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().metadataDatasourceContainer());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isMetadataDatasourceContainerModified())
						setMetadataDatasourceContainer((DatasourceContainerDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setMetadataDatasourceContainer(DatasourceContainerDto metadataDatasourceContainer)  {
		/* old value */
		DatasourceContainerDto oldValue = null;
		if(GWT.isClient())
			oldValue = getMetadataDatasourceContainer();

		/* set new value */
		this.metadataDatasourceContainer = metadataDatasourceContainer;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(metadataDatasourceContainer_pa, oldValue, metadataDatasourceContainer, this.metadataDatasourceContainer_m));

		/* set indicator */
		this.metadataDatasourceContainer_m = true;

		this.fireObjectChangedEvent(TableReportDtoPA.INSTANCE.metadataDatasourceContainer(), oldValue);
	}


	public boolean isMetadataDatasourceContainerModified()  {
		return metadataDatasourceContainer_m;
	}


	public static PropertyAccessor<TableReportDto, DatasourceContainerDto> getMetadataDatasourceContainerPropertyAccessor()  {
		return metadataDatasourceContainer_pa;
	}


	public PreFilterDto getPreFilter()  {
		if(! isDtoProxy()){
			return this.preFilter;
		}

		if(isPreFilterModified())
			return this.preFilter;

		if(! GWT.isClient())
			return null;

		PreFilterDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().preFilter());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isPreFilterModified())
						setPreFilter((PreFilterDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setPreFilter(PreFilterDto preFilter)  {
		/* old value */
		PreFilterDto oldValue = null;
		if(GWT.isClient())
			oldValue = getPreFilter();

		/* set new value */
		this.preFilter = preFilter;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(preFilter_pa, oldValue, preFilter, this.preFilter_m));

		/* set indicator */
		this.preFilter_m = true;

		this.fireObjectChangedEvent(TableReportDtoPA.INSTANCE.preFilter(), oldValue);
	}


	public boolean isPreFilterModified()  {
		return preFilter_m;
	}


	public static PropertyAccessor<TableReportDto, PreFilterDto> getPreFilterPropertyAccessor()  {
		return preFilter_pa;
	}


	@Override
	public String toDisplayTitle()  {
		try{
			if(null == getName())
				return BaseMessages.INSTANCE.unnamed();
			return getName().toString();
		} catch(NullPointerException e){
			return BaseMessages.INSTANCE.unnamed();
		}
	}

	@Override
	public String toTypeDescription()  {
		return TableMessages.INSTANCE.reportTypeName();
	}

	@Override
	public BaseIcon toIcon()  {
		return BaseIcon.from("table");
	}

	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof TableReportDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((TableReportDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new TableReportDto2PosoMap();
	}

	public TableReportDtoPA instantiatePropertyAccess()  {
		return GWT.create(TableReportDtoPA.class);
	}

	public void clearModified()  {
		this.additionalColumns = null;
		this.additionalColumns_m = false;
		this.allowCubification = false;
		this.allowCubification_m = false;
		this.allowMdx = false;
		this.allowMdx_m = false;
		this.columns = null;
		this.columns_m = false;
		this.cubeFlag = false;
		this.cubeFlag_m = false;
		this.cubeXml = null;
		this.cubeXml_m = false;
		this.distinctFlag = null;
		this.distinctFlag_m = false;
		this.enableSubtotals = false;
		this.enableSubtotals_m = false;
		this.hideParents = false;
		this.hideParents_m = false;
		this.metadataDatasourceContainer = null;
		this.metadataDatasourceContainer_m = false;
		this.preFilter = null;
		this.preFilter_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(additionalColumns_m)
			return true;
		if(allowCubification_m)
			return true;
		if(allowMdx_m)
			return true;
		if(columns_m)
			return true;
		if(cubeFlag_m)
			return true;
		if(cubeXml_m)
			return true;
		if(distinctFlag_m)
			return true;
		if(enableSubtotals_m)
			return true;
		if(hideParents_m)
			return true;
		if(metadataDatasourceContainer_m)
			return true;
		if(preFilter_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(additionalColumns_pa);
		list.add(allowCubification_pa);
		list.add(allowMdx_pa);
		list.add(columns_pa);
		list.add(cubeFlag_pa);
		list.add(cubeXml_pa);
		list.add(distinctFlag_pa);
		list.add(enableSubtotals_pa);
		list.add(hideParents_pa);
		list.add(metadataDatasourceContainer_pa);
		list.add(preFilter_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(additionalColumns_m)
			list.add(additionalColumns_pa);
		if(allowCubification_m)
			list.add(allowCubification_pa);
		if(allowMdx_m)
			list.add(allowMdx_pa);
		if(columns_m)
			list.add(columns_pa);
		if(cubeFlag_m)
			list.add(cubeFlag_pa);
		if(cubeXml_m)
			list.add(cubeXml_pa);
		if(distinctFlag_m)
			list.add(distinctFlag_pa);
		if(enableSubtotals_m)
			list.add(enableSubtotals_pa);
		if(hideParents_m)
			list.add(hideParents_pa);
		if(metadataDatasourceContainer_m)
			list.add(metadataDatasourceContainer_pa);
		if(preFilter_m)
			list.add(preFilter_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.FTO) >= 0){
			list.add(allowMdx_pa);
			list.add(cubeFlag_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(additionalColumns_pa);
			list.add(allowCubification_pa);
			list.add(columns_pa);
			list.add(cubeXml_pa);
			list.add(distinctFlag_pa);
			list.add(enableSubtotals_pa);
			list.add(hideParents_pa);
			list.add(metadataDatasourceContainer_pa);
			list.add(preFilter_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(additionalColumns_pa);
		list.add(columns_pa);
		list.add(metadataDatasourceContainer_pa);
		list.add(preFilter_pa);
		return list;
	}



	net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto wl_0;
	net.datenwerke.rs.base.client.reportengines.table.dto.AdditionalColumnSpecDto wl_1;
	net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto wl_2;
	net.datenwerke.rs.base.client.reportengines.table.dto.PreFilterDto wl_3;

}
