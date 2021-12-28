package net.datenwerke.rs.base.service.reportengines.table.entities.format;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.utils.juel.SimpleJuel;

@Entity
@Table(name="COLUMN_FORMAT_TEMPLATE")
@Inheritance(strategy=InheritanceType.JOINED)
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.rs.base.client.reportengines.table.dto",
	createDecorator=true
)
public class ColumnFormatTemplate extends ColumnFormat{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7426511593850193265L;

	@Transient @Inject
	protected static Provider<SimpleJuel> simpleJuelProvider;
	
	@ExposeToClient
	private String template;
	
	public void setTemplate(String template) {
		this.template = template;
	}

	public String getTemplate() {
		return template;
	}
	
	@Override
	public String format(Object value) {
		SimpleJuel juel = simpleJuelProvider.get();
		
		juel.addReplacement("isNull", value == null);
		juel.addReplacement("value", value);
		
		return juel.parse(template);
	}

}
