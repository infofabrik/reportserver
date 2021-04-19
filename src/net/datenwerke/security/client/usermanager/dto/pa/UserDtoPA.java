package net.datenwerke.security.client.usermanager.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Boolean;
import java.lang.String;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.security.client.usermanager.dto.GroupDto;
import net.datenwerke.security.client.usermanager.dto.SexDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.client.usermanager.dto.UserPropertyDto;
import net.datenwerke.security.client.usermanager.dto.decorator.UserDtoDec;
import net.datenwerke.security.client.usermanager.dto.pa.AbstractUserManagerNodeDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.security.service.usermanager.entities.User.class)
public interface UserDtoPA extends AbstractUserManagerNodeDtoPA {


	public static final UserDtoPA INSTANCE = GWT.create(UserDtoPA.class);


	/* Properties */
	public ValueProvider<UserDto,Boolean> active();
	public ValueProvider<UserDto,String> email();
	public ValueProvider<UserDto,String> firstname();
	public ValueProvider<UserDto,Set<GroupDto>> groups();
	public ValueProvider<UserDto,String> lastname();
	public ValueProvider<UserDto,String> password();
	public ValueProvider<UserDto,Set<UserPropertyDto>> properties();
	public ValueProvider<UserDto,SexDto> sex();
	public ValueProvider<UserDto,Boolean> superUser();
	public ValueProvider<UserDto,String> title();
	public ValueProvider<UserDto,String> username();
	public ValueProvider<UserDto,Boolean> hasPassword();


}
