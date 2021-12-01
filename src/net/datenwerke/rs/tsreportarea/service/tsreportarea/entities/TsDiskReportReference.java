package net.datenwerke.rs.tsreportarea.service.tsreportarea.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gf.base.service.annotations.Indexed;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReport;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.birt.service.reportengine.entities.BirtReport;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.crystal.service.crystal.entities.CrystalReport;
import net.datenwerke.rs.grideditor.service.grideditor.entities.GridEditorReport;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.JxlsReport;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport;
import net.datenwerke.rs.scriptreport.service.scriptreport.entities.ScriptReport;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.locale.TsDiskMessages;
import net.datenwerke.rs.utils.instancedescription.annotations.IconProvider;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;

/**
 * 
 *
 */
@Entity
@Table(name="TS_DISK_REPORT_REFERENCE")
@Audited
@Indexed
@GenerateDto(
	dtoPackage="net.datenwerke.rs.tsreportarea.client.tsreportarea.dto",
	createDecorator=true
)
@InstanceDescription(
	msgLocation=TsDiskMessages.class,
	objNameKey="tsDiskReportReferenceName"
)
public class TsDiskReportReference extends TsDiskGeneralReference{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6215675034169121006L;

	@ManyToOne
	@ExposeToClient(
		view=DtoView.LIST,
		inheritDtoView=true
	)
	private Report report;
	
	@ExposeToClient(
		view=DtoView.LIST
	)
	private Boolean hardlink = false;
	
	public void setHardlink(Boolean hardlink) {
		if(null == hardlink)
			hardlink = false;
		this.hardlink = hardlink;
	}

	public Boolean isHardlink() {
		return null == hardlink ? false : hardlink;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public Report getReport() {
		return report;
	}

	@Override
	public Date getReferenceLastUpdated() {
		if(null == report)
			return null;
		return report.getLastUpdated();
	}
	
	@Override
	public boolean hasData() {
		return false;
	}
	
	@IconProvider
	public String getIcon(){
		if(null != getReport()){
			Report r = getReport();
			if(r instanceof TableReport)
				return BaseIcon.REPORT_DL.toString();
			if(r instanceof JasperReport)
				return BaseIcon.REPORT_JASPER.toString();
			if(r instanceof BirtReport)
				return BaseIcon.REPORT_BIRT.toString();
			if(r instanceof CrystalReport)
				return BaseIcon.REPORT_CRYSTAL.toString();
			if(r instanceof GridEditorReport)
				return BaseIcon.REPORT_GE.toString();
			if(r instanceof JxlsReport)
				return BaseIcon.REPORT_JXLS.toString();
			if(r instanceof SaikuReport)
				return BaseIcon.REPORT_SAIKU.toString();
			if(r instanceof ScriptReport)
				return BaseIcon.SCRIPT.toString();
		}
		
		return "file";
	}
}
