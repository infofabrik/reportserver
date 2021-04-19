package net.datenwerke.rs.utils.filename;

import com.google.inject.ImplementedBy;

@ImplementedBy(FileNameServiceImpl.class)
public interface FileNameService {

	public String sanitizeFileName(String name);
	
	public String sanitizeFileNameStrict(String name);
}
