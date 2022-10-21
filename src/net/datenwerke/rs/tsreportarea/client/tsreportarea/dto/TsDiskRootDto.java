package net.datenwerke.rs.tsreportarea.client.tsreportarea.dto;

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
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.AbstractTsDiskNodeDtoDec;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.pa.TsDiskRootDtoPA;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.posomap.TsDiskRootDto2PosoMap;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskRoot;

/**
 * Dto for {@link TsDiskRoot}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class TsDiskRootDto extends AbstractTsDiskNodeDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String description;
	private  boolean description_m;
	public static final String PROPERTY_DESCRIPTION = "dpi-tsdiskroot-description";

	private transient static PropertyAccessor<TsDiskRootDto, String> description_pa = new PropertyAccessor<TsDiskRootDto, String>() {
		@Override
		public void setValue(TsDiskRootDto container, String object) {
			container.setDescription(object);
		}

		@Override
		public String getValue(TsDiskRootDto container) {
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
		public void setModified(TsDiskRootDto container, boolean modified) {
			container.description_m = modified;
		}

		@Override
		public boolean isModified(TsDiskRootDto container) {
			return container.isDescriptionModified();
		}
	};

	private String name;
	private  boolean name_m;
	public static final String PROPERTY_NAME = "dpi-tsdiskroot-name";

	private transient static PropertyAccessor<TsDiskRootDto, String> name_pa = new PropertyAccessor<TsDiskRootDto, String>() {
		@Override
		public void setValue(TsDiskRootDto container, String object) {
			container.setName(object);
		}

		@Override
		public String getValue(TsDiskRootDto container) {
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
		public void setModified(TsDiskRootDto container, boolean modified) {
			container.name_m = modified;
		}

		@Override
		public boolean isModified(TsDiskRootDto container) {
			return container.isNameModified();
		}
	};

	private TeamSpaceDto teamSpace;
	private  boolean teamSpace_m;
	public static final String PROPERTY_TEAM_SPACE = "dpi-tsdiskroot-teamspace";

	private transient static PropertyAccessor<TsDiskRootDto, TeamSpaceDto> teamSpace_pa = new PropertyAccessor<TsDiskRootDto, TeamSpaceDto>() {
		@Override
		public void setValue(TsDiskRootDto container, TeamSpaceDto object) {
			container.setTeamSpace(object);
		}

		@Override
		public TeamSpaceDto getValue(TsDiskRootDto container) {
			return container.getTeamSpace();
		}

		@Override
		public Class<?> getType() {
			return TeamSpaceDto.class;
		}

		@Override
		public String getPath() {
			return "teamSpace";
		}

		@Override
		public void setModified(TsDiskRootDto container, boolean modified) {
			container.teamSpace_m = modified;
		}

		@Override
		public boolean isModified(TsDiskRootDto container) {
			return container.isTeamSpaceModified();
		}
	};


	public TsDiskRootDto() {
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

		this.fireObjectChangedEvent(TsDiskRootDtoPA.INSTANCE.description(), oldValue);
	}


	public boolean isDescriptionModified()  {
		return description_m;
	}


	public static PropertyAccessor<TsDiskRootDto, String> getDescriptionPropertyAccessor()  {
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

		this.fireObjectChangedEvent(TsDiskRootDtoPA.INSTANCE.name(), oldValue);
	}


	public boolean isNameModified()  {
		return name_m;
	}


	public static PropertyAccessor<TsDiskRootDto, String> getNamePropertyAccessor()  {
		return name_pa;
	}


	public TeamSpaceDto getTeamSpace()  {
		if(! isDtoProxy()){
			return this.teamSpace;
		}

		if(isTeamSpaceModified())
			return this.teamSpace;

		if(! GWT.isClient())
			return null;

		TeamSpaceDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().teamSpace());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isTeamSpaceModified())
						setTeamSpace((TeamSpaceDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setTeamSpace(TeamSpaceDto teamSpace)  {
		/* old value */
		TeamSpaceDto oldValue = null;
		if(GWT.isClient())
			oldValue = getTeamSpace();

		/* set new value */
		this.teamSpace = teamSpace;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(teamSpace_pa, oldValue, teamSpace, this.teamSpace_m));

		/* set indicator */
		this.teamSpace_m = true;

		this.fireObjectChangedEvent(TsDiskRootDtoPA.INSTANCE.teamSpace(), oldValue);
	}


	public boolean isTeamSpaceModified()  {
		return teamSpace_m;
	}


	public static PropertyAccessor<TsDiskRootDto, TeamSpaceDto> getTeamSpacePropertyAccessor()  {
		return teamSpace_pa;
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
		if(! (obj instanceof TsDiskRootDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((TsDiskRootDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new TsDiskRootDto2PosoMap();
	}

	public TsDiskRootDtoPA instantiatePropertyAccess()  {
		return GWT.create(TsDiskRootDtoPA.class);
	}

	public void clearModified()  {
		this.description = null;
		this.description_m = false;
		this.name = null;
		this.name_m = false;
		this.teamSpace = null;
		this.teamSpace_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(description_m)
			return true;
		if(name_m)
			return true;
		if(teamSpace_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(description_pa);
		list.add(name_pa);
		list.add(teamSpace_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(description_m)
			list.add(description_pa);
		if(name_m)
			list.add(name_pa);
		if(teamSpace_m)
			list.add(teamSpace_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(description_pa);
			list.add(name_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(teamSpace_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(teamSpace_pa);
		return list;
	}



	net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto wl_0;

}
