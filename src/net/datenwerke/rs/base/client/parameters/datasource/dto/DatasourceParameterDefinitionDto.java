package net.datenwerke.rs.base.client.parameters.datasource.dto;

import com.google.gwt.core.client.GWT;
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
import net.datenwerke.rs.base.client.parameters.datasource.dto.BoxLayoutModeDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.BoxLayoutPackModeDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDataDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.ModeDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.MultiSelectionModeDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.SingleSelectionModeDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.pa.DatasourceParameterDefinitionDtoPA;
import net.datenwerke.rs.base.client.parameters.datasource.dto.posomap.DatasourceParameterDefinitionDto2PosoMap;
import net.datenwerke.rs.base.client.parameters.locale.RsMessages;
import net.datenwerke.rs.base.service.parameters.datasource.DatasourceParameterDefinition;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerProviderDto;
import net.datenwerke.rs.core.client.parameters.dto.DatatypeDto;
import net.datenwerke.rs.core.client.parameters.dto.decorator.ParameterDefinitionDtoDec;

/**
 * Dto for {@link DatasourceParameterDefinition}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DatasourceParameterDefinitionDto extends ParameterDefinitionDtoDec implements DatasourceContainerProviderDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private BoxLayoutModeDto boxLayoutMode;
	private  boolean boxLayoutMode_m;
	public static final String PROPERTY_BOX_LAYOUT_MODE = "dpi-datasourceparameterdefinition-boxlayoutmode";

	private transient static PropertyAccessor<DatasourceParameterDefinitionDto, BoxLayoutModeDto> boxLayoutMode_pa = new PropertyAccessor<DatasourceParameterDefinitionDto, BoxLayoutModeDto>() {
		@Override
		public void setValue(DatasourceParameterDefinitionDto container, BoxLayoutModeDto object) {
			container.setBoxLayoutMode(object);
		}

		@Override
		public BoxLayoutModeDto getValue(DatasourceParameterDefinitionDto container) {
			return container.getBoxLayoutMode();
		}

		@Override
		public Class<?> getType() {
			return BoxLayoutModeDto.class;
		}

		@Override
		public String getPath() {
			return "boxLayoutMode";
		}

		@Override
		public void setModified(DatasourceParameterDefinitionDto container, boolean modified) {
			container.boxLayoutMode_m = modified;
		}

		@Override
		public boolean isModified(DatasourceParameterDefinitionDto container) {
			return container.isBoxLayoutModeModified();
		}
	};

	private int boxLayoutPackColSize;
	private  boolean boxLayoutPackColSize_m;
	public static final String PROPERTY_BOX_LAYOUT_PACK_COL_SIZE = "dpi-datasourceparameterdefinition-boxlayoutpackcolsize";

	private transient static PropertyAccessor<DatasourceParameterDefinitionDto, Integer> boxLayoutPackColSize_pa = new PropertyAccessor<DatasourceParameterDefinitionDto, Integer>() {
		@Override
		public void setValue(DatasourceParameterDefinitionDto container, Integer object) {
			container.setBoxLayoutPackColSize(object);
		}

		@Override
		public Integer getValue(DatasourceParameterDefinitionDto container) {
			return container.getBoxLayoutPackColSize();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "boxLayoutPackColSize";
		}

		@Override
		public void setModified(DatasourceParameterDefinitionDto container, boolean modified) {
			container.boxLayoutPackColSize_m = modified;
		}

		@Override
		public boolean isModified(DatasourceParameterDefinitionDto container) {
			return container.isBoxLayoutPackColSizeModified();
		}
	};

	private BoxLayoutPackModeDto boxLayoutPackMode;
	private  boolean boxLayoutPackMode_m;
	public static final String PROPERTY_BOX_LAYOUT_PACK_MODE = "dpi-datasourceparameterdefinition-boxlayoutpackmode";

	private transient static PropertyAccessor<DatasourceParameterDefinitionDto, BoxLayoutPackModeDto> boxLayoutPackMode_pa = new PropertyAccessor<DatasourceParameterDefinitionDto, BoxLayoutPackModeDto>() {
		@Override
		public void setValue(DatasourceParameterDefinitionDto container, BoxLayoutPackModeDto object) {
			container.setBoxLayoutPackMode(object);
		}

		@Override
		public BoxLayoutPackModeDto getValue(DatasourceParameterDefinitionDto container) {
			return container.getBoxLayoutPackMode();
		}

		@Override
		public Class<?> getType() {
			return BoxLayoutPackModeDto.class;
		}

		@Override
		public String getPath() {
			return "boxLayoutPackMode";
		}

		@Override
		public void setModified(DatasourceParameterDefinitionDto container, boolean modified) {
			container.boxLayoutPackMode_m = modified;
		}

		@Override
		public boolean isModified(DatasourceParameterDefinitionDto container) {
			return container.isBoxLayoutPackModeModified();
		}
	};

	private DatasourceContainerDto datasourceContainer;
	private  boolean datasourceContainer_m;
	public static final String PROPERTY_DATASOURCE_CONTAINER = "dpi-datasourceparameterdefinition-datasourcecontainer";

	private transient static PropertyAccessor<DatasourceParameterDefinitionDto, DatasourceContainerDto> datasourceContainer_pa = new PropertyAccessor<DatasourceParameterDefinitionDto, DatasourceContainerDto>() {
		@Override
		public void setValue(DatasourceParameterDefinitionDto container, DatasourceContainerDto object) {
			container.setDatasourceContainer(object);
		}

		@Override
		public DatasourceContainerDto getValue(DatasourceParameterDefinitionDto container) {
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
		public void setModified(DatasourceParameterDefinitionDto container, boolean modified) {
			container.datasourceContainer_m = modified;
		}

		@Override
		public boolean isModified(DatasourceParameterDefinitionDto container) {
			return container.isDatasourceContainerModified();
		}
	};

	private String format;
	private  boolean format_m;
	public static final String PROPERTY_FORMAT = "dpi-datasourceparameterdefinition-format";

	private transient static PropertyAccessor<DatasourceParameterDefinitionDto, String> format_pa = new PropertyAccessor<DatasourceParameterDefinitionDto, String>() {
		@Override
		public void setValue(DatasourceParameterDefinitionDto container, String object) {
			container.setFormat(object);
		}

		@Override
		public String getValue(DatasourceParameterDefinitionDto container) {
			return container.getFormat();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "format";
		}

		@Override
		public void setModified(DatasourceParameterDefinitionDto container, boolean modified) {
			container.format_m = modified;
		}

		@Override
		public boolean isModified(DatasourceParameterDefinitionDto container) {
			return container.isFormatModified();
		}
	};

	private int height;
	private  boolean height_m;
	public static final String PROPERTY_HEIGHT = "dpi-datasourceparameterdefinition-height";

	private transient static PropertyAccessor<DatasourceParameterDefinitionDto, Integer> height_pa = new PropertyAccessor<DatasourceParameterDefinitionDto, Integer>() {
		@Override
		public void setValue(DatasourceParameterDefinitionDto container, Integer object) {
			container.setHeight(object);
		}

		@Override
		public Integer getValue(DatasourceParameterDefinitionDto container) {
			return container.getHeight();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "height";
		}

		@Override
		public void setModified(DatasourceParameterDefinitionDto container, boolean modified) {
			container.height_m = modified;
		}

		@Override
		public boolean isModified(DatasourceParameterDefinitionDto container) {
			return container.isHeightModified();
		}
	};

	private ModeDto mode;
	private  boolean mode_m;
	public static final String PROPERTY_MODE = "dpi-datasourceparameterdefinition-mode";

	private transient static PropertyAccessor<DatasourceParameterDefinitionDto, ModeDto> mode_pa = new PropertyAccessor<DatasourceParameterDefinitionDto, ModeDto>() {
		@Override
		public void setValue(DatasourceParameterDefinitionDto container, ModeDto object) {
			container.setMode(object);
		}

		@Override
		public ModeDto getValue(DatasourceParameterDefinitionDto container) {
			return container.getMode();
		}

		@Override
		public Class<?> getType() {
			return ModeDto.class;
		}

		@Override
		public String getPath() {
			return "mode";
		}

		@Override
		public void setModified(DatasourceParameterDefinitionDto container, boolean modified) {
			container.mode_m = modified;
		}

		@Override
		public boolean isModified(DatasourceParameterDefinitionDto container) {
			return container.isModeModified();
		}
	};

	private List<DatasourceParameterDataDto> multiDefaultValueSimpleData;
	private  boolean multiDefaultValueSimpleData_m;
	public static final String PROPERTY_MULTI_DEFAULT_VALUE_SIMPLE_DATA = "dpi-datasourceparameterdefinition-multidefaultvaluesimpledata";

	private transient static PropertyAccessor<DatasourceParameterDefinitionDto, List<DatasourceParameterDataDto>> multiDefaultValueSimpleData_pa = new PropertyAccessor<DatasourceParameterDefinitionDto, List<DatasourceParameterDataDto>>() {
		@Override
		public void setValue(DatasourceParameterDefinitionDto container, List<DatasourceParameterDataDto> object) {
			container.setMultiDefaultValueSimpleData(object);
		}

		@Override
		public List<DatasourceParameterDataDto> getValue(DatasourceParameterDefinitionDto container) {
			return container.getMultiDefaultValueSimpleData();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "multiDefaultValueSimpleData";
		}

		@Override
		public void setModified(DatasourceParameterDefinitionDto container, boolean modified) {
			container.multiDefaultValueSimpleData_m = modified;
		}

		@Override
		public boolean isModified(DatasourceParameterDefinitionDto container) {
			return container.isMultiDefaultValueSimpleDataModified();
		}
	};

	private MultiSelectionModeDto multiSelectionMode;
	private  boolean multiSelectionMode_m;
	public static final String PROPERTY_MULTI_SELECTION_MODE = "dpi-datasourceparameterdefinition-multiselectionmode";

	private transient static PropertyAccessor<DatasourceParameterDefinitionDto, MultiSelectionModeDto> multiSelectionMode_pa = new PropertyAccessor<DatasourceParameterDefinitionDto, MultiSelectionModeDto>() {
		@Override
		public void setValue(DatasourceParameterDefinitionDto container, MultiSelectionModeDto object) {
			container.setMultiSelectionMode(object);
		}

		@Override
		public MultiSelectionModeDto getValue(DatasourceParameterDefinitionDto container) {
			return container.getMultiSelectionMode();
		}

		@Override
		public Class<?> getType() {
			return MultiSelectionModeDto.class;
		}

		@Override
		public String getPath() {
			return "multiSelectionMode";
		}

		@Override
		public void setModified(DatasourceParameterDefinitionDto container, boolean modified) {
			container.multiSelectionMode_m = modified;
		}

		@Override
		public boolean isModified(DatasourceParameterDefinitionDto container) {
			return container.isMultiSelectionModeModified();
		}
	};

	private String postProcess;
	private  boolean postProcess_m;
	public static final String PROPERTY_POST_PROCESS = "dpi-datasourceparameterdefinition-postprocess";

	private transient static PropertyAccessor<DatasourceParameterDefinitionDto, String> postProcess_pa = new PropertyAccessor<DatasourceParameterDefinitionDto, String>() {
		@Override
		public void setValue(DatasourceParameterDefinitionDto container, String object) {
			container.setPostProcess(object);
		}

		@Override
		public String getValue(DatasourceParameterDefinitionDto container) {
			return container.getPostProcess();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "postProcess";
		}

		@Override
		public void setModified(DatasourceParameterDefinitionDto container, boolean modified) {
			container.postProcess_m = modified;
		}

		@Override
		public boolean isModified(DatasourceParameterDefinitionDto container) {
			return container.isPostProcessModified();
		}
	};

	private DatatypeDto returnType;
	private  boolean returnType_m;
	public static final String PROPERTY_RETURN_TYPE = "dpi-datasourceparameterdefinition-returntype";

	private transient static PropertyAccessor<DatasourceParameterDefinitionDto, DatatypeDto> returnType_pa = new PropertyAccessor<DatasourceParameterDefinitionDto, DatatypeDto>() {
		@Override
		public void setValue(DatasourceParameterDefinitionDto container, DatatypeDto object) {
			container.setReturnType(object);
		}

		@Override
		public DatatypeDto getValue(DatasourceParameterDefinitionDto container) {
			return container.getReturnType();
		}

		@Override
		public Class<?> getType() {
			return DatatypeDto.class;
		}

		@Override
		public String getPath() {
			return "returnType";
		}

		@Override
		public void setModified(DatasourceParameterDefinitionDto container, boolean modified) {
			container.returnType_m = modified;
		}

		@Override
		public boolean isModified(DatasourceParameterDefinitionDto container) {
			return container.isReturnTypeModified();
		}
	};

	private DatasourceParameterDataDto singleDefaultValueSimpleData;
	private  boolean singleDefaultValueSimpleData_m;
	public static final String PROPERTY_SINGLE_DEFAULT_VALUE_SIMPLE_DATA = "dpi-datasourceparameterdefinition-singledefaultvaluesimpledata";

	private transient static PropertyAccessor<DatasourceParameterDefinitionDto, DatasourceParameterDataDto> singleDefaultValueSimpleData_pa = new PropertyAccessor<DatasourceParameterDefinitionDto, DatasourceParameterDataDto>() {
		@Override
		public void setValue(DatasourceParameterDefinitionDto container, DatasourceParameterDataDto object) {
			container.setSingleDefaultValueSimpleData(object);
		}

		@Override
		public DatasourceParameterDataDto getValue(DatasourceParameterDefinitionDto container) {
			return container.getSingleDefaultValueSimpleData();
		}

		@Override
		public Class<?> getType() {
			return DatasourceParameterDataDto.class;
		}

		@Override
		public String getPath() {
			return "singleDefaultValueSimpleData";
		}

		@Override
		public void setModified(DatasourceParameterDefinitionDto container, boolean modified) {
			container.singleDefaultValueSimpleData_m = modified;
		}

		@Override
		public boolean isModified(DatasourceParameterDefinitionDto container) {
			return container.isSingleDefaultValueSimpleDataModified();
		}
	};

	private SingleSelectionModeDto singleSelectionMode;
	private  boolean singleSelectionMode_m;
	public static final String PROPERTY_SINGLE_SELECTION_MODE = "dpi-datasourceparameterdefinition-singleselectionmode";

	private transient static PropertyAccessor<DatasourceParameterDefinitionDto, SingleSelectionModeDto> singleSelectionMode_pa = new PropertyAccessor<DatasourceParameterDefinitionDto, SingleSelectionModeDto>() {
		@Override
		public void setValue(DatasourceParameterDefinitionDto container, SingleSelectionModeDto object) {
			container.setSingleSelectionMode(object);
		}

		@Override
		public SingleSelectionModeDto getValue(DatasourceParameterDefinitionDto container) {
			return container.getSingleSelectionMode();
		}

		@Override
		public Class<?> getType() {
			return SingleSelectionModeDto.class;
		}

		@Override
		public String getPath() {
			return "singleSelectionMode";
		}

		@Override
		public void setModified(DatasourceParameterDefinitionDto container, boolean modified) {
			container.singleSelectionMode_m = modified;
		}

		@Override
		public boolean isModified(DatasourceParameterDefinitionDto container) {
			return container.isSingleSelectionModeModified();
		}
	};

	private int width;
	private  boolean width_m;
	public static final String PROPERTY_WIDTH = "dpi-datasourceparameterdefinition-width";

	private transient static PropertyAccessor<DatasourceParameterDefinitionDto, Integer> width_pa = new PropertyAccessor<DatasourceParameterDefinitionDto, Integer>() {
		@Override
		public void setValue(DatasourceParameterDefinitionDto container, Integer object) {
			container.setWidth(object);
		}

		@Override
		public Integer getValue(DatasourceParameterDefinitionDto container) {
			return container.getWidth();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "width";
		}

		@Override
		public void setModified(DatasourceParameterDefinitionDto container, boolean modified) {
			container.width_m = modified;
		}

		@Override
		public boolean isModified(DatasourceParameterDefinitionDto container) {
			return container.isWidthModified();
		}
	};


	public DatasourceParameterDefinitionDto() {
		super();
	}

	public BoxLayoutModeDto getBoxLayoutMode()  {
		if(! isDtoProxy()){
			return this.boxLayoutMode;
		}

		if(isBoxLayoutModeModified())
			return this.boxLayoutMode;

		if(! GWT.isClient())
			return null;

		BoxLayoutModeDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().boxLayoutMode());

		return _value;
	}


	public void setBoxLayoutMode(BoxLayoutModeDto boxLayoutMode)  {
		/* old value */
		BoxLayoutModeDto oldValue = null;
		if(GWT.isClient())
			oldValue = getBoxLayoutMode();

		/* set new value */
		this.boxLayoutMode = boxLayoutMode;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(boxLayoutMode_pa, oldValue, boxLayoutMode, this.boxLayoutMode_m));

		/* set indicator */
		this.boxLayoutMode_m = true;

		this.fireObjectChangedEvent(DatasourceParameterDefinitionDtoPA.INSTANCE.boxLayoutMode(), oldValue);
	}


	public boolean isBoxLayoutModeModified()  {
		return boxLayoutMode_m;
	}


	public static PropertyAccessor<DatasourceParameterDefinitionDto, BoxLayoutModeDto> getBoxLayoutModePropertyAccessor()  {
		return boxLayoutMode_pa;
	}


	public int getBoxLayoutPackColSize()  {
		if(! isDtoProxy()){
			return this.boxLayoutPackColSize;
		}

		if(isBoxLayoutPackColSizeModified())
			return this.boxLayoutPackColSize;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().boxLayoutPackColSize());

		return _value;
	}


	public void setBoxLayoutPackColSize(int boxLayoutPackColSize)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getBoxLayoutPackColSize();

		/* set new value */
		this.boxLayoutPackColSize = boxLayoutPackColSize;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(boxLayoutPackColSize_pa, oldValue, boxLayoutPackColSize, this.boxLayoutPackColSize_m));

		/* set indicator */
		this.boxLayoutPackColSize_m = true;

		this.fireObjectChangedEvent(DatasourceParameterDefinitionDtoPA.INSTANCE.boxLayoutPackColSize(), oldValue);
	}


	public boolean isBoxLayoutPackColSizeModified()  {
		return boxLayoutPackColSize_m;
	}


	public static PropertyAccessor<DatasourceParameterDefinitionDto, Integer> getBoxLayoutPackColSizePropertyAccessor()  {
		return boxLayoutPackColSize_pa;
	}


	public BoxLayoutPackModeDto getBoxLayoutPackMode()  {
		if(! isDtoProxy()){
			return this.boxLayoutPackMode;
		}

		if(isBoxLayoutPackModeModified())
			return this.boxLayoutPackMode;

		if(! GWT.isClient())
			return null;

		BoxLayoutPackModeDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().boxLayoutPackMode());

		return _value;
	}


	public void setBoxLayoutPackMode(BoxLayoutPackModeDto boxLayoutPackMode)  {
		/* old value */
		BoxLayoutPackModeDto oldValue = null;
		if(GWT.isClient())
			oldValue = getBoxLayoutPackMode();

		/* set new value */
		this.boxLayoutPackMode = boxLayoutPackMode;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(boxLayoutPackMode_pa, oldValue, boxLayoutPackMode, this.boxLayoutPackMode_m));

		/* set indicator */
		this.boxLayoutPackMode_m = true;

		this.fireObjectChangedEvent(DatasourceParameterDefinitionDtoPA.INSTANCE.boxLayoutPackMode(), oldValue);
	}


	public boolean isBoxLayoutPackModeModified()  {
		return boxLayoutPackMode_m;
	}


	public static PropertyAccessor<DatasourceParameterDefinitionDto, BoxLayoutPackModeDto> getBoxLayoutPackModePropertyAccessor()  {
		return boxLayoutPackMode_pa;
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

		this.fireObjectChangedEvent(DatasourceParameterDefinitionDtoPA.INSTANCE.datasourceContainer(), oldValue);
	}


	public boolean isDatasourceContainerModified()  {
		return datasourceContainer_m;
	}


	public static PropertyAccessor<DatasourceParameterDefinitionDto, DatasourceContainerDto> getDatasourceContainerPropertyAccessor()  {
		return datasourceContainer_pa;
	}


	public String getFormat()  {
		if(! isDtoProxy()){
			return this.format;
		}

		if(isFormatModified())
			return this.format;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().format());

		return _value;
	}


	public void setFormat(String format)  {
		/* old value */
		String oldValue = null;
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

		this.fireObjectChangedEvent(DatasourceParameterDefinitionDtoPA.INSTANCE.format(), oldValue);
	}


	public boolean isFormatModified()  {
		return format_m;
	}


	public static PropertyAccessor<DatasourceParameterDefinitionDto, String> getFormatPropertyAccessor()  {
		return format_pa;
	}


	public int getHeight()  {
		if(! isDtoProxy()){
			return this.height;
		}

		if(isHeightModified())
			return this.height;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().height());

		return _value;
	}


	public void setHeight(int height)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getHeight();

		/* set new value */
		this.height = height;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(height_pa, oldValue, height, this.height_m));

		/* set indicator */
		this.height_m = true;

		this.fireObjectChangedEvent(DatasourceParameterDefinitionDtoPA.INSTANCE.height(), oldValue);
	}


	public boolean isHeightModified()  {
		return height_m;
	}


	public static PropertyAccessor<DatasourceParameterDefinitionDto, Integer> getHeightPropertyAccessor()  {
		return height_pa;
	}


	public ModeDto getMode()  {
		if(! isDtoProxy()){
			return this.mode;
		}

		if(isModeModified())
			return this.mode;

		if(! GWT.isClient())
			return null;

		ModeDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().mode());

		return _value;
	}


	public void setMode(ModeDto mode)  {
		/* old value */
		ModeDto oldValue = null;
		if(GWT.isClient())
			oldValue = getMode();

		/* set new value */
		this.mode = mode;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(mode_pa, oldValue, mode, this.mode_m));

		/* set indicator */
		this.mode_m = true;

		this.fireObjectChangedEvent(DatasourceParameterDefinitionDtoPA.INSTANCE.mode(), oldValue);
	}


	public boolean isModeModified()  {
		return mode_m;
	}


	public static PropertyAccessor<DatasourceParameterDefinitionDto, ModeDto> getModePropertyAccessor()  {
		return mode_pa;
	}


	public List<DatasourceParameterDataDto> getMultiDefaultValueSimpleData()  {
		if(! isDtoProxy()){
			List<DatasourceParameterDataDto> _currentValue = this.multiDefaultValueSimpleData;
			if(null == _currentValue)
				this.multiDefaultValueSimpleData = new ArrayList<DatasourceParameterDataDto>();

			return this.multiDefaultValueSimpleData;
		}

		if(isMultiDefaultValueSimpleDataModified())
			return this.multiDefaultValueSimpleData;

		if(! GWT.isClient())
			return null;

		List<DatasourceParameterDataDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().multiDefaultValueSimpleData());

		_value = new ChangeMonitoredList<DatasourceParameterDataDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isMultiDefaultValueSimpleDataModified())
						setMultiDefaultValueSimpleData((List<DatasourceParameterDataDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setMultiDefaultValueSimpleData(List<DatasourceParameterDataDto> multiDefaultValueSimpleData)  {
		/* old value */
		List<DatasourceParameterDataDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getMultiDefaultValueSimpleData();

		/* set new value */
		this.multiDefaultValueSimpleData = multiDefaultValueSimpleData;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(multiDefaultValueSimpleData_pa, oldValue, multiDefaultValueSimpleData, this.multiDefaultValueSimpleData_m));

		/* set indicator */
		this.multiDefaultValueSimpleData_m = true;

		this.fireObjectChangedEvent(DatasourceParameterDefinitionDtoPA.INSTANCE.multiDefaultValueSimpleData(), oldValue);
	}


	public boolean isMultiDefaultValueSimpleDataModified()  {
		return multiDefaultValueSimpleData_m;
	}


	public static PropertyAccessor<DatasourceParameterDefinitionDto, List<DatasourceParameterDataDto>> getMultiDefaultValueSimpleDataPropertyAccessor()  {
		return multiDefaultValueSimpleData_pa;
	}


	public MultiSelectionModeDto getMultiSelectionMode()  {
		if(! isDtoProxy()){
			return this.multiSelectionMode;
		}

		if(isMultiSelectionModeModified())
			return this.multiSelectionMode;

		if(! GWT.isClient())
			return null;

		MultiSelectionModeDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().multiSelectionMode());

		return _value;
	}


	public void setMultiSelectionMode(MultiSelectionModeDto multiSelectionMode)  {
		/* old value */
		MultiSelectionModeDto oldValue = null;
		if(GWT.isClient())
			oldValue = getMultiSelectionMode();

		/* set new value */
		this.multiSelectionMode = multiSelectionMode;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(multiSelectionMode_pa, oldValue, multiSelectionMode, this.multiSelectionMode_m));

		/* set indicator */
		this.multiSelectionMode_m = true;

		this.fireObjectChangedEvent(DatasourceParameterDefinitionDtoPA.INSTANCE.multiSelectionMode(), oldValue);
	}


	public boolean isMultiSelectionModeModified()  {
		return multiSelectionMode_m;
	}


	public static PropertyAccessor<DatasourceParameterDefinitionDto, MultiSelectionModeDto> getMultiSelectionModePropertyAccessor()  {
		return multiSelectionMode_pa;
	}


	public String getPostProcess()  {
		if(! isDtoProxy()){
			return this.postProcess;
		}

		if(isPostProcessModified())
			return this.postProcess;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().postProcess());

		return _value;
	}


	public void setPostProcess(String postProcess)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getPostProcess();

		/* set new value */
		this.postProcess = postProcess;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(postProcess_pa, oldValue, postProcess, this.postProcess_m));

		/* set indicator */
		this.postProcess_m = true;

		this.fireObjectChangedEvent(DatasourceParameterDefinitionDtoPA.INSTANCE.postProcess(), oldValue);
	}


	public boolean isPostProcessModified()  {
		return postProcess_m;
	}


	public static PropertyAccessor<DatasourceParameterDefinitionDto, String> getPostProcessPropertyAccessor()  {
		return postProcess_pa;
	}


	public DatatypeDto getReturnType()  {
		if(! isDtoProxy()){
			return this.returnType;
		}

		if(isReturnTypeModified())
			return this.returnType;

		if(! GWT.isClient())
			return null;

		DatatypeDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().returnType());

		return _value;
	}


	public void setReturnType(DatatypeDto returnType)  {
		/* old value */
		DatatypeDto oldValue = null;
		if(GWT.isClient())
			oldValue = getReturnType();

		/* set new value */
		this.returnType = returnType;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(returnType_pa, oldValue, returnType, this.returnType_m));

		/* set indicator */
		this.returnType_m = true;

		this.fireObjectChangedEvent(DatasourceParameterDefinitionDtoPA.INSTANCE.returnType(), oldValue);
	}


	public boolean isReturnTypeModified()  {
		return returnType_m;
	}


	public static PropertyAccessor<DatasourceParameterDefinitionDto, DatatypeDto> getReturnTypePropertyAccessor()  {
		return returnType_pa;
	}


	public DatasourceParameterDataDto getSingleDefaultValueSimpleData()  {
		if(! isDtoProxy()){
			return this.singleDefaultValueSimpleData;
		}

		if(isSingleDefaultValueSimpleDataModified())
			return this.singleDefaultValueSimpleData;

		if(! GWT.isClient())
			return null;

		DatasourceParameterDataDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().singleDefaultValueSimpleData());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isSingleDefaultValueSimpleDataModified())
						setSingleDefaultValueSimpleData((DatasourceParameterDataDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setSingleDefaultValueSimpleData(DatasourceParameterDataDto singleDefaultValueSimpleData)  {
		/* old value */
		DatasourceParameterDataDto oldValue = null;
		if(GWT.isClient())
			oldValue = getSingleDefaultValueSimpleData();

		/* set new value */
		this.singleDefaultValueSimpleData = singleDefaultValueSimpleData;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(singleDefaultValueSimpleData_pa, oldValue, singleDefaultValueSimpleData, this.singleDefaultValueSimpleData_m));

		/* set indicator */
		this.singleDefaultValueSimpleData_m = true;

		this.fireObjectChangedEvent(DatasourceParameterDefinitionDtoPA.INSTANCE.singleDefaultValueSimpleData(), oldValue);
	}


	public boolean isSingleDefaultValueSimpleDataModified()  {
		return singleDefaultValueSimpleData_m;
	}


	public static PropertyAccessor<DatasourceParameterDefinitionDto, DatasourceParameterDataDto> getSingleDefaultValueSimpleDataPropertyAccessor()  {
		return singleDefaultValueSimpleData_pa;
	}


	public SingleSelectionModeDto getSingleSelectionMode()  {
		if(! isDtoProxy()){
			return this.singleSelectionMode;
		}

		if(isSingleSelectionModeModified())
			return this.singleSelectionMode;

		if(! GWT.isClient())
			return null;

		SingleSelectionModeDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().singleSelectionMode());

		return _value;
	}


	public void setSingleSelectionMode(SingleSelectionModeDto singleSelectionMode)  {
		/* old value */
		SingleSelectionModeDto oldValue = null;
		if(GWT.isClient())
			oldValue = getSingleSelectionMode();

		/* set new value */
		this.singleSelectionMode = singleSelectionMode;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(singleSelectionMode_pa, oldValue, singleSelectionMode, this.singleSelectionMode_m));

		/* set indicator */
		this.singleSelectionMode_m = true;

		this.fireObjectChangedEvent(DatasourceParameterDefinitionDtoPA.INSTANCE.singleSelectionMode(), oldValue);
	}


	public boolean isSingleSelectionModeModified()  {
		return singleSelectionMode_m;
	}


	public static PropertyAccessor<DatasourceParameterDefinitionDto, SingleSelectionModeDto> getSingleSelectionModePropertyAccessor()  {
		return singleSelectionMode_pa;
	}


	public int getWidth()  {
		if(! isDtoProxy()){
			return this.width;
		}

		if(isWidthModified())
			return this.width;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().width());

		return _value;
	}


	public void setWidth(int width)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getWidth();

		/* set new value */
		this.width = width;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(width_pa, oldValue, width, this.width_m));

		/* set indicator */
		this.width_m = true;

		this.fireObjectChangedEvent(DatasourceParameterDefinitionDtoPA.INSTANCE.width(), oldValue);
	}


	public boolean isWidthModified()  {
		return width_m;
	}


	public static PropertyAccessor<DatasourceParameterDefinitionDto, Integer> getWidthPropertyAccessor()  {
		return width_pa;
	}


	@Override
	public String toDisplayTitle()  {
		try{
			return RsMessages.INSTANCE.datasourceParameterText();
		} catch(NullPointerException e){
			return BaseMessages.INSTANCE.unnamed();
		}
	}

	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof DatasourceParameterDefinitionDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((DatasourceParameterDefinitionDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new DatasourceParameterDefinitionDto2PosoMap();
	}

	public DatasourceParameterDefinitionDtoPA instantiatePropertyAccess()  {
		return GWT.create(DatasourceParameterDefinitionDtoPA.class);
	}

	public void clearModified()  {
		this.boxLayoutMode = null;
		this.boxLayoutMode_m = false;
		this.boxLayoutPackColSize = 0;
		this.boxLayoutPackColSize_m = false;
		this.boxLayoutPackMode = null;
		this.boxLayoutPackMode_m = false;
		this.datasourceContainer = null;
		this.datasourceContainer_m = false;
		this.format = null;
		this.format_m = false;
		this.height = 0;
		this.height_m = false;
		this.mode = null;
		this.mode_m = false;
		this.multiDefaultValueSimpleData = null;
		this.multiDefaultValueSimpleData_m = false;
		this.multiSelectionMode = null;
		this.multiSelectionMode_m = false;
		this.postProcess = null;
		this.postProcess_m = false;
		this.returnType = null;
		this.returnType_m = false;
		this.singleDefaultValueSimpleData = null;
		this.singleDefaultValueSimpleData_m = false;
		this.singleSelectionMode = null;
		this.singleSelectionMode_m = false;
		this.width = 0;
		this.width_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(boxLayoutMode_m)
			return true;
		if(boxLayoutPackColSize_m)
			return true;
		if(boxLayoutPackMode_m)
			return true;
		if(datasourceContainer_m)
			return true;
		if(format_m)
			return true;
		if(height_m)
			return true;
		if(mode_m)
			return true;
		if(multiDefaultValueSimpleData_m)
			return true;
		if(multiSelectionMode_m)
			return true;
		if(postProcess_m)
			return true;
		if(returnType_m)
			return true;
		if(singleDefaultValueSimpleData_m)
			return true;
		if(singleSelectionMode_m)
			return true;
		if(width_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(boxLayoutMode_pa);
		list.add(boxLayoutPackColSize_pa);
		list.add(boxLayoutPackMode_pa);
		list.add(datasourceContainer_pa);
		list.add(format_pa);
		list.add(height_pa);
		list.add(mode_pa);
		list.add(multiDefaultValueSimpleData_pa);
		list.add(multiSelectionMode_pa);
		list.add(postProcess_pa);
		list.add(returnType_pa);
		list.add(singleDefaultValueSimpleData_pa);
		list.add(singleSelectionMode_pa);
		list.add(width_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(boxLayoutMode_m)
			list.add(boxLayoutMode_pa);
		if(boxLayoutPackColSize_m)
			list.add(boxLayoutPackColSize_pa);
		if(boxLayoutPackMode_m)
			list.add(boxLayoutPackMode_pa);
		if(datasourceContainer_m)
			list.add(datasourceContainer_pa);
		if(format_m)
			list.add(format_pa);
		if(height_m)
			list.add(height_pa);
		if(mode_m)
			list.add(mode_pa);
		if(multiDefaultValueSimpleData_m)
			list.add(multiDefaultValueSimpleData_pa);
		if(multiSelectionMode_m)
			list.add(multiSelectionMode_pa);
		if(postProcess_m)
			list.add(postProcess_pa);
		if(returnType_m)
			list.add(returnType_pa);
		if(singleDefaultValueSimpleData_m)
			list.add(singleDefaultValueSimpleData_pa);
		if(singleSelectionMode_m)
			list.add(singleSelectionMode_pa);
		if(width_m)
			list.add(width_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(boxLayoutMode_pa);
			list.add(boxLayoutPackColSize_pa);
			list.add(boxLayoutPackMode_pa);
			list.add(datasourceContainer_pa);
			list.add(format_pa);
			list.add(height_pa);
			list.add(mode_pa);
			list.add(multiDefaultValueSimpleData_pa);
			list.add(multiSelectionMode_pa);
			list.add(postProcess_pa);
			list.add(returnType_pa);
			list.add(singleDefaultValueSimpleData_pa);
			list.add(singleSelectionMode_pa);
			list.add(width_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(datasourceContainer_pa);
		list.add(multiDefaultValueSimpleData_pa);
		list.add(singleDefaultValueSimpleData_pa);
		return list;
	}



	net.datenwerke.rs.base.client.parameters.datasource.dto.ModeDto wl_0;
	net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto wl_1;
	net.datenwerke.rs.base.client.parameters.datasource.dto.BoxLayoutPackModeDto wl_2;
	net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDataDto wl_3;
	net.datenwerke.rs.core.client.parameters.dto.DatatypeDto wl_4;
	net.datenwerke.rs.base.client.parameters.datasource.dto.MultiSelectionModeDto wl_5;
	net.datenwerke.rs.base.client.parameters.datasource.dto.BoxLayoutModeDto wl_6;
	net.datenwerke.rs.base.client.parameters.datasource.dto.SingleSelectionModeDto wl_7;

}
