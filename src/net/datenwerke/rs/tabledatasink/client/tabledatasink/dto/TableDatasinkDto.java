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


	public TableDatasinkDto() {
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

		this.fireObjectChangedEvent(TableDatasinkDtoPA.INSTANCE.datasourceContainer(), oldValue);
	}


	public boolean isDatasourceContainerModified()  {
		return datasourceContainer_m;
	}


	public static PropertyAccessor<TableDatasinkDto, DatasourceContainerDto> getDatasourceContainerPropertyAccessor()  {
		return datasourceContainer_pa;
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
		this.datasourceContainer = null;
		this.datasourceContainer_m = false;
		this.tableName = null;
		this.tableName_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(datasourceContainer_m)
			return true;
		if(tableName_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(datasourceContainer_pa);
		list.add(tableName_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(datasourceContainer_m)
			list.add(datasourceContainer_pa);
		if(tableName_m)
			list.add(tableName_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(datasourceContainer_pa);
			list.add(tableName_pa);
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
