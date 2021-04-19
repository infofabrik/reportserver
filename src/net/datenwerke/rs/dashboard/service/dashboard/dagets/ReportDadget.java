package net.datenwerke.rs.dashboard.service.dashboard.dagets;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import net.datenwerke.dtoservices.dtogenerator.annotations.AdditionalField;
import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.dashboard.client.dashboard.dadgets.i.ReportContainerDadget;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.post.ReportDadget2DtoPost;
import net.datenwerke.rs.dashboard.service.dashboard.entities.Dadget;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskReportReference;


@Entity
@Table(name="DADGET_REPORT")
@GenerateDto(
	dtoPackage="net.datenwerke.rs.dashboard.client.dashboard.dto",
	createDecorator=true,
	poso2DtoPostProcessors=ReportDadget2DtoPost.class,
	dtoImplementInterfaces=ReportContainerDadget.class,
	additionalFields=@AdditionalField(name="reportForDisplay",type=ReportDto.class)
)
public class ReportDadget extends Dadget {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3154364200782237989L;

	@ExposeToClient(inheritDtoView=true)
	@ManyToOne(fetch=FetchType.LAZY)
	private Report report;
	
	@ExposeToClient(inheritDtoView=true)
	@ManyToOne(fetch=FetchType.LAZY)
	private TsDiskReportReference reportReference;
	
	@ExposeToClient(
			view=DtoView.MINIMAL
			)
	@Lob
	@Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
	private String config;
	
	@ExposeToClient
	private boolean showExecuteButton;
	
	public void setReport(Report report) {
		this.report = report;
		if(null != report)
			setReportReference(null);
	}

	public Report getReport() {
		return report;
	}

	public void setReportReference(TsDiskReportReference reportReference) {
		this.reportReference = reportReference;
		if(null != reportReference)
			report = null;
	}

	public TsDiskReportReference getReportReference() {
		return reportReference;
	}

	public String getConfig() {
		return config;
	}

	public void setConfig(String config) {
		this.config = config;
	}
	
	public void setShowExecuteButton(boolean showExecuteButton) {
		this.showExecuteButton = showExecuteButton;
	}
	public boolean isShowExecuteButton() {
		return showExecuteButton;
	}

}
