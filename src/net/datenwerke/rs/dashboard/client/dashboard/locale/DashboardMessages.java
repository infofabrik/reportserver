package net.datenwerke.rs.dashboard.client.dashboard.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface DashboardMessages extends Messages {

	public static final DashboardMessages INSTANCE = GWT.create(DashboardMessages.class);

	String clientModuleName();
	
	String graphDadgetTitle();
	String graphDadgetDescription();

	String addDadget();

	String tools();

	String createDashboard();
	String removeDashboard();
	String editDashboard();

	String removeDashboardConfirmTitle();
	String removeDashboardConfirmMsg();

	String nameLabel();
	String descriptionLabel();
	String layoutLabel();

	String singleColLayout();
	String twoColLayut();
	String twoColLayout1to2();
	String towColLayout2to1();
	String threeColLayout();

	String singleColLayoutDesc();
	String twoColLayoutDesc();
	String twoColLayout1to2Desc();
	String twoColLayout2to1Desc();
	String threeColLayoutDesc();

	String urlDadgetTitle();
	String urlDadgetDescription();

	String reportDadgetTitle();
	String reportDadgetDescription();

	String urlDadgetHeader(String url);

	String removeDadgetConfirmTitle();
	String removeDadgetConfirmMsg();

	String configDadgetTitle();

	String urlLabel();

	String reportSelectionLocation();

	String reportTeamspaceLocation();
	String reportAdminLocation();

	String selectReportLabel();

	String selectTeamSpace();

	String reportDadgetFormatPreview();
	String reportDadgetFormatHtml();
	String reportDadgetFormatFull();

	String reportDadgetFormat();

	String reportSelection();

	String favoriteListTitle();
	String favoriteListDescription();

	String addToFavorites();

	String staticHtmlDadgetTitle();
	String staticHtmlDadgetDescription();

	String staticHtmlDadgetHtmlLabel();
	String staticHtmlDadgetTitleLabel();

	String securityTargetDescription();

	String mainPanelHeading();

	String dadget();

	String insert();

	String adminLabel();

	String editFolder();

	String dashboardAdminRightsLabel();

	String editDadgetNode();

	String libraryDadgetTitle();
	String libraryDadgetDescription();

	String editDashboardNode();

	String dashboard();

	String createReferenceDashboard();

	String editDashboardOrder();

	String reportDadgetFormatImage();

	String reloadIntervalLabel();

	String singlePageLabel();

	String heightLabel();

	String parameterDescription();

	String parameterTitle();

	String resetReferenceDashboard();

	String editParameterBtnLabel();

	String storeDashboardPromptTitle();

	String storeToConfigure();

	String defaultDashboardLabel();

	String importConfigFailureNoParent();
	String importerName();
	String importMainPropertiesDescription();
	String importMainPropertiesHeadline();
	String importWhereTo();

	String showExecuteIcon();

}