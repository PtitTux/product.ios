package io.product.server.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.product.server.entities.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class JWTUserDetailsImpl implements UserDetails
{
	private UUID id;
	private String email;
	private String name;
	@JsonIgnore
	private String password;

	public JWTUserDetailsImpl(UUID id, String email, String name, String password)
	{
		this.id = id;
		this.email = email;
		this.name = name;
		this.password = password;
	}

	public static JWTUserDetailsImpl build(UserEntity entity)
	{
		return new JWTUserDetailsImpl(entity.getId(), entity.getEmail(), entity.getName(), entity.getPassword());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		return Collections.emptyList();
	}

	@Override
	public String getPassword()
	{
		return this.password;
	}

	@Override
	public String getUsername()
	{
		return this.email;
	}

	public UUID getId()
	{
		return id;
	}

	public String getEmail()
	{
		return email;
	}

	public String getName()
	{
		return name;
	}

	public String getLabel() {
		return "{id:" + this.id.toString() + ", name:" + this.name + ", email:"+this.email+"}";
	}

	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	@Override
	public boolean isEnabled()
	{
		return true;
	}
}
