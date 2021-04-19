package net.datenwerke.rs.scheduleasfile.client.scheduleasfile.filter.dto;

import com.google.gwt.core.client.GWT;
import java.lang.Long;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.filter.dto.pa.TeamSpaceReportJobFilterDtoPA;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.filter.dto.posomap.TeamSpaceReportJobFilterDto2PosoMap;
import net.datenwerke.rs.scheduleasfile.service.scheduleasfile.filter.TeamSpaceReportJobFilter;
import net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterCriteriaDto;

/**
 * Dto for {@link TeamSpaceReportJobFilter}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class TeamSpaceReportJobFilterDto extends RsDto implements JobFilterCriteriaDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private Long teamspaceId;
	private  boolean teamspaceId_m;
	public static final String PROPERTY_TEAMSPACE_ID = "dpi-teamspacereportjobfilter-teamspaceid";

	private transient static PropertyAccessor<TeamSpaceReportJobFilterDto, Long> teamspaceId_pa = new PropertyAccessor<TeamSpaceReportJobFilterDto, Long>() {
		@Override
		public void setValue(TeamSpaceReportJobFilterDto container, Long object) {
			container.setTeamspaceId(object);
		}

		@Override
		public Long getValue(TeamSpaceReportJobFilterDto container) {
			return container.getTeamspaceId();
		}

		@Override
		public Class<?> getType() {
			return Long.class;
		}

		@Override
		public String getPath() {
			return "teamspaceId";
		}

		@Override
		public void setModified(TeamSpaceReportJobFilterDto container, boolean modified) {
			container.teamspaceId_m = modified;
		}

		@Override
		public boolean isModified(TeamSpaceReportJobFilterDto container) {
			return container.isTeamspaceIdModified();
		}
	};


	public TeamSpaceReportJobFilterDto() {
		super();
	}

	public Long getTeamspaceId()  {
		if(! isDtoProxy()){
			return this.teamspaceId;
		}

		if(isTeamspaceIdModified())
			return this.teamspaceId;

		if(! GWT.isClient())
			return null;

		Long _value = dtoManager.getProperty(this, instantiatePropertyAccess().teamspaceId());

		return _value;
	}


	public void setTeamspaceId(Long teamspaceId)  {
		/* old value */
		Long oldValue = null;
		if(GWT.isClient())
			oldValue = getTeamspaceId();

		/* set new value */
		this.teamspaceId = teamspaceId;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(teamspaceId_pa, oldValue, teamspaceId, this.teamspaceId_m));

		/* set indicator */
		this.teamspaceId_m = true;

		this.fireObjectChangedEvent(TeamSpaceReportJobFilterDtoPA.INSTANCE.teamspaceId(), oldValue);
	}


	public boolean isTeamspaceIdModified()  {
		return teamspaceId_m;
	}


	public static PropertyAccessor<TeamSpaceReportJobFilterDto, Long> getTeamspaceIdPropertyAccessor()  {
		return teamspaceId_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new TeamSpaceReportJobFilterDto2PosoMap();
	}

	public TeamSpaceReportJobFilterDtoPA instantiatePropertyAccess()  {
		return GWT.create(TeamSpaceReportJobFilterDtoPA.class);
	}

	public void clearModified()  {
		this.teamspaceId = null;
		this.teamspaceId_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(teamspaceId_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(teamspaceId_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(teamspaceId_m)
			list.add(teamspaceId_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(teamspaceId_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
