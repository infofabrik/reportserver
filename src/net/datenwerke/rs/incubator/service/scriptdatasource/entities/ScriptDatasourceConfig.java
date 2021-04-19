package net.datenwerke.rs.incubator.service.scriptdatasource.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinitionConfig;

@Entity
@Table(name="SCRIPT_DATASOURCE_CONFIG")
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.rs.incubator.client.scriptdatasource.dto"
)
public class ScriptDatasourceConfig extends DatasourceDefinitionConfig {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4068895957698607312L;

	@ExposeToClient
	private String arguments = "";
	
	@ExposeToClient(allowArbitraryLobSize=true,disableHtmlEncode=true)
	@Lob
	@Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
	private String script = "";
	
	@ExposeToClient(disableHtmlEncode=true)
	@Column(length = 4096)
	private String queryWrapper = "";

	public String getQueryWrapper() {
		return queryWrapper;
	}

	public void setQueryWrapper(String queryWrapper) {
		this.queryWrapper = queryWrapper;
	}

	public void setArguments(String arguments) {
		this.arguments = arguments;
	}

	public String getArguments() {
		return arguments;
	} 
	
	public String getScript() {
		return script;
	}
	
	public void setScript(String script) {
		this.script = script;
	}
	
	@Override
	public boolean contentEquals(DatasourceDefinition definition, DatasourceDefinitionConfig config) {
		if(! (config instanceof ScriptDatasourceConfig))
			return false;
		if(! (definition instanceof ScriptDatasource))
			return false;
		
		ScriptDatasourceConfig otherConfig = (ScriptDatasourceConfig) config;
		
		if(null == arguments && null != otherConfig.getArguments())
			return false;
		
		if(null != arguments && ! arguments.equals(otherConfig.getArguments()))
			return false;
		
		if(null == queryWrapper && null != otherConfig.getQueryWrapper())
			return false;
		
		if(null != queryWrapper && ! queryWrapper.equals(otherConfig.getQueryWrapper()))
			return false;
		
		/* script if allowed */
		if(((ScriptDatasource)definition).isDefineAtTarget()){
			if(null == script && null != otherConfig.getScript())
				return false;
			
			if(null != script && ! script.equals(otherConfig.getScript()))
				return false;
		}
		
		return true;
	}
}
