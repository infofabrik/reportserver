package net.datenwerke.rs.license.client.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface LicenseMessages extends Messages{

	public final LicenseMessages INSTANCE = GWT.create(LicenseMessages.class);
	
	public String viewNavigationTitle();
	
	public String dialogTitle();

	public String installationDateLabel();
	public String serverIdLabel();

	public String informationPanelHeader();

	public String currentLicenseLabel();

	public String licenseExpirationDate();

	public String noExpirationDateMsg();

	public String purchaseEnterpriseLabel();
	public String purchaseEnterpriseText();

	public String updateLicenseInfoBtnLabel();
	public String updateLicenseInfoFieldLabel();

	public String licenseeLabel();

	public String licenseExpiredLabel();

	public String upgradesAvailableUntilLabel();

	public String versionLabel();

	public String permissionModuleDescription();

}
