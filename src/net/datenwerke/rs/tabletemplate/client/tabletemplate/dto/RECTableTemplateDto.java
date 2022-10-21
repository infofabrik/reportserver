package net.datenwerke.rs.tabletemplate.client.tabletemplate.dto;

import com.google.gwt.core.client.GWT;
import java.lang.Long;
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
import net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.pa.RECTableTemplateDtoPA;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.posomap.RECTableTemplateDto2PosoMap;
import net.datenwerke.rs.tabletemplate.service.tabletemplate.config.RECTableTemplate;

/**
 * Dto for {@link RECTableTemplate}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class RECTableTemplateDto extends RsDto implements ReportExecutionConfigDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private Long templateId;
	private  boolean templateId_m;
	public static final String PROPERTY_TEMPLATE_ID = "dpi-rectabletemplate-templateid";

	private transient static PropertyAccessor<RECTableTemplateDto, Long> templateId_pa = new PropertyAccessor<RECTableTemplateDto, Long>() {
		@Override
		public void setValue(RECTableTemplateDto container, Long object) {
			container.setTemplateId(object);
		}

		@Override
		public Long getValue(RECTableTemplateDto container) {
			return container.getTemplateId();
		}

		@Override
		public Class<?> getType() {
			return Long.class;
		}

		@Override
		public String getPath() {
			return "templateId";
		}

		@Override
		public void setModified(RECTableTemplateDto container, boolean modified) {
			container.templateId_m = modified;
		}

		@Override
		public boolean isModified(RECTableTemplateDto container) {
			return container.isTemplateIdModified();
		}
	};

	private String templateKey;
	private  boolean templateKey_m;
	public static final String PROPERTY_TEMPLATE_KEY = "dpi-rectabletemplate-templatekey";

	private transient static PropertyAccessor<RECTableTemplateDto, String> templateKey_pa = new PropertyAccessor<RECTableTemplateDto, String>() {
		@Override
		public void setValue(RECTableTemplateDto container, String object) {
			container.setTemplateKey(object);
		}

		@Override
		public String getValue(RECTableTemplateDto container) {
			return container.getTemplateKey();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "templateKey";
		}

		@Override
		public void setModified(RECTableTemplateDto container, boolean modified) {
			container.templateKey_m = modified;
		}

		@Override
		public boolean isModified(RECTableTemplateDto container) {
			return container.isTemplateKeyModified();
		}
	};

	private Long temporaryId;
	private  boolean temporaryId_m;
	public static final String PROPERTY_TEMPORARY_ID = "dpi-rectabletemplate-temporaryid";

	private transient static PropertyAccessor<RECTableTemplateDto, Long> temporaryId_pa = new PropertyAccessor<RECTableTemplateDto, Long>() {
		@Override
		public void setValue(RECTableTemplateDto container, Long object) {
			container.setTemporaryId(object);
		}

		@Override
		public Long getValue(RECTableTemplateDto container) {
			return container.getTemporaryId();
		}

		@Override
		public Class<?> getType() {
			return Long.class;
		}

		@Override
		public String getPath() {
			return "temporaryId";
		}

		@Override
		public void setModified(RECTableTemplateDto container, boolean modified) {
			container.temporaryId_m = modified;
		}

		@Override
		public boolean isModified(RECTableTemplateDto container) {
			return container.isTemporaryIdModified();
		}
	};


	public RECTableTemplateDto() {
		super();
	}

	public Long getTemplateId()  {
		if(! isDtoProxy()){
			return this.templateId;
		}

		if(isTemplateIdModified())
			return this.templateId;

		if(! GWT.isClient())
			return null;

		Long _value = dtoManager.getProperty(this, instantiatePropertyAccess().templateId());

		return _value;
	}


	public void setTemplateId(Long templateId)  {
		/* old value */
		Long oldValue = null;
		if(GWT.isClient())
			oldValue = getTemplateId();

		/* set new value */
		this.templateId = templateId;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(templateId_pa, oldValue, templateId, this.templateId_m));

		/* set indicator */
		this.templateId_m = true;

		this.fireObjectChangedEvent(RECTableTemplateDtoPA.INSTANCE.templateId(), oldValue);
	}


	public boolean isTemplateIdModified()  {
		return templateId_m;
	}


	public static PropertyAccessor<RECTableTemplateDto, Long> getTemplateIdPropertyAccessor()  {
		return templateId_pa;
	}


	public String getTemplateKey()  {
		if(! isDtoProxy()){
			return this.templateKey;
		}

		if(isTemplateKeyModified())
			return this.templateKey;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().templateKey());

		return _value;
	}


	public void setTemplateKey(String templateKey)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getTemplateKey();

		/* set new value */
		this.templateKey = templateKey;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(templateKey_pa, oldValue, templateKey, this.templateKey_m));

		/* set indicator */
		this.templateKey_m = true;

		this.fireObjectChangedEvent(RECTableTemplateDtoPA.INSTANCE.templateKey(), oldValue);
	}


	public boolean isTemplateKeyModified()  {
		return templateKey_m;
	}


	public static PropertyAccessor<RECTableTemplateDto, String> getTemplateKeyPropertyAccessor()  {
		return templateKey_pa;
	}


	public Long getTemporaryId()  {
		if(! isDtoProxy()){
			return this.temporaryId;
		}

		if(isTemporaryIdModified())
			return this.temporaryId;

		if(! GWT.isClient())
			return null;

		Long _value = dtoManager.getProperty(this, instantiatePropertyAccess().temporaryId());

		return _value;
	}


	public void setTemporaryId(Long temporaryId)  {
		/* old value */
		Long oldValue = null;
		if(GWT.isClient())
			oldValue = getTemporaryId();

		/* set new value */
		this.temporaryId = temporaryId;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(temporaryId_pa, oldValue, temporaryId, this.temporaryId_m));

		/* set indicator */
		this.temporaryId_m = true;

		this.fireObjectChangedEvent(RECTableTemplateDtoPA.INSTANCE.temporaryId(), oldValue);
	}


	public boolean isTemporaryIdModified()  {
		return temporaryId_m;
	}


	public static PropertyAccessor<RECTableTemplateDto, Long> getTemporaryIdPropertyAccessor()  {
		return temporaryId_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new RECTableTemplateDto2PosoMap();
	}

	public RECTableTemplateDtoPA instantiatePropertyAccess()  {
		return GWT.create(RECTableTemplateDtoPA.class);
	}

	public void clearModified()  {
		this.templateId = null;
		this.templateId_m = false;
		this.templateKey = null;
		this.templateKey_m = false;
		this.temporaryId = null;
		this.temporaryId_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(templateId_m)
			return true;
		if(templateKey_m)
			return true;
		if(temporaryId_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(templateId_pa);
		list.add(templateKey_pa);
		list.add(temporaryId_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(templateId_m)
			list.add(templateId_pa);
		if(templateKey_m)
			list.add(templateKey_pa);
		if(temporaryId_m)
			list.add(temporaryId_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(templateId_pa);
			list.add(templateKey_pa);
			list.add(temporaryId_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
