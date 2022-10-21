package net.datenwerke.rs.base.client.reportengines.table.dto;

import com.google.gwt.core.client.GWT;
import java.lang.IllegalStateException;
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
import net.datenwerke.rs.base.client.reportengines.table.dto.BlockTypeDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterBlockDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.PreFilterDtoPA;
import net.datenwerke.rs.base.client.reportengines.table.dto.posomap.PreFilterDto2PosoMap;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.PreFilter;

/**
 * Dto for {@link PreFilter}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class PreFilterDto extends RsDto implements IdedDto {


	private static final long serialVersionUID = 1;

	private Long dtoId;


	/* Fields */
	private String description;
	private  boolean description_m;
	public static final String PROPERTY_DESCRIPTION = "dpi-prefilter-description";

	private transient static PropertyAccessor<PreFilterDto, String> description_pa = new PropertyAccessor<PreFilterDto, String>() {
		@Override
		public void setValue(PreFilterDto container, String object) {
			container.setDescription(object);
		}

		@Override
		public String getValue(PreFilterDto container) {
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
		public void setModified(PreFilterDto container, boolean modified) {
			container.description_m = modified;
		}

		@Override
		public boolean isModified(PreFilterDto container) {
			return container.isDescriptionModified();
		}
	};

	private Long id;
	private  boolean id_m;
	public static final String PROPERTY_ID = "dpi-prefilter-id";

	private transient static PropertyAccessor<PreFilterDto, Long> id_pa = new PropertyAccessor<PreFilterDto, Long>() {
		@Override
		public void setValue(PreFilterDto container, Long object) {
			// id field
		}

		@Override
		public Long getValue(PreFilterDto container) {
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
		public void setModified(PreFilterDto container, boolean modified) {
			container.id_m = modified;
		}

		@Override
		public boolean isModified(PreFilterDto container) {
			return container.isIdModified();
		}
	};

	private FilterBlockDto rootBlock;
	private  boolean rootBlock_m;
	public static final String PROPERTY_ROOT_BLOCK = "dpi-prefilter-rootblock";

	private transient static PropertyAccessor<PreFilterDto, FilterBlockDto> rootBlock_pa = new PropertyAccessor<PreFilterDto, FilterBlockDto>() {
		@Override
		public void setValue(PreFilterDto container, FilterBlockDto object) {
			container.setRootBlock(object);
		}

		@Override
		public FilterBlockDto getValue(PreFilterDto container) {
			return container.getRootBlock();
		}

		@Override
		public Class<?> getType() {
			return FilterBlockDto.class;
		}

		@Override
		public String getPath() {
			return "rootBlock";
		}

		@Override
		public void setModified(PreFilterDto container, boolean modified) {
			container.rootBlock_m = modified;
		}

		@Override
		public boolean isModified(PreFilterDto container) {
			return container.isRootBlockModified();
		}
	};

	private BlockTypeDto rootBlockType;
	private  boolean rootBlockType_m;
	public static final String PROPERTY_ROOT_BLOCK_TYPE = "dpi-prefilter-rootblocktype";

	private transient static PropertyAccessor<PreFilterDto, BlockTypeDto> rootBlockType_pa = new PropertyAccessor<PreFilterDto, BlockTypeDto>() {
		@Override
		public void setValue(PreFilterDto container, BlockTypeDto object) {
			container.setRootBlockType(object);
		}

		@Override
		public BlockTypeDto getValue(PreFilterDto container) {
			return container.getRootBlockType();
		}

		@Override
		public Class<?> getType() {
			return BlockTypeDto.class;
		}

		@Override
		public String getPath() {
			return "rootBlockType";
		}

		@Override
		public void setModified(PreFilterDto container, boolean modified) {
			container.rootBlockType_m = modified;
		}

		@Override
		public boolean isModified(PreFilterDto container) {
			return container.isRootBlockTypeModified();
		}
	};


	public PreFilterDto() {
		super();
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

		this.fireObjectChangedEvent(PreFilterDtoPA.INSTANCE.description(), oldValue);
	}


	public boolean isDescriptionModified()  {
		return description_m;
	}


	public static PropertyAccessor<PreFilterDto, String> getDescriptionPropertyAccessor()  {
		return description_pa;
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


	public static PropertyAccessor<PreFilterDto, Long> getIdPropertyAccessor()  {
		return id_pa;
	}


	public FilterBlockDto getRootBlock()  {
		if(! isDtoProxy()){
			return this.rootBlock;
		}

		if(isRootBlockModified())
			return this.rootBlock;

		if(! GWT.isClient())
			return null;

		FilterBlockDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().rootBlock());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isRootBlockModified())
						setRootBlock((FilterBlockDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setRootBlock(FilterBlockDto rootBlock)  {
		/* old value */
		FilterBlockDto oldValue = null;
		if(GWT.isClient())
			oldValue = getRootBlock();

		/* set new value */
		this.rootBlock = rootBlock;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(rootBlock_pa, oldValue, rootBlock, this.rootBlock_m));

		/* set indicator */
		this.rootBlock_m = true;

		this.fireObjectChangedEvent(PreFilterDtoPA.INSTANCE.rootBlock(), oldValue);
	}


	public boolean isRootBlockModified()  {
		return rootBlock_m;
	}


	public static PropertyAccessor<PreFilterDto, FilterBlockDto> getRootBlockPropertyAccessor()  {
		return rootBlock_pa;
	}


	public BlockTypeDto getRootBlockType()  {
		if(! isDtoProxy()){
			return this.rootBlockType;
		}

		if(isRootBlockTypeModified())
			return this.rootBlockType;

		if(! GWT.isClient())
			return null;

		BlockTypeDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().rootBlockType());

		return _value;
	}


	public void setRootBlockType(BlockTypeDto rootBlockType)  {
		/* old value */
		BlockTypeDto oldValue = null;
		if(GWT.isClient())
			oldValue = getRootBlockType();

		/* set new value */
		this.rootBlockType = rootBlockType;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(rootBlockType_pa, oldValue, rootBlockType, this.rootBlockType_m));

		/* set indicator */
		this.rootBlockType_m = true;

		this.fireObjectChangedEvent(PreFilterDtoPA.INSTANCE.rootBlockType(), oldValue);
	}


	public boolean isRootBlockTypeModified()  {
		return rootBlockType_m;
	}


	public static PropertyAccessor<PreFilterDto, BlockTypeDto> getRootBlockTypePropertyAccessor()  {
		return rootBlockType_pa;
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
		if(! (obj instanceof PreFilterDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((PreFilterDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new PreFilterDto2PosoMap();
	}

	public PreFilterDtoPA instantiatePropertyAccess()  {
		return GWT.create(PreFilterDtoPA.class);
	}

	public void clearModified()  {
		this.description = null;
		this.description_m = false;
		this.id = null;
		this.id_m = false;
		this.rootBlock = null;
		this.rootBlock_m = false;
		this.rootBlockType = null;
		this.rootBlockType_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(description_m)
			return true;
		if(id_m)
			return true;
		if(rootBlock_m)
			return true;
		if(rootBlockType_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(description_pa);
		list.add(id_pa);
		list.add(rootBlock_pa);
		list.add(rootBlockType_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(description_m)
			list.add(description_pa);
		if(id_m)
			list.add(id_pa);
		if(rootBlock_m)
			list.add(rootBlock_pa);
		if(rootBlockType_m)
			list.add(rootBlockType_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(description_pa);
			list.add(id_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(rootBlock_pa);
			list.add(rootBlockType_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(rootBlock_pa);
		return list;
	}



	net.datenwerke.rs.base.client.reportengines.table.dto.BlockTypeDto wl_0;
	net.datenwerke.rs.base.client.reportengines.table.dto.FilterBlockDto wl_1;

}
