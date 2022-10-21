package net.datenwerke.rs.base.ext.service.parameters.fileselection.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.util.Collection;
import javax.persistence.EntityManager;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.SelectedParameterFileDto;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.UploadedParameterFileDto;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.SelectedParameterFile;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.UploadedParameterFile;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.dtogen.Dto2SelectedParameterFileGenerator;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.AbstractTsDiskNode;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for SelectedParameterFile
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2SelectedParameterFileGenerator implements Dto2PosoGenerator<SelectedParameterFileDto,SelectedParameterFile> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2SelectedParameterFileGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		Provider<EntityManager> entityManagerProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.entityManagerProvider = entityManagerProvider;
		this.reflectionService = reflectionService;
	}

	public SelectedParameterFile loadPoso(SelectedParameterFileDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		SelectedParameterFile poso = entityManager.find(SelectedParameterFile.class, id);
		return poso;
	}

	public SelectedParameterFile instantiatePoso()  {
		SelectedParameterFile poso = new SelectedParameterFile();
		return poso;
	}

	public SelectedParameterFile createPoso(SelectedParameterFileDto dto)  throws ExpectedException {
		SelectedParameterFile poso = new SelectedParameterFile();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public SelectedParameterFile createUnmanagedPoso(SelectedParameterFileDto dto)  throws ExpectedException {
		SelectedParameterFile poso = new SelectedParameterFile();

		/* store old id */
		if(null != dto.getId()){
			Field transientIdField = reflectionService.getFieldByAnnotation(poso, TransientID.class);
			if(null != transientIdField){
				transientIdField.setAccessible(true);
				try{
					transientIdField.set(poso, dto.getId());
				} catch(Exception e){
				}
			}
		}

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(SelectedParameterFileDto dto, final SelectedParameterFile poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(SelectedParameterFileDto dto, final SelectedParameterFile poso)  throws ExpectedException {
		/*  set fileServerFile */
		AbstractFileServerNodeDto tmpDto_fileServerFile = dto.getFileServerFile();
		if(null != tmpDto_fileServerFile && null != tmpDto_fileServerFile.getId()){
			if(null != poso.getFileServerFile() && null != poso.getFileServerFile().getId() && ! poso.getFileServerFile().getId().equals(tmpDto_fileServerFile.getId())){
				AbstractFileServerNode newPropertyValue = (AbstractFileServerNode)dtoServiceProvider.get().loadPoso(tmpDto_fileServerFile);
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getFileServerFile(), newPropertyValue, "fileServerFile");
				poso.setFileServerFile(newPropertyValue);
			} else if(null == poso.getFileServerFile()){
				poso.setFileServerFile((AbstractFileServerNode)dtoServiceProvider.get().loadPoso(tmpDto_fileServerFile));
			}
		} else if(null != tmpDto_fileServerFile && null == tmpDto_fileServerFile.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_fileServerFile, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso)
						throw new IllegalArgumentException("referenced dto should have an id (fileServerFile)");
					poso.setFileServerFile((AbstractFileServerNode)refPoso);
				}
			});
		} else if(null == tmpDto_fileServerFile){
			dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getFileServerFile(), null, "fileServerFile");
			poso.setFileServerFile(null);
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set teamSpaceFile */
		AbstractTsDiskNodeDto tmpDto_teamSpaceFile = dto.getTeamSpaceFile();
		if(null != tmpDto_teamSpaceFile && null != tmpDto_teamSpaceFile.getId()){
			if(null != poso.getTeamSpaceFile() && null != poso.getTeamSpaceFile().getId() && ! poso.getTeamSpaceFile().getId().equals(tmpDto_teamSpaceFile.getId())){
				AbstractTsDiskNode newPropertyValue = (AbstractTsDiskNode)dtoServiceProvider.get().loadPoso(tmpDto_teamSpaceFile);
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getTeamSpaceFile(), newPropertyValue, "teamSpaceFile");
				poso.setTeamSpaceFile(newPropertyValue);
			} else if(null == poso.getTeamSpaceFile()){
				poso.setTeamSpaceFile((AbstractTsDiskNode)dtoServiceProvider.get().loadPoso(tmpDto_teamSpaceFile));
			}
		} else if(null != tmpDto_teamSpaceFile && null == tmpDto_teamSpaceFile.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_teamSpaceFile, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso)
						throw new IllegalArgumentException("referenced dto should have an id (teamSpaceFile)");
					poso.setTeamSpaceFile((AbstractTsDiskNode)refPoso);
				}
			});
		} else if(null == tmpDto_teamSpaceFile){
			dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getTeamSpaceFile(), null, "teamSpaceFile");
			poso.setTeamSpaceFile(null);
		}

		/*  set uploadedFile */
		UploadedParameterFileDto tmpDto_uploadedFile = dto.getUploadedFile();
		if(null != tmpDto_uploadedFile && null != tmpDto_uploadedFile.getId()){
			if(null != poso.getUploadedFile() && null != poso.getUploadedFile().getId() && poso.getUploadedFile().getId().equals(tmpDto_uploadedFile.getId()))
				poso.setUploadedFile((UploadedParameterFile)dtoServiceProvider.get().loadAndMergePoso(tmpDto_uploadedFile));
			else
				throw new IllegalArgumentException("enclosed dto should not have non matching id (uploadedFile)");
		} else if(null != poso.getUploadedFile()){
			UploadedParameterFile newPropertyValue = (UploadedParameterFile)dtoServiceProvider.get().createPoso(tmpDto_uploadedFile);
			dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getUploadedFile(), newPropertyValue, "uploadedFile");
			poso.setUploadedFile(newPropertyValue);
		} else {
			poso.setUploadedFile((UploadedParameterFile)dtoServiceProvider.get().createPoso(tmpDto_uploadedFile));
		}

	}

	protected void mergeProxy2Poso(SelectedParameterFileDto dto, final SelectedParameterFile poso)  throws ExpectedException {
		/*  set fileServerFile */
		if(dto.isFileServerFileModified()){
			AbstractFileServerNodeDto tmpDto_fileServerFile = dto.getFileServerFile();
			if(null != tmpDto_fileServerFile && null != tmpDto_fileServerFile.getId()){
				if(null != poso.getFileServerFile() && null != poso.getFileServerFile().getId() && ! poso.getFileServerFile().getId().equals(tmpDto_fileServerFile.getId())){
					AbstractFileServerNode newPropertyValue = (AbstractFileServerNode)dtoServiceProvider.get().loadPoso(tmpDto_fileServerFile);
					dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getFileServerFile(), newPropertyValue, "fileServerFile");
					poso.setFileServerFile(newPropertyValue);
				} else if(null == poso.getFileServerFile()){
					poso.setFileServerFile((AbstractFileServerNode)dtoServiceProvider.get().loadPoso(tmpDto_fileServerFile));
				}
			} else if(null != tmpDto_fileServerFile && null == tmpDto_fileServerFile.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_fileServerFile, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso)
							throw new IllegalArgumentException("referenced dto should have an id (fileServerFile)");
						poso.setFileServerFile((AbstractFileServerNode)refPoso);
					}
			});
			} else if(null == tmpDto_fileServerFile){
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getFileServerFile(), null, "fileServerFile");
				poso.setFileServerFile(null);
			}
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set teamSpaceFile */
		if(dto.isTeamSpaceFileModified()){
			AbstractTsDiskNodeDto tmpDto_teamSpaceFile = dto.getTeamSpaceFile();
			if(null != tmpDto_teamSpaceFile && null != tmpDto_teamSpaceFile.getId()){
				if(null != poso.getTeamSpaceFile() && null != poso.getTeamSpaceFile().getId() && ! poso.getTeamSpaceFile().getId().equals(tmpDto_teamSpaceFile.getId())){
					AbstractTsDiskNode newPropertyValue = (AbstractTsDiskNode)dtoServiceProvider.get().loadPoso(tmpDto_teamSpaceFile);
					dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getTeamSpaceFile(), newPropertyValue, "teamSpaceFile");
					poso.setTeamSpaceFile(newPropertyValue);
				} else if(null == poso.getTeamSpaceFile()){
					poso.setTeamSpaceFile((AbstractTsDiskNode)dtoServiceProvider.get().loadPoso(tmpDto_teamSpaceFile));
				}
			} else if(null != tmpDto_teamSpaceFile && null == tmpDto_teamSpaceFile.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_teamSpaceFile, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso)
							throw new IllegalArgumentException("referenced dto should have an id (teamSpaceFile)");
						poso.setTeamSpaceFile((AbstractTsDiskNode)refPoso);
					}
			});
			} else if(null == tmpDto_teamSpaceFile){
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getTeamSpaceFile(), null, "teamSpaceFile");
				poso.setTeamSpaceFile(null);
			}
		}

		/*  set uploadedFile */
		if(dto.isUploadedFileModified()){
			UploadedParameterFileDto tmpDto_uploadedFile = dto.getUploadedFile();
			if(null != tmpDto_uploadedFile && null != tmpDto_uploadedFile.getId()){
				if(null != poso.getUploadedFile() && null != poso.getUploadedFile().getId() && poso.getUploadedFile().getId().equals(tmpDto_uploadedFile.getId()))
					poso.setUploadedFile((UploadedParameterFile)dtoServiceProvider.get().loadAndMergePoso(tmpDto_uploadedFile));
				else
					throw new IllegalArgumentException("enclosed dto should not have non matching id (uploadedFile)");
			} else if(null != poso.getUploadedFile()){
				UploadedParameterFile newPropertyValue = (UploadedParameterFile)dtoServiceProvider.get().createPoso(tmpDto_uploadedFile);
				dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getUploadedFile(), newPropertyValue, "uploadedFile");
				poso.setUploadedFile(newPropertyValue);
			} else {
				poso.setUploadedFile((UploadedParameterFile)dtoServiceProvider.get().createPoso(tmpDto_uploadedFile));
			}
		}

	}

	public void mergeUnmanagedPoso(SelectedParameterFileDto dto, final SelectedParameterFile poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(SelectedParameterFileDto dto, final SelectedParameterFile poso)  throws ExpectedException {
		/*  set fileServerFile */
		AbstractFileServerNodeDto tmpDto_fileServerFile = dto.getFileServerFile();
		if(null != tmpDto_fileServerFile && null != tmpDto_fileServerFile.getId()){
			AbstractFileServerNode newPropertyValue = (AbstractFileServerNode)dtoServiceProvider.get().loadPoso(tmpDto_fileServerFile);
			poso.setFileServerFile(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_fileServerFile, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null != refPoso)
						poso.setFileServerFile((AbstractFileServerNode)refPoso);
				}
			});
		} else if(null != tmpDto_fileServerFile && null == tmpDto_fileServerFile.getId()){
			final AbstractFileServerNodeDto tmpDto_fileServerFile_final = tmpDto_fileServerFile;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_fileServerFile, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso){
						try{
							poso.setFileServerFile((AbstractFileServerNode)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_fileServerFile_final));
						} catch(ExpectedException e){
							throw new RuntimeException(e);
						}
					} else {
						poso.setFileServerFile((AbstractFileServerNode)refPoso);
					}
				}
			});
		} else if(null == tmpDto_fileServerFile){
			poso.setFileServerFile(null);
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set teamSpaceFile */
		AbstractTsDiskNodeDto tmpDto_teamSpaceFile = dto.getTeamSpaceFile();
		if(null != tmpDto_teamSpaceFile && null != tmpDto_teamSpaceFile.getId()){
			AbstractTsDiskNode newPropertyValue = (AbstractTsDiskNode)dtoServiceProvider.get().loadPoso(tmpDto_teamSpaceFile);
			poso.setTeamSpaceFile(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_teamSpaceFile, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null != refPoso)
						poso.setTeamSpaceFile((AbstractTsDiskNode)refPoso);
				}
			});
		} else if(null != tmpDto_teamSpaceFile && null == tmpDto_teamSpaceFile.getId()){
			final AbstractTsDiskNodeDto tmpDto_teamSpaceFile_final = tmpDto_teamSpaceFile;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_teamSpaceFile, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso){
						try{
							poso.setTeamSpaceFile((AbstractTsDiskNode)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_teamSpaceFile_final));
						} catch(ExpectedException e){
							throw new RuntimeException(e);
						}
					} else {
						poso.setTeamSpaceFile((AbstractTsDiskNode)refPoso);
					}
				}
			});
		} else if(null == tmpDto_teamSpaceFile){
			poso.setTeamSpaceFile(null);
		}

		/*  set uploadedFile */
		UploadedParameterFileDto tmpDto_uploadedFile = dto.getUploadedFile();
		poso.setUploadedFile((UploadedParameterFile)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_uploadedFile));

	}

	protected void mergeProxy2UnmanagedPoso(SelectedParameterFileDto dto, final SelectedParameterFile poso)  throws ExpectedException {
		/*  set fileServerFile */
		if(dto.isFileServerFileModified()){
			AbstractFileServerNodeDto tmpDto_fileServerFile = dto.getFileServerFile();
			if(null != tmpDto_fileServerFile && null != tmpDto_fileServerFile.getId()){
				AbstractFileServerNode newPropertyValue = (AbstractFileServerNode)dtoServiceProvider.get().loadPoso(tmpDto_fileServerFile);
				poso.setFileServerFile(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_fileServerFile, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null != refPoso)
							poso.setFileServerFile((AbstractFileServerNode)refPoso);
					}
			});
			} else if(null != tmpDto_fileServerFile && null == tmpDto_fileServerFile.getId()){
				final AbstractFileServerNodeDto tmpDto_fileServerFile_final = tmpDto_fileServerFile;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_fileServerFile, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso){
							try{
								poso.setFileServerFile((AbstractFileServerNode)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_fileServerFile_final));
							} catch(ExpectedException e){
								throw new RuntimeException(e);
							}
						} else {
							poso.setFileServerFile((AbstractFileServerNode)refPoso);
						}
					}
			});
			} else if(null == tmpDto_fileServerFile){
				poso.setFileServerFile(null);
			}
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set teamSpaceFile */
		if(dto.isTeamSpaceFileModified()){
			AbstractTsDiskNodeDto tmpDto_teamSpaceFile = dto.getTeamSpaceFile();
			if(null != tmpDto_teamSpaceFile && null != tmpDto_teamSpaceFile.getId()){
				AbstractTsDiskNode newPropertyValue = (AbstractTsDiskNode)dtoServiceProvider.get().loadPoso(tmpDto_teamSpaceFile);
				poso.setTeamSpaceFile(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_teamSpaceFile, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null != refPoso)
							poso.setTeamSpaceFile((AbstractTsDiskNode)refPoso);
					}
			});
			} else if(null != tmpDto_teamSpaceFile && null == tmpDto_teamSpaceFile.getId()){
				final AbstractTsDiskNodeDto tmpDto_teamSpaceFile_final = tmpDto_teamSpaceFile;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_teamSpaceFile, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso){
							try{
								poso.setTeamSpaceFile((AbstractTsDiskNode)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_teamSpaceFile_final));
							} catch(ExpectedException e){
								throw new RuntimeException(e);
							}
						} else {
							poso.setTeamSpaceFile((AbstractTsDiskNode)refPoso);
						}
					}
			});
			} else if(null == tmpDto_teamSpaceFile){
				poso.setTeamSpaceFile(null);
			}
		}

		/*  set uploadedFile */
		if(dto.isUploadedFileModified()){
			UploadedParameterFileDto tmpDto_uploadedFile = dto.getUploadedFile();
			poso.setUploadedFile((UploadedParameterFile)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_uploadedFile));
		}

	}

	public SelectedParameterFile loadAndMergePoso(SelectedParameterFileDto dto)  throws ExpectedException {
		SelectedParameterFile poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(SelectedParameterFileDto dto, SelectedParameterFile poso)  {
	}


	public void postProcessCreateUnmanaged(SelectedParameterFileDto dto, SelectedParameterFile poso)  {
	}


	public void postProcessLoad(SelectedParameterFileDto dto, SelectedParameterFile poso)  {
	}


	public void postProcessMerge(SelectedParameterFileDto dto, SelectedParameterFile poso)  {
	}


	public void postProcessInstantiate(SelectedParameterFile poso)  {
	}



}
