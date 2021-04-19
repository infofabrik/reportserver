package net.datenwerke.rs.grideditor.client.grideditor.dto;

import com.google.gwt.core.client.GWT;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gf.base.client.dtogenerator.RsDto;
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
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorColumnConfigDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.pa.GridEditorConfigDtoPA;
import net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.GridEditorConfigDto2PosoMap;
import net.datenwerke.rs.grideditor.service.grideditor.definition.GridEditorConfig;

/**
 * Dto for {@link GridEditorConfig}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class GridEditorConfigDto extends RsDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private boolean canAddRecords;
	private  boolean canAddRecords_m;
	public static final String PROPERTY_CAN_ADD_RECORDS = "dpi-grideditorconfig-canaddrecords";

	private transient static PropertyAccessor<GridEditorConfigDto, Boolean> canAddRecords_pa = new PropertyAccessor<GridEditorConfigDto, Boolean>() {
		@Override
		public void setValue(GridEditorConfigDto container, Boolean object) {
			container.setCanAddRecords(object);
		}

		@Override
		public Boolean getValue(GridEditorConfigDto container) {
			return container.isCanAddRecords();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "canAddRecords";
		}

		@Override
		public void setModified(GridEditorConfigDto container, boolean modified) {
			container.canAddRecords_m = modified;
		}

		@Override
		public boolean isModified(GridEditorConfigDto container) {
			return container.isCanAddRecordsModified();
		}
	};

	private boolean canDuplicateRecords;
	private  boolean canDuplicateRecords_m;
	public static final String PROPERTY_CAN_DUPLICATE_RECORDS = "dpi-grideditorconfig-canduplicaterecords";

	private transient static PropertyAccessor<GridEditorConfigDto, Boolean> canDuplicateRecords_pa = new PropertyAccessor<GridEditorConfigDto, Boolean>() {
		@Override
		public void setValue(GridEditorConfigDto container, Boolean object) {
			container.setCanDuplicateRecords(object);
		}

		@Override
		public Boolean getValue(GridEditorConfigDto container) {
			return container.isCanDuplicateRecords();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "canDuplicateRecords";
		}

		@Override
		public void setModified(GridEditorConfigDto container, boolean modified) {
			container.canDuplicateRecords_m = modified;
		}

		@Override
		public boolean isModified(GridEditorConfigDto container) {
			return container.isCanDuplicateRecordsModified();
		}
	};

	private boolean canRemoveRecords;
	private  boolean canRemoveRecords_m;
	public static final String PROPERTY_CAN_REMOVE_RECORDS = "dpi-grideditorconfig-canremoverecords";

	private transient static PropertyAccessor<GridEditorConfigDto, Boolean> canRemoveRecords_pa = new PropertyAccessor<GridEditorConfigDto, Boolean>() {
		@Override
		public void setValue(GridEditorConfigDto container, Boolean object) {
			container.setCanRemoveRecords(object);
		}

		@Override
		public Boolean getValue(GridEditorConfigDto container) {
			return container.isCanRemoveRecords();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "canRemoveRecords";
		}

		@Override
		public void setModified(GridEditorConfigDto container, boolean modified) {
			container.canRemoveRecords_m = modified;
		}

		@Override
		public boolean isModified(GridEditorConfigDto container) {
			return container.isCanRemoveRecordsModified();
		}
	};

	private List<GridEditorColumnConfigDto> columnConfigs;
	private  boolean columnConfigs_m;
	public static final String PROPERTY_COLUMN_CONFIGS = "dpi-grideditorconfig-columnconfigs";

	private transient static PropertyAccessor<GridEditorConfigDto, List<GridEditorColumnConfigDto>> columnConfigs_pa = new PropertyAccessor<GridEditorConfigDto, List<GridEditorColumnConfigDto>>() {
		@Override
		public void setValue(GridEditorConfigDto container, List<GridEditorColumnConfigDto> object) {
			container.setColumnConfigs(object);
		}

		@Override
		public List<GridEditorColumnConfigDto> getValue(GridEditorConfigDto container) {
			return container.getColumnConfigs();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "columnConfigs";
		}

		@Override
		public void setModified(GridEditorConfigDto container, boolean modified) {
			container.columnConfigs_m = modified;
		}

		@Override
		public boolean isModified(GridEditorConfigDto container) {
			return container.isColumnConfigsModified();
		}
	};

	private int defaultPageSize;
	private  boolean defaultPageSize_m;
	public static final String PROPERTY_DEFAULT_PAGE_SIZE = "dpi-grideditorconfig-defaultpagesize";

	private transient static PropertyAccessor<GridEditorConfigDto, Integer> defaultPageSize_pa = new PropertyAccessor<GridEditorConfigDto, Integer>() {
		@Override
		public void setValue(GridEditorConfigDto container, Integer object) {
			container.setDefaultPageSize(object);
		}

		@Override
		public Integer getValue(GridEditorConfigDto container) {
			return container.getDefaultPageSize();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "defaultPageSize";
		}

		@Override
		public void setModified(GridEditorConfigDto container, boolean modified) {
			container.defaultPageSize_m = modified;
		}

		@Override
		public boolean isModified(GridEditorConfigDto container) {
			return container.isDefaultPageSizeModified();
		}
	};

	private boolean filterable;
	private  boolean filterable_m;
	public static final String PROPERTY_FILTERABLE = "dpi-grideditorconfig-filterable";

	private transient static PropertyAccessor<GridEditorConfigDto, Boolean> filterable_pa = new PropertyAccessor<GridEditorConfigDto, Boolean>() {
		@Override
		public void setValue(GridEditorConfigDto container, Boolean object) {
			container.setFilterable(object);
		}

		@Override
		public Boolean getValue(GridEditorConfigDto container) {
			return container.isFilterable();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "filterable";
		}

		@Override
		public void setModified(GridEditorConfigDto container, boolean modified) {
			container.filterable_m = modified;
		}

		@Override
		public boolean isModified(GridEditorConfigDto container) {
			return container.isFilterableModified();
		}
	};

	private boolean hasForm;
	private  boolean hasForm_m;
	public static final String PROPERTY_HAS_FORM = "dpi-grideditorconfig-hasform";

	private transient static PropertyAccessor<GridEditorConfigDto, Boolean> hasForm_pa = new PropertyAccessor<GridEditorConfigDto, Boolean>() {
		@Override
		public void setValue(GridEditorConfigDto container, Boolean object) {
			container.setHasForm(object);
		}

		@Override
		public Boolean getValue(GridEditorConfigDto container) {
			return container.isHasForm();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "hasForm";
		}

		@Override
		public void setModified(GridEditorConfigDto container, boolean modified) {
			container.hasForm_m = modified;
		}

		@Override
		public boolean isModified(GridEditorConfigDto container) {
			return container.isHasFormModified();
		}
	};

	private int maxPageSize;
	private  boolean maxPageSize_m;
	public static final String PROPERTY_MAX_PAGE_SIZE = "dpi-grideditorconfig-maxpagesize";

	private transient static PropertyAccessor<GridEditorConfigDto, Integer> maxPageSize_pa = new PropertyAccessor<GridEditorConfigDto, Integer>() {
		@Override
		public void setValue(GridEditorConfigDto container, Integer object) {
			container.setMaxPageSize(object);
		}

		@Override
		public Integer getValue(GridEditorConfigDto container) {
			return container.getMaxPageSize();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "maxPageSize";
		}

		@Override
		public void setModified(GridEditorConfigDto container, boolean modified) {
			container.maxPageSize_m = modified;
		}

		@Override
		public boolean isModified(GridEditorConfigDto container) {
			return container.isMaxPageSizeModified();
		}
	};

	private boolean paging;
	private  boolean paging_m;
	public static final String PROPERTY_PAGING = "dpi-grideditorconfig-paging";

	private transient static PropertyAccessor<GridEditorConfigDto, Boolean> paging_pa = new PropertyAccessor<GridEditorConfigDto, Boolean>() {
		@Override
		public void setValue(GridEditorConfigDto container, Boolean object) {
			container.setPaging(object);
		}

		@Override
		public Boolean getValue(GridEditorConfigDto container) {
			return container.isPaging();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "paging";
		}

		@Override
		public void setModified(GridEditorConfigDto container, boolean modified) {
			container.paging_m = modified;
		}

		@Override
		public boolean isModified(GridEditorConfigDto container) {
			return container.isPagingModified();
		}
	};

	private boolean sortable;
	private  boolean sortable_m;
	public static final String PROPERTY_SORTABLE = "dpi-grideditorconfig-sortable";

	private transient static PropertyAccessor<GridEditorConfigDto, Boolean> sortable_pa = new PropertyAccessor<GridEditorConfigDto, Boolean>() {
		@Override
		public void setValue(GridEditorConfigDto container, Boolean object) {
			container.setSortable(object);
		}

		@Override
		public Boolean getValue(GridEditorConfigDto container) {
			return container.isSortable();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "sortable";
		}

		@Override
		public void setModified(GridEditorConfigDto container, boolean modified) {
			container.sortable_m = modified;
		}

		@Override
		public boolean isModified(GridEditorConfigDto container) {
			return container.isSortableModified();
		}
	};

	private int totalNumberOfRecords;
	private  boolean totalNumberOfRecords_m;
	public static final String PROPERTY_TOTAL_NUMBER_OF_RECORDS = "dpi-grideditorconfig-totalnumberofrecords";

	private transient static PropertyAccessor<GridEditorConfigDto, Integer> totalNumberOfRecords_pa = new PropertyAccessor<GridEditorConfigDto, Integer>() {
		@Override
		public void setValue(GridEditorConfigDto container, Integer object) {
			container.setTotalNumberOfRecords(object);
		}

		@Override
		public Integer getValue(GridEditorConfigDto container) {
			return container.getTotalNumberOfRecords();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "totalNumberOfRecords";
		}

		@Override
		public void setModified(GridEditorConfigDto container, boolean modified) {
			container.totalNumberOfRecords_m = modified;
		}

		@Override
		public boolean isModified(GridEditorConfigDto container) {
			return container.isTotalNumberOfRecordsModified();
		}
	};


	public GridEditorConfigDto() {
		super();
	}

	public boolean isCanAddRecords()  {
		if(! isDtoProxy()){
			return this.canAddRecords;
		}

		if(isCanAddRecordsModified())
			return this.canAddRecords;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().canAddRecords());

		return _value;
	}


	public void setCanAddRecords(boolean canAddRecords)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isCanAddRecords();

		/* set new value */
		this.canAddRecords = canAddRecords;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(canAddRecords_pa, oldValue, canAddRecords, this.canAddRecords_m));

		/* set indicator */
		this.canAddRecords_m = true;

		this.fireObjectChangedEvent(GridEditorConfigDtoPA.INSTANCE.canAddRecords(), oldValue);
	}


	public boolean isCanAddRecordsModified()  {
		return canAddRecords_m;
	}


	public static PropertyAccessor<GridEditorConfigDto, Boolean> getCanAddRecordsPropertyAccessor()  {
		return canAddRecords_pa;
	}


	public boolean isCanDuplicateRecords()  {
		if(! isDtoProxy()){
			return this.canDuplicateRecords;
		}

		if(isCanDuplicateRecordsModified())
			return this.canDuplicateRecords;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().canDuplicateRecords());

		return _value;
	}


	public void setCanDuplicateRecords(boolean canDuplicateRecords)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isCanDuplicateRecords();

		/* set new value */
		this.canDuplicateRecords = canDuplicateRecords;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(canDuplicateRecords_pa, oldValue, canDuplicateRecords, this.canDuplicateRecords_m));

		/* set indicator */
		this.canDuplicateRecords_m = true;

		this.fireObjectChangedEvent(GridEditorConfigDtoPA.INSTANCE.canDuplicateRecords(), oldValue);
	}


	public boolean isCanDuplicateRecordsModified()  {
		return canDuplicateRecords_m;
	}


	public static PropertyAccessor<GridEditorConfigDto, Boolean> getCanDuplicateRecordsPropertyAccessor()  {
		return canDuplicateRecords_pa;
	}


	public boolean isCanRemoveRecords()  {
		if(! isDtoProxy()){
			return this.canRemoveRecords;
		}

		if(isCanRemoveRecordsModified())
			return this.canRemoveRecords;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().canRemoveRecords());

		return _value;
	}


	public void setCanRemoveRecords(boolean canRemoveRecords)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isCanRemoveRecords();

		/* set new value */
		this.canRemoveRecords = canRemoveRecords;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(canRemoveRecords_pa, oldValue, canRemoveRecords, this.canRemoveRecords_m));

		/* set indicator */
		this.canRemoveRecords_m = true;

		this.fireObjectChangedEvent(GridEditorConfigDtoPA.INSTANCE.canRemoveRecords(), oldValue);
	}


	public boolean isCanRemoveRecordsModified()  {
		return canRemoveRecords_m;
	}


	public static PropertyAccessor<GridEditorConfigDto, Boolean> getCanRemoveRecordsPropertyAccessor()  {
		return canRemoveRecords_pa;
	}


	public List<GridEditorColumnConfigDto> getColumnConfigs()  {
		if(! isDtoProxy()){
			List<GridEditorColumnConfigDto> _currentValue = this.columnConfigs;
			if(null == _currentValue)
				this.columnConfigs = new ArrayList<GridEditorColumnConfigDto>();

			return this.columnConfigs;
		}

		if(isColumnConfigsModified())
			return this.columnConfigs;

		if(! GWT.isClient())
			return null;

		List<GridEditorColumnConfigDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().columnConfigs());

		_value = new ChangeMonitoredList<GridEditorColumnConfigDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isColumnConfigsModified())
						setColumnConfigs((List<GridEditorColumnConfigDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setColumnConfigs(List<GridEditorColumnConfigDto> columnConfigs)  {
		/* old value */
		List<GridEditorColumnConfigDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getColumnConfigs();

		/* set new value */
		this.columnConfigs = columnConfigs;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(columnConfigs_pa, oldValue, columnConfigs, this.columnConfigs_m));

		/* set indicator */
		this.columnConfigs_m = true;

		this.fireObjectChangedEvent(GridEditorConfigDtoPA.INSTANCE.columnConfigs(), oldValue);
	}


	public boolean isColumnConfigsModified()  {
		return columnConfigs_m;
	}


	public static PropertyAccessor<GridEditorConfigDto, List<GridEditorColumnConfigDto>> getColumnConfigsPropertyAccessor()  {
		return columnConfigs_pa;
	}


	public int getDefaultPageSize()  {
		if(! isDtoProxy()){
			return this.defaultPageSize;
		}

		if(isDefaultPageSizeModified())
			return this.defaultPageSize;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().defaultPageSize());

		return _value;
	}


	public void setDefaultPageSize(int defaultPageSize)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getDefaultPageSize();

		/* set new value */
		this.defaultPageSize = defaultPageSize;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(defaultPageSize_pa, oldValue, defaultPageSize, this.defaultPageSize_m));

		/* set indicator */
		this.defaultPageSize_m = true;

		this.fireObjectChangedEvent(GridEditorConfigDtoPA.INSTANCE.defaultPageSize(), oldValue);
	}


	public boolean isDefaultPageSizeModified()  {
		return defaultPageSize_m;
	}


	public static PropertyAccessor<GridEditorConfigDto, Integer> getDefaultPageSizePropertyAccessor()  {
		return defaultPageSize_pa;
	}


	public boolean isFilterable()  {
		if(! isDtoProxy()){
			return this.filterable;
		}

		if(isFilterableModified())
			return this.filterable;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().filterable());

		return _value;
	}


	public void setFilterable(boolean filterable)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isFilterable();

		/* set new value */
		this.filterable = filterable;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(filterable_pa, oldValue, filterable, this.filterable_m));

		/* set indicator */
		this.filterable_m = true;

		this.fireObjectChangedEvent(GridEditorConfigDtoPA.INSTANCE.filterable(), oldValue);
	}


	public boolean isFilterableModified()  {
		return filterable_m;
	}


	public static PropertyAccessor<GridEditorConfigDto, Boolean> getFilterablePropertyAccessor()  {
		return filterable_pa;
	}


	public boolean isHasForm()  {
		if(! isDtoProxy()){
			return this.hasForm;
		}

		if(isHasFormModified())
			return this.hasForm;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().hasForm());

		return _value;
	}


	public void setHasForm(boolean hasForm)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isHasForm();

		/* set new value */
		this.hasForm = hasForm;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(hasForm_pa, oldValue, hasForm, this.hasForm_m));

		/* set indicator */
		this.hasForm_m = true;

		this.fireObjectChangedEvent(GridEditorConfigDtoPA.INSTANCE.hasForm(), oldValue);
	}


	public boolean isHasFormModified()  {
		return hasForm_m;
	}


	public static PropertyAccessor<GridEditorConfigDto, Boolean> getHasFormPropertyAccessor()  {
		return hasForm_pa;
	}


	public int getMaxPageSize()  {
		if(! isDtoProxy()){
			return this.maxPageSize;
		}

		if(isMaxPageSizeModified())
			return this.maxPageSize;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().maxPageSize());

		return _value;
	}


	public void setMaxPageSize(int maxPageSize)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getMaxPageSize();

		/* set new value */
		this.maxPageSize = maxPageSize;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(maxPageSize_pa, oldValue, maxPageSize, this.maxPageSize_m));

		/* set indicator */
		this.maxPageSize_m = true;

		this.fireObjectChangedEvent(GridEditorConfigDtoPA.INSTANCE.maxPageSize(), oldValue);
	}


	public boolean isMaxPageSizeModified()  {
		return maxPageSize_m;
	}


	public static PropertyAccessor<GridEditorConfigDto, Integer> getMaxPageSizePropertyAccessor()  {
		return maxPageSize_pa;
	}


	public boolean isPaging()  {
		if(! isDtoProxy()){
			return this.paging;
		}

		if(isPagingModified())
			return this.paging;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().paging());

		return _value;
	}


	public void setPaging(boolean paging)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isPaging();

		/* set new value */
		this.paging = paging;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(paging_pa, oldValue, paging, this.paging_m));

		/* set indicator */
		this.paging_m = true;

		this.fireObjectChangedEvent(GridEditorConfigDtoPA.INSTANCE.paging(), oldValue);
	}


	public boolean isPagingModified()  {
		return paging_m;
	}


	public static PropertyAccessor<GridEditorConfigDto, Boolean> getPagingPropertyAccessor()  {
		return paging_pa;
	}


	public boolean isSortable()  {
		if(! isDtoProxy()){
			return this.sortable;
		}

		if(isSortableModified())
			return this.sortable;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().sortable());

		return _value;
	}


	public void setSortable(boolean sortable)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isSortable();

		/* set new value */
		this.sortable = sortable;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(sortable_pa, oldValue, sortable, this.sortable_m));

		/* set indicator */
		this.sortable_m = true;

		this.fireObjectChangedEvent(GridEditorConfigDtoPA.INSTANCE.sortable(), oldValue);
	}


	public boolean isSortableModified()  {
		return sortable_m;
	}


	public static PropertyAccessor<GridEditorConfigDto, Boolean> getSortablePropertyAccessor()  {
		return sortable_pa;
	}


	public int getTotalNumberOfRecords()  {
		if(! isDtoProxy()){
			return this.totalNumberOfRecords;
		}

		if(isTotalNumberOfRecordsModified())
			return this.totalNumberOfRecords;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().totalNumberOfRecords());

		return _value;
	}


	public void setTotalNumberOfRecords(int totalNumberOfRecords)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getTotalNumberOfRecords();

		/* set new value */
		this.totalNumberOfRecords = totalNumberOfRecords;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(totalNumberOfRecords_pa, oldValue, totalNumberOfRecords, this.totalNumberOfRecords_m));

		/* set indicator */
		this.totalNumberOfRecords_m = true;

		this.fireObjectChangedEvent(GridEditorConfigDtoPA.INSTANCE.totalNumberOfRecords(), oldValue);
	}


	public boolean isTotalNumberOfRecordsModified()  {
		return totalNumberOfRecords_m;
	}


	public static PropertyAccessor<GridEditorConfigDto, Integer> getTotalNumberOfRecordsPropertyAccessor()  {
		return totalNumberOfRecords_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new GridEditorConfigDto2PosoMap();
	}

	public GridEditorConfigDtoPA instantiatePropertyAccess()  {
		return GWT.create(GridEditorConfigDtoPA.class);
	}

	public void clearModified()  {
		this.canAddRecords = false;
		this.canAddRecords_m = false;
		this.canDuplicateRecords = false;
		this.canDuplicateRecords_m = false;
		this.canRemoveRecords = false;
		this.canRemoveRecords_m = false;
		this.columnConfigs = null;
		this.columnConfigs_m = false;
		this.defaultPageSize = 0;
		this.defaultPageSize_m = false;
		this.filterable = false;
		this.filterable_m = false;
		this.hasForm = false;
		this.hasForm_m = false;
		this.maxPageSize = 0;
		this.maxPageSize_m = false;
		this.paging = false;
		this.paging_m = false;
		this.sortable = false;
		this.sortable_m = false;
		this.totalNumberOfRecords = 0;
		this.totalNumberOfRecords_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(canAddRecords_m)
			return true;
		if(canDuplicateRecords_m)
			return true;
		if(canRemoveRecords_m)
			return true;
		if(columnConfigs_m)
			return true;
		if(defaultPageSize_m)
			return true;
		if(filterable_m)
			return true;
		if(hasForm_m)
			return true;
		if(maxPageSize_m)
			return true;
		if(paging_m)
			return true;
		if(sortable_m)
			return true;
		if(totalNumberOfRecords_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(canAddRecords_pa);
		list.add(canDuplicateRecords_pa);
		list.add(canRemoveRecords_pa);
		list.add(columnConfigs_pa);
		list.add(defaultPageSize_pa);
		list.add(filterable_pa);
		list.add(hasForm_pa);
		list.add(maxPageSize_pa);
		list.add(paging_pa);
		list.add(sortable_pa);
		list.add(totalNumberOfRecords_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(canAddRecords_m)
			list.add(canAddRecords_pa);
		if(canDuplicateRecords_m)
			list.add(canDuplicateRecords_pa);
		if(canRemoveRecords_m)
			list.add(canRemoveRecords_pa);
		if(columnConfigs_m)
			list.add(columnConfigs_pa);
		if(defaultPageSize_m)
			list.add(defaultPageSize_pa);
		if(filterable_m)
			list.add(filterable_pa);
		if(hasForm_m)
			list.add(hasForm_pa);
		if(maxPageSize_m)
			list.add(maxPageSize_pa);
		if(paging_m)
			list.add(paging_pa);
		if(sortable_m)
			list.add(sortable_pa);
		if(totalNumberOfRecords_m)
			list.add(totalNumberOfRecords_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(canAddRecords_pa);
			list.add(canDuplicateRecords_pa);
			list.add(canRemoveRecords_pa);
			list.add(columnConfigs_pa);
			list.add(defaultPageSize_pa);
			list.add(filterable_pa);
			list.add(hasForm_pa);
			list.add(maxPageSize_pa);
			list.add(paging_pa);
			list.add(sortable_pa);
			list.add(totalNumberOfRecords_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(columnConfigs_pa);
		return list;
	}



	net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorColumnConfigDto wl_0;

}
