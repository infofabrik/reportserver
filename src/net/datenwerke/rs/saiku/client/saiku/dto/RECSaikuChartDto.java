package net.datenwerke.rs.saiku.client.saiku.dto;

import com.google.gwt.core.client.GWT;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.saiku.client.saiku.dto.pa.RECSaikuChartDtoPA;
import net.datenwerke.rs.saiku.client.saiku.dto.posomap.RECSaikuChartDto2PosoMap;
import net.datenwerke.rs.saiku.service.saiku.reportengine.config.RECSaikuChart;

/**
 * Dto for {@link RECSaikuChart}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class RECSaikuChartDto extends RsDto implements ReportExecutionConfigDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String type;
	private  boolean type_m;
	public static final String PROPERTY_TYPE = "dpi-recsaikuchart-type";

	private transient static PropertyAccessor<RECSaikuChartDto, String> type_pa = new PropertyAccessor<RECSaikuChartDto, String>() {
		@Override
		public void setValue(RECSaikuChartDto container, String object) {
			container.setType(object);
		}

		@Override
		public String getValue(RECSaikuChartDto container) {
			return container.getType();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "type";
		}

		@Override
		public void setModified(RECSaikuChartDto container, boolean modified) {
			container.type_m = modified;
		}

		@Override
		public boolean isModified(RECSaikuChartDto container) {
			return container.isTypeModified();
		}
	};


	public RECSaikuChartDto() {
		super();
	}

	public String getType()  {
		if(! isDtoProxy()){
			return this.type;
		}

		if(isTypeModified())
			return this.type;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().type());

		return _value;
	}


	public void setType(String type)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getType();

		/* set new value */
		this.type = type;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(type_pa, oldValue, type, this.type_m));

		/* set indicator */
		this.type_m = true;

		this.fireObjectChangedEvent(RECSaikuChartDtoPA.INSTANCE.type(), oldValue);
	}


	public boolean isTypeModified()  {
		return type_m;
	}


	public static PropertyAccessor<RECSaikuChartDto, String> getTypePropertyAccessor()  {
		return type_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new RECSaikuChartDto2PosoMap();
	}

	public RECSaikuChartDtoPA instantiatePropertyAccess()  {
		return GWT.create(RECSaikuChartDtoPA.class);
	}

	public void clearModified()  {
		this.type = null;
		this.type_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(type_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(type_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(type_m)
			list.add(type_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(type_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
