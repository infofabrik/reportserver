package net.datenwerke.rs.enterprise.client;

import java.io.Serializable;

public class EnterpriseInformationDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean enterprise;
	private boolean evaluation;
	private boolean enterpriseJarAvailable;
	
	public EnterpriseInformationDto() {
	}

	public boolean isEnterprise() {
		return enterprise;
	}

	public void setEnterprise(boolean enterprise) {
		this.enterprise = enterprise;
	}

	public boolean isEvaluation() {
		return evaluation;
	}

	public void setEvaluation(boolean evaluation) {
		this.evaluation = evaluation;
	}

	public boolean isEnterpriseJarAvailable() {
		return enterpriseJarAvailable;
	}

	public void setEnterpriseJarAvailable(boolean enterpriseJarAvailable) {
		this.enterpriseJarAvailable = enterpriseJarAvailable;
	}
	
	
}
