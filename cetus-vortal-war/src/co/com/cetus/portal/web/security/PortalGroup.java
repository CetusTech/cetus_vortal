package co.com.cetus.portal.web.security;

import java.io.Serializable;
import java.security.Principal;
import java.security.acl.Group;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

public class PortalGroup implements Group, Serializable {

	private static final long serialVersionUID = 1L;
	private final String name;
	private final Set<Principal> users = new HashSet<Principal>();

	public PortalGroup(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean addMember(Principal user) {
		return users.add(user);
	}

	@Override
	public boolean isMember(Principal member) {
		return users.contains(member);
	}

	@Override
	public Enumeration<? extends Principal> members() {
		return Collections.enumeration(users);
	}

	@Override
	public boolean removeMember(Principal user) {
		return users.remove(user);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PortalGroup) {
			return ((PortalGroup) obj).getName().equals(this.name);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

}
