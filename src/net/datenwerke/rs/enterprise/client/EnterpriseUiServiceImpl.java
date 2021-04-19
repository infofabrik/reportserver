package net.datenwerke.rs.enterprise.client;

public class EnterpriseUiServiceImpl implements EnterpriseUiService {

	private boolean enterprise;
	private boolean evaluation;
	private boolean enterpriseJarAvailable;

	@Override
	public boolean isCommunity() {
		return ! isEnterprise();
	}
	
	@Override
	public boolean isEnterprise() {
		return enterprise;
	}

	void setEnterprise(boolean enterprise) {
		this.enterprise = enterprise;
	}
	
	@Override
	public boolean isEvaluation() {
		return evaluation;
	}
	
	void setEvaluation(boolean evaluation) {
		this.evaluation = evaluation;
	}
	
	@Override
	public boolean isEnterpriseJarAvailable() {
		return enterpriseJarAvailable;
	}
	
	void setEnterpriseJarAvailable(boolean enterpriseJarAvailable) {
		this.enterpriseJarAvailable = enterpriseJarAvailable;
	}
}
