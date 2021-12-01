package net.datenwerke.rs.incubator.server.reportmetadata;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.persist.Transactional;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportMetadataDto;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportMetadata;
import net.datenwerke.rs.incubator.client.reportmetadata.rpc.ReportMetadataRpcService;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.annotation.ArgumentVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Write;

/**
 * 
 *
 */
@Singleton
public class ReportMetadataRpcServiceImpl extends SecuredRemoteServiceServlet
		implements ReportMetadataRpcService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6592646535785857296L;

	private final DtoService dtoService;
	private final ReportService reportService;
	
	
	@Inject
	public ReportMetadataRpcServiceImpl(
		DtoService dtoService,
		ReportService reportService
		) {
		
		/* store objects */
		this.dtoService = dtoService;
		this.reportService = reportService;
	}

	@SecurityChecked(
		argumentVerification = {
			@ArgumentVerification(
				name = "report",
				isDto = true,
				verify = @RightsVerification(rights=Write.class)
			)
		}
	)
	@Override
	@Transactional(rollbackOn={Exception.class})
	public ReportDto updateMetadata(@Named("report") ReportDto reportDto,
			List<ReportMetadataDto> addedProperties,
			List<ReportMetadataDto> modifiedProperties, List<ReportMetadataDto> removedProperties
			) throws ServerCallFailedException {
		Report report = (Report) dtoService.loadPoso(reportDto);
		
		/* remove */
		for(ReportMetadataDto propDto : removedProperties){
			ReportMetadata property = (ReportMetadata) dtoService.loadPoso(propDto);
			
			if(! report.hasReportMetadata(property))
				throw new ServerCallFailedException("Report does not have property");
			
			reportService.remove(report, property);
		}
		
		/* add */
		for(ReportMetadataDto propertyDto : addedProperties){
			ReportMetadata property = new ReportMetadata();
			property.setName(propertyDto.getName());
			property.setValue(propertyDto.getValue());
			if(null == property.getName())
				property.setName(getUniqueName(report));
			report.addReportMetadata(property);
			reportService.persist(property);
		}
		
		modifiedProperties.removeAll(removedProperties);
		modifiedProperties.removeAll(addedProperties);
		
		/* update */
		for(ReportMetadataDto propertyDto : modifiedProperties){
			ReportMetadata property = (ReportMetadata) dtoService.loadPoso(propertyDto);
				
			if(! report.hasReportMetadata(property))
				throw new ServerCallFailedException("Report does not have property");
				
			if(null == property.getName())
				property.setName(getUniqueName(report));
			
			/* merge data and later store report */
			dtoService.mergePoso(propertyDto, property);
		}
		
		reportService.merge(report);
		
		return (ReportDto) dtoService.createDtoFullAccess(report);
	}

	protected String getUniqueName(Report report) {
		String name = "unnamed";
		int i = 0;
		while(hasProperty(report, name))
			name = "unnamed_" + (++i);
		
		return name;
	}

	protected boolean hasProperty(Report report, String name) {
		for(ReportMetadata p : report.getReportMetadata())
			if(name.equals(p.getName()))
				return true;
		return false;
	}

	@Override
	@Transactional(rollbackOn={Exception.class})
	public List<String> getMetadataKeys() throws ServerCallFailedException {
		return reportService.getReportMetadataKeys();
	}

}
