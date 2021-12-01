package net.datenwerke.rs.base.service.parameterreplacements.provider;

import net.datenwerke.security.service.usermanager.entities.User;

public class UserForJuel {

	private String firstname = "";
	private String lastname = "";
	private String email = "";
	private String username = "";
	private long id = -1;
	private String title = "";
	
	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}
	
	public String getTitle() {
		return title;
	}

	public long getId() {
		return id;
	}

	public static UserForJuel createInstance(User user) {
		UserForJuel juelUser = new UserForJuel();
		
		if(null != user){
			if(null != user.getFirstname())
				juelUser.firstname = user.getFirstname();
			if(null != user.getLastname())
				juelUser.lastname = user.getLastname();
			if(null != user.getEmail())
				juelUser.email = user.getEmail();
			if(null != user.getUsername())
				juelUser.username = user.getUsername();
			if(null != user.getId())
				juelUser.id = user.getId();
			if(null != user.getTitle())
				juelUser.title = user.getTitle();
		}
		
		return juelUser;
	}

	
}
