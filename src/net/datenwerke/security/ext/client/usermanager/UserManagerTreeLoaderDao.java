package net.datenwerke.security.ext.client.usermanager;

import net.datenwerke.security.client.usermanager.dto.SexDto;
import net.datenwerke.security.ext.client.usermanager.rpc.UserManagerTreeLoaderAsync;
import net.datenwerke.treedb.client.treedb.TreeDbFtoConverter;
import net.datenwerke.treedb.client.treedb.TreeDbFtoConverter.TypeConverter;
import net.datenwerke.treedb.client.treedb.TreeDbLoaderDao;

import com.google.inject.Inject;

public class UserManagerTreeLoaderDao extends TreeDbLoaderDao {

	@Inject
	public UserManagerTreeLoaderDao(UserManagerTreeLoaderAsync treeLoader, TreeDbFtoConverter treeDbFtoConverter){
		super(treeLoader, treeDbFtoConverter);
		
		/* UserDto */
		treeDbFtoConverter.addTypeConverter(new TypeConverter() {
			
			@Override
			public Object convert(Class<?> type, String val) {
				if(null == val)
					return null;
				return SexDto.valueOf(val);
			}
			
			@Override
			public boolean consumes(Class<?> type) {
				return type.equals(SexDto.class);
			}
		});
		
	}
}
