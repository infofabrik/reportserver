package net.datenwerke.rs.core.client.datasinkmanager.dto;

import com.google.gwt.core.client.GWT;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.rs.core.client.datasinkmanager.dto.pa.AbstractDatasinkManagerNodeDtoPA;
import net.datenwerke.rs.core.client.datasinkmanager.dto.posomap.AbstractDatasinkManagerNodeDto2PosoMap;
import net.datenwerke.rs.core.service.datasinkmanager.entities.AbstractDatasinkManagerNode;
import net.datenwerke.security.client.treedb.dto.decorator.SecuredAbstractNodeDtoDec;

/**
 * Dto for {@link AbstractDatasinkManagerNode}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class AbstractDatasinkManagerNodeDto extends SecuredAbstractNodeDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */

	public AbstractDatasinkManagerNodeDto() {
		super();
	}

	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof AbstractDatasinkManagerNodeDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((AbstractDatasinkManagerNodeDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new AbstractDatasinkManagerNodeDto2PosoMap();
	}

	public AbstractDatasinkManagerNodeDtoPA instantiatePropertyAccess()  {
		return GWT.create(AbstractDatasinkManagerNodeDtoPA.class);
	}

	public void clearModified()  {
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}



	net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto wl_0;
	net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkFolderDto wl_1;
	net.datenwerke.rs.core.client.reportmanager.dto.reports.AbstractReportManagerNodeDto wl_2;

}
