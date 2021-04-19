package net.datenwerke.rs.birt.client.datasources.dto;

import com.google.gwt.core.client.GWT;
import java.lang.String;
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
import net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceTargetTypeDto;
import net.datenwerke.rs.birt.client.datasources.dto.pa.BirtReportDatasourceConfigDtoPA;
import net.datenwerke.rs.birt.client.datasources.dto.posomap.BirtReportDatasourceConfigDto2PosoMap;
import net.datenwerke.rs.birt.client.reportengines.dto.BirtReportDto;
import net.datenwerke.rs.birt.service.datasources.birtreport.entities.BirtReportDatasourceConfig;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto;

/**
 * Dto for {@link BirtReportDatasourceConfig}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class BirtReportDatasourceConfigDto extends DatasourceDefinitionConfigDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String queryWrapper;
	private  boolean queryWrapper_m;
	public static final String PROPERTY_QUERY_WRAPPER = "dpi-birtreportdatasourceconfig-querywrapper";

	private transient static PropertyAccessor<BirtReportDatasourceConfigDto, String> queryWrapper_pa = new PropertyAccessor<BirtReportDatasourceConfigDto, String>() {
		@Override
		public void setValue(BirtReportDatasourceConfigDto container, String object) {
			container.setQueryWrapper(object);
		}

		@Override
		public String getValue(BirtReportDatasourceConfigDto container) {
			return container.getQueryWrapper();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "queryWrapper";
		}

		@Override
		public void setModified(BirtReportDatasourceConfigDto container, boolean modified) {
			container.queryWrapper_m = modified;
		}

		@Override
		public boolean isModified(BirtReportDatasourceConfigDto container) {
			return container.isQueryWrapperModified();
		}
	};

	private BirtReportDto report;
	private  boolean report_m;
	public static final String PROPERTY_REPORT = "dpi-birtreportdatasourceconfig-report";

	private transient static PropertyAccessor<BirtReportDatasourceConfigDto, BirtReportDto> report_pa = new PropertyAccessor<BirtReportDatasourceConfigDto, BirtReportDto>() {
		@Override
		public void setValue(BirtReportDatasourceConfigDto container, BirtReportDto object) {
			container.setReport(object);
		}

		@Override
		public BirtReportDto getValue(BirtReportDatasourceConfigDto container) {
			return container.getReport();
		}

		@Override
		public Class<?> getType() {
			return BirtReportDto.class;
		}

		@Override
		public String getPath() {
			return "report";
		}

		@Override
		public void setModified(BirtReportDatasourceConfigDto container, boolean modified) {
			container.report_m = modified;
		}

		@Override
		public boolean isModified(BirtReportDatasourceConfigDto container) {
			return container.isReportModified();
		}
	};

	private String target;
	private  boolean target_m;
	public static final String PROPERTY_TARGET = "dpi-birtreportdatasourceconfig-target";

	private transient static PropertyAccessor<BirtReportDatasourceConfigDto, String> target_pa = new PropertyAccessor<BirtReportDatasourceConfigDto, String>() {
		@Override
		public void setValue(BirtReportDatasourceConfigDto container, String object) {
			container.setTarget(object);
		}

		@Override
		public String getValue(BirtReportDatasourceConfigDto container) {
			return container.getTarget();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "target";
		}

		@Override
		public void setModified(BirtReportDatasourceConfigDto container, boolean modified) {
			container.target_m = modified;
		}

		@Override
		public boolean isModified(BirtReportDatasourceConfigDto container) {
			return container.isTargetModified();
		}
	};

	private BirtReportDatasourceTargetTypeDto targetType;
	private  boolean targetType_m;
	public static final String PROPERTY_TARGET_TYPE = "dpi-birtreportdatasourceconfig-targettype";

	private transient static PropertyAccessor<BirtReportDatasourceConfigDto, BirtReportDatasourceTargetTypeDto> targetType_pa = new PropertyAccessor<BirtReportDatasourceConfigDto, BirtReportDatasourceTargetTypeDto>() {
		@Override
		public void setValue(BirtReportDatasourceConfigDto container, BirtReportDatasourceTargetTypeDto object) {
			container.setTargetType(object);
		}

		@Override
		public BirtReportDatasourceTargetTypeDto getValue(BirtReportDatasourceConfigDto container) {
			return container.getTargetType();
		}

		@Override
		public Class<?> getType() {
			return BirtReportDatasourceTargetTypeDto.class;
		}

		@Override
		public String getPath() {
			return "targetType";
		}

		@Override
		public void setModified(BirtReportDatasourceConfigDto container, boolean modified) {
			container.targetType_m = modified;
		}

		@Override
		public boolean isModified(BirtReportDatasourceConfigDto container) {
			return container.isTargetTypeModified();
		}
	};


	public BirtReportDatasourceConfigDto() {
		super();
	}

	public String getQueryWrapper()  {
		if(! isDtoProxy()){
			return this.queryWrapper;
		}

		if(isQueryWrapperModified())
			return this.queryWrapper;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().queryWrapper());

		return _value;
	}


	public void setQueryWrapper(String queryWrapper)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getQueryWrapper();

		/* set new value */
		this.queryWrapper = queryWrapper;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(queryWrapper_pa, oldValue, queryWrapper, this.queryWrapper_m));

		/* set indicator */
		this.queryWrapper_m = true;

		this.fireObjectChangedEvent(BirtReportDatasourceConfigDtoPA.INSTANCE.queryWrapper(), oldValue);
	}


	public boolean isQueryWrapperModified()  {
		return queryWrapper_m;
	}


	public static PropertyAccessor<BirtReportDatasourceConfigDto, String> getQueryWrapperPropertyAccessor()  {
		return queryWrapper_pa;
	}


	public BirtReportDto getReport()  {
		if(! isDtoProxy()){
			return this.report;
		}

		if(isReportModified())
			return this.report;

		if(! GWT.isClient())
			return null;

		BirtReportDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().report());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isReportModified())
						setReport((BirtReportDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setReport(BirtReportDto report)  {
		/* old value */
		BirtReportDto oldValue = null;
		if(GWT.isClient())
			oldValue = getReport();

		/* set new value */
		this.report = report;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(report_pa, oldValue, report, this.report_m));

		/* set indicator */
		this.report_m = true;

		this.fireObjectChangedEvent(BirtReportDatasourceConfigDtoPA.INSTANCE.report(), oldValue);
	}


	public boolean isReportModified()  {
		return report_m;
	}


	public static PropertyAccessor<BirtReportDatasourceConfigDto, BirtReportDto> getReportPropertyAccessor()  {
		return report_pa;
	}


	public String getTarget()  {
		if(! isDtoProxy()){
			return this.target;
		}

		if(isTargetModified())
			return this.target;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().target());

		return _value;
	}


	public void setTarget(String target)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getTarget();

		/* set new value */
		this.target = target;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(target_pa, oldValue, target, this.target_m));

		/* set indicator */
		this.target_m = true;

		this.fireObjectChangedEvent(BirtReportDatasourceConfigDtoPA.INSTANCE.target(), oldValue);
	}


	public boolean isTargetModified()  {
		return target_m;
	}


	public static PropertyAccessor<BirtReportDatasourceConfigDto, String> getTargetPropertyAccessor()  {
		return target_pa;
	}


	public BirtReportDatasourceTargetTypeDto getTargetType()  {
		if(! isDtoProxy()){
			return this.targetType;
		}

		if(isTargetTypeModified())
			return this.targetType;

		if(! GWT.isClient())
			return null;

		BirtReportDatasourceTargetTypeDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().targetType());

		return _value;
	}


	public void setTargetType(BirtReportDatasourceTargetTypeDto targetType)  {
		/* old value */
		BirtReportDatasourceTargetTypeDto oldValue = null;
		if(GWT.isClient())
			oldValue = getTargetType();

		/* set new value */
		this.targetType = targetType;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(targetType_pa, oldValue, targetType, this.targetType_m));

		/* set indicator */
		this.targetType_m = true;

		this.fireObjectChangedEvent(BirtReportDatasourceConfigDtoPA.INSTANCE.targetType(), oldValue);
	}


	public boolean isTargetTypeModified()  {
		return targetType_m;
	}


	public static PropertyAccessor<BirtReportDatasourceConfigDto, BirtReportDatasourceTargetTypeDto> getTargetTypePropertyAccessor()  {
		return targetType_pa;
	}


	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof BirtReportDatasourceConfigDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((BirtReportDatasourceConfigDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new BirtReportDatasourceConfigDto2PosoMap();
	}

	public BirtReportDatasourceConfigDtoPA instantiatePropertyAccess()  {
		return GWT.create(BirtReportDatasourceConfigDtoPA.class);
	}

	public void clearModified()  {
		this.queryWrapper = null;
		this.queryWrapper_m = false;
		this.report = null;
		this.report_m = false;
		this.target = null;
		this.target_m = false;
		this.targetType = null;
		this.targetType_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(queryWrapper_m)
			return true;
		if(report_m)
			return true;
		if(target_m)
			return true;
		if(targetType_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(queryWrapper_pa);
		list.add(report_pa);
		list.add(target_pa);
		list.add(targetType_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(queryWrapper_m)
			list.add(queryWrapper_pa);
		if(report_m)
			list.add(report_pa);
		if(target_m)
			list.add(target_pa);
		if(targetType_m)
			list.add(targetType_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(queryWrapper_pa);
			list.add(report_pa);
			list.add(target_pa);
			list.add(targetType_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(report_pa);
		return list;
	}



	net.datenwerke.rs.birt.client.reportengines.dto.BirtReportDto wl_0;
	net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceTargetTypeDto wl_1;

}
