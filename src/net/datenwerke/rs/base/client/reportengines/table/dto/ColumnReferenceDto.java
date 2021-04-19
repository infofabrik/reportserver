package net.datenwerke.rs.base.client.reportengines.table.dto;

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
import net.datenwerke.rs.base.client.reportengines.table.dto.AdditionalColumnSpecDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.ColumnReferenceDtoPA;
import net.datenwerke.rs.base.client.reportengines.table.dto.posomap.ColumnReferenceDto2PosoMap;
import net.datenwerke.rs.base.service.reportengines.table.entities.ColumnReference;

/**
 * Dto for {@link ColumnReference}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class ColumnReferenceDto extends ColumnDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private AdditionalColumnSpecDto reference;
	private  boolean reference_m;
	public static final String PROPERTY_REFERENCE = "dpi-columnreference-reference";

	private transient static PropertyAccessor<ColumnReferenceDto, AdditionalColumnSpecDto> reference_pa = new PropertyAccessor<ColumnReferenceDto, AdditionalColumnSpecDto>() {
		@Override
		public void setValue(ColumnReferenceDto container, AdditionalColumnSpecDto object) {
			container.setReference(object);
		}

		@Override
		public AdditionalColumnSpecDto getValue(ColumnReferenceDto container) {
			return container.getReference();
		}

		@Override
		public Class<?> getType() {
			return AdditionalColumnSpecDto.class;
		}

		@Override
		public String getPath() {
			return "reference";
		}

		@Override
		public void setModified(ColumnReferenceDto container, boolean modified) {
			container.reference_m = modified;
		}

		@Override
		public boolean isModified(ColumnReferenceDto container) {
			return container.isReferenceModified();
		}
	};


	public ColumnReferenceDto() {
		super();
	}

	public AdditionalColumnSpecDto getReference()  {
		if(! isDtoProxy()){
			return this.reference;
		}

		if(isReferenceModified())
			return this.reference;

		if(! GWT.isClient())
			return null;

		AdditionalColumnSpecDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().reference());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isReferenceModified())
						setReference((AdditionalColumnSpecDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setReference(AdditionalColumnSpecDto reference)  {
		/* old value */
		AdditionalColumnSpecDto oldValue = null;
		if(GWT.isClient())
			oldValue = getReference();

		/* set new value */
		this.reference = reference;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(reference_pa, oldValue, reference, this.reference_m));

		/* set indicator */
		this.reference_m = true;

		this.fireObjectChangedEvent(ColumnReferenceDtoPA.INSTANCE.reference(), oldValue);
	}


	public boolean isReferenceModified()  {
		return reference_m;
	}


	public static PropertyAccessor<ColumnReferenceDto, AdditionalColumnSpecDto> getReferencePropertyAccessor()  {
		return reference_pa;
	}


	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof ColumnReferenceDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((ColumnReferenceDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new ColumnReferenceDto2PosoMap();
	}

	public ColumnReferenceDtoPA instantiatePropertyAccess()  {
		return GWT.create(ColumnReferenceDtoPA.class);
	}

	public void clearModified()  {
		this.reference = null;
		this.reference_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(reference_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(reference_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(reference_m)
			list.add(reference_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(reference_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(reference_pa);
		return list;
	}



	net.datenwerke.rs.base.client.reportengines.table.dto.AdditionalColumnSpecDto wl_0;

}
