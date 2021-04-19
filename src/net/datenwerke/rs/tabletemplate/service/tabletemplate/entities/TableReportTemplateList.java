package net.datenwerke.rs.tabletemplate.service.tabletemplate.entities;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportProperty;
import net.datenwerke.rs.tabletemplate.service.tabletemplate.TableTemplateConstants;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;

@Entity
@Table(name="TABLE_REPORT_TEMPLATE_LST")
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.rs.tabletemplate.client.tabletemplate.dto"
)
public class TableReportTemplateList extends ReportProperty implements Iterable<TableReportTemplate> {

	@JoinTable(name="TAB_REP_TPL_LST_2_TPL")
	@EnclosedEntity
	@OneToMany(cascade=CascadeType.ALL)
	private Set<TableReportTemplate> templates = new HashSet<TableReportTemplate>();

	public TableReportTemplateList(){
		super(TableTemplateConstants.TEMPLATE_LIST_PROPERTY_NAME);
		
	}
	
	@Override
	public void setName(String name) {
		throw new UnsupportedOperationException("Cannot change name of this property");
	}
	
	public void setTemplates(Set<TableReportTemplate> templates) {
		this.templates = templates;
	}

	public Set<TableReportTemplate> getTemplates() {
		return templates;
	}

	@Override
	public Iterator<TableReportTemplate> iterator() {
		return templates.iterator();
	}

	public void add(TableReportTemplate template) {
		templates.add(template);
	}
	
	
}
