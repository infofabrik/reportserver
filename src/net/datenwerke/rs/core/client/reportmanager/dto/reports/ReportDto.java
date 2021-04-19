package net.datenwerke.rs.core.client.reportmanager.dto.reports;

import com.google.gwt.core.client.GWT;
import java.lang.NullPointerException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.ChangeMonitoredList;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.ChangeMonitoredSet;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.MonitoredCollection;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerProviderDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.AbstractReportManagerNodeDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportMetadataDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportPropertyDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.pa.ReportDtoPA;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.posomap.ReportDto2PosoMap;
import net.datenwerke.rs.core.client.reportmanager.locale.ReportmanagerMessages;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;

/**
 * Dto for {@link Report}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class ReportDto extends AbstractReportManagerNodeDto implements DatasourceContainerProviderDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private DatasourceContainerDto datasourceContainer;
	private  boolean datasourceContainer_m;
	public static final String PROPERTY_DATASOURCE_CONTAINER = "dpi-report-datasourcecontainer";

	private transient static PropertyAccessor<ReportDto, DatasourceContainerDto> datasourceContainer_pa = new PropertyAccessor<ReportDto, DatasourceContainerDto>() {
		@Override
		public void setValue(ReportDto container, DatasourceContainerDto object) {
			container.setDatasourceContainer(object);
		}

		@Override
		public DatasourceContainerDto getValue(ReportDto container) {
			return container.getDatasourceContainer();
		}

		@Override
		public Class<?> getType() {
			return DatasourceContainerDto.class;
		}

		@Override
		public String getPath() {
			return "datasourceContainer";
		}

		@Override
		public void setModified(ReportDto container, boolean modified) {
			container.datasourceContainer_m = modified;
		}

		@Override
		public boolean isModified(ReportDto container) {
			return container.isDatasourceContainerModified();
		}
	};

	private String description;
	private  boolean description_m;
	public static final String PROPERTY_DESCRIPTION = "dpi-report-description";

	private transient static PropertyAccessor<ReportDto, String> description_pa = new PropertyAccessor<ReportDto, String>() {
		@Override
		public void setValue(ReportDto container, String object) {
			container.setDescription(object);
		}

		@Override
		public String getValue(ReportDto container) {
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
		public void setModified(ReportDto container, boolean modified) {
			container.description_m = modified;
		}

		@Override
		public boolean isModified(ReportDto container) {
			return container.isDescriptionModified();
		}
	};

	private String key;
	private  boolean key_m;
	public static final String PROPERTY_KEY = "dpi-report-key";

	private transient static PropertyAccessor<ReportDto, String> key_pa = new PropertyAccessor<ReportDto, String>() {
		@Override
		public void setValue(ReportDto container, String object) {
			container.setKey(object);
		}

		@Override
		public String getValue(ReportDto container) {
			return container.getKey();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "key";
		}

		@Override
		public void setModified(ReportDto container, boolean modified) {
			container.key_m = modified;
		}

		@Override
		public boolean isModified(ReportDto container) {
			return container.isKeyModified();
		}
	};

	private String name;
	private  boolean name_m;
	public static final String PROPERTY_NAME = "dpi-report-name";

	private transient static PropertyAccessor<ReportDto, String> name_pa = new PropertyAccessor<ReportDto, String>() {
		@Override
		public void setValue(ReportDto container, String object) {
			container.setName(object);
		}

		@Override
		public String getValue(ReportDto container) {
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
		public void setModified(ReportDto container, boolean modified) {
			container.name_m = modified;
		}

		@Override
		public boolean isModified(ReportDto container) {
			return container.isNameModified();
		}
	};

	private List<ParameterDefinitionDto> parameterDefinitions;
	private  boolean parameterDefinitions_m;
	public static final String PROPERTY_PARAMETER_DEFINITIONS = "dpi-report-parameterdefinitions";

	private transient static PropertyAccessor<ReportDto, List<ParameterDefinitionDto>> parameterDefinitions_pa = new PropertyAccessor<ReportDto, List<ParameterDefinitionDto>>() {
		@Override
		public void setValue(ReportDto container, List<ParameterDefinitionDto> object) {
			container.setParameterDefinitions(object);
		}

		@Override
		public List<ParameterDefinitionDto> getValue(ReportDto container) {
			return container.getParameterDefinitions();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "parameterDefinitions";
		}

		@Override
		public void setModified(ReportDto container, boolean modified) {
			container.parameterDefinitions_m = modified;
		}

		@Override
		public boolean isModified(ReportDto container) {
			return container.isParameterDefinitionsModified();
		}
	};

	private Set<ParameterInstanceDto> parameterInstances;
	private  boolean parameterInstances_m;
	public static final String PROPERTY_PARAMETER_INSTANCES = "dpi-report-parameterinstances";

	private transient static PropertyAccessor<ReportDto, Set<ParameterInstanceDto>> parameterInstances_pa = new PropertyAccessor<ReportDto, Set<ParameterInstanceDto>>() {
		@Override
		public void setValue(ReportDto container, Set<ParameterInstanceDto> object) {
			container.setParameterInstances(object);
		}

		@Override
		public Set<ParameterInstanceDto> getValue(ReportDto container) {
			return container.getParameterInstances();
		}

		@Override
		public Class<?> getType() {
			return Set.class;
		}

		@Override
		public String getPath() {
			return "parameterInstances";
		}

		@Override
		public void setModified(ReportDto container, boolean modified) {
			container.parameterInstances_m = modified;
		}

		@Override
		public boolean isModified(ReportDto container) {
			return container.isParameterInstancesModified();
		}
	};

	private Set<ReportMetadataDto> reportMetadata;
	private  boolean reportMetadata_m;
	public static final String PROPERTY_REPORT_METADATA = "dpi-report-reportmetadata";

	private transient static PropertyAccessor<ReportDto, Set<ReportMetadataDto>> reportMetadata_pa = new PropertyAccessor<ReportDto, Set<ReportMetadataDto>>() {
		@Override
		public void setValue(ReportDto container, Set<ReportMetadataDto> object) {
			container.setReportMetadata(object);
		}

		@Override
		public Set<ReportMetadataDto> getValue(ReportDto container) {
			return container.getReportMetadata();
		}

		@Override
		public Class<?> getType() {
			return Set.class;
		}

		@Override
		public String getPath() {
			return "reportMetadata";
		}

		@Override
		public void setModified(ReportDto container, boolean modified) {
			container.reportMetadata_m = modified;
		}

		@Override
		public boolean isModified(ReportDto container) {
			return container.isReportMetadataModified();
		}
	};

	private Set<ReportPropertyDto> reportProperties;
	private  boolean reportProperties_m;
	public static final String PROPERTY_REPORT_PROPERTIES = "dpi-report-reportproperties";

	private transient static PropertyAccessor<ReportDto, Set<ReportPropertyDto>> reportProperties_pa = new PropertyAccessor<ReportDto, Set<ReportPropertyDto>>() {
		@Override
		public void setValue(ReportDto container, Set<ReportPropertyDto> object) {
			container.setReportProperties(object);
		}

		@Override
		public Set<ReportPropertyDto> getValue(ReportDto container) {
			return container.getReportProperties();
		}

		@Override
		public Class<?> getType() {
			return Set.class;
		}

		@Override
		public String getPath() {
			return "reportProperties";
		}

		@Override
		public void setModified(ReportDto container, boolean modified) {
			container.reportProperties_m = modified;
		}

		@Override
		public boolean isModified(ReportDto container) {
			return container.isReportPropertiesModified();
		}
	};

	private String uuid;
	private  boolean uuid_m;
	public static final String PROPERTY_UUID = "dpi-report-uuid";

	private transient static PropertyAccessor<ReportDto, String> uuid_pa = new PropertyAccessor<ReportDto, String>() {
		@Override
		public void setValue(ReportDto container, String object) {
			container.setUuid(object);
		}

		@Override
		public String getValue(ReportDto container) {
			return container.getUuid();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "uuid";
		}

		@Override
		public void setModified(ReportDto container, boolean modified) {
			container.uuid_m = modified;
		}

		@Override
		public boolean isModified(ReportDto container) {
			return container.isUuidModified();
		}
	};

	private String temporaryUid;
	private  boolean temporaryUid_m;
	public static final String PROPERTY_TEMPORARY_UID = "dpi-report-temporaryuid";

	private transient static PropertyAccessor<ReportDto, String> temporaryUid_pa = new PropertyAccessor<ReportDto, String>() {
		@Override
		public void setValue(ReportDto container, String object) {
			container.setTemporaryUid(object);
		}

		@Override
		public String getValue(ReportDto container) {
			return container.getTemporaryUid();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "temporaryUid";
		}

		@Override
		public void setModified(ReportDto container, boolean modified) {
			container.temporaryUid_m = modified;
		}

		@Override
		public boolean isModified(ReportDto container) {
			return container.isTemporaryUidModified();
		}
	};

	private String parentReportKey;
	private  boolean parentReportKey_m;
	public static final String PROPERTY_PARENT_REPORT_KEY = "dpi-report-parentreportkey";

	private transient static PropertyAccessor<ReportDto, String> parentReportKey_pa = new PropertyAccessor<ReportDto, String>() {
		@Override
		public void setValue(ReportDto container, String object) {
			container.setParentReportKey(object);
		}

		@Override
		public String getValue(ReportDto container) {
			return container.getParentReportKey();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "parentReportKey";
		}

		@Override
		public void setModified(ReportDto container, boolean modified) {
			container.parentReportKey_m = modified;
		}

		@Override
		public boolean isModified(ReportDto container) {
			return container.isParentReportKeyModified();
		}
	};

	private String parentReportName;
	private  boolean parentReportName_m;
	public static final String PROPERTY_PARENT_REPORT_NAME = "dpi-report-parentreportname";

	private transient static PropertyAccessor<ReportDto, String> parentReportName_pa = new PropertyAccessor<ReportDto, String>() {
		@Override
		public void setValue(ReportDto container, String object) {
			container.setParentReportName(object);
		}

		@Override
		public String getValue(ReportDto container) {
			return container.getParentReportName();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "parentReportName";
		}

		@Override
		public void setModified(ReportDto container, boolean modified) {
			container.parentReportName_m = modified;
		}

		@Override
		public boolean isModified(ReportDto container) {
			return container.isParentReportNameModified();
		}
	};

	private String parentReportDescription;
	private  boolean parentReportDescription_m;
	public static final String PROPERTY_PARENT_REPORT_DESCRIPTION = "dpi-report-parentreportdescription";

	private transient static PropertyAccessor<ReportDto, String> parentReportDescription_pa = new PropertyAccessor<ReportDto, String>() {
		@Override
		public void setValue(ReportDto container, String object) {
			container.setParentReportDescription(object);
		}

		@Override
		public String getValue(ReportDto container) {
			return container.getParentReportDescription();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "parentReportDescription";
		}

		@Override
		public void setModified(ReportDto container, boolean modified) {
			container.parentReportDescription_m = modified;
		}

		@Override
		public boolean isModified(ReportDto container) {
			return container.isParentReportDescriptionModified();
		}
	};

	private HashSet<ReportPropertyDto> parentReportProperties;
	private  boolean parentReportProperties_m;
	public static final String PROPERTY_PARENT_REPORT_PROPERTIES = "dpi-report-parentreportproperties";

	private transient static PropertyAccessor<ReportDto, HashSet<ReportPropertyDto>> parentReportProperties_pa = new PropertyAccessor<ReportDto, HashSet<ReportPropertyDto>>() {
		@Override
		public void setValue(ReportDto container, HashSet<ReportPropertyDto> object) {
			container.setParentReportProperties(object);
		}

		@Override
		public HashSet<ReportPropertyDto> getValue(ReportDto container) {
			return container.getParentReportProperties();
		}

		@Override
		public Class<?> getType() {
			return HashSet.class;
		}

		@Override
		public String getPath() {
			return "parentReportProperties";
		}

		@Override
		public void setModified(ReportDto container, boolean modified) {
			container.parentReportProperties_m = modified;
		}

		@Override
		public boolean isModified(ReportDto container) {
			return container.isParentReportPropertiesModified();
		}
	};


	public ReportDto() {
		super();
	}

	public DatasourceContainerDto getDatasourceContainer()  {
		if(! isDtoProxy()){
			return this.datasourceContainer;
		}

		if(isDatasourceContainerModified())
			return this.datasourceContainer;

		if(! GWT.isClient())
			return null;

		DatasourceContainerDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().datasourceContainer());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isDatasourceContainerModified())
						setDatasourceContainer((DatasourceContainerDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setDatasourceContainer(DatasourceContainerDto datasourceContainer)  {
		/* old value */
		DatasourceContainerDto oldValue = null;
		if(GWT.isClient())
			oldValue = getDatasourceContainer();

		/* set new value */
		this.datasourceContainer = datasourceContainer;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(datasourceContainer_pa, oldValue, datasourceContainer, this.datasourceContainer_m));

		/* set indicator */
		this.datasourceContainer_m = true;

		this.fireObjectChangedEvent(ReportDtoPA.INSTANCE.datasourceContainer(), oldValue);
	}


	public boolean isDatasourceContainerModified()  {
		return datasourceContainer_m;
	}


	public static PropertyAccessor<ReportDto, DatasourceContainerDto> getDatasourceContainerPropertyAccessor()  {
		return datasourceContainer_pa;
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

		this.fireObjectChangedEvent(ReportDtoPA.INSTANCE.description(), oldValue);
	}


	public boolean isDescriptionModified()  {
		return description_m;
	}


	public static PropertyAccessor<ReportDto, String> getDescriptionPropertyAccessor()  {
		return description_pa;
	}


	public String getKey()  {
		if(! isDtoProxy()){
			return this.key;
		}

		if(isKeyModified())
			return this.key;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().key());

		return _value;
	}


	public void setKey(String key)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getKey();

		/* set new value */
		this.key = key;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(key_pa, oldValue, key, this.key_m));

		/* set indicator */
		this.key_m = true;

		this.fireObjectChangedEvent(ReportDtoPA.INSTANCE.key(), oldValue);
	}


	public boolean isKeyModified()  {
		return key_m;
	}


	public static PropertyAccessor<ReportDto, String> getKeyPropertyAccessor()  {
		return key_pa;
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

		this.fireObjectChangedEvent(ReportDtoPA.INSTANCE.name(), oldValue);
	}


	public boolean isNameModified()  {
		return name_m;
	}


	public static PropertyAccessor<ReportDto, String> getNamePropertyAccessor()  {
		return name_pa;
	}


	public List<ParameterDefinitionDto> getParameterDefinitions()  {
		if(! isDtoProxy()){
			List<ParameterDefinitionDto> _currentValue = this.parameterDefinitions;
			if(null == _currentValue)
				this.parameterDefinitions = new ArrayList<ParameterDefinitionDto>();

			return this.parameterDefinitions;
		}

		if(isParameterDefinitionsModified())
			return this.parameterDefinitions;

		if(! GWT.isClient())
			return null;

		List<ParameterDefinitionDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().parameterDefinitions());

		_value = new ChangeMonitoredList<ParameterDefinitionDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isParameterDefinitionsModified())
						setParameterDefinitions((List<ParameterDefinitionDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setParameterDefinitions(List<ParameterDefinitionDto> parameterDefinitions)  {
		/* old value */
		List<ParameterDefinitionDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getParameterDefinitions();

		/* set new value */
		this.parameterDefinitions = parameterDefinitions;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(parameterDefinitions_pa, oldValue, parameterDefinitions, this.parameterDefinitions_m));

		/* set indicator */
		this.parameterDefinitions_m = true;

		this.fireObjectChangedEvent(ReportDtoPA.INSTANCE.parameterDefinitions(), oldValue);
	}


	public boolean isParameterDefinitionsModified()  {
		return parameterDefinitions_m;
	}


	public static PropertyAccessor<ReportDto, List<ParameterDefinitionDto>> getParameterDefinitionsPropertyAccessor()  {
		return parameterDefinitions_pa;
	}


	public Set<ParameterInstanceDto> getParameterInstances()  {
		if(! isDtoProxy()){
			Set<ParameterInstanceDto> _currentValue = this.parameterInstances;
			if(null == _currentValue)
				this.parameterInstances = new HashSet<ParameterInstanceDto>();

			return this.parameterInstances;
		}

		if(isParameterInstancesModified())
			return this.parameterInstances;

		if(! GWT.isClient())
			return null;

		Set<ParameterInstanceDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().parameterInstances());

		_value = new ChangeMonitoredSet<ParameterInstanceDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isParameterInstancesModified())
						setParameterInstances((Set<ParameterInstanceDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setParameterInstances(Set<ParameterInstanceDto> parameterInstances)  {
		/* old value */
		Set<ParameterInstanceDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getParameterInstances();

		/* set new value */
		this.parameterInstances = parameterInstances;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(parameterInstances_pa, oldValue, parameterInstances, this.parameterInstances_m));

		/* set indicator */
		this.parameterInstances_m = true;

		this.fireObjectChangedEvent(ReportDtoPA.INSTANCE.parameterInstances(), oldValue);
	}


	public boolean isParameterInstancesModified()  {
		return parameterInstances_m;
	}


	public static PropertyAccessor<ReportDto, Set<ParameterInstanceDto>> getParameterInstancesPropertyAccessor()  {
		return parameterInstances_pa;
	}


	public Set<ReportMetadataDto> getReportMetadata()  {
		if(! isDtoProxy()){
			Set<ReportMetadataDto> _currentValue = this.reportMetadata;
			if(null == _currentValue)
				this.reportMetadata = new HashSet<ReportMetadataDto>();

			return this.reportMetadata;
		}

		if(isReportMetadataModified())
			return this.reportMetadata;

		if(! GWT.isClient())
			return null;

		Set<ReportMetadataDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().reportMetadata());

		_value = new ChangeMonitoredSet<ReportMetadataDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isReportMetadataModified())
						setReportMetadata((Set<ReportMetadataDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setReportMetadata(Set<ReportMetadataDto> reportMetadata)  {
		/* old value */
		Set<ReportMetadataDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getReportMetadata();

		/* set new value */
		this.reportMetadata = reportMetadata;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(reportMetadata_pa, oldValue, reportMetadata, this.reportMetadata_m));

		/* set indicator */
		this.reportMetadata_m = true;

		this.fireObjectChangedEvent(ReportDtoPA.INSTANCE.reportMetadata(), oldValue);
	}


	public boolean isReportMetadataModified()  {
		return reportMetadata_m;
	}


	public static PropertyAccessor<ReportDto, Set<ReportMetadataDto>> getReportMetadataPropertyAccessor()  {
		return reportMetadata_pa;
	}


	public Set<ReportPropertyDto> getReportProperties()  {
		if(! isDtoProxy()){
			Set<ReportPropertyDto> _currentValue = this.reportProperties;
			if(null == _currentValue)
				this.reportProperties = new HashSet<ReportPropertyDto>();

			return this.reportProperties;
		}

		if(isReportPropertiesModified())
			return this.reportProperties;

		if(! GWT.isClient())
			return null;

		Set<ReportPropertyDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().reportProperties());

		_value = new ChangeMonitoredSet<ReportPropertyDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isReportPropertiesModified())
						setReportProperties((Set<ReportPropertyDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setReportProperties(Set<ReportPropertyDto> reportProperties)  {
		/* old value */
		Set<ReportPropertyDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getReportProperties();

		/* set new value */
		this.reportProperties = reportProperties;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(reportProperties_pa, oldValue, reportProperties, this.reportProperties_m));

		/* set indicator */
		this.reportProperties_m = true;

		this.fireObjectChangedEvent(ReportDtoPA.INSTANCE.reportProperties(), oldValue);
	}


	public boolean isReportPropertiesModified()  {
		return reportProperties_m;
	}


	public static PropertyAccessor<ReportDto, Set<ReportPropertyDto>> getReportPropertiesPropertyAccessor()  {
		return reportProperties_pa;
	}


	public String getUuid()  {
		if(! isDtoProxy()){
			return this.uuid;
		}

		if(isUuidModified())
			return this.uuid;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().uuid());

		return _value;
	}


	public void setUuid(String uuid)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getUuid();

		/* set new value */
		this.uuid = uuid;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(uuid_pa, oldValue, uuid, this.uuid_m));

		/* set indicator */
		this.uuid_m = true;

		this.fireObjectChangedEvent(ReportDtoPA.INSTANCE.uuid(), oldValue);
	}


	public boolean isUuidModified()  {
		return uuid_m;
	}


	public static PropertyAccessor<ReportDto, String> getUuidPropertyAccessor()  {
		return uuid_pa;
	}


	public String getTemporaryUid()  {
		if(! isDtoProxy()){
			return this.temporaryUid;
		}

		if(isTemporaryUidModified())
			return this.temporaryUid;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().temporaryUid());

		return _value;
	}


	public void setTemporaryUid(String temporaryUid)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getTemporaryUid();

		/* set new value */
		this.temporaryUid = temporaryUid;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(temporaryUid_pa, oldValue, temporaryUid, this.temporaryUid_m));

		/* set indicator */
		this.temporaryUid_m = true;

		this.fireObjectChangedEvent(ReportDtoPA.INSTANCE.temporaryUid(), oldValue);
	}


	public boolean isTemporaryUidModified()  {
		return temporaryUid_m;
	}


	public static PropertyAccessor<ReportDto, String> getTemporaryUidPropertyAccessor()  {
		return temporaryUid_pa;
	}


	public String getParentReportKey()  {
		if(! isDtoProxy()){
			return this.parentReportKey;
		}

		if(isParentReportKeyModified())
			return this.parentReportKey;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().parentReportKey());

		return _value;
	}


	public void setParentReportKey(String parentReportKey)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getParentReportKey();

		/* set new value */
		this.parentReportKey = parentReportKey;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(parentReportKey_pa, oldValue, parentReportKey, this.parentReportKey_m));

		/* set indicator */
		this.parentReportKey_m = true;

		this.fireObjectChangedEvent(ReportDtoPA.INSTANCE.parentReportKey(), oldValue);
	}


	public boolean isParentReportKeyModified()  {
		return parentReportKey_m;
	}


	public static PropertyAccessor<ReportDto, String> getParentReportKeyPropertyAccessor()  {
		return parentReportKey_pa;
	}


	public String getParentReportName()  {
		if(! isDtoProxy()){
			return this.parentReportName;
		}

		if(isParentReportNameModified())
			return this.parentReportName;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().parentReportName());

		return _value;
	}


	public void setParentReportName(String parentReportName)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getParentReportName();

		/* set new value */
		this.parentReportName = parentReportName;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(parentReportName_pa, oldValue, parentReportName, this.parentReportName_m));

		/* set indicator */
		this.parentReportName_m = true;

		this.fireObjectChangedEvent(ReportDtoPA.INSTANCE.parentReportName(), oldValue);
	}


	public boolean isParentReportNameModified()  {
		return parentReportName_m;
	}


	public static PropertyAccessor<ReportDto, String> getParentReportNamePropertyAccessor()  {
		return parentReportName_pa;
	}


	public String getParentReportDescription()  {
		if(! isDtoProxy()){
			return this.parentReportDescription;
		}

		if(isParentReportDescriptionModified())
			return this.parentReportDescription;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().parentReportDescription());

		return _value;
	}


	public void setParentReportDescription(String parentReportDescription)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getParentReportDescription();

		/* set new value */
		this.parentReportDescription = parentReportDescription;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(parentReportDescription_pa, oldValue, parentReportDescription, this.parentReportDescription_m));

		/* set indicator */
		this.parentReportDescription_m = true;

		this.fireObjectChangedEvent(ReportDtoPA.INSTANCE.parentReportDescription(), oldValue);
	}


	public boolean isParentReportDescriptionModified()  {
		return parentReportDescription_m;
	}


	public static PropertyAccessor<ReportDto, String> getParentReportDescriptionPropertyAccessor()  {
		return parentReportDescription_pa;
	}


	public HashSet<ReportPropertyDto> getParentReportProperties()  {
		if(! isDtoProxy()){
			HashSet<ReportPropertyDto> _currentValue = this.parentReportProperties;
			if(null == _currentValue)
				this.parentReportProperties = new HashSet<ReportPropertyDto>();

			return this.parentReportProperties;
		}

		if(isParentReportPropertiesModified())
			return this.parentReportProperties;

		if(! GWT.isClient())
			return null;

		HashSet<ReportPropertyDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().parentReportProperties());

		return _value;
	}


	public void setParentReportProperties(HashSet<ReportPropertyDto> parentReportProperties)  {
		/* old value */
		HashSet<ReportPropertyDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getParentReportProperties();

		/* set new value */
		this.parentReportProperties = parentReportProperties;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(parentReportProperties_pa, oldValue, parentReportProperties, this.parentReportProperties_m));

		/* set indicator */
		this.parentReportProperties_m = true;

		this.fireObjectChangedEvent(ReportDtoPA.INSTANCE.parentReportProperties(), oldValue);
	}


	public boolean isParentReportPropertiesModified()  {
		return parentReportProperties_m;
	}


	public static PropertyAccessor<ReportDto, HashSet<ReportPropertyDto>> getParentReportPropertiesPropertyAccessor()  {
		return parentReportProperties_pa;
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
		return ReportmanagerMessages.INSTANCE.reportLabel();
	}

	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof ReportDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((ReportDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new ReportDto2PosoMap();
	}

	public ReportDtoPA instantiatePropertyAccess()  {
		return GWT.create(ReportDtoPA.class);
	}

	public void clearModified()  {
		this.datasourceContainer = null;
		this.datasourceContainer_m = false;
		this.description = null;
		this.description_m = false;
		this.key = null;
		this.key_m = false;
		this.name = null;
		this.name_m = false;
		this.parameterDefinitions = null;
		this.parameterDefinitions_m = false;
		this.parameterInstances = null;
		this.parameterInstances_m = false;
		this.reportMetadata = null;
		this.reportMetadata_m = false;
		this.reportProperties = null;
		this.reportProperties_m = false;
		this.uuid = null;
		this.uuid_m = false;
		this.temporaryUid = null;
		this.temporaryUid_m = false;
		this.parentReportKey = null;
		this.parentReportKey_m = false;
		this.parentReportName = null;
		this.parentReportName_m = false;
		this.parentReportDescription = null;
		this.parentReportDescription_m = false;
		this.parentReportProperties = null;
		this.parentReportProperties_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(datasourceContainer_m)
			return true;
		if(description_m)
			return true;
		if(key_m)
			return true;
		if(name_m)
			return true;
		if(parameterDefinitions_m)
			return true;
		if(parameterInstances_m)
			return true;
		if(reportMetadata_m)
			return true;
		if(reportProperties_m)
			return true;
		if(uuid_m)
			return true;
		if(temporaryUid_m)
			return true;
		if(parentReportKey_m)
			return true;
		if(parentReportName_m)
			return true;
		if(parentReportDescription_m)
			return true;
		if(parentReportProperties_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(datasourceContainer_pa);
		list.add(description_pa);
		list.add(key_pa);
		list.add(name_pa);
		list.add(parameterDefinitions_pa);
		list.add(parameterInstances_pa);
		list.add(reportMetadata_pa);
		list.add(reportProperties_pa);
		list.add(uuid_pa);
		list.add(temporaryUid_pa);
		list.add(parentReportKey_pa);
		list.add(parentReportName_pa);
		list.add(parentReportDescription_pa);
		list.add(parentReportProperties_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(datasourceContainer_m)
			list.add(datasourceContainer_pa);
		if(description_m)
			list.add(description_pa);
		if(key_m)
			list.add(key_pa);
		if(name_m)
			list.add(name_pa);
		if(parameterDefinitions_m)
			list.add(parameterDefinitions_pa);
		if(parameterInstances_m)
			list.add(parameterInstances_pa);
		if(reportMetadata_m)
			list.add(reportMetadata_pa);
		if(reportProperties_m)
			list.add(reportProperties_pa);
		if(uuid_m)
			list.add(uuid_pa);
		if(temporaryUid_m)
			list.add(temporaryUid_pa);
		if(parentReportKey_m)
			list.add(parentReportKey_pa);
		if(parentReportName_m)
			list.add(parentReportName_pa);
		if(parentReportDescription_m)
			list.add(parentReportDescription_pa);
		if(parentReportProperties_m)
			list.add(parentReportProperties_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(description_pa);
			list.add(name_pa);
			list.add(temporaryUid_pa);
			list.add(parentReportKey_pa);
			list.add(parentReportName_pa);
			list.add(parentReportDescription_pa);
			list.add(parentReportProperties_pa);
		}
		if(view.compareTo(DtoView.LIST) >= 0){
			list.add(key_pa);
			list.add(uuid_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(datasourceContainer_pa);
			list.add(parameterDefinitions_pa);
			list.add(parameterInstances_pa);
			list.add(reportMetadata_pa);
			list.add(reportProperties_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(datasourceContainer_pa);
		list.add(parameterDefinitions_pa);
		list.add(parameterInstances_pa);
		list.add(reportMetadata_pa);
		list.add(reportProperties_pa);
		return list;
	}



	net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportMetadataDto wl_0;
	net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto wl_1;
	net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto wl_2;
	net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto wl_3;
	net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportPropertyDto wl_4;

}
