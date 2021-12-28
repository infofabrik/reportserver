package net.datenwerke.rs.enterprise.client;

public interface EnterpriseUiService {

   boolean isCommunity();

   boolean isEnterprise();

   boolean isEvaluation();

   boolean isEnterpriseJarAvailable();
}
