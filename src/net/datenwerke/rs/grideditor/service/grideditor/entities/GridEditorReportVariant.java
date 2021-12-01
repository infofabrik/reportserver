package net.datenwerke.rs.grideditor.service.grideditor.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.dtoservices.dtogenerator.annotations.IgnoreMergeBackDto;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.utils.entitycloner.annotation.ClonePostProcessor;

import org.apache.commons.lang3.NotImplementedException;
import org.hibernate.envers.Audited;
import org.hibernate.proxy.HibernateProxy;
import net.datenwerke.gf.base.service.annotations.Indexed;

@Entity
@Table(name="GRID_EDT_REPORT_VARIANT")
@Audited
@Indexed
@GenerateDto(
	dtoPackage="net.datenwerke.rs.grideditor.client.grideditor.dto", 
	createDecorator=true
)
public class GridEditorReportVariant extends GridEditorReport implements ReportVariant {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2918985738470489990L;

	public GridEditorReport getBaseReport() {
		AbstractReportManagerNode parent = getParent();
		if(parent instanceof HibernateProxy)
			parent = (AbstractReportManagerNode) ((HibernateProxy)parent).getHibernateLazyInitializer().getImplementation();
		return (GridEditorReport) parent;
	}

	public void setBaseReport(Report baseReport) {
		throw new IllegalStateException("should not be called on server");
	}

	@IgnoreMergeBackDto
	@Override
	public void setDatasourceContainer(DatasourceContainer datasource){
		throw new NotImplementedException("not implemented");
	}
	
	@Override
	public List<ParameterDefinition> getParameterDefinitions() {
		return getBaseReport().getParameterDefinitions();
	}
	
	@IgnoreMergeBackDto
	@Override
	public void setParameterDefinitions( List<ParameterDefinition> parameters) {
		throw new NotImplementedException("not implemented");
	}
	

	@Override
	public DatasourceContainer getDatasourceContainer() {
		return getBaseReport().getDatasourceContainer();
	}
	
	@Override
	public void setScript(FileServerFile script) {
		throw new NotImplementedException("not implemented");
	}
	
	@Override
	public void setArguments(String arguments) {
		throw new NotImplementedException("not implemented");
	}
	
	@Override
	public FileServerFile getScript() {
		return getBaseReport().getScript();
	}
	
	@Override
	public String getArguments() {
		return getBaseReport().getArguments();
	}
	
	
	/**
	 */
	@ClonePostProcessor
	public void guideCloningProcess(Object report){
		super.setParameterDefinitions(null);
		super.setDatasourceContainer(null);
	}
	
	@Override
	public boolean hasChildren() {
		return false;
	}
}
