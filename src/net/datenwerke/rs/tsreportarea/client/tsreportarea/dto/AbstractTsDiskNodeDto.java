package net.datenwerke.rs.tsreportarea.client.tsreportarea.dto;

import com.google.gwt.core.client.GWT;
import java.lang.Long;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.pa.AbstractTsDiskNodeDtoPA;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.posomap.AbstractTsDiskNodeDto2PosoMap;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.AbstractTsDiskNode;
import net.datenwerke.treedb.client.treedb.dto.decorator.AbstractNodeDtoDec;

/**
 * Dto for {@link AbstractTsDiskNode}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class AbstractTsDiskNodeDto extends AbstractNodeDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private Long teamSpaceId;
	private  boolean teamSpaceId_m;
	public static final String PROPERTY_TEAM_SPACE_ID = "dpi-abstracttsdisknode-teamspaceid";

	private transient static PropertyAccessor<AbstractTsDiskNodeDto, Long> teamSpaceId_pa = new PropertyAccessor<AbstractTsDiskNodeDto, Long>() {
		@Override
		public void setValue(AbstractTsDiskNodeDto container, Long object) {
			container.setTeamSpaceId(object);
		}

		@Override
		public Long getValue(AbstractTsDiskNodeDto container) {
			return container.getTeamSpaceId();
		}

		@Override
		public Class<?> getType() {
			return Long.class;
		}

		@Override
		public String getPath() {
			return "teamSpaceId";
		}

		@Override
		public void setModified(AbstractTsDiskNodeDto container, boolean modified) {
			container.teamSpaceId_m = modified;
		}

		@Override
		public boolean isModified(AbstractTsDiskNodeDto container) {
			return container.isTeamSpaceIdModified();
		}
	};


	public AbstractTsDiskNodeDto() {
		super();
	}

	public Long getTeamSpaceId()  {
		if(! isDtoProxy()){
			return this.teamSpaceId;
		}

		if(isTeamSpaceIdModified())
			return this.teamSpaceId;

		if(! GWT.isClient())
			return null;

		Long _value = dtoManager.getProperty(this, instantiatePropertyAccess().teamSpaceId());

		return _value;
	}


	public void setTeamSpaceId(Long teamSpaceId)  {
		/* old value */
		Long oldValue = null;
		if(GWT.isClient())
			oldValue = getTeamSpaceId();

		/* set new value */
		this.teamSpaceId = teamSpaceId;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(teamSpaceId_pa, oldValue, teamSpaceId, this.teamSpaceId_m));

		/* set indicator */
		this.teamSpaceId_m = true;

		this.fireObjectChangedEvent(AbstractTsDiskNodeDtoPA.INSTANCE.teamSpaceId(), oldValue);
	}


	public boolean isTeamSpaceIdModified()  {
		return teamSpaceId_m;
	}


	public static PropertyAccessor<AbstractTsDiskNodeDto, Long> getTeamSpaceIdPropertyAccessor()  {
		return teamSpaceId_pa;
	}


	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof AbstractTsDiskNodeDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((AbstractTsDiskNodeDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new AbstractTsDiskNodeDto2PosoMap();
	}

	public AbstractTsDiskNodeDtoPA instantiatePropertyAccess()  {
		return GWT.create(AbstractTsDiskNodeDtoPA.class);
	}

	public void clearModified()  {
		this.teamSpaceId = null;
		this.teamSpaceId_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(teamSpaceId_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(teamSpaceId_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(teamSpaceId_m)
			list.add(teamSpaceId_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(teamSpaceId_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}



	net.datenwerke.gf.base.client.dtogenerator.RsDto wl_0;

}
