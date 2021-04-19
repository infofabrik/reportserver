package net.datenwerke.rs.dashboard.client.dashboard.dto;

import com.google.gwt.core.client.GWT;
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
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetNodeDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DadgetDtoDec;
import net.datenwerke.rs.dashboard.client.dashboard.dto.pa.LibraryDadgetDtoPA;
import net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.LibraryDadgetDto2PosoMap;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.LibraryDadget;

/**
 * Dto for {@link LibraryDadget}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class LibraryDadgetDto extends DadgetDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private DadgetNodeDto dadgetNode;
	private  boolean dadgetNode_m;
	public static final String PROPERTY_DADGET_NODE = "dpi-librarydadget-dadgetnode";

	private transient static PropertyAccessor<LibraryDadgetDto, DadgetNodeDto> dadgetNode_pa = new PropertyAccessor<LibraryDadgetDto, DadgetNodeDto>() {
		@Override
		public void setValue(LibraryDadgetDto container, DadgetNodeDto object) {
			container.setDadgetNode(object);
		}

		@Override
		public DadgetNodeDto getValue(LibraryDadgetDto container) {
			return container.getDadgetNode();
		}

		@Override
		public Class<?> getType() {
			return DadgetNodeDto.class;
		}

		@Override
		public String getPath() {
			return "dadgetNode";
		}

		@Override
		public void setModified(LibraryDadgetDto container, boolean modified) {
			container.dadgetNode_m = modified;
		}

		@Override
		public boolean isModified(LibraryDadgetDto container) {
			return container.isDadgetNodeModified();
		}
	};


	public LibraryDadgetDto() {
		super();
	}

	public DadgetNodeDto getDadgetNode()  {
		if(! isDtoProxy()){
			return this.dadgetNode;
		}

		if(isDadgetNodeModified())
			return this.dadgetNode;

		if(! GWT.isClient())
			return null;

		DadgetNodeDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().dadgetNode());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isDadgetNodeModified())
						setDadgetNode((DadgetNodeDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setDadgetNode(DadgetNodeDto dadgetNode)  {
		/* old value */
		DadgetNodeDto oldValue = null;
		if(GWT.isClient())
			oldValue = getDadgetNode();

		/* set new value */
		this.dadgetNode = dadgetNode;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(dadgetNode_pa, oldValue, dadgetNode, this.dadgetNode_m));

		/* set indicator */
		this.dadgetNode_m = true;

		this.fireObjectChangedEvent(LibraryDadgetDtoPA.INSTANCE.dadgetNode(), oldValue);
	}


	public boolean isDadgetNodeModified()  {
		return dadgetNode_m;
	}


	public static PropertyAccessor<LibraryDadgetDto, DadgetNodeDto> getDadgetNodePropertyAccessor()  {
		return dadgetNode_pa;
	}


	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof LibraryDadgetDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((LibraryDadgetDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new LibraryDadgetDto2PosoMap();
	}

	public LibraryDadgetDtoPA instantiatePropertyAccess()  {
		return GWT.create(LibraryDadgetDtoPA.class);
	}

	public void clearModified()  {
		this.dadgetNode = null;
		this.dadgetNode_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(dadgetNode_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(dadgetNode_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(dadgetNode_m)
			list.add(dadgetNode_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(dadgetNode_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(dadgetNode_pa);
		return list;
	}



	net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetNodeDto wl_0;

}
