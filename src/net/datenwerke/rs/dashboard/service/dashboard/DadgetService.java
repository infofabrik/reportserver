package net.datenwerke.rs.dashboard.service.dashboard;

import java.util.Collection;
import java.util.List;

import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.FavoriteList;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.FavoriteListEntry;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.LibraryDadget;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.ReportDadget;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DadgetNode;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardNode;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardReference;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.AbstractTsDiskNode;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskReportReference;
import net.datenwerke.security.service.usermanager.entities.User;

public interface DadgetService {

	FavoriteList loadFavoriteList();

	void persist(FavoriteListEntry entry);

	FavoriteList merge(FavoriteList list);
	
	void remove(FavoriteList list);

	void remove(FavoriteList list, FavoriteListEntry entry);

	void removeFromFavorites(AbstractTsDiskNode node);

	void removeFromReportDadgets(Report node);

	List<LibraryDadget> getLibraryDadgetsWith(DadgetNode node);

	List<DashboardReference> getDashboardContainerssWith(DashboardNode node);

	void removeFromReportDadgets(TsDiskReportReference reference);

	Collection<ReportDadget> getReportDadgetsWith(Report report);

	Collection<ReportDadget> getReportDadgetsWith(
			TsDiskReportReference reference);
	
	FavoriteList loadFavoriteList(User currentUser);
}
