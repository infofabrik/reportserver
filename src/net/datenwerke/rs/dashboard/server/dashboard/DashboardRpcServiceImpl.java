package net.datenwerke.rs.dashboard.server.dashboard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.inject.Singleton;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import com.google.inject.persist.Transactional;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoadResultBean;

import net.datenwerke.gf.client.history.HistoryLocation;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ViolatedSecurityExceptionDto;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.hooks.ParameterInstanceCreatedFromDtoHook;
import net.datenwerke.rs.dashboard.client.dashboard.dispatcher.DashboardInlineDispatcher;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardContainerDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardNodeDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardReferenceDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DashboardDtoDec;
import net.datenwerke.rs.dashboard.client.dashboard.rpc.DashboardRpcService;
import net.datenwerke.rs.dashboard.service.dashboard.DadgetService;
import net.datenwerke.rs.dashboard.service.dashboard.DashboardManagerService;
import net.datenwerke.rs.dashboard.service.dashboard.DashboardService;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.FavoriteList;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.FavoriteListEntry;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.LibraryDadget;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.ReportDadget;
import net.datenwerke.rs.dashboard.service.dashboard.entities.Dadget;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DadgetNode;
import net.datenwerke.rs.dashboard.service.dashboard.entities.Dashboard;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardContainer;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardNode;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardReference;
import net.datenwerke.rs.dashboard.service.dashboard.entities.LayoutType;
import net.datenwerke.rs.dashboard.service.dashboard.genrights.DashboardViewSecurityTarget;
import net.datenwerke.rs.teamspace.service.teamspace.TeamSpaceService;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.TsDiskService;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.AbstractTsDiskNode;
import net.datenwerke.rs.utils.entitycloner.EntityClonerService;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.annotation.ArgumentVerification;
import net.datenwerke.security.service.security.annotation.GenericTargetVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.exceptions.ViolatedSecurityException;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.Write;
import net.datenwerke.security.service.usermanager.entities.User;

@Singleton
public class DashboardRpcServiceImpl extends SecuredRemoteServiceServlet implements DashboardRpcService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2357811151987662453L;


	private final Provider<AuthenticatorService> authenticatorProvider;
	private final DashboardService dashboardService;
	private final DashboardManagerService dashboardManagerService;
	private final DtoService dtoService;
	private final DadgetService dadgetService;
	private final TeamSpaceService teamSpaceService;
	private final Provider<SecurityService> securityServiceProvider;
	private final Injector injector;
	private final EntityClonerService entityCloner;
	private final HookHandlerService hookHandler;
	private final TsDiskService diskService;

	@Inject
	public DashboardRpcServiceImpl(
			Provider<AuthenticatorService> authenticatorProvider,
			DashboardService dashboardService,
			DashboardManagerService dashboardManagerService,
			DadgetService dadgetService,
			DtoService dtoService,
			TeamSpaceService teamSpaceService,
			TsDiskService diskService,
			Provider<SecurityService> securityServiceProvider,
			EntityClonerService entityCloner,
			HookHandlerService hookHandler,
			Injector injector
			){
		this.authenticatorProvider = authenticatorProvider;
		this.dashboardService = dashboardService;
		this.dashboardManagerService = dashboardManagerService;
		this.dadgetService = dadgetService;
		this.dtoService = dtoService;
		this.teamSpaceService = teamSpaceService;
		this.diskService = diskService;
		this.securityServiceProvider = securityServiceProvider;
		this.entityCloner = entityCloner;
		this.hookHandler = hookHandler;
		this.injector = injector;
		
	}

	@SecurityChecked(
			genericTargetVerification = {
				@GenericTargetVerification(
					target=DashboardViewSecurityTarget.class,
					verify=@RightsVerification(rights=Read.class)
				)
			}
		)
	@Override
	@Transactional(rollbackOn=Exception.class)
	public DashboardContainerDto getDashboardForUser() throws ServerCallFailedException{
		User user = authenticatorProvider.get().getCurrentUser();
		if(null == user)
			return null;

		return (DashboardContainerDto) dtoService.createDto(dashboardService.getDashboardFor(user));
	}
	
	@Override
	@Transactional(rollbackOn={Exception.class})
	public DashboardDto getExplicitPrimaryDashboard() throws ServerCallFailedException {
		User user = authenticatorProvider.get().getCurrentUser();
		
		Dashboard dashboard = dashboardService.getExplicitPrimaryDashboard(user);
		
		return (DashboardDto) dtoService.createDto(dashboard);
	}

	@SecurityChecked(
			genericTargetVerification = {
				@GenericTargetVerification(
					target=DashboardViewSecurityTarget.class,
					verify=@RightsVerification(rights=Read.class)
				)
			}
		)
	@Override
	public DashboardDto reloadDashboard(DashboardDto dashboard) {
		return (DashboardDto) dtoService.createDto(dashboardService.getDashboardById(dashboard.getId()));
	}

	@SecurityChecked(
			genericTargetVerification = {
				@GenericTargetVerification(
					target=DashboardViewSecurityTarget.class,
					verify=@RightsVerification(rights=Write.class)
				)
			}
		)
	@Override
	@Transactional(rollbackOn=Exception.class)
	public DashboardDto createDashboardForUser() throws ServerCallFailedException{
		User user = authenticatorProvider.get().getCurrentUser();
		if(null == user)
			return null;

		DashboardContainer container = dashboardService.getDashboardFor(user);
		Dashboard dashboard = new Dashboard();
		dashboard.setName("Default");
		dashboardService.persist(dashboard);

		container.addDashboard(dashboard);

		dashboardService.merge(container);

		return (DashboardDto) dtoService.createDto(dashboard);
	}

	@SecurityChecked(
			genericTargetVerification = {
				@GenericTargetVerification(
					target=DashboardViewSecurityTarget.class,
					verify=@RightsVerification(rights=Write.class)
				)
			}
		)
	@Override
	@Transactional(rollbackOn=Exception.class)
	public void editDashboards(ArrayList<DashboardDto> dashboards)
			throws ServerCallFailedException {
		for(DashboardDto dashboard : dashboards)
			editDashboard(dashboard);
	}
	
	@Override
	@Transactional(rollbackOn=Exception.class)
	public void changeDashboardOrder(ArrayList<Long> dashboardIds) throws ServerCallFailedException {
		User user = authenticatorProvider.get().getCurrentUser();
		DashboardContainer container = dashboardService.getDashboardFor(user);
		HashMap<Long, Dashboard> tmp = new HashMap<>();
		
		for(Dashboard d : container.getDashboards()){
			tmp.put(d.getId(), d);
		}
		
		container.getDashboards().clear();
		
		int n = 0;
		for(Long id : dashboardIds){
			Dashboard d = tmp.remove(id);
			if(null != d){
				d.setN(n++);
				container.getDashboards().add(d);
			}
		}
		
		for(Long id : tmp.keySet()){
			Dashboard d = tmp.get(id);
			if(null != d){
				d.setN(n++);
				container.getDashboards().add(d);
			}
		}
		
		dashboardService.merge(container);
	}

	@Override
	@Transactional(rollbackOn=Exception.class)
	public void removeDashboard(DashboardDto dashboardDto) throws ServerCallFailedException{
		Dashboard dashboard = (Dashboard) dtoService.loadPoso(dashboardDto);
		DashboardContainer container = dashboardService.getDashboardContainerFor(dashboard);
		checkAccess(container);

		if(container.removeDashboard(dashboard)){
			dashboardService.remove(dashboard);
			dashboardService.merge(container);
		}
	}

	@SecurityChecked(
			argumentVerification = {
					@ArgumentVerification(
							name = "node",
							isDto = true,
							verify = @RightsVerification(rights=Read.class)
							)
			}
			)
	@Override
	@Transactional(rollbackOn=Exception.class)
	public DashboardDto importReferencedDashboardForUser(@Named("node")DashboardNodeDto dashboardNodeDto) throws ServerCallFailedException {
		DashboardNode node = (DashboardNode) dtoService.loadPoso(dashboardNodeDto);

		User user = authenticatorProvider.get().getCurrentUser();
		if(null == user)
			return null;

		DashboardContainer container = dashboardService.getDashboardFor(user);
		DashboardReference dashboardReference = new DashboardReference();
		dashboardReference.setDashboardNode(node);
		
		/* copy dashboard */
		resetReferenceDashboard(dashboardReference, node);
		
		dashboardService.persist(dashboardReference);

		container.addDashboard(dashboardReference);

		dashboardService.merge(container);

		return (DashboardDto) dtoService.createDto(dashboardReference);
	}
	
	@Override
	@Transactional(rollbackOn=Exception.class)
	public DashboardDto resetReferencedDashboard(DashboardReferenceDto dashboardDto){
		DashboardReference dashboardReference = (DashboardReference) dtoService.loadPoso(dashboardDto);
		DashboardNode node = dashboardReference.getDashboardNode();
		
		if(null == node)
			throw new IllegalStateException("Corresponding dashboard node not found.");
		
		User user = authenticatorProvider.get().getCurrentUser();
		if(null == user)
			return null;
		
		DashboardContainer container = dashboardService.getDashboardContainerFor(dashboardReference);
		checkAccess(container);
		
		if(! securityServiceProvider.get().checkRights(node, Read.class))
			throw new IllegalArgumentException("Insufficient rights to read dashboard");
		
		for(Dadget d : new ArrayList<>(dashboardReference.getDadgets())){
			dashboardReference.removeDadget(d);
			dashboardService.remove(d);
		}
		
		resetReferenceDashboard(dashboardReference, node);
		
		Dashboard dashboard = dashboardService.merge(dashboardReference);	
		
		return (DashboardDto) dtoService.createDto(dashboard);
	}
	
	@Override
	@Transactional(rollbackOn=Exception.class)
	public DashboardDto loadDashboardForDisplay(DashboardDto dashboardDto) {
		Dashboard dashboard = (Dashboard) dtoService.loadPoso(dashboardDto);
		DashboardContainer container = dashboardService.getDashboardContainerFor(dashboard);
		checkAccess(container);
		
		if(dashboard instanceof DashboardReference && dashboard.getDadgets().isEmpty()){
			DashboardNode node = ((DashboardReference)dashboard).getDashboardNode();
			
			if(securityServiceProvider.get().checkRights(node, Read.class)){
				resetReferenceDashboard((DashboardReference) dashboard, node);
				dashboardService.merge(dashboard);
			}
		}
		
		return (DashboardDto) dtoService.createDto(dashboard);
	}
	
	@Override
	public DashboardDto loadDashboardForDisplayFrom(HistoryLocation location) {
		Dashboard dashboard = null;
		if(location.hasParameter(DashboardInlineDispatcher.DASHBOARD_ID)){
			Long id = Long.parseLong(location.getParameterValue(DashboardInlineDispatcher.DASHBOARD_ID));
			DashboardNode node = (DashboardNode) dashboardManagerService.getNodeById(id);
			
			if(! securityServiceProvider.get().checkRights(node, Read.class))
				throw new ViolatedSecurityException("Insufficient rights to load dashboard");
			
			dashboard = new DashboardReference();
			resetReferenceDashboard((DashboardReference) dashboard, node);
		} else {
			User user = authenticatorProvider.get().getCurrentUser();
			if(null != user) {
				DashboardContainer container = dashboardService.getDashboardFor(user);
				if(container.getDashboards().isEmpty())
					dashboard = new Dashboard();
				else {
					dashboard = container.getDashboards().get(0);
					
					if(location.hasParameter(DashboardInlineDispatcher.DASHBOARD_NR) || location.hasParameter(DashboardInlineDispatcher.DASHBOARD_NR_OFFSET)){
						try{
							int nr = 0;
							if(location.hasParameter(DashboardInlineDispatcher.DASHBOARD_NR))
								nr = Integer.parseInt(location.getParameterValue(DashboardInlineDispatcher.DASHBOARD_NR));
							if(location.hasParameter(DashboardInlineDispatcher.DASHBOARD_NR_OFFSET))
								nr += Integer.parseInt(location.getParameterValue(DashboardInlineDispatcher.DASHBOARD_NR_OFFSET));
							dashboard = container.getDashboards().get((nr-1 )% container.getDashboards().size());
						} catch(Exception e){}
					}
				}
			}
		}
		
		if(null == dashboard)
			return null;

		LinkedHashMap<String, ParameterInstance> dashboardParameterMap = new LinkedHashMap<String, ParameterInstance>();
		for(Dadget dadget : dashboard.getDadgets())
			addParameterInstancesFor(dashboardParameterMap, dadget);
		
		for(String name : location.getParameterNames()){
			if(name.startsWith("p_") && name.length() > 2){ //$NON-NLS-1$
				String parameterName = name.substring(2);
				
				/* search dadgets */
				for(Dadget dadget : dashboard.getDadgets())
					adaptParameterInstances(dadget.getParameterInstances(), parameterName, location.getParameterValue(name));
				
				/* adapt parameter map */
				adaptParameterInstances(dashboardParameterMap.values(), parameterName, location.getParameterValue(name));
			}
		}
		
		/* set map */
		DashboardDtoDec dashboardDto = (DashboardDtoDec) dtoService.createDto(dashboard);
		dashboardDto.setTemporaryInstanceMap(toDto(dashboardParameterMap));
		
		return dashboardDto;
	}
	
	private void adaptParameterInstances(Collection<ParameterInstance> parameterInstances, String parameterName,
			String parameterValue) {
		for(ParameterInstance instance : parameterInstances){
			injector.injectMembers(instance);
			
			ParameterDefinition definition = instance.getDefinition();
			if(parameterName.equals(definition.getKey()) && definition.isEditable())
				instance.parseStringValue(parameterValue);
		}
	}

	protected void resetReferenceDashboard(DashboardReference dashboardReference, DashboardNode node){
		/* copy dashboard */
		dashboardReference.setName(node.getName());
		dashboardReference.setDescription(node.getDescription());
		dashboardReference.setLayout(node.getDashboard().getLayout());
		dashboardReference.setSinglePage(node.getDashboard().isSinglePage());
		
		for(Dadget d : node.getDashboard().getDadgets()){
			Dadget dClone = entityCloner.cloneEntity(d);
			dashboardService.persist(dClone);
			dashboardReference.addDadgetPlain(dClone);
		}
	}

	@SecurityChecked(
			genericTargetVerification = {
				@GenericTargetVerification(
					target=DashboardViewSecurityTarget.class,
					verify=@RightsVerification(rights=Write.class)
				)
			}
		)
	@Override
	@Transactional(rollbackOn=Exception.class)
	public DashboardDto editDashboard(DashboardDto dashboardDto) throws ServerCallFailedException {
		Dashboard dashboard = (Dashboard) dtoService.loadPoso(dashboardDto);

		DashboardContainer container = dashboardService.getDashboardContainerFor(dashboard);
		checkAccess(container);

		if(! container.contains(dashboard))
			throw new ViolatedSecurityException();

		dashboard.setN(dashboardDto.getN());

		dashboard.setName(dashboardDto.getName());
		dashboard.setDescription(dashboardDto.getDescription());
		dashboard.setReloadInterval(dashboardDto.getReloadInterval());
		dashboard.setSinglePage(dashboardDto.isSinglePage());
		
		LayoutType oldL = dashboard.getLayout();
		dashboard.setLayout((LayoutType) dtoService.loadPoso(dashboardDto.getLayout()));
		if(oldL.compareTo(dashboard.getLayout()) > 0){
			switch (dashboard.getLayout()) {
			case TWO_COLUMN_EQUI:
			case TWO_COLUMN_LEFT_LARGE:
			case TWO_COLUMN_RIGHT_LARGE:
				for(Dadget d : dashboard.getDadgetsInColumn(3)){
					d.setCol(2);
					dashboardService.merge(d);
				}
				break;
			case ONE_COLUMN:
				for(Dadget d : dashboard.getDadgets()){
					d.setCol(1);
					dashboardService.merge(d);
				}
				break;
			}
		}

		dashboard = dashboardService.merge(dashboard);

		return (DashboardDto) dtoService.createDto(dashboard);	
	}

	@SecurityChecked(
			genericTargetVerification = {
				@GenericTargetVerification(
					target=DashboardViewSecurityTarget.class,
					verify=@RightsVerification(rights=Write.class)
				)
			}
		)
	@Override
	@Transactional(rollbackOn=Exception.class)
	public DashboardDto addDadgetToDashboard(DashboardDto dashboardDto,
			DadgetDto dadgetDto) throws ServerCallFailedException {
		Dashboard dashboard = (Dashboard) dtoService.loadPoso(dashboardDto);
		DashboardContainer container = dashboardService.getDashboardContainerFor(dashboard);
		checkAccess(container);

		if(! container.contains(dashboard))
			throw new ViolatedSecurityException();

		Dadget dadget = (Dadget) dtoService.createPoso(dadgetDto);
		dadget.init();
		dashboardService.persist(dadget);

		dashboard.addDadget(dadget);
		dashboard = dashboardService.merge(dashboard);

		return (DashboardDto) dtoService.createDto(dashboard);	
	}

	@SecurityChecked(
			genericTargetVerification = {
				@GenericTargetVerification(
					target=DashboardViewSecurityTarget.class,
					verify=@RightsVerification(rights=Write.class)
				)
			}
		)
	@Override
	@Transactional(rollbackOn=Exception.class)
	public DashboardDto removeDadgetFromDashboard(DashboardDto dashboardDto,
			DadgetDto dadgetDto) throws ServerCallFailedException {
		Dashboard dashboard = (Dashboard) dtoService.loadPoso(dashboardDto);
		DashboardContainer container = dashboardService.getDashboardContainerFor(dashboard);
		checkAccess(container);

		if(! container.contains(dashboard))
			throw new ViolatedSecurityException();

		Dadget dadget = (Dadget) dtoService.loadPoso(dadgetDto);
		if(! dashboard.contains(dadget))
			throw new ViolatedSecurityException();

		if(null != dadget && dashboard.removeDadget(dadget)){
			dashboardService.remove(dadget);
			dashboard = dashboardService.merge(dashboard);	
		}

		return (DashboardDto) dtoService.createDto(dashboard);	
	}

	@SecurityChecked(
			genericTargetVerification = {
				@GenericTargetVerification(
					target=DashboardViewSecurityTarget.class,
					verify=@RightsVerification(rights=Write.class)
				)
			}
		)
	@Override
	@Transactional(rollbackOn=Exception.class)
	public DashboardDto editDadgetToDashboard(DashboardDto dashboardDto, DadgetDto dadgetDto)
			throws ServerCallFailedException {
		Dashboard dashboard = (Dashboard) dtoService.loadPoso(dashboardDto);
		DashboardContainer container = dashboardService.getDashboardContainerFor(dashboard); 
		checkAccess(container);

		if(! container.contains(dashboard))
			throw new ViolatedSecurityException();

		Dadget dadget = (Dadget) dtoService.loadPoso(dadgetDto);

		int oldN = dadget.getN();
		int oldC = dadget.getCol();
		dadget = (Dadget) dtoService.loadAndMergePoso(dadgetDto);
		if(oldN != dadget.getN() || oldC != dadget.getCol()){
			List<Dadget> dadgetsInCol = dashboard.getDadgetsInColumn(dadget.getCol());

			dadgetsInCol.remove(dadget);
			if(dadget.getN() > dadgetsInCol.size() || dadget.getN() < 0)
				dadgetsInCol.add(dadget);
			else
				dadgetsInCol.add(dadget.getN(), dadget);
			int c = 0;
			for(Dadget d : dadgetsInCol){
				d.setN(c++);
				dashboardService.merge(d);
			}

			/* sort */
			Collections.sort(dashboard.getDadgets(), new Comparator<Dadget>() {
				@Override
				public int compare(Dadget o1, Dadget o2) {
					return ((Integer)o1.getN()).compareTo(o2.getN());
				}
			});
			dashboardService.merge(dashboard);
		}

		dashboardService.merge(dadget);

		DashboardDto dash = (DashboardDto) dtoService.createDto(dashboard);
		return dash;
	}

	protected void checkAccess(DashboardContainer container) {
		securityServiceProvider.get().assertUserLoggedIn();

		DashboardContainer refcontainer = dashboardService.getDashboardFor(authenticatorProvider.get().getCurrentUser());
		if(null == refcontainer || ! refcontainer.equals(container))
			throw new ViolatedSecurityException();
	}
	
	protected void checkAccess(Dashboard dashboard, DashboardContainer container) {
		if(null != container)
			checkAccess(container);
		else {
			DashboardNode node = dashboardService.getNodeFor(dashboard);
			if(! securityServiceProvider.get().checkRights(node, Write.class))
				throw new ViolatedSecurityException("Insufficient rights to change dashboard");
		}
	}


	@Override
	@Transactional(rollbackOn=Exception.class)
	public void addToFavorites(AbstractTsDiskNodeDto nodeDto)
			throws ServerCallFailedException {
		AbstractTsDiskNode node = (AbstractTsDiskNode) dtoService.loadPoso(nodeDto);
		if(null == node)
			throw new ServerCallFailedException("could not load reference");

		TeamSpace ts = diskService.getTeamSpaceFor(node);
		teamSpaceService.assertAccess(ts);

		FavoriteList list = dadgetService.loadFavoriteList();

		if(! list.containsEntry(node)){
			FavoriteListEntry entry = new FavoriteListEntry();
			entry.setReferenceEntry(node);
			list.addEntry(entry);
		}

		dadgetService.merge(list);
	}

	@Override
	@Transactional(rollbackOn=Exception.class)
	public void removeFromFavorites(AbstractTsDiskNodeDto nodeDto)
			throws ServerCallFailedException {
		AbstractTsDiskNode node = (AbstractTsDiskNode) dtoService.loadPoso(nodeDto);
		if(null == node)
			throw new ServerCallFailedException("could not load reference");

		TeamSpace ts = diskService.getTeamSpaceFor(node);
		teamSpaceService.assertAccess(ts);

		FavoriteList list = dadgetService.loadFavoriteList();

		if(list.containsEntry(node)){
			FavoriteListEntry entry = list.getEntry(node);
			dadgetService.remove(list, entry);
			dadgetService.merge(list);
		}
	}


	@Override
	@Transactional(rollbackOn=Exception.class)
	public FavoriteListDto loadFavorites() throws ServerCallFailedException {
		FavoriteList list = dadgetService.loadFavoriteList();
		return (FavoriteListDto) dtoService.createDto(list);
	}

	@Override
	public Map<String, ParameterInstanceDto> getDashboardParameterInstances(DashboardDto dashboardDto) throws ServerCallFailedException {
		Dashboard dashboard = (Dashboard) dtoService.loadPoso(dashboardDto);
		
		/* security */
		DashboardContainer container = dashboardService.getDashboardContainerFor(dashboard);
		checkAccess(dashboard, container);
		
		HashMap<String, ParameterInstance> res = new LinkedHashMap<String, ParameterInstance>();
		for(Dadget dadget : dashboard.getDadgets())
			addParameterInstancesFor(res, dadget);
		
		return toDto(res);
	}

	@Override
	public Map<String, ParameterInstanceDto> getDadgetParameterInstances(DadgetDto dadgetDto) throws ServerCallFailedException {
		Dadget dadget = (Dadget) dtoService.loadPoso(dadgetDto);

		/* security */
		Dashboard dashboard = dashboardService.getDashboardFor(dadget);
		DashboardContainer container = dashboardService.getDashboardContainerFor(dashboard);
		checkAccess(dashboard, container);
		
		LinkedHashMap<String, ParameterInstance> res = new LinkedHashMap<String, ParameterInstance>();
		if(null == dadget){
			return new HashMap<>();
		}
		
		addParameterInstancesFor(res, dadget);

		return toDto(res);
	}
	
	private void addParameterInstancesFor(HashMap<String, ParameterInstance> res, Dadget dadget) {
       if(dadget instanceof ReportDadget){
          Report report = ((ReportDadget) dadget).getReport();
          if (null == report)
             report = ((ReportDadget) dadget).getReportReference().getReport();
             
           res.putAll(getParameterInstancesFor(report, dadget));
       } else if(dadget instanceof LibraryDadget){
           DadgetNode dadgetNode = ((LibraryDadget)dadget).getDadgetNode();
           if(null != dadgetNode){
               Dadget referencedDadget = dadgetNode.getDadget();
               if(referencedDadget instanceof ReportDadget){
                  Report report = ((ReportDadget) referencedDadget).getReport();
                  if (null == report)
                     report = ((ReportDadget) referencedDadget).getReportReference().getReport();
                   res.putAll(getParameterInstancesFor(report, dadget));
               }
           }
       }
   }

	private LinkedHashMap<String, ParameterInstanceDto> toDto(HashMap<String, ParameterInstance> res) {
		LinkedHashMap<String, ParameterInstanceDto> dtoMap = new LinkedHashMap<>();
		for(Entry<String, ParameterInstance> e : res.entrySet())
			dtoMap.put(e.getKey(), (ParameterInstanceDto) dtoService.createDto(e.getValue(), DtoView.ALL, DtoView.ALL));
		return dtoMap;
	}
	
	private HashMap<String, ParameterInstance> getParameterInstancesFor(Report report, Dadget dadget) {
		HashMap<String, ParameterInstance> res = new LinkedHashMap<>();
		if(null != report){
			/* loop over definition to get order right */
			for(ParameterDefinition definition: report.getParameterDefinitions()){
				ParameterInstance instance = report.getParameterInstanceFor(definition);
				res.put(definition.getKey(), instance);
				
				/* ensure that dadget has parameter */
				boolean found = false;
				for(ParameterInstance dadgetInstance:  dadget.getParameterInstances()){
					if(definition.equals(dadgetInstance.getDefinition())){
						found = true;
						break;
					}
				}
				if(! found){
					ParameterInstance newInstance = definition.createParameterInstance();
					dadget.getParameterInstances().add(newInstance);
				}
					
			} 
			for(ParameterInstance instance:  dadget.getParameterInstances())
				res.put(instance.getDefinition().getKey(), instance);
		}
		return res;
	}

	@Override
	@Transactional(rollbackOn=Exception.class)
	public List<DadgetDto> setDashboardParameterInstances(DashboardDto dashboardDto, Set<ParameterInstanceDto> parameterInstances) throws ServerCallFailedException {
		ArrayList<DadgetDto> res = new ArrayList<>();
		Dashboard dashboard = (Dashboard) dtoService.loadPoso(dashboardDto);
		
		/* security */
		DashboardContainer container = dashboardService.getDashboardContainerFor(dashboard);
		checkAccess(dashboard, container);
		
		for(Dadget dadget : dashboard.getDadgets()){
			Report report = null;
			if(dadget instanceof ReportDadget) {
				report = ((ReportDadget) dadget).getReport();
				if (null == report)
				   report =  ((ReportDadget) dadget).getReportReference().getReport();
			} else if(dadget instanceof LibraryDadget){
				DadgetNode dadgetNode = ((LibraryDadget)dadget).getDadgetNode();
				if(null == dadgetNode || ! (dadgetNode.getDadget() instanceof ReportDadget))
					continue;
				
				report = ((ReportDadget)dadgetNode.getDadget()).getReport();
				if (null == report)
				   report = ((ReportDadget)dadgetNode.getDadget()).getReportReference().getReport();
			} 	
			
			if(null != report){
				Set<ParameterInstance> instancesForReport = getInstancesForReport(report, parameterInstances);
				dadget.getParameterInstances().clear();
				dadget.getParameterInstances().addAll(instancesForReport);

				if(!instancesForReport.isEmpty()){
					dashboardService.merge(dadget);
					res.add((DadgetDto) dtoService.createDto(dadget));
				}
			}

		}
		return res;
	}

	@Override
	@Transactional(rollbackOn=Exception.class)
	public DadgetDto setDadgetParameterInstances(DadgetDto dadgetDto, Set<ParameterInstanceDto> parameterInstances) throws ServerCallFailedException {
		Dadget dadget= (Dadget) dtoService.loadPoso(dadgetDto);

		/* security */
		Dashboard dashboard = dashboardService.getDashboardFor(dadget);
		DashboardContainer container = dashboardService.getDashboardContainerFor(dashboard);
		checkAccess(dashboard, container);
		
		Report report = null;
		if(dadget instanceof ReportDadget) {
			report = ((ReportDadget) dadget).getReport();
			if (null == report)
			   report = ((ReportDadget) dadget).getReportReference().getReport();
		} else if(dadget instanceof LibraryDadget){
			DadgetNode dadgetNode = ((LibraryDadget)dadget).getDadgetNode();
			if(null != dadgetNode && (dadgetNode.getDadget() instanceof ReportDadget)) {
				report = ((ReportDadget)dadgetNode.getDadget()).getReport();
				if (null == report)
				   report = ((ReportDadget)dadgetNode.getDadget()).getReportReference().getReport();
			}
		}
		
		if(null != report){
			Set<ParameterInstance> instancesForReport = getInstancesForReport(report, parameterInstances);
			dadget.getParameterInstances().clear();
			dadget.getParameterInstances().addAll(instancesForReport);

			dashboardService.merge(dadget);
			return ((DadgetDto) dtoService.createDto(dadget));
		}
		return dadgetDto;
	}

	private Set<ParameterInstance> getInstancesForReport(Report report, Set<ParameterInstanceDto> instances) throws ExpectedException{
		HashMap<String, ParameterDefinition> keys = new HashMap<>();
		HashSet<ParameterInstance> res = new HashSet<>();

		for(ParameterDefinition pd : report.getParameterDefinitions()){
			keys.put(pd.getKey(), pd);
		}

		for(ParameterInstanceDto pidto : instances){
			if(keys.containsKey(pidto.getDefinition().getKey())){
				ParameterInstance pi = (ParameterInstance) dtoService.createUnmanagedPoso(pidto);
				
				/* postprocess parameter instances */
				for(ParameterInstanceCreatedFromDtoHook hook : hookHandler.getHookers(ParameterInstanceCreatedFromDtoHook.class)){
					if(hook.consumes(pidto)){
						hook.posoCreated(pidto, pi);
					}
				}
				
				pi.setDefinition(keys.get(pidto.getDefinition().getKey()));
				res.add(pi);
			}
		}

		return res;
	}

	@Transactional(rollbackOn=Exception.class)
	private void importDashboard(User user, DashboardNode node) {
		
		// Import
		DashboardContainer container = dashboardService.getDashboardFor(user);
		
		List<Long> dashboardIds = new ArrayList<>();
		
		for (Dashboard dashboard: container.getDashboards()) {
			dashboardIds.add(dashboard.getId());
		}
		
		DashboardReference dashboardReference = new DashboardReference();
		dashboardReference.setDashboardNode(node);
		
		/* copy dashboard */
		dashboardReference.setName(node.getName());
		dashboardReference.setDescription(node.getDescription());
		dashboardReference.setLayout(node.getDashboard().getLayout());
		dashboardReference.setSinglePage(node.getDashboard().isSinglePage());
		
		for(Dadget d : node.getDashboard().getDadgets()){
			Dadget dClone = entityCloner.cloneEntity(d);
			dashboardService.persist(dClone);
			dashboardReference.addDadgetPlain(dClone);
		}
		
		dashboardService.persist(dashboardReference);

		container.getDashboards().add(0, dashboardReference);
		//container.addDashboard(dashboardReference);

		dashboardService.merge(container);
		
		Long dashboardReferenceId = dashboardReference.getId();
		//userPropertiesService.setPropertyValue(user, "dashboard:primaryDashboard", dashboardReferenceId);
		
		// change order
		HashMap<Long, Dashboard> tmp = new HashMap<>();
		
		for(Dashboard d : container.getDashboards()){
			tmp.put(d.getId(), d);
		}
		
		container.getDashboards().clear();
		
		dashboardIds.add(0, dashboardReferenceId);
		int n = 0;
		for(Long id : dashboardIds){
			Dashboard d = tmp.remove(id);
			if(null != d){
				d.setN(n++);
				container.getDashboards().add(d);
			}
		}
		
		for(Long id : tmp.keySet()){
			Dashboard d = tmp.get(id);
			if(null != d){
				d.setN(n++);
				container.getDashboards().add(d);
			}
		}
		
		dashboardService.merge(container);

	}

	@Override
	@Transactional(rollbackOn={Exception.class})
	public ListLoadResult<DashboardDto> loadAllDashboards()
			throws ServerCallFailedException {
		Collection<Dashboard> dashboards =  dashboardService.getAllDashboards();
		
		List<DashboardDto> dtos = new ArrayList<DashboardDto>();
		for(Dashboard dashboard : dashboards)
			dtos.add((DashboardDto)dtoService.createListDto(dashboard));
			
		return new ListLoadResultBean<DashboardDto>(dtos);
	}

	@Override
	@Transactional(rollbackOn={Exception.class})
	public ListLoadResult<DashboardDto> loadDashboards()
			throws ServerCallFailedException {
		Collection<Dashboard> dashboards = dashboardService.getDashboards();
		
		List<DashboardDto> dtos = new ArrayList<DashboardDto>();
		for(Dashboard dashboard: dashboards)
			dtos.add((DashboardDto) dtoService.createDto(dashboard));
		
		return new ListLoadResultBean<DashboardDto>(dtos);
	}

	@Override
	@Transactional(rollbackOn={Exception.class})
	public void setPrimaryDashboard(DashboardDto dashboardDto) throws ServerCallFailedException {
		Dashboard dashboard = (Dashboard) dtoService.loadPoso(dashboardDto);
		User user = authenticatorProvider.get().getCurrentUser();
		if( null != dashboard && !dashboardService.isOwner(user, dashboard))
			throw new ViolatedSecurityExceptionDto("insufficient rights: tried to use a dashboard you have no access to as your primary dashboard");

		dashboardService.setPrimaryDashboard(dashboard);
	}
}