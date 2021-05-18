package net.datenwerke.rs.dashboard.client.dashboard.ui;

import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardDto;

public interface DadgetCatalogFactory {

   public DadgetCatalog create(DashboardDto dashboard, DashboardContainer container);
}
