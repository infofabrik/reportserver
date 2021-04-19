package net.datenwerke.rs.base.ext.service.parameters.fileselection;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Entity;
import javax.persistence.Table;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeMethodToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.base.client.parameters.locale.RsMessages;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;

import org.hibernate.envers.Audited;

@Entity
@Table(name="FILESEL_PARAM_DEF")
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.rs.base.ext.client.parameters.fileselection.dto",
	displayTitle="RsMessages.INSTANCE.fileSelectionParameterText()",
	additionalImports=RsMessages.class	
)
public class FileSelectionParameterDefinition extends ParameterDefinition<FileSelectionParameterInstance> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3807675984310979069L;
	
	private static final Pattern FileSizePattern = Pattern.compile("([\\d.]+)\\s*([GMK])B?", Pattern.CASE_INSENSITIVE);
	
	@ExposeToClient
	private boolean allowFileUpload;
	
	@ExposeToClient
	private boolean allowTeamSpaceSelection;
	
	@ExposeToClient
	private boolean allowFileServerSelection;

	@ExposeToClient
	private String fileSizeString;
	
	@ExposeToClient
	private String allowedFileExtensions;
	
	@ExposeToClient
	private Integer minNumberOfFiles = 0;
	
	@ExposeToClient
	private Integer maxNumberOfFiles = 0;
	
	@ExposeToClient
	private boolean allowDownload = true;
	
	@ExposeToClient
	private int width = 520;
	
	@ExposeToClient
	private int height = 150;
	
	
	
	@Override
	protected FileSelectionParameterInstance doCreateParameterInstance() {
		return new FileSelectionParameterInstance();
	}

	@ExposeMethodToClient()
	public long getFileSize() {
		if(null == fileSizeString || fileSizeString.trim().isEmpty())
			return -1;
		
	    Matcher matcher = FileSizePattern.matcher(fileSizeString.trim());
	   
	    Map<String, Integer> powerMap = new HashMap<String, Integer>();
	    powerMap.put("GB", 3);
	    powerMap.put("MB", 2);
	    powerMap.put("KB", 1);
	    powerMap.put("G", 3);
	    powerMap.put("M", 2);
	    powerMap.put("K", 1);
	    
	    if (! matcher.find())
	    	return Long.valueOf(fileSizeString);
	    else {
	    	String number = matcher.group(1);
	    	int pow = powerMap.get(matcher.group(2).toUpperCase());
	    	BigDecimal bytes = new BigDecimal(number);
	    	bytes = bytes.multiply(BigDecimal.valueOf(1024).pow(pow));
	    	return bytes.longValue();
	    }
	}

	public boolean isAllowFileUpload() {
		return allowFileUpload;
	}

	public void setAllowFileUpload(boolean allowFileUpload) {
		this.allowFileUpload = allowFileUpload;
	}

	public boolean isAllowTeamSpaceSelection() {
		return allowTeamSpaceSelection;
	}

	public void setAllowTeamSpaceSelection(boolean allowTeamSpaceSelection) {
		this.allowTeamSpaceSelection = allowTeamSpaceSelection;
	}

	public boolean isAllowFileServerSelection() {
		return allowFileServerSelection;
	}

	public void setAllowFileServerSelection(boolean allowFileServerSelection) {
		this.allowFileServerSelection = allowFileServerSelection;
	}

	public String getFileSizeString() {
		return fileSizeString;
	}

	public void setFileSizeString(String fileSizeString) {
		this.fileSizeString = fileSizeString;
	}

	public String getAllowedFileExtensions() {
		return allowedFileExtensions;
	}

	public void setAllowedFileExtensions(String allowedFileExtensions) {
		this.allowedFileExtensions = allowedFileExtensions;
	}

	public Integer getMinNumberOfFiles() {
		if(null == minNumberOfFiles || minNumberOfFiles <= 0)
			return null;
		
		return minNumberOfFiles;
	}

	public void setMinNumberOfFiles(Integer minNumberOfFiles) {
		this.minNumberOfFiles = minNumberOfFiles;
	}

	public Integer getMaxNumberOfFiles() {
		if(null == maxNumberOfFiles || maxNumberOfFiles <= 0)
			return null;
		
		return maxNumberOfFiles;
	}

	public void setMaxNumberOfFiles(Integer maxNumberOfFiles) {
		this.maxNumberOfFiles = maxNumberOfFiles;
	}
	
	public boolean isAllowDownload() {
		return allowDownload;
	}

	public void setAllowDownload(boolean allowDownload) {
		this.allowDownload = allowDownload;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	
	
}
