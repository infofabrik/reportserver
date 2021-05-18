package net.datenwerke.rs.utils.zip;

import static net.datenwerke.rs.utils.exception.shared.LambdaExceptionUtil.rethrowConsumer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;

import net.datenwerke.rs.core.service.mail.SimpleAttachment;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;

public class ZipUtilsServiceImpl implements ZipUtilsService{

	final int BUFFER = 2048;
	
	@Override
	public void createZip(byte[] content, OutputStream os) throws IOException {
		ZipOutputStream out = new ZipOutputStream(os); 
		
		out.putNextEntry(new ZipEntry("data"));
		
		ByteArrayInputStream in = new ByteArrayInputStream(content);
		if(null != in)
			IOUtils.copy(in, out);
		
		out.closeEntry(); 
		
		out.close();
	}
	
	@Override
	public void createZip(Map<String, ? extends Object> content, OutputStream os) throws IOException {
		ZipOutputStream out = new ZipOutputStream(os); 
		for (String name : content.keySet()) {
			if(DIRECTORY_MARKER == (content.get(name))){
				out.putNextEntry(new ZipEntry(name.endsWith("/") ? name : name + "/"));
			}else{
				out.putNextEntry(new ZipEntry(name));
			}
			
			ByteArrayInputStream in = null;
			if(content.get(name) instanceof String){
				String text = (String) content.get(name);
				in = new ByteArrayInputStream(text.getBytes());
			}else if(content.get(name) instanceof byte[]){
				in = new ByteArrayInputStream((byte[]) content.get(name));
			}
			
			if(null != in){
				IOUtils.copy(in, out);
			}
			
			out.closeEntry(); 
		}
		
		out.close();
	}
	
	@Override
	public void createZip(List<Path> files, OutputStream os) throws IOException {
		if (! files.stream().allMatch(f -> Files.exists(f) && ! Files.isDirectory(f)) )
			throw new IOException("Given file does not exist or is a directory");
		
		try (BufferedOutputStream bos = new BufferedOutputStream(os); 
				ZipOutputStream zos = new ZipOutputStream(bos)) {
			files.stream().forEach(rethrowConsumer(f -> {
				String name = f.getFileName().toString();
				zos.putNextEntry(new ZipEntry(name));
				Files.copy(f, zos);
				zos.closeEntry();
			}));

		}
	}
	
    @Override
    public void createZipFromEmailAttachments(final List<SimpleAttachment> attachments, OutputStream os) throws IOException {
       try (BufferedOutputStream bos = new BufferedOutputStream(os); 
             ZipOutputStream zos = new ZipOutputStream(bos)) {
          attachments
             .forEach(rethrowConsumer(attachment -> {
                String name = attachment.getFileName();
                zos.putNextEntry(new ZipEntry(name));
                Object attachmentData = attachment.getAttachment();
                if (attachmentData instanceof Path) {
                   Path attachmentPath = (Path)attachmentData;
                   if (!Files.exists(attachmentPath) || Files.isDirectory(attachmentPath))
                      throw new IllegalArgumentException(
                            "Attachment not found or directory: " + attachmentPath);
                   Files.copy((Path)attachmentData, zos);
                } else if (attachmentData instanceof byte[]) 
                   zos.write((byte[])attachmentData);
                else if (attachmentData instanceof String) 
                   zos.write(((String)attachmentData).getBytes(StandardCharsets.UTF_8));
                else
                   throw new IllegalArgumentException(
                         "Attachment type not supported: " + attachment.getClass().getCanonicalName());
                zos.closeEntry();
             }));
       }
    }
	
	public void zipDirectory(File dir, OutputStream os) throws IOException{
		ZipOutputStream out = new ZipOutputStream(os); 
		
		try{
			for(File f : getFilesRec(dir)){
				
				if(f.isDirectory()){
					String name = f.getPath().replace(dir.getPath(), "").replace("\\", "/").substring(1);
					name = name.endsWith("/") ? name : name + "/";
					out.putNextEntry(new ZipEntry(name));
					continue;
				}
				
				out.putNextEntry(new ZipEntry(f.getPath().replace(dir.getPath(), "").replace("\\", "/").substring(1)));
				
				try (InputStream in = new FileInputStream(f)) {
					byte[] buff = new byte[1024];
					int read = 0;
					while((read = in.read(buff)) > 0){
						out.write(buff, 0, read);
					}
				}
			}
		} finally {
			out.finish();
			out.close();	
		}
	}
	
	private List<File> getFilesRec(File path){
		List<File> files = new ArrayList<File>();
		for(File f : path.listFiles()) {
			if(f.isDirectory())	{
				files.add(f);
				files.addAll(getFilesRec(f));
			}else{
				files.add(f);
			}
		}
		return files;
	}

	@Override
	public void extractZip(byte[] data, ZipExtractionConfig config) throws IOException{
		extractZip(new ByteArrayInputStream(data), config);
	}
	
	@Override
	public void extractZip(InputStream is, ZipExtractionConfig config) throws IOException{
		ZipInputStream zin = new ZipInputStream(new BufferedInputStream(is));
		ZipEntry entry;
		while((entry = zin.getNextEntry()) != null) {
			if(! config.isAllowedFile(entry))
				continue;
			
			int count;
            byte data[] = new byte[BUFFER];

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            
            while ((count = zin.read(data, 0, BUFFER)) != -1) {
            	out.write(data, 0, count);
            }
            
            config.processContent(entry, out.toByteArray());
		}
	}
	
	@Override
	public void createZip(FileServerFolder folder, OutputStream os) throws IOException {
		createZip(folder, os, new FileFilter() {
			@Override
			public boolean addNode(AbstractFileServerNode node) {
				return false;
			}
		});
	}
	
	@Override
	public void createZip(FileServerFolder folder, OutputStream os, FileFilter filter) throws IOException {
		HashMap<String, Object> zipObjects = new HashMap<String, Object>(); 
		addFolder(folder, folder, zipObjects, filter);
		createZip(zipObjects, os);
	}
	
	private void addFile(FileServerFile file, FileServerFolder baseFolder, Map<String, Object> zipObjects, FileFilter filter){
		if(filter.addNode(file))
			zipObjects.put(getRelativePath(file, baseFolder), file.getData());
	}

	private void addFolder(FileServerFolder folder, FileServerFolder baseFolder, Map<String, Object> zipObjects, FileFilter filter){
		if(! filter.addNode(folder))
			return;
		
		zipObjects.put(getRelativePath(folder, baseFolder), ZipUtilsService.DIRECTORY_MARKER);
		for(AbstractFileServerNode f : folder.getChildren()){
			if(f instanceof FileServerFolder){
				addFolder((FileServerFolder) f, baseFolder, zipObjects, filter);
			}else if(f instanceof FileServerFile){
				addFile((FileServerFile) f, baseFolder, zipObjects, filter);
			}
		}

	}

	private String getRelativePath(AbstractFileServerNode node, AbstractFileServerNode root){
		if(node == root){
			return "";
		}else{
			String name = "";
			if(node instanceof FileServerFile)
				name = ((FileServerFile) node).getName();
			if(node instanceof FileServerFolder)
				name = ((FileServerFolder) node).getName();

			if(null != node.getParent()){
				String rp = getRelativePath(node.getParent(), root);
				return ("".equals(rp)?name: rp + "/" + name);
			}else{
				return name;

			}
		}
	}

}
