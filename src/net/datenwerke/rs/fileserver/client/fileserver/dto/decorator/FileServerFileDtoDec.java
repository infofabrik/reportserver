package net.datenwerke.rs.fileserver.client.fileserver.dto.decorator;

import net.datenwerke.rs.fileserver.client.fileserver.FileServerUiModule;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Random;

/**
 * Dto Decorator for {@link FileServerFileDto}
 *
 */
public class FileServerFileDtoDec extends FileServerFileDto {

	private static final long serialVersionUID = 1L;

	public FileServerFileDtoDec() {
		super();
	}

	@Override
	public Integer getSize(){
		Integer size = super.getSize();
		if(null == size)
			return 0;
		return size;
	}
	
	public String getThumbnailUrl() {
		return GWT.getModuleBaseURL() + FileServerUiModule.FILE_ACCESS_SERVLET + "?id=" + getId() + "&thumbnail=true&nonce=" + Random.nextInt();
	}
}
