package net.datenwerke.rs.dashboard.client.dashboard.dto;

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
import net.datenwerke.rs.dashboard.client.dashboard.dto.AbstractDashboardManagerNodeDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.pa.DadgetNodeDtoPA;
import net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.DadgetNodeDto2PosoMap;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DadgetNode;

/**
 * Dto for {@link DadgetNode}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DadgetNodeDto extends AbstractDashboardManagerNodeDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private DadgetDto dadget;
	private  boolean dadget_m;
	public static final String PROPERTY_DADGET = "dpi-dadgetnode-dadget";

	private transient static PropertyAccessor<DadgetNodeDto, DadgetDto> dadget_pa = new PropertyAccessor<DadgetNodeDto, DadgetDto>() {
		@Override
		public void setValue(DadgetNodeDto container, DadgetDto object) {
			container.setDadget(object);
		}

		@Override
		public DadgetDto getValue(DadgetNodeDto container) {
			return container.getDadget();
		}

		@Override
		public Class<?> getType() {
			return DadgetDto.class;
		}

		@Override
		public String getPath() {
			return "dadget";
		}

		@Override
		public void setModified(DadgetNodeDto container, boolean modified) {
			container.dadget_m = modified;
		}

		@Override
		public boolean isModified(DadgetNodeDto container) {
			return container.isDadgetModified();
		}
	};

	private String description;
	private  boolean description_m;
	public static final String PROPERTY_DESCRIPTION = "dpi-dadgetnode-description";

	private transient static PropertyAccessor<DadgetNodeDto, String> description_pa = new PropertyAccessor<DadgetNodeDto, String>() {
		@Override
		public void setValue(DadgetNodeDto container, String object) {
			container.setDescription(object);
		}

		@Override
		public String getValue(DadgetNodeDto container) {
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
		public void setModified(DadgetNodeDto container, boolean modified) {
			container.description_m = modified;
		}

		@Override
		public boolean isModified(DadgetNodeDto container) {
			return container.isDescriptionModified();
		}
	};

	private String name;
	private  boolean name_m;
	public static final String PROPERTY_NAME = "dpi-dadgetnode-name";

	private transient static PropertyAccessor<DadgetNodeDto, String> name_pa = new PropertyAccessor<DadgetNodeDto, String>() {
		@Override
		public void setValue(DadgetNodeDto container, String object) {
			container.setName(object);
		}

		@Override
		public String getValue(DadgetNodeDto container) {
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
		public void setModified(DadgetNodeDto container, boolean modified) {
			container.name_m = modified;
		}

		@Override
		public boolean isModified(DadgetNodeDto container) {
			return container.isNameModified();
		}
	};


	public DadgetNodeDto() {
		super();
	}

	public DadgetDto getDadget()  {
		if(! isDtoProxy()){
			return this.dadget;
		}

		if(isDadgetModified())
			return this.dadget;

		if(! GWT.isClient())
			return null;

		DadgetDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().dadget());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isDadgetModified())
						setDadget((DadgetDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setDadget(DadgetDto dadget)  {
		/* old value */
		DadgetDto oldValue = null;
		if(GWT.isClient())
			oldValue = getDadget();

		/* set new value */
		this.dadget = dadget;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(dadget_pa, oldValue, dadget, this.dadget_m));

		/* set indicator */
		this.dadget_m = true;

		this.fireObjectChangedEvent(DadgetNodeDtoPA.INSTANCE.dadget(), oldValue);
	}


	public boolean isDadgetModified()  {
		return dadget_m;
	}


	public static PropertyAccessor<DadgetNodeDto, DadgetDto> getDadgetPropertyAccessor()  {
		return dadget_pa;
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

		this.fireObjectChangedEvent(DadgetNodeDtoPA.INSTANCE.description(), oldValue);
	}


	public boolean isDescriptionModified()  {
		return description_m;
	}


	public static PropertyAccessor<DadgetNodeDto, String> getDescriptionPropertyAccessor()  {
		return description_pa;
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

		this.fireObjectChangedEvent(DadgetNodeDtoPA.INSTANCE.name(), oldValue);
	}


	public boolean isNameModified()  {
		return name_m;
	}


	public static PropertyAccessor<DadgetNodeDto, String> getNamePropertyAccessor()  {
		return name_pa;
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
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof DadgetNodeDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((DadgetNodeDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new DadgetNodeDto2PosoMap();
	}

	public DadgetNodeDtoPA instantiatePropertyAccess()  {
		return GWT.create(DadgetNodeDtoPA.class);
	}

	public void clearModified()  {
		this.dadget = null;
		this.dadget_m = false;
		this.description = null;
		this.description_m = false;
		this.name = null;
		this.name_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(dadget_m)
			return true;
		if(description_m)
			return true;
		if(name_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(dadget_pa);
		list.add(description_pa);
		list.add(name_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(dadget_m)
			list.add(dadget_pa);
		if(description_m)
			list.add(description_pa);
		if(name_m)
			list.add(name_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(description_pa);
			list.add(name_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(dadget_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(dadget_pa);
		return list;
	}



	net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto wl_0;

}
