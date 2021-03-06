package ru.vallball.kino01.model;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Proxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;


@SuppressWarnings("serial")
@Entity
@Table(name = "users")
public class User implements UserDetails{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Size(min=3, max=30)
	private String username;
	@NotNull
	@Size(min=3, max=300)
	private String password;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public User() {
		//this.setRole(Role.ROLE_CLIENT);
	}

	public Role getRole() {
		return role;
	}

	/*public void setRole(Role role) {
		this.role = role;
	}*/
	
	public void setRole(String role) {
		this.role = Role.valueOf("ROLE_" + role.toUpperCase());
	}

	public Long getId() {
		return id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
			return Arrays.asList(new SimpleGrantedAuthority(role.getAuthority()));
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public User toUser(PasswordEncoder passwordEncoder) {
		this.setPassword(passwordEncoder.encode(password));
		return this;
	}

	public String toString() {
		return this.getUsername() + " c ролью " + this.getRole() + " " + this.getPassword();
	}
}
