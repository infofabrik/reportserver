package net.datenwerke.gf.client.dtoinfo;

import com.google.inject.Inject;
import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gf.client.history.dto.HistoryLinkDto;
import net.datenwerke.gf.client.juel.dto.JuelResultDto;
import net.datenwerke.gxtdto.client.dtoinfo.DtoInformationService;
import net.datenwerke.gxtdto.client.dtoinfo.DtoMainInformationService;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.rs.adminutils.client.logs.dto.ViewLogFileCommandResultExtensionDto;
import net.datenwerke.rs.amazons3.client.amazons3.dto.AmazonS3DatasinkDto;
import net.datenwerke.rs.base.client.datasources.dto.ArgumentDatasourceConnectorDto;
import net.datenwerke.rs.base.client.datasources.dto.CsvDatasourceConfigDto;
import net.datenwerke.rs.base.client.datasources.dto.CsvDatasourceDto;
import net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceConfigDto;
import net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceDto;
import net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorConfigDto;
import net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorDto;
import net.datenwerke.rs.base.client.datasources.dto.FormatBasedDatasourceConfigDto;
import net.datenwerke.rs.base.client.datasources.dto.FormatBasedDatasourceDefinitionDto;
import net.datenwerke.rs.base.client.datasources.dto.TextDatasourceConnectorDto;
import net.datenwerke.rs.base.client.datasources.dto.UrlDatasourceConnectorDto;
import net.datenwerke.rs.base.client.dbhelper.dto.DatabaseHelperDto;
import net.datenwerke.rs.base.client.jasperutils.dto.JasperParameterProposalDto;
import net.datenwerke.rs.base.client.parameters.blatext.dto.BlatextParameterDefinitionDto;
import net.datenwerke.rs.base.client.parameters.blatext.dto.BlatextParameterInstanceDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDataDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDefinitionDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterInstanceDto;
import net.datenwerke.rs.base.client.parameters.datetime.dto.DateTimeParameterDefinitionDto;
import net.datenwerke.rs.base.client.parameters.datetime.dto.DateTimeParameterInstanceDto;
import net.datenwerke.rs.base.client.parameters.headline.dto.HeadlineParameterDefinitionDto;
import net.datenwerke.rs.base.client.parameters.headline.dto.HeadlineParameterInstanceDto;
import net.datenwerke.rs.base.client.parameters.separator.dto.SeparatorParameterDefinitionDto;
import net.datenwerke.rs.base.client.parameters.separator.dto.SeparatorParameterInstanceDto;
import net.datenwerke.rs.base.client.parameters.string.dto.TextParameterDefinitionDto;
import net.datenwerke.rs.base.client.parameters.string.dto.TextParameterInstanceDto;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.CompiledCSVJasperReportDto;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.CompiledHTMLJasperReportDto;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.CompiledPNGJasperReportDto;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportDto;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportJRXMLFileDto;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportVariantDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.AdditionalColumnSpecDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.BinaryColumnFilterDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFilterDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatCurrencyDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatDateDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatNumberDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatTemplateDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatTextDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnReferenceDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.CompiledHTMLTableReportDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterBlockDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterRangeDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterSpecDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.PreFilterDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.RSStringTableRowDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.RSTableModelDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.RSTableRowDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableDefinitionDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportVariantDto;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.FileSelectionParameterDefinitionDto;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.FileSelectionParameterInstanceDto;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.SelectedParameterFileDto;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.UploadedParameterFileDto;
import net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceConfigDto;
import net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceDefinitionDto;
import net.datenwerke.rs.birt.client.reportengines.dto.BirtReportDto;
import net.datenwerke.rs.birt.client.reportengines.dto.BirtReportFileDto;
import net.datenwerke.rs.birt.client.reportengines.dto.BirtReportVariantDto;
import net.datenwerke.rs.birt.client.reportengines.dto.CompiledHTMLBirtReportDto;
import net.datenwerke.rs.birt.client.reportengines.dto.CompiledPNGBirtReportDto;
import net.datenwerke.rs.birt.client.utils.dto.BirtParameterProposalDto;
import net.datenwerke.rs.box.client.box.dto.BoxDatasinkDto;
import net.datenwerke.rs.computedcolumns.client.computedcolumns.dto.ComputedColumnDto;
import net.datenwerke.rs.condition.client.condition.dto.ReportConditionDto;
import net.datenwerke.rs.core.client.datasinkmanager.dto.AbstractDatasinkManagerNodeDto;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkContainerDto;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkFolderDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.AbstractDatasourceManagerNodeDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceFolderDto;
import net.datenwerke.rs.core.client.i18tools.dto.FormatPatternsDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.core.client.reportexporter.dto.RECCsvDto;
import net.datenwerke.rs.core.client.reportexporter.dto.RECJxlsDto;
import net.datenwerke.rs.core.client.reportmanager.dto.ReportFolderDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.AbstractReportManagerNodeDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportBytePropertyDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportMetadataDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportPropertyDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportServerSideStringPropertyDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportStringPropertyDto;
import net.datenwerke.rs.crystal.client.crystal.dto.CrystalParameterProposalDto;
import net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportDto;
import net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportFileDto;
import net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportVariantDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.AbstractDashboardManagerNodeDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetNodeDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardContainerDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardFolderDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardNodeDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardReferenceDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListDadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListEntryDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.LibraryDadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.ParameterDadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.ReportDadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.StaticHtmlDadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.UrlDadgetDto;
import net.datenwerke.rs.dropbox.client.dropbox.dto.DropboxDatasinkDto;
import net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleDto;
import net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleEntryDto;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.EmailDatasinkDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.EditCommandResultExtensionDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto;
import net.datenwerke.rs.ftp.client.ftp.dto.FtpDatasinkDto;
import net.datenwerke.rs.ftp.client.ftp.dto.FtpsDatasinkDto;
import net.datenwerke.rs.ftp.client.ftp.dto.SftpDatasinkDto;
import net.datenwerke.rs.globalconstants.client.globalconstants.dto.GlobalConstantDto;
import net.datenwerke.rs.googledrive.client.googledrive.dto.GoogleDriveDatasinkDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.CustomValidatorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.DateSelectionListEditorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.DecimalSelectionListEditorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.DoubleSelectionListEditorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.EmptyValidatorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.FixedLengthValidatorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.FloatSelectionListEditorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorColumnConfigDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorConfigDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorDataDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordEntryDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorReportDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorReportVariantDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.IntBooleanEditorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.IntSelectionListEditorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.LongSelectionListEditorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.MaxBigDecimalValidatorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.MaxDateValidatorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.MaxDoubleValidatorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.MaxFloatValidatorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.MaxIntegerValidatorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.MaxLengthValidatorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.MaxLongValidatorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.MinBigDecimalValidatorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.MinDateValidatorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.MinDoubleValidatorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.MinFloatValidatorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.MinIntegerValidatorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.MinLengthValidatorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.MinLongValidatorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.RegExValidatorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.TextAreaEditorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.TextBooleanEditorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.TextDateEditorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.TextSelectionListEditorDto;
import net.datenwerke.rs.incubator.client.jaspertotable.dto.JasperToTableConfigDto;
import net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportDto;
import net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportFileDto;
import net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportVariantDto;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto.LocalFileSystemDatasinkDto;
import net.datenwerke.rs.onedrive.client.onedrive.dto.OneDriveDatasinkDto;
import net.datenwerke.rs.printer.client.printer.dto.PrinterDatasinkDto;
import net.datenwerke.rs.remotersserver.client.remotersserver.dto.RemoteRsServerDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.AbstractRemoteServerManagerNodeDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerContainerDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerDefinitionDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerFolderDto;
import net.datenwerke.rs.reportdoc.client.dto.DeployAnalyzeCommandResultExtensionDto;
import net.datenwerke.rs.reportdoc.client.dto.VariantTestCommandResultExtensionDto;
import net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceConfigDto;
import net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceDto;
import net.datenwerke.rs.saiku.client.saiku.dto.RECSaikuChartDto;
import net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportDto;
import net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportVariantDto;
import net.datenwerke.rs.samba.client.samba.dto.SambaDatasinkDto;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto.ExecutedReportFileReferenceDto;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.filter.dto.TeamSpaceReportJobFilterDto;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportServerJobFilterDto;
import net.datenwerke.rs.scp.client.scp.dto.ScpDatasinkDto;
import net.datenwerke.rs.scriptdatasink.client.scriptdatasink.dto.ScriptDatasinkDto;
import net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto.ScriptDatasourceConfigDto;
import net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto.ScriptDatasourceDto;
import net.datenwerke.rs.scripting.client.scripting.dto.AddMenuEntryExtensionDto;
import net.datenwerke.rs.scripting.client.scripting.dto.AddMenuSeparatorEntryExtensionDto;
import net.datenwerke.rs.scripting.client.scripting.dto.AddReportExportFormatProviderDto;
import net.datenwerke.rs.scripting.client.scripting.dto.AddStatusbBarLabelExtensionDto;
import net.datenwerke.rs.scripting.client.scripting.dto.AddToolbarEntryExtensionDto;
import net.datenwerke.rs.scripting.client.scripting.dto.DisplayConditionDto;
import net.datenwerke.rs.scriptreport.client.scriptreport.dto.ScriptReportDto;
import net.datenwerke.rs.scriptreport.client.scriptreport.dto.ScriptReportVariantDto;
import net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.ScriptParameterDefinitionDto;
import net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.ScriptParameterInstanceDto;
import net.datenwerke.rs.search.client.search.dto.SearchFilterDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultEntryDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultListDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultTagDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultTagTypeDto;
import net.datenwerke.rs.tabledatasink.client.tabledatasink.dto.TableDatasinkDto;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.RECTableTemplateDto;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportByteTemplateDto;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportStringTemplateDto;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportTemplateDto;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportTemplateListDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.AppPropertyDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceAppDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceMemberDto;
import net.datenwerke.rs.teamspace.client.teamspace.security.TeamSpaceSecureeDto;
import net.datenwerke.rs.teamspace.client.teamspace.security.rights.TeamSpaceAdministratorDto;
import net.datenwerke.rs.terminal.client.terminal.dto.AutocompleteResultDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultAnchorDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultHtmlDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultHyperlinkDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultLineDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultListDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultTableDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CreJavaScriptDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CreMessageDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CreOverlayDto;
import net.datenwerke.rs.terminal.client.terminal.dto.InteractiveResultModifierDto;
import net.datenwerke.rs.terminal.client.terminal.dto.PressKeyResultModifierDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFileReferenceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFolderDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskGeneralReferenceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskRootDto;
import net.datenwerke.rs.uservariables.client.parameters.dto.UserVariableParameterDefinitionDto;
import net.datenwerke.rs.uservariables.client.parameters.dto.UserVariableParameterInstanceDto;
import net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableDefinitionDto;
import net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableInstanceDto;
import net.datenwerke.rs.uservariables.client.variabletypes.list.dto.ListUserVariableDefinitionDto;
import net.datenwerke.rs.uservariables.client.variabletypes.list.dto.ListUserVariableInstanceDto;
import net.datenwerke.rs.uservariables.client.variabletypes.string.dto.StringUserVariableDefinitionDto;
import net.datenwerke.rs.uservariables.client.variabletypes.string.dto.StringUserVariableInstanceDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.DailyConfigDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.DateTriggerConfigDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthlyNthDayConfigDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthlyNthDayOfWeekConfigDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.TimeDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.WeeklyConfigDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.YearlyAtDateConfigDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.YearlyNthDayOfWeekConfigDto;
import net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterConfigurationDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.ActionEntryDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.ExecutionLogEntryDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.HistoryEntryPropertyDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.JobEntryDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.JobHistoryDto;
import net.datenwerke.security.client.security.dto.AceAccessMapDto;
import net.datenwerke.security.client.security.dto.AceDto;
import net.datenwerke.security.client.security.dto.DeleteDto;
import net.datenwerke.security.client.security.dto.ExecuteDto;
import net.datenwerke.security.client.security.dto.GrantAccessDto;
import net.datenwerke.security.client.security.dto.HierarchicalAceDto;
import net.datenwerke.security.client.security.dto.ReadDto;
import net.datenwerke.security.client.security.dto.SecurityServiceSecureeDto;
import net.datenwerke.security.client.security.dto.WriteDto;
import net.datenwerke.security.client.treedb.dto.SecuredAbstractNodeDto;
import net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto;
import net.datenwerke.security.client.usermanager.dto.GroupDto;
import net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.client.usermanager.dto.UserPropertyDto;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class ClientDtoInfoServiceImpl implements DtoInformationService, DtoMainInformationService {

	@SuppressWarnings("unchecked")
	private List<net.datenwerke.gxtdto.client.dtoinfo.DtoInformationService> subModules = new ArrayList<net.datenwerke.gxtdto.client.dtoinfo.DtoInformationService>();

	private Map<Class<? extends net.datenwerke.gf.base.client.dtogenerator.RsDto>, Class<? extends net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper>> dto2PosoMapperByDtoLookup = new HashMap<>();

	@SuppressWarnings("unchecked")
	@Inject
	public ClientDtoInfoServiceImpl(
	){
	}

	public Object getDtoId(Dto dto)  {
		if(! isAuthorityFor(dto)){
			for(DtoInformationService submodule : subModules){
				if(submodule.isAuthorityFor(dto)){
					return submodule.getDtoId(dto);
				}
			}
			throw new IllegalArgumentException("Unrecognized Dto: " + dto.getClass().getName());
		}

		if(dto instanceof net.datenwerke.rs.amazons3.client.amazons3.dto.AmazonS3DatasinkDto)
			return ((net.datenwerke.rs.amazons3.client.amazons3.dto.AmazonS3DatasinkDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.FileSelectionParameterDefinitionDto)
			return ((net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.FileSelectionParameterDefinitionDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.FileSelectionParameterInstanceDto)
			return ((net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.FileSelectionParameterInstanceDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.SelectedParameterFileDto)
			return ((net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.SelectedParameterFileDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.UploadedParameterFileDto)
			return ((net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.UploadedParameterFileDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.datasources.dto.ArgumentDatasourceConnectorDto)
			return ((net.datenwerke.rs.base.client.datasources.dto.ArgumentDatasourceConnectorDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorDto)
			return ((net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorConfigDto)
			return ((net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorConfigDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.datasources.dto.TextDatasourceConnectorDto)
			return ((net.datenwerke.rs.base.client.datasources.dto.TextDatasourceConnectorDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.datasources.dto.UrlDatasourceConnectorDto)
			return ((net.datenwerke.rs.base.client.datasources.dto.UrlDatasourceConnectorDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.datasources.dto.CsvDatasourceDto)
			return ((net.datenwerke.rs.base.client.datasources.dto.CsvDatasourceDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.datasources.dto.CsvDatasourceConfigDto)
			return ((net.datenwerke.rs.base.client.datasources.dto.CsvDatasourceConfigDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceDto)
			return ((net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceConfigDto)
			return ((net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceConfigDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.datasources.dto.FormatBasedDatasourceConfigDto)
			return ((net.datenwerke.rs.base.client.datasources.dto.FormatBasedDatasourceConfigDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.datasources.dto.FormatBasedDatasourceDefinitionDto)
			return ((net.datenwerke.rs.base.client.datasources.dto.FormatBasedDatasourceDefinitionDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.parameters.blatext.dto.BlatextParameterDefinitionDto)
			return ((net.datenwerke.rs.base.client.parameters.blatext.dto.BlatextParameterDefinitionDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.parameters.blatext.dto.BlatextParameterInstanceDto)
			return ((net.datenwerke.rs.base.client.parameters.blatext.dto.BlatextParameterInstanceDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDataDto)
			return ((net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDataDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDefinitionDto)
			return ((net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDefinitionDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterInstanceDto)
			return ((net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterInstanceDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.parameters.datetime.dto.DateTimeParameterDefinitionDto)
			return ((net.datenwerke.rs.base.client.parameters.datetime.dto.DateTimeParameterDefinitionDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.parameters.datetime.dto.DateTimeParameterInstanceDto)
			return ((net.datenwerke.rs.base.client.parameters.datetime.dto.DateTimeParameterInstanceDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.parameters.headline.dto.HeadlineParameterDefinitionDto)
			return ((net.datenwerke.rs.base.client.parameters.headline.dto.HeadlineParameterDefinitionDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.parameters.headline.dto.HeadlineParameterInstanceDto)
			return ((net.datenwerke.rs.base.client.parameters.headline.dto.HeadlineParameterInstanceDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.parameters.separator.dto.SeparatorParameterDefinitionDto)
			return ((net.datenwerke.rs.base.client.parameters.separator.dto.SeparatorParameterDefinitionDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.parameters.separator.dto.SeparatorParameterInstanceDto)
			return ((net.datenwerke.rs.base.client.parameters.separator.dto.SeparatorParameterInstanceDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.parameters.string.dto.TextParameterDefinitionDto)
			return ((net.datenwerke.rs.base.client.parameters.string.dto.TextParameterDefinitionDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.parameters.string.dto.TextParameterInstanceDto)
			return ((net.datenwerke.rs.base.client.parameters.string.dto.TextParameterInstanceDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportDto)
			return ((net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportJRXMLFileDto)
			return ((net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportJRXMLFileDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportVariantDto)
			return ((net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportVariantDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.jasperutils.dto.JasperParameterProposalDto)
			return ((net.datenwerke.rs.base.client.jasperutils.dto.JasperParameterProposalDto) dto).getKey();
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.table.dto.AdditionalColumnSpecDto)
			return ((net.datenwerke.rs.base.client.reportengines.table.dto.AdditionalColumnSpecDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto)
			return ((net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.table.dto.ColumnReferenceDto)
			return ((net.datenwerke.rs.base.client.reportengines.table.dto.ColumnReferenceDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.table.dto.FilterRangeDto)
			return ((net.datenwerke.rs.base.client.reportengines.table.dto.FilterRangeDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto)
			return ((net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.table.dto.TableReportVariantDto)
			return ((net.datenwerke.rs.base.client.reportengines.table.dto.TableReportVariantDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.table.dto.BinaryColumnFilterDto)
			return ((net.datenwerke.rs.base.client.reportengines.table.dto.BinaryColumnFilterDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFilterDto)
			return ((net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFilterDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.table.dto.FilterDto)
			return ((net.datenwerke.rs.base.client.reportengines.table.dto.FilterDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.table.dto.FilterBlockDto)
			return ((net.datenwerke.rs.base.client.reportengines.table.dto.FilterBlockDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.table.dto.FilterSpecDto)
			return ((net.datenwerke.rs.base.client.reportengines.table.dto.FilterSpecDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.table.dto.PreFilterDto)
			return ((net.datenwerke.rs.base.client.reportengines.table.dto.PreFilterDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatDto)
			return ((net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatCurrencyDto)
			return ((net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatCurrencyDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatDateDto)
			return ((net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatDateDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatNumberDto)
			return ((net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatNumberDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatTemplateDto)
			return ((net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatTemplateDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatTextDto)
			return ((net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatTextDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceConfigDto)
			return ((net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceConfigDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceDefinitionDto)
			return ((net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceDefinitionDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.birt.client.reportengines.dto.BirtReportDto)
			return ((net.datenwerke.rs.birt.client.reportengines.dto.BirtReportDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.birt.client.reportengines.dto.BirtReportFileDto)
			return ((net.datenwerke.rs.birt.client.reportengines.dto.BirtReportFileDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.birt.client.reportengines.dto.BirtReportVariantDto)
			return ((net.datenwerke.rs.birt.client.reportengines.dto.BirtReportVariantDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.birt.client.utils.dto.BirtParameterProposalDto)
			return ((net.datenwerke.rs.birt.client.utils.dto.BirtParameterProposalDto) dto).getKey();
		if(dto instanceof net.datenwerke.rs.box.client.box.dto.BoxDatasinkDto)
			return ((net.datenwerke.rs.box.client.box.dto.BoxDatasinkDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.computedcolumns.client.computedcolumns.dto.ComputedColumnDto)
			return ((net.datenwerke.rs.computedcolumns.client.computedcolumns.dto.ComputedColumnDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.condition.client.condition.dto.ReportConditionDto)
			return ((net.datenwerke.rs.condition.client.condition.dto.ReportConditionDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.core.client.datasinkmanager.dto.AbstractDatasinkManagerNodeDto)
			return ((net.datenwerke.rs.core.client.datasinkmanager.dto.AbstractDatasinkManagerNodeDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkContainerDto)
			return ((net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkContainerDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto)
			return ((net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkFolderDto)
			return ((net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkFolderDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.core.client.datasourcemanager.dto.AbstractDatasourceManagerNodeDto)
			return ((net.datenwerke.rs.core.client.datasourcemanager.dto.AbstractDatasourceManagerNodeDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto)
			return ((net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto)
			return ((net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto)
			return ((net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceFolderDto)
			return ((net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceFolderDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto)
			return ((net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto)
			return ((net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.core.client.reportmanager.dto.reports.AbstractReportManagerNodeDto)
			return ((net.datenwerke.rs.core.client.reportmanager.dto.reports.AbstractReportManagerNodeDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.core.client.reportmanager.dto.ReportFolderDto)
			return ((net.datenwerke.rs.core.client.reportmanager.dto.ReportFolderDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto)
			return ((net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportBytePropertyDto)
			return ((net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportBytePropertyDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportMetadataDto)
			return ((net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportMetadataDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportPropertyDto)
			return ((net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportPropertyDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportServerSideStringPropertyDto)
			return ((net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportServerSideStringPropertyDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportStringPropertyDto)
			return ((net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportStringPropertyDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.crystal.client.crystal.dto.CrystalParameterProposalDto)
			return ((net.datenwerke.rs.crystal.client.crystal.dto.CrystalParameterProposalDto) dto).getKey();
		if(dto instanceof net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportDto)
			return ((net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportFileDto)
			return ((net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportFileDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportVariantDto)
			return ((net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportVariantDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListDto)
			return ((net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListDadgetDto)
			return ((net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListDadgetDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListEntryDto)
			return ((net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListEntryDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.dashboard.client.dashboard.dto.LibraryDadgetDto)
			return ((net.datenwerke.rs.dashboard.client.dashboard.dto.LibraryDadgetDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.dashboard.client.dashboard.dto.ParameterDadgetDto)
			return ((net.datenwerke.rs.dashboard.client.dashboard.dto.ParameterDadgetDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.dashboard.client.dashboard.dto.ReportDadgetDto)
			return ((net.datenwerke.rs.dashboard.client.dashboard.dto.ReportDadgetDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.dashboard.client.dashboard.dto.StaticHtmlDadgetDto)
			return ((net.datenwerke.rs.dashboard.client.dashboard.dto.StaticHtmlDadgetDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.dashboard.client.dashboard.dto.UrlDadgetDto)
			return ((net.datenwerke.rs.dashboard.client.dashboard.dto.UrlDadgetDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.dashboard.client.dashboard.dto.AbstractDashboardManagerNodeDto)
			return ((net.datenwerke.rs.dashboard.client.dashboard.dto.AbstractDashboardManagerNodeDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto)
			return ((net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetNodeDto)
			return ((net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetNodeDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardDto)
			return ((net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardContainerDto)
			return ((net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardContainerDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardFolderDto)
			return ((net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardFolderDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardNodeDto)
			return ((net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardNodeDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardReferenceDto)
			return ((net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardReferenceDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.dropbox.client.dropbox.dto.DropboxDatasinkDto)
			return ((net.datenwerke.rs.dropbox.client.dropbox.dto.DropboxDatasinkDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleDto)
			return ((net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleEntryDto)
			return ((net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleEntryDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.EmailDatasinkDto)
			return ((net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.EmailDatasinkDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto)
			return ((net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto)
			return ((net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto)
			return ((net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.ftp.client.ftp.dto.FtpDatasinkDto)
			return ((net.datenwerke.rs.ftp.client.ftp.dto.FtpDatasinkDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.ftp.client.ftp.dto.FtpsDatasinkDto)
			return ((net.datenwerke.rs.ftp.client.ftp.dto.FtpsDatasinkDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.ftp.client.ftp.dto.SftpDatasinkDto)
			return ((net.datenwerke.rs.ftp.client.ftp.dto.SftpDatasinkDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.globalconstants.client.globalconstants.dto.GlobalConstantDto)
			return ((net.datenwerke.rs.globalconstants.client.globalconstants.dto.GlobalConstantDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.googledrive.client.googledrive.dto.GoogleDriveDatasinkDto)
			return ((net.datenwerke.rs.googledrive.client.googledrive.dto.GoogleDriveDatasinkDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorReportDto)
			return ((net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorReportDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorReportVariantDto)
			return ((net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorReportVariantDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.incubator.client.jaspertotable.dto.JasperToTableConfigDto)
			return ((net.datenwerke.rs.incubator.client.jaspertotable.dto.JasperToTableConfigDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportDto)
			return ((net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportFileDto)
			return ((net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportFileDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportVariantDto)
			return ((net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportVariantDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto.LocalFileSystemDatasinkDto)
			return ((net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto.LocalFileSystemDatasinkDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.onedrive.client.onedrive.dto.OneDriveDatasinkDto)
			return ((net.datenwerke.rs.onedrive.client.onedrive.dto.OneDriveDatasinkDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.printer.client.printer.dto.PrinterDatasinkDto)
			return ((net.datenwerke.rs.printer.client.printer.dto.PrinterDatasinkDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.remotersserver.client.remotersserver.dto.RemoteRsServerDto)
			return ((net.datenwerke.rs.remotersserver.client.remotersserver.dto.RemoteRsServerDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.AbstractRemoteServerManagerNodeDto)
			return ((net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.AbstractRemoteServerManagerNodeDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerContainerDto)
			return ((net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerContainerDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerDefinitionDto)
			return ((net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerDefinitionDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerFolderDto)
			return ((net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerFolderDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceDto)
			return ((net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceConfigDto)
			return ((net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceConfigDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportDto)
			return ((net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportVariantDto)
			return ((net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportVariantDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.samba.client.samba.dto.SambaDatasinkDto)
			return ((net.datenwerke.rs.samba.client.samba.dto.SambaDatasinkDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto.ExecutedReportFileReferenceDto)
			return ((net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto.ExecutedReportFileReferenceDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.scp.client.scp.dto.ScpDatasinkDto)
			return ((net.datenwerke.rs.scp.client.scp.dto.ScpDatasinkDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.scriptdatasink.client.scriptdatasink.dto.ScriptDatasinkDto)
			return ((net.datenwerke.rs.scriptdatasink.client.scriptdatasink.dto.ScriptDatasinkDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto.ScriptDatasourceDto)
			return ((net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto.ScriptDatasourceDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto.ScriptDatasourceConfigDto)
			return ((net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto.ScriptDatasourceConfigDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.scriptreport.client.scriptreport.dto.ScriptReportDto)
			return ((net.datenwerke.rs.scriptreport.client.scriptreport.dto.ScriptReportDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.scriptreport.client.scriptreport.dto.ScriptReportVariantDto)
			return ((net.datenwerke.rs.scriptreport.client.scriptreport.dto.ScriptReportVariantDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.ScriptParameterDefinitionDto)
			return ((net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.ScriptParameterDefinitionDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.ScriptParameterInstanceDto)
			return ((net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.ScriptParameterInstanceDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.search.client.search.dto.SearchResultEntryDto)
			return ((net.datenwerke.rs.search.client.search.dto.SearchResultEntryDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.tabledatasink.client.tabledatasink.dto.TableDatasinkDto)
			return ((net.datenwerke.rs.tabledatasink.client.tabledatasink.dto.TableDatasinkDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportByteTemplateDto)
			return ((net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportByteTemplateDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportStringTemplateDto)
			return ((net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportStringTemplateDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportTemplateDto)
			return ((net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportTemplateDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportTemplateListDto)
			return ((net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportTemplateListDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.teamspace.client.teamspace.dto.AppPropertyDto)
			return ((net.datenwerke.rs.teamspace.client.teamspace.dto.AppPropertyDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto)
			return ((net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceAppDto)
			return ((net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceAppDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceMemberDto)
			return ((net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceMemberDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto)
			return ((net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFileReferenceDto)
			return ((net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFileReferenceDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFolderDto)
			return ((net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFolderDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskGeneralReferenceDto)
			return ((net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskGeneralReferenceDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto)
			return ((net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskRootDto)
			return ((net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskRootDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.uservariables.client.parameters.dto.UserVariableParameterDefinitionDto)
			return ((net.datenwerke.rs.uservariables.client.parameters.dto.UserVariableParameterDefinitionDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.uservariables.client.parameters.dto.UserVariableParameterInstanceDto)
			return ((net.datenwerke.rs.uservariables.client.parameters.dto.UserVariableParameterInstanceDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableDefinitionDto)
			return ((net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableDefinitionDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableInstanceDto)
			return ((net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableInstanceDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.uservariables.client.variabletypes.list.dto.ListUserVariableDefinitionDto)
			return ((net.datenwerke.rs.uservariables.client.variabletypes.list.dto.ListUserVariableDefinitionDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.uservariables.client.variabletypes.list.dto.ListUserVariableInstanceDto)
			return ((net.datenwerke.rs.uservariables.client.variabletypes.list.dto.ListUserVariableInstanceDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.uservariables.client.variabletypes.string.dto.StringUserVariableDefinitionDto)
			return ((net.datenwerke.rs.uservariables.client.variabletypes.string.dto.StringUserVariableDefinitionDto) dto).getId();
		if(dto instanceof net.datenwerke.rs.uservariables.client.variabletypes.string.dto.StringUserVariableInstanceDto)
			return ((net.datenwerke.rs.uservariables.client.variabletypes.string.dto.StringUserVariableInstanceDto) dto).getId();
		if(dto instanceof net.datenwerke.scheduler.client.scheduler.dto.history.ActionEntryDto)
			return ((net.datenwerke.scheduler.client.scheduler.dto.history.ActionEntryDto) dto).getId();
		if(dto instanceof net.datenwerke.scheduler.client.scheduler.dto.history.ExecutionLogEntryDto)
			return ((net.datenwerke.scheduler.client.scheduler.dto.history.ExecutionLogEntryDto) dto).getId();
		if(dto instanceof net.datenwerke.scheduler.client.scheduler.dto.history.HistoryEntryPropertyDto)
			return ((net.datenwerke.scheduler.client.scheduler.dto.history.HistoryEntryPropertyDto) dto).getId();
		if(dto instanceof net.datenwerke.scheduler.client.scheduler.dto.history.JobEntryDto)
			return ((net.datenwerke.scheduler.client.scheduler.dto.history.JobEntryDto) dto).getId();
		if(dto instanceof net.datenwerke.scheduler.client.scheduler.dto.history.JobHistoryDto)
			return ((net.datenwerke.scheduler.client.scheduler.dto.history.JobHistoryDto) dto).getId();
		if(dto instanceof net.datenwerke.scheduler.client.scheduler.dto.config.complex.DailyConfigDto)
			return ((net.datenwerke.scheduler.client.scheduler.dto.config.complex.DailyConfigDto) dto).getId();
		if(dto instanceof net.datenwerke.scheduler.client.scheduler.dto.config.complex.DateTriggerConfigDto)
			return ((net.datenwerke.scheduler.client.scheduler.dto.config.complex.DateTriggerConfigDto) dto).getId();
		if(dto instanceof net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthlyNthDayConfigDto)
			return ((net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthlyNthDayConfigDto) dto).getId();
		if(dto instanceof net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthlyNthDayOfWeekConfigDto)
			return ((net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthlyNthDayOfWeekConfigDto) dto).getId();
		if(dto instanceof net.datenwerke.scheduler.client.scheduler.dto.config.complex.WeeklyConfigDto)
			return ((net.datenwerke.scheduler.client.scheduler.dto.config.complex.WeeklyConfigDto) dto).getId();
		if(dto instanceof net.datenwerke.scheduler.client.scheduler.dto.config.complex.YearlyAtDateConfigDto)
			return ((net.datenwerke.scheduler.client.scheduler.dto.config.complex.YearlyAtDateConfigDto) dto).getId();
		if(dto instanceof net.datenwerke.scheduler.client.scheduler.dto.config.complex.YearlyNthDayOfWeekConfigDto)
			return ((net.datenwerke.scheduler.client.scheduler.dto.config.complex.YearlyNthDayOfWeekConfigDto) dto).getId();
		if(dto instanceof net.datenwerke.security.client.security.dto.AceDto)
			return ((net.datenwerke.security.client.security.dto.AceDto) dto).getId();
		if(dto instanceof net.datenwerke.security.client.security.dto.AceAccessMapDto)
			return ((net.datenwerke.security.client.security.dto.AceAccessMapDto) dto).getId();
		if(dto instanceof net.datenwerke.security.client.security.dto.HierarchicalAceDto)
			return ((net.datenwerke.security.client.security.dto.HierarchicalAceDto) dto).getId();
		if(dto instanceof net.datenwerke.security.client.treedb.dto.SecuredAbstractNodeDto)
			return ((net.datenwerke.security.client.treedb.dto.SecuredAbstractNodeDto) dto).getId();
		if(dto instanceof net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto)
			return ((net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto) dto).getId();
		if(dto instanceof net.datenwerke.security.client.usermanager.dto.GroupDto)
			return ((net.datenwerke.security.client.usermanager.dto.GroupDto) dto).getId();
		if(dto instanceof net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto)
			return ((net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto) dto).getId();
		if(dto instanceof net.datenwerke.security.client.usermanager.dto.UserDto)
			return ((net.datenwerke.security.client.usermanager.dto.UserDto) dto).getId();
		if(dto instanceof net.datenwerke.security.client.usermanager.dto.UserPropertyDto)
			return ((net.datenwerke.security.client.usermanager.dto.UserPropertyDto) dto).getId();
		if(dto instanceof net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto)
			return ((net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto) dto).getId();
		throw new IllegalArgumentException("unrecognized dto: " + dto);
	}


	public <X extends Dto> X createInstance(Class<X> dtoClass)  {
		if(! isAuthorityForClass(dtoClass)){
			for(DtoInformationService submodule : subModules){
				if(submodule.isAuthorityForClass(dtoClass)){
					return submodule.createInstance(dtoClass);
				}
			}
			throw new IllegalArgumentException("Unrecognized Dto: " + dtoClass.getName());
		}

		if(net.datenwerke.gf.client.history.dto.HistoryLinkDto.class.equals(dtoClass))
			return (X) new net.datenwerke.gf.client.history.dto.HistoryLinkDto();
		if(net.datenwerke.gf.client.juel.dto.decorator.JuelResultDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.gf.client.juel.dto.decorator.JuelResultDtoDec();
		if(net.datenwerke.rs.adminutils.client.logs.dto.ViewLogFileCommandResultExtensionDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.adminutils.client.logs.dto.ViewLogFileCommandResultExtensionDto();
		if(net.datenwerke.rs.amazons3.client.amazons3.dto.AmazonS3DatasinkDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.amazons3.client.amazons3.dto.AmazonS3DatasinkDto();
		if(net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.FileSelectionParameterDefinitionDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.FileSelectionParameterDefinitionDto();
		if(net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.decorator.FileSelectionParameterInstanceDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.decorator.FileSelectionParameterInstanceDtoDec();
		if(net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.SelectedParameterFileDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.SelectedParameterFileDto();
		if(net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.UploadedParameterFileDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.UploadedParameterFileDto();
		if(net.datenwerke.rs.base.client.datasources.dto.decorator.ArgumentDatasourceConnectorDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.datasources.dto.decorator.ArgumentDatasourceConnectorDtoDec();
		if(net.datenwerke.rs.base.client.datasources.dto.decorator.DatasourceConnectorDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.datasources.dto.decorator.DatasourceConnectorDtoDec();
		if(net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorConfigDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorConfigDto();
		if(net.datenwerke.rs.base.client.datasources.dto.TextDatasourceConnectorDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.datasources.dto.TextDatasourceConnectorDto();
		if(net.datenwerke.rs.base.client.datasources.dto.UrlDatasourceConnectorDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.datasources.dto.UrlDatasourceConnectorDto();
		if(net.datenwerke.rs.base.client.datasources.dto.CsvDatasourceDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.datasources.dto.CsvDatasourceDto();
		if(net.datenwerke.rs.base.client.datasources.dto.CsvDatasourceConfigDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.datasources.dto.CsvDatasourceConfigDto();
		if(net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceDto();
		if(net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceConfigDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceConfigDto();
		if(net.datenwerke.rs.base.client.dbhelper.dto.DatabaseHelperDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.dbhelper.dto.DatabaseHelperDto();
		if(net.datenwerke.rs.base.client.parameters.blatext.dto.BlatextParameterDefinitionDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.parameters.blatext.dto.BlatextParameterDefinitionDto();
		if(net.datenwerke.rs.base.client.parameters.blatext.dto.BlatextParameterInstanceDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.parameters.blatext.dto.BlatextParameterInstanceDto();
		if(net.datenwerke.rs.base.client.parameters.datasource.dto.decorator.DatasourceParameterDataDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.parameters.datasource.dto.decorator.DatasourceParameterDataDtoDec();
		if(net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDefinitionDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDefinitionDto();
		if(net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterInstanceDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterInstanceDto();
		if(net.datenwerke.rs.base.client.parameters.datetime.dto.decorator.DateTimeParameterDefinitionDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.parameters.datetime.dto.decorator.DateTimeParameterDefinitionDtoDec();
		if(net.datenwerke.rs.base.client.parameters.datetime.dto.decorator.DateTimeParameterInstanceDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.parameters.datetime.dto.decorator.DateTimeParameterInstanceDtoDec();
		if(net.datenwerke.rs.base.client.parameters.headline.dto.HeadlineParameterDefinitionDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.parameters.headline.dto.HeadlineParameterDefinitionDto();
		if(net.datenwerke.rs.base.client.parameters.headline.dto.HeadlineParameterInstanceDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.parameters.headline.dto.HeadlineParameterInstanceDto();
		if(net.datenwerke.rs.base.client.parameters.separator.dto.SeparatorParameterDefinitionDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.parameters.separator.dto.SeparatorParameterDefinitionDto();
		if(net.datenwerke.rs.base.client.parameters.separator.dto.SeparatorParameterInstanceDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.parameters.separator.dto.SeparatorParameterInstanceDto();
		if(net.datenwerke.rs.base.client.parameters.string.dto.TextParameterDefinitionDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.parameters.string.dto.TextParameterDefinitionDto();
		if(net.datenwerke.rs.base.client.parameters.string.dto.TextParameterInstanceDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.parameters.string.dto.TextParameterInstanceDto();
		if(net.datenwerke.rs.base.client.reportengines.jasper.dto.decorator.JasperReportDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.reportengines.jasper.dto.decorator.JasperReportDtoDec();
		if(net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportJRXMLFileDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportJRXMLFileDto();
		if(net.datenwerke.rs.base.client.reportengines.jasper.dto.decorator.JasperReportVariantDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.reportengines.jasper.dto.decorator.JasperReportVariantDtoDec();
		if(net.datenwerke.rs.base.client.reportengines.jasper.dto.CompiledCSVJasperReportDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.reportengines.jasper.dto.CompiledCSVJasperReportDto();
		if(net.datenwerke.rs.base.client.reportengines.jasper.dto.CompiledHTMLJasperReportDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.reportengines.jasper.dto.CompiledHTMLJasperReportDto();
		if(net.datenwerke.rs.base.client.reportengines.jasper.dto.CompiledPNGJasperReportDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.reportengines.jasper.dto.CompiledPNGJasperReportDto();
		if(net.datenwerke.rs.base.client.jasperutils.dto.JasperParameterProposalDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.jasperutils.dto.JasperParameterProposalDto();
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.AdditionalColumnSpecDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.decorator.AdditionalColumnSpecDtoDec();
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnDtoDec();
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnReferenceDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnReferenceDtoDec();
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterRangeDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterRangeDtoDec();
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.TableReportDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.decorator.TableReportDtoDec();
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.TableReportVariantDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.decorator.TableReportVariantDtoDec();
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.BinaryColumnFilterDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.decorator.BinaryColumnFilterDtoDec();
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFilterDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFilterDtoDec();
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterDtoDec();
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterBlockDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterBlockDtoDec();
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.PreFilterDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.decorator.PreFilterDtoDec();
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatCurrencyDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatCurrencyDtoDec();
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatDateDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatDateDtoDec();
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatNumberDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatNumberDtoDec();
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatTemplateDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatTemplateDtoDec();
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatTextDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatTextDtoDec();
		if(net.datenwerke.rs.base.client.reportengines.table.dto.CompiledHTMLTableReportDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.CompiledHTMLTableReportDto();
		if(net.datenwerke.rs.base.client.reportengines.table.dto.RSStringTableRowDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.RSStringTableRowDto();
		if(net.datenwerke.rs.base.client.reportengines.table.dto.RSTableModelDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.RSTableModelDto();
		if(net.datenwerke.rs.base.client.reportengines.table.dto.RSTableRowDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.RSTableRowDto();
		if(net.datenwerke.rs.base.client.reportengines.table.dto.TableDefinitionDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.TableDefinitionDto();
		if(net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceConfigDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceConfigDto();
		if(net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceDefinitionDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceDefinitionDto();
		if(net.datenwerke.rs.birt.client.reportengines.dto.decorator.BirtReportDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.birt.client.reportengines.dto.decorator.BirtReportDtoDec();
		if(net.datenwerke.rs.birt.client.reportengines.dto.BirtReportFileDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.birt.client.reportengines.dto.BirtReportFileDto();
		if(net.datenwerke.rs.birt.client.reportengines.dto.decorator.BirtReportVariantDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.birt.client.reportengines.dto.decorator.BirtReportVariantDtoDec();
		if(net.datenwerke.rs.birt.client.reportengines.dto.CompiledHTMLBirtReportDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.birt.client.reportengines.dto.CompiledHTMLBirtReportDto();
		if(net.datenwerke.rs.birt.client.reportengines.dto.CompiledPNGBirtReportDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.birt.client.reportengines.dto.CompiledPNGBirtReportDto();
		if(net.datenwerke.rs.birt.client.utils.dto.BirtParameterProposalDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.birt.client.utils.dto.BirtParameterProposalDto();
		if(net.datenwerke.rs.box.client.box.dto.BoxDatasinkDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.box.client.box.dto.BoxDatasinkDto();
		if(net.datenwerke.rs.computedcolumns.client.computedcolumns.dto.ComputedColumnDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.computedcolumns.client.computedcolumns.dto.ComputedColumnDto();
		if(net.datenwerke.rs.condition.client.condition.dto.decorator.ReportConditionDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.condition.client.condition.dto.decorator.ReportConditionDtoDec();
		if(net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkContainerDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkContainerDto();
		if(net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkFolderDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkFolderDto();
		if(net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto();
		if(net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto();
		if(net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceFolderDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceFolderDto();
		if(net.datenwerke.rs.core.client.i18tools.dto.FormatPatternsDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.core.client.i18tools.dto.FormatPatternsDto();
		if(net.datenwerke.rs.core.client.reportexporter.dto.decorator.RECCsvDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.core.client.reportexporter.dto.decorator.RECCsvDtoDec();
		if(net.datenwerke.rs.core.client.reportexporter.dto.decorator.RECJxlsDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.core.client.reportexporter.dto.decorator.RECJxlsDtoDec();
		if(net.datenwerke.rs.core.client.reportmanager.dto.ReportFolderDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.core.client.reportmanager.dto.ReportFolderDto();
		if(net.datenwerke.rs.core.client.reportmanager.dto.reports.decorator.ReportDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.core.client.reportmanager.dto.reports.decorator.ReportDtoDec();
		if(net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportBytePropertyDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportBytePropertyDto();
		if(net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportMetadataDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportMetadataDto();
		if(net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportPropertyDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportPropertyDto();
		if(net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportServerSideStringPropertyDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportServerSideStringPropertyDto();
		if(net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportStringPropertyDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportStringPropertyDto();
		if(net.datenwerke.rs.crystal.client.crystal.dto.CrystalParameterProposalDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.crystal.client.crystal.dto.CrystalParameterProposalDto();
		if(net.datenwerke.rs.crystal.client.crystal.dto.decorator.CrystalReportDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.crystal.client.crystal.dto.decorator.CrystalReportDtoDec();
		if(net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportFileDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportFileDto();
		if(net.datenwerke.rs.crystal.client.crystal.dto.decorator.CrystalReportVariantDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.crystal.client.crystal.dto.decorator.CrystalReportVariantDtoDec();
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.FavoriteListDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.FavoriteListDtoDec();
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.FavoriteListDadgetDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.FavoriteListDadgetDtoDec();
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.FavoriteListEntryDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.FavoriteListEntryDtoDec();
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.LibraryDadgetDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.LibraryDadgetDtoDec();
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.ParameterDadgetDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.ParameterDadgetDtoDec();
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.ReportDadgetDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.ReportDadgetDtoDec();
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.StaticHtmlDadgetDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.StaticHtmlDadgetDtoDec();
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.UrlDadgetDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.UrlDadgetDtoDec();
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetNodeDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetNodeDto();
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DashboardDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DashboardDtoDec();
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DashboardContainerDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DashboardContainerDtoDec();
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardFolderDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardFolderDto();
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardNodeDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardNodeDto();
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DashboardReferenceDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DashboardReferenceDtoDec();
		if(net.datenwerke.rs.dropbox.client.dropbox.dto.DropboxDatasinkDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.dropbox.client.dropbox.dto.DropboxDatasinkDto();
		if(net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleDto();
		if(net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleEntryDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleEntryDto();
		if(net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.EmailDatasinkDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.EmailDatasinkDto();
		if(net.datenwerke.rs.fileserver.client.fileserver.dto.decorator.FileServerFileDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.fileserver.client.fileserver.dto.decorator.FileServerFileDtoDec();
		if(net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto();
		if(net.datenwerke.rs.fileserver.client.fileserver.dto.EditCommandResultExtensionDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.fileserver.client.fileserver.dto.EditCommandResultExtensionDto();
		if(net.datenwerke.rs.ftp.client.ftp.dto.FtpDatasinkDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.ftp.client.ftp.dto.FtpDatasinkDto();
		if(net.datenwerke.rs.ftp.client.ftp.dto.FtpsDatasinkDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.ftp.client.ftp.dto.FtpsDatasinkDto();
		if(net.datenwerke.rs.ftp.client.ftp.dto.SftpDatasinkDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.ftp.client.ftp.dto.SftpDatasinkDto();
		if(net.datenwerke.rs.globalconstants.client.globalconstants.dto.GlobalConstantDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.globalconstants.client.globalconstants.dto.GlobalConstantDto();
		if(net.datenwerke.rs.googledrive.client.googledrive.dto.GoogleDriveDatasinkDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.googledrive.client.googledrive.dto.GoogleDriveDatasinkDto();
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorColumnConfigDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorColumnConfigDtoDec();
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorConfigDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorConfigDto();
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorDataDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorDataDtoDec();
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorRecordDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorRecordDtoDec();
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorRecordEntryDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorRecordEntryDtoDec();
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.DateSelectionListEditorDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.DateSelectionListEditorDtoDec();
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.DecimalSelectionListEditorDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.DecimalSelectionListEditorDtoDec();
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.DoubleSelectionListEditorDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.DoubleSelectionListEditorDtoDec();
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.FloatSelectionListEditorDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.FloatSelectionListEditorDtoDec();
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.IntBooleanEditorDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.IntBooleanEditorDtoDec();
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.IntSelectionListEditorDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.IntSelectionListEditorDtoDec();
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.LongSelectionListEditorDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.LongSelectionListEditorDtoDec();
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.TextAreaEditorDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.TextAreaEditorDtoDec();
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.TextBooleanEditorDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.TextBooleanEditorDtoDec();
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.TextDateEditorDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.TextDateEditorDtoDec();
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.TextSelectionListEditorDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.TextSelectionListEditorDtoDec();
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.CustomValidatorDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.CustomValidatorDtoDec();
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.EmptyValidatorDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.EmptyValidatorDtoDec();
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.FixedLengthValidatorDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.FixedLengthValidatorDtoDec();
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxBigDecimalValidatorDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxBigDecimalValidatorDtoDec();
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxDateValidatorDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxDateValidatorDtoDec();
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxDoubleValidatorDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxDoubleValidatorDtoDec();
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxFloatValidatorDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxFloatValidatorDtoDec();
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxIntegerValidatorDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxIntegerValidatorDtoDec();
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxLengthValidatorDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxLengthValidatorDtoDec();
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxLongValidatorDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxLongValidatorDtoDec();
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinBigDecimalValidatorDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinBigDecimalValidatorDtoDec();
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinDateValidatorDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinDateValidatorDtoDec();
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinDoubleValidatorDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinDoubleValidatorDtoDec();
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinFloatValidatorDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinFloatValidatorDtoDec();
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinIntegerValidatorDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinIntegerValidatorDtoDec();
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinLengthValidatorDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinLengthValidatorDtoDec();
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinLongValidatorDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinLongValidatorDtoDec();
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.RegExValidatorDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.RegExValidatorDtoDec();
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorReportDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorReportDtoDec();
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorReportVariantDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorReportVariantDtoDec();
		if(net.datenwerke.rs.incubator.client.jaspertotable.dto.decorator.JasperToTableConfigDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.incubator.client.jaspertotable.dto.decorator.JasperToTableConfigDtoDec();
		if(net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.decorator.JxlsReportDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.decorator.JxlsReportDtoDec();
		if(net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportFileDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportFileDto();
		if(net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.decorator.JxlsReportVariantDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.decorator.JxlsReportVariantDtoDec();
		if(net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto.LocalFileSystemDatasinkDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto.LocalFileSystemDatasinkDto();
		if(net.datenwerke.rs.onedrive.client.onedrive.dto.OneDriveDatasinkDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.onedrive.client.onedrive.dto.OneDriveDatasinkDto();
		if(net.datenwerke.rs.printer.client.printer.dto.PrinterDatasinkDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.printer.client.printer.dto.PrinterDatasinkDto();
		if(net.datenwerke.rs.remotersserver.client.remotersserver.dto.RemoteRsServerDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.remotersserver.client.remotersserver.dto.RemoteRsServerDto();
		if(net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerContainerDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerContainerDto();
		if(net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerFolderDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerFolderDto();
		if(net.datenwerke.rs.reportdoc.client.dto.DeployAnalyzeCommandResultExtensionDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.reportdoc.client.dto.DeployAnalyzeCommandResultExtensionDto();
		if(net.datenwerke.rs.reportdoc.client.dto.VariantTestCommandResultExtensionDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.reportdoc.client.dto.VariantTestCommandResultExtensionDto();
		if(net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceDto();
		if(net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceConfigDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceConfigDto();
		if(net.datenwerke.rs.saiku.client.saiku.dto.decorator.SaikuReportDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.saiku.client.saiku.dto.decorator.SaikuReportDtoDec();
		if(net.datenwerke.rs.saiku.client.saiku.dto.decorator.SaikuReportVariantDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.saiku.client.saiku.dto.decorator.SaikuReportVariantDtoDec();
		if(net.datenwerke.rs.saiku.client.saiku.dto.decorator.RECSaikuChartDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.saiku.client.saiku.dto.decorator.RECSaikuChartDtoDec();
		if(net.datenwerke.rs.samba.client.samba.dto.SambaDatasinkDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.samba.client.samba.dto.SambaDatasinkDto();
		if(net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto.decorator.ExecutedReportFileReferenceDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto.decorator.ExecutedReportFileReferenceDtoDec();
		if(net.datenwerke.rs.scheduleasfile.client.scheduleasfile.filter.dto.TeamSpaceReportJobFilterDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.scheduleasfile.client.scheduleasfile.filter.dto.TeamSpaceReportJobFilterDto();
		if(net.datenwerke.rs.scheduler.client.scheduler.dto.ReportServerJobFilterDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.scheduler.client.scheduler.dto.ReportServerJobFilterDto();
		if(net.datenwerke.rs.scp.client.scp.dto.ScpDatasinkDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.scp.client.scp.dto.ScpDatasinkDto();
		if(net.datenwerke.rs.scriptdatasink.client.scriptdatasink.dto.ScriptDatasinkDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.scriptdatasink.client.scriptdatasink.dto.ScriptDatasinkDto();
		if(net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto.ScriptDatasourceDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto.ScriptDatasourceDto();
		if(net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto.ScriptDatasourceConfigDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto.ScriptDatasourceConfigDto();
		if(net.datenwerke.rs.scripting.client.scripting.dto.AddMenuEntryExtensionDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.scripting.client.scripting.dto.AddMenuEntryExtensionDto();
		if(net.datenwerke.rs.scripting.client.scripting.dto.AddMenuSeparatorEntryExtensionDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.scripting.client.scripting.dto.AddMenuSeparatorEntryExtensionDto();
		if(net.datenwerke.rs.scripting.client.scripting.dto.AddReportExportFormatProviderDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.scripting.client.scripting.dto.AddReportExportFormatProviderDto();
		if(net.datenwerke.rs.scripting.client.scripting.dto.AddStatusbBarLabelExtensionDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.scripting.client.scripting.dto.AddStatusbBarLabelExtensionDto();
		if(net.datenwerke.rs.scripting.client.scripting.dto.AddToolbarEntryExtensionDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.scripting.client.scripting.dto.AddToolbarEntryExtensionDto();
		if(net.datenwerke.rs.scripting.client.scripting.dto.DisplayConditionDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.scripting.client.scripting.dto.DisplayConditionDto();
		if(net.datenwerke.rs.scriptreport.client.scriptreport.dto.decorator.ScriptReportDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.scriptreport.client.scriptreport.dto.decorator.ScriptReportDtoDec();
		if(net.datenwerke.rs.scriptreport.client.scriptreport.dto.decorator.ScriptReportVariantDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.scriptreport.client.scriptreport.dto.decorator.ScriptReportVariantDtoDec();
		if(net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.ScriptParameterDefinitionDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.ScriptParameterDefinitionDto();
		if(net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.ScriptParameterInstanceDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.ScriptParameterInstanceDto();
		if(net.datenwerke.rs.search.client.search.dto.decorator.SearchFilterDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.search.client.search.dto.decorator.SearchFilterDtoDec();
		if(net.datenwerke.rs.search.client.search.dto.decorator.SearchResultEntryDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.search.client.search.dto.decorator.SearchResultEntryDtoDec();
		if(net.datenwerke.rs.search.client.search.dto.decorator.SearchResultListDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.search.client.search.dto.decorator.SearchResultListDtoDec();
		if(net.datenwerke.rs.search.client.search.dto.SearchResultTagDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.search.client.search.dto.SearchResultTagDto();
		if(net.datenwerke.rs.search.client.search.dto.SearchResultTagTypeDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.search.client.search.dto.SearchResultTagTypeDto();
		if(net.datenwerke.rs.tabledatasink.client.tabledatasink.dto.TableDatasinkDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.tabledatasink.client.tabledatasink.dto.TableDatasinkDto();
		if(net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.RECTableTemplateDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.RECTableTemplateDto();
		if(net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportByteTemplateDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportByteTemplateDto();
		if(net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportStringTemplateDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportStringTemplateDto();
		if(net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportTemplateListDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportTemplateListDto();
		if(net.datenwerke.rs.teamspace.client.teamspace.dto.AppPropertyDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.teamspace.client.teamspace.dto.AppPropertyDto();
		if(net.datenwerke.rs.teamspace.client.teamspace.dto.decorator.TeamSpaceDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.teamspace.client.teamspace.dto.decorator.TeamSpaceDtoDec();
		if(net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceAppDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceAppDto();
		if(net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceMemberDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceMemberDto();
		if(net.datenwerke.rs.teamspace.client.teamspace.security.TeamSpaceSecureeDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.teamspace.client.teamspace.security.TeamSpaceSecureeDto();
		if(net.datenwerke.rs.teamspace.client.teamspace.security.rights.TeamSpaceAdministratorDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.teamspace.client.teamspace.security.rights.TeamSpaceAdministratorDto();
		if(net.datenwerke.rs.terminal.client.terminal.dto.decorator.AutocompleteResultDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.terminal.client.terminal.dto.decorator.AutocompleteResultDtoDec();
		if(net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultDtoDec();
		if(net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultAnchorDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultAnchorDtoDec();
		if(net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultHtmlDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultHtmlDtoDec();
		if(net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultHyperlinkDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultHyperlinkDtoDec();
		if(net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultLineDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultLineDtoDec();
		if(net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultListDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultListDtoDec();
		if(net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultTableDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultTableDtoDec();
		if(net.datenwerke.rs.terminal.client.terminal.dto.CreJavaScriptDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.terminal.client.terminal.dto.CreJavaScriptDto();
		if(net.datenwerke.rs.terminal.client.terminal.dto.CreMessageDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.terminal.client.terminal.dto.CreMessageDto();
		if(net.datenwerke.rs.terminal.client.terminal.dto.CreOverlayDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.terminal.client.terminal.dto.CreOverlayDto();
		if(net.datenwerke.rs.terminal.client.terminal.dto.InteractiveResultModifierDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.terminal.client.terminal.dto.InteractiveResultModifierDto();
		if(net.datenwerke.rs.terminal.client.terminal.dto.PressKeyResultModifierDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.terminal.client.terminal.dto.PressKeyResultModifierDto();
		if(net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.TsDiskFileReferenceDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.TsDiskFileReferenceDtoDec();
		if(net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFolderDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFolderDto();
		if(net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.TsDiskReportReferenceDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.TsDiskReportReferenceDtoDec();
		if(net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskRootDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskRootDto();
		if(net.datenwerke.rs.uservariables.client.parameters.dto.UserVariableParameterDefinitionDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.uservariables.client.parameters.dto.UserVariableParameterDefinitionDto();
		if(net.datenwerke.rs.uservariables.client.parameters.dto.decorator.UserVariableParameterInstanceDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.uservariables.client.parameters.dto.decorator.UserVariableParameterInstanceDtoDec();
		if(net.datenwerke.rs.uservariables.client.variabletypes.list.dto.ListUserVariableDefinitionDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.uservariables.client.variabletypes.list.dto.ListUserVariableDefinitionDto();
		if(net.datenwerke.rs.uservariables.client.variabletypes.list.dto.ListUserVariableInstanceDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.uservariables.client.variabletypes.list.dto.ListUserVariableInstanceDto();
		if(net.datenwerke.rs.uservariables.client.variabletypes.string.dto.StringUserVariableDefinitionDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.uservariables.client.variabletypes.string.dto.StringUserVariableDefinitionDto();
		if(net.datenwerke.rs.uservariables.client.variabletypes.string.dto.StringUserVariableInstanceDto.class.equals(dtoClass))
			return (X) new net.datenwerke.rs.uservariables.client.variabletypes.string.dto.StringUserVariableInstanceDto();
		if(net.datenwerke.scheduler.client.scheduler.dto.history.ActionEntryDto.class.equals(dtoClass))
			return (X) new net.datenwerke.scheduler.client.scheduler.dto.history.ActionEntryDto();
		if(net.datenwerke.scheduler.client.scheduler.dto.history.ExecutionLogEntryDto.class.equals(dtoClass))
			return (X) new net.datenwerke.scheduler.client.scheduler.dto.history.ExecutionLogEntryDto();
		if(net.datenwerke.scheduler.client.scheduler.dto.history.HistoryEntryPropertyDto.class.equals(dtoClass))
			return (X) new net.datenwerke.scheduler.client.scheduler.dto.history.HistoryEntryPropertyDto();
		if(net.datenwerke.scheduler.client.scheduler.dto.history.JobEntryDto.class.equals(dtoClass))
			return (X) new net.datenwerke.scheduler.client.scheduler.dto.history.JobEntryDto();
		if(net.datenwerke.scheduler.client.scheduler.dto.history.JobHistoryDto.class.equals(dtoClass))
			return (X) new net.datenwerke.scheduler.client.scheduler.dto.history.JobHistoryDto();
		if(net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterConfigurationDto.class.equals(dtoClass))
			return (X) new net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterConfigurationDto();
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.DailyConfigDto.class.equals(dtoClass))
			return (X) new net.datenwerke.scheduler.client.scheduler.dto.config.complex.DailyConfigDto();
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.DateTriggerConfigDto.class.equals(dtoClass))
			return (X) new net.datenwerke.scheduler.client.scheduler.dto.config.complex.DateTriggerConfigDto();
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthlyNthDayConfigDto.class.equals(dtoClass))
			return (X) new net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthlyNthDayConfigDto();
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthlyNthDayOfWeekConfigDto.class.equals(dtoClass))
			return (X) new net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthlyNthDayOfWeekConfigDto();
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.decorator.TimeDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.scheduler.client.scheduler.dto.config.complex.decorator.TimeDtoDec();
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.WeeklyConfigDto.class.equals(dtoClass))
			return (X) new net.datenwerke.scheduler.client.scheduler.dto.config.complex.WeeklyConfigDto();
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.YearlyAtDateConfigDto.class.equals(dtoClass))
			return (X) new net.datenwerke.scheduler.client.scheduler.dto.config.complex.YearlyAtDateConfigDto();
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.YearlyNthDayOfWeekConfigDto.class.equals(dtoClass))
			return (X) new net.datenwerke.scheduler.client.scheduler.dto.config.complex.YearlyNthDayOfWeekConfigDto();
		if(net.datenwerke.security.client.security.dto.SecurityServiceSecureeDto.class.equals(dtoClass))
			return (X) new net.datenwerke.security.client.security.dto.SecurityServiceSecureeDto();
		if(net.datenwerke.security.client.security.dto.decorator.AceDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.security.client.security.dto.decorator.AceDtoDec();
		if(net.datenwerke.security.client.security.dto.decorator.AceAccessMapDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.security.client.security.dto.decorator.AceAccessMapDtoDec();
		if(net.datenwerke.security.client.security.dto.HierarchicalAceDto.class.equals(dtoClass))
			return (X) new net.datenwerke.security.client.security.dto.HierarchicalAceDto();
		if(net.datenwerke.security.client.security.dto.DeleteDto.class.equals(dtoClass))
			return (X) new net.datenwerke.security.client.security.dto.DeleteDto();
		if(net.datenwerke.security.client.security.dto.ExecuteDto.class.equals(dtoClass))
			return (X) new net.datenwerke.security.client.security.dto.ExecuteDto();
		if(net.datenwerke.security.client.security.dto.GrantAccessDto.class.equals(dtoClass))
			return (X) new net.datenwerke.security.client.security.dto.GrantAccessDto();
		if(net.datenwerke.security.client.security.dto.ReadDto.class.equals(dtoClass))
			return (X) new net.datenwerke.security.client.security.dto.ReadDto();
		if(net.datenwerke.security.client.security.dto.WriteDto.class.equals(dtoClass))
			return (X) new net.datenwerke.security.client.security.dto.WriteDto();
		if(net.datenwerke.security.client.usermanager.dto.GroupDto.class.equals(dtoClass))
			return (X) new net.datenwerke.security.client.usermanager.dto.GroupDto();
		if(net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto.class.equals(dtoClass))
			return (X) new net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto();
		if(net.datenwerke.security.client.usermanager.dto.decorator.UserDtoDec.class.equals(dtoClass))
			return (X) new net.datenwerke.security.client.usermanager.dto.decorator.UserDtoDec();
		if(net.datenwerke.security.client.usermanager.dto.UserPropertyDto.class.equals(dtoClass))
			return (X) new net.datenwerke.security.client.usermanager.dto.UserPropertyDto();
		throw new IllegalArgumentException("unrecognized dto: " + dtoClass);
	}


	public <X extends Dto> X createInstance(String dtoClassName)  {
		if(! isAuthorityForClassName(dtoClassName)){
			for(DtoInformationService submodule : subModules){
				if(submodule.isAuthorityForClassName(dtoClassName)){
					return submodule.createInstance(dtoClassName);
				}
			}
			throw new IllegalArgumentException("Unrecognized Dto: " + dtoClassName);
		}

		if("net.datenwerke.gf.client.history.dto.HistoryLinkDto".equals(dtoClassName))
			return (X) new net.datenwerke.gf.client.history.dto.HistoryLinkDto();
		if("net.datenwerke.gf.client.juel.dto.decorator.JuelResultDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.gf.client.juel.dto.decorator.JuelResultDtoDec();
		if("net.datenwerke.rs.adminutils.client.logs.dto.ViewLogFileCommandResultExtensionDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.adminutils.client.logs.dto.ViewLogFileCommandResultExtensionDto();
		if("net.datenwerke.rs.amazons3.client.amazons3.dto.AmazonS3DatasinkDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.amazons3.client.amazons3.dto.AmazonS3DatasinkDto();
		if("net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.FileSelectionParameterDefinitionDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.FileSelectionParameterDefinitionDto();
		if("net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.decorator.FileSelectionParameterInstanceDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.decorator.FileSelectionParameterInstanceDtoDec();
		if("net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.SelectedParameterFileDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.SelectedParameterFileDto();
		if("net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.UploadedParameterFileDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.UploadedParameterFileDto();
		if("net.datenwerke.rs.base.client.datasources.dto.decorator.ArgumentDatasourceConnectorDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.datasources.dto.decorator.ArgumentDatasourceConnectorDtoDec();
		if("net.datenwerke.rs.base.client.datasources.dto.decorator.DatasourceConnectorDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.datasources.dto.decorator.DatasourceConnectorDtoDec();
		if("net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorConfigDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorConfigDto();
		if("net.datenwerke.rs.base.client.datasources.dto.TextDatasourceConnectorDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.datasources.dto.TextDatasourceConnectorDto();
		if("net.datenwerke.rs.base.client.datasources.dto.UrlDatasourceConnectorDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.datasources.dto.UrlDatasourceConnectorDto();
		if("net.datenwerke.rs.base.client.datasources.dto.CsvDatasourceDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.datasources.dto.CsvDatasourceDto();
		if("net.datenwerke.rs.base.client.datasources.dto.CsvDatasourceConfigDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.datasources.dto.CsvDatasourceConfigDto();
		if("net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceDto();
		if("net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceConfigDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceConfigDto();
		if("net.datenwerke.rs.base.client.dbhelper.dto.DatabaseHelperDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.dbhelper.dto.DatabaseHelperDto();
		if("net.datenwerke.rs.base.client.parameters.blatext.dto.BlatextParameterDefinitionDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.parameters.blatext.dto.BlatextParameterDefinitionDto();
		if("net.datenwerke.rs.base.client.parameters.blatext.dto.BlatextParameterInstanceDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.parameters.blatext.dto.BlatextParameterInstanceDto();
		if("net.datenwerke.rs.base.client.parameters.datasource.dto.decorator.DatasourceParameterDataDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.parameters.datasource.dto.decorator.DatasourceParameterDataDtoDec();
		if("net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDefinitionDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDefinitionDto();
		if("net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterInstanceDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterInstanceDto();
		if("net.datenwerke.rs.base.client.parameters.datetime.dto.decorator.DateTimeParameterDefinitionDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.parameters.datetime.dto.decorator.DateTimeParameterDefinitionDtoDec();
		if("net.datenwerke.rs.base.client.parameters.datetime.dto.decorator.DateTimeParameterInstanceDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.parameters.datetime.dto.decorator.DateTimeParameterInstanceDtoDec();
		if("net.datenwerke.rs.base.client.parameters.headline.dto.HeadlineParameterDefinitionDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.parameters.headline.dto.HeadlineParameterDefinitionDto();
		if("net.datenwerke.rs.base.client.parameters.headline.dto.HeadlineParameterInstanceDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.parameters.headline.dto.HeadlineParameterInstanceDto();
		if("net.datenwerke.rs.base.client.parameters.separator.dto.SeparatorParameterDefinitionDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.parameters.separator.dto.SeparatorParameterDefinitionDto();
		if("net.datenwerke.rs.base.client.parameters.separator.dto.SeparatorParameterInstanceDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.parameters.separator.dto.SeparatorParameterInstanceDto();
		if("net.datenwerke.rs.base.client.parameters.string.dto.TextParameterDefinitionDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.parameters.string.dto.TextParameterDefinitionDto();
		if("net.datenwerke.rs.base.client.parameters.string.dto.TextParameterInstanceDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.parameters.string.dto.TextParameterInstanceDto();
		if("net.datenwerke.rs.base.client.reportengines.jasper.dto.decorator.JasperReportDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.reportengines.jasper.dto.decorator.JasperReportDtoDec();
		if("net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportJRXMLFileDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportJRXMLFileDto();
		if("net.datenwerke.rs.base.client.reportengines.jasper.dto.decorator.JasperReportVariantDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.reportengines.jasper.dto.decorator.JasperReportVariantDtoDec();
		if("net.datenwerke.rs.base.client.reportengines.jasper.dto.CompiledCSVJasperReportDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.reportengines.jasper.dto.CompiledCSVJasperReportDto();
		if("net.datenwerke.rs.base.client.reportengines.jasper.dto.CompiledHTMLJasperReportDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.reportengines.jasper.dto.CompiledHTMLJasperReportDto();
		if("net.datenwerke.rs.base.client.reportengines.jasper.dto.CompiledPNGJasperReportDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.reportengines.jasper.dto.CompiledPNGJasperReportDto();
		if("net.datenwerke.rs.base.client.jasperutils.dto.JasperParameterProposalDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.jasperutils.dto.JasperParameterProposalDto();
		if("net.datenwerke.rs.base.client.reportengines.table.dto.decorator.AdditionalColumnSpecDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.decorator.AdditionalColumnSpecDtoDec();
		if("net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnDtoDec();
		if("net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnReferenceDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnReferenceDtoDec();
		if("net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterRangeDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterRangeDtoDec();
		if("net.datenwerke.rs.base.client.reportengines.table.dto.decorator.TableReportDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.decorator.TableReportDtoDec();
		if("net.datenwerke.rs.base.client.reportengines.table.dto.decorator.TableReportVariantDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.decorator.TableReportVariantDtoDec();
		if("net.datenwerke.rs.base.client.reportengines.table.dto.decorator.BinaryColumnFilterDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.decorator.BinaryColumnFilterDtoDec();
		if("net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFilterDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFilterDtoDec();
		if("net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterDtoDec();
		if("net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterBlockDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterBlockDtoDec();
		if("net.datenwerke.rs.base.client.reportengines.table.dto.decorator.PreFilterDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.decorator.PreFilterDtoDec();
		if("net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatCurrencyDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatCurrencyDtoDec();
		if("net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatDateDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatDateDtoDec();
		if("net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatNumberDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatNumberDtoDec();
		if("net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatTemplateDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatTemplateDtoDec();
		if("net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatTextDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatTextDtoDec();
		if("net.datenwerke.rs.base.client.reportengines.table.dto.CompiledHTMLTableReportDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.CompiledHTMLTableReportDto();
		if("net.datenwerke.rs.base.client.reportengines.table.dto.RSStringTableRowDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.RSStringTableRowDto();
		if("net.datenwerke.rs.base.client.reportengines.table.dto.RSTableModelDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.RSTableModelDto();
		if("net.datenwerke.rs.base.client.reportengines.table.dto.RSTableRowDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.RSTableRowDto();
		if("net.datenwerke.rs.base.client.reportengines.table.dto.TableDefinitionDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.base.client.reportengines.table.dto.TableDefinitionDto();
		if("net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceConfigDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceConfigDto();
		if("net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceDefinitionDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceDefinitionDto();
		if("net.datenwerke.rs.birt.client.reportengines.dto.decorator.BirtReportDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.birt.client.reportengines.dto.decorator.BirtReportDtoDec();
		if("net.datenwerke.rs.birt.client.reportengines.dto.BirtReportFileDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.birt.client.reportengines.dto.BirtReportFileDto();
		if("net.datenwerke.rs.birt.client.reportengines.dto.decorator.BirtReportVariantDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.birt.client.reportengines.dto.decorator.BirtReportVariantDtoDec();
		if("net.datenwerke.rs.birt.client.reportengines.dto.CompiledHTMLBirtReportDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.birt.client.reportengines.dto.CompiledHTMLBirtReportDto();
		if("net.datenwerke.rs.birt.client.reportengines.dto.CompiledPNGBirtReportDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.birt.client.reportengines.dto.CompiledPNGBirtReportDto();
		if("net.datenwerke.rs.birt.client.utils.dto.BirtParameterProposalDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.birt.client.utils.dto.BirtParameterProposalDto();
		if("net.datenwerke.rs.box.client.box.dto.BoxDatasinkDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.box.client.box.dto.BoxDatasinkDto();
		if("net.datenwerke.rs.computedcolumns.client.computedcolumns.dto.ComputedColumnDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.computedcolumns.client.computedcolumns.dto.ComputedColumnDto();
		if("net.datenwerke.rs.condition.client.condition.dto.decorator.ReportConditionDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.condition.client.condition.dto.decorator.ReportConditionDtoDec();
		if("net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkContainerDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkContainerDto();
		if("net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkFolderDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkFolderDto();
		if("net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto();
		if("net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto();
		if("net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceFolderDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceFolderDto();
		if("net.datenwerke.rs.core.client.i18tools.dto.FormatPatternsDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.core.client.i18tools.dto.FormatPatternsDto();
		if("net.datenwerke.rs.core.client.reportexporter.dto.decorator.RECCsvDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.core.client.reportexporter.dto.decorator.RECCsvDtoDec();
		if("net.datenwerke.rs.core.client.reportexporter.dto.decorator.RECJxlsDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.core.client.reportexporter.dto.decorator.RECJxlsDtoDec();
		if("net.datenwerke.rs.core.client.reportmanager.dto.ReportFolderDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.core.client.reportmanager.dto.ReportFolderDto();
		if("net.datenwerke.rs.core.client.reportmanager.dto.reports.decorator.ReportDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.core.client.reportmanager.dto.reports.decorator.ReportDtoDec();
		if("net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportBytePropertyDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportBytePropertyDto();
		if("net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportMetadataDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportMetadataDto();
		if("net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportPropertyDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportPropertyDto();
		if("net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportServerSideStringPropertyDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportServerSideStringPropertyDto();
		if("net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportStringPropertyDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportStringPropertyDto();
		if("net.datenwerke.rs.crystal.client.crystal.dto.CrystalParameterProposalDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.crystal.client.crystal.dto.CrystalParameterProposalDto();
		if("net.datenwerke.rs.crystal.client.crystal.dto.decorator.CrystalReportDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.crystal.client.crystal.dto.decorator.CrystalReportDtoDec();
		if("net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportFileDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportFileDto();
		if("net.datenwerke.rs.crystal.client.crystal.dto.decorator.CrystalReportVariantDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.crystal.client.crystal.dto.decorator.CrystalReportVariantDtoDec();
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.FavoriteListDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.FavoriteListDtoDec();
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.FavoriteListDadgetDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.FavoriteListDadgetDtoDec();
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.FavoriteListEntryDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.FavoriteListEntryDtoDec();
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.LibraryDadgetDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.LibraryDadgetDtoDec();
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.ParameterDadgetDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.ParameterDadgetDtoDec();
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.ReportDadgetDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.ReportDadgetDtoDec();
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.StaticHtmlDadgetDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.StaticHtmlDadgetDtoDec();
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.UrlDadgetDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.UrlDadgetDtoDec();
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetNodeDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetNodeDto();
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DashboardDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DashboardDtoDec();
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DashboardContainerDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DashboardContainerDtoDec();
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardFolderDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardFolderDto();
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardNodeDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardNodeDto();
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DashboardReferenceDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DashboardReferenceDtoDec();
		if("net.datenwerke.rs.dropbox.client.dropbox.dto.DropboxDatasinkDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.dropbox.client.dropbox.dto.DropboxDatasinkDto();
		if("net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleDto();
		if("net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleEntryDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleEntryDto();
		if("net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.EmailDatasinkDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.EmailDatasinkDto();
		if("net.datenwerke.rs.fileserver.client.fileserver.dto.decorator.FileServerFileDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.fileserver.client.fileserver.dto.decorator.FileServerFileDtoDec();
		if("net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto();
		if("net.datenwerke.rs.fileserver.client.fileserver.dto.EditCommandResultExtensionDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.fileserver.client.fileserver.dto.EditCommandResultExtensionDto();
		if("net.datenwerke.rs.ftp.client.ftp.dto.FtpDatasinkDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.ftp.client.ftp.dto.FtpDatasinkDto();
		if("net.datenwerke.rs.ftp.client.ftp.dto.FtpsDatasinkDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.ftp.client.ftp.dto.FtpsDatasinkDto();
		if("net.datenwerke.rs.ftp.client.ftp.dto.SftpDatasinkDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.ftp.client.ftp.dto.SftpDatasinkDto();
		if("net.datenwerke.rs.globalconstants.client.globalconstants.dto.GlobalConstantDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.globalconstants.client.globalconstants.dto.GlobalConstantDto();
		if("net.datenwerke.rs.googledrive.client.googledrive.dto.GoogleDriveDatasinkDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.googledrive.client.googledrive.dto.GoogleDriveDatasinkDto();
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorColumnConfigDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorColumnConfigDtoDec();
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorConfigDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorConfigDto();
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorDataDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorDataDtoDec();
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorRecordDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorRecordDtoDec();
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorRecordEntryDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorRecordEntryDtoDec();
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.DateSelectionListEditorDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.DateSelectionListEditorDtoDec();
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.DecimalSelectionListEditorDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.DecimalSelectionListEditorDtoDec();
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.DoubleSelectionListEditorDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.DoubleSelectionListEditorDtoDec();
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.FloatSelectionListEditorDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.FloatSelectionListEditorDtoDec();
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.IntBooleanEditorDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.IntBooleanEditorDtoDec();
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.IntSelectionListEditorDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.IntSelectionListEditorDtoDec();
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.LongSelectionListEditorDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.LongSelectionListEditorDtoDec();
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.TextAreaEditorDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.TextAreaEditorDtoDec();
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.TextBooleanEditorDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.TextBooleanEditorDtoDec();
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.TextDateEditorDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.TextDateEditorDtoDec();
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.TextSelectionListEditorDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.TextSelectionListEditorDtoDec();
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.CustomValidatorDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.CustomValidatorDtoDec();
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.EmptyValidatorDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.EmptyValidatorDtoDec();
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.FixedLengthValidatorDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.FixedLengthValidatorDtoDec();
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxBigDecimalValidatorDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxBigDecimalValidatorDtoDec();
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxDateValidatorDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxDateValidatorDtoDec();
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxDoubleValidatorDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxDoubleValidatorDtoDec();
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxFloatValidatorDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxFloatValidatorDtoDec();
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxIntegerValidatorDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxIntegerValidatorDtoDec();
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxLengthValidatorDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxLengthValidatorDtoDec();
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxLongValidatorDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxLongValidatorDtoDec();
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinBigDecimalValidatorDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinBigDecimalValidatorDtoDec();
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinDateValidatorDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinDateValidatorDtoDec();
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinDoubleValidatorDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinDoubleValidatorDtoDec();
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinFloatValidatorDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinFloatValidatorDtoDec();
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinIntegerValidatorDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinIntegerValidatorDtoDec();
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinLengthValidatorDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinLengthValidatorDtoDec();
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinLongValidatorDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinLongValidatorDtoDec();
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.RegExValidatorDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.RegExValidatorDtoDec();
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorReportDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorReportDtoDec();
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorReportVariantDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorReportVariantDtoDec();
		if("net.datenwerke.rs.incubator.client.jaspertotable.dto.decorator.JasperToTableConfigDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.incubator.client.jaspertotable.dto.decorator.JasperToTableConfigDtoDec();
		if("net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.decorator.JxlsReportDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.decorator.JxlsReportDtoDec();
		if("net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportFileDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportFileDto();
		if("net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.decorator.JxlsReportVariantDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.decorator.JxlsReportVariantDtoDec();
		if("net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto.LocalFileSystemDatasinkDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto.LocalFileSystemDatasinkDto();
		if("net.datenwerke.rs.onedrive.client.onedrive.dto.OneDriveDatasinkDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.onedrive.client.onedrive.dto.OneDriveDatasinkDto();
		if("net.datenwerke.rs.printer.client.printer.dto.PrinterDatasinkDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.printer.client.printer.dto.PrinterDatasinkDto();
		if("net.datenwerke.rs.remotersserver.client.remotersserver.dto.RemoteRsServerDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.remotersserver.client.remotersserver.dto.RemoteRsServerDto();
		if("net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerContainerDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerContainerDto();
		if("net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerFolderDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerFolderDto();
		if("net.datenwerke.rs.reportdoc.client.dto.DeployAnalyzeCommandResultExtensionDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.reportdoc.client.dto.DeployAnalyzeCommandResultExtensionDto();
		if("net.datenwerke.rs.reportdoc.client.dto.VariantTestCommandResultExtensionDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.reportdoc.client.dto.VariantTestCommandResultExtensionDto();
		if("net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceDto();
		if("net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceConfigDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceConfigDto();
		if("net.datenwerke.rs.saiku.client.saiku.dto.decorator.SaikuReportDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.saiku.client.saiku.dto.decorator.SaikuReportDtoDec();
		if("net.datenwerke.rs.saiku.client.saiku.dto.decorator.SaikuReportVariantDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.saiku.client.saiku.dto.decorator.SaikuReportVariantDtoDec();
		if("net.datenwerke.rs.saiku.client.saiku.dto.decorator.RECSaikuChartDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.saiku.client.saiku.dto.decorator.RECSaikuChartDtoDec();
		if("net.datenwerke.rs.samba.client.samba.dto.SambaDatasinkDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.samba.client.samba.dto.SambaDatasinkDto();
		if("net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto.decorator.ExecutedReportFileReferenceDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto.decorator.ExecutedReportFileReferenceDtoDec();
		if("net.datenwerke.rs.scheduleasfile.client.scheduleasfile.filter.dto.TeamSpaceReportJobFilterDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.scheduleasfile.client.scheduleasfile.filter.dto.TeamSpaceReportJobFilterDto();
		if("net.datenwerke.rs.scheduler.client.scheduler.dto.ReportServerJobFilterDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.scheduler.client.scheduler.dto.ReportServerJobFilterDto();
		if("net.datenwerke.rs.scp.client.scp.dto.ScpDatasinkDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.scp.client.scp.dto.ScpDatasinkDto();
		if("net.datenwerke.rs.scriptdatasink.client.scriptdatasink.dto.ScriptDatasinkDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.scriptdatasink.client.scriptdatasink.dto.ScriptDatasinkDto();
		if("net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto.ScriptDatasourceDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto.ScriptDatasourceDto();
		if("net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto.ScriptDatasourceConfigDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto.ScriptDatasourceConfigDto();
		if("net.datenwerke.rs.scripting.client.scripting.dto.AddMenuEntryExtensionDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.scripting.client.scripting.dto.AddMenuEntryExtensionDto();
		if("net.datenwerke.rs.scripting.client.scripting.dto.AddMenuSeparatorEntryExtensionDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.scripting.client.scripting.dto.AddMenuSeparatorEntryExtensionDto();
		if("net.datenwerke.rs.scripting.client.scripting.dto.AddReportExportFormatProviderDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.scripting.client.scripting.dto.AddReportExportFormatProviderDto();
		if("net.datenwerke.rs.scripting.client.scripting.dto.AddStatusbBarLabelExtensionDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.scripting.client.scripting.dto.AddStatusbBarLabelExtensionDto();
		if("net.datenwerke.rs.scripting.client.scripting.dto.AddToolbarEntryExtensionDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.scripting.client.scripting.dto.AddToolbarEntryExtensionDto();
		if("net.datenwerke.rs.scripting.client.scripting.dto.DisplayConditionDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.scripting.client.scripting.dto.DisplayConditionDto();
		if("net.datenwerke.rs.scriptreport.client.scriptreport.dto.decorator.ScriptReportDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.scriptreport.client.scriptreport.dto.decorator.ScriptReportDtoDec();
		if("net.datenwerke.rs.scriptreport.client.scriptreport.dto.decorator.ScriptReportVariantDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.scriptreport.client.scriptreport.dto.decorator.ScriptReportVariantDtoDec();
		if("net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.ScriptParameterDefinitionDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.ScriptParameterDefinitionDto();
		if("net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.ScriptParameterInstanceDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.ScriptParameterInstanceDto();
		if("net.datenwerke.rs.search.client.search.dto.decorator.SearchFilterDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.search.client.search.dto.decorator.SearchFilterDtoDec();
		if("net.datenwerke.rs.search.client.search.dto.decorator.SearchResultEntryDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.search.client.search.dto.decorator.SearchResultEntryDtoDec();
		if("net.datenwerke.rs.search.client.search.dto.decorator.SearchResultListDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.search.client.search.dto.decorator.SearchResultListDtoDec();
		if("net.datenwerke.rs.search.client.search.dto.SearchResultTagDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.search.client.search.dto.SearchResultTagDto();
		if("net.datenwerke.rs.search.client.search.dto.SearchResultTagTypeDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.search.client.search.dto.SearchResultTagTypeDto();
		if("net.datenwerke.rs.tabledatasink.client.tabledatasink.dto.TableDatasinkDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.tabledatasink.client.tabledatasink.dto.TableDatasinkDto();
		if("net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.RECTableTemplateDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.RECTableTemplateDto();
		if("net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportByteTemplateDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportByteTemplateDto();
		if("net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportStringTemplateDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportStringTemplateDto();
		if("net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportTemplateListDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportTemplateListDto();
		if("net.datenwerke.rs.teamspace.client.teamspace.dto.AppPropertyDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.teamspace.client.teamspace.dto.AppPropertyDto();
		if("net.datenwerke.rs.teamspace.client.teamspace.dto.decorator.TeamSpaceDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.teamspace.client.teamspace.dto.decorator.TeamSpaceDtoDec();
		if("net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceAppDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceAppDto();
		if("net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceMemberDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceMemberDto();
		if("net.datenwerke.rs.teamspace.client.teamspace.security.TeamSpaceSecureeDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.teamspace.client.teamspace.security.TeamSpaceSecureeDto();
		if("net.datenwerke.rs.teamspace.client.teamspace.security.rights.TeamSpaceAdministratorDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.teamspace.client.teamspace.security.rights.TeamSpaceAdministratorDto();
		if("net.datenwerke.rs.terminal.client.terminal.dto.decorator.AutocompleteResultDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.terminal.client.terminal.dto.decorator.AutocompleteResultDtoDec();
		if("net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultDtoDec();
		if("net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultAnchorDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultAnchorDtoDec();
		if("net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultHtmlDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultHtmlDtoDec();
		if("net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultHyperlinkDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultHyperlinkDtoDec();
		if("net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultLineDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultLineDtoDec();
		if("net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultListDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultListDtoDec();
		if("net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultTableDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultTableDtoDec();
		if("net.datenwerke.rs.terminal.client.terminal.dto.CreJavaScriptDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.terminal.client.terminal.dto.CreJavaScriptDto();
		if("net.datenwerke.rs.terminal.client.terminal.dto.CreMessageDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.terminal.client.terminal.dto.CreMessageDto();
		if("net.datenwerke.rs.terminal.client.terminal.dto.CreOverlayDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.terminal.client.terminal.dto.CreOverlayDto();
		if("net.datenwerke.rs.terminal.client.terminal.dto.InteractiveResultModifierDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.terminal.client.terminal.dto.InteractiveResultModifierDto();
		if("net.datenwerke.rs.terminal.client.terminal.dto.PressKeyResultModifierDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.terminal.client.terminal.dto.PressKeyResultModifierDto();
		if("net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.TsDiskFileReferenceDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.TsDiskFileReferenceDtoDec();
		if("net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFolderDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFolderDto();
		if("net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.TsDiskReportReferenceDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.TsDiskReportReferenceDtoDec();
		if("net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskRootDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskRootDto();
		if("net.datenwerke.rs.uservariables.client.parameters.dto.UserVariableParameterDefinitionDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.uservariables.client.parameters.dto.UserVariableParameterDefinitionDto();
		if("net.datenwerke.rs.uservariables.client.parameters.dto.decorator.UserVariableParameterInstanceDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.rs.uservariables.client.parameters.dto.decorator.UserVariableParameterInstanceDtoDec();
		if("net.datenwerke.rs.uservariables.client.variabletypes.list.dto.ListUserVariableDefinitionDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.uservariables.client.variabletypes.list.dto.ListUserVariableDefinitionDto();
		if("net.datenwerke.rs.uservariables.client.variabletypes.list.dto.ListUserVariableInstanceDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.uservariables.client.variabletypes.list.dto.ListUserVariableInstanceDto();
		if("net.datenwerke.rs.uservariables.client.variabletypes.string.dto.StringUserVariableDefinitionDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.uservariables.client.variabletypes.string.dto.StringUserVariableDefinitionDto();
		if("net.datenwerke.rs.uservariables.client.variabletypes.string.dto.StringUserVariableInstanceDto".equals(dtoClassName))
			return (X) new net.datenwerke.rs.uservariables.client.variabletypes.string.dto.StringUserVariableInstanceDto();
		if("net.datenwerke.scheduler.client.scheduler.dto.history.ActionEntryDto".equals(dtoClassName))
			return (X) new net.datenwerke.scheduler.client.scheduler.dto.history.ActionEntryDto();
		if("net.datenwerke.scheduler.client.scheduler.dto.history.ExecutionLogEntryDto".equals(dtoClassName))
			return (X) new net.datenwerke.scheduler.client.scheduler.dto.history.ExecutionLogEntryDto();
		if("net.datenwerke.scheduler.client.scheduler.dto.history.HistoryEntryPropertyDto".equals(dtoClassName))
			return (X) new net.datenwerke.scheduler.client.scheduler.dto.history.HistoryEntryPropertyDto();
		if("net.datenwerke.scheduler.client.scheduler.dto.history.JobEntryDto".equals(dtoClassName))
			return (X) new net.datenwerke.scheduler.client.scheduler.dto.history.JobEntryDto();
		if("net.datenwerke.scheduler.client.scheduler.dto.history.JobHistoryDto".equals(dtoClassName))
			return (X) new net.datenwerke.scheduler.client.scheduler.dto.history.JobHistoryDto();
		if("net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterConfigurationDto".equals(dtoClassName))
			return (X) new net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterConfigurationDto();
		if("net.datenwerke.scheduler.client.scheduler.dto.config.complex.DailyConfigDto".equals(dtoClassName))
			return (X) new net.datenwerke.scheduler.client.scheduler.dto.config.complex.DailyConfigDto();
		if("net.datenwerke.scheduler.client.scheduler.dto.config.complex.DateTriggerConfigDto".equals(dtoClassName))
			return (X) new net.datenwerke.scheduler.client.scheduler.dto.config.complex.DateTriggerConfigDto();
		if("net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthlyNthDayConfigDto".equals(dtoClassName))
			return (X) new net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthlyNthDayConfigDto();
		if("net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthlyNthDayOfWeekConfigDto".equals(dtoClassName))
			return (X) new net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthlyNthDayOfWeekConfigDto();
		if("net.datenwerke.scheduler.client.scheduler.dto.config.complex.decorator.TimeDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.scheduler.client.scheduler.dto.config.complex.decorator.TimeDtoDec();
		if("net.datenwerke.scheduler.client.scheduler.dto.config.complex.WeeklyConfigDto".equals(dtoClassName))
			return (X) new net.datenwerke.scheduler.client.scheduler.dto.config.complex.WeeklyConfigDto();
		if("net.datenwerke.scheduler.client.scheduler.dto.config.complex.YearlyAtDateConfigDto".equals(dtoClassName))
			return (X) new net.datenwerke.scheduler.client.scheduler.dto.config.complex.YearlyAtDateConfigDto();
		if("net.datenwerke.scheduler.client.scheduler.dto.config.complex.YearlyNthDayOfWeekConfigDto".equals(dtoClassName))
			return (X) new net.datenwerke.scheduler.client.scheduler.dto.config.complex.YearlyNthDayOfWeekConfigDto();
		if("net.datenwerke.security.client.security.dto.SecurityServiceSecureeDto".equals(dtoClassName))
			return (X) new net.datenwerke.security.client.security.dto.SecurityServiceSecureeDto();
		if("net.datenwerke.security.client.security.dto.decorator.AceDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.security.client.security.dto.decorator.AceDtoDec();
		if("net.datenwerke.security.client.security.dto.decorator.AceAccessMapDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.security.client.security.dto.decorator.AceAccessMapDtoDec();
		if("net.datenwerke.security.client.security.dto.HierarchicalAceDto".equals(dtoClassName))
			return (X) new net.datenwerke.security.client.security.dto.HierarchicalAceDto();
		if("net.datenwerke.security.client.security.dto.DeleteDto".equals(dtoClassName))
			return (X) new net.datenwerke.security.client.security.dto.DeleteDto();
		if("net.datenwerke.security.client.security.dto.ExecuteDto".equals(dtoClassName))
			return (X) new net.datenwerke.security.client.security.dto.ExecuteDto();
		if("net.datenwerke.security.client.security.dto.GrantAccessDto".equals(dtoClassName))
			return (X) new net.datenwerke.security.client.security.dto.GrantAccessDto();
		if("net.datenwerke.security.client.security.dto.ReadDto".equals(dtoClassName))
			return (X) new net.datenwerke.security.client.security.dto.ReadDto();
		if("net.datenwerke.security.client.security.dto.WriteDto".equals(dtoClassName))
			return (X) new net.datenwerke.security.client.security.dto.WriteDto();
		if("net.datenwerke.security.client.usermanager.dto.GroupDto".equals(dtoClassName))
			return (X) new net.datenwerke.security.client.usermanager.dto.GroupDto();
		if("net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto".equals(dtoClassName))
			return (X) new net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto();
		if("net.datenwerke.security.client.usermanager.dto.decorator.UserDtoDec".equals(dtoClassName))
			return (X) new net.datenwerke.security.client.usermanager.dto.decorator.UserDtoDec();
		if("net.datenwerke.security.client.usermanager.dto.UserPropertyDto".equals(dtoClassName))
			return (X) new net.datenwerke.security.client.usermanager.dto.UserPropertyDto();
		throw new IllegalArgumentException("unrecognized dto: " + dtoClassName);
	}


	@Override
	public Class<? extends Dto2PosoMapper> lookupPosoMapper(Class<? extends RsDto> dtoClass)  {
		if(net.datenwerke.gf.client.history.dto.HistoryLinkDto.class.equals(dtoClass))
			return net.datenwerke.gf.client.history.dto.posomap.HistoryLinkDto2PosoMap.class;
		if(net.datenwerke.gf.client.juel.dto.JuelResultDto.class.equals(dtoClass))
			return net.datenwerke.gf.client.juel.dto.posomap.JuelResultDto2PosoMap.class;
		if(net.datenwerke.gf.client.juel.dto.decorator.JuelResultDtoDec.class.equals(dtoClass))
			return net.datenwerke.gf.client.juel.dto.posomap.JuelResultDto2PosoMap.class;
		if(net.datenwerke.gf.client.juel.dto.JuelResultTypeDto.class.equals(dtoClass))
			return net.datenwerke.gf.client.juel.dto.posomap.JuelResultTypeDto2PosoMap.class;
		if(net.datenwerke.rs.adminutils.client.logs.dto.ViewLogFileCommandResultExtensionDto.class.equals(dtoClass))
			return net.datenwerke.rs.adminutils.client.logs.dto.posomap.ViewLogFileCommandResultExtensionDto2PosoMap.class;
		if(net.datenwerke.rs.amazons3.client.amazons3.dto.AmazonS3DatasinkDto.class.equals(dtoClass))
			return net.datenwerke.rs.amazons3.client.amazons3.dto.posomap.AmazonS3DatasinkDto2PosoMap.class;
		if(net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.FileSelectionParameterDefinitionDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.posomap.FileSelectionParameterDefinitionDto2PosoMap.class;
		if(net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.FileSelectionParameterInstanceDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.posomap.FileSelectionParameterInstanceDto2PosoMap.class;
		if(net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.decorator.FileSelectionParameterInstanceDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.posomap.FileSelectionParameterInstanceDto2PosoMap.class;
		if(net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.SelectedParameterFileDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.posomap.SelectedParameterFileDto2PosoMap.class;
		if(net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.UploadedParameterFileDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.posomap.UploadedParameterFileDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.datasources.dto.ArgumentDatasourceConnectorDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.datasources.dto.posomap.ArgumentDatasourceConnectorDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.datasources.dto.decorator.ArgumentDatasourceConnectorDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.datasources.dto.posomap.ArgumentDatasourceConnectorDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.datasources.dto.posomap.DatasourceConnectorDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.datasources.dto.decorator.DatasourceConnectorDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.datasources.dto.posomap.DatasourceConnectorDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorConfigDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.datasources.dto.posomap.DatasourceConnectorConfigDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.datasources.dto.TextDatasourceConnectorDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.datasources.dto.posomap.TextDatasourceConnectorDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.datasources.dto.UrlDatasourceConnectorDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.datasources.dto.posomap.UrlDatasourceConnectorDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.datasources.dto.CsvDatasourceDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.datasources.dto.posomap.CsvDatasourceDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.datasources.dto.CsvDatasourceConfigDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.datasources.dto.posomap.CsvDatasourceConfigDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.datasources.dto.posomap.DatabaseDatasourceDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceConfigDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.datasources.dto.posomap.DatabaseDatasourceConfigDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.datasources.dto.FormatBasedDatasourceConfigDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.datasources.dto.posomap.FormatBasedDatasourceConfigDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.datasources.dto.FormatBasedDatasourceDefinitionDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.datasources.dto.posomap.FormatBasedDatasourceDefinitionDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.dbhelper.dto.DatabaseHelperDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.dbhelper.dto.posomap.DatabaseHelperDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.parameters.blatext.dto.BlatextParameterDefinitionDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.parameters.blatext.dto.posomap.BlatextParameterDefinitionDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.parameters.blatext.dto.BlatextParameterInstanceDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.parameters.blatext.dto.posomap.BlatextParameterInstanceDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.parameters.datasource.dto.BoxLayoutModeDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.parameters.datasource.dto.posomap.BoxLayoutModeDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.parameters.datasource.dto.BoxLayoutPackModeDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.parameters.datasource.dto.posomap.BoxLayoutPackModeDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDataDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.parameters.datasource.dto.posomap.DatasourceParameterDataDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.parameters.datasource.dto.decorator.DatasourceParameterDataDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.parameters.datasource.dto.posomap.DatasourceParameterDataDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDefinitionDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.parameters.datasource.dto.posomap.DatasourceParameterDefinitionDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterInstanceDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.parameters.datasource.dto.posomap.DatasourceParameterInstanceDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.parameters.datasource.dto.ModeDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.parameters.datasource.dto.posomap.ModeDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.parameters.datasource.dto.MultiSelectionModeDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.parameters.datasource.dto.posomap.MultiSelectionModeDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.parameters.datasource.dto.SingleSelectionModeDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.parameters.datasource.dto.posomap.SingleSelectionModeDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.parameters.datetime.dto.DateTimeParameterDefinitionDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.parameters.datetime.dto.posomap.DateTimeParameterDefinitionDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.parameters.datetime.dto.decorator.DateTimeParameterDefinitionDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.parameters.datetime.dto.posomap.DateTimeParameterDefinitionDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.parameters.datetime.dto.DateTimeParameterInstanceDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.parameters.datetime.dto.posomap.DateTimeParameterInstanceDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.parameters.datetime.dto.decorator.DateTimeParameterInstanceDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.parameters.datetime.dto.posomap.DateTimeParameterInstanceDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.parameters.datetime.dto.ModeDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.parameters.datetime.dto.posomap.ModeDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.parameters.headline.dto.HeadlineParameterDefinitionDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.parameters.headline.dto.posomap.HeadlineParameterDefinitionDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.parameters.headline.dto.HeadlineParameterInstanceDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.parameters.headline.dto.posomap.HeadlineParameterInstanceDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.parameters.separator.dto.SeparatorParameterDefinitionDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.parameters.separator.dto.posomap.SeparatorParameterDefinitionDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.parameters.separator.dto.SeparatorParameterInstanceDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.parameters.separator.dto.posomap.SeparatorParameterInstanceDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.parameters.string.dto.TextParameterDefinitionDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.parameters.string.dto.posomap.TextParameterDefinitionDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.parameters.string.dto.TextParameterInstanceDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.parameters.string.dto.posomap.TextParameterInstanceDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.jasper.dto.posomap.JasperReportDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.jasper.dto.decorator.JasperReportDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.jasper.dto.posomap.JasperReportDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportJRXMLFileDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.jasper.dto.posomap.JasperReportJRXMLFileDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportVariantDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.jasper.dto.posomap.JasperReportVariantDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.jasper.dto.decorator.JasperReportVariantDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.jasper.dto.posomap.JasperReportVariantDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.jasper.dto.CompiledCSVJasperReportDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.jasper.dto.posomap.CompiledCSVJasperReportDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.jasper.dto.CompiledHTMLJasperReportDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.jasper.dto.posomap.CompiledHTMLJasperReportDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.jasper.dto.CompiledPNGJasperReportDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.jasper.dto.posomap.CompiledPNGJasperReportDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.jasperutils.dto.JasperParameterProposalDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.jasperutils.dto.posomap.JasperParameterProposalDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.AdditionalColumnSpecDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.AdditionalColumnSpecDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.AdditionalColumnSpecDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.AdditionalColumnSpecDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.AggregateFunctionDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.AggregateFunctionDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.ColumnDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.ColumnDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.ColumnReferenceDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.ColumnReferenceDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnReferenceDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.ColumnReferenceDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.FilterRangeDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.FilterRangeDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterRangeDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.FilterRangeDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.NullHandlingDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.NullHandlingDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.OrderDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.OrderDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.TableReportDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.TableReportDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.TableReportDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.TableReportVariantDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.TableReportVariantDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.TableReportVariantDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.TableReportVariantDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.BinaryColumnFilterDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.BinaryColumnFilterDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.BinaryColumnFilterDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.BinaryColumnFilterDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.BinaryOperatorDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.BinaryOperatorDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.BlockTypeDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.BlockTypeDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFilterDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.ColumnFilterDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFilterDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.ColumnFilterDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.FilterDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.FilterDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.FilterDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.FilterBlockDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.FilterBlockDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterBlockDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.FilterBlockDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.FilterSpecDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.FilterSpecDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterSpecDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.FilterSpecDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.PreFilterDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.PreFilterDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.PreFilterDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.PreFilterDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.ColumnFormatDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.ColumnFormatDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatCurrencyDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.ColumnFormatCurrencyDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatCurrencyDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.ColumnFormatCurrencyDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatDateDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.ColumnFormatDateDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatDateDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.ColumnFormatDateDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatNumberDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.ColumnFormatNumberDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatNumberDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.ColumnFormatNumberDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatTemplateDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.ColumnFormatTemplateDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatTemplateDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.ColumnFormatTemplateDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatTextDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.ColumnFormatTextDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatTextDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.ColumnFormatTextDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.CurrencyTypeDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.CurrencyTypeDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.NumberTypeDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.NumberTypeDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.CompiledHTMLTableReportDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.CompiledHTMLTableReportDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.RSStringTableRowDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.RSStringTableRowDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.RSTableModelDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.RSTableModelDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.RSTableRowDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.RSTableRowDto2PosoMap.class;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.TableDefinitionDto.class.equals(dtoClass))
			return net.datenwerke.rs.base.client.reportengines.table.dto.posomap.TableDefinitionDto2PosoMap.class;
		if(net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceConfigDto.class.equals(dtoClass))
			return net.datenwerke.rs.birt.client.datasources.dto.posomap.BirtReportDatasourceConfigDto2PosoMap.class;
		if(net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceDefinitionDto.class.equals(dtoClass))
			return net.datenwerke.rs.birt.client.datasources.dto.posomap.BirtReportDatasourceDefinitionDto2PosoMap.class;
		if(net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceTargetTypeDto.class.equals(dtoClass))
			return net.datenwerke.rs.birt.client.datasources.dto.posomap.BirtReportDatasourceTargetTypeDto2PosoMap.class;
		if(net.datenwerke.rs.birt.client.reportengines.dto.BirtReportDto.class.equals(dtoClass))
			return net.datenwerke.rs.birt.client.reportengines.dto.posomap.BirtReportDto2PosoMap.class;
		if(net.datenwerke.rs.birt.client.reportengines.dto.decorator.BirtReportDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.birt.client.reportengines.dto.posomap.BirtReportDto2PosoMap.class;
		if(net.datenwerke.rs.birt.client.reportengines.dto.BirtReportFileDto.class.equals(dtoClass))
			return net.datenwerke.rs.birt.client.reportengines.dto.posomap.BirtReportFileDto2PosoMap.class;
		if(net.datenwerke.rs.birt.client.reportengines.dto.BirtReportVariantDto.class.equals(dtoClass))
			return net.datenwerke.rs.birt.client.reportengines.dto.posomap.BirtReportVariantDto2PosoMap.class;
		if(net.datenwerke.rs.birt.client.reportengines.dto.decorator.BirtReportVariantDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.birt.client.reportengines.dto.posomap.BirtReportVariantDto2PosoMap.class;
		if(net.datenwerke.rs.birt.client.reportengines.dto.CompiledHTMLBirtReportDto.class.equals(dtoClass))
			return net.datenwerke.rs.birt.client.reportengines.dto.posomap.CompiledHTMLBirtReportDto2PosoMap.class;
		if(net.datenwerke.rs.birt.client.reportengines.dto.CompiledPNGBirtReportDto.class.equals(dtoClass))
			return net.datenwerke.rs.birt.client.reportengines.dto.posomap.CompiledPNGBirtReportDto2PosoMap.class;
		if(net.datenwerke.rs.birt.client.utils.dto.BirtParameterProposalDto.class.equals(dtoClass))
			return net.datenwerke.rs.birt.client.utils.dto.posomap.BirtParameterProposalDto2PosoMap.class;
		if(net.datenwerke.rs.box.client.box.dto.BoxDatasinkDto.class.equals(dtoClass))
			return net.datenwerke.rs.box.client.box.dto.posomap.BoxDatasinkDto2PosoMap.class;
		if(net.datenwerke.rs.computedcolumns.client.computedcolumns.dto.ComputedColumnDto.class.equals(dtoClass))
			return net.datenwerke.rs.computedcolumns.client.computedcolumns.dto.posomap.ComputedColumnDto2PosoMap.class;
		if(net.datenwerke.rs.condition.client.condition.dto.ReportConditionDto.class.equals(dtoClass))
			return net.datenwerke.rs.condition.client.condition.dto.posomap.ReportConditionDto2PosoMap.class;
		if(net.datenwerke.rs.condition.client.condition.dto.decorator.ReportConditionDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.condition.client.condition.dto.posomap.ReportConditionDto2PosoMap.class;
		if(net.datenwerke.rs.core.client.datasinkmanager.dto.AbstractDatasinkManagerNodeDto.class.equals(dtoClass))
			return net.datenwerke.rs.core.client.datasinkmanager.dto.posomap.AbstractDatasinkManagerNodeDto2PosoMap.class;
		if(net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkContainerDto.class.equals(dtoClass))
			return net.datenwerke.rs.core.client.datasinkmanager.dto.posomap.DatasinkContainerDto2PosoMap.class;
		if(net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkContainerProviderDto.class.equals(dtoClass))
			return net.datenwerke.rs.core.client.datasinkmanager.dto.posomap.DatasinkContainerProviderDto2PosoMap.class;
		if(net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto.class.equals(dtoClass))
			return net.datenwerke.rs.core.client.datasinkmanager.dto.posomap.DatasinkDefinitionDto2PosoMap.class;
		if(net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkFolderDto.class.equals(dtoClass))
			return net.datenwerke.rs.core.client.datasinkmanager.dto.posomap.DatasinkFolderDto2PosoMap.class;
		if(net.datenwerke.rs.core.client.datasourcemanager.dto.AbstractDatasourceManagerNodeDto.class.equals(dtoClass))
			return net.datenwerke.rs.core.client.datasourcemanager.dto.posomap.AbstractDatasourceManagerNodeDto2PosoMap.class;
		if(net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto.class.equals(dtoClass))
			return net.datenwerke.rs.core.client.datasourcemanager.dto.posomap.DatasourceContainerDto2PosoMap.class;
		if(net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerProviderDto.class.equals(dtoClass))
			return net.datenwerke.rs.core.client.datasourcemanager.dto.posomap.DatasourceContainerProviderDto2PosoMap.class;
		if(net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto.class.equals(dtoClass))
			return net.datenwerke.rs.core.client.datasourcemanager.dto.posomap.DatasourceDefinitionDto2PosoMap.class;
		if(net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto.class.equals(dtoClass))
			return net.datenwerke.rs.core.client.datasourcemanager.dto.posomap.DatasourceDefinitionConfigDto2PosoMap.class;
		if(net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceFolderDto.class.equals(dtoClass))
			return net.datenwerke.rs.core.client.datasourcemanager.dto.posomap.DatasourceFolderDto2PosoMap.class;
		if(net.datenwerke.rs.core.client.i18tools.dto.FormatPatternsDto.class.equals(dtoClass))
			return net.datenwerke.rs.core.client.i18tools.dto.posomap.FormatPatternsDto2PosoMap.class;
		if(net.datenwerke.rs.core.client.parameters.dto.DatatypeDto.class.equals(dtoClass))
			return net.datenwerke.rs.core.client.parameters.dto.posomap.DatatypeDto2PosoMap.class;
		if(net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto.class.equals(dtoClass))
			return net.datenwerke.rs.core.client.parameters.dto.posomap.ParameterDefinitionDto2PosoMap.class;
		if(net.datenwerke.rs.core.client.parameters.dto.decorator.ParameterDefinitionDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.core.client.parameters.dto.posomap.ParameterDefinitionDto2PosoMap.class;
		if(net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto.class.equals(dtoClass))
			return net.datenwerke.rs.core.client.parameters.dto.posomap.ParameterInstanceDto2PosoMap.class;
		if(net.datenwerke.rs.core.client.reportexporter.dto.RECCsvDto.class.equals(dtoClass))
			return net.datenwerke.rs.core.client.reportexporter.dto.posomap.RECCsvDto2PosoMap.class;
		if(net.datenwerke.rs.core.client.reportexporter.dto.decorator.RECCsvDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.core.client.reportexporter.dto.posomap.RECCsvDto2PosoMap.class;
		if(net.datenwerke.rs.core.client.reportexporter.dto.RECJxlsDto.class.equals(dtoClass))
			return net.datenwerke.rs.core.client.reportexporter.dto.posomap.RECJxlsDto2PosoMap.class;
		if(net.datenwerke.rs.core.client.reportexporter.dto.decorator.RECJxlsDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.core.client.reportexporter.dto.posomap.RECJxlsDto2PosoMap.class;
		if(net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto.class.equals(dtoClass))
			return net.datenwerke.rs.core.client.reportexporter.dto.posomap.ReportExecutionConfigDto2PosoMap.class;
		if(net.datenwerke.rs.core.client.reportmanager.dto.reports.AbstractReportManagerNodeDto.class.equals(dtoClass))
			return net.datenwerke.rs.core.client.reportmanager.dto.reports.posomap.AbstractReportManagerNodeDto2PosoMap.class;
		if(net.datenwerke.rs.core.client.reportmanager.dto.ReportFolderDto.class.equals(dtoClass))
			return net.datenwerke.rs.core.client.reportmanager.dto.posomap.ReportFolderDto2PosoMap.class;
		if(net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto.class.equals(dtoClass))
			return net.datenwerke.rs.core.client.reportmanager.dto.reports.posomap.ReportDto2PosoMap.class;
		if(net.datenwerke.rs.core.client.reportmanager.dto.reports.decorator.ReportDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.core.client.reportmanager.dto.reports.posomap.ReportDto2PosoMap.class;
		if(net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportBytePropertyDto.class.equals(dtoClass))
			return net.datenwerke.rs.core.client.reportmanager.dto.reports.posomap.ReportBytePropertyDto2PosoMap.class;
		if(net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportMetadataDto.class.equals(dtoClass))
			return net.datenwerke.rs.core.client.reportmanager.dto.reports.posomap.ReportMetadataDto2PosoMap.class;
		if(net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportPropertyDto.class.equals(dtoClass))
			return net.datenwerke.rs.core.client.reportmanager.dto.reports.posomap.ReportPropertyDto2PosoMap.class;
		if(net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportServerSideStringPropertyDto.class.equals(dtoClass))
			return net.datenwerke.rs.core.client.reportmanager.dto.reports.posomap.ReportServerSideStringPropertyDto2PosoMap.class;
		if(net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportStringPropertyDto.class.equals(dtoClass))
			return net.datenwerke.rs.core.client.reportmanager.dto.reports.posomap.ReportStringPropertyDto2PosoMap.class;
		if(net.datenwerke.rs.core.client.reportmanager.dto.reports.ServerSidePropertyDto.class.equals(dtoClass))
			return net.datenwerke.rs.core.client.reportmanager.dto.reports.posomap.ServerSidePropertyDto2PosoMap.class;
		if(net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportVariantDto.class.equals(dtoClass))
			return net.datenwerke.rs.core.client.reportmanager.dto.interfaces.posomap.ReportVariantDto2PosoMap.class;
		if(net.datenwerke.rs.crystal.client.crystal.dto.CrystalParameterProposalDto.class.equals(dtoClass))
			return net.datenwerke.rs.crystal.client.crystal.dto.posomap.CrystalParameterProposalDto2PosoMap.class;
		if(net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportDto.class.equals(dtoClass))
			return net.datenwerke.rs.crystal.client.crystal.dto.posomap.CrystalReportDto2PosoMap.class;
		if(net.datenwerke.rs.crystal.client.crystal.dto.decorator.CrystalReportDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.crystal.client.crystal.dto.posomap.CrystalReportDto2PosoMap.class;
		if(net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportFileDto.class.equals(dtoClass))
			return net.datenwerke.rs.crystal.client.crystal.dto.posomap.CrystalReportFileDto2PosoMap.class;
		if(net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportVariantDto.class.equals(dtoClass))
			return net.datenwerke.rs.crystal.client.crystal.dto.posomap.CrystalReportVariantDto2PosoMap.class;
		if(net.datenwerke.rs.crystal.client.crystal.dto.decorator.CrystalReportVariantDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.crystal.client.crystal.dto.posomap.CrystalReportVariantDto2PosoMap.class;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListDto.class.equals(dtoClass))
			return net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.FavoriteListDto2PosoMap.class;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.FavoriteListDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.FavoriteListDto2PosoMap.class;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListDadgetDto.class.equals(dtoClass))
			return net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.FavoriteListDadgetDto2PosoMap.class;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.FavoriteListDadgetDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.FavoriteListDadgetDto2PosoMap.class;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListEntryDto.class.equals(dtoClass))
			return net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.FavoriteListEntryDto2PosoMap.class;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.FavoriteListEntryDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.FavoriteListEntryDto2PosoMap.class;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.LibraryDadgetDto.class.equals(dtoClass))
			return net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.LibraryDadgetDto2PosoMap.class;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.LibraryDadgetDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.LibraryDadgetDto2PosoMap.class;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.ParameterDadgetDto.class.equals(dtoClass))
			return net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.ParameterDadgetDto2PosoMap.class;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.ParameterDadgetDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.ParameterDadgetDto2PosoMap.class;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.ReportDadgetDto.class.equals(dtoClass))
			return net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.ReportDadgetDto2PosoMap.class;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.ReportDadgetDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.ReportDadgetDto2PosoMap.class;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.StaticHtmlDadgetDto.class.equals(dtoClass))
			return net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.StaticHtmlDadgetDto2PosoMap.class;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.StaticHtmlDadgetDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.StaticHtmlDadgetDto2PosoMap.class;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.UrlDadgetDto.class.equals(dtoClass))
			return net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.UrlDadgetDto2PosoMap.class;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.UrlDadgetDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.UrlDadgetDto2PosoMap.class;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.AbstractDashboardManagerNodeDto.class.equals(dtoClass))
			return net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.AbstractDashboardManagerNodeDto2PosoMap.class;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto.class.equals(dtoClass))
			return net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.DadgetDto2PosoMap.class;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DadgetDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.DadgetDto2PosoMap.class;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetContainerDto.class.equals(dtoClass))
			return net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.DadgetContainerDto2PosoMap.class;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetNodeDto.class.equals(dtoClass))
			return net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.DadgetNodeDto2PosoMap.class;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardDto.class.equals(dtoClass))
			return net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.DashboardDto2PosoMap.class;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DashboardDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.DashboardDto2PosoMap.class;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardContainerDto.class.equals(dtoClass))
			return net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.DashboardContainerDto2PosoMap.class;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DashboardContainerDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.DashboardContainerDto2PosoMap.class;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardFolderDto.class.equals(dtoClass))
			return net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.DashboardFolderDto2PosoMap.class;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardNodeDto.class.equals(dtoClass))
			return net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.DashboardNodeDto2PosoMap.class;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardReferenceDto.class.equals(dtoClass))
			return net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.DashboardReferenceDto2PosoMap.class;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DashboardReferenceDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.DashboardReferenceDto2PosoMap.class;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.LayoutTypeDto.class.equals(dtoClass))
			return net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.LayoutTypeDto2PosoMap.class;
		if(net.datenwerke.rs.dropbox.client.dropbox.dto.DropboxDatasinkDto.class.equals(dtoClass))
			return net.datenwerke.rs.dropbox.client.dropbox.dto.posomap.DropboxDatasinkDto2PosoMap.class;
		if(net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleDto.class.equals(dtoClass))
			return net.datenwerke.rs.dsbundle.client.dsbundle.dto.posomap.DatabaseBundleDto2PosoMap.class;
		if(net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleEntryDto.class.equals(dtoClass))
			return net.datenwerke.rs.dsbundle.client.dsbundle.dto.posomap.DatabaseBundleEntryDto2PosoMap.class;
		if(net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.EmailDatasinkDto.class.equals(dtoClass))
			return net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.posomap.EmailDatasinkDto2PosoMap.class;
		if(net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto.class.equals(dtoClass))
			return net.datenwerke.rs.fileserver.client.fileserver.dto.posomap.AbstractFileServerNodeDto2PosoMap.class;
		if(net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto.class.equals(dtoClass))
			return net.datenwerke.rs.fileserver.client.fileserver.dto.posomap.FileServerFileDto2PosoMap.class;
		if(net.datenwerke.rs.fileserver.client.fileserver.dto.decorator.FileServerFileDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.fileserver.client.fileserver.dto.posomap.FileServerFileDto2PosoMap.class;
		if(net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto.class.equals(dtoClass))
			return net.datenwerke.rs.fileserver.client.fileserver.dto.posomap.FileServerFolderDto2PosoMap.class;
		if(net.datenwerke.rs.fileserver.client.fileserver.dto.EditCommandResultExtensionDto.class.equals(dtoClass))
			return net.datenwerke.rs.fileserver.client.fileserver.dto.posomap.EditCommandResultExtensionDto2PosoMap.class;
		if(net.datenwerke.rs.ftp.client.ftp.dto.FtpDatasinkDto.class.equals(dtoClass))
			return net.datenwerke.rs.ftp.client.ftp.dto.posomap.FtpDatasinkDto2PosoMap.class;
		if(net.datenwerke.rs.ftp.client.ftp.dto.FtpsDatasinkDto.class.equals(dtoClass))
			return net.datenwerke.rs.ftp.client.ftp.dto.posomap.FtpsDatasinkDto2PosoMap.class;
		if(net.datenwerke.rs.ftp.client.ftp.dto.SftpDatasinkDto.class.equals(dtoClass))
			return net.datenwerke.rs.ftp.client.ftp.dto.posomap.SftpDatasinkDto2PosoMap.class;
		if(net.datenwerke.rs.globalconstants.client.globalconstants.dto.GlobalConstantDto.class.equals(dtoClass))
			return net.datenwerke.rs.globalconstants.client.globalconstants.dto.posomap.GlobalConstantDto2PosoMap.class;
		if(net.datenwerke.rs.googledrive.client.googledrive.dto.GoogleDriveDatasinkDto.class.equals(dtoClass))
			return net.datenwerke.rs.googledrive.client.googledrive.dto.posomap.GoogleDriveDatasinkDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorColumnConfigDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.GridEditorColumnConfigDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorColumnConfigDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.GridEditorColumnConfigDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorConfigDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.GridEditorConfigDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorDataDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.GridEditorDataDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorDataDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.GridEditorDataDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.GridEditorRecordDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorRecordDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.GridEditorRecordDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordEntryDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.GridEditorRecordEntryDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorRecordEntryDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.GridEditorRecordEntryDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.DateSelectionListEditorDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.DateSelectionListEditorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.DateSelectionListEditorDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.DateSelectionListEditorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.DecimalSelectionListEditorDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.DecimalSelectionListEditorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.DecimalSelectionListEditorDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.DecimalSelectionListEditorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.DoubleSelectionListEditorDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.DoubleSelectionListEditorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.DoubleSelectionListEditorDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.DoubleSelectionListEditorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.EditorDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.EditorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.EditorDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.EditorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.FloatSelectionListEditorDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.FloatSelectionListEditorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.FloatSelectionListEditorDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.FloatSelectionListEditorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.IntBooleanEditorDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.IntBooleanEditorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.IntBooleanEditorDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.IntBooleanEditorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.IntSelectionListEditorDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.IntSelectionListEditorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.IntSelectionListEditorDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.IntSelectionListEditorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.LongSelectionListEditorDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.LongSelectionListEditorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.LongSelectionListEditorDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.LongSelectionListEditorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.SelectionListEditorDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.SelectionListEditorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.TextAreaEditorDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.TextAreaEditorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.TextAreaEditorDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.TextAreaEditorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.TextBooleanEditorDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.TextBooleanEditorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.TextBooleanEditorDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.TextBooleanEditorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.TextDateEditorDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.TextDateEditorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.TextDateEditorDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.TextDateEditorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.TextSelectionListEditorDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.TextSelectionListEditorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.TextSelectionListEditorDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.TextSelectionListEditorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.CustomValidatorDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.CustomValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.CustomValidatorDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.CustomValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.EmptyValidatorDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.EmptyValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.EmptyValidatorDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.EmptyValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.FixedLengthValidatorDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.FixedLengthValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.FixedLengthValidatorDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.FixedLengthValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.MaxBigDecimalValidatorDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.MaxBigDecimalValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxBigDecimalValidatorDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.MaxBigDecimalValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.MaxDateValidatorDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.MaxDateValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxDateValidatorDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.MaxDateValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.MaxDoubleValidatorDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.MaxDoubleValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxDoubleValidatorDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.MaxDoubleValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.MaxFloatValidatorDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.MaxFloatValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxFloatValidatorDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.MaxFloatValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.MaxIntegerValidatorDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.MaxIntegerValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxIntegerValidatorDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.MaxIntegerValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.MaxLengthValidatorDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.MaxLengthValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxLengthValidatorDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.MaxLengthValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.MaxLongValidatorDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.MaxLongValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxLongValidatorDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.MaxLongValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.MinBigDecimalValidatorDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.MinBigDecimalValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinBigDecimalValidatorDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.MinBigDecimalValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.MinDateValidatorDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.MinDateValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinDateValidatorDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.MinDateValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.MinDoubleValidatorDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.MinDoubleValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinDoubleValidatorDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.MinDoubleValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.MinFloatValidatorDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.MinFloatValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinFloatValidatorDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.MinFloatValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.MinIntegerValidatorDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.MinIntegerValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinIntegerValidatorDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.MinIntegerValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.MinLengthValidatorDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.MinLengthValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinLengthValidatorDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.MinLengthValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.MinLongValidatorDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.MinLongValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinLongValidatorDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.MinLongValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.RegExValidatorDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.RegExValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.RegExValidatorDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.RegExValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.ValidatorDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.ValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.ValidatorDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.ValidatorDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorReportDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.GridEditorReportDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorReportDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.GridEditorReportDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorReportVariantDto.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.GridEditorReportVariantDto2PosoMap.class;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorReportVariantDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.GridEditorReportVariantDto2PosoMap.class;
		if(net.datenwerke.rs.incubator.client.jaspertotable.dto.JasperToTableConfigDto.class.equals(dtoClass))
			return net.datenwerke.rs.incubator.client.jaspertotable.dto.posomap.JasperToTableConfigDto2PosoMap.class;
		if(net.datenwerke.rs.incubator.client.jaspertotable.dto.decorator.JasperToTableConfigDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.incubator.client.jaspertotable.dto.posomap.JasperToTableConfigDto2PosoMap.class;
		if(net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportDto.class.equals(dtoClass))
			return net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.posomap.JxlsReportDto2PosoMap.class;
		if(net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.decorator.JxlsReportDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.posomap.JxlsReportDto2PosoMap.class;
		if(net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportFileDto.class.equals(dtoClass))
			return net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.posomap.JxlsReportFileDto2PosoMap.class;
		if(net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportVariantDto.class.equals(dtoClass))
			return net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.posomap.JxlsReportVariantDto2PosoMap.class;
		if(net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.decorator.JxlsReportVariantDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.posomap.JxlsReportVariantDto2PosoMap.class;
		if(net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto.LocalFileSystemDatasinkDto.class.equals(dtoClass))
			return net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto.posomap.LocalFileSystemDatasinkDto2PosoMap.class;
		if(net.datenwerke.rs.onedrive.client.onedrive.dto.OneDriveDatasinkDto.class.equals(dtoClass))
			return net.datenwerke.rs.onedrive.client.onedrive.dto.posomap.OneDriveDatasinkDto2PosoMap.class;
		if(net.datenwerke.rs.printer.client.printer.dto.PrinterDatasinkDto.class.equals(dtoClass))
			return net.datenwerke.rs.printer.client.printer.dto.posomap.PrinterDatasinkDto2PosoMap.class;
		if(net.datenwerke.rs.remotersserver.client.remotersserver.dto.RemoteRsServerDto.class.equals(dtoClass))
			return net.datenwerke.rs.remotersserver.client.remotersserver.dto.posomap.RemoteRsServerDto2PosoMap.class;
		if(net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.AbstractRemoteServerManagerNodeDto.class.equals(dtoClass))
			return net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.posomap.AbstractRemoteServerManagerNodeDto2PosoMap.class;
		if(net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerContainerDto.class.equals(dtoClass))
			return net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.posomap.RemoteServerContainerDto2PosoMap.class;
		if(net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerContainerProviderDto.class.equals(dtoClass))
			return net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.posomap.RemoteServerContainerProviderDto2PosoMap.class;
		if(net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerDefinitionDto.class.equals(dtoClass))
			return net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.posomap.RemoteServerDefinitionDto2PosoMap.class;
		if(net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerFolderDto.class.equals(dtoClass))
			return net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.posomap.RemoteServerFolderDto2PosoMap.class;
		if(net.datenwerke.rs.reportdoc.client.dto.DeployAnalyzeCommandResultExtensionDto.class.equals(dtoClass))
			return net.datenwerke.rs.reportdoc.client.dto.posomap.DeployAnalyzeCommandResultExtensionDto2PosoMap.class;
		if(net.datenwerke.rs.reportdoc.client.dto.VariantTestCommandResultExtensionDto.class.equals(dtoClass))
			return net.datenwerke.rs.reportdoc.client.dto.posomap.VariantTestCommandResultExtensionDto2PosoMap.class;
		if(net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceDto.class.equals(dtoClass))
			return net.datenwerke.rs.saiku.client.datasource.dto.posomap.MondrianDatasourceDto2PosoMap.class;
		if(net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceConfigDto.class.equals(dtoClass))
			return net.datenwerke.rs.saiku.client.datasource.dto.posomap.MondrianDatasourceConfigDto2PosoMap.class;
		if(net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportDto.class.equals(dtoClass))
			return net.datenwerke.rs.saiku.client.saiku.dto.posomap.SaikuReportDto2PosoMap.class;
		if(net.datenwerke.rs.saiku.client.saiku.dto.decorator.SaikuReportDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.saiku.client.saiku.dto.posomap.SaikuReportDto2PosoMap.class;
		if(net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportVariantDto.class.equals(dtoClass))
			return net.datenwerke.rs.saiku.client.saiku.dto.posomap.SaikuReportVariantDto2PosoMap.class;
		if(net.datenwerke.rs.saiku.client.saiku.dto.decorator.SaikuReportVariantDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.saiku.client.saiku.dto.posomap.SaikuReportVariantDto2PosoMap.class;
		if(net.datenwerke.rs.saiku.client.saiku.dto.RECSaikuChartDto.class.equals(dtoClass))
			return net.datenwerke.rs.saiku.client.saiku.dto.posomap.RECSaikuChartDto2PosoMap.class;
		if(net.datenwerke.rs.saiku.client.saiku.dto.decorator.RECSaikuChartDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.saiku.client.saiku.dto.posomap.RECSaikuChartDto2PosoMap.class;
		if(net.datenwerke.rs.samba.client.samba.dto.SambaDatasinkDto.class.equals(dtoClass))
			return net.datenwerke.rs.samba.client.samba.dto.posomap.SambaDatasinkDto2PosoMap.class;
		if(net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto.ExecutedReportFileReferenceDto.class.equals(dtoClass))
			return net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto.posomap.ExecutedReportFileReferenceDto2PosoMap.class;
		if(net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto.decorator.ExecutedReportFileReferenceDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto.posomap.ExecutedReportFileReferenceDto2PosoMap.class;
		if(net.datenwerke.rs.scheduleasfile.client.scheduleasfile.filter.dto.TeamSpaceReportJobFilterDto.class.equals(dtoClass))
			return net.datenwerke.rs.scheduleasfile.client.scheduleasfile.filter.dto.posomap.TeamSpaceReportJobFilterDto2PosoMap.class;
		if(net.datenwerke.rs.scheduler.client.scheduler.dto.ReportServerJobFilterDto.class.equals(dtoClass))
			return net.datenwerke.rs.scheduler.client.scheduler.dto.posomap.ReportServerJobFilterDto2PosoMap.class;
		if(net.datenwerke.rs.scp.client.scp.dto.ScpDatasinkDto.class.equals(dtoClass))
			return net.datenwerke.rs.scp.client.scp.dto.posomap.ScpDatasinkDto2PosoMap.class;
		if(net.datenwerke.rs.scriptdatasink.client.scriptdatasink.dto.ScriptDatasinkDto.class.equals(dtoClass))
			return net.datenwerke.rs.scriptdatasink.client.scriptdatasink.dto.posomap.ScriptDatasinkDto2PosoMap.class;
		if(net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto.ScriptDatasourceDto.class.equals(dtoClass))
			return net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto.posomap.ScriptDatasourceDto2PosoMap.class;
		if(net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto.ScriptDatasourceConfigDto.class.equals(dtoClass))
			return net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto.posomap.ScriptDatasourceConfigDto2PosoMap.class;
		if(net.datenwerke.rs.scripting.client.scripting.dto.AddMenuEntryExtensionDto.class.equals(dtoClass))
			return net.datenwerke.rs.scripting.client.scripting.dto.posomap.AddMenuEntryExtensionDto2PosoMap.class;
		if(net.datenwerke.rs.scripting.client.scripting.dto.AddMenuSeparatorEntryExtensionDto.class.equals(dtoClass))
			return net.datenwerke.rs.scripting.client.scripting.dto.posomap.AddMenuSeparatorEntryExtensionDto2PosoMap.class;
		if(net.datenwerke.rs.scripting.client.scripting.dto.AddReportExportFormatProviderDto.class.equals(dtoClass))
			return net.datenwerke.rs.scripting.client.scripting.dto.posomap.AddReportExportFormatProviderDto2PosoMap.class;
		if(net.datenwerke.rs.scripting.client.scripting.dto.AddStatusbBarLabelExtensionDto.class.equals(dtoClass))
			return net.datenwerke.rs.scripting.client.scripting.dto.posomap.AddStatusbBarLabelExtensionDto2PosoMap.class;
		if(net.datenwerke.rs.scripting.client.scripting.dto.AddToolbarEntryExtensionDto.class.equals(dtoClass))
			return net.datenwerke.rs.scripting.client.scripting.dto.posomap.AddToolbarEntryExtensionDto2PosoMap.class;
		if(net.datenwerke.rs.scripting.client.scripting.dto.DisplayConditionDto.class.equals(dtoClass))
			return net.datenwerke.rs.scripting.client.scripting.dto.posomap.DisplayConditionDto2PosoMap.class;
		if(net.datenwerke.rs.scripting.client.scripting.dto.DisplayConditionTypeDto.class.equals(dtoClass))
			return net.datenwerke.rs.scripting.client.scripting.dto.posomap.DisplayConditionTypeDto2PosoMap.class;
		if(net.datenwerke.rs.scriptreport.client.scriptreport.dto.ScriptReportDto.class.equals(dtoClass))
			return net.datenwerke.rs.scriptreport.client.scriptreport.dto.posomap.ScriptReportDto2PosoMap.class;
		if(net.datenwerke.rs.scriptreport.client.scriptreport.dto.decorator.ScriptReportDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.scriptreport.client.scriptreport.dto.posomap.ScriptReportDto2PosoMap.class;
		if(net.datenwerke.rs.scriptreport.client.scriptreport.dto.ScriptReportVariantDto.class.equals(dtoClass))
			return net.datenwerke.rs.scriptreport.client.scriptreport.dto.posomap.ScriptReportVariantDto2PosoMap.class;
		if(net.datenwerke.rs.scriptreport.client.scriptreport.dto.decorator.ScriptReportVariantDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.scriptreport.client.scriptreport.dto.posomap.ScriptReportVariantDto2PosoMap.class;
		if(net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.ScriptParameterDefinitionDto.class.equals(dtoClass))
			return net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.posomap.ScriptParameterDefinitionDto2PosoMap.class;
		if(net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.ScriptParameterInstanceDto.class.equals(dtoClass))
			return net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.posomap.ScriptParameterInstanceDto2PosoMap.class;
		if(net.datenwerke.rs.search.client.search.dto.SearchFilterDto.class.equals(dtoClass))
			return net.datenwerke.rs.search.client.search.dto.posomap.SearchFilterDto2PosoMap.class;
		if(net.datenwerke.rs.search.client.search.dto.decorator.SearchFilterDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.search.client.search.dto.posomap.SearchFilterDto2PosoMap.class;
		if(net.datenwerke.rs.search.client.search.dto.SearchResultEntryDto.class.equals(dtoClass))
			return net.datenwerke.rs.search.client.search.dto.posomap.SearchResultEntryDto2PosoMap.class;
		if(net.datenwerke.rs.search.client.search.dto.decorator.SearchResultEntryDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.search.client.search.dto.posomap.SearchResultEntryDto2PosoMap.class;
		if(net.datenwerke.rs.search.client.search.dto.SearchResultListDto.class.equals(dtoClass))
			return net.datenwerke.rs.search.client.search.dto.posomap.SearchResultListDto2PosoMap.class;
		if(net.datenwerke.rs.search.client.search.dto.decorator.SearchResultListDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.search.client.search.dto.posomap.SearchResultListDto2PosoMap.class;
		if(net.datenwerke.rs.search.client.search.dto.SearchResultTagDto.class.equals(dtoClass))
			return net.datenwerke.rs.search.client.search.dto.posomap.SearchResultTagDto2PosoMap.class;
		if(net.datenwerke.rs.search.client.search.dto.SearchResultTagTypeDto.class.equals(dtoClass))
			return net.datenwerke.rs.search.client.search.dto.posomap.SearchResultTagTypeDto2PosoMap.class;
		if(net.datenwerke.rs.tabledatasink.client.tabledatasink.dto.TableDatasinkDto.class.equals(dtoClass))
			return net.datenwerke.rs.tabledatasink.client.tabledatasink.dto.posomap.TableDatasinkDto2PosoMap.class;
		if(net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.RECTableTemplateDto.class.equals(dtoClass))
			return net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.posomap.RECTableTemplateDto2PosoMap.class;
		if(net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportByteTemplateDto.class.equals(dtoClass))
			return net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.posomap.TableReportByteTemplateDto2PosoMap.class;
		if(net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportStringTemplateDto.class.equals(dtoClass))
			return net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.posomap.TableReportStringTemplateDto2PosoMap.class;
		if(net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportTemplateDto.class.equals(dtoClass))
			return net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.posomap.TableReportTemplateDto2PosoMap.class;
		if(net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportTemplateListDto.class.equals(dtoClass))
			return net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.posomap.TableReportTemplateListDto2PosoMap.class;
		if(net.datenwerke.rs.teamspace.client.teamspace.dto.AppPropertyDto.class.equals(dtoClass))
			return net.datenwerke.rs.teamspace.client.teamspace.dto.posomap.AppPropertyDto2PosoMap.class;
		if(net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto.class.equals(dtoClass))
			return net.datenwerke.rs.teamspace.client.teamspace.dto.posomap.TeamSpaceDto2PosoMap.class;
		if(net.datenwerke.rs.teamspace.client.teamspace.dto.decorator.TeamSpaceDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.teamspace.client.teamspace.dto.posomap.TeamSpaceDto2PosoMap.class;
		if(net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceAppDto.class.equals(dtoClass))
			return net.datenwerke.rs.teamspace.client.teamspace.dto.posomap.TeamSpaceAppDto2PosoMap.class;
		if(net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceMemberDto.class.equals(dtoClass))
			return net.datenwerke.rs.teamspace.client.teamspace.dto.posomap.TeamSpaceMemberDto2PosoMap.class;
		if(net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceRoleDto.class.equals(dtoClass))
			return net.datenwerke.rs.teamspace.client.teamspace.dto.posomap.TeamSpaceRoleDto2PosoMap.class;
		if(net.datenwerke.rs.teamspace.client.teamspace.security.TeamSpaceSecureeDto.class.equals(dtoClass))
			return net.datenwerke.rs.teamspace.client.teamspace.security.posomap.TeamSpaceSecureeDto2PosoMap.class;
		if(net.datenwerke.rs.teamspace.client.teamspace.security.rights.TeamSpaceAdministratorDto.class.equals(dtoClass))
			return net.datenwerke.rs.teamspace.client.teamspace.security.rights.posomap.TeamSpaceAdministratorDto2PosoMap.class;
		if(net.datenwerke.rs.terminal.client.terminal.dto.AutocompleteResultDto.class.equals(dtoClass))
			return net.datenwerke.rs.terminal.client.terminal.dto.posomap.AutocompleteResultDto2PosoMap.class;
		if(net.datenwerke.rs.terminal.client.terminal.dto.decorator.AutocompleteResultDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.terminal.client.terminal.dto.posomap.AutocompleteResultDto2PosoMap.class;
		if(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultDto.class.equals(dtoClass))
			return net.datenwerke.rs.terminal.client.terminal.dto.posomap.CommandResultDto2PosoMap.class;
		if(net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.terminal.client.terminal.dto.posomap.CommandResultDto2PosoMap.class;
		if(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultAnchorDto.class.equals(dtoClass))
			return net.datenwerke.rs.terminal.client.terminal.dto.posomap.CommandResultAnchorDto2PosoMap.class;
		if(net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultAnchorDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.terminal.client.terminal.dto.posomap.CommandResultAnchorDto2PosoMap.class;
		if(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultEntryDto.class.equals(dtoClass))
			return net.datenwerke.rs.terminal.client.terminal.dto.posomap.CommandResultEntryDto2PosoMap.class;
		if(net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultEntryDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.terminal.client.terminal.dto.posomap.CommandResultEntryDto2PosoMap.class;
		if(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultExtensionDto.class.equals(dtoClass))
			return net.datenwerke.rs.terminal.client.terminal.dto.posomap.CommandResultExtensionDto2PosoMap.class;
		if(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultHtmlDto.class.equals(dtoClass))
			return net.datenwerke.rs.terminal.client.terminal.dto.posomap.CommandResultHtmlDto2PosoMap.class;
		if(net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultHtmlDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.terminal.client.terminal.dto.posomap.CommandResultHtmlDto2PosoMap.class;
		if(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultHyperlinkDto.class.equals(dtoClass))
			return net.datenwerke.rs.terminal.client.terminal.dto.posomap.CommandResultHyperlinkDto2PosoMap.class;
		if(net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultHyperlinkDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.terminal.client.terminal.dto.posomap.CommandResultHyperlinkDto2PosoMap.class;
		if(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultLineDto.class.equals(dtoClass))
			return net.datenwerke.rs.terminal.client.terminal.dto.posomap.CommandResultLineDto2PosoMap.class;
		if(net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultLineDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.terminal.client.terminal.dto.posomap.CommandResultLineDto2PosoMap.class;
		if(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultListDto.class.equals(dtoClass))
			return net.datenwerke.rs.terminal.client.terminal.dto.posomap.CommandResultListDto2PosoMap.class;
		if(net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultListDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.terminal.client.terminal.dto.posomap.CommandResultListDto2PosoMap.class;
		if(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultModifierDto.class.equals(dtoClass))
			return net.datenwerke.rs.terminal.client.terminal.dto.posomap.CommandResultModifierDto2PosoMap.class;
		if(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultTableDto.class.equals(dtoClass))
			return net.datenwerke.rs.terminal.client.terminal.dto.posomap.CommandResultTableDto2PosoMap.class;
		if(net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultTableDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.terminal.client.terminal.dto.posomap.CommandResultTableDto2PosoMap.class;
		if(net.datenwerke.rs.terminal.client.terminal.dto.CreJavaScriptDto.class.equals(dtoClass))
			return net.datenwerke.rs.terminal.client.terminal.dto.posomap.CreJavaScriptDto2PosoMap.class;
		if(net.datenwerke.rs.terminal.client.terminal.dto.CreMessageDto.class.equals(dtoClass))
			return net.datenwerke.rs.terminal.client.terminal.dto.posomap.CreMessageDto2PosoMap.class;
		if(net.datenwerke.rs.terminal.client.terminal.dto.CreOverlayDto.class.equals(dtoClass))
			return net.datenwerke.rs.terminal.client.terminal.dto.posomap.CreOverlayDto2PosoMap.class;
		if(net.datenwerke.rs.terminal.client.terminal.dto.DisplayModeDto.class.equals(dtoClass))
			return net.datenwerke.rs.terminal.client.terminal.dto.posomap.DisplayModeDto2PosoMap.class;
		if(net.datenwerke.rs.terminal.client.terminal.dto.InteractiveResultModifierDto.class.equals(dtoClass))
			return net.datenwerke.rs.terminal.client.terminal.dto.posomap.InteractiveResultModifierDto2PosoMap.class;
		if(net.datenwerke.rs.terminal.client.terminal.dto.PressKeyResultModifierDto.class.equals(dtoClass))
			return net.datenwerke.rs.terminal.client.terminal.dto.posomap.PressKeyResultModifierDto2PosoMap.class;
		if(net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto.class.equals(dtoClass))
			return net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.posomap.AbstractTsDiskNodeDto2PosoMap.class;
		if(net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.AbstractTsDiskNodeDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.posomap.AbstractTsDiskNodeDto2PosoMap.class;
		if(net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFileReferenceDto.class.equals(dtoClass))
			return net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.posomap.TsDiskFileReferenceDto2PosoMap.class;
		if(net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.TsDiskFileReferenceDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.posomap.TsDiskFileReferenceDto2PosoMap.class;
		if(net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFolderDto.class.equals(dtoClass))
			return net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.posomap.TsDiskFolderDto2PosoMap.class;
		if(net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskGeneralReferenceDto.class.equals(dtoClass))
			return net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.posomap.TsDiskGeneralReferenceDto2PosoMap.class;
		if(net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.TsDiskGeneralReferenceDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.posomap.TsDiskGeneralReferenceDto2PosoMap.class;
		if(net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto.class.equals(dtoClass))
			return net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.posomap.TsDiskReportReferenceDto2PosoMap.class;
		if(net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.TsDiskReportReferenceDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.posomap.TsDiskReportReferenceDto2PosoMap.class;
		if(net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskRootDto.class.equals(dtoClass))
			return net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.posomap.TsDiskRootDto2PosoMap.class;
		if(net.datenwerke.rs.uservariables.client.parameters.dto.UserVariableParameterDefinitionDto.class.equals(dtoClass))
			return net.datenwerke.rs.uservariables.client.parameters.dto.posomap.UserVariableParameterDefinitionDto2PosoMap.class;
		if(net.datenwerke.rs.uservariables.client.parameters.dto.UserVariableParameterInstanceDto.class.equals(dtoClass))
			return net.datenwerke.rs.uservariables.client.parameters.dto.posomap.UserVariableParameterInstanceDto2PosoMap.class;
		if(net.datenwerke.rs.uservariables.client.parameters.dto.decorator.UserVariableParameterInstanceDtoDec.class.equals(dtoClass))
			return net.datenwerke.rs.uservariables.client.parameters.dto.posomap.UserVariableParameterInstanceDto2PosoMap.class;
		if(net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableDefinitionDto.class.equals(dtoClass))
			return net.datenwerke.rs.uservariables.client.uservariables.dto.posomap.UserVariableDefinitionDto2PosoMap.class;
		if(net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableInstanceDto.class.equals(dtoClass))
			return net.datenwerke.rs.uservariables.client.uservariables.dto.posomap.UserVariableInstanceDto2PosoMap.class;
		if(net.datenwerke.rs.uservariables.client.variabletypes.list.dto.ListUserVariableDefinitionDto.class.equals(dtoClass))
			return net.datenwerke.rs.uservariables.client.variabletypes.list.dto.posomap.ListUserVariableDefinitionDto2PosoMap.class;
		if(net.datenwerke.rs.uservariables.client.variabletypes.list.dto.ListUserVariableInstanceDto.class.equals(dtoClass))
			return net.datenwerke.rs.uservariables.client.variabletypes.list.dto.posomap.ListUserVariableInstanceDto2PosoMap.class;
		if(net.datenwerke.rs.uservariables.client.variabletypes.string.dto.StringUserVariableDefinitionDto.class.equals(dtoClass))
			return net.datenwerke.rs.uservariables.client.variabletypes.string.dto.posomap.StringUserVariableDefinitionDto2PosoMap.class;
		if(net.datenwerke.rs.uservariables.client.variabletypes.string.dto.StringUserVariableInstanceDto.class.equals(dtoClass))
			return net.datenwerke.rs.uservariables.client.variabletypes.string.dto.posomap.StringUserVariableInstanceDto2PosoMap.class;
		if(net.datenwerke.scheduler.client.scheduler.dto.JobExecutionStatusDto.class.equals(dtoClass))
			return net.datenwerke.scheduler.client.scheduler.dto.posomap.JobExecutionStatusDto2PosoMap.class;
		if(net.datenwerke.scheduler.client.scheduler.dto.MisfireInstructionDto.class.equals(dtoClass))
			return net.datenwerke.scheduler.client.scheduler.dto.posomap.MisfireInstructionDto2PosoMap.class;
		if(net.datenwerke.scheduler.client.scheduler.dto.OutcomeDto.class.equals(dtoClass))
			return net.datenwerke.scheduler.client.scheduler.dto.posomap.OutcomeDto2PosoMap.class;
		if(net.datenwerke.scheduler.client.scheduler.dto.history.ActionEntryDto.class.equals(dtoClass))
			return net.datenwerke.scheduler.client.scheduler.dto.history.posomap.ActionEntryDto2PosoMap.class;
		if(net.datenwerke.scheduler.client.scheduler.dto.history.ExecutionLogEntryDto.class.equals(dtoClass))
			return net.datenwerke.scheduler.client.scheduler.dto.history.posomap.ExecutionLogEntryDto2PosoMap.class;
		if(net.datenwerke.scheduler.client.scheduler.dto.history.HistoryEntryPropertyDto.class.equals(dtoClass))
			return net.datenwerke.scheduler.client.scheduler.dto.history.posomap.HistoryEntryPropertyDto2PosoMap.class;
		if(net.datenwerke.scheduler.client.scheduler.dto.history.JobEntryDto.class.equals(dtoClass))
			return net.datenwerke.scheduler.client.scheduler.dto.history.posomap.JobEntryDto2PosoMap.class;
		if(net.datenwerke.scheduler.client.scheduler.dto.history.JobHistoryDto.class.equals(dtoClass))
			return net.datenwerke.scheduler.client.scheduler.dto.history.posomap.JobHistoryDto2PosoMap.class;
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.RetryTimeUnitDto.class.equals(dtoClass))
			return net.datenwerke.scheduler.client.scheduler.dto.config.complex.posomap.RetryTimeUnitDto2PosoMap.class;
		if(net.datenwerke.scheduler.client.scheduler.dto.VetoActionExecutionModeDto.class.equals(dtoClass))
			return net.datenwerke.scheduler.client.scheduler.dto.posomap.VetoActionExecutionModeDto2PosoMap.class;
		if(net.datenwerke.scheduler.client.scheduler.dto.VetoJobExecutionModeDto.class.equals(dtoClass))
			return net.datenwerke.scheduler.client.scheduler.dto.posomap.VetoJobExecutionModeDto2PosoMap.class;
		if(net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterConfigurationDto.class.equals(dtoClass))
			return net.datenwerke.scheduler.client.scheduler.dto.filter.posomap.JobFilterConfigurationDto2PosoMap.class;
		if(net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterCriteriaDto.class.equals(dtoClass))
			return net.datenwerke.scheduler.client.scheduler.dto.filter.posomap.JobFilterCriteriaDto2PosoMap.class;
		if(net.datenwerke.scheduler.client.scheduler.dto.filter.OrderDto.class.equals(dtoClass))
			return net.datenwerke.scheduler.client.scheduler.dto.filter.posomap.OrderDto2PosoMap.class;
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.DailyConfigDto.class.equals(dtoClass))
			return net.datenwerke.scheduler.client.scheduler.dto.config.complex.posomap.DailyConfigDto2PosoMap.class;
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.DateTriggerConfigDto.class.equals(dtoClass))
			return net.datenwerke.scheduler.client.scheduler.dto.config.complex.posomap.DateTriggerConfigDto2PosoMap.class;
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthlyNthDayConfigDto.class.equals(dtoClass))
			return net.datenwerke.scheduler.client.scheduler.dto.config.complex.posomap.MonthlyNthDayConfigDto2PosoMap.class;
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthlyNthDayOfWeekConfigDto.class.equals(dtoClass))
			return net.datenwerke.scheduler.client.scheduler.dto.config.complex.posomap.MonthlyNthDayOfWeekConfigDto2PosoMap.class;
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.TimeDto.class.equals(dtoClass))
			return net.datenwerke.scheduler.client.scheduler.dto.config.complex.posomap.TimeDto2PosoMap.class;
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.decorator.TimeDtoDec.class.equals(dtoClass))
			return net.datenwerke.scheduler.client.scheduler.dto.config.complex.posomap.TimeDto2PosoMap.class;
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.WeeklyConfigDto.class.equals(dtoClass))
			return net.datenwerke.scheduler.client.scheduler.dto.config.complex.posomap.WeeklyConfigDto2PosoMap.class;
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.YearlyAtDateConfigDto.class.equals(dtoClass))
			return net.datenwerke.scheduler.client.scheduler.dto.config.complex.posomap.YearlyAtDateConfigDto2PosoMap.class;
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.YearlyNthDayOfWeekConfigDto.class.equals(dtoClass))
			return net.datenwerke.scheduler.client.scheduler.dto.config.complex.posomap.YearlyNthDayOfWeekConfigDto2PosoMap.class;
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.DailyPatternDto.class.equals(dtoClass))
			return net.datenwerke.scheduler.client.scheduler.dto.config.complex.posomap.DailyPatternDto2PosoMap.class;
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.DailyRepeatTypeDto.class.equals(dtoClass))
			return net.datenwerke.scheduler.client.scheduler.dto.config.complex.posomap.DailyRepeatTypeDto2PosoMap.class;
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.DaysDto.class.equals(dtoClass))
			return net.datenwerke.scheduler.client.scheduler.dto.config.complex.posomap.DaysDto2PosoMap.class;
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.EndTypesDto.class.equals(dtoClass))
			return net.datenwerke.scheduler.client.scheduler.dto.config.complex.posomap.EndTypesDto2PosoMap.class;
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthsDto.class.equals(dtoClass))
			return net.datenwerke.scheduler.client.scheduler.dto.config.complex.posomap.MonthsDto2PosoMap.class;
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.NthDto.class.equals(dtoClass))
			return net.datenwerke.scheduler.client.scheduler.dto.config.complex.posomap.NthDto2PosoMap.class;
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.TimeUnitsDto.class.equals(dtoClass))
			return net.datenwerke.scheduler.client.scheduler.dto.config.complex.posomap.TimeUnitsDto2PosoMap.class;
		if(net.datenwerke.security.client.security.dto.SecureeDto.class.equals(dtoClass))
			return net.datenwerke.security.client.security.dto.posomap.SecureeDto2PosoMap.class;
		if(net.datenwerke.security.client.security.dto.SecurityServiceSecureeDto.class.equals(dtoClass))
			return net.datenwerke.security.client.security.dto.posomap.SecurityServiceSecureeDto2PosoMap.class;
		if(net.datenwerke.security.client.security.dto.AccessTypeDto.class.equals(dtoClass))
			return net.datenwerke.security.client.security.dto.posomap.AccessTypeDto2PosoMap.class;
		if(net.datenwerke.security.client.security.dto.AceDto.class.equals(dtoClass))
			return net.datenwerke.security.client.security.dto.posomap.AceDto2PosoMap.class;
		if(net.datenwerke.security.client.security.dto.decorator.AceDtoDec.class.equals(dtoClass))
			return net.datenwerke.security.client.security.dto.posomap.AceDto2PosoMap.class;
		if(net.datenwerke.security.client.security.dto.AceAccessMapDto.class.equals(dtoClass))
			return net.datenwerke.security.client.security.dto.posomap.AceAccessMapDto2PosoMap.class;
		if(net.datenwerke.security.client.security.dto.decorator.AceAccessMapDtoDec.class.equals(dtoClass))
			return net.datenwerke.security.client.security.dto.posomap.AceAccessMapDto2PosoMap.class;
		if(net.datenwerke.security.client.security.dto.HierarchicalAceDto.class.equals(dtoClass))
			return net.datenwerke.security.client.security.dto.posomap.HierarchicalAceDto2PosoMap.class;
		if(net.datenwerke.security.client.security.dto.InheritanceTypeDto.class.equals(dtoClass))
			return net.datenwerke.security.client.security.dto.posomap.InheritanceTypeDto2PosoMap.class;
		if(net.datenwerke.security.client.security.dto.DeleteDto.class.equals(dtoClass))
			return net.datenwerke.security.client.security.dto.posomap.DeleteDto2PosoMap.class;
		if(net.datenwerke.security.client.security.dto.ExecuteDto.class.equals(dtoClass))
			return net.datenwerke.security.client.security.dto.posomap.ExecuteDto2PosoMap.class;
		if(net.datenwerke.security.client.security.dto.GrantAccessDto.class.equals(dtoClass))
			return net.datenwerke.security.client.security.dto.posomap.GrantAccessDto2PosoMap.class;
		if(net.datenwerke.security.client.security.dto.ReadDto.class.equals(dtoClass))
			return net.datenwerke.security.client.security.dto.posomap.ReadDto2PosoMap.class;
		if(net.datenwerke.security.client.security.dto.RightDto.class.equals(dtoClass))
			return net.datenwerke.security.client.security.dto.posomap.RightDto2PosoMap.class;
		if(net.datenwerke.security.client.security.dto.WriteDto.class.equals(dtoClass))
			return net.datenwerke.security.client.security.dto.posomap.WriteDto2PosoMap.class;
		if(net.datenwerke.security.client.treedb.dto.SecuredAbstractNodeDto.class.equals(dtoClass))
			return net.datenwerke.security.client.treedb.dto.posomap.SecuredAbstractNodeDto2PosoMap.class;
		if(net.datenwerke.security.client.treedb.dto.decorator.SecuredAbstractNodeDtoDec.class.equals(dtoClass))
			return net.datenwerke.security.client.treedb.dto.posomap.SecuredAbstractNodeDto2PosoMap.class;
		if(net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto.class.equals(dtoClass))
			return net.datenwerke.security.client.usermanager.dto.posomap.AbstractUserManagerNodeDto2PosoMap.class;
		if(net.datenwerke.security.client.usermanager.dto.GroupDto.class.equals(dtoClass))
			return net.datenwerke.security.client.usermanager.dto.posomap.GroupDto2PosoMap.class;
		if(net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto.class.equals(dtoClass))
			return net.datenwerke.security.client.usermanager.dto.posomap.OrganisationalUnitDto2PosoMap.class;
		if(net.datenwerke.security.client.usermanager.dto.SexDto.class.equals(dtoClass))
			return net.datenwerke.security.client.usermanager.dto.posomap.SexDto2PosoMap.class;
		if(net.datenwerke.security.client.usermanager.dto.UserDto.class.equals(dtoClass))
			return net.datenwerke.security.client.usermanager.dto.posomap.UserDto2PosoMap.class;
		if(net.datenwerke.security.client.usermanager.dto.decorator.UserDtoDec.class.equals(dtoClass))
			return net.datenwerke.security.client.usermanager.dto.posomap.UserDto2PosoMap.class;
		if(net.datenwerke.security.client.usermanager.dto.UserPropertyDto.class.equals(dtoClass))
			return net.datenwerke.security.client.usermanager.dto.posomap.UserPropertyDto2PosoMap.class;
		if(net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto.class.equals(dtoClass))
			return net.datenwerke.treedb.client.treedb.dto.posomap.AbstractNodeDto2PosoMap.class;
		if(net.datenwerke.treedb.client.treedb.dto.decorator.AbstractNodeDtoDec.class.equals(dtoClass))
			return net.datenwerke.treedb.client.treedb.dto.posomap.AbstractNodeDto2PosoMap.class;
		throw new IllegalArgumentException("unrecognized dto: " + dtoClass);
	}


	public boolean isProxyableDto(Dto dto)  {
		if(! isAuthorityFor(dto)){
			for(DtoInformationService submodule : subModules){
				if(submodule.isAuthorityFor(dto)){
					return submodule.isProxyableDto(dto);
				}
			}
			return false;
		}

		if(dto instanceof net.datenwerke.rs.amazons3.client.amazons3.dto.AmazonS3DatasinkDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.FileSelectionParameterDefinitionDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.FileSelectionParameterInstanceDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.SelectedParameterFileDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.UploadedParameterFileDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.datasources.dto.ArgumentDatasourceConnectorDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorConfigDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.datasources.dto.TextDatasourceConnectorDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.datasources.dto.UrlDatasourceConnectorDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.datasources.dto.CsvDatasourceDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.datasources.dto.CsvDatasourceConfigDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceConfigDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.datasources.dto.FormatBasedDatasourceConfigDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.datasources.dto.FormatBasedDatasourceDefinitionDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.parameters.blatext.dto.BlatextParameterDefinitionDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.parameters.blatext.dto.BlatextParameterInstanceDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDefinitionDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterInstanceDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.parameters.datetime.dto.DateTimeParameterDefinitionDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.parameters.datetime.dto.DateTimeParameterInstanceDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.parameters.headline.dto.HeadlineParameterDefinitionDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.parameters.headline.dto.HeadlineParameterInstanceDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.parameters.separator.dto.SeparatorParameterDefinitionDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.parameters.separator.dto.SeparatorParameterInstanceDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.parameters.string.dto.TextParameterDefinitionDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.parameters.string.dto.TextParameterInstanceDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportJRXMLFileDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportVariantDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.table.dto.AdditionalColumnSpecDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.table.dto.ColumnReferenceDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.table.dto.FilterRangeDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.table.dto.TableReportVariantDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.table.dto.BinaryColumnFilterDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFilterDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.table.dto.FilterDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.table.dto.FilterBlockDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.table.dto.FilterSpecDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.table.dto.PreFilterDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatCurrencyDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatDateDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatNumberDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatTemplateDto)
			return true;
		if(dto instanceof net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatTextDto)
			return true;
		if(dto instanceof net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceConfigDto)
			return true;
		if(dto instanceof net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceDefinitionDto)
			return true;
		if(dto instanceof net.datenwerke.rs.birt.client.reportengines.dto.BirtReportDto)
			return true;
		if(dto instanceof net.datenwerke.rs.birt.client.reportengines.dto.BirtReportFileDto)
			return true;
		if(dto instanceof net.datenwerke.rs.birt.client.reportengines.dto.BirtReportVariantDto)
			return true;
		if(dto instanceof net.datenwerke.rs.box.client.box.dto.BoxDatasinkDto)
			return true;
		if(dto instanceof net.datenwerke.rs.computedcolumns.client.computedcolumns.dto.ComputedColumnDto)
			return true;
		if(dto instanceof net.datenwerke.rs.condition.client.condition.dto.ReportConditionDto)
			return true;
		if(dto instanceof net.datenwerke.rs.core.client.datasinkmanager.dto.AbstractDatasinkManagerNodeDto)
			return true;
		if(dto instanceof net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkContainerDto)
			return true;
		if(dto instanceof net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto)
			return true;
		if(dto instanceof net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkFolderDto)
			return true;
		if(dto instanceof net.datenwerke.rs.core.client.datasourcemanager.dto.AbstractDatasourceManagerNodeDto)
			return true;
		if(dto instanceof net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto)
			return true;
		if(dto instanceof net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto)
			return true;
		if(dto instanceof net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto)
			return true;
		if(dto instanceof net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceFolderDto)
			return true;
		if(dto instanceof net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto)
			return true;
		if(dto instanceof net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto)
			return true;
		if(dto instanceof net.datenwerke.rs.core.client.reportmanager.dto.reports.AbstractReportManagerNodeDto)
			return true;
		if(dto instanceof net.datenwerke.rs.core.client.reportmanager.dto.ReportFolderDto)
			return true;
		if(dto instanceof net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto)
			return true;
		if(dto instanceof net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportBytePropertyDto)
			return true;
		if(dto instanceof net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportMetadataDto)
			return true;
		if(dto instanceof net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportPropertyDto)
			return true;
		if(dto instanceof net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportServerSideStringPropertyDto)
			return true;
		if(dto instanceof net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportStringPropertyDto)
			return true;
		if(dto instanceof net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportDto)
			return true;
		if(dto instanceof net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportFileDto)
			return true;
		if(dto instanceof net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportVariantDto)
			return true;
		if(dto instanceof net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListDto)
			return true;
		if(dto instanceof net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListDadgetDto)
			return true;
		if(dto instanceof net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListEntryDto)
			return true;
		if(dto instanceof net.datenwerke.rs.dashboard.client.dashboard.dto.LibraryDadgetDto)
			return true;
		if(dto instanceof net.datenwerke.rs.dashboard.client.dashboard.dto.ParameterDadgetDto)
			return true;
		if(dto instanceof net.datenwerke.rs.dashboard.client.dashboard.dto.ReportDadgetDto)
			return true;
		if(dto instanceof net.datenwerke.rs.dashboard.client.dashboard.dto.StaticHtmlDadgetDto)
			return true;
		if(dto instanceof net.datenwerke.rs.dashboard.client.dashboard.dto.UrlDadgetDto)
			return true;
		if(dto instanceof net.datenwerke.rs.dashboard.client.dashboard.dto.AbstractDashboardManagerNodeDto)
			return true;
		if(dto instanceof net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto)
			return true;
		if(dto instanceof net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetNodeDto)
			return true;
		if(dto instanceof net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardDto)
			return true;
		if(dto instanceof net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardContainerDto)
			return true;
		if(dto instanceof net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardFolderDto)
			return true;
		if(dto instanceof net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardNodeDto)
			return true;
		if(dto instanceof net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardReferenceDto)
			return true;
		if(dto instanceof net.datenwerke.rs.dropbox.client.dropbox.dto.DropboxDatasinkDto)
			return true;
		if(dto instanceof net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleDto)
			return true;
		if(dto instanceof net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleEntryDto)
			return true;
		if(dto instanceof net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.EmailDatasinkDto)
			return true;
		if(dto instanceof net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto)
			return true;
		if(dto instanceof net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto)
			return true;
		if(dto instanceof net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto)
			return true;
		if(dto instanceof net.datenwerke.rs.ftp.client.ftp.dto.FtpDatasinkDto)
			return true;
		if(dto instanceof net.datenwerke.rs.ftp.client.ftp.dto.FtpsDatasinkDto)
			return true;
		if(dto instanceof net.datenwerke.rs.ftp.client.ftp.dto.SftpDatasinkDto)
			return true;
		if(dto instanceof net.datenwerke.rs.globalconstants.client.globalconstants.dto.GlobalConstantDto)
			return true;
		if(dto instanceof net.datenwerke.rs.googledrive.client.googledrive.dto.GoogleDriveDatasinkDto)
			return true;
		if(dto instanceof net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorReportDto)
			return true;
		if(dto instanceof net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorReportVariantDto)
			return true;
		if(dto instanceof net.datenwerke.rs.incubator.client.jaspertotable.dto.JasperToTableConfigDto)
			return true;
		if(dto instanceof net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportDto)
			return true;
		if(dto instanceof net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportFileDto)
			return true;
		if(dto instanceof net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportVariantDto)
			return true;
		if(dto instanceof net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto.LocalFileSystemDatasinkDto)
			return true;
		if(dto instanceof net.datenwerke.rs.onedrive.client.onedrive.dto.OneDriveDatasinkDto)
			return true;
		if(dto instanceof net.datenwerke.rs.printer.client.printer.dto.PrinterDatasinkDto)
			return true;
		if(dto instanceof net.datenwerke.rs.remotersserver.client.remotersserver.dto.RemoteRsServerDto)
			return true;
		if(dto instanceof net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.AbstractRemoteServerManagerNodeDto)
			return true;
		if(dto instanceof net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerContainerDto)
			return true;
		if(dto instanceof net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerDefinitionDto)
			return true;
		if(dto instanceof net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerFolderDto)
			return true;
		if(dto instanceof net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceDto)
			return true;
		if(dto instanceof net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceConfigDto)
			return true;
		if(dto instanceof net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportDto)
			return true;
		if(dto instanceof net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportVariantDto)
			return true;
		if(dto instanceof net.datenwerke.rs.samba.client.samba.dto.SambaDatasinkDto)
			return true;
		if(dto instanceof net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto.ExecutedReportFileReferenceDto)
			return true;
		if(dto instanceof net.datenwerke.rs.scp.client.scp.dto.ScpDatasinkDto)
			return true;
		if(dto instanceof net.datenwerke.rs.scriptdatasink.client.scriptdatasink.dto.ScriptDatasinkDto)
			return true;
		if(dto instanceof net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto.ScriptDatasourceDto)
			return true;
		if(dto instanceof net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto.ScriptDatasourceConfigDto)
			return true;
		if(dto instanceof net.datenwerke.rs.scriptreport.client.scriptreport.dto.ScriptReportDto)
			return true;
		if(dto instanceof net.datenwerke.rs.scriptreport.client.scriptreport.dto.ScriptReportVariantDto)
			return true;
		if(dto instanceof net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.ScriptParameterDefinitionDto)
			return true;
		if(dto instanceof net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.ScriptParameterInstanceDto)
			return true;
		if(dto instanceof net.datenwerke.rs.search.client.search.dto.SearchResultEntryDto)
			return true;
		if(dto instanceof net.datenwerke.rs.tabledatasink.client.tabledatasink.dto.TableDatasinkDto)
			return true;
		if(dto instanceof net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportByteTemplateDto)
			return true;
		if(dto instanceof net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportStringTemplateDto)
			return true;
		if(dto instanceof net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportTemplateDto)
			return true;
		if(dto instanceof net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportTemplateListDto)
			return true;
		if(dto instanceof net.datenwerke.rs.teamspace.client.teamspace.dto.AppPropertyDto)
			return true;
		if(dto instanceof net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto)
			return true;
		if(dto instanceof net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceAppDto)
			return true;
		if(dto instanceof net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceMemberDto)
			return true;
		if(dto instanceof net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto)
			return true;
		if(dto instanceof net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFileReferenceDto)
			return true;
		if(dto instanceof net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFolderDto)
			return true;
		if(dto instanceof net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskGeneralReferenceDto)
			return true;
		if(dto instanceof net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto)
			return true;
		if(dto instanceof net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskRootDto)
			return true;
		if(dto instanceof net.datenwerke.rs.uservariables.client.parameters.dto.UserVariableParameterDefinitionDto)
			return true;
		if(dto instanceof net.datenwerke.rs.uservariables.client.parameters.dto.UserVariableParameterInstanceDto)
			return true;
		if(dto instanceof net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableDefinitionDto)
			return true;
		if(dto instanceof net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableInstanceDto)
			return true;
		if(dto instanceof net.datenwerke.rs.uservariables.client.variabletypes.list.dto.ListUserVariableDefinitionDto)
			return true;
		if(dto instanceof net.datenwerke.rs.uservariables.client.variabletypes.list.dto.ListUserVariableInstanceDto)
			return true;
		if(dto instanceof net.datenwerke.rs.uservariables.client.variabletypes.string.dto.StringUserVariableDefinitionDto)
			return true;
		if(dto instanceof net.datenwerke.rs.uservariables.client.variabletypes.string.dto.StringUserVariableInstanceDto)
			return true;
		if(dto instanceof net.datenwerke.scheduler.client.scheduler.dto.history.ActionEntryDto)
			return true;
		if(dto instanceof net.datenwerke.scheduler.client.scheduler.dto.history.ExecutionLogEntryDto)
			return true;
		if(dto instanceof net.datenwerke.scheduler.client.scheduler.dto.history.HistoryEntryPropertyDto)
			return true;
		if(dto instanceof net.datenwerke.scheduler.client.scheduler.dto.history.JobEntryDto)
			return true;
		if(dto instanceof net.datenwerke.scheduler.client.scheduler.dto.history.JobHistoryDto)
			return true;
		if(dto instanceof net.datenwerke.scheduler.client.scheduler.dto.config.complex.DailyConfigDto)
			return true;
		if(dto instanceof net.datenwerke.scheduler.client.scheduler.dto.config.complex.DateTriggerConfigDto)
			return true;
		if(dto instanceof net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthlyNthDayConfigDto)
			return true;
		if(dto instanceof net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthlyNthDayOfWeekConfigDto)
			return true;
		if(dto instanceof net.datenwerke.scheduler.client.scheduler.dto.config.complex.WeeklyConfigDto)
			return true;
		if(dto instanceof net.datenwerke.scheduler.client.scheduler.dto.config.complex.YearlyAtDateConfigDto)
			return true;
		if(dto instanceof net.datenwerke.scheduler.client.scheduler.dto.config.complex.YearlyNthDayOfWeekConfigDto)
			return true;
		if(dto instanceof net.datenwerke.security.client.treedb.dto.SecuredAbstractNodeDto)
			return true;
		if(dto instanceof net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto)
			return true;
		if(dto instanceof net.datenwerke.security.client.usermanager.dto.GroupDto)
			return true;
		if(dto instanceof net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto)
			return true;
		if(dto instanceof net.datenwerke.security.client.usermanager.dto.UserDto)
			return true;
		if(dto instanceof net.datenwerke.security.client.usermanager.dto.UserPropertyDto)
			return true;
		if(dto instanceof net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto)
			return true;

		return false;
	}



	public boolean isAuthorityForClass(Class<?> dtoType)  {
		if(null == dtoType)
			return false;

		if(net.datenwerke.gf.client.history.dto.HistoryLinkDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.gf.client.juel.dto.JuelResultDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.gf.client.juel.dto.decorator.JuelResultDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.gf.client.juel.dto.JuelResultTypeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.adminutils.client.logs.dto.ViewLogFileCommandResultExtensionDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.amazons3.client.amazons3.dto.AmazonS3DatasinkDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.FileSelectionParameterDefinitionDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.FileSelectionParameterInstanceDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.decorator.FileSelectionParameterInstanceDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.SelectedParameterFileDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.UploadedParameterFileDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.datasources.dto.ArgumentDatasourceConnectorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.datasources.dto.decorator.ArgumentDatasourceConnectorDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.datasources.dto.decorator.DatasourceConnectorDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorConfigDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.datasources.dto.TextDatasourceConnectorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.datasources.dto.UrlDatasourceConnectorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.datasources.dto.CsvDatasourceDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.datasources.dto.CsvDatasourceConfigDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceConfigDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.datasources.dto.FormatBasedDatasourceConfigDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.datasources.dto.FormatBasedDatasourceDefinitionDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.dbhelper.dto.DatabaseHelperDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.parameters.blatext.dto.BlatextParameterDefinitionDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.parameters.blatext.dto.BlatextParameterInstanceDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.parameters.datasource.dto.BoxLayoutModeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.parameters.datasource.dto.BoxLayoutPackModeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDataDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.parameters.datasource.dto.decorator.DatasourceParameterDataDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDefinitionDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterInstanceDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.parameters.datasource.dto.ModeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.parameters.datasource.dto.MultiSelectionModeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.parameters.datasource.dto.SingleSelectionModeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.parameters.datetime.dto.DateTimeParameterDefinitionDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.parameters.datetime.dto.decorator.DateTimeParameterDefinitionDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.parameters.datetime.dto.DateTimeParameterInstanceDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.parameters.datetime.dto.decorator.DateTimeParameterInstanceDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.parameters.datetime.dto.ModeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.parameters.headline.dto.HeadlineParameterDefinitionDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.parameters.headline.dto.HeadlineParameterInstanceDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.parameters.separator.dto.SeparatorParameterDefinitionDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.parameters.separator.dto.SeparatorParameterInstanceDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.parameters.string.dto.TextParameterDefinitionDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.parameters.string.dto.TextParameterInstanceDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.jasper.dto.decorator.JasperReportDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportJRXMLFileDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportVariantDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.jasper.dto.decorator.JasperReportVariantDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.jasper.dto.CompiledCSVJasperReportDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.jasper.dto.CompiledHTMLJasperReportDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.jasper.dto.CompiledPNGJasperReportDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.jasperutils.dto.JasperParameterProposalDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.AdditionalColumnSpecDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.AdditionalColumnSpecDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.AggregateFunctionDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.ColumnReferenceDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnReferenceDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.FilterRangeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterRangeDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.NullHandlingDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.OrderDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.TableReportDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.TableReportVariantDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.TableReportVariantDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.BinaryColumnFilterDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.BinaryColumnFilterDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.BinaryOperatorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.BlockTypeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFilterDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFilterDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.FilterDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.FilterBlockDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterBlockDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.FilterSpecDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterSpecDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.PreFilterDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.PreFilterDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatCurrencyDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatCurrencyDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatDateDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatDateDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatNumberDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatNumberDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatTemplateDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatTemplateDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatTextDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatTextDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.CurrencyTypeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.NumberTypeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.CompiledHTMLTableReportDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.RSStringTableRowDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.RSTableModelDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.RSTableRowDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.base.client.reportengines.table.dto.TableDefinitionDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceConfigDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceDefinitionDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceTargetTypeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.birt.client.reportengines.dto.BirtReportDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.birt.client.reportengines.dto.decorator.BirtReportDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.birt.client.reportengines.dto.BirtReportFileDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.birt.client.reportengines.dto.BirtReportVariantDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.birt.client.reportengines.dto.decorator.BirtReportVariantDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.birt.client.reportengines.dto.CompiledHTMLBirtReportDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.birt.client.reportengines.dto.CompiledPNGBirtReportDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.birt.client.utils.dto.BirtParameterProposalDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.box.client.box.dto.BoxDatasinkDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.computedcolumns.client.computedcolumns.dto.ComputedColumnDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.condition.client.condition.dto.ReportConditionDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.condition.client.condition.dto.decorator.ReportConditionDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.core.client.datasinkmanager.dto.AbstractDatasinkManagerNodeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkContainerDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkContainerProviderDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkFolderDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.core.client.datasourcemanager.dto.AbstractDatasourceManagerNodeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerProviderDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceFolderDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.core.client.i18tools.dto.FormatPatternsDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.core.client.parameters.dto.DatatypeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.core.client.parameters.dto.decorator.ParameterDefinitionDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.core.client.reportexporter.dto.RECCsvDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.core.client.reportexporter.dto.decorator.RECCsvDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.core.client.reportexporter.dto.RECJxlsDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.core.client.reportexporter.dto.decorator.RECJxlsDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.core.client.reportmanager.dto.reports.AbstractReportManagerNodeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.core.client.reportmanager.dto.ReportFolderDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.core.client.reportmanager.dto.reports.decorator.ReportDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportBytePropertyDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportMetadataDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportPropertyDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportServerSideStringPropertyDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportStringPropertyDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.core.client.reportmanager.dto.reports.ServerSidePropertyDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportVariantDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.crystal.client.crystal.dto.CrystalParameterProposalDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.crystal.client.crystal.dto.decorator.CrystalReportDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportFileDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportVariantDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.crystal.client.crystal.dto.decorator.CrystalReportVariantDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.FavoriteListDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListDadgetDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.FavoriteListDadgetDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListEntryDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.FavoriteListEntryDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.LibraryDadgetDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.LibraryDadgetDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.ParameterDadgetDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.ParameterDadgetDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.ReportDadgetDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.ReportDadgetDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.StaticHtmlDadgetDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.StaticHtmlDadgetDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.UrlDadgetDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.UrlDadgetDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.AbstractDashboardManagerNodeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DadgetDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetContainerDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetNodeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DashboardDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardContainerDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DashboardContainerDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardFolderDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardNodeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardReferenceDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DashboardReferenceDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.dashboard.client.dashboard.dto.LayoutTypeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.dropbox.client.dropbox.dto.DropboxDatasinkDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleEntryDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.EmailDatasinkDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.fileserver.client.fileserver.dto.decorator.FileServerFileDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.fileserver.client.fileserver.dto.EditCommandResultExtensionDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.ftp.client.ftp.dto.FtpDatasinkDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.ftp.client.ftp.dto.FtpsDatasinkDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.ftp.client.ftp.dto.SftpDatasinkDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.globalconstants.client.globalconstants.dto.GlobalConstantDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.googledrive.client.googledrive.dto.GoogleDriveDatasinkDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorColumnConfigDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorColumnConfigDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorConfigDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorDataDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorDataDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorRecordDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordEntryDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorRecordEntryDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.DateSelectionListEditorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.DateSelectionListEditorDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.DecimalSelectionListEditorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.DecimalSelectionListEditorDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.DoubleSelectionListEditorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.DoubleSelectionListEditorDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.EditorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.EditorDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.FloatSelectionListEditorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.FloatSelectionListEditorDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.IntBooleanEditorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.IntBooleanEditorDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.IntSelectionListEditorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.IntSelectionListEditorDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.LongSelectionListEditorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.LongSelectionListEditorDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.SelectionListEditorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.TextAreaEditorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.TextAreaEditorDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.TextBooleanEditorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.TextBooleanEditorDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.TextDateEditorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.TextDateEditorDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.TextSelectionListEditorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.TextSelectionListEditorDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.CustomValidatorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.CustomValidatorDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.EmptyValidatorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.EmptyValidatorDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.FixedLengthValidatorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.FixedLengthValidatorDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.MaxBigDecimalValidatorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxBigDecimalValidatorDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.MaxDateValidatorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxDateValidatorDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.MaxDoubleValidatorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxDoubleValidatorDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.MaxFloatValidatorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxFloatValidatorDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.MaxIntegerValidatorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxIntegerValidatorDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.MaxLengthValidatorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxLengthValidatorDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.MaxLongValidatorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxLongValidatorDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.MinBigDecimalValidatorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinBigDecimalValidatorDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.MinDateValidatorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinDateValidatorDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.MinDoubleValidatorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinDoubleValidatorDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.MinFloatValidatorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinFloatValidatorDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.MinIntegerValidatorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinIntegerValidatorDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.MinLengthValidatorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinLengthValidatorDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.MinLongValidatorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinLongValidatorDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.RegExValidatorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.RegExValidatorDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.ValidatorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.ValidatorDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorReportDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorReportDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorReportVariantDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorReportVariantDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.incubator.client.jaspertotable.dto.JasperToTableConfigDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.incubator.client.jaspertotable.dto.decorator.JasperToTableConfigDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.decorator.JxlsReportDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportFileDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportVariantDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.decorator.JxlsReportVariantDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto.LocalFileSystemDatasinkDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.onedrive.client.onedrive.dto.OneDriveDatasinkDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.printer.client.printer.dto.PrinterDatasinkDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.remotersserver.client.remotersserver.dto.RemoteRsServerDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.AbstractRemoteServerManagerNodeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerContainerDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerContainerProviderDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerDefinitionDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerFolderDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.reportdoc.client.dto.DeployAnalyzeCommandResultExtensionDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.reportdoc.client.dto.VariantTestCommandResultExtensionDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceConfigDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.saiku.client.saiku.dto.decorator.SaikuReportDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportVariantDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.saiku.client.saiku.dto.decorator.SaikuReportVariantDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.saiku.client.saiku.dto.RECSaikuChartDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.saiku.client.saiku.dto.decorator.RECSaikuChartDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.samba.client.samba.dto.SambaDatasinkDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto.ExecutedReportFileReferenceDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto.decorator.ExecutedReportFileReferenceDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.scheduleasfile.client.scheduleasfile.filter.dto.TeamSpaceReportJobFilterDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.scheduler.client.scheduler.dto.ReportServerJobFilterDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.scp.client.scp.dto.ScpDatasinkDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.scriptdatasink.client.scriptdatasink.dto.ScriptDatasinkDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto.ScriptDatasourceDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto.ScriptDatasourceConfigDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.scripting.client.scripting.dto.AddMenuEntryExtensionDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.scripting.client.scripting.dto.AddMenuSeparatorEntryExtensionDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.scripting.client.scripting.dto.AddReportExportFormatProviderDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.scripting.client.scripting.dto.AddStatusbBarLabelExtensionDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.scripting.client.scripting.dto.AddToolbarEntryExtensionDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.scripting.client.scripting.dto.DisplayConditionDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.scripting.client.scripting.dto.DisplayConditionTypeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.scriptreport.client.scriptreport.dto.ScriptReportDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.scriptreport.client.scriptreport.dto.decorator.ScriptReportDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.scriptreport.client.scriptreport.dto.ScriptReportVariantDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.scriptreport.client.scriptreport.dto.decorator.ScriptReportVariantDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.ScriptParameterDefinitionDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.ScriptParameterInstanceDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.search.client.search.dto.SearchFilterDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.search.client.search.dto.decorator.SearchFilterDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.search.client.search.dto.SearchResultEntryDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.search.client.search.dto.decorator.SearchResultEntryDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.search.client.search.dto.SearchResultListDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.search.client.search.dto.decorator.SearchResultListDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.search.client.search.dto.SearchResultTagDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.search.client.search.dto.SearchResultTagTypeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.tabledatasink.client.tabledatasink.dto.TableDatasinkDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.RECTableTemplateDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportByteTemplateDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportStringTemplateDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportTemplateDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportTemplateListDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.teamspace.client.teamspace.dto.AppPropertyDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.teamspace.client.teamspace.dto.decorator.TeamSpaceDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceAppDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceMemberDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceRoleDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.teamspace.client.teamspace.security.TeamSpaceSecureeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.teamspace.client.teamspace.security.rights.TeamSpaceAdministratorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.terminal.client.terminal.dto.AutocompleteResultDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.terminal.client.terminal.dto.decorator.AutocompleteResultDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultAnchorDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultAnchorDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultEntryDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultEntryDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultExtensionDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultHtmlDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultHtmlDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultHyperlinkDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultHyperlinkDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultLineDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultLineDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultListDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultListDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultModifierDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultTableDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultTableDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.terminal.client.terminal.dto.CreJavaScriptDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.terminal.client.terminal.dto.CreMessageDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.terminal.client.terminal.dto.CreOverlayDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.terminal.client.terminal.dto.DisplayModeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.terminal.client.terminal.dto.InteractiveResultModifierDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.terminal.client.terminal.dto.PressKeyResultModifierDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.AbstractTsDiskNodeDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFileReferenceDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.TsDiskFileReferenceDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFolderDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskGeneralReferenceDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.TsDiskGeneralReferenceDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.TsDiskReportReferenceDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskRootDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.uservariables.client.parameters.dto.UserVariableParameterDefinitionDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.uservariables.client.parameters.dto.UserVariableParameterInstanceDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.uservariables.client.parameters.dto.decorator.UserVariableParameterInstanceDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableDefinitionDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableInstanceDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.uservariables.client.variabletypes.list.dto.ListUserVariableDefinitionDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.uservariables.client.variabletypes.list.dto.ListUserVariableInstanceDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.uservariables.client.variabletypes.string.dto.StringUserVariableDefinitionDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.rs.uservariables.client.variabletypes.string.dto.StringUserVariableInstanceDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.scheduler.client.scheduler.dto.JobExecutionStatusDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.scheduler.client.scheduler.dto.MisfireInstructionDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.scheduler.client.scheduler.dto.OutcomeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.scheduler.client.scheduler.dto.history.ActionEntryDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.scheduler.client.scheduler.dto.history.ExecutionLogEntryDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.scheduler.client.scheduler.dto.history.HistoryEntryPropertyDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.scheduler.client.scheduler.dto.history.JobEntryDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.scheduler.client.scheduler.dto.history.JobHistoryDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.RetryTimeUnitDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.scheduler.client.scheduler.dto.VetoActionExecutionModeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.scheduler.client.scheduler.dto.VetoJobExecutionModeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterConfigurationDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterCriteriaDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.scheduler.client.scheduler.dto.filter.OrderDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.DailyConfigDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.DateTriggerConfigDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthlyNthDayConfigDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthlyNthDayOfWeekConfigDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.TimeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.decorator.TimeDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.WeeklyConfigDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.YearlyAtDateConfigDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.YearlyNthDayOfWeekConfigDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.DailyPatternDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.DailyRepeatTypeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.DaysDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.EndTypesDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthsDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.NthDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.scheduler.client.scheduler.dto.config.complex.TimeUnitsDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.security.client.security.dto.SecureeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.security.client.security.dto.SecurityServiceSecureeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.security.client.security.dto.AccessTypeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.security.client.security.dto.AceDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.security.client.security.dto.decorator.AceDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.security.client.security.dto.AceAccessMapDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.security.client.security.dto.decorator.AceAccessMapDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.security.client.security.dto.HierarchicalAceDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.security.client.security.dto.InheritanceTypeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.security.client.security.dto.DeleteDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.security.client.security.dto.ExecuteDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.security.client.security.dto.GrantAccessDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.security.client.security.dto.ReadDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.security.client.security.dto.RightDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.security.client.security.dto.WriteDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.security.client.treedb.dto.SecuredAbstractNodeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.security.client.treedb.dto.decorator.SecuredAbstractNodeDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.security.client.usermanager.dto.GroupDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.security.client.usermanager.dto.SexDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.security.client.usermanager.dto.UserDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.security.client.usermanager.dto.decorator.UserDtoDec.class.equals(dtoType))
			return true;
		if(net.datenwerke.security.client.usermanager.dto.UserPropertyDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto.class.equals(dtoType))
			return true;
		if(net.datenwerke.treedb.client.treedb.dto.decorator.AbstractNodeDtoDec.class.equals(dtoType))
			return true;
		return false;
	}


	public boolean isAuthorityFor(Object dto)  {
		if(null == dto)
			return false;

		return isAuthorityForClass(dto.getClass());
	}


	public boolean isAuthorityForClassName(String dtoClassName)  {
		if(null == dtoClassName)
			return false;

		if("net.datenwerke.gf.client.history.dto.HistoryLinkDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.gf.client.juel.dto.JuelResultDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.gf.client.juel.dto.decorator.JuelResultDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.gf.client.juel.dto.JuelResultTypeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.adminutils.client.logs.dto.ViewLogFileCommandResultExtensionDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.amazons3.client.amazons3.dto.AmazonS3DatasinkDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.FileSelectionParameterDefinitionDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.FileSelectionParameterInstanceDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.decorator.FileSelectionParameterInstanceDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.SelectedParameterFileDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.UploadedParameterFileDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.datasources.dto.ArgumentDatasourceConnectorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.datasources.dto.decorator.ArgumentDatasourceConnectorDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.datasources.dto.decorator.DatasourceConnectorDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorConfigDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.datasources.dto.TextDatasourceConnectorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.datasources.dto.UrlDatasourceConnectorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.datasources.dto.CsvDatasourceDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.datasources.dto.CsvDatasourceConfigDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceConfigDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.datasources.dto.FormatBasedDatasourceConfigDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.datasources.dto.FormatBasedDatasourceDefinitionDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.dbhelper.dto.DatabaseHelperDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.parameters.blatext.dto.BlatextParameterDefinitionDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.parameters.blatext.dto.BlatextParameterInstanceDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.parameters.datasource.dto.BoxLayoutModeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.parameters.datasource.dto.BoxLayoutPackModeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDataDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.parameters.datasource.dto.decorator.DatasourceParameterDataDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDefinitionDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterInstanceDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.parameters.datasource.dto.ModeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.parameters.datasource.dto.MultiSelectionModeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.parameters.datasource.dto.SingleSelectionModeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.parameters.datetime.dto.DateTimeParameterDefinitionDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.parameters.datetime.dto.decorator.DateTimeParameterDefinitionDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.parameters.datetime.dto.DateTimeParameterInstanceDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.parameters.datetime.dto.decorator.DateTimeParameterInstanceDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.parameters.datetime.dto.ModeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.parameters.headline.dto.HeadlineParameterDefinitionDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.parameters.headline.dto.HeadlineParameterInstanceDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.parameters.separator.dto.SeparatorParameterDefinitionDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.parameters.separator.dto.SeparatorParameterInstanceDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.parameters.string.dto.TextParameterDefinitionDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.parameters.string.dto.TextParameterInstanceDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.jasper.dto.decorator.JasperReportDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportJRXMLFileDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportVariantDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.jasper.dto.decorator.JasperReportVariantDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.jasper.dto.CompiledCSVJasperReportDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.jasper.dto.CompiledHTMLJasperReportDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.jasper.dto.CompiledPNGJasperReportDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.jasperutils.dto.JasperParameterProposalDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.AdditionalColumnSpecDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.decorator.AdditionalColumnSpecDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.AggregateFunctionDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.ColumnReferenceDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnReferenceDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.FilterRangeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterRangeDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.NullHandlingDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.OrderDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.decorator.TableReportDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.TableReportVariantDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.decorator.TableReportVariantDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.BinaryColumnFilterDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.decorator.BinaryColumnFilterDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.BinaryOperatorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.BlockTypeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFilterDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFilterDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.FilterDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.FilterBlockDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterBlockDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.FilterSpecDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterSpecDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.PreFilterDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.decorator.PreFilterDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatCurrencyDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatCurrencyDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatDateDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatDateDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatNumberDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatNumberDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatTemplateDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatTemplateDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatTextDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatTextDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.CurrencyTypeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.NumberTypeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.CompiledHTMLTableReportDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.RSStringTableRowDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.RSTableModelDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.RSTableRowDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.base.client.reportengines.table.dto.TableDefinitionDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceConfigDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceDefinitionDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceTargetTypeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.birt.client.reportengines.dto.BirtReportDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.birt.client.reportengines.dto.decorator.BirtReportDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.birt.client.reportengines.dto.BirtReportFileDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.birt.client.reportengines.dto.BirtReportVariantDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.birt.client.reportengines.dto.decorator.BirtReportVariantDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.birt.client.reportengines.dto.CompiledHTMLBirtReportDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.birt.client.reportengines.dto.CompiledPNGBirtReportDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.birt.client.utils.dto.BirtParameterProposalDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.box.client.box.dto.BoxDatasinkDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.computedcolumns.client.computedcolumns.dto.ComputedColumnDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.condition.client.condition.dto.ReportConditionDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.condition.client.condition.dto.decorator.ReportConditionDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.core.client.datasinkmanager.dto.AbstractDatasinkManagerNodeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkContainerDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkContainerProviderDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkFolderDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.core.client.datasourcemanager.dto.AbstractDatasourceManagerNodeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerProviderDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceFolderDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.core.client.i18tools.dto.FormatPatternsDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.core.client.parameters.dto.DatatypeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.core.client.parameters.dto.decorator.ParameterDefinitionDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.core.client.reportexporter.dto.RECCsvDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.core.client.reportexporter.dto.decorator.RECCsvDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.core.client.reportexporter.dto.RECJxlsDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.core.client.reportexporter.dto.decorator.RECJxlsDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.core.client.reportmanager.dto.reports.AbstractReportManagerNodeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.core.client.reportmanager.dto.ReportFolderDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.core.client.reportmanager.dto.reports.decorator.ReportDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportBytePropertyDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportMetadataDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportPropertyDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportServerSideStringPropertyDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportStringPropertyDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.core.client.reportmanager.dto.reports.ServerSidePropertyDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportVariantDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.crystal.client.crystal.dto.CrystalParameterProposalDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.crystal.client.crystal.dto.decorator.CrystalReportDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportFileDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportVariantDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.crystal.client.crystal.dto.decorator.CrystalReportVariantDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.FavoriteListDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListDadgetDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.FavoriteListDadgetDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListEntryDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.FavoriteListEntryDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.LibraryDadgetDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.LibraryDadgetDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.ParameterDadgetDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.ParameterDadgetDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.ReportDadgetDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.ReportDadgetDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.StaticHtmlDadgetDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.StaticHtmlDadgetDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.UrlDadgetDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.UrlDadgetDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.AbstractDashboardManagerNodeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DadgetDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetContainerDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetNodeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DashboardDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardContainerDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DashboardContainerDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardFolderDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardNodeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardReferenceDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DashboardReferenceDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.dashboard.client.dashboard.dto.LayoutTypeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.dropbox.client.dropbox.dto.DropboxDatasinkDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleEntryDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.EmailDatasinkDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.fileserver.client.fileserver.dto.decorator.FileServerFileDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.fileserver.client.fileserver.dto.EditCommandResultExtensionDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.ftp.client.ftp.dto.FtpDatasinkDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.ftp.client.ftp.dto.FtpsDatasinkDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.ftp.client.ftp.dto.SftpDatasinkDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.globalconstants.client.globalconstants.dto.GlobalConstantDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.googledrive.client.googledrive.dto.GoogleDriveDatasinkDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorColumnConfigDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorColumnConfigDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorConfigDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorDataDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorDataDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorRecordDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordEntryDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorRecordEntryDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.DateSelectionListEditorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.DateSelectionListEditorDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.DecimalSelectionListEditorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.DecimalSelectionListEditorDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.DoubleSelectionListEditorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.DoubleSelectionListEditorDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.EditorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.EditorDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.FloatSelectionListEditorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.FloatSelectionListEditorDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.IntBooleanEditorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.IntBooleanEditorDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.IntSelectionListEditorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.IntSelectionListEditorDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.LongSelectionListEditorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.LongSelectionListEditorDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.SelectionListEditorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.TextAreaEditorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.TextAreaEditorDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.TextBooleanEditorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.TextBooleanEditorDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.TextDateEditorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.TextDateEditorDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.TextSelectionListEditorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.TextSelectionListEditorDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.CustomValidatorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.CustomValidatorDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.EmptyValidatorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.EmptyValidatorDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.FixedLengthValidatorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.FixedLengthValidatorDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.MaxBigDecimalValidatorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxBigDecimalValidatorDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.MaxDateValidatorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxDateValidatorDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.MaxDoubleValidatorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxDoubleValidatorDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.MaxFloatValidatorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxFloatValidatorDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.MaxIntegerValidatorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxIntegerValidatorDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.MaxLengthValidatorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxLengthValidatorDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.MaxLongValidatorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxLongValidatorDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.MinBigDecimalValidatorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinBigDecimalValidatorDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.MinDateValidatorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinDateValidatorDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.MinDoubleValidatorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinDoubleValidatorDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.MinFloatValidatorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinFloatValidatorDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.MinIntegerValidatorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinIntegerValidatorDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.MinLengthValidatorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinLengthValidatorDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.MinLongValidatorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinLongValidatorDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.RegExValidatorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.RegExValidatorDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.ValidatorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.ValidatorDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorReportDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorReportDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorReportVariantDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorReportVariantDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.incubator.client.jaspertotable.dto.JasperToTableConfigDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.incubator.client.jaspertotable.dto.decorator.JasperToTableConfigDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.decorator.JxlsReportDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportFileDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportVariantDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.decorator.JxlsReportVariantDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto.LocalFileSystemDatasinkDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.onedrive.client.onedrive.dto.OneDriveDatasinkDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.printer.client.printer.dto.PrinterDatasinkDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.remotersserver.client.remotersserver.dto.RemoteRsServerDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.AbstractRemoteServerManagerNodeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerContainerDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerContainerProviderDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerDefinitionDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerFolderDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.reportdoc.client.dto.DeployAnalyzeCommandResultExtensionDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.reportdoc.client.dto.VariantTestCommandResultExtensionDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceConfigDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.saiku.client.saiku.dto.decorator.SaikuReportDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportVariantDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.saiku.client.saiku.dto.decorator.SaikuReportVariantDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.saiku.client.saiku.dto.RECSaikuChartDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.saiku.client.saiku.dto.decorator.RECSaikuChartDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.samba.client.samba.dto.SambaDatasinkDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto.ExecutedReportFileReferenceDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto.decorator.ExecutedReportFileReferenceDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.scheduleasfile.client.scheduleasfile.filter.dto.TeamSpaceReportJobFilterDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.scheduler.client.scheduler.dto.ReportServerJobFilterDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.scp.client.scp.dto.ScpDatasinkDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.scriptdatasink.client.scriptdatasink.dto.ScriptDatasinkDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto.ScriptDatasourceDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto.ScriptDatasourceConfigDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.scripting.client.scripting.dto.AddMenuEntryExtensionDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.scripting.client.scripting.dto.AddMenuSeparatorEntryExtensionDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.scripting.client.scripting.dto.AddReportExportFormatProviderDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.scripting.client.scripting.dto.AddStatusbBarLabelExtensionDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.scripting.client.scripting.dto.AddToolbarEntryExtensionDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.scripting.client.scripting.dto.DisplayConditionDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.scripting.client.scripting.dto.DisplayConditionTypeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.scriptreport.client.scriptreport.dto.ScriptReportDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.scriptreport.client.scriptreport.dto.decorator.ScriptReportDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.scriptreport.client.scriptreport.dto.ScriptReportVariantDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.scriptreport.client.scriptreport.dto.decorator.ScriptReportVariantDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.ScriptParameterDefinitionDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.ScriptParameterInstanceDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.search.client.search.dto.SearchFilterDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.search.client.search.dto.decorator.SearchFilterDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.search.client.search.dto.SearchResultEntryDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.search.client.search.dto.decorator.SearchResultEntryDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.search.client.search.dto.SearchResultListDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.search.client.search.dto.decorator.SearchResultListDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.search.client.search.dto.SearchResultTagDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.search.client.search.dto.SearchResultTagTypeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.tabledatasink.client.tabledatasink.dto.TableDatasinkDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.RECTableTemplateDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportByteTemplateDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportStringTemplateDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportTemplateDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportTemplateListDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.teamspace.client.teamspace.dto.AppPropertyDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.teamspace.client.teamspace.dto.decorator.TeamSpaceDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceAppDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceMemberDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceRoleDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.teamspace.client.teamspace.security.TeamSpaceSecureeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.teamspace.client.teamspace.security.rights.TeamSpaceAdministratorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.terminal.client.terminal.dto.AutocompleteResultDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.terminal.client.terminal.dto.decorator.AutocompleteResultDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.terminal.client.terminal.dto.CommandResultDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.terminal.client.terminal.dto.CommandResultAnchorDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultAnchorDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.terminal.client.terminal.dto.CommandResultEntryDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultEntryDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.terminal.client.terminal.dto.CommandResultExtensionDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.terminal.client.terminal.dto.CommandResultHtmlDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultHtmlDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.terminal.client.terminal.dto.CommandResultHyperlinkDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultHyperlinkDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.terminal.client.terminal.dto.CommandResultLineDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultLineDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.terminal.client.terminal.dto.CommandResultListDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultListDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.terminal.client.terminal.dto.CommandResultModifierDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.terminal.client.terminal.dto.CommandResultTableDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultTableDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.terminal.client.terminal.dto.CreJavaScriptDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.terminal.client.terminal.dto.CreMessageDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.terminal.client.terminal.dto.CreOverlayDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.terminal.client.terminal.dto.DisplayModeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.terminal.client.terminal.dto.InteractiveResultModifierDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.terminal.client.terminal.dto.PressKeyResultModifierDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.AbstractTsDiskNodeDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFileReferenceDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.TsDiskFileReferenceDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFolderDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskGeneralReferenceDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.TsDiskGeneralReferenceDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.TsDiskReportReferenceDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskRootDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.uservariables.client.parameters.dto.UserVariableParameterDefinitionDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.uservariables.client.parameters.dto.UserVariableParameterInstanceDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.uservariables.client.parameters.dto.decorator.UserVariableParameterInstanceDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableDefinitionDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableInstanceDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.uservariables.client.variabletypes.list.dto.ListUserVariableDefinitionDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.uservariables.client.variabletypes.list.dto.ListUserVariableInstanceDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.uservariables.client.variabletypes.string.dto.StringUserVariableDefinitionDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.rs.uservariables.client.variabletypes.string.dto.StringUserVariableInstanceDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.scheduler.client.scheduler.dto.JobExecutionStatusDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.scheduler.client.scheduler.dto.MisfireInstructionDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.scheduler.client.scheduler.dto.OutcomeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.scheduler.client.scheduler.dto.history.ActionEntryDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.scheduler.client.scheduler.dto.history.ExecutionLogEntryDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.scheduler.client.scheduler.dto.history.HistoryEntryPropertyDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.scheduler.client.scheduler.dto.history.JobEntryDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.scheduler.client.scheduler.dto.history.JobHistoryDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.scheduler.client.scheduler.dto.config.complex.RetryTimeUnitDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.scheduler.client.scheduler.dto.VetoActionExecutionModeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.scheduler.client.scheduler.dto.VetoJobExecutionModeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterConfigurationDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterCriteriaDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.scheduler.client.scheduler.dto.filter.OrderDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.scheduler.client.scheduler.dto.config.complex.DailyConfigDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.scheduler.client.scheduler.dto.config.complex.DateTriggerConfigDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthlyNthDayConfigDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthlyNthDayOfWeekConfigDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.scheduler.client.scheduler.dto.config.complex.TimeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.scheduler.client.scheduler.dto.config.complex.decorator.TimeDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.scheduler.client.scheduler.dto.config.complex.WeeklyConfigDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.scheduler.client.scheduler.dto.config.complex.YearlyAtDateConfigDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.scheduler.client.scheduler.dto.config.complex.YearlyNthDayOfWeekConfigDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.scheduler.client.scheduler.dto.config.complex.DailyPatternDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.scheduler.client.scheduler.dto.config.complex.DailyRepeatTypeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.scheduler.client.scheduler.dto.config.complex.DaysDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.scheduler.client.scheduler.dto.config.complex.EndTypesDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthsDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.scheduler.client.scheduler.dto.config.complex.NthDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.scheduler.client.scheduler.dto.config.complex.TimeUnitsDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.security.client.security.dto.SecureeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.security.client.security.dto.SecurityServiceSecureeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.security.client.security.dto.AccessTypeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.security.client.security.dto.AceDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.security.client.security.dto.decorator.AceDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.security.client.security.dto.AceAccessMapDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.security.client.security.dto.decorator.AceAccessMapDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.security.client.security.dto.HierarchicalAceDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.security.client.security.dto.InheritanceTypeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.security.client.security.dto.DeleteDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.security.client.security.dto.ExecuteDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.security.client.security.dto.GrantAccessDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.security.client.security.dto.ReadDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.security.client.security.dto.RightDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.security.client.security.dto.WriteDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.security.client.treedb.dto.SecuredAbstractNodeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.security.client.treedb.dto.decorator.SecuredAbstractNodeDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.security.client.usermanager.dto.GroupDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.security.client.usermanager.dto.SexDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.security.client.usermanager.dto.UserDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.security.client.usermanager.dto.decorator.UserDtoDec".equals(dtoClassName))
			return true;
		if("net.datenwerke.security.client.usermanager.dto.UserPropertyDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto".equals(dtoClassName))
			return true;
		if("net.datenwerke.treedb.client.treedb.dto.decorator.AbstractNodeDtoDec".equals(dtoClassName))
			return true;
		return false;
	}


	public void attachSubModule(net.datenwerke.gxtdto.client.dtoinfo.DtoInformationService subModule)  {
		subModules.add(subModule);
	}


}
