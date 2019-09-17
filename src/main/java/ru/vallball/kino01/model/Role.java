package ru.vallball.kino01.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
	ROLE_CLIENT, ROLE_ADMIN;

	@Override
	public String getAuthority() {
		return name();
	}
}
