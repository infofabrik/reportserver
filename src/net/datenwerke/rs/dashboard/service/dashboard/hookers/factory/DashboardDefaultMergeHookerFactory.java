package net.datenwerke.rs.dashboard.service.dashboard.hookers.factory;

import net.datenwerke.rs.dashboard.service.dashboard.entities.AbstractDashboardManagerNode;
import net.datenwerke.rs.dashboard.service.dashboard.hookers.DashboardDefaultMergeHooker;

public interface DashboardDefaultMergeHookerFactory {
   
   DashboardDefaultMergeHooker<? extends AbstractDashboardManagerNode> create(Class<? extends AbstractDashboardManagerNode> targetClass);

}
