package net.datenwerke.security.client.security.dto;

import com.google.gwt.core.client.GWT;
import java.lang.String;
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
import net.datenwerke.security.client.security.dto.RightDto;
import net.datenwerke.security.client.security.dto.SecureeDto;
import net.datenwerke.security.client.security.dto.pa.SecurityServiceSecureeDtoPA;
import net.datenwerke.security.client.security.dto.posomap.SecurityServiceSecureeDto2PosoMap;
import net.datenwerke.security.service.security.SecurityServiceSecuree;

/**
 * Dto for {@link SecurityServiceSecuree}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class SecurityServiceSecureeDto extends RsDto implements SecureeDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String name;
	private  boolean name_m;
	public static final String PROPERTY_NAME = "dpi-securityservicesecuree-name";

	private transient static PropertyAccessor<SecurityServiceSecureeDto, String> name_pa = new PropertyAccessor<SecurityServiceSecureeDto, String>() {
		@Override
		public void setValue(SecurityServiceSecureeDto container, String object) {
			container.setName(object);
		}

		@Override
		public String getValue(SecurityServiceSecureeDto container) {
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
		public void setModified(SecurityServiceSecureeDto container, boolean modified) {
			container.name_m = modified;
		}

		@Override
		public boolean isModified(SecurityServiceSecureeDto container) {
			return container.isNameModified();
		}
	};

	private List<RightDto> rights;
	private  boolean rights_m;
	public static final String PROPERTY_RIGHTS = "dpi-securityservicesecuree-rights";

	private transient static PropertyAccessor<SecurityServiceSecureeDto, List<RightDto>> rights_pa = new PropertyAccessor<SecurityServiceSecureeDto, List<RightDto>>() {
		@Override
		public void setValue(SecurityServiceSecureeDto container, List<RightDto> object) {
			container.setRights(object);
		}

		@Override
		public List<RightDto> getValue(SecurityServiceSecureeDto container) {
			return container.getRights();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "rights";
		}

		@Override
		public void setModified(SecurityServiceSecureeDto container, boolean modified) {
			container.rights_m = modified;
		}

		@Override
		public boolean isModified(SecurityServiceSecureeDto container) {
			return container.isRightsModified();
		}
	};

	private String secureeId;
	private  boolean secureeId_m;
	public static final String PROPERTY_SECUREE_ID = "dpi-securityservicesecuree-secureeid";

	private transient static PropertyAccessor<SecurityServiceSecureeDto, String> secureeId_pa = new PropertyAccessor<SecurityServiceSecureeDto, String>() {
		@Override
		public void setValue(SecurityServiceSecureeDto container, String object) {
			container.setSecureeId(object);
		}

		@Override
		public String getValue(SecurityServiceSecureeDto container) {
			return container.getSecureeId();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "secureeId";
		}

		@Override
		public void setModified(SecurityServiceSecureeDto container, boolean modified) {
			container.secureeId_m = modified;
		}

		@Override
		public boolean isModified(SecurityServiceSecureeDto container) {
			return container.isSecureeIdModified();
		}
	};


	public SecurityServiceSecureeDto() {
		super();
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

		this.fireObjectChangedEvent(SecurityServiceSecureeDtoPA.INSTANCE.name(), oldValue);
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


	public boolean isNameModified()  {
		return name_m;
	}


	public static PropertyAccessor<SecurityServiceSecureeDto, String> getNamePropertyAccessor()  {
		return name_pa;
	}


	public void setRights(List<RightDto> rights)  {
		/* old value */
		List<RightDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getRights();

		/* set new value */
		this.rights = rights;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(rights_pa, oldValue, rights, this.rights_m));

		/* set indicator */
		this.rights_m = true;

		this.fireObjectChangedEvent(SecurityServiceSecureeDtoPA.INSTANCE.rights(), oldValue);
	}


	public List<RightDto> getRights()  {
		if(! isDtoProxy()){
			List<RightDto> _currentValue = this.rights;
			if(null == _currentValue)
				this.rights = new ArrayList<RightDto>();

			return this.rights;
		}

		if(isRightsModified())
			return this.rights;

		if(! GWT.isClient())
			return null;

		List<RightDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().rights());

		_value = new ChangeMonitoredList<RightDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isRightsModified())
						setRights((List<RightDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public boolean isRightsModified()  {
		return rights_m;
	}


	public static PropertyAccessor<SecurityServiceSecureeDto, List<RightDto>> getRightsPropertyAccessor()  {
		return rights_pa;
	}


	public void setSecureeId(String secureeId)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getSecureeId();

		/* set new value */
		this.secureeId = secureeId;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(secureeId_pa, oldValue, secureeId, this.secureeId_m));

		/* set indicator */
		this.secureeId_m = true;

		this.fireObjectChangedEvent(SecurityServiceSecureeDtoPA.INSTANCE.secureeId(), oldValue);
	}


	public String getSecureeId()  {
		if(! isDtoProxy()){
			return this.secureeId;
		}

		if(isSecureeIdModified())
			return this.secureeId;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().secureeId());

		return _value;
	}


	public boolean isSecureeIdModified()  {
		return secureeId_m;
	}


	public static PropertyAccessor<SecurityServiceSecureeDto, String> getSecureeIdPropertyAccessor()  {
		return secureeId_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new SecurityServiceSecureeDto2PosoMap();
	}

	public SecurityServiceSecureeDtoPA instantiatePropertyAccess()  {
		return GWT.create(SecurityServiceSecureeDtoPA.class);
	}

	public void clearModified()  {
		this.name = null;
		this.name_m = false;
		this.rights = null;
		this.rights_m = false;
		this.secureeId = null;
		this.secureeId_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(name_m)
			return true;
		if(rights_m)
			return true;
		if(secureeId_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(name_pa);
		list.add(rights_pa);
		list.add(secureeId_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(name_m)
			list.add(name_pa);
		if(rights_m)
			list.add(rights_pa);
		if(secureeId_m)
			list.add(secureeId_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(name_pa);
			list.add(rights_pa);
			list.add(secureeId_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(rights_pa);
		return list;
	}



	net.datenwerke.security.client.security.dto.RightDto wl_0;

}
