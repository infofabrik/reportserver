package net.datenwerke.rs.incubator.service.versioning.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import net.datenwerke.rs.incubator.service.versioning.RevisionEntityListener;
import net.datenwerke.security.service.authenticator.AuthenticatorService;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import com.google.inject.Inject;
import com.google.inject.Provider;

@Entity
@Table(name="REVISION")
@RevisionEntity(RevisionEntityListener.class)
public class Revision extends DefaultRevisionEntity implements Comparable<Revision>{

	@Inject
	private static Provider<AuthenticatorService> authenticatorServiceProvider;
	
	private static final long serialVersionUID = 6478004157779519705L;

	private long userId;
	
	public Revision() {
		try{
			this.userId = authenticatorServiceProvider.get().getCurrentUser().getId();
		}catch (Exception e) {
		}
	}
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public int compareTo(Revision o) {
		return Long.valueOf(o.getId()).compareTo(Long.valueOf(getId()));
	}
	
}
