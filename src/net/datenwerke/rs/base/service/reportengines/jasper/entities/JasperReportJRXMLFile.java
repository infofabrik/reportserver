package net.datenwerke.rs.base.service.reportengines.jasper.entities;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Version;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;

import org.apache.commons.io.FileUtils;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

/**
 * Stores JRXML Files
 * 
 *
 */
@Entity
@Table(name="JASPER_REPORT_JRXML")
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.rs.base.client.reportengines.jasper.dto"
)
public class JasperReportJRXMLFile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4955608283709254297L;

	@ExposeToClient(view=DtoView.MINIMAL)
	@Column(length = 128)
	private String name;

	@Basic(fetch=FetchType.LAZY)
	@Lob
	@Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
	private String content;
	
	@Version
	private Long version;
	
	@ExposeToClient(id=true)
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
    public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public static JasperReportJRXMLFile fromInputFile(File file) throws IOException{
		String content = FileUtils.readFileToString(file);
		JasperReportJRXMLFile jrxml = new JasperReportJRXMLFile();
		jrxml.setContent(content);
		
		return jrxml;
	}
	
	public static JasperReportJRXMLFile fromInputStream(InputStream is) throws IOException{
		InputStreamReader isr = new InputStreamReader(is);
	    BufferedReader br = new BufferedReader(isr);
	    String line;
	    String content = ""; //$NON-NLS-1$
	    while ((line = br.readLine()) != null)
	    	content += line + "\n"; //$NON-NLS-1$

	    JasperReportJRXMLFile jrxml = new JasperReportJRXMLFile();
		jrxml.setContent(content);
		
		return jrxml;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
