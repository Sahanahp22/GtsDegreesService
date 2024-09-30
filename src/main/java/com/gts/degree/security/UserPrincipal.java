package com.gts.degree.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = 1879585839504604929L;

	private long gts_user_id;

	public UserPrincipal(long gts_user_id) {
		this.gts_user_id = gts_user_id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		List<GrantedAuthority> grantedAll = new ArrayList<>();
		List<String> allPermission = new ArrayList<>();

		allPermission.forEach((permission) -> {
			grantedAll.add(new SimpleGrantedAuthority(permission));
		});

		return grantedAll;
		
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

	public long getGts_user_id() {

		return gts_user_id;
	}

	public void setGts_user_id(long gts_user_id) {

		this.gts_user_id = gts_user_id;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

}
