package net.datenwerke.rs.core.client.reportmanager.dto.reports;

import com.google.gwt.core.client.GWT;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.pa.AbstractReportManagerNodeDtoPA;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.posomap.AbstractReportManagerNodeDto2PosoMap;
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode;
import net.datenwerke.security.client.treedb.dto.decorator.SecuredAbstractNodeDtoDec;

/**
 * Dto for {@link AbstractReportManagerNode}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class AbstractReportManagerNodeDto extends SecuredAbstractNodeDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */

	public AbstractReportManagerNodeDto() {
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
		if(! (obj instanceof AbstractReportManagerNodeDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((AbstractReportManagerNodeDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new AbstractReportManagerNodeDto2PosoMap();
	}

	public AbstractReportManagerNodeDtoPA instantiatePropertyAccess()  {
		return GWT.create(AbstractReportManagerNodeDtoPA.class);
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



	net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto wl_0;
	net.datenwerke.rs.core.client.datasourcemanager.dto.AbstractDatasourceManagerNodeDto wl_1;
	net.datenwerke.rs.core.client.reportmanager.dto.ReportFolderDto wl_2;
	net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportVariantDto wl_3;

}
