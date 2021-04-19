package net.datenwerke.rs.teamspace.client.teamspace.dto;

import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.teamspace.client.teamspace.locale.TeamSpaceMessages;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public enum TeamSpaceRoleDto {

	ADMIN {
		public String toString(){
			return TeamSpaceMessages.INSTANCE.memberRoleAdmin();
		}
	},
	MANAGER {
		public String toString(){
			return TeamSpaceMessages.INSTANCE.memberRoleManager();
		}
	},
	USER {
		public String toString(){
			return TeamSpaceMessages.INSTANCE.memberRoleUser();
		}
	},
	GUEST {
		public String toString(){
			return TeamSpaceMessages.INSTANCE.memberRoleGuest();
		}
	}

}
