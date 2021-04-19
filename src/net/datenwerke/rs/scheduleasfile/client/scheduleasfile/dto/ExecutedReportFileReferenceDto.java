package net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto;

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
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto.pa.ExecutedReportFileReferenceDtoPA;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto.posomap.ExecutedReportFileReferenceDto2PosoMap;
import net.datenwerke.rs.scheduleasfile.service.scheduleasfile.entities.ExecutedReportFileReference;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.TsDiskGeneralReferenceDtoDec;

/**
 * Dto for {@link ExecutedReportFileReference}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class ExecutedReportFileReferenceDto extends TsDiskGeneralReferenceDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String outputFormat;
	private  boolean outputFormat_m;
	public static final String PROPERTY_OUTPUT_FORMAT = "dpi-executedreportfilereference-outputformat";

	private transient static PropertyAccessor<ExecutedReportFileReferenceDto, String> outputFormat_pa = new PropertyAccessor<ExecutedReportFileReferenceDto, String>() {
		@Override
		public void setValue(ExecutedReportFileReferenceDto container, String object) {
			container.setOutputFormat(object);
		}

		@Override
		public String getValue(ExecutedReportFileReferenceDto container) {
			return container.getOutputFormat();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "outputFormat";
		}

		@Override
		public void setModified(ExecutedReportFileReferenceDto container, boolean modified) {
			container.outputFormat_m = modified;
		}

		@Override
		public boolean isModified(ExecutedReportFileReferenceDto container) {
			return container.isOutputFormatModified();
		}
	};

	private String iconStr;
	private  boolean iconStr_m;
	public static final String PROPERTY_ICON_STR = "dpi-executedreportfilereference-iconstr";

	private transient static PropertyAccessor<ExecutedReportFileReferenceDto, String> iconStr_pa = new PropertyAccessor<ExecutedReportFileReferenceDto, String>() {
		@Override
		public void setValue(ExecutedReportFileReferenceDto container, String object) {
			container.setIconStr(object);
		}

		@Override
		public String getValue(ExecutedReportFileReferenceDto container) {
			return container.getIconStr();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "iconStr";
		}

		@Override
		public void setModified(ExecutedReportFileReferenceDto container, boolean modified) {
			container.iconStr_m = modified;
		}

		@Override
		public boolean isModified(ExecutedReportFileReferenceDto container) {
			return container.isIconStrModified();
		}
	};

	private String typeStr;
	private  boolean typeStr_m;
	public static final String PROPERTY_TYPE_STR = "dpi-executedreportfilereference-typestr";

	private transient static PropertyAccessor<ExecutedReportFileReferenceDto, String> typeStr_pa = new PropertyAccessor<ExecutedReportFileReferenceDto, String>() {
		@Override
		public void setValue(ExecutedReportFileReferenceDto container, String object) {
			container.setTypeStr(object);
		}

		@Override
		public String getValue(ExecutedReportFileReferenceDto container) {
			return container.getTypeStr();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "typeStr";
		}

		@Override
		public void setModified(ExecutedReportFileReferenceDto container, boolean modified) {
			container.typeStr_m = modified;
		}

		@Override
		public boolean isModified(ExecutedReportFileReferenceDto container) {
			return container.isTypeStrModified();
		}
	};


	public ExecutedReportFileReferenceDto() {
		super();
	}

	public String getOutputFormat()  {
		if(! isDtoProxy()){
			return this.outputFormat;
		}

		if(isOutputFormatModified())
			return this.outputFormat;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().outputFormat());

		return _value;
	}


	public void setOutputFormat(String outputFormat)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getOutputFormat();

		/* set new value */
		this.outputFormat = outputFormat;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(outputFormat_pa, oldValue, outputFormat, this.outputFormat_m));

		/* set indicator */
		this.outputFormat_m = true;

		this.fireObjectChangedEvent(ExecutedReportFileReferenceDtoPA.INSTANCE.outputFormat(), oldValue);
	}


	public boolean isOutputFormatModified()  {
		return outputFormat_m;
	}


	public static PropertyAccessor<ExecutedReportFileReferenceDto, String> getOutputFormatPropertyAccessor()  {
		return outputFormat_pa;
	}


	public String getIconStr()  {
		if(! isDtoProxy()){
			return this.iconStr;
		}

		if(isIconStrModified())
			return this.iconStr;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().iconStr());

		return _value;
	}


	public void setIconStr(String iconStr)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getIconStr();

		/* set new value */
		this.iconStr = iconStr;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(iconStr_pa, oldValue, iconStr, this.iconStr_m));

		/* set indicator */
		this.iconStr_m = true;

		this.fireObjectChangedEvent(ExecutedReportFileReferenceDtoPA.INSTANCE.iconStr(), oldValue);
	}


	public boolean isIconStrModified()  {
		return iconStr_m;
	}


	public static PropertyAccessor<ExecutedReportFileReferenceDto, String> getIconStrPropertyAccessor()  {
		return iconStr_pa;
	}


	public String getTypeStr()  {
		if(! isDtoProxy()){
			return this.typeStr;
		}

		if(isTypeStrModified())
			return this.typeStr;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().typeStr());

		return _value;
	}


	public void setTypeStr(String typeStr)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getTypeStr();

		/* set new value */
		this.typeStr = typeStr;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(typeStr_pa, oldValue, typeStr, this.typeStr_m));

		/* set indicator */
		this.typeStr_m = true;

		this.fireObjectChangedEvent(ExecutedReportFileReferenceDtoPA.INSTANCE.typeStr(), oldValue);
	}


	public boolean isTypeStrModified()  {
		return typeStr_m;
	}


	public static PropertyAccessor<ExecutedReportFileReferenceDto, String> getTypeStrPropertyAccessor()  {
		return typeStr_pa;
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
		if(! (obj instanceof ExecutedReportFileReferenceDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((ExecutedReportFileReferenceDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new ExecutedReportFileReferenceDto2PosoMap();
	}

	public ExecutedReportFileReferenceDtoPA instantiatePropertyAccess()  {
		return GWT.create(ExecutedReportFileReferenceDtoPA.class);
	}

	public void clearModified()  {
		this.outputFormat = null;
		this.outputFormat_m = false;
		this.iconStr = null;
		this.iconStr_m = false;
		this.typeStr = null;
		this.typeStr_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(outputFormat_m)
			return true;
		if(iconStr_m)
			return true;
		if(typeStr_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(outputFormat_pa);
		list.add(iconStr_pa);
		list.add(typeStr_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(outputFormat_m)
			list.add(outputFormat_pa);
		if(iconStr_m)
			list.add(iconStr_pa);
		if(typeStr_m)
			list.add(typeStr_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(iconStr_pa);
			list.add(typeStr_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(outputFormat_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
