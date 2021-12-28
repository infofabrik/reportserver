package net.datenwerke.rs.tabletemplate.service.tabletemplate.entities;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@Entity
@Table(name="TABLE_REPORT_BYTE_TPL")
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.rs.tabletemplate.client.tabletemplate.dto"
)
public class TableReportByteTemplate extends TableReportTemplate {
	
	@Lob
	private byte[] template;

	public void setTemplate(byte[] template) {
		this.template = template;
	}

	public byte[] getTemplate() {
		return template;
	}
	
	@Override
	public void updateData(TableReportTemplate template) {
		if(! (template instanceof TableReportByteTemplate))
			throw new IllegalArgumentException("Expected " + TableReportByteTemplate.class);
		
		super.updateData(template);
		
		setTemplate(((TableReportByteTemplate)template).getTemplate());
	}

	@Override
	protected TableReportTemplate doCreateTemporary() {
		TableReportByteTemplate template = new TableReportByteTemplate();
		template.setTemplate(getTemplate());
		return template;
	}
}
