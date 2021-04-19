package net.datenwerke.rs.saiku.client.saiku.dto;

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
import net.datenwerke.rs.core.client.reportmanager.dto.reports.decorator.ReportDtoDec;
import net.datenwerke.rs.saiku.client.saiku.dto.pa.SaikuReportDtoPA;
import net.datenwerke.rs.saiku.client.saiku.dto.posomap.SaikuReportDto2PosoMap;
import net.datenwerke.rs.saiku.client.saiku.locale.SaikuMessages;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * Dto for {@link SaikuReport}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class SaikuReportDto extends ReportDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private boolean allowMdx;
	private  boolean allowMdx_m;
	public static final String PROPERTY_ALLOW_MDX = "dpi-saikureport-allowmdx";

	private transient static PropertyAccessor<SaikuReportDto, Boolean> allowMdx_pa = new PropertyAccessor<SaikuReportDto, Boolean>() {
		@Override
		public void setValue(SaikuReportDto container, Boolean object) {
			container.setAllowMdx(object);
		}

		@Override
		public Boolean getValue(SaikuReportDto container) {
			return container.isAllowMdx();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "allowMdx";
		}

		@Override
		public void setModified(SaikuReportDto container, boolean modified) {
			container.allowMdx_m = modified;
		}

		@Override
		public boolean isModified(SaikuReportDto container) {
			return container.isAllowMdxModified();
		}
	};

	private boolean createdFromPivotReport;
	private  boolean createdFromPivotReport_m;
	public static final String PROPERTY_CREATED_FROM_PIVOT_REPORT = "dpi-saikureport-createdfrompivotreport";

	private transient static PropertyAccessor<SaikuReportDto, Boolean> createdFromPivotReport_pa = new PropertyAccessor<SaikuReportDto, Boolean>() {
		@Override
		public void setValue(SaikuReportDto container, Boolean object) {
			container.setCreatedFromPivotReport(object);
		}

		@Override
		public Boolean getValue(SaikuReportDto container) {
			return container.isCreatedFromPivotReport();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "createdFromPivotReport";
		}

		@Override
		public void setModified(SaikuReportDto container, boolean modified) {
			container.createdFromPivotReport_m = modified;
		}

		@Override
		public boolean isModified(SaikuReportDto container) {
			return container.isCreatedFromPivotReportModified();
		}
	};

	private boolean hideParents;
	private  boolean hideParents_m;
	public static final String PROPERTY_HIDE_PARENTS = "dpi-saikureport-hideparents";

	private transient static PropertyAccessor<SaikuReportDto, Boolean> hideParents_pa = new PropertyAccessor<SaikuReportDto, Boolean>() {
		@Override
		public void setValue(SaikuReportDto container, Boolean object) {
			container.setHideParents(object);
		}

		@Override
		public Boolean getValue(SaikuReportDto container) {
			return container.isHideParents();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "hideParents";
		}

		@Override
		public void setModified(SaikuReportDto container, boolean modified) {
			container.hideParents_m = modified;
		}

		@Override
		public boolean isModified(SaikuReportDto container) {
			return container.isHideParentsModified();
		}
	};

	private long originalPivotReportId;
	private  boolean originalPivotReportId_m;
	public static final String PROPERTY_ORIGINAL_PIVOT_REPORT_ID = "dpi-saikureport-originalpivotreportid";

	private transient static PropertyAccessor<SaikuReportDto, Long> originalPivotReportId_pa = new PropertyAccessor<SaikuReportDto, Long>() {
		@Override
		public void setValue(SaikuReportDto container, Long object) {
			container.setOriginalPivotReportId(object);
		}

		@Override
		public Long getValue(SaikuReportDto container) {
			return container.getOriginalPivotReportId();
		}

		@Override
		public Class<?> getType() {
			return Long.class;
		}

		@Override
		public String getPath() {
			return "originalPivotReportId";
		}

		@Override
		public void setModified(SaikuReportDto container, boolean modified) {
			container.originalPivotReportId_m = modified;
		}

		@Override
		public boolean isModified(SaikuReportDto container) {
			return container.isOriginalPivotReportIdModified();
		}
	};

	private String queryXml;
	private  boolean queryXml_m;
	public static final String PROPERTY_QUERY_XML = "dpi-saikureport-queryxml";

	private transient static PropertyAccessor<SaikuReportDto, String> queryXml_pa = new PropertyAccessor<SaikuReportDto, String>() {
		@Override
		public void setValue(SaikuReportDto container, String object) {
			container.setQueryXml(object);
		}

		@Override
		public String getValue(SaikuReportDto container) {
			return container.getQueryXml();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "queryXml";
		}

		@Override
		public void setModified(SaikuReportDto container, boolean modified) {
			container.queryXml_m = modified;
		}

		@Override
		public boolean isModified(SaikuReportDto container) {
			return container.isQueryXmlModified();
		}
	};


	public SaikuReportDto() {
		super();
	}

	public boolean isAllowMdx()  {
		if(! isDtoProxy()){
			return this.allowMdx;
		}

		if(isAllowMdxModified())
			return this.allowMdx;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().allowMdx());

		return _value;
	}


	public void setAllowMdx(boolean allowMdx)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isAllowMdx();

		/* set new value */
		this.allowMdx = allowMdx;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(allowMdx_pa, oldValue, allowMdx, this.allowMdx_m));

		/* set indicator */
		this.allowMdx_m = true;

		this.fireObjectChangedEvent(SaikuReportDtoPA.INSTANCE.allowMdx(), oldValue);
	}


	public boolean isAllowMdxModified()  {
		return allowMdx_m;
	}


	public static PropertyAccessor<SaikuReportDto, Boolean> getAllowMdxPropertyAccessor()  {
		return allowMdx_pa;
	}


	public boolean isCreatedFromPivotReport()  {
		if(! isDtoProxy()){
			return this.createdFromPivotReport;
		}

		if(isCreatedFromPivotReportModified())
			return this.createdFromPivotReport;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().createdFromPivotReport());

		return _value;
	}


	public void setCreatedFromPivotReport(boolean createdFromPivotReport)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isCreatedFromPivotReport();

		/* set new value */
		this.createdFromPivotReport = createdFromPivotReport;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(createdFromPivotReport_pa, oldValue, createdFromPivotReport, this.createdFromPivotReport_m));

		/* set indicator */
		this.createdFromPivotReport_m = true;

		this.fireObjectChangedEvent(SaikuReportDtoPA.INSTANCE.createdFromPivotReport(), oldValue);
	}


	public boolean isCreatedFromPivotReportModified()  {
		return createdFromPivotReport_m;
	}


	public static PropertyAccessor<SaikuReportDto, Boolean> getCreatedFromPivotReportPropertyAccessor()  {
		return createdFromPivotReport_pa;
	}


	public boolean isHideParents()  {
		if(! isDtoProxy()){
			return this.hideParents;
		}

		if(isHideParentsModified())
			return this.hideParents;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().hideParents());

		return _value;
	}


	public void setHideParents(boolean hideParents)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isHideParents();

		/* set new value */
		this.hideParents = hideParents;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(hideParents_pa, oldValue, hideParents, this.hideParents_m));

		/* set indicator */
		this.hideParents_m = true;

		this.fireObjectChangedEvent(SaikuReportDtoPA.INSTANCE.hideParents(), oldValue);
	}


	public boolean isHideParentsModified()  {
		return hideParents_m;
	}


	public static PropertyAccessor<SaikuReportDto, Boolean> getHideParentsPropertyAccessor()  {
		return hideParents_pa;
	}


	public long getOriginalPivotReportId()  {
		if(! isDtoProxy()){
			return this.originalPivotReportId;
		}

		if(isOriginalPivotReportIdModified())
			return this.originalPivotReportId;

		if(! GWT.isClient())
			return 0;

		long _value = dtoManager.getProperty(this, instantiatePropertyAccess().originalPivotReportId());

		return _value;
	}


	public void setOriginalPivotReportId(long originalPivotReportId)  {
		/* old value */
		long oldValue = 0;
		if(GWT.isClient())
			oldValue = getOriginalPivotReportId();

		/* set new value */
		this.originalPivotReportId = originalPivotReportId;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(originalPivotReportId_pa, oldValue, originalPivotReportId, this.originalPivotReportId_m));

		/* set indicator */
		this.originalPivotReportId_m = true;

		this.fireObjectChangedEvent(SaikuReportDtoPA.INSTANCE.originalPivotReportId(), oldValue);
	}


	public boolean isOriginalPivotReportIdModified()  {
		return originalPivotReportId_m;
	}


	public static PropertyAccessor<SaikuReportDto, Long> getOriginalPivotReportIdPropertyAccessor()  {
		return originalPivotReportId_pa;
	}


	public String getQueryXml()  {
		if(! isDtoProxy()){
			return this.queryXml;
		}

		if(isQueryXmlModified())
			return this.queryXml;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().queryXml());

		return _value;
	}


	public void setQueryXml(String queryXml)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getQueryXml();

		/* set new value */
		this.queryXml = queryXml;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(queryXml_pa, oldValue, queryXml, this.queryXml_m));

		/* set indicator */
		this.queryXml_m = true;

		this.fireObjectChangedEvent(SaikuReportDtoPA.INSTANCE.queryXml(), oldValue);
	}


	public boolean isQueryXmlModified()  {
		return queryXml_m;
	}


	public static PropertyAccessor<SaikuReportDto, String> getQueryXmlPropertyAccessor()  {
		return queryXml_pa;
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
	public String toTypeDescription()  {
		return SaikuMessages.INSTANCE.reportTypeName();
	}

	@Override
	public BaseIcon toIcon()  {
		return BaseIcon.from("cubes");
	}

	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof SaikuReportDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((SaikuReportDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new SaikuReportDto2PosoMap();
	}

	public SaikuReportDtoPA instantiatePropertyAccess()  {
		return GWT.create(SaikuReportDtoPA.class);
	}

	public void clearModified()  {
		this.allowMdx = false;
		this.allowMdx_m = false;
		this.createdFromPivotReport = false;
		this.createdFromPivotReport_m = false;
		this.hideParents = false;
		this.hideParents_m = false;
		this.originalPivotReportId = 0;
		this.originalPivotReportId_m = false;
		this.queryXml = null;
		this.queryXml_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(allowMdx_m)
			return true;
		if(createdFromPivotReport_m)
			return true;
		if(hideParents_m)
			return true;
		if(originalPivotReportId_m)
			return true;
		if(queryXml_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(allowMdx_pa);
		list.add(createdFromPivotReport_pa);
		list.add(hideParents_pa);
		list.add(originalPivotReportId_pa);
		list.add(queryXml_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(allowMdx_m)
			list.add(allowMdx_pa);
		if(createdFromPivotReport_m)
			list.add(createdFromPivotReport_pa);
		if(hideParents_m)
			list.add(hideParents_pa);
		if(originalPivotReportId_m)
			list.add(originalPivotReportId_pa);
		if(queryXml_m)
			list.add(queryXml_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(allowMdx_pa);
			list.add(createdFromPivotReport_pa);
			list.add(hideParents_pa);
			list.add(originalPivotReportId_pa);
			list.add(queryXml_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
