package net.datenwerke.rs.dsbundle.client.dsbundle.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.datasources.dto.pa.DatabaseDatasourceDtoPA;
import net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleDto;
import net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleEntryDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.dsbundle.service.dsbundle.entities.DatabaseBundle.class)
public interface DatabaseBundleDtoPA extends DatabaseDatasourceDtoPA {


	public static final DatabaseBundleDtoPA INSTANCE = GWT.create(DatabaseBundleDtoPA.class);


	/* Properties */
	public ValueProvider<DatabaseBundleDto,Set<DatabaseBundleEntryDto>> bundleEntries();
	public ValueProvider<DatabaseBundleDto,String> keySource();
	public ValueProvider<DatabaseBundleDto,String> keySourceParamName();
	public ValueProvider<DatabaseBundleDto,String> mappingSource();


}
