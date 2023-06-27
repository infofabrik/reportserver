package org.saiku.datasources.connection;

import org.apache.commons.vfs2.FileName;
import org.apache.commons.vfs2.FileType;
import org.apache.commons.vfs2.provider.AbstractFileName;

public class RepositoryFileName
        extends AbstractFileName
{
    public RepositoryFileName(String fileRef, FileType fileType)
    {
        super("repo", fileRef, fileType);
    }

    public FileName createName(String s, FileType fileType)
    {
        return new RepositoryFileName(s, fileType);
    }

    protected void appendRootUri(StringBuffer stringBuffer, boolean b) {}

	@Override
	protected void appendRootUri(StringBuilder arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
	}
}