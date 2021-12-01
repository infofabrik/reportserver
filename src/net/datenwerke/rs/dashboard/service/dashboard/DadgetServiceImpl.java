package net.datenwerke.rs.dashboard.service.dashboard;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance__;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.FavoriteList;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.FavoriteListEntry;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.FavoriteListEntry__;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.FavoriteList__;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.LibraryDadget;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.LibraryDadget__;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.ReportDadget;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.ReportDadget__;
import net.datenwerke.rs.dashboard.service.dashboard.entities.Dadget;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DadgetNode;
import net.datenwerke.rs.dashboard.service.dashboard.entities.Dadget__;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardNode;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardReference;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardReference__;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.AbstractTsDiskNode;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskReportReference;
import net.datenwerke.rs.utils.simplequery.annotations.Join;
import net.datenwerke.rs.utils.simplequery.annotations.Predicate;
import net.datenwerke.rs.utils.simplequery.annotations.QueryByAttribute;
import net.datenwerke.rs.utils.simplequery.annotations.SimpleQuery;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.eventlogger.annotations.FireRemoveEntityEvents;
import net.datenwerke.security.service.usermanager.entities.User;

public class DadgetServiceImpl implements DadgetService {

	private Provider<EntityManager> entityManagerProvider;
	private final Provider<AuthenticatorService> authenticatorServiceProvider;

	@Inject
	public DadgetServiceImpl(
		Provider<AuthenticatorService> authenticatorServiceProvider,
		Provider<EntityManager> entityManagerProvider){
			
		this.authenticatorServiceProvider = authenticatorServiceProvider;
		this.entityManagerProvider = entityManagerProvider;
	}
	
	@Override
	public FavoriteList loadFavoriteList() {
		User currentUser = authenticatorServiceProvider.get().getCurrentUser();
		FavoriteList list = loadFavoriteList(currentUser);
		
		if(null == list){
			list = new FavoriteList();
			list.setUser(currentUser);
			
			entityManagerProvider.get().persist(list);
		}
		
		return list;
	}

	@Override
	@QueryByAttribute(where=FavoriteList__.user)
	public FavoriteList loadFavoriteList(User currentUser) {
		return null; // magic
	}
	
	@Override
	public void persist(FavoriteListEntry entry) {
		entityManagerProvider.get().persist(entry);
	}
	
	@Override
	public FavoriteList merge(FavoriteList list) {
		EntityManager em = entityManagerProvider.get();
		list = em.merge(list);
		return list;
	}
	
	@Override
	@FireRemoveEntityEvents
	public void remove(FavoriteList list) {
		if(null == list)
			return;
		EntityManager em = entityManagerProvider.get();
		list = em.find(FavoriteList.class, list.getId());
		
		if(null != list)
			em.remove(list);	
	}

	@Override
	public void remove(FavoriteList list, FavoriteListEntry entry) {
		if(null == entry)
			return;
		
		for (Iterator iterator = list.getReferenceEntries().iterator(); iterator.hasNext();) {
			FavoriteListEntry e = (FavoriteListEntry) iterator.next();
			if(e.equals(entry)){
				iterator.remove();
				break;
			}
		}
		
		entityManagerProvider.get().remove(entry);
	}

	@Override
	public void removeFromFavorites(AbstractTsDiskNode node) {
		Collection<FavoriteList> lists = getFavoriteListsWithFavorite(node);
		if(null == lists)
			return;
		
		for(FavoriteList list : lists){
			remove(list, list.getEntry(node));
			merge(list);
		}
	}

	@Override
	public void removeFromReportDadgets(Report report) {
		EntityManager em = entityManagerProvider.get();

		/* update report dagets */
		Collection<ReportDadget> dadgets = getReportDadgetsWith(report);
		if(null != dadgets){
			for(ReportDadget dadget : dadgets){
				dadget.setReport(null);
				dadget.getParameterInstances().clear();
				em.merge(dadget);
			}
		}
		
		/* check for parameters */
		if(! (report instanceof ReportVariant)){
			for(ParameterDefinition def : report.getParameterDefinitions()){
				Collection<Dadget> dadgetWithParamDef = getDadgetsWithParameterDefinition(def);
				
				for(Dadget dadget : dadgetWithParamDef){
					dadget.getParameterInstances().clear();
					em.merge(dadget);
				}
			}
		}
	}
	
	@Override
	public void removeFromReportDadgets(TsDiskReportReference reference) {
		Collection<ReportDadget> dadgets = getReportDadgetsWith(reference);
		if(null == dadgets)
			return;
		
		EntityManager em = entityManagerProvider.get();
		
		for(ReportDadget dadget : dadgets){
			dadget.setReportReference(null);
			em.merge(dadget);
		}
	}
	
	@SimpleQuery(from=Dadget.class,join=@Join(joinAttribute=Dadget__.parameterInstances,where=@Predicate(attribute=ParameterInstance__.definition,value="pd")))
	public Collection<Dadget> getDadgetsWithParameterDefinition(@Named("pd")ParameterDefinition pd) {
		return null; // magic
	}
	
	@Override
	@QueryByAttribute(where=ReportDadget__.report)
	public Collection<ReportDadget> getReportDadgetsWith(Report report) {
		return null; // magic
	}
	
	@Override
	@QueryByAttribute(where=ReportDadget__.reportReference)
	public Collection<ReportDadget> getReportDadgetsWith(TsDiskReportReference reference) {
		return null; // magic
	}

	@SimpleQuery(join=@Join(joinAttribute=FavoriteList__.referenceEntries,where=@Predicate(attribute=FavoriteListEntry__.referenceEntry, value="fav")))
	public List<FavoriteList> getFavoriteListsWithFavorite(@Named("fav")AbstractTsDiskNode node) {
		return null; // by magic
	}
	
	@Override
	@QueryByAttribute(where=LibraryDadget__.dadgetNode)
	public List<LibraryDadget> getLibraryDadgetsWith(DadgetNode node) {
		return null; //magic
	}
	
	@Override
	@QueryByAttribute(where=DashboardReference__.dashboardNode)
	public List<DashboardReference> getDashboardContainerssWith(DashboardNode node) {
		return null;
	}
}
