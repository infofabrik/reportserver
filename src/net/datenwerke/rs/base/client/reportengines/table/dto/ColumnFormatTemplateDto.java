package net.datenwerke.rs.base.client.reportengines.table.dto;

import com.google.gwt.core.client.GWT;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.ColumnFormatTemplateDtoPA;
import net.datenwerke.rs.base.client.reportengines.table.dto.posomap.ColumnFormatTemplateDto2PosoMap;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatTemplate;

/**
 * Dto for {@link ColumnFormatTemplate}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class ColumnFormatTemplateDto extends ColumnFormatDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String template;
	private  boolean template_m;
	public static final String PROPERTY_TEMPLATE = "dpi-columnformattemplate-template";

	private transient static PropertyAccessor<ColumnFormatTemplateDto, String> template_pa = new PropertyAccessor<ColumnFormatTemplateDto, String>() {
		@Override
		public void setValue(ColumnFormatTemplateDto container, String object) {
			container.setTemplate(object);
		}

		@Override
		public String getValue(ColumnFormatTemplateDto container) {
			return container.getTemplate();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "template";
		}

		@Override
		public void setModified(ColumnFormatTemplateDto container, boolean modified) {
			container.template_m = modified;
		}

		@Override
		public boolean isModified(ColumnFormatTemplateDto container) {
			return container.isTemplateModified();
		}
	};


	public ColumnFormatTemplateDto() {
		super();
	}

	public String getTemplate()  {
		if(! isDtoProxy()){
			return this.template;
		}

		if(isTemplateModified())
			return this.template;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().template());

		return _value;
	}


	public void setTemplate(String template)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getTemplate();

		/* set new value */
		this.template = template;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(template_pa, oldValue, template, this.template_m));

		/* set indicator */
		this.template_m = true;

		this.fireObjectChangedEvent(ColumnFormatTemplateDtoPA.INSTANCE.template(), oldValue);
	}


	public boolean isTemplateModified()  {
		return template_m;
	}


	public static PropertyAccessor<ColumnFormatTemplateDto, String> getTemplatePropertyAccessor()  {
		return template_pa;
	}


	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof ColumnFormatTemplateDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((ColumnFormatTemplateDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new ColumnFormatTemplateDto2PosoMap();
	}

	public ColumnFormatTemplateDtoPA instantiatePropertyAccess()  {
		return GWT.create(ColumnFormatTemplateDtoPA.class);
	}

	public void clearModified()  {
		this.template = null;
		this.template_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(template_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(template_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(template_m)
			list.add(template_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(template_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
