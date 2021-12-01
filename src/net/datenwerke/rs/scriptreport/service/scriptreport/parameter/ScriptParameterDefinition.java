package net.datenwerke.rs.scriptreport.service.scriptreport.parameter;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.scriptreport.client.scriptreport.locale.ScriptReportMessages;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

/**
 * 
 *
 */
@Entity
@Table(name="SCRIPT_PARAM_DEF")
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto",
	displayTitle="ScriptReportMessages.INSTANCE.scriptParameterText()",
	additionalImports=ScriptReportMessages.class
)
public class ScriptParameterDefinition extends ParameterDefinition<ScriptParameterInstance> {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5286168287308916541L;
	
	@ExposeToClient
	@ManyToOne
	private FileServerFile script;
	
	@ExposeToClient
	private String arguments;
	
	@ExposeToClient(allowArbitraryLobSize=true,
			disableHtmlEncode=true)
	@Lob
	@Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
	private String defaultValue;
	
	@ExposeToClient
	private Integer width = 640;
	
	@ExposeToClient
	private Integer height = 480;

	@Override
	protected ScriptParameterInstance doCreateParameterInstance() {
		return new ScriptParameterInstance();
	}
	
	public String getDefaultValue() {
		return defaultValue;
	}
	
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	public FileServerFile getScript() {
		return script;
	}
	
	public void setScript(FileServerFile script) {
		this.script = script;
	}

	public String getArguments() {
		return arguments;
	}
	
	public void setArguments(String arguments) {
		this.arguments = arguments;
	}
	
	public Integer getWidth() {
		return width;
	}
	
	public void setWidth(Integer width) {
		this.width = width;
	}
	
	public Integer getHeight() {
		return height;
	}
	
	public void setHeight(Integer height) {
		this.height = height;
	}
}
