package net.datenwerke.gf.service.dtoservice;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import java.lang.IllegalStateException;
import java.lang.ThreadLocal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.IdedDto;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.fto.FtoSupervisor;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoCreationHelper;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import org.hibernate.proxy.HibernateProxy;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DtoServiceImpl implements DtoMainService, DtoService {

	@SuppressWarnings("unchecked")
	private List<DtoService> subModules = new ArrayList<DtoService>();

	private ThreadLocal<DtoCreationHelper> creationHelper = new ThreadLocal<DtoCreationHelper>();

	final private Provider<DtoCreationHelper> creationHelperProvider;

	@SuppressWarnings("unchecked")
	private Map<Class<?>, Poso2DtoGenerator> dtoGeneratorByPosoLookup;

	@SuppressWarnings("unchecked")
	private Map<Class<?>, Dto2PosoGenerator> dto2PosoGeneratorLookup;

	@SuppressWarnings("unchecked")
	private Set<Class<?>> dtoClassLookup;

	@SuppressWarnings("unchecked")
	private Set<Class<?>> posoClassLookup;

	@SuppressWarnings("unchecked")
	@Inject
	public DtoServiceImpl(
		Provider<DtoCreationHelper> creationHelperProvider

		, Injector injector


	){
		this.creationHelperProvider = creationHelperProvider;

		dtoGeneratorByPosoLookup = new HashMap<Class<?>, Poso2DtoGenerator>();

		dtoGeneratorByPosoLookup.put(net.datenwerke.gf.service.history.HistoryLink.class, injector.getInstance(net.datenwerke.gf.service.history.dtogen.HistoryLink2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.gf.service.juel.JuelResult.class, injector.getInstance(net.datenwerke.gf.service.juel.dtogen.JuelResult2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.gf.service.juel.JuelResultType.class, injector.getInstance(net.datenwerke.gf.service.juel.dtogen.JuelResultType2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.adminutils.service.logs.terminal.commands.ViewLogFileCommandResultExtension.class, injector.getInstance(net.datenwerke.rs.adminutils.service.logs.terminal.commands.dtogen.ViewLogFileCommandResultExtension2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.amazons3.service.amazons3.definitions.AmazonS3Datasink.class, injector.getInstance(net.datenwerke.rs.amazons3.service.amazons3.definitions.dtogen.AmazonS3Datasink2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.ext.service.parameters.fileselection.FileSelectionParameterDefinition.class, injector.getInstance(net.datenwerke.rs.base.ext.service.parameters.fileselection.dtogen.FileSelectionParameterDefinition2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.ext.service.parameters.fileselection.FileSelectionParameterInstance.class, injector.getInstance(net.datenwerke.rs.base.ext.service.parameters.fileselection.dtogen.FileSelectionParameterInstance2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.ext.service.parameters.fileselection.SelectedParameterFile.class, injector.getInstance(net.datenwerke.rs.base.ext.service.parameters.fileselection.dtogen.SelectedParameterFile2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.ext.service.parameters.fileselection.UploadedParameterFile.class, injector.getInstance(net.datenwerke.rs.base.ext.service.parameters.fileselection.dtogen.UploadedParameterFile2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.datasources.connectors.ArgumentDatasourceConnector.class, injector.getInstance(net.datenwerke.rs.base.service.datasources.connectors.dtogen.ArgumentDatasourceConnector2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.datasources.connectors.DatasourceConnector.class, injector.getInstance(net.datenwerke.rs.base.service.datasources.connectors.dtogen.DatasourceConnector2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.datasources.connectors.DatasourceConnectorConfig.class, injector.getInstance(net.datenwerke.rs.base.service.datasources.connectors.dtogen.DatasourceConnectorConfig2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.datasources.connectors.TextDatasourceConnector.class, injector.getInstance(net.datenwerke.rs.base.service.datasources.connectors.dtogen.TextDatasourceConnector2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.datasources.connectors.UrlDatasourceConnector.class, injector.getInstance(net.datenwerke.rs.base.service.datasources.connectors.dtogen.UrlDatasourceConnector2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.datasources.definitions.CsvDatasource.class, injector.getInstance(net.datenwerke.rs.base.service.datasources.definitions.dtogen.CsvDatasource2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.datasources.definitions.CsvDatasourceConfig.class, injector.getInstance(net.datenwerke.rs.base.service.datasources.definitions.dtogen.CsvDatasourceConfig2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource.class, injector.getInstance(net.datenwerke.rs.base.service.datasources.definitions.dtogen.DatabaseDatasource2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasourceConfig.class, injector.getInstance(net.datenwerke.rs.base.service.datasources.definitions.dtogen.DatabaseDatasourceConfig2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.dbhelper.DatabaseHelper.class, injector.getInstance(net.datenwerke.rs.base.service.dbhelper.dtogen.DatabaseHelper2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.parameters.blatext.BlatextParameterDefinition.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.blatext.dtogen.BlatextParameterDefinition2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.parameters.blatext.BlatextParameterInstance.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.blatext.dtogen.BlatextParameterInstance2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.parameters.datasource.BoxLayoutMode.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.datasource.dtogen.BoxLayoutMode2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.parameters.datasource.BoxLayoutPackMode.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.datasource.dtogen.BoxLayoutPackMode2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.parameters.datasource.DatasourceParameterData.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.datasource.dtogen.DatasourceParameterData2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.parameters.datasource.DatasourceParameterDefinition.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.datasource.dtogen.DatasourceParameterDefinition2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.parameters.datasource.DatasourceParameterInstance.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.datasource.dtogen.DatasourceParameterInstance2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.parameters.datasource.Mode.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.datasource.dtogen.Mode2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.parameters.datasource.MultiSelectionMode.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.datasource.dtogen.MultiSelectionMode2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.parameters.datasource.SingleSelectionMode.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.datasource.dtogen.SingleSelectionMode2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.parameters.datetime.DateTimeParameterDefinition.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.datetime.dtogen.DateTimeParameterDefinition2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.parameters.datetime.DateTimeParameterInstance.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.datetime.dtogen.DateTimeParameterInstance2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.parameters.datetime.Mode.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.datetime.dtogen.Mode2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.parameters.headline.HeadlineParameterDefinition.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.headline.dtogen.HeadlineParameterDefinition2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.parameters.headline.HeadlineParameterInstance.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.headline.dtogen.HeadlineParameterInstance2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.parameters.separator.SeparatorParameterDefinition.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.separator.dtogen.SeparatorParameterDefinition2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.parameters.separator.SeparatorParameterInstance.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.separator.dtogen.SeparatorParameterInstance2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.parameters.string.TextParameterDefinition.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.string.dtogen.TextParameterDefinition2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.parameters.string.TextParameterInstance.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.string.dtogen.TextParameterInstance2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReport.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.jasper.entities.dtogen.JasperReport2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReportJRXMLFile.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.jasper.entities.dtogen.JasperReportJRXMLFile2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReportVariant.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.jasper.entities.dtogen.JasperReportVariant2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.reportengines.jasper.output.object.CompiledCSVJasperReport.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.jasper.output.object.dtogen.CompiledCSVJasperReport2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.reportengines.jasper.output.object.CompiledHTMLJasperReport.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.jasper.output.object.dtogen.CompiledHTMLJasperReport2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.reportengines.jasper.output.object.CompiledPNGJasperReport.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.jasper.output.object.dtogen.CompiledPNGJasperReport2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.reportengines.jasper.util.JasperParameterProposal.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.jasper.util.dtogen.JasperParameterProposal2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.reportengines.table.entities.AdditionalColumnSpec.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.dtogen.AdditionalColumnSpec2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.reportengines.table.entities.AggregateFunction.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.dtogen.AggregateFunction2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.reportengines.table.entities.Column.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.dtogen.Column2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.reportengines.table.entities.ColumnReference.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.dtogen.ColumnReference2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.reportengines.table.entities.FilterRange.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.dtogen.FilterRange2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.reportengines.table.entities.NullHandling.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.dtogen.NullHandling2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.reportengines.table.entities.Order.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.dtogen.Order2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.reportengines.table.entities.TableReport.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.dtogen.TableReport2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.reportengines.table.entities.TableReportVariant.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.dtogen.TableReportVariant2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.reportengines.table.entities.filters.BinaryColumnFilter.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.filters.dtogen.BinaryColumnFilter2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.reportengines.table.entities.filters.BinaryOperator.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.filters.dtogen.BinaryOperator2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.reportengines.table.entities.filters.BlockType.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.filters.dtogen.BlockType2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.reportengines.table.entities.filters.ColumnFilter.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.filters.dtogen.ColumnFilter2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.reportengines.table.entities.filters.Filter.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.filters.dtogen.Filter2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.reportengines.table.entities.filters.FilterBlock.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.filters.dtogen.FilterBlock2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.reportengines.table.entities.filters.PreFilter.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.filters.dtogen.PreFilter2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatCurrency.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.format.dtogen.ColumnFormatCurrency2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatDate.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.format.dtogen.ColumnFormatDate2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatNumber.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.format.dtogen.ColumnFormatNumber2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatTemplate.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.format.dtogen.ColumnFormatTemplate2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatText.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.format.dtogen.ColumnFormatText2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.reportengines.table.entities.format.enums.CurrencyType.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.format.enums.dtogen.CurrencyType2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.reportengines.table.entities.format.enums.NumberType.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.format.enums.dtogen.NumberType2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.reportengines.table.output.object.CompiledHTMLTableReport.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.output.object.dtogen.CompiledHTMLTableReport2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.reportengines.table.output.object.RSStringTableRow.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.output.object.dtogen.RSStringTableRow2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.output.object.dtogen.RSTableModel2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableRow.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.output.object.dtogen.RSTableRow2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.output.object.dtogen.TableDefinition2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.birt.service.datasources.birtreport.entities.BirtReportDatasourceConfig.class, injector.getInstance(net.datenwerke.rs.birt.service.datasources.birtreport.entities.dtogen.BirtReportDatasourceConfig2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.birt.service.datasources.birtreport.entities.BirtReportDatasourceDefinition.class, injector.getInstance(net.datenwerke.rs.birt.service.datasources.birtreport.entities.dtogen.BirtReportDatasourceDefinition2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.birt.service.datasources.birtreport.entities.BirtReportDatasourceTargetType.class, injector.getInstance(net.datenwerke.rs.birt.service.datasources.birtreport.entities.dtogen.BirtReportDatasourceTargetType2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.birt.service.reportengine.entities.BirtReport.class, injector.getInstance(net.datenwerke.rs.birt.service.reportengine.entities.dtogen.BirtReport2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.birt.service.reportengine.entities.BirtReportFile.class, injector.getInstance(net.datenwerke.rs.birt.service.reportengine.entities.dtogen.BirtReportFile2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.birt.service.reportengine.entities.BirtReportVariant.class, injector.getInstance(net.datenwerke.rs.birt.service.reportengine.entities.dtogen.BirtReportVariant2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.birt.service.reportengine.output.object.CompiledHTMLBirtReport.class, injector.getInstance(net.datenwerke.rs.birt.service.reportengine.output.object.dtogen.CompiledHTMLBirtReport2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.birt.service.reportengine.output.object.CompiledPNGBirtReport.class, injector.getInstance(net.datenwerke.rs.birt.service.reportengine.output.object.dtogen.CompiledPNGBirtReport2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.birt.service.utils.BirtParameterProposal.class, injector.getInstance(net.datenwerke.rs.birt.service.utils.dtogen.BirtParameterProposal2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.box.service.box.definitions.BoxDatasink.class, injector.getInstance(net.datenwerke.rs.box.service.box.definitions.dtogen.BoxDatasink2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.computedcolumns.service.computedcolumns.entities.ComputedColumn.class, injector.getInstance(net.datenwerke.rs.computedcolumns.service.computedcolumns.entities.dtogen.ComputedColumn2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.condition.service.condition.entity.ReportCondition.class, injector.getInstance(net.datenwerke.rs.condition.service.condition.entity.dtogen.ReportCondition2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkContainer.class, injector.getInstance(net.datenwerke.rs.core.service.datasinkmanager.entities.dtogen.DatasinkContainer2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkFolder.class, injector.getInstance(net.datenwerke.rs.core.service.datasinkmanager.entities.dtogen.DatasinkFolder2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer.class, injector.getInstance(net.datenwerke.rs.core.service.datasourcemanager.entities.dtogen.DatasourceContainer2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinitionConfig.class, injector.getInstance(net.datenwerke.rs.core.service.datasourcemanager.entities.dtogen.DatasourceDefinitionConfig2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceFolder.class, injector.getInstance(net.datenwerke.rs.core.service.datasourcemanager.entities.dtogen.DatasourceFolder2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.core.service.i18ntools.FormatPatterns.class, injector.getInstance(net.datenwerke.rs.core.service.i18ntools.dtogen.FormatPatterns2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.core.service.parameters.entities.Datatype.class, injector.getInstance(net.datenwerke.rs.core.service.parameters.entities.dtogen.Datatype2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.core.service.reportmanager.engine.config.RECCsv.class, injector.getInstance(net.datenwerke.rs.core.service.reportmanager.engine.config.dtogen.RECCsv2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.core.service.reportmanager.engine.config.RECJxls.class, injector.getInstance(net.datenwerke.rs.core.service.reportmanager.engine.config.dtogen.RECJxls2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.core.service.reportmanager.entities.ReportFolder.class, injector.getInstance(net.datenwerke.rs.core.service.reportmanager.entities.dtogen.ReportFolder2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.core.service.reportmanager.entities.reports.Report.class, injector.getInstance(net.datenwerke.rs.core.service.reportmanager.entities.reports.dtogen.Report2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportByteProperty.class, injector.getInstance(net.datenwerke.rs.core.service.reportmanager.entities.reports.dtogen.ReportByteProperty2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportMetadata.class, injector.getInstance(net.datenwerke.rs.core.service.reportmanager.entities.reports.dtogen.ReportMetadata2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportProperty.class, injector.getInstance(net.datenwerke.rs.core.service.reportmanager.entities.reports.dtogen.ReportProperty2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportServerSideStringProperty.class, injector.getInstance(net.datenwerke.rs.core.service.reportmanager.entities.reports.dtogen.ReportServerSideStringProperty2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportStringProperty.class, injector.getInstance(net.datenwerke.rs.core.service.reportmanager.entities.reports.dtogen.ReportStringProperty2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.crystal.service.crystal.CrystalParameterProposal.class, injector.getInstance(net.datenwerke.rs.crystal.service.crystal.dtogen.CrystalParameterProposal2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.crystal.service.crystal.entities.CrystalReport.class, injector.getInstance(net.datenwerke.rs.crystal.service.crystal.entities.dtogen.CrystalReport2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.crystal.service.crystal.entities.CrystalReportFile.class, injector.getInstance(net.datenwerke.rs.crystal.service.crystal.entities.dtogen.CrystalReportFile2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.crystal.service.crystal.entities.CrystalReportVariant.class, injector.getInstance(net.datenwerke.rs.crystal.service.crystal.entities.dtogen.CrystalReportVariant2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.dashboard.service.dashboard.dagets.FavoriteList.class, injector.getInstance(net.datenwerke.rs.dashboard.service.dashboard.dagets.dtogen.FavoriteList2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.dashboard.service.dashboard.dagets.FavoriteListDadget.class, injector.getInstance(net.datenwerke.rs.dashboard.service.dashboard.dagets.dtogen.FavoriteListDadget2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.dashboard.service.dashboard.dagets.FavoriteListEntry.class, injector.getInstance(net.datenwerke.rs.dashboard.service.dashboard.dagets.dtogen.FavoriteListEntry2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.dashboard.service.dashboard.dagets.LibraryDadget.class, injector.getInstance(net.datenwerke.rs.dashboard.service.dashboard.dagets.dtogen.LibraryDadget2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.dashboard.service.dashboard.dagets.ParameterDadget.class, injector.getInstance(net.datenwerke.rs.dashboard.service.dashboard.dagets.dtogen.ParameterDadget2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.dashboard.service.dashboard.dagets.ReportDadget.class, injector.getInstance(net.datenwerke.rs.dashboard.service.dashboard.dagets.dtogen.ReportDadget2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.dashboard.service.dashboard.dagets.StaticHtmlDadget.class, injector.getInstance(net.datenwerke.rs.dashboard.service.dashboard.dagets.dtogen.StaticHtmlDadget2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.dashboard.service.dashboard.dagets.UrlDadget.class, injector.getInstance(net.datenwerke.rs.dashboard.service.dashboard.dagets.dtogen.UrlDadget2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.dashboard.service.dashboard.entities.DadgetContainer.class, injector.getInstance(net.datenwerke.rs.dashboard.service.dashboard.entities.dtogen.DadgetContainer2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.dashboard.service.dashboard.entities.DadgetNode.class, injector.getInstance(net.datenwerke.rs.dashboard.service.dashboard.entities.dtogen.DadgetNode2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.dashboard.service.dashboard.entities.Dashboard.class, injector.getInstance(net.datenwerke.rs.dashboard.service.dashboard.entities.dtogen.Dashboard2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardContainer.class, injector.getInstance(net.datenwerke.rs.dashboard.service.dashboard.entities.dtogen.DashboardContainer2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardFolder.class, injector.getInstance(net.datenwerke.rs.dashboard.service.dashboard.entities.dtogen.DashboardFolder2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardNode.class, injector.getInstance(net.datenwerke.rs.dashboard.service.dashboard.entities.dtogen.DashboardNode2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardReference.class, injector.getInstance(net.datenwerke.rs.dashboard.service.dashboard.entities.dtogen.DashboardReference2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.dashboard.service.dashboard.entities.LayoutType.class, injector.getInstance(net.datenwerke.rs.dashboard.service.dashboard.entities.dtogen.LayoutType2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.dropbox.service.dropbox.definitions.DropboxDatasink.class, injector.getInstance(net.datenwerke.rs.dropbox.service.dropbox.definitions.dtogen.DropboxDatasink2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.dsbundle.service.dsbundle.entities.DatabaseBundle.class, injector.getInstance(net.datenwerke.rs.dsbundle.service.dsbundle.entities.dtogen.DatabaseBundle2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.dsbundle.service.dsbundle.entities.DatabaseBundleEntry.class, injector.getInstance(net.datenwerke.rs.dsbundle.service.dsbundle.entities.dtogen.DatabaseBundleEntry2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.EmailDatasink.class, injector.getInstance(net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.dtogen.EmailDatasink2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile.class, injector.getInstance(net.datenwerke.rs.fileserver.service.fileserver.entities.dtogen.FileServerFile2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder.class, injector.getInstance(net.datenwerke.rs.fileserver.service.fileserver.entities.dtogen.FileServerFolder2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.fileserver.service.fileserver.terminal.commands.EditCommandResultExtension.class, injector.getInstance(net.datenwerke.rs.fileserver.service.fileserver.terminal.commands.dtogen.EditCommandResultExtension2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.ftp.service.ftp.definitions.FtpDatasink.class, injector.getInstance(net.datenwerke.rs.ftp.service.ftp.definitions.dtogen.FtpDatasink2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.ftp.service.ftp.definitions.FtpsDatasink.class, injector.getInstance(net.datenwerke.rs.ftp.service.ftp.definitions.dtogen.FtpsDatasink2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.ftp.service.ftp.definitions.SftpDatasink.class, injector.getInstance(net.datenwerke.rs.ftp.service.ftp.definitions.dtogen.SftpDatasink2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.globalconstants.service.globalconstants.entities.GlobalConstant.class, injector.getInstance(net.datenwerke.rs.globalconstants.service.globalconstants.entities.dtogen.GlobalConstant2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.googledrive.service.googledrive.definitions.GoogleDriveDatasink.class, injector.getInstance(net.datenwerke.rs.googledrive.service.googledrive.definitions.dtogen.GoogleDriveDatasink2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.grideditor.service.grideditor.definition.GridEditorColumnConfig.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.definition.dtogen.GridEditorColumnConfig2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.grideditor.service.grideditor.definition.GridEditorConfig.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.definition.dtogen.GridEditorConfig2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.grideditor.service.grideditor.definition.GridEditorData.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.definition.dtogen.GridEditorData2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.grideditor.service.grideditor.definition.GridEditorRecord.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.definition.dtogen.GridEditorRecord2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.grideditor.service.grideditor.definition.GridEditorRecordEntry.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.definition.dtogen.GridEditorRecordEntry2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.DateSelectionListEditor.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.dtogen.DateSelectionListEditor2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.DecimalSelectionListEditor.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.dtogen.DecimalSelectionListEditor2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.DoubleSelectionListEditor.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.dtogen.DoubleSelectionListEditor2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.FloatSelectionListEditor.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.dtogen.FloatSelectionListEditor2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.IntBooleanEditor.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.dtogen.IntBooleanEditor2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.IntSelectionListEditor.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.dtogen.IntSelectionListEditor2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.LongSelectionListEditor.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.dtogen.LongSelectionListEditor2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.TextAreaEditor.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.dtogen.TextAreaEditor2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.TextBooleanEditor.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.dtogen.TextBooleanEditor2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.TextDateEditor.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.dtogen.TextDateEditor2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.TextSelectionListEditor.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.dtogen.TextSelectionListEditor2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.CustomValidator.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.dtogen.CustomValidator2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.EmptyValidator.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.dtogen.EmptyValidator2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.FixedLengthValidator.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.dtogen.FixedLengthValidator2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MaxBigDecimalValidator.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.dtogen.MaxBigDecimalValidator2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MaxDateValidator.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.dtogen.MaxDateValidator2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MaxDoubleValidator.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.dtogen.MaxDoubleValidator2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MaxFloatValidator.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.dtogen.MaxFloatValidator2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MaxIntegerValidator.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.dtogen.MaxIntegerValidator2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MaxLengthValidator.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.dtogen.MaxLengthValidator2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MaxLongValidator.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.dtogen.MaxLongValidator2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MinBigDecimalValidator.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.dtogen.MinBigDecimalValidator2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MinDateValidator.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.dtogen.MinDateValidator2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MinDoubleValidator.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.dtogen.MinDoubleValidator2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MinFloatValidator.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.dtogen.MinFloatValidator2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MinIntegerValidator.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.dtogen.MinIntegerValidator2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MinLengthValidator.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.dtogen.MinLengthValidator2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MinLongValidator.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.dtogen.MinLongValidator2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.RegExValidator.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.dtogen.RegExValidator2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.grideditor.service.grideditor.entities.GridEditorReport.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.entities.dtogen.GridEditorReport2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.grideditor.service.grideditor.entities.GridEditorReportVariant.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.entities.dtogen.GridEditorReportVariant2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.incubator.service.jaspertotable.entities.JasperToTableConfig.class, injector.getInstance(net.datenwerke.rs.incubator.service.jaspertotable.entities.dtogen.JasperToTableConfig2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.incubator.service.scriptdatasource.entities.ScriptDatasource.class, injector.getInstance(net.datenwerke.rs.incubator.service.scriptdatasource.entities.dtogen.ScriptDatasource2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.incubator.service.scriptdatasource.entities.ScriptDatasourceConfig.class, injector.getInstance(net.datenwerke.rs.incubator.service.scriptdatasource.entities.dtogen.ScriptDatasourceConfig2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.JxlsReport.class, injector.getInstance(net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.dtogen.JxlsReport2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.JxlsReportFile.class, injector.getInstance(net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.dtogen.JxlsReportFile2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.JxlsReportVariant.class, injector.getInstance(net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.dtogen.JxlsReportVariant2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.localfsdatasink.service.localfsdatasink.definitions.LocalFileSystemDatasink.class, injector.getInstance(net.datenwerke.rs.localfsdatasink.service.localfsdatasink.definitions.dtogen.LocalFileSystemDatasink2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.onedrive.service.onedrive.definitions.OneDriveDatasink.class, injector.getInstance(net.datenwerke.rs.onedrive.service.onedrive.definitions.dtogen.OneDriveDatasink2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.reportdoc.service.terminal.commands.DeployAnalyzeCommandResultExtension.class, injector.getInstance(net.datenwerke.rs.reportdoc.service.terminal.commands.dtogen.DeployAnalyzeCommandResultExtension2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.reportdoc.service.terminal.commands.VariantTestCommandResultExtension.class, injector.getInstance(net.datenwerke.rs.reportdoc.service.terminal.commands.dtogen.VariantTestCommandResultExtension2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.saiku.service.datasource.MondrianDatasource.class, injector.getInstance(net.datenwerke.rs.saiku.service.datasource.dtogen.MondrianDatasource2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.saiku.service.datasource.MondrianDatasourceConfig.class, injector.getInstance(net.datenwerke.rs.saiku.service.datasource.dtogen.MondrianDatasourceConfig2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport.class, injector.getInstance(net.datenwerke.rs.saiku.service.saiku.entities.dtogen.SaikuReport2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.saiku.service.saiku.entities.SaikuReportVariant.class, injector.getInstance(net.datenwerke.rs.saiku.service.saiku.entities.dtogen.SaikuReportVariant2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.saiku.service.saiku.reportengine.config.RECSaikuChart.class, injector.getInstance(net.datenwerke.rs.saiku.service.saiku.reportengine.config.dtogen.RECSaikuChart2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.samba.service.samba.definitions.SambaDatasink.class, injector.getInstance(net.datenwerke.rs.samba.service.samba.definitions.dtogen.SambaDatasink2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.scheduleasfile.service.scheduleasfile.entities.ExecutedReportFileReference.class, injector.getInstance(net.datenwerke.rs.scheduleasfile.service.scheduleasfile.entities.dtogen.ExecutedReportFileReference2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.scheduleasfile.service.scheduleasfile.filter.TeamSpaceReportJobFilter.class, injector.getInstance(net.datenwerke.rs.scheduleasfile.service.scheduleasfile.filter.dtogen.TeamSpaceReportJobFilter2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.scheduler.service.scheduler.jobs.filter.ReportServerJobFilter.class, injector.getInstance(net.datenwerke.rs.scheduler.service.scheduler.jobs.filter.dtogen.ReportServerJobFilter2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.scp.service.scp.definitions.ScpDatasink.class, injector.getInstance(net.datenwerke.rs.scp.service.scp.definitions.dtogen.ScpDatasink2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.scripting.service.scripting.extensions.AddMenuEntryExtension.class, injector.getInstance(net.datenwerke.rs.scripting.service.scripting.extensions.dtogen.AddMenuEntryExtension2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.scripting.service.scripting.extensions.AddMenuSeparatorEntryExtension.class, injector.getInstance(net.datenwerke.rs.scripting.service.scripting.extensions.dtogen.AddMenuSeparatorEntryExtension2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.scripting.service.scripting.extensions.AddReportExportFormatProvider.class, injector.getInstance(net.datenwerke.rs.scripting.service.scripting.extensions.dtogen.AddReportExportFormatProvider2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.scripting.service.scripting.extensions.AddStatusbBarLabelExtension.class, injector.getInstance(net.datenwerke.rs.scripting.service.scripting.extensions.dtogen.AddStatusbBarLabelExtension2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.scripting.service.scripting.extensions.AddToolbarEntryExtension.class, injector.getInstance(net.datenwerke.rs.scripting.service.scripting.extensions.dtogen.AddToolbarEntryExtension2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.scripting.service.scripting.extensions.DisplayCondition.class, injector.getInstance(net.datenwerke.rs.scripting.service.scripting.extensions.dtogen.DisplayCondition2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.scripting.service.scripting.extensions.DisplayConditionType.class, injector.getInstance(net.datenwerke.rs.scripting.service.scripting.extensions.dtogen.DisplayConditionType2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.scriptreport.service.scriptreport.entities.ScriptReport.class, injector.getInstance(net.datenwerke.rs.scriptreport.service.scriptreport.entities.dtogen.ScriptReport2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.scriptreport.service.scriptreport.entities.ScriptReportVariant.class, injector.getInstance(net.datenwerke.rs.scriptreport.service.scriptreport.entities.dtogen.ScriptReportVariant2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.scriptreport.service.scriptreport.parameter.ScriptParameterDefinition.class, injector.getInstance(net.datenwerke.rs.scriptreport.service.scriptreport.parameter.dtogen.ScriptParameterDefinition2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.scriptreport.service.scriptreport.parameter.ScriptParameterInstance.class, injector.getInstance(net.datenwerke.rs.scriptreport.service.scriptreport.parameter.dtogen.ScriptParameterInstance2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.search.service.search.results.SearchFilter.class, injector.getInstance(net.datenwerke.rs.search.service.search.results.dtogen.SearchFilter2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.search.service.search.results.SearchResultEntry.class, injector.getInstance(net.datenwerke.rs.search.service.search.results.dtogen.SearchResultEntry2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.search.service.search.results.SearchResultList.class, injector.getInstance(net.datenwerke.rs.search.service.search.results.dtogen.SearchResultList2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.search.service.search.results.SearchResultTag.class, injector.getInstance(net.datenwerke.rs.search.service.search.results.dtogen.SearchResultTag2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.search.service.search.results.SearchResultTagType.class, injector.getInstance(net.datenwerke.rs.search.service.search.results.dtogen.SearchResultTagType2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.tabletemplate.service.tabletemplate.config.RECTableTemplate.class, injector.getInstance(net.datenwerke.rs.tabletemplate.service.tabletemplate.config.dtogen.RECTableTemplate2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.tabletemplate.service.tabletemplate.entities.TableReportByteTemplate.class, injector.getInstance(net.datenwerke.rs.tabletemplate.service.tabletemplate.entities.dtogen.TableReportByteTemplate2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.tabletemplate.service.tabletemplate.entities.TableReportStringTemplate.class, injector.getInstance(net.datenwerke.rs.tabletemplate.service.tabletemplate.entities.dtogen.TableReportStringTemplate2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.tabletemplate.service.tabletemplate.entities.TableReportTemplateList.class, injector.getInstance(net.datenwerke.rs.tabletemplate.service.tabletemplate.entities.dtogen.TableReportTemplateList2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.teamspace.service.teamspace.entities.AppProperty.class, injector.getInstance(net.datenwerke.rs.teamspace.service.teamspace.entities.dtogen.AppProperty2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace.class, injector.getInstance(net.datenwerke.rs.teamspace.service.teamspace.entities.dtogen.TeamSpace2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpaceApp.class, injector.getInstance(net.datenwerke.rs.teamspace.service.teamspace.entities.dtogen.TeamSpaceApp2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpaceMember.class, injector.getInstance(net.datenwerke.rs.teamspace.service.teamspace.entities.dtogen.TeamSpaceMember2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpaceRole.class, injector.getInstance(net.datenwerke.rs.teamspace.service.teamspace.entities.dtogen.TeamSpaceRole2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.teamspace.service.teamspace.security.TeamSpaceSecuree.class, injector.getInstance(net.datenwerke.rs.teamspace.service.teamspace.security.dtogen.TeamSpaceSecuree2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.teamspace.service.teamspace.security.rights.TeamSpaceAdministrator.class, injector.getInstance(net.datenwerke.rs.teamspace.service.teamspace.security.rights.dtogen.TeamSpaceAdministrator2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.terminal.service.terminal.obj.AutocompleteResult.class, injector.getInstance(net.datenwerke.rs.terminal.service.terminal.obj.dtogen.AutocompleteResult2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.terminal.service.terminal.obj.CommandResult.class, injector.getInstance(net.datenwerke.rs.terminal.service.terminal.obj.dtogen.CommandResult2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.terminal.service.terminal.obj.CommandResultAnchor.class, injector.getInstance(net.datenwerke.rs.terminal.service.terminal.obj.dtogen.CommandResultAnchor2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.terminal.service.terminal.obj.CommandResultHtml.class, injector.getInstance(net.datenwerke.rs.terminal.service.terminal.obj.dtogen.CommandResultHtml2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.terminal.service.terminal.obj.CommandResultHyperlink.class, injector.getInstance(net.datenwerke.rs.terminal.service.terminal.obj.dtogen.CommandResultHyperlink2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.terminal.service.terminal.obj.CommandResultLine.class, injector.getInstance(net.datenwerke.rs.terminal.service.terminal.obj.dtogen.CommandResultLine2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.terminal.service.terminal.obj.CommandResultList.class, injector.getInstance(net.datenwerke.rs.terminal.service.terminal.obj.dtogen.CommandResultList2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.terminal.service.terminal.obj.CommandResultTable.class, injector.getInstance(net.datenwerke.rs.terminal.service.terminal.obj.dtogen.CommandResultTable2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.terminal.service.terminal.obj.CreJavaScript.class, injector.getInstance(net.datenwerke.rs.terminal.service.terminal.obj.dtogen.CreJavaScript2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.terminal.service.terminal.obj.CreMessage.class, injector.getInstance(net.datenwerke.rs.terminal.service.terminal.obj.dtogen.CreMessage2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.terminal.service.terminal.obj.CreOverlay.class, injector.getInstance(net.datenwerke.rs.terminal.service.terminal.obj.dtogen.CreOverlay2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.terminal.service.terminal.obj.DisplayMode.class, injector.getInstance(net.datenwerke.rs.terminal.service.terminal.obj.dtogen.DisplayMode2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.terminal.service.terminal.obj.InteractiveResultModifier.class, injector.getInstance(net.datenwerke.rs.terminal.service.terminal.obj.dtogen.InteractiveResultModifier2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.terminal.service.terminal.obj.PressKeyResultModifier.class, injector.getInstance(net.datenwerke.rs.terminal.service.terminal.obj.dtogen.PressKeyResultModifier2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskFolder.class, injector.getInstance(net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.dtogen.TsDiskFolder2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskReportReference.class, injector.getInstance(net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.dtogen.TsDiskReportReference2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskRoot.class, injector.getInstance(net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.dtogen.TsDiskRoot2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.uservariables.service.parameters.UserVariableParameterDefinition.class, injector.getInstance(net.datenwerke.rs.uservariables.service.parameters.dtogen.UserVariableParameterDefinition2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.uservariables.service.parameters.UserVariableParameterInstance.class, injector.getInstance(net.datenwerke.rs.uservariables.service.parameters.dtogen.UserVariableParameterInstance2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.uservariables.service.variabletypes.list.ListUserVariableDefinition.class, injector.getInstance(net.datenwerke.rs.uservariables.service.variabletypes.list.dtogen.ListUserVariableDefinition2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.uservariables.service.variabletypes.list.ListUserVariableInstance.class, injector.getInstance(net.datenwerke.rs.uservariables.service.variabletypes.list.dtogen.ListUserVariableInstance2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.uservariables.service.variabletypes.string.StringUserVariableDefinition.class, injector.getInstance(net.datenwerke.rs.uservariables.service.variabletypes.string.dtogen.StringUserVariableDefinition2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.rs.uservariables.service.variabletypes.string.StringUserVariableInstance.class, injector.getInstance(net.datenwerke.rs.uservariables.service.variabletypes.string.dtogen.StringUserVariableInstance2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.scheduler.service.scheduler.entities.JobExecutionStatus.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.entities.dtogen.JobExecutionStatus2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.scheduler.service.scheduler.entities.MisfireInstruction.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.entities.dtogen.MisfireInstruction2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.scheduler.service.scheduler.entities.Outcome.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.entities.dtogen.Outcome2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.scheduler.service.scheduler.entities.history.ActionEntry.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.entities.history.dtogen.ActionEntry2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.scheduler.service.scheduler.entities.history.ExecutionLogEntry.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.entities.history.dtogen.ExecutionLogEntry2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.scheduler.service.scheduler.entities.history.HistoryEntryProperty.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.entities.history.dtogen.HistoryEntryProperty2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.scheduler.service.scheduler.entities.history.JobEntry.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.entities.history.dtogen.JobEntry2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.scheduler.service.scheduler.entities.history.JobHistory.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.entities.history.dtogen.JobHistory2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.scheduler.service.scheduler.helper.RetryTimeUnit.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.helper.dtogen.RetryTimeUnit2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.scheduler.service.scheduler.helper.VetoActionExecutionMode.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.helper.dtogen.VetoActionExecutionMode2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.scheduler.service.scheduler.helper.VetoJobExecutionMode.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.helper.dtogen.VetoJobExecutionMode2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.scheduler.service.scheduler.stores.jpa.filter.JobFilterConfiguration.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.stores.jpa.filter.dtogen.JobFilterConfiguration2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.scheduler.service.scheduler.stores.jpa.filter.Order.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.stores.jpa.filter.dtogen.Order2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.DailyConfig.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.dtogen.DailyConfig2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.DateTriggerConfig.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.dtogen.DateTriggerConfig2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.MonthlyNthDayConfig.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.dtogen.MonthlyNthDayConfig2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.MonthlyNthDayOfWeekConfig.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.dtogen.MonthlyNthDayOfWeekConfig2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.Time.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.dtogen.Time2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.WeeklyConfig.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.dtogen.WeeklyConfig2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.YearlyAtDateConfig.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.dtogen.YearlyAtDateConfig2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.YearlyNthDayOfWeekConfig.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.dtogen.YearlyNthDayOfWeekConfig2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.DailyPattern.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.dtogen.DailyPattern2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.DailyRepeatType.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.dtogen.DailyRepeatType2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.Days.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.dtogen.Days2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.EndTypes.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.dtogen.EndTypes2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.Months.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.dtogen.Months2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.Nth.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.dtogen.Nth2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.TimeUnits.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.dtogen.TimeUnits2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.security.service.security.SecurityServiceSecuree.class, injector.getInstance(net.datenwerke.security.service.security.dtogen.SecurityServiceSecuree2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.security.service.security.entities.AccessType.class, injector.getInstance(net.datenwerke.security.service.security.entities.dtogen.AccessType2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.security.service.security.entities.Ace.class, injector.getInstance(net.datenwerke.security.service.security.entities.dtogen.Ace2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.security.service.security.entities.AceAccessMap.class, injector.getInstance(net.datenwerke.security.service.security.entities.dtogen.AceAccessMap2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.security.service.security.entities.HierarchicalAce.class, injector.getInstance(net.datenwerke.security.service.security.entities.dtogen.HierarchicalAce2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.security.service.security.entities.InheritanceType.class, injector.getInstance(net.datenwerke.security.service.security.entities.dtogen.InheritanceType2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.security.service.security.rights.Delete.class, injector.getInstance(net.datenwerke.security.service.security.rights.dtogen.Delete2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.security.service.security.rights.Execute.class, injector.getInstance(net.datenwerke.security.service.security.rights.dtogen.Execute2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.security.service.security.rights.GrantAccess.class, injector.getInstance(net.datenwerke.security.service.security.rights.dtogen.GrantAccess2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.security.service.security.rights.Read.class, injector.getInstance(net.datenwerke.security.service.security.rights.dtogen.Read2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.security.service.security.rights.Write.class, injector.getInstance(net.datenwerke.security.service.security.rights.dtogen.Write2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.security.service.usermanager.entities.Group.class, injector.getInstance(net.datenwerke.security.service.usermanager.entities.dtogen.Group2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.security.service.usermanager.entities.OrganisationalUnit.class, injector.getInstance(net.datenwerke.security.service.usermanager.entities.dtogen.OrganisationalUnit2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.security.service.usermanager.entities.Sex.class, injector.getInstance(net.datenwerke.security.service.usermanager.entities.dtogen.Sex2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.security.service.usermanager.entities.User.class, injector.getInstance(net.datenwerke.security.service.usermanager.entities.dtogen.User2DtoGenerator.class));
		dtoGeneratorByPosoLookup.put(net.datenwerke.security.service.usermanager.entities.UserProperty.class, injector.getInstance(net.datenwerke.security.service.usermanager.entities.dtogen.UserProperty2DtoGenerator.class));


		dto2PosoGeneratorLookup = new HashMap<Class<?>, Dto2PosoGenerator>();

		dto2PosoGeneratorLookup.put(net.datenwerke.gf.client.history.dto.HistoryLinkDto.class, injector.getInstance(net.datenwerke.gf.service.history.dtogen.Dto2HistoryLinkGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.adminutils.client.logs.dto.ViewLogFileCommandResultExtensionDto.class, injector.getInstance(net.datenwerke.rs.adminutils.service.logs.terminal.commands.dtogen.Dto2ViewLogFileCommandResultExtensionGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.amazons3.client.amazons3.dto.AmazonS3DatasinkDto.class, injector.getInstance(net.datenwerke.rs.amazons3.service.amazons3.definitions.dtogen.Dto2AmazonS3DatasinkGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.FileSelectionParameterDefinitionDto.class, injector.getInstance(net.datenwerke.rs.base.ext.service.parameters.fileselection.dtogen.Dto2FileSelectionParameterDefinitionGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.FileSelectionParameterInstanceDto.class, injector.getInstance(net.datenwerke.rs.base.ext.service.parameters.fileselection.dtogen.Dto2FileSelectionParameterInstanceGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.SelectedParameterFileDto.class, injector.getInstance(net.datenwerke.rs.base.ext.service.parameters.fileselection.dtogen.Dto2SelectedParameterFileGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.UploadedParameterFileDto.class, injector.getInstance(net.datenwerke.rs.base.ext.service.parameters.fileselection.dtogen.Dto2UploadedParameterFileGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.datasources.dto.ArgumentDatasourceConnectorDto.class, injector.getInstance(net.datenwerke.rs.base.service.datasources.connectors.dtogen.Dto2ArgumentDatasourceConnectorGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorConfigDto.class, injector.getInstance(net.datenwerke.rs.base.service.datasources.connectors.dtogen.Dto2DatasourceConnectorConfigGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.datasources.dto.TextDatasourceConnectorDto.class, injector.getInstance(net.datenwerke.rs.base.service.datasources.connectors.dtogen.Dto2TextDatasourceConnectorGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.datasources.dto.UrlDatasourceConnectorDto.class, injector.getInstance(net.datenwerke.rs.base.service.datasources.connectors.dtogen.Dto2UrlDatasourceConnectorGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.datasources.dto.CsvDatasourceDto.class, injector.getInstance(net.datenwerke.rs.base.service.datasources.definitions.dtogen.Dto2CsvDatasourceGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.datasources.dto.CsvDatasourceConfigDto.class, injector.getInstance(net.datenwerke.rs.base.service.datasources.definitions.dtogen.Dto2CsvDatasourceConfigGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceDto.class, injector.getInstance(net.datenwerke.rs.base.service.datasources.definitions.dtogen.Dto2DatabaseDatasourceGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceConfigDto.class, injector.getInstance(net.datenwerke.rs.base.service.datasources.definitions.dtogen.Dto2DatabaseDatasourceConfigGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.parameters.blatext.dto.BlatextParameterDefinitionDto.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.blatext.dtogen.Dto2BlatextParameterDefinitionGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.parameters.blatext.dto.BlatextParameterInstanceDto.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.blatext.dtogen.Dto2BlatextParameterInstanceGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.parameters.datasource.dto.BoxLayoutModeDto.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.datasource.dtogen.Dto2BoxLayoutModeGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.parameters.datasource.dto.BoxLayoutPackModeDto.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.datasource.dtogen.Dto2BoxLayoutPackModeGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDataDto.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.datasource.dtogen.Dto2DatasourceParameterDataGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDefinitionDto.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.datasource.dtogen.Dto2DatasourceParameterDefinitionGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterInstanceDto.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.datasource.dtogen.Dto2DatasourceParameterInstanceGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.parameters.datasource.dto.ModeDto.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.datasource.dtogen.Dto2ModeGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.parameters.datasource.dto.MultiSelectionModeDto.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.datasource.dtogen.Dto2MultiSelectionModeGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.parameters.datasource.dto.SingleSelectionModeDto.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.datasource.dtogen.Dto2SingleSelectionModeGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.parameters.datetime.dto.DateTimeParameterDefinitionDto.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.datetime.dtogen.Dto2DateTimeParameterDefinitionGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.parameters.datetime.dto.DateTimeParameterInstanceDto.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.datetime.dtogen.Dto2DateTimeParameterInstanceGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.parameters.datetime.dto.ModeDto.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.datetime.dtogen.Dto2ModeGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.parameters.headline.dto.HeadlineParameterDefinitionDto.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.headline.dtogen.Dto2HeadlineParameterDefinitionGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.parameters.headline.dto.HeadlineParameterInstanceDto.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.headline.dtogen.Dto2HeadlineParameterInstanceGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.parameters.separator.dto.SeparatorParameterDefinitionDto.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.separator.dtogen.Dto2SeparatorParameterDefinitionGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.parameters.separator.dto.SeparatorParameterInstanceDto.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.separator.dtogen.Dto2SeparatorParameterInstanceGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.parameters.string.dto.TextParameterDefinitionDto.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.string.dtogen.Dto2TextParameterDefinitionGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.parameters.string.dto.TextParameterInstanceDto.class, injector.getInstance(net.datenwerke.rs.base.service.parameters.string.dtogen.Dto2TextParameterInstanceGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportDto.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.jasper.entities.dtogen.Dto2JasperReportGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportJRXMLFileDto.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.jasper.entities.dtogen.Dto2JasperReportJRXMLFileGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportVariantDto.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.jasper.entities.dtogen.Dto2JasperReportVariantGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.reportengines.jasper.dto.CompiledCSVJasperReportDto.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.jasper.output.object.dtogen.Dto2CompiledCSVJasperReportGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.reportengines.jasper.dto.CompiledHTMLJasperReportDto.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.jasper.output.object.dtogen.Dto2CompiledHTMLJasperReportGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.reportengines.jasper.dto.CompiledPNGJasperReportDto.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.jasper.output.object.dtogen.Dto2CompiledPNGJasperReportGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.jasperutils.dto.JasperParameterProposalDto.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.jasper.util.dtogen.Dto2JasperParameterProposalGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.reportengines.table.dto.AdditionalColumnSpecDto.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.dtogen.Dto2AdditionalColumnSpecGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.reportengines.table.dto.AggregateFunctionDto.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.dtogen.Dto2AggregateFunctionGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.dtogen.Dto2ColumnGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.reportengines.table.dto.ColumnReferenceDto.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.dtogen.Dto2ColumnReferenceGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.reportengines.table.dto.FilterRangeDto.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.dtogen.Dto2FilterRangeGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.reportengines.table.dto.NullHandlingDto.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.dtogen.Dto2NullHandlingGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.reportengines.table.dto.OrderDto.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.dtogen.Dto2OrderGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.dtogen.Dto2TableReportGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.reportengines.table.dto.TableReportVariantDto.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.dtogen.Dto2TableReportVariantGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.reportengines.table.dto.BinaryColumnFilterDto.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.filters.dtogen.Dto2BinaryColumnFilterGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.reportengines.table.dto.BinaryOperatorDto.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.filters.dtogen.Dto2BinaryOperatorGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.reportengines.table.dto.BlockTypeDto.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.filters.dtogen.Dto2BlockTypeGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFilterDto.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.filters.dtogen.Dto2ColumnFilterGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.reportengines.table.dto.FilterDto.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.filters.dtogen.Dto2FilterGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.reportengines.table.dto.FilterBlockDto.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.filters.dtogen.Dto2FilterBlockGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.reportengines.table.dto.PreFilterDto.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.filters.dtogen.Dto2PreFilterGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatCurrencyDto.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.format.dtogen.Dto2ColumnFormatCurrencyGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatDateDto.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.format.dtogen.Dto2ColumnFormatDateGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatNumberDto.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.format.dtogen.Dto2ColumnFormatNumberGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatTemplateDto.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.format.dtogen.Dto2ColumnFormatTemplateGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatTextDto.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.format.dtogen.Dto2ColumnFormatTextGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.reportengines.table.dto.CurrencyTypeDto.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.format.enums.dtogen.Dto2CurrencyTypeGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.base.client.reportengines.table.dto.NumberTypeDto.class, injector.getInstance(net.datenwerke.rs.base.service.reportengines.table.entities.format.enums.dtogen.Dto2NumberTypeGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceConfigDto.class, injector.getInstance(net.datenwerke.rs.birt.service.datasources.birtreport.entities.dtogen.Dto2BirtReportDatasourceConfigGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceDefinitionDto.class, injector.getInstance(net.datenwerke.rs.birt.service.datasources.birtreport.entities.dtogen.Dto2BirtReportDatasourceDefinitionGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceTargetTypeDto.class, injector.getInstance(net.datenwerke.rs.birt.service.datasources.birtreport.entities.dtogen.Dto2BirtReportDatasourceTargetTypeGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.birt.client.reportengines.dto.BirtReportDto.class, injector.getInstance(net.datenwerke.rs.birt.service.reportengine.entities.dtogen.Dto2BirtReportGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.birt.client.reportengines.dto.BirtReportFileDto.class, injector.getInstance(net.datenwerke.rs.birt.service.reportengine.entities.dtogen.Dto2BirtReportFileGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.birt.client.reportengines.dto.BirtReportVariantDto.class, injector.getInstance(net.datenwerke.rs.birt.service.reportengine.entities.dtogen.Dto2BirtReportVariantGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.birt.client.reportengines.dto.CompiledHTMLBirtReportDto.class, injector.getInstance(net.datenwerke.rs.birt.service.reportengine.output.object.dtogen.Dto2CompiledHTMLBirtReportGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.birt.client.reportengines.dto.CompiledPNGBirtReportDto.class, injector.getInstance(net.datenwerke.rs.birt.service.reportengine.output.object.dtogen.Dto2CompiledPNGBirtReportGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.birt.client.utils.dto.BirtParameterProposalDto.class, injector.getInstance(net.datenwerke.rs.birt.service.utils.dtogen.Dto2BirtParameterProposalGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.box.client.box.dto.BoxDatasinkDto.class, injector.getInstance(net.datenwerke.rs.box.service.box.definitions.dtogen.Dto2BoxDatasinkGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.computedcolumns.client.computedcolumns.dto.ComputedColumnDto.class, injector.getInstance(net.datenwerke.rs.computedcolumns.service.computedcolumns.entities.dtogen.Dto2ComputedColumnGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.condition.client.condition.dto.ReportConditionDto.class, injector.getInstance(net.datenwerke.rs.condition.service.condition.entity.dtogen.Dto2ReportConditionGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkContainerDto.class, injector.getInstance(net.datenwerke.rs.core.service.datasinkmanager.entities.dtogen.Dto2DatasinkContainerGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkFolderDto.class, injector.getInstance(net.datenwerke.rs.core.service.datasinkmanager.entities.dtogen.Dto2DatasinkFolderGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto.class, injector.getInstance(net.datenwerke.rs.core.service.datasourcemanager.entities.dtogen.Dto2DatasourceContainerGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto.class, injector.getInstance(net.datenwerke.rs.core.service.datasourcemanager.entities.dtogen.Dto2DatasourceDefinitionConfigGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceFolderDto.class, injector.getInstance(net.datenwerke.rs.core.service.datasourcemanager.entities.dtogen.Dto2DatasourceFolderGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.core.client.i18tools.dto.FormatPatternsDto.class, injector.getInstance(net.datenwerke.rs.core.service.i18ntools.dtogen.Dto2FormatPatternsGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.core.client.parameters.dto.DatatypeDto.class, injector.getInstance(net.datenwerke.rs.core.service.parameters.entities.dtogen.Dto2DatatypeGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.core.client.reportexporter.dto.RECCsvDto.class, injector.getInstance(net.datenwerke.rs.core.service.reportmanager.engine.config.dtogen.Dto2RECCsvGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.core.client.reportexporter.dto.RECJxlsDto.class, injector.getInstance(net.datenwerke.rs.core.service.reportmanager.engine.config.dtogen.Dto2RECJxlsGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.core.client.reportmanager.dto.ReportFolderDto.class, injector.getInstance(net.datenwerke.rs.core.service.reportmanager.entities.dtogen.Dto2ReportFolderGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportBytePropertyDto.class, injector.getInstance(net.datenwerke.rs.core.service.reportmanager.entities.reports.dtogen.Dto2ReportBytePropertyGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportMetadataDto.class, injector.getInstance(net.datenwerke.rs.core.service.reportmanager.entities.reports.dtogen.Dto2ReportMetadataGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportPropertyDto.class, injector.getInstance(net.datenwerke.rs.core.service.reportmanager.entities.reports.dtogen.Dto2ReportPropertyGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportServerSideStringPropertyDto.class, injector.getInstance(net.datenwerke.rs.core.service.reportmanager.entities.reports.dtogen.Dto2ReportServerSideStringPropertyGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportStringPropertyDto.class, injector.getInstance(net.datenwerke.rs.core.service.reportmanager.entities.reports.dtogen.Dto2ReportStringPropertyGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.crystal.client.crystal.dto.CrystalParameterProposalDto.class, injector.getInstance(net.datenwerke.rs.crystal.service.crystal.dtogen.Dto2CrystalParameterProposalGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportDto.class, injector.getInstance(net.datenwerke.rs.crystal.service.crystal.entities.dtogen.Dto2CrystalReportGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportFileDto.class, injector.getInstance(net.datenwerke.rs.crystal.service.crystal.entities.dtogen.Dto2CrystalReportFileGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportVariantDto.class, injector.getInstance(net.datenwerke.rs.crystal.service.crystal.entities.dtogen.Dto2CrystalReportVariantGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListDto.class, injector.getInstance(net.datenwerke.rs.dashboard.service.dashboard.dagets.dtogen.Dto2FavoriteListGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListDadgetDto.class, injector.getInstance(net.datenwerke.rs.dashboard.service.dashboard.dagets.dtogen.Dto2FavoriteListDadgetGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListEntryDto.class, injector.getInstance(net.datenwerke.rs.dashboard.service.dashboard.dagets.dtogen.Dto2FavoriteListEntryGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.dashboard.client.dashboard.dto.LibraryDadgetDto.class, injector.getInstance(net.datenwerke.rs.dashboard.service.dashboard.dagets.dtogen.Dto2LibraryDadgetGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.dashboard.client.dashboard.dto.ParameterDadgetDto.class, injector.getInstance(net.datenwerke.rs.dashboard.service.dashboard.dagets.dtogen.Dto2ParameterDadgetGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.dashboard.client.dashboard.dto.ReportDadgetDto.class, injector.getInstance(net.datenwerke.rs.dashboard.service.dashboard.dagets.dtogen.Dto2ReportDadgetGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.dashboard.client.dashboard.dto.StaticHtmlDadgetDto.class, injector.getInstance(net.datenwerke.rs.dashboard.service.dashboard.dagets.dtogen.Dto2StaticHtmlDadgetGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.dashboard.client.dashboard.dto.UrlDadgetDto.class, injector.getInstance(net.datenwerke.rs.dashboard.service.dashboard.dagets.dtogen.Dto2UrlDadgetGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetContainerDto.class, injector.getInstance(net.datenwerke.rs.dashboard.service.dashboard.entities.dtogen.Dto2DadgetContainerGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetNodeDto.class, injector.getInstance(net.datenwerke.rs.dashboard.service.dashboard.entities.dtogen.Dto2DadgetNodeGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardDto.class, injector.getInstance(net.datenwerke.rs.dashboard.service.dashboard.entities.dtogen.Dto2DashboardGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardContainerDto.class, injector.getInstance(net.datenwerke.rs.dashboard.service.dashboard.entities.dtogen.Dto2DashboardContainerGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardFolderDto.class, injector.getInstance(net.datenwerke.rs.dashboard.service.dashboard.entities.dtogen.Dto2DashboardFolderGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardNodeDto.class, injector.getInstance(net.datenwerke.rs.dashboard.service.dashboard.entities.dtogen.Dto2DashboardNodeGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardReferenceDto.class, injector.getInstance(net.datenwerke.rs.dashboard.service.dashboard.entities.dtogen.Dto2DashboardReferenceGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.dashboard.client.dashboard.dto.LayoutTypeDto.class, injector.getInstance(net.datenwerke.rs.dashboard.service.dashboard.entities.dtogen.Dto2LayoutTypeGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.dropbox.client.dropbox.dto.DropboxDatasinkDto.class, injector.getInstance(net.datenwerke.rs.dropbox.service.dropbox.definitions.dtogen.Dto2DropboxDatasinkGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleDto.class, injector.getInstance(net.datenwerke.rs.dsbundle.service.dsbundle.entities.dtogen.Dto2DatabaseBundleGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleEntryDto.class, injector.getInstance(net.datenwerke.rs.dsbundle.service.dsbundle.entities.dtogen.Dto2DatabaseBundleEntryGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.EmailDatasinkDto.class, injector.getInstance(net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.dtogen.Dto2EmailDatasinkGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto.class, injector.getInstance(net.datenwerke.rs.fileserver.service.fileserver.entities.dtogen.Dto2FileServerFileGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto.class, injector.getInstance(net.datenwerke.rs.fileserver.service.fileserver.entities.dtogen.Dto2FileServerFolderGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.fileserver.client.fileserver.dto.EditCommandResultExtensionDto.class, injector.getInstance(net.datenwerke.rs.fileserver.service.fileserver.terminal.commands.dtogen.Dto2EditCommandResultExtensionGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.ftp.client.ftp.dto.FtpDatasinkDto.class, injector.getInstance(net.datenwerke.rs.ftp.service.ftp.definitions.dtogen.Dto2FtpDatasinkGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.ftp.client.ftp.dto.FtpsDatasinkDto.class, injector.getInstance(net.datenwerke.rs.ftp.service.ftp.definitions.dtogen.Dto2FtpsDatasinkGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.ftp.client.ftp.dto.SftpDatasinkDto.class, injector.getInstance(net.datenwerke.rs.ftp.service.ftp.definitions.dtogen.Dto2SftpDatasinkGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.globalconstants.client.globalconstants.dto.GlobalConstantDto.class, injector.getInstance(net.datenwerke.rs.globalconstants.service.globalconstants.entities.dtogen.Dto2GlobalConstantGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.googledrive.client.googledrive.dto.GoogleDriveDatasinkDto.class, injector.getInstance(net.datenwerke.rs.googledrive.service.googledrive.definitions.dtogen.Dto2GoogleDriveDatasinkGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorReportDto.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.entities.dtogen.Dto2GridEditorReportGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorReportVariantDto.class, injector.getInstance(net.datenwerke.rs.grideditor.service.grideditor.entities.dtogen.Dto2GridEditorReportVariantGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.incubator.client.jaspertotable.dto.JasperToTableConfigDto.class, injector.getInstance(net.datenwerke.rs.incubator.service.jaspertotable.entities.dtogen.Dto2JasperToTableConfigGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.incubator.client.scriptdatasource.dto.ScriptDatasourceDto.class, injector.getInstance(net.datenwerke.rs.incubator.service.scriptdatasource.entities.dtogen.Dto2ScriptDatasourceGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.incubator.client.scriptdatasource.dto.ScriptDatasourceConfigDto.class, injector.getInstance(net.datenwerke.rs.incubator.service.scriptdatasource.entities.dtogen.Dto2ScriptDatasourceConfigGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportDto.class, injector.getInstance(net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.dtogen.Dto2JxlsReportGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportFileDto.class, injector.getInstance(net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.dtogen.Dto2JxlsReportFileGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportVariantDto.class, injector.getInstance(net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.dtogen.Dto2JxlsReportVariantGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto.LocalFileSystemDatasinkDto.class, injector.getInstance(net.datenwerke.rs.localfsdatasink.service.localfsdatasink.definitions.dtogen.Dto2LocalFileSystemDatasinkGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.onedrive.client.onedrive.dto.OneDriveDatasinkDto.class, injector.getInstance(net.datenwerke.rs.onedrive.service.onedrive.definitions.dtogen.Dto2OneDriveDatasinkGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.reportdoc.client.dto.DeployAnalyzeCommandResultExtensionDto.class, injector.getInstance(net.datenwerke.rs.reportdoc.service.terminal.commands.dtogen.Dto2DeployAnalyzeCommandResultExtensionGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.reportdoc.client.dto.VariantTestCommandResultExtensionDto.class, injector.getInstance(net.datenwerke.rs.reportdoc.service.terminal.commands.dtogen.Dto2VariantTestCommandResultExtensionGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceDto.class, injector.getInstance(net.datenwerke.rs.saiku.service.datasource.dtogen.Dto2MondrianDatasourceGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceConfigDto.class, injector.getInstance(net.datenwerke.rs.saiku.service.datasource.dtogen.Dto2MondrianDatasourceConfigGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportDto.class, injector.getInstance(net.datenwerke.rs.saiku.service.saiku.entities.dtogen.Dto2SaikuReportGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportVariantDto.class, injector.getInstance(net.datenwerke.rs.saiku.service.saiku.entities.dtogen.Dto2SaikuReportVariantGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.saiku.client.saiku.dto.RECSaikuChartDto.class, injector.getInstance(net.datenwerke.rs.saiku.service.saiku.reportengine.config.dtogen.Dto2RECSaikuChartGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.samba.client.samba.dto.SambaDatasinkDto.class, injector.getInstance(net.datenwerke.rs.samba.service.samba.definitions.dtogen.Dto2SambaDatasinkGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto.ExecutedReportFileReferenceDto.class, injector.getInstance(net.datenwerke.rs.scheduleasfile.service.scheduleasfile.entities.dtogen.Dto2ExecutedReportFileReferenceGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.scheduleasfile.client.scheduleasfile.filter.dto.TeamSpaceReportJobFilterDto.class, injector.getInstance(net.datenwerke.rs.scheduleasfile.service.scheduleasfile.filter.dtogen.Dto2TeamSpaceReportJobFilterGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.scheduler.client.scheduler.dto.ReportServerJobFilterDto.class, injector.getInstance(net.datenwerke.rs.scheduler.service.scheduler.jobs.filter.dtogen.Dto2ReportServerJobFilterGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.scp.client.scp.dto.ScpDatasinkDto.class, injector.getInstance(net.datenwerke.rs.scp.service.scp.definitions.dtogen.Dto2ScpDatasinkGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.scripting.client.scripting.dto.AddMenuEntryExtensionDto.class, injector.getInstance(net.datenwerke.rs.scripting.service.scripting.extensions.dtogen.Dto2AddMenuEntryExtensionGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.scripting.client.scripting.dto.AddMenuSeparatorEntryExtensionDto.class, injector.getInstance(net.datenwerke.rs.scripting.service.scripting.extensions.dtogen.Dto2AddMenuSeparatorEntryExtensionGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.scripting.client.scripting.dto.AddReportExportFormatProviderDto.class, injector.getInstance(net.datenwerke.rs.scripting.service.scripting.extensions.dtogen.Dto2AddReportExportFormatProviderGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.scripting.client.scripting.dto.AddStatusbBarLabelExtensionDto.class, injector.getInstance(net.datenwerke.rs.scripting.service.scripting.extensions.dtogen.Dto2AddStatusbBarLabelExtensionGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.scripting.client.scripting.dto.AddToolbarEntryExtensionDto.class, injector.getInstance(net.datenwerke.rs.scripting.service.scripting.extensions.dtogen.Dto2AddToolbarEntryExtensionGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.scripting.client.scripting.dto.DisplayConditionDto.class, injector.getInstance(net.datenwerke.rs.scripting.service.scripting.extensions.dtogen.Dto2DisplayConditionGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.scripting.client.scripting.dto.DisplayConditionTypeDto.class, injector.getInstance(net.datenwerke.rs.scripting.service.scripting.extensions.dtogen.Dto2DisplayConditionTypeGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.scriptreport.client.scriptreport.dto.ScriptReportDto.class, injector.getInstance(net.datenwerke.rs.scriptreport.service.scriptreport.entities.dtogen.Dto2ScriptReportGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.scriptreport.client.scriptreport.dto.ScriptReportVariantDto.class, injector.getInstance(net.datenwerke.rs.scriptreport.service.scriptreport.entities.dtogen.Dto2ScriptReportVariantGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.ScriptParameterDefinitionDto.class, injector.getInstance(net.datenwerke.rs.scriptreport.service.scriptreport.parameter.dtogen.Dto2ScriptParameterDefinitionGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.ScriptParameterInstanceDto.class, injector.getInstance(net.datenwerke.rs.scriptreport.service.scriptreport.parameter.dtogen.Dto2ScriptParameterInstanceGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.search.client.search.dto.SearchFilterDto.class, injector.getInstance(net.datenwerke.rs.search.service.search.results.dtogen.Dto2SearchFilterGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.search.client.search.dto.SearchResultEntryDto.class, injector.getInstance(net.datenwerke.rs.search.service.search.results.dtogen.Dto2SearchResultEntryGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.search.client.search.dto.SearchResultListDto.class, injector.getInstance(net.datenwerke.rs.search.service.search.results.dtogen.Dto2SearchResultListGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.search.client.search.dto.SearchResultTagDto.class, injector.getInstance(net.datenwerke.rs.search.service.search.results.dtogen.Dto2SearchResultTagGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.search.client.search.dto.SearchResultTagTypeDto.class, injector.getInstance(net.datenwerke.rs.search.service.search.results.dtogen.Dto2SearchResultTagTypeGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.RECTableTemplateDto.class, injector.getInstance(net.datenwerke.rs.tabletemplate.service.tabletemplate.config.dtogen.Dto2RECTableTemplateGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportByteTemplateDto.class, injector.getInstance(net.datenwerke.rs.tabletemplate.service.tabletemplate.entities.dtogen.Dto2TableReportByteTemplateGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportStringTemplateDto.class, injector.getInstance(net.datenwerke.rs.tabletemplate.service.tabletemplate.entities.dtogen.Dto2TableReportStringTemplateGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportTemplateListDto.class, injector.getInstance(net.datenwerke.rs.tabletemplate.service.tabletemplate.entities.dtogen.Dto2TableReportTemplateListGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.teamspace.client.teamspace.dto.AppPropertyDto.class, injector.getInstance(net.datenwerke.rs.teamspace.service.teamspace.entities.dtogen.Dto2AppPropertyGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto.class, injector.getInstance(net.datenwerke.rs.teamspace.service.teamspace.entities.dtogen.Dto2TeamSpaceGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceAppDto.class, injector.getInstance(net.datenwerke.rs.teamspace.service.teamspace.entities.dtogen.Dto2TeamSpaceAppGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceMemberDto.class, injector.getInstance(net.datenwerke.rs.teamspace.service.teamspace.entities.dtogen.Dto2TeamSpaceMemberGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceRoleDto.class, injector.getInstance(net.datenwerke.rs.teamspace.service.teamspace.entities.dtogen.Dto2TeamSpaceRoleGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.teamspace.client.teamspace.security.TeamSpaceSecureeDto.class, injector.getInstance(net.datenwerke.rs.teamspace.service.teamspace.security.dtogen.Dto2TeamSpaceSecureeGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.teamspace.client.teamspace.security.rights.TeamSpaceAdministratorDto.class, injector.getInstance(net.datenwerke.rs.teamspace.service.teamspace.security.rights.dtogen.Dto2TeamSpaceAdministratorGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.terminal.client.terminal.dto.AutocompleteResultDto.class, injector.getInstance(net.datenwerke.rs.terminal.service.terminal.obj.dtogen.Dto2AutocompleteResultGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultDto.class, injector.getInstance(net.datenwerke.rs.terminal.service.terminal.obj.dtogen.Dto2CommandResultGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultAnchorDto.class, injector.getInstance(net.datenwerke.rs.terminal.service.terminal.obj.dtogen.Dto2CommandResultAnchorGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultHtmlDto.class, injector.getInstance(net.datenwerke.rs.terminal.service.terminal.obj.dtogen.Dto2CommandResultHtmlGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultHyperlinkDto.class, injector.getInstance(net.datenwerke.rs.terminal.service.terminal.obj.dtogen.Dto2CommandResultHyperlinkGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultLineDto.class, injector.getInstance(net.datenwerke.rs.terminal.service.terminal.obj.dtogen.Dto2CommandResultLineGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultListDto.class, injector.getInstance(net.datenwerke.rs.terminal.service.terminal.obj.dtogen.Dto2CommandResultListGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultTableDto.class, injector.getInstance(net.datenwerke.rs.terminal.service.terminal.obj.dtogen.Dto2CommandResultTableGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.terminal.client.terminal.dto.CreJavaScriptDto.class, injector.getInstance(net.datenwerke.rs.terminal.service.terminal.obj.dtogen.Dto2CreJavaScriptGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.terminal.client.terminal.dto.CreMessageDto.class, injector.getInstance(net.datenwerke.rs.terminal.service.terminal.obj.dtogen.Dto2CreMessageGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.terminal.client.terminal.dto.CreOverlayDto.class, injector.getInstance(net.datenwerke.rs.terminal.service.terminal.obj.dtogen.Dto2CreOverlayGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.terminal.client.terminal.dto.DisplayModeDto.class, injector.getInstance(net.datenwerke.rs.terminal.service.terminal.obj.dtogen.Dto2DisplayModeGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.terminal.client.terminal.dto.InteractiveResultModifierDto.class, injector.getInstance(net.datenwerke.rs.terminal.service.terminal.obj.dtogen.Dto2InteractiveResultModifierGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.terminal.client.terminal.dto.PressKeyResultModifierDto.class, injector.getInstance(net.datenwerke.rs.terminal.service.terminal.obj.dtogen.Dto2PressKeyResultModifierGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFolderDto.class, injector.getInstance(net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.dtogen.Dto2TsDiskFolderGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto.class, injector.getInstance(net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.dtogen.Dto2TsDiskReportReferenceGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskRootDto.class, injector.getInstance(net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.dtogen.Dto2TsDiskRootGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.uservariables.client.parameters.dto.UserVariableParameterDefinitionDto.class, injector.getInstance(net.datenwerke.rs.uservariables.service.parameters.dtogen.Dto2UserVariableParameterDefinitionGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.uservariables.client.parameters.dto.UserVariableParameterInstanceDto.class, injector.getInstance(net.datenwerke.rs.uservariables.service.parameters.dtogen.Dto2UserVariableParameterInstanceGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.uservariables.client.variabletypes.list.dto.ListUserVariableDefinitionDto.class, injector.getInstance(net.datenwerke.rs.uservariables.service.variabletypes.list.dtogen.Dto2ListUserVariableDefinitionGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.uservariables.client.variabletypes.list.dto.ListUserVariableInstanceDto.class, injector.getInstance(net.datenwerke.rs.uservariables.service.variabletypes.list.dtogen.Dto2ListUserVariableInstanceGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.uservariables.client.variabletypes.string.dto.StringUserVariableDefinitionDto.class, injector.getInstance(net.datenwerke.rs.uservariables.service.variabletypes.string.dtogen.Dto2StringUserVariableDefinitionGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.rs.uservariables.client.variabletypes.string.dto.StringUserVariableInstanceDto.class, injector.getInstance(net.datenwerke.rs.uservariables.service.variabletypes.string.dtogen.Dto2StringUserVariableInstanceGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.scheduler.client.scheduler.dto.JobExecutionStatusDto.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.entities.dtogen.Dto2JobExecutionStatusGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.scheduler.client.scheduler.dto.MisfireInstructionDto.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.entities.dtogen.Dto2MisfireInstructionGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.scheduler.client.scheduler.dto.OutcomeDto.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.entities.dtogen.Dto2OutcomeGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.scheduler.client.scheduler.dto.history.ActionEntryDto.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.entities.history.dtogen.Dto2ActionEntryGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.scheduler.client.scheduler.dto.history.ExecutionLogEntryDto.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.entities.history.dtogen.Dto2ExecutionLogEntryGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.scheduler.client.scheduler.dto.history.HistoryEntryPropertyDto.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.entities.history.dtogen.Dto2HistoryEntryPropertyGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.scheduler.client.scheduler.dto.history.JobEntryDto.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.entities.history.dtogen.Dto2JobEntryGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.scheduler.client.scheduler.dto.history.JobHistoryDto.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.entities.history.dtogen.Dto2JobHistoryGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.scheduler.client.scheduler.dto.config.complex.RetryTimeUnitDto.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.helper.dtogen.Dto2RetryTimeUnitGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.scheduler.client.scheduler.dto.VetoActionExecutionModeDto.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.helper.dtogen.Dto2VetoActionExecutionModeGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.scheduler.client.scheduler.dto.VetoJobExecutionModeDto.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.helper.dtogen.Dto2VetoJobExecutionModeGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterConfigurationDto.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.stores.jpa.filter.dtogen.Dto2JobFilterConfigurationGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.scheduler.client.scheduler.dto.filter.OrderDto.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.stores.jpa.filter.dtogen.Dto2OrderGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.scheduler.client.scheduler.dto.config.complex.DailyConfigDto.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.dtogen.Dto2DailyConfigGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.scheduler.client.scheduler.dto.config.complex.DateTriggerConfigDto.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.dtogen.Dto2DateTriggerConfigGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthlyNthDayConfigDto.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.dtogen.Dto2MonthlyNthDayConfigGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthlyNthDayOfWeekConfigDto.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.dtogen.Dto2MonthlyNthDayOfWeekConfigGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.scheduler.client.scheduler.dto.config.complex.TimeDto.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.dtogen.Dto2TimeGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.scheduler.client.scheduler.dto.config.complex.WeeklyConfigDto.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.dtogen.Dto2WeeklyConfigGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.scheduler.client.scheduler.dto.config.complex.YearlyAtDateConfigDto.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.dtogen.Dto2YearlyAtDateConfigGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.scheduler.client.scheduler.dto.config.complex.YearlyNthDayOfWeekConfigDto.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.dtogen.Dto2YearlyNthDayOfWeekConfigGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.scheduler.client.scheduler.dto.config.complex.DailyPatternDto.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.dtogen.Dto2DailyPatternGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.scheduler.client.scheduler.dto.config.complex.DailyRepeatTypeDto.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.dtogen.Dto2DailyRepeatTypeGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.scheduler.client.scheduler.dto.config.complex.DaysDto.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.dtogen.Dto2DaysGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.scheduler.client.scheduler.dto.config.complex.EndTypesDto.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.dtogen.Dto2EndTypesGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthsDto.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.dtogen.Dto2MonthsGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.scheduler.client.scheduler.dto.config.complex.NthDto.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.dtogen.Dto2NthGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.scheduler.client.scheduler.dto.config.complex.TimeUnitsDto.class, injector.getInstance(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.dtogen.Dto2TimeUnitsGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.security.client.security.dto.SecurityServiceSecureeDto.class, injector.getInstance(net.datenwerke.security.service.security.dtogen.Dto2SecurityServiceSecureeGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.security.client.security.dto.AccessTypeDto.class, injector.getInstance(net.datenwerke.security.service.security.entities.dtogen.Dto2AccessTypeGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.security.client.security.dto.AceDto.class, injector.getInstance(net.datenwerke.security.service.security.entities.dtogen.Dto2AceGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.security.client.security.dto.AceAccessMapDto.class, injector.getInstance(net.datenwerke.security.service.security.entities.dtogen.Dto2AceAccessMapGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.security.client.security.dto.HierarchicalAceDto.class, injector.getInstance(net.datenwerke.security.service.security.entities.dtogen.Dto2HierarchicalAceGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.security.client.security.dto.InheritanceTypeDto.class, injector.getInstance(net.datenwerke.security.service.security.entities.dtogen.Dto2InheritanceTypeGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.security.client.security.dto.DeleteDto.class, injector.getInstance(net.datenwerke.security.service.security.rights.dtogen.Dto2DeleteGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.security.client.security.dto.ExecuteDto.class, injector.getInstance(net.datenwerke.security.service.security.rights.dtogen.Dto2ExecuteGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.security.client.security.dto.GrantAccessDto.class, injector.getInstance(net.datenwerke.security.service.security.rights.dtogen.Dto2GrantAccessGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.security.client.security.dto.ReadDto.class, injector.getInstance(net.datenwerke.security.service.security.rights.dtogen.Dto2ReadGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.security.client.security.dto.WriteDto.class, injector.getInstance(net.datenwerke.security.service.security.rights.dtogen.Dto2WriteGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.security.client.usermanager.dto.GroupDto.class, injector.getInstance(net.datenwerke.security.service.usermanager.entities.dtogen.Dto2GroupGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto.class, injector.getInstance(net.datenwerke.security.service.usermanager.entities.dtogen.Dto2OrganisationalUnitGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.security.client.usermanager.dto.SexDto.class, injector.getInstance(net.datenwerke.security.service.usermanager.entities.dtogen.Dto2SexGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.security.client.usermanager.dto.UserDto.class, injector.getInstance(net.datenwerke.security.service.usermanager.entities.dtogen.Dto2UserGenerator.class));
		dto2PosoGeneratorLookup.put(net.datenwerke.security.client.usermanager.dto.UserPropertyDto.class, injector.getInstance(net.datenwerke.security.service.usermanager.entities.dtogen.Dto2UserPropertyGenerator.class));
		//Dto lookup


		dtoClassLookup = new HashSet<Class<?>>();

		dtoClassLookup.add(net.datenwerke.gf.client.history.dto.HistoryLinkDto.class);
		dtoClassLookup.add(net.datenwerke.gf.client.juel.dto.JuelResultDto.class);
		dtoClassLookup.add(net.datenwerke.gf.client.juel.dto.decorator.JuelResultDtoDec.class);
		dtoClassLookup.add(net.datenwerke.gf.client.juel.dto.JuelResultTypeDto.class);
		dtoClassLookup.add(net.datenwerke.rs.adminutils.client.logs.dto.ViewLogFileCommandResultExtensionDto.class);
		dtoClassLookup.add(net.datenwerke.rs.amazons3.client.amazons3.dto.AmazonS3DatasinkDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.FileSelectionParameterDefinitionDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.FileSelectionParameterInstanceDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.decorator.FileSelectionParameterInstanceDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.SelectedParameterFileDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.UploadedParameterFileDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.datasources.dto.ArgumentDatasourceConnectorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.datasources.dto.decorator.ArgumentDatasourceConnectorDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.datasources.dto.decorator.DatasourceConnectorDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorConfigDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.datasources.dto.TextDatasourceConnectorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.datasources.dto.UrlDatasourceConnectorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.datasources.dto.CsvDatasourceDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.datasources.dto.CsvDatasourceConfigDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceConfigDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.datasources.dto.FormatBasedDatasourceConfigDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.datasources.dto.FormatBasedDatasourceDefinitionDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.dbhelper.dto.DatabaseHelperDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.parameters.blatext.dto.BlatextParameterDefinitionDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.parameters.blatext.dto.BlatextParameterInstanceDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.parameters.datasource.dto.BoxLayoutModeDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.parameters.datasource.dto.BoxLayoutPackModeDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDataDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.parameters.datasource.dto.decorator.DatasourceParameterDataDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDefinitionDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterInstanceDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.parameters.datasource.dto.ModeDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.parameters.datasource.dto.MultiSelectionModeDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.parameters.datasource.dto.SingleSelectionModeDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.parameters.datetime.dto.DateTimeParameterDefinitionDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.parameters.datetime.dto.decorator.DateTimeParameterDefinitionDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.parameters.datetime.dto.DateTimeParameterInstanceDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.parameters.datetime.dto.decorator.DateTimeParameterInstanceDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.parameters.datetime.dto.ModeDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.parameters.headline.dto.HeadlineParameterDefinitionDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.parameters.headline.dto.HeadlineParameterInstanceDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.parameters.separator.dto.SeparatorParameterDefinitionDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.parameters.separator.dto.SeparatorParameterInstanceDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.parameters.string.dto.TextParameterDefinitionDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.parameters.string.dto.TextParameterInstanceDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.jasper.dto.decorator.JasperReportDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportJRXMLFileDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportVariantDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.jasper.dto.decorator.JasperReportVariantDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.jasper.dto.CompiledCSVJasperReportDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.jasper.dto.CompiledHTMLJasperReportDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.jasper.dto.CompiledPNGJasperReportDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.jasperutils.dto.JasperParameterProposalDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.AdditionalColumnSpecDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.AdditionalColumnSpecDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.AggregateFunctionDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.ColumnReferenceDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnReferenceDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.FilterRangeDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterRangeDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.NullHandlingDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.OrderDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.TableReportDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.TableReportVariantDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.TableReportVariantDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.BinaryColumnFilterDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.BinaryColumnFilterDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.BinaryOperatorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.BlockTypeDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFilterDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFilterDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.FilterDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.FilterBlockDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterBlockDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.FilterSpecDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterSpecDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.PreFilterDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.PreFilterDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatCurrencyDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatCurrencyDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatDateDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatDateDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatNumberDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatNumberDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatTemplateDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatTemplateDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatTextDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatTextDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.CurrencyTypeDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.NumberTypeDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.CompiledHTMLTableReportDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.RSStringTableRowDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.RSTableModelDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.RSTableRowDto.class);
		dtoClassLookup.add(net.datenwerke.rs.base.client.reportengines.table.dto.TableDefinitionDto.class);
		dtoClassLookup.add(net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceConfigDto.class);
		dtoClassLookup.add(net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceDefinitionDto.class);
		dtoClassLookup.add(net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceTargetTypeDto.class);
		dtoClassLookup.add(net.datenwerke.rs.birt.client.reportengines.dto.BirtReportDto.class);
		dtoClassLookup.add(net.datenwerke.rs.birt.client.reportengines.dto.decorator.BirtReportDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.birt.client.reportengines.dto.BirtReportFileDto.class);
		dtoClassLookup.add(net.datenwerke.rs.birt.client.reportengines.dto.BirtReportVariantDto.class);
		dtoClassLookup.add(net.datenwerke.rs.birt.client.reportengines.dto.decorator.BirtReportVariantDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.birt.client.reportengines.dto.CompiledHTMLBirtReportDto.class);
		dtoClassLookup.add(net.datenwerke.rs.birt.client.reportengines.dto.CompiledPNGBirtReportDto.class);
		dtoClassLookup.add(net.datenwerke.rs.birt.client.utils.dto.BirtParameterProposalDto.class);
		dtoClassLookup.add(net.datenwerke.rs.box.client.box.dto.BoxDatasinkDto.class);
		dtoClassLookup.add(net.datenwerke.rs.computedcolumns.client.computedcolumns.dto.ComputedColumnDto.class);
		dtoClassLookup.add(net.datenwerke.rs.condition.client.condition.dto.ReportConditionDto.class);
		dtoClassLookup.add(net.datenwerke.rs.condition.client.condition.dto.decorator.ReportConditionDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.core.client.datasinkmanager.dto.AbstractDatasinkManagerNodeDto.class);
		dtoClassLookup.add(net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkContainerDto.class);
		dtoClassLookup.add(net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkContainerProviderDto.class);
		dtoClassLookup.add(net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto.class);
		dtoClassLookup.add(net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkFolderDto.class);
		dtoClassLookup.add(net.datenwerke.rs.core.client.datasourcemanager.dto.AbstractDatasourceManagerNodeDto.class);
		dtoClassLookup.add(net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto.class);
		dtoClassLookup.add(net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerProviderDto.class);
		dtoClassLookup.add(net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto.class);
		dtoClassLookup.add(net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto.class);
		dtoClassLookup.add(net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceFolderDto.class);
		dtoClassLookup.add(net.datenwerke.rs.core.client.i18tools.dto.FormatPatternsDto.class);
		dtoClassLookup.add(net.datenwerke.rs.core.client.parameters.dto.DatatypeDto.class);
		dtoClassLookup.add(net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto.class);
		dtoClassLookup.add(net.datenwerke.rs.core.client.parameters.dto.decorator.ParameterDefinitionDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto.class);
		dtoClassLookup.add(net.datenwerke.rs.core.client.reportexporter.dto.RECCsvDto.class);
		dtoClassLookup.add(net.datenwerke.rs.core.client.reportexporter.dto.decorator.RECCsvDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.core.client.reportexporter.dto.RECJxlsDto.class);
		dtoClassLookup.add(net.datenwerke.rs.core.client.reportexporter.dto.decorator.RECJxlsDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto.class);
		dtoClassLookup.add(net.datenwerke.rs.core.client.reportmanager.dto.reports.AbstractReportManagerNodeDto.class);
		dtoClassLookup.add(net.datenwerke.rs.core.client.reportmanager.dto.ReportFolderDto.class);
		dtoClassLookup.add(net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto.class);
		dtoClassLookup.add(net.datenwerke.rs.core.client.reportmanager.dto.reports.decorator.ReportDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportBytePropertyDto.class);
		dtoClassLookup.add(net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportMetadataDto.class);
		dtoClassLookup.add(net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportPropertyDto.class);
		dtoClassLookup.add(net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportServerSideStringPropertyDto.class);
		dtoClassLookup.add(net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportStringPropertyDto.class);
		dtoClassLookup.add(net.datenwerke.rs.core.client.reportmanager.dto.reports.ServerSidePropertyDto.class);
		dtoClassLookup.add(net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportVariantDto.class);
		dtoClassLookup.add(net.datenwerke.rs.crystal.client.crystal.dto.CrystalParameterProposalDto.class);
		dtoClassLookup.add(net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportDto.class);
		dtoClassLookup.add(net.datenwerke.rs.crystal.client.crystal.dto.decorator.CrystalReportDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportFileDto.class);
		dtoClassLookup.add(net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportVariantDto.class);
		dtoClassLookup.add(net.datenwerke.rs.crystal.client.crystal.dto.decorator.CrystalReportVariantDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListDto.class);
		dtoClassLookup.add(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.FavoriteListDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListDadgetDto.class);
		dtoClassLookup.add(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.FavoriteListDadgetDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListEntryDto.class);
		dtoClassLookup.add(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.FavoriteListEntryDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.dashboard.client.dashboard.dto.LibraryDadgetDto.class);
		dtoClassLookup.add(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.LibraryDadgetDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.dashboard.client.dashboard.dto.ParameterDadgetDto.class);
		dtoClassLookup.add(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.ParameterDadgetDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.dashboard.client.dashboard.dto.ReportDadgetDto.class);
		dtoClassLookup.add(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.ReportDadgetDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.dashboard.client.dashboard.dto.StaticHtmlDadgetDto.class);
		dtoClassLookup.add(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.StaticHtmlDadgetDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.dashboard.client.dashboard.dto.UrlDadgetDto.class);
		dtoClassLookup.add(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.UrlDadgetDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.dashboard.client.dashboard.dto.AbstractDashboardManagerNodeDto.class);
		dtoClassLookup.add(net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto.class);
		dtoClassLookup.add(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DadgetDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetContainerDto.class);
		dtoClassLookup.add(net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetNodeDto.class);
		dtoClassLookup.add(net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardDto.class);
		dtoClassLookup.add(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DashboardDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardContainerDto.class);
		dtoClassLookup.add(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DashboardContainerDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardFolderDto.class);
		dtoClassLookup.add(net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardNodeDto.class);
		dtoClassLookup.add(net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardReferenceDto.class);
		dtoClassLookup.add(net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DashboardReferenceDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.dashboard.client.dashboard.dto.LayoutTypeDto.class);
		dtoClassLookup.add(net.datenwerke.rs.dropbox.client.dropbox.dto.DropboxDatasinkDto.class);
		dtoClassLookup.add(net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleDto.class);
		dtoClassLookup.add(net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleEntryDto.class);
		dtoClassLookup.add(net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.EmailDatasinkDto.class);
		dtoClassLookup.add(net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto.class);
		dtoClassLookup.add(net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto.class);
		dtoClassLookup.add(net.datenwerke.rs.fileserver.client.fileserver.dto.decorator.FileServerFileDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto.class);
		dtoClassLookup.add(net.datenwerke.rs.fileserver.client.fileserver.dto.EditCommandResultExtensionDto.class);
		dtoClassLookup.add(net.datenwerke.rs.ftp.client.ftp.dto.FtpDatasinkDto.class);
		dtoClassLookup.add(net.datenwerke.rs.ftp.client.ftp.dto.FtpsDatasinkDto.class);
		dtoClassLookup.add(net.datenwerke.rs.ftp.client.ftp.dto.SftpDatasinkDto.class);
		dtoClassLookup.add(net.datenwerke.rs.globalconstants.client.globalconstants.dto.GlobalConstantDto.class);
		dtoClassLookup.add(net.datenwerke.rs.googledrive.client.googledrive.dto.GoogleDriveDatasinkDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorColumnConfigDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorColumnConfigDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorConfigDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorDataDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorDataDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorRecordDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordEntryDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorRecordEntryDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.DateSelectionListEditorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.DateSelectionListEditorDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.DecimalSelectionListEditorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.DecimalSelectionListEditorDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.DoubleSelectionListEditorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.DoubleSelectionListEditorDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.EditorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.EditorDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.FloatSelectionListEditorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.FloatSelectionListEditorDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.IntBooleanEditorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.IntBooleanEditorDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.IntSelectionListEditorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.IntSelectionListEditorDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.LongSelectionListEditorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.LongSelectionListEditorDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.SelectionListEditorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.TextAreaEditorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.TextAreaEditorDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.TextBooleanEditorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.TextBooleanEditorDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.TextDateEditorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.TextDateEditorDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.TextSelectionListEditorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.TextSelectionListEditorDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.CustomValidatorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.CustomValidatorDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.EmptyValidatorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.EmptyValidatorDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.FixedLengthValidatorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.FixedLengthValidatorDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.MaxBigDecimalValidatorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxBigDecimalValidatorDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.MaxDateValidatorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxDateValidatorDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.MaxDoubleValidatorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxDoubleValidatorDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.MaxFloatValidatorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxFloatValidatorDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.MaxIntegerValidatorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxIntegerValidatorDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.MaxLengthValidatorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxLengthValidatorDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.MaxLongValidatorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxLongValidatorDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.MinBigDecimalValidatorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinBigDecimalValidatorDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.MinDateValidatorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinDateValidatorDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.MinDoubleValidatorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinDoubleValidatorDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.MinFloatValidatorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinFloatValidatorDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.MinIntegerValidatorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinIntegerValidatorDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.MinLengthValidatorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinLengthValidatorDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.MinLongValidatorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinLongValidatorDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.RegExValidatorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.RegExValidatorDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.ValidatorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.ValidatorDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorReportDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorReportDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorReportVariantDto.class);
		dtoClassLookup.add(net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorReportVariantDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.incubator.client.jaspertotable.dto.JasperToTableConfigDto.class);
		dtoClassLookup.add(net.datenwerke.rs.incubator.client.jaspertotable.dto.decorator.JasperToTableConfigDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.incubator.client.scriptdatasource.dto.ScriptDatasourceDto.class);
		dtoClassLookup.add(net.datenwerke.rs.incubator.client.scriptdatasource.dto.ScriptDatasourceConfigDto.class);
		dtoClassLookup.add(net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportDto.class);
		dtoClassLookup.add(net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.decorator.JxlsReportDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportFileDto.class);
		dtoClassLookup.add(net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportVariantDto.class);
		dtoClassLookup.add(net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.decorator.JxlsReportVariantDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto.LocalFileSystemDatasinkDto.class);
		dtoClassLookup.add(net.datenwerke.rs.onedrive.client.onedrive.dto.OneDriveDatasinkDto.class);
		dtoClassLookup.add(net.datenwerke.rs.reportdoc.client.dto.DeployAnalyzeCommandResultExtensionDto.class);
		dtoClassLookup.add(net.datenwerke.rs.reportdoc.client.dto.VariantTestCommandResultExtensionDto.class);
		dtoClassLookup.add(net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceDto.class);
		dtoClassLookup.add(net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceConfigDto.class);
		dtoClassLookup.add(net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportDto.class);
		dtoClassLookup.add(net.datenwerke.rs.saiku.client.saiku.dto.decorator.SaikuReportDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportVariantDto.class);
		dtoClassLookup.add(net.datenwerke.rs.saiku.client.saiku.dto.decorator.SaikuReportVariantDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.saiku.client.saiku.dto.RECSaikuChartDto.class);
		dtoClassLookup.add(net.datenwerke.rs.saiku.client.saiku.dto.decorator.RECSaikuChartDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.samba.client.samba.dto.SambaDatasinkDto.class);
		dtoClassLookup.add(net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto.ExecutedReportFileReferenceDto.class);
		dtoClassLookup.add(net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto.decorator.ExecutedReportFileReferenceDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.scheduleasfile.client.scheduleasfile.filter.dto.TeamSpaceReportJobFilterDto.class);
		dtoClassLookup.add(net.datenwerke.rs.scheduler.client.scheduler.dto.ReportServerJobFilterDto.class);
		dtoClassLookup.add(net.datenwerke.rs.scp.client.scp.dto.ScpDatasinkDto.class);
		dtoClassLookup.add(net.datenwerke.rs.scripting.client.scripting.dto.AddMenuEntryExtensionDto.class);
		dtoClassLookup.add(net.datenwerke.rs.scripting.client.scripting.dto.AddMenuSeparatorEntryExtensionDto.class);
		dtoClassLookup.add(net.datenwerke.rs.scripting.client.scripting.dto.AddReportExportFormatProviderDto.class);
		dtoClassLookup.add(net.datenwerke.rs.scripting.client.scripting.dto.AddStatusbBarLabelExtensionDto.class);
		dtoClassLookup.add(net.datenwerke.rs.scripting.client.scripting.dto.AddToolbarEntryExtensionDto.class);
		dtoClassLookup.add(net.datenwerke.rs.scripting.client.scripting.dto.DisplayConditionDto.class);
		dtoClassLookup.add(net.datenwerke.rs.scripting.client.scripting.dto.DisplayConditionTypeDto.class);
		dtoClassLookup.add(net.datenwerke.rs.scriptreport.client.scriptreport.dto.ScriptReportDto.class);
		dtoClassLookup.add(net.datenwerke.rs.scriptreport.client.scriptreport.dto.decorator.ScriptReportDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.scriptreport.client.scriptreport.dto.ScriptReportVariantDto.class);
		dtoClassLookup.add(net.datenwerke.rs.scriptreport.client.scriptreport.dto.decorator.ScriptReportVariantDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.ScriptParameterDefinitionDto.class);
		dtoClassLookup.add(net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.ScriptParameterInstanceDto.class);
		dtoClassLookup.add(net.datenwerke.rs.search.client.search.dto.SearchFilterDto.class);
		dtoClassLookup.add(net.datenwerke.rs.search.client.search.dto.decorator.SearchFilterDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.search.client.search.dto.SearchResultEntryDto.class);
		dtoClassLookup.add(net.datenwerke.rs.search.client.search.dto.decorator.SearchResultEntryDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.search.client.search.dto.SearchResultListDto.class);
		dtoClassLookup.add(net.datenwerke.rs.search.client.search.dto.decorator.SearchResultListDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.search.client.search.dto.SearchResultTagDto.class);
		dtoClassLookup.add(net.datenwerke.rs.search.client.search.dto.SearchResultTagTypeDto.class);
		dtoClassLookup.add(net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.RECTableTemplateDto.class);
		dtoClassLookup.add(net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportByteTemplateDto.class);
		dtoClassLookup.add(net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportStringTemplateDto.class);
		dtoClassLookup.add(net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportTemplateDto.class);
		dtoClassLookup.add(net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportTemplateListDto.class);
		dtoClassLookup.add(net.datenwerke.rs.teamspace.client.teamspace.dto.AppPropertyDto.class);
		dtoClassLookup.add(net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto.class);
		dtoClassLookup.add(net.datenwerke.rs.teamspace.client.teamspace.dto.decorator.TeamSpaceDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceAppDto.class);
		dtoClassLookup.add(net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceMemberDto.class);
		dtoClassLookup.add(net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceRoleDto.class);
		dtoClassLookup.add(net.datenwerke.rs.teamspace.client.teamspace.security.TeamSpaceSecureeDto.class);
		dtoClassLookup.add(net.datenwerke.rs.teamspace.client.teamspace.security.rights.TeamSpaceAdministratorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.terminal.client.terminal.dto.AutocompleteResultDto.class);
		dtoClassLookup.add(net.datenwerke.rs.terminal.client.terminal.dto.decorator.AutocompleteResultDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultDto.class);
		dtoClassLookup.add(net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultAnchorDto.class);
		dtoClassLookup.add(net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultAnchorDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultEntryDto.class);
		dtoClassLookup.add(net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultEntryDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultExtensionDto.class);
		dtoClassLookup.add(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultHtmlDto.class);
		dtoClassLookup.add(net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultHtmlDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultHyperlinkDto.class);
		dtoClassLookup.add(net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultHyperlinkDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultLineDto.class);
		dtoClassLookup.add(net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultLineDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultListDto.class);
		dtoClassLookup.add(net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultListDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultModifierDto.class);
		dtoClassLookup.add(net.datenwerke.rs.terminal.client.terminal.dto.CommandResultTableDto.class);
		dtoClassLookup.add(net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultTableDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.terminal.client.terminal.dto.CreJavaScriptDto.class);
		dtoClassLookup.add(net.datenwerke.rs.terminal.client.terminal.dto.CreMessageDto.class);
		dtoClassLookup.add(net.datenwerke.rs.terminal.client.terminal.dto.CreOverlayDto.class);
		dtoClassLookup.add(net.datenwerke.rs.terminal.client.terminal.dto.DisplayModeDto.class);
		dtoClassLookup.add(net.datenwerke.rs.terminal.client.terminal.dto.InteractiveResultModifierDto.class);
		dtoClassLookup.add(net.datenwerke.rs.terminal.client.terminal.dto.PressKeyResultModifierDto.class);
		dtoClassLookup.add(net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto.class);
		dtoClassLookup.add(net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.AbstractTsDiskNodeDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFolderDto.class);
		dtoClassLookup.add(net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskGeneralReferenceDto.class);
		dtoClassLookup.add(net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.TsDiskGeneralReferenceDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto.class);
		dtoClassLookup.add(net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.TsDiskReportReferenceDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskRootDto.class);
		dtoClassLookup.add(net.datenwerke.rs.uservariables.client.parameters.dto.UserVariableParameterDefinitionDto.class);
		dtoClassLookup.add(net.datenwerke.rs.uservariables.client.parameters.dto.UserVariableParameterInstanceDto.class);
		dtoClassLookup.add(net.datenwerke.rs.uservariables.client.parameters.dto.decorator.UserVariableParameterInstanceDtoDec.class);
		dtoClassLookup.add(net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableDefinitionDto.class);
		dtoClassLookup.add(net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableInstanceDto.class);
		dtoClassLookup.add(net.datenwerke.rs.uservariables.client.variabletypes.list.dto.ListUserVariableDefinitionDto.class);
		dtoClassLookup.add(net.datenwerke.rs.uservariables.client.variabletypes.list.dto.ListUserVariableInstanceDto.class);
		dtoClassLookup.add(net.datenwerke.rs.uservariables.client.variabletypes.string.dto.StringUserVariableDefinitionDto.class);
		dtoClassLookup.add(net.datenwerke.rs.uservariables.client.variabletypes.string.dto.StringUserVariableInstanceDto.class);
		dtoClassLookup.add(net.datenwerke.scheduler.client.scheduler.dto.JobExecutionStatusDto.class);
		dtoClassLookup.add(net.datenwerke.scheduler.client.scheduler.dto.MisfireInstructionDto.class);
		dtoClassLookup.add(net.datenwerke.scheduler.client.scheduler.dto.OutcomeDto.class);
		dtoClassLookup.add(net.datenwerke.scheduler.client.scheduler.dto.history.ActionEntryDto.class);
		dtoClassLookup.add(net.datenwerke.scheduler.client.scheduler.dto.history.ExecutionLogEntryDto.class);
		dtoClassLookup.add(net.datenwerke.scheduler.client.scheduler.dto.history.HistoryEntryPropertyDto.class);
		dtoClassLookup.add(net.datenwerke.scheduler.client.scheduler.dto.history.JobEntryDto.class);
		dtoClassLookup.add(net.datenwerke.scheduler.client.scheduler.dto.history.JobHistoryDto.class);
		dtoClassLookup.add(net.datenwerke.scheduler.client.scheduler.dto.config.complex.RetryTimeUnitDto.class);
		dtoClassLookup.add(net.datenwerke.scheduler.client.scheduler.dto.VetoActionExecutionModeDto.class);
		dtoClassLookup.add(net.datenwerke.scheduler.client.scheduler.dto.VetoJobExecutionModeDto.class);
		dtoClassLookup.add(net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterConfigurationDto.class);
		dtoClassLookup.add(net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterCriteriaDto.class);
		dtoClassLookup.add(net.datenwerke.scheduler.client.scheduler.dto.filter.OrderDto.class);
		dtoClassLookup.add(net.datenwerke.scheduler.client.scheduler.dto.config.complex.DailyConfigDto.class);
		dtoClassLookup.add(net.datenwerke.scheduler.client.scheduler.dto.config.complex.DateTriggerConfigDto.class);
		dtoClassLookup.add(net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthlyNthDayConfigDto.class);
		dtoClassLookup.add(net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthlyNthDayOfWeekConfigDto.class);
		dtoClassLookup.add(net.datenwerke.scheduler.client.scheduler.dto.config.complex.TimeDto.class);
		dtoClassLookup.add(net.datenwerke.scheduler.client.scheduler.dto.config.complex.decorator.TimeDtoDec.class);
		dtoClassLookup.add(net.datenwerke.scheduler.client.scheduler.dto.config.complex.WeeklyConfigDto.class);
		dtoClassLookup.add(net.datenwerke.scheduler.client.scheduler.dto.config.complex.YearlyAtDateConfigDto.class);
		dtoClassLookup.add(net.datenwerke.scheduler.client.scheduler.dto.config.complex.YearlyNthDayOfWeekConfigDto.class);
		dtoClassLookup.add(net.datenwerke.scheduler.client.scheduler.dto.config.complex.DailyPatternDto.class);
		dtoClassLookup.add(net.datenwerke.scheduler.client.scheduler.dto.config.complex.DailyRepeatTypeDto.class);
		dtoClassLookup.add(net.datenwerke.scheduler.client.scheduler.dto.config.complex.DaysDto.class);
		dtoClassLookup.add(net.datenwerke.scheduler.client.scheduler.dto.config.complex.EndTypesDto.class);
		dtoClassLookup.add(net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthsDto.class);
		dtoClassLookup.add(net.datenwerke.scheduler.client.scheduler.dto.config.complex.NthDto.class);
		dtoClassLookup.add(net.datenwerke.scheduler.client.scheduler.dto.config.complex.TimeUnitsDto.class);
		dtoClassLookup.add(net.datenwerke.security.client.security.dto.SecureeDto.class);
		dtoClassLookup.add(net.datenwerke.security.client.security.dto.SecurityServiceSecureeDto.class);
		dtoClassLookup.add(net.datenwerke.security.client.security.dto.AccessTypeDto.class);
		dtoClassLookup.add(net.datenwerke.security.client.security.dto.AceDto.class);
		dtoClassLookup.add(net.datenwerke.security.client.security.dto.decorator.AceDtoDec.class);
		dtoClassLookup.add(net.datenwerke.security.client.security.dto.AceAccessMapDto.class);
		dtoClassLookup.add(net.datenwerke.security.client.security.dto.decorator.AceAccessMapDtoDec.class);
		dtoClassLookup.add(net.datenwerke.security.client.security.dto.HierarchicalAceDto.class);
		dtoClassLookup.add(net.datenwerke.security.client.security.dto.InheritanceTypeDto.class);
		dtoClassLookup.add(net.datenwerke.security.client.security.dto.DeleteDto.class);
		dtoClassLookup.add(net.datenwerke.security.client.security.dto.ExecuteDto.class);
		dtoClassLookup.add(net.datenwerke.security.client.security.dto.GrantAccessDto.class);
		dtoClassLookup.add(net.datenwerke.security.client.security.dto.ReadDto.class);
		dtoClassLookup.add(net.datenwerke.security.client.security.dto.RightDto.class);
		dtoClassLookup.add(net.datenwerke.security.client.security.dto.WriteDto.class);
		dtoClassLookup.add(net.datenwerke.security.client.treedb.dto.SecuredAbstractNodeDto.class);
		dtoClassLookup.add(net.datenwerke.security.client.treedb.dto.decorator.SecuredAbstractNodeDtoDec.class);
		dtoClassLookup.add(net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto.class);
		dtoClassLookup.add(net.datenwerke.security.client.usermanager.dto.GroupDto.class);
		dtoClassLookup.add(net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto.class);
		dtoClassLookup.add(net.datenwerke.security.client.usermanager.dto.SexDto.class);
		dtoClassLookup.add(net.datenwerke.security.client.usermanager.dto.UserDto.class);
		dtoClassLookup.add(net.datenwerke.security.client.usermanager.dto.decorator.UserDtoDec.class);
		dtoClassLookup.add(net.datenwerke.security.client.usermanager.dto.UserPropertyDto.class);
		dtoClassLookup.add(net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto.class);
		dtoClassLookup.add(net.datenwerke.treedb.client.treedb.dto.decorator.AbstractNodeDtoDec.class);
		//POSO lookup


		posoClassLookup = new HashSet<Class<?>>();

		posoClassLookup.add(net.datenwerke.gf.service.history.HistoryLink.class);
		posoClassLookup.add(net.datenwerke.gf.service.juel.JuelResult.class);
		posoClassLookup.add(net.datenwerke.gf.service.juel.JuelResultType.class);
		posoClassLookup.add(net.datenwerke.rs.adminutils.service.logs.terminal.commands.ViewLogFileCommandResultExtension.class);
		posoClassLookup.add(net.datenwerke.rs.amazons3.service.amazons3.definitions.AmazonS3Datasink.class);
		posoClassLookup.add(net.datenwerke.rs.base.ext.service.parameters.fileselection.FileSelectionParameterDefinition.class);
		posoClassLookup.add(net.datenwerke.rs.base.ext.service.parameters.fileselection.FileSelectionParameterInstance.class);
		posoClassLookup.add(net.datenwerke.rs.base.ext.service.parameters.fileselection.SelectedParameterFile.class);
		posoClassLookup.add(net.datenwerke.rs.base.ext.service.parameters.fileselection.UploadedParameterFile.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.datasources.connectors.ArgumentDatasourceConnector.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.datasources.connectors.DatasourceConnector.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.datasources.connectors.DatasourceConnectorConfig.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.datasources.connectors.TextDatasourceConnector.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.datasources.connectors.UrlDatasourceConnector.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.datasources.definitions.CsvDatasource.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.datasources.definitions.CsvDatasourceConfig.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasourceConfig.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.datasources.definitions.FormatBasedDatasourceConfig.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.datasources.definitions.FormatBasedDatasourceDefinition.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.dbhelper.DatabaseHelper.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.parameters.blatext.BlatextParameterDefinition.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.parameters.blatext.BlatextParameterInstance.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.parameters.datasource.BoxLayoutMode.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.parameters.datasource.BoxLayoutPackMode.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.parameters.datasource.DatasourceParameterData.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.parameters.datasource.DatasourceParameterDefinition.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.parameters.datasource.DatasourceParameterInstance.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.parameters.datasource.Mode.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.parameters.datasource.MultiSelectionMode.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.parameters.datasource.SingleSelectionMode.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.parameters.datetime.DateTimeParameterDefinition.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.parameters.datetime.DateTimeParameterInstance.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.parameters.datetime.Mode.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.parameters.headline.HeadlineParameterDefinition.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.parameters.headline.HeadlineParameterInstance.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.parameters.separator.SeparatorParameterDefinition.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.parameters.separator.SeparatorParameterInstance.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.parameters.string.TextParameterDefinition.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.parameters.string.TextParameterInstance.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReport.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReportJRXMLFile.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReportVariant.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.jasper.output.object.CompiledCSVJasperReport.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.jasper.output.object.CompiledHTMLJasperReport.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.jasper.output.object.CompiledPNGJasperReport.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.jasper.util.JasperParameterProposal.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.table.entities.AdditionalColumnSpec.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.table.entities.AggregateFunction.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.table.entities.Column.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.table.entities.ColumnReference.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.table.entities.FilterRange.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.table.entities.NullHandling.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.table.entities.Order.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.table.entities.TableReport.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.table.entities.TableReportVariant.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.table.entities.filters.BinaryColumnFilter.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.table.entities.filters.BinaryOperator.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.table.entities.filters.BlockType.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.table.entities.filters.ColumnFilter.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.table.entities.filters.Filter.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.table.entities.filters.FilterBlock.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.table.entities.filters.FilterSpec.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.table.entities.filters.PreFilter.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormat.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatCurrency.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatDate.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatNumber.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatTemplate.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatText.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.table.entities.format.enums.CurrencyType.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.table.entities.format.enums.NumberType.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.table.output.object.CompiledHTMLTableReport.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.table.output.object.RSStringTableRow.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableRow.class);
		posoClassLookup.add(net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition.class);
		posoClassLookup.add(net.datenwerke.rs.birt.service.datasources.birtreport.entities.BirtReportDatasourceConfig.class);
		posoClassLookup.add(net.datenwerke.rs.birt.service.datasources.birtreport.entities.BirtReportDatasourceDefinition.class);
		posoClassLookup.add(net.datenwerke.rs.birt.service.datasources.birtreport.entities.BirtReportDatasourceTargetType.class);
		posoClassLookup.add(net.datenwerke.rs.birt.service.reportengine.entities.BirtReport.class);
		posoClassLookup.add(net.datenwerke.rs.birt.service.reportengine.entities.BirtReportFile.class);
		posoClassLookup.add(net.datenwerke.rs.birt.service.reportengine.entities.BirtReportVariant.class);
		posoClassLookup.add(net.datenwerke.rs.birt.service.reportengine.output.object.CompiledHTMLBirtReport.class);
		posoClassLookup.add(net.datenwerke.rs.birt.service.reportengine.output.object.CompiledPNGBirtReport.class);
		posoClassLookup.add(net.datenwerke.rs.birt.service.utils.BirtParameterProposal.class);
		posoClassLookup.add(net.datenwerke.rs.box.service.box.definitions.BoxDatasink.class);
		posoClassLookup.add(net.datenwerke.rs.computedcolumns.service.computedcolumns.entities.ComputedColumn.class);
		posoClassLookup.add(net.datenwerke.rs.condition.service.condition.entity.ReportCondition.class);
		posoClassLookup.add(net.datenwerke.rs.core.service.datasinkmanager.entities.AbstractDatasinkManagerNode.class);
		posoClassLookup.add(net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkContainer.class);
		posoClassLookup.add(net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkContainerProvider.class);
		posoClassLookup.add(net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition.class);
		posoClassLookup.add(net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkFolder.class);
		posoClassLookup.add(net.datenwerke.rs.core.service.datasourcemanager.entities.AbstractDatasourceManagerNode.class);
		posoClassLookup.add(net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer.class);
		posoClassLookup.add(net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainerProvider.class);
		posoClassLookup.add(net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition.class);
		posoClassLookup.add(net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinitionConfig.class);
		posoClassLookup.add(net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceFolder.class);
		posoClassLookup.add(net.datenwerke.rs.core.service.i18ntools.FormatPatterns.class);
		posoClassLookup.add(net.datenwerke.rs.core.service.parameters.entities.Datatype.class);
		posoClassLookup.add(net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition.class);
		posoClassLookup.add(net.datenwerke.rs.core.service.parameters.entities.ParameterInstance.class);
		posoClassLookup.add(net.datenwerke.rs.core.service.reportmanager.engine.config.RECCsv.class);
		posoClassLookup.add(net.datenwerke.rs.core.service.reportmanager.engine.config.RECJxls.class);
		posoClassLookup.add(net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig.class);
		posoClassLookup.add(net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode.class);
		posoClassLookup.add(net.datenwerke.rs.core.service.reportmanager.entities.ReportFolder.class);
		posoClassLookup.add(net.datenwerke.rs.core.service.reportmanager.entities.reports.Report.class);
		posoClassLookup.add(net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportByteProperty.class);
		posoClassLookup.add(net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportMetadata.class);
		posoClassLookup.add(net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportProperty.class);
		posoClassLookup.add(net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportServerSideStringProperty.class);
		posoClassLookup.add(net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportStringProperty.class);
		posoClassLookup.add(net.datenwerke.rs.core.service.reportmanager.entities.reports.interfaces.ServerSideProperty.class);
		posoClassLookup.add(net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant.class);
		posoClassLookup.add(net.datenwerke.rs.crystal.service.crystal.CrystalParameterProposal.class);
		posoClassLookup.add(net.datenwerke.rs.crystal.service.crystal.entities.CrystalReport.class);
		posoClassLookup.add(net.datenwerke.rs.crystal.service.crystal.entities.CrystalReportFile.class);
		posoClassLookup.add(net.datenwerke.rs.crystal.service.crystal.entities.CrystalReportVariant.class);
		posoClassLookup.add(net.datenwerke.rs.dashboard.service.dashboard.dagets.FavoriteList.class);
		posoClassLookup.add(net.datenwerke.rs.dashboard.service.dashboard.dagets.FavoriteListDadget.class);
		posoClassLookup.add(net.datenwerke.rs.dashboard.service.dashboard.dagets.FavoriteListEntry.class);
		posoClassLookup.add(net.datenwerke.rs.dashboard.service.dashboard.dagets.LibraryDadget.class);
		posoClassLookup.add(net.datenwerke.rs.dashboard.service.dashboard.dagets.ParameterDadget.class);
		posoClassLookup.add(net.datenwerke.rs.dashboard.service.dashboard.dagets.ReportDadget.class);
		posoClassLookup.add(net.datenwerke.rs.dashboard.service.dashboard.dagets.StaticHtmlDadget.class);
		posoClassLookup.add(net.datenwerke.rs.dashboard.service.dashboard.dagets.UrlDadget.class);
		posoClassLookup.add(net.datenwerke.rs.dashboard.service.dashboard.entities.AbstractDashboardManagerNode.class);
		posoClassLookup.add(net.datenwerke.rs.dashboard.service.dashboard.entities.Dadget.class);
		posoClassLookup.add(net.datenwerke.rs.dashboard.service.dashboard.entities.DadgetContainer.class);
		posoClassLookup.add(net.datenwerke.rs.dashboard.service.dashboard.entities.DadgetNode.class);
		posoClassLookup.add(net.datenwerke.rs.dashboard.service.dashboard.entities.Dashboard.class);
		posoClassLookup.add(net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardContainer.class);
		posoClassLookup.add(net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardFolder.class);
		posoClassLookup.add(net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardNode.class);
		posoClassLookup.add(net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardReference.class);
		posoClassLookup.add(net.datenwerke.rs.dashboard.service.dashboard.entities.LayoutType.class);
		posoClassLookup.add(net.datenwerke.rs.dropbox.service.dropbox.definitions.DropboxDatasink.class);
		posoClassLookup.add(net.datenwerke.rs.dsbundle.service.dsbundle.entities.DatabaseBundle.class);
		posoClassLookup.add(net.datenwerke.rs.dsbundle.service.dsbundle.entities.DatabaseBundleEntry.class);
		posoClassLookup.add(net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.EmailDatasink.class);
		posoClassLookup.add(net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode.class);
		posoClassLookup.add(net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile.class);
		posoClassLookup.add(net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder.class);
		posoClassLookup.add(net.datenwerke.rs.fileserver.service.fileserver.terminal.commands.EditCommandResultExtension.class);
		posoClassLookup.add(net.datenwerke.rs.ftp.service.ftp.definitions.FtpDatasink.class);
		posoClassLookup.add(net.datenwerke.rs.ftp.service.ftp.definitions.FtpsDatasink.class);
		posoClassLookup.add(net.datenwerke.rs.ftp.service.ftp.definitions.SftpDatasink.class);
		posoClassLookup.add(net.datenwerke.rs.globalconstants.service.globalconstants.entities.GlobalConstant.class);
		posoClassLookup.add(net.datenwerke.rs.googledrive.service.googledrive.definitions.GoogleDriveDatasink.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.GridEditorColumnConfig.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.GridEditorConfig.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.GridEditorData.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.GridEditorRecord.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.GridEditorRecordEntry.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.DateSelectionListEditor.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.DecimalSelectionListEditor.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.DoubleSelectionListEditor.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.Editor.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.FloatSelectionListEditor.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.IntBooleanEditor.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.IntSelectionListEditor.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.LongSelectionListEditor.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.SelectionListEditor.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.TextAreaEditor.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.TextBooleanEditor.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.TextDateEditor.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.TextSelectionListEditor.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.CustomValidator.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.EmptyValidator.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.FixedLengthValidator.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MaxBigDecimalValidator.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MaxDateValidator.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MaxDoubleValidator.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MaxFloatValidator.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MaxIntegerValidator.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MaxLengthValidator.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MaxLongValidator.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MinBigDecimalValidator.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MinDateValidator.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MinDoubleValidator.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MinFloatValidator.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MinIntegerValidator.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MinLengthValidator.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MinLongValidator.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.RegExValidator.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.Validator.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.entities.GridEditorReport.class);
		posoClassLookup.add(net.datenwerke.rs.grideditor.service.grideditor.entities.GridEditorReportVariant.class);
		posoClassLookup.add(net.datenwerke.rs.incubator.service.jaspertotable.entities.JasperToTableConfig.class);
		posoClassLookup.add(net.datenwerke.rs.incubator.service.scriptdatasource.entities.ScriptDatasource.class);
		posoClassLookup.add(net.datenwerke.rs.incubator.service.scriptdatasource.entities.ScriptDatasourceConfig.class);
		posoClassLookup.add(net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.JxlsReport.class);
		posoClassLookup.add(net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.JxlsReportFile.class);
		posoClassLookup.add(net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.JxlsReportVariant.class);
		posoClassLookup.add(net.datenwerke.rs.localfsdatasink.service.localfsdatasink.definitions.LocalFileSystemDatasink.class);
		posoClassLookup.add(net.datenwerke.rs.onedrive.service.onedrive.definitions.OneDriveDatasink.class);
		posoClassLookup.add(net.datenwerke.rs.reportdoc.service.terminal.commands.DeployAnalyzeCommandResultExtension.class);
		posoClassLookup.add(net.datenwerke.rs.reportdoc.service.terminal.commands.VariantTestCommandResultExtension.class);
		posoClassLookup.add(net.datenwerke.rs.saiku.service.datasource.MondrianDatasource.class);
		posoClassLookup.add(net.datenwerke.rs.saiku.service.datasource.MondrianDatasourceConfig.class);
		posoClassLookup.add(net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport.class);
		posoClassLookup.add(net.datenwerke.rs.saiku.service.saiku.entities.SaikuReportVariant.class);
		posoClassLookup.add(net.datenwerke.rs.saiku.service.saiku.reportengine.config.RECSaikuChart.class);
		posoClassLookup.add(net.datenwerke.rs.samba.service.samba.definitions.SambaDatasink.class);
		posoClassLookup.add(net.datenwerke.rs.scheduleasfile.service.scheduleasfile.entities.ExecutedReportFileReference.class);
		posoClassLookup.add(net.datenwerke.rs.scheduleasfile.service.scheduleasfile.filter.TeamSpaceReportJobFilter.class);
		posoClassLookup.add(net.datenwerke.rs.scheduler.service.scheduler.jobs.filter.ReportServerJobFilter.class);
		posoClassLookup.add(net.datenwerke.rs.scp.service.scp.definitions.ScpDatasink.class);
		posoClassLookup.add(net.datenwerke.rs.scripting.service.scripting.extensions.AddMenuEntryExtension.class);
		posoClassLookup.add(net.datenwerke.rs.scripting.service.scripting.extensions.AddMenuSeparatorEntryExtension.class);
		posoClassLookup.add(net.datenwerke.rs.scripting.service.scripting.extensions.AddReportExportFormatProvider.class);
		posoClassLookup.add(net.datenwerke.rs.scripting.service.scripting.extensions.AddStatusbBarLabelExtension.class);
		posoClassLookup.add(net.datenwerke.rs.scripting.service.scripting.extensions.AddToolbarEntryExtension.class);
		posoClassLookup.add(net.datenwerke.rs.scripting.service.scripting.extensions.DisplayCondition.class);
		posoClassLookup.add(net.datenwerke.rs.scripting.service.scripting.extensions.DisplayConditionType.class);
		posoClassLookup.add(net.datenwerke.rs.scriptreport.service.scriptreport.entities.ScriptReport.class);
		posoClassLookup.add(net.datenwerke.rs.scriptreport.service.scriptreport.entities.ScriptReportVariant.class);
		posoClassLookup.add(net.datenwerke.rs.scriptreport.service.scriptreport.parameter.ScriptParameterDefinition.class);
		posoClassLookup.add(net.datenwerke.rs.scriptreport.service.scriptreport.parameter.ScriptParameterInstance.class);
		posoClassLookup.add(net.datenwerke.rs.search.service.search.results.SearchFilter.class);
		posoClassLookup.add(net.datenwerke.rs.search.service.search.results.SearchResultEntry.class);
		posoClassLookup.add(net.datenwerke.rs.search.service.search.results.SearchResultList.class);
		posoClassLookup.add(net.datenwerke.rs.search.service.search.results.SearchResultTag.class);
		posoClassLookup.add(net.datenwerke.rs.search.service.search.results.SearchResultTagType.class);
		posoClassLookup.add(net.datenwerke.rs.tabletemplate.service.tabletemplate.config.RECTableTemplate.class);
		posoClassLookup.add(net.datenwerke.rs.tabletemplate.service.tabletemplate.entities.TableReportByteTemplate.class);
		posoClassLookup.add(net.datenwerke.rs.tabletemplate.service.tabletemplate.entities.TableReportStringTemplate.class);
		posoClassLookup.add(net.datenwerke.rs.tabletemplate.service.tabletemplate.entities.TableReportTemplate.class);
		posoClassLookup.add(net.datenwerke.rs.tabletemplate.service.tabletemplate.entities.TableReportTemplateList.class);
		posoClassLookup.add(net.datenwerke.rs.teamspace.service.teamspace.entities.AppProperty.class);
		posoClassLookup.add(net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace.class);
		posoClassLookup.add(net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpaceApp.class);
		posoClassLookup.add(net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpaceMember.class);
		posoClassLookup.add(net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpaceRole.class);
		posoClassLookup.add(net.datenwerke.rs.teamspace.service.teamspace.security.TeamSpaceSecuree.class);
		posoClassLookup.add(net.datenwerke.rs.teamspace.service.teamspace.security.rights.TeamSpaceAdministrator.class);
		posoClassLookup.add(net.datenwerke.rs.terminal.service.terminal.obj.AutocompleteResult.class);
		posoClassLookup.add(net.datenwerke.rs.terminal.service.terminal.obj.CommandResult.class);
		posoClassLookup.add(net.datenwerke.rs.terminal.service.terminal.obj.CommandResultAnchor.class);
		posoClassLookup.add(net.datenwerke.rs.terminal.service.terminal.obj.CommandResultEntry.class);
		posoClassLookup.add(net.datenwerke.rs.terminal.service.terminal.obj.CommandResultExtension.class);
		posoClassLookup.add(net.datenwerke.rs.terminal.service.terminal.obj.CommandResultHtml.class);
		posoClassLookup.add(net.datenwerke.rs.terminal.service.terminal.obj.CommandResultHyperlink.class);
		posoClassLookup.add(net.datenwerke.rs.terminal.service.terminal.obj.CommandResultLine.class);
		posoClassLookup.add(net.datenwerke.rs.terminal.service.terminal.obj.CommandResultList.class);
		posoClassLookup.add(net.datenwerke.rs.terminal.service.terminal.obj.CommandResultModifier.class);
		posoClassLookup.add(net.datenwerke.rs.terminal.service.terminal.obj.CommandResultTable.class);
		posoClassLookup.add(net.datenwerke.rs.terminal.service.terminal.obj.CreJavaScript.class);
		posoClassLookup.add(net.datenwerke.rs.terminal.service.terminal.obj.CreMessage.class);
		posoClassLookup.add(net.datenwerke.rs.terminal.service.terminal.obj.CreOverlay.class);
		posoClassLookup.add(net.datenwerke.rs.terminal.service.terminal.obj.DisplayMode.class);
		posoClassLookup.add(net.datenwerke.rs.terminal.service.terminal.obj.InteractiveResultModifier.class);
		posoClassLookup.add(net.datenwerke.rs.terminal.service.terminal.obj.PressKeyResultModifier.class);
		posoClassLookup.add(net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.AbstractTsDiskNode.class);
		posoClassLookup.add(net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskFolder.class);
		posoClassLookup.add(net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskGeneralReference.class);
		posoClassLookup.add(net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskReportReference.class);
		posoClassLookup.add(net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskRoot.class);
		posoClassLookup.add(net.datenwerke.rs.uservariables.service.parameters.UserVariableParameterDefinition.class);
		posoClassLookup.add(net.datenwerke.rs.uservariables.service.parameters.UserVariableParameterInstance.class);
		posoClassLookup.add(net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableDefinition.class);
		posoClassLookup.add(net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableInstance.class);
		posoClassLookup.add(net.datenwerke.rs.uservariables.service.variabletypes.list.ListUserVariableDefinition.class);
		posoClassLookup.add(net.datenwerke.rs.uservariables.service.variabletypes.list.ListUserVariableInstance.class);
		posoClassLookup.add(net.datenwerke.rs.uservariables.service.variabletypes.string.StringUserVariableDefinition.class);
		posoClassLookup.add(net.datenwerke.rs.uservariables.service.variabletypes.string.StringUserVariableInstance.class);
		posoClassLookup.add(net.datenwerke.scheduler.service.scheduler.entities.JobExecutionStatus.class);
		posoClassLookup.add(net.datenwerke.scheduler.service.scheduler.entities.MisfireInstruction.class);
		posoClassLookup.add(net.datenwerke.scheduler.service.scheduler.entities.Outcome.class);
		posoClassLookup.add(net.datenwerke.scheduler.service.scheduler.entities.history.ActionEntry.class);
		posoClassLookup.add(net.datenwerke.scheduler.service.scheduler.entities.history.ExecutionLogEntry.class);
		posoClassLookup.add(net.datenwerke.scheduler.service.scheduler.entities.history.HistoryEntryProperty.class);
		posoClassLookup.add(net.datenwerke.scheduler.service.scheduler.entities.history.JobEntry.class);
		posoClassLookup.add(net.datenwerke.scheduler.service.scheduler.entities.history.JobHistory.class);
		posoClassLookup.add(net.datenwerke.scheduler.service.scheduler.helper.RetryTimeUnit.class);
		posoClassLookup.add(net.datenwerke.scheduler.service.scheduler.helper.VetoActionExecutionMode.class);
		posoClassLookup.add(net.datenwerke.scheduler.service.scheduler.helper.VetoJobExecutionMode.class);
		posoClassLookup.add(net.datenwerke.scheduler.service.scheduler.stores.jpa.filter.JobFilterConfiguration.class);
		posoClassLookup.add(net.datenwerke.scheduler.service.scheduler.stores.jpa.filter.JobFilterCriteria.class);
		posoClassLookup.add(net.datenwerke.scheduler.service.scheduler.stores.jpa.filter.Order.class);
		posoClassLookup.add(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.DailyConfig.class);
		posoClassLookup.add(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.DateTriggerConfig.class);
		posoClassLookup.add(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.MonthlyNthDayConfig.class);
		posoClassLookup.add(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.MonthlyNthDayOfWeekConfig.class);
		posoClassLookup.add(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.Time.class);
		posoClassLookup.add(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.WeeklyConfig.class);
		posoClassLookup.add(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.YearlyAtDateConfig.class);
		posoClassLookup.add(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.YearlyNthDayOfWeekConfig.class);
		posoClassLookup.add(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.DailyPattern.class);
		posoClassLookup.add(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.DailyRepeatType.class);
		posoClassLookup.add(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.Days.class);
		posoClassLookup.add(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.EndTypes.class);
		posoClassLookup.add(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.Months.class);
		posoClassLookup.add(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.Nth.class);
		posoClassLookup.add(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.TimeUnits.class);
		posoClassLookup.add(net.datenwerke.security.service.security.Securee.class);
		posoClassLookup.add(net.datenwerke.security.service.security.SecurityServiceSecuree.class);
		posoClassLookup.add(net.datenwerke.security.service.security.entities.AccessType.class);
		posoClassLookup.add(net.datenwerke.security.service.security.entities.Ace.class);
		posoClassLookup.add(net.datenwerke.security.service.security.entities.AceAccessMap.class);
		posoClassLookup.add(net.datenwerke.security.service.security.entities.HierarchicalAce.class);
		posoClassLookup.add(net.datenwerke.security.service.security.entities.InheritanceType.class);
		posoClassLookup.add(net.datenwerke.security.service.security.rights.Delete.class);
		posoClassLookup.add(net.datenwerke.security.service.security.rights.Execute.class);
		posoClassLookup.add(net.datenwerke.security.service.security.rights.GrantAccess.class);
		posoClassLookup.add(net.datenwerke.security.service.security.rights.Read.class);
		posoClassLookup.add(net.datenwerke.security.service.security.rights.Right.class);
		posoClassLookup.add(net.datenwerke.security.service.security.rights.Write.class);
		posoClassLookup.add(net.datenwerke.security.service.treedb.entities.SecuredAbstractNode.class);
		posoClassLookup.add(net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode.class);
		posoClassLookup.add(net.datenwerke.security.service.usermanager.entities.Group.class);
		posoClassLookup.add(net.datenwerke.security.service.usermanager.entities.OrganisationalUnit.class);
		posoClassLookup.add(net.datenwerke.security.service.usermanager.entities.Sex.class);
		posoClassLookup.add(net.datenwerke.security.service.usermanager.entities.User.class);
		posoClassLookup.add(net.datenwerke.security.service.usermanager.entities.UserProperty.class);
		posoClassLookup.add(net.datenwerke.treedb.service.treedb.AbstractNode.class);
	}


	/**
	 * Generates a Dto instance given a Poso.
	 */
	@SuppressWarnings("unchecked")
	public Object instantiateDto(Object poso)  {
		boolean initial = null == creationHelper.get();
		if(initial)
			creationHelper.set(creationHelperProvider.get());

		try{
			/* return null if poso is null */
			if(null == poso)
				return null;
			if(! isAuthorityForPoso(poso)){
				for(DtoService submodule : subModules){
					if(submodule.isAuthorityForPoso(poso)){
						return submodule.instantiateDto(poso);
					}
				}
				throw new IllegalArgumentException("Unrecognized Dto: " + poso.getClass().getName());
			}

			/* unproxy hibernate object if necessary */
			if (poso instanceof HibernateProxy)
				poso = ((HibernateProxy)poso).getHibernateLazyInitializer().getImplementation();
			/* validate object and find correct type */
			Class<?> posoType = poso.getClass();
			while(! dtoGeneratorByPosoLookup.containsKey(posoType)){
				if(null == posoType)
					throw new IllegalArgumentException("Unrecognized Poso: " + poso.getClass().getName());
				posoType = posoType.getSuperclass();
			}

			/* get generator */
			Poso2DtoGenerator generator = dtoGeneratorByPosoLookup.get(posoType);
			return generator.instantiateDto(poso);
		} finally {
			if(initial){
				try{
					creationHelper.get().cleanup();
				} finally {
					creationHelper.set(null);
				}
			}
		}
	}


	/**
	 * Generates a Dto instance given a Poso.
	 */
	@SuppressWarnings("unchecked")
	public Object instantiateDto(Class<?> posoType)  {
		boolean initial = null == creationHelper.get();
		if(initial)
			creationHelper.set(creationHelperProvider.get());

		try{
			/* return null if poso is null */
			if(null == posoType)
				return null;
			if(! isAuthorityForPosoClass(posoType)){
				for(DtoService submodule : subModules){
					if(submodule.isAuthorityForPosoClass(posoType)){
						return submodule.instantiateDto(posoType);
					}
				}
				throw new IllegalArgumentException("Unrecognized Dto: " + posoType.getName());
			}

			/* validate object and find correct type */
			Class<?> orgType = posoType;
			while(! dtoGeneratorByPosoLookup.containsKey(posoType)){
				if(null == posoType)
					throw new IllegalArgumentException("Unrecognized Poso: " + orgType.getName());
				posoType = posoType.getSuperclass();
			}

			/* get generator */
			Poso2DtoGenerator generator = dtoGeneratorByPosoLookup.get(posoType);
			return generator.instantiateDto(null);
		} finally {
			if(initial){
				try{
					creationHelper.get().cleanup();
				} finally {
					creationHelper.set(null);
				}
			}
		}
	}


	/**
	 * Generates a DTO given a Poso.
	 */
	@SuppressWarnings("unchecked")
	public Object createDto(Object poso, DtoView here, DtoView referenced)  {
		boolean initial = null == creationHelper.get();
		if(initial)
			creationHelper.set(creationHelperProvider.get());

		/* unproxy hibernate object if necessary */
		if (poso instanceof HibernateProxy)
			poso = ((HibernateProxy)poso).getHibernateLazyInitializer().getImplementation();

		try{
			/* return null if poso is null */
			if(null == poso)
				return null;
			if(! isAuthorityForPoso(poso)){
				for(DtoService submodule : subModules){
					if(submodule.isAuthorityForPoso(poso)){
						return submodule.createDto(poso, here, referenced);
					}
				}
				throw new IllegalArgumentException("Unrecognized Dto: " + poso.getClass().getName());
			}
			/* validate object and find correct type */
			Class<?> posoType = poso.getClass();
			while(! dtoGeneratorByPosoLookup.containsKey(posoType)){
				if(null == posoType)
					throw new IllegalArgumentException("Unrecognized Poso: " + poso.getClass().getName());
				posoType = posoType.getSuperclass();
			}

			/* get generator */
			Poso2DtoGenerator generator = dtoGeneratorByPosoLookup.get(posoType);
			return generator.createDto(poso, here, referenced);
		} finally {
			if(initial){
				try{
					creationHelper.get().cleanup();
				} finally {
					creationHelper.set(null);
				}
			}
		}
	}

	public Object createDto(Object poso)  {
		boolean initial = null == creationHelper.get();
		if(initial)
			creationHelper.set(creationHelperProvider.get());

		try{
			return createDto(poso, DtoView.NORMAL, DtoView.MINIMAL);
		} finally {
			if(initial){
				try{
					creationHelper.get().cleanup();
				} finally {
					creationHelper.set(null);
				}
			}
		}
	}

	public Object createListDto(Object poso)  {
		boolean initial = null == creationHelper.get();
		if(initial)
			creationHelper.set(creationHelperProvider.get());

		try{
			return createDto(poso, DtoView.LIST, DtoView.MINIMAL);
		} finally {
			if(initial){
				try{
					creationHelper.get().cleanup();
				} finally {
					creationHelper.set(null);
				}
			}
		}
	}

	public Object createDtoFullAccess(Object poso)  {
		boolean initial = null == creationHelper.get();
		if(initial)
			creationHelper.set(creationHelperProvider.get());

		try{
			return createDto(poso, DtoView.ALL, DtoView.MINIMAL);
		} finally {
			if(initial){
				try{
					creationHelper.get().cleanup();
				} finally {
					creationHelper.set(null);
				}
			}
		}
	}

	/**
	 * Loads a Poso given a Dto.
	 */
	@SuppressWarnings("unchecked")
	public Object loadPoso(Object dto)  {
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator generator = null;
		boolean initial = null == creationHelper.get();
		if(initial)
			creationHelper.set(creationHelperProvider.get());

		try{
			if(null == dto)
				return null;

			if(! isAuthorityForDto(dto)){
				for(DtoService submodule : subModules){
					if(submodule.isAuthorityForDto(dto)){
						return submodule.loadPoso(dto);
					}
				}
				/* handle anonymous enums */
				if(dto instanceof java.lang.Enum && ! java.lang.Enum.class.equals(dto.getClass().getSuperclass())){
					Class<?> superEnum = dto.getClass().getSuperclass();
					if(! isAuthorityForDtoClass(superEnum)){
						for(DtoService submodule : subModules){
							if(submodule.isAuthorityForDtoClass(superEnum)){
								return submodule.loadPoso(dto);
							}
						}
					}
				}
				else if(dto instanceof java.lang.Enum && java.lang.Enum.class.equals(dto.getClass().getSuperclass()) && ! isAuthorityForDtoClass(dto.getClass().getSuperclass()))
					throw new IllegalArgumentException("Unrecognized Dto: " + dto.getClass().getName());
			}

			/* validate object */
			Class<?> dtoType = dto.getClass();
			while(! dto2PosoGeneratorLookup.containsKey(dtoType)){
				if(null == dtoType)
					throw new IllegalArgumentException("Unrecognized Dto: " + dto.getClass().getName());
				dtoType = dtoType.getSuperclass();
			}

			/* get generator */
			generator = dto2PosoGeneratorLookup.get(dtoType);
			Object poso = generator.loadPoso(dto);
			creationHelper.get().posoLoadedFor(dto, poso, generator);
			return poso;
		} finally {
			if(initial){
				try{
					creationHelper.get().cleanup();
				} finally {
					creationHelper.set(null);
				}
			}
		}
	}

	/**
	 * Generates a Poso given a Dto.
	 */
	@SuppressWarnings("unchecked")
	public Object createPoso(Object dto)  throws net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException {
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator generator = null;
		boolean initial = null == creationHelper.get();
		if(initial)
			creationHelper.set(creationHelperProvider.get());

		try{
			if(null == dto)
				return null;

			if(! isAuthorityForDto(dto)){
				for(DtoService submodule : subModules){
					if(submodule.isAuthorityForDto(dto)){
						Object poso = submodule.createPoso(dto);
						return poso;
					}
				}
				/* handle anonymous enums */
				if(dto instanceof java.lang.Enum && ! java.lang.Enum.class.equals(dto.getClass().getSuperclass())){
					Class<?> superEnum = dto.getClass().getSuperclass();
					if(! isAuthorityForDtoClass(superEnum)){
						for(DtoService submodule : subModules){
							if(submodule.isAuthorityForDtoClass(superEnum)){
								Object poso = submodule.createPoso(dto);
								return poso;
							}
						}
					}
				}
				else if(dto instanceof java.lang.Enum && java.lang.Enum.class.equals(dto.getClass().getSuperclass()) && ! isAuthorityForDtoClass(dto.getClass().getSuperclass()))
					throw new IllegalArgumentException("Unrecognized Dto: " + dto.getClass().getName());
			}

			/* validate object */
			Class<?> dtoType = dto.getClass();
			while(! dto2PosoGeneratorLookup.containsKey(dtoType)){
				if(null == dtoType)
					throw new IllegalArgumentException("Unrecognized Dto: " + dto.getClass().getName());
				dtoType = dtoType.getSuperclass();
			}

			/* get generator */
			generator = dto2PosoGeneratorLookup.get(dtoType);
			Object poso = generator.createPoso(dto);

			creationHelper.get().posoCreatedFor(dto, poso, generator);

			return poso;
		} finally {
			if(initial){
				try{
					creationHelper.get().cleanup();
				} finally {
					creationHelper.set(null);
				}
			}
		}
	}

	/**
	 * Generates a Poso given a Dto.
	 */
	@SuppressWarnings("unchecked")
	public Object createUnmanagedPoso(Object dto)  throws net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException {
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator generator = null;
		boolean initial = null == creationHelper.get();
		if(initial){
			creationHelper.set(creationHelperProvider.get());
			creationHelper.get().setModeUnmanaged(true);
		}

		try{
			if(null == dto)
				return null;

			if(! isAuthorityForDto(dto)){
				for(DtoService submodule : subModules){
					if(submodule.isAuthorityForDto(dto)){
						Object poso = submodule.createUnmanagedPoso(dto);
						return poso;
					}
				}
				/* handle anonymous enums */
				if(dto instanceof java.lang.Enum && ! java.lang.Enum.class.equals(dto.getClass().getSuperclass())){
					Class<?> superEnum = dto.getClass().getSuperclass();
					if(! isAuthorityForDtoClass(superEnum)){
						for(DtoService submodule : subModules){
							if(submodule.isAuthorityForDtoClass(superEnum)){
								Object poso = submodule.createUnmanagedPoso(dto);
								return poso;
							}
						}
					}
				}
				else if(dto instanceof java.lang.Enum && java.lang.Enum.class.equals(dto.getClass().getSuperclass()) && ! isAuthorityForDtoClass(dto.getClass().getSuperclass()))
					throw new IllegalArgumentException("Unrecognized Dto: " + dto.getClass().getName());
			}

			/* validate object */
			Class<?> dtoType = dto.getClass();
			while(! dto2PosoGeneratorLookup.containsKey(dtoType)){
				if(null == dtoType)
					throw new IllegalArgumentException("Unrecognized Dto: " + dto.getClass().getName());
				dtoType = dtoType.getSuperclass();
			}

			/* get generator */
			generator = dto2PosoGeneratorLookup.get(dtoType);
			Object poso = generator.createUnmanagedPoso(dto);
			creationHelper.get().posoCreatedFor(dto, poso, generator);
			creationHelper.get().unmanagedPosoCreatedFor(dto, poso, generator);
			return poso;
		} finally {
			if(initial){
				try{
					creationHelper.get().cleanup();
				} finally {
					creationHelper.set(null);
				}
			}
		}
	}

	/**
	 * Generates a Poso given a Dto's class (no properties are set).
	 */
	@SuppressWarnings("unchecked")
	public Object instantiatePoso(Class<?> dtoType)  {
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator generator = null;
		boolean initial = null == creationHelper.get();
		if(initial)
			creationHelper.set(creationHelperProvider.get());

		try{
			if(null == dtoType)
				return null;

			if(! isAuthorityForDtoClass(dtoType)){
				for(DtoService submodule : subModules){
					if(submodule.isAuthorityForDtoClass(dtoType)){
						return submodule.instantiatePoso(dtoType);
					}
				}
				throw new IllegalArgumentException("Unrecognized Dto: " + dtoType.getName());
			}
			/* validate object */
			Class<?> tmpType = dtoType;
			while(! dto2PosoGeneratorLookup.containsKey(dtoType)){
				if(null == dtoType)
					throw new IllegalArgumentException("Unrecognized Dto: " + tmpType.getName());
				dtoType = dtoType.getSuperclass();
			}

			/* get generator */
			generator = dto2PosoGeneratorLookup.get(dtoType);
			Object poso = generator.instantiatePoso();
			creationHelper.get().posoInstantiatedFor(poso, generator);
			return poso;
		} finally {
			if(initial){
				try{
					creationHelper.get().cleanup();
				} finally {
					creationHelper.set(null);
				}
			}
		}
	}

	/**
	 * Merges a Poso given a Dto.
	 */
	@SuppressWarnings("unchecked")
	public void mergePoso(Object dto, Object poso)  throws net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException {
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator generator = null;
		boolean initial = null == creationHelper.get();
		if(initial)
			creationHelper.set(creationHelperProvider.get());

		try{
			if(null == dto || null == poso)
				return;

			if(! isAuthorityForDto(dto)){
				for(DtoService submodule : subModules){
					if(submodule.isAuthorityForDto(dto)){
						submodule.mergePoso(dto, poso);
						return;
					}
				}
				/* handle anonymous enums */
				if(dto instanceof java.lang.Enum && ! java.lang.Enum.class.equals(dto.getClass().getSuperclass())){
					Class<?> superEnum = dto.getClass().getSuperclass();
					if(! isAuthorityForDtoClass(superEnum)){
						for(DtoService submodule : subModules){
							if(submodule.isAuthorityForDtoClass(superEnum)){
								submodule.mergePoso(dto, poso);
								return;
							}
						}
					}
				}
				else if(dto instanceof java.lang.Enum && java.lang.Enum.class.equals(dto.getClass().getSuperclass()) && ! isAuthorityForDtoClass(dto.getClass().getSuperclass()))
					throw new IllegalArgumentException("Unrecognized Dto: " + dto.getClass().getName());
			}

			/* validate object */
			Class<?> dtoType = dto.getClass();
			while(! dto2PosoGeneratorLookup.containsKey(dtoType)){
				if(null == dtoType)
					throw new IllegalArgumentException("Unrecognized Dto: " + dto.getClass().getName());
				dtoType = dtoType.getSuperclass();
			}

			/* get generator */
			generator = dto2PosoGeneratorLookup.get(dtoType);
			generator.mergePoso(dto, poso);
			creationHelper.get().posoMergedFor(dto, poso, generator);
		} finally {
			if(initial){
				try{
					creationHelper.get().cleanup();
				} finally {
					creationHelper.set(null);
				}
			}
		}
	}

	/**
	 * Merges an unmanaged Poso given a Dto.
	 */
	@SuppressWarnings("unchecked")
	public void mergeUnmanagedPoso(Object dto, Object poso)  throws net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException {
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator generator = null;
		boolean initial = null == creationHelper.get();
		if(initial){
			creationHelper.set(creationHelperProvider.get());
			creationHelper.get().setModeUnmanaged(true);
		}

		try{
			if(null == dto || null == poso)
				return;

			if(! isAuthorityForDto(dto)){
				for(DtoService submodule : subModules){
					if(submodule.isAuthorityForDto(dto)){
						submodule.mergeUnmanagedPoso(dto, poso);
						return;
					}
				}
				/* handle anonymous enums */
				if(dto instanceof java.lang.Enum && ! java.lang.Enum.class.equals(dto.getClass().getSuperclass())){
					Class<?> superEnum = dto.getClass().getSuperclass();
					if(! isAuthorityForDtoClass(superEnum)){
						for(DtoService submodule : subModules){
							if(submodule.isAuthorityForDtoClass(superEnum)){
								submodule.mergeUnmanagedPoso(dto, poso);
								return;
							}
						}
					}
				}
				else if(dto instanceof java.lang.Enum && java.lang.Enum.class.equals(dto.getClass().getSuperclass()) && ! isAuthorityForDtoClass(dto.getClass().getSuperclass()))
					throw new IllegalArgumentException("Unrecognized Dto: " + dto.getClass().getName());
			}

			/* validate object */
			Class<?> dtoType = dto.getClass();
			while(! dto2PosoGeneratorLookup.containsKey(dtoType)){
				if(null == dtoType)
					throw new IllegalArgumentException("Unrecognized Dto: " + dto.getClass().getName());
				dtoType = dtoType.getSuperclass();
			}

			/* get generator */
			generator = dto2PosoGeneratorLookup.get(dtoType);
			generator.mergeUnmanagedPoso(dto, poso);
			creationHelper.get().posoMergedFor(dto, poso, generator);
		} finally {
			if(initial){
				try{
					creationHelper.get().cleanup();
				} finally {
					creationHelper.set(null);
				}
			}
		}
	}

	/**
	 * Loads a poso and merges data given a Dto.
	 */
	@SuppressWarnings("unchecked")
	public Object loadAndMergePoso(Object dto)  throws net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException {
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator generator = null;
		boolean initial = null == creationHelper.get();
		if(initial)
			creationHelper.set(creationHelperProvider.get());

		try{
			if(null == dto)
				return null;

			if(! isAuthorityForDto(dto)){
				for(DtoService submodule : subModules){
					if(submodule.isAuthorityForDto(dto)){
						return submodule.loadAndMergePoso(dto);
					}
				}
				/* handle anonymous enums */
				if(dto instanceof java.lang.Enum && ! java.lang.Enum.class.equals(dto.getClass().getSuperclass())){
					Class<?> superEnum = dto.getClass().getSuperclass();
					if(! isAuthorityForDtoClass(superEnum)){
						for(DtoService submodule : subModules){
							if(submodule.isAuthorityForDtoClass(superEnum)){
								return submodule.loadAndMergePoso(dto);
							}
						}
					}
				}
				else if(dto instanceof java.lang.Enum && java.lang.Enum.class.equals(dto.getClass().getSuperclass()) && ! isAuthorityForDtoClass(dto.getClass().getSuperclass()))
					throw new IllegalArgumentException("Unrecognized Dto: " + dto.getClass().getName());
			}

			/* validate object */
			Class<?> dtoType = dto.getClass();
			while(! dto2PosoGeneratorLookup.containsKey(dtoType)){
				if(null == dtoType)
					throw new IllegalArgumentException("Unrecognized Dto: " + dto.getClass().getName());
				dtoType = dtoType.getSuperclass();
			}

			/* get generator */
			generator = dto2PosoGeneratorLookup.get(dtoType);
			Object poso = generator.loadAndMergePoso(dto);
			creationHelper.get().posoLoadedFor(dto, poso, generator);
			creationHelper.get().posoMergedFor(dto, poso, generator);
			return poso;
		} finally {
			if(initial){
				try{
					creationHelper.get().cleanup();
				} finally {
					creationHelper.set(null);
				}
			}
		}
	}


	public boolean isAuthorityForPosoClass(Class<?> posoType)  {
		if(null == posoType)
			return false;

		/* validate object */
		while(! posoClassLookup.contains(posoType)){
			if(null == posoType || posoType.isAnnotationPresent(net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto.class))
				return false;
			posoType = posoType.getSuperclass();
		}

		return true;
	}


	public boolean isAuthorityForDtoClass(Class<?> dtoType)  {
		if(null == dtoType)
			return false;

		/* validate object */
		if(! dtoClassLookup.contains(dtoType)){
			return false;
		}

		return true;
	}


	public boolean isAuthorityForPoso(Object poso)  {
		if(null == poso)
			return false;

		return isAuthorityForPosoClass(poso.getClass());
	}


	public boolean isAuthorityForDto(Object dto)  {
		if(null == dto)
			return false;

		return isAuthorityForDtoClass(dto.getClass());
	}

	@SuppressWarnings("unchecked")
	public String[] createFto(Object poso, DtoView here, DtoView referenced)  {
		Dto dto = (Dto)createDto(poso, here, referenced);
		return dto2Fto(dto);
	}

	@SuppressWarnings("unchecked")
	public String[] createFto(Object poso)  {
		Dto dto = (Dto)createDto(poso);
		return dto2Fto(dto);
	}

	@SuppressWarnings("unchecked")
	public String[] createListFto(Object poso)  {
		Dto dto = (Dto)createListDto(poso);
		return dto2Fto(dto);
	}

	@SuppressWarnings("unchecked")
	public String[] createFtoFullAccess(Object poso)  {
		Dto dto = (Dto)createDtoFullAccess(poso);
		return dto2Fto(dto);
	}

	@SuppressWarnings("unchecked")
	public String[] dto2Fto(Dto dto)  {
		List<PropertyAccessor> propertyAccessors = dto.getPropertyAccessors();
		ArrayList<String> res = new ArrayList<String>();
		for(PropertyAccessor pa : propertyAccessors){
			try {
				// make sure this property can be recreated from string
				if(dto instanceof FtoSupervisor && ((FtoSupervisor)dto).consumes(pa))
				    res.add(((FtoSupervisor)dto).adaptFtoGeneration(pa, dto));
			    else {
			        Object prop = pa.getValue(dto);
			    	if(null == prop)
			    	    res.add(null);
			    	else {
			    	    if(!(prop instanceof String))
				        	prop.getClass().getMethod("valueOf", String.class);
				        res.add(String.valueOf(prop));
			        }
			    }
			} catch (Exception e) {
				// never mind
				res.add("_fto_ignore_this:");
			}
		}
		res.add("_fto_dto_type:" + dto.getClass().getName());
		if(dto instanceof IdedDto)
		    res.add("_fto_dto_id:" + String.valueOf(((IdedDto)dto).getDtoId()));
		return res.toArray(new String[0]);
	}


	public DtoCreationHelper getCreationHelper()  {
		return creationHelper.get();
	}


	public void setCreationHelper(DtoCreationHelper creationHelper)  {
		this.creationHelper.set(creationHelper);
	}


	public void attachSubModule(DtoService subModule)  {
		subModules.add(subModule);
	}

	/**
	 * Resolves poso from mapper
	 */
	public Class<?> getPosoFromDtoMapper(Dto2PosoMapper mapper)  {
		boolean initial = null == creationHelper.get();
		if(initial)
			creationHelper.set(creationHelperProvider.get());

		try{
			if(null == mapper)
				return null;

			return mapper.getClass().getAnnotation(CorrespondingPoso.class).value();
		} finally {
			if(initial){
				try{
					creationHelper.get().cleanup();
				} finally {
					creationHelper.set(null);
				}
			}
		}
	}


}
