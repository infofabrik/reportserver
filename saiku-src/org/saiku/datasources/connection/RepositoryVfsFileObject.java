package org.saiku.datasources.connection;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.vfs2.FileContent;
import org.apache.commons.vfs2.FileName;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSelector;
import org.apache.commons.vfs2.FileSystem;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileType;
import org.apache.commons.vfs2.NameScope;
import org.apache.commons.vfs2.operations.FileOperations;
import org.saiku.service.datasource.IDatasourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RepositoryVfsFileObject
        implements FileObject
{
    private static final Logger log = LoggerFactory.getLogger(RepositoryVfsFileObject.class);
    private String fileRef;
    private boolean fileInitialized;
    private RepositoryFile repositoryFile;
    private IDatasourceManager repo;
    private RepositoryVfsFileContent content;
    private String fileUrl;

    private RepositoryVfsFileObject(){

    }

    public RepositoryVfsFileObject(String fileRef, IDatasourceManager repo)
    {
        this.repo = repo;
        this.fileRef = fileRef;
    }

    private void initFile()
    {
        if (!this.fileInitialized)
        {
            this.fileUrl = this.fileRef.replace("mondrian://", "");
            try
            {
                this.fileUrl = URLDecoder.decode(this.fileUrl, Charset.defaultCharset().name());
            }
            catch (UnsupportedEncodingException e)
            {
                this.fileUrl = this.fileRef;
            }
            this.repositoryFile = this.repo.getFile(this.fileUrl);

            this.fileInitialized = true;
        }
    }

    public FileName getName()
    {
        initFile();
        FileType fileType;
        try
        {
            fileType = getType();
        }
        catch (Exception ex)
        {
            fileType = FileType.FOLDER;
        }
        return new RepositoryFileName(this.fileRef, fileType);
    }

    public URL getURL()
            throws FileSystemException
    {
        return null;
    }

    public boolean exists()
            throws FileSystemException
    {
        initFile();
        return this.repositoryFile != null;
    }

    public boolean isHidden()
            throws FileSystemException
    {
        return false;
    }

    public boolean isReadable()
            throws FileSystemException
    {
        return exists();
    }

    public boolean isWriteable()
            throws FileSystemException
    {
        return false;
    }

    public FileType getType()
            throws FileSystemException
    {
        return (this.repositoryFile != null) && (!this.repositoryFile.isFolder()) ? FileType.FILE : FileType.FOLDER;
    }

    public FileObject getParent()
            throws FileSystemException
    {
        return null;
    }

    public FileSystem getFileSystem()
    {
        return null;
    }

    public FileObject[] getChildren()
            throws FileSystemException
    {
        return null;
    }

    public FileObject getChild(String s)
            throws FileSystemException
    {
        return null;
    }

    public FileObject resolveFile(String s, NameScope nameScope)
            throws FileSystemException
    {
        return null;
    }

    public FileObject resolveFile(String s)
            throws FileSystemException
    {
        return null;
    }

    public FileObject[] findFiles(FileSelector fileSelector)
            throws FileSystemException
    {
        return new FileObject[0];
    }

    public void findFiles(FileSelector fileSelector, boolean b, List list)
            throws FileSystemException
    {}

    public boolean delete()
            throws FileSystemException
    {
        return false;
    }

    public int delete(FileSelector fileSelector)
            throws FileSystemException
    {
        return 0;
    }

    public void createFolder()
            throws FileSystemException
    {}

    public void createFile()
            throws FileSystemException
    {}

    public void copyFrom(FileObject fileObject, FileSelector fileSelector)
            throws FileSystemException
    {}

    public void moveTo(FileObject fileObject)
            throws FileSystemException
    {}

    public boolean canRenameTo(FileObject fileObject)
    {
        return false;
    }

    public FileContent getContent()
            throws FileSystemException
    {
        this.content = new RepositoryVfsFileContent(this);
        return this.content;
    }

    public void close()
            throws FileSystemException
    {
        if (this.content != null)
        {
            this.content.close();
            this.content = null;
        }
    }

    public void refresh()
            throws FileSystemException
    {}

    public boolean isAttached()
    {
        return false;
    }

    public boolean isContentOpen()
    {
        return (this.content != null) && (this.content.isOpen());
    }

//    public FileOperations getFileOperations()
//            throws FileSystemException
//    {
//        return null;
//    }

    public InputStream getInputStream()
            throws FileSystemException
    {
//        InputStream inputStream = null;
//        if (exists()) {
//            try {
//                inputStream = new ByteArrayInputStream(this.repo.getInternalFileData(this.fileUrl).getBytes(StandardCharsets.UTF_8));
//            } catch (RepositoryException e) {
//                log.error("Could not create input stream", e);
//            }
//        }
//        return inputStream;
    	 throw new RuntimeException("not implemented");
    }

	@Override
	public int compareTo(FileObject o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<FileObject> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteAll() throws FileSystemException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public FileOperations getFileOperations() throws FileSystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPublicURIString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isExecutable() throws FileSystemException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFile() throws FileSystemException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFolder() throws FileSystemException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setExecutable(boolean arg0, boolean arg1) throws FileSystemException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setReadable(boolean arg0, boolean arg1) throws FileSystemException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setWritable(boolean arg0, boolean arg1) throws FileSystemException {
		// TODO Auto-generated method stub
		return false;
	}
}