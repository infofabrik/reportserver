package net.datenwerke.rs.saiku.service.hooker;

import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.hooks.VariantCreatorHook;
import net.datenwerke.rs.saiku.service.datasource.MondrianDatasource;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport;

public class VariantCreatedAdjustSaikuQueryHooker implements VariantCreatorHook {

	@Override
	public void newVariantCreated(Report referenceReport, Report adjustedReport, Report variant) {

	}

	@Override
	public void temporaryVariantCreated(Report referenceReport, Report adjustedReport, Report variant) {

		if (referenceReport instanceof SaikuReport && adjustedReport instanceof SaikuReport
				&& variant instanceof SaikuReport) {
			if (((SaikuReport) adjustedReport).getQueryXml() != null
					&& !"".contentEquals(((SaikuReport) adjustedReport).getQueryXml().trim())) {
				((SaikuReport) variant).setQueryXml(((SaikuReport) adjustedReport).getQueryXml());
				((SaikuReport) variant).setHideParents(((SaikuReport) adjustedReport).isHideParents());
				((SaikuReport) variant).setAllowMdx(((SaikuReport) adjustedReport).isAllowMdx());
			} else {
				((SaikuReport) variant).setQueryXml(((SaikuReport) referenceReport).getQueryXml());
				((SaikuReport) variant).setHideParents(((SaikuReport) referenceReport).isHideParents());
				((SaikuReport) variant).setAllowMdx(((SaikuReport) referenceReport).isAllowMdx());
			}
		} else if (referenceReport instanceof TableReport && adjustedReport instanceof TableReport
				&& variant instanceof TableReport) {

			if (((TableReport) variant).isCubeFlag()) {

				if (((TableReport) adjustedReport).getCubeXml() != null
						&& !"".contentEquals(((TableReport) adjustedReport).getCubeXml().trim())) {
					((TableReport) variant).setCubeXml(((TableReport) adjustedReport).getCubeXml());
					((TableReport) variant).setHideParents(((TableReport) adjustedReport).isHideParents());
					((TableReport) variant).setAllowMdx(((TableReport) adjustedReport).isAllowMdx());
				} else {
					((TableReport) variant).setCubeXml(((TableReport) referenceReport).getCubeXml());
					((TableReport) variant).setHideParents(((TableReport) referenceReport).isHideParents());
					((TableReport) variant).setAllowMdx(((TableReport) referenceReport).isAllowMdx());
				}

			}

		}

	}

}
