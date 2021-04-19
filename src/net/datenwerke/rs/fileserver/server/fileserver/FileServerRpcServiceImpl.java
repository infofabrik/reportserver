package net.datenwerke.rs.fileserver.server.fileserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.persist.Transactional;

import net.datenwerke.eximport.ExportService;
import net.datenwerke.eximport.ex.ExportConfig;
import net.datenwerke.gf.client.upload.dto.FileToUpload;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.eximport.service.eximport.ex.http.HttpExportService;
import net.datenwerke.rs.eximport.service.genrights.ExportSecurityTarget;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto;
import net.datenwerke.rs.fileserver.client.fileserver.eximport.ex.rpc.FileServerExportRpcService;
import net.datenwerke.rs.fileserver.client.fileserver.rpc.FileServerRpcService;
import net.datenwerke.rs.fileserver.client.fileserver.rpc.FileServerTreeLoader;
import net.datenwerke.rs.fileserver.client.fileserver.rpc.FileServerTreeManager;
import net.datenwerke.rs.fileserver.service.fileserver.FileServerService;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;
import net.datenwerke.rs.fileserver.service.fileserver.terminal.commands.unzip.BasepathZipExtractConfigFactory;
import net.datenwerke.rs.utils.entitycloner.EntityClonerService;
import net.datenwerke.rs.utils.misc.MimeUtils;
import net.datenwerke.rs.utils.zip.ZipExtractionConfig;
import net.datenwerke.rs.utils.zip.ZipUtilsService;
import net.datenwerke.security.server.TreeDBManagerTreeHandler;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.annotation.ArgumentVerification;
import net.datenwerke.security.service.security.annotation.GenericTargetVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.Write;
import net.datenwerke.security.service.treedb.actions.InsertAction;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeExportItemConfig;

/**
 * 
 *
 */
@Singleton
public class FileServerRpcServiceImpl extends TreeDBManagerTreeHandler<AbstractFileServerNode> 
implements 
FileServerRpcService, 
FileServerTreeLoader, 
FileServerTreeManager,
FileServerExportRpcService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8869851090677352067L;

	private final FileServerService fileService;
	private final ExportService exportService;
	private final Provider<HttpExportService> httpExportServiceProvider;
	private final MimeUtils mimeUtils;
	private final BasepathZipExtractConfigFactory extractConfigFactory;
	private final Provider<ZipUtilsService> zipUtilsServiceProvider;

	@Inject
	public FileServerRpcServiceImpl(
			DtoService dtoService,
			FileServerService fileService,
			SecurityService securityService,
			EntityClonerService entityClonerService,
			ExportService exportService,
			Provider<HttpExportService> httpExportServiceProvider, 
			MimeUtils mimeUtils,
			BasepathZipExtractConfigFactory extractConfigFactory,
			Provider<ZipUtilsService> zipUtilsServiceProvider
			) {

		super(fileService, dtoService, securityService, entityClonerService);

		/* store objects */
		this.fileService = fileService;
		this.exportService = exportService;
		this.httpExportServiceProvider = httpExportServiceProvider;
		this.mimeUtils = mimeUtils;
		this.extractConfigFactory = extractConfigFactory;
		this.zipUtilsServiceProvider = zipUtilsServiceProvider;
	}

	@SecurityChecked(
			argumentVerification = {
					@ArgumentVerification(
							name = "node",
							isDto = true,
							verify = @RightsVerification(rights=Write.class)
							)
			}
			)
	@Override
	@Transactional(rollbackOn=Exception.class)
	public void updateFile(@Named("node") FileServerFileDto fileDto, String data)
			throws ServerCallFailedException {
		FileServerFile file = (FileServerFile) dtoService.loadPoso(fileDto);
		file.setData(data.getBytes());
		fileService.merge(file);
	}

	@Override
	protected boolean allowDuplicateNode(AbstractFileServerNode realNode) {
		return true;
	}

	@SecurityChecked(
			argumentVerification = {
					@ArgumentVerification(
							name = "node",
							isDto = true,
							verify = @RightsVerification(rights=Read.class)
							)
			}
			)
	@Override
	@Transactional(rollbackOn=Exception.class)
	public String loadFileDataAsString(@Named("node") FileServerFileDto fileDto) {
		FileServerFile file = (FileServerFile) dtoService.loadPoso(fileDto);
		if(null == file.getData())
			return "";
		return new String(file.getData());
	}

	@Override
	@SecurityChecked(
			genericTargetVerification = { 
					@GenericTargetVerification(
							target = ExportSecurityTarget.class, 
							verify = @RightsVerification(rights = Execute.class)) 
			})
	@Transactional(rollbackOn={Exception.class})
	public void quickExport(AbstractFileServerNodeDto nodeDto) throws ServerCallFailedException {
		AbstractFileServerNode node = (AbstractFileServerNode) dtoService.loadPoso(nodeDto);

		/* export report */
		ExportConfig exportConfig = new ExportConfig();
		exportConfig.setName("FileServer-Export");
		exportConfig.addItemConfig(new TreeNodeExportItemConfig(node));

		addChildren(exportConfig, node);

		String exportXML = exportService.exportIndent(exportConfig);

		httpExportServiceProvider.get().storeExport(exportXML, node.getName());
	}

	private void addChildren(ExportConfig exportConfig, AbstractFileServerNode report) {
		for(AbstractFileServerNode childNode : report.getChildren()){
			exportConfig.addItemConfig(new TreeNodeExportItemConfig(childNode));
			addChildren(exportConfig, childNode);
		}
	}


	@Override
	public String loadResult() throws ServerCallFailedException {
		return httpExportServiceProvider.get().getAndRemoveStoredExport();
	}

	@SecurityChecked(
			argumentVerification = {
					@ArgumentVerification(
							name = "parentNode",
							isDto = true,
							verify = @RightsVerification(actions=InsertAction.class)
							)
			}
			)
	@Override
	@Transactional(rollbackOn={Exception.class})
	public List<FileServerFileDto> uploadFiles(@Named("parentNode") FileServerFolderDto folderDto,
			List<FileToUpload> files)
					throws ServerCallFailedException {
		List<FileServerFileDto> dtoFiles = new ArrayList<FileServerFileDto>(); 

		FileServerFolder folder = (FileServerFolder) dtoService.loadPoso(folderDto);

		for(FileToUpload uFile : files){
			FileServerFile file = getFileFromUploadFile(uFile);
			folder.addChild(file);

			fileService.persist(file);

			dtoFiles.add((FileServerFileDto) dtoService.createDto(file));
		}

		fileService.merge(folder);

		return dtoFiles;
	}

	
	@SecurityChecked(
			argumentVerification = {
					@ArgumentVerification(
							name = "parentNode",
							isDto = true,
							verify = @RightsVerification(actions=InsertAction.class)
							)
			}
			)
	@Override
	@Transactional(rollbackOn={Exception.class})
	public List<AbstractFileServerNodeDto> uploadAndExtract(@Named("parentNode") FileServerFolderDto folderDto, FileToUpload fileToUpload)
			throws ServerCallFailedException {
		List<AbstractFileServerNodeDto> dtoFiles = new ArrayList<AbstractFileServerNodeDto>(); 

		FileServerFolder folder = (FileServerFolder) dtoService.loadPoso(folderDto);
		
		if(null != fileToUpload){
			FileServerFile archive = getFileFromUploadFile(fileToUpload);
			
			/* create folder to extract into */
			FileServerFolder base = new FileServerFolder();
			String name = archive.getName();
			base.setName(name.endsWith(".zip") ? name.substring(0, name.length()-4) : name);
			folder.addChild(base);
			fileService.persist(base);
			dtoFiles.add((AbstractFileServerNodeDto) dtoService.createListDto(base));
			
			base.addChild(archive);
			
			ZipExtractionConfig zec = extractConfigFactory.create(base);

			try {
				zipUtilsServiceProvider.get().extractZip(archive.getData(), zec);
				
				/* remove zip */
				base.removeChild(archive);
			} catch (IOException e) {
				throw new ServerCallFailedException(e);
			}
		}
		
		fileService.merge(folder);
		
		return dtoFiles;
	}
	
	protected FileServerFile getFileFromUploadFile(FileToUpload uFile) {
		int b64idx = uFile.getB64Data().indexOf("base64,");

		String strData = uFile.getB64Data().substring(b64idx + 7);
		String mimeType = (b64idx < 6)? "" : uFile.getB64Data().substring(5, b64idx - 1);

		if(null == mimeType || mimeType.isEmpty() || "application/octet-stream".equals(mimeType)){
			mimeType = mimeUtils.getMimeTypeByExtension(uFile.getName());
		}

		byte[] data = Base64.decodeBase64(strData.getBytes());

		FileServerFile file = new FileServerFile();
		file.setName(uFile.getName());
		file.setContentType(mimeType);
		file.setData(data);
		
		return file;
	}

	@Override
	protected void nodeCloned(AbstractFileServerNode clonedNode) {
		if(! (clonedNode instanceof FileServerFile))
			throw new IllegalArgumentException();
		FileServerFile file = (FileServerFile) clonedNode;
		
		file.setName(file.getName() == null ? "copy" : file.getName() + " (copy)");
	}

}
