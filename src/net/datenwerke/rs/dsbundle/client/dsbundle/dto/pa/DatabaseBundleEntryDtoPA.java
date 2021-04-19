package net.datenwerke.rs.dsbundle.client.dsbundle.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.datasourcemanager.dto.AbstractDatasourceManagerNodeDto;
import net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleEntryDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.dsbundle.service.dsbundle.entities.DatabaseBundleEntry.class)
public interface DatabaseBundleEntryDtoPA extends PropertyAccess<DatabaseBundleEntryDto> {


	public static final DatabaseBundleEntryDtoPA INSTANCE = GWT.create(DatabaseBundleEntryDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<DatabaseBundleEntryDto> dtoId();

	/* Properties */
	public ValueProvider<DatabaseBundleEntryDto,AbstractDatasourceManagerNodeDto> database();
	public ValueProvider<DatabaseBundleEntryDto,Long> id();
	public ValueProvider<DatabaseBundleEntryDto,String> key();


}
