package net.datenwerke.rs.tabletemplate.client.tabletemplate;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.XTemplates.FormatterFactories;
import com.sencha.gxt.core.client.XTemplates.FormatterFactory;
import com.sencha.gxt.core.client.XTemplates.FormatterFactoryMethod;

import net.datenwerke.gxtdto.client.xtemplates.NullSafeFormatter;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportTemplateDto;

public class TableTemplateUIServiceImpl implements TableTemplateUIService {

	@FormatterFactories(@FormatterFactory(factory=NullSafeFormatter.class,methods=@FormatterFactoryMethod(name="nullsafe")))
	public interface TableTemplateTemplates extends XTemplates {
		@XTemplate("<div class=\"rt-ticket-grid-desc\">" +
		    	"<div class=\"title\">{name:nullsafe}</div>" +
		    	"<div class=\"description\">{description:nullsafe}</div>" +
		    "</div>")
	    public SafeHtml descriptionTemplate(TableReportTemplateDto dto); 
	}

	@Override
	public TableTemplateTemplates getDescriptionTemplate() {
		return GWT.create(TableTemplateTemplates.class);
	}
	
}
