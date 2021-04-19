package net.datenwerke.rs.base.ext.client.reportmanager.eximport.im.ui;

import java.util.List;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.sencha.gxt.data.shared.IconProvider;

import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.decorator.JasperReportDtoDec;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.decorator.JasperReportVariantDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.TableReportDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.TableReportVariantDtoDec;
import net.datenwerke.rs.base.ext.client.reportmanager.eximport.im.ReportManagerImportDao;
import net.datenwerke.rs.base.ext.client.reportmanager.eximport.im.dto.ReportManagerImportConfigDto;
import net.datenwerke.rs.birt.client.reportengines.dto.decorator.BirtReportDtoDec;
import net.datenwerke.rs.birt.client.reportengines.dto.decorator.BirtReportVariantDtoDec;
import net.datenwerke.rs.core.client.reportmanager.dto.ReportFolderDto;
import net.datenwerke.rs.crystal.client.crystal.dto.decorator.CrystalReportDtoDec;
import net.datenwerke.rs.crystal.client.crystal.dto.decorator.CrystalReportVariantDtoDec;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorReportDtoDec;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorReportVariantDtoDec;
import net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.decorator.JxlsReportDtoDec;
import net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.decorator.JxlsReportVariantDtoDec;
import net.datenwerke.rs.saiku.client.saiku.dto.decorator.SaikuReportDtoDec;
import net.datenwerke.rs.saiku.client.saiku.dto.decorator.SaikuReportVariantDtoDec;
import net.datenwerke.rs.scriptreport.client.scriptreport.dto.decorator.ScriptReportDtoDec;
import net.datenwerke.rs.scriptreport.client.scriptreport.dto.decorator.ScriptReportVariantDtoDec;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;
import net.datenwerke.treedb.ext.client.eximport.im.ui.ImporterItemsPanel;

public class ReportImporterItemsPanel extends ImporterItemsPanel<ReportManagerImportConfigDto> {

	private final ReportManagerImportDao rmImportDao;
	
	@Inject
	public ReportImporterItemsPanel(ReportManagerImportDao rmImportDao) {
		super();
		
		/* store objects */
		this.rmImportDao = rmImportDao;
		
		/* load data */
		loadData();
	}


	private void loadData() {
		rmImportDao.loadTree(new RsAsyncCallback<List<ImportTreeModel>>(){
			@Override
			public void onSuccess(List<ImportTreeModel> roots) {
				buildTree(roots);
			}
		});
	}
	
	protected void configureTree() {
		super.configureTree();
		
		tree.setIconProvider(new IconProvider<ImportTreeModel>() {
			@Override
			public ImageResource getIcon(ImportTreeModel model) {
				if(TableReportDtoDec.class.getName().equals(model.getType()))
					return BaseIcon.REPORT_DL.toImageResource();
				if(TableReportVariantDtoDec.class.getName().equals(model.getType()))
					return BaseIcon.REPORT_DL.toImageResource();
				
				if(JasperReportDtoDec.class.getName().equals(model.getType()))
					return BaseIcon.REPORT_JASPER.toImageResource();
				if(JasperReportVariantDtoDec.class.getName().equals(model.getType()))
					return BaseIcon.REPORT_JASPER.toImageResource();
				
				if(BirtReportDtoDec.class.getName().equals(model.getType()))
					return BaseIcon.REPORT_BIRT.toImageResource();
				if(BirtReportVariantDtoDec.class.getName().equals(model.getType()))
					return BaseIcon.REPORT_BIRT.toImageResource();
				
				if(CrystalReportDtoDec.class.getName().equals(model.getType()))
					return BaseIcon.REPORT_CRYSTAL.toImageResource();
				if(CrystalReportVariantDtoDec.class.getName().equals(model.getType()))
					return BaseIcon.REPORT_CRYSTAL.toImageResource();
				
				if(GridEditorReportDtoDec.class.getName().equals(model.getType()))
					return BaseIcon.REPORT_GE.toImageResource();
				if(GridEditorReportVariantDtoDec.class.getName().equals(model.getType()))
					return BaseIcon.REPORT_GE.toImageResource();
				
				if(JxlsReportDtoDec.class.getName().equals(model.getType()))
					return BaseIcon.REPORT_JXLS.toImageResource();
				if(JxlsReportVariantDtoDec.class.getName().equals(model.getType()))
					return BaseIcon.REPORT_JXLS.toImageResource();
				
				if(ScriptReportDtoDec.class.getName().equals(model.getType()))
					return BaseIcon.SCRIPT.toImageResource();
				if(ScriptReportVariantDtoDec.class.getName().equals(model.getType()))
					return BaseIcon.SCRIPT.toImageResource();
				
				if (SaikuReportDtoDec.class.getName().equals(model.getType()))
					return BaseIcon.CUBE.toImageResource();
				if (SaikuReportVariantDtoDec.class.getName().equals(model.getType()))
					return BaseIcon.FILE.toImageResource();
				
				if(ReportFolderDto.class.getName().equals(model.getType()))
					return BaseIcon.FOLDER_O.toImageResource();
				return null;
			}
		});
	}
}
