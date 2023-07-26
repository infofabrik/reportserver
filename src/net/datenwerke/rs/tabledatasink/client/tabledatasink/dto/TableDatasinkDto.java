package net.datenwerke.rs.tabledatasink.client.tabledatasink.dto;

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
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerProviderDto;
import net.datenwerke.rs.tabledatasink.client.tabledatasink.dto.pa.TableDatasinkDtoPA;
import net.datenwerke.rs.tabledatasink.client.tabledatasink.dto.posomap.TableDatasinkDto2PosoMap;
import net.datenwerke.rs.tabledatasink.service.tabledatasink.definitions.TableDatasink;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * Dto for {@link TableDatasink}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class TableDatasinkDto extends DatasinkDefinitionDto implements DatasourceContainerProviderDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private int batchSize;
	private  boolean batchSize_m;
	public static final String PROPERTY_BATCH_SIZE = "dpi-tabledatasink-batchsize";

	private transient static PropertyAccessor<TableDatasinkDto, Integer> batchSize_pa = new PropertyAccessor<TableDatasinkDto, Integer>() {
		@Override
		public void setValue(TableDatasinkDto container, Integer object) {
			container.setBatchSize(object);
		}

		@Override
		public Integer getValue(TableDatasinkDto container) {
			return container.getBatchSize();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "batchSize";
		}

		@Override
		public void setModified(TableDatasinkDto container, boolean modified) {
			container.batchSize_m = modified;
		}

		@Override
		public boolean isModified(TableDatasinkDto container) {
			return container.isBatchSizeModified();
		}
	};

	private boolean copyPrimaryKeys;
	private  boolean copyPrimaryKeys_m;
	public static final String PROPERTY_COPY_PRIMARY_KEYS = "dpi-tabledatasink-copyprimarykeys";

	private transient static PropertyAccessor<TableDatasinkDto, Boolean> copyPrimaryKeys_pa = new PropertyAccessor<TableDatasinkDto, Boolean>() {
		@Override
		public void setValue(TableDatasinkDto container, Boolean object) {
			container.setCopyPrimaryKeys(object);
		}

		@Override
		public Boolean getValue(TableDatasinkDto container) {
			return container.isCopyPrimaryKeys();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "copyPrimaryKeys";
		}

		@Override
		public void setModified(TableDatasinkDto container, boolean modified) {
			container.copyPrimaryKeys_m = modified;
		}

		@Override
		public boolean isModified(TableDatasinkDto container) {
			return container.isCopyPrimaryKeysModified();
		}
	};

	private DatasourceContainerDto datasourceContainer;
	private  boolean datasourceContainer_m;
	public static final String PROPERTY_DATASOURCE_CONTAINER = "dpi-tabledatasink-datasourcecontainer";

	private transient static PropertyAccessor<TableDatasinkDto, DatasourceContainerDto> datasourceContainer_pa = new PropertyAccessor<TableDatasinkDto, DatasourceContainerDto>() {
		@Override
		public void setValue(TableDatasinkDto container, DatasourceContainerDto object) {
			container.setDatasourceContainer(object);
		}

		@Override
		public DatasourceContainerDto getValue(TableDatasinkDto container) {
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
		public void setModified(TableDatasinkDto container, boolean modified) {
			container.datasourceContainer_m = modified;
		}

		@Override
		public boolean isModified(TableDatasinkDto container) {
			return container.isDatasourceContainerModified();
		}
	};

	private String primaryKeys;
	private  boolean primaryKeys_m;
	public static final String PROPERTY_PRIMARY_KEYS = "dpi-tabledatasink-primarykeys";

	private transient static PropertyAccessor<TableDatasinkDto, String> primaryKeys_pa = new PropertyAccessor<TableDatasinkDto, String>() {
		@Override
		public void setValue(TableDatasinkDto container, String object) {
			container.setPrimaryKeys(object);
		}

		@Override
		public String getValue(TableDatasinkDto container) {
			return container.getPrimaryKeys();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "primaryKeys";
		}

		@Override
		public void setModified(TableDatasinkDto container, boolean modified) {
			container.primaryKeys_m = modified;
		}

		@Override
		public boolean isModified(TableDatasinkDto container) {
			return container.isPrimaryKeysModified();
		}
	};

	private String tableName;
	private  boolean tableName_m;
	public static final String PROPERTY_TABLE_NAME = "dpi-tabledatasink-tablename";

	private transient static PropertyAccessor<TableDatasinkDto, String> tableName_pa = new PropertyAccessor<TableDatasinkDto, String>() {
		@Override
		public void setValue(TableDatasinkDto container, String object) {
			container.setTableName(object);
		}

		@Override
		public String getValue(TableDatasinkDto container) {
			return container.getTableName();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "tableName";
		}

		@Override
		public void setModified(TableDatasinkDto container, boolean modified) {
			container.tableName_m = modified;
		}

		@Override
		public boolean isModified(TableDatasinkDto container) {
			return container.isTableNameModified();
		}
	};

	private boolean truncateTable;
	private  boolean truncateTable_m;
	public static final String PROPERTY_TRUNCATE_TABLE = "dpi-tabledatasink-truncatetable";

	private transient static PropertyAccessor<TableDatasinkDto, Boolean> truncateTable_pa = new PropertyAccessor<TableDatasinkDto, Boolean>() {
		@Override
		public void setValue(TableDatasinkDto container, Boolean object) {
			container.setTruncateTable(object);
		}

		@Override
		public Boolean getValue(TableDatasinkDto container) {
			return container.isTruncateTable();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "truncateTable";
		}

		@Override
		public void setModified(TableDatasinkDto container, boolean modified) {
			container.truncateTable_m = modified;
		}

		@Override
		public boolean isModified(TableDatasinkDto container) {
			return container.isTruncateTableModified();
		}
	};


	public TableDatasinkDto() {
		super();
	}

	public int getBatchSize()  {
		if(! isDtoProxy()){
			return this.batchSize;
		}

		if(isBatchSizeModified())
			return this.batchSize;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().batchSize());

		return _value;
	}


	public void setBatchSize(int batchSize)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getBatchSize();

		/* set new value */
		this.batchSize = batchSize;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(batchSize_pa, oldValue, batchSize, this.batchSize_m));

		/* set indicator */
		this.batchSize_m = true;

		this.fireObjectChangedEvent(TableDatasinkDtoPA.INSTANCE.batchSize(), oldValue);
	}


	public boolean isBatchSizeModified()  {
		return batchSize_m;
	}


	public static PropertyAccessor<TableDatasinkDto, Integer> getBatchSizePropertyAccessor()  {
		return batchSize_pa;
	}


	public boolean isCopyPrimaryKeys()  {
		if(! isDtoProxy()){
			return this.copyPrimaryKeys;
		}

		if(isCopyPrimaryKeysModified())
			return this.copyPrimaryKeys;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().copyPrimaryKeys());

		return _value;
	}


	public void setCopyPrimaryKeys(boolean copyPrimaryKeys)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isCopyPrimaryKeys();

		/* set new value */
		this.copyPrimaryKeys = copyPrimaryKeys;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(copyPrimaryKeys_pa, oldValue, copyPrimaryKeys, this.copyPrimaryKeys_m));

		/* set indicator */
		this.copyPrimaryKeys_m = true;

		this.fireObjectChangedEvent(TableDatasinkDtoPA.INSTANCE.copyPrimaryKeys(), oldValue);
	}


	public boolean isCopyPrimaryKeysModified()  {
		return copyPrimaryKeys_m;
	}


	public static PropertyAccessor<TableDatasinkDto, Boolean> getCopyPrimaryKeysPropertyAccessor()  {
		return copyPrimaryKeys_pa;
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

		this.fireObjectChangedEvent(TableDatasinkDtoPA.INSTANCE.datasourceContainer(), oldValue);
	}


	public boolean isDatasourceContainerModified()  {
		return datasourceContainer_m;
	}


	public static PropertyAccessor<TableDatasinkDto, DatasourceContainerDto> getDatasourceContainerPropertyAccessor()  {
		return datasourceContainer_pa;
	}


	public String getPrimaryKeys()  {
		if(! isDtoProxy()){
			return this.primaryKeys;
		}

		if(isPrimaryKeysModified())
			return this.primaryKeys;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().primaryKeys());

		return _value;
	}


	public void setPrimaryKeys(String primaryKeys)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getPrimaryKeys();

		/* set new value */
		this.primaryKeys = primaryKeys;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(primaryKeys_pa, oldValue, primaryKeys, this.primaryKeys_m));

		/* set indicator */
		this.primaryKeys_m = true;

		this.fireObjectChangedEvent(TableDatasinkDtoPA.INSTANCE.primaryKeys(), oldValue);
	}


	public boolean isPrimaryKeysModified()  {
		return primaryKeys_m;
	}


	public static PropertyAccessor<TableDatasinkDto, String> getPrimaryKeysPropertyAccessor()  {
		return primaryKeys_pa;
	}


	public String getTableName()  {
		if(! isDtoProxy()){
			return this.tableName;
		}

		if(isTableNameModified())
			return this.tableName;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().tableName());

		return _value;
	}


	public void setTableName(String tableName)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getTableName();

		/* set new value */
		this.tableName = tableName;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(tableName_pa, oldValue, tableName, this.tableName_m));

		/* set indicator */
		this.tableName_m = true;

		this.fireObjectChangedEvent(TableDatasinkDtoPA.INSTANCE.tableName(), oldValue);
	}


	public boolean isTableNameModified()  {
		return tableName_m;
	}


	public static PropertyAccessor<TableDatasinkDto, String> getTableNamePropertyAccessor()  {
		return tableName_pa;
	}


	public boolean isTruncateTable()  {
		if(! isDtoProxy()){
			return this.truncateTable;
		}

		if(isTruncateTableModified())
			return this.truncateTable;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().truncateTable());

		return _value;
	}


	public void setTruncateTable(boolean truncateTable)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isTruncateTable();

		/* set new value */
		this.truncateTable = truncateTable;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(truncateTable_pa, oldValue, truncateTable, this.truncateTable_m));

		/* set indicator */
		this.truncateTable_m = true;

		this.fireObjectChangedEvent(TableDatasinkDtoPA.INSTANCE.truncateTable(), oldValue);
	}


	public boolean isTruncateTableModified()  {
		return truncateTable_m;
	}


	public static PropertyAccessor<TableDatasinkDto, Boolean> getTruncateTablePropertyAccessor()  {
		return truncateTable_pa;
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
		if(! (obj instanceof TableDatasinkDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((TableDatasinkDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new TableDatasinkDto2PosoMap();
	}

	public TableDatasinkDtoPA instantiatePropertyAccess()  {
		return GWT.create(TableDatasinkDtoPA.class);
	}

	public void clearModified()  {
		this.batchSize = 0;
		this.batchSize_m = false;
		this.copyPrimaryKeys = false;
		this.copyPrimaryKeys_m = false;
		this.datasourceContainer = null;
		this.datasourceContainer_m = false;
		this.primaryKeys = null;
		this.primaryKeys_m = false;
		this.tableName = null;
		this.tableName_m = false;
		this.truncateTable = false;
		this.truncateTable_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(batchSize_m)
			return true;
		if(copyPrimaryKeys_m)
			return true;
		if(datasourceContainer_m)
			return true;
		if(primaryKeys_m)
			return true;
		if(tableName_m)
			return true;
		if(truncateTable_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(batchSize_pa);
		list.add(copyPrimaryKeys_pa);
		list.add(datasourceContainer_pa);
		list.add(primaryKeys_pa);
		list.add(tableName_pa);
		list.add(truncateTable_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(batchSize_m)
			list.add(batchSize_pa);
		if(copyPrimaryKeys_m)
			list.add(copyPrimaryKeys_pa);
		if(datasourceContainer_m)
			list.add(datasourceContainer_pa);
		if(primaryKeys_m)
			list.add(primaryKeys_pa);
		if(tableName_m)
			list.add(tableName_pa);
		if(truncateTable_m)
			list.add(truncateTable_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(batchSize_pa);
			list.add(copyPrimaryKeys_pa);
			list.add(datasourceContainer_pa);
			list.add(primaryKeys_pa);
			list.add(tableName_pa);
			list.add(truncateTable_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(datasourceContainer_pa);
		return list;
	}



	net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto wl_0;

}
