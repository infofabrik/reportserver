package net.datenwerke.rs.incubator.service.exportmetadata;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.base.service.parameterreplacements.provider.ReportForJuel;
import net.datenwerke.rs.base.service.parameterreplacements.provider.UserForJuel;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.incubator.service.exportmetadata.annotations.ExportMetadataModuleAuthor;
import net.datenwerke.rs.incubator.service.exportmetadata.annotations.ExportMetadataModuleCreator;
import net.datenwerke.rs.incubator.service.exportmetadata.annotations.ExportMetadataModuleTitle;
import net.datenwerke.rs.utils.juel.SimpleJuel;
import net.datenwerke.security.service.usermanager.entities.User;

public class ExportMetadataServiceImpl implements ExportMetadataService {

	private static final String REPORT_REPLACEMENT_KEY = "report";
	private static final String USER_REPLACEMENT_KEY = "user";
	
	private final Provider<String> authorProvider;
	private final Provider<String> titleProvider;
	private final Provider<String> creatorProvider;
	
	private final Provider<SimpleJuel> simpleJuelProvider;

	/**
	 * All three elements are configurable in the Export metadata configuration
	 * page.
	 * 
	 * @param author The author of the to be generated PDF file.
	 * @param title  The title of the to be generated PDF file.
	 * @param creator The creator of the to be generated PDF file.
	 */
	@Inject
	public ExportMetadataServiceImpl(
			@ExportMetadataModuleAuthor Provider<String> author,
			@ExportMetadataModuleTitle Provider<String> title,
			@ExportMetadataModuleCreator Provider<String> creator,
			Provider<SimpleJuel> simpleJuelProvider
			) {
		
		/* store objects */
		this.authorProvider = author;
		this.titleProvider = title;
		this.creatorProvider = creator;
		
		this.simpleJuelProvider = simpleJuelProvider;
	}
	
	
	@Override
	public String getAuthor(){
		return authorProvider.get();
	}
	
	@Override
	public String getCreator(){
		return creatorProvider.get();
	}

	@Override
	public String getTitle(){
		return titleProvider.get();
	}

	@Override
	public String getAuthor(Report report, User user){
		return parse(report, user, getAuthor());
	}
	
	@Override
	public String getCreator(Report report, User user){
		return parse(report, user, getCreator());
	}
	
	@Override
	public String getTitle(Report report, User user){
		return parse(report, user, getTitle());
	}


	private String parse(Report report, User user, String template) {
		if(null == template)
			return "";
		
		try{
			return getJuel(report, user).parse(template);
		} catch (Exception e) {
			return "Could not parse template: " + String.valueOf(template);
		}
	}


	private SimpleJuel getJuel(Report report, User user) {
		SimpleJuel juel = simpleJuelProvider.get();
		juel.addReplacement(REPORT_REPLACEMENT_KEY, new ReportForJuel(report));
		juel.addReplacement(USER_REPLACEMENT_KEY, UserForJuel.createInstance(user));
		
		return juel;
	}
	
}
