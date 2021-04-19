package net.datenwerke.gf.client.history.dto;

import com.google.gwt.core.client.GWT;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gf.client.history.dto.pa.HistoryLinkDtoPA;
import net.datenwerke.gf.client.history.dto.posomap.HistoryLinkDto2PosoMap;
import net.datenwerke.gf.service.history.HistoryLink;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;

/**
 * Dto for {@link HistoryLink}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class HistoryLinkDto extends RsDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String historyLinkBuilderIcon;
	private  boolean historyLinkBuilderIcon_m;
	public static final String PROPERTY_HISTORY_LINK_BUILDER_ICON = "dpi-historylink-historylinkbuildericon";

	private transient static PropertyAccessor<HistoryLinkDto, String> historyLinkBuilderIcon_pa = new PropertyAccessor<HistoryLinkDto, String>() {
		@Override
		public void setValue(HistoryLinkDto container, String object) {
			container.setHistoryLinkBuilderIcon(object);
		}

		@Override
		public String getValue(HistoryLinkDto container) {
			return container.getHistoryLinkBuilderIcon();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "historyLinkBuilderIcon";
		}

		@Override
		public void setModified(HistoryLinkDto container, boolean modified) {
			container.historyLinkBuilderIcon_m = modified;
		}

		@Override
		public boolean isModified(HistoryLinkDto container) {
			return container.isHistoryLinkBuilderIconModified();
		}
	};

	private String historyLinkBuilderId;
	private  boolean historyLinkBuilderId_m;
	public static final String PROPERTY_HISTORY_LINK_BUILDER_ID = "dpi-historylink-historylinkbuilderid";

	private transient static PropertyAccessor<HistoryLinkDto, String> historyLinkBuilderId_pa = new PropertyAccessor<HistoryLinkDto, String>() {
		@Override
		public void setValue(HistoryLinkDto container, String object) {
			container.setHistoryLinkBuilderId(object);
		}

		@Override
		public String getValue(HistoryLinkDto container) {
			return container.getHistoryLinkBuilderId();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "historyLinkBuilderId";
		}

		@Override
		public void setModified(HistoryLinkDto container, boolean modified) {
			container.historyLinkBuilderId_m = modified;
		}

		@Override
		public boolean isModified(HistoryLinkDto container) {
			return container.isHistoryLinkBuilderIdModified();
		}
	};

	private String historyLinkBuilderName;
	private  boolean historyLinkBuilderName_m;
	public static final String PROPERTY_HISTORY_LINK_BUILDER_NAME = "dpi-historylink-historylinkbuildername";

	private transient static PropertyAccessor<HistoryLinkDto, String> historyLinkBuilderName_pa = new PropertyAccessor<HistoryLinkDto, String>() {
		@Override
		public void setValue(HistoryLinkDto container, String object) {
			container.setHistoryLinkBuilderName(object);
		}

		@Override
		public String getValue(HistoryLinkDto container) {
			return container.getHistoryLinkBuilderName();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "historyLinkBuilderName";
		}

		@Override
		public void setModified(HistoryLinkDto container, boolean modified) {
			container.historyLinkBuilderName_m = modified;
		}

		@Override
		public boolean isModified(HistoryLinkDto container) {
			return container.isHistoryLinkBuilderNameModified();
		}
	};

	private String historyToken;
	private  boolean historyToken_m;
	public static final String PROPERTY_HISTORY_TOKEN = "dpi-historylink-historytoken";

	private transient static PropertyAccessor<HistoryLinkDto, String> historyToken_pa = new PropertyAccessor<HistoryLinkDto, String>() {
		@Override
		public void setValue(HistoryLinkDto container, String object) {
			container.setHistoryToken(object);
		}

		@Override
		public String getValue(HistoryLinkDto container) {
			return container.getHistoryToken();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "historyToken";
		}

		@Override
		public void setModified(HistoryLinkDto container, boolean modified) {
			container.historyToken_m = modified;
		}

		@Override
		public boolean isModified(HistoryLinkDto container) {
			return container.isHistoryTokenModified();
		}
	};

	private String objectCaption;
	private  boolean objectCaption_m;
	public static final String PROPERTY_OBJECT_CAPTION = "dpi-historylink-objectcaption";

	private transient static PropertyAccessor<HistoryLinkDto, String> objectCaption_pa = new PropertyAccessor<HistoryLinkDto, String>() {
		@Override
		public void setValue(HistoryLinkDto container, String object) {
			container.setObjectCaption(object);
		}

		@Override
		public String getValue(HistoryLinkDto container) {
			return container.getObjectCaption();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "objectCaption";
		}

		@Override
		public void setModified(HistoryLinkDto container, boolean modified) {
			container.objectCaption_m = modified;
		}

		@Override
		public boolean isModified(HistoryLinkDto container) {
			return container.isObjectCaptionModified();
		}
	};


	public HistoryLinkDto() {
		super();
	}

	public String getHistoryLinkBuilderIcon()  {
		if(! isDtoProxy()){
			return this.historyLinkBuilderIcon;
		}

		if(isHistoryLinkBuilderIconModified())
			return this.historyLinkBuilderIcon;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().historyLinkBuilderIcon());

		return _value;
	}


	public void setHistoryLinkBuilderIcon(String historyLinkBuilderIcon)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getHistoryLinkBuilderIcon();

		/* set new value */
		this.historyLinkBuilderIcon = historyLinkBuilderIcon;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(historyLinkBuilderIcon_pa, oldValue, historyLinkBuilderIcon, this.historyLinkBuilderIcon_m));

		/* set indicator */
		this.historyLinkBuilderIcon_m = true;

		this.fireObjectChangedEvent(HistoryLinkDtoPA.INSTANCE.historyLinkBuilderIcon(), oldValue);
	}


	public boolean isHistoryLinkBuilderIconModified()  {
		return historyLinkBuilderIcon_m;
	}


	public static PropertyAccessor<HistoryLinkDto, String> getHistoryLinkBuilderIconPropertyAccessor()  {
		return historyLinkBuilderIcon_pa;
	}


	public String getHistoryLinkBuilderId()  {
		if(! isDtoProxy()){
			return this.historyLinkBuilderId;
		}

		if(isHistoryLinkBuilderIdModified())
			return this.historyLinkBuilderId;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().historyLinkBuilderId());

		return _value;
	}


	public void setHistoryLinkBuilderId(String historyLinkBuilderId)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getHistoryLinkBuilderId();

		/* set new value */
		this.historyLinkBuilderId = historyLinkBuilderId;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(historyLinkBuilderId_pa, oldValue, historyLinkBuilderId, this.historyLinkBuilderId_m));

		/* set indicator */
		this.historyLinkBuilderId_m = true;

		this.fireObjectChangedEvent(HistoryLinkDtoPA.INSTANCE.historyLinkBuilderId(), oldValue);
	}


	public boolean isHistoryLinkBuilderIdModified()  {
		return historyLinkBuilderId_m;
	}


	public static PropertyAccessor<HistoryLinkDto, String> getHistoryLinkBuilderIdPropertyAccessor()  {
		return historyLinkBuilderId_pa;
	}


	public String getHistoryLinkBuilderName()  {
		if(! isDtoProxy()){
			return this.historyLinkBuilderName;
		}

		if(isHistoryLinkBuilderNameModified())
			return this.historyLinkBuilderName;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().historyLinkBuilderName());

		return _value;
	}


	public void setHistoryLinkBuilderName(String historyLinkBuilderName)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getHistoryLinkBuilderName();

		/* set new value */
		this.historyLinkBuilderName = historyLinkBuilderName;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(historyLinkBuilderName_pa, oldValue, historyLinkBuilderName, this.historyLinkBuilderName_m));

		/* set indicator */
		this.historyLinkBuilderName_m = true;

		this.fireObjectChangedEvent(HistoryLinkDtoPA.INSTANCE.historyLinkBuilderName(), oldValue);
	}


	public boolean isHistoryLinkBuilderNameModified()  {
		return historyLinkBuilderName_m;
	}


	public static PropertyAccessor<HistoryLinkDto, String> getHistoryLinkBuilderNamePropertyAccessor()  {
		return historyLinkBuilderName_pa;
	}


	public String getHistoryToken()  {
		if(! isDtoProxy()){
			return this.historyToken;
		}

		if(isHistoryTokenModified())
			return this.historyToken;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().historyToken());

		return _value;
	}


	public void setHistoryToken(String historyToken)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getHistoryToken();

		/* set new value */
		this.historyToken = historyToken;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(historyToken_pa, oldValue, historyToken, this.historyToken_m));

		/* set indicator */
		this.historyToken_m = true;

		this.fireObjectChangedEvent(HistoryLinkDtoPA.INSTANCE.historyToken(), oldValue);
	}


	public boolean isHistoryTokenModified()  {
		return historyToken_m;
	}


	public static PropertyAccessor<HistoryLinkDto, String> getHistoryTokenPropertyAccessor()  {
		return historyToken_pa;
	}


	public String getObjectCaption()  {
		if(! isDtoProxy()){
			return this.objectCaption;
		}

		if(isObjectCaptionModified())
			return this.objectCaption;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().objectCaption());

		return _value;
	}


	public void setObjectCaption(String objectCaption)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getObjectCaption();

		/* set new value */
		this.objectCaption = objectCaption;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(objectCaption_pa, oldValue, objectCaption, this.objectCaption_m));

		/* set indicator */
		this.objectCaption_m = true;

		this.fireObjectChangedEvent(HistoryLinkDtoPA.INSTANCE.objectCaption(), oldValue);
	}


	public boolean isObjectCaptionModified()  {
		return objectCaption_m;
	}


	public static PropertyAccessor<HistoryLinkDto, String> getObjectCaptionPropertyAccessor()  {
		return objectCaption_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new HistoryLinkDto2PosoMap();
	}

	public HistoryLinkDtoPA instantiatePropertyAccess()  {
		return GWT.create(HistoryLinkDtoPA.class);
	}

	public void clearModified()  {
		this.historyLinkBuilderIcon = null;
		this.historyLinkBuilderIcon_m = false;
		this.historyLinkBuilderId = null;
		this.historyLinkBuilderId_m = false;
		this.historyLinkBuilderName = null;
		this.historyLinkBuilderName_m = false;
		this.historyToken = null;
		this.historyToken_m = false;
		this.objectCaption = null;
		this.objectCaption_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(historyLinkBuilderIcon_m)
			return true;
		if(historyLinkBuilderId_m)
			return true;
		if(historyLinkBuilderName_m)
			return true;
		if(historyToken_m)
			return true;
		if(objectCaption_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(historyLinkBuilderIcon_pa);
		list.add(historyLinkBuilderId_pa);
		list.add(historyLinkBuilderName_pa);
		list.add(historyToken_pa);
		list.add(objectCaption_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(historyLinkBuilderIcon_m)
			list.add(historyLinkBuilderIcon_pa);
		if(historyLinkBuilderId_m)
			list.add(historyLinkBuilderId_pa);
		if(historyLinkBuilderName_m)
			list.add(historyLinkBuilderName_pa);
		if(historyToken_m)
			list.add(historyToken_pa);
		if(objectCaption_m)
			list.add(objectCaption_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(historyLinkBuilderIcon_pa);
			list.add(historyLinkBuilderId_pa);
			list.add(historyLinkBuilderName_pa);
			list.add(historyToken_pa);
			list.add(objectCaption_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
