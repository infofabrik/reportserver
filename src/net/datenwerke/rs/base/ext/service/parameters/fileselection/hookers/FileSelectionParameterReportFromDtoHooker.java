package net.datenwerke.rs.base.ext.service.parameters.fileselection.hookers;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.gf.client.fileselection.dto.SelectedFileWrapper;
import net.datenwerke.gf.service.upload.FileUploadService;
import net.datenwerke.gf.service.upload.UploadedFile;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.FileSelectionParameterDefinitionDto;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.decorator.FileSelectionParameterInstanceDtoDec;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.FileSelectionParameterDefinition;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.FileSelectionParameterDtoHelper;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.FileSelectionParameterInstance;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.SelectedParameterFile;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.UploadedParameterFile;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.decorator.ReportDtoDec;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportCreatedFromDtoHook;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.AbstractTsDiskNode;
import net.datenwerke.rs.utils.entitycloner.EntityClonerService;
import net.datenwerke.security.service.security.exceptions.ViolatedSecurityException;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;

public class FileSelectionParameterReportFromDtoHooker implements
		ReportCreatedFromDtoHook {

	private final FileUploadService uploadService;
	private final DtoService dtoService;
	private final EntityClonerService entityCloner;
	private final FileSelectionParameterDtoHelper fileSelectionParameterDtoHelper;
	private final Provider<Injector> injectorProvider;
	
	
	@Inject
	public FileSelectionParameterReportFromDtoHooker(
			FileUploadService uploadService,
			DtoService dtoService,
			EntityClonerService entityCloner,
			FileSelectionParameterDtoHelper fileSelectionParameterDtoHelper,
			Provider<Injector> injectorProvider) {
		this.uploadService = uploadService;
		this.dtoService = dtoService;
		this.entityCloner = entityCloner;
		this.fileSelectionParameterDtoHelper = fileSelectionParameterDtoHelper;
		this.injectorProvider =injectorProvider;
	}

	@Override
	public void reportCreated(ReportDto reportDto, Report report) {
		fileSelectionParameterDtoHelper.adaptPoso(reportDto,report);
	}

	@Override
	public void reportMerged(ReportDto reportDto, Report report) {
		fileSelectionParameterDtoHelper.adaptPoso(reportDto,report);
	}

	@Override
	public void reportCreatedUnmanaged(ReportDto reportDto, Report report) {
		
	}

}
