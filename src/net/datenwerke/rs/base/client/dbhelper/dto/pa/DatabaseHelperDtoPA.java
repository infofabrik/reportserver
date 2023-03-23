package net.datenwerke.rs.base.client.dbhelper.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Boolean;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.dbhelper.dto.DatabaseHelperDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.base.service.dbhelper.DatabaseHelper.class)
public interface DatabaseHelperDtoPA extends PropertyAccess<DatabaseHelperDto> {


	public static final DatabaseHelperDtoPA INSTANCE = GWT.create(DatabaseHelperDtoPA.class);


	/* Properties */
	public ValueProvider<DatabaseHelperDto,String> description();
	public ValueProvider<DatabaseHelperDto,String> descriptor();
	public ValueProvider<DatabaseHelperDto,String> driver();
	public ValueProvider<DatabaseHelperDto,String> name();
	public ValueProvider<DatabaseHelperDto,Boolean> jdbcDriverAvailable();


}
