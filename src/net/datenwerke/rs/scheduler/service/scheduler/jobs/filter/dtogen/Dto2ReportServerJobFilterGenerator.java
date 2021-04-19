package net.datenwerke.rs.scheduler.service.scheduler.jobs.filter.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.NullPointerException;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.util.Collection;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportServerJobFilterDto;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.filter.ReportServerJobFilter;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.filter.dtogen.Dto2ReportServerJobFilterGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;
import net.datenwerke.scheduler.client.scheduler.dto.JobExecutionStatusDto;
import net.datenwerke.scheduler.client.scheduler.dto.OutcomeDto;
import net.datenwerke.scheduler.client.scheduler.dto.filter.OrderDto;
import net.datenwerke.scheduler.service.scheduler.entities.JobExecutionStatus;
import net.datenwerke.scheduler.service.scheduler.entities.Outcome;
import net.datenwerke.scheduler.service.scheduler.stores.jpa.filter.Order;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * Dto2PosoGenerator for ReportServerJobFilter
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2ReportServerJobFilterGenerator implements Dto2PosoGenerator<ReportServerJobFilterDto,ReportServerJobFilter> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2ReportServerJobFilterGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public ReportServerJobFilter loadPoso(ReportServerJobFilterDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public ReportServerJobFilter instantiatePoso()  {
		ReportServerJobFilter poso = new ReportServerJobFilter();
		return poso;
	}

	public ReportServerJobFilter createPoso(ReportServerJobFilterDto dto)  throws ExpectedException {
		ReportServerJobFilter poso = new ReportServerJobFilter();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public ReportServerJobFilter createUnmanagedPoso(ReportServerJobFilterDto dto)  throws ExpectedException {
		ReportServerJobFilter poso = new ReportServerJobFilter();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(ReportServerJobFilterDto dto, final ReportServerJobFilter poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(ReportServerJobFilterDto dto, final ReportServerJobFilter poso)  throws ExpectedException {
		/*  set active */
		try{
			poso.setActive(dto.isActive() );
		} catch(NullPointerException e){
		}

		/*  set executionStatus */
		JobExecutionStatusDto tmpDto_executionStatus = dto.getExecutionStatus();
		poso.setExecutionStatus((JobExecutionStatus)dtoServiceProvider.get().createPoso(tmpDto_executionStatus));

		/*  set fromCurrentUser */
		try{
			poso.setFromCurrentUser(dto.isFromCurrentUser() );
		} catch(NullPointerException e){
		}

		/*  set fromUser */
		UserDto tmpDto_fromUser = dto.getFromUser();
		if(null != tmpDto_fromUser && null != tmpDto_fromUser.getId()){
			if(null != poso.getFromUser() && null != poso.getFromUser().getId() && ! poso.getFromUser().getId().equals(tmpDto_fromUser.getId())){
				User newPropertyValue = (User)dtoServiceProvider.get().loadPoso(tmpDto_fromUser);
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getFromUser(), newPropertyValue, "fromUser");
				poso.setFromUser(newPropertyValue);
			} else if(null == poso.getFromUser()){
				poso.setFromUser((User)dtoServiceProvider.get().loadPoso(tmpDto_fromUser));
			}
		} else if(null != tmpDto_fromUser && null == tmpDto_fromUser.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_fromUser, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso)
						throw new IllegalArgumentException("referenced dto should have an id (fromUser)");
					poso.setFromUser((User)refPoso);
				}
			});
		} else if(null == tmpDto_fromUser){
			dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getFromUser(), null, "fromUser");
			poso.setFromUser(null);
		}

		/*  set inActive */
		try{
			poso.setInActive(dto.isInActive() );
		} catch(NullPointerException e){
		}

		/*  set jobId */
		poso.setJobId(dto.getJobId() );

		/*  set jobTitle */
		poso.setJobTitle(dto.getJobTitle() );

		/*  set lastOutcome */
		OutcomeDto tmpDto_lastOutcome = dto.getLastOutcome();
		poso.setLastOutcome((Outcome)dtoServiceProvider.get().createPoso(tmpDto_lastOutcome));

		/*  set limit */
		try{
			poso.setLimit(dto.getLimit() );
		} catch(NullPointerException e){
		}

		/*  set offset */
		try{
			poso.setOffset(dto.getOffset() );
		} catch(NullPointerException e){
		}

		/*  set order */
		OrderDto tmpDto_order = dto.getOrder();
		poso.setOrder((Order)dtoServiceProvider.get().createPoso(tmpDto_order));

		/*  set owner */
		UserDto tmpDto_owner = dto.getOwner();
		if(null != tmpDto_owner && null != tmpDto_owner.getId()){
			if(null != poso.getOwner() && null != poso.getOwner().getId() && ! poso.getOwner().getId().equals(tmpDto_owner.getId())){
				User newPropertyValue = (User)dtoServiceProvider.get().loadPoso(tmpDto_owner);
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getOwner(), newPropertyValue, "owner");
				poso.setOwner(newPropertyValue);
			} else if(null == poso.getOwner()){
				poso.setOwner((User)dtoServiceProvider.get().loadPoso(tmpDto_owner));
			}
		} else if(null != tmpDto_owner && null == tmpDto_owner.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_owner, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso)
						throw new IllegalArgumentException("referenced dto should have an id (owner)");
					poso.setOwner((User)refPoso);
				}
			});
		} else if(null == tmpDto_owner){
			dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getOwner(), null, "owner");
			poso.setOwner(null);
		}

		/*  set reportId */
		poso.setReportId(dto.getReportId() );

		/*  set reports */
		poso.setReports(dto.getReports() );

		/*  set scheduledBy */
		UserDto tmpDto_scheduledBy = dto.getScheduledBy();
		if(null != tmpDto_scheduledBy && null != tmpDto_scheduledBy.getId()){
			if(null != poso.getScheduledBy() && null != poso.getScheduledBy().getId() && ! poso.getScheduledBy().getId().equals(tmpDto_scheduledBy.getId())){
				User newPropertyValue = (User)dtoServiceProvider.get().loadPoso(tmpDto_scheduledBy);
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getScheduledBy(), newPropertyValue, "scheduledBy");
				poso.setScheduledBy(newPropertyValue);
			} else if(null == poso.getScheduledBy()){
				poso.setScheduledBy((User)dtoServiceProvider.get().loadPoso(tmpDto_scheduledBy));
			}
		} else if(null != tmpDto_scheduledBy && null == tmpDto_scheduledBy.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_scheduledBy, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso)
						throw new IllegalArgumentException("referenced dto should have an id (scheduledBy)");
					poso.setScheduledBy((User)refPoso);
				}
			});
		} else if(null == tmpDto_scheduledBy){
			dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getScheduledBy(), null, "scheduledBy");
			poso.setScheduledBy(null);
		}

		/*  set sortField */
		poso.setSortField(dto.getSortField() );

		/*  set toCurrentUser */
		try{
			poso.setToCurrentUser(dto.isToCurrentUser() );
		} catch(NullPointerException e){
		}

		/*  set toUser */
		UserDto tmpDto_toUser = dto.getToUser();
		if(null != tmpDto_toUser && null != tmpDto_toUser.getId()){
			if(null != poso.getToUser() && null != poso.getToUser().getId() && ! poso.getToUser().getId().equals(tmpDto_toUser.getId())){
				User newPropertyValue = (User)dtoServiceProvider.get().loadPoso(tmpDto_toUser);
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getToUser(), newPropertyValue, "toUser");
				poso.setToUser(newPropertyValue);
			} else if(null == poso.getToUser()){
				poso.setToUser((User)dtoServiceProvider.get().loadPoso(tmpDto_toUser));
			}
		} else if(null != tmpDto_toUser && null == tmpDto_toUser.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_toUser, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso)
						throw new IllegalArgumentException("referenced dto should have an id (toUser)");
					poso.setToUser((User)refPoso);
				}
			});
		} else if(null == tmpDto_toUser){
			dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getToUser(), null, "toUser");
			poso.setToUser(null);
		}

	}

	protected void mergeProxy2Poso(ReportServerJobFilterDto dto, final ReportServerJobFilter poso)  throws ExpectedException {
		/*  set active */
		if(dto.isActiveModified()){
			try{
				poso.setActive(dto.isActive() );
			} catch(NullPointerException e){
			}
		}

		/*  set executionStatus */
		if(dto.isExecutionStatusModified()){
			JobExecutionStatusDto tmpDto_executionStatus = dto.getExecutionStatus();
			poso.setExecutionStatus((JobExecutionStatus)dtoServiceProvider.get().createPoso(tmpDto_executionStatus));
		}

		/*  set fromCurrentUser */
		if(dto.isFromCurrentUserModified()){
			try{
				poso.setFromCurrentUser(dto.isFromCurrentUser() );
			} catch(NullPointerException e){
			}
		}

		/*  set fromUser */
		if(dto.isFromUserModified()){
			UserDto tmpDto_fromUser = dto.getFromUser();
			if(null != tmpDto_fromUser && null != tmpDto_fromUser.getId()){
				if(null != poso.getFromUser() && null != poso.getFromUser().getId() && ! poso.getFromUser().getId().equals(tmpDto_fromUser.getId())){
					User newPropertyValue = (User)dtoServiceProvider.get().loadPoso(tmpDto_fromUser);
					dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getFromUser(), newPropertyValue, "fromUser");
					poso.setFromUser(newPropertyValue);
				} else if(null == poso.getFromUser()){
					poso.setFromUser((User)dtoServiceProvider.get().loadPoso(tmpDto_fromUser));
				}
			} else if(null != tmpDto_fromUser && null == tmpDto_fromUser.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_fromUser, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso)
							throw new IllegalArgumentException("referenced dto should have an id (fromUser)");
						poso.setFromUser((User)refPoso);
					}
			});
			} else if(null == tmpDto_fromUser){
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getFromUser(), null, "fromUser");
				poso.setFromUser(null);
			}
		}

		/*  set inActive */
		if(dto.isInActiveModified()){
			try{
				poso.setInActive(dto.isInActive() );
			} catch(NullPointerException e){
			}
		}

		/*  set jobId */
		if(dto.isJobIdModified()){
			poso.setJobId(dto.getJobId() );
		}

		/*  set jobTitle */
		if(dto.isJobTitleModified()){
			poso.setJobTitle(dto.getJobTitle() );
		}

		/*  set lastOutcome */
		if(dto.isLastOutcomeModified()){
			OutcomeDto tmpDto_lastOutcome = dto.getLastOutcome();
			poso.setLastOutcome((Outcome)dtoServiceProvider.get().createPoso(tmpDto_lastOutcome));
		}

		/*  set limit */
		if(dto.isLimitModified()){
			try{
				poso.setLimit(dto.getLimit() );
			} catch(NullPointerException e){
			}
		}

		/*  set offset */
		if(dto.isOffsetModified()){
			try{
				poso.setOffset(dto.getOffset() );
			} catch(NullPointerException e){
			}
		}

		/*  set order */
		if(dto.isOrderModified()){
			OrderDto tmpDto_order = dto.getOrder();
			poso.setOrder((Order)dtoServiceProvider.get().createPoso(tmpDto_order));
		}

		/*  set owner */
		if(dto.isOwnerModified()){
			UserDto tmpDto_owner = dto.getOwner();
			if(null != tmpDto_owner && null != tmpDto_owner.getId()){
				if(null != poso.getOwner() && null != poso.getOwner().getId() && ! poso.getOwner().getId().equals(tmpDto_owner.getId())){
					User newPropertyValue = (User)dtoServiceProvider.get().loadPoso(tmpDto_owner);
					dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getOwner(), newPropertyValue, "owner");
					poso.setOwner(newPropertyValue);
				} else if(null == poso.getOwner()){
					poso.setOwner((User)dtoServiceProvider.get().loadPoso(tmpDto_owner));
				}
			} else if(null != tmpDto_owner && null == tmpDto_owner.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_owner, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso)
							throw new IllegalArgumentException("referenced dto should have an id (owner)");
						poso.setOwner((User)refPoso);
					}
			});
			} else if(null == tmpDto_owner){
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getOwner(), null, "owner");
				poso.setOwner(null);
			}
		}

		/*  set reportId */
		if(dto.isReportIdModified()){
			poso.setReportId(dto.getReportId() );
		}

		/*  set reports */
		if(dto.isReportsModified()){
			poso.setReports(dto.getReports() );
		}

		/*  set scheduledBy */
		if(dto.isScheduledByModified()){
			UserDto tmpDto_scheduledBy = dto.getScheduledBy();
			if(null != tmpDto_scheduledBy && null != tmpDto_scheduledBy.getId()){
				if(null != poso.getScheduledBy() && null != poso.getScheduledBy().getId() && ! poso.getScheduledBy().getId().equals(tmpDto_scheduledBy.getId())){
					User newPropertyValue = (User)dtoServiceProvider.get().loadPoso(tmpDto_scheduledBy);
					dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getScheduledBy(), newPropertyValue, "scheduledBy");
					poso.setScheduledBy(newPropertyValue);
				} else if(null == poso.getScheduledBy()){
					poso.setScheduledBy((User)dtoServiceProvider.get().loadPoso(tmpDto_scheduledBy));
				}
			} else if(null != tmpDto_scheduledBy && null == tmpDto_scheduledBy.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_scheduledBy, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso)
							throw new IllegalArgumentException("referenced dto should have an id (scheduledBy)");
						poso.setScheduledBy((User)refPoso);
					}
			});
			} else if(null == tmpDto_scheduledBy){
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getScheduledBy(), null, "scheduledBy");
				poso.setScheduledBy(null);
			}
		}

		/*  set sortField */
		if(dto.isSortFieldModified()){
			poso.setSortField(dto.getSortField() );
		}

		/*  set toCurrentUser */
		if(dto.isToCurrentUserModified()){
			try{
				poso.setToCurrentUser(dto.isToCurrentUser() );
			} catch(NullPointerException e){
			}
		}

		/*  set toUser */
		if(dto.isToUserModified()){
			UserDto tmpDto_toUser = dto.getToUser();
			if(null != tmpDto_toUser && null != tmpDto_toUser.getId()){
				if(null != poso.getToUser() && null != poso.getToUser().getId() && ! poso.getToUser().getId().equals(tmpDto_toUser.getId())){
					User newPropertyValue = (User)dtoServiceProvider.get().loadPoso(tmpDto_toUser);
					dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getToUser(), newPropertyValue, "toUser");
					poso.setToUser(newPropertyValue);
				} else if(null == poso.getToUser()){
					poso.setToUser((User)dtoServiceProvider.get().loadPoso(tmpDto_toUser));
				}
			} else if(null != tmpDto_toUser && null == tmpDto_toUser.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_toUser, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso)
							throw new IllegalArgumentException("referenced dto should have an id (toUser)");
						poso.setToUser((User)refPoso);
					}
			});
			} else if(null == tmpDto_toUser){
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getToUser(), null, "toUser");
				poso.setToUser(null);
			}
		}

	}

	public void mergeUnmanagedPoso(ReportServerJobFilterDto dto, final ReportServerJobFilter poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(ReportServerJobFilterDto dto, final ReportServerJobFilter poso)  throws ExpectedException {
		/*  set active */
		try{
			poso.setActive(dto.isActive() );
		} catch(NullPointerException e){
		}

		/*  set executionStatus */
		JobExecutionStatusDto tmpDto_executionStatus = dto.getExecutionStatus();
		poso.setExecutionStatus((JobExecutionStatus)dtoServiceProvider.get().createPoso(tmpDto_executionStatus));

		/*  set fromCurrentUser */
		try{
			poso.setFromCurrentUser(dto.isFromCurrentUser() );
		} catch(NullPointerException e){
		}

		/*  set fromUser */
		UserDto tmpDto_fromUser = dto.getFromUser();
		if(null != tmpDto_fromUser && null != tmpDto_fromUser.getId()){
			User newPropertyValue = (User)dtoServiceProvider.get().loadPoso(tmpDto_fromUser);
			poso.setFromUser(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_fromUser, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null != refPoso)
						poso.setFromUser((User)refPoso);
				}
			});
		} else if(null != tmpDto_fromUser && null == tmpDto_fromUser.getId()){
			final UserDto tmpDto_fromUser_final = tmpDto_fromUser;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_fromUser, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso){
						try{
							poso.setFromUser((User)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_fromUser_final));
						} catch(ExpectedException e){
							throw new RuntimeException(e);
						}
					} else {
						poso.setFromUser((User)refPoso);
					}
				}
			});
		} else if(null == tmpDto_fromUser){
			poso.setFromUser(null);
		}

		/*  set inActive */
		try{
			poso.setInActive(dto.isInActive() );
		} catch(NullPointerException e){
		}

		/*  set jobId */
		poso.setJobId(dto.getJobId() );

		/*  set jobTitle */
		poso.setJobTitle(dto.getJobTitle() );

		/*  set lastOutcome */
		OutcomeDto tmpDto_lastOutcome = dto.getLastOutcome();
		poso.setLastOutcome((Outcome)dtoServiceProvider.get().createPoso(tmpDto_lastOutcome));

		/*  set limit */
		try{
			poso.setLimit(dto.getLimit() );
		} catch(NullPointerException e){
		}

		/*  set offset */
		try{
			poso.setOffset(dto.getOffset() );
		} catch(NullPointerException e){
		}

		/*  set order */
		OrderDto tmpDto_order = dto.getOrder();
		poso.setOrder((Order)dtoServiceProvider.get().createPoso(tmpDto_order));

		/*  set owner */
		UserDto tmpDto_owner = dto.getOwner();
		if(null != tmpDto_owner && null != tmpDto_owner.getId()){
			User newPropertyValue = (User)dtoServiceProvider.get().loadPoso(tmpDto_owner);
			poso.setOwner(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_owner, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null != refPoso)
						poso.setOwner((User)refPoso);
				}
			});
		} else if(null != tmpDto_owner && null == tmpDto_owner.getId()){
			final UserDto tmpDto_owner_final = tmpDto_owner;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_owner, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso){
						try{
							poso.setOwner((User)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_owner_final));
						} catch(ExpectedException e){
							throw new RuntimeException(e);
						}
					} else {
						poso.setOwner((User)refPoso);
					}
				}
			});
		} else if(null == tmpDto_owner){
			poso.setOwner(null);
		}

		/*  set reportId */
		poso.setReportId(dto.getReportId() );

		/*  set reports */
		poso.setReports(dto.getReports() );

		/*  set scheduledBy */
		UserDto tmpDto_scheduledBy = dto.getScheduledBy();
		if(null != tmpDto_scheduledBy && null != tmpDto_scheduledBy.getId()){
			User newPropertyValue = (User)dtoServiceProvider.get().loadPoso(tmpDto_scheduledBy);
			poso.setScheduledBy(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_scheduledBy, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null != refPoso)
						poso.setScheduledBy((User)refPoso);
				}
			});
		} else if(null != tmpDto_scheduledBy && null == tmpDto_scheduledBy.getId()){
			final UserDto tmpDto_scheduledBy_final = tmpDto_scheduledBy;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_scheduledBy, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso){
						try{
							poso.setScheduledBy((User)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_scheduledBy_final));
						} catch(ExpectedException e){
							throw new RuntimeException(e);
						}
					} else {
						poso.setScheduledBy((User)refPoso);
					}
				}
			});
		} else if(null == tmpDto_scheduledBy){
			poso.setScheduledBy(null);
		}

		/*  set sortField */
		poso.setSortField(dto.getSortField() );

		/*  set toCurrentUser */
		try{
			poso.setToCurrentUser(dto.isToCurrentUser() );
		} catch(NullPointerException e){
		}

		/*  set toUser */
		UserDto tmpDto_toUser = dto.getToUser();
		if(null != tmpDto_toUser && null != tmpDto_toUser.getId()){
			User newPropertyValue = (User)dtoServiceProvider.get().loadPoso(tmpDto_toUser);
			poso.setToUser(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_toUser, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null != refPoso)
						poso.setToUser((User)refPoso);
				}
			});
		} else if(null != tmpDto_toUser && null == tmpDto_toUser.getId()){
			final UserDto tmpDto_toUser_final = tmpDto_toUser;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_toUser, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso){
						try{
							poso.setToUser((User)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_toUser_final));
						} catch(ExpectedException e){
							throw new RuntimeException(e);
						}
					} else {
						poso.setToUser((User)refPoso);
					}
				}
			});
		} else if(null == tmpDto_toUser){
			poso.setToUser(null);
		}

	}

	protected void mergeProxy2UnmanagedPoso(ReportServerJobFilterDto dto, final ReportServerJobFilter poso)  throws ExpectedException {
		/*  set active */
		if(dto.isActiveModified()){
			try{
				poso.setActive(dto.isActive() );
			} catch(NullPointerException e){
			}
		}

		/*  set executionStatus */
		if(dto.isExecutionStatusModified()){
			JobExecutionStatusDto tmpDto_executionStatus = dto.getExecutionStatus();
			poso.setExecutionStatus((JobExecutionStatus)dtoServiceProvider.get().createPoso(tmpDto_executionStatus));
		}

		/*  set fromCurrentUser */
		if(dto.isFromCurrentUserModified()){
			try{
				poso.setFromCurrentUser(dto.isFromCurrentUser() );
			} catch(NullPointerException e){
			}
		}

		/*  set fromUser */
		if(dto.isFromUserModified()){
			UserDto tmpDto_fromUser = dto.getFromUser();
			if(null != tmpDto_fromUser && null != tmpDto_fromUser.getId()){
				User newPropertyValue = (User)dtoServiceProvider.get().loadPoso(tmpDto_fromUser);
				poso.setFromUser(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_fromUser, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null != refPoso)
							poso.setFromUser((User)refPoso);
					}
			});
			} else if(null != tmpDto_fromUser && null == tmpDto_fromUser.getId()){
				final UserDto tmpDto_fromUser_final = tmpDto_fromUser;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_fromUser, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso){
							try{
								poso.setFromUser((User)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_fromUser_final));
							} catch(ExpectedException e){
								throw new RuntimeException(e);
							}
						} else {
							poso.setFromUser((User)refPoso);
						}
					}
			});
			} else if(null == tmpDto_fromUser){
				poso.setFromUser(null);
			}
		}

		/*  set inActive */
		if(dto.isInActiveModified()){
			try{
				poso.setInActive(dto.isInActive() );
			} catch(NullPointerException e){
			}
		}

		/*  set jobId */
		if(dto.isJobIdModified()){
			poso.setJobId(dto.getJobId() );
		}

		/*  set jobTitle */
		if(dto.isJobTitleModified()){
			poso.setJobTitle(dto.getJobTitle() );
		}

		/*  set lastOutcome */
		if(dto.isLastOutcomeModified()){
			OutcomeDto tmpDto_lastOutcome = dto.getLastOutcome();
			poso.setLastOutcome((Outcome)dtoServiceProvider.get().createPoso(tmpDto_lastOutcome));
		}

		/*  set limit */
		if(dto.isLimitModified()){
			try{
				poso.setLimit(dto.getLimit() );
			} catch(NullPointerException e){
			}
		}

		/*  set offset */
		if(dto.isOffsetModified()){
			try{
				poso.setOffset(dto.getOffset() );
			} catch(NullPointerException e){
			}
		}

		/*  set order */
		if(dto.isOrderModified()){
			OrderDto tmpDto_order = dto.getOrder();
			poso.setOrder((Order)dtoServiceProvider.get().createPoso(tmpDto_order));
		}

		/*  set owner */
		if(dto.isOwnerModified()){
			UserDto tmpDto_owner = dto.getOwner();
			if(null != tmpDto_owner && null != tmpDto_owner.getId()){
				User newPropertyValue = (User)dtoServiceProvider.get().loadPoso(tmpDto_owner);
				poso.setOwner(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_owner, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null != refPoso)
							poso.setOwner((User)refPoso);
					}
			});
			} else if(null != tmpDto_owner && null == tmpDto_owner.getId()){
				final UserDto tmpDto_owner_final = tmpDto_owner;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_owner, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso){
							try{
								poso.setOwner((User)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_owner_final));
							} catch(ExpectedException e){
								throw new RuntimeException(e);
							}
						} else {
							poso.setOwner((User)refPoso);
						}
					}
			});
			} else if(null == tmpDto_owner){
				poso.setOwner(null);
			}
		}

		/*  set reportId */
		if(dto.isReportIdModified()){
			poso.setReportId(dto.getReportId() );
		}

		/*  set reports */
		if(dto.isReportsModified()){
			poso.setReports(dto.getReports() );
		}

		/*  set scheduledBy */
		if(dto.isScheduledByModified()){
			UserDto tmpDto_scheduledBy = dto.getScheduledBy();
			if(null != tmpDto_scheduledBy && null != tmpDto_scheduledBy.getId()){
				User newPropertyValue = (User)dtoServiceProvider.get().loadPoso(tmpDto_scheduledBy);
				poso.setScheduledBy(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_scheduledBy, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null != refPoso)
							poso.setScheduledBy((User)refPoso);
					}
			});
			} else if(null != tmpDto_scheduledBy && null == tmpDto_scheduledBy.getId()){
				final UserDto tmpDto_scheduledBy_final = tmpDto_scheduledBy;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_scheduledBy, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso){
							try{
								poso.setScheduledBy((User)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_scheduledBy_final));
							} catch(ExpectedException e){
								throw new RuntimeException(e);
							}
						} else {
							poso.setScheduledBy((User)refPoso);
						}
					}
			});
			} else if(null == tmpDto_scheduledBy){
				poso.setScheduledBy(null);
			}
		}

		/*  set sortField */
		if(dto.isSortFieldModified()){
			poso.setSortField(dto.getSortField() );
		}

		/*  set toCurrentUser */
		if(dto.isToCurrentUserModified()){
			try{
				poso.setToCurrentUser(dto.isToCurrentUser() );
			} catch(NullPointerException e){
			}
		}

		/*  set toUser */
		if(dto.isToUserModified()){
			UserDto tmpDto_toUser = dto.getToUser();
			if(null != tmpDto_toUser && null != tmpDto_toUser.getId()){
				User newPropertyValue = (User)dtoServiceProvider.get().loadPoso(tmpDto_toUser);
				poso.setToUser(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_toUser, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null != refPoso)
							poso.setToUser((User)refPoso);
					}
			});
			} else if(null != tmpDto_toUser && null == tmpDto_toUser.getId()){
				final UserDto tmpDto_toUser_final = tmpDto_toUser;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_toUser, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso){
							try{
								poso.setToUser((User)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_toUser_final));
							} catch(ExpectedException e){
								throw new RuntimeException(e);
							}
						} else {
							poso.setToUser((User)refPoso);
						}
					}
			});
			} else if(null == tmpDto_toUser){
				poso.setToUser(null);
			}
		}

	}

	public ReportServerJobFilter loadAndMergePoso(ReportServerJobFilterDto dto)  throws ExpectedException {
		ReportServerJobFilter poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(ReportServerJobFilterDto dto, ReportServerJobFilter poso)  {
	}


	public void postProcessCreateUnmanaged(ReportServerJobFilterDto dto, ReportServerJobFilter poso)  {
	}


	public void postProcessLoad(ReportServerJobFilterDto dto, ReportServerJobFilter poso)  {
	}


	public void postProcessMerge(ReportServerJobFilterDto dto, ReportServerJobFilter poso)  {
	}


	public void postProcessInstantiate(ReportServerJobFilter poso)  {
	}



}
