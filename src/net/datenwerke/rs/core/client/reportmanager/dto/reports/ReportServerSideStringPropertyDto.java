package net.datenwerke.rs.core.client.reportmanager.dto.reports;

import com.google.gwt.core.client.GWT;
import java.lang.NullPointerException;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportPropertyDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ServerSidePropertyDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.pa.ReportServerSideStringPropertyDtoPA;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.posomap.ReportServerSideStringPropertyDto2PosoMap;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportServerSideStringProperty;

/**
 * Dto for {@link ReportServerSideStringProperty}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class ReportServerSideStringPropertyDto extends ReportPropertyDto implements ServerSidePropertyDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String strValue;
	private  boolean strValue_m;
	public static final String PROPERTY_STR_VALUE = "dpi-reportserversidestringproperty-strvalue";

	private transient static PropertyAccessor<ReportServerSideStringPropertyDto, String> strValue_pa = new PropertyAccessor<ReportServerSideStringPropertyDto, String>() {
		@Override
		public void setValue(ReportServerSideStringPropertyDto container, String object) {
			container.setStrValue(object);
		}

		@Override
		public String getValue(ReportServerSideStringPropertyDto container) {
			return container.getStrValue();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "strValue";
		}

		@Override
		public void setModified(ReportServerSideStringPropertyDto container, boolean modified) {
			container.strValue_m = modified;
		}

		@Override
		public boolean isModified(ReportServerSideStringPropertyDto container) {
			return container.isStrValueModified();
		}
	};


	public ReportServerSideStringPropertyDto() {
		super();
	}

	public String getStrValue()  {
		if(! isDtoProxy()){
			return this.strValue;
		}

		if(isStrValueModified())
			return this.strValue;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().strValue());

		return _value;
	}


	public void setStrValue(String strValue)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getStrValue();

		/* set new value */
		this.strValue = strValue;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(strValue_pa, oldValue, strValue, this.strValue_m));

		/* set indicator */
		this.strValue_m = true;

		this.fireObjectChangedEvent(ReportServerSideStringPropertyDtoPA.INSTANCE.strValue(), oldValue);
	}


	public boolean isStrValueModified()  {
		return strValue_m;
	}


	public static PropertyAccessor<ReportServerSideStringPropertyDto, String> getStrValuePropertyAccessor()  {
		return strValue_pa;
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
		if(! (obj instanceof ReportServerSideStringPropertyDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((ReportServerSideStringPropertyDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new ReportServerSideStringPropertyDto2PosoMap();
	}

	public ReportServerSideStringPropertyDtoPA instantiatePropertyAccess()  {
		return GWT.create(ReportServerSideStringPropertyDtoPA.class);
	}

	public void clearModified()  {
		this.strValue = null;
		this.strValue_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(strValue_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(strValue_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(strValue_m)
			list.add(strValue_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(strValue_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
