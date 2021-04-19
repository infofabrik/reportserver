package net.datenwerke.rs.base.client.reportengines.table.dto;

import com.google.gwt.core.client.GWT;
import java.lang.Boolean;
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
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.ColumnFormatDateDtoPA;
import net.datenwerke.rs.base.client.reportengines.table.dto.posomap.ColumnFormatDateDto2PosoMap;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatDate;

/**
 * Dto for {@link ColumnFormatDate}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class ColumnFormatDateDto extends ColumnFormatDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String baseFormat;
	private  boolean baseFormat_m;
	public static final String PROPERTY_BASE_FORMAT = "dpi-columnformatdate-baseformat";

	private transient static PropertyAccessor<ColumnFormatDateDto, String> baseFormat_pa = new PropertyAccessor<ColumnFormatDateDto, String>() {
		@Override
		public void setValue(ColumnFormatDateDto container, String object) {
			container.setBaseFormat(object);
		}

		@Override
		public String getValue(ColumnFormatDateDto container) {
			return container.getBaseFormat();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "baseFormat";
		}

		@Override
		public void setModified(ColumnFormatDateDto container, boolean modified) {
			container.baseFormat_m = modified;
		}

		@Override
		public boolean isModified(ColumnFormatDateDto container) {
			return container.isBaseFormatModified();
		}
	};

	private String errorReplacement;
	private  boolean errorReplacement_m;
	public static final String PROPERTY_ERROR_REPLACEMENT = "dpi-columnformatdate-errorreplacement";

	private transient static PropertyAccessor<ColumnFormatDateDto, String> errorReplacement_pa = new PropertyAccessor<ColumnFormatDateDto, String>() {
		@Override
		public void setValue(ColumnFormatDateDto container, String object) {
			container.setErrorReplacement(object);
		}

		@Override
		public String getValue(ColumnFormatDateDto container) {
			return container.getErrorReplacement();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "errorReplacement";
		}

		@Override
		public void setModified(ColumnFormatDateDto container, boolean modified) {
			container.errorReplacement_m = modified;
		}

		@Override
		public boolean isModified(ColumnFormatDateDto container) {
			return container.isErrorReplacementModified();
		}
	};

	private Boolean replaceErrors;
	private  boolean replaceErrors_m;
	public static final String PROPERTY_REPLACE_ERRORS = "dpi-columnformatdate-replaceerrors";

	private transient static PropertyAccessor<ColumnFormatDateDto, Boolean> replaceErrors_pa = new PropertyAccessor<ColumnFormatDateDto, Boolean>() {
		@Override
		public void setValue(ColumnFormatDateDto container, Boolean object) {
			container.setReplaceErrors(object);
		}

		@Override
		public Boolean getValue(ColumnFormatDateDto container) {
			return container.isReplaceErrors();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "replaceErrors";
		}

		@Override
		public void setModified(ColumnFormatDateDto container, boolean modified) {
			container.replaceErrors_m = modified;
		}

		@Override
		public boolean isModified(ColumnFormatDateDto container) {
			return container.isReplaceErrorsModified();
		}
	};

	private Boolean rollOver;
	private  boolean rollOver_m;
	public static final String PROPERTY_ROLL_OVER = "dpi-columnformatdate-rollover";

	private transient static PropertyAccessor<ColumnFormatDateDto, Boolean> rollOver_pa = new PropertyAccessor<ColumnFormatDateDto, Boolean>() {
		@Override
		public void setValue(ColumnFormatDateDto container, Boolean object) {
			container.setRollOver(object);
		}

		@Override
		public Boolean getValue(ColumnFormatDateDto container) {
			return container.isRollOver();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "rollOver";
		}

		@Override
		public void setModified(ColumnFormatDateDto container, boolean modified) {
			container.rollOver_m = modified;
		}

		@Override
		public boolean isModified(ColumnFormatDateDto container) {
			return container.isRollOverModified();
		}
	};

	private String targetFormat;
	private  boolean targetFormat_m;
	public static final String PROPERTY_TARGET_FORMAT = "dpi-columnformatdate-targetformat";

	private transient static PropertyAccessor<ColumnFormatDateDto, String> targetFormat_pa = new PropertyAccessor<ColumnFormatDateDto, String>() {
		@Override
		public void setValue(ColumnFormatDateDto container, String object) {
			container.setTargetFormat(object);
		}

		@Override
		public String getValue(ColumnFormatDateDto container) {
			return container.getTargetFormat();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "targetFormat";
		}

		@Override
		public void setModified(ColumnFormatDateDto container, boolean modified) {
			container.targetFormat_m = modified;
		}

		@Override
		public boolean isModified(ColumnFormatDateDto container) {
			return container.isTargetFormatModified();
		}
	};


	public ColumnFormatDateDto() {
		super();
	}

	public String getBaseFormat()  {
		if(! isDtoProxy()){
			return this.baseFormat;
		}

		if(isBaseFormatModified())
			return this.baseFormat;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().baseFormat());

		return _value;
	}


	public void setBaseFormat(String baseFormat)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getBaseFormat();

		/* set new value */
		this.baseFormat = baseFormat;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(baseFormat_pa, oldValue, baseFormat, this.baseFormat_m));

		/* set indicator */
		this.baseFormat_m = true;

		this.fireObjectChangedEvent(ColumnFormatDateDtoPA.INSTANCE.baseFormat(), oldValue);
	}


	public boolean isBaseFormatModified()  {
		return baseFormat_m;
	}


	public static PropertyAccessor<ColumnFormatDateDto, String> getBaseFormatPropertyAccessor()  {
		return baseFormat_pa;
	}


	public String getErrorReplacement()  {
		if(! isDtoProxy()){
			return this.errorReplacement;
		}

		if(isErrorReplacementModified())
			return this.errorReplacement;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().errorReplacement());

		return _value;
	}


	public void setErrorReplacement(String errorReplacement)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getErrorReplacement();

		/* set new value */
		this.errorReplacement = errorReplacement;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(errorReplacement_pa, oldValue, errorReplacement, this.errorReplacement_m));

		/* set indicator */
		this.errorReplacement_m = true;

		this.fireObjectChangedEvent(ColumnFormatDateDtoPA.INSTANCE.errorReplacement(), oldValue);
	}


	public boolean isErrorReplacementModified()  {
		return errorReplacement_m;
	}


	public static PropertyAccessor<ColumnFormatDateDto, String> getErrorReplacementPropertyAccessor()  {
		return errorReplacement_pa;
	}


	public Boolean isReplaceErrors()  {
		if(! isDtoProxy()){
			return this.replaceErrors;
		}

		if(isReplaceErrorsModified())
			return this.replaceErrors;

		if(! GWT.isClient())
			return null;

		Boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().replaceErrors());

		return _value;
	}


	public void setReplaceErrors(Boolean replaceErrors)  {
		/* old value */
		Boolean oldValue = null;
		if(GWT.isClient())
			oldValue = isReplaceErrors();

		/* set new value */
		this.replaceErrors = replaceErrors;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(replaceErrors_pa, oldValue, replaceErrors, this.replaceErrors_m));

		/* set indicator */
		this.replaceErrors_m = true;

		this.fireObjectChangedEvent(ColumnFormatDateDtoPA.INSTANCE.replaceErrors(), oldValue);
	}


	public boolean isReplaceErrorsModified()  {
		return replaceErrors_m;
	}


	public static PropertyAccessor<ColumnFormatDateDto, Boolean> getReplaceErrorsPropertyAccessor()  {
		return replaceErrors_pa;
	}


	public Boolean isRollOver()  {
		if(! isDtoProxy()){
			return this.rollOver;
		}

		if(isRollOverModified())
			return this.rollOver;

		if(! GWT.isClient())
			return null;

		Boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().rollOver());

		return _value;
	}


	public void setRollOver(Boolean rollOver)  {
		/* old value */
		Boolean oldValue = null;
		if(GWT.isClient())
			oldValue = isRollOver();

		/* set new value */
		this.rollOver = rollOver;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(rollOver_pa, oldValue, rollOver, this.rollOver_m));

		/* set indicator */
		this.rollOver_m = true;

		this.fireObjectChangedEvent(ColumnFormatDateDtoPA.INSTANCE.rollOver(), oldValue);
	}


	public boolean isRollOverModified()  {
		return rollOver_m;
	}


	public static PropertyAccessor<ColumnFormatDateDto, Boolean> getRollOverPropertyAccessor()  {
		return rollOver_pa;
	}


	public String getTargetFormat()  {
		if(! isDtoProxy()){
			return this.targetFormat;
		}

		if(isTargetFormatModified())
			return this.targetFormat;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().targetFormat());

		return _value;
	}


	public void setTargetFormat(String targetFormat)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getTargetFormat();

		/* set new value */
		this.targetFormat = targetFormat;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(targetFormat_pa, oldValue, targetFormat, this.targetFormat_m));

		/* set indicator */
		this.targetFormat_m = true;

		this.fireObjectChangedEvent(ColumnFormatDateDtoPA.INSTANCE.targetFormat(), oldValue);
	}


	public boolean isTargetFormatModified()  {
		return targetFormat_m;
	}


	public static PropertyAccessor<ColumnFormatDateDto, String> getTargetFormatPropertyAccessor()  {
		return targetFormat_pa;
	}


	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof ColumnFormatDateDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((ColumnFormatDateDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new ColumnFormatDateDto2PosoMap();
	}

	public ColumnFormatDateDtoPA instantiatePropertyAccess()  {
		return GWT.create(ColumnFormatDateDtoPA.class);
	}

	public void clearModified()  {
		this.baseFormat = null;
		this.baseFormat_m = false;
		this.errorReplacement = null;
		this.errorReplacement_m = false;
		this.replaceErrors = null;
		this.replaceErrors_m = false;
		this.rollOver = null;
		this.rollOver_m = false;
		this.targetFormat = null;
		this.targetFormat_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(baseFormat_m)
			return true;
		if(errorReplacement_m)
			return true;
		if(replaceErrors_m)
			return true;
		if(rollOver_m)
			return true;
		if(targetFormat_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(baseFormat_pa);
		list.add(errorReplacement_pa);
		list.add(replaceErrors_pa);
		list.add(rollOver_pa);
		list.add(targetFormat_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(baseFormat_m)
			list.add(baseFormat_pa);
		if(errorReplacement_m)
			list.add(errorReplacement_pa);
		if(replaceErrors_m)
			list.add(replaceErrors_pa);
		if(rollOver_m)
			list.add(rollOver_pa);
		if(targetFormat_m)
			list.add(targetFormat_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(baseFormat_pa);
			list.add(errorReplacement_pa);
			list.add(replaceErrors_pa);
			list.add(rollOver_pa);
			list.add(targetFormat_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
