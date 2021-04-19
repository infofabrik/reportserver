package net.datenwerke.rs.core.client.datasourcemanager.dto;

import com.google.gwt.core.client.GWT;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.rs.core.client.datasourcemanager.dto.pa.AbstractDatasourceManagerNodeDtoPA;
import net.datenwerke.rs.core.client.datasourcemanager.dto.posomap.AbstractDatasourceManagerNodeDto2PosoMap;
import net.datenwerke.rs.core.service.datasourcemanager.entities.AbstractDatasourceManagerNode;
import net.datenwerke.security.client.treedb.dto.decorator.SecuredAbstractNodeDtoDec;

/**
 * Dto for {@link AbstractDatasourceManagerNode}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class AbstractDatasourceManagerNodeDto extends SecuredAbstractNodeDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */

	public AbstractDatasourceManagerNodeDto() {
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
		if(! (obj instanceof AbstractDatasourceManagerNodeDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((AbstractDatasourceManagerNodeDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new AbstractDatasourceManagerNodeDto2PosoMap();
	}

	public AbstractDatasourceManagerNodeDtoPA instantiatePropertyAccess()  {
		return GWT.create(AbstractDatasourceManagerNodeDtoPA.class);
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



	net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceFolderDto wl_0;
	net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto wl_1;
	net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto wl_2;
	net.datenwerke.rs.core.client.reportmanager.dto.reports.AbstractReportManagerNodeDto wl_3;

}
