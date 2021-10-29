package net.datenwerke.rs.jxlsreport.service.jxlsreport.entities;

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
import net.datenwerke.rs.utils.entitycloner.annotation.ClonePostProcessor;

import org.apache.commons.lang3.NotImplementedException;
import org.hibernate.envers.Audited;
import org.hibernate.proxy.HibernateProxy;
import net.datenwerke.gf.base.service.annotations.Indexed;

@Entity
@Table(name="JXLS_REPORT_VARIANT")
@Audited
@Indexed
@GenerateDto(
	dtoPackage="net.datenwerke.rs.jxlsreport.client.jxlsreport.dto", 
	createDecorator=true
)
public class JxlsReportVariant extends JxlsReport implements ReportVariant {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5745075422591319002L;

	@Override
	public JxlsReportFile getReportFile() {
		return getBaseReport().getReportFile();
	}
	
	@IgnoreMergeBackDto
	@Override
	public void setReportFile(JxlsReportFile reportFile) {
		throw new NotImplementedException("not implemented");
	}
	
	public JxlsReport getBaseReport() {
		AbstractReportManagerNode parent = getParent();
		if(parent instanceof HibernateProxy)
			parent = (AbstractReportManagerNode) ((HibernateProxy)parent).getHibernateLazyInitializer().getImplementation();
		return (JxlsReport) parent;
	}

	public void setBaseReport(Report baseReport) {
		throw new IllegalStateException("should not be called on server");
	}

	@Override
	public DatasourceContainer getDatasourceContainer() {
		return getBaseReport().getDatasourceContainer();
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
